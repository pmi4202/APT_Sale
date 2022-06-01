package com.ssafy.util.dust;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ssafy.final_project.model.dto.DustDto;

public class DustUtilImpl implements DustUtil{
	//Map<Map<String,Date>,List<DustDto>> forcache; 
	//클래스에 단 하나.
	//private static Map<String,List<DustDto>> cacheForDust;
	
	public List<DustDto> getDustList(String siName) throws Exception {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();

		DustSaxHandler sax = new DustSaxHandler();
		
		try (DustExplorer dustExpoler = new DustExplorer()) {

			// 요청 여러개 올텐데.. 포트 많이 열어야하니깐 이게 맞지 . 싱글턴이 아니라. 그리고 멤버 변수 있음
			SAXParser parser = factory.newSAXParser();
			parser.parse(dustExpoler.getExploringBuffer(siName), sax);

		} catch (Exception e) {
			throw new Exception("공공 API 탐색 중 에러");
		}
		
		
		
		return sax.getDustList();
	}
}
