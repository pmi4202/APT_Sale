package com.ssafy.util.dust;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DustExplorer implements AutoCloseable {

	private BufferedInputStream rd;
	private HttpURLConnection conn;

	final static String SERVICE_KEY = "EDFpt5i3MicX3UPZvZI3%2FnQp%2FIdTrJ2CXcC3rdVwx9j90i%2FGoZI8A3PJQGR%2F%2B4AomyTOKOL7nA9O5z3OWkxCoQ%3D%3D";

	@Override
	public void close() throws Exception {
		try {
			if (rd != null) {
				rd.close();
			}

		} catch (Exception e) {

		}
		try {
			if (conn != null) {
				conn.disconnect();
			}
		} catch (Exception e) {
		}

	}

	public InputStream getExploringBuffer(String siName) throws Exception {

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /* URL */

		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + SERVICE_KEY); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
				+ URLEncoder.encode("xml", "UTF-8")); /* xml 또는 json */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode("1", "UTF-8")); /* 페이지번호(조회 날짜로 검색 시 사용 안함) */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
		urlBuilder.append(
				"&" + URLEncoder.encode("sidoName", "UTF-8") + "=" + URLEncoder.encode(siName, "UTF-8")); /* 검색 시도 */
		urlBuilder
				.append("&" + URLEncoder.encode("ver", "UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); /* 검색 시도 */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());

		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedInputStream(conn.getInputStream());
			/*
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String temp  = null;
			while((temp = br.readLine())!= null) {
				System.out.println(temp);
			}*/
		} else {
			rd = new BufferedInputStream(conn.getErrorStream());
		}
		return rd;

	}
	

}
