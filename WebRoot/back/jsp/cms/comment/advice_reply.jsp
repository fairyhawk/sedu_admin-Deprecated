<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>回复意见</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
<script type="text/javascript" src="<%=contextPath%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
			$("#lengthwarn").css("display","none");
			// 初始化 Kindeditor
   	        KE.show({
   	                    id:'tmpContent',
   	                    resizeMode : 1,
   	                    allowPreviewEmoticons : false,
   	                    allowUpload : true,
   	                    syncType : 'auto',
   	                    urlType : 'absolute',
   	                    
   	                    allowFileManager : false,
   	                    items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
   	                        'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
   	                        'code', '|']
   	                });
	});
	function check(){
		
		if(KE.count('tmpContent','text')==0){
			alert("必须写入回复内容！");
			return false;
		}
		if(KE.count("tmpContent")>200){
			$("#lengthwarn").css("display","block");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/cms/comment!replyAdvice.action" method="post" name="addForm" id="addForm" onsubmit="return check();">
	<input type="hidden" value="<s:property value="comment.cmtId"/>" name="comment.sourceId" />
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">回复意见</font>
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
					<td width="10%">
						<font color="red">*</font>意见内容
					</td>
					<td class="lists_tleft" width="90%">
						<s:property value="comment.cmtInfo"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>回复内容
					</td>
					<td class="lists_tleft">
						<textarea name="comment.cmtInfo" style="width:500px;height:250px;margin:3px;visibility:hidden;" id="tmpContent" class="{required:true}" ></textarea>
						
							<div id="lengthwarn">
							<span style="color:red;">
							*字符不能超过200个。
							</span>
							</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
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
