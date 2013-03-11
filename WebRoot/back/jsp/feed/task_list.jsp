<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建小组</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		
		<script>
			function sendTaskActionConfirm(taskId){
				if(confirm("确定要发送吗，一旦发送将不能修改!")){
					sendTaskAction(taskId);
				}
			}
			/**
				修改任务状态
			*/
			function delTaskAction(taskId){
			
				if(!confirm("确定要删除吗!")){
					return;
				}
				
				var url = "<%=contextPath%>/feed/back!doDelTask.action";
				var params = {
					id:taskId
				}
				//使用$.post方式
				$.post(
					url,
					params,
					function topic(data){
						//条件成立，执行成功
						if(data == 1){
							document.location.reload();
						}else{
							showErrorWin('删除失败!');
						}
					},
					'json'
				);
			}
			/**
				发送任务并修改状态
			*/
			function sendTaskAction(taskId){
				var url = "<%=contextPath%>/feed/back!sendTask.action";
				var params = {
					id:taskId
				}
				$.post(
					url,
					params,
					function topic(data){
						//条件成立，执行成功
						if(data == 1){
							alert("执行成功");
							document.location.reload();
						}else if(data == 2){
							alert('该任务正在执行中,不能多次发送!');
						}else if(data == 3){
							alert('修改失败');
						}else if(data == 4){
							alert('测试');
						}else{
							alert('失败!');
						}
					},
					'json'
				);
			}
		</script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					任务列表
				</font>
				<font class="lists_fright">
					&nbsp;
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<form name="searchForm" action="<%=contextPath%>/feed/back!taskList.action" method="post">
							<input type="hidden" name="queryTaskLogCondition.currentPage" value="1" />
							
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr>
												<td colspan="4">
													项目:
													<select name="queryTaskLogCondition.subjectId">
														<option value="-1">全部专业</option>
														<s:iterator value="subjectList">
															<s:if test="queryTaskLogCondition.subjectId == subjectId">
																<option value="<s:property value="subjectId"/>" selected="selected"><s:property value="subjectName"/></option>
															</s:if>
															<s:else>
																<option value="<s:property value="subjectId"/>"><s:property value="subjectName"/></option>
															</s:else>
														</s:iterator>
													</select>
													时间:
													<input type="text" name="queryTaskLogCondition.startTime" id="startTime" value="${queryTaskLogCondition.startTime != -1 ? queryTaskLogCondition.startTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													到<input type="text" name="queryTaskLogCondition.endTime" id="endTime" value="${queryTaskLogCondition.endTime != -1 ? queryTaskLogCondition.endTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
												</td>
											</tr>
											<tr align="center">
												<td colspan="4">
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
							ID
						</td>
						<td>
							任务ID
						</td>
						<td>
							邮件名称
						</td>
						<td>
							计划总次数
						</td>
						<td>
							激活次数
						</td>
						<td>
							发送次数(成功)
						</td>
						<td>
							错误次数(失败)
						</td>
						<td>
							定时任务发送时间
						</td>
						<td>
							上次发送时间
						</td>
						<td>
							状态
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="taskLog">
						<tr>
							<td>
								<s:property value="id" />
							</td>
							<td>
								<s:property value="taskList.id" />
							</td>
							<td>
								<s:property value="taskList.name" />
							</td>
							<td>
								<s:property value="total" />
							</td>
							<td>
								<s:property value="urlClickNum" />
							</td>
							<td>
								<s:property value="sendCount" />
							</td>
							<td>
								<s:property value="sendErrorCount" />
							</td>
							<td>
								<s:property value="taskList.regularlySentTime" />
							</td>
							<td>
								<s:date format="yyyy-MM-dd HH:mm:ss" name="modified"/>
							</td>
							<td>
								<s:iterator value="taskStatusList" id="item">
									<s:if test="taskList.status == #item.value">
										<s:property value="#item.text"/>
									</s:if>
								</s:iterator>
							</td>
							<td>
								<span>
									<s:if test="taskList.status == 1">
										<a href="javascript:sendTaskActionConfirm('<s:property value="taskList.id" />');" id="start_" style="color: blue;">启动</a> 
										<a href="<%=contextPath %>/feed/back!updateTask.action?id=<s:property value="taskList.id" />">修改</a>
										<a href="javascript:delTaskAction('<s:property value="taskList.id" />')">删除</a>
									</s:if>
									<s:if test="taskList.status == 2">
										<a href="javascript:alert('该任务已发送，不能重复发送');" id="start_" style="color: red;">已启动</a>
										<!-- 
										<a href="javascript:alert('当前处于发送状态，不允许修改');">修改</a>
										-->
										<a href="javascript:alert('当前处于发送状态，不允许删除!');">删除</a>
									</s:if>
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