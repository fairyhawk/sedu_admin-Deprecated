<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	</head>
	<body>
<div>
	<form action="<%=contextPath%>/cou/course!listCoursesBySortId.action" method="post" name="courselist" id="courselist">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">课程删除</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td>
							课程名
						</td>

						<td>
							添加时间
						</td>
						
						<td>
							课程类型
						</td>

						<td>
							操作
						</td>
					</tr>
					<s:iterator value="courseList" id="course">
						<tr>
							<td>
								<s:property value="#course.title" />
							</td>
							<td>
								<s:date name="#course.addtime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<s:if test="#course.status==0">
								  正常
								</s:if>
								<s:if test="#course.status==1">
								  冻结
								</s:if>
								<s:if test="#course.status==3">
								  内部课程
								</s:if>
							</td>
							<td>
								<a href="<%=contextPath%>/cou/course!deleteRealCourse.action?course.courseId=<s:property value="#course.courseId"/>" onclick="return confirm('是否删除？')" >删除</a>
							</td>
						</tr>
					</s:iterator>
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
</div>
	</body>
</html>
