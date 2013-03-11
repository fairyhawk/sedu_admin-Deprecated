<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/style/right.css" />
	</head>

	<body>
		<div class="main_right">
			<h1>
				发送HTML型邮件
			</h1>
			<form name="form1" method="post" action="mail!sendMail.action">
				<table cellpadding="0" cellspacing="1" class="tables"
					style="width: 600px; height: 200px;">
					<tr>
						<th align="left">
							SMTP服务器：
						</th>
						<td>
							<input type="text" id="SMTPHost" name="SMTPHost" />
						</td>
					</tr>
					<tr>
						<th align="left">
							登录帐号：
						</th>
						<td>
							<input type="text" id="user" name="user" />
						</td>
					</tr>
					<tr>
						<th align="left">
							登录密码：
						</th>
						<td>
							<input type="password" id="password" name="password" />
						</td>
					</tr>
					<tr>
						<th align="left">
							发件人邮箱：
						</th>
						<td>
							<input type="text" id="from" name="from" />
						</td>
					</tr>
					<tr>
						<th align="left">

							收件人邮箱：
						</th>
						<td>
							<input type="text" id="to" name="to" />
						</td>
					</tr>
					<tr>
						<th align="left">
							邮件标题：
						</th>
						<td>
							<input type="text" id="subject" name="subject" />
						</td>
					</tr>
					<tr>
						<th align="left">
							邮件内容HTML代码：
						</th>
						<td>
							<textarea id="content" name="content" rows="5" cols="40"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" name="submit" value="发送" />
							&nbsp;
							<input type="reset" name="reset" value="重填" />

						</td>
					</tr>
				</table>

			</form>
		</div>
	</body>
</html>
