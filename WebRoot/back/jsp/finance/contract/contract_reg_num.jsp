<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>订单列表</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script>
			 function trim(str){  //删除左右两端的空格
			 	return str.replace(/(^\s*)|(\s*$)/g, "");
			 }
			 function searchRegNum(ObjectForm){
			     var CusStartTime=document.getElementById("CusStartTime").value;
			     var CusEndTime=document.getElementById("CusEndTime").value;
			     var cusWebFrom_ = document.getElementById("cusWebFrom").value;
			     var cusWebAgent_ = document.getElementById("cusWebAgent").value;
			     var cusWebFrom = trim(cusWebFrom_);
			     var cusWebAgent = trim(cusWebAgent_);
			   	
			     if(CusStartTime==""&&CusEndTime==""&&cusWebFrom==""&&cusWebAgent==""){
			       alert("请输入推广网盟");
			     }else if(CusStartTime != "" && CusEndTime==""){
			     	 alert("请选择注册结束时间!");
			     }else if(CusStartTime == "" && CusEndTime!=""){
			     	 alert("请选择注册开始时间!");
			     }else if((CusStartTime != "" && CusEndTime!="")&&(CusStartTime > CusEndTime)){
			     	 alert("结束时间不能小于开始时间!");
			     }else{
			   		ObjectForm.action="<%=contextPath%>/finance/regnum.action";
					ObjectForm.submit();
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
						<font class="lists_fleft">查询结果</font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
						<td width="12" class="lists_bor">
						</td>
						<td aling="center">
						<form name="f2" method="post" action="">
							<table width="50%" border="0" cellspacing="1" cellpadding="0"
								class="lists" align="center">
								<tr>
									<td>
									注册开始时间：<input type="text" name="queryCustomerCondition.startTime" id="CusStartTime" readonly
										onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="${queryCustomerCondition.startTime}"/>
									</td>
									<td>
									注册结束时间：<input type="text" name="queryCustomerCondition.endTime" id="CusEndTime" readonly
										onFocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="${queryCustomerCondition.endTime}"/>
									</td>
								</tr>
								<tr> 
									<td>推&nbsp;广&nbsp;网&nbsp;盟：
										<input type="text" name="queryCustomerCondition.cusWebFrom" id="cusWebFrom" value="${queryCustomerCondition.cusWebFrom}" />
									</td>
									
									<td>站 &nbsp; 长 &nbsp;ID：
										<input type="text" name="queryCustomerCondition.cusWebAgent" id="cusWebAgent"  value="${queryCustomerCondition.cusWebAgent}"/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="lists_tleft">
										<img src="<%=contextPath%>/back/images/add_a.gif" />
										<a href="javascript:searchRegNum(document.f2)">查询</a>
										<s:hidden name="exportType" id="exportType" ></s:hidden>
									</td>
								</tr>
							</table>
							</form>
				<tr>
					<td scope="2" height="25" width="12" class="lists_bor"></td>
				</tr>
				<tr>
					<td width="12" class="lists_bor"></td>
					<td align="center" style="border: 1;" frame="border">

						<br />
						<table width="50%" height="193" border="0" cellpadding="0"
							cellspacing="1" class="tabletj" style="border: 0 none;">
							<tr>
								<td height="40" colspan="2" align="center" valign="middle"
									style="padding-top: 10px">
									<strong>注册学员总人数:</strong>&nbsp;
									<s:property value="regNum" />
								</td>
							</tr>
							<tr>
								<td width="217" height="47" align="center" valign="middle">
									<strong>未下单人数</strong>：<s:property value="notContractNum" />
								</td>
								<td width="248" align="center" valign="middle">
									<strong>已下单人数</strong> ：<s:property value="contractNum" />
								</td>
							</tr>
							<tr>
								<td height="38" colspan="2" align="center" valign="middle"
									style="padding-top: 10px">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="38" colspan="2" align="center" valign="middle"
									style="padding-top: 10px">
									<strong>订单总单数： <s:property value="PayNum" /> 单</strong>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="center" valign="middle"style="padding-top: 10px; padding-left: 20px">
									<strong>未支付： <s:property value="PayfalNum" /> 单</strong>
								</td>
								<td height="43" align="center" valign="middle"
									style="padding-top: 10px; padding-left: 20px">
									<strong>已支付： <s:property value="PaySucNum" /> 单</strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td scope="2" height="25" width="12" class="lists_bor"></td>
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
			
			<!-- 
			<div align="center" style="color: red;">
			<b>在此推广域名下注册的学员数有：<s:property value="regNum" /> 位。<br />
			<b>未下过订单学员数量：<s:property value="notContractNum" /> 位，已下订单的学员数量：<s:property value="contractNum"/> 位。<br />
			 <b>所有学员下订单的总量：<s:property value="PayNum" /> 单，未支付：<s:property value="PayfalNum" /> 单，已支付：<s:property value="PaySucNum" />单。 
			</div>
			 -->
		</div>
	</body>
</html>
