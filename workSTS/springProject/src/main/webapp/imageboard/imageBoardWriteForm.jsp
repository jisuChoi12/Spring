<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="imageboardWriteForm" name="imageboardWriteForm">
	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>상품코드</th>
			<td>
				<input type="text" name="imageId" id="imageId"
				style="width: 300px" placeholder="상품코드 입력">
				<div id="imageIdDiv"></div>
			</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>
				<input type="text" name="imageName" id="imageName"
				style="width: 300px" placeholder="상품명 입력">
				<div id="imageNameDiv"></div>
			</td>
		</tr>
		<tr>
			<th>단가</th>
			<td>
				<input type="text" name="imagePrice" id="imagePrice"
				style="width: 300px" placeholder="단가 입력">
				<div id="imagePriceDiv"></div>
			</td>
		</tr>
		<tr>
			<th>개수</th>
			<td>
				<input type="text" name="imageQty" id="imageQty"
				style="width: 300px" placeholder="개수 입력">
				<div id="imageQtyDiv"></div>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="imageContent" id="imageContent"
					style="width: 300px; height: 200px; resize: none"
					placeholder="내용 입력"></textarea>
				<div id="imageContentDiv"></div>
			</td>
		</tr>
		<!-- name은 하나로 잡아서 배열로 넘기기 아이디는 다르게-->
		<tr>
			<td colspan="2">
				<input type="file" value="찾아보기" name="img" id="img1" style="width: 100%">
				<div id="img1Div"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="file" value="찾아보기" name="img" id="img2" style="width: 100%">
				<div id="img1Div"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="file" value="찾아보기" name="img[]" id="img3" style="width: 100%" multiple="multiple"> <!-- 사진 여러개 동시선택 -->
				<div id="img3Div"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="이미지 등록" id="imageboardWriteBtn">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../js/imageboard.js" type="text/javascript">
</script>