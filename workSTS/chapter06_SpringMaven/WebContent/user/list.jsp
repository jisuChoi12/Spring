<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<a href="/chapter06_SpringMaven/main/index"><img src="../image/pandaJr.png" width="40" /></a>
	<table id="table" cellpadding="5" cellspacing="0">
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
	// 여기 --> UserController -> UserService --상속--> UserServiceImpl -> UserDAO --상속--> UserDAOMyBatis -> ... -> UserController(List~>JSON) -> 여기 
	$(document).ready(function() { 
		$.ajax({
			type : 'POST',
			url : '/chapter06_SpringMaven/user/getList',
			dataType : 'json',
			success : function(data) {
				/* index	items 
			         0      {'name' : 김얍얍 , 'id' : yap, 'pwd' : 123} */
				/* alert(JSON.stringify(data)); */
				$.each(data.list, function(index,items){
					/* $('#table').append('<tr><td>'+items.name+'</td><td>'+items.id+'</td><td>'+items.pwd+'</td></tr>'); */
					$('<tr/>').append($('<td/>',{
							align : 'center',
							text : items.name
						})).append($('<td/>',{
							align : 'center',
							text : items.id
						})).append($('<td/>',{
							align : 'center',
							text : items.pwd
						})).appendTo('#table');
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
