<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>统计管理>>视频统计</title>
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
			<form name="feed" action="<%=contextPath%>/feed/video!videoStatSearch.action" method="post">
				<input type="hidden" name="queryMicroVideoCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr>
												<td width="10%" class="lists_tright">
												请选择时间:
												</td>
												<td class="lists_tleft">
													时间:
													<input type="text" name="queryMicroVideoCondition.startTime" id="startTime" value="${queryMicroVideoCondition.startTime != -1 ? queryMicroVideoCondition.startTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													到<input type="text" name="queryMicroVideoCondition.endTime" id="endTime" value="${queryMicroVideoCondition.endTime != -1 ? queryMicroVideoCondition.endTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													</td>
												</tr>
													
												<tr>
													<td width="10%" class="lists_tright">
													课程类别：
													</td>
												<td class="lists_tleft">
													<select name="queryMicroVideoCondition.courseId">
														<option value="-1">全部类别</option>
														<s:iterator value="courseList" var="items">
															<s:if test="queryMicroVideoCondition.courseId == #items.id">
																<option value ="<s:property value="#items.id"/>" selected="selected"><s:property value="#items.name"/></option>
															</s:if>
															<s:else>
																<option value ="<s:property value="#items.id"/>"><s:property value="#items.name"/></option>
															</s:else>
														</s:iterator>
													</select>
													<input type="submit" />
												</td>
											</tr>
				</table>
			</form>
				<font class="lists_fright">
					<a href="<%=contextPath%>/feed/video!downStatisticsData.action">导出数据</a>
				</font>
			</form>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							视频ID
						</td>
						<td>
							视频名称
						</td>
						<td>
							点击数
						</td>
						<td>
							观看人数
						</td>
						<td>
							看完人数
						</td>
						<td>
							观看总时长
						</td>
						<td>
							支持数
						</td>
						<td>
							反对数
						</td>
						<td>
							下载数
						</td>
						<td>
							收藏数
						</td>
					</tr>
				<s:iterator value="page.pageResult" id="item">
					<tr>
						<td>
							<s:property value="#item.id"/>
						</td>
						<td>
							<s:property value="#item.name"/>
						</td>
						<td>
							<s:property value="#item.clickNum"/>
						</td>
						<td>
							<a href="<%=contextPath%>/feed/video!videoWatchDownCollectDetailsSearch.action?queryMicroVideoCondition.id=<s:property value="#item.id"/>&videoStatus=1"><s:property value="#item.clickUserNum"/></a>
						</td>
						<td>
							<a href="<%=contextPath%>/feed/video!videoWatchDownCollectDetailsSearch.action?queryMicroVideoCondition.id=<s:property value="#item.id"/>&videoStatus=2"><s:property value="#item.clickDoneUserNum"/></a>
						</td>
						<td>
							<s:property value="#item.totalWatchTimeStr"/>分
						</td>
						<td>
							<s:property value="#item.supportNumber"/>
						</td>
						<td>
							<s:property value="#item.antilogNumber"/>
						</td>
						<td>
							<a href="<%=contextPath%>/feed/video!videoWatchDownCollectDetailsSearch.action?queryMicroVideoCondition.id=<s:property value="#item.id"/>&videoStatus=3"><s:property value="#item.downNum"/></a>
						</td>
						<td>
							<a href="<%=contextPath%>/feed/video!videoWatchDownCollectDetailsSearch.action?queryMicroVideoCondition.id=<s:property value="#item.id"/>&videoStatus=4"><s:property value="#item.collectNum"/></a>
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