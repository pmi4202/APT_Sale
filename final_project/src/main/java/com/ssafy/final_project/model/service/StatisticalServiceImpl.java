package com.ssafy.final_project.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.final_project.model.dto.DealAmountRankingDto;
import com.ssafy.final_project.model.dto.DealCountRankingDto;
import com.ssafy.final_project.model.dto.LikeRankingDto;
import com.ssafy.final_project.model.mapper.StatisticalMapper;

@Service
public class StatisticalServiceImpl implements StatisticalService{

	
	private StatisticalMapper statisticalMapper;
	public StatisticalServiceImpl(StatisticalMapper statisticalMapper) {
		this.statisticalMapper = statisticalMapper;
	}
	@Override
	public List<LikeRankingDto> getLikeRanking(Map<String, String> paramMap) throws Exception {
	
		return statisticalMapper.selectAptLikeTop10(paramMap);
	}

	@Override
	public List<DealAmountRankingDto> getDealMaxRaking(Map<String, String> paramMap) throws Exception {
		
		return statisticalMapper.selectAptDealAmountTop10(paramMap);
	}

	@Override
	public List<DealCountRankingDto> getDealCountRaking() throws Exception {
		
		return statisticalMapper.selectDealCount();
	}

}
