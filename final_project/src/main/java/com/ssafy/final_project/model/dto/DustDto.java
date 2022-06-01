package com.ssafy.final_project.model.dto;

public class DustDto {
	
	private String dataTime; //측정일
	
	//1 좋음, 2,보통, 3 나쁩 , 4 매우나쁨
	private String khaiValue;// 통합 대기환경 수치
	private String khaiGrade;// 통합 대기환경 지수
	private String so2Grade;// 아황산가스 지수
	private String coGrade; // 일산화 지수
	private String o3Grade;	//오존 지수
	private String no2Grade;	//이산화질소 지수
	private String pm10Grade;//	미세먼지(PM10) 
	private String stationName;
	public DustDto() {}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getKhaiValue() {
		return khaiValue;
	}

	public void setKhaiValue(String khaiValue) {
		this.khaiValue = khaiValue;
	}

	public String getKhaiGrade() {
		return khaiGrade;
	}

	public void setKhaiGrade(String khaiGrade) {
		this.khaiGrade = khaiGrade;
	}

	public String getSo2Grade() {
		return so2Grade;
	}

	public void setSo2Grade(String so2Grade) {
		this.so2Grade = so2Grade;
	}

	public String getCoGrade() {
		return coGrade;
	}

	public void setCoGrade(String coGrade) {
		this.coGrade = coGrade;
	}

	public String getO3Grade() {
		return o3Grade;
	}

	public void setO3Grade(String o3Grade) {
		this.o3Grade = o3Grade;
	}

	public String getNo2Grade() {
		return no2Grade;
	}

	public void setNo2Grade(String no2Grade) {
		this.no2Grade = no2Grade;
	}

	public String getPm10Grade() {
		return pm10Grade;
	}

	public void setPm10Grade(String pm10Grade) {
		this.pm10Grade = pm10Grade;
	}


	
	

}
