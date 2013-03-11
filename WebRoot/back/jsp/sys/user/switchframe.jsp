<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>显示/隐藏左侧导航栏</title>
<style type="text/css">
<!--
body {
	background-color: #F2F2F2;
}
*{
	padding: 0;
	margin: 0;
}
a, a:hover{
	outline: none;
}
img{
	border: none;
}
#switchpic {
	width: 6px;
	cursor:pointer;
	clear: both;
	vertical-align: bottom;
	margin-top: 220px;
}
-->
</style>
<link href="<%=contextPath %>/back/css/style.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript">
function Submit_onclick(){
	if(parent.myFrame.cols == "206,6,*") {
		parent.myFrame.cols="0,10,*";
		document.getElementById("ImgArrow").src="<%=contextPath %>/images/switch_right.gif";
		document.getElementById("ImgArrow").alt="打开左侧导航栏";
	} else {
		parent.myFrame.cols="206,6,*";
		document.getElementById("ImgArrow").src="<%=contextPath %>/images/switch_left.gif";
		document.getElementById("ImgArrow").alt="隐藏左侧导航栏";
	}
}

function MyLoad() {
	if(window.parent.location.href.indexOf("MainUrl")>0) {
		window.top.midFrame.document.getElementById("ImgArrow").src="<%=contextPath %>/images/switch_right.gif";
	}
}
</script>
<body onload="MyLoad()">
<div id="switchpic"><a href="javascript:Submit_onclick()"><img src="<%=contextPath %>/images/switch_left.gif" alt="隐藏左侧导航栏" id="ImgArrow" /></a></div>
</body>
</html>
