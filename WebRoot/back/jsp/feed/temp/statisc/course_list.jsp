<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>课程管理>>课程统计</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
	    <script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script type="text/javascript">
			function promtExcel(){
				window.location.href="<%=contextPath%>/feed/course!promtExcel.action";
			}
			
			function goVideoStatistics(){
				window.location.href="<%=contextPath%>/feed/video!videoStatSearch.action?queryMicroVideoCondition.currentPage=1";
			}
		</script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					查询条件：
				</font>
				<font class="lists_right">
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<form name="feed" action="course!selectCourse.action?isStatics=1" method="post">
				<input type="hidden" name="queryCourseCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr>
												<td colspan="4">
												请选择时间:
													<input type="text" name="queryCourseStaticsCondition.startTime" id="startTime" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true" value="<s:date name="queryCourseStaticsCondition.startTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
													到
													<input type="text" name="queryCourseStaticsCondition.endTime" id="endTime" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true" value="<s:date name="queryCourseStaticsCondition.endTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
													课程类别：
													<select id="category" name="queryCourseStaticsCondition.courseCategoryId">
														<option value="-1">选择类别</option>
														<s:iterator value="courseCategoryList" var="items">
															<option value ="<s:property value="#items.id"/>"><s:property value="#items.categoryName"/></option>
														</s:iterator>
													</select>
													<button onclick="queryByTerm()">查询</button>
												</td>
											</tr>
				</table>
				<div style="align:right">
					<input type='button' value="视频统计" onclick="goVideoStatistics()"/>
					<input type='button' value="导出数据" onclick="promtExcel()"/>
				</div>
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
							课程ID
						</td>
						<td>
							观看用户数
						</td>
						<td>
							观看次数
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="course">
						<tr>
							<td><s:property value="#course.courseName"/></td>
							<td><a href="<%=contextPath %>/feed/course!viewWatchUser.action?courseId=<s:property value="#course.id"/>"><s:property value="#course.usePersonNumber"/></a></td>
							<td><s:property value="#course.watchNumber"/></td>
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