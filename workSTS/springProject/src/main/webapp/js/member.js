var checkedId;

$('#loginBtn').click(function(){
	$('#loginIdDiv').empty();
	$('#loginPwdDiv').empty();
	if($('#loginId').val()==''){
		$('#loginIdDiv').text('아이디를 입력하세요').css('color','red').css('font-size','8pt');
	} else if($('#loginPwd').val()==''){
		$('#loginPwdDiv').text('비밀번호를 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		$.ajax({
			type : 'POST',
			url : '/springProject/member/login',
			data : 'id='+$('#loginId').val()+"&pwd="+$('#loginPwd').val(),
			dataType : 'text',
			success : function(data){
				if(data=='success'){
					location.href='/springProject/main/index';
				} else if(data=='fail') {
					$('#loginResultDiv').text('아이디 또는 비밀번호가 틀렸습니다').css('color','red').css('font-size','8pt').css('font-weight','bold');
				}
			},
			error : function(e){
				console.log(e);
			}
		});
	}
});

$('#logoutBtn').click(function(){
	$.ajax({
		type : 'POST',
		url : '/springProject/member/logout',
		success : function(){
			alert("로그아웃");
			location.href='/springProject/main/index';
		},
		error : function(e){
			console.log(e);
		}
	});
});

$('#writeFormBtn').click(function(){
	location.href='/springProject/member/writeForm';

});

$('#writeName').focusout(function(){
	if($('#writeName').val()==''){
		$('#writeNameDiv').text('이름을 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		$('#writeNameDiv').empty();
	}
});
$('#writeId').focusout(function(){
	if($('#writeId').val()==''){
		$('#writeIdDiv').text('아이디를 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		$('#writeIdDiv').empty();
		$.ajax({
			type : 'POST',
			url : '/springProject/member/checkId',
			data : {'id' : $('#writeId').val()},
			dataType : 'text',
			success : function(data){
				if(data=='exist'){
					$('#writeIdDiv').text('이미 있는 아이디입니다').css('color','red').css('font-size','8pt');
				} else {
					$('#writeIdDiv').text('사용 가능한 아이디입니다').css('color','blue').css('font-size','8pt');
					$('#check').val($('#writeId').val());
				}
			},
			error : function(e){
				console.log(e);
			}
		});
	}
});
$('#writePwd').focusout(function(){
	if($('#writePwd').val()==''){
		$('#writePwdDiv').text('비밀번호를 입력하세요').css('color','red').css('font-size','8pt');
	} else {
		$('#writePwdDiv').empty();
	}
});
$('#writeRePwd').keyup(function(){
	if($('#writeRePwd').val() != $('#writePwd').val()){
		$('#writeRePwdDiv').text('비밀번호가 일치하지 않습니다').css('color','red').css('font-size','8pt');
	} else {
		$('#writeRePwdDiv').empty();
	}
});

$('#writeBtn').click(function(){
	if($('#writeIdDiv').text()!='사용 가능한 아이디입니다'){
		$('#writeDiv').text('아이디 중복체크').css('color','red').css('font-size','8pt');
	} else if($('#writeId').val()==''){
		$('#writeDiv').text('아이디 없음').css('color','red').css('font-size','8pt');
	} else if($('#writeName').val()==''){
		$('#writeDiv').text('이름 없음').css('color','red').css('font-size','8pt');
	} else if($('#writePwd').val()==''){
		$('#writeDiv').text('비밀번호 없음').css('color','red').css('font-size','8pt');
	} else if($('#writeRePwd').val()==''){
		$('#writeDiv').text('비밀번호 재확인 없음').css('color','red').css('font-size','8pt');
	} else if($('#writePwd').val()!=$('#writeRePwd').val()) {
		$('#writeDiv').text('비밀번호 재확인 다시').css('color','red').css('font-size','8pt');
	} else {
		$.ajax({
			type : 'POST',
			url : '/springProject/member/write',
			data : $('#writeForm').serialize(),
			dataType : 'text',
			success : function(){
				alert('회원가입 성공! 메인페이지로 이동합니다');
				location.href='/springProject/main/index';
			},
			error : function(e){
				console.log(e);
			}
		});
	}
	// $('form[name=writeForm]').submit(); id없이 name속성으로 submit하기 
});

$('#zipcodeBtn').click(function(){
	window.open("/springProject/member/checkPostForm","","width=700 height=500 left=800 top=150 locations=yes scrollbar=yes");
});

$('#zipcodeSearchBtn').click(function(){
	$('#zipcodeSearchDiv').empty();
	if($('#sido').val()=='시도선택'){
		$('#zipcodeSearchDiv').text('시도를 선택하세요').css('color','red').css('font-size','8pt');
	} else if ($('#roadname').val()==''){
		$('#zipcodeSearchDiv').text('도로명을 입력하세요').css('color','red').css('font-size','8pt'); 	
	} else {
		/*$('#tbody').empty();*/
		$('#postTable tr:gt(2)').remove();
		$.ajax({
			type : 'POST',
			url : '/springProject/member/checkPost',
			data : $('#checkPostForm').serialize(),
			dataType : 'json',
			success : function(data){
				$.each(data.list, function(index,items){
					/*if(items.buildingname==null){
						items.buildingname='';
					}
					if(items.ri==null){
						items.ri='';
					}
					if(items.sigungu==null){
						items.sigungu='';
					}*/
					var address = items.sido+" "+items.sigungu+" "+items.yubmyundong+" "+items.ri+" "+items.roadname+" "+items.buildingname;
					address = address.replace(/null/g,''); // null 전체 ''로
					$('<tr/>').append($('<td/>',{
						align : 'center',
						text : items.zipcode
					})).append($('<td/>',{
						colspan : 3,
						align : 'left'
					}).append($('<a/>',{
						href : '#',
						id : 'addressA',
						text : address
					}))).appendTo('#tbody');
				}); // each
				
				$('a').click(function(){
					// alert($(this).prop('tagName'));
					$('#daum_zipcode', opener.document).val($(this).parent().prev().text());
					$('#daum_addr1', opener.document).val($(this).text());
					window.close();
				});
			},
			error : function(e){
				console.log(e);
			}
		});	
	}
	
});

//$('#tbody').on('click','a',function(){
////	opener.document.getElementById("daum_zipcode").value = $(this).parent().parent().children(':first-child').text();
//	opener.document.getElementById("daum_zipcode").value = $(this).parent().prev().text();
//	opener.document.getElementById("daum_addr1").value = $(this).text();
//	window.close();
//	opener.document.getElementById("daum_addr2").focus();
//});


function checkModify(){
	if (document.modifyForm.name.value == "") {
		alert("이름 입력하세요");
		document.modifyForm.name.focus();
	} else if (document.modifyForm.pwd.value == "") {
		alert("패스워드름 입력하세요");
		document.modifyForm.pwd.focus();
	} else if (document.modifyForm.repwd.value != document.modifyForm.pwd.value) {
		alert("패스워드가 일치하지 않습니다.");
		document.modifyForm.repwd.focus();
	} else {
		document.modifyForm.submit();
	}
}

$('#modifyBtn').click(function(){
	$('#modifyNameDiv').empty();
	$('#modifyPwdDiv').empty();
	$('#modifyRePwdDiv').empty();
	if($('#name').val()==''){
		$('#modifyNameDiv').text('이름을 입력하세요').css('color','red').css('font-size','8pt');
	} else if($('#pwd').val()==''){
		$('#modifyPwdDiv').text('비밀번호를 입력하세요').css('color','red').css('font-size','8pt');
	} else if($('#repwd').val() != $('#pwd').val()){
		$('#modifyRePwdDiv').text('비밀번호가 일치하지 않습니다').css('color','red').css('font-size','8pt');
	} else {
		$.ajax({
			type : 'POST',
			url : '/springProject/member/modify',
			data : $('#modifyForm').serialize(),
			success : function(){
				alert('회원정보 수정이 완료되었습니다! 다시 로그인 해주세요 :D');
				location.href="/springProject/main/index";
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});

