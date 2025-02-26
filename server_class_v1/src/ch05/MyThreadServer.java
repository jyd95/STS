package ch05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyThreadServer extends AbstractServer{

	@Override
	protected void setupServer() throws IOException {
		// 추상 클래스 --> 부모 -- 자식 (부모 기능의 확장 또는 사용 가능)
		// 서버측 소켓 통신 -- 준비물 : 서버소켓 , 포트번호
		super.setServerSocket(new ServerSocket(5000));
		System.out.println(">>> Server Started On Port 5000 <<<");
	}

	@Override
	protected void connection() throws IOException {
		// 서버 소켓.accept() 호출임
		super.setSocket(super.gerServerSocket().accept());
	}
	
	public static void main(String[] args) {
		MyThreadServer ms = new MyThreadServer();
		ms.run();
	}
	
}
