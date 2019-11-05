<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/board.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form id="imageboardQnaList" method="post" action="">
	<br>
	<table id="imageboardQnaTable" border="1" frame="hsides" rules="rows"
		cellspacing="0" cellpadding="2" width="600px">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>답변여부</th>
		</tr>
		<c:if test="${list != null }">
			<c:forEach var="imageboardQnaDTO" items="${list }" varStatus="i">
				<tr>
					<td align="center">${imageboardQnaDTO.seq}</td>
					<td class="subject_wrap">
						<a id="qna${i.count }" href="javascript:void(0)"><c:if test="${imageboardQnaDTO.secretStatus=='1'}"><img src="../storage/ico_secret.png" style="width: 12px; height: 17px;"> </c:if>${imageboardQnaDTO.subject}</a>
					</td>
					<td>${imageboardQnaDTO.id}</td>
					<td align="center"><fmt:formatDate value="${imageboardQnaDTO.logtimeQ}" pattern="yyyy-MM-dd"/></td>
					<td>
						<c:if test="${imageboardQnaDTO.replyStatus=='0'}">답변대기중</c:if>
						<c:if test="${imageboardQnaDTO.replyStatus=='1'}">답변완료</c:if>
					</td>
				</tr>
				<tr class="qna${i.count } content_wrap">
					<td colspan="5">${imageboardQnaDTO.content}<input type="button" value="수정" onclick="location.href='/springProject/imageboard/imageboardQnaModify?productCode=${productCode}&seq=${imageboardQnaDTO.seq}'"><input type="button" value="삭제"></td>
				</tr>
				<c:if test="${imageboardQnaDTO.replyStatus=='1'}">
				<tr class="qna${i.count } content_wrap">
					<td colspan="5">${imageboardQnaDTO.replyContent}</td>
				</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</table>
	<br>
	<div id="imageboardPagingDiv" style="display: inline-block; float: left; text-align: center; width: 100%;"></div>
</form>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
/* $(document).ready(function(){
$.ajax({
	type : 'POST',
	url : '/springProject/imageboard/getImageboardQnaList',
	data : {'pg' : '${pg}', 'productCode' : '${productCode}'},
	dataType : 'json',
	success : function(data){
		$.each(data.list, function(index,items){
			$('<tr/>').append($('<td/>',{ // 번호
				align: 'center',
				text: items.seq
			})).append($('<td/>',{ // 제목
				text: items.subject
			})).append($('<td/>',{ // 작성자
				text: items.id
			})).append($('<td/>',{ // 작성일
				align: 'center',
				text: items.logtimeA
			})).append($('<td/>',{ // 답변여부
				text : items.replyStatus,
				align: 'center'
			})).appendTo($('#imageboardQnaTable'));
		}); //each
	},
	error : function(err){
		console.log(err);
	}
});
}); */
$(document).ready(function(){
	$('.content_wrap').hide();
	$('.subject_wrap').on({
		click : function(){
			if($(this).children().first().prop('tagName')=='IMG' && $(this).parent().next().text() != '${memId}'){
				alert("비밀글입니다");
			} else {
				var aaa = $(this).attr('id');
				$('.'+aaa).slideToggle();
			}
		}
	},'a');
});
</script>