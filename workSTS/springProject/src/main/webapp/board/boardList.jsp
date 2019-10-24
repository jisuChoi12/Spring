<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="../css/board.css">

<div style="width: 700px;">
	<div id="searchDiv"></div>
	<select id="searchOption" style="width: 100px;">
		<option value="id" id="id">작성자</option>
		<option value="subject" id="subject">제목</option>
	</select>
	<input type="text" id="keyword" value="${keyword }" placeholder="검색어를 입력하세요" size="30%">
	<input type="button" value="검색" id="searchBtn"><br><br>
</div>
<input type="hidden" id="pg" value="${pg }">
<table id="boardListTable" border="1" frame="hsides" rules="rows" cellspacing="0" cellpadding="2" width="800px">
	<tr>
		<th width="100">글번호</th>
		<th width="300">제목</th>
		<th width="100">작성자</th>
		<th width="100">작성일</th>
		<th width="100">조회수</th>
	</tr>
</table>
<br>
<div id="boardPagingDiv" style="display: inline-block; float: left; text-align: center; width: 100%; cursor: pointer;"></div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../js/board.js" type="text/javascript"></script>
