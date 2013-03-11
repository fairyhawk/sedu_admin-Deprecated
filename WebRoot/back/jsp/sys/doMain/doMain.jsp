<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<!--
<script type="text/javascript" src="<%=contextPath%>/js/web/public/copy.js"></script>
-->
<script type="text/javascript">
$(document).ready(function(){
	$("#copy").click(function(){
		if(v("url") && v("folderUrl")){
			$.ajax({
				url: "cms/domain!CopyFile.action",
				data:{
					"url":$("#url").val(),
					"folderUrl":$("#folderUrl").val()
				},
				type : "post",
				cache : false,
				success : function(result) {
					$("#url").attr("value","");
					$("#folderUrl").attr("value","");	
					if(result=="error01"){
						alert("未发现文件!");
					}
					else{
						if(result=="error02"){
							alert("写入错误!");
						}
						else{
							alert("成功！");						
						}						
					}
				},
				error : function(error) {
					alert("系统繁忙，请稍后重试！");
				}
			});			
		}else 
		{
			if(!v("folderUrl")){
			$("#folderUrl").attr("value","");
			alert("文件夹名称输入不正确，请重新输入！");
		    }else if(!v("url")){
			$("#url").attr("value","");
			alert("文件路径不正确，请重新输入！");
		    }
		}
	});
});

function v(id){
	if(($("#"+id).val()=="")){
		return false;
	}else{
		return true;
	}
}
</script>
</head>
 <body>
 	<div id="rightframe">
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
			<tr>
				<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
				<td class="lists_top">
					<font class="lists_fleft">文件</font>
					<font class="lists_fright"></font>
				</td>
				<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
			</tr>
			<tr>
				<td class="lists_bor"></td>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr>
							<td style="width:10%">
								<font color="red">*</font>文件路径：
							</td>
							<td class="lists_tleft">
								<input type="text" id="url" size="40" maxlength="40"/>
							</td>
						</tr>
						<tr>
							<td style="width:10%">
								<font color="red">*</font>文件夹名称：
							</td>
							<td class="lists_tleft">
								<input type="text" id="folderUrl" size="40" maxlength="40" onkeyup="value=value.replace(/[\W]/g,'') "  />
							</td>
						</tr>
						<tr>
							<td style="width:10%">
								<font color="red">*</font>操作：
							</td>
							<td class="lists_tleft">
								<input id="copy" type="button" value="提交"/>
							</td>
						</tr>
					</table>
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif"/></td>
				<td class="lists_bottom"></td>
				<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
