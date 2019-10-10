package sample02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CalcMul implements Calc {
//	private @Value("25") int x;
//	private @Value("36") int y;

	private int x, y;

	// setter를 통해서 값이 들어가는걸 보여주기 위해서 이런식으로 씀
	// 생성자와 달리 setter는 자동으로 불려지지 않기 때문에 @Autowired를 걸어줘야함
	@Autowired
	public void setX(@Value("25")int x) {
		this.x = x;
	}
	@Autowired
	public void setY(@Value("36")int y) {
		this.y = y;
	}

	@Override
	public void calculate() {
		System.out.println(x + " * " + y + " = " + (x * y));
	}
}
