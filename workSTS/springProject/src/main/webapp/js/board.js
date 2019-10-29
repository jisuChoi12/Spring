// boardList.jsp
$(document).ready(function(){
	$.ajax({
		type : 'POST',
		url : '/springProject/board/getBoardList',
		data : {'pg' : $('#pg').val()},
		dataType : 'json',
		success : function(data){
//			alert(JSON.stringify(data));
			$.each(data.list, function(index,items){
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.seq
				})).append($('<td/>',{
					
				}).append($('<a/>',{
					class : items.seq+'',
					id : 'subjectA',
					text: items.subject,
					href : 'javascript:void(0)'
				}))).append($('<td/>',{
					align: 'center',
					text: items.id
				})).append($('<td/>',{
					align: 'center',
					text: items.logtime
				})).append($('<td/>',{
					align: 'center',
					text: items.hit
				})).appendTo($('#boardListTable'));
				
				// 답글
				for(i=0; i<items.lev; i++){
					$('.'+items.seq).before('&emsp;');
				}
				if(items.lev!=0){
//					$('.'+items.seq).before('<img src="../image/reply.gif">');
					$('.'+items.seq).before($('<img/>',{
						src : '../image/reply.gif'
					}));
				}
				
			}); // each
			
			// 페이징 처리
			$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			
			// 로그인 여부
			$('#boardListTable').on('click','#subjectA',function(){ // 제목을 클릭했을때
				if(data.memId==null){
					alert("먼저 로그인 하세요");
				} else {	
//					alert($(this).parent().prev().text());
//					alert($(this).attr('class'));
					location.href='/springProject/board/boardView?seq='+$(this).attr('class')+'&pg='+$('#pg').val();
				}
			});
		},
		error : function(err){
			console.log(err);
		}
	});
});

$('#boardWriteBtn').click(function(){
	$('#subjectDiv').empty();
	$('#contentDiv').empty();
	if($('#subject').val()==''){
		$('#subjectDiv').text('제목을 입력하세요').css('color','red').css('font-size','8pt');
	} else if($('#content').val()==''){
		$('#contentDiv').text('내용을 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		$.ajax({
			type : 'POST',
			url : '/springProject/board/boardWrite',
			data : $('#boardWriteForm').serialize(),
			success : function(){
				alert("게시글 등록 완료!");
				location.href="/springProject/board/boardList";
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});

/*$('#searchBtn').click(function(){
	$('#searchDiv').empty();
	if($('input[name=keyword]')==''){
		$('#searchDiv').text('검색어를 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		$('#boardListTable tr:gt(0)').remove();
		$.ajax({
			type : 'POST',
			url : '/springProject/board/boardSearch',
			data :  {'searchOption' : $('#searchOption').val(), 'keyword' : $('#keyword').val(), 'pg' : '1'},
			dataType : 'json',
			success : function(data){
				$.each(data.list, function(index,items){
					$('<tr/>').append($('<td/>',{
						align: 'center',
						text: items.seq
					})).append($('<td/>',{
						
					}).append($('<a/>',{
						class : items.seq+'',
						id : 'subjectA',
						text: items.subject,
						href : 'javascript:void(0)'
					}))).append($('<td/>',{
						align: 'center',
						text: items.id
					})).append($('<td/>',{
						align: 'center',
						text: items.logtime
					})).append($('<td/>',{
						align: 'center',
						text: items.hit
					})).appendTo($('#boardListTable'));
				});
				
				// 페이징 처리
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
				
				// 페이징 이벤트
				$('#boardPagingDiv').on('click','a',function(){
					$('#boardListTable tr:gt(0)').remove();
					$.ajax({
						type : 'POST',
						url : '/springProject/board/boardSearch',
						data : {'searchOption' : $('#searchOption').val(), 'keyword' : $('#keyword').val(), 'pg' : $(this).prop('class')},
						dataType : 'json',
						success : function(data){
							$.each(data.list, function(index,items){
								$('<tr/>').append($('<td/>',{
									align: 'center',
									text: items.seq
								})).append($('<td/>',{
									
								}).append($('<a/>',{
									class : items.seq+'',
									id : 'subjectA',
									text: items.subject,
									href : 'javascript:void(0)'
								}))).append($('<td/>',{
									align: 'center',
									text: items.id
								})).append($('<td/>',{
									align: 'center',
									text: items.logtime
								})).append($('<td/>',{
									align: 'center',
									text: items.hit
								})).appendTo($('#boardListTable'));
							});
							
							// 페이징 처리
							$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
						},
						error : function(err) {
							console.log(err);
						}
					});
				});
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});*/

$('#searchBtn').click(function(event, str){
	$('#searchDiv').empty();
	if($('#keyword').val()==''){
		$('#searchDiv').text('검색어를 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		if(str!='trigger') {
			$('input[name=pg]').val(1);
		}
		$('#boardListTable tr:gt(0)').remove();
		$.ajax({
			type : 'POST',
			url : '/springProject/board/boardSearch',
			data :  {'searchOption' : $('#searchOption').val(), 'keyword' : $('#keyword').val(), 'pg' : $('input[name=pg]').val()},//$('#boardSearchForm').serialize(),// serialize는 name태그로 넘어간다
			dataType : 'json',
			success : function(data){
				$.each(data.list, function(index,items){
					$('<tr/>').append($('<td/>',{
						align: 'center',
						text: items.seq
					})).append($('<td/>',{
						
					}).append($('<a/>',{
						class : items.seq+'',
						id : 'subjectA',
						text: items.subject,
						href : 'javascript:void(0)'
					}))).append($('<td/>',{
						align: 'center',
						text: items.id
					})).append($('<td/>',{
						align: 'center',
						text: items.logtime
					})).append($('<td/>',{
						align: 'center',
						text: items.hit
					})).appendTo($('#boardListTable'));
				});
				
				// 페이징 처리
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});

