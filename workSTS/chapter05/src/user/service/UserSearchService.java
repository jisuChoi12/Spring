package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSearchService implements UserService {
	@Setter
	private UserDAO userDAO;

	@Override
	public void execute() {
		String columnName = null;
		String value = null;
		int num;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("*****************");
		System.out.println("1.이름 검색");
		System.out.println("2.아이디 검색");
		System.out.println("*****************");
		System.out.print("번호 입력 : ");
		num = scan.nextInt();

		if (num == 1) {
			System.out.print("검색할 이름 입력 : ");
			columnName = "name";
			value = scan.next();
		} else if (num == 2) {
			System.out.print("검색할 아이디 입력 : ");
			columnName = "id";
			value = scan.next();
		} else {
			System.out.println("1~2까지 선택 가능합니다");
			return;
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("columnName", columnName);
		map.put("value", value);
		List<UserDTO> list = userDAO.userSearch(map);
	
		// 응답
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName()+"\t"+userDTO.getId()+"\t"+userDTO.getPwd());
		}
	}

}
