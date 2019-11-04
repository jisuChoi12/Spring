<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<table border="1" frame="hsides" rules="rows" cellspacing="0" cellpadding="5">
		<tr>
			<td rowspan="4" width="200px;">
				<div style="display: inline-block;">
					<img id="image1" height="150" width="150" id="productImg">
					<!-- <img src="../image/zoom.png" onclick="bigImage()" style="position: relative; left: -30px; cursor: pointer;"> -->
				</div>
			</td>
			<td>상품명</td>
			<td><span id="imageNameSpan"></span></td>
		</tr>
		<tr>
			<td>단가</td>
			<td><span id="imagePriceSpan"></span></td>
		</tr>
		<tr>
			<td>개수</td>
			<td><span id="imageQtySpan"></span></td>
		</tr>
		<tr>
			<td>합계</td>
			<td><span id="imageTotalSpan"></span></td>
		</tr>
		<tr>
			<td colspan="6">
				<pre style="height: 180px; width: 450px; resize: none; border: none;"><span id="imageContentSpan"></span>
				</pre>
			</td>
		</tr>
		<tr>
			<td colspan="6" align="center">
				<input type="button" value="목록" onclick="location.href='/springProject/imageboard/imageboardList?pg=${pg}'">
				<%-- <input type="button" value="상품문의하기" onclick="location.href='/springProject/imageboard/imageboardQna?pg=${pg}&seq=${seq}'"> --%>
				<input type="button" value="상품문의하기" onclick="location.href='/springProject/imageboard/imageboardQna?pg=${pg}&productCode=8809656139995'">
				<input type="button" value="상품문의리스트" onclick="location.href='/springProject/imageboard/imageboardQnaList?pg=1&productCode=8809656139995'">
			</td>
		</tr>
	</table>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type : 'POST',
		url : '/springProject/imageboard/getImageboardView',
		data : 'seq=${seq}',
		dataType : 'json',
		success : function(data){
			/* alert(JSON.stringify(data)); */
			var total = data.imageboardDTO.imagePrice*data.imageboardDTO.imageQty;
			$('#image1').prop('src','../storage/'+data.imageboardDTO.image1);
			$('#imageNameSpan').text(data.imageboardDTO.imageName);
			$('#imagePriceSpan').text(data.imageboardDTO.imagePrice.toLocaleString());
			$('#imageQtySpan').text(data.imageboardDTO.imageQty);
			$('#imageTotalSpan').text(total.toLocaleString());
			$('#imageContentSpan').text(data.imageboardDTO.imageContent);
		},
		error : function(err){
			console.log(err);
		}
	});
});
</script>
