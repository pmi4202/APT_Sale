package com.ssafy.final_project.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.final_project.model.dto.HouseDealDto;


@Mapper
public interface HouseDealMapper {
	List<HouseDealDto> selectDealByAptCode(int aptCode) throws Exception;
}
