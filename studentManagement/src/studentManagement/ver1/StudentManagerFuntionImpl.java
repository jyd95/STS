package studentManagement.ver1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class StudentManagerFuntionImpl implements StudentManagerFuntion {

	public static final String ADD_STUDENT = " INSERT INTO students(name, age, email) VALUES(?, ?, ?) ";
	public static final String VIEW_STUDENT = " SELECT * FROM students ";
	public static final String UPDATE_STUDENT_NAME = " UPDATE students set name = ? WHERE id = ? ";
	public static final String UPDATE_STUDENT_AGE = " UPDATE students set age = ? WHERE id = ? ";
	public static final String UPDATE_STUDENT_EMAIL = " UPDATE students set email = ? WHERE id = ? ";
	public static final String DELETE_STUDENT_PER_ID = " DELETE * FROM students WHERE id = ? ";
	public static final String DELETE_STUDENT_PER_EMAIL = " DELETE * FROM students WHERE email = ? ";

	@Override
	public int addStudentInfo(String name, int age, String email) throws SQLException {
		int resultRowCount = 0;

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(ADD_STUDENT);
			// 트랜젝션 처리 가능 -- 수동모드 커밋 사용 가능
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, email);
			resultRowCount = pstmt.executeUpdate();
			System.out.println("입력하신 학생의 정보가 추가되었습니다.");
		} catch (SQLException e) {
			System.out.println("잘못된 입력입니다. 다시 시도해 주세요.");
		}
		return resultRowCount;
	}

	@Override
	public List<StudentDTO> viewStudentInfo() throws SQLException {
		List<StudentDTO> list = new ArrayList<>();
		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(VIEW_STUDENT);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				System.out.println("학생 ID : " + rs.getInt("id"));
				System.out.println("학생 이름 : " + rs.getString("name"));
				System.out.println("학생 나이 : " + rs.getInt("age"));
				System.out.println("학생 email : " + rs.getString("email"));
				System.out.println("----------------------------------------");
				list.add(new StudentDTO(id, name, age, email));
			}
		}
		return list;
	}

	@Override
	public int updateStudentName(int id, String name) throws SQLException {
		int resultRowCount = 0;
		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_STUDENT_NAME);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			resultRowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("업데이트에 실패하였습니다. 다시 시도해주세요.");
		}
		return resultRowCount;
	}

	@Override
	public int updateStudentAge(int id, int age) throws SQLException {
		int resultRowCount = 0;
		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_STUDENT_AGE);
			pstmt.setInt(1, id);
			pstmt.setInt(2, age);
			resultRowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("업데이트에 실패하였습니다. 다시 시도해주세요.");
		}
		return resultRowCount;
	}

	@Override
	public int updateStudentEmail(int id, String email) throws SQLException {
		int resultRowCount = 0;
		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_STUDENT_EMAIL);
			pstmt.setInt(1, id);
			pstmt.setString(2, email);
			resultRowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("업데이트에 실패하였습니다. 다시 시도해주세요.");
		}
		return resultRowCount;
	}

	@Override
	public void deleteStudentId(int id) {
		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_STUDENT_PER_ID);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("번호가 " + id + "인 학생의 정보가 삭제되었습니다.");
		} catch (Exception e) {
			System.out.println("해당 id를 가진 학생은 존재하지 않습니다.");
		}
		return;
	}

	@Override
	public void deleteStudentEmail(String email) {
		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_STUDENT_PER_EMAIL);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			System.out.println("메일주소가 " + email + "인 학생의 정보가 삭제되었습니다.");
		} catch (Exception e) {
			System.out.println("해당 Email을 가진 학생은 존재하지 않습니다.");
		}
		return;
	}

}
