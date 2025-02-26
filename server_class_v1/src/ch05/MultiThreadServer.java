package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

	// 메인 함수
	public static void main(String[] args) {

		System.out.println("======== 서버 실행 ========");

		// 서버측 소켓을 만들기 위한 준비물
		// 준비물 서버 소켓, 포트 번호

		try (ServerSocket serverSocket = new ServerSocket(5000);) {

			Socket socket = serverSocket.accept();
			// 클라이언트 연결 대기 --> 연결요청 --> 소켓 객체 생성(클라이언트와 연결 된 상태)
			System.out.println("------ 클라이언트 연결 됨 ------");

			// 클라이언트와 통신을 위한 스트림을 설정(대상 소켓을 얻었다)
			BufferedReader readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 클라이언트에게 보낼 스트림 설정
			PrintWriter writerStream = new PrintWriter(socket.getOutputStream(), true);

			// 키보드 스트림 준비
			BufferedReader keyBoardReader = new BufferedReader(new InputStreamReader(System.in));

			// 스레드를 시작
			startReadThread(readerStream); // <--- maon join() 대기 상태
			startWriteThread(writerStream, keyBoardReader);

			System.out.println("main 스레드 작업 완료.....");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end of main

	// 클라이언트로 부터 데이터를 읽는 스레드 분리
	private static void startReadThread(BufferedReader bufferedReader) {

		Thread readThread = new Thread(() -> {
			try {

				String clientMessage;
				while ((clientMessage = bufferedReader.readLine()) != null) {
					// 서버측 콘솔에 클라이언트가 보낸 메세지 출력
					System.out.println("클라이언트 : " + clientMessage);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		readThread.start();
		// join() --> readThread 가 종료 될 때까지 메인스레드를 대기..

	}

	// 서버측에서 클라이언트로 데이터를 보내는 기능
	private static void startWriteThread(PrintWriter printWriter, BufferedReader keyBoardReader) {

		Thread writeThread = new Thread(() -> {
			try {
				String serverMessage;
				while ((serverMessage = keyBoardReader.readLine()) != null) {
					printWriter.println(serverMessage);
					printWriter.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		writeThread.start();

		try {
			writeThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 워커 스레드가 종료될때까지 기다리는 메서드
	private static void waitForThreadToEnd(Thread thread) {
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// end of class
