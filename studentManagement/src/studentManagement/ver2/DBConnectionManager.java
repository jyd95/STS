package studentManagement.ver2;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Data;

@Data
// 싱글톤 패턴 - 단 하나의 객체만 필요함을 보장해야 한다면
// 싱글톤 패턴으로 설계할 수 있다.
public class DBConnectionManager {
	
	
	// 자기 자신의 참조 주소값을 담을 변수 생성, 단 private
	public static DBConnectionManager instance;
	private HikariDataSource dataSource;
		
	// 생성자 <-- 외부에서 호출할 수 없게 막아야 한다.
	private DBConnectionManager() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/studentdb?serverTimezone=Asia/Seoul");
		config.setUsername("root");
		config.setPassword("asd123");
		config.setMaximumPoolSize(10);
		dataSource = new HikariDataSource(config);
	}
	
	// 외부에서 클래스이름.getxxx 메서드를 만들어 주면 된다.
	// 한번에 하나의 스레드만 접근하도록 동기화 처리.
	public static synchronized DBConnectionManager getInstance() {
		if(instance == null) {
			instance = new DBConnectionManager();
		}
		return instance;
	}
	
	// Connection 객체를 반환(구현체 - HikariCP 이다)
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
}
