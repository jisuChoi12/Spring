<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name="boardReplyForm">
	<input type="hidden" name="pseq" id="pseq" value="${pseq }">
	<input type="hidden" name="pg" id="pg" value="${pg }">
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
				<input type="button" value="답글쓰기" id="boardReplyBtn">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$('#boardReplyBtn').click(function(){
	$('#subjectDiv').empty();
	$('#contentDiv').empty();
	if($('#subject').val()==''){
		$('#subjectDiv').text('제목을 입력하세요').css('color','red').css('font-size','8pt');
	} else if($('#content').val()==''){
		$('#contentDiv').text('내용을 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		$.ajax({
			type : 'POST',
			url : '/springProject/board/boardReply',
			data : {'subject' : $('#subject').val(), 'content' : $('#content').val(), 'pseq' : $('#pseq').val(), 'pg' : $('#pg').val()},
			success : function(){
				alert("답글 등록 완료!");
				location.href="/springProject/board/boardList?pg="+${pg}; // el태그로 그냥 컨트롤러에서 준 값
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});
</script>