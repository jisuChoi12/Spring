package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
//		ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MessageBean bean1 = (MessageBean) context.getBean("messageBean"); // 부모로 접근
		bean1.sayHello("Spring");
		System.out.println();
		MessageBean bean2 = (MessageBean) context.getBean("messageBean"); 
		bean2.sayHello("Spring");
		System.out.println();
		MessageBean bean3 = (MessageBean) context.getBean("messageBean"); 
		bean3.sayHello("Spring");
		System.out.println();
		
		((AbstractApplicationContext) context).close();
	}

}

// 스프링 설정 파일 - applicationContext.xml 보통 src에 만든다 (Spring Bean Configuration File)