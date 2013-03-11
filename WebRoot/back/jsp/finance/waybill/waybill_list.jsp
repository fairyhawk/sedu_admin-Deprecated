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
			function abolishWaybill(waybillId){
				if(window.confirm("请注意：你作废该运单后将不能恢复，是否继续？"))
				{
						$.ajax({
							url :  "<%=contextPath%>/finance/waybill!addOrUpdateWaybill.action",
							data : {
								"waybill.waybillId" :waybillId,
								"abolishWaybill" : "yes"
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
			function finishWaybill(waybillId){
				$.ajax({
					url :  "<%=contextPath%>/finance/waybill!addOrUpdateWaybill.action",
					data : {
						"waybill.waybillId" :waybillId,
						"finishWaybill" : "yes"
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
			function backQueryList(){
				document.waybillListForm.action="<%=contextPath%>/finance/waybill!getWaybillInfoList.action?queryWaybillCondition.currentPage=1";
				document.waybillListForm.submit();
			}
			function search(obj){
				var msg=validateInfo();
				if(msg!=""){
					alert(msg);
					return;
				}
				obj.action="<%=contextPath%>/finance/waybill!getWaybillInfoList.action";
				obj.submit();
			}
			function clear(){
				$("#consigneePhone").val("");
				$("#waybillCode").val("");
				$("#expressCompany").val("");
				$("#consigneeEmail").val("");
				$("#createBeginDate").val("");
				$("#createEndDate").val("");
				
			}
			function validateInfo(){
				var message="";
				if((new Date($("#createBeginDate").val().replace(/-/g,"/")))>(new Date($("#createEndDate").val().replace(/-/g,"/"))))
				{
					message +="开始时间不能大于结束时间！\n";
				}
				return message;
			}
			
			function exportExcel()
			{
				document.getElementById("searchForm").action="<%=contextPath%>/finance/waybill!exportCSV.action";
				document.getElementById("searchForm").submit();
				document.getElementById("searchForm").action="<%=contextPath%>/finance/waybill!getWaybillInfoList.action?queryWaybillCondition.currentPage=1";
			 
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
							<font class="lists_fleft">运单列表</font>
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
											手机号码：
											
										</td>
										<td valign="middle" align="left" style="text-align:left">
											<input type="text" name="queryWaybillCondition.consigneePhone"
												id="consigneePhone" value="${queryWaybillCondition.consigneePhone}" />
										</td>
										<td>
											物流单号：
											
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input type="text" name="queryWaybillCondition.waybillCode"
												id="waybillCode" value="${queryWaybillCondition.waybillCode}" />
										</td>
										<td>
											快递公司：
											
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input type="text" name="queryWaybillCondition.expressCompany"
												id="expressCompany" value="${queryWaybillCondition.expressCompany}" />
										</td>
										
									</tr>
									<tr>
										<td>
											电子邮箱：
										</td valign="middle" align="left" colspan="2" style="text-align:left">
										<td valign="middle" align="left"  style="text-align:left">
											<input type="text" name="queryWaybillCondition.consigneeEmail"
												id="consigneeEmail" value="${queryWaybillCondition.consigneeEmail}" />
										</td>
										<td >时间选择：</td>
										<td valign="middle" align="left" colspan="5" style="text-align:left">
											<input type="text" readonly="readonly" name="queryWaybillCondition.createBeginDate" id="createBeginDate"
	                                    		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="queryWaybillCondition.createBeginDate"  format="yyyy-MM-dd HH:mm:ss"/>" />
	                                	 	&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
	                                 		<input type="text" readonly="readonly" name="queryWaybillCondition.createEndDate" id="createEndDate"
	                                     		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="queryWaybillCondition.createEndDate"  format="yyyy-MM-dd HH:mm:ss"/>" />
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
											<a href="<%=contextPath%>/finance/waybill!getWaybillInfo.action">新建</a>
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
							<form action="?"  name="waybillListForm" id="waybillListForm" method="post" >
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="lists_info" >
	
									<tr class="lists_infobg">
										<td>
											编号
										</td>
										<td>
											物流单号
										</td>
										<td>
											订单号
										</td>
										<td>
											收货人姓名
										</td>
										<td>
											手机号码
										</td>
										<td>
											物品类别
										</td>
										<td>
											物流公司
										</td>
										<td>
											物流状态
										</td>
										<td>
											物流时间
										</td>
										<td colspan="1">
											操作
										</td>
									</tr>
									<s:if test="page.pageResult.size()!=0">
										<s:iterator value="page.pageResult" id="waybill"
											status="status">
											<tr>
												<td>
													<s:property
														value="(page.currentPage-1)*page.pageSize+#status.count" /> 
												</td>
												<td>
													<s:property value="#waybill.waybillCode"/>
												</td>
												<td>
													<s:property value="#waybill.orderCode"/>
												</td>
												<td>
													<s:property value="#waybill.consigneeName"/>
												</td>
												<td>
													<s:property value="#waybill.consigneePhone"/>
												</td>
												<td>
													<s:property value="#waybill.goodsCategory"/>
												</td>
												<td>
													<s:property value="#waybill.expressCompany"/>
												</td>
												
												<td>
													<s:property value="#waybill.waybillStatus"/>
												</td>
												<td>
													<s:date name="#waybill.createDate" format="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
													<s:if test="#waybill.waybillStatus=='未完成'">
														<a href="javaScript:void(0)" onclick="finishWaybill('${waybill.waybillId}')">完成</a>|
														<a href="javaScript:void(0)" onclick="abolishWaybill('${waybill.waybillId}')">作废</a>|
													</s:if>
													<s:if test="#waybill.waybillStatus=='已完成'">
														<a href="javaScript:void(0)" onclick="abolishWaybill('${waybill.waybillId}')">作废</a>|
													</s:if>
													<a href="<%=contextPath%>/finance/waybill!getWaybillInfo.action" >新开</a>
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