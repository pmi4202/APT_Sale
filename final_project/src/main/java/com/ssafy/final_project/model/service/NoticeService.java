package com.ssafy.final_project.model.service;

import java.util.List;

import java.util.Map;

import com.ssafy.final_project.model.dto.ListParameterDto;
import com.ssafy.final_project.model.dto.NoticeUserDto;
import com.ssafy.util.PageNavi;

public interface NoticeService {
	public PageNavi makePageNavigation(ListParameterDto paramMap) throws Exception;
	List<NoticeUserDto> selectNotice(ListParameterDto lpd) throws Exception;

	int insertNotice(NoticeUserDto dto) throws Exception;

	NoticeUserDto selectNoteByNo(int no)throws Exception;

	int deleteNoticeByNo(Map<String,String> paramMap)throws Exception;

	int updateNotice(NoticeUserDto dto) throws Exception;
	
}
