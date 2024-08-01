package ch01;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExample {

	public static void main(String[] args) {

		//
		Student student1 = new Student("고길동", 40, "애완학과");
		Student student2 = new Student("둘리", 5, "문제학과");

		//
		Student[] studentArr = { student1, student2 };

		// --> 특정 형식 (구조화) 으로 되어 있는 문자열(JSON)로 변환 하고 싶다면?

		// Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// 객체를 JSON 형식의 문자열로 변환하는 메서드 -->toJson();
		String student1Str = gson.toJson(student1);
		// System.out.println(student1Str);

		// setPrettyPrinting() --> 프리뷰, 가시성 좋게 출력
		Gson gson2 = new Gson();
		String student2Str = gson2.toJson(student2);
		// System.out.println(student2Str);

		// 객체 -> 문자열 형태로 변환 기능 습득
		// 문자열 --> 객체 형태 변환은 어떻게 해야할까?
		Student studentObject = gson.fromJson(student1Str, Student.class);
		// System.out.println(studentObject.getName());

		Dog dog1 = new Dog("흰둥이", 4, "진돗개");
		Dog dog2 = new Dog("김선식", 29, "혈중알코올농도0.2");

		Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
		String myDog = gson3.toJson(dog1);
		System.out.println(myDog);
		String idiot = gson3.toJson(dog2);
		System.out.println(idiot);

		Dog countryDog = gson.fromJson(myDog, Dog.class);
		System.out.println(countryDog.getBreed());
		
		

	}

}
