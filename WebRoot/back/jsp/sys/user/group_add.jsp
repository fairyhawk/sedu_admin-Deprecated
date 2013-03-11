<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加用户组</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			$().ready(function() {
	 			$("#addForm").validate();
	 		});
		
			function onchangeShow(pId){
				document.getElementById('group.parentGroupId').value=pId;
				document.getElementById('group.level').value=2;
				if(pId == 0) {
					document.getElementById('sGroupId').options.length = 1;
					return;
				}
				
				$.ajax({  
					url : "<%=contextPath%>/sys/group!getChildGroupById.action",  
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
				
			function onchangecallback(result){   
					//alert(data);		
				document.getElementById('sGroupId').options.length = 1;  //清空原有的option 
			
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].key+"'>"+result.entity[i].value+"</option>"  
				}
				$("#sGroupId").html($("#sGroupId").html() + str);  
			 } 
			
			function onchangeSecond(pId){
				if(pId == -1) {
					document.getElementById("group.parentGroupId").value = document.getElementById("fGroupId").value;
					document.getElementById("group.level").value = 2;
				} else {
					document.getElementById('group.parentGroupId').value=pId;
					document.getElementById('group.level').value=3;
				}
			}
			
			function checkSubmit(){
				var fid = document.getElementById('group.parentGroupId').value;
				if(fid==null || fid==0){
					document.getElementById('group.parentGroupId').value=0;
					document.getElementById('group.level').value=1;
				}
			}

		</script>
	</head>
	<body>
<div>
	<s:form action="group!addGroup" name="addForm" id="addForm" method="post" onsubmit="checkSubmit();">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加用户组</font>
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
					<td width="20%">
						选择父级用户组：
					</td>
					<td class="lists_tleft">
						<s:select name="fGroupId" id="fGroupId" list="groupList"
							listKey="groupId" listValue="groupName" headerKey="0"
							headerValue="请选择" theme="simple" onchange="onchangeShow(this.value);">
						</s:select>
						<s:select name="sGroupId" id="sGroupId" list="#{}" headerKey="-1"
							headerValue="请选择" theme="simple" onchange="onchangeSecond(this.value);">
						</s:select>
					</td>
				</tr>
				<tr>
					<td>
						新用户组名称：
					</td>
					<td class="lists_tleft">
						<input type="text" name="group.groupName" id="group.groupName" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:hidden name="group.parentGroupId" id="group.parentGroupId"></s:hidden>
						<s:hidden name="group.level" id="group.level"></s:hidden>
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
