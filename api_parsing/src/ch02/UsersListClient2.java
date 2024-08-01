package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
/*
 * json array 형태를 파싱해보자.
 */


public class UsersListClient2 {

	public static void main(String[] args) {
		
		// 순수 자바코드에서 http 통신
		// 1. 서버 주소 경로
		// 2. URL 클래스
		// 3. usr.openconnection() <-- 스트림 I/O 
		
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/users/1");
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
			
			// 하나의 json object 형태 파싱
			User userDTO = gson.fromJson(result.toString(), User.class);
			//System.out.println(userDTO.getId());
			//System.out.println(userDTO.getName());
			System.out.println(userDTO);
			//System.out.println(userDTO.getCompany());
			
			
			
			// array 타입의 json 형태 파싱 
			// [{...},{...},{...}]
			// Gson에서 제공하는 Type 이라는 데이터 타입을 활용할 수 있다.
			// Json 배열 형태를 쉽게 파싱하는 방법 --> TypeToken 안의 List<T>를 활용.
			//Type albumType = new TypeToken<List<Album>>(){}.getType();
			
			//List<Album> albumList = gson.fromJson(result.toString(), albumType);
			
//			System.out.println(albumList.size());
//			for(Album a : albumList) {
//				System.out.println(a.toString());
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
	}// end of main

}
