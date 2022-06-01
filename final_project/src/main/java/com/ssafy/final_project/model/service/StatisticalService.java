package com.ssafy.final_project.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.final_project.model.dto.DealAmountRankingDto;
import com.ssafy.final_project.model.dto.DealCountRankingDto;
import com.ssafy.final_project.model.dto.LikeRankingDto;

public interface StatisticalService {

	List<LikeRankingDto> getLikeRanking(Map<String, String> paramMap) throws Exception;

	List<DealAmountRankingDto> getDealMaxRaking(Map<String, String> paramMap)  throws Exception;

	List<DealCountRankingDto> getDealCountRaking() throws Exception;


}
