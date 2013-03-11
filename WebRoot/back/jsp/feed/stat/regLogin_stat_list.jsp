<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>注册/登录统计</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
	</head>
	<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">
					注册/登录统计
				</font>
				<font class="lists_fright">
					&nbsp;
				</font>
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<!-- 这里增加一行包含文件 -->
		<jsp:include page="stat_head.jsp" flush="true"></jsp:include>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							注册点击数
						</td>
						<td>
							注册人数
						</td>
						<td>
							登录点击数
						</td>
						<td>
							登录人数
						</td>
					</tr>
					<tr>
						<td>
							<s:property value="regClickCount" />
						</td>
						<td>
							<s:property value="regCount" />
						</td>
						<td>
							<s:property value="loginClickCount" />
						</td>
						<td>
							<s:property value="loginCount" />
						</td>
					</tr>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<!-- 这里展示根据不同来源渠道查询，进行显示 -->
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr>
						<s:iterator value="commonList" var="item" status="status">
							<td>
								<s:property value="#item"/>
							</td>
							<s:if test="#status.count % 4 == 0">
								</tr>
								<tr>
							</s:if>
						</s:iterator>
					</tr>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
			<tr>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_18.gif" />
				</td>
				<td class="lists_bottom">
					<jsp:include page="/back/jsp/common/showPage.jsp" />
				</td>
				<td class="td_wid_r">
					<img src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
	</table>
</div>
	</body>
</html>

