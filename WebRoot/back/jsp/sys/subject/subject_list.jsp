<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>项目列表</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
</head>
<body>
<div id="right-content">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
<tr>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
	<td class="lists_top"><font class="lists_fleft">项目列表</font></td>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
</tr>
<tr>
	<td width="12" class="lists_bor"></td>
	<td>
		<div class="msg-xt">
			<s:form namespace="/sys" action="subject!querySubject" method="get">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td class="lists_fright">专业名称：</td>
						<td>
							<s:textfield name="querySubjectConditionAdv.subjectName" />
							<s:hidden name="querySubjectConditionAdv.currentPage" />
							<input type="submit" value="搜索" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</td>
	<td width="16" class="lists_tright lists_bor2"></td>
</tr>
<tr>
	<td width="12" class="lists_bor"></td>
	<td>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
			<tr class="lists_infobg">
				<td width="10%">专业ID</td>
				<td width="15%">专业名称</td>
				<td width="10%">状态</td>
				<td width="15%">创建时间</td>
				<td width="15%">修改时间</td>
				<td width="15%">测试用时</td>
				<td width="15%">配置课程</td>
				<td width="10%">操作</td>
			</tr>
			<s:iterator value="page.pageResult" var="subject">
			<tr>
				<td>${subject.subjectId}</td>
				<td>${subject.subjectName}</td>
				<td>
					<s:if test="#subject.status == 0">默认</s:if>
					<s:elseif test="#subject.status == 1">冻结</s:elseif>
					<s:elseif test="#subject.status == 2">删除</s:elseif>
				</td>
				<td><s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/></td>
				<td><s:date name="updateTime" format="yyyy-MM-dd hh:mm:ss"/></td>
				<td><s:date name="testTime" format="yyyy-MM-dd hh:mm:ss"/></td>
					<td>${subject.freeClasses}</td>
				<td>
					<s:a namespace="/sys" action="subject" method="updateSubjectInit">
						<s:param name="subjectId" value="subjectId" />编辑
					</s:a>
				</td>
			</tr>
			</s:iterator>
		</table>
	</td>
	<td width="16" class="lists_tright lists_bor2"></td>
</tr>
<tr>
	<td><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
	<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
	<td><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
</tr>
</table>
</div>
</body>
</html>
