package user.service;

import lombok.Setter;
import user.dao.UserDAO;

public class UserSelectService implements UserService {
	@Setter
	private UserDAO userDAO;
	
	@Override
	public void execute() {
		// DB
		
		
		// 응답

	}

}
