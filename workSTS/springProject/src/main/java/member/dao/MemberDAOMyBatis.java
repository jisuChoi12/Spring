package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

// DB
@Transactional
@Repository("memberDAO")
public class MemberDAOMyBatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDTO login(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.login", map);
	}

}
