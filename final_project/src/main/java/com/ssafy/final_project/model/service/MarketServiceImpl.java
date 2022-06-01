package com.ssafy.final_project.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.final_project.model.dto.MarketDto;
import com.ssafy.final_project.model.dto.SubCategoryDto;
import com.ssafy.final_project.model.mapper.MarketMapper;

@Service
public class MarketServiceImpl implements MarketService {

	@Autowired
	private MarketMapper marketMapper;

	@Override
	public List<MarketDto> selectMarkets(Map<String, String> map) throws Exception {

		return marketMapper.selectMarkets(map);
	}

	@Override
	public List<SubCategoryDto> selectSubcategory() throws Exception {
		
		return marketMapper.selectSubcategory();
	}

}
