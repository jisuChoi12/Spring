package member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

// DB
@Transactional // commit과 close를 대신해줌 
@Repository("memberDAO")
public class MemberDAOMyBatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDTO login(Map<String, String> map) { 
		return sqlSession.selectOne("memberSQL.login", map);
	}

	@Override
	public MemberDTO isExistId(String id) {
		return sqlSession.selectOne("memberSQL.isExistId",id);
	}

	@Override
	public void write(MemberDTO memberDTO) {
		sqlSession.insert("memberSQL.insert", memberDTO);
		
	}

	@Override
	public List<ZipcodeDTO> getZipcodeList(Map<String, String> map) {
		return sqlSession.selectList("memberSQL.getZipcodeList", map);
	}

	@Override
	public MemberDTO getMember(String id) {
		return sqlSession.selectOne("memberSQL.getMember", id);
	}

	@Override
	public void modify(MemberDTO memberDTO) {
		sqlSession.update("memberSQL.modify",memberDTO);
	}

	@Override
	public void insertAddendance(String id) {
		int count = sqlSession.selectOne("memberSQL.isAttExist",id);
		if(count==0){
			sqlSession.insert("memberSQL.insertAttendance",id);
		} else {
			sqlSession.update("memberSQL.updateAttendance",id);			
		}
	}

	@Override
	public String getAttDates(String id) {
		return sqlSession.selectOne("memberSQL.getAttDates",id);
	}
}
