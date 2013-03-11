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
<script type="text/javascript">
    function submitForm(form){
        form.submit();
    }
</script>
<body>
<table border="1" width="50%" height="1%">
    <tr>
        <td>id</td>
        <td>name</td>
    </tr>
    <tr>
        <td>
            <s:select list="testSsiVOList" listKey="id" listValue="name" id="testSsiVO.id" name="testSsiVO.id"/>
        </td>
        <td>&nbsp;</td>
    </tr>
    <s:iterator id="ssiIte" status="i" value="testSsiVOList">
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
</table>
<a href="<s:url action="randomCode"><s:param name="ttt" value="11"/></s:url>">tt</a><s:url />
<s:form action="test" onsubmit="return submitForm();">
    <s:submit value="submit"/>
</s:form>
</body>
</html>