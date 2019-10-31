<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="../css/board.css">
<form id="imageboardListForm" method="post" action="/springProject/imageboard/imageboardDelete">
	<br>
	<table id="imageboardTable" border="1" frame="hsides" rules="rows"
		cellspacing="0" cellpadding="2" width="600px">
		<tr>
			<th><input type="checkbox" id="all">번호</th>
			<th>이미지</th>
			<th>상품명</th>
			<th>단가</th>
			<th>개수</th>
			<th>합계</th>
		</tr>
	</table>
	<div style="float: center; border: 0; width: 600px;">
		<input type="button" value="선택삭제" id="checkDeleteBtn" style="float: right;">
	</div>
	<br>
	<div id="imageboardPagingDiv" style="display: inline-block; float: left; text-align: center; width: 100%;"></div>
</form>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$.ajax({
			type : 'POST',
			url : '/springProject/imageboard/getImageboardList',
			data : {'pg' : '${pg}'},
			dataType : 'json',
			success : function(data){
				$.each(data.list, function(index,items){
					$('<tr/>').append($('<td/>',{
						align : 'center',
						text : items.seq
					}).prepend($('<input/>',{
						class : 'check',
						type : 'checkbox',
						value : items.seq,
						name : 'check'
					}))).append($('<td/>',{
						align : 'center'
					}).append($('<img>',{
						class : items.seq+'',
						src : "../storage/"+items.image1,
						style : 'cursor : pointer; width : 100px; height : 100px;'
					}))).append($('<td/>',{
						text : items.imageName
					})).append($('<td/>',{
						align : 'center',
						text : items.imagePrice.toLocaleString()
					})).append($('<td/>',{
						align : 'center',
						text : items.imageQty
					})).append($('<td/>',{
						align : 'center',
						text : (items.imagePrice*items.imageQty).toLocaleString()
					})).appendTo($('#imageboardTable'));
					
					// 이미지 보기
				 	$('.'+items.seq).click(function(){
				 		location.href='/springProject/imageboard/imageboardView?seq='+items.seq+'&pg=${pg}';
				 	});
				}); // each 
				
				$('#imageboardPagingDiv').html(data.imageboardPaging.pagingHTML);
				
			},
			error : function(err){
				console.log(err);
			}
		}); // ajax
		
		// 전체 선택/해제
		$('#all').click(function(){
			if($('#all').prop('checked')){
				$('.check').prop('checked', true);
			} else {
				$('.check').prop('checked', false);
			}
		});
		
		// 선택 삭제
		$('#checkDeleteBtn').click(function(){
			var count = $('.check:checked').length;
			if(count==0){
				alert('삭제할 항목을 선택하세요');
			} else {
				if(confirm('정말로 삭제하시겠습니까?')){	
					$('#imageboardListForm').submit();
				} 
			}
		});
	});
</script>
