package ch01;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		// 클라이언트 측 -- 소켓 통신을 하기 위해서 준비물
		// 1. 서버 측 컴퓨터에 주소 : 포트 번호.
		// 2. 서버측과 연결 될 기본 소켓.
		
		// 생성자 매개변수에 서버측 (IP주소, 포트번호)
		// 127.0.0.1 ==> 자기자신의 ip 주소 (루프백) 문자형식 = localhost
		
		try (Socket socket = new Socket("192.168.0.34",5000)){
			// new Socket("localhost",5000) -> 객체 생성시 서버측과 연결되어
			// 스트림을 활용할 수 있다.
			// 대상은 소켓이다. (중요)
			OutputStream output = socket.getOutputStream(); // 소켓에서 기반 스트림
			PrintWriter writer = new PrintWriter(output,true); // 기능 확장 - 보조 스트림 
			writer.println("보내기보내기보내기보내기보내기");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
