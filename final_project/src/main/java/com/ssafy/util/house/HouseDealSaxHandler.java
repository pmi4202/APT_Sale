package com.ssafy.util.house;

import java.util.HashMap;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class HouseDealSaxHandler extends DefaultHandler {

	HashMap<HouseInfoTemp, LinkedList<HouseDealTemp>> map;
	
	HouseDealTemp houseDeal;
	HouseInfoTemp houseInfo;
	
	private String temp;

	public HouseDealSaxHandler() {
		map = new HashMap<HouseInfoTemp, LinkedList<HouseDealTemp>>();
	}

	public void startElement(String uri, String localName, String qName, Attributes att) {
		if (qName.equals("item")) { //item이라는 태그 시작하면.
			houseInfo = new HouseInfoTemp();
			houseDeal = new HouseDealTemp();
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if("거래금액".equals(qName)) {
			//????
			houseDeal.setDealAmount(temp.trim().replace(",", ""));
		}else if("건축년도".equals(qName)) {
			houseInfo.setBuildYear(Integer.parseInt(temp));
		}else if("년".equals(qName)) {
			houseDeal.setDealYear(Integer.parseInt(temp));
		}else if("월".equals(qName)) {
			houseDeal.setDealMonth(Integer.parseInt(temp));
		}else if("일".equals(qName)) {
			houseDeal.setDealDay(Integer.parseInt(temp));
		}else if("법정동".equals(qName)) {
			houseInfo.setDongName(temp.trim());
		}else if("법정동시군구코드".equals(qName)) {
			houseInfo.setDongCode(temp.trim());
		}else if("법정동읍면코드".equals(qName)) {
			houseInfo.setDongCode(houseInfo.getDongCode().concat(temp.trim()));
		}else if("아파트".equals(qName)) {
			houseInfo.setAptName(temp.trim());
		}else if("전용면적".equals(qName)) {
			houseDeal.setArea(temp.trim());
		}else if("지번".equals(qName)) {
			houseInfo.setJibun(temp.trim());
		}else if("층".equals(qName)) {
			houseDeal.setFloor(temp.trim());
		}else if("item".equals(qName)) {
			
			System.out.println(houseInfo.hashCode());
			
			if(!map.containsKey(houseInfo)) {
				LinkedList<HouseDealTemp> ll = new LinkedList<HouseDealTemp>();
				ll.add(houseDeal);
				map.put(houseInfo, ll);
			}
			
			map.get(houseInfo).add(houseDeal); //주소 갖고 왔으니깐 되겠지
		}
	}

	public void characters(char[] ch, int start, int length) {
		//태그 엘리먼트 값이 너무 길거나하면 끝나기 전에 끊길 수 있는데 , 여긴 괜ㅊ낳은 듯?
		temp = new String(ch, start, length).trim();
	}

	public HashMap<HouseInfoTemp, LinkedList<HouseDealTemp>> getMap() {
		return map;
	}

	


}
