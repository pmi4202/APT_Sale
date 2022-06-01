package com.ssafy.final_project.model.mapper;

import java.sql.SQLException;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.final_project.model.dto.CommentDto;

@Mapper
public interface CommentMapper {
	
	/**
	 * 글번호를 받는다 . 댓글 paging을 수행하기 위해서 dto를 받는다. curPage와 countPerPage만 있으면 될 듯 ?
	 * @param no
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public List<CommentDto> selectComment(int no) throws SQLException;
	
	/**
	 * 코멘트를 받고 업데이트 
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int createComment(CommentDto dto) throws SQLException;
	
	/**
	 * dto에는 유저아이디를 채워 넣으니깐 그걸로 자기가 쓴건지 확인하자 .
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int updateComment(CommentDto dto) throws SQLException;
	
	
	
	public int deleteComment(CommentDto dto)  throws SQLException;
}
