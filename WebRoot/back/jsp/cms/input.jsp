<%--
  Created by IntelliJ IDEA.
  UserVO: guoqiang.liu
  Date: 2009-3-21
  Time: 11:07:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head><title>Simple jsp page</title></head>
  <body>
    <s:form action="creatColumn!createNewColumn" method="post">
        <s:hidden name="tempColumn.parentId" value="1"/>
        <s:textfield name="tempColumn.columnName"/><br>
        <s:textfield name="tempColumn.explanation"/><br>
        <s:textfield name="tempColumn.catalog"/><br>
        <s:textfield name="tempColumn.meta"/><br>
        <s:textfield name="tempColumn.indexTemplateId"/><br>
        <s:textfield name="tempColumn.articleTemplateId"/><br>
        <s:textfield name="tempColumn.sequence"/><br>
        <s:textfield name="tempColumn.isFinally"/><br>
        <s:textfield name="tempColumn.linkUrl"/><br>
        <s:textfield name="tempColumn.columnType"/><br>
        <s:submit value="提交"/>
    </s:form>
  </body>
</html>