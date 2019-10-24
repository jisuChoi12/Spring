<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="modifyForm" method="post"
	action="/springProject/member/modify.do">
<input type="hidden" id="memId" value="${memId }">
	<table id="modifyTable" border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width=120>이름</th>
			<td align="left">
				<input type="text" id="name" name="name" size=20>
				<div id="modifyNameDiv"></div>
			</td>
		</tr>

		<tr>
			<th>아이디</th>
			<td align="left"><input type="text" name="id" id="id" size=25 readonly></td>
		</tr>

		<tr>
			<th>비밀번호</th>
			<td align="left">
				<input type="password" name="pwd" id="pwd" size=30>
				<div id="modifyPwdDiv"></div>
			</td>
		</tr>

		<tr>
			<th>비밀번호 확인</th>
			<td align="left">
				<input type="password" name="repwd" id="repwd" size=30>
				<div id="modifyRePwdDiv"></div>
			</td>
		</tr>

		<tr>
			<th>성별</th>
			<td align="left">
				<input type="radio" class="genderRadio" name="gender" id="gender_0" value="0"> 여성 
				<input type="radio" class="genderRadio" name="gender" id="gender_1" value="1"> 남성
			</td>
		</tr>

		<tr>
			<th>이메일</th>
			<td align="left">
				<input type="text" name="email1" id="email1" size=15 > @ 
				<input list="mailaddr" name="email2" id="email2" size=15 placeholder="직접입력">
				<datalist id="mailaddr">
					<option value="gmail.com"></option>
					<option value="naver.com"></option>
					<option value="hotmail.com"></option>
				</datalist>
			</td>
		</tr>

		<tr>
			<th>핸드폰</th>
			<td align="left">
				<select name="tel1" id="tel1">
					<option value="010">010</option>
					<option value="019">019</option>
					<option value="011">011</option>
				</select>
				<input type="text" name="tel2" id="tel2" size=5>
				<input type="text" name="tel3" id="tel3" size=5>
			</td>
		</tr>

		<tr>
			<th>주소</th>
			<td align="left">
				<input type="text" id="daum_zipcode" name="zipcode" size=5 readonly>
				<input type="button" value="우편번호검색" id="zipcodeBtn"> <br>
				<input type="text" id="daum_addr1" name="addr1" size=40 placeholder="주소 " readonly="readonly"><br>
				<input type="text" id="daum_addr2" name="addr2" size=40 placeholder="상세 주소 " >
			</td>
		</tr>

		<tr>
			<td colspan=2 align="center">
				<input type="button" id="modifyBtn" value="회원정보수정">
				<input type="button" id="refresh" value="다시작성" onclick="location.href='/springProject/member/modifyForm'">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script> <!-- 매번 쓰기 귀찮으면 index.jsp에 한번 적어주기 -->
<script src="../js/member.js" type="text/javascript"></script>
<script type="text/javascript">	
	$(document).ready(function(){
		$.ajax({
			type : 'POST',
			url : '/springProject/member/getMember',
			data : {'id' : $('#memId').val()},
			dataType : 'json',
			success : function(data){
				$('#name').val(data.memberDTO.name);
				$('#id').val(data.memberDTO.id);
				$('#email1').val(data.memberDTO.email1);
				$('#email2').val(data.memberDTO.email2);
				$('#tel1').val(data.memberDTO.tel1);
				$('#tel2').val(data.memberDTO.tel2);
				$('#tel3').val(data.memberDTO.tel3);
				$('#daum_zipcode').val(data.memberDTO.zipcode);
				$('#daum_addr1').val(data.memberDTO.addr1);
				$('#daum_addr2').val(data.memberDTO.addr2);
				$('#gender_'+data.memberDTO.gender).prop('checked', true);

			},
			error : function(err){
				console.log(err);
			}
		});
	});
</script>