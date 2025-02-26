package ch01;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

public class SimpleHttpServer {

	public static void main(String[] args) {
		// 8080 <- https / 80 <-- http (80은 기본포트라 포트번호 생략 가능)
		try {
			// 포트번호 8080으로 http 서버 생성
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
			
			// 서버에 대한 설정
			
			// 프로토콜 정의 (경로, 핸들러 처리)
			// 핸들러 처리를 내부 정적 클래스로 사용
			httpServer.createContext("/test", new MyTestHandler()); 
			httpServer.createContext("/hello", new HelloHandler()); 
			
			// 서버 시작
			httpServer.start();
			System.out.println(">> My Http Server started on port 8080 <<");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// end of main
	
	// http://localhost:8080/test  <- 주소 설계
	static class MyTestHandler implements HttpHandler{

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			
			//사용자의 요청 방식 (method) , get인지 post인지 알아야 동작 시킬 수 있다.
			String method = exchange.getRequestMethod();
			System.out.println("method : " + method);
			
			if("GET".equalsIgnoreCase(method)) {
				// GET 요청시 여기 동작
				//System.out.println("여기는 GET 방식으로 호출됨");
				
				//GET -> path : /test 라고 들어오면 어떤 응답거리를 내려 주면 된다.
				handleGetRequest(exchange);
				
				
			}else if("POST".equalsIgnoreCase(method)) {
				// POST 요청시 여기 동작
				//System.out.println("여기는 POST 방식으로 호출됨");
				handlePostRequest(exchange);
				
			}else {
				// 지원하지 않는 메서드에 대한 응답
				String response = "Unsupported Method : " + method;
				exchange.sendResponseHeaders(405, response.length()); // Method Not Allowed
				OutputStream os = exchange.getResponseBody();
				os.write(response.getBytes());
				os.flush();
				os.close();
			}
		
			
		}
		
		// GET 요청시 동작 만들기
		// GET 방식은 HTTP 메세지에 바디 영역이 없다. 
		private void handleGetRequest(HttpExchange exchange) throws IOException{
			String response = """ 
					<!DOCTYPE html>
					<html lang="ko">
						<head></head>
						<body>
							<h1 style="background-color:red"> 안녕 Path by Test </h1>
						</body>
					</html>
				""";
			// String response = "Hello Get~~"; // 응답 메세지
			
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			OutputStreamWriter ous = new OutputStreamWriter(os);
			BufferedWriter bus = new BufferedWriter(ous);
			bus.write(response); // 응답 본문 전송
			bus.close();
			
		}
		
		// post 요청시 동작 만들기
		private void handlePostRequest(HttpExchange exchange) throws IOException{
			// POST 요청은 HTTP 메세지에 바디 영역이 존재 한다.
			String response = """ 
						<!DOCTYPE html>
						<html lang="ko">
							<head></head>
							<body>
								<h1 style="background-color:red"> Hello Path by Test </h1>
							</body>
						</html>
					""";
			
			// HTTP 응답 메세지 헤더 설정
			exchange.setAttribute("Content-Type", "text.html; charset=UTF-8");
			exchange.sendResponseHeaders(200, response.length());
			// getResponseBody
			OutputStream os = exchange.getResponseBody();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(response);
			osw.close();
		}
		
		
	}// end of MyTestHandler
	
	
	static class HelloHandler implements HttpHandler{

		@Override
		public void handle(HttpExchange exchange) throws IOException {
		
			String method = exchange.getRequestMethod();
			System.out.println("hello method : " + method);
			
		}
		
	}// end of HelloHandler
	
}// end of class

