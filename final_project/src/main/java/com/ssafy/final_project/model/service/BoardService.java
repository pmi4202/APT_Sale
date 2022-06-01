package com.ssafy.final_project.model.service;

import java.sql.SQLException;


import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.final_project.model.dto.BoardDto;
import com.ssafy.final_project.model.dto.CommentDto;
import com.ssafy.final_project.model.dto.ListParameterDto;
import com.ssafy.util.PageNavi;


public interface BoardService {

	/**
	 * 게시글을 작성
	 * @param dto
	 * @param path 
	 * @param files 
	 * @return
	 * @throws SQLException
	 */
	int insertBoard(BoardDto dto, MultipartFile[] files, String path)throws Exception;
	
	/**
	 * no 글 번호 게시글을 삭제한다, 그리고 그 글번호를 가진 레코드의 userId가 일치하여야한다. 
	 * @param no
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	int deleteBoard(Map<String,String> paramMap)throws Exception;
	
	/**
	 * 해당 글번호의 정보를 가져온다. 언제 쓰냐 ? 업데이트 하기전에 다 불러와야하고 , 상세보기 할 때 
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	BoardDto selectOne(int no) throws Exception;

	int updateBoard(BoardDto dto) throws Exception;


	PageNavi getNaviAttribute(ListParameterDto lpd)
			throws Exception;

	List<CommentDto> selectCommentByBoadrNo(int no) throws Exception;

	int createComment(CommentDto dto) throws Exception;

	int deleteComment(CommentDto dto) throws Exception;

	int insertBoard(BoardDto dto) throws Exception;

	List<BoardDto> selectBoard(ListParameterDto pageDto) throws Exception;

}
