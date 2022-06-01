package com.ssafy.final_project.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.final_project.model.dto.HouseDealDto;
import com.ssafy.final_project.model.dto.HouseInfoDto;
import com.ssafy.final_project.model.dto.HouseLikeDto;
import com.ssafy.final_project.model.mapper.HouseDealMapper;
import com.ssafy.final_project.model.mapper.HouseInfoMapper;
import com.ssafy.final_project.model.mapper.HouseLikeMapper;

@Service
public class HouseServiceImpl implements HouseService {

	private HouseInfoMapper houseinfoMapper;
	private HouseDealMapper houseDealMapper;
	private HouseLikeMapper houseLikeMapper;



	public HouseServiceImpl(HouseInfoMapper houseinfoMapper, HouseDealMapper houseDealMapper,
			HouseLikeMapper houseLikeMapper ) {

		this.houseinfoMapper = houseinfoMapper;
		this.houseLikeMapper = houseLikeMapper;
		this.houseDealMapper = houseDealMapper;
	}

	@Override
	public List<HouseInfoDto> selectHouseByDong(String dongCode) throws Exception {


		List<HouseInfoDto> list = houseinfoMapper.selectHouseByDong(dongCode);

		return list;
	}

	// 하우스 인포와 하우스 딜간의 조인은 딜이한다.
	@Override
	public Map<String, Object> getHouseDetailById(int aptCode) throws Exception {

		HouseInfoDto infoDto = houseinfoMapper.selectHouseByAptCode(aptCode);
		List<HouseDealDto> dealDtos = houseDealMapper.selectDealByAptCode(aptCode);
		int count = houseLikeMapper.getHouseLike(aptCode);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseInfo", infoDto);
		map.put("houseDealInfo", dealDtos);
		map.put("houseLikeCount", count);

		return map;
	}

	@Override
	public boolean getMyHouseLikeState(HouseLikeDto houseLikeDto) throws Exception {
		boolean result = houseLikeMapper.userLikeStateAboutHouse(houseLikeDto) == 1 ? true : false;
		return result;

	}

	@Override
	public int likeHouse(Map<String, String> map) throws Exception {
		return houseLikeMapper.setHouseLike(map);

	}

	@Override
	public int unLikeHouse(Map<String, String> map) throws Exception {

		return houseLikeMapper.unSetHouseLike(map);
	}


	@Override
	public List<HouseLikeDto> getMyHouseLikeList(String userId) throws Exception {

		return houseLikeMapper.selectAllByUserIdWhenLike(userId);
	}

	@Override
	public List<HouseInfoDto> selectHouseByDongWithLike(Map<String, String> map) throws Exception {

		return houseinfoMapper.selectHouseByDongWithLike(map);

	}

}
