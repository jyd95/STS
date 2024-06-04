package ch04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThreadClient {

	public static void main(String[] args) {

		System.out.println("### 클라이언트 실행 ###");

		try {

			Socket socket = new Socket("localhost", 5000);
			System.out.println("*** connected to server ***");

			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyBoardReader = new BufferedReader(new InputStreamReader(System.in));

			// 서버로부터 데이터를 읽을 스레드 생성
			Thread readThread = new Thread(() -> {

				try {
					String serverMessage;
					while ((serverMessage = socketReader.readLine()) != null) {
						System.out.println("서버 : " + serverMessage);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			});

			// 서버에게 데이터를 보내는 스레드 생성
			Thread writeThread = new Thread(() -> {
				try {

					String clientMessage;
					while ((clientMessage = keyBoardReader.readLine()) != null) {
						// 1. 키보드에서 데이터를 응용프로그램 안으로 입력 받아서
						// 2. 서버측 소켓과 연결 되어있는 출력 스트림을 통해 데이터를 보낸다.
						socketWriter.println(clientMessage);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			});

			readThread.start();
			writeThread.start();

			readThread.join();
			writeThread.join();

			System.out.println("클라이언트 측 프로그램 종료");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end of main

}// end of class
