<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>订单列表</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
			<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/easydialog.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/easydialog.min.js"></script>
		<script>
		
			function abolishInvoice(invoiceId){
				if(window.confirm("请注意：你作废该发票后将不能恢复，是否继续？"))
				{
						$.ajax({
							url :  "<%=contextPath%>/finance/invoice!addOrUpdateInvoice.action",
							data : {
								"invoice.invoiceId" :invoiceId,
								"abolishInvoice" : "yes"
							},
							type : "post",
							cache : false,
							async : false,
							success : function(result) {
								backQueryList();
							},
							error : function(error) {
								alert(error);
							}
						});
			}
			}
			
			function sendInvoice(invoiceId){
				var htmlStr="<div><table>"
	        		+"<tr >"
	            	+"<td class=\"bfont\">输入运单号码：</td\">"
	            	+"<td><input class=\"txtbox\" type=\"text\" name=\"invoice.waybillCode\" id=\"waybillCode2\" />"
	            	+"<input  type=\"hidden\" name=\"invoice.invoiceId\" id=\"invoiceId\" value=\""+invoiceId+"\" />"
	            	+"</td>"
	        		+"</tr>"
	        		+"<tr>"
	                +"<td class=\"bfont\">输入发票号码：</td>"
	                +"<td><input class=\"txtbox\" type=\"text\" name=\"invoice.invoiceCode\" id=\"invoiceCode2\"/></td>"
	        		+"</tr>"
	    			+"</table></div>";
				easyDialog.open({
					  container : {
					    header : '寄出操作',
					    content : htmlStr,
					    yesFn : doYes,
					    noFn : true
					  }
					});
			};
			var doYes = function(){
				var msg=validateSendInfo();
				if(msg!=""){
					alert(msg);
					return false;
				}
				$.ajax({
					url :  "<%=contextPath%>/finance/invoice!addOrUpdateInvoice.action",
					data : {
						"invoice.waybillCode" : getWaybillCode(),
						"invoice.invoiceCode" : getInvoiceCode(),
						"invoice.invoiceId" :getInvoiceId(),
						"updateFlag" : "updateInvoice"
					},
					cache : false,
					async : false,
					success : function(result) {
						backQueryList();
					},
					error : function(error) {
						alert(error);
					}
				});
				  return true;
				};
			function validateSendInfo(){
				var message="";
				message+=validateRepeat();
				if(getWaybillCode()==""){
					message+="运单号码不能为空！\n";
				}
				if(getInvoiceCode()==""){
					message+="发票号码不能为空！\n";
				}
				
				return message;
			}
			function getWaybillCode(){
				return $("#waybillCode2").val();
			}
			function getInvoiceCode(){
				return $("#invoiceCode2").val();
			}
			function getInvoiceId(){
				return $("#invoiceId").val();
			}
			function backQueryList(){
				document.invoiceListForm.action="<%=contextPath%>/finance/invoice!getInvoiceInfoList.action?queryInvoiceCondition.currentPage=1";
				document.invoiceListForm.submit();
			}
			function search(obj){
				obj.action="<%=contextPath%>/finance/invoice!getInvoiceInfoList.action";
				obj.submit();
			}
			function clear(){
				$("#orderCode").val("");
				$("#waybillCode").val("");
				$("#phoneNumber").val("");
				$("#invoiceCode").val("");
				$("#payType").val("-1");
				$("#invoiceStatus").val("");
				$("#createBeginDate").val("");
				$("#createEndDate").val("");
				
			}
			
			function exportExcel()
			{
		
				document.getElementById("searchForm").action="<%=contextPath%>/finance/invoice!exportCSV.action";
				document.getElementById("searchForm").submit();
				document.getElementById("searchForm").action="<%=contextPath%>/finance/invoice!getInvoiceInfoList.action?queryInvoiceCondition.currentPage=1";
			 
			}
			function validateRepeat(){
				var message="";
				$.ajax({
					url :  "<%=contextPath%>/finance/invoice!validateRepeat.action",
					data : {
						"invoice.waybillCode" : getWaybillCode(),
						"invoice.invoiceCode" : getInvoiceCode()
					},
					type : "post",
					dataType : "json",
					cache : false,
					async : false,
					success : function(result) {
						 if(result.entity[0].waybillCodeCount>0){
							message+="运单号在系统中已经对应了一张发票，请确认以前是否已经录入过该运单号！\n"
						}
						if(result.entity[0].invoiceCodeCount>0){
							message+="发票号已经在系统中存在，请确认以前是否已经录入过该发票号！\n"
						} 
					},
					error : function(error) {
						alert(error);
					}
				});
				return message;
			}
			

		</script>
		<style type="text/css">
			.txtbox{border:1px solid #E79F50;height:20px;line-height:22px;width:120px;}
			.bfont{font:700 12px "宋体"};
		</style>
	</head>
	<body>
		<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">发票列表</font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
						<form action="?"  name="searchForm" id="searchForm" method="post" >
							<div class="msg-cw">	
								<table width="70%" border="0" cellspacing="1" cellpadding="0"
									class="lists" align="center">
									<tr>
										<td>
											订 单 编 号：
											
										</td>
										<td valign="middle" align="left" style="text-align:left">
											<input type="text" name="queryInvoiceCondition.orderCode"
												id="orderCode" value="${queryInvoiceCondition.orderCode}" />
										</td>
										<td>
											物流单号：
											
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input type="text" name="queryInvoiceCondition.waybillCode"
												id="waybillCode" value="${queryInvoiceCondition.waybillCode}" />
										</td>
										<td>
											手机号码：
											
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input type="text" name="queryInvoiceCondition.phoneNumber"
												id="phoneNumber" value="${queryInvoiceCondition.phoneNumber}" />
										</td>
										<td>
											发票号码：
											
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input type="text" name="queryInvoiceCondition.invoiceCode"
												id="invoiceCode" value="${queryInvoiceCondition.invoiceCode}" />
										</td>
									</tr>
									<tr>
										<td>
											支付方式：
										</td valign="middle" align="left" colspan="2" style="text-align:left">
										<td>
											<select name="queryInvoiceCondition.payType" id="payType" style="width: 155px">
												<option value="-1" id="op1">
													---请选择---
												</option>
												
												<option value="1" <s:if test="queryInvoiceCondition.payType==1">selected</s:if>>
													支付宝/网银
												</option>
												<option value="3" <s:if test="queryInvoiceCondition.payType==3">selected</s:if>>
													网银在线/网银
												</option>
												<option value="4" <s:if test="queryInvoiceCondition.payType==4">selected</s:if>>
													快钱
												</option>
												<option value="2" <s:if test="queryInvoiceCondition.payType==2">selected</s:if>>
													货到付款
												</option>
												<option value="0" <s:if test="queryInvoiceCondition.payType==0">selected</s:if>>
													内部开通
												</option>
												<option value="5" <s:if test="queryInvoiceCondition.payType==5">selected</s:if>>
													代理商
												</option>
												<option value="6" <s:if test="queryInvoiceCondition.payType==6">selected</s:if>>
													真友
												</option>
												<option value="7" <s:if test="queryInvoiceCondition.payType==7">selected</s:if>>
													银行汇款
												</option>
												<option value="8" <s:if test="queryInvoiceCondition.payType==8">selected</s:if>>
													银联在线
												</option>
											</select>
										</td>
										<td>
											发票状态：
										</td valign="middle" align="left" colspan="2" style="text-align:left">
										<td>
											<s:select list="invoiceStatusList" listKey="dicContent" listValue="dicContent" name="queryInvoiceCondition.invoiceStatus" id="invoiceStatus"
											headerKey="" headerValue="---请选择---">
											</s:select>
										</td>
										<td >时间选择：</td>
										<td valign="middle" align="left" colspan="5" style="text-align:left">
											<input type="text" readonly="readonly" name="queryInvoiceCondition.createBeginDate" id="createBeginDate"
	                                    		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="queryInvoiceCondition.createBeginDate"  format="yyyy-MM-dd HH:mm:ss"/>" />
	                                	 	&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
	                                 		<input type="text" readonly="readonly" name="queryInvoiceCondition.createEndDate" id="createEndDate"
	                                     		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="queryInvoiceCondition.createEndDate"  format="yyyy-MM-dd HH:mm:ss"/>" />
									 	</td>
									 	
									</tr>
									<tr>
										<td>
	
										</td>
										<td class="lists_tleft">
											<img src="<%=contextPath%>/back/images/add_a.gif" />
											<a href="javascript:search(document.searchForm)">查询</a>&nbsp;
											<img src="<%=contextPath%>/back/images/del_a.gif" />
											<a href="javascript:clear()">清空</a>
											<s:hidden name="exportType" id="exportType"></s:hidden>
											<img src="<%=contextPath%>/back/images/add_a.gif" />
											<a href="<%=contextPath%>/finance/invoice!getInvoiceInfo.action">新建</a>
											<img src="<%=contextPath%>/back/images/down16_16.gif" />
											<a href="javascript:exportExcel()">导出excel</a>
										</td>
									</tr>
								</table>
							</div>
							</form>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<form action="?"  name="invoiceListForm" id="invoiceListForm" method="post" >
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="lists_info" >
	
									<tr class="lists_infobg">
										<td>
											编号
										</td>
										<td>
											订单编号
										</td>
										<td>
											支付方式
										</td>
										<td>
											发票抬头
										</td>
										<td>
											发票内容
										</td>
										<td>
											发票状态
										</td>
										<td>
											发票金额
										</td>
										<td>
											发票信息
										</td>
										<td>
											手机号码
										</td>
										<td>
											操作人
										</td>
										<td>
											物流单号
										</td>
										<td>
											发票号
										</td>
										<td colspan="1">
											操作
										</td>
									</tr>
									<s:if test="page.pageResult.size()!=0">
										<s:iterator value="page.pageResult" id="invoice"
											status="status">
											<tr>
												<td>
													<s:property
														value="(page.currentPage-1)*page.pageSize+#status.count" /> 
												</td>
												<td>
													<s:property value="#invoice.orderCode"/>
												</td>
												<td>
													<s:if test="#invoice.payType==1">
													支付宝/网银
													</s:if>
													<s:elseif test="#invoice.payType==3">
													网银在线/网银
													</s:elseif>
													<s:elseif test="#invoice.payType==2">
													货到付款
													</s:elseif>
													<s:elseif test="#invoice.payType==0">
													内部开通
													</s:elseif>
													<s:elseif test="#invoice.payType==4">
													快钱
													</s:elseif>
													<s:elseif test="#invoice.payType==5">
													代理商
													</s:elseif>
													<s:elseif test="#invoice.payType==6">
													真友
													</s:elseif>
													<s:elseif test="#invoice.payType==7">
													银行汇款
													</s:elseif>
													<s:elseif test="#invoice.payType==8">
													银联在线/银联
													</s:elseif>
												</td>
												<td>
													<s:if test="#invoice.invoiceTitle==0">
														个人
													</s:if>
													<s:if test="#invoice.invoiceTitle==1">
														单位
													</s:if>
												</td>
												<td>
													<s:property value="#invoice.invoiceContent"/>
												</td>
												<td>
													<s:property value="#invoice.invoiceStatus"/>
												</td>
												<td>
													<fmt:formatNumber value="${invoice.invoiceSum}" pattern="##0.00"/>
												</td>
												<td>
													<s:property value="#invoice.invoiceAddress"/>
												</td>
												<td>
													<s:property value="#invoice.mobile"/>
												</td>
												<td>
													<s:property value="#invoice.creator"/>
												</td>
												<td>
													<s:property value="#invoice.waybillCode"/>
												</td>
												<td>
													<s:property value="#invoice.invoiceCode"/>
												</td>
												<td>
													<s:if test="#invoice.invoiceStatus=='已出票'">
														<a href="javaScript:void(0)" onclick="sendInvoice('${invoice.invoiceId}')">寄出</a>|
													</s:if>
													<s:if test="#invoice.invoiceStatus!='废弃'">
														<a href="javaScript:void(0)" onclick="abolishInvoice('${invoice.invoiceId}')">作废</a>|
													</s:if>
													
													<a href="<%=contextPath%>/finance/invoice!getInvoiceInfo.action" >新开</a>
												</td>
											</tr>
										</s:iterator>
									</s:if>
								</table>
							</form>
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
			</div>	
	</body>
</html>