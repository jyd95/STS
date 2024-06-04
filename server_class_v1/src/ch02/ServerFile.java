package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

	public static void main(String[] args) {
		
		// 준비물 
		// 1. 서버 소켓 필요.
		// 2. 포트 번호 필요. (0~65535 까지 존재)
		// 2.1 잘 알려진 포트 번호 (0~1023 까지는 시스템 레벨에서 선점)
		// 2.2 등록 가능한 포트 번호 (1024 ~ 49151 까지)
		// 2.3 동적/사설 포트 번호 - 그외 나머지 번호 (임시 사용)
		
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(5001);
			System.out.println("서버를 시작합니다. - 포트번호 : 5001");
			
			Socket socket = serverSocket.accept(); // 연결될 때 까지 while
			System.out.println(">>> 클라이언트가 연결 되었습니다. <<<");
			
			// 데이터를 전달 받기 위해서 필요한 것은? --> 스트림
			InputStream input = socket.getInputStream();
			// 문자 기반 스트림을 사용
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			// 실제 데이터를 읽는 행위가 필요
			String message = reader.readLine();
			System.out.println("클라이언트가 보낸 메세지 : " + message);

			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
