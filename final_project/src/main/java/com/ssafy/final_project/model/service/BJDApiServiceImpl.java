package com.ssafy.final_project.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.final_project.model.dto.DongDto;
import com.ssafy.final_project.model.dto.SidoDto;
import com.ssafy.final_project.model.mapper.BJDApiMapper;


@Service
public class BJDApiServiceImpl implements BJDApiService {

	BJDApiMapper bjdAPiMapper;

	public BJDApiServiceImpl(BJDApiMapper bjdAPiDao) {
		this.bjdAPiMapper = bjdAPiDao;
	}

	@Override
	public List<SidoDto> selectSidoInfo() throws Exception {

		List<SidoDto> list = bjdAPiMapper.selectSidoInfo();

		return list;
	}

	@Override
	public List<SidoDto> selectGuGunInfo(String sidoCode) throws Exception {
		sidoCode = sidoCode.substring(0, 2);

		List<SidoDto> list = bjdAPiMapper.selectGuGunInfo(sidoCode);

		return list;

	}

	@Override
	public List<DongDto> selectDongInfo(String gugunCode) throws Exception {
		gugunCode = gugunCode.substring(0, 5);
		List<DongDto> list = bjdAPiMapper.selectDongInfo(gugunCode);

		return list;
	}

}
