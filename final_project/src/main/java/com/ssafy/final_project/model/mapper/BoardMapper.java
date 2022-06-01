package com.ssafy.final_project.model.mapper;

import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.final_project.model.dto.BoardDto;
import com.ssafy.final_project.model.dto.ListParameterDto;


@Mapper
public interface BoardMapper {

	List<BoardDto> selectBoard(ListParameterDto lpd) throws SQLException;

	int insertBoard(BoardDto dto) throws SQLException;

	int deleteBoard(Map<String, String> paramMap) throws SQLException;

	BoardDto selectOne(int no) throws SQLException;

	int updateBoard(BoardDto dto) throws SQLException;

	int getBoardCount(ListParameterDto lpd) throws SQLException;

	int insertFile(BoardDto dto)throws SQLException;

}
