<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<table id="listTable" cellpadding="5" cellspacing="0">
		<tr>
			<th width="100">이름</th>
			<th width="100">아이디</th>
			<th width="100">비밀번호</th>
		</tr>
	</table>
</body>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() { // --> UserController -> UserService --상속--> UserServiceImpl -> UserDAO --상속--> UserDAOMyBatis
		$.ajax({
			type : 'POST',
			url : '/chapter06_SpringMaven/user/getList',
			dataType : 'json',
			success : function(data) {
				/* alert(JSON.stringify(data)); */
				$.each(data.list, function(key,value){
					$('#listTable').append('<tr><td>'+value.name+'</td><td>'+value.id+'</td><td>'+value.pwd+'</td></tr>');
				}); 
			},
			error : function() {
				alert("따란~ 에러입니다 :D");
			}
		});
	});
</script>
</html>
<!--  
dataType : text, html, xml, json 만 가능 list를 json으로 변경시켜서 보내야한다 1. 클래스로 2. 어노테이션
 -->
