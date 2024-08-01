package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertExample {

	public static void main(String[] args) {
		
		
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";
		
		Connection connection = null;
		Statement statement = null; 
		ResultSet resultSet = null;
		
		// connection 객체를 얻어서 insert 구문을 작성 해보자.
		// mydb2 사용 , employee 테이블에 값을 넣는 코드를 작성하시오.
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "INSERT INTO employee VALUES(?, ?, ?, ?, now())";
			PreparedStatement pr = connection.prepareStatement(query);
			pr.setInt(1, 8);
			pr.setString(2, "롤랜드");
			pr.setString(3, "HOST");
			pr.setString(4, "10000000.00");
			
			int rowCount = pr.executeUpdate();
			System.out.println("rowCount : " + rowCount);
			
			System.out.println("------------------------------------------");
			
			resultSet = pr.executeQuery("SELECT * FROM employee"); // select 실행시 사용.
			
			while(resultSet.next()) {
				System.out.println("USER ID : " + resultSet.getInt("id"));
				System.out.println("USER NAME : " + resultSet.getString("name"));
				System.out.println("USER DEPARTMENT : " + resultSet.getString("department"));
				System.out.println("USER SALARY : " + resultSet.getLong("salary"));
				System.out.println("USER DATE : " + resultSet.getString("hire_date"));
				System.out.println("------------------------------------");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		
		
		
		
	}// end of main

}// end of class
