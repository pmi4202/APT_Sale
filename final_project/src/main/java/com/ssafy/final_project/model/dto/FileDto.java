package com.ssafy.final_project.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("파일 DTO")
public class FileDto {

	@ApiModelProperty("no")
	private int no; //FK
	@ApiModelProperty("오리지널 파일 네임")
	private String originalFileName;
	@ApiModelProperty("저장 시 지정된 파일네임")
	private String saveFileName;
	
	@ApiModelProperty("저장된 폴더")
	private String saveFolder;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	
	
}
