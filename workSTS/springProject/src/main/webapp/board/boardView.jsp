<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input type="hidden" id="seq" value="${seq }">
<input type="hidden" id="memId" value="${memId }">
<table id="boardViewTable" border="1" frame="hsides" rules="rows" cellspacing="0" cellpadding="5">
	<tr>
		<td id="subject" align="left" colspan="6" style="font-weight: bold; font-size: 1.4em"></td>
	</tr>
	<tr>
		<td colspan="2" align="left">글번호: <div style="display: inline-block;" id="boardSeq"></div></td>
		<td colspan="2" align="center">글쓴이: <div style="display: inline-block;" id="boardId"></div></td>
		<td colspan="2" align="right">조회수: <div style="display: inline-block;" id="boardHit"></div></td>
	</tr>
	<tr>
		<td colspan="6" align="left">
			<pre id="boardContent" style="overflow: auto; white-space: pre-line; word-break: break-all; width: 400px; height: 200px"></pre>
		</td>
	</tr>
	<tr>
		<td colspan="6" align="center">
			<input type="button" value="목록" onclick="location.href='/springProject/board/boardList?pg=${pg}'">
			<input type="button" value="답글" onclick="location.href='/springProject/board/boardReplyForm?pseq=${boardDTO.seq}&pg=${pg }'">
			<div id="modifyAndDeleteBtn" style="display: inline-block;">
				<input type="button" value="글수정"
					onclick="location.href='/springProject/board/boardModifyForm?seq=${boardDTO.seq}&pg=${pg}'">
				<input type="button" value="글삭제"
					onclick="location.href='/springProject/board/boardDelete?seq=${boardDTO.seq}'">
				<!-- 다시 비밀번호 물어보는거 추가하기 -->
			</div>
		</td>
	</tr>
</table>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#modifyAndDeleteBtn').hide();
	$.ajax({
		type : 'POST',
		url : '/springProject/board/getBoardView',
		data : {'seq' : $('#seq').val()},
		dataType : 'json',
		success : function(data){
			$('#subject').text(data.boardDTO.subject);
			$('#boardSeq').text(data.boardDTO.seq+'');
			$('#boardId').text(data.boardDTO.id);
			$('#boardHit').text(data.boardDTO.hit);
			$('#boardContent').text(data.boardDTO.content);
			
			if($('#memId').val() == data.boardDTO.id){
				$('#modifyAndDeleteBtn').show();
			}
			
		},
		error : function(err){
			console.log(err);
		}
		
	});
});
</script>

