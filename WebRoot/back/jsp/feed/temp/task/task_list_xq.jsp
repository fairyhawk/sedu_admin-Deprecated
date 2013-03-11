<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>公告详情列表</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">公告详情列表</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr class="lists_infobg">
							<td>
								任务ID
							</td>
							<td>
								专业ID
							</td>
							<td>
								模板ID
							</td>
							<td>
								邮件名称
							</td>
							<td>
								邮件内容
							</td>
							<td>
								内容存储格式
							</td>
							<td>
								发送模式
							</td>
							<td>
								计划模式
							</td>
							<td>
								发送时间
							</td>
							<td>
								定时发送时间
							</td>
							<td>
								发送对象
							</td>
							<td>
								视频ID
							</td>
							<td>
								状态
							</td>
							<td>
								创建时间
							</td>
							<td>
								修改时间
							</td>
						</tr>
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
