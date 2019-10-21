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
	<a href="/chapter06_SpringMaven/main/index"><img style="margin-left: 140px;" src="../image/pandaJr.png" width="50" /></a>
	<div style="margin-bottom: 20px; margin-top: 10px; margin-left: 30px;">
		<select id="searchOption" name="searchOption" style="padding: 2px;">
			<option value="" selected="selected">--선택--</option>
			<option value="name" id="name">이름</option>
			<option value="id" id="id">아이디</option>
		</select>
		<input type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요" >
		<input type="button" id="sBtn" value="검색">
		<div id="sDiv"></div>
	</div>
	<table id="table" cellpadding="5" cellspacing="0">
		<thead>
			<tr>
				<th width="100">이름</th>
				<th width="100">아이디</th>
				<th width="100">비밀번호</th>
			</tr>
		</thead>
		<tbody id="tbody"></tbody>
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
						})).appendTo('#tbody');
				});
				
			},
			error : function() {
				alert("따란~ 에러입니다 :D");
			}
		});
	});
	
	$('#sBtn').click(function(){
		$('#sDiv').empty();
		if($('#searchOption option:selected').val()==''){
			$('#sDiv').text("검색 옵션을 선택해주세요").css('color', 'red').css('font-size', '7pt').css('font-weight', 'bold');
		} else if($('#keyword').val()==''){
			$('#sDiv').text("검색어를 입력해주세요").css('color', 'red').css('font-size', '7pt').css('font-weight', 'bold');
		} else {
			$('#tbody').empty();
			$.ajax({
				type : 'POST',
				url : '/chapter06_SpringMaven/user/search',
				data :  JSON.stringify({'option' : $('#searchOption').val(), 'keyword' : $('#keyword').val()}), 
				dataType : 'json',
				contentType : 'application/json;charset=UTF-8', // json으로 넘겨줄 경우 contentType을 지정해줘야 한다
				success : function(data){
					$.each(data.list, function(index,items){
						$('<tr/>').append($('<td/>',{
								align : 'center',
								text : items.name
							})).append($('<td/>',{
								align : 'center',
								text : items.id
							})).append($('<td/>',{
								align : 'center',
								text : items.pwd
							})).appendTo('#tbody');
					});
				},
				error : function(err){
					console.log(err);
					alert("에러");
				} 
			});
		}
	});
</script>
</html>
<!--  
dataType : text, html, xml, json 만 가능 list를 json으로 변경시켜서 보내야한다 1. 클래스로 2. 어노테이션
 -->
