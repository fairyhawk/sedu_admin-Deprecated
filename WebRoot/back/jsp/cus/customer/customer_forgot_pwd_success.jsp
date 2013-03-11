<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员注册</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
	</head>
	<body>
		<div class="main_right">
			<h1>
				找回密码成功
			</h1>
			<table align="center">
				<tr height="30" align="center">
					<th>
						已将新密码发送至邮箱，请查收！
					</th>
				</tr>
				<tr height="30" align="center">
					<td>
						<input type="button" value="返回登陆页" onclick="history.go(-1)"></input>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>