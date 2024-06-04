package ch01;

public class MainTest1 {

	public static void main(String[] args) {
		
		ThreeDPrinter dPrinter1 = new ThreeDPrinter();
		dPrinter1.setMaterial(new Plastic());
		System.out.println(dPrinter1.material.toString());
		
		System.out.println("-------------------------");
		
		ThreeDPrinter2 dPrinter2 = new ThreeDPrinter2();
		dPrinter2.setMaterial(new Powder());
		System.out.println(dPrinter2.material.toString());
		
		System.out.println("-------------------------");
		
		ThreeDPrinter3 dPrinter3 = new ThreeDPrinter3();
		dPrinter3.setMaterial(new Plastic());
		System.out.println(dPrinter3.material.toString());
		
		System.out.println("-------------------------");
		
		ThreeDPrinter3 dPrinter3_2 = new ThreeDPrinter3();
		dPrinter3_2.setMaterial(new Powder());
		System.out.println(dPrinter3_2.material.toString());
		
		Plastic plastic01 = (Plastic)dPrinter3.getMaterial(); // 다운캐스팅 해야함
		Powder powder02 = (Powder)dPrinter3_2.getMaterial();
		
		
		// 위 3d 프린터의 한계는 재료가 플라스틱에 종속되어 있음
		// 하지만 사용자 입장에서 재료를 파우더로 변경 한다면 
		// 코드의 수정이나 새로운 클래스 생성이 불가피하다.
		
	}// end of main
	// 제네릭이란?
	// 무엇이든 담을 수 있는 제네릭 프로그래밍, 자바 ver 5.0 부터 사용;
	
	// 사용하는 이유 ? : 우리가 변수를 사용한다고 한다면 항상 자료형을
	// 먼저 지정하게 되어 있다. 
	// 변수의 이름이 같고 데이터 타입(자료형)이 달라야 한다면 
	// 제네릭 문법을 사용한다. 
}
