package studentManagement.ver1;

import java.sql.SQLException;
import java.util.List;

public interface StudentManagerFuntion {
	int addStudentInfo(String name, int age, String email) throws SQLException;

	List<StudentDTO> viewStudentInfo() throws SQLException;
	
	int updateStudentName(int id, String name) throws SQLException;
	int updateStudentAge(int id, int age) throws SQLException;
	int updateStudentEmail(int id, String name) throws SQLException;
	
	void deleteStudentId(int id);
	void deleteStudentEmail(String email);
}

// viewStudentInfo
// addStudentInfo
// updateStudentInfo
// deleteStudentInfo