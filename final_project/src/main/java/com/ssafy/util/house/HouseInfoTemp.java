package com.ssafy.util.house;

import com.ssafy.final_project.model.dto.HouseInfoDto;

public class HouseInfoTemp extends HouseInfoDto{

	HouseInfoTemp(){} //다른 패키지 사용 금지
	
	private int hash ;
	private boolean hashEnable = false;
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null || !(obj instanceof HouseInfoTemp) ) { //널이거나 , 파라메터가 같은 객체가 아니면 .. 다운 캐스팅 몰라...
			return false;
		}
		
		HouseInfoDto objt = (HouseInfoDto)obj ;
		
		if(objt.getAptName().equals(this.getAptName())) {
			if(objt.getDongCode().equals(this.getDongCode())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
	
		if(!hashEnable) {
			hash= 17;
			hash = 31 * hash + getDongCode().hashCode();
			hash = hash * 31 + getAptName().hashCode();
			hashEnable = true;
		}
		
		return hash; 
	}

	@Override
	public String toString() {
		return "HouseInfoTemp [getAptCode()=" + getAptCode() + ", getAptName()=" + getAptName() + ", getDongCode()="
				+ getDongCode() + ", toString()=" + super.toString() + ", toJson()=" + toJson() + ", getDongName()="
				+ getDongName() + ", getBuildYear()=" + getBuildYear() + ", getJibun()=" + getJibun() + ", getLat()="
				+ getLat() + ", getLng()=" + getLng() + ", getImg()=" + getImg() + ", getClass()=" + getClass() + "]";
	}
	
	
}