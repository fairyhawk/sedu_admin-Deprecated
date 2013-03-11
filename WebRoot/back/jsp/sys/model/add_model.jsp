<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>增加模块</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" href="<%=contextPath%>/styles/usercenter/uc_public.css" type="text/css"></link>
		<link rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css" type="text/css"></link>
		<script language="javascript">		
		function onSubmit(){
			if(document.getElementById("modelName").value==""){
				var modelName=document.getElementById("modelName");
				alert("请输入模块名称！");
				modelName.focus();
				return false;
			}
			if(document.getElementById("url").value==""){
				var remark=document.getElementById("url");
				alert("请输入模块URL地址！");
				remark.focus();
				return false;
			}
			var sort=document.getElementById("sort").value;
			if(isNaN(sort)){
				alert("排序值只能为数字！");
				var remark=document.getElementById("sort");
				remark.focus();
				return false;
			}
		}
		
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
		</script>
	</head>
	<body>
		<div>
			<form action="<%=contextPath%>/sys/model!createModel.action" method="post" onsubmit="return onSubmit();">
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr >
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">新建模块信息</font>
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
							<font style="color: red">*</font>模块名称：
						</td>
						<td class="lists_tleft">
							<input type="text" value="" name="model.model_name" id="modelName" style="width:180px;"/>
						</td>
					</tr>
					<tr height="30">
						<td class="lists_tright">
							<font style="color: red">*</font>模块URL：
						</td>
						<td class="lists_tleft">
							<input type="text" value="" name="model.url" id="url" style="width:180px;"/>
						</td>
					</tr>
					<tr height="30">
						<td class="lists_tright">
							<font style="color: red">*</font>模块内容：
						</td>
						<td class="lists_tleft">
							<textarea name="model.content" id="content" style="width:650px;height:500px"></textarea>
						</td>
					</tr>
					<tr height="30">
						<td class="lists_tright">
							<font style="color: red">*</font>所属模板：
						</td>
						<td class="lists_tleft">
							<input type="text" value="" name="model.m_id" id="mid" style="width:180px;"/>
						</td>
					</tr>
					<tr height="30">
						<td class="lists_tright">
							<font style="color: red">*</font>是否可用：
						</td>
						<td class="lists_tleft">
							<input type="radio"  name="model.is_enabled" value="0" checked="checked"/>是
							<input type="radio"  name="model.is_enabled" value="1"/>否
						</td>
					</tr>
					<tr height="30">
						<td class="lists_tright">
							排序：
						</td>
						<td class="lists_tleft">
							<input type="text" value="" name="model.sort" id="sort" style="width:180px;"/>
						</td>
					</tr>
					<tr height="30">
						<td class="lists_tright">
							模块备注：
						</td>
						<td class="lists_tleft">
							<input type="text" value="" name="model.remark" id="remark" style="width:600px; height: 100px;"/>
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
