<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.chart {
	display : inline-block;
	width : 25px;
	height : 75px;
	margin-right: 2px;
	background-color: coral;
}
</style>
</head>
<body>
 <div></div>
</body>
<script src="https://d3js.org/d3.v5.min.js">
 var data = [5,10,15,20,25,20,15,10,5];
 d3.select('body').selectAll('div').data(dataset).enter().append('div').attr('class','chart').style('height',function(d){return d*5+'px';})

</script>
</html>