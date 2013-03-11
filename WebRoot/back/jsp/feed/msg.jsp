<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<title>提示窗口</title>
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
				<td background="<%=request.getContextPath()%>/back/images/good.jpg">
					<table align="right" cellspacing="0"cellpadding="0" width="200" height="200" border="0" style="margin-right: 3px;">
						<tr>
							<td height="115">
								<div style="color: red; font-size: 15;">
									<!-- 信息提示区域 -->
									<s:property value="msg" escape="html"/>
								</div>
							</td>
						</tr>
						<tr>
							<td height="62">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>