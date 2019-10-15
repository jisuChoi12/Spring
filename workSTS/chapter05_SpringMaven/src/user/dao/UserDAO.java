package user.dao;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;

public interface UserDAO {

	public void userWrite(UserDTO userDTO);

	public UserDTO getUser(String id);

	public void userDelete(String id);

	public List<UserDTO> getUserList();

	public void userUpdate(UserDTO userDTO);

	public List<UserDTO> userSearch(Map<String, String> map);

}
