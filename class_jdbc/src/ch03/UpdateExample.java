package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateExample {

	public static void main(String[] args) {
		
		
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";
		
		Connection connection = null;
		Statement statement = null; 
		ResultSet resultSet = null;
		
		// connection 객체를 얻어서 update 구문을 작성 해보자.
		// mydb2 사용 , employee 테이블에 값을 넣는 코드를 작성하시오.
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "UPDATE employee set name = ? where name = ? ";
			PreparedStatement pr = connection.prepareStatement(query);
			pr.setString(1, "마루쉐");
			pr.setString(2, "한수연");
			
			int rowCount = pr.executeUpdate();
			System.out.println("rowCount : " + rowCount);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		
		
		
		
	}// end of main

}// end of class
