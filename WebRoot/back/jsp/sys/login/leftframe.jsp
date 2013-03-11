<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ page buffer="1000kb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="StyleSheet" href="<%=contextPath%>/back/style/test_dtree.css" type="text/css" />
<link rel="StyleSheet" href="<%=contextPath%>/back/style/screen.css" type="text/css" />
<style type="text/css">
body{background: url("<%=contextPath%>/back/images/bg_left.jpg") repeat-y 0 0; width:210px;margin:0px; padding:0px;}
</style>
<script type="text/javascript" src="<%=contextPath%>/back/script/test_dtree.js"></script>
</head>

<body >
<DIV class="menu">
<!-- 
<DL>
  <DT><A onclick="showHide('items1');" href="#" target=_self>模板管理</A></DT>
  <DD id=items1 style="DISPLAY: block">
  <UL>
    <LI><A href="admin_templates.php" target=right>页面模板管理</A></LI>
   </UL>
   </DD>
</DL>
 -->
	<a id="hide_menu" href="javascript:;">&nbsp;</a>
	<script type="text/javascript">
	<s:if test="#parameters.functionId==null">
		<s:set id="rootId" scope="request" value="#session.tabUserFunctionList[0][0].functionId"></s:set>
	</s:if>
	<s:else>
		<s:set id="rootId" scope="request" value="#parameters.functionId"/>
	</s:else>
	<!--
	d = new dTree('d','<%=contextPath%>','table');
	<s:iterator value="#session['tabUserFunctionList']" status="status" id="tabList">
		<s:set id="tabRootId" value="#tabList[0].functionId" scope="request"/>
		<%
			String paramFunctionId = request.getParameter("functionId");
			String rootId = String.valueOf(request.getAttribute("rootId"));
			String tabRootId = String.valueOf(request.getAttribute("tabRootId"));
			if((paramFunctionId == null && rootId.equals(tabRootId)) || (paramFunctionId != null && paramFunctionId.equals(tabRootId)) ) {
		%>
			d.add(<s:property value="#tabList[0].functionId"/>,-1,'<s:property value="#tabList[0].functionName"/>','<s:property value="#tabList[0].functionUrl"/>','<s:property value="#tabList[0].functionName"/>','rightFrame','images/Icon/ico__l_03.png','images/Icon/ico__l_03.png');
			var images="";
			var zu="<s:property value="#tabList[0].functionId" />";
			if(zu==274){
			 images="<%=contextPath%>/back/images/Icon/ico_l_04.png";
			}
			if(zu==279){
			 images="<%=contextPath%>/back/images/Icon/ico_l_01.png";
			}
			if(zu==269){
			 images="<%=contextPath%>/back/images/Icon/ico__l_03.png";
			}
			if(zu==260){
			 images="<%=contextPath%>/back/images/Icon/ico__l_05.png";
			}
			if(zu==268){
			 images="<%=contextPath%>/back/images/Icon/ico__l_02.png";
			}
			<s:iterator value="#tabList">
				<s:if test="status!=0&&level!=0">
					<s:if test="functionTypeId==@com.shangde.edu.sys.domain.Function@FUNCTION_TYPE_ITEM">
						<s:if test="functionUrl!=''&&functionUrl!=null">
						d.add(<s:property value="functionId"/>,<s:property value="parentFunctionId"/>,'<s:property value="functionName"/>','<%=contextPath %><s:property value="functionUrl"/>','<s:property value="functionName"/>','rightFrame','','',false);
						</s:if>
						
						<s:if test="functionUrl==''||functionUrl==null">
						d.add(<s:property value="functionId"/>,<s:property value="parentFunctionId"/>,'<s:property value="functionName"/>','<s:property value="functionUrl"/>','<s:property value="functionName"/>','rightFrame',images,images,false);
						
						</s:if>
					</s:if>
				</s:if>
			</s:iterator>
			
		<%
			}
		%>
	</s:iterator>
	document.write(d);
	d.closeAll();
	</script>
</DIV>
</div>




</body>
</html>
