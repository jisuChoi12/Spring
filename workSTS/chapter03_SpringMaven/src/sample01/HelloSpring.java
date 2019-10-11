package sample01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("acQuickStart.xml");
		MessageBean messageBean = context.getBean("messageBeanImpl", MessageBean.class);
//		messageBean.showPrintBefore(); System.out.println(); // 삽입 대상자 O
//		messageBean.viewPrintBefore(); System.out.println(); // 삽입 대상자 O
//		messageBean.display(); System.out.println(); // 삽입 대상자 X
		
//		messageBean.showPrintAfter(); System.out.println(); // 삽입 대상자 O
//		messageBean.viewPrintAfter(); System.out.println(); // 삽입 대상자 O
//		messageBean.display(); System.out.println(); // 삽입 대상자 X
		
		messageBean.showPrint(); System.out.println(); // 삽입 대상자 O
		messageBean.viewPrint(); System.out.println(); // 삽입 대상자 O
		messageBean.display(); System.out.println(); // 삽입 대상자 X
		
	}

}
