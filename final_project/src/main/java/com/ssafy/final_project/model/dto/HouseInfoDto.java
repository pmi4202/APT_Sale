package com.ssafy.final_project.model.dto;

public class HouseInfoDto {
	private int aptCode;
	private String aptName	;
	private String dongCode	;
	private String dongName	;
	private int buildYear	;
	private String jibun	;
	private String lat	;
	private String lng	;
	private String img	;
	
	private boolean liked;
	
	
	
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
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
	@Override
	public String toString() {
		return "{'aptCode':'" + aptCode + "', 'aptName':'" + aptName + "', 'dongCode':'" + dongCode + "', 'dongName':'"
				+ dongName + "', 'buildYear':'" + buildYear + "', 'jibun':'" + jibun + "', 'lat':'" + lat + "', 'lng':'" + lng
				+ "', 'img':'" + img + "'}";
	}
	public String toJson() {
		return "{'aptCode':'" + aptCode + "', 'aptName':'" + aptName + "', 'dongCode':'" + dongCode + "', 'dongName':'"
				+ dongName + "', 'buildYear':'" + buildYear + "', 'jibun':'" + jibun + "', 'lat':'" + lat + "', 'lng':'" + lng
				+ "', 'img':'" + img + "'}";
	}
	
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
