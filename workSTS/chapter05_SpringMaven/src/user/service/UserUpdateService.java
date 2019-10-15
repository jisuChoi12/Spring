package user.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service
public class UserUpdateService implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public void execute() {
		// 데이터
		Scanner scan = new Scanner(System.in);

		System.out.print("수정할 아이디 입력 : ");
		String id = scan.nextLine();

		// DB
		UserDTO userDTO = userDAO.getUser(id);
		if (userDTO == null) {
			System.out.println("찾고자 하는 아이디가 없습니다");
			return;
		}

		System.out.print("[수정] 이름 입력: ");
		userDTO.setName(scan.nextLine());
		System.out.print("[수정] 비밀번호 입력: ");
		userDTO.setPwd(scan.nextLine());

		userDAO.userUpdate(userDTO);
		// 응답
		System.out.println("데이터를 수정하였습니다");
	}

}
