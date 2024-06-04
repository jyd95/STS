package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 2단계 상속 활용 리팩토링 단계
public abstract class AbstractClient {

	private Socket socket;
	private PrintWriter socketWriter;
	private BufferedReader socketReader;
	private BufferedReader keyBoardReader;

	protected void setSocket(Socket socket) {
		this.socket = socket;
	}

	protected Socket getSocket() {
		return socket;
	}

	public final void run() {

		try {
			setupClient();
			setupStream();
			startService();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("클라이언트 쪽 cleanup() 호출 확인");
			cleanup();
		}

	}

	protected abstract void setupClient() throws IOException;


	private void setupStream() throws IOException {
		socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		socketWriter = new PrintWriter(socket.getOutputStream(), true);
		keyBoardReader = new BufferedReader(new InputStreamReader(System.in));
	}

	private void startService() {
		Thread readThread = createReadThread();
		Thread writeThread = createWriteThread();

		readThread.start();
		writeThread.start();
		
		try {
			readThread.join();
			writeThread.join();
			// main 스레드 잠깐 기다려 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Thread createReadThread() {
		return new Thread(() -> {

			try {
				String msg;
				while ((msg = socketReader.readLine()) != null) {
					System.out.println("서버 : " + msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	private Thread createWriteThread() {
		return new Thread(() -> {

			try {
				String msg;
				while ((msg = keyBoardReader.readLine()) != null) {
					socketWriter.println("클라이언트 : " + msg);
					socketWriter.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void cleanup() {
		try {
			if (socket != null) {
				socket.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
