package ch01;

import lombok.Data;

@Data
public class Todos {
	
	private String userId;
	private int id;
	private String title;
	private boolean completed;
	
}
