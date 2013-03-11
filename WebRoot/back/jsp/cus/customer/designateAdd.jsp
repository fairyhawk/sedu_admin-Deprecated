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
									$('#sysUserName').html(content);
									},
									error: function(){ 
										alert('error');  
									}
				  });
				  }
		}
		
		function test(){
			var scene=$('#scene').val();
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
			if(commuStatus==5){
			if(content==''){
				alert('正常接通时内容不能为空');
				return;
			}
			}
			if(content.length>400){
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
			if(studyReason.length>200){
				return;
			}
			var fProblem=$('#fProblem').val();
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
  <form action='<%=contextPath %>/cus/cusOrder!addDes.action' method="post" id="addDes" name="addDes">
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
        	
            <tr><td width="15%" >项目:</td><td width="20%"  style="text-align:left;"><s:select list="groupList" name="querySellrecordCondition.groupId" listKey="groupId" listValue="groupName" headerKey="0"
								headerValue="请选择项目" theme="simple" onchange="selected(this.value);"></s:select></td><td width="13%" ><font color="#ff0000">*</font>指派人:</td><td width="20%"  style="text-align:left;" id="sysUserName"><s:select name="sellrecord.sysUserId" id="sysUserId"  list="userList" listKey="userId" listValue="userName" 
								 theme="simple">							</s:select></td></tr>
			
			<tr><td width="15%"><font color="#ff0000">*</font>场景:</td>
			<td  width="20%"  style="text-align:left;"><select id="scene" name="sellrecord.scene">
				<option value=0>请选择场景</option>
				<option value=1>在线</option>
				<option value=2>订单</option>
				<option value=3>注册</option>
			</select></td>
            <td colspan="2"></td>
			</tr>
			<tr><td colspan="4"></td></tr>
			<tr><td>联系记录</td><td colspan="3"></td></tr>
			<tr><td colspan="4"><hr/></td></tr>
			<tr>
			
				<td width="15%">
					沟通状态:
				</td>
				
				<td width="20%"  style="text-align:left;">
					<select name="sellrecord.commuStatus" id="commuStatus">
								<option value="0">请选择用户沟通情况</option>
								<option value="1">空号</option>
								<option value="2">通话中</option>
								<option value="3">再联系</option>
								<option value="4">测试</option>
								<option value="5">正常接通</option>
								<option value="6">未接通</option>
					</select>
				</td>
				
				<td width="15%">
					咨询类型：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<select name="sellrecord.consultType" id="consultType">
						<option value=0>请选择学员咨询类型</option>
						<option value=1>首次</option>
						<option value=2>回访</option>
						<option value=3>二次回访</option>
						<option value=4>三次回访</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					<font color="#ff0000">*</font>手机：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<input name="sellrecord.mobile" id="mobile" value='<s:property value="mobile"/>' type="text" maxlength="11"/>
				</td>
				
				<td width="15%">
					姓名：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<input name="sellrecord.realName" type="text" maxlength="20"/>
				</td>
				
			</tr>
			
			<tr>
				<td width="15%">
					性别：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<select name="sellrecord.sex">
						<option value=0>请选择学员性别</option>
						<option value=1>男</option>
						<option value=2>女</option>
					</select>
				</td>
				
				<td width="15%">
					职业：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<input name="sellrecord.profession" type="text" maxlength="20"/>
				</td>
				
			</tr>
			
			<tr>
				<td width="15%">
					年龄：
				</td>
				
				<td width="20%"  style="text-align:left;">
					<input name="sellrecord.age" id="age" type="text" maxlength="20"/>
				</td>
				<td colspan="2"></td>
			</tr>
			
			<tr>
				<td width="15%">
					为什么想学这门课程：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea style="width:592px;" name="sellrecord.studyReason" class="required:true;" id="studyReason" maxlength="200"></textarea>
				</td>
			</tr>					
								
			<tr>
				<td width="15%">
					学员问题一：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.fProblem" id="fProblem" class="required:true;" maxlength="200" style="width:592px;"></textarea>
				</td>
			</tr>
				
			<tr>
				<td width="15%">
					学员问题二：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.twProblem" id="twProblem" maxlength="200" style="width:592px;"></textarea>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					学员问题三：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.thProblem" id="thProblem" maxlength="200" style="width:592px;"></textarea>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					顾虑一：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.fWorry" id="fWorry" class="required:true;" maxlength="200" style="width:592px;"></textarea>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					顾虑二：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.twWorry" id="twWorry" maxlength="200" style="width:592px;"></textarea>
				</td>
			</tr>
			
			<tr>
				<td width="15%">
					顾虑三：
				</td>
				
				<td width="20%"  style="text-align:left;" colspan="3">
					<textarea name="sellrecord.thWorry" id="thWorry" maxlength="200" style="width:592px;"></textarea>
				</td>
				
			</tr>
			
            <tr>
            	<td width="15%" >
            		备注:
            	</td>
            	
            	<td  style="text-align:left;" colspan="3">
	            	<textarea name="sellrecord.reason" rows="20" maxlength="200" id="reason" style="width:592px; height:75px;"   ></textarea>
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
	<input type="hidden" name="sellrecord.cusId" value='<s:property value="cusId"/>'/>
   </form>
  </body>
</html>
