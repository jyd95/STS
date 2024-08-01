package studentManagement.ver1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class StudentManager_Ver_001 {

	private static final Logger LOGGER = Logger.getLogger(StudentManager_Ver_001.class.getName());

	public static void main(String[] args) {

		try (Connection conn = DBConnectionManager.getConnection(); Scanner scanner = new Scanner(System.in)) {

			while (true) {
				System.out.println();
				System.out.println("----------------------------------------");
				System.out.println("1. 학생 정보 조회하기");
				System.out.println("2. 학생 정보 추가하기");
				System.out.println("3. 학생 정보 수정하기");
				System.out.println("4. 학생 정보 삭제하기");
				System.out.println("5. 프로그램 종료");
				System.out.print("원하는 기능을 선택하시오 : ");

				int choice = scanner.nextInt(); // 블로킹

				StudentManagerFuntionImpl studentManagerFuntionImpl = new StudentManagerFuntionImpl();
				if (choice == 1) {
					studentManagerFuntionImpl.viewStudentInfo();
				} else if (choice == 2) {
					System.out.println("정보를 추가할 학생의 이름을 입력하세요");
					scanner.nextLine();
					String name = scanner.nextLine();
					System.out.println("정보를 추가할 학생의 나이를 입력하세요");
					int age = scanner.nextInt();
					scanner.nextLine();
					System.out.println("정보를 추가할 학생의 이메일을 입력하세요");
					String email = scanner.nextLine();
					studentManagerFuntionImpl.addStudentInfo(name, age, email);
				} else if (choice == 3) {
					System.out.println("정보를 변경할 학생의 id를 입력하시오");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("1. 이름 변경하기");
					System.out.println("2. 나이 변경하기");
					System.out.println("3. 이메일 변경하기");
					System.out.print("변경할 정보를 선택하세요.");
					int select = scanner.nextInt();
					if (select == 1) {
						System.out.println("변경할 이름을 입력하시오.");
						scanner.nextLine();
						String name = scanner.nextLine();
						studentManagerFuntionImpl.updateStudentName(id, name);
						System.out.println("이름이 변경되었습니다.");
					} else if (select == 2) {
						System.out.println("변경할 나이를 입력하시오.");
						scanner.nextLine();
						int age = scanner.nextInt();
						studentManagerFuntionImpl.updateStudentAge(id, age);
						System.out.println("나이가 변경되었습니다.");
					} else if (select == 3) {
						scanner.nextLine();
						System.out.println("변경할 이메일을 입력하시오.");
						String email = scanner.nextLine();
						studentManagerFuntionImpl.updateStudentEmail(id, email);
						System.out.println("이메일이 변경되었습니다.");
					} else {
						System.out.println("잘못된 입력입니다. 다시 시도해 주세요.");
					}
				} else if (choice == 4) {
					System.out.println("1. ID로 삭제하기");
					System.out.println("2. Email 로 삭제하기");
					System.out.print("삭제방법을 선택하시오.");
					int select = scanner.nextInt();
					if(select == 1) {
						System.out.println("삭제할 학생의 id를 입력하세요.");
						int id = scanner.nextInt();
						studentManagerFuntionImpl.deleteStudentId(id);
					}else if (select == 2) {
						System.out.println("삭제할 학생의 email을 입력하세요.");
						String email = scanner.nextLine();
						studentManagerFuntionImpl.deleteStudentEmail(email);
					}else {
						System.out.println("잘못된 입력입니다. 다시 시도해 주세요");
					}
				}else if(choice == 5) {
					System.out.println("프로그램이 종료됩니다.");
					break;
				}else {
					System.out.println("잘못된 입력입니다. 1 ~ 5 사이의 정수 값으로 다시 입력하세요.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
