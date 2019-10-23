<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<form name="loginForm" method="post"
	action="/springProject/member/login">
	<table border="0" cellspacing="0" cellpadding="5" style="width: 300px;">
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="id" id="loginId">
				<div id="loginIdDiv"></div>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="pwd" id="loginPwd">
				<div id="loginPwdDiv"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="loginBtn" value="로그인" style="cursor:pointer; width: 100%; border: 0; outline: 0;"><br>
				<input type="button" id="writeFormBtn" value="회원가입" style="cursor:pointer; width: 100%; border: 0; outline: 0;">
				<div id="loginResultDiv"></div>
			</td>
		</tr>
	</table>
	
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../js/member.js" type="text/javascript"></script>
