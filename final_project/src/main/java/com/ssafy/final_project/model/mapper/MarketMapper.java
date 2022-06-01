package com.ssafy.final_project.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.final_project.model.dto.MarketDto;
import com.ssafy.final_project.model.dto.SubCategoryDto;

@Mapper
public interface MarketMapper {
	List<MarketDto> selectMarkets(Map<String,String> map) throws Exception;
	
	List<SubCategoryDto> selectSubcategory() throws Exception;
	
}
