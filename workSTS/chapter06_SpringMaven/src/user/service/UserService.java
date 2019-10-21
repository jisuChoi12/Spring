package user.service;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;

public interface UserService {

	public void write(UserDTO userDTO);

	public List<UserDTO> getList();

	public UserDTO getUser(String searchId);

	public void update(UserDTO userDTO);

	public void userDelete(String id);

	public UserDTO isExistId(String id);

	public List<UserDTO> userSearch(Map<String, String> map);
}
