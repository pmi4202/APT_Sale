package com.ssafy.final_project.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.final_project.model.dto.MarketDto;
import com.ssafy.final_project.model.dto.SubCategoryDto;

public interface MarketService {
	public List<MarketDto> selectMarkets(Map<String, String> map) throws Exception;
	List<SubCategoryDto> selectSubcategory()throws Exception;
}
