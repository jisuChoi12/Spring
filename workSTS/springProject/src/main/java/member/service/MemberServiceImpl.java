package member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

//컨트롤러와 DB의 중간 전달자의 역할
@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public MemberDTO loginForm(Map<String, String> map) {
		return memberDAO.login(map);
	}

}
