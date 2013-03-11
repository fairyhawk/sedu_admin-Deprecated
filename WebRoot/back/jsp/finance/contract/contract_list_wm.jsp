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
		<script language="JavaScript">	

	function checkOutExcel()
	{
		var startTime=document.getElementById("startTime").value;
     	var endTime=document.getElementById("endTime").value;
//		document.getElementById("exportType").value="excel";
		 if(startTime!=null&&endTime!=null)
     {
	     if(startTime>endTime)
	     {
	        alert("结束时间要大于开始时间!");
	        return false;
	     }else{
	     	document.f1.action="<%=contextPath%>/finance/backContract!exportExcelFile.action";
			document.f1.submit();
	   	}
     }
	}
   function search(ObjectForm){
     var startTime=document.getElementById("startTime").value;
     var endTime=document.getElementById("endTime").value;
    
     if(startTime!=null&&endTime!=null)
     {
	     if(startTime>endTime)
	     {
	        alert("结束时间要大于开始时间!");
	     }else{
	     	
	   		ObjectForm.action="<%=contextPath%>/finance/wml.action";
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
   document.getElementById("payType").value="";
   document.getElementById("status").value="";
   document.getElementById("mail").value="";
   $("#op1").attr("selected","selected");
   $("#op0").attr("selected","selected");
   $("#op2").attr("selected","selected");
   $("#op3").attr("selected","selected");
   $("#op4").attr("selected","selected");
   $("#spId").val(-1);;


   }
   
   	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
		changefkStartHH('<s:property value="fkStartHH"/>');
		changefkEndHH('<s:property value="fkEndHH"/>')
		$("#payType").val(${payType});
		onchangePayType1(${payType});
		$('#spId').val(${status});
		$('#contractFromUrl').val(${contract.contractFromUrl});
		$('#webFrom').val('${contract.webFrom}');
		$('#webAgent').val('${contract.webAgent}');
		$('#status').val('${qwm.newStatus}');
		$('#spId').val(${qwm.newStatus});
		onchangePayType();
	}
	function onchangePayType(){
			var str="";
			str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
			$("#spId").html(str); 
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
				 	  "<option value='3'>已取消</option>"+
				 	  "<option value='4'>已退费</option>";
			}else if(payType==1){
				  str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
			}else if(payType==0){
				str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='2'>内部/代理商开通</option>"+
				 	  "<option value='4'>内部/代理商开通</option>";
			}else if(payType==3){
				 str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
			}else if(payType==4){
				 str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
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
	function onSelectSubject(subId)
	{
		document.getElementById("subjectId").value=subId;
	}
	
	
	function initCourse(id, index){
		var subjectId = 1;
		if(id != null && id != 0 && !isNaN(id)) {
			subjectId = id;
		}
		$.ajax({
			url :  "<%=contextPath%>/cou/course!getCourseListBySubjectId.action",
			data : {
				"queryCourseCondition.subjectId" : subjectId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result == null || result.entity == null) {
				
					return;
				}
				
				var courses = result.entity;				
				var html = '';
				for(var i=0; i<courses.length; i++) {
				
					html += "<option value='" + courses[i].courseId + "'>" + courses[i].courseName + "</option>";
				}
				if(index == 0) {
					$(html).appendTo("#subject");
				} else if(index == 1) {
					
					$("#course").html("");					
					$("<option value='0'>---请选择---</option>"+ html).appendTo("#course");
				} 
			},
			error : function(error) {
				alert(error);
			}
		});
	}
	
　function openWin(ctId) { 
	window.open ("<%=contextPath%>/finance/backContract!getContractView.action?contract.id="+ctId, "newwindow", "height=600, width=800, toolbar=no, menubar=yes, scrollbars=no, resizable=no, location=no, status=no");
　
　　}
	
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
						<form name="f1" method="post" action="bopayendTimeoks!getBooksList.action">
								<s:hidden name="qwm.currentPage" value="1" />
							<table width="70%" border="0" cellspacing="1" cellpadding="0"
								class="lists" align="center">
								<tr>
									<td>
										支 付 类 型：&nbsp;
										<select name="payType" id="payType" style="width: 155px"
											onchange="onchangePayType1(this.value)">
											<option value="-1" id="op1">
												---请选择---
											</option>
											<option value="1">
												支付宝/网银
											</option>
											<option value="3">
												网银在线/网银
											</option>
											<option value="4">
												快钱
											</option>
											<option value="2">
												货到付款
											</option>
										</select>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										订 单 状 态：&nbsp;
										 
										<s:select name="spId" id="spId" list="#{}" listKey="cId"
											listValue="cName" headerValue="---请选择---" headerKey="-1"
											onchange="onSelectStatus(this.value)" style="width: 155px">
										</s:select>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="hidden" name="status" id="status">
									</td>
								</tr>

								<tr>
									<td>
										下单开始时间：
										<input type="text" name="startTime" readonly id="startTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${startTime}" />
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
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${endTime}" />
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
									推 广 网 盟 ：
										<input type="text" name="contract.webFrom"
											id="webFrom" value="${contract.webFrom}" />
									</td>
									<td>
									站 长 &nbsp;&nbsp;ID &nbsp;：
										<input type="text" name="contract.webAgent"
											id="webAgent" value="${contract.webAgent}" />
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="lists_tleft">
										<img src="<%=contextPath%>/back/images/add_a.gif" />
										<a href="javascript:search(document.f1)">查询</a>
										<s:hidden name="exportType" id="exportType" ></s:hidden>
									</td>
								</tr>
							</table>
							</form>
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
										推广网盟
									</td>
									<td>
										站长ID
									</td>
									 
									<td>
										下单时间
									</td>
									<td>
										付款时间
									</td>
									<td>
										金额(<b><s:property value="contractPriceSum"/></b>)
									</td>
									<td>
										状态
									</td>
									<td>
										支付类型
									</td>
									  
								</tr>
								<s:if test="page.pageResult.size()!=0">
									<s:iterator value="page.pageResult" id="contract" status="status">
										<tr>
											<td>
												<s:property value="(page.currentPage-1)*page.pageSize+#status.count" />
											</td>
											<td>
												<!-- 
												<a href="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action?cashRecord.ctId=<s:property value="#contract.id"/>&cashRecord.cusId=<s:property value="#contract.cusId"/>">
												 -->
												<a target="_blank" href="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action?queryCashRecordCondition.contractId=<s:property value="#contract.contractId"/>" >
													<s:property value="#contract.contractId" /> 
												 </a>
											</td>
											
											<td>
												<!--
													<a href="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email=<s:property value="#contract.customer.email"/>"><s:property	value="#contract.customer.email" /> </a>
													  -->
													<s:property value="#contract.customer.email"/>
											</td>
											
											<td>
												<s:property value="#contract.webFrom" />
											</td>
											<td>
												<s:property value="#contract.webAgent" />
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
												<s:property value="#contract.contractPriceWithFreight" />
											</td>
											<td>
												<s:if test="#contract.payType==2">
												 	<s:if test="#contract.status==0">未激活</s:if>
												 	<s:elseif test="#contract.status==1">已激活</s:elseif>
												 	<s:elseif test="#contract.status==2">赠送</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												 </s:if>
												 <s:else>
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==2">赠送</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												 </s:else>
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
												内部/代理商开通
												</s:elseif>
												<s:elseif test="#contract.payType==4">
												快钱
												</s:elseif>
												<s:elseif test="#contract.payType==6">
												真友
												</s:elseif>
												<s:elseif test="#contract.payType==7">
												银行汇款
												</s:elseif>
												<s:elseif test="#contract.payType==8">
												银联
												</s:elseif>
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td align="center" colspan="10">
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
						<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" />
						</td>
						<td>
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>
		</div>
	</body>
</html>
