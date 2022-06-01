package com.ssafy.util;

public class PageNavi {
	int startPageNum; //페이지 시작번호
	int endPageNum; //페이지 끝번호 
	int prevPageNum; //왼쪽 누를 시 가는 곳
	int nextPageNum; //오른쪽 누를 시 가는 곳 
	int headPageNum; //왼쪽 마지막 페이지 
	int tailPageNum; //오른쪽 마지막 페이지
	int curPageNum; //현재 페이지
	

	public int getCurPageNum() {
		return curPageNum;
	}
	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}
	public int getPrevPageNum() {
		return prevPageNum;
	}
	public void setPrevPageNum(int prevPageNum) {
		this.prevPageNum = prevPageNum;
	}
	public int getNextPageNum() {
		return nextPageNum;
	}
	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}
	public int getHeadPageNum() {
		return headPageNum;
	}
	public void setHeadPageNum(int headPageNum) {
		this.headPageNum = headPageNum;
	}
	public int getTailPageNum() {
		return tailPageNum;
	}
	public void setTailPageNum(int tailPageNum) {
		this.tailPageNum = tailPageNum;
	}
	
}
