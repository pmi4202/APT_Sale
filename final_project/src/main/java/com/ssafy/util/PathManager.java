package com.ssafy.util;

import java.io.File;

//잘 만들어 보고 싶었는데 , .... 없느니만 못하다. 
// webapp . resource 밑에 upload 대신에 c밑에 저장하기 위함 
public class PathManager {
	static {
		TEST_UPLOAD_PATH = "c:" + File.pathSeparator + "spring_upload";
	}
	public final static String TEST_UPLOAD_PATH;

}
