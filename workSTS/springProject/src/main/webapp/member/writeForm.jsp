<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form name="writeForm" id="writeForm" method="post"
	action="/springProject/member/write">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width=120>이름</th>
			<td align="left">
				<input type="text" id="writeName" name="name" size=20 placeholder="이름 입력">
				<div id="writeNameDiv"></div>
			</td>
		</tr>

		<tr>
			<th>아이디</th>
			<td align="left">
				<input id="writeId" type="text" name="id" size=25 placeholder="아이디 입력">
				<!-- <input type="button" id="checkIdBtn" value=중복체크> -->
				<input type="hidden" name="check" value="">
				<div id="writeIdDiv"></div>
				<!-- <div id="checkIdDiv"></div> -->
			</td>
		</tr>

		<tr>
			<th>비밀번호</th>
			<td align="left">
				<input type="password" name="pwd" id="writePwd" size=30>
				<div id="writePwdDiv"></div>
			</td>
		</tr>

		<tr>
			<th>비밀번호 확인</th>
			<td align="left">
				<input type="password" name="repwd" id="writeRePwd" size=30>
				<div id="writeRePwdDiv"></div>
			</td>
		</tr>

		<tr>
			<th>성별</th>
			<td align="left"><input type="radio" name="gender" value="0"
				checked="checked"> 여성 <input type="radio" name="gender"
				value="1"> 남성</td>
		</tr>

		<tr>
			<th>이메일</th>
			<td align="left"><input type="text" name="email1" id="email1" size=15> @ <input
				list="mailaddr" name="email2" id="email2" size=15 placeholder="직접입력"> <datalist
					id="mailaddr">
					<option value="gmail.com"></option>
					<option value="naver.com"></option>
					<option value="hotmail.com"></option>
				</datalist>
			</td>
		</tr>

		<tr>
			<th>핸드폰</th>
			<td align="left"><select name="tel1" id="tel1">
					<option value="010">010</option>
					<option value="019">019</option>
					<option value="011">011</option>
			</select> <input type="text" name="tel2" id="tel2" size=5> <input type="text"
				name="tel3" id="tel3" size=5></td>
		</tr>

		<tr>
			<th>주소</th>
			<td align="left">
				<input type="text" id="daum_zipcode" name="zipcode" size=5 readonly>
				<input type="button" value="우편번호검색" id="zipcodeBtn"><br>
				<input type="text" id="daum_addr1" name="addr1" size=40 placeholder="주소 " readsonly><br>
				<input type="text" id="daum_addr2" name="addr2" size=40 placeholder="상세 주소 ">
			</td>
		</tr>

		<tr>
			<td colspan=2 align="center">
				<input type="button" value="회원가입" id="writeBtn">
				<input type="reset" value="다시작성">
				<div id="writeDiv"></div>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../js/member.js" type="text/javascript"></script>