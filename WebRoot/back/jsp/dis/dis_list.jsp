<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>小组列表</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
			<font class="lists_fleft">小组列表</font>
			<font class="lists_fright"></font></td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<div class="msg-app">
				<form name="searchForm" action="<%=contextPath%>/dis/back!searchDis.action" method="post" onsubmit="return encodeCommon();">
							<input type="hidden" name="queryDiscussionCondition.currentPage" value="1" />
							<input type="hidden" id="keyWorld" name="queryDiscussionCondition.keyWorld" value="" />
										<table width="100%" cellspacing="1" cellpadding="0" border="0">
											<tr>
												<td class="lists_fright">
													关键字：
												</td>
												<td>
													
														<input type="text" id="world" id="tle" value="${queryDiscussionCondition.keyWorld}"/>
														<select name="queryDiscussionCondition.subjectId">
															<option value="-1">项目</option>
															<s:iterator value="#session.session_subjectList" var="item">
																<s:if test="queryDiscussionCondition.subjectId == #item.subjectId">
																	<option value="<s:property value="#item.subjectId"/>" selected="selected"><s:property value="#item.subjectName"/></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="#item.subjectId"/>"><s:property value="#item.subjectName"/></option>
																</s:else>
															</s:iterator>
														</select>
														<select name="queryDiscussionCondition.searchCriteria">
															<option value="-1">检索条件</option>
															<s:iterator value="searchCriteriaByDisList" var="item">
																<s:if test="queryDiscussionCondition.searchCriteria == #item.value">
																	<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
																</s:else>
															</s:iterator>
														</select>
														<select name="queryDiscussionCondition.status">
															<option value="-1" selected="selected">状态</option>
															<s:iterator value="statusDisList" var="item">
																<s:if test="queryDiscussionCondition.status == #item.value">
																	<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
																</s:else>
															</s:iterator>
														</select>
														<select name="queryDiscussionCondition.type">
															<option value="-1">小组类型</option>
															<s:iterator value="typeDisList" var="item">
																<s:if test="queryDiscussionCondition.type == #item.value">
																	<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
																</s:else>
															</s:iterator>
														</select>
												</td>
											</tr>
											<tr>
												<td class="lists_fright">
													创建时间：
												</td>
												<td>
													<input type="text" name="queryDiscussionCondition.createTimeStart" readonly id="startTime"
														value="${queryDiscussionCondition.createTimeStart != -1 ? queryDiscussionCondition.createTimeStart : ''}" width="10" onclick="WdatePicker()"/>
														<select name="startHH" id="startHH">
														<option value=" 00:00:00" id="op0">00:00:00</option>
														<option value=" 01:00:00">01:00:00</option>
														<option value=" 02:00:00">02:00:00</option>
														<option value=" 03:00:00">03:00:00</option>
														<option value=" 04:00:00">04:00:00</option>
														<option value=" 05:00:00">05:00:00</option>
														<option value=" 06:00:00">06:00:00</option>
														<option value=" 07:00:00">07:00:00</option>
														<option value=" 08:00:00">08:00:00</option>
														<option value=" 09:00:00">09:00:00</option>
														<option value=" 10:00:00">10:00:00</option>
														<option value=" 11:00:00">11:00:00</option>
														<option value=" 12:00:00">12:00:00</option>
														<option value=" 13:00:00">13:00:00</option>
														<option value=" 14:00:00">14:00:00</option>
														<option value=" 15:00:00">15:00:00</option>
														<option value=" 16:00:00">16:00:00</option>
														<option value=" 17:00:00">17:00:00</option>
														<option value=" 18:00:00">18:00:00</option>
														<option value=" 19:00:00">19:00:00</option>
														<option value=" 20:00:00">20:00:00</option>
														<option value=" 21:00:00">21:00:00</option>
														<option value=" 22:00:00">22:00:00</option>
														<option value=" 23:00:00">23:00:00</option>
														<option value=" 23:59:59">23:59:59</option>
														</select> --
														<input type="text" name="queryDiscussionCondition.createTimeEnd" readonly id="endTime"
														onclick="WdatePicker()" 
														value="${queryDiscussionCondition.createTimeEnd != -1 ? queryDiscussionCondition.createTimeEnd : ''}" width="10"/>
														<select name="endHH" id="endHH">
														<option value=" 23:59:59" id="op0">23:59:59</option>
														<option value=" 00:00:00">00:00:00</option>
														<option value=" 01:00:00">01:00:00</option>
														<option value=" 02:00:00">02:00:00</option>
														<option value=" 03:00:00">03:00:00</option>
														<option value=" 04:00:00">04:00:00</option>
														<option value=" 05:00:00">05:00:00</option>
														<option value=" 06:00:00">06:00:00</option>
														<option value=" 07:00:00">07:00:00</option>
														<option value=" 08:00:00">08:00:00</option>
														<option value=" 09:00:00">09:00:00</option>
														<option value=" 10:00:00">10:00:00</option>
														<option value=" 11:00:00">11:00:00</option>
														<option value=" 12:00:00">12:00:00</option>
														<option value=" 13:00:00">13:00:00</option>
														<option value=" 14:00:00">14:00:00</option>
														<option value=" 15:00:00">15:00:00</option>
														<option value=" 16:00:00">16:00:00</option>
														<option value=" 17:00:00">17:00:00</option>
														<option value=" 18:00:00">18:00:00</option>
														<option value=" 19:00:00">19:00:00</option>
														<option value=" 20:00:00">20:00:00</option>
														<option value=" 21:00:00">21:00:00</option>
														<option value=" 22:00:00">22:00:00</option>
														<option value=" 23:00:00">23:00:00</option>
														</select>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>
													<s:submit value="搜 索" />
												</td>
											</tr>
										</table>
						</form>
					</div>
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
							项目
						</td>
						<td>
							小组名称
						</td>
						<td>
							小组类型
						</td>
						<td>
							创建人
						</td>
						<td>
							成员数量
						</td>
						<td>
							状态
						</td>
						<td>
							创建时间
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="dis">
						<tr>
							<td>
								<s:property value="id" />
							</td>
							<td>
								<s:property value="subject" />
							</td>
							<td>
								<s:property value="name" />
							</td>
							<td>
								<s:iterator value="typeDisList" var="item">
									<s:if test="type == #item.value">
										<s:property value="#item.text"/>
									</s:if>
								</s:iterator>
							</td>
							<td>
								<s:property value="creatuser" />
							</td>
							<td>
								<s:property value="userCount" />
							</td>
							<td>
								<s:iterator value="statusDisList" var="item">
									<s:if test="status == #item.value">
										<s:property value="#item.text"/>
									</s:if>
								</s:iterator>
							</td>
							<td>
								<s:date name="createtime" format="yyyy:MM:dd HH:mm:ss"/>
							</td>
							<td>
								<a	href='back!toEditDis.action?dis.id=<s:property value="id"/>'>修改</a>
								<s:if test="type==1">
									<a href='back!deleteDis.action?dis.id=<s:property value="userId"/>'>删除</a>
								</s:if>
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
				<!-- 分页页码 -->
				<s:if test="page.totalPage > 1">
					<jsp:include page="/back/jsp/common/showPage.jsp" />
				</s:if>
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>
	<script type="text/javascript">
			/**
				编码含有中文字符字段
			*/
			function encodeCommon(){
				$("#keyWorld").val(encodeURIComponent($("#world").val()));
				return true;
			}
			/**
				时间控件中只显示 yyyy-MM-dd 格式
			*/
			$(function(){
				$("#startTime").val($("#startTime").val().substring(0,10));
				$("#endTime").val($("#endTime").val().substring(0,10));
			})
	</script>