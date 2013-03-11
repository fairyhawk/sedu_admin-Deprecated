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
				jQuery.validator.addMethod("mobile", function(value, element) {
					var pattern = /^1{1}[0-9]{10}$/;
	 					return this.optional(element) || pattern.test(value);
	 				});
				jQuery.validator.addMethod("identify", function(value, element) {
					var pattern = /^[0-9]{17}[0-9xX]{1}\b$|^([0-9]{15}\b)$/;
	 					return this.optional(element) || pattern.test(value);
	 				});
				jQuery.extend(jQuery.validator.messages, { 
		  			equalTo : "两次密码输入不一致",
		  			mobile : "请输入正确的手机号码",
		  			identify : "请输入正确的身份证号"
				}); 
	 			$("#addForm").validate({
			        rules: {
			            "customer.cusName": {
			            	required : true,
			                cusName : true,
			                maxlength : 20,
			                minlength : 6,
			                remote : {
			                   data: {
			                        'queryCustomerCondition.cusName': function(){
			                            return $("input[name=customer.cusName]").val();
			                        }
			                    },
			                    url : "<%=contextPath %>/cus/cus!checkCusName.action",
			                    type : "post"
			                }
			            }
			        },
			    	messages : {
			    		"customer.cusName": {
			                remote: "该用户名已存在",
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
				学员注册
			</h1>
			<form action="<%=contextPath %>/cus/cus!addCustomer.action" method="post" name="addForm" id="addForm">
				<table cellpadding="0" cellspacing="1" class="tables">
					<tr height="30">
						<th>
							<font color="red">*</font>用户名
						</th>
						<td>
							<input type="text" name="customer.cusName" id="cusName" />
						</td>
					</tr>
					<tr height="30">
						<th>
							<font color="red">*</font>密码
						</th>
						<td>
							<input type="password" name="customer.cusPwd" id="cusPwd" class="{required:true,minlength:6,maxlength:20}" />
						</td>
					</tr>
					<tr height="30">
						<th>
							<font color="red">*</font>确认密码
						</th>
						<td>
							<input type="password" id="cusPwdConfirm" equalTo="#cusPwd"/>
						</td>
					</tr>
					<tr height="30">
						<th>
							<font color="red">*</font>电子邮箱
						</th>
						<td>
							<input type="text" name="customer.email" id="email" class="{required:true,email:true,maxlength:50}" />
						</td>
					</tr>
					<tr height="30">
						<th>
							<font color="red">*</font>手机号码
						</th>
						<td>
							<input type="text" name="customer.mobile" id="mobile" class="{required:true,mobile:true}" />
						</td>
					</tr>
					<tr height="30">
						<th>
							<font color="red">*</font>真实姓名
						</th>
						<td>
							<input type="text" name="customer.realName" id="realName" class="{required:true}"/>
						</td>
					</tr>
					<tr height="30">
						<th>
							性别
						</th>
						<td>
							<select name="customer.sex">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</td>
					</tr>
					<tr height="30">
						<th>
							<font color="red">*</font>身份证号
						</th>
						<td>
							<input type="text" name="customer.idNum" id="idNum" class="{identify:true,required:true}"/>
						</td>
					</tr>
					<tr height="30">
						<td colspan="2">
							<input type="submit" value="提交"/><input type="button" value="返回" onclick="history.go(-1)"></input>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>