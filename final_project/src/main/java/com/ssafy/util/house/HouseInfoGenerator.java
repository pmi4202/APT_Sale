package com.ssafy.util.house;

import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Component;

//공공 api에서 뜯어온다.
@Component
public class HouseInfoGenerator {

	public HashMap<HouseInfoTemp, LinkedList<HouseDealTemp>> getFromPublicApi(String dongCode) throws Exception {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		HouseDealSaxHandler sax = new HouseDealSaxHandler();

		try (HouseDealExplorer hde = new HouseDealExplorer()) {

			// 요청 여러개 올텐데.. 포트 많이 열어야하니깐 이게 맞지 . 싱글턴이 아니라. 그리고 멤버 변수 있음
			SAXParser parser = factory.newSAXParser();
			parser.parse(hde.explorering(dongCode), sax);

		} //catch (Exception e) {
		//	throw new Exception("공공 API 탐색 중 에러");
		//}
		

		return sax.getMap();
	}

//	public static void main(String[] args) throws Exception {
//		HashMap<HouseInfoTemp, LinkedList<HouseDealTemp>>  map = getFromPublicApi("11110");
//		
//		int count = 0;
//		for(HouseInfoTemp hit : map.keySet()) {
//			System.out.println(hit);
//			System.out.println("의 딜 정보 :--");
//			
//			LinkedList<HouseDealTemp> list = map.get(hit);
//			
//			for(HouseDealTemp iii : list) {
//				System.out.println(iii);
//				count++;
//			}
//			
//			System.out.println("딜 총계 : "+count);
//		}
//		
//	}
}
