<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建小组</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script>
		
			//冻结
			
			function freezeCourse(id){
				var param = "courseId="+id;
				if($('#'+id).html() == "取消"){
					$.ajax({
					   type:"post",
					   url:"<%=contextPath%>/feed/course!freezeCourse.action",
					   data:param,
					   success: function(data){
					  		if(data == "success"){
								alert("取消冻结成功");
								$('#'+id).html("冻结");
							}else{
								alert("冻结失败");
							}			   
					   }
					});
				}else{
					$.ajax({
					   type:"post",
					   url:"<%=contextPath%>/feed/course!freezeCourse.action",
					   data:param,
					   success: function(data){
					  		if(data == "success"){
								alert("冻结成功");
								$('#'+id).html("取消");
							}else{
								alert("冻结失败");
							}			   
					   }
					});
				}
			}
			//删除
			
			function deleteCourse(id,rowId){
				if($('#'+id).html() == "取消"){
					var param = "courseId="+id;
					$.ajax({
					   type:"post",
					   url:"<%=contextPath%>/feed/course!deleteCourse.action",
					   data:param,
					   success: function(data){
							if(data == "success"){
								alert("删除成功");
								$('#'+rowId).hide();
							}else{
								alert("删除失败");
							}				   
					   }
					});
				}else{
					alert("此课程没有冻结，不能删除，如需删除，请先冻结该课程！");
					return;
				}
			}
		</script>		
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					课程列表
				</font>
				<font class="lists_fright">
					<a href="<%=contextPath%>/feed/course!courseAdd.action">新建课程</a>
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<form name="feed" action="course!selectCourse.action" method="post">
				<input type="hidden" name="queryCourseCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr>
												<td colspan="4">
												时间:
													<input type="text" name="queryCourseCondition.startTime" id="startTime" value="${queryCourseCondition.startTime != -1 ? queryCourseCondition.startTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													到<input type="text" name="queryCourseCondition.endTime" id="endTime" value="${queryCourseCondition.endTime != -1 ? queryCourseCondition.endTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													类别:
													<select name="queryCourseCondition.courseCategoryId">
														<option value="-1">全部类别</option>
														<s:iterator value="courseCategoryList" var="items">
															<option value ="<s:property value="#items.id"/>"><s:property value="#items.categoryName"/></option>
														</s:iterator>
													</select>
													<s:submit value="搜索" />
												</td>
											</tr>
				</table>
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
							课程名称
						</td>
						<td>
							课程类别
						</td>
						<td>
							视频数
						</td>
						<td>
							使用人数
						</td>
						<td>
							独享
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="course" status="row">
						<tr id='tr<s:property value="#row.count"/>'>
							<td>
								<s:property value="#course.id" />
							</td>
							<td>
								<s:property value="#course.name" />
							</td>
							<td>
								<s:property value="#course.courseCategoryName" />
							</td>
							<td>
								<s:property value="#course.videoCount" />
							</td>
							<td>
								<s:property value="#course.usePersonNumber" />
							</td>
							<td>
								<s:if test="#course.isExclusive == 0">否</s:if>
								<s:if test="#course.isExclusive == 1">是</s:if>
							</td>
							<td>
								<span>
									<a href="<%=contextPath %>/feed/course!edtorCourse.action?courseId=<s:property value="#course.id" />">详情</a>
									<a href="<%=contextPath%>/feed/course!edtorCourse.action?courseId=<s:property value="#course.id" />">修改</a>
									<a id="<s:property value="#course.id" />" href="javascript:void(0)" onclick="freezeCourse(<s:property value="#course.id" />)">冻结</a>
									<a href="javascript:void(0)" onclick="deleteCourse(<s:property value="#course.id"/>,'tr<s:property value="#row.count"/>' )">删除</a>|
									<a href="<%=contextPath %>/feed/course!toVideoFramesetPage.action?id=<s:property value="#course.id"/>">添加视频</a>
									<a href="<%=contextPath %>/feed/video!goAllVideo.action?queryMicroVideoCondition.currentPage=1&queryMicroVideoCondition.courseId=<s:property value="#course.id"/>">视频列表</a>
								</span>
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