package com.ssafy.util.dust;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.final_project.model.dto.DustDto;

public class DustSaxHandler extends DefaultHandler {

	private List<DustDto> dustList;
	private DustDto dustInfo;
	private String temp;

	public DustSaxHandler() {
		dustList = new ArrayList<DustDto>();
	}

	// 태그 시작
	public void startElement(String uri, String localName, String qName, Attributes att) {

		if (qName.equals("item")) { // item이라는 태그 시작하면.
			this.dustInfo = new DustDto();
		}
	}

	// 태그 끝
	public void endElement(String uri, String localName, String qName) {
		if ("dataTime".equals(qName)) {
			dustInfo.setDataTime(temp);
		} else if ("khaiValue".equals(qName)) {
			dustInfo.setKhaiValue(temp);
		} else if ("khaiGrade".equals(qName)) {
			dustInfo.setKhaiGrade(temp);
		} else if ("so2Grade".equals(qName)) {
			dustInfo.setSo2Grade(temp);
		} else if ("coGrade".equals(qName)) {
			dustInfo.setCoGrade(temp);
		} else if ("o3Grade".equals(qName)) {
			dustInfo.setO3Grade(temp);
		} else if ("no2Grade".equals(qName)) {
			dustInfo.setNo2Grade(temp);
		} else if ("pm10Grade".equals(qName)) {
			dustInfo.setPm10Grade(temp);
		}else if("stationName".equals(qName)) {
			dustInfo.setStationName(temp);
		} else if ("item".equals(qName)) {
			dustList.add(dustInfo);
			dustInfo = null;
		}
	}

	// 태그 안
	public void characters(char[] ch, int start, int length) {
		// 태그 엘리먼트 값이 너무 길거나하면 끝나기 전에 끊길 수 있는데 , 여긴 괜ㅊ낳은 듯?
		temp = new String(ch, start, length).trim();
	}

	public List<DustDto> getDustList() {
		return this.dustList;
	}
}
