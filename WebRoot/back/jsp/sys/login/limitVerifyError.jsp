<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head><title>权限错误</title>
 
<style>
<!--
body {
	background-color: #ffffff;
}
td { font-size: 12px;}
-->
</style>
</head>
<body>
	<div style="margin-top: 150px;">
		<table align="center" border="0" width="300" cellspacing="0"cellpadding="0" height="200">
			<tr>
				<td background="<%=request.getContextPath()%>/back/images/er.gif">
					<table align="right" cellspacing="0"cellpadding="0" width="200" height="200" border="0" style="margin-right: 3px;">
						<tr>
							<td height="115">
								<div>
									 <body>
		    					对不起，您没有权限访问此功能！
		  						</body>
								</div>
								<div>
									点击<a href="###" onclick="javascript:history.go(-1);">返回</a>！
								</div>
							</td>
						</tr>
						<tr>
							<td height="62">
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>