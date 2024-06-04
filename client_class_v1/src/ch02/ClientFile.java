package ch02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFile {

	public static void main(String[] args) {

		// 클라이언트 측 준비물
		// 1. 서버측 ip 주소와 포트 번호
		// 2. 서버측 소켓과 연결될 소켓
		Socket socket = null;
		try {
			socket = new Socket("localhost", 5001);

			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			// PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			// true ==> auto flush
			
			writer.println("안녕 반가워~"); // 줄바꿈 처리 하자.
			//print 사용 할 시 writer.flush(); 또는 뒤에 \n


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
