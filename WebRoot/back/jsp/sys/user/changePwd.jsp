<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/include/header.inc"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改密码</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
 	    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
    <script type="text/javascript">
        function validatePwd() {
            var flag = true;
            var validateMsg = "";
            document.getElementById("sub").disabled = true;
            if($("#oldPwd").val() == ""){
                validateMsg += "请输入原有密码！<br>";
                flag = false;
            }
            if($("#newPwd").val() == ""){
                validateMsg += "请输入新密码！<br>";
                flag = false;
            }

            if(flag && $("#newPwd1").val() != $("#newPwd").val()){
                validateMsg += "两次输入的新密码必须一样！";
                flag = false;
            }
            if (flag){
                $("#validateMsg").html("");
                document.getElementById("sub").disabled = false;
            } else{
                document.getElementById("sub").disabled = true;
                $("#validateMsg").html("<font color='red'>" + validateMsg + "</font>");
            }
        }
    </script>
</head>
<body class="manage">
<div >
	<s:form action="user!changePwdSubmit.action">
	<table class="lists" cellspacing="0" cellpadding="0" border="0" width="100%">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">修改密码</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor"></td>
			<td>
				<table  border="0" cellspacing="1" cellpadding="0" >
				<tbody>
					 <tr>
	                     <td class="lists_tright">原有密码：</td>
	                     <td class="lists_tleft"><input id="oldPwd" name="user.loginPwd" type="password" class="login_input" onchange="validatePwd();"/></td>
	                 </tr>
	                 <tr>
	                     <td class="lists_tright">新密码：</td>
	                     <td class="lists_tleft"><input id="newPwd" name="changePwd" type="password" class="login_input" onchange="validatePwd();"/></td>
	                 </tr>
	                 <tr>
	                      <td class="lists_tright">重复新密码：</td>
	                     <td class="lists_tleft"><input id="newPwd1" name="changePwd1" type="password" class="login_input" onchange="validatePwd();"/></td>
	                 </tr>
	                 <tr>
	                 	<td class="lists_tright"></td>
	                     <td><s:actionerror cssStyle="color:red;"/><span id="validateMsg"></span></td>
	                 </tr>
	                 <tr>
	                 	<td></td>
	                     <td align="left">
	                        <input name="Submit" id="sub" type="submit" value="确定" disabled="disabled"/>
	                     </td>
	                 </tr>
	               </tbody>
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
	</s:form>
</div>
</body>
</html>
