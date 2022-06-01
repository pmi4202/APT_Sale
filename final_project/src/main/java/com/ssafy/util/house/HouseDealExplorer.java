package com.ssafy.util.house;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HouseDealExplorer implements AutoCloseable {

	private BufferedInputStream rd;
	private HttpURLConnection conn;

	public BufferedInputStream explorering(String dongcode) throws Exception {
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
																															 * URL
																															 */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=EDFpt5i3MicX3UPZvZI3%2FnQp%2FIdTrJ2CXcC3rdVwx9j90i%2FGoZI8A3PJQGR%2F%2B4AomyTOKOL7nA9O5z3OWkxCoQ%3D%3D"); /* Service Key */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10000", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(dongcode.substring(0, 5), "UTF-8")); /* 지역코드 */
		urlBuilder.append(
				"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode("201912", "UTF-8")); /* 계약월 */
//내 db에 있는게 201912인듯
		
		URL url = new URL(urlBuilder.toString());
		conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		System.out.println("Response code: " + conn.getResponseCode());

		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			
			rd = new BufferedInputStream(conn.getInputStream());
			
		} else {
			rd = new BufferedInputStream(conn.getErrorStream());
		}


		// 커넥션을 끊으면 버퍼도 끊기지?

		return rd;
	}

	@Override
	public void close(){

		try {
			if (rd != null) {
				rd.close();
			}

		} catch (IOException e) {

		}

		if (conn != null)
			conn.disconnect();

	}
}
