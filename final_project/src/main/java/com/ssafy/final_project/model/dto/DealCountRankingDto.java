package com.ssafy.final_project.model.dto;

public class DealCountRankingDto extends HouseInfoDto{

	private int ranking;
	private int count;
	DealCountRankingDto(){};
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
