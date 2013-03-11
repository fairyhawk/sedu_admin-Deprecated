<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>统计管理>>应用统计</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					查询条件：
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<form name="feed" action="<%=contextPath%>/feed/appStat!appStatList.action" method="post">
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr>
												<td  width="10%" class="lists_tright">
													请选择时间:
											</td>
												<td  class="lists_tleft">
													<input type="text" name="queryAppStatCondition.startTime" id="startTime" value="${queryAppStatCondition.startTime != -1 ? queryAppStatCondition.startTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													到<input type="text" name="queryAppStatCondition.endTime" id="endTime" value="${queryAppStatCondition.endTime != -1 ? queryAppStatCondition.endTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													<input type="submit" value="查询" />
												</td>
											</tr>
					</table>
				</form>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<font class="lists_fright">
					<a href="<%=contextPath%>/feed/appStat!downStatisticsData.action">导出数据</a>
				</font>
					<tr class="lists_infobg">
						<td>
							应用名称
						</td>
						<td>
							点击次数
						</td>
						<td>
							使用人数
						</td>
					</tr>
				<s:iterator value="appStatList" var="item">
					<tr>
						<td>
							<s:property value="#item.name"/>
						</td>
						<td>
							<s:property value="#item.clickNum"/>
						</td>
						<td>
							<s:property value="#item.useUserNum"/>
						</td>
					</tr>
				</s:iterator>
				</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>