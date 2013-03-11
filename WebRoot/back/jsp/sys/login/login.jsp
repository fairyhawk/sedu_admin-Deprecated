<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录远程平台管理系统后台</title>
<link rel="stylesheet" href="<%=contextPath %>/back/style/screen.css" type="text/css" media="screen" title="default" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
	background:#02609c;
}
.mg{color: #ff9;font-size: 18px;font-weight: bolder;height:40px;line-height:40px;text-align: center; position:absolute; top:0; left:0; width:100%;}
-->
</style>

<!--  jquery core -->
<script src="<%=contextPath%>/back/script//jquery/jquery-1.4.1.min.js" type="text/javascript"></script>

<!-- Custom jquery scripts -->
<script src="<%=contextPath%>/back/script//jquery/custom_jquery.js" type="text/javascript"></script>

<!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
<script src="<%=contextPath%>/back/script//jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
$(document).pngFix( );
});

</script>
<script type="text/JavaScript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
	function enterSubmit(event) {
		var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;   
        if (keyCode == 13) {   
        	document.loginForm.submit();
        }
	}
//-->
</script>
</head>
<body id="login-bg"> 
 <div class="mg">再次声明销售人员请登录：<a href="http://ad.highso.cn:8081">http://ad.highso.cn:8081</a></div>
<!-- Start: login-holder -->
<div id="login-holder">

	<!-- start logo -->
	<div id="logo-login">
		<div class="logo"></div>
	</div>
	<!-- end logo -->
	
	<div class="clear"></div>
	
	<!--  start loginbox ................................................................................. -->
	<div id="loginbox">
	
	<!--  start login-inner -->
	<div id="login-inner">
	 <form action="<%=contextPath%>/sys/backLogin!login.action" name="loginForm" method="post" style="margin: 0px;">
		<table border="0" cellpadding="0" cellspacing="0">
		 <tr>
            <td colspan="3" ><div align="center"><font style="height:1;font-size:9pt; color:#bfdbeb;filter:glow(color=#1070a3,strength=1)"><s:actionerror cssStyle="color:red;" /></font>
</div></td>
          </tr>
		<tr>
			<th>用 &nbsp; 户</th>
			<td colspan="2" class="login-td"><input type="text" name="user.loginName"   class="login-inp" ></td>
		</tr>
		<tr>
			<th>密 &nbsp; 码</th>
			<td colspan="2" class="login-td"><input onkeyup="enterSubmit(event)" type="password" name="user.loginPwd"  class="login-inp" ></td>
		</tr>
        <tr>
			<th>验证码</th>
			<td class="login-td login-img"><input onkeyup="enterSubmit(event)" type="text" name="randomCode"  class="login-inp" style="width:94px;" maxlength="4"></td>
			<td>&nbsp; <img src="<%=contextPath%>/util/randomCode.action" alt="验证码，点击图片更换" onclick="this.src='<%=contextPath%>/util/randomCode.action?random='+Math.random();" style="cursor:pointer; border:1px  solid #999 ; margin-top:4px; "/></td>
		</tr>
		<tr>
			<th></th>
			<!--<td colspan="2" valign="top"><input type="checkbox" class="checkbox-size" id="login-check" /><label for="login-check">记住密码</label></td>-->
		</tr>
		<tr>
			<th></th>
			<td colspan="2"><input type="submit" checked="checked" class="submit-login" value="登 录"/> &nbsp; <input type="reset" checked="checked" class="submit-login" value="重 置"/></td>
		</tr>
		</table>
	  </form>
	</div>
 	<!--  end login-inner -->
	<div class="clear"></div>

 </div>
 <!--  end loginbox -->
 
</div>
<!-- End: login-holder -->
</body>
</html>

