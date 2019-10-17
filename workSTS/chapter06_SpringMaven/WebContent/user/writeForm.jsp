<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WriteForm</title>
<style type="text/css">
tr {
	padding: 5px;
}

td {
	width: 100px;
	padding: 5px;
}
</style>
</head>
<body>
	<form id="writeForm" method="post" action="/chapter06_SpringMaven/user/write">
		<table cellpadding="5" cellspacing="0">
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name">
					<div id="nameDiv"></div></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" id="id" name="id">
					<div id="idDiv"></div></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="pwd" name="pwd">
					<div id="pwdDiv"></div></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="button" value="등록" id="writeBtn"
					onclick="checkWrite()">&ensp; <input type="reset"
					id="resetBtn" value="초기화"></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$('#writeBtn').click(
			function() {
				$('#nameDiv').empty();
				$('#idDiv').empty();
				$('#pwdDiv').empty();
				if ($('#name').val() == '') {
					$('#nameDiv').text("이름을 입력하세요").css('color', 'red').css(
							'font-size', '7pt').css('font-weight', 'bold');
				} else if ($('#id').val() == '') {
					$('#idDiv').text("아이디를 입력하세요").css('color', 'red').css(
							'font-size', '7pt').css('font-weight', 'bold');
				} else if ($('#pwd').val() == "") {
					$('#pwdDiv').text("비밀번호를 입력하세요").css('color', 'red').css(
							'font-size', '7pt').css('font-weight', 'bold');
				} else {
					$.ajax({
						type : 'POST',
						url : '/chapter06_SpringMaven/user/write',
						data : $('#writeForm').serialize(),
						success : function() {
							alert("등록 완료");
							location.href='/chapter06_SpringMaven/main/index';
						},
						error : function() {
							alert("등록 실패");
							//location.href='/chapter06_SpringMaven/user/writeForm';
						}
					});
				}
			});
	$('#resetBtn').click(function() {
		$('div').empty();
	});
</script>
</html>