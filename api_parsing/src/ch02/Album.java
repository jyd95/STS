package ch02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 
  "userId": 1,
  "id": 1,
  "title": "quidem molestiae enim"
**/
// DTO 클래스 - Data Transfer Object
// private 사용 시 Gson lib --> 변수에 접근해서 json 값을 넣어줌
// 즉 setter 필요.
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Album {
	private int userId;
	private int id;
	private String title;
}
