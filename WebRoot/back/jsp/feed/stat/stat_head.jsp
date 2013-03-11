<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<script type="text/javascript">
	
	function dayWeekMonthAction(where){
		var type = $("#type").val();
		var url = "<%=contextPath%>/feed/back!queryTrafficStatistics.action?type=" + type + "&where=" + where;
		if(type == 1){
			url += "&queryAdStatCondition.currentPage=1";
		}else if(type == 2){
			url += "&queryBrowseLogCondition.currentPage=1";
		}else if(type == 3){
			url += "&queryUserUseCondition.currentPage=1";
		}
		document.location.href = url;
	}
</script>


		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<form name="feed" action="<%=contextPath %>/feed/back!queryTrafficStatistics.action" method="post">
					<input type="hidden" name="queryStatCommonCondition.currentPage" value="1">
					<input type="hidden" name="where" value="0">
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr>
												<td colspan="4">
													请选择查阅对象：
													<select style="width: 350px;" id="type" name="type">
														<s:if test="type == 1">
															<option value="1" selected="selected">微学习广告图</option>
															<!--
															<option value="2">微学习内容页</option>
															<option value="3" title="微学习入口页面登录/注册详细">微学习入口页面登录/注册</option>
															-->
														</s:if>
														<!--
															<s:if test="type == 2">
																<option value="1">微学习广告图</option>
																<option value="2" selected="selected">微学习内容页</option>
																<option value="3" title="微学习入口页面登录/注册详细">微学习入口页面登录/注册</option>
															</s:if>
														<s:if test="type == 3">
															<option value="1">微学习广告图</option>
															<option value="2">微学习内容页</option>
															<option value="3" selected="selected">微学习入口页面登录/注册</option>
														</s:if>
														-->
													</select>
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<a href="javascript:dayWeekMonthAction(1);">今天（24小时内）</a>|
													<a href="javascript:dayWeekMonthAction(2);">一周内</a>|
													<a href="javascript:dayWeekMonthAction(3);">一个月</a>
												</td>
											</tr>
											<tr>
												<td colspan="4">
												请选择时间：
												 <input type="text" name="queryStatCommonCondition.startTime" id="startTime" value="${queryStatCommonCondition.startTime}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
												到<input type="text" name="queryStatCommonCondition.endTime" id="endTime" value="${queryStatCommonCondition.endTime}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													<s:submit value="搜索" />
												</td>
											</tr>
											<tr class="lists_infobg">
												<td>
													<s:if test="type == 1">
														微学习广告图
													</s:if>
													<s:elseif test="type == 2">
														微学习项目页面
													</s:elseif>
													<s:elseif test="type == 3">
														登录/注册
													</s:elseif>
													<s:else>
														时间段查询
													</s:else>
													/
													<s:if test="where == 1">
														今天（24小时内）
													</s:if>
													<s:elseif test="where == 2">
														本周内
													</s:elseif>
													<s:elseif test="where == 3">
														一月内
													</s:elseif>
												</td>
												<td>
													独立ip数：<s:property value="count"/>
												</td>
											</tr>
					</table>
				</form>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		