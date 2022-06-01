package com.ssafy.final_project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.final_project.error.NotSatisfiedAthorityException;
import com.ssafy.final_project.model.dto.UserInfoDto;
import com.ssafy.final_project.model.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Servlet implementation class UserInfoController
 */

@Api(tags = "유저 관련 컨트롤러")
@RestController
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService service;

//	@PostMapping("/restore")
//	public ResponseEntity<Map<String, Object>> restoreAccount(@ApiIgnore Authentication authentication)
//			throws Exception {
//		String userId = authentication.getPrincipal().toString();
//		service.retoreAccount(userId);
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sucess", true);
//		map.put("message", "회원이 복구 되었습니다.");
//
//		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//	}

//	@PostMapping("/findpassword")
//	public String findPassWord(@RequestBody UserInfoDto userDto, Model model) throws Exception {
//
//		String path = "findPassword";
//
//		UserInfoDto dto = service.findPassword(userDto);
//
//		if (dto == null) {
//			model.addAttribute("passmsg", "일치하는 계정이 없습니다.");
//		} else {
//			model.addAttribute("passmsg", String.format("id : %s, pass : %s", dto.getUserId(), dto.getUserPassword()));
//		}
//
//		return path;
//	}

	@GetMapping("/logout")
	public String logout() {
		// 로그아웃하면 클라이언트 쪽에서 세션에 있는 JWT 삭제하면 되는 거 아님?
		return "redirect:/index";
	}

	@PutMapping("")
	public ResponseEntity<Map<String, Object>> doUpdateUser(@RequestBody UserInfoDto dto,
			@ApiIgnore Authentication authentication) throws Exception {

		int cnt = 0;

		cnt = service.update(dto);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", cnt==1);
		map.put("message", cnt==1 ?  "회원 정보가 수정되었습니다.":"회원 정보 수정이 실패 되었습니다.");

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "논리적 유저 삭제")
	@DeleteMapping("")
	public ResponseEntity<?> doDeleteUser(@ApiIgnore Authentication authentication) throws Exception {

		// 인터셉터에서 로그인 중인지는 확인을 할텐데,
		//
		String userId = (String) authentication.getPrincipal();
		service.delete(userId);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("success", true);
		resultMap.put("message", "회원 탈퇴 되었습니다. 토큰을 삭제 바람");

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "사용자 상세 정보 조회", notes = "토큰에서 유저 정보 추출, 토큰 유효 검사 , 후 해당 유저 id를 통해 가져옴 데이터를")
	@GetMapping("/{userid}")
	public ResponseEntity<?> myPage(@PathVariable String userid, @ApiIgnore Authentication authentication)
			throws Exception {
		if (authentication == null) {
			throw new NotSatisfiedAthorityException("사용자 상세 정보 조회를 하기 위한 권한이 없습니다.");
		}
		UserInfoDto userDto = service.read((String) authentication.getPrincipal());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("success", true);
		resultMap.put("user", userDto);
		resultMap.put("message", "유저 정보 반환");

		if (userDto == null) {
			resultMap.put("success", false);
			resultMap.put("message", "유저 상세보기 실패");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입")
	@PostMapping("")
	public ResponseEntity<?> doRegist(@RequestBody UserInfoDto dto) throws Exception {

		int cnt = 0;

		cnt = service.regist(dto);

		if (cnt == 0) {
			throw new Exception("회원 가입 중 오류 발생");
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);

	}

}
