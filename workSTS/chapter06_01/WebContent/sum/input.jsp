<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>input</title>
</head>
<body>
	<form method="GET" action="/chapter06_01/result.do">
		<table style="padding: 5px;">
			<tr>
				<td>X :</td>
				<td><input type="text" name="x"></td>
			</tr>
			<tr>
				<td>Y :</td>
				<td><input type="text" name="y"></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>