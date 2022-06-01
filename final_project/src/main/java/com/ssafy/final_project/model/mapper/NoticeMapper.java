package com.ssafy.final_project.model.mapper;

import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import com.ssafy.final_project.model.dto.ListParameterDto;
import com.ssafy.final_project.model.dto.NoticeUserDto;

public interface NoticeMapper {
	public int selectCount(ListParameterDto lpd) throws SQLException;

	int insertNotice(NoticeUserDto dto) throws SQLException;

	NoticeUserDto selectNoteByNo(int no) throws SQLException;

	int deleteNoticeByNo(Map<String,String> paramMap) throws SQLException;

	int updateNotice(NoticeUserDto dto)throws SQLException;
	public List<NoticeUserDto> selectAllNotice(ListParameterDto listParameterDto) throws SQLException;

}
