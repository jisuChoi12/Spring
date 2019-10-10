package sample04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageBeanImpl2 implements MessageBean {
	private @Value("김띠용") String name;
	private String phone = "010-1234-4567";
	@Autowired
	private Outputter outputter;

	@Override
	public void helloCall() {
		System.out.println("helloCall 메소드");
		outputter.output("이름:" + name + "\t핸드폰:" + phone);
	}

}