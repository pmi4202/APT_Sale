package com.ssafy.final_project.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("공지사항 DTO")
public class NoticeUserDto {
	@ApiModelProperty("no")
	private int no	;
	@ApiModelProperty("제목")
	private String title;
	@ApiModelProperty("내용")
	private String text	;
	@ApiModelProperty("작성일")
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime createDate;
	@ApiModelProperty("작성자 아이디")
	private String userId;
	@ApiModelProperty("작성자 이름")
	private String userName; //조인 속성인데 걍 쓰자 
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userid) {
		userId = userid;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
