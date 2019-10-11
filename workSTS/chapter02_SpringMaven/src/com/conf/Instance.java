package com.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample03.SungJukDTO;
import sample03.SungJukImpl;
import sample05.SungJukDTO2;
import sample05.SungJukOutput;

@Configuration
public class Instance {

	// sample03
	@Bean(name = "sungJukImpl")
	public SungJukImpl getSungJukImpl() {
		return new SungJukImpl();
	}

	@Bean(name = "sungJukDTO")
	public SungJukDTO getSungJukDTO() {
		return new SungJukDTO();
	}

	// sample05
	@Bean(name = "list")
	public List<SungJukDTO2> getArrayList() {
		return new ArrayList<SungJukDTO2>();
	}

	@Bean(name = "sungJukOutput") // 이름을 정해주지 않으면 getSungJukOutput을 찾는다...객체명을 메소드명으로 잡으면 에러 안남
	public SungJukOutput getSungJukOutput() {
		return new SungJukOutput();
	}

}

//interface Fruit {
//}
//
//@Component
//class Apple implements Fruit {
//}
//
//@Component
//class Banana implements Fruit {
//}
//
//@Component
//class Test {
//	@Autowired
//	private Fruit fruit // error
//}
//
//@Component
//class Test {
//	@Autowired
//	@Qualifier("apple")
//	private Fruit fruit
//}