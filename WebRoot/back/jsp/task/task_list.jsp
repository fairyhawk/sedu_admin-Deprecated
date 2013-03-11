<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>任务列表</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />

		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/static/js/util.js"></script>

	</head>
	<body>
<div>

	<form action="<%=contextPath%>/task/task!toListTasks.action" method="post" name="tasklist" id="tasklist">
	<s:hidden name="queryTaskCondition.currentPage" value="1" />
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">任务列表</font>
				
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		
		<tr>
	            <td class="lists_bor"></td>
	            <td>
	              <div class="msg-zy">
				<table class="" border="0" cellspacing="0"  cellpadding="0">
						<tr>
						<td>
							按课程名检索：
						<input type="text" name="searchKey" id="searchKey" value="${searchKey}" />
						</td>
						<td width="20px;"></td>
								<td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
								<td><a href="javascript:document.tasklist.submit()">查询</a></td>
								<td width="20px;"></td>
								<td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
								<td><a href="<%=contextPath%>/task/task!toAddTask.action">添加</a></td>
						</tr>		
						</table>
					 </td>
            <td class="lists_tright lists_bor2"></td>
        </tr>
		
		
		
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td>
							任务名称
						</td>
						<td>
							排序编号
						</td>
						<td>
							任务类型
						</td>

						<td>
							添加时间
						</td>
						
						<td>
							状态
						</td>

						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="task">
						<tr>
							<td>
								<s:property value="#task.taskName" />
							</td>
							<td>
								<s:property value="#task.sortNum" />
							</td>
							<td>
							<s:if test="#task.taskType==1">
							  正常任务
							</s:if>
							<s:if test="#task.taskType==2">
							  每日任务
							</s:if>
							<s:if test="#task.taskType==3">
							  循环任务
							</s:if>
							</td>
							
							<td>
								<s:date name="#task.createTime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							
							<td>
								<s:if test="#task.publishStatus == 0">
									未发布
								</s:if>
								<s:elseif test="#task.publishStatus == 1">
									已发布
								</s:elseif>
							</td>
						
							<td>
								<a href="<%=contextPath%>/task/task!publishTask.action?task.taskId=<s:property value='#task.taskId'/>">发布</a>|
								<a href="<%=contextPath%>/task/task!toUpdateTask.action?task.taskId=<s:property value='#task.taskId'/>">修改</a>|
								<a href="<%=contextPath%>/task/task!deleteTask.action?task.taskId=<s:property value='#task.taskId'/>" onclick="return confirm('是否删除？')" >删除</a>
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
				<jsp:include page="/back/jsp/common/showPage.jsp" />
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
