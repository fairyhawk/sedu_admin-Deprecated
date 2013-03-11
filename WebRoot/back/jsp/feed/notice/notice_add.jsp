<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>公管理>>新建公告</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_left">
					新建公告
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<form action="<%=contextPath%>/feed/notice!doNoticeAdd.action" method="post">
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr>
							<td>
								公告标题:
							</td>
							<td class="lists_tleft">
								<textarea rows="5" cols="30" name="notice.content"></textarea>
							</td>
						</tr>
						<tr>
							<td>
								公告类别：
									
							</td>
							<td class="lists_tleft">
								<select name="notice.courseCategoryId">
									<s:iterator value="courseCategoryList" var="item">
										<option value="<s:property value="#item.id"/>"><s:property value="#item.categoryName"/></option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="发布"/>
							</td>
						</tr>
				</table>
			</form>
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