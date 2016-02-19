<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"  name="viewport" content="width=device-width,initial-scale=1;charset=utf-8"/>
<title>防伪查询系统</title>
<style type="text/css"> 
body {background:#000000; margin:0;}
#content {width:768px;height:auto; margin:0 auto;position:relative;}
#content img {width:100%;}
.search-box {
	position:absolute;
	width:600px;
	height:560px;
	top:454px;
	left:87px; margin-bottom:30px;
	background:#2e2e2e;
	border-radius:10px;
	opacity:1;
}
.search-box-left,
.search-box-right {width:580px;height:250px;margin-left:10px; text-align:center; vertical-align:middle}
.erweima {width:241px;height:182px; left:165px;top:46px;position:relative;}
.erweima-text {color:#F3ECED;text-align:center;}
.search-box-left-input {width:260px;height:30px;margin:20px; padding-left:10px;line-height:30px;color:#727171;font-weight:400;background:#121212;border-radius:10px;}
.search-box-left-button {
	width:118px;
	height:35px;
}
.search-box-text {margin:30px 15px 0px 15px; text-align:left;color:#b6b5b5;line-height:24px;font-size:12px;font-weight:400;}
</style>

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
			$("#error").html('<font color="red">请输入申码</font>');
		}
	});
});
</script>
</head>

<body>
<div id="content">
<img src="${pageContext.request.contextPath}/res/img/bg2.png" />
<div class="search-box">
<div class="search-box-left">
<div>
<input class="search-box-left-input" name="scode" id="scode" type="text" maxlength="21" size="80" /><br/>
<!-- <input type="button"  id="qb" value="点击查询" /> -->
<input class="search-box-left-button" id="qb" type="image" src="${pageContext.request.contextPath}/res/img/search_button2.png" />
</div>
<div>
	<label id="error" align="left"></label>
</div>
<div class="search-box-text">
<p>*请按产品上的防伪标识提示，在查询验证框中依次输入防伪编码，全部输完后回车或点击查询按钮就可识别产品真伪。
</br>
*如查询输入的防伪编码为正确的，系统提示为：您所查询的产品为******公司生产的正牌产品，请放心使用！
</br>
*如查询输入的防伪编码为错误的，系统提示为：您输入的防伪编码有误，谨防假冒！
</br>
*如查询输入的防伪编码已被查询，系统提示为：该产品已于某年某月某日被查询过，谨防假冒！
</p>
</div>
</div>
<div class="search-box-right">
<div class="erweima">
<img src="${pageContext.request.contextPath}/res/img/erweima2.png"/>
<p class="erweima-text">*图示刮开即为防伪码</p>
</div>

</div>
</div>
</div>
</body>
</html>
