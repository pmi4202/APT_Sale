package com.ssafy.final_project.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.final_project.model.dto.HouseInfoDto;
import com.ssafy.final_project.model.dto.HouseLikeDto;

public interface HouseService {

	List<HouseInfoDto> selectHouseByDong(String dongCode) throws Exception;

	Map<String, Object> getHouseDetailById(int aptCode)throws Exception;

	boolean getMyHouseLikeState(HouseLikeDto houseLikeDto) throws Exception;

	int likeHouse(Map<String,String> map) throws Exception;
	int unLikeHouse(Map<String, String> map) throws Exception;
	

	List<HouseLikeDto> getMyHouseLikeList(String userId) throws Exception;

	List<HouseInfoDto> selectHouseByDongWithLike(Map<String, String> map) throws Exception;
}
