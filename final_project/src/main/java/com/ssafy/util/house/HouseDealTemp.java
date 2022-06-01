package com.ssafy.util.house;

import com.ssafy.final_project.model.dto.HouseDealDto;

/**
 * @author start
 *
 */
public class HouseDealTemp extends HouseDealDto{
	HouseDealTemp(){}//다른 패키지 사ㅛㅇ 금지

	@Override
	public String toString() {
		return "HouseDealTemp [getNo()=" + getNo() + ", getAptCode()=" + getAptCode() + ", getDealAmount()="
				+ getDealAmount() + ", getDealYear()=" + getDealYear() + ", getDealMonth()=" + getDealMonth()
				+ ", getDealDay()=" + getDealDay() + ", getArea()=" + getArea() + ", getFloor()=" + getFloor()
				+ ", getType()=" + getType() + ", getRentMoney()=" + getRentMoney() + "]";
	}

	
	
}
