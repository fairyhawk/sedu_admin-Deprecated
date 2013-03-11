<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>统计管理>>用户统计</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
	    <script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script type="text/javascript">
			function promtExcel(){
				window.location.href="<%=contextPath%>/feed/userStatistics!promtExcel.action";
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
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<form name="feed" action="userStatistics!goUserStatisticsPage.action" method="post">
				<input type="hidden" name="queryUserStatisticsCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr>
												<td colspan="4">
												请选择时间:
													<input type="text" name="queryUserStatisticsCondition.startTime" id="startTime" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true" value="<s:date name="queryUserStatisticsCondition.startTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
													到
													<input type="text" name="queryUserStatisticsCondition.endTime" id="endTime" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true" value="<s:date name="queryUserStatisticsCondition.startTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
													课程：
													<select name="queryUserStatisticsCondition.courseId">
														<option value="-1">全部课程</option>
														<s:iterator value="courseList" id="items">
															<option value="<s:property value="#items.id"/>"><s:property value="#items.name"/></option>
														</s:iterator>
													</select>
													<button onclick="queryByTerm()">查询</button>
												</td>
											</tr>
				</table>
				<font class="lists_left">
				当前使用用户：
				</font>
					<font class="lists_fright">
					<input type='button' value="导出数据" onclick="promtExcel()"/>
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
							用户ID
						</td>
						<td>
							用户名
						</td>
						<td>
							注册专业
						</td>
						<td>
							注册位置
						</td>
						<td>
							注册时间
						</td>
						<td>
							到达微学习的次数
						</td>
						<td>
							观看课程次数
						</td>
						<td>
							观看视频次数
						</td>
						<td>
							总观看时间
						</td>
						<td>
							下载视频数
						</td>
						<td>
							收藏视频数
						</td>
						<td>
							提问数
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="user">
						<tr>
							<td><s:property value="#user.id" /></td>
							<td><s:property value="#user.userName" /></td>
							<td><s:property value="#user.subjectName" /></td>
							<td>
								<s:if test="#user.regFrom == 1">
									微学习页
								</s:if>
								<s:else>
									其他
								</s:else>
							</td>
							<td><s:date name="#user.regTime" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
								<s:if test="#user.comeFeedNum==null">0</s:if>
								<s:else><s:property value="#user.comeFeedNum" /></s:else>
							</td>
							<td>
								<a href="<%=contextPath%>/feed/userStatistics!goUserUseInfoPage.action?queryUserStatisticsCondition.userId=<s:property value="#user.id" />&isWhat=1">
									<s:if test="#user.watchCourseNum==null">0</s:if>
									<s:else><s:property value="#user.watchCourseNum" /></s:else>
								</a>
 							</td>
							<td>
								<a href="<%=contextPath%>/feed/userStatistics!goUserUseInfoPage.action?queryUserStatisticsCondition.userId=<s:property value="#user.id" />&isWhat=2">
									<s:if test="#user.watchVideoNum==null">0</s:if>
									<s:else><s:property value="#user.watchVideoNum" /></s:else>
								</a>
							</td>
							<td>
								<s:if test="#user.watchAllTime==null">0</s:if>
								<s:else><s:property value="#user.watchAllTime" /></s:else>分
							</td>
							<td>
								<a href="<%=contextPath%>/feed/userStatistics!goUserUseInfoPage.action?queryUserStatisticsCondition.userId=<s:property value="#user.id" />&isWhat=3">
									<s:if test="#user.downloadVideoNum==null">0</s:if>
									<s:else><s:property value="#user.downloadVideoNum" /></s:else>
								</a>
							</td>
							<td>
								<a href="<%=contextPath%>/feed/userStatistics!goUserUseInfoPage.action?queryUserStatisticsCondition.userId=<s:property value="#user.id" />&isWhat=4">
									<s:if test="#user.collectionVideoNum==null">0</s:if>
									<s:else><s:property value="#user.collectionVideoNum" /></s:else>
								</a>
							</td>
							<td>
								<s:if test="#user.questionNum==null">0</s:if>
								<s:else><s:property value="#user.questionNum" /></s:else>
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