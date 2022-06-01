package com.ssafy.final_project.model.service;

import java.util.List;

import com.ssafy.final_project.model.dto.DongDto;
import com.ssafy.final_project.model.dto.SidoDto;



public interface BJDApiService {
	List<SidoDto> selectSidoInfo() throws Exception;

	List<SidoDto> selectGuGunInfo(String sidoCode) throws Exception;

	List<DongDto> selectDongInfo(String gugunCode)throws Exception;
}
