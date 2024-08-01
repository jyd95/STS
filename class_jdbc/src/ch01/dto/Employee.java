package ch01.dto;

import javax.annotation.processing.Generated;

import lombok.Getter;
import lombok.Setter;

// DTO 설계 하고 값을 담아서 .연산자를 사용해 보시오.

@Getter
@Setter

public class Employee {
	private int id;
	private String name;
	private String department;
	private double salary;
	private String hire_date;
}
