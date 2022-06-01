package com.ssafy.final_project.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.final_project.model.dto.UserInfoDto;

@Mapper
public interface UserInfoMapper {
	List<UserInfoDto> select();

	UserInfoDto login(Map<String, String> paramMap) throws Exception;

	int regist(UserInfoDto dto) throws Exception;

	void updateToDeleteState(String userid) throws Exception;

	int update(UserInfoDto dto) throws Exception;

	UserInfoDto read(String userid) throws Exception;

	UserInfoDto findPassword(UserInfoDto userDto) throws Exception;

	int updateDeletedByUserId(String userid)throws Exception;

}