<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>防伪查询系统</title>
<style type="text/css"> 
body {background:#ffffff; margin:0; color:#333; font-family:"微软雅黑"}
a{text-decoration:none;cursor: pointer;}
img{border:0;}
.pc-logo{ padding:38px 0; text-align:center;}
.pc-banner{ height:395px; background:url(${pageContext.request.contextPath}/res/scspimg/pc-banner.jpg) no-repeat center top;}
.pc-form{ text-align:center; padding:30px 0}
.pc-form input{ vertical-align:middle;}
.pc-form .t-text{ padding:10px; background:#fff; border:1px solid #0044d3; width:190px; font-size:13px; color:#333;font-family:"微软雅黑"}
.pc-form .t-submit{ height:37px; background:url(${pageContext.request.contextPath}/res/scspimg/pc-btn.gif) repeat-x; border:none; width:90px; color:#fff; font-size:14px; cursor:pointer; margin-left:20px;}
.pc-txt{ width:710px; margin:0 auto; font-size:12px; line-height:20px;}
.pc-img{ text-align:center; padding:65px 0}
.pc-img span{ display:inline-block; *display:inline;*zoom:1; vertical-align:middle; margin:0 50px;}
.pc-foot{ text-align:center; font-size:14px; padding-bottom:20px;}
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
<div class="pc-logo"><img src="${pageContext.request.contextPath}/res/scspimg/pc-logo.png"></div>
<div class="pc-banner"></div>
<form>
  <div class="pc-form">
  <input type="text" placeholder="请输入防伪密码" class="t-text"  name="scode" id="scode"/>
  <input name="" type="button" class="t-submit" id="qb" value="查询" />
  </div> 
  <div class="pc-txt">
	<label id="error" align="left"></label>
</div>
</form>
<div class="pc-txt">
*请按产品上的防伪标识提示，在查询验证框中依次输入防伪编码，全部输完后回车或点击查询按钮就可识别产品真伪。<br>
*如查询输入的防伪编码为正确的，系统提示为：您所查询的产品为吉林省上层上品装饰材料有限公司生产的正牌产品，请放心使用！<br>
 如查询输入的防伪编码为错误的，系统提示为：您输入的防伪编码有误，谨防假冒！<br>
 如查询输入的防伪编码已被查询，系统提示为：该产品已于某年某月某日被查询过，谨防假冒！
</div>

<div class="pc-img">
<span><img src="${pageContext.request.contextPath}/res/scspimg/pc-im1.gif"></span>
<span><img src="${pageContext.request.contextPath}/res/scspimg/pc-im2.gif"></span>
</div>
<div class="pc-foot">*注：图示刮开后数码即为防伪密码</div>
</body>
</html>
