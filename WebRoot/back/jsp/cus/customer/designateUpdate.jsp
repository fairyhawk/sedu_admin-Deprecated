<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
			<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
		$().ready(function() {
			$("#addDes").validate();
			
		});

		function selected(groupId){
		if(groupId!=0){
			$.ajax({
									url : "<%=contextPath%>/cus/cusOrder!getGroupUserInfo.action",  
									data : {
									"groupIds":groupId,
									"status":1},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									var content=result.returnMessage;
									$('#selectt').html(content);
									},
									error: function(){ 
										alert('error');  
									}
				  });
				  }
		}

		function test(){
			var scene=$('#scene').val();
			var roleId='<s:property value="roleId"/>';
			if(scene==0){
				alert('请选择场景');
				return;
			}
			
			var sysUserId=$('#sysUserId').val();
			if(sysUserId==0){
				alert('请选择指派人');
				return;
			}
		
			var commuStatus=$('#commuStatus').val();
			var content=$('#reason').val();
			if(commuStatus==0&&roleId==73){
				alert('请选择沟通状态');
				return;
			}
			if(commuStatus==5){
			if(content==''){
				alert('正常接通时内容不能为空');
				return;
			}
			}
			if(content.length>400){
				return;
			}
			var consultType=$('#consultType').val();
			if(consultType==0&&roleId==73){
				alert('请选择咨询类型');
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
			var age=$('#age').val();
			if(age!=''&&!patrn.exec(age)){
				alert('年龄必须是数字');
				return;
			}
			if(age==''){
				$('#age').val(0);
			}
			var studyReason=$('#studyReason').val();
			if(studyReason==''&&roleId==73){
				alert('想学这门课程的原因不能为空');
				return;
			}
			if(studyReason.length>200){
				return;
			}
			var fProblem=$('#fProblem').val();
			if(fProblem==''&&roleId==73){
				alert('学员问题一不能为空');
				return;
			}
			if(fProblem.length>200){
				return;
			}
			var twProblem=$('#twProblem').val();
			if(twProblem.length>200){
				return;
			}
			var thProblem=$('#thProblem').val();
			if(thProblem.length>200){
				return;
			}
			var fWorry=$('#fWorry').val();
			if(fWorry==''&&roleId==73){
				alert('学员顾虑一不能为空');
				return;
			}
			if(fWorry.length>200){
				return;
			}
			var twWorry=$('#twWorry').val();
			if(twWorry.length>200){
				return;
			}
			var thWorry=$('#thWorry').val();
			if(thWorry.length>200){
				return;
			}
			
			document.forms['addDes'].submit();
		}
		
		function closes(){
			this.window.close();
		}
    </script>
    

  </head>
  
  <body>
  <form action='<%=contextPath %>/cus/cusOrder!updateDes.action' method="post" id="addDes">
  <table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">

		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
			  <font class="lists_fleft"></font></td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />

			</td>
		</tr>
		
		<tr  >
			<td width="12" class="lists_bor">
			</td>
			<td>
   		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="lists_info">
   			
   			<s:if test="roleId!=73">
   			 <tr>
	   			 <td width="15%" >
	   				 项目:
	   			 </td>
	   			 
   				 <td width="20%"  style="text-align:left;">
					<select name="querySellrecordCondition.groupId"  onchange="selected(this.value);">
						<option value="0">请选择项目</option>
						 <s:iterator value="groupList" id="groupList">
							 <s:if test="#groupList.groupId==groupIds">
						 		<option value='<s:property value="#groupList.groupId"/>' selected="selected">
						 			<s:property value="#groupList.groupName"/>
						 		</option>
							 </s:if>
							 <s:else>
								 <option value='<s:property value="#groupList.groupId"/>'>
								 	<s:property value="#groupList.groupName"/>
								 </option>
				 			 </s:else>
						 </s:iterator>
					</select>					
				</td>
				
				<td width="15%">
					<font color="#ff0000">*</font>指派人
				</td>
             	<td width="20%" style="text-align:left;" id="selectt">
            		<select name="sellrecord.sysUserId" id="sysUserId">
           	  			<option value='<s:property value="sellrecord.sysUserId"/>'>
           	  				<s:property value="sellrecord.sysUserName"/>
           	  			</option>
			            <s:iterator value="userList" id="userList">
			            	<s:if test="#userList.userId!=sellrecord.sysUserId">
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
   				<td width="15%">
   					<font color="#ff0000">*</font>指派人
   				</td>
           	 	<td width="20%" style="text-align:left;" id="selectt">
            		<select name="sellrecord.sysUserId">
           		 		<option value='<s:property value="sellrecord.sysUserId"/>'>
           		 			<s:property value="sellrecord.sysUserName"/>
           		 		</option>
            		</select>
				</td>
   			</tr>
   		</s:else>
			
			<tr>
				<td width="15%">
					<font color="#ff0000">*</font>场景:
				</td>
				
				<td width="20%"  style="text-align:left;"><select id="scene" name="sellrecord.scene">
					<s:if test="sellrecord.scene==0">
						<option value=0>请选择场景</option>
						<option value=1>在线</option>
						<option value=2>订单</option>
						<option value=3>注册</option>
					</s:if>
					<s:if test="sellrecord.scene==1">
						<option value=1>在线</option>
						<option value=0>请选择场景</option>
						<option value=2>订单</option>
						<option value=3>注册</option>
					</s:if>
					<s:if test="sellrecord.scene==2">
						<option value=2>订单</option>
						<option value=0>请选择场景</option>
						<option value=1>在线</option>
						<option value=3>注册</option>
					</s:if>
					<s:if test="sellrecord.scene==3">
						<option value=3>注册</option>
						<option value=0>请选择场景</option>
						<option value=1>在线</option>
						<option value=2>订单</option>
					</s:if>
				</select>
			</td>
				
	            <td colspan="2">
	            </td>
			</tr>
			<tr><td colspan="4"></td></tr>
			<tr><td>联系记录</td><td colspan="3"></td></tr>
			<tr><td colspan="4"><hr/></td></tr>
			<tr>
			
				<td width="15%">
				<s:if test="roleId==73"><font color="#ff0000">*</font>沟通状态:</s:if>
				<s:else>沟通状态:</s:else>
				</td>
				
				<td width="20%"  style="text-align:left;">
					<select id="commuStatus" name="sellrecord.commuStatus">
         					    <s:if test="sellrecord.commuStatus==0">
         					    <option value="0">请选择用户沟通情况</option>
         					    <option value="1">空号</option>
						<option value="2">通话中</option>
						<option value="3">再联系</option>
						<option value="4">测试</option>
						<option value="5">正常接通</option>
						<option value="6">未接通</option>
         					    </s:if>
          					<s:if test="sellrecord.commuStatus==1">
          					<option value="1">空号</option>
          					<option value="0">请选择用户沟通情况</option>
						<option value="2">通话中</option>
						<option value="3">再联系</option>
						<option value="4">测试</option>
						<option value="5">正常接通</option>
						<option value="6">未接通</option>
          					</s:if>
          					<s:if test="sellrecord.commuStatus==2">
          					<option value="2">通话中</option>
          					<option value="0">请选择用户沟通情况</option>
						<option value="1">空号</option>
						<option value="3">再联系</option>
						<option value="4">测试</option>
						<option value="5">正常接通</option>
						<option value="6">未接通</option>
						</s:if>
          					<s:if test="sellrecord.commuStatus==3">
          					<option value="3">再联系</option>
          					<option value="0">请选择用户沟通情况</option>
						<option value="1">空号</option>
						<option value="2">通话中</option>
						<option value="4">测试</option>
						<option value="5">正常接通</option>
						<option value="6">未接通</option>
						</s:if>
          					<s:if test="sellrecord.commuStatus==4">
          					<option value="4">测试</option>
          					<option value="0">请选择用户沟通情况</option>
						<option value="1">空号</option>
						<option value="2">通话中</option>
						<option value="3">再联系</option>
						<option value="5">正常接通</option>
						<option value="6">未接通</option>
          					</s:if>
          					<s:if test="sellrecord.commuStatus==5">
          					<option value="5">正常接通</option>
          					<option value="0">请选择用户沟通情况</option>
						<option value="1">空号</option>
						<option value="2">通话中</option>
						<option value="3">再联系</option>
						<option value="4">测试</option>
						<option value="6">未接通</option>
          					</s:if>
          					<s:if test="sellrecord.commuStatus==6">
          					<option value="6">未接通</option>
          					<option value="0">请选择用户沟通情况</option>
						<option value="1">空号</option>
						<option value="2">通话中</option>
						<option value="3">再联系</option>
						<option value="4">测试</option>
						<option value="5">正常接通</option>
          					</s:if>
						
					</select>
				</td>
				
				<td width="15%">
				<s:if test="roleId==73"><font color="#ff0000">*</font>咨询类型：</s:if>
				<s:else>咨询类型：</s:else>
				</td>
				
				<td width="20%"  style="text-align:left;">
					<select name="sellrecord.consultType" id="consultType">
						<s:if test="sellrecord.consultType==0">
							<option value=0>请选择学员咨询类型</option>
							<option value=1>首次</option>
							<option value=2>回访</option>
							<option value=3>二次回访</option>
							<option value=4>三次回访</option>
						</s:if>
						<s:if test="sellrecord.consultType==1">
							<option value=1>首次</option>
							<option value=0>请选择学员咨询类型</option>
							<option value=2>回访</option>
							<option value=3>二次回访</option>
							<option value=4>三次回访</option>
						</s:if>
						<s:if test="sellrecord.consultType==2">
							<option value=2>回访</option>
							<option value=0>请选择学员咨询类型</option>
							<option value=1>首次</option>
							<option value=3>二次回访</option>
							<option value=4>三次回访</option>
						</s:if>
						<s:if test="sellrecord.consultType==3">
							<option value=3>二次回访</option>
							<option value=0>请选择学员咨询类型</option>
							<option value=1>首次</option>
							<option value=2>回访</option>
							<option value=4>三次回访</option>
						</s:if>
						<s:if test="sellrecord.consultType==4">
							<option value=4>三次回访</option>
							<option value=0>请选择学员咨询类型</option>
							<option value=1>首次</option>
							<option value=2>回访</option>
							<option value=3>二次回访</option>
						</s:if>
					</select>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					<font color="#ff0000">*</font>手机：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<input name="sellrecord.mobile" type="text" maxlength="11" id="mobile" value='<s:property value="sellrecord.mobile"/>'/>
				</td>
				
				<td width="15%">
					姓名：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<input name="sellrecord.realName" type="text" maxlength="20" value='<s:property value="sellrecord.realName"/>'/>
				</td>
				
			</tr>
			
			<tr>
				<td width="15%">
					性别：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<select name="sellrecord.sex">
						<s:if test="sellrecord.sex==0">
							<option value=0>请选择学员性别</option>
							<option value=1>男</option>
							<option value=2>女</option>
						</s:if>
						<s:if test="sellrecord.sex==1">
							<option value=1>男</option>
							<option value=0>请选择学员性别</option>
							<option value=2>女</option>
						</s:if>
						<s:if test="sellrecord.sex==2">
							<option value=2>女</option>
							<option value=0>请选择学员性别</option>
							<option value=1>男</option>
						</s:if>
					</select>
				</td>
				
				<td width="15%">
					职业：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<input name="sellrecord.profession" type="text" maxlength="20" value='<s:property value="sellrecord.profession"/>'/>
				</td>
				
			</tr>
			
			<tr>
				<td width="15%">
					年龄：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<input name="sellrecord.age" type="text" maxlength="20" id="age" value='<s:property value="sellrecord.age"/>'/>
				</td>
				<td colspan="2"></td>
			</tr>
			
			<tr>
				<td width="15%">
					<s:if test="roleId==73"><font color="#ff0000">*</font>为什么想学这门课程：</s:if>
					<s:else>为什么想学这门课程：</s:else>
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea style="width:592px;" id="studyReason" name="sellrecord.studyReason" maxlength="200"><s:property value="sellrecord.studyReason" escape="false"/></textarea>
				</td>
			</tr>					
								
			<tr>
				<td width="15%">
					<s:if test="roleId==73"><font color="#ff0000">*</font>学员问题一：</s:if>
					<s:else>学员问题一：</s:else>
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.fProblem" id="fProblem" maxlength="200" style="width:592px;"><s:property value="sellrecord.fProblem" escape="false"/></textarea>
				</td>
			</tr>
				
			<tr>
				<td width="15%">
					学员问题二：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.twProblem" id="twProblem" maxlength="200" style="width:592px;"><s:property value="sellrecord.twProblem" escape="false"/></textarea>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					学员问题三：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.thProblem" id="thProblem" maxlength="200" style="width:592px;"><s:property value="sellrecord.thProblem" escape="false"/></textarea>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					<s:if test="roleId==73"><font color="#ff0000">*</font>顾虑一：</s:if>
					<s:else>顾虑一：</s:else>
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.fWorry" id="fWorry" maxlength="200" style="width:592px;"><s:property value="sellrecord.fWorry" escape="false"/></textarea>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					顾虑二：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.twWorry" id="twWorry" maxlength="200" style="width:592px;"><s:property value="sellrecord.twWorry" escape="false"/></textarea>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					顾虑三：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.thWorry" id="thWorry" maxlength="200" style="width:592px;"><s:property value="sellrecord.thWorry" escape="false"/></textarea>
				</td>
				
			</tr>
			
            <tr>
            	<td width="15%" >
            		备注:
            	</td>
            	
            	<td  style="text-align:left;" colspan="3">
	            	<textarea name="sellrecord.reason" rows="20" maxlength="200" class="required:ture" id="reason" style="width:592px; height:75px;"  ><s:property value="sellrecord.reason" escape="false"/></textarea>
            	</td>
            </tr>
            
            <tr>
            	<td width="15%" >
            		
            	</td>
            
            	<td  style="text-align:left">
            		<input type="button" onclick="test()" value="确定"/>
            	</td>
            	<td colspan="2" style="text-align:left">
            		<input type="button" onclick="closes()" value="关闭"/>
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
	<input type="hidden" name="sellrecord.crmId" value='<s:property value="sellrecord.crmId"/>'/>
   </form>
  </body>
</html>
