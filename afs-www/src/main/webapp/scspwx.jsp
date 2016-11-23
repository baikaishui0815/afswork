<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>防伪查询系统</title>
<style type="text/css"> 
body {background:#ffffff; margin:0;}
a{text-decoration:none;cursor: pointer;}
img{border:0;}
.m-banner img{ display:block; width:100%}
.m-form{ padding:0; margin:0;}
.m-form li{ list-style:none;}
.m-form{ padding-top:30px; text-align:center; margin-bottom:30px;}
.m-form .t-text{ width:70%;   padding:10px 5px; background:#f2f2f2; border:1px solid #999999; font-size:14px;}
.m-form li{ margin-bottom:20px;}
.m-form .t-submit{ font-size:16px; color:#fff; background:#065989; padding:10px 20px; border-radius:5px; border:none}
.m-text{ width:80%; font-size:13px; margin:0 auto; line-height:20px;}
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
<div class="m-banner"><img src="${pageContext.request.contextPath}/res/scspimg/m-banner.jpg"></div>
<form>
  <ul class="m-form">
  <li><input name="scode" id="scode" type="text" placeholder="请依次输入标签涂层内的防伪数码" class="t-text" /></li>
  <li><input id="qb" type="button" class="t-submit" value="点击查询" /></li>
  </ul> 
</form>
<div class="m-text">
<div>
	<label id="error" align="left"></label>
</div>
<p>*请按产品上的防伪标识提示，在查询验证框中依次
 输入防伪编码，全部输完后点击查询按钮即可马上
 识别购买产品的真伪。</p>
<p>*如查询结果为非正品，烦请向本微信举报购买商家，
 维护自己的权益!</p>
 </div>
</body>
</html>
