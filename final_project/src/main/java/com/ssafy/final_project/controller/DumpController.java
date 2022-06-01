package com.ssafy.final_project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@Api(tags="스프링 시큐리티 api 정보를 나타내기위한 덤프")
@RestController
public class DumpController {

	static class UserDump{
		@ApiModelProperty("아이디")
		private String userid;

		@ApiModelProperty("비밀번호")
		private String password;
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}
	@PostMapping("/login")
	@ApiOperation("로그인")
	public  String login(@RequestBody UserDump a) {
		return "nono";
	}
}
