package com.ssafy.final_project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssafy.final_project.model.dto.ListParameterDto;
import com.ssafy.final_project.model.dto.NoticeUserDto;
import com.ssafy.final_project.model.service.NoticeService;
import com.ssafy.util.PageNavi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Servlet implementation class BoardController
 */
@Api(tags = "공지사항")
@RestController
@RequestMapping("/notice")
public class NoticeController {

	private NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@ApiOperation("공지 사항을 수정한다.")
	@PutMapping("")
	public ResponseEntity<Map<String, Object>> updateNotice(@RequestBody NoticeUserDto dto, @ApiIgnore Authentication authentication)
			throws Exception {

		String userId = authentication.getPrincipal().toString();

		dto.setUserId(userId);
		int count = noticeService.updateNotice(dto);

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success",count == 1 ? true:false);
		map.put("data",dto);
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}

	@ApiOperation("공지사항 no 를 통해 삭제, 권한 어드민")
	@DeleteMapping("/{no}") // no하나 담겨온다.
	public ResponseEntity<Map<String, Object>> deleteNotice(@PathVariable String no,
			@ApiIgnore Authentication authentication) throws Exception {

		int count = 0;

		String userId = authentication.getPrincipal().toString();

		if (userId != null) {
			Map<String, String> map = new HashMap<String, String>() {
				{
					put("no", no);
					put("userid", userId);
				}
			};
			count = noticeService.deleteNoticeByNo(map);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("success", count == 1 ? true : false);
		resultMap.put("count", count);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "공지사항 상세 정보 반환")
	@GetMapping("/{no}")
	public ResponseEntity<Map<String, Object>> specificNotice(@PathVariable("no") int noticeId)
			throws Exception {

		int no = noticeId;

		NoticeUserDto dto = noticeService.selectNoteByNo(no);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", dto != null ? true : false);
		map.put("data", dto);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "공지사항을 작성한다. 어드민 권한")
	@PostMapping("")
	public ResponseEntity<Map<String, Object>> createNotice(@RequestBody NoticeUserDto dto,
			@ApiIgnore Authentication authentication) throws Exception {


		dto.setUserId(authentication.getPrincipal().toString());
		// no는 AI , create 디폴트 ,lastUpdate는 dao에서 처리
		int count = noticeService.insertNotice(dto);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", count == 1 ? true : false);
		map.put("message", count == 1 ? "공지 사항 작성 성공" : "공지 사항 작성 실패");

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "조건에 맞춰 공지사항 리스트 출력")
	@GetMapping("")
	public ResponseEntity<?> getNotice(ListParameterDto lpd) throws Exception {

		List<NoticeUserDto> list = noticeService.selectNotice(lpd);

		PageNavi pageNavi = noticeService.makePageNavigation(lpd);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notices", list);
		map.put("navigation", pageNavi);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
