package com.tenco.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuizGame {

	// 준비물
	private static final String URL = "jdbc:mysql://localhost:3306/quizdb?serverTimezone=Asia/Seoul";
	private static final String USER = "root";
	private static final String PASSWORD = "asd123";
	
	
	public static void main(String[] args) {
		
		// JDBC 드라이버 로드 <== 인터페이스, <== 구현 클래스 필요
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Scanner scanner = new Scanner(System.in)) {
			
			while(true) {
				System.out.println();
				System.out.println("-----------------------------------------");
				System.out.println("1. 퀴즈 문제 추가");
				System.out.println("2. 퀴즈 문제 조회");
				System.out.println("3. 퀴즈 게임 시작");
				System.out.println("4. 종료");
				System.out.print("옵션을 선택하세요.");
				
				int choice = scanner.nextInt(); // 블로킹
				
				if(choice == 1) {
					// 퀴즈 문제 추가 --> 함수로 만들기
					addQuizQuestion(conn,scanner);
				} else if(choice == 2) {
					// 퀴즈 문제 조회 --> 함수로 만들기
					viewQuizQuestion(conn);
				} else if(choice == 3) {
					// 퀴즈 게임 시작 --> 함수로 만들기
					playQuizGame(conn,scanner);
				} else if(choice == 4) {
					System.out.println("프로그램을 종료합니다.");
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}// end of main


	private static void playQuizGame(Connection conn, Scanner scanner) {
		String sql = " select * from quiz order by rand() limit 1 ";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String question = rs.getString("question");
				String answer = rs.getString("answer");
				
				System.out.println("퀴즈 문제 : " + question);
				scanner.nextLine();
				System.out.print("정답을 제출하세요 : ");
				String userAnswer = scanner.nextLine();
				
				if(userAnswer.equalsIgnoreCase(answer)) {
					System.out.println("축하합니다. 정답을 맞추셨습니다.");
				} else {
					System.out.println("틀렸습니다.");
				}
			} else {
				System.out.println("아직 퀴즈 제작중입니다.");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private static void viewQuizQuestion(Connection conn) {
		String sql = " select * from quiz ";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				System.out.println("문제 ID : " + resultSet.getInt("id"));
				System.out.println("문제 : " + resultSet.getString("question"));
				System.out.println("정답 : " + resultSet.getString("answer"));
				if (!resultSet.isLast()) {
					System.out.println("-----------------------------------------");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	private static void addQuizQuestion(Connection conn,Scanner scanner){
		System.out.println("퀴즈를 입력하시오 : ");
		scanner.nextLine();
		String question = scanner.nextLine();
		System.out.println("정답을 입력하시오 : ");
		String answer = scanner.nextLine();
		
		String sql = " INSERT INTO quiz(question, answer) VALUES(?, ?) ";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, question);
			pstmt.setString(2, answer);
			int rowsInsertedCount = pstmt.executeUpdate();
			System.out.println(rowsInsertedCount + "개의 문제가 추가되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 퀴즈를 추가하는 함수 만들기
	// 사용자의 퀴즈와 답을 입력받아야 함
	// Connection 을 활용해서 query를 db에 날려야 한다
	
	
	
	
	
}// end of class
