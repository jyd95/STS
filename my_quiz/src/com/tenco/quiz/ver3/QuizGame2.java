package com.tenco.quiz.ver3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 테스트 용도
public class QuizGame2 {
	
	
	public static void main(String[] args) {


		try (Connection conn = com.tenco.quiz.ver3.DBConnectionManager.getConnection();
				Scanner scanner = new Scanner(System.in)) {
			
			while(true) {
				System.out.println();
				System.out.println("----------------------------------------");
				System.out.println("1. 퀴즈 문제 추가");
				System.out.println("2. 퀴즈 문제 조회");
				System.out.println("3. 퀴즈 게임 시작");
				System.out.println("4. 종료");
				System.out.print("옵션을 선택 하세요: ");
				
				int choice = scanner.nextInt(); // 블로킹 
				
				if(choice == 1) {
					addQuizQuestion(conn, scanner);
				} else if(choice == 2) {
					viewQuizQuestion(conn);
				} else if(choice == 3) {
					playQuizGame(conn, scanner);
				} else if(choice == 4) {
					System.out.println("프로그램을 종료 합니다");
					break;
				} else  {
					System.out.println("잘못된 선택입니다. 다시 시도하세요.");
				}
			}
			
		} catch (Exception e) {
			
		}

	} // end of main

	private static void playQuizGame(Connection conn, Scanner scanner) {
		String sql = " select * from quiz order by rand() limit 1  ";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String question = rs.getString("question");
				String answer = rs.getString("answer");
				
				System.out.println("퀴즈 문제 : " + question);
				// 버그처리 
				scanner.nextLine();
				System.out.print("당신에 답: ");
				String userAnswer = scanner.nextLine();
				
				if(userAnswer.equalsIgnoreCase(answer)) {
					System.out.println("정답입니다! 점수를 얻었습니다.");
				} else {
					System.out.println("오답입니다!");
					System.out.println("퀴즈 정답은 -->  " + answer);
				}
			} else {
				System.out.println("죄송합니다 아직 퀴즈 문제를 만들고 있습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void viewQuizQuestion(Connection conn) {
		String sql = " select * from quiz  ";
	 	try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	 		ResultSet resultSet =  pstmt.executeQuery();
	 		while(resultSet.next()) {
	 			System.out.println("문제 ID : " + resultSet.getInt("id"));
	 			System.out.println("문제 : " + resultSet.getString("question"));
	 			System.out.println("정답 : " + resultSet.getString("answer"));
	 			if(!resultSet.isLast()) {
	 				System.out.println("----------------------------------------");
	 			}
	 		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void addQuizQuestion(Connection conn, Scanner scanner) {
		
		System.out.println("퀴즈 문제를 입력하세요: ");
		scanner.nextLine();
		String question = scanner.nextLine();
		System.out.println("퀴즈 정답을 입력하세요: ");
		String answer = scanner.nextLine();
		
		String sql = " insert into quiz(question, answer) values(?, ?) ";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, question);
			pstmt.setString(2, answer);
			int rowsInsertedCount = pstmt.executeUpdate();
			System.out.println("추가된 행의 수 : " + rowsInsertedCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}  // end of class
