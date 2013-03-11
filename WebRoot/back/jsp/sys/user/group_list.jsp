<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head><title>超级管理员列表</title>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
</head>
<body>
<table border="1" width="50%" height="1%" onmouseover="changeto()" onmouseout="changeback()">
    <tr>
        <td>组名称</td>
        <td>状态</td>
    </tr>
    <s:iterator value="groupList">
        <tr>
            <td>
                <s:property value="groupName"/>
            </td>
            <td>
                <s:property value="status"/>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>