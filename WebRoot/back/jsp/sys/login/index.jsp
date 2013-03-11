<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>尚德网络教育平台-后台管理系统</title>
</head>
<frameset rows="130,*,7" cols="*" frameborder="no" border="0" framespacing="0">

	<frame src="backMain!topframe.action" name="topFrame" frameborder="no" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
	
	<frameset name="myFrame" cols="213,*" frameborder="no" border="0" framespacing="0">

<!-- 中间 -->

    	<frame src="backMain!leftframe.action" name="leftFrame" frameborder="no" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" > </frame>

		

		<frameset name="rightmyFrame" rows="30,*" frameborder="no" border="0" framespacing="0">
		<frame  src="<%=contextPath %>/back/jsp/sys/login/rightframe.jsp" frameborder="no"  noresize="noresize" />
        <frame src="backMain!rightframe.action" name="rightFrame" frameborder="no" scrolling="yes" noresize="noresize" id="rightFrame" title="rightFrame" >
		</frame>
		</frameset>

<!-- 中间 //-->
	</frameset>
    <frame src="bottom.html" name="bottomFrame" frameborder="no" scrolling="no" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>


<noframes>
<body></body>
</noframes>
</html>