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
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>		
		<script language="JavaScript">
   function search(ObjectForm){
     var startTime=document.getElementById("startTime").value;
     var endTime=document.getElementById("endTime").value;
     if(startTime!=null&&endTime!=null){
     if(startTime>endTime){
        alert("结束时间要大于开始时间!");
        return false;
     }else{
   		ObjectForm.action="<%=contextPath%>/finance/backContract!getContractCODList.action";
		ObjectForm.submit();
   	}
     }
   }
   //货到付款导出excel
   function excelHDFK(ObjectForm){
     var startTime=document.getElementById("startTime").value;
     var endTime=document.getElementById("endTime").value;
     if(startTime!=null&&endTime!=null){
     if(startTime>endTime){
        alert("结束时间要大于开始时间!");
        return false;
     }else{
   		ObjectForm.action="<%=contextPath%>/finance/backContract!hdfkExportExcel.action";
		ObjectForm.submit();
   	}
     }
   }
   function cancel(){
   document.getElementById("startTime").value="";
   document.getElementById("endTime").value="";
   document.getElementById("payStartTime").value="";
   document.getElementById("payEndTime").value="";
   document.getElementById("contract.contractId").value="";
   document.getElementById("status").value="";
   document.getElementById("mail").value="";
   document.getElementById("contract.contractFrom").value="";
   
   $("#subjectId option:eq(0)").attr("selected", true);
   
   $("#op1").attr("selected","selected");
   $("#op0").attr("selected","selected");
   $("#op2").attr("selected","selected");
   $("#op3").attr("selected","selected");
   $("#op4").attr("selected","selected");
   }
   
   	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
		changefkStartHH('<s:property value="fkStartHH"/>');
		changefkEndHH('<s:property value="fkEndHH"/>')
		$("#status").val(${status});
	}
	function onchangePayType(payType){
			$.ajax({
				url: "<%=contextPath%>/finance/backContract!getContractByPayType.action",
				data : {payType : payType},
				type : "post",
				cache : false,
				dataType : "json",
				error : function(){
				alert('error');
				},
				success:onchangecallback
			});
		  }
	function onchangecallback(result){
			var scList = eval(result.returnMessage);
			document.getElementById('spId').options.length = 0; //清空原有的option 
			$("#spId").html("<option value=-1>请选择</option>");
			var str="";
			for(var i=0;i<scList.length;i++){  
				str+="<option value='"+scList[i].CId+"'>"+scList[i].CName+"</option>"  
			}  
			if(str!=""){
				$("#spId").html(str);  
			}
	}
	function onchangePayType1(payType){
			var str="";
			if(payType==2){
				str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未激活</option>"+
				 	  "<option value='1'>已激活</option>"+
				 	  "<option value='2'>已取消</option>";
			}else if(payType==1){
				  str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>";
			}else if(payType==0){
				str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='2'>赠送</option>"+
				 	  "<option value='4'>修复/赠送</option>";
			}
			$("#spId").html(str); 
		  }
   	function onSelectStatus(status){ 
		document.getElementById('status').value = status;
	}

	function changeStartHH(hourStr) {
		$.each($("#startHH option"),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
		}

	function changefkStartHH(hourStr) {
		$.each($("#fkStartHH option"),function(){
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
	function changefkEndHH(hourStr) {
		$.each($("#fkEndHH option"),function(){
			if(this.value == hourStr) {
				this.selected = true;
			}
		})
	}
	function getCusByEmail(str){
	   		ObjectForm.action="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email='"+str+"'";
	}
	
	var onClick = function (fzbk_id){
		var div = document.getElementById(fzbk_id);
		var visible = div.style.display;
		if(visible == "none"){
			document.getElementById(fzbk_id).style.display = "block";
		}else{
			document.getElementById(fzbk_id).style.display = "none";
		}
		}
   function openWin(ctId) { 
	window.open ("<%=contextPath%>/finance/backContract!getContractView.action?contract.id="+ctId, "newwindow", "height=600, width=800, toolbar=no, menubar=yes, scrollbars=no, resizable=no, location=no, status=no");
　
　　}
  
  	//修改所属项目					
	function getSubjectName(obj){
		var subValue = obj.options[obj.selectedIndex].value;
		var subText = obj.options[obj.selectedIndex].text;
	}
</script>
	</head>
	<body onload="changeData()">
		<div>
			<form name="f1" method="post"
				action="bopayendTimeoks!getBooksList.action">
				<s:hidden name="queryContractCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">订单列表</font>
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
						<div class="msg-cw">	
							<table width="70%" border="0" cellspacing="1" cellpadding="0"
								class="lists" align="center">
								<tr>
									<td>
										订 单 编 号：&nbsp;
										<input type="text" name="contract.contractId"
											id="contract.contractId" value="${contract.contractId}" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										订 单 来 源：&nbsp;
										<input type="text" name="contract.contractFrom"
											id="contract.contractFrom" value="${contract.contractFrom}" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
							
								<tr>
									<td>
										邮 箱 地 址：&nbsp;
										<input type="text" name="mail" id="mail" value="${mail}" />

									</td>
									<td>
										订 单 状 态：&nbsp;
										<select name="status" id="status" style="width: 155px">
											<option value="">
												---请选择---
											</option>
											<option value="0">
												未激活
											</option>
											<option value="1">
												已激活
											</option>
											<option value="3">
												已取消
											</option>
											<option value="4">
												已退费
											</option>
										</select> 
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td scope="2">
										所 属 项 目：&nbsp;
										<s:select style="width: 155px" list="subjectList" id="subjectId" name="subjectId"  listKey="subjectId" listValue="subjectName" headerKey="-1" headerValue="--所属项目--" onchange="getSubjectName(this)">
										</s:select>
									</td>
								</tr>
								<tr>
									<td>
										下单开始时间：
										<input type="text" name="startTime" readonly id="startTime"
											onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"
											value="${yestartTime}"/>
										<select name="startHH" id="startHH">
											<option value=" 00:00:00" id="op4">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
									<td>
										下单结束时间：
										<input type="text" name="endTime" readonly id="endTime"
											onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"
											value="${yeendTime}"/>
										<select name="endHH" id="endHH">
											<option value=" 23:59:59" id="op0">
												23:59:59
											</option>
											<option value=" 00:00:00">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
								</tr>
								<tr>
									<td>
										付款开始时间：
										<input type="text" name="payStartTime" readonly
											id="payStartTime" onclick="WdatePicker()"
											onfocus="inputOnFocus()" value="${payStartTime}" />
										<select name="fkStartHH" id="fkStartHH">
											<option value=" 00:00:00" id="op2">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
									<td>
										付款结束时间：
										<input type="text" name="payEndTime" readonly id="payEndTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${payEndTime}" />
										<select name="fkEndHH" id="fkEndHH">
											<option value=" 23:59:59" id="op3">
												23:59:59
											</option>
											<option value=" 00:00:00">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
								</tr>

								<tr>
									<td>
										<img src="<%=contextPath%>/back/images/add_a.gif" />
										<a href="javascript:search(document.f1)">查询</a>&nbsp;
										<img src="<%=contextPath%>/back/images/del_a.gif" />
										<a href="javascript:cancel()">清空</a>
									</td>
									<td><img src="<%=contextPath%>/back/images/down16_16.gif" /><a href="javascript:excelHDFK(document.f1)">导出货到付款excel</a></td>
								</tr>
							</table>
							</div>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>


					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info" onmouseover="changeto()"
								onmouseout="changeback()">
								<tr class="lists_infobg">
									<td>
										编号
									</td>
									<td>
										订单编号
									</td>
									<td>
										用户email
									</td>
									<td>
										激活码
									</td>
									<td>
										订单来源
									</td>
									<td>
										下单时间
									</td>
									<td>
										付款时间
									</td>
									<td>
										金额
									</td>
									<td>
										状态
									</td>
									<td>
										支付类型
									</td>
									<td>
										访问次数
									</td>
									<td>
										取消来源		
									</td>
									<td colspan="1">
										操作 
									</td>
								</tr>
								<% int i=0;%>
								<s:if test="page.pageResult.size()!=0">
									<s:iterator value="page.pageResult" id="contract" status="status">
										<tr>
											<td>
												<s:property value="(page.currentPage-1)*page.pageSize+#status.count" />
											</td>
											<td>
												<a
													href="<%=contextPath%>/finance/backCashRecord!getUserCashRecordList.action?cashRecord.ctId=<s:property value="#contract.id"/>&cashRecord.cusId=<s:property value="#contract.cusId"/>"><s:property
														value="#contract.contractId" /> </a>
											</td>
											<td>
													<a href="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email=<s:property value="#contract.customer.email"/>"><s:property	value="#contract.customer.email" /> </a>
													&nbsp;&nbsp;
													<s:if test="#contract.payType==2">
													｜&nbsp;&nbsp;<a href='<%=contextPath%>/cou/address!toContractCustomerAddress.action?address.id=<s:property value="#contract.cusIdAddress"/>'><font color="green">地址</font></a>
													｜&nbsp;&nbsp;<a href="javascript:openWin(<s:property value="#contract.id"/>)"><font color="green">详细</font></a>
													</s:if>
											</td>
											<td>
													<s:property value="#contract.contractCDkey"/>
											
											</td>
											<td>
												<s:property value="#contract.contractFrom" />
											</td>
											<td>
												<s:date name="#contract.createTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:date name="#contract.payTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<script>
												var lin=<s:property value="#contract.contractCutSumMoney" />+10;
												document.write(lin);
												</script>	
											</td>
											<td>
											<s:if test="#contract.payType==2">
											    <s:if test="#contract.status==0">未激活</s:if>
												<s:elseif test="#contract.status==1">已激活</s:elseif>
												<s:elseif test="#contract.status==4">已取消</s:elseif>
												<s:elseif test="#contract.status==3">已退费</s:elseif>
											</s:if>
											<s:elseif test="#contract.payType==1">
											    <s:if test="#contract.status==0">未付款</s:if>
												<s:elseif test="#contract.status==1">已付款</s:elseif>
												<s:elseif test="#contract.status==3">已退费</s:elseif>
											</s:elseif>
											<s:elseif test="#contract.payType==0">
												<s:if test="#contract.status==2">赠送</s:if>
												<s:elseif test="#contract.status==4">赠送/修复</s:elseif>
											</s:elseif>
											</td>
											<td>
												<s:if test="#contract.payType==1">
												支付宝/网银
												</s:if>
												<s:elseif test="#contract.payType==3">
												网银在线/网银
												</s:elseif>
												<s:elseif test="#contract.payType==2">
												货到付款
												</s:elseif>
												<s:elseif test="#contract.payType==0">
												赠送/修复
												</s:elseif>
											</td>
											<td>
												<s:property value="#contract.callSum" />
											</td>
											<td>
												<s:if test="#contract.payType==0">
													无
												</s:if>
												<s:elseif test="#contract.payType==2">
													<s:if test="#contract.status==3">
														<s:if test="#contract.cancelFrom==0">
															前台
														</s:if>
														<s:if test="#contract.cancelFrom==1">
															后台
														</s:if>
													</s:if>
													<s:else>
															无
													</s:else>
												</s:elseif>
												<s:else>
													<s:if test="#contract.status==4">
														<s:if test="#contract.cancelFrom==0">
															前台
														</s:if>
														<s:if test="#contract.cancelFrom==1">
															后台
														</s:if>
													</s:if>
													<s:else>
														无
													</s:else>
												</s:else>	
											</td>
											<td>
											<s:if test="#contract.payType==2">
											    <s:if test="#contract.status!=3">
													<s:set name="tempStatus" value="0"/>
									   	       			<s:iterator value="#contract.codList" id="cod">
															<s:if test="#cod.codStatus>#tempStatus">
																<s:set name="tempStatus" value="#cod.codStatus"/>
															</s:if>
														</s:iterator>
												    	<s:if test="#tempStatus==0">
															<a href="<%=contextPath%>/finance/backContract!getContractUserCOD.action?contractStep=1&contract.id=<s:property value="#contract.id"/>">审核</a>
														</s:if>
												    	<s:elseif test="#tempStatus==1">
															<a href="<%=contextPath%>/finance/backContract!getContractUserCOD.action?contractStep=2&contract.id=<s:property value="#contract.id"/>">发货</a>
														</s:elseif>
														<s:elseif test="#tempStatus==2">
															<a href="<%=contextPath%>/finance/backContract!getContractUserCOD.action?contractStep=3&contract.id=<s:property value="#contract.id"/>">送达</a>
														</s:elseif>
														<s:elseif test="#tempStatus==3">
															<a href="<%=contextPath%>/finance/backContract!getContractUserCOD.action?contractStep=4&contract.id=<s:property value="#contract.id"/>">结款</a>
														</s:elseif>
											        	<s:else>
														------
														</s:else>
												  </s:if>
												  <s:else>
												  ------
												  </s:else>
											    ｜
											    <s:if test="#contract.payType==2">
												    <s:if test="#contract.status==0">
												    <a href="<%=contextPath%>/finance/backContract!getUserContractCancel.action?contract.id=<s:property value="#contract.id"/>&#page=<s:property value="#page"/>" onclick="return confirm('确定要取消吗？')" >取消</a>
												    </s:if> 
												    <s:elseif test="#contract.status==1">
												    <a href="<%=contextPath%>/finance/backContract!getReFundHDFK.action?contract.id=<s:property value="#contract.id"/>">退费</a>
												    </s:elseif>
												    <s:elseif test="#contract.status==3">
												    <a href="<%=contextPath%>/finance/backContract!getUserContractRecoverment.action?contract.id=<s:property value="#contract.id"/>" onclick="return confirm('确定要恢复吗？')">恢复</a>
												    </s:elseif>
												    <s:else>----</s:else>
											    </s:if>
											    ｜<a href="#" onclick="onClick('god<s:property value="#contract.id"/>')" class="font_bai">展开</a>
											</s:if>
											
											</td>
										</tr>
										<tr id="god<s:property value="#contract.id"/>" style="display:none" >
											<td colspan="12">
												<div  class="wen_info_sr mtop-10 bor1blue font_hui">
				  									<div style="margin:0 auto; width:100%;">
				  									<s:if test="#contract.codList.size>0">
				  									<s:iterator value="#contract.codList" id="newCod">
				  									<div style="width:40%; float:left;">
				  										<table><tr>
				  										<td><s:property value="#newCod.codContent"/></td>
				  										<td>&nbsp;</td>
				  										<td>&nbsp;</td>
														<td>&nbsp;</td>
				  										</tr>
				  										<tr>
				  										<td></td>
				  										<td>状态：
				  										<s:if test="#newCod.codStatus==1">
				  										等待发货
				  										</s:if>
				  										<s:elseif test="#newCod.codStatus==2">
				  										等待送达
				  										</s:elseif>
				  										<s:elseif test="#newCod.codStatus==3">
				  										等待结款
				  										</s:elseif>
				  										<s:elseif test="#newCod.codStatus==4">
				  										等待激活
				  										</s:elseif>
				  										</td>
				  										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：<s:date name="#newCod.codTime" format="yyyy-MM-dd HH:mm:ss" /></td>
														<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人：<s:property value="user.loginName"/></td>
				  										</tr></table>
				  									 
				  									</div>
				  									</s:iterator>
				  									</s:if>
				  									<s:else>
				  									　　无状态
				  									</s:else>
				  									</div>			
												</div>
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td align="center" colspan="12">
											没有订单!
										</td>
									</tr>
								</s:else>
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
							<jsp:include page="/back/jsp/common/showPage.jsp" />
						</td>
						<td>
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
