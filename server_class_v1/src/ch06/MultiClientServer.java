package ch06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MultiClientServer {

	private static final int PORT = 5000;
	// 하나의 변수에 자원을 통으로 관리하기 위한 기법 --> 자료구조
	// 어떤 자료구조를 선택해야 할까? 멀티 쓰레드 환경 ==> 어떤 자료구조?
	// 객체 배열 <-- Vector<> : 멀티 쓰레드에 안정적이다.
	private static Vector<PrintWriter> clientWriters = new Vector<>();
	//private static Set<PrintWriter> clientWriters = ConcurrentHashMap.newKeySet(); // 스레드 안전한 클라이언트 출력 스트림 집합

	public static void main(String[] args) {
		System.out.println("Server started ...");

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {

			while (true) {
				// 1. serverSocket.accept(); 를 호출하면 블록킹 상태가 됨.(멈춰있음)
				// 2. 클라이언트가 연결 요청 하면 새로운 소켓 객체가 생성됨
				// 3. 새로운 스레드를 만들어 처리 ... (클라이언트가 데이터를 주고 받기 위한 스레드)
				// 4. 새로운 클라이언트가 접속 하기 까지 다시 대기 유지 (무한반복)
				Socket socket = serverSocket.accept();
				
				// 새로운 클라이언트가 연결 되면 새로운 스레드가 생성 된다.
				new ClientHandler(socket).start();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of main
	
	// 정적 내부 클래스 설계	
	private static class ClientHandler extends Thread {
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;

		public ClientHandler(Socket socket) {
			this.socket = socket;

		}

		// 스레드 start() 호출 시 동작되는 run 메서드
		@Override
		public void run() {

			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				// 중요! - 서버가 관리하는 자료 구조에 자원 저장(클라이언트와 연결된 소켓 -> outputStream)
				clientWriters.add(out);

				String msg;
				while ((msg = in.readLine()) != null) {
					System.out.println("Received : " + msg);
					broadcastMessage(msg);
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
					System.out.println("....... 클라이언트 연결 해제 .......");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}// end of ClientHandler
	
	// 모든 클라이언트 에게 메세지 보내기
	static void broadcastMessage(String msg) {
		
		for(PrintWriter writer : clientWriters) {
			writer.println(msg);
		}
		
	}
	
	
}
