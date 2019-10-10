package sample01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageBean messageBean = (MessageBean) context.getBean("messageBeanImpl");
		messageBean.sayHello(); // 딸기 5000 3 (어노테이션)
		messageBean.sayHello("수박", 20000); // 수박 20000 3 
		messageBean.sayHello("토마토", 3500, 7); // 토마토 3500 7
	}
}
