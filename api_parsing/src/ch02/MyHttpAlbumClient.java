package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyHttpAlbumClient {

	public static void main(String[] args) {
		
		// 순수 자바코드에서 http 통신
		// 1. 서버 주소 경로
		// 2. URL 클래스
		// 3. usr.openconnection() <-- 스트림 I/O 
		
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/albums/1");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
		
			// 응답 코드 확인
			int responsCode = conn.getResponseCode();
			System.out.println("response code : " + responsCode);
			
			// HTTP 응답 메세지에 데이터를 추출 [] -- stream -- []
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));   
			String inputLine;
			StringBuffer result = new StringBuffer();
			while( (inputLine = in.readLine()) != null ) {
				result.append(inputLine);
			}
			in.close();
			
			System.out.println(result.toString());
			System.out.println("-------------------------**----------------------");
			// gson lib 활용
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Album albumDTO = gson.fromJson(result.toString(), Album.class);
			System.out.println(albumDTO.getId());
			System.out.println(albumDTO.getUserId());
			System.out.println(albumDTO.getTitle());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
	}// end of main

}
