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
			$().ready(function() {
				if($("#createBeginDate").val()==''){
					$("#createBeginDate").val(getDate()+" 00:00:00");
				}
				if($("#createEndDate").val()==''){
					$("#createEndDate").val(getDate()+" 23:59:59");
				}
				
			});
			 function getDate(){
				 var dateStr="";
				 var myDate = new Date();
				 dateStr+=myDate.getFullYear()+"-";
				 dateStr+=(myDate.getMonth()+1)>9?(myDate.getMonth()+1)+"-":"0"+(myDate.getMonth()+1)+"-";
				 dateStr+=myDate.getDate()>9?myDate.getDate():"0"+myDate.getDate();
				 return dateStr;
		 	}
			function search(obj){
				var msg=validateInfo();
				if(msg!=""){
					alert(msg);
					return;
				} 
				obj.action="<%=contextPath%>/purse/purseRecord!getIPurseRecordList.action";
				obj.submit();
			}
			function validateInfo(){
				var message="";
				if((new Date($("#createBeginDate").val().replace(/-/g,"/")))>(new Date($("#createEndDate").val().replace(/-/g,"/"))))
				{
					message +="创建开始时间不能大于创建结束时间！\n";
				}
				return message;
			}
			function exportExcel(){
				document.getElementById("searchForm").action="<%=contextPath%>/purse/purseRecord!exportCSV.action";
				document.getElementById("searchForm").submit();
				document.getElementById("searchForm").action="<%=contextPath%>/purse/purseRecord!getIPurseRecordList.action?queryRecordCondition.currentPage=1";
			}
			function clear(){
				$("#recordId").val("");
				$("#userAccount").val("");
				$("#recordType").val("");
				$("#remark").val("");
				$("#payType").val("");
				$("#creator").val("");
				$("#szStatus").val("");
				$("#createBeginDate").val("");
				$("#createEndDate").val("");
			}
		</script>
		
	</head>
	<body>
		<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">交易记录列表</font>
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
												交易编号：
											</td>
											<td valign="middle" align="left" style="text-align:left">
												<input type="text" name="queryRecordCondition.recordId"
													id="recordId" value="${queryRecordCondition.recordId}" />
											</td>
											<td>
												用户账号：
											</td>
											<td valign="middle" align="left"  style="text-align:left">
												<input type="text" name="queryRecordCondition.userAccount"
													id="userAccount" value="${queryRecordCondition.userAccount}" />
											</td>
										</tr>
										<tr>
											<td>
												交易类型：
											</td>
												<td>
												<s:select list="recordTypeMap"   listKey="key" listValue="value"  name="queryRecordCondition.recordType" id="recordType"
												headerKey="" headerValue="---请选择---">
												</s:select>
											</td>
											<td>
												交易备注：
											</td>
											<td valign="middle" align="left"  style="text-align:left">
												<input type="text" name="queryRecordCondition.remark"
													id="remark" value="${queryRecordCondition.remark}" />
											</td>
										</tr>
										<tr>
											<td>
												交易方式：
											</td>
												<td>
												<s:select list="payTypeMap"   listKey="key" listValue="value"  name="queryRecordCondition.payType" id="payType"
												headerKey="" headerValue="---请选择---">
												</s:select>
											</td>
											<td>
												操作人：
											</td>
											<td valign="middle" align="left"  style="text-align:left">
												<input type="text" name="queryRecordCondition.creator"
													id="creator" value="${queryRecordCondition.creator}" />
											</td>
										</tr>
										<tr>
											<td>
												收支方向：
											</td>
											<td>
												<s:select list="szStatusMap"   listKey="key" listValue="value"  name="queryRecordCondition.szStatus" id="szStatus"
												headerKey="" headerValue="---请选择---">
												</s:select>
											</td>
											<td >创建时间：</td>
											<td valign="middle" align="left" colspan="5" style="text-align:left">
												<input type="text" readonly="readonly" name="queryRecordCondition.createBeginDate" id="createBeginDate"
		                                    		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="queryRecordCondition.createBeginDate"  format="yyyy-MM-dd HH:mm:ss"/>" />
		                                	 	&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
		                                 		<input type="text" readonly="readonly" name="queryRecordCondition.createEndDate" id="createEndDate"
		                                     		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="queryRecordCondition.createEndDate"  format="yyyy-MM-dd HH:mm:ss"/>" />
										 	</td>
										</tr>
										<tr>
											
											<td class="lists_tleft">
												<img src="<%=contextPath%>/back/images/add_a.gif" />
												<a href="javascript:search(document.searchForm)">查询</a>&nbsp;
												<img src="<%=contextPath%>/back/images/del_a.gif" />
												<a href="javascript:clear()">清空</a>
												<s:hidden name="exportType" id="exportType"></s:hidden>
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
							<form action="?"  name="purseRecordListForm" id="purseRecordListForm" method="post" >
								<table border="0" width="50%" cellpadding="0" cellspacing="0">
									<tr>
										<td class="lists_tright" width="10%">充值金额：</td>
										<td >
											<s:property value="paidMoneySum"/>
										</td>
										<td  class="lists_tright" width="20%">退费金额：</td>
										<td >
											<s:property value="refundSum"/>
										</td>
										<td  class="lists_tright" width="20%">退课金额：</td>
										<td >
											<s:property value="dropCourseSum"/>
										</td>
										<td class="lists_tright" width="30%">支付订单金额：</td>
										<td>
											<s:property value="paidOrderSum"/>
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="lists_info" >
	
									<tr class="lists_infobg">
										<td>
											交易编号
										</td>
										<td>
											用户账号
										</td>
										<td>
											创建时间
										</td>
										<td>
											交易方式
										</td>
										<td>
											金额（元）
										</td>
										<td>
											交易方式
										</td>
										<td>
											收/支
										</td>
										<td>
											状态
										</td>
										<td>
											操作人
										</td>
										<td>
											交易备注
										</td>
										<td colspan="1">
											操作
										</td>
									</tr>
									<s:if test="page.pageResult.size()!=0">
										<s:iterator value="page.pageResult" id="purseRecord"
											status="status">
											<tr>
												<td>
													<s:property value="#purseRecord.recordId"/>
												</td>
												<td>
													<s:property value="#purseRecord.userAccount"/>
												</td>
												<td>
													<s:date name="#purseRecord.createTime" format="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
													<s:property value="#purseRecord.recordTypeName"/>
													<s:if test="#purseRecord.orderCode!=null">
														<s:if test="#purseRecord.orderCode!=''">
															<br/>
															订单编号：<a href="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action?queryCashRecordCondition.contractId=<s:property value='record.orderCode'/>"><s:property value="#purseRecord.orderCode"/></a>
														</s:if>
													</s:if>
												</td>
												<td>
													<s:if test="#purseRecord.money>0">+</s:if>
													<s:property value="#purseRecord.money"/>
												</td>
												<td>
													<s:property value="#purseRecord.payTypeName"/>
												</td>
												
												<td>
													<s:property value="#purseRecord.szStatusName"/>
												</td>
												<td>
													<s:property value="#purseRecord.payStatusName"/>
												</td>
												<td>
													<s:property value="#purseRecord.creator"/>
												</td>
												<td>
													<s:property value="#purseRecord.remark"/>
												</td>
												<td>
													<a href="<%=contextPath%>/purse/purseRecord!getPurseRecordInfo.action?record.id=<s:property value='#purseRecord.id'/>" >查看</a>
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