<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加新用户</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			$().ready(function() {
				jQuery.validator.addMethod("loginName", function(value, element) {
					var pattern = /^[a-zA-Z]{1}([a-zA-Z0-9]|[_])*$/;
	 					return this.optional(element) || pattern.test(value);
	 				});
				jQuery.validator.addMethod("tel", function(value, element) {
					var pattern = /^[0-9]{1}[0-9-]*$/;
	 					return this.optional(element) || pattern.test(value);
	 				});
				jQuery.extend(jQuery.validator.messages, { 
		  			equalTo : "两次密码输入不一致",
		  			tel : "只能输入数字和中划线"
				}); 
	 			$("#addForm").validate({
			        rules: {
			            "user.loginName": {
			            	maxlength : 20,
			            	required : true,
			            	loginName : true,
			            	minlength : 6,
			                remote : {
			                   data: {
			                        'queryUserCondition.searchKey': function(){
			                            return $("input[name=user.loginName]").val();
			                        }
			                    },
			                    url : "<%=contextPath%>/sys/user!checkLoginName.action",
			                    type : "post"
			                }
			            }
			        },
			    	messages : {
			    		"user.loginName": {
			                remote: "该用户名已存在",
			                loginName : "必须以字母开头，可带字母、数字以及下划线"
			            }
			    	}
	 			});
	 		});
		
			function getFChildGroup(pId){ 
				$.ajax({  
					url : "<%=contextPath%>/sys/user!getAllGroup.action",  
					data : {groupId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback  
				});  
			} 
				
			function onchangecallback(data){   
				document.getElementById('sGroupId').options.length = 1;  //清空原有的option 
				var str="";  
				for(var i=0;i<data.length;i++){  
					str+="<option value='"+data[i].id+"'>"+data[i].val+"</option>"  
				}  
				$("#sGroupId").html($("#sGroupId").html() + str);
				document.getElementById('tGroupId').options.length = 1;
			}  

			function getSChildGroup(pId){ 
				$.ajax({  
					url : "<%=contextPath%>/sys/user!getAllGroup.action",  
					data : {groupId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback1 
				});  
			} 
				
			function onchangecallback1(data){   
				document.getElementById('tGroupId').options.length = 1;  //清空原有的option 
				var str="";  
				for(var i=0;i<data.length;i++){  
					str+="<option value='"+data[i].id+"'>"+data[i].val+"</option>"  
				}  
				$("#tGroupId").html($("#tGroupId").html() + str);  
			}  

			function checkSubmit(){
				var fGroupId=document.getElementById("fGroupId").value;
				var sGroupId=document.getElementById("sGroupId").value;
				var tGroupId=document.getElementById("tGroupId").value;
				if(tGroupId!='-1'){
					document.getElementById("user.groupId").value=tGroupId;
				}else if(sGroupId!='-1'&&tGroupId=='-1'){
					document.getElementById("user.groupId").value=sGroupId;	
				}else{
					alert("用户必须归属二级组或三级组下!");
					return false;
				}
			    document.addForm.action = "<%=contextPath%>/sys/user!addUser.action"
				return true;
			}
		</script>
	</head>
	<body>
<div>
	<s:form name="addForm" id="addForm" method="post" onsubmit="return checkSubmit();">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加用户</font>
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
				<tr>
					<td width="20%" class="lists_tright">
						用户名：
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" name="user.loginName" id="user.loginName" />
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						真实姓名：
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" name="user.userName" id="user.userName" class="{required:true}" />
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						所属用户组：
					</td>
					<td class="lists_tleft">
						&nbsp;<s:select  name="fGroupId" id="fGroupId" list="groupList"  listKey="groupId" listValue="groupName" headerKey="-1" headerValue="请选择" theme="simple" onchange="getFChildGroup(this.value);">
						</s:select>
						<s:select  name="sGroupId" id="sGroupId" list="#{}"   headerKey="-1" headerValue="请选择" theme="simple" onchange="getSChildGroup(this.value);">
						</s:select>
						<s:select  name="tGroupId" id="tGroupId" list="#{}"   headerKey="-1" headerValue="请选择" theme="simple">
						</s:select>
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						联系电话：
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" name="user.tel" id="user.tel" class="{required:true,tel:true}"/>
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						e_mail：
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" name="user.email" id="user.email" class="{required:true,email:true,maxlength:50}" />
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						性别：
					</td>
					<td class="lists_tleft">
					<input type="radio" name="user.userTypeId" value="1" checked/>
						   男
					<input type="radio" name="user.userTypeId" value="0"/>
						    女
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						密码：
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="password" name="user.loginPwd" id="loginPwd" class="{required:true,minlength:6,maxlength:20}"/>
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						重复密码：
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="password"  id="user.loginPwdConfirm" equalTo="#loginPwd" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
					    <s:hidden name="user.groupId" id="user.groupId"></s:hidden>
					    <!--<s:hidden name="user.userType" id="user.userType" value="%{userType}"></s:hidden>-->
						<s:submit value="提交" />
					</td>
				</tr>
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
