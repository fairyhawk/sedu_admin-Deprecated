<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员注册</title>	
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			$().ready(function() {
				jQuery.validator.addMethod("cusName", function(value, element) {
					var pattern = /^[a-zA-Z]{1}([a-zA-Z0-9]|[_])*$/;
	 					return this.optional(element) || pattern.test(value);
	 				});
	 			$("#forgotForm").validate({
			        rules: {
			            "customer.cusName": {
			            	required : true,
			                cusName : true,
			                maxlength : 20,
			                minlength : 6
			        	}
			    	},
			    	messages : {
				    		"customer.cusName": {
				                cusName : "必须以字母开头，可带字母、数字以及下划线"
				            }
			    		}
	 			});
	 		});
		</script>
	</head>
	<body>
		<div class="main_right">
			<h1>
				找回密码
			</h1>
			<form action="<%=contextPath %>/cus/cus!forgotPwd.action" method="post" name="forgotForm" id="forgotForm">
				<table cellpadding="0" cellspacing="1" class="tables">
					<tr height="30">
						<th align="center">
							请输入要找回的用户名
						</th>
					</tr>
					<tr height="30">
						<td align="center">
							<input type="text" name="customer.cusName" id="cusName" />
						</td>
					</tr>
					<tr height="30">
						<td align="center">
							<input type="submit" value="提交"/><input type="button" value="返回" onclick="history.go(-1)"></input>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>