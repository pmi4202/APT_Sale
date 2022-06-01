package com.ssafy.final_project.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserInfoDto {
	@ApiModelProperty(value = "아이디")
	private String userId;
	@ApiModelProperty(value = "패스워드")
	private String userPassword;
	@ApiModelProperty(value = "이름")
	private String userName;
	@ApiModelProperty(value = "주소")
	private String userAddress;
	@ApiModelProperty(value = "휴대폰 번호")
	private String phoneNumber;
	@ApiModelProperty(value = "등록일")
	private String registerDate;
	@ApiModelProperty(value = "삭제일")
	private String deletedDate;
	@ApiModelProperty(value = "권한 - 0:관리자,1:일반")
	private int grade;
	@ApiModelProperty(value = "탈퇴 여부")
	private int deleted;

	public String getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(String deletedDate) {
		this.deletedDate = deletedDate;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "UserInfoDto [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", userAddress=" + userAddress + ", phoneNumber=" + phoneNumber + ", registerDate=" + registerDate
				+ ", grade=" + grade + "]";
	}
	
	
	
}
