package com.ssafy.final_project.model.mapper;

import java.sql.SQLException;
import java.util.Map;

public interface DongDataCheckMapper {

	//사용자가 요청한 동코드의 하우스 인포데이터가 있는지 검사한다.
	public int checkDongData(Map<String,String> paramMap) throws SQLException;
	public int  insertDongcode(Map<String,String> paramMap) throws SQLException;
}
