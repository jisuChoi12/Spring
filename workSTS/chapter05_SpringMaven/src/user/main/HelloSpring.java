package user.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import user.service.UserService;

@Component
public class HelloSpring {
	public void menu(ApplicationContext context) {
		Scanner scan = new Scanner(System.in);
		UserService userService = null;
		int num;

		while (true) {
			System.out.println("*******메뉴*******");
			System.out.println("1. 입력");
			System.out.println("2. 출력");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("5. 검색");
			System.out.println("6. 끝");
			System.out.println("*****************");
			System.out.print("번호 입력: ");
			num = scan.nextInt();

			if (num == 6) {
				break;
			}
			if (num == 1) {
				userService = context.getBean("userInsertService", UserService.class);
			} else if (num == 2) {
				userService = context.getBean("userSelectService", UserService.class);
			} else if (num == 3) {
				userService = context.getBean("userUpdateService", UserService.class);
			} else if (num == 4) {
				userService = context.getBean("userDeleteService", UserService.class);
			} else if (num == 5) {
				userService = context.getBean("userSearchService", UserService.class);
			}
			userService.execute();
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		HelloSpring helloSpring = context.getBean("helloSpring", HelloSpring.class);
		helloSpring.menu(context);
		System.out.println("프로그램을 종료합니다.");
	}

}
