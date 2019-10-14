package user.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service // @Component와 같은 기능 (데이터/DB/응답을 포함하는 java 로직은 모두 service... dto는 일반적인 클래스이기 때문에 component)
public class UserInsertService implements UserService { 
	@Autowired
	private UserDTO userDTO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		// 데이터
		System.out.print("이름 입력 : ");
		String name = scan.next();
		System.out.print("아이디 입력 : ");
		String id = scan.next();
		System.out.print("비밀번호 입력 : ");
		String pwd = scan.next();
		
		userDTO.setName(name);
		userDTO.setId(id);
		userDTO.setPwd(pwd);
		
		// DB
		userDAO.userWrite(userDTO);
		
		//응답
		System.out.println("데이터를 저장하였습니다.");

	}

}
