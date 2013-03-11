<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加用户</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
  <script type="text/javascript">
	$(document).ready(function() {
		// 初始化 Kindeditor
    	        KE.show({
    	                    id : 'content',
    	                    resizeMode : 1,
    	                    allowPreviewEmoticons : false,
    	                    allowUpload : true,
    	                    syncType : 'auto',
    	                    urlType : 'absolute',
    	                    imageUploadJson : 'http://tp.highso.cn:8080/upload!article.action',
    	                    allowFileManager : false,
    	                    items : ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|', 'selectall', '-','title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|', 'image','flash', 'media', 'advtable', 'hr', 'emoticons', 'link', 'unlink']
    	                });
	});

	function check(){
		var subjectId = $("#subjectId").val();
		if(subjectId==-1||subjectId==""){
			alert("请选择专业！");
			return false;
		}
		var $title = $("#title").val();
		var title = $.trim($title);
		if(title==""){
			alert("请输入公告标题！");
			return false;
		}
		var $author = $("#author").val();
		var author = $.trim($author);
		if(author==""){
			alert("请输入发布作者！");
			return false;
		}

		var $content = $("#content").val();
		var content = $.trim($content);
		if(content==""){
			alert("请输入公告内容！");
			return false;
		}
		return true;
	}

	function yulan()
	{	
		var check = this.check();
		if(check){
			document.acmentForm.action="<%=contextPath%>/cms/acment!preview.action";
			document.acmentForm.target = "_blank";
			document.acmentForm.submit();
			document.acmentForm.target = "";
			document.acmentForm.action="<%=contextPath%>/cms/acment!add.action";
		}
	}
	
</script>
<style>
<!--
	.w20_m-p6 input{
		width: 20px;
		height:15px;
	}
	#selectT select{
		float: left;margin-left:5px;display:inline;
	}
-->
</style>
</head>
<body>
<div id="rightframe">
	<form action="<%=contextPath%>/cms/acment!edit.action" id="acmentForm" name="acmentForm" method="post" onsubmit="return check();" >
	<input type="hidden" name="announcement.id" value="<s:property value="announcement.id"/>" />
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td style="font-size:0px;width:12px;">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">修改公告</font>
			</td>
			<td style="font-size:0px;width:12px;">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr style="height: 30px">
		    		<td width="10%">*所属专业：</td>
		    		<td class="lists_tleft" width="90%">
							<s:select list="sujectbList" headerKey="-1"
								headerValue="请选择专业..." listKey="subjectId"
								listValue="subjectName" name="announcement.subjectId"
								id="subjectId" >
							</s:select>
					</td>
		    	</tr>
		    	<tr style="height: 30px">
		    		<td width="10%">*公告标题：</td>
		    		<td class="lists_tleft" width="90%">
		    			<input type="text" name="announcement.title" id="title" value="<s:property value="announcement.title"/>" maxlength="100" />
		    		</td>
		    	</tr>
		    	<tr style="height: 30px">
		    		<td width="10%">*发布作者：</td>
		    		<td class="lists_tleft" width="40%"> 
		    			<input type="text" name="announcement.author" id="author" value="<s:property value="announcement.author"/>" maxlength="50" />
		    		</td>
		    	</tr>
		    	<tr style="height: 30px">
		    		<td width="10%">*公告状态：</td>
		    		<td class="lists_tleft" width="40%"> 
		    			<select id="status" name="announcement.status">
		    				<option value="1" <s:property value="announcement.status==1?'selected=\"selected\"':''"/>>显示</option>
		    				<option value="0" <s:property value="announcement.status==0?'selected=\"selected\"':''"/>>隐藏</option>
		    			</select>
		    		</td>
		    	</tr>	   	
				<tr>
		    		<td width="10%">*公告内容：</td>
		    		<td width="90%" class="lists_tleft" align="left">
		    		<textarea id="content" name="announcement.content" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;"><s:property value="announcement.content"/></textarea>
					</td>
		    	</tr>
		    	<tr>
		    		<td style="text-align: center;" colspan="4"><input class="submit" type="submit" value="提交"/>	<input class="submit" type="button" value="公告预览" onclick="yulan()" /></td>
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
