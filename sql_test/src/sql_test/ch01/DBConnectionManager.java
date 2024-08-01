package sql_test.ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
	
	private static final String URL = "jdbc:mysql://localhost:3306/demo3?serverTimezone=Asia/Seoul";
	private static final String USER = "root";
	private static final String PASSWORD = "asd123";
	
	// static{} 블록 or 정적 초기화 블록
	// 클래스가 처음 로드될 때 한번 실행됨.
	// 스태틱 변수의 초기화나 복잡한 초기화 작업을 수행할때 사용
	// 예외를 던질수도 있다.
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 정적 메서드 - 커넥션 객체를 리턴하는 함수를 만들어 보자.
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	
}
