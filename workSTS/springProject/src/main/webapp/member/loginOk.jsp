<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

${sessionScope.memName }님 로그인
<br>
<br>
<input type="button" value="로그아웃" id="logoutBtn" style="cursor:pointer; width: 100%; border: 0; outline: 0;">
<input type="submit" value="회원정보수정 " onclick="location.href='/springProject/member/modifyForm'" style="cursor:pointer; width: 100%; border: 0; outline: 0;">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../js/member.js" type="text/javascript"></script>

<!-- pageScope -> requestScope -> sessionScope 순으로 찾는다 -->
<!-- ${sessionScope.memName} 대신 ${memName} 해도 괜찮음 -->

