<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/include/header.inc"%>
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
	<link rel="StyleSheet" href="<%=contextPath%>/back/script/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/dtree/dtree.js"></script>
	<script type="text/javascript">
		function reloadCourseList(){
			window.location.href ="<%=contextPath%>/feed/course!goCourseVideoTreePage.action?id=${id}";
		}
		/**激活后，n秒钟后出发刷新*/
		function acvReload(){
			setTimeout("reloadCourseList()",2000);
		}
	</script>
  </head>
  
  <body>
  		<!-- 树结构 -->
   		${courseTree}
  </body>
</html>
