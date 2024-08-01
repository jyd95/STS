package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIExplorer2 {

	public static void main(String[] args) throws IOException {

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/6260000/AttractionService/getAttractionKr");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=fhnbXWgJDkhB2MiOjGpufD8eY3V089endSxPTNIxQspiUftaOq%2BmWkzSoXCSydYR3v6VtkAHAtmv8CVbPa%2Bvzw%3D%3D");
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("UC_SEQ", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
	}// end of main

}// end of class
