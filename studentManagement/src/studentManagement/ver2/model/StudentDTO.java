package studentManagement.ver2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// DTO의 기능은 단지 데이터를 담는 역할만 있는것이 아니다.
// 기능 확장이 가능하다.

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder

public class StudentDTO {
	
	private int id;
	private String name;
	private int age;
	private String email;
	
	
}
