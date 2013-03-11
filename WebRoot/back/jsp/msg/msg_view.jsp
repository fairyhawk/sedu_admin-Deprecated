<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>消息浏览</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists" id="tblList">
				<tr >
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">消息修改</font>
						<font class="lists_fright">
						</font>
					</td>
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12"  class="lists_bor">
					</td>
					<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tbody id="tagTb">
							<tr>
								<td>
									<font color="red">*</font>消息标题
								</td>
								<td>
									<s:property value='msg.msgTitle'/>
								</td>
							</tr>
							
							<tr>
								<td>
									<font color="red">*</font>发件人
								</td>
								<td>
									<s:property value='msg.senderName'/>
								</td>
							</tr>
							
							<tr>
								<td>
									<font color="red">*</font>消息内容
								</td>
								<td>
									<s:property value='msg.msgContent'/>
								</td>
							</tr>
							
							
						</table>
						</td>
					</tr>
					<tr>
						<td>
							<img src="<%=contextPath%>/back/images/tab_18.gif" />
						</td>
						<td class="lists_bottom">
						</td>
						<td>
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
			</table>
	</body>
</html>
