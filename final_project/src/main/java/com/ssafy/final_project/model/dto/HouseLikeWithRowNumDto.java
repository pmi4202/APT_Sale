package com.ssafy.final_project.model.dto;

public class HouseLikeWithRowNumDto extends HouseLikeDto {

	//해당 로우넘은 파티션바이 유저아이디이다.
	private int rowNum;

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	
}
