package member.service;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public interface MemberService {

	public MemberDTO login(Map<String, String> map);

	public MemberDTO isExistId(String id);

	public void write(MemberDTO memberDTO);

	public List<ZipcodeDTO> getZipcodeList(Map<String, String> map);

	public MemberDTO getMember(String id);

	public void modify(MemberDTO memberDTO);

	public void insertAddendance(String id);

	public String getAttDates(String id);

}
