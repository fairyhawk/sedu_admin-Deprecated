<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>查看提醒</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" href="<%=contextPath%>/styles/usercenter/uc_public.css" type="text/css"></link>
		<link rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css" type="text/css"></link>
		
		<script type="text/javascript">
		//时间比较；
		function startVSendTime()
		{
			 //开始时间
			var srartDate=document.getElementById("startdate").value;
			//结束时间
			var endDate=document.getElementById("enddate").value;
			//邮件发送时间
			var alertdate=document.getElementById("alertdate").value;

			if(endDate!="" && srartDate>endDate){
				alert("'开始时间' 不能大于 '结束时间',\n请从新选择开始时间！");
				var srartDate=document.getElementById("startdate");
				srartDate.value="";
				srartDate.focus();
			}
			if(alertdate!="" && alertdate!="" && srartDate > alertdate){
				alert("'开始时间' 不能大于'提醒邮件发送时间' ,\n请从新选择开始时间！");
				var srartDate=document.getElementById("startdate");
				srartDate.value="";
				srartDate.focus();
			}
		}
		function endVSendTime()
		{
			 //开始时间
			var srartDate=document.getElementById("startdate").value;
			//结束时间
			var endDate=document.getElementById("enddate").value;
			//邮件发送时间
			var alertdate=document.getElementById("alertdate").value;
			if(srartDate!="" && srartDate>endDate){
				alert("'结束时间' 不能小于 '开始时间',\n请从新选择结束时间！");
				var enddate=document.getElementById("enddate");
				enddate.value="";
				enddate.focus();
			}
			if(alertdate!="" && alertdate > endDate){
				alert("'结束时间' 不能小于 '提醒邮件发送时间',\n请从新选择结束时间！");
				var enddate=document.getElementById("enddate");
				enddate.value="";
				enddate.focus();
			}
			
			
		}
		function sendVSendTime()
		{
			//开始时间
			var srartDate=document.getElementById("startdate").value;
			//结束时间
			var endDate=document.getElementById("enddate").value;
			//邮件发送时间
			var alertdate=document.getElementById("alertdate").value;
			if(srartDate!="" && srartDate>alertdate){
				alert("'提醒邮件发送时间' 不能小于 '开始时间',\n请从新选择发送时间！");
				var alertdate=document.getElementById("alertdate");
				alertdate.value="";
				alertdate.focus();
			}
			if(endDate!="" && alertdate!="" && endDate < alertdate){
				alert("'提醒邮件发送时间' 不能大于 '结束时间',\n请从新选择发送时间！");
				var alertdate=document.getElementById("alertdate");
				alertdate.value="";
				alertdate.focus();
			}
		}
		
		
		//关闭
		function clocs()
		{
			var aBlean=confirm("数据还未提交，你确认关闭该页面？");
			if(aBlean==true)
			{
				window.location="javascript:history.go(-1)"
			}else{
				return false;
			}
		}
		
		//  进行校验；
		function onSubmit()
		{
			if(document.getElementById("ctitle").value==""){
				alert("提醒标题不能为空！");
				var ctitle=document.getElementById("ctitle");
				ctitle.focus();
				return false;
			}else if(document.getElementById("subjectId").value<=0){
				alert("请选择所属项目！");
				var csubjecte=document.getElementById("subjectId");
				csubjecte.focus();
				return false;
			}else if(document.getElementById("ccontent").value==""){
				alert("提醒内容不能为空！");
				var ccontent=document.getElementById("ccontent");
				ccontent.focus();
				return false;
			}else if(document.getElementById("startdate").value==""){
				alert("请选择提醒时间开始时间！");
				var startdate=document.getElementById("startdate");
				startdate.focus();
				return false;
			}else if(document.getElementById("alertdate").value==""){
				alert("请选择提醒邮件发送时间！");
				var alertdate=document.getElementById("alertdate");
				alertdate.focus();
				return false;
			}
			else if(document.getElementById("cstatus").value==""){
				alert("提醒状态不能为空！");
				var cstatus=document.getElementById("cstatus");
				cstatus.focus();
				return false;
			}else{
				alert("修改成功");
				return  true;
			}
		}
			
		//修改所属项目					
		function getSubjectName(obj){
			var subValue = obj.options[obj.selectedIndex].value;
			var subText = obj.options[obj.selectedIndex].text;
			$('#subjectnamne').val(subText);
		}
				
		</SCRIPT>
	</head>
	<body>
		<div>
		<form action="<%=contextPath%>/stu/planclock!updatePlanClock.action" method="post" name="planClock" Onsubmit="return onSubmit();">
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
			<tr >
				<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top">
					<font class="lists_fleft">查看/修改 提醒信息</font>
					<font class="lists_fright">
					</font>
				</td>
				<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
			</tr>
			<tr>
			<td width="12"  class="lists_bor"></td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr height="30">
					<td width="20%">
						<font style="color: red">*</font>ID号：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:property value="planClock.clockId"/>" readonly="readonly" name="queryPlanclockCondition.clockId" id="clockId"/>
						<input type="hidden" value="<s:date name="planClock.createdate" format="yyyy-MM-dd HH:mm:ss" />" name="queryPlanclockCondition.createdate" id="createdate"/>
						<input type="hidden" value="<s:property value="planClock.coursename"/>" name="queryPlanclockCondition.coursename" id="coursename" />
						<input type="hidden" value="<s:property value="planClock.isent"/>" name="queryPlanclockCondition.isent" id="isent"/>
					</td>																			
				</tr>
					
				<tr height="30">
					<td>
						<font style="color: red">*</font>提醒类型：
					</td>
					<td class="lists_tleft" colspan="2">
						<select name="queryPlanclockCondition.ctype" id="ctype">
							<option value="0" <s:if test="planClock.ctype == 0">selected="selected"</s:if>>系统提醒</option>
						</select>
					</td>
				</tr>
				
				<tr height="30">
					<td>
						<font style="color: red">*</font>所属项目：
					</td>
					<td class="lists_tleft" colspan="2">
						<s:select list="subjectList" id="subjectId" name="queryPlanclockCondition.subjectId"  listKey="subjectId" listValue="subjectName" headerKey="-1" headerValue="--所属项目--"  value="planClock.subjectId" onchange="getSubjectName(this)">
						</s:select>
						<input type="hidden" value="<s:property value="planClock.subjectnamne"/>" name="queryPlanclockCondition.subjectnamne" id="subjectnamne" />
					</td>
				</tr>
				<tr height="30">
					<td style="height:30px; line-height:30px; vertical-align:middle;">
						<font style="color: red">*</font>提醒标题:
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" name="queryPlanclockCondition.ctitle" id="ctitle" style="font-size:12px" value="<s:property value="planClock.ctitle"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td style="height:30px; line-height:30px; vertical-align:middle;">
						<font style="color: red">*</font>提醒内容:
					</td>
					<td class="lists_tleft" colspan="2">
						<textarea rows="3" cols="50" name="queryPlanclockCondition.ccontent" id="ccontent" style="font-size:12px"><s:property value="planClock.ccontent" /></textarea>
					</td>
				</tr>
				<tr height="30">
					<td style="height:30px; line-height:30px; vertical-align:middle;">
						<font style="color: red">*</font>提醒时间:
					</td>
					<td class="lists_tleft" colspan="2">
						开始时间：<input type="text" value="<s:date name="planClock.startdate" format="yyyy-MM-dd HH:mm:ss" />" name="queryPlanclockCondition.startdate" id="startdate" 
						readonly onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  onchange="startVSendTime()"/><br /><br>
						结束时间：<input type="text" value="<s:date name="planClock.enddate" format="yyyy-MM-dd HH:mm:ss" />" name="queryPlanclockCondition.enddate" id="enddate" 
						readonly onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"   onchange="endVSendTime()"/><br />
					<input type="checkbox" checked="checked" disabled="disabled" name="isAllDay" id="isAllDay"/>全天
					</td>
				</tr>	
				<tr height="30">
					<td>
						是否重复：
					</td>
					<td class="lists_tleft" colspan="2">
						<select disabled="disabled" name="queryPlanclockCondition.issended" id="issended">
							<option value="0" <s:if test="planClock.issended == 0">selected="selected"</s:if>>无</option>
							<option value="1" <s:if test="planClock.issended == 1">selected="selected"</s:if>>每天</option>
							<option value="2" <s:if test="planClock.issended == 2">selected="selected"</s:if>>每月</option>
							<option value="3" <s:if test="planClock.issended == 3">selected="selected"</s:if>>每年</option>
						</select>
					</td>
				</tr>	
				<tr height="30">
					<td>
						<font style="color: red">*</font>提醒状态：
					</td>
					<td class="lists_tleft" colspan="2">
						<select name="queryPlanclockCondition.cstatus" id="cstatus">
							<option value="1" <s:if test="planClock.cstatus == 1">selected="selected"</s:if>>发布</option>
							<option value="2" <s:if test="planClock.cstatus == 2">selected="selected"</s:if>>新稿</option>
							<option value="0" <s:if test="planClock.cstatus == 0">selected="selected"</s:if>>删除</option>
						</select>
					</td>
				</tr>
				<tr height="30">
					<td>
						提醒邮件发送时间：
					</td>
					<!-- <td class="lists_tleft" colspan="2">
						<input type="text" value="<s:date name="planClock.alertdate" format="yyyy-MM-dd HH:mm:ss" />" name="queryPlanclockCondition.alertdate" id="alertdate" 
						readonly onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="sendVSendTime()/>&nbsp;&nbsp;(在开始时间----结束时间之间)
					</td> -->
					<td class="lists_tleft" colspan="2">
						<input type="text" name="queryPlanclockCondition.alertdate" id="alertdate" readonly  value="<s:date name="planClock.alertdate" format="yyyy-MM-dd HH:mm:ss" />"
						onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="sendVSendTime()"/>&nbsp;&nbsp;(在开始时间----结束时间之间)
					</td>
				</tr>
				<!-- 	
				<tr height="30">
					<td>
						创建时间：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:date name="planClock.createdate" format="yyyy-MM-dd HH:mm:ss" />" readonly  name="queryPlanclockCondition.createdate" id="createdate"/>
					</td>
				</tr>
				 -->	
			<tr >
				<td colspan="3"><input type="submit" value="保存"/> | <input type="button" value="关闭"  onclick="clocs();"/></td>
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
	</div>	
		
	</body>
</html>
