package com.ssafy.final_project.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.final_project.model.dto.HouseLikeDto;
import com.ssafy.final_project.model.dto.HouseLikeWithRowNumDto;

public interface HouseLikeMapper {
	
	/**
	 * 
	 * @param aptCode 건물 코드
	 * @return 해당 건물의 좋아요 수를 반환한다. 
	 * @throws SQLException
	 */
	int getHouseLike(int aptCode) throws SQLException;
	
	/**
	 * 
	 * @param aptCode 건물 코드
	 * @param userId 유저 아이디 
	 * @return boolean 내가 해당 건물을 좋아요 했는지 안했는지 했으면 true , 안했으면 false
	 * @throws SQLException
	 */
	int userLikeStateAboutHouse(HouseLikeDto houseLikeDto) throws SQLException;
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 * 해당 집을 좋아요한다.
	 */
	int setHouseLike(Map<String,String> map) throws SQLException;
	
	/**
	 * 해당집의 좋아요를 취소한다.
	 * @param aptCode
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	int unSetHouseLike(Map<String, String> map) throws SQLException;
	
	/**
	 * 
	 * @return 모든 좋아요 정보
	 * @throws SQLException
	 */
	List<HouseLikeWithRowNumDto> selectAllDataExceptME(String userid)throws SQLException;
	
	int selectUserCount() throws SQLException;
	
	List<HouseLikeWithRowNumDto> selectHouseLikeByUserId(Map<String, String> map)throws SQLException;

	List<HouseLikeDto> selectAllByUserIdWhenLike(String userId)throws SQLException;
}
