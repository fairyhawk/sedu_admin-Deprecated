<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改教师信息</title>
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
			$("#updateForm").validate();
			var picPath = '<s:property value="teacher.picPath"/>';
			if(picPath != "") {
				if(IsIE()) {
				document.getElementById("defaultImg").style.display = "none";
				document.getElementById("teacherImg").filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = "<%=contextPath %>/cou/teacher!download.action?downloadFileName=" + picPath;
			} else {
				document.getElementById("defaultImg").src = "<%=contextPath %>/cou/teacher!download.action?downloadFileName=" + picPath;
			}
		}
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
				def.src = "<%=contextPath %>/images/teacher_default.JPG"
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
<body>
<div>
	<form action="<%=contextPath %>/cou/teacher!updateTeacher.action" method="post" name="updateForm" id="updateForm" enctype="multipart/form-data">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">修改教师信息</font>
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
						<input type="hidden" name="teacher.picPath" value="<s:property value='teacher.picPath'/>"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>教师名
					</td>
					<td class="lists_tleft">
						<input type="text" name="teacher.name" id="name" value="<s:property value='teacher.name'/>" class="{required:true,maxlength:20}"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>教育背景
					</td>
					<td class="lists_tleft">
						<input type="text" name="teacher.education" id="education" value="<s:property value='teacher.education'/>" class="{required:true,maxlength:100}"/>
					</td>
				</tr>
				<tr height="30">
						<td>
							项目 
						</td>
						<td class="lists_tleft">
							<s:select name="subjectId" id="subId" list="subjectList" listKey="subjectId" listValue="subjectName" value="subjectId"  headerKey="0"
								headerValue="请选择" theme="simple">
							</s:select>
						</td>
					</tr>
				<tr height="30">
					<td>
						明星教师
					</td>
					<td class="lists_tleft">
						<select name="teacher.isStar" id="isStar" >
							<option value="1" <s:property value="teacher.isStar==1?'selected':''"/>>明星教师</option>
							<option value="0" <s:property value="teacher.isStar==0?'selected':''"/>>普通教师</option>
						</select>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>从业简介
					</td>
					<td class="lists_tleft">
						<textarea rows="12" cols="80" name="teacher.career" id="career" class="{required:true,maxlength:1000}"><s:property value='teacher.career'/></textarea>
					</td>
				</tr>
				<tr height="30">
					<td colspan="2">
						<input type="hidden" name="teacher.tcId" id="tcId" value=<s:property value="teacher.tcId"/> />
						<input type="submit" value="修改"/><input type="button" value="返回" onclick="goBack()"></input>
					</td>
				</tr height="30">
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
