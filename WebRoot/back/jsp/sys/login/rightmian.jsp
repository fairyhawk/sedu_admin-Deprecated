<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无题文档</title>
<link rel="stylesheet" href="../back/style/screen.css" type="text/css" media="screen" title="default" />
<style>


</style>
</head>

<body>
 <!-- start right-content -->

  <div class="cont">
    <h1>所有功能</h1>
    <div class="other">
      <ul id="dashboard-buttons">
      	 <s:iterator value="#session['tabUserFunctionList']" status="status" id="tabList">
      	 <s:if test="#tabList[0].functionId==269">
		 <a href="<%=contextPath %>/back/jsp/sys/login/leftframe.jsp?functionId=<s:property value="#tabList[0].functionId"/>" target="leftFrame">
        <li class="a" ><div class="img"><img src="../back/images/Icon/ico_03.gif" alt="" title="" /></div> <div class="info">系统管理<p>技术负责人：刘庆刚</p></div> </li></a>
        </s:if>
         <s:if test="#tabList[0].functionId==260">
		 <a href="<%=contextPath %>/back/jsp/sys/login/leftframe.jsp?functionId=<s:property value="#tabList[0].functionId"/>" target="leftFrame">
        <li class="b"><div class="img"><img src="../back/images/Icon/ico_05.gif" alt="" title="" /></div> <div class="info">资源管理<p>技术负责人：刘庆刚，何海强，谢金龙</p></div> </li></a>
        </s:if>
         <s:if test="#tabList[0].functionId==268">
		 <a href="<%=contextPath %>/back/jsp/sys/login/leftframe.jsp?functionId=<s:property value="#tabList[0].functionId"/>" target="leftFrame">
        <li class="c"><div class="img"><img src="../back/images/Icon/ico_02.gif" alt="" title="" /></div> <div class="info">业务管理<p>技术负责人：谢金龙，刘佰晟</p></div> </li></a>
        </s:if>
         <s:if test="#tabList[0].functionId==274">
		 <a href="<%=contextPath %>/back/jsp/sys/login/leftframe.jsp?functionId=<s:property value="#tabList[0].functionId"/>" target="leftFrame">
        <li class="d"><div class="img"><img src="../back/images/Icon/ico_04.gif" alt="" title="" /></div> <div class="info">财务管理<p>技术负责人：谢金龙，刘庆刚</p></div></li></a>
        </s:if>
         <s:if test="#tabList[0].functionId==279">
		 <a href="<%=contextPath %>/back/jsp/sys/login/leftframe.jsp?functionId=<s:property value="#tabList[0].functionId"/>" target="leftFrame">
        <li class="e"><div class="img"><img src="../back/images/Icon/ico_01.gif" alt="" title="" /></div> <div class="info">App管理<p>技术负责人：何海强，刘佰晟</p></div></li></a>
        </s:if>
        </s:iterator>
        </li>
      </ul>
      <div class="clearfix"></div>
    </div>
    </div>
 
  <!--  end right-content -->
</body>
</html>