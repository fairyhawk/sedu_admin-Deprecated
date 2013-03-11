<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>项目列表</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
<script language="JavaScript">
</script>
</head>
<body>
<div id="right-content">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
<tr>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
	<td class="lists_top"><font class="lists_fleft">模块列表</font></td>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
</tr>
<tr>
	<td width="12" class="lists_bor"></td>
	<td>
		<div class="msg-xt">
			<s:form namespace="/sys" action="model!getModelList" method="get">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td class="lists_fright">模块名称：</td>
						<td>
							<s:textfield name="queryModelCondition.model_name" />
							<s:hidden name="queryModelCondition.currentPage" />
							<input type="submit" value="搜索" />
						</td>
						<td class="lists_fright"><a href="<%=contextPath%>/sys/model!toCreateModel.action">新建模块</a></td>
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
				<td>编号</td>
				<td>模块名称</td>
				<td>模块URL地址</td>
				<td>备注</td>
				<td>创建时间</td>
				<td>创建人</td>
				<td>操作</td>
			</tr>
			<s:iterator value="page.pageResult" var="model">
			<tr>
				<td>${model.id}</td>
				<td>${model.model_name}</td>
				<td>${model.url}</td>
				<td>${model.remark}</td>
				<td>${model.author}</td>
				<td>${model.create_time}</td>
				<td>
					<a href="<%=contextPath%>/sys/model!toEditModel.action?modelId=${model.id}">编辑</a>|<a onClick="return confirm('是否删除当前模块？');" href="<%=contextPath%>/sys/model!deleteModelByID.action?modelId=${model.id}">删除</a>
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
