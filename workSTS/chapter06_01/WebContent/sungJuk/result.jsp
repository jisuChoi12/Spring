<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result</title>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="2" width="120" align="center">***${sungJukDTO.name}의 성적
				***</td>
		</tr>
		<tr>
			<td align="center">총점</td>
			<td><input type="text" name="tot" value="${sungJukDTO.tot}"></td>
		</tr>
		<tr>
			<td align="center">평균</td>
			<td><input type="text" name="avg" value="${sungJukDTO.avg}"></td>
		</tr>

	</table>
</body>
</html>