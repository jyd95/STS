package ch03;

/*
 * T extends 클래스 문법을 사용하기 위한 설계
 */

public class Powder extends Material{
	
	@Override
	public String toString() {
		return "재료는 파우더 입니다.";
	}
	
	
	@Override
	public void doPrinting() {
		System.out.println("파우더 재료로 출력합니다.");
	}
}
