<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>daemon</title>
<script src="${pageContext.request.contextPath}/res/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script>
$(function(){
	
	$("#qb").click(function(e) {
		
//			alert($("#scode").val());
		if($("#scode").val()){
			$.getJSON("afsQuery/netQuery", {scode:$("#scode").val()}, function(resp){
				if(resp['flg'] == 'Y'){
					$("#error").html('<font color="green">'+resp['res']+'</font>');
				}else{
					$("#error").html('<font color="red">'+resp['res']+'</font>');
				}
			});
		}else{
			$("#error").html("请输入申码");
		}
	});
});
</script>
</head>
<body>
	<center>
		<h2>防伪查询系统</h2>
	</center>
	<hr>
	<div align="center">
		<b>请输入查询码 </b> <input name="scode" id="scode" type="text"
			maxlength="21" size="80" />
		<button id="qb">查询</button>
	</div>
	<div id="error" align="center"/>
</body>
</html>
