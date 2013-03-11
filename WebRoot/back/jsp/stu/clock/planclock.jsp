<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>提醒设置</title>
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
					alert("请选择提醒信息");
				}else{
					alert("状态设置成功");
					location.href="<%=contextPath%>/stu/planclock!batchProcessPlanClock.action?batchStatus="+batchStatus+"&batchParamsId="+batchParamsId;
				}
				
			}else{
				alert("请选择处理状态");			
			}
		}
		
		
		//时间比较；
		function startVSendTime()
		{
			var s_startdate=document.getElementById("s_startdate").value;
			var s_enddate=document.getElementById("s_enddate").value;
			if(s_startdate > s_enddate)
			{
				alert("提醒开始时间不能大于提醒结束时间,请从新选择！");
				var s_enddate=document.getElementById("s_enddate");
				s_enddate.value="";
				s_enddate.focus();
			}
			
			var s_startCreatedate=document.getElementById("s_startCreatedate").value;
			var s_endCreatedate=document.getElementById("s_endCreatedate").value;
			if(s_startCreatedate > s_endCreatedate)
			{
				alert("创建开始时间不能大于创建结束时间,请从新选择！");
				var s_endCreatedate=document.getElementById("s_endCreatedate");
				s_endCreatedate.value="";
				s_endCreatedate.focus();
			}
		}
		
		//  进行校验；
		function validate()
		{
			var keyword = document.getElementById("s_kyewords").value;
			var subjectId = document.getElementById("s_subjectId").value;
			var cstatus = document.getElementById("s_cstatus").value;
			
			var startdate = document.getElementById("s_startdate").value;
			var enddate = document.getElementById("s_enddate").value;
			var startCreatedate = document.getElementById("s_startCreatedate").value;
			var endCreatedate = document.getElementById("s_endCreatedate").value;
			
			if(keyword=="" && startdate=="" && enddate=="" && startCreatedate==""&& endCreatedate=="" && subjectId == -1 && cstatus == -1){
				
				alert("请至少填写一项搜索条件");
				return false;
			}else if(startdate!="" && enddate==""){
				alert("请输入结束时间");
				var enddate=document.getElementById("s_enddate");
				enddate.value="";
				enddate.focus();
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
						<font class="lists_fleft">提醒列表</font>
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
						<form
							action="<%=contextPath%>/stu/planclock!searchPlanClock.action?queryPlanclockCondition.currentPage=1"
							name="keySearchForm" method="post" onSubmit="return validate();">
							<table cellpadding="0" cellspacing="0" border="0"
								style="border: 1px solid #00ccff" width="100%">
								
								<tr>
									<td height="40px" width="15px"></td>
									<td height="40px" width="1100px">
										<table cellpadding="10" cellspacing="0" border="0"
											width="100%">
											<tr>
												<td>
													关键字(标题)：
													<input type="text" value=""
														name="queryPlanclockCondition.kyewords" id="s_kyewords" />
													<s:select list="subjectList" id="subjectId" name="queryPlanclockCondition.subjectId"  listKey="subjectId" listValue="subjectName" headerKey="-1" headerValue="--所属项目--" >
													</s:select>
													<select name="queryPlanclockCondition.cstatus"
														id="s_cstatus">
														<option selected="selected" value="-1">
															-状态-
														</option>
														<option value="1">
															发布
														</option>
														<option value="2">
															新稿
														</option>
														<option value="0">
															删除
														</option>
													</select>
													提醒时间：
													<input type="text" readonly
														name="queryPlanclockCondition.startdate" id="s_startdate"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" />
													--
													<input type="text" readonly
														name="queryPlanclockCondition.enddate" id="s_enddate"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="startVSendTime()"/>

													创建时间：
													<input type="text" readonly
														name="queryPlanclockCondition.startCreatedate"
														id="s_startCreatedate"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"/>
													--
													<input type="text" readonly
														name="queryPlanclockCondition.endCreatedate"
														id="s_endCreatedate"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="startVSendTime()"/>

													<input type="submit" value=" 搜索 " />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="reset" value=" 重置 " />
												</td>
											</tr>
										</table>
									</td>
									<td width="16" class="lists_tright lists_bor2"></td>
								</tr>

							</table>
						</form>
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
									<td colspan="9" style="height:25px; text-align: right;">
										批量处理：
										<select name="batchStatus" id="batchStatus">
											<option selected="selected" value="-1">
												-提醒状态-
											</option>
											<option value="1">
												发布
											</option>
											<option value="2">
												新稿
											</option>
											<option value="0">
												删除
											</option>
										</select>
										<input type="button" value="确定" onclick="batchProcess()" />
									</td>
								</tr>
								<tr>
									<td colspan="9" height="40px">&nbsp;</td>
								</tr>
								<tr class="lists_infobg">
									<td width="90px">
										<input type="checkbox" name="checkAll" id="checkAll"
											onclick="checkAll()" />
										ID
									</td>
									<td width="140px">
										所属项目
									</td>
									<td width="140">
										提醒标题
									</td>
									<td width="240">
										提醒内容
									</td>
									<td width="160">
										提醒开始日期
									</td>
									<td width="160">
										提醒结束日期
									</td>
									<td width="120">
										提醒状态
									</td>
									<td width="160">
										创建时间
									</td>
									<td width="120">
										操作
									</td>
								</tr>
								<s:if test="page.pageResult != null">
									<s:iterator value="page.pageResult" id="clockList">
										<tr>
											<td>
												<input type="checkbox" name="checkID"
													id="checkID<s:property value="#clockList.clockId"/>" />
												<s:property value="#clockList.clockId" />
											</td>
											<td>
												<s:property value="#clockList.subjectnamne" />
											</td>
											<td>
												<s:property value="#clockList.ctitle" />
											</td>
											<td>
												<s:property value="#clockList.ccontent" />
											</td>
											<td>
												<s:date name="#clockList.startdate"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:date name="#clockList.enddate"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:if test="#clockList.cstatus == 1">
																		发布
																	</s:if>
												<s:elseif test="#clockList.cstatus == 2">
																		新稿
																	</s:elseif>
												<s:elseif test="#clockList.cstatus == 0">
																		删除
																	</s:elseif>
											</td>
											<td>
												<s:date name="#clockList.createdate"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<!--  <a href="<%=contextPath%>/stu/planclock!getPlanClockById.action?planclockId=<s:property value="#clockList.clockId"/>">查看</a> -->
												<a
													href="<%=contextPath%>/stu/planclock!getPlanClockById.action?planclockId=<s:property value="#clockList.clockId"/>">修改</a>
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
		<!-- <br /> 
	<a href="<%=contextPath%>/stu/calendar!getListPlanByCalendard.action">Test</a>-->
	</body>
</html>
