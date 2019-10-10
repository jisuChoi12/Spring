package sample02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CalcAdd implements Calc {
//	private @Value("25") int x;
//	private @Value("36") int y;
	
	private int x, y;

	// 생성자 통해서 값이 들어가는걸 보여주기 위해서 이런식으로 씀
	// 생성자는 빈이 만들어질때 자동으로 불려지기 때문에 문제없음
	public CalcAdd(@Value("25")int x, @Value("36")int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void calculate() {
		System.out.println(x + " + " + y + " = " + (x + y));
	}
}
