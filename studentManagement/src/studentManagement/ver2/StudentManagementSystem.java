package studentManagement.ver2;

import java.sql.SQLException;
import java.util.List;

import studentManagement.ver1.StudentDTO;

public class StudentManagementSystem {

	private static final StudentDAO studentDAO = new StudentDAO();

	public static void main(String[] args) {
		// 사용자에게 보여주는 콘솔 부분 디자인
		try {
			List<studentManagement.ver2.model.StudentDTO> list = studentDAO.getAllStudents();
			System.out.println(list.size());
			System.out.println(list.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// end of main

}// end of class
