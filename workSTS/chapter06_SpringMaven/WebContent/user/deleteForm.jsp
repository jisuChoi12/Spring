<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteForm</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<td><input type="text" name="searchId" id="searchId" placeholder="삭제할 아이디를 입력하세요" style="width: 180px;">&ensp;</td>
				<td><input type="button" value="검색" id="searchBtn"
					name="searchBtn">&ensp;</td>
				<td><div id="searchDiv"></div></td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$('#searchBtn').click(function(){
		$('#searchDiv').empty();
		if($('#searchId').val() == ''){
			$('#searchDiv').text("먼저 아이디를 입력해주세요").css('color', 'red').css('font-size', '7pt').css('font-weight', 'bold');
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
						if(confirm("정말 삭제하시겠습니까?")){
							$.ajax({
								type : 'POST',
								url : '/chapter06_SpringMaven/user/delete',
								data : {'id' : $('#searchId').val()},
								success : function(){
									alert("삭제 완료");
									location.href='http://localhost:8080/chapter06_SpringMaven/main/index';
								},
								error : function(){
									alert("에러");
								}
							});
						}
					}
				},
				error : function(){
					alert("따란~ 에러입니다 :D");
				}
			});
		}
	});
</script>
</html>