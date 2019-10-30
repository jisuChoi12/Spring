<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="../css/board.css">
<style>
</style>

<form name="boardViewForm">
<input type="hidden" id="seq" name="seq" value="${seq }">
<input type="hidden" id="pg" name="pg" value="${pg }">
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
			<input type="button" value="목록" onclick="location.href='/springProject/board/boardList?pg=${pg}'" style="cursor: pointer;">
			<input type="button" value="답글" onclick="mode(3)" style="cursor: pointer;">
			<div id="modifyAndDeleteBtnDiv" style="display: inline-block;">
				<input type="button" value="글수정"
					onclick="mode(1)" style="cursor: pointer;">
				<input type="button" value="글삭제"
					onclick="mode(2)" style="cursor: pointer;">
				<!-- 다시 비밀번호 물어보는거 추가하기 -->
			</div>
		</td>
	</tr>
</table>
</form>
<script>
function mode(num){
	if(num==1){
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = '/springProject/board/boardModifyForm';
		document.boardViewForm.submit();
	} else if(num==2){
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = '/springProject/board/boardDelete';
		document.boardViewForm.submit();
	} else if(num==3) {
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = '/springProject/board/boardReplyForm';
		document.boardViewForm.submit();
	}
}

</script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#modifyAndDeleteBtnDiv').hide();
	$.ajax({
		type : 'POST',
		url : '/springProject/imageboard/getImageView',
		data : {'seq' : $('#seq').val(), 'pg' : $('#pg').val()},
		dataType : 'json',
		success : function(data){
			$('#subject').text(data.boardDTO.subject);
			$('#boardSeq').text(data.boardDTO.seq+'');
			$('#boardId').text(data.boardDTO.id);
			$('#boardHit').text(data.boardDTO.hit);
			$('#boardContent').text(data.boardDTO.content);
			
			if($('#memId').val() == data.boardDTO.id){
				$('#modifyAndDeleteBtnDiv').show();
			}
			
		},
		error : function(err){
			console.log(err);
		}
		
	});
});
</script>