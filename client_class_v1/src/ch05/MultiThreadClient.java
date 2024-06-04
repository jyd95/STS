package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

// 1단계 - 함수로 분리해서 리팩토링
public class MultiThreadClient {

	public static void main(String[] args) {

		System.out.println("===== 클라이언트 실행 =====");
		try (Socket socket = new Socket("192.168.0.48", 5000)) {
			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyBoardReader = new BufferedReader(new InputStreamReader(System.in));

			startReadThread(socketReader);
			startWriteThread(socketWriter, keyBoardReader);
			// 메인스레드 기다리게 하는 코드가 어딨지? 가독성이 떨어짐
			// startWriteThread 안에 있음.
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end of main

	// 1. 클라이언트로부터 데이터를 읽는 스레드 시작 스레드 생성
	private static void startReadThread(BufferedReader bufferedReader) {
		Thread readThread = new Thread(() -> {
			try {
				String svmsg;
				while ((svmsg = bufferedReader.readLine()) != null) {
					System.out.println("서버 : " + svmsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		readThread.start();

	}

	// 2. 키보드에서 입력을 받아 클라이언트 측으로 데이터를 전송하는 스레드
	private static void startWriteThread(PrintWriter printWriter, BufferedReader keyBoardReader) {
		Thread writeThread = new Thread(() -> {
			try {
				String clmsg;
				while ((clmsg = keyBoardReader.readLine()) != null) {
					printWriter.println(clmsg);
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

	private static void waitThreadEnd(Thread thread) {
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// end of class
