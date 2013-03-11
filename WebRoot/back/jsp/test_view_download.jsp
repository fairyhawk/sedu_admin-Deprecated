<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>查看已上传图片</title>	
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/right.css" />
		<script type="text/javascript" src="<%=contextPath%>/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			function viewPic(fileName) {
				document.getElementById("picImg").style.display = "block";
				document.getElementById("picImg").src = "<%=contextPath%>/test!download.action?downloadFileName=" + fileName.substring();
			}
		</script>
	</head>
	<body>
		<div class="main_right">
			<h1>
				查看已上传图片
			</h1>
			<img style="display:none" id="picImg"/>
			<form action="<%=contextPath %>/test!picUpload.action" method="post" name="addForm" id="addForm" enctype="multipart/form-data">
				<table cellpadding="0" cellspacing="1" class="tables">
					<s:iterator value="fileNames" id="fileName">
						<tr>
							<td>
								<input type="button" value="查看<s:property value='#fileName' />" id="<s:property value='#fileName' />" onclick="viewPic(this.id)"/>
							</td>
						</tr>
					</s:iterator>
				</table>
			</form>
		</div>
	</body>
</html>