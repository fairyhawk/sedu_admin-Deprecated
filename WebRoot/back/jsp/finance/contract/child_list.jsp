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
		
   function search(ObjectForm){
     ObjectForm.submit();
   }
   function cancel(){
	   document.getElementById("startTime").value="";
	   document.getElementById("endTime").value="";
	   document.getElementById("payStartTime").value="";
	   document.getElementById("payEndTime").value="";
	   document.getElementById("contractId").value="";
	   document.getElementById("childContractId").value="";
	   document.getElementById("status").value="";
	   document.getElementById("email").value="";
   }
   
   	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
		changefkStartHH('<s:property value="fkStartHH"/>');
		changefkEndHH('<s:property value="fkEndHH"/>')
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

	 
</script>
	</head>
	<body onload="changeData()">
		<div>
			<form name="f1" method="post"
				action="childContract!getChildContractList.action" id="form1">
				<s:hidden name="queryChildCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">订单列表</font>
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
										<input type="text" name="queryChildCondition.contractId"
											id="contractId" value="${queryChildCondition.contractId}" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										子 订 单 号：&nbsp;
										 <input type="text" name="queryChildCondition.childContractId" value="${queryChildCondition.childContractId}" id="childContractId"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										邮 箱 地 址：&nbsp;
										<input type="text" name="queryChildCondition.email" id="email" value="${queryChildCondition.email}" />

									</td>
									<td>
										订 单 状 态：&nbsp;
										<select  name="queryChildCondition.status" id="status" style="width: 155px">
											<option value="-1">--请选择--</option>
											<option value="0">未支付</option>
											<option value="1">已支付</option>
										</select>
										&nbsp;&nbsp; 
									</td>
								</tr>

								<tr>
									<td>
										下单开始时间：
										<input type="text" name="queryChildCondition.startCreateTime" readonly id="startTime"
											 onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
											value="<s:date name="queryChildCondition.startCreateTime"  format="yyyy-MM-dd HH:mm:ss"/>" />
											
									</td>
									<td>
										下单结束时间：
										<input type="text" name="queryChildCondition.endCreateTime" readonly id="endTime"
											 onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
											 value="<s:date name="queryChildCondition.endCreateTime"  format="yyyy-MM-dd HH:mm:ss"/>" />
									</td>
								</tr>
								<tr>
									<td>
										付款开始时间：
										<input type="text" name="queryChildCondition.startPayTime" readonly
											id="payStartTime" 
											onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" 
											 value="<s:date name="queryChildCondition.startPayTime"  format="yyyy-MM-dd HH:mm:ss"/>" />
									</td>
									<td>
										付款结束时间：
										<input type="text" name="queryChildCondition.endPayTime" readonly id="payEndTime"
											onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
											value="<s:date name="queryChildCondition.endPayTime"  format="yyyy-MM-dd HH:mm:ss"/>"
											/>
									</td>
								</tr>
								<tr>
									<td>
									</td>
									<td class="lists_tleft">
										<img src="<%=contextPath%>/back/images/add_a.gif" />
										<a href="javascript:search(document.f1)">查询</a>&nbsp;
										<img src="<%=contextPath%>/back/images/del_a.gif" />
										<a href="javascript:cancel()">清空</a>
									</td>
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
									<td>编号</td>
									<td>订单编号</td>
									<td>用户email</td>
									<td>子订单号</td>
									<td>下单时间</td>
									<td>支付时间</td>
									<td>支付金额</td>
									<td>订单状态</td>
								</tr>
								<s:if test="page.pageResult.size()!=0">
									<s:iterator value="page.pageResult" id="childcontract"
										status="status">
										<tr>
											<td><s:property value="(page.currentPage-1)*page.pageSize+#status.count" /> </td>
											<td>
												<s:property value="#childcontract.contractId" /> </td>
											<td>
												<a href="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email=<s:property value="#childcontract.email"/>"><s:property
														value="#childcontract.email" /> </a> &nbsp;&nbsp;
											</td>
											<td>
												<s:property value="#childcontract.childContractId" />
											</td>
											<td>
												<s:date name="#childcontract.createTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:date name="#childcontract.payTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:property value="#childcontract.money" />
											</td>
											<td>
												<s:if test="#childcontract.status==0">未支付</s:if>
												<s:elseif test="#childcontract.status==1">已支付</s:elseif>
											</td>
										</tr>
										
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td align="center" colspan="17">
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
						<td class="td_wid_1">
							<img src="<%=contextPath%>/back/images/tab_18.gif" />
						</td>
						<td class="lists_bottom"><jsp:include
								page="/back/jsp/common/showPage.jsp" />
						</td>
						<td class="td_wid_r">
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>