<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>广告列表</title>
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
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">广告列表</font>
				<font class="lists_fright">
					<img src="<%=contextPath%>/back/images/add_a.gif"/>
					<a href="<%=contextPath%>/feed/ad!toAddPage.action">新建广告</a>
				</font>
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<!-- 
		<tr>
			<td class="lists_bor"></td>
			<td>
				<form name="feed" action="course!selectCourse.action" method="post">
					<input type="hidden" name="queryAdCondition.currentPage" value="1" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
								<tr>
									<td width="12" class="lists_bor">
									</td>
									<td>
										<table width="100%" border="0" cellspacing="1" cellpadding="0"
											class="lists_info">
											<tr>
												<td colspan="4">
												时间:
													<input type="text" name="queryCourseCondition.startTime" id="startTime" value="${queryCourseCondition.startTime != -1 ? queryCourseCondition.startTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													到<input type="text" name="queryCourseCondition.endTime" id="endTime" value="${queryCourseCondition.endTime != -1 ? queryCourseCondition.endTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													项目:
													<select name="queryCourseCondition.subjectId">
														<option value="-1">全部专业</option>
														<s:iterator value="subjectList">
															<option value="<s:property value="subjectId"/>"><s:property value="subjectName"/></option>
														</s:iterator>
													</select>
													<s:submit value="搜索" />
												</td>
											</tr>
										</table>
									</td>
									<td width="16" class="lists_tright lists_bor2">
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
						</form>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		 -->
		<tr>
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
							广告备注
						</td>
						<td>
							发布时间
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="ad" status="row">
						<tr id='tr<s:property value="#row.count"/>'>
							<td>
								<s:property value="#ad.id" />
							</td>
							<td>
								<s:property value="#ad.name" />
							</td>
							<td>
								<s:property value="#ad.remark" />
							</td>
							<td>
								<s:date name="#ad.pubDate" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<span>
									<a href="<%=contextPath%>/feed/ad!toUpdatePage.action?id=<s:property value="#ad.id" />">修改</a>
								</span>
							</td>
						</tr>
					</s:iterator>
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

