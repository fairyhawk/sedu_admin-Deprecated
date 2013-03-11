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
<script type="text/javascript">
	$().ready(function() {
			$("#updateForm").validate();
		});
		
		function deleteReply() {
			if(window.confirm("确认删除本条回复吗？")) {
			window.location.href = "<%=contextPath%>/cms/comment!deleteReply.action?queryCommentCondition.currentPage=1&comment.cmtId=<s:property value="comment.cmtId"/>";
		}
		}
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/cms/comment!replyUpdate.action" method="post" name="updateForm" id="updateForm">
		<input type="hidden" value="<s:property value="comment.cmtId"/>" name="comment.cmtId" />
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
					<td>
						<font color="red">*</font>意见内容
					</td>
					<td class="lists_tleft">
						<s:property value="comment.sourceObject.cmtInfo"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>回复内容
					</td>
					<td class="lists_tleft">
						<textarea name="comment.cmtInfo" style="width:300px;height:120px" id="tmpContent" class="{required:true}" ><s:property value="comment.cmtInfo.trim()"/></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="保存"/><input type="button" value="删除回复" onclick="deleteReply()"/><input type="button" value="返回" onclick="history.go(-1)"></input>
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
