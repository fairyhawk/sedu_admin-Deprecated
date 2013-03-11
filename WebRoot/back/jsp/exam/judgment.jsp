<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/header.inc"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'judgment.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    评定页面得分${totalScroe }
    <br/>
    ${scoreJudgment }
    <br/>
    <table>
    <s:if test="wrongQsts.size>0">
    <tr>
    	<th>错题记录</th>
    </tr>
    </s:if>
    <s:iterator id="qst" value="wrongQsts">
    <tr>
				<td   align="left">
					<s:property value="#count.index+1"/>.&nbsp;&nbsp;&nbsp;
					<s:property value="#qst.qstContent"/>
				</td>
			</tr>
			<s:iterator value="#qst.options" id="option">
			<tr >
				<td   align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<s:property value="#option.optOrder"/>
					<s:property value="#option.optContent"/>
				</td>
			</tr>
			
			</s:iterator>
			<tr>
				<td><span>您刚刚选择了${qst.wrongAsr }&nbsp;&nbsp;&nbsp;&nbsp;正确答案为：${qst.isAsr }</span></td>
			</tr>
			<tr>
				<td>问题解析:${qst.wrongJude }</td>
			</tr>
	
    </s:iterator>
    </table>
  </body>
</html>
