package user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

// 컨트롤러와 DB의 중간 전달자의 역할
@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
	}

	@Override
	public List<UserDTO> getList() {
		return userDAO.getList();
	}

	@Override
	public UserDTO getUser(String id) {
		return userDAO.getUser(id);
	}

	@Override
	public void update(UserDTO userDTO) {
		userDAO.userUpdate(userDTO);
		
	}

	@Override
	public void userDelete(String id) {
		userDAO.userDelete(id);
	}

	@Override
	public UserDTO isExistId(String id) {
		return userDAO.isExistId(id);
	}

	@Override
	public List<UserDTO> userSearch(Map<String, String> map) {
		return userDAO.userSearch(map);
	}

}
