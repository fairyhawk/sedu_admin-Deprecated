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
    <s:form action="test!inputSave" method="post">
        <s:hidden name="pageQueryConditionVO.currentPage" value="1"/>
        <s:textfield name="testSsiVO.id"/><br>
        <s:textfield name="testSsiVO.name"/><br>
        <s:submit value="提交"/>
    </s:form>
  </body>
</html>