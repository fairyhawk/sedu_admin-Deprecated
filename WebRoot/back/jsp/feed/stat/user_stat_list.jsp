<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户统计</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
			<font class="lists_fleft">用户统计</font>
				<font class="lists_fright">
						<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0">
							<tr>
								<td></td>
							</tr>
						</table>
						<table class="lists_fleft" width="80" border="0" cellspacing="0"  cellpadding="0">
							<tr>
								<td>&nbsp;</td>
								<td><a href="<%=contextPath %>/feed/userStat!downStatisticsData.action">导出数据</a></td>
							</tr>
						</table>
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				  	<tr>
				  		<td >
					  				<form action="<%=contextPath%>/feed/userStat!userStatListBySearch.action" method="post">
					  					<input type="hidden" value="1" name="queryUserStatCondition.currentPage">
						  				<table style="margin-left: auto;margin-right: auto;">
						  					<tr style="height: 30px;">
						  						<td align="right">时间:&nbsp;</td>
						  						<td>
												  		<input type="text" name="queryUserStatCondition.startTime" id="startTime" value="${queryUserStatCondition.startTime}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
														到<input type="text" name="queryUserStatCondition.endTime" id="endTime" value="${queryUserStatCondition.endTime}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
						  						</td>
						  					</tr>
						  					<tr style="height: 30px;">
						  						<td align="right">专业:&nbsp;</td>
						  						<td>
						  							<div style="float: left;">
												  		<select name="queryUserStatCondition.subjectId" id="selectSubject" style="width: 180px;"> 
															<option value="0">--全部--</option>
															<option value="6">迷你课程</option>
															<s:iterator value="subjectList">
															    <s:if test="queryUserStatCondition.subjectId == subjectId">
															    	<option value="<s:property value="subjectId"/>" selected="selected"><s:property value="subjectName"/></option>
															    </s:if>
															    <s:else>
															   		 <option value="<s:property value="subjectId"/>"><s:property value="subjectName"/></option>
															    </s:else>
															</s:iterator>
														</select>
													</div>
						  						</td>
						  					</tr>
						  					<tr style="height: 50px;">
						  						<td colspan="2" align="right">
						  							<input type="submit" value="搜  索>>"/>
						  						</td>
						  					</tr>
						  				</table>
					  				</form>
				  		</td>
				  	</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							ID
						</td>
						<td>
							用户邮箱
						</td>
						<td>
							专业
						</td>
						<td>
							注册时间
						</td>
						<td>
							注册位置
						</td>
						<td>
							使用次数
						</td>
						<td>
						 	未观看视频数
						</td>
						<td>
							观看时长
						</td>
						<td>
							最近使用时间	
						</td>
					</tr>
					<s:iterator value="page.pageResult">
						<tr>
							<td>
								<s:property value="id" />
							</td>
							<td>
								<s:property value="email" />
							</td>
							<td>
								<s:property value="subject.subjectName" />
							</td>
							<td>
								<s:date name="regTime" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:if test="regLocation == 1">
									微学习
								</s:if>
								<s:else>
									其他
								</s:else>
							</td>
							<td>
							 	<s:property value="useNum" />
							</td>
							<td><s:property value="notWatchNum" /></td>
							<td>
								<s:property value="totalLength" />
							</td>
							<td>
								<s:date name="modified" format="yyyy-MM-dd HH:mm:ss"/>
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