<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<td><input type="text" name="searchId" id="searchId" placeholder="수정할 아이디를 입력하세요" style="width: 180px;">&ensp;</td>
				<td><input type="button" value="검색" id="searchBtn" name="searchBtn">&ensp;</td>
				<td><div id="searchDiv"></div></td>
			</tr>
		</table>
	</div>
	<form id="modifyForm" method="post" action="/chapter06_SpringMaven/user/modify">
		<table id="modifyTable" cellpadding="5" cellspacing="0">
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name">
					<div id="nameDiv"></div></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" id="id" name="id" readonly="readonly">
					<div id="idDiv"></div></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="pwd" name="pwd">
					<div id="pwdDiv"></div></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="button" value="수정" id="modifyBtn" >&ensp; <input type="button"
					id="resetBtn" value="초기화"></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#modifyTable').hide();
		$('#searchBtn').click(function(){
			$('#searchDiv').empty();
			if($('#searchId').val() == ''){
				$('#searchDiv').text("찾고자 하는 아이디가 없습니다").css('color', 'red').css('font-size', '7pt').css('font-weight', 'bold');
			} else {
				$.ajax({
					type : 'POST',
					url : '/chapter06_SpringMaven/user/getUser',
					data : {'id' : $('#searchId').val()},
					dataType : 'json',
					success : function(data){
						if(data.userDTO == null) {
							$('#searchDiv').text("아이디가 존재하지 않습니다").css('color', 'red').css('font-size', '7pt').css('font-weight', 'bold');
						} else {
							$('#modifyTable').show();
							$('#name').val(data.userDTO.name);
							$('#id').val(data.userDTO.id);
							$('#pwd').val(data.userDTO.pwd);
							
							$('#resetBtn').click(function(){
								$('#name').val(data.userDTO.name);
								$('#id').val(data.userDTO.id);
								$('#pwd').val(data.userDTO.pwd);
							});
						}
					},
					error : function(){
						alert("따란~ 에러입니다 :D");
					}
				});
			}
		});
		
		$('#modifyBtn').click(function(){
			$('#nameDiv').empty();
			$('#pwdDiv').empty();
			if ($('#name').val() == '') {
				$('#nameDiv').text("이름을 입력하세요").css('color', 'red').css(
						'font-size', '7pt').css('font-weight', 'bold');
			} else if ($('#pwd').val() == "") {
				$('#pwdDiv').text("비밀번호를 입력하세요").css('color', 'red').css(
						'font-size', '7pt').css('font-weight', 'bold');
			} else {
				$.ajax({
					type : 'POST',
					url : '/chapter06_SpringMaven/user/modify',
					data : $('#modifyForm').serialize(),
					success : function() {
						alert("수정 완료");
						location.href='http://localhost:8080/chapter06_SpringMaven/main/index';
					},
					error : function() {
						alert("등록 실패");
						//location.href='/chapter06_SpringMaven/user/writeForm';
					}
				});
			}
		});
	});
</script>
</html>