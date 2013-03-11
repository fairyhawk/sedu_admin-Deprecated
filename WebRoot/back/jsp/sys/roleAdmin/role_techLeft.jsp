<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>角色列表</title>
		<link href="<%=contextPath%>/back/style/leftframe.css" rel="stylesheet" type="text/css" />
		<link href="<%=contextPath%>/back/style/style.css" rel="stylesheet" type="text/css" />
	</head>
<body>
<div id="leftframe">
<div class="TreeMenu" id="CategoryTree">
<table border="0" width="90%">
<s:hidden name="role.roleId"/>
        <tr>
            <td width="30%" valign="top">
                <table border="0" width="100%">
                    <s:iterator value="roleList" id="role" status="i">
                        <tr>
                            <td bgcolor="<s:if test="roleId==role.roleId">red<s:else>white</s:else></s:if>">
                                <a href="<s:url value="/sys/roleAdmin!teacherRoleList.action?role.roleId=%{roleId}"/>" target="rightFrame"><s:property
                                        value="roleName"/></a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </td>
        </tr>
</table>
</div>
</div>
</body>
</html>