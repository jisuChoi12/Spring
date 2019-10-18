<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<style>
#mainA:link {
	color: black;
	text-decoration: none;
}
#mainA:visited {
	color: black;
	text-decoration: none;
}
#mainA:hover {
	color: red;
	text-decoration: underline;
	font-weight: bold;
}
#mainA:active {
	color: black;
	text-decoration: none;
}
ul {
	list-style: none;
}
li {
	margin-top: 10px;
}
</style>
</head>
<body>
	<div>
		<div style="margin-left: 0px;">*** 메인화면 ***</div>
		<div>
			<ul>
				<li><a id="mainA" href="/chapter06_SpringMaven/user/writeForm">입력</a></li>
				<li><a id="mainA" href="../user/list">출력</a></li>
				<li><a id="mainA" href="../user/modifyForm">수정</a></li>
				<li><a id="mainA" href="../user/deleteForm">삭제</a></li>
			</ul>
		</div>
	</div>
</body>
</html>