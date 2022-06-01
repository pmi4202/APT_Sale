package com.ssafy.final_project.model.mapper;

import java.sql.SQLException;


import java.util.List;
import java.util.Map;

import com.ssafy.final_project.model.dto.HouseInfoDto;

public interface HouseInfoMapper {

	List<HouseInfoDto> selectHouseByDong(String dongCode) throws SQLException;

	HouseInfoDto selectHouseByAptCode(int aptCode)throws SQLException;

	int selectMaxAptCode() throws SQLException;
	
	List<HouseInfoDto> selectHouseByDongWithLike(Map<String,String> map) throws SQLException;
}
