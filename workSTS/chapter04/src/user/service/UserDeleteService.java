package user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserDeleteService implements UserService {
	@Setter
	private UserDAO userDAO;

	@Override
	public void execute() {
		// 데이터
		Scanner scan = new Scanner(System.in);

		System.out.print("삭제할 아이디 입력 : ");
		String id = scan.nextLine();

		// DB
		UserDTO userDTO = userDAO.getUser(id);
		if (userDTO == null) {
			System.out.println("삭제하고자 하는 아이디가 없습니다");
			return;
		}
		
		userDAO.userDelete(id);
		
		// 응답
		System.out.println("데이터를 삭제하였습니다");

	}

}
