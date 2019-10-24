package member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.dao.MemberDAO;

//컨트롤러와 DB의 중간 전달자의 역할
@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}

	@Override
	public MemberDTO isExistId(String id) {
		return memberDAO.isExistId(id);
	}

	@Override
	public void write(MemberDTO memberDTO) {
		memberDAO.write(memberDTO);		
	}

	@Override
	public List<ZipcodeDTO> getZipcodeList(Map<String, String> map) {
		return memberDAO.getZipcodeList(map);
	}

	@Override
	public MemberDTO getMember(String id) {
		return memberDAO.getMember(id);
	}

	@Override
	public void modify(MemberDTO memberDTO) {
		memberDAO.modify(memberDTO);
	}

}
