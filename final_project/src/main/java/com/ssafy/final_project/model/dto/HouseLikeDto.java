package com.ssafy.final_project.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HouseLikeDto extends HouseInfoDto{

	
	private int no;


	private int aptCode;
	private String userId;

	@JsonFormat(pattern = "yyyy년 mm월 dd일 hh:mm:ss")
	private LocalDateTime likeTime;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	public int getAptCode() {
		return aptCode;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(LocalDateTime likeTime) {
		this.likeTime = likeTime;
	}

}
