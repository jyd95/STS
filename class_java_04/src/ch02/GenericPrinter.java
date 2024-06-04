package ch02;

public class GenericPrinter<T> {

	// 일반적으로 T 라는 대체 문자를 사용, E - element (자료구조) , K - key , 
	// V - value ( 아무 문자나 가능함 ), 자료형 매개 변수(type parameter)
	// 이 클래스를 사용하는 시점에서 실제 사용될 자료형이 결정 된다.
	
	private T material; // T 대체 문자형으로 변수 선언
	
	// get,set
	public T getMaterial() {
		return material;
	}
	
	public void setMaterial(T material) {
		this.material = material;
	}
	// GenericPronter<T> -- 참조 변수를 sysout(참조변수) --> 나의 멤버 meterial에 toString() 설계 함
	
	@Override
	public String toString() {
		return material.toString();
	}
}
