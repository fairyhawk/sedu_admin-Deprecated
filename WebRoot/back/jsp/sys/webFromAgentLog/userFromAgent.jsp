<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>学员推广查询</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript">
		function checkSubmit() {
			this.isBlank = function(str) {
				return str == null || str == '' || $.trim(str) == '';
			}
			var startTime = $('#startTime').val();
			var endTime = $('#endTime').val();
			var startHH = $('#startHH').val();
			var endHH = $('#endHH').val();
			var sTime = startTime + ' ' + startHH;
			var eTime = endTime + ' ' + endHH;
			if (!this.isBlank(startTime) && !this.isBlank(endTime) && sTime > eTime) {
				alert('开始时间不能大于结束时间，请重新输入！');
				return false;
			}
			return true;
		}
		function clearForm() {
			$('#startTime').val('');
			$('#endTime').val('');
			$('#webFrom').val('');
			$('#webAgent').val('');
			$('#mobile').val('');
			$('#email').val('');
			$('#currentPage').val('1');
			$('#webFromAgentLogForm select').each(function() {
				$($(this).find('option')[0]).attr('selected', 'selected');
			});
			$('#endHH').val('23:59:59');
		}
		function userWebFromExportExcel() {
			if(checkSubmit()) {
				$('#exportExcel').val('true');
				document.getElementById('webFromAgentLogForm').submit();
				$('#exportExcel').val('false');
			}
		}
	</script>
</head>
<body>
<div id="right-content">
<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
<tr>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
	<td class="lists_top"><font class="lists_fleft">学员推广查询</font></td>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
</tr>
<tr>
	<td width="12" class="lists_bor"></td>
	<td>
		<div class="msg-xt">
			<s:form action="webFromAgentLog!queryUserWebFrom" namespace="/sys" method="get" id="webFromAgentLogForm" onsubmit="return checkSubmit();">
				<table class="lists" border="0" cellpadding="0" cellspacing="0" width="70%" align="center">
					<tr>
						<td>开始时间：&nbsp;<input type="text" name="queryUserWebFromCondition.startTime" id="startTime" onclick="WdatePicker();" readonly="readonly" value='<s:date format="yyyy-MM-dd" name="queryUserWebFromCondition.startTime" />' />
						<s:select list="hourSelect" name="queryUserWebFromCondition.startHH" id="startHH" />(时:分:秒)</td>
						<td>结束时间：&nbsp;<input type="text" name="queryUserWebFromCondition.endTime" id="endTime" onclick="WdatePicker();" readonly="readonly" value='<s:date format="yyyy-MM-dd" name="queryUserWebFromCondition.endTime" />' />
						<s:select list="hourSelect" name="queryUserWebFromCondition.endHH" id="endHH" />(时:分:秒)</td>
					</tr>
					<tr>
						<td>推广来源：&nbsp;<s:textfield name="queryUserWebFromCondition.webFrom" id="webFrom"/></td>
						<td>站长ID：&nbsp;<s:textfield name="queryUserWebFromCondition.webAgent" id="webAgent"/></td>
					</tr>
					<tr>
						<td>注册项目：&nbsp;<s:select list="subjectList" name="queryUserWebFromCondition.subjectId" listKey="subjectId" listValue="subjectName" id="subjectId" headerKey="" headerValue="---请选择---" /></td>
						<td>手机号码：&nbsp;<s:textfield name="queryUserWebFromCondition.mobile" id="mobile"/></td>
					</tr>
					<tr>
						<td>用户Email：&nbsp;<s:textfield name="queryUserWebFromCondition.email" id="email"/></td>
						<td>
							<input type="hidden" name="queryUserWebFromCondition.currentPage" id="currentPage" value="1" />
							<input type="hidden" name="queryUserWebFromCondition.exportExcel" id="exportExcel" value="false" />
							<input type="submit" value="搜索" />
							<input type="button" value="清空" onclick="clearForm();" />
							<input type="button" value="导出" onclick="userWebFromExportExcel();" />
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
				<td width="5%">序号</td>
				<td width="15%">手机号</td>
				<td width="20%">注册Email</td>
				<td width="15%">注册路径</td>
				<td width="10%">推广来源</td>
				<td width="10%">站长ID</td>
				<td width="15%">注册时间</td>
				<td width="10%">支付数/订单数</td>
			</tr>
			<s:iterator value="page.pageResult" var="cus" status="sta">
			<tr>
				<td>${sta.count + queryUserWebFromCondition.pageSize * (queryUserWebFromCondition.currentPage - 1)}</td>
				<td>${cus.mobile}</td>
				<td><a href="<%=request.getContextPath()%>/cus/cus!viewCus.action?customer.cusId=${cus.cusId}">${cus.email}</a></td>
				<td>${cus.subjectName}</td>
				<td>${cus.webFrom}</td>
				<td>${cus.webAgent}</td>
				<td><s:date format="yyyy-MM-dd hh:mm:ss" name="regDate" /></td>
				<td>${cus.payedRecordCount}/${cus.recordCount}</td>
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
