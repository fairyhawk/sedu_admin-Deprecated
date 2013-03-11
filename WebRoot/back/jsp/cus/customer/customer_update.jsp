<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改学员信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
	var request = { 
		QueryString : function(val) { 
						var uri = window.location.search; 
						var re = new RegExp("" +val+ "=([^&?]*)", "ig"); 
						return ((uri.match(re))?(uri.match(re)[0].substr(val.length+1)):null); 
					} 
				}

	$().ready(function() {
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
			$("#updateForm").validate();
	});
</script>
</head>
<body>
<div>
	<form action="<%=contextPath%>/cus/cus!updateCustomer.action" method="post" name="updateForm" id="updateForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">修改学员信息</font>
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
				<tr height="30">
					<td width="20%">
						学员类型
					</td>
					<td class="lists_tleft">
						<input type="radio" name="customer.cusType" id="cusType1" value="1" <s:property value="customer.cusType==1?'checked':''"/> />内部学员
						<input type="radio" name="customer.cusType" id="cusType0" value="0" <s:property value="customer.cusType==0?'checked':''"/> />注册学员
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>邮箱
					</td>
					<td class="lists_tleft">
						<input type="text" name="customer.email" id="email" value="<s:property value="customer.email"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>昵称
					</td>
					<td class="lists_tleft">
						<input type="text" name="customer.cusName" id="cusName" value="<s:property value="customer.cusName"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>真实姓名
					</td>
					<td class="lists_tleft">
						<input type="text" name="customer.realName" id="realName" value="<s:property value="customer.realName"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>手机号码
					</td>
					<td class="lists_tleft">
						<input type="text" name="customer.mobile" id="mobile" class="{mobile:true,required:true}" value="<s:property value="customer.mobile"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>QQ
					</td>
					<td class="lists_tleft">
						<input type="text" name="customer.qq" id="qq" value="<s:property value="customer.qq"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>MSN
					</td>
					<td class="lists_tleft">
						<input type="text" name="customer.MSN" id="MSN" value="<s:property value="customer.MSN"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>联系方式
					</td>
					<td class="lists_tleft">
						<input type="text" name="customer.address" id="address" value="<s:property value="customer.address"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						性别
					</td>
					<td class="lists_tleft">
						<input type="radio" name="customer.sex" id="sex1" value="1" <s:property value="customer.sex==1?'checked':''"/> />男
						<input type="radio" name="customer.sex" id="sex0" value="0" <s:property value="customer.sex==0?'checked':''"/> />女
					</td>
				</tr>
				<tr height="30">
					<td>
						生日
					</td>
					<td class="lists_tleft">
						<input type="text" readonly name="customer.birthday" id="birthday" value="<s:date name="customer.birthday" format="yyyy-MM-dd"/>" onclick="WdatePicker()"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						完善个人信息时间
					</td>
					<td class="lists_tleft">
						<input type="text" readonly name="customer.completeTime" id="completeTime" value="<s:date name="customer.completeTime" format="yyyy-MM-dd"/>" onclick="WdatePicker()"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						登陆次数
					</td>
					<td class="lists_tleft">
						<input type="text" name="customer.loginTimes" id="loginTimes" value="<s:property value="customer.loginTimes"/>"/>
					</td>
				</tr>
				<tr height="30">
					<td colspan="2">
						<input type="hidden" name="customer.cusId" id="cusId" value="<s:property value="customer.cusId"/>"/>
						<input type="hidden" name="customer.cusPwd" id="cusPwd" value="<s:property value="customer.cusPwd"/>"/>
						<input type="hidden" name="customer.lastloginIP" id="lastloginIP" value="<s:property value="customer.lastloginIP"/>"/>
						<input type="hidden" name="customer.lastloginTime" id="lastloginTime" value="<s:property value="customer.lastloginTime"/>"/>
						<input type="hidden" name="customer.regTime" id="regTime" value="<s:property value="customer.regTime"/>"/>
						<input type="hidden" name="customer.isComplete" id="isComplete" value="<s:property value="customer.isComplete"/>"/>
						<input type="hidden" name="customer.photo" id="photo" value="<s:property value="customer.photo"/>"/>
						<input type="submit" value="修改"/><input type="button" value="返回" onclick="history.go(-1)"></input>
					</td>
				</tr height="30">
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
