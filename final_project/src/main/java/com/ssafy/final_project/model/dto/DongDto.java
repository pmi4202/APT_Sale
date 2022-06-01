package com.ssafy.final_project.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DongDto")
public class DongDto {
	@ApiModelProperty("동코드")
	private String dongCode;

	@ApiModelProperty("시도 코드")
	private String sidoName;

	@ApiModelProperty("구군코드")
	private String gugunName;

	@ApiModelProperty("동코드")
	private String dongName;
	
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	
	
	public String toJson() {
		return String.format("{\"code\":\"%s\",\"sido\":\"%s\",\"gugun\":\"%s\",\"dong\":\"%s\"}", dongCode,sidoName,gugunName,dongName);
	}
}
