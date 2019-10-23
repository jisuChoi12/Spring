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
				location.href="/springProject/main.index";
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});

// boardList.jsp
$(document).ready(function(){
	$.ajax({
		type : 'GET',
		url : '/springProject/board/',
		data : {'pg' : 1},
		dataType : 'json',
		success : function(data){
			
		},
		error : function(err){
			console.log(err);
		}
	});
});