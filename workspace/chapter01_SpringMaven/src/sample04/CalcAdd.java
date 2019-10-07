package sample04;

import org.springframework.stereotype.Component;

@Component // 클래스명과 객체명이 같으면 따로 적어줄 필요 없음
public class CalcAdd implements Calc{

	@Override
	public void calculate(int x, int y) {
		System.out.println(x+" + "+y+" = "+(x+y));
		
	}

}
