<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'chooseExampaper.jsp' starting page</title>
    
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

  	<s:iterator id="subject" value="subjectList">
  	<table>
  		
  		<tr>
  			<th>${subject.subjectName }</th>
  		</tr>
  		<s:iterator id="exampaper" value="#subject.exampapers">
  		<tr>
  			<td><a href="<%=contextPath%>/exam/qstManager!getExamPaper.action?epid=${exampaper.epId }">${exampaper.epName }</a></td>
  		</tr>
  		</s:iterator>
  	</table>
  	</s:iterator>
  </body>
</html>
