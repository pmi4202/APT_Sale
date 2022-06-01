package com.ssafy.final_project.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author skj
 * 시도 코드와 구군 코드는 스키마가 일치한다.
 * 유지관리에 좋지는 않지만 공유하도록 하겠음
 */
@ApiModel("시도 DTO")
public class SidoDto {
	@ApiModelProperty("시도 Code")
	private String sidoCode; // PK

	@ApiModelProperty("시도 Name")
	private String sidoName; //UNIQUE
	
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String toJson() {
		return String.format("{\"code\":\"%s\",\"name\":\"%s\"}", sidoCode,sidoName);
	}
}
