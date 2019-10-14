package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import lombok.Setter;
import user.bean.UserDTO;

//public class UserDAOImpl implements UserDAO {
//	@Setter
//	private JdbcTemplate jdbcTemplate;
//
//	@Override
//	public void userWrite(UserDTO userDTO) {
//		String sql = "insert into usertable values(?,?,?)";
//		jdbcTemplate.update(sql, userDTO.getName(), userDTO.getId(), userDTO.getPwd());
//		
//	}
//}

///////////////////////////////////////////////////////////////////////////////////////////////////

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {
	// 스프링이 제공하는 기반클래스 NamedParameterJdbcDaoSupport를 extends
	// connection pool은 UserDAOImpl이 가지고 있어야 한다 (xml에서 setter injection)
	// JdbcDaoSupport에서는 getJdbcTemplate()메소드를 제공하므로 JdbcTemplate을 편리하게 사용할 수 있다

	@Override
	public void userWrite(UserDTO userDTO) {
//		String sql = "insert into usertable values(?,?,?)";
//		getJdbcTemplate().update(sql, userDTO.getName(), userDTO.getId(), userDTO.getPwd());

		String sql = "insert into usertable values(:name,:id,:pwd)"; // map으로 넘겨줘야함... 알아서 매칭해서 들어감
		Map<String, String> map = new HashMap<String, String>(); // HashMap HashedMap
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public List<UserDTO> getUserList() {
		String sql = "select * from usertable";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
	}

	@Override
	public UserDTO getUser(String id) {
		UserDTO userDTO = null;
//		String sql = "select * from usertable where id=?";
//		try {
//			userDTO = getJdbcTemplate().queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));			
//		} catch (EmptyResultDataAccessException e) {
//			return null;
//		}
//		return userDTO;
		String sql = "select * from usertable where id=:id";
		try {
			return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void userUpdate(UserDTO userDTO) {
		String sql = "update usertable set name=:name, pwd=:pwd where id=:id";
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", userDTO.getName());
		map.put("pwd", userDTO.getPwd());
		map.put("id", userDTO.getId());
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public void userDelete(String id) {
		String sql = "delete from usertable where id=:id";
		getJdbcTemplate().update(sql, id);
	}

}

// RowMapper
// 입력상자, DTO, 컬럼 이름이 같아야 함
// 알아서 매핑, 알아서 list에 담겨 간다.
