package ch01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpReceiveJson {

	public static void main(String[] args) {
		String urlString = "https://jsonplaceholder.typicode.com/todos";
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Gson gson2 = new Gson();
		try {
			URL url = new URL(urlString);
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader brIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			StringBuffer responseBuffer = new StringBuffer();
			
			while( (inputLine = brIn.readLine()) != null ) {
				responseBuffer.append (inputLine);
			}
			Todos[] StringArr =  gson2.fromJson(gson2.toJson(responseBuffer.toString()), Todos[].class);
			
			System.out.println(StringArr[2].getTitle());
			brIn.close();
			
//			String gsread = gson.toJson(responseBuffer);
//			System.out.println(gsread);
			
//			Todos todos1 = gson2.fromJson(gsread, Todos.class);
//			System.out.println(todos1.getTitle());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
