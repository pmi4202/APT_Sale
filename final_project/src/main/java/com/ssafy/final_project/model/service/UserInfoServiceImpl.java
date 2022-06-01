
package com.ssafy.final_project.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.final_project.model.dto.UserInfoDto;
import com.ssafy.final_project.model.mapper.UserInfoMapper;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	private PasswordEncoder passwordEncoder;
	public UserInfoServiceImpl(PasswordEncoder passwordEncoder){
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	private UserInfoMapper userinfoMapper;

	@Override
	public UserInfoDto login(Map<String, String> paramMap) throws Exception {
		return userinfoMapper.login(paramMap);
	}

	@Override
	public int regist(UserInfoDto dto) throws Exception {
		dto.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));
		return userinfoMapper.regist(dto);
	}

	@Override
	public void delete(String userid) throws Exception {
		userinfoMapper.updateToDeleteState(userid);
	}

	@Override
	public int update(UserInfoDto dto) throws Exception {
		return userinfoMapper.update(dto);
	}

	@Override //안쓰임 ...
	public UserInfoDto read(String userid) throws Exception {
		// TODO Auto-generated method stub
		return userinfoMapper.read(userid);
	}

	@Override
	public UserInfoDto findPassword(UserInfoDto userDto) throws Exception {

		return userinfoMapper.findPassword(userDto);
	}

	@Override
	public int retoreAccount(String userid) throws Exception {
		
		return userinfoMapper.updateDeletedByUserId(userid);
	}
}