package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserDTO;

// DB
@Transactional
@Repository("userDAO")
public class UserDAOMybatis implements UserDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void write(UserDTO userDTO) {
		sqlSession.insert("userSQL.write", userDTO);
	}

	@Override
	public UserDTO getUser(String id) {
		return sqlSession.selectOne("userSQL.getUser", id);
	}

	@Override
	public void userDelete(String id) {
		sqlSession.delete("userSQL.userDelete", id);
	}

	@Override
	public List<UserDTO> getList() {
		return sqlSession.selectList("userSQL.getList");
	}

	@Override
	public void userUpdate(UserDTO userDTO) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		sqlSession.update("userSQL.userUpdate", map);
	}

	@Override
	public List<UserDTO> userSearch(Map<String, String> map) {
		return sqlSession.selectList("userSQL.userSearch",map);
	}

	@Override
	public UserDTO isExistId(String id) {
		return sqlSession.selectOne("userSQL.isExistId", id);
	}

}
