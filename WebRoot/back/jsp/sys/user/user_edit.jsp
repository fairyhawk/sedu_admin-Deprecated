<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.shangde.edu.sys.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>信息修改——用户基本信息设置</title>
		<script type="text/javascript" src="<%=contextPath%>/script/validate.js"></script>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			$().ready(function() {
				jQuery.validator.addMethod("tel", function(value, element) {
					var pattern = /^[0-9]{1}[0-9-]*$/;
	 					return this.optional(element) || pattern.test(value);
	 				});
				jQuery.extend(jQuery.validator.messages, { 
		  			equalTo : "两次密码输入不一致",
		  			tel : "只能输入数字和中划线"
				}); 
	 			$("#updateForm").validate();
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
				var fGroupId = document.getElementById("fGroupId").value;
				var sGroupId = document.getElementById("sGroupId").value;
				var tGroupId = document.getElementById("tGroupId").value;
				if(tGroupId!='-1'){
					document.getElementById("user.groupId").value = tGroupId;
				}else if(sGroupId!='-1'&&tGroupId=='-1'){
					document.getElementById("user.groupId").value = sGroupId;
				}else{
					alert("用户必须归属二级组或三级组下!");
					 return false;
				}
				return true;
			}
		</script>
	</head>
	<body>
<div>
	<s:form action="user!editUser" id="updateForm" method="post" onsubmit="return checkSubmit()">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">信息修改——用户基本信息设置</font>
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
					<td>
						<font color="red">*</font>用户名
					</td>
					<td class="lists_tleft">
						<input type="text" readonly name="user.loginName" id="user.loginName"
							value="<s:property value="user.loginName"/>" />
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>真实姓名
					</td>
					<td class="lists_tleft">
						<input type="text" name="user.userName" id="user.userName"
							value="<s:property value="user.userName"/>" class="{required:true,maxlength:20}"/>
					</td>
				</tr>
				<tr>
					<td width="15%">
						<font color="red">*</font>所属用户组
					</td>
					<td class="lists_tleft">
						<select id="fGroupId" onchange="getFChildGroup(this.value)">
							<option value="-1">
								请选择
							</option>
							<s:if test="user.group.level>1">
								<s:iterator value="groupList" id="group">
									<s:if test="#group.level==1">
										<option value="<s:property value="#group.groupId"/>" <s:property value="fg.groupId==#group.groupId?'selected':''"/>><s:property value="#group.groupName"/></option>
									</s:if>
								</s:iterator>
							</s:if>
						</select>
						<select id="sGroupId"  onchange="getSChildGroup(this.value)">
							<option value="-1">
								请选择
							</option>
							<s:if test="user.group.level>1">
								<s:iterator value="groupList" id="group">
									<s:if test="#group.level==2&&#group.parentGroupId==fg.groupId">
										<option value="<s:property value="#group.groupId"/>" <s:property value="sg.groupId==#group.groupId?'selected':''"/>><s:property value="#group.groupName"/></option>
									</s:if>
								</s:iterator>
							</s:if>
						</select>
						<select id="tGroupId">
							<option value="-1">
								请选择
							</option>
							<s:if test="user.group.level>1">
								<s:iterator value="groupList" id="group">
									<s:if test="#group.level==3&&#group.parentGroupId==sg.groupId">
										<option value="<s:property value="#group.groupId"/>" <s:property value="tg.groupId==#group.groupId?'selected':''"/>><s:property value="#group.groupName"/></option>
									</s:if>
								</s:iterator>
							</s:if>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						联系电话
					</td>
					<td class="lists_tleft">
						<input type="text" name="user.tel" id="user.tel"
							value="<s:property value="user.tel"/>"  class="{required:true,tel:true}"/>
					</td>
				</tr>
				<tr>
					<td>
						e_mail
					</td>
					<td class="lists_tleft">
						<input type="text" name="user.email" id="user.email"
							value="<s:property value="user.email"/>" class="{required:true,email:true,maxlength:50}" />
					</td>
				</tr>
				<tr>
					<td>
						性别
					</td>
					<td class="lists_tleft">
						<input type="radio" name="user.userTypeId" value="1" <s:property value="user.userTypeId==1?'checked':''"/> /> 男
						<input type="radio" name="user.userTypeId" value="0" <s:property value="user.userTypeId==0?'checked':''"/> /> 女
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="user.loginPwd" id="user.loginPwd" value="<s:property value="user.loginPwd"/>"/>
						<input type="hidden" name="user.userId" id="user.userId" value="<s:property value="user.userId"/>"/>
						<input type="hidden" name="user.status" id="user.status" value="<s:property value="user.status"/>"/>
						<input type="hidden" name="user.groupId" id="user.groupId" value="<s:property value="user.groupId"/>"/>
						<s:submit value="提交" /> &nbsp; <input type="button" value="返回" onclick="history.go(-1)" />
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
