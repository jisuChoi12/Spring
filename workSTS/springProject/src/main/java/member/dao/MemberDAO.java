package member.dao;

import java.util.Map;

import member.bean.MemberDTO;

public interface MemberDAO {

	MemberDTO login(Map<String, String> map);

}
