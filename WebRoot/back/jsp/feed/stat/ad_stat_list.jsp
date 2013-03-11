<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>ad访问统计</title>
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
					ad访问统计
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
		<!--<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							广告id
						</td>
						<td>
							广告名称
						</td>
						<td>
							用户id
						</td>
						<td>
							ip地址
						</td>
						<td width="500px;">
							ua
						</td>
					</tr>
					<s:iterator value="page.pageResult">
						<tr>
							<td>
								<s:property value="ad.id" />
							</td>
							<td>
								<s:property value="ad.name" />
							</td>
							<td>
								<s:property value="cusId" />
							</td>
							<td>
								<s:property value="ip" />
							</td>
							<td>
								<div style="width:500px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap" title="<s:property value="ua" />">
									<s:property value="ua" />
								</div>
							</td>
						</tr>
					</s:iterator>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
			--><tr>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_18.gif" />
				</td>
				<td class="lists_bottom">
					&nbsp;
				</td>
				<td class="td_wid_r">
					<img src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
	</table>
</div>
	</body>
</html>

