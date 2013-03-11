<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加新笔记</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
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
		<div class="main_right">
			<h1>
				添加笔记
			</h1>
			<form action="<%=contextPath %>/res/notes!addNotes.action" method="post" name="addForm" id="addForm">
				<table cellpadding="0" cellspacing="1" class="tables">
					<tr>
						<th>
							<font color="red">*</font>笔记内容
						</th>
						<td>
							<textarea name="notes.noteInfo" style="width:300px;height:120px" id="noteInfo" class="{required:true}" ></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="hidden" name="notes.pointId" id="noteId" value=<s:property value="notes.pointId"/> />
							<input type="hidden" name="notes.cusId" id="noteId" value=<s:property value="notes.cusId"/> />
							<input type="submit" value="增加"/><input type="button" value="返回" onclick="history.go(-1)"></input>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>