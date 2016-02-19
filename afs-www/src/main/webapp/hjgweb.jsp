<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>防伪查询系统</title>
<style type="text/css"> 
body {background:#000000}
#content {width:960px;height:auto;margin:0 auto;position:relative;}
#content img {width:100%;}
.search-box {position:absolute;width:800px;height:350px;top:58%;left:80px;background:#666;
         border-radius:10px;opacity:0.6;}
.search-box-left,
.search-box-right {width:380px;height:300px;margin-left:10px;float:left;}
.erweima {width:220px;height:146px; left:90px;top:46px;position:relative;}
.erweima-text {color:#F3ECED;text-align:center;}
.search-box-left-input {width:260px;height:30px;margin:20px;line-height:30px;color:#FFF;font-weight:400;background:#333;border-radius:10px;}
.search-box-left-button {width:110px;height:28px;border-radius:5px;background:#FFF;margin:0 20px;}
.search-box-text {margin:10px 20px;color:white;line-height:24px;font-size:12px;font-weight:400;}
</style>

<script src="${pageContext.request.contextPath}/res/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script>
$(function(){
	
	$("#qb").click(function(e) {
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
<img src="${pageContext.request.contextPath}/res/img/bg1.png" />
<div class="search-box">
<div class="search-box-left">
<div>
<input class="search-box-left-input" name="scode" id="scode" type="text" maxlength="21" size="80" />
<input class="search-box-left-button" type="button"  id="qb" value="点击查询" />
<div>
	<label id="error" align="left"></label>
</div>
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
<img src="${pageContext.request.contextPath}/res/img/erweima1.png" />
<p class="erweima-text">*图示刮开即为防伪码</p>
</div>
</div>
</div>
</div>
</body>
</html>
