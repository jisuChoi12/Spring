<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="imageboardQnaWriteForm" method="post"
	action="/springProject/imageboard/imageboardQnaWrite">
	<input type="hidden" id="memId" value="${memId }">
	<input type="hidden" id="productCode" name="productCode" value="${productCode }">
	<table id="qnaTable" border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width=120>이름</th>
			<td align="left"><input type="text" id="name" name="name"
				size=20 readonly="readonly"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td align="left"><input type="text" name="id" id="id" size=25
				readonly="readonly"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td align="left"><input type="text" name="email" id="email"
				size=30 readonly="readonly"></td>
		</tr>
		<tr>
			<th>핸드폰</th>
			<td align="left"><input type="text" name="tel" id="tel" size=30
				readonly="readonly"></td>
		</tr>
		<tr>
			<th>비밀글</th>
			<td><input type="checkbox" id="secretCheckBox"
				name="secretCheckBox"><span style="font-size: 10pt;">비밀글</span>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
				<div id="subjectDiv"></div> <input type="text" name="subject"
				id="subject" style="width: 300px" placeholder="제목 입력">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="contentDiv"></div>
				<!-- <textarea name="content" id="content" style="width: 300px; height: 200px; resize: none" placeholder="내용 입력"></textarea> -->
				<textarea id="summernote" name="summernote" style="resize: none;"></textarea></td>
		</tr>
		<tr>
			<td colspan=2 align="center"><input type="button"
				id="productQnaWriteBtn" value="등록"> <input type="button"
				id="refresh" value="다시작성"
				onclick="location.href='/springProject/imageboard/imageboardQna?pg=${pg}&productCode=8809656139995'">
			</td>
		</tr>
	</table>
</form>

<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>

<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->
<!-- 매번 쓰기 귀찮으면 index.jsp에 한번 적어주기 -->
<script type="text/javascript">	
	$(document).ready(function(){
		$.ajax({
			type : 'POST',
			url : '/springProject/member/getMember',
			data : {'id' : $('#memId').val()},
			dataType : 'json',
			success : function(data){
				$('#name').val(data.memberDTO.name);
				$('#id').val(data.memberDTO.id);
				$('#email').val(data.memberDTO.email1+"@"+data.memberDTO.email2);
				$('#tel').val(data.memberDTO.tel1+"-"+data.memberDTO.tel2+"-"+data.memberDTO.tel3);
			
			},
			error : function(err){
				console.log(err);
			}
		});
		
		$('#summernote').summernote({
		    height : 300, // 에디터의 높이
		    width : 700,
		    minHeight : null,
		    maxHeight : null,
		    focus : true,
		    lang : 'ko-KR', // 기본 메뉴언어 US->KR로 변경
		   	disableResizeEditor : true,
		   	callbacks: {
		          onImageUpload: function(files, editor, welEditable) {
		        	  sendFile(files[0], this); 
		         }
		   	}
		});
	});
	
	/* summernote에서 이미지 업로드시 실행할 함수 */
	function sendFile(file, editor){
		/* 파일 전송을 위한 폼생성 */
		data = new FormData();
		data.append('uploadFile', file);
		$.ajax({ // ajax를 통해 파일 업로드 처리
			data : data,
			type : 'POST',
			url : '/springProject/imageboard/imageUpload',
			cache : false,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			dataType : 'text',
			success : function(data){
				$(editor).summernote('editor.insertImage', '../storage/'+data);
			},
			error : function(err){
				console.log(err);
			}
		});
	}
	
	$('#productQnaWriteBtn').click(function(){
		$('#subjectDiv').empty();
		$('#contentDiv').empty();
		if($('#subject').val()==''){
			$('#subjectDiv').text('제목을 입력하세요').css('color','red').css('font-size','8pt');
		} else if($('#content').val()==''){
			$('#contentDiv').text('내용을 입력하세요').css('color','red').css('font-size','8pt');
		} else {
			if($('#secretCheckBox').prop('checked')){
				$('#secretCheckBox').val('1');
			} else {
				$('#secretCheckBox').val('0');
			}
			$('form[id=imageboardQnaWriteForm]').submit();
		}
	}); 
</script>