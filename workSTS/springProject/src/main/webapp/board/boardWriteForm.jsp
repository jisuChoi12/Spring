<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="boardWriteForm">
	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>제목</th>
			<td>
				<div id="subjectDiv"></div>
				<input type="text" name="subject" id="subject" style="width: 300px" placeholder="제목 입력">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<div id="contentDiv"></div>
				<textarea name="content" id="content" style="width: 300px; height: 200px; resize: none"
					placeholder="내용 입력"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글쓰기" id="boardWriteBtn">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script> <!-- 매번 쓰기 귀찮으면 index.jsp에 한번 적어주기 -->
<script src="../js/board.js" type="text/javascript"></script>
