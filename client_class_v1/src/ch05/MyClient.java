package ch05;

import java.io.IOException;
import java.net.Socket;

// 2-1단계 상속을 활용한 구현 클래스 설계하기
public class MyClient extends AbstractClient{


	@Override
	protected void setupClient() throws IOException {
		super.setSocket(new Socket("localhost", 5000));
		System.out.println("client connected");
	}

	public static void main(String[] args) {
		
		MyClient mc = new MyClient();
		mc.run();
		
		
	}

}
