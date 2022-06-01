package com.ssafy.final_project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.final_project.model.dto.DealAmountRankingDto;
import com.ssafy.final_project.model.dto.DealCountRankingDto;
import com.ssafy.final_project.model.dto.DustDto;
import com.ssafy.final_project.model.dto.LikeRankingDto;
import com.ssafy.final_project.model.service.DustService;
import com.ssafy.final_project.model.service.StatisticalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@Api(tags = "통계 정보를 제공하는 api,인증 불필요")
@RestController
@RequestMapping("/statistic")
public class StatisticsRestController {

	/*
	 * 좋아요가 가장 많은 순으로 동점자는 같은 순위로 치고 순위는 안 밈 , 10위 까지 ,--10개를 넘을 수 있다. select
	 * ranking,aptCode,aptName,dongCode,dongName,like_count from houseinfo h left
	 * outer join (select dense_rank() over(order by count(*) desc) as
	 * ranking,aptCode,count(*) like_count from houseinfolike group by aptCode
	 * having like_count > 0) hlc using (aptCode) where like_count > 0
	 */

	@ApiModel(value = "통계 정보")
	static class StatisticsProtocol<E> {
		
		@ApiModelProperty("성공여부")
		private boolean success;
		@ApiModelProperty("갯수")
		private int cnt;
		@ApiModelProperty("데이터")
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

	@Autowired
	private StatisticalService statisticService;
	@Autowired
	private DustService dustService;
	
	@ApiOperation(value = "탑 10개 좋아요 순")
	@GetMapping(path = { "/liketop10", "/liketop10/{guguncode}" })
	public ResponseEntity<?> likeRaking(@PathVariable(name = "guguncode", required = false) String gugunCode)
			throws Exception {
		// 전체랑 , 동 범위만 지원할 꺼임
		if (gugunCode == null) {
			gugunCode = "";
		}
		// 어디까지 해야될지 모르겟따...

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("guguncode", gugunCode);
		List<LikeRankingDto> likeAptDto = statisticService.getLikeRanking(paramMap);

		StatisticsProtocol<LikeRankingDto> spt = new StatisticsProtocol<>();

		spt.setCnt(likeAptDto.size());
		spt.setRegcodes(likeAptDto);
		spt.setSuccess(true);

		return new ResponseEntity<StatisticsProtocol<LikeRankingDto>>(spt, HttpStatus.OK);
	}

	/*
	 * 상위 10위권 거래액 순위 중복, 순위 증가 x select * from ( select dense_rank() over(order by
	 * dealAmount desc) as ranking,aptCode,aptName,dongCode,dealAmount from
	 * houseinfo hi left outer join (select aptCode,max(dealAmount) as dealAmount
	 * from housedeal where aptCode is not null group by aptCode) hd using(aptCode))
	 * t where ranking <= 10;
	 */
	@ApiOperation(value = "거래 금액 top10")
	@GetMapping(path = { "/dealtop10", "/dealtop10/{guguncode}" })
	public ResponseEntity<?> dealMaxRanking(@PathVariable(name = "guguncode", required = false) String gugunCode)
			throws Exception {

		if (gugunCode == null) {
			gugunCode = "";
		}

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("guguncode", gugunCode);
		List<DealAmountRankingDto> dealAptDto = statisticService.getDealMaxRaking(paramMap);

		StatisticsProtocol<DealAmountRankingDto> spt = new StatisticsProtocol<>();
		spt.setCnt(dealAptDto.size());
		spt.setRegcodes(dealAptDto);
		spt.setSuccess(true);

		return new ResponseEntity<StatisticsProtocol<DealAmountRankingDto>>(spt, HttpStatus.OK);
	}
	@ApiOperation(value = "거래 횟수 top10")
	@GetMapping(path = { "/dealcount" })
	public ResponseEntity<?> dealCountRanking() throws Exception{
		List<DealCountRankingDto> dealAptDto = statisticService.getDealCountRaking();
		
		StatisticsProtocol<DealCountRankingDto> spt = new StatisticsProtocol<>();
		spt.setCnt(dealAptDto.size());
		spt.setRegcodes(dealAptDto);
		spt.setSuccess(true);
		return new ResponseEntity<StatisticsProtocol<DealCountRankingDto> >(spt, HttpStatus.OK);
	}
	
	@GetMapping(path="/dust/{siName}")
	public ResponseEntity<?> getDustInfo(@PathVariable String siName) throws Exception{
		
		List<DustDto> result = dustService.getDustInfo(siName);
		
		return new ResponseEntity<List<DustDto>>(result,HttpStatus.OK);
	}
}
