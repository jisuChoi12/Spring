<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3 style="color: white;">
	<img src="../image/pandaJr.png" width="70" height="70"
		onclick="location.href='/springProject/main/index'"
		style="cursor: pointer"><br>MVC를 이용한 미니프로젝트
</h3>
<br>
<font size="3" style="font-weight: bold;">
	<c:if test="${sessionScope.memId != null }">
		<!-- 로그인 상태 -->		
		<a href="/springProject/member/attendance" style="color: white;">출석체크</a>&emsp;
		<a href="/springProject/board/boardWriteForm" style="color: white;">글쓰기</a>&emsp;
		<c:if test="${sessionScope.memId eq 'admin' }">
			<a href="/springProject/imageboard/imageboardWriteForm" style="color: white;">이미지등록</a>
			<!-- 로그인 아이디가 admin 비밀번호는 111 -->
			&emsp;
		</c:if>
		<a href="/springProject/imageboard/imageboardList" style="color: white;">이미지목록</a>&emsp;
	</c:if> <a href="/springProject/board/boardList" style="color: white;">목록</a>
</font>

