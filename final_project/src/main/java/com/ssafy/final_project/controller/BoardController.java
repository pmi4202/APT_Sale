package com.ssafy.final_project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.final_project.model.dto.BoardDto;
import com.ssafy.final_project.model.dto.CommentDto;
import com.ssafy.final_project.model.dto.FileDto;
import com.ssafy.final_project.model.dto.ListParameterDto;
import com.ssafy.final_project.model.service.BoardService;
import com.ssafy.util.PageNavi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Servlet implementation class BoardController
 */
// 게시글을 처리하는 컨트롤러 
@Api(tags = "게시글 컨트롤러")
@RestController
@RequestMapping("/board")
public class BoardController extends HttpServlet {

	private BoardService boardService;
	// 실제 파일 구동 루트를 가져오기 위함!
	private ServletContext servletContext;

	public BoardController(BoardService boardService, ServletContext servletContext) {
		this.boardService = boardService;
		this.servletContext = servletContext;
	}

	/**
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception 로그인 안해도 됨
	 */
	@ApiOperation(value = "게시판 리스트를 반환한다.")
	@GetMapping("")
	public ResponseEntity<?> home(@ModelAttribute ListParameterDto lpd ) throws Exception {

		List<BoardDto> boardList = boardService.selectBoard(lpd);
		PageNavi navigation = boardService.getNaviAttribute(lpd);

		// mav.addObject("boardlist", booadList);
		// mav.addObject("navigation", navigation);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("boards", boardList);
		resultMap.put("navigation", navigation);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	/**
	 * 비동기 요청에 대해 boardno 에 맞춰 그 상세게시판의 댓글을 넘겨준다. $$ reponsebody 배운 후 해결하자. 미리 조치는
	 * 해놓자.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@ApiModel(value = "댓글 정보")
	static class CommentProtocol<T> {
		@ApiModelProperty(value = "성공 여부")
		private boolean success;
		// cnt..
		@ApiModelProperty(value = "갯수")
		private int cnt;
		@ApiModelProperty(value = "댓글 정보")
		private T data;

		public int getCnt() {
			return cnt;
		}

		public void setCnt(int cnt) {
			this.cnt = cnt;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public void setData(T data) {
			this.data = data;
		}

		public boolean isSuccess() {
			return success;
		}

		public T getData() {
			return data;
		}
	}

	@ApiOperation(value = "board의 댓글들을가져온다(전체).")
	// 컨트롤러의 리퀘스트 맵핑 ./board
	@GetMapping("/comment/{boardno}") // 게시판중에 댓글을 작성하는데 , boardno라는 특정게시판에.
	public ResponseEntity<?> getComment(@PathVariable("boardno") String boardno) throws Exception {

		int boardNo = Integer.parseInt(boardno);

		List<CommentDto> data = null;
		data = boardService.selectCommentByBoadrNo(boardNo);

		CommentProtocol<List<CommentDto>> cpt = new CommentProtocol<List<CommentDto>>();

		if (data == null || data.size() == 0) {
			cpt.setSuccess(false);
			cpt.setCnt(0);
			return new ResponseEntity<CommentProtocol<List<CommentDto>>>(cpt, HttpStatus.OK);
		}

		cpt.setCnt(data.size());
		cpt.setSuccess(true);
		cpt.setData(data);
		return new ResponseEntity<CommentProtocol<List<CommentDto>>>(cpt, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 삭제 , 댓글 no를 통해 , 해당 유저의 댓글인지 보고 삭제")
	@DeleteMapping("/comment/{no}") // 코멘트에서 id(no)를 삭제하라.PK
	public ResponseEntity<?> deleteComment(@PathVariable("no") int no, @ApiIgnore Authentication authentication)
			throws Exception {

		CommentDto dto = new CommentDto();

		dto.setNo(no); // mapper까지 코멘트 dto넘겨주는데,
		// 삭제 조건이 , no 같고 ,userid 같은 거임
		dto.setUserId(authentication.getPrincipal().toString());

		int count = boardService.deleteComment(dto);

		CommentProtocol<String> cpt = new CommentProtocol<String>();

		cpt.setCnt(count);
		if (count != 1) { // 삭제가 되지 않음
			cpt.setSuccess(false);
			cpt.setData("댓글 삭제 실패");

		} else { // 1일 경우
			cpt.setSuccess(true);
			cpt.setData("댓글 삭제 성공");
		}

		return new ResponseEntity<CommentProtocol<String>>(cpt, HttpStatus.OK);
	}

	@ApiModelProperty("boardno를 가진 게시글에 댓글을 작성한다.")
	@PostMapping("/comment/{boardno}")
	public ResponseEntity<?> createComment(@PathVariable("boardno") String boardNo, @RequestBody CommentDto comment,
			 @ApiIgnore Authentication authentication) throws Exception {

		CommentProtocol<String> cpt = new CommentProtocol<String>();
		String userId = authentication.getPrincipal().toString();

		if (comment == null || boardNo == null || userId == null || boardNo.isEmpty() || comment.getText() == null) {
			cpt.setData("댓글 작성 요건을 만족하지 못합니다.");
			cpt.setSuccess(false);
			cpt.setCnt(-1);
			return new ResponseEntity<CommentProtocol<String>>(cpt, HttpStatus.BAD_REQUEST);
		}

		CommentDto dto = new CommentDto();
		dto.setText(comment.getText());
		dto.setBoardNo(boardNo); // int로 db에 기록되있는데 , 이미 스트링 객체로한 코드가 있다..(지나친 듯)
		// 따라서 그냥 스트링으로 쓰겠다.

		dto.setUserId(userId);

		int result = boardService.createComment(dto);

		if (result != 1) {
			cpt.setCnt(result);
			cpt.setData("댓글 생성 중 오류 발생");
			cpt.setSuccess(false);
			return new ResponseEntity<CommentProtocol<String>>(cpt, HttpStatus.EXPECTATION_FAILED);
		}

		cpt.setCnt(result);
		cpt.setData("댓글 작성 성공");
		cpt.setSuccess(true);
		return new ResponseEntity<CommentProtocol<String>>(cpt, HttpStatus.OK);

	}

	@ApiOperation("게시글 논리적 삭제")
	@DeleteMapping("/{boardno}")
	public ResponseEntity<Map<String, Object>> boardDelete(
			@PathVariable(name = "boardno", required = true) String boardNo, @ApiIgnore Authentication authentication)
			throws Exception {

		String userId = authentication.getPrincipal().toString();

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("boardno", boardNo);
		paramMap.put("userid", userId);

		int result = boardService.deleteBoard(paramMap);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("success", result == 1 ? true : false);
		resultMap.put("count", result);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	// !--- 이 밑에 다시 다하셈 아직 덜함 ...
	// 나중에하자 ....
	@PutMapping("")
	public ResponseEntity<?> boardUpdate(@RequestBody BoardDto dto, Authentication authentication) throws Exception {

		String userId = (String) authentication.getPrincipal();
		dto.setUserId(userId);

		int result = boardService.updateBoard(dto);
		if (result != 1) {
			throw new Exception("게시판 업데이트 오류");
		}

		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success",result == 1?true:false);
		return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<?> writeBoard(@ModelAttribute BoardDto dto,
			@RequestPart(required = false) MultipartFile[] files, Authentication authentication)
			throws Exception {

		String id = authentication.getPrincipal().toString();
		dto.setUserId(id);

		int result = 0;
		if (files == null || files.length == 0) {
			result = boardService.insertBoard(dto);
		} else {
			// 사용자가 파일 입력했으면
			String path;
			path = servletContext.getRealPath("/resources");
			// 내가 알기로 webapp 밑에 안만든다 쳐도 만들어지는 걸로 알음.
			result = boardService.insertBoard(dto, files, path);
		}

		if (result != 1) {
			throw new Exception("뭔가 이상함 ");
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", result == 1);
		resultMap.put("message", result == 1 ? "글 작성 완료" : "글 작성 오류");
		// 새로 고침하면 글 작성 2개되는 것을 막기 위해 redirect
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	/**
	 * 로그인 안해도 됨
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("게시글 상세보기")
	@GetMapping("/detail/{boardno}")
	public ResponseEntity<Map<String, Object>> specificBoard(@PathVariable("boardno") int boardNo) throws Exception {

		BoardDto dto = boardService.selectOne(boardNo);

		if (dto != null && dto.getText() != null) {
			dto.setText(dto.getText().replace("\r\n", "</br>").replace(" ", "&nbsp;"));
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("board", dto.getDeleted() == 1 ? null : dto);
		resultMap.put("success", dto != null && dto.getDeleted() != 1 ? true : false);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation("파일 다운로드")
	@GetMapping("/file")
	public ModelAndView boardFileDownload(@ModelAttribute FileDto fileDto) {

		// request scope라서 modelattrubute 살아있음
		return new ModelAndView("fileDownLoadView");

	}

}
