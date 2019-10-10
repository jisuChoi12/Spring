package sample05;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloSpring {

	public void menu(ApplicationContext context) {
		Scanner scan = new Scanner(System.in);
		SungJuk sungJuk;
		int num;

		while (true) {
			System.out.println("******************");
			System.out.println("1. 입력");
			System.out.println("2. 출력");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("5. 정렬_총점으로 내림차순");
			System.out.println("6. 끝");
			System.out.println("******************");
			System.out.print("번호 입력: ");
			num = scan.nextInt();

			if (num == 6) {
				break;
			}
			if (num == 1) {
				sungJuk = context.getBean("sungJukInput", SungJuk.class);
				sungJuk.execute();
			} else if (num == 2) {
				sungJuk = context.getBean("sungJukOutput", SungJuk.class);
				sungJuk.execute();
			} else if (num == 3) {
				sungJuk = context.getBean("sungJukModify", SungJuk.class);
				sungJuk.execute();
			} else if (num == 4) {
				sungJuk = context.getBean("sungJukDelete", SungJuk.class);
				sungJuk.execute();
			} else if (num == 5) {
				sungJuk = context.getBean("sungJukSort", SungJuk.class);
				sungJuk.execute();
			} else {
				System.out.println("1번부터 5번까지만 입력해주세요.");
			}
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloSpring helloSpring = context.getBean("helloSpring", HelloSpring.class);
		helloSpring.menu(context);
		System.out.println("프로그램을 종료합니다.");
	}
}
