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
			System.out.println("5. 끝");
			System.out.println("*****************");
			System.out.print("번호 입력: ");
			num = scan.nextInt();

			if (num == 5) {
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


//*중복
//
//1.함수
//함수 안에서 중복되는 코드를 제거하기
//DB에서 특히 많이 쓰임 
//
//a(){
//카드
//카톡
//카드
//}
//
//b(){
//카드
//졸고
//카드
//}
//
//c(){
//카드
//공부
//카드
//}
//
//d(){
//구경
//}
//
//조인 포인트 : 모든 함수 (a,b,c,d)
//포인트 컷 : 원하는 함수 (a,b,c)
//핵심 모듈 : 다 다른거 (카톡,졸고,공부)
//공통 모듈(advice) : 카드 카드 ---> 이걸 삽입 (before,after,around)
