<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改学习计划</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" href="<%=contextPath%>/styles/usercenter/uc_public.css" type="text/css"></link>
				<link rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css" type="text/css"></link>
		
		<SCRIPT>

		//  提交时候进行校验；
		function onSubmit()
		{	
			var pstatusVal = document.getElementById("pstatus").value;
			var contentVal = document.getElementById("content").value;
			
			if(pstatusVal==1 && contentVal =="" ){
				$("#content").val("管理员审核通过");
			}else if(pstatusVal!=1 && contentVal ==""){
				$("#content").val("管理员审核删除");
			}
			
			var xq = document.getElementById("xq").value;
			var jh = document.getElementById("jh").value;
			var jyl = document.getElementById("jyl").value;
			var xlqd = document.getElementById("xlqd").value;
			var jb = document.getElementById("jb").value;
			var shuqian = document.getElementById("shuqian").value;
			var xinde = document.getElementById("xinde").value;
			var tmrplan = document.getElementById("tmrplan").value;

			if(xq==""){
				alert("今日心情不能为空！");
				var xq=document.getElementById("xq");
				xq.focus();
				return false;
			}else if(jh==""){
				alert("学习计划不能为空！");
				var jh=document.getElementById("jh");
				jh.focus();
				return false;
			}else if(jyl==""){
				alert("记忆力、注意力不能为空！");
				var jyl=document.getElementById("jyl");
				jyl.focus();
				return false;
			}else if(xlqd==""){
				alert("学习效率、强度不能为空！");
				var xlqd=document.getElementById("xlqd");
				xlqd.focus();
				return false;
			}else if(jb==""){
				alert("比起昨天的进步不能为空！");
				var jb=document.getElementById("jb");
				jb.focus();
				return false;
			}else if(shuqian==""){
				alert("我的书签、备忘不能为空！");
				var shuqian=document.getElementById("shuqian");
				shuqian.focus();
				return false;
			}else if(xinde==""){
				alert("学习心得、体会不能为空！");
				var xinde=document.getElementById("xinde");
				xinde.focus();
				return false;
			}else if(tmrplan==""){
				alert("明日计划不能为空！");
				var tmrplan=document.getElementById("tmrplan");
				tmrplan.focus();
				return false;
			}else if(document.getElementById("plantitle").value==""){
				alert("计划标题不能为空！");
				var plantitle=document.getElementById("plantitle");
				plantitle.focus();
				return false;
			}else if(document.getElementById("publish").value==""){
				alert("请选择发布时间！");
				var publish=document.getElementById("publish");
				publish.focus();
				return false;
			}else if(document.getElementById("pstatus").value==""){
				alert("计划状态不能为空！");
				var pstatus=document.getElementById("pstatus");
				pstatus.focus();
				return false;
			}else if(document.getElementById("picon").value==""){
				alert("计划今日图标不能为空！");
				var picon=document.getElementById("picon");
				picon.focus();
				return false;
			}else{
				alert("修改保存成功！");
				return true;
			}
		}
	
		//关闭
		function clocs()
		{
			var aBlean=confirm("修改还未保存成功，你确认关闭该页面？");
			if(aBlean==true)
			{
				window.location="javascript:history.go(-1)";
			}else{
				return false;
			}
		}	
		</SCRIPT>
	</head>
	<body>
		<div>
		<form action="<%=contextPath%>/stu/plan!updatePlan.action?planitem.planId=<s:property value='planitem.planId'/>"" method="post" name="planForm" id="planForm" onsubmit="return onSubmit();">
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
			<tr >
				<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top">
					<font class="lists_fleft">[	
						<s:if test="customer.cusName == null">
							<s:property value="customer.email"/>
						</s:if>
						<s:elseif test="customer.cusName ==''">
							<s:property value="customer.email"/>
						</s:elseif>
						<s:else>
							<s:property value="customer.cusName"/>
						</s:else> ]--[
						<s:property value="plan.plantitle"/>
						]
						</font>
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
					<td width="20%" class="lists_tright">
						<font style="color: red">*</font>ID号：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:property value="plan.planId"/>" readonly="readonly" name="queryPlanCondition.planId" id="planId"/>
					</td>																			
				</tr>
				<tr height="30">
					<td class="lists_tright">
						<font style="color: red">*</font>计划标题：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:property value="plan.plantitle"/>" name="queryPlanCondition.plantitle" id="plantitle" style="width:180px;"/>
					<span class="jqVaildate"></span>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						<font style="color: red">*</font>学员昵称：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" readonly="readonly" value="<s:property value="customer.cusName"/>" name="" id="" style="width:180px;"/>
						<span class="jqVaildate"></span>
					</td>
				</tr>
				
				<tr height="30">
					<td class="lists_tright">
						<font style="color: red">*</font>学员邮箱：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" readonly="readonly" value="<s:property value="customer.email"/>" name="" id="" style="width:180px;"/>
						<span class="jqVaildate"></span>
					</td>
				</tr>
			
				<tr height="30">
					<td style="height:30px; line-height:30px; vertical-align:middle;" class="lists_tright">
						<font style="color: red">*</font>发布时间：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:date name="plan.publish" format="yyyy-MM-dd HH:mm:ss" />" name="queryPlanCondition.publish" id="publish" 
						readonly onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"/><br /><br>
					</td>
				</tr>
				
				<!-- 处理时间有数据时 显示,否者不显示 -->
				<s:if test="plan.handledate != null">
				<tr height="30">
					<td style="height:30px; line-height:30px; vertical-align:middle;" class="lists_tright">
						处理时间：
						<input type="hidden" value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>" name="queryPlanCondition.handledate" id="handledate"/>
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:date name="plan.handledate" format="yyyy-MM-dd HH:mm:ss" />" name="queryPlanCondition.handledate" 
						readonly disabled="disabled"/><br /><br>
					</td>
				</tr>
				</s:if>
				<s:else>
					<input type="hidden" value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>" name="queryPlanCondition.handledate" id="handledate"/>
				</s:else>
				
				<tr height="30">
					<td class="lists_tright">
						<font style="color: red">*</font>状态：
					</td>
					<td class="lists_tleft" colspan="2">
						<select name="queryPlanCondition.pstatus" id="pstatus">
							<option value="1" <s:if test="plan.pstatus == 1">selected="selected"</s:if>>发布</option>
							<option value="0" <s:if test="plan.pstatus == 0">selected="selected"</s:if>>未审核</option>
							<option value="2" <s:if test="plan.pstatus == 2">selected="selected"</s:if>>删除</option>
						</select>
					</td>
				</tr>
				<tr height="300">
					<td class="lists_tright">
						<font style="color: red">*</font>计划内容：
					</td>
					<td class="lists_tleft" colspan="2">
						<b><font color="blue">今日心情：</font></b><br>
						<textarea rows="2" cols="50" name="planitem.itemtitle" id="xq"><s:property value="strArr[0]" /></textarea><br/><br/>
						
						<b><font color="blue">学习计划：</font></b><br>
						<textarea rows="2" cols="50" name="planitem.itemtitle" id="jh"><s:property value="strArr[1]" /></textarea><br/><br/>
						
						<b><font color="blue">记忆力、注意力：</font></b><br>
						<textarea rows="2" cols="50" name="planitem.itemtitle" id="jyl"><s:property value="strArr[2]" /></textarea><br/><br/>
						
						<b><font color="blue">学习效率、强度：</font></b><br>
						<textarea rows="2" cols="50" name="planitem.itemtitle" id="xlqd"><s:property value="strArr[3]" /></textarea><br/><br/>
						
						<b><font color="blue">比起昨天的进步：</font></b><br>
						<textarea rows="2" cols="50" name="planitem.itemtitle" id="jb"><s:property value="strArr[4]" /></textarea><br/><br/>
						
						<b><font color="blue">我的书签、备忘：</font></b><br>
						<textarea rows="2" cols="50" name="planitem.itemtitle" id="shuqian"><s:property value="strArr[5]" /></textarea><br/><br/>
						
						<b><font color="blue">学习心得、体会：</font></b><br>
						<textarea rows="2" cols="50" name="planitem.itemtitle" id="xinde"><s:property value="strArr[6]" /></textarea><br/><br/>
						
						<b><font color="blue">明日计划：</font></b><br>
						<textarea rows="2" cols="50" name="planitem.itemtitle" id="tmrplan"><s:property value="strArr[7]" /></textarea><br/><br/>
						
						<b><font color="blue">今日图标：</font></b><br>
						<select name="queryPlanCondition.picon" id="picon">
							<option value="1" <s:if test="plan.picon == 1">selected="selected"</s:if>>开心</option>
							<option value="2" <s:if test="plan.picon == 2">selected="selected"</s:if>>汗</option>
							<option value="3" <s:if test="plan.picon == 3">selected="selected"</s:if>>流泪</option>
							<option value="4" <s:if test="plan.picon == 4">selected="selected"</s:if>>不高兴</option>
							<option value="5" <s:if test="plan.picon == 5">selected="selected"</s:if>>怒</option>
							<option value="6" <s:if test="plan.picon == 6">selected="selected"</s:if>>笑</option>
							<option value="7" <s:if test="plan.picon == 7">selected="selected"</s:if>>困</option>
							<option value="8" <s:if test="plan.picon == 8">selected="selected"</s:if>>吓</option>
							<option value="9" <s:if test="plan.picon == 9">selected="selected"</s:if>>酷</option>
						</select>
					</td>
				</tr>
				<tr height="30">
					<td style="height:30px; line-height:30px; vertical-align:middle;" class="lists_tright">
						<font style="color: red">*</font>计划备注：
					</td>
					<td class="lists_tleft" colspan="2">
					<s:if test="plan.content.length()>0">
						<textarea rows="4" cols="50" name="queryPlanCondition.content" id="content"><s:property value="plan.content"/></textarea><br/>
					</s:if>
					<s:else>
						<textarea rows="4" cols="50" name="queryPlanCondition.content" id="content">管理员审核通过</textarea><br/>
					</s:else>
					<span class="jqVaildate"></span>
					</td>
				</tr>
			<tr >
				<td colspan="3"><input type="submit" value="提交"/> | <input type="button" value="关闭"  onclick="clocs();"/></td>
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
