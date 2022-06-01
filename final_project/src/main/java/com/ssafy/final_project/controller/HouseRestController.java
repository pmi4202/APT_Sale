package com.ssafy.final_project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafy.final_project.error.HouseLikeException;
import com.ssafy.final_project.error.NotSatisfiedAthorityException;
import com.ssafy.final_project.model.dto.HouseInfoDto;
import com.ssafy.final_project.model.dto.HouseLikeDto;
import com.ssafy.final_project.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@Api(tags = "아파트 매물 관련 정보")
@RestController
@RequestMapping("/house")
public class HouseRestController  {

	@Autowired
	private HouseService houseService;
	
	public HouseRestController(  HouseService houseService){
		this.houseService = houseService;
	}
	
	// 세션을 쓰고 있다. 나중에 JWT 추가 후 수정 필요
	@ApiOperation(value = "해당 하우스의 좋아요 취소")
	@PostMapping("/unlike/{aptcode}")
	public ResponseEntity<?> unLikeHouse(@PathVariable("aptcode") String aptCode,Authentication authentication) throws Exception {

		String userId = (String)authentication.getPrincipal();
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("userid", userId);
		map.put("aptcode", aptCode);

		if (houseService.unLikeHouse(map) == 0) {
			throw new HouseLikeException("좋아요를 하지 않았습니다.",aptCode,userId);
		}
		
		return new ResponseEntity<Map<String,String>>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="사용자가 좋아요한 apt 리스트")
	@GetMapping("/like")
	public  ResponseEntity<?> likeHouseList(
			Authentication authentication )throws Exception{
		String userId = (String)authentication.getPrincipal(); 
		List<HouseLikeDto> houses =   houseService.getMyHouseLikeList(userId);
		
		HouseInfoProtocol<HouseLikeDto> result = new HouseInfoProtocol<HouseLikeDto>();
		
		if(houses == null) {
			result.setSuccess(false);
			return new ResponseEntity<HouseInfoProtocol<HouseLikeDto>>(result,HttpStatus.OK);
		}
		result.setCnt(houses.size());
		result.setSuccess(true);
		result.setRegcodes(houses);
		
		return new ResponseEntity<HouseInfoProtocol<HouseLikeDto>>(result,HttpStatus.OK);
	}
	
	@ApiOperation(value = "하우스 좋아요")
	@PostMapping("/like/{aptcode}")
	public ResponseEntity<?> likeHouse(@RequestParam("aptcode") String aptCode,Authentication authentication) throws Exception {
		
		String userId = (String)authentication.getPrincipal(); 

		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("aptcode", aptCode);

		if (houseService.likeHouse(map) == 0) {
			throw new HouseLikeException("좋아요 실패",aptCode,userId);
		}
		
		return new ResponseEntity<Map<String,String>>(HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "사용자의 하우스 좋아요 상태")
	@PostMapping("/likestate/{aptcode}")
	public ResponseEntity<?> likeHouseState(@RequestParam("aptcode") String aptCode,Authentication authentication) throws Exception {
		
		String userId = (String)authentication.getPrincipal(); 
		
		if(userId == null) {
			//이건 불가능 할 듯 ?
			throw new NotSatisfiedAthorityException("권한 에러");
		}
		
		HouseLikeDto hld = new HouseLikeDto();
		
		hld.setAptCode(Integer.parseInt(aptCode));
		hld.setUserId(userId);
		
		boolean result = houseService.getMyHouseLikeState(hld);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userid", userId);
		map.put("aptcode", aptCode);
		map.put("data",result);
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
	}

	@ApiOperation(value = "하우스 매물 상세보기(하우스,거래내역,좋아요 수)",response = Map.class)
	@GetMapping("/detail/{aptcode}") 
	public ResponseEntity<?> getHouseDetailById(@PathVariable("aptcode") String aptcode,Authentication authentication)
			throws Exception {

		int aptCode = Integer.parseInt(aptcode);

		// select 만 하는 거니깐 원자성이 없어도 되지 않을까?
		Map<String, Object> map = houseService.getHouseDetailById(aptCode);
		//해당 메소드는 거래내역 , 하우스 상세정보, 
		
		
		return new ResponseEntity<	Map<String, Object>>(map,HttpStatus.OK);
	}

	@ApiModel(value = "하우스 정보")
	static class HouseInfoProtocol<E>{
		@ApiModelProperty(value = "성공 여부")
		private boolean success;
		@ApiModelProperty(value = "레코드 수")
		private int cnt;
		@ApiModelProperty(value = "하우스 정보")
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
	
//	@ApiOperation(value = "시도,구군코드로 아파트 리스트 가져오기",response = HouseInfoProtocol.class)
//	@GetMapping("/{code}")
//	public ResponseEntity<?> houseInfoByCode(@PathVariable("code") String code) throws Exception {
//		List<HouseInfoDto> list = houseService.selectHouseByDong(code);
//		HouseInfoProtocol<HouseInfoDto> hipt = new HouseInfoProtocol<>();
//		
//		hipt.setCnt(list.size());
//		hipt.setRegcodes(list);
//		hipt.setSuccess(true);
//		
//		return new ResponseEntity<HouseInfoProtocol<HouseInfoDto>>(hipt,HttpStatus.OK);
//	}
	
	@ApiOperation(value = "시도,구군코드로 아파트 리스트 가져오기 유저 정보 이용해서 조인, 좋아요 상태도 같이",response = HouseInfoProtocol.class)
	@GetMapping("/{code}")
	public ResponseEntity<?> houseInfoByCode(@PathVariable("code") String code,Authentication auth) throws Exception {
		
		
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("dongCode",code);
		paramMap.put("userId",auth.getPrincipal().toString());
		
		List<HouseInfoDto> list = houseService.selectHouseByDongWithLike(paramMap);
		
		HouseInfoProtocol<HouseInfoDto> hipt = new HouseInfoProtocol<>();
		
		hipt.setCnt(list.size());
		hipt.setRegcodes(list);
		hipt.setSuccess(true);
		
		return new ResponseEntity<HouseInfoProtocol<HouseInfoDto>>(hipt,HttpStatus.OK);
	}
	/**
	 * 사용안함
	 * @param user
	 * @return
	 * @throws Exception
	 */
//	@ApiOperation(value = "사용하지 마세요")
//	@GetMapping("/recommend") //뷰에 적용안함 아직
//	public ResponseEntity<?> houseRecommended(@SessionAttribute("user") UserInfoDto user) throws Exception{
//		
//		Map<String,String> paramMap = new HashMap<String, String>();
//		paramMap.put("userid", user.getUserId());
//		
//		//List<TupleIntInt> recommendedHouse = houseService.RecommendedHouse(paramMap);
//		//apt code밖에 안넘겨줘서 ... 아 이걸 , 할려면 코드 아예 다 바꾸거나 , 
//		//여기서 db가서 하나하나 테이블 탐색해서 해결하거나 . 
//		// view 쪽에서 받앗을 떄 ??
//		
//		return null;
//	}

}
