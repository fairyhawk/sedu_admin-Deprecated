<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!-- 新建任务 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
		<frameset cols="190,*" bordercolor="#FF0000" border="1">
			<frame src="<%=contextPath%>/feed/course!goCourseVideoTreePage.action" frameborder="0" noresize="noresize" name="leftMain">
			<frame src="<%=contextPath%>/feed/course!goVideoJumpPage.action?id=${param.id}" frameborder="0" noresize="noresize" name="rightMain">
		</frameset>
</html>