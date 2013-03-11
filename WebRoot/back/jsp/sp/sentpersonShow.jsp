<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改接收人信息</title>
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
		function onSubmit(){
			var falg = false;   
			var personName = document.getElementById("personName").value;
			var personPhone = document.getElementById("personPhone").value;
			
			if (personPhone.match(/^0*(13|15|18)\d{9}$/)){
				 falg = true;
			}  
			
			if(personName==""){
				alert("接收人姓名不能为空！");
				var pName=document.getElementById("personName");
				pName.focus();
				return false;
			}else if(personPhone==""){
				alert("接收人电话不能为空！");
				var pPhone=document.getElementById("personPhone");
				pPhone.focus();
				return false;
			}else if(personPhone!="" && !falg){
				alert("请输入正确的电话号码！");
				var pPhone=document.getElementById("personPhone");
				pPhone.focus();
				return false;
			}else{
				alert("修改成功！");
				return  true;
			}
			
		}
			
		//关闭
		function clocs()
		{
			var aBlean=confirm("数据还未提交，你确认关闭该页面？");
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
		<form action="<%=contextPath%>/sp/sentperson!updateSentPerson.action" method="post" name="sentPersonForm" id="sentPersonForm" onsubmit="return onSubmit();">
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
			<tr >
				<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top">
					<font class="lists_fleft">修改接收人信息</font>
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
					<td class="lists_tright">
						<font style="color: red">*</font>接收人姓名：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="hidden" value="<s:property value='sentPerson.Id'/>" name="querySentPersonCondition.Id" id="Id"/>
						<input type="text" value="<s:property value='sentPerson.personName'/>" name="querySentPersonCondition.personName" id="personName" style="width:180px;"/>
					<span class="jqVaildate"></span>
					
					</td>
				</tr>
				
				<tr height="30">
					<td class="lists_tright">
						<font style="color: red">*</font>接收人电话号码：
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:property value='sentPerson.personPhone'/>" name="querySentPersonCondition.personPhone" id="personPhone" style="width:180px;"/>
					<span class="jqVaildate"></span>
					</td>
				</tr>
				
				
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
