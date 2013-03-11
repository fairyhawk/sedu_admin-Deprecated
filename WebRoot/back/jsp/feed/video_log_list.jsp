<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>视频日志列表</title>
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
			<font class="lists_fleft">视频日志列表</font>
			<font class="lists_fright"></font></td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr class="lists_infobg">
							<td>视频ID</td>
							<td>视频名称</td>
							<td>发送次数</td>
							<td>激活次数</td>
							<td>观看次数</td>
							<td>点击下单数</td>
							<td>支付成功数</td>
							<td>发送激活比</td>
							<td>激活观看比</td>
						</tr>
						<s:iterator value="page.pageResult">
							<tr>
								<td>
									<s:property value="videoId" />
								</td>
								<td>
									<s:property value="videoName" />
								</td>
								<td>
									<s:property value="total" />
								</td>
								<td>
									<s:property value="activeNum" />
								</td>
								<td>
									<s:property value="clickNum" />
								</td>
								<td>
									<s:property value="clickBuyNum" />
								</td>
								<td>
									<s:property value="buyNum" />
								</td>
								<td>
									<s:property value="@com.shangde.edu.feed.utils.Utils@percentage(activeNum,total,1)"/>%
								</td>
								<td>
									<s:property value="@com.shangde.edu.feed.utils.Utils@percentage(clickNum,total,1)"/>%
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
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>