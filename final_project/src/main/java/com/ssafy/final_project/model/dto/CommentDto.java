package com.ssafy.final_project.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("댓글")
public class CommentDto {
	@ApiModelProperty("no")
	private int no; //댓글 레코드의 id

	@ApiModelProperty("작성자 아이디")
	private String userId;
	@ApiModelProperty("작성자 이름")
	private String userName;
	@ApiModelProperty("댓글이 작성된 게시판")
	private String boardNo;
	//실제 db 에는 int형이다.
	@ApiModelProperty("내용")
	private String text;
	@ApiModelProperty("작성일")
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime editDate;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toJson() {
		return String.format("{\"userId\":\"%s\",\"no\":\"%d\",\"boardNo\":\"%d\",\"text\":\"%s\",\"editDate\":\"%s\",\"lastEditDate\":\"%s\"}", 
				userId,no,boardNo,text,editDate);
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getEditDate() {
		return editDate;
	}
	public void setEditDate(LocalDateTime editDate) {
		this.editDate = editDate;
	}

	
}
