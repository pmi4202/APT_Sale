package com.ssafy.final_project.model.service;



import java.util.Map;

import com.ssafy.final_project.model.dto.UserInfoDto;

public interface UserInfoService {
	UserInfoDto login( Map<String,String> paramMap) throws Exception;
	int regist(UserInfoDto dto)  throws Exception;
	void delete(String userid)  throws Exception;
	int update(UserInfoDto dto) throws Exception;
	public UserInfoDto read(String userid) throws Exception;
	UserInfoDto findPassword(UserInfoDto userDto)throws Exception;
	int retoreAccount(String userid) throws Exception;
	
}
