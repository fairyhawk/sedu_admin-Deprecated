<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
	
	function checkOutExcel(pageNo)
	{
			$("#currentPage").val(pageNo);
	     	document.searchForm.action="<%=contextPath%>/cus/cus!exportExcelFile.action";
			document.searchForm.submit();
	}
	<!--判断是否为数字-->
	function IsNum(Text){
		return /^\d+$/.test(Text);
	}
	
	function IsMoblie(Text)
	{
		return /^1\d{10}$/g.test(Text);

	}

	 function search(ObjectForm){
	  var startTime=document.getElementById("startTime").value;
	  var endTime=document.getElementById("endTime").value;
	  var startNumber=document.getElementById("startNumber").value;
	  var endNumber=document.getElementById("endNumber").value;
	  var mobile=document.getElementById("mobile").value;	  
	  if(startTime!=null&&endTime!=null){
	  if(startTime>endTime){
		 alert("结束时间要大于开始时间!");
		 return;
	  }else{
	  	  var email=$("#email").val();
	  	  email=email.replace(/(^\s*|\s*$)/g,"");
	  	  if(email.length>50){
	  	  		alert("邮箱字符不能大于50个！");
	  	  		return;
	  	  }
	  	  
	  	 //var isEmail=/^(\w+[_|\.]?)*\w+@[a-zA-Z0-9]+((_|\.)[a-zA-Z0-9])*(\.[a-z]{2,3}(\.[a-z]{2,3})*)$/g.test(email);
	  	 // if(email!=''&&!isEmail){
	  	 //		alert("请输入正确的邮箱格式！");
	  	 // 		return;
	  	 //}
		  if(mobile!="")
		  {
		  		if(mobile.length>11)
		  		{
		  			alert("手机号格式不正确");
		  			return ;
		  		}
		  }
		  
		if(startNumber!=""||endNumber!="")
		{	
		 	if(IsNum(startNumber)!=true)
			{
				alert("首次数不是数字");
				return ;
			}
			if(IsNum(endNumber)!=true)
			{
				alert("末次数不是数字");
				return ;
			}
		 
			if(startNumber!=""&&endNumber=="")
			{
				alert("末次数为空");
				return ;
			}	  
			
			if(startNumber==""&&endNumber!="")
			{
				alert("首次数为空");
				return ;
			}
			
			if(parseInt(startNumber)>parseInt(endNumber))
			{
				alert("首次数不能大于末次数");
				return ;
			}
			
		}
		if($("#cusName").val()!=''){					
			if($("#cusName").val().length>30){
				alert("昵称不能超过30个字符");
				return;
			}
		}
			var cusName=$("#cusName").val().replace(/(^\s+)|(\s+$)/g,"");
			$("#cusName").val(encodeURIComponent(cusName));
			ObjectForm.action="<%=contextPath%>/cus/cus!showCustomerList.action";
			ObjectForm.submit();
		}
	  }
	}

	


	
	function addCustomer() {
		window.location.href = "<%=contextPath%>/cus/cus!initAddCustomer.action";
	}
	
	function updateCustomer(id) {
		window.location.href = "<%=contextPath%>/cus/cus!initUpdateCustomer.action?customer.cusId=" + id;
	}
	
	function resetKey() {
		$("#op0").attr("selected","selected");
		$("input[name=queryCustomerCondition.email]").val("");
		$("input[name=queryCustomerCondition.mobile]").val("");
		$("input[name=queryCustomerCondition.startNumber]").val("0");
		$("input[name=queryCustomerCondition.endNumber]").val("999999");
		$("input[name=startTime]").val("");
		$("input[name=endTime]").val("");
		$("#op1").attr("selected","selected");
		$("#op2").attr("selected","selected");
		$("#op3").attr("selected","selected");
		$("#subjectId").val(-1);
		$("#cusName").val("");
		$("#fromType").val("-1");
		$("#isProtocal").val("-1");
//		var str=document.getElementById("queryCustomerCondition.subjectId").value;
//		alert(str);
//		if(str!=-1){
//		alert(11);
//			document.getElementById("queryCustomerCondition.subjectId").selected=-1;
//		}
	}
	
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
	}
	
	function updatePwd(id) {
		window.location.href = "<%=contextPath%>/cus/cus!initUpdatePwd.action?customer.cusId=" + id;
	}
	function freeCourse(id,type){
		if(confirm("免费赠送售卖方式，不会重复")){
		window.location.href="<%=contextPath%>/cus/cus!freeCourse.action?customer.cusId="+id+"&customer.cusType="+type;
		}
	}
	function toForgotPwd() {
		window.location.href = "<%=contextPath%>/cus/cus!toForgotPwd.action";
	}
	
	function viewCus(cusId) {
		if(cusId==0||cusId==""||cusId==null){
			alert("该用户不存在！");
			return false;
		}else{
			window.location.href = "<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=" + cusId;
		}
	}
	
	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
		$("#cusFromUrl").val(${customer.cusFromUrl});
	}
	function changeStartHH(hourStr) {
		$.each($("#startHH option"),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
		}
	function changeEndHH(hourStr) {
		$.each($("#endHH option"),function(){
			if(this.value == hourStr) {
				this.selected = true;
			}
		})
	}
	$(document).ready(function(){
		//$("#queryCustomerCondition.cusType").val(6);
		if(${queryCustomerCondition.visitType==2})
			document.getElementById("queryCustomerCondition.cusType").value=6;			
		if($("#cusName").val()!=''){
			$("#cusName").val(decodeURIComponent($("#cusName").val()));				
		}
	});	
</script>
	</head>
	<body onload="changeData()">
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">
						<s:if test="queryCustomerCondition.visitType==1">已注册学员列表</s:if>
						<s:elseif test="queryCustomerCondition.visitType==2">未支付学员列表</s:elseif>
						<s:else>学员列表</s:else>
						</font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<form name="searchForm" action="" method="post">
							<input type="hidden" name="queryCustomerCondition.visitType" value="${queryCustomerCondition.visitType }"/>
							<div class="msg-yw">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists" align="center">
								<tr align="left">
									<td></td>
									<td>
										<s:hidden name="queryCustomerCondition.currentPage" value="1" id="currentPage"/>
									  开始时间：
										<input type="text" name="startTime" readonly id="startTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${startTime}" width="10"/>
											<select name="startHH" id="startHH">
											<option value=" 00:00:00" id="op1">00:00:00</option>
											<option value=" 01:00:00">01:00:00</option>
											<option value=" 02:00:00">02:00:00</option>
											<option value=" 03:00:00">03:00:00</option>
											<option value=" 04:00:00">04:00:00</option>
											<option value=" 05:00:00">05:00:00</option>
											<option value=" 06:00:00">06:00:00</option>
											<option value=" 07:00:00">07:00:00</option>
											<option value=" 08:00:00">08:00:00</option>
											<option value=" 09:00:00">09:00:00</option>
											<option value=" 10:00:00">10:00:00</option>
											<option value=" 11:00:00">11:00:00</option>
											<option value=" 12:00:00">12:00:00</option>
											<option value=" 13:00:00">13:00:00</option>
											<option value=" 14:00:00">14:00:00</option>
											<option value=" 15:00:00">15:00:00</option>
											<option value=" 16:00:00">16:00:00</option>
											<option value=" 17:00:00">17:00:00</option>
											<option value=" 18:00:00">18:00:00</option>
											<option value=" 19:00:00">19:00:00</option>
											<option value=" 20:00:00">20:00:00</option>
											<option value=" 21:00:00">21:00:00</option>
											<option value=" 22:00:00">22:00:00</option>
											<option value=" 23:00:00">23:00:00</option>
											<option value=" 23:59:59">23:59:59</option>
											</select>(时:分:秒)
									</td>
									<td>
										结束时间：
										<input type="text" name="endTime" readonly id="endTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${endTime}" width="10"/>
											<select name="endHH" id="endHH">
											<option value=" 23:59:59" id="op0">23:59:59</option>
											<option value=" 00:00:00">00:00:00</option>
											<option value=" 01:00:00">01:00:00</option>
											<option value=" 02:00:00">02:00:00</option>
											<option value=" 03:00:00">03:00:00</option>
											<option value=" 04:00:00">04:00:00</option>
											<option value=" 05:00:00">05:00:00</option>
											<option value=" 06:00:00">06:00:00</option>
											<option value=" 07:00:00">07:00:00</option>
											<option value=" 08:00:00">08:00:00</option>
											<option value=" 09:00:00">09:00:00</option>
											<option value=" 10:00:00">10:00:00</option>
											<option value=" 11:00:00">11:00:00</option>
											<option value=" 12:00:00">12:00:00</option>
											<option value=" 13:00:00">13:00:00</option>
											<option value=" 14:00:00">14:00:00</option>
											<option value=" 15:00:00">15:00:00</option>
											<option value=" 16:00:00">16:00:00</option>
											<option value=" 17:00:00">17:00:00</option>
											<option value=" 18:00:00">18:00:00</option>
											<option value=" 19:00:00">19:00:00</option>
											<option value=" 20:00:00">20:00:00</option>
											<option value=" 21:00:00">21:00:00</option>
											<option value=" 22:00:00">22:00:00</option>
											<option value=" 23:00:00">23:00:00</option>
											</select>(时:分:秒)
								  </td>
									<td></td>
								</tr>
								<tr>
									<td width="15%"></td>
									<td>
										  注册路径：
										    <s:select name="queryCustomerCondition.subjectId"
											id="subjectId" list="subjectList"
											listKey="subjectId" listValue="subjectName" headerKey="-1"
											headerValue="---请选择---" theme="simple" style="width: 155px"></s:select>
									</td>
									<td>
										用户类型：
										<select name="queryCustomerCondition.cusType"
											id="queryCustomerCondition.cusType" style="width: 155px" 
											<s:if test="queryCustomerCondition.visitType==1||queryCustomerCondition.visitType==2">disabled="disabled"</s:if>>
											<option value="" id="op2">
												---请选择---
											</option>
											<option value="0">
												注册学员
											</option>
											<option value="1">
												内部学员
											</option>
											<option value="2">
												体验用户
											</option>
											<option value="3">
												临时学员(10天)
											</option>
											<option value="4">
												临时学员(30天)
											</option>
											<option value="5">
												临时学员(5天)
											</option>
											<s:if test="queryCustomerCondition.visitType==2">
												<option value="6">
													未支付学员
												</option>
											</s:if>
										</select>
									</td>
									<td width="15%"></td>
								</tr>
								<tr>
									<td width="15%"></td>
									<td>
										邮箱地址：
										<input type="text" id="email"
											value="<s:property value="queryCustomerCondition.email"/>"
											name="queryCustomerCondition.email" />
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;手机号：
										<input type="text" id="mobile"
											value="<s:property value="queryCustomerCondition.mobile"/>"
											name="queryCustomerCondition.mobile" />
									</td>
									<td width="10%"></td>
								</tr>
								<tr>
									<td width="10%"></td>
									<td>
										登陆次数：
										<input type="text"
											value="<s:property value="queryCustomerCondition.startNumber"/>"
											id="startNumber" name="queryCustomerCondition.startNumber"
											style="width: 100px;" />
										至
										<input type="text"
											value="<s:property value="queryCustomerCondition.endNumber"/>"
											id="endNumber" name="queryCustomerCondition.endNumber"
											style="width: 100px;" />
										次
									</td>
									
									<td>
									域名来源：
									<s:if test="fromURLType==1">
										<select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
										<option value="1">highso.cn</option>										
										</select>
										<s:hidden name="fromURLType" value="1"></s:hidden>
									</s:if>
									<s:elseif test="fromURLType==2">
										<select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
										<option value="2">highso.org</option>
										</select>
										<s:hidden name="fromURLType" value="2"></s:hidden>
									</s:elseif>
									<s:elseif test="fromURLType==3">
										<select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
										<option value="3">haixue.com</option>
										<s:hidden name="fromURLType" value="3"></s:hidden>
										</select>
									</s:elseif>
									<s:else>									
										<select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
										<option value="" id="op3">---请选择---</option>
										<option value="1">highso.cn</option>
										<option value="2">highso.org</option>
										<option value="3">haixue.com</option>
										</select>
									</s:else>
									</td>
									<td width="15%"></td>
								</tr>
								
								<tr>
									<td width="15%"></td>
									<td>
										昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：
										<input type="text" id="cusName"
											value="<s:property value="customer.cusName"/>"
											name="customer.cusName" />
									</td>
									<td>
										是否保过：
										<select name="queryCustomerCondition.isProtocal" id="isProtocal" style="width:155px">
											<option value="-1" selected="selected">---全部---</option>
											<option value="1" <s:if test="queryCustomerCondition.isProtocal==1">selected</s:if>>是</option>
											<option value="0" <s:if test="queryCustomerCondition.isProtocal==0">selected</s:if>>否</option>
										</select>
									</td>
									<td width="10%"></td>
								</tr>

								<tr>
									<td width="15%"></td>
									<td>注册位置：
										<s:select style="width: 155px;" list="#{'-1':'---全部---', '4':'首页', '1':'项目页', 
										'2':'项目购买页', '5':'haixue.com','6':'代理商','7':'内部开通'}" name="queryCustomerCondition.fromType" id="fromType" /></td>
									<td>
										<img src="<%=contextPath%>/back/images/add_a.gif" />
										<a href="javascript:search(document.searchForm)">查询</a>
										<img src="<%=contextPath%>/back/images/del_a.gif" />
										<a href="javascript:resetKey()">清空</a>
										<img src="<%=contextPath%>/back/images/down16_16.gif" /><a href="javascript:checkOutExcel(${queryCustomerCondition.currentPage })" >导出excel</a>
									</td>
									<td width="15%"></td>
								</tr>

							</table>
							</div>
						</form>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
			</table>
			<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<form name="cusForm" method="post">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
									<tr class="lists_infobg">
										<td width="5%">
											昵称
										</td>
										<td width="15%">
											电子邮件
										</td>
										<td width="10%">
											注册路径
										</td>
										<td width="5%">
											注册位置
										</td>
										<td width="5%">
											域名来源
										</td>
										<td width="15%">
											手机号码
										</td>
										<td width="9%">
											上次登录地
										</td>
										<td width="8%">
											用户类型
										</td>
										<td width="5%">
											登录次数
										</td>
										<td width="8%">
											注册时间
										</td>
										<td width="5%">
											支付数/订单数
										</td>
										<td width="5%">
											是否保过
										</td>
										<td width="*">
											操作
										</td>
									</tr>
									<s:iterator value="page.pageResult" id="customer"
										status="status">
										<tr>
											<!--  <td><input type="checkbox" name="ids" value="<s:property value="cusId"/>"/></td>-->
											<td>
												<s:property value="cusName" />
											</td>
											<td>
												<a href="#" onclick="viewCus(<s:property value="cusId" />)"><s:property
														value="email" /> </a>
											</td>
											<td>
												<s:property value="cusSubject.subjectName" />
											</td>
											<td>
												<s:if test="fromType == \"1\"">项目页</s:if>
												<s:elseif test="fromType == \"2\"">项目购买页</s:elseif>
												<s:elseif test="fromType == \"4\"">首页</s:elseif>
												<s:elseif test="fromType == \"6\"">代理商</s:elseif>
												<s:elseif test="fromType == \"7\"">内部开通</s:elseif>
												<s:elseif test="fromType == \"5\"">haixue.com</s:elseif>
												<s:else>其它</s:else>
											</td>
											<td>
												<s:property value="cusFromUrl"/>
											</td>
											<td>
												<s:property value="mobile" />
											</td>
											<td>
												<s:if test="loginRecordList.size>0">
													<s:property value="loginRecordList[0].address" />
												</s:if>
											</td>
											<td>
												<s:if test="cusType==0">
									注册学员
									</s:if>
												<s:if test="cusType==1">
									内部学员
									</s:if>
											</td>
											<td>
												<s:property value="loginTimes" />
											</td>
											<td>
												<s:date format="yyyy-MM-dd HH:mm:ss" name="regTime" />
											</td>
											<td>
												<s:if test="contractCount>0">
													<s:if test="contractStatus>0">
														<a href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">
															<font color="red"><s:property
																	value="contractStatus" />/<s:property
																	value="contractCount" />|已付款</font> </a>
													</s:if>
													<s:else>
													   <s:if test="cusType==1">
														<a
															href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">
															<font color="red">内部/代理商开通</font> </a>
													   </s:if>
													   <s:else>
														<a
															href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">
															<font color="green"><s:property
																	value="contractStatus" />/<s:property
																	value="contractCount" />|未付款</font> </a>
														</s:else>
													</s:else>
												</s:if>
											<s:else>
												0/0
											</s:else>
											</td>
											<td>
												<s:if test="isProtocal == true">是</s:if>
												<s:else>否</s:else>
											</td>
											<td>
												<a href="#"
													onclick="updateCustomer(<s:property value="cusId" />)">修改</a>
												<a href="#"
													onclick="updatePwd(<s:property value="cusId" />)">修改密码</a>
												<a href="#"
													onclick="freeCourse(<s:property value="cusId" />,<s:property value="cusType" />)">赠送售卖方式</a>
											</td>
										</tr>
									</s:iterator>
								</table>
							</form>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>
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
	<script language="javascript">
var userType=document.getElementById("queryCustomerCondition.cusType");
for (i=0;i<userType.options.length;i++)
{ 
     if(userType.options[i].value=='${queryCustomerCondition.cusType}')
     {
         userType.options[i].selected=true;break;
     } 
}
</script>
</html>
