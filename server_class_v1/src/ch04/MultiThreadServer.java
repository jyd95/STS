package ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

	// 메인 함수
	public static void main(String[] args) {

		System.out.println("=== 서버 실행 ===");

		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(5001);
			socket = serverSocket.accept();
			System.out.println("포트 번호 - 5001 할당 완료");

			// 1. 클라이언트로부터 데이터를 받을 입력 스트림 필요
			// 2. 클라이언트에 데이터를 보낼 출력 스트림 필요
			// 3. 키보드 입력을 받기 위한 입력 스트림 필요 (서버내)
			BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader keyBoardReader = new BufferedReader(new InputStreamReader(System.in));

			// 멀티 스레딩 개념의 확장
			// 클라이언트로 부터 데이터를 읽는 스레드 생성

			Thread readThread = new Thread(() -> {
				try {
					String clientMessage;
					while ((clientMessage = socketReader.readLine()) != null) {
						System.out.println("서버 : " + clientMessage);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			// 클라이언트에게 데이터를 보내는 쓰레드 생성

			Thread writeThread = new Thread(() -> {
				try {
					String serverMessage;
					while ((serverMessage = keyBoardReader.readLine()) != null) {
						// 1. 먼저 키보드를 통해 데이터를 읽고
						// 2. 출력 스트림을 활용해서 데이터를 보내야 한다.
						socketWriter.println(serverMessage);
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			});

			// 스레드 동작 --> start() 호출

			readThread.start();
			writeThread.start();

			// Thread.join 메서드는 하나의 스레드가 종료 될 때 까지 기다리도록 하는
			// 기능을 제공한다.
			readThread.join();
			writeThread.join();

			System.out.println("--- 서버 프로그램 종료 ---");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}// end of main

}// end of class
