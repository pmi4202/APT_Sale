package com.ssafy.final_project.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionException;

import com.ssafy.final_project.model.dto.DealAmountRankingDto;
import com.ssafy.final_project.model.dto.DealCountRankingDto;
import com.ssafy.final_project.model.dto.LikeRankingDto;

public interface StatisticalMapper {

	List<LikeRankingDto> selectAptLikeTop10(Map<String, String> paramMap) throws SqlSessionException;

	List<DealAmountRankingDto> selectAptDealAmountTop10(Map<String, String> paramMap) throws SqlSessionException;
	List<DealCountRankingDto> selectDealCount() throws SqlSessionException;
}
