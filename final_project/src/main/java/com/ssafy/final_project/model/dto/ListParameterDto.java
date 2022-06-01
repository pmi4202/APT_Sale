package com.ssafy.final_project.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ListParameterDto {

	@ApiModelProperty(value = "페이지에 보여줄 갯수")
	private int itemPerPage;// 페이지 당 글 갯수

	@ApiModelProperty(value = "요청 페이지")
	private int curPageNum;// 페이지 번호

	@ApiModelProperty(value = "검색 키워드")
	private String keyWord;

	@ApiModelProperty(value = "검색 단어")
	private String searchWord;

	// 시작 레코드 위치
	public int getStartRecordNum() {
		return (curPageNum - 1) * itemPerPage;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public ListParameterDto(int itemPerPage, int curPageNum, String keyWord, String searchWord) {
		super();
		this.itemPerPage = itemPerPage;
		this.curPageNum = curPageNum;
		this.keyWord = keyWord;
		this.searchWord = searchWord;
	}

	public ListParameterDto() {
		this.itemPerPage = 10;// 페이지 당 글 갯수
		this.curPageNum = 1;// 페이지 번호
		this.keyWord = "";
		this.searchWord = "";
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public int getCurPageNum() {
		return curPageNum;
	}

	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}

}
