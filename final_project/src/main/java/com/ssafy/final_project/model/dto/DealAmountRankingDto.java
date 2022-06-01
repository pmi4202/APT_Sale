package com.ssafy.final_project.model.dto;

public class DealAmountRankingDto {

	private int ranking;
	private int aptCode;
	private String aptName;
	private String dongCode;
	private int dealAmount;
	
	public int getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(int dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	
	
}
