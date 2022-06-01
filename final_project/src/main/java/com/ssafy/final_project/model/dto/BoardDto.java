package com.ssafy.final_project.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("게시판")
public class BoardDto {
	@ApiModelProperty("no")
	private int no;
	@ApiModelProperty("제목")
	private String title;
	@ApiModelProperty("내용")
	private String text;
	@ApiModelProperty("작성일")

	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime createDate;
	@ApiModelProperty("작성자 아이디")
	private String userId;
	@ApiModelProperty("작성자 이름")
	private String userName;
	@ApiModelProperty("게시판에 업로드된 파일리스트")

	private List<FileDto> filelist;
	@ApiModelProperty("삭제여부")
	private int deleted;

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public List<FileDto> getFilelist() {
		return filelist;
	}

	public void setFilelist(List<FileDto> filelist) {
		this.filelist = filelist;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
