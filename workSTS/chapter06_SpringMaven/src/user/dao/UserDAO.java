package user.dao;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;

public interface UserDAO {

	public void write(UserDTO userDTO);

	public UserDTO getUser(String id);

	public void userDelete(String id);

	public List<UserDTO> getList();

	public void userUpdate(UserDTO userDTO);

	public List<UserDTO> userSearch(Map<String, String> map);

}
