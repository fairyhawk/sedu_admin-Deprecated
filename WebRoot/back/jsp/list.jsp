<%--
  struts2功能测试
  UserVO: guoqiang.liu
  Date: 2008-12-6
  Time: 11:38:32
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<table border="1" width="50%" height="1%">
    <tr>
        <td>id</td>
        <td>name</td>
    </tr>
    <s:iterator id="pageIte" status="i" value="pageVO.pageResult">
        <tr>
            <td width="60%">
                <s:property value="id"/>
            </td>
            <td>
                <s:property value="name"/>
            </td>
        </tr>
    </s:iterator>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td colspan="10"><jsp:include page="common/showPage.jsp"/></td>
    </tr>
</table>
<a href="<s:url action="randomCode"><s:param name="ttt" value="11"/></s:url>">tt</a><s:url />
${UrlParms}<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1<br>1
</body>
</html>