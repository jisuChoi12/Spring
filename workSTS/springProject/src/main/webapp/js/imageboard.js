$('#imageboardWriteBtn').click(function(){
	$('#imageIdDiv').empty();
	$('#imageNameDiv').empty();
	$('#imagePriceDiv').empty();
	$('#imageQtyDiv').empty();
	$('#imageContentDiv').empty();
	$('#img1Div').empty();
	if($('#imageId').val()==''){
		$('#imageIdDiv').text('상품코드를 입력하세요.').css('color','red').css('font-size', '8pt');
		$('#imageId').focus();
	} else if($('#imageName').val()==''){
		$('#imageNameDiv').text('상품명을 입력하세요.').css('color','red').css('font-size', '8pt');
		$('#imageName').focus();
	} else if($('#imagePrice').val()==''){
		$('#imagePriceDiv').text('단가를 입력하세요.').css('color','red').css('font-size', '8pt');
		$('#imagePrice').focus();
	} else if($('#imageQty').val()==''){
		$('#imageQtyDiv').text('개수를 입력하세요.').css('color','red').css('font-size', '8pt');
		$('#imageQty').focus();
	} else if($('#imageContent').val()==''){
		$('#imageContentDiv').text('내용을 입력하세요.').css('color','red').css('font-size', '8pt');
		$('#imageContent').focus();
	} else if($('#img3').val()==''){
		$('#img3Div').text('파일을 선택하세요.').css('color','red').css('font-size', '8pt');
		$('#img3').focus();
	} else {
		/*$('#imageboardWriteForm').submit();*/
		
		var formData = new FormData($('#imageboardWriteForm')[0]);
		$.ajax({
			type : 'POST',
			enctype : 'multipart/form-data',
			processData : false, // 데이터를 컨텐트 타입에 맞게 변환 여부
			contentType : false, // 요청 컨텐트 타입, contentType : 'application/json'
			url : '/springProject/imageboard/imageboardWrite',
			data : formData,
			success : function(){
				alert('글쓰기 성공');
				location.herf='/springProject/imageboard/imageboardList';
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});








