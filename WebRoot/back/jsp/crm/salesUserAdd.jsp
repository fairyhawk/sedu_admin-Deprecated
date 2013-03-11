<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
			<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/WebCalendar.js" ></script>  
		<title>新建销售机会</title>
		<script type="text/javascript">
		$().ready(function (){
			$('#salesChanceAdd').validate();
		});
		
		function cancle(){
			var roleId='<s:property value="roleId"/>';
			if(roleId==73){
				window.location='<%=contextPath%>/crm/crmChance!getSalesChanceList.action?queryChanceCondition.currentPage=1';
			}else{
				window.location='<%=contextPath%>/crm/crmChance!getAllChance.action?pageQuery.currentPage=1';
			}
		}
		
		function selected(groupId){
		if(groupId!=0){
			$.ajax({
									url : "<%=contextPath%>/crm/crmChance!getGroupUserInfo.action",  
									data : {
									"groupIds":groupId
									},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									var content=result.returnMessage;
									$('#sysUserName').html(content);
									},
									error: function(){ 
										alert('error');  
									}
				  });
				  }
		}
		
		function change(){
			$('#submits').attr("disabled","");
			}
		
		
		function test(){
			var chanceFrom=$('#chanceFrom').val();
			if(chanceFrom==0){
				alert('请选择机会来源');
				return;
			}
			
			var subjectId=$('#subjectId').val();
			if(subjectId==0){
				alert('请选择项目');
				return;
			}
			
			var roleId='<s:property value="roleId"/>';
			if(roleId!=73){
				var groupId=$('#groupId').val();
				if(groupId==0){
					alert('请选择项目组');
					return;
				}
			}
			
			var usersId=$('#usersId').val();
			if(usersId==0){
				alert('请选择坐席');
				return;
			}
			
			var followStatus=$('#followStatus').val();
			if(followStatus==0){
				alert('请选择用户跟踪状态');
				return;
			}
			
			var mobile=$('#mobile').val();
			var patrn=/^[0-9]{1,20}$/; 
			if(mobile==''){
				alert("手机号必填")
				return;
			}else if(!patrn.exec(mobile)){
				alert("手机号必须是数字");
				$("#sortId").val(count);
				return;
			}
			if(mobile.length!=11){
				alert('手机号填写格式不正确');
				return;
			}
			
			$('#submits').attr("disabled","disabled");
			
			$.ajax({
									url : "<%=contextPath%>/crm/crmChance!testMobile.action",  
									data : {
									"mobile":mobile
									},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
										var bo=result.jumpType;
										if(!bo){
											alert('该手机对应用户已有指派人');
											return
										}else{
											document.salesChanceAdd.submit();
										}
									},
									error: function(){ 
										alert('error');  
									}
				  });
			
		}
		</script>
    
    


  </head>
  
  <body>
  <form action="<%=contextPath%>/crm/crmChance!crmAdduser.action" method="post"  id="salesChanceAdd" name="salesChanceAdd">
   	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">

		<tr>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
			  <font class="lists_fleft">新建销售机会</font></td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />

			</td>
		</tr>
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<div class="crm_tit">机会属性</div>
   		<table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
        	
        	
        	
        		<tr>
		        	<td width="20%" class="crm_tableFR"> <font color="#ff0000">*</font>机会来源：</td>
					<td    class="crm_tableFL">&nbsp;
						<select id="chanceFrom" name="chance.origin">
							<option value="2">乐语在线</option>
							<option value="6">Callin</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td class="crm_tableFR">
						<font color="#ff0000">*</font>项目：
					</td>
					<td  class="crm_tableFL">&nbsp;
						<s:select list="subjectList" name="chance.subjectId" listKey="subjectId" listValue="subjectName" headerKey="0"
								headerValue="---请选择---" id="subjectId" ></s:select>
					</td>
				</tr>
				
				<tr>
					<td class="crm_tableFR">
						<font color="#ff0000">*</font>手机号：
					</td>
					<td  class="crm_tableFL">&nbsp;
						<input type="text" maxlength="11" onchange="change()" value='<s:property value="mobile"/>' name="user.mobile"  id="mobile"/>
					</td>
				</tr>
				
				
				<tr>
					<td class="crm_tableFR">
						<font color="#ff0000">*</font>销售机会状态：
					</td>
					<td  class="crm_tableFL">&nbsp;
						未注册
					</td>
				</tr>
				
				<tr>
					<td class="crm_tableFR">
						<font color="#ff0000">*</font>用户跟踪状态：
					</td>
					<td  class="crm_tableFL">&nbsp;
						<select id="followStatus" name="chance.followStatus">
							<option value="0">---请选择---</option>
							<option value="1">放弃</option>
							<option value="2">跟踪</option>
							<option value="3">热点</option>
							<option value="4">成交</option>
							<option value="5">预约</option>
						</select>
					</td>
				</tr>
				
				<s:if test="roleId!=73">
   		 	 <tr>
	   			 <td class="crm_tableFR">
	   				<font color="#ff0000">*</font> 项目组：
	   			 </td>
	   			 
   				 <td class="crm_tableFL">&nbsp;
					<s:select list="groupList" name="" listKey="groupId" listValue="groupName" headerKey="0" id="groupId"
								headerValue="请选择项目组" theme="simple" onchange="selected(this.value);"></s:select>				
				</td>
			</tr>
			
			<tr>	
				<td class="crm_tableFR">
					<font color="#ff0000">*</font>销售坐席：
				</td>
             	<td width="600px" class="crm_tableFL" id="sysUserName" >&nbsp;
            		<select name="userId" id="usersId">
            			<option value="0">请选择坐席</option>
			            <s:iterator value="userList" id="userList">
			            	<s:if test="#userList.userId==usersId">
			            		<option value='<s:property value="#userList.userId"/>'>
			            			<s:property value="#userList.userName"/>
			            		</option>
			            	</s:if>
			            	<s:if test="#userList.userId!=usersId">
			            		<option value='<s:property value="#userList.userId"/>'>
			            			<s:property value="#userList.userName"/>
			            		</option>
			            	</s:if>
			            </s:iterator>
            		</select>
				</td>
			</tr>
   		</s:if>
   		<s:else>
   			<tr>
   				<td class="crm_tableFR">
   					<font color="#ff0000">*</font>销售坐席：
   				</td>
           	 	<td width="600px" class="crm_tableFL">&nbsp;
            		<select name="userId" >
			            <s:iterator value="userList" id="userList">
			            	<s:if test="#userList.userId==usersId">
			            		<option value='<s:property value="#userList.userId"/>' selected="selected">
			            			<s:property value="#userList.userName"/>
			            		</option>
			            	</s:if>
			            </s:iterator>
            		</select>
				</td>
   			</tr>
   		</s:else>
				
				</table>
				
			<div class="crm_tit">基础信息</div>
        	<table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
        		<tr>
					<td width="20%" class="crm_tableFR">
						姓名：
					</td>
					<td    class="crm_tableFL">&nbsp;
						<input type="text"  maxlength="20" name="user.realName"/>
					</td>
				</tr>
				
				<tr>
					<td class="crm_tableFR">
						性别：
					</td>
					<td  class="crm_tableFL">&nbsp;
						<select name="user.sex">
							<option value="0">---请选择---</option>
							<option value="1">女士</option>
							<option value="2">先生</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td class="crm_tableFR">
						职位：
					</td>
					<td  class="crm_tableFL">&nbsp;
						<input type="text" id="age" maxlength="20" name="user.profession"/>
					</td>
				</tr>
				
				<tr>
					<td class="crm_tableFR">
						生日：
					</td>
					<td  class="crm_tableFL">&nbsp;
						<input class="inp_txt width315" type="text" maxlength="20" name="user.birthday" 
						onclick="SelectDate(this,'yyyy\/MM\/dd')" width="10"/>
					</td>
				</tr>
				
				<tr>
					<td class="crm_tableFR">
						地址：
					</td>
					<td  class="crm_tableFL">&nbsp;
						<input type="text"  maxlength="50" name="user.address"/>
					</td>
				</tr>
				
				<tr>
					<td class="crm_tableFR">
						备注：
					</td>
					<td  class="crm_tableFL">&nbsp;
						<textarea style="width:400px;" id="remarks" name="user.remarks" maxlength="300"></textarea>
					</td>
				</tr>
        	</table>
				
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
			
            <tr>
            	<td width="280px">
            	</td>
            	<td  class="crm_tableFL">
            		<input type="button" onclick="test()" id="submits"   value="确定"/>&nbsp;&nbsp;&nbsp;<input type="button" onclick="cancle()" value="返回"/>
            	</td>
            </tr>
        </table>
        </td>
			<td width="16" class="lists_tright lists_bor2">

			</td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	</form>
  </body>
</html>
