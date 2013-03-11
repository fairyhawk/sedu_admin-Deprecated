<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加班级</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>

		<script type="text/javascript">
		$().ready(function() {

			$("#clazzform").validate({   
		        rules: {
		    
		        	"clazz.subjectId":{
		        		required:true,
		        		min:0
		        	}
		        },   
		        messages: {   
		        	"clazz.subjectId":{
		        		required:"请选择专业",
		        		min:"请选择专业"
		        	}
		        }   
   	 		});  
		})	
		
			
		function IsIE() {
		    if (window.navigator.userAgent.indexOf("MSIE")>=1) {
		        //IE浏览器
		        return true;
		    }else{
		        return false;
		    }
		    if (window.navigator.userAgent.indexOf("Firefox")>=1){
		    	return true;
		    }else{
		    	return false;
		    }

		    
		}

		function FCKeditor_OnComplete(editorInstance) {
			window.status = editorInstance.Description;
		}
		
		function changeMainPic(ipt) {
				var img = document.getElementById("mainpicImg");
				var div = document.getElementById("mainpicDiv");
				if(ipt.value != '') {
					if(IsIE()) {
						ipt.select();    
		        		var imgSrc = document.selection.createRange().text; 
						div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
						div.style.height = "100px";
						div.style.width = "100px";
					} else {
						img.src = window.URL.createObjectURL(ipt.files.item(0)); 
						img.style.display = "";
					}
				} else {
					img.style.display = "none";
				}
		}
		
		function changePic(ipt) {
				var img = document.getElementById("clazzPicImg");
				var div = document.getElementById("clazzPicDiv");
				if(ipt.value != '') {
					if(IsIE()) {
						ipt.select();    
		        		var imgSrc = document.selection.createRange().text; 
						div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
						div.style.height = "100px";
						div.style.width = "100px";
					} else {
						img.src = window.URL.createObjectURL(ipt.files.item(0)); 
						img.style.display = "";
					}
				} else {
					img.style.display = "none";
				}
			 } 

		</script>
	</head>
	<body>
<div>
	<form action="cou/clazz!updateClazz.action" method="post" name="clazzform" id="clazzform" enctype="multipart/form-data">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">班级添加</font>
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
				<tbody id="tagTb">
					<tr>
						<td width="200">
							<font color="red">*</font>班级名称
						</td>
						<td class="lists_tleft">
							<input type="hidden" name="clazz.clazzId" value='<s:property value="clazz.clazzId"/>'/>
							<input type="text" name="clazz.title" id="clazz.title" value='<s:property value="clazz.title"/>'/>
							<input type="hidden" name="clazz.clazzId" id="clazz.clazzId" value='<s:property value="clazz.clazzId"/>'/>
						</td>
					</tr>
					
					<tr>
						<td>
							<font color="red">*</font>所属专业
						</td>
						<td class="lists_tleft">
							<s:select name="clazz.subjectId" id="clazz.subjectId"
								list="subjectList" listKey="subjectId" listValue="subjectName" headerKey="-1"
								headerValue="请选择" theme="simple">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td>
							<font color="red">*</font>年级
						</td>
						<td class="lists_tleft">
							<s:select name="clazz.gradeId" id="clazz.gradeId"
								list="gradeList" listKey="gradeId" listValue="gradeName" headerKey="-1"
								headerValue="请选择" theme="simple">
							</s:select>
						</td>
					</tr>
					
					
					<tr>
						<td>
							<font color="red">*</font>描述
						</td>
						<td class="lists_tleft">
							<textarea rows="" cols="" name="clazz.introduce"class="{required:true,minlength:4,maxlength:1000}" style="height:80px;width:99%;"><s:property value="clazz.introduce"/></textarea>
						</td>
					</tr>
					
					<tr>
						<td>
							<font color="red">*</font>优惠金额
						</td>
						<td class="lists_tleft">
							<input name="clazz.clazzOfferPrice" type='text'value='<s:property value="clazz.clazzOfferPrice"/>'/>
						</td>
					</tr>
					
					<tr>
						<td>
							<font color="red">*</font>班级在首页图片
						</td>
						<td class="lists_tleft">
							<input type="file" name="clazzIndexPic" id="clazzIndexPic"
								 onchange="changeMainPic(this)"/>
							<div class="uploadPic" id="mainpicDiv"></div>
							<img id="mainpicImg" src="<%=contextPath %>/back/upload/clazz/<s:property value='clazz.clazzIndexPic'/>"/>
						</td>
					</tr>
					
					<tr>
						<td>
							<font color="red">*</font>班级页图片
						</td>
						<td class="lists_tleft">
							<input type="file" name="clazzPic" id="clazzPic"
								 onchange="changePic(this)"/>
							<div class="uploadPic" id="clazzPicDiv"></div>
							<img id="clazzPicImg"  src="<%=contextPath %>/back/upload/clazz/<s:property value='clazz.clazzPic'/>" />
						</td>
					</tr>
				</tbody>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
				<s:submit value="提交" />
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
