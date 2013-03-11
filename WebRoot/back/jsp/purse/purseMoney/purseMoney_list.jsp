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
			
			function search(obj){
				var msg=validateInfo();
				if(msg!=""){
					alert(msg);
					return;
				} 
				obj.action="<%=contextPath%>/purse/purseMoney!getPurseMoneyList.action";
				obj.submit();
			}
			function validateInfo(){
				var message="";
				if((new Date($("#createBeginTime").val().replace(/-/g,"/")))>(new Date($("#createEndTime").val().replace(/-/g,"/"))))
				{
					message +="创建开始时间不能大于创建结束时间！\n";
				}
				if((new Date($("#updateBeginTime").val().replace(/-/g,"/")))>(new Date($("#updateEndTime").val().replace(/-/g,"/"))))
				{
					message +="最后充值开始时间不能大于最后充值结束时间！\n";
				}
				return message;
			}
			function exportExcel(){
				document.getElementById("searchForm").action="<%=contextPath%>/purse/purseMoney!exportCSV.action";
				document.getElementById("searchForm").submit();
				document.getElementById("searchForm").action="<%=contextPath%>/purse/purseMoney!getPurseMoneyList.action?queryMoneyCondition.currentPage=1";
			}
			function allCheck(obj) {
				$("input[name=ids]:checkbox").attr('checked', obj.checked);
			}
			function clear(){
				$("#userAccount").val("");
				$("#createBeginTime").val("");
				$("#createEndTime").val("");
				$("#mobile").val("");
				$("#updateBeginTime").val("");
				$("#updateEndTime").val("");
				$("#status").val("");
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
							<font class="lists_fleft">账户管理列表</font>
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
									<table width="50%" border="0" cellspacing="1" cellpadding="0"
										class="lists" align="center">
										<tr>
											<td>
												用户账号：
											</td>
											<td valign="middle" align="left"  style="text-align:left">
												<input  type="text" name="queryMoneyCondition.userAccount"
													id="userAccount" value="${queryMoneyCondition.userAccount}" />
											</td>
											<td >创建时间：</td>
											<td valign="middle" align="left"  style="text-align:left">
												<input type="text" readonly="readonly" name="queryMoneyCondition.createBeginTime" id="createBeginTime"
		                                    		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="queryMoneyCondition.createBeginTime"  format="yyyy-MM-dd HH:mm:ss"/>" />
		                                	 	&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
		                                 		<input type="text" readonly="readonly" name="queryMoneyCondition.createEndTime" id="createEndTime"
		                                     		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="queryMoneyCondition.createEndTime"  format="yyyy-MM-dd HH:mm:ss"/>" />
										 	</td>
										</tr>
										<tr>
											<td>
												手机：
											</td>
											<td valign="middle" align="left"  style="text-align:left">
												<input type="text" name="queryMoneyCondition.mobile"
													id="mobile" value="${queryMoneyCondition.mobile}" />
											</td>
											<td >最后充值时间：</td>
											<td valign="middle" align="left" colspan="5" style="text-align:left">
												<input type="text" readonly="readonly" name="queryMoneyCondition.updateBeginTime" id="updateBeginTime"
		                                    		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="queryMoneyCondition.updateBeginTime"  format="yyyy-MM-dd HH:mm:ss"/>" />
		                                	 	&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
		                                 		<input type="text" readonly="readonly" name="queryMoneyCondition.updateEndTime" id="updateEndTime"
		                                     		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="queryMoneyCondition.updateEndTime"  format="yyyy-MM-dd HH:mm:ss"/>" />
										 	</td>
										</tr>
										<tr>
											<td>
												账户状态：
											</td>
												<td>
												<s:select list="accountTypeMap"   listKey="key" listValue="value"  name="queryMoneyCondition.status" id="status"
												headerKey="" headerValue="---请选择---">
												</s:select>
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
										<td class="lists_tright" width="50%">累计账户余额：</td>
										<td >
											<s:property value="accountDalanceSum"/>
										</td>
										<td  class="lists_tright" width="50%">冻结账户余额：</td>
										<td >
											<s:property value="freezeSum"/>
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="lists_info" >
	
									<tr class="lists_infobg">
										<td><input type="checkbox"
									onclick="allCheck(this)" />编号</td>
										<td>
											用户账号
										</td>
										<td>
											手机号
										</td>
										<td>
											账户余额
										</td>
										<td>
											状态
										</td>
										<td>
											创建时间
										</td>
										<td>
											最后充值时间
										</td>
										<td>
											最后操作人
										</td>
										
										<td colspan="1">
											操作
										</td>
									</tr>
									<s:if test="page.pageResult.size()!=0">
										<s:iterator value="page.pageResult" id="purseMoney"
											status="status">
											<tr>
												<td>
												<input type="checkbox" name="ids" value='<s:property value="#purseMoney.cusId"/>' />
													<s:property
														value="(page.currentPage-1)*page.pageSize+#status.count" /> 
												</td>
												<td>
													<s:property value="#purseMoney.userAccount"/>
												</td>
												<td>
													<s:property value="#purseMoney.mobile"/>
													
												</td>
												<td>
													<s:property value="#purseMoney.money"/>
													
												</td>
												<td>
													<s:property value="#purseMoney.accountStatus"/>
													
												</td>
												<td>
													<s:date name="#purseMoney.createTime" format="yyyy-MM-dd HH:mm:ss"/>
												</td>
												
												<td>
													<s:date name="#purseMoney.updateTime" format="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td>
													<s:property value="#purseMoney.updateUser"/>
												</td>
												
												<td>
												
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