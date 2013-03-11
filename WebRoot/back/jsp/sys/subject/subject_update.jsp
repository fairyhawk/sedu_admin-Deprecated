<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>编辑项目</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
</head>
<body>
<div id="right-content">
<s:form namespace="/sys" action="subject!updateSubject">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
	<tr>
		<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
		<td class="lists_top"><font class="lists_fleft">编辑项目</font></td>
		<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
	</tr>
	<tr>
		<td width="12" class="lists_bor"></td>
		<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td class="lists_tright" width="20%">项目名称：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.subjectName" /></td>
				</tr>
				<tr>
					<td class="lists_tright" width="20%">课程配置：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.freeClasses" /><span style="font-family:宋体;color:red">多个课程以逗号隔开</span></td>
				</tr>
				<tr>
					<td class="lists_tright">状态：</td>
					<td class="lists_tleft">&nbsp;<s:select name="subject.status" list="#{'0':'默认', '1':'冻结', '2':'删除'}" /></td>
				</tr>
				<tr>
					<td class="lists_tright">400服务热线：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.hotline" /><span style="font-family:宋体;color:red">XXXX-XXX-XXX</span></td>
				</tr>
				<tr>
					<td class="lists_tright">课程咨询服务人员姓名：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.courseConsultName" /></td>
				</tr>
				<tr>
					<td class="lists_tright">课程咨询服务号码：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.courseConsultNumber" /></td>
				</tr>
				<tr>
					<td class="lists_tright">售后咨询服务人员姓名：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.customerServiceName" /></td>
				</tr>
				<tr>
					<td class="lists_tright">售后咨询服务号码：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.customerServiceNumber" /></td>
				</tr>
				<tr>
					<td class="lists_tright">投诉建议服务人员名称：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.complaintServiceName" /></td>
				</tr>
				<tr>
					<td class="lists_tright">投诉建议服务号码：</td>
					<td class="lists_tleft">&nbsp;<s:textfield name="subject.complaintServiceNumber" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<s:hidden name="subject.subjectId"/>
						<input type="submit" value="确定" />
						<input type="button" value="取消" onclick="window.history.back();" />
					</td>
				</tr>
			</table>
		</td>
		<td width="16" class="lists_tright lists_bor2"></td>
	</tr>
</table>
</s:form>
</div>
</body>
</html>