<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加教师</title>	
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<style type="text/css">
	#teacherImg
	{
	    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
	}
</style>
<script type="text/javascript">
	$().ready(function() {
			$("#addForm").validate();
		});
		
	function goBack() {
		window.history.back();
	}
	
	function changeImg() {
		var def = document.getElementById("defaultImg");
		var img = document.getElementById("teacherImg");
		var ipt = document.getElementById("files");
		if(ipt.value != '') {
			if(IsIE()) {
				def.style.display = "none";
				img.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = ipt.value;
				def
			} else {
				def.src = ipt.files.item(0).getAsDataURL();
			}
		} else {
			if(IsIE()) {
				def.style.display = "block";
			} else {
				def.src = "<%=contextPath %>/back/images/teacher_default.JPG";
			}
		}
	}
	
	function IsIE() {
	    if (window.navigator.userAgent.indexOf("MSIE")>=1) {
	        //IE浏览器
	        return true;
	    }else{
	        return false;
	    }
	}
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/cou/teacher!addTeacher.action" method="post" name="addForm" id="addForm" enctype="multipart/form-data">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加教师</font>
				<font class="lists_fright">
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
						<td>
							头像
						</td>
						<td class="lists_tleft">
							<div id="teacherImg" style="width:122px;height:122px;"><img id="defaultImg" src="<%=contextPath %>/back/images/teacher_default.JPG"/></div>
							<input type="file" name="files" id="files" onchange="changeImg()"/>
						</td>
					</tr>
					<tr height="30">
						<td>
							<font color="red">*</font>教师名
						</td>
						<td class="lists_tleft">
							<input type="text" name="teacher.name" id="name" class="{required:true,maxlength:20}"/>
						</td>
					</tr>
					<tr height="30">
						<td>
							<font color="red">*</font>教育背景
						</td>
						<td class="lists_tleft">
							<input type="text" name="teacher.education" id="education" class="{required:true,maxlength:100}" />
						</td>
					</tr>
					<tr height="30">
						<td>
							项目 
						</td>
						<td class="lists_tleft">
							<s:select name="subjectId" id="subId" list="subjectList" listKey="subjectId" listValue="subjectName"  headerKey="0"
								headerValue="请选择" theme="simple">
							</s:select>
						</td>
					</tr>
					<tr height="30">
						<td>
							明星教师
						</td>
						<td class="lists_tleft">
							<select name="teacher.isStar" id="isStar">
								<option value="1">明星教师</option>
								<option value="0">普通教师</option>
							</select>
						</td>
					</tr>
					<tr height="30">
						<td>
							<font color="red">*</font>从业简介
						</td>
						<td class="lists_tleft">
							<textarea rows="12" cols="80" name="teacher.career" id="career" class="{required:true,maxlength:1000}"></textarea>
						</td>
					</tr>
					<tr height="30">
						<td colspan="2">
							<input type="submit" value="提交"/><input type="button" value="返回" onclick="goBack()"></input>
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
