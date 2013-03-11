<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>项目推广统计</title>
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
			$('#currentPage').val('1');
			$('#webFromAgentLogForm select').each(function() {
				$($(this).find('option')[0]).attr('selected', 'selected');
			});
			$('#endHH').val('23:59:59');
			$('#webFrom').val('');
			$('#showOthers').attr('checked', false);
		}
		function queryWebFromLogExportExcel() {
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
	<td class="lists_top"><font class="lists_fleft">项目推广统计</font></td>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
</tr>
<tr>
	<td width="12" class="lists_bor"></td>
	<td>
		<div class="msg-xt">
			<s:form action="webFromAgentLog!queryWebFromLog" id="webFromAgentLogForm" namespace="/sys" method="get">
				<table class="lists" border="0" cellpadding="0" cellspacing="0" width="70%" align="center">
					<tr>
						<td>开始时间：&nbsp;<input type="text" name="queryWebFromAgentLogsCondition.startTime" id="startTime" onclick="WdatePicker()" readonly="readonly" value='<s:date format="yyyy-MM-dd" name="queryWebFromAgentLogsCondition.startTime" />' />
							<s:select list="hourSelect" name="queryWebFromAgentLogsCondition.startHH" id="startHH" /></td>
						<td>结束时间：&nbsp;<input type="text" name="queryWebFromAgentLogsCondition.endTime" id="endTime" onclick="WdatePicker()" readonly="readonly" value='<s:date format="yyyy-MM-dd" name="queryWebFromAgentLogsCondition.endTime" />' />
							<s:select list="hourSelect" name="queryWebFromAgentLogsCondition.endHH" id="endHH" /></td>
					</tr>
					<tr>
						<td>项目路径：&nbsp;<s:select list="subjectList" name="queryWebFromAgentLogsCondition.subjectId" listKey="subjectId" listValue="subjectName" id="subjectId" headerKey="-1" headerValue="---请选择---" /></td>
						<td>域名：&nbsp;<s:select list="domainMap" name="queryWebFromAgentLogsCondition.domain" id="domain" headerKey="" headerValue="---请选择---" /></td>
					</tr>
					<tr>
						<td>推广渠道：&nbsp;<s:textfield name="queryWebFromAgentLogsCondition.webFrom" id="webFrom" /></td>
						<td>显示其它：&nbsp;<s:checkbox name="queryWebFromAgentLogsCondition.showOthers" id="showOthers" /></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="hidden" name="queryWebFromAgentLogsCondition.currentPage" id="currentPage" value="1" />
							<input type="hidden" name="queryWebFromAgentLogsCondition.exportExcel" id="exportExcel" value="false" />
							<input type="submit" value="搜索" />
							<input type="button" value="清空" onclick="clearForm();" />
							<input type="button" value="导出" onclick="queryWebFromLogExportExcel();" />
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
				<td>推广渠道</td>
				<td>点击量</td>
				<td>注册量</td>
				<td>点击注册率</td>
				<td>总订单数</td>
				<td>总订单金额</td>
				<td>已付款订单数</td>
				<td>已付款订单金额</td>
			</tr>
			<s:iterator value="page.pageResult" var="sta">
				<tr>
					<td>${sta.webFrom}</td>
					<td>${sta.clickCount}</td>
					<td>${sta.registerCount}</td>
					<td>${sta.registerRate}</td>
					<td>${sta.contractCount}</td>
					<td>${sta.contractMoneySumStr}</td>
					<td>${sta.payedContractCount}</td>
					<td>${sta.payedContractMoneySumStr}</td>
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