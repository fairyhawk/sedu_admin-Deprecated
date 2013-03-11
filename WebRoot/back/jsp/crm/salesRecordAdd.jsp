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
		<title><script type="text/javascript">
			var scanType='<s:property value="scanType"/>'; 
			if(scanType==0){
				document.write('添加咨询记录');
			}else{
				document.write('查看详情');
			}
		</script></title>
    	<script type="text/javascript">
		$().ready(function (){
			$('#salesRecordAdd').validate();
			var scanType='<s:property value="scanType"/>'; 
			if(scanType==0){
				$('#title').html('添加咨询记录');
			}else{
				$('#title').html('查看详情');
			}
			 reasonType=['-原因类型','客户主观原因','客户客观原因','数据原因','其他原因']
			 reason=[[],['','价格高','试听不满意','对比了解中','对产品不认可','不信任网络教学'],['','差评','自学','无意向','没报考','已报班','不符合报考条件'],['','假号码','空号','挂机','无人接听'],['']]
			var reaType='';
			for(var i=0;i<reasonType.length;i++){
				reaType+='<option value='+i+'>'+reasonType[i]+'</option>';
			}
			$('#reasonType').html(reaType);
		});
		
		function cancle(){
			var roleId='<s:property value="roleId"/>';
			var scanType='<s:property value="scanType"/>'; 
			if(scanType==2){
				window.location='<%=contextPath%>/crm/crmChance!getChanceList.action?pageQuery.currentPage=1';
			}else if(roleId==73){
				window.location='<%=contextPath%>/crm/crmChance!getSalesChanceList.action?queryChanceCondition.currentPage=1';
			}else{
				window.location='<%=contextPath%>/crm/crmChance!getAllChance.action?pageQuery.currentPage=1';
			}
		}
		
		function test(){
			var scanType='<s:property value="scanType"/>'; 
			var roleId='<s:property value="roleId"/>';
			var userId='<s:property value="chanceRecordDTO.userId"/>';
			var usersId=$('#usersId').val();
			
			if(scanType==0){
				var commuStatus=$('#commuStatus').val();
				var focus=$('#focus').val();
				
				if(roleId!=73){
					var groupId=$('#groupId').val();
					if(groupId==0){
						alert('请选择项目组');
						return;
					}
				
				
					if(usersId==0){
							alert('请选择坐席');
							return;
						}
					
					if(usersId==userId&&roleId!=73){
						alert('请选择想要改派的销售坐席');
						return;
					}
				}else{
					if(commuStatus==0){
						alert('请选择沟通状态');
						return;
					}
					if(commuStatus==5&&focus==''){
						alert('正常接通时关注点为必填项');
						return;
					}
					if(commuStatus==8&&focus==''){
						alert('已购买时关注点为必填项');
						return;
					}
					if(focus.length>200){
						return;
					}
					
					var studyReason=$('#studyReason').val();
					if(studyReason.length>200){
						return;
					}
					
					var remark=$('#remark').val();
					if(remark.length>200){
						return;
					}
					
					var rea=$('#reason').val();
					var reaType=$('#reasonType').val();
					var reasons;
					if(reaType<4&rea==0){
						alert('请选择未成单原因');
						return;
					}
					if(reaType==4&rea.replace(/(^\s*)|(\s*$)/g, "")==''){
						alert('请填写未成单原因');
						return;
					} 
					if(reaType>0&reaType<4)reasons=reasonType[reaType]+'-'+reason[reaType][rea];
					if(reaType==4)reasons=reasonType[reaType]+'-'+rea;
					$('#reasons').val(reasons);
				}
				var ss=window.confirm('确认提交?');
				if(ss){
				if(roleId!=73){
				// 销售组长重新指派坐席
				document.salesRecordAdd.action='<%=contextPath%>/crm/crmChance!crmUpdateAllocated.action';
				document.salesRecordAdd.submit();
				return;
				}
				// 销售 添加沟通记录
				document.salesRecordAdd.action='<%=contextPath%>/crm/crmChance!crmAddRecord.action';
				document.salesRecordAdd.submit();
				}else{
					return;
				}
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
		
		function showHistory(){
			var s='联系历史记录<a href="javascript:hideHistory();">&#60;&#60;收缩</a>'
			$('#funcEdit').html(s);
			$('#historys').fadeIn();
    	}
    	function hideHistory(){
    		var s='联系历史记录<a href="javascript:showHistory();">&#62;&#62;展开</a>';
    		$('#funcEdit').html(s);
			$('#historys').fadeOut();
    	}
    	
    	function selectReasonType(value){
    		var reasonStr;
    		if(value==0){
    			reasonStr='<select  id="reason"><option value='+0+'>-原因</option></select>';
    		}else if(value!=4){
    			var reasonArr=reason[value];
    			reasonStr='<select  id="reason">';
    			for(var i=0;i<reasonArr.length;i++){
	    			if(i==0){
	    				reasonStr+='<option value='+i+'>-原因</option>';
	    			}else{
	    				reasonStr+='<option value='+i+'>'+reasonArr[i]+'</option>';
	    			}
    			}
    			reasonStr+='</select>'
    		}else if(value==4)
    		reasonStr='<input type="text" id="reason" maxlength="70"/>';
    		$('#reasonText').html(reasonStr);
    	}
		</script>
    


  </head>
  
  <body>
  	<form action="" id="salesRecordAdd" name="salesRecordAdd" method="post">
   	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">

		<tr >
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
			  <input type="hidden" value='<s:property value=""/>'/>
			  <font class="lists_fleft" id="title">添加咨询记录</font></td>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		
			<tr>
			<td width="12" class="lists_bor">			</td>
			<td valign="top">
			
			<!--机会属性-->
			<div class="crm_tit">机会属性</div>
   		<table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
        	
        	
        	
        		<tr>
					<td width="120" class="crm_tableFR">
						<font color="#ff0000">*</font>销售机会ID：					</td>
					<td  width="600"  class="crm_tableFL">&nbsp;
						<input type="hidden" name="crmChanceId" value='<s:property value="chanceRecordDTO.id"/>'/>
						<input type="text" disabled="disabled" value='<s:property value="chanceRecordDTO.id"/>'/>					</td>
				    <td  width="120"  class="crm_tableFR"><font color="#ff0000">*</font>机会来源：		        	 </td>
				    <td  width="600"  class="crm_tableFL"><select name="select" id="select" >
                      <s:if test="chanceRecordDTO.origin==1">
                        <option value="1">自然注册</option>
                      </s:if>
                      <s:if test="chanceRecordDTO.origin==2">
                        <option value="2">乐语在线</option>
                      </s:if>
                    </select></td>
        		</tr>
				
				<tr>
					<td  class="crm_tableFR">
						乐语咨询ID：					</td>
					<td  class="crm_tableFL">&nbsp;
						<input type="text" id="leYuID" name="record.leyuId"/>					</td>
				    <td width="120"  class="crm_tableFR"><span class="crm_tableFR"><font color="#ff0000">*</font>项目：</span></td>
				    <td  class="crm_tableFL"><select name="select2" >
                      <s:iterator value="subjectList" id="subjectList">
                        <s:if test="#subjectList.subjectId==chanceRecordDTO.subjectId">
                          <option value='&lt;s:property value=&quot;#subjectList.subjectId&quot;/&gt;' selected="selected">
                          <s:property value="#subjectList.subjectName"/>
                          </option>
                        </s:if>
                      </s:iterator>
                    </select></td>
				</tr>
				
				<tr>
					<td  class="crm_tableFR">
						<font color="#ff0000">*</font>手机号：					</td>
					<td  class="crm_tableFL">&nbsp;
						<input type="text"  disabled="disabled" value='<s:property value="user.mobile"/>' />					</td>
				    <td width="120"  class="crm_tableFR"><span class="crm_tableFR">用户email： </span></td>
				    <td  class="crm_tableFL"><input type="text" disabled="disabled" value='<s:property value="user.email"/>'/></td>
				</tr>
				
				
				<tr>
					<td  class="crm_tableFR">
						<font color="#ff0000">*</font>用户跟踪状态：					</td>
					<td  class="crm_tableFL">&nbsp;
						<select id="followStatus" name="chance.followStatus">
							<s:if test="chanceRecordDTO.followStatus==0">
								<option value="0">---请选择---</option>
								<option value="1">放弃</option>
								<option value="2">跟踪</option>
								<option value="3">热点</option>
								<option value="4">成交</option>
								<option value="5">预约</option>
							</s:if>
							<s:if test="chanceRecordDTO.followStatus==1">
								<option value="1">放弃</option>
								<option value="2">跟踪</option>
								<option value="3">热点</option>
								<option value="4">成交</option>
								<option value="5">预约</option>
							</s:if>
							<s:if test="chanceRecordDTO.followStatus==2">
								<option value="2">跟踪</option>
								<option value="1">放弃</option>
								<option value="3">热点</option>
								<option value="4">成交</option>
								<option value="5">预约</option>
							</s:if>
							<s:if test="chanceRecordDTO.followStatus==3">
								<option value="3">热点</option>
								<option value="1">放弃</option>
								<option value="2">跟踪</option>
								<option value="4">成交</option>
								<option value="5">预约</option>
							</s:if>
							<s:if test="chanceRecordDTO.followStatus==4">
								<option value="4">成交</option>
								<option value="1">放弃</option>
								<option value="2">跟踪</option>
								<option value="3">热点</option>
								<option value="5">预约</option>
							</s:if>
							<s:if test="chanceRecordDTO.followStatus==5">
							    <option value="5">预约</option>
								<option value="4">成交</option>
								<option value="1">放弃</option>
								<option value="2">跟踪</option>
								<option value="3">热点</option>
							</s:if>
						</select>					</td>
				    <td width="120"  class="crm_tableFR"><span class="crm_tableFR">创建时间： </span></td>
				    <td  class="crm_tableFL"><input type="text" disabled="disabled" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="chanceRecordDTO.chanceStime" />'/></td>
				</tr>
				
				<tr>
					<td  class="crm_tableFR">
						最后指派时间：					</td>
					<td  class="crm_tableFL">&nbsp;
						<input type="text" disabled="disabled" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="chanceRecordDTO.chanceUtime" />'/>					</td>
				    <td width="120"  class="crm_tableFR">&nbsp;</td>
				    <td  class="crm_tableFL">&nbsp;</td>
				</tr> 
				
				
				<s:if test="roleId!=73">
   		 	 <tr>
	   			 <td  class="crm_tableFR">
	   				<font color="#ff0000">*</font> 项目组：	 </td>
	   			 
   				 <td class="crm_tableFL">&nbsp;
								<select id="groupId" onchange="selected(this.value);">
									<option value=0>请选择项目组</option>
									<s:iterator value="groupList" id="groupList">
										<s:if test="groupIds==#groupList.groupId">
											<option value='<s:property value="#groupList.groupId"/>' selected="selected"><s:property value="#groupList.groupName"/></option>
										</s:if><s:else>
										<option value='<s:property value="#groupList.groupId"/>'><s:property value="#groupList.groupName"/></option>
										</s:else>
									</s:iterator>
								</select>			</td>
			     <td width="120" class="crm_tableFR">&nbsp;</td>
			     <td class="crm_tableFL">&nbsp;</td>
   		 	 </tr>
			
			<tr>	
				<td  class="crm_tableFR">
					<font color="#ff0000">*</font>销售坐席：				</td>
             	<td width="600" class="crm_tableFL" id="sysUserName" >&nbsp;
            		<select name="userId" id="usersId">
            			<option value="0">请选择坐席</option>
			            <s:iterator value="userList" id="userList">
			            	<s:if test="#userList.userId==chanceRecordDTO.userId">
			            		<option value='<s:property value="#userList.userId"/>' selected="selected">
			            			<s:property value="#userList.userName"/>
			            		</option>
			            	</s:if>
			            	<s:if test="#userList.userId!=chanceRecordDTO.userId">
			            		<option value='<s:property value="#userList.userId"/>'>
			            			<s:property value="#userList.userName"/>
			            		</option>
			            	</s:if>
			            </s:iterator>
            		</select>				</td>
			    <td width="120" class="crm_tableFR" id="sysUserName" >&nbsp;</td>
			    <td width="600" class="crm_tableFL" id="sysUserName" >&nbsp;</td>
			</tr>
   		</s:if>
   		<s:else>
   			<tr>
   				<td  class="crm_tableFR">
   					<font color="#ff0000">*</font>销售坐席：   				</td>
           	 	<td width="600" class="crm_tableFL">&nbsp;
            		<select name="userId" >
			            <s:iterator value="userList" id="userList">
			            	<s:if test="#userList.userId==chanceRecordDTO.userId">
			            		<option value='<s:property value="#userList.userId"/>' selected="selected">
			            			<s:property value="#userList.userName"/>
			            		</option>
			            	</s:if>
			            </s:iterator>
            		</select>				</td>
   			    <td width="120" class="crm_tableFR">&nbsp;</td>
   			    <td width="600" class="crm_tableFL">&nbsp;</td>
   			</tr>
   		</s:else>
   		
   		<s:iterator value="recordDTOList" id="recordList">
   			<tr>
   				<td  class="crm_tableFR">   				</td>
           	 	<td width="600" class="crm_tableFL">&nbsp;
            		<label><s:property value="#recordList.userName"/>&nbsp;&nbsp;<s:date format="yyyy-MM-dd HH:mm:ss" name="#recordList.createTime" /></label>				</td>
   			    <td width="120" class="crm_tableFR">&nbsp;</td>
   			    <td width="600" class="crm_tableFL">&nbsp;</td>
   			</tr>
   		</s:iterator>
				</table>
				<!--机会属性结束-->
			
			</td><td width="16" class="lists_tright lists_bor2">			</td>
			<tr>
			
			<tr>
			<td width="12" class="lists_bor">			</td>
			<td valign="top">
			<!--基础信息-->	
			<div class="crm_tit">基础信息</div>
        	<table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
        		<tr>
					<td width="120" class="crm_tableFR">
						姓名：					</td>
					<td  width="600"  class="crm_tableFL">&nbsp;
						<input type="text" disabled="disabled"  maxlength="20" value='<s:property value="user.realName"/>' disabled="disabled" />		</td>
				    <td  width="120"  class="crm_tableFR"><span class="crm_tableFR">性别： </span></td>
				    <td  width="600"  class="crm_tableFL">
					<select >
							<s:if test="user.sex==0"><option value="0">未知</option></s:if>
							<s:if test="user.sex==1"><option value="1">女士</option></s:if>
							<s:if test="user.sex==2"><option value="2">先生</option></s:if>
						</select>
					
					</td>
        		</tr>
				
				<tr>
					<td  class="crm_tableFR">
						职位：					</td>
					<td  class="crm_tableFL">&nbsp;
						<input type="text" id="age" maxlength="20" disabled="disabled" value='<s:property value="user.profession"/>' />					</td>
				    <td width="120"  class="crm_tableFR"><span class="crm_tableFR">生日： </span></td>
				    <td  class="crm_tableFL"><input class="inp_txt width315" type="text"
						onclick="SelectDate(this,'yyyy\/MM\/dd')" disabled="disabled" width="10" value='<s:property value="user.birthday"/>'/></td>
				</tr>
				
				<tr>
					<td  class="crm_tableFR">
						地址：					</td>
					<td  class="crm_tableFL">&nbsp;
						<input type="text"  maxlength="50" disabled="disabled" value='<s:property value="user.address"/>' />					</td>
				    <td width="120"  class="crm_tableFR"><span class="crm_tableFR">备注： </span></td>
				    <td  class="crm_tableFL"><textarea name="textarea" id="remarks" width="400px" maxlength="300"><s:property value="user.remarks"/>
				    </textarea></td>
				</tr>
        	</table>
			<!-- 信息结束-->
			
			</td><td width="16" class="lists_tright lists_bor2">			</td>
			<tr>
			
			<tr>
			<td width="12" class="lists_bor">			</td>
			<td valign="top">
			<!--沟通记录-->
        	<s:if test="scanType==0">
	        	<div class="crm_tit">沟通记录</div>
				<table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
	        	
		        	<tr>
						<td width="120" class="crm_tableFR">
							<font color="#ff0000">*</font>沟通状态：						</td>
						<td  width="600"  class="crm_tableFL">&nbsp;
							<select name="record.commStatus" id="commuStatus">
										<option value="0">请选择用户沟通情况</option>
										<option value="1">空号</option>
										<option value="3">再联系</option>
										<option value="4">测试</option>
										<option value="5">正常接通</option>
										<option value="6">未接通</option>
										<option value="7">假号码</option>
										<option value="8">已购买</option>
							</select>						</td>
					    <td  width="120"  class="crm_tableFR"><span class="crm_tableFR">为什么想学这门课程：</span></td>
					    <td  width="600"  class="crm_tableFL"><textarea width="400px" id="studyReason" name="record.whyLearm" maxlength="200"></textarea></td>
		        	</tr>
					
					<tr>
						<td  class="crm_tableFR">
							<font color="#ff0000">*</font>未成单原因：<input type="hidden" name="record.reasons" id="reasons"/>						</td>
						<td  class="crm_tableFL">&nbsp;
							<select  onchange="selectReasonType(this.value)" id="reasonType">
							</select>	
							<span id="reasonText"><select  id="reason">
								<option value="0">-原因</option>
							</select></span>
									
						</td>
					    <td width="120"  class="crm_tableFR"><span class="crm_tableFR">备注：</span></td>
					    <td  class="crm_tableFL"><textarea width="400px" id="remark" name="record.remarks" maxlength="200"></textarea></td>
					</tr>
					<tr>
						<td  class="crm_tableFR">
							关注点：						</td>
						<td  class="crm_tableFL">&nbsp;
							<textarea width="400px" id="focus" name="record.concerns" maxlength="200"></textarea>						</td>
					    <td width="120"  class="crm_tableFR"><span class="crm_tableFR"></span></td>
					    <td  class="crm_tableFL"></td>
					</tr>
		        </table>
	        </s:if>
			<!--沟通记录结束 -->
			</td><td width="16" class="lists_tright lists_bor2">			</td>
			<tr>
			
			<tr>
			<td width="12" class="lists_bor">			</td>
			<td valign="top">
			<!--历史记录-->
        <div class="crm_tit" id="funcEdit">联系历史记录<a href="javascript:hideHistory();">&#60;&#60;收缩</a></div>
        <div id="historys" >
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
			<s:if test="recordFirstList!=null&&recordFirstList.size()!=0">
				<tr>
					<td width="5%" >
						首咨
					</td>
					<td  width="95%"  class="crm_tableFL">
						<table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
								<tr>
									<td width="3%">
										序号
									</td>
									
									<td width="10%">
										销售坐席
									</td>
									
									<td width="30%">
										关注点
									</td>
									
									<td width="5%">
										沟通状态
									</td>
									
									<td width="10%">
										未成单原因
									</td>
									
									<td width="20%">
										学习原因
									</td>
									
									<td width="20%">
										备注
									</td>
									
									<td width="8%">
										提交时间
									</td>
								</tr>
							
							<s:iterator value="recordFirstList" id="recordFirstList" status="count">
								<tr>
									<td>
										<s:property value="#count.index+1"/>
									</td>
									
									<td>
										<s:property value="#recordFirstList.userName"/>
									</td>
									
									<td>
										<s:property value="#recordFirstList.concerns" escape="false"/>
									</td>
									
									<td>
										<s:if test="#recordFirstList.commStatus==1">空号</s:if>
										<s:if test="#recordFirstList.commStatus==2">通话中</s:if>
										<s:if test="#recordFirstList.commStatus==3">再联系</s:if>
										<s:if test="#recordFirstList.commStatus==4">测试</s:if>
										<s:if test="#recordFirstList.commStatus==5">正常接通</s:if>
										<s:if test="#recordFirstList.commStatus==6">未接通</s:if>
										<s:if test="#recordFirstList.commStatus==7">假号码</s:if>
										<s:if test="#recordFirstList.commStatus==8">已购买</s:if>
									</td>
									
									<td><s:property value="reasons"/>
									</td>
									
									<td>
										<s:property value="#recordFirstList.whyLearm" escape="false"/>
									</td>
									
									<td>
										<s:property value="#recordFirstList.remarks" escape="false"/>
									</td>
									
									<td>
										<s:date format="yyyy-MM-dd HH:mm:ss" name="#recordFirstList.createTime" />
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				</s:if>
				<s:if test="recordSecList!=null&&recordSecList.size()!=0">
				<tr>
					<td width="5%">
						回访
					</td>
					<td colspan="2" width="95%"  class="crm_tableFL">
						<table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info crm_lineh">
							
							<tr>
									<td width="3%">
										序号
									</td>
									
									<td width="10%">
										销售坐席
									</td>
									
									<td width="30%">
										关注点
									</td>
									
									<td width="5%">
										沟通状态
									</td>
									
									<td width="10%">
										未成单原因
									</td>
									
									<td width="20%">
										学习原因
									</td>
									
									<td width="20%">
										备注
									</td>
									
									<td width="8%">
										提交时间
									</td>
								</tr>
							
							<s:iterator value="recordSecList" id="recordSecList" status="count">
								<tr>
									<td>
										<s:property value="#count.index+1"/>
									</td>
									
									<td>
										<s:property value="#recordSecList.userName"/>
									</td>
									
									<td>
										<s:property value="#recordSecList.concerns" escape="false"/>
									</td>
									
									<td>
										<s:if test="#recordSecList.commStatus==1">空号</s:if>
										<s:if test="#recordSecList.commStatus==3">再联系</s:if>
										<s:if test="#recordSecList.commStatus==4">测试</s:if>
										<s:if test="#recordSecList.commStatus==5">正常接通</s:if>
										<s:if test="#recordSecList.commStatus==6">未接通</s:if>
										<s:if test="#recordSecList.commStatus==7">假号码</s:if>
										<s:if test="#recordSecList.commStatus==8">已购买</s:if>
									</td>
									
									<td><s:property value="reasons"/>
									</td>
									
									<td>
										<s:property value="#recordSecList.whyLearm" escape="false"/>
									</td>
									
									<td>
										<s:property value="#recordSecList.remarks" escape="false"/>
									</td>
									
									<td>
										<s:date format="yyyy-MM-dd HH:mm:ss" name="#recordSecList.createTime" />
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				</s:if>
			</table>
			<!--历史记录结束-->
			</div>
			</td><td width="16" class="lists_tright lists_bor2">			</td>
			<tr>
			<td width="12" class="lists_bor">			</td>
			<td>
		
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="lists_info">
				<tr>
	            	<td width="280px">	            	</td>
	            	<td class="crm_tableFL">
	            		<s:if test="scanType==0">
	            		<input type="button" onclick="test()"  value="确定"/>&nbsp;&nbsp;&nbsp;	            		</s:if>
	            		<input type="button" onclick="cancle()" value="返回"/>	            	</td>
	            </tr>
	         </table>        </td>
			<td width="16" class="lists_tright lists_bor2">			</td>
		</tr>
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
			<td class="lists_bottom">			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
		</tr>
	</table>
	</form>
  </body>
</html>
