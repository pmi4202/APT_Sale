package com.ssafy.final_project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.final_project.model.dto.DongDto;
import com.ssafy.final_project.model.dto.SidoDto;
import com.ssafy.final_project.model.service.BJDApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;


@Api(tags = "법정동 코드 API")
@RequestMapping("/bjd")
@RestController
public class BJDApiController {
	
	private BJDApiService bjdApiService;
	BJDApiController(BJDApiService bjdApiService){
		this.bjdApiService = bjdApiService;
	}
	
	@ApiModel(value = "법정동 정보")
	static class BJDProtocol<E> {
		@ApiModelProperty(value = "성공 여부")
		private boolean success;
		@ApiModelProperty(value = "반환된 레코드 수 ")
		private int cnt;
		@ApiModelProperty(value = "법정동 데이터")
		private List<E> regcodes;

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public int getCnt() {
			return cnt;
		}

		public void setCnt(int cnt) {
			this.cnt = cnt;
		}

		public List<E> getRegcodes() {
			return regcodes;
		}

		public void setRegcodes(List<E> regcodes) {
			this.regcodes = regcodes;
		}

	}

	@ApiOperation(value="5자리 코드를 주면 동코드 리스트 , 이하 오퍼레이션들은 코드 길이가 명시되있으나 알아서 잘라가지고 처리한다.")
	@GetMapping("/dong/{guguncode}")
	public ResponseEntity<?> getDongInfo(@PathVariable("guguncode") String gugunCode) throws Exception {

		List<DongDto> list = bjdApiService.selectDongInfo(gugunCode);

		BJDProtocol<DongDto> bpt = new BJDProtocol<>();
		bpt.setRegcodes(list);
		bpt.setCnt(list.size());

		if (list == null || list.size() == 0) {
			return new ResponseEntity<BJDProtocol<DongDto>>(bpt, HttpStatus.OK);
		}
		bpt.setSuccess(true);
		return new ResponseEntity<BJDProtocol<DongDto>>(bpt, HttpStatus.OK);
	}
	
	@ApiOperation(value="2자리 시도코드 => 구군코드")
	@GetMapping("/gugun/{sidocode}")
	public ResponseEntity<BJDProtocol<SidoDto>> getGugunInfo(@PathVariable("sidocode") String sidoCode)
			throws Exception {
		// 구군 코드를 얻기위해서는 시도코드가 필요

		List<SidoDto> list = bjdApiService.selectGuGunInfo(sidoCode);

		BJDProtocol<SidoDto> bpt = new BJDProtocol<>();

		bpt.setRegcodes(list);
		bpt.setCnt(list.size());

		if (list == null || list.size() == 0) {
			return new ResponseEntity<BJDProtocol<SidoDto>>(bpt, HttpStatus.OK);
		}
		bpt.setSuccess(true);
		return new ResponseEntity<BJDProtocol<SidoDto>>(bpt, HttpStatus.OK);
	}

	@ApiOperation(value="시도 코드를 반환")
	@GetMapping("/sido")
	public ResponseEntity<BJDProtocol<SidoDto>> getSidoInfo() throws Exception {

		List<SidoDto> list = bjdApiService.selectSidoInfo(); 

		BJDProtocol<SidoDto> bpt = new BJDProtocol<>();

		bpt.setRegcodes(list);
		bpt.setCnt(list.size());

		if (list == null || list.size() == 0) {
			return new ResponseEntity<BJDProtocol<SidoDto>>(bpt, HttpStatus.OK);
		}

		bpt.setSuccess(true);
		return new ResponseEntity<BJDProtocol<SidoDto>>(bpt, HttpStatus.OK);
	}
}
