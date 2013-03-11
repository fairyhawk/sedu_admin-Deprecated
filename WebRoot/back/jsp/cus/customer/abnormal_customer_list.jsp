<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>异常用户统计</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
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
		var abnormalTimes = $('#abnormalTimes').val();
		var sTime = startTime + ' ' + startHH;
		var eTime = endTime + ' ' + endHH;
		if(startTime == '' || endTime == '') {
			alert('开始时间和结束时间不能为空！');
			return false;
		}
		if (!this.isBlank(startTime) && !this.isBlank(endTime) && sTime > eTime) {
			alert('开始时间不能大于结束时间，请重新输入！');
			return false;
		}
		if (abnormalTimes == '' || isNaN(abnormalTimes)) {
			alert('登录地点数量不能为空，且必须为整数！');
			return false;
		}
	}
	function changeFreezeStatus(cusId, freezeStatus) {
		var confirmMsg = '';
		var freezeCofirmMsg = '是否确定冻结该用户？';
		var unfreezeCofirmMsg = '是否确定解冻该用户？';
		if(freezeStatus == 0) {
			confirmMsg = freezeCofirmMsg;
		} else if (freezeStatus == 1) {
			confirmMsg = unfreezeCofirmMsg;
		}
		if (confirm(confirmMsg)) {
			location.href = '<%=contextPath%>/cus/abnormalCus!changeFreezeStatus.action?cusFreezeStatus.cusId=' + cusId + '&cusFreezeStatus.freezeStatus=' + freezeStatus;
		}
	}
	function showLoginDetial(cusId) {
		var sTime = $('#startTime').val() + ' ' + $('#startHH').val();
		var eTime = $('#endTime').val(); + ' ' + $('#endHH').val();
		var url = '<%=contextPath%>/cus/abnormalCus!showLoginDetial.action?queryLoginRecordCond.startTime=' + sTime + '&queryLoginRecordCond.endTime=' + eTime + '&queryLoginRecordCond.cusId=' + cusId;
		window.showModalDialog(url, null, 'center=1;resizable=1;status=0;scroll=1;dialogWidth=400px;dialogHeight=300px');
	}
	function showFreezeLogDetial(cusId) {
		var url = '<%=contextPath%>/cus/abnormalCus!showFreezeLogDetial.action?cusId=' + cusId;
		window.showModalDialog(url, null, 'center=1;resizable=1;status=0;scroll=1;dialogWidth=400px;dialogHeight=300px');
	}
	function sendUserMsg() {
		var url = '<%=contextPath%>/cus/abnormalCus!sendUserMsg.action';
		var ids_total = document.getElementsByName('sendMsgCusIds');
		var ids_send = new Array();
		var i;
		for (i = 0; i < ids_total.length; i++) {
			if (ids_total[i].checked == true) {
				ids_send.push(ids_total[i].value);
			}
		}
		if (ids_send != null && ids_send.length > 0) {
			url += '?sendMsgCusIds=' + ids_send[0];
			for (i = 1; i < ids_send.length; i++) {
				url += '&sendMsgCusIds=' + ids_send[i];
			}
			location.href = url;
		} else {
			alert('请先选择用户！');
			return false;
		}
	}
	function selectAll() {
		var a = document.getElementById('selectAll');
		var ids_total = document.getElementsByName('sendMsgCusIds');
		var i;
		if (a.checked == true) {
			for (i = 0; i < ids_total.length; i++) {
				ids_total[i].checked = true;
			}
		} else {
			for (i = 0; i < ids_total.length; i++) {
				ids_total[i].checked = false;
			}
		}
	}
</script>
</head>
<body>
<div id="right-content">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
<tr>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
	<td class="lists_top"><font class="lists_fleft">异常用户统计</font></td>
	<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
</tr>
<tr>
	<td width="12" class="lists_bor"></td>
	<td>
		<div class="msg-xt">
			<s:form namespace="/cus" action="abnormalCus!searchAbnormalCustomer" method="get" onsubmit="return checkSubmit();">
				<table class="lists" border="0" cellpadding="0" cellspacing="0" width="70%" align="center">
					<tr>
						<td>开始时间：&nbsp;<input type="text" name="queryAbnormalCusCond.startTime" id="startTime" onclick="WdatePicker()" readonly="readonly" value='<s:date format="yyyy-MM-dd" name="queryAbnormalCusCond.startTime" />' />
							<s:select list="hourSelect" name="queryAbnormalCusCond.startHH" id="startHH" /></td>
						<td>结束时间：&nbsp;<input type="text" name="queryAbnormalCusCond.endTime" id="endTime" onclick="WdatePicker()" readonly="readonly" value='<s:date format="yyyy-MM-dd" name="queryAbnormalCusCond.endTime" />' />
							<s:select list="hourSelect" name="queryAbnormalCusCond.endHH" id="endHH" /></td>
					</tr>
					<tr>
						<td>E-mail:&nbsp;<s:textfield name="queryAbnormalCusCond.email" id="email" /></td>
						<td>登录地点数量：<s:textfield name="queryAbnormalCusCond.abnormalTimes" id="abnormalTimes" /></td>
					</tr>
					<tr>
						<td></td>
						<td><s:hidden name="queryAbnormalCusCond.currentPage" id="currentPage" /><input type="submit" value="搜索" /></td>
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
				<td width="5%"><input type="checkbox" id="selectAll" onclick="selectAll();" /><label for="selectAll">全选</label></td>
				<td width="10%">昵称</td>
				<td width="15%">邮件</td>
				<td width="10%">手机</td>
				<td width="10%">是否冻结</td>
				<td width="10%">上次冻结/解冻时间</td>
				<td width="10%">登录地点数量</td>
				<td width="*">操作</td>
			</tr>
			<s:iterator value="page.pageResult" var="cus">
			<tr>
				<td><input type="checkbox" name="sendMsgCusIds" value="${cus.cusId}" /></td>
				<td>${cus.cusName}</td>
				<td>${cus.email}</td>
				<td>${cus.mobile}</td>
				<td><s:if test="#cus.freezeStatus == 0">已冻结</s:if><s:else>正常</s:else></td>
				<td><s:date name="freezeChangedDate" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${cus.abnormalLoginTimes}</td>
				<td>
				<s:if test="#cus.freezeStatus == 0">
					<a href="#" onclick="changeFreezeStatus(${cus.cusId}, 1);">解冻</a>
				</s:if>
				<s:else>
					<a href="#" onclick="changeFreezeStatus(${cus.cusId}, 0);">冻结</a>
				</s:else>
				<a href="#" onclick="showFreezeLogDetial(${cus.cusId});">查看操作记录</a>
				<a href="#" onclick="showLoginDetial(${cus.cusId});">查看登录日志</a></td>
			</tr>
			</s:iterator>
		</table>
		<s:if test="page.pageResult != null && page.pageResult.size > 0">
			<input type="button" value="站内信通知" onclick="sendUserMsg();" style="margin:5px 0;" />
		</s:if>
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