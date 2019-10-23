<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
td {
	font-size: 8pt;
}

#addressA:Link {
	color: black;
	text-decoration: none;
}

#addressA:visited {
	color: black;
	text-decoration: none;
}

#addressA:hover {
	color: green;
	text-decoration: underline;
}

#addressA:active {
	color: black;
	text-decoration: none;
}
</style>

<form id="checkPostForm" method="post" action="/springProject/member/checkPost">
	<table border=1 width="100%" cellpadding="2" cellspacing="1" id="postTable">
		<thead id="thead">
			<tr>
				<td align="center">시도</td>
				<td><select name="sido" id="sido" style="width: 120px">
						<option>시도선택</option>
						<option value="서울">서울</option>
						<option value="인천">인천</option>
						<option value="대전">대전</option>
						<option value="대구">대구</option>
						<option value="울산">울산</option>
						<option value="세종">세종</option>
						<option value="광주">광주</option>
						<option value="경기">경기</option>
						<option value="강원">강원</option>
						<option value="전남">전남</option>
						<option value="전북">전북</option>
						<option value="경남">경남</option>
						<option value="경북">경북</option>
						<option value="충남">충남</option>
						<option value="충북">충북</option>
						<option value="부산">부산</option>
						<option value="제주">제주</option></td>
				<td align="center">시.군.구</td>
				<td><input name="sigungu" id="sigungu" type="text"></td>
			</tr>
			<tr>
				<td align="center">도로명</td>
				<td colspan="3"><input type="text" name="roadname" id="roadname" size="35">
					<input type="button" id="zipcodeSearchBtn" value="검색">
					<div id="zipcodeSearchDiv"></div>
				</td>
			</tr>
			<tr>
				<td align="center">우편번호</td>
				<td align="center" colspan="3">주소</td>
			</tr>
		</thead>
		<tbody id="tbody"></tbody>
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../js/member.js" type="text/javascript"></script>
