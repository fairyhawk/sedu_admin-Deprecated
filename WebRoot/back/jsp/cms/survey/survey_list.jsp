<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加调查问卷</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" />
<script type="text/javascript">
	function addSurvey() {
		window.location = "<%=contextPath %>/cms/sq!initAddSurvey.action";
	}
	
	function updateSurvey(createTime) {
		window.location = "<%=contextPath %>/cms/sq!initUpdateSurvey.action?querySurveyQstCondition.createTime=" + createTime;
	}
	
	function genericSurvey(createTime) {
		window.location = "<%=contextPath %>/cms/sq!genericSurvey.action?querySurveyQstCondition.createTime=" + createTime;
	}
</script>
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">问卷列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:addSurvey()">添加</a></td></tr></table>
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td  class="lists_bor" width="12">
			<img src="<%=contextPath%>/back/images/tab_17.gif" />
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td width="35%">问卷名称</td>
						<td width="40%">创建时间</td>
						<td width="25%">操作</td>
					</tr>
					<s:iterator value="page.pageResult" id="surveyQst" status="status">
						<tr>
							<td>
								<s:property value="surveyName" />
							</td>
							<td>
								<s:date format="yyyy-MM-dd HH:mm:ss" name="createTime"/>
							</td>
							<td>
								<a href="javascript:updateSurvey('<s:date format="yyyy-MM-dd HH:mm:ss" name="createTime"/>')">修改</a>
								<a href="javascript:genericSurvey('<s:date format="yyyy-MM-dd HH:mm:ss" name="createTime"/>')">生成问卷</a>
							</td>
						</tr>
					</s:iterator>
			</table>
			</td>
			<td width="16" class="lists_tright">
			<img src="<%=contextPath%>/back/images/tab_17.gif" />
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>
