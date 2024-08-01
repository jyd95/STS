package studentManagement.ver1;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/*
 *  커넥션 풀을 활용하는 예제로 수정해 보자.
 *  HikariCP-5.1.0.jar - lib 설정  
 */
public class DBConnectionManager {
	
	private static HikariDataSource dataSource;
	
	
	private static final String URL = "jdbc:mysql://localhost:3306/studentdb?serverTimezone=Asia/Seoul";
	private static final String USER = "root";
	private static final String PASSWORD = "asd123";
	
	// static{} 블록 or 정적 초기화 블록
	// 클래스가 처음 로드될 때 한번 실행됨.
	// 스태틱 변수의 초기화나 복잡한 초기화 작업을 수행할때 사용
	// 예외를 던질수도 있다.
	static {
		// HikariCP 를 사용하기 위한 설정이 필요하다.
		// HikariConfig --> 해당 클래스를 활용하여 상세 설정이 가능.
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		config.setMaximumPoolSize(10); // 최대 연결 수 10 설정.
		
		dataSource = new HikariDataSource(config);
		
		
	}
	
	public static Connection getConnection() throws SQLException{
		System.out.println("HikariCP 를 사용한 Data Source 활용");
		return dataSource.getConnection();
	}
	
	// 테스트 코드 확인
	public static void main(String[] args) {
		
		try {
			Connection conn = DBConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}// end of main
	
	// 정적 메서드 - 커넥션 객체를 리턴하는 함수를 만들어 보자.
//  기본 JDBC 드라이버 사용 버전
//	public static Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(URL, USER, PASSWORD);
//	}
	
	
}
