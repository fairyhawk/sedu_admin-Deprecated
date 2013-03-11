<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<html>
<head>
<title>角色列表</title>
</head>
<frameset name="myFrame" cols="206,6,*" frameborder="no" border="0" framespacing="0">
    	<frame src="roleAdmin!toTeacherRoleLeft.action" name="leftFrame" frameborder="no" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    	<frame src="group!switchframe.action" name="midFrame" frameborder="no" scrolling="no" noresize="noresize" id="midFrame" title="midFrame" />
        <frame src="roleAdmin!teacherRoleList.action?queryUserCondition.currentPage=1&" name="rightFrame" frameborder="no" scrolling="yes" noresize="noresize" id="rolerightFrame" title="rolerightFrame" />
</frameset>
<noframes><body>
</body>
</noframes>
</html>