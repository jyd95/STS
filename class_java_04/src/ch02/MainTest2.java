package ch02;

import ch01.Plastic;
import ch01.Powder;

public class MainTest2 {
	
	public static void main(String[] args) {
		
		// 재료 선언
		Plastic plastic01 = new Plastic();
		Powder powder01 = new Powder();
		Water water01 = new Water();
		
		// 사용하는 시점에 T 대신 어떤 자료형을 사용할지 지정 하면 된다.
		GenericPrinter<Plastic> genericPrinter1 = new GenericPrinter<>();
		genericPrinter1.setMaterial(plastic01); // 메서드 의존 주입
		
		// 최상위 클래스 Object 를 활용할 때와의 비교
		// 형변환을 할 필요가 없음 (다운캐스팅)
		Plastic returnPlastic = genericPrinter1.getMaterial();
		System.out.println(returnPlastic);
		// 컴파일 시점에 오류를 알려줘서 안정적인 코드 작업이 진행된다.
		// Powder retuPowder = genericPrinter1.getMaterial(); <-- 오류 발생
		
		GenericPrinter<Water> genericPrinter2 = new GenericPrinter<>();
		genericPrinter2.setMaterial(water01);
		System.out.println(genericPrinter2);
		
		// 제네릭 프로그래밍의 단점
		// 사용하는 시점에 무엇이든 담을 수 있기 때문에 클래스 설계자 입장으로 바라볼 때
		// 의도하지 않은 타입이 들어올 수 있게 된다.
		
		// 해결 방법 = <T extends 클래스> 문법을 사용.
		
		
	}// end of main
	
}
