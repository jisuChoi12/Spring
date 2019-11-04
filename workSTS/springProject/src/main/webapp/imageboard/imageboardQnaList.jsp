<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/board.css">
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
	</table>
	<br>
	<div id="imageboardPagingDiv" style="display: inline-block; float: left; text-align: center; width: 100%;"></div>
</form>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$.ajax({
			type : 'POST',
			url : '/springProject/imageboard/getImageboardQnaList',
			data : {'pg' : '${pg}', 'productCode' : '${productCode}'},
			dataType : 'json',
			success : function(data){
				/* alert(JSON.stringify(data)); */
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
	
	});

</script>