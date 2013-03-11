<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改广告</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
			<span class="lists_fleft" style="padding-top: 6px;">修改广告</span>
			<font class="lists_fright"></font></td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<s:form namespace="feed" action="/feed/ad!doUpdate.action" method="post" name="addForm" id="addForm">
					<input type="hidden" name="ad.id" value="<s:property value="ad.id"/>" />
					<input type="hidden" name="ad.status" value="<s:property value="ad.status"/>" />
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
									<tr>
										<td>
											广告名称：
										</td>
										<td class="lists_tleft">
											<input id="name" type="text" name="ad.name"  size="30" value="<s:property value="ad.name"/>"/>
										</td>
									</tr>
									<tr>
										<td>
											备注：
										</td>
										<td class="lists_tleft">
											<textarea  name="ad.remark" id="remark" cols="80" rows="4"><s:property value="ad.remark"/></textarea>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<input type="submit" value="修  改" />
										</td>
									</tr>
								</table>
					</s:form>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom"></td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>