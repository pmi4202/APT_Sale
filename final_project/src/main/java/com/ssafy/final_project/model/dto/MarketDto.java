package com.ssafy.final_project.model.dto;

public class MarketDto {
	private int marketingId;
	private String marketName;
	private String subcategoryCode;
	private String dongCode;
	private String address;
	private double lng; //경도
	private double lat; //위도
	
	public String toJson() {
		return String.format("{marketingId:%d,marketName:%s,subcategoryCode:%s,dongCode:%s,address:%s,lng:%d,lat:%d}"
				, marketingId,marketName,subcategoryCode,dongCode,address,lng,lat);
	}
	
	public int getMarketingId() {
		return marketingId;
	}
	public void setMarketingId(int marketingId) {
		this.marketingId = marketingId;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getSubcategoryCode() {
		return subcategoryCode;
	}
	public void setSubcategoryCode(String subcategoryCode) {
		this.subcategoryCode = subcategoryCode;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	

}
