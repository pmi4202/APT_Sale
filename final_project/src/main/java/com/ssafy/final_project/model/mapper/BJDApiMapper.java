package com.ssafy.final_project.model.mapper;

import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.final_project.model.dto.DongDto;
import com.ssafy.final_project.model.dto.SidoDto;

@Mapper
public interface BJDApiMapper {
	ArrayList<SidoDto> selectSidoInfo() throws SQLException;

	List<SidoDto> selectGuGunInfo(String sidoCode)throws SQLException;

	List<DongDto> selectDongInfo(String gugunCode)throws SQLException;
}
