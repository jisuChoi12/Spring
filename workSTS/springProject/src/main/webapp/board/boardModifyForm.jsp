<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form name="boardModifyForm" method="post" action="/springProject/board/boardModify">
	<input type="hidden" name="seq" id="seq" value="${seq }">
	<input type="hidden" name="pg" id="pg" value="${pg }">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td>제목</td>
			<td colspan="5" style="font-weight: bold; font-size: 1.4em">
				<div id="subjectDiv"></div>
				<input type="text" name="subject" id="subject" value="${boardDTO.subject }">
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="5">
				<div id="contentDiv"></div>
				<pre style="overflow: auto; white-space: pre-line; word-break: break-all; width: 400px; height: 200px;">
					<textarea name="content" id="content" style="width: 390px; height: 190px; resize: none">${boardDTO.content }</textarea>
				</pre>
			</td>
		</tr>
		<tr>
			<td colspan="6" align="center">
				<input type="button" value="글수정" id="boardModifyBtn">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$('#boardModifyBtn').click(function(){
	$('#subjectDiv').empty();
	$('#contentDiv').empty();
	if($('#subject').val()==''){
		$('#subjectDiv').text('제목을 입력하세요').css('color','red').css('font-size','8pt');
	} else if($('#content').val()==''){
		$('#contentDiv').text('내용을 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		$.ajax({
			type : 'POST',
			url : '/springProject/board/boardModify',
			data : {'subject' : $('#subject').val(), 'content' : $('#content').val(), 'seq' : $('#seq').val(), 'pg' : $('#pg').val()},
			success : function(){
				alert("게시글 수정 완료!");
				location.href="/springProject/board/boardList?pg="+${pg};
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});
</script>
