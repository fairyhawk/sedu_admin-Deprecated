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
			<form action="<%=contextPath%>/feed/notice!doNoticeUpdate.action" method="post">
				<input type="hidden" value="<s:property value="notice.id"/>" name="notice.id" />
				<input type="hidden" value="<s:property value="notice.status"/>" name="notice.status" />
				<input type="hidden" value="<s:property value="notice.createUser"/>" name="notice.createUser" />
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr>
							<td>
								公告内容:
							</td>
							<td class="lists_tleft">
								<textarea rows="5" cols="30" name="notice.content"><s:property value="notice.content"/></textarea>
							</td>
						</tr>
						<tr>
							<td>
								公告类别：
							</td>
							<td class="lists_tleft">
								<select name="notice.courseCategoryId">
									<s:iterator value="courseCategoryList" var="item">
										<s:if test="notice.courseCategoryId == #item.id">
											<option value="<s:property value="#item.id"/>" selected="selected"><s:property value="#item.categoryName"/></option>
										</s:if>
										<s:else>
											<option value="<s:property value="#item.id"/>"><s:property value="#item.categoryName"/></option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="修改"/>
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
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>