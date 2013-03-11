<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加新模板</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript">
	$().ready(function() {
			$("#addForm").validate();
		});
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/cms/template!addTemplate.action" method="post" name="addForm" id="addForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加模板</font>
				<font class="lists_fright">
					<!-- 
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:addNotes()">添加</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:delNotes()">删除</a></td></tr></table>
					 -->
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td class="lists_tright">
						<font color="red">*</font>模板名：
					</td>
					<td class="lists_tleft">
						<input type="text" name="template.tmpName" id="template.tmpName" class="{required:true,minlength:4,maxlength:50}" />
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						<font color="red">*</font>模板类型：
					</td>
					<td class="lists_tleft">
						<select id="tmpType" name="template.tmpType">
							<option value="0">栏目模板</option>
							<option value="1">文章模板</option>
							<option value="2">其他模板</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						<font color="red">*</font>模板内容：
					</td>
					<td class="lists_tleft">
						<textarea name="template.tmpContent" style="width:720px;height:300px" id="tmpContent" class="{required:true}" ></textarea>
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						<font color="red">*</font>是否可用：
					</td>
					<td class="lists_tleft">
						<select name="template.isUse" id="isUse">
							<option value="1">可用</option>
							<option value="0">不可用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lists_tright"></td>
					<td class="lists_tleft">
						<input type="submit" value="增加"/><input type="button" value="返回" onclick="history.go(-1)"></input>
					</td>
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
