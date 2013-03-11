<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学习计划列表</title>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet"
			href="<%=contextPath%>/styles/usercenter/uc_public.css"
			type="text/css"></link>
		<link rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css"
			type="text/css"></link>
		<style type="text/css">
.bgimg2 {
	background: url('<%=importURL%>/images/usercenter/bgimg.jpg') repeat-x;
	height: 38px;
}

.bgimg3 {
	border-top: 1px solid #00ccff;
	height: 38px;
}
</style>
		<script type="text/javascript">
		function checkAll(){
			var check=document.getElementsByName('checkID');
			var sLength=document.getElementsByName('checkID').length;
			if($('#checkAll').attr('checked') == true){
				for(var i=0;i<sLength;i++){
					if(check[i].checked == true)
						check[i].checked = false;
					else
						check[i].checked = true;
				}
			}else{
				for(var i=0;i<sLength;i++){
					if(check[i].checked == false)
						check[i].checked = true;
					else
						check[i].checked = false;
				}
			}
		}
		// 批处理
		function batchProcess(){
			// 处理状态
			var batchStatus=$('#batchStatus').val();
			if(batchStatus != -1){
				// 拼接ID
				var batchParamsId='';
				
				var checkBath=document.getElementsByName('checkID');
				var sLengthBath=document.getElementsByName('checkID').length;
				
				for(var i=0;i<sLengthBath;i++){
					if(checkBath[i].checked==true){
						var tempId=checkBath[i].id.substring(7)+',';
						batchParamsId += tempId;
						
					}
				}
				
				if(batchParamsId == ""){
					alert("请选择学习计划信息");
				}else{
					alert("状态设置成功");
					location.href="<%=contextPath%>/stu/plan!batchProcessPlan.action?batchStatus="+batchStatus+"&batchParamsId="+batchParamsId;
				}
				
			}else{
				alert("请选择处理状态");			
			}
		}
		
		
		//时间比较；
		function startVSendTime()
		{
			var s_publish=document.getElementById("s_publish").value;
			var e_publish=document.getElementById("e_publish").value;

			if(s_publish > e_publish)
			{
				alert("发布开始时间不能大于发布结束时间,请从新选择！");
				var e_publish=document.getElementById("e_publish");
				e_publish.value="";
				e_publish.focus();
			}
			
			var s_handledate=document.getElementById("s_handledate").value;
			var e_handledate=document.getElementById("e_handledate").value;
			if(s_handledate > e_handledate)
			{
				alert("处理开始时间不能大于处理结束时间,请从新选择！");
				var s_handledate=document.getElementById("s_handledate");
				s_handledate.value="";
				s_handledate.focus();
			}
		}
		
		//  进行校验；
		function validate()
		{
			//var cusName = document.getElementById("s_cusName").value;
			var email = document.getElementById("s_email").value;
			var pstatus = document.getElementById("s_pstatus").value;
			
			var s_publish = document.getElementById("s_publish").value;
			var e_publish = document.getElementById("e_publish").value;
			var s_handledate = document.getElementById("s_handledate").value;
			var e_handledate = document.getElementById("e_handledate").value;
		
			if(email == "" && pstatus== -1 && s_publish == "" && e_publish=="" && s_handledate== "" && e_handledate == ""){
				
				alert("请至少填写一项搜索条件");
				return false;
			}else if(s_publish!="" && e_publish==""){
				alert("请输入发布结束时间");
				var e_publish=document.getElementById("e_publish");
				e_publish.value="";
				e_publish.focus();
				return false;
			}else if(s_handledate!="" && e_handledate==""){
				alert("请输入处理结束时间");
				var e_handledate=document.getElementById("e_handledate");
				e_handledate.value="";
				e_handledate.focus();
				return false;
				
			}else{
				
				return  true;
			}
		}
		</script>
	</head>
	<body>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">学习计划列表</font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor"></td>
					<td>
						<!-- 检索条件 -->
						<div class="msg-xt">
						<form
							action="<%=contextPath%>/stu/plan!searchPlan.action?queryPlanCondition.currentPage=1"
							name="keySearchForm" method="post" onSubmit="return validate();">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="40px" width="15px"></td>
									<td height="40px" width="1100px">
										<table cellpadding="10" cellspacing="0" border="0"
											width="100%">
											<tr>
												<td width="98%">
												<!-- 
												用户昵称：
													<input type="text" value=""
														name="queryPlanCondition.cusName" id="s_cusName" />
												 -->		
													学员邮箱：
													<input type="text" value=""
														name="queryPlanCondition.email" id="s_email" />
													<select name="queryPlanCondition.pstatus"
														id="s_pstatus">
														<option selected="selected" value="-1">
															-计划状态-
														</option>
														<option value="1">
															发布
														</option>
														<option value="0">
															未审核
														</option>
														<option value="2">
															删除
														</option>
													</select>
													发布时间：
													<input type="text" readonly
														name="queryPlanCondition.stapublish" id="s_publish"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" />
													--
													<input type="text" readonly
														name="queryPlanCondition.endpublish" id="e_publish"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="startVSendTime()"/>
													处理时间：
													<input type="text" readonly
														name="queryPlanCondition.stahandledate" id="s_handledate"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" />
													--
													<input type="text" readonly
														name="queryPlanCondition.endhandledate" id="e_handledate"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="startVSendTime()"/>
													
													<input type="submit" value=" 搜索 " />
													&nbsp;&nbsp;
													<input type="reset" value=" 重置 " />
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table>
						</form>
						</div>
					</td>
					<td width="16" class="lists_tright lists_bor2"></td>
				</tr>
			</table>

		

			<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr>
						<td width="12" class="lists_bor"></td>
						<td>
							<!-- 检索结果列表 -->

							<table cellspacing="1" cellpadding="0" border="0" width="100%"
								class="lists_info">
								
								<tr class="lists_infobg">
									<td colspan="8" style="height:25px; text-align: right;">
										批量处理：
										<select name="batchStatus" id="batchStatus">
											<option selected="selected" value="-1">
												-计划状态-
											</option>
											<option value="1">
												发布
											</option>
											<option value="2">
												删除
											</option>
											<option value="0">
												未审核
											</option>
										</select>
										<input type="button" value="确定" onclick="batchProcess()" />
									</td>
								</tr>
								<tr>
									<td colspan="8" height="40px">&nbsp;</td>
								</tr>
								<tr class="lists_infobg">
									<td width="8%" height="25px">
										<input type="checkbox" name="checkAll" id="checkAll"
											onclick="checkAll()" />
										ID
									</td>
									<td width="15%" height="25px">
										计划标题
									</td>
									<td width="15%" height="25px">
										学员邮箱
									</td>
									<td width="15%" height="25px">
										学员昵称
									</td>
									<td width="10%" height="25px">
										计划状态
									</td>
									<td width="15%" height="25px">
										发布日期
									</td>
									<td width="15%" height="25px">
										处理日期
									</td>
									<td width="7%" height="25px">
										操作
									</td>
								</tr>
								<s:if test="page.pageResult != null">
									<s:iterator value="page.pageResult" id="planList">
										<tr>
											<td>
												<input type="checkbox" name="checkID"
													id="checkID<s:property value="#planList.planId"/>" />
												<s:property value="#planList.planId" />
											</td>
										
											<td>
												<s:property value="#planList.plantitle" />
											</td>
											<td>
												<s:property value="#planList.email"/>
											</td>
											
											<td>
												<s:property value="#planList.cusName"/> 
											</td>
											<!-- 
											<td>
												<s:if test="%{#planList.cusName == null}">
													邮箱：<s:property value="#planList.email"/>
												</s:if>
												<s:elseif test="%{#planList.cusName ==''}">
													邮箱：<s:property value="#planList.email"/>
												</s:elseif>
												<s:else>
													昵称：<s:property value="#planList.cusName"/> &nbsp;&nbsp; | &nbsp;&nbsp;邮箱： <s:property value="#planList.email"/>
												</s:else>
											</td>
											 -->
											
											<td>
												<s:if test="#planList.pstatus == 1">
																		发布
																	</s:if>
												<s:elseif test="#planList.pstatus == 0">
																		未审核
																	</s:elseif>
												<s:elseif test="#planList.pstatus == 2">
																		删除
																	</s:elseif>
											</td>
											<td>
												<s:date name="#planList.publish"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:date name="#planList.handledate"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<a href="<%=contextPath%>/stu/plan!getPlanById.action?planId=<s:property value="#planList.planId"/>">修改</a>
											</td>
										</tr>
									</s:iterator>
								</s:if>
							</table>
						</td>
						<td width="16" class="lists_tright lists_bor2"></td>
					</tr>
					<!--  底下分页行 -->
					<tr>
						<td>
							<img src="<%=contextPath%>/back/images/tab_18.gif" />
						</td>
						<td class="lists_bottom">
							<jsp:include page="/back/jsp/common/showPage.jsp" />
						</td>
						<td>
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>

			</div>
		</div>
	</body>
</html>
