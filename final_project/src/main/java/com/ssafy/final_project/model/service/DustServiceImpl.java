package com.ssafy.final_project.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.final_project.model.dto.DustDto;
import com.ssafy.util.dust.*;

@Service
public class DustServiceImpl implements DustService{

	private DustUtil dustUtil;
	
	public DustServiceImpl(DustUtil dustUtil) {
		this.dustUtil = dustUtil;
	}
	
	@Override
	public List<DustDto> getDustInfo(String siName) throws Exception {
		
		return dustUtil.getDustList(siName);
	}

}
