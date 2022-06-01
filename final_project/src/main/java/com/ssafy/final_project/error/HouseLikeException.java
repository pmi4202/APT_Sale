package com.ssafy.final_project.error;

public class HouseLikeException extends Exception {

	public HouseLikeException(String msg,String aptCode,String userId) {
		super(String.format("%s 아파트 코드 : %s , 유저 아이디,%s", msg,aptCode,userId));
	}
}
