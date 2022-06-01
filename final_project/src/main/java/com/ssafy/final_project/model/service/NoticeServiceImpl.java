package com.ssafy.final_project.model.service;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.final_project.model.dto.ListParameterDto;
import com.ssafy.final_project.model.dto.NoticeUserDto;
import com.ssafy.final_project.model.mapper.NoticeMapper;
import com.ssafy.util.PageNavi;

@Service
public class NoticeServiceImpl implements NoticeService {

	private NoticeMapper noticeMapper;

	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	public List<NoticeUserDto> selectNotice(ListParameterDto lpd) throws Exception {

		return noticeMapper.selectAllNotice(lpd);
	}

	@Override
	public int insertNotice(NoticeUserDto dto) throws Exception {

		return noticeMapper.insertNotice(dto);

	}

	@Override
	public NoticeUserDto selectNoteByNo(int no) throws Exception {
		return noticeMapper.selectNoteByNo(no);
	}

	@Override
	public int deleteNoticeByNo(Map<String,String> paramMap) throws Exception {

		return noticeMapper.deleteNoticeByNo(paramMap);
	}

	@Override
	public int updateNotice(NoticeUserDto dto) throws Exception {

		return noticeMapper.updateNotice(dto);

	}

	@Override
	public PageNavi makePageNavigation(ListParameterDto lpd) throws Exception {

		int totalCount = noticeMapper.selectCount(lpd);
		
		
		int naviSize = 5;
		
		PageNavi pageNavigation = new PageNavi();

		int totalPageCount = (totalCount -1) / lpd.getItemPerPage() + 1;
		int startPageNum = (lpd.getCurPageNum() -1) / naviSize * naviSize + 1;
		
		pageNavigation.setStartPageNum(startPageNum);
		pageNavigation.setEndPageNum((startPageNum + naviSize -1)>totalPageCount ? totalPageCount:(startPageNum + naviSize -1));
		
		pageNavigation.setPrevPageNum(startPageNum >1 ? startPageNum-1:1);
		pageNavigation.setNextPageNum(pageNavigation.getEndPageNum() >= totalPageCount? totalPageCount: pageNavigation.getEndPageNum()+1);
		
		pageNavigation.setCurPageNum(lpd.getCurPageNum());
		pageNavigation.setHeadPageNum(1);
		pageNavigation.setTailPageNum(totalPageCount);
		return pageNavigation;
	}

}
