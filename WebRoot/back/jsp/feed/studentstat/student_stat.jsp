<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习-学员统计</title>
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
					<font class="lists_fleft">微学习-学员统计</font>
					<font class="lists_fright">
						<a href="<%=contextPath%>/feed/studentstat!downData.action">导出数据</a>
					</font>
								<%-- 
								<form action="<%=contextPath%>/feed/studentstat!searchStudentStat.action" method="post" name="searchForm">
								选择时间:<input type="text" name="queryStudentStatCondition.searchDate" id="searchDate" value="${queryStudentStatCondition.searchDate}" onclick="SelectDate(this,'yyyy-MM-dd')" readonly="true"/>
								  <input type="submit" value="搜索>>"/>	
								</form>
								  --%>  
					
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<div id="fromDiv">
					<!-- 按照来源条件查询 -->
				   <form  action="<%=contextPath%>/feed/studentstat!searchStuStatByFromIds.action" method="post" onsubmit="return CheckFromIds();">
				   		<input name="fromIds" value="" type="hidden" id="formIds"/>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr>
								<td width="10%" class="lists_tright">
									请选择来源条件：
								</td>
								<td class="lists_tleft">
										<s:iterator value="listForm">
											<input  type="checkbox" name="checkboxName" value='<s:property  value="id"/>'/><s:property  value="name"/>
										</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="10%" class="lists_tright">
									请选择时间：
								</td>
								<td class="lists_tleft">
										<input type="text" name="queryStudentStatCondition.searchDate" id="searchDate" value="${queryStudentStatCondition.searchDate}" onclick="SelectDate(this,'yyyy-MM-dd')" readonly="true"/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="submit" value="查询>>"/>
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
			    <!-- 显示所有信息 -->
				<div>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr class="lists_infobg">
							    <td>
							      	来&nbsp;源
							    </td>
								<td>
									页流量
								</td>
								<td>
									注册人数
								</td>
								<td>
									登录人数
								</td>
								<td>
									跳出人数
								</td>
								<td>
									到达微学习页
								</td>
								<td>
									首次用户
								</td>
								<td>
									非重复用户
								</td>
								<td>
									重复用户
								</td>
								<td>
									带来订单
								</td>
								<td>
									带来流水
								</td>
								<td>
									统计日期
								</td>
							</tr>
							<s:iterator value="studentStatList">
								<tr>
									<td>
									  	<s:if test="fromId == 1">
									  		首 页
									  	</s:if>
									  	<s:elseif test="fromId == 2">
									  		项目页
									  	</s:elseif>
									  	<s:elseif test="fromId == 3">
									  		邮 件
									  	</s:elseif>
									  	<s:elseif test="fromId == 4">
									  		其他
									  	</s:elseif>
									  	<s:elseif test="fromId == 5">
									  		网站其他入口
									  	</s:elseif>
									</td>
									<td><s:property  value="pageFlow"/></td>
									<td><s:property  value="registerCount"/></td>
									<td><s:property  value="loginCount"/></td>
									<td><s:property  value="outCount"/></td>
									<td><s:property  value="arriveWxxCount"/></td>
									<td><s:property  value="firstUserCount"/></td>
									<td><s:property  value="nonRepeatUserCount"/></td>
									<td><s:property  value="repeatUserCount"/></td>
									<td><s:property  value="orderCount"/></td>
									<td><s:property  value="runningWaterCount"/></td>
									<td><s:property  value="searchDate"/></td>
								</tr>
							</s:iterator>
						</table>
					</div>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>
<script  type="text/javascript">
	//获取来源编号
	function CheckFromIds(){
		var fromIds="";
		$("#fromDiv input[name='checkboxName']").each(function(){
			   if($(this).attr("checked")==true){
				   fromIds += ","+$(this).val();
			   }
		});

		$("#formIds").val(fromIds);
		return true;
	}
	
</script>