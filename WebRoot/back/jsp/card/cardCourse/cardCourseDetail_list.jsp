<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程卡查询列表</title>
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
		
		<script>
		$(function(){
			$("#search").click(function(){
				var msg=validateInfo();
				if(msg!=""){
					alert(msg);
					return;
				}  
				$("#searchForm").attr("action","<%=contextPath%>/card/cardMain!getCardCourseList.action?queryCardCourseCondition.currentPage=1");
				$("#searchForm").submit();
			});
		});
		function abolishCardCourse(cardCourseId){
			if(window.confirm("作废后将不能进行恢复，是否作废此课程卡？")){
				$.ajax({
					url :  "<%=contextPath%>/card/cardMain!abolishCardCourse.action",
					data : {
						"cardCourse.cardCourseId" : cardCourseId,
						"cardCourse.cardCourseStatus" : 4
					},
					type : "post",
					cache : false,
					async : false,
					success : function(result) {
						callBackQueryList();
					},
					error : function(error) {
						alert(error);
					}
				});
			}
		}
		function callBackQueryList(){
			$("#searchForm").attr("action","<%=contextPath%>/card/cardMain!getCardCourseList.action?queryCardCourseCondition.currentPage=1");
			$("#searchForm").submit();
		}
		function validateInfo(){
			var message="";
			if((new Date($("#createBeginTime").val().replace(/-/g,"/")))>(new Date($("#createEndTime").val().replace(/-/g,"/"))))
			{
				message +="开始时间不能大于结束时间！\n";
			}
			return message;
		}
		function exportExcel(){
			$("#searchForm").attr("action","<%=contextPath%>/card/cardMain!exportCardCouseDetail.action");
			$("#searchForm").submit();
			$("#searchForm").attr("action","<%=contextPath%>/card/cardMain!getCardCourseList.action?queryCardCourseCondition.currentPage=1");
			
		}
		function clear(){
			$("#createBeginTime").val("");
			$("#createEndTime").val("");
			$("#cardMoney").val("");
			$("#cardCodeBegin").val("");
			$("#cardCodeEnd").val("");
			$("#cardCourseStatus").val("");
			$("#cardName").val("");
			$("#updateUser").val("");
			$("#sellIds").val("");
			$("#cardCourseUseStatus").val("");
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
						<font class="lists_fleft">课程卡查询列表</font>
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
								<table width="60%" border="0" cellspacing="1" cellpadding="0"
									class="lists" align="center">
									<tr>
										<td >时间查询：</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input type="text" readonly="readonly" name="queryCardCourseCondition.createBeginTime" id="createBeginTime"
	                                    		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="queryCardCourseCondition.createBeginTime"  format="yyyy-MM-dd"/>" />
	                                	 	&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
	                                 		<input type="text" readonly="readonly" name="queryCardCourseCondition.createEndTime" id="createEndTime"
	                                     		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="queryCardCourseCondition.createEndTime"  format="yyyy-MM-dd"/>" />
									 	</td>
									 	<td width="10%">
											课程卡金额：
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input  type="text" name="queryCardCourseCondition.cardMoney"
												id="cardMoney" value="${queryCardCourseCondition.cardMoney}" />
										</td>
									</tr>
									<tr>
										<td >课程卡编码：</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input type="text"  name="queryCardCourseCondition.cardCodeBegin" id="cardCodeBegin"
	                                    		 value="${queryCardCourseCondition.cardCodeBegin}" />
	                                	 	&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
	                                 		<input type="text"  name="queryCardCourseCondition.cardCodeEnd" id="cardCodeEnd"
	                                    		 value="${queryCardCourseCondition.cardCodeEnd}" />
									 	</td>
										<td width="10%">
											课程卡名称：
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input  type="text" name="queryCardCourseCondition.cardName"
												id="cardName" value="${queryCardCourseCondition.cardName}" />
										</td>
										
									</tr>
									<tr>
										<td width="10%">
											使用状态：
										</td>
										<td>
											<s:select list="cardCourseUseStatusMap"   listKey="key" listValue="value"  name="queryCardCourseCondition.cardCourseUseStatus" id="cardCourseUseStatus"
											headerKey="" headerValue="---请选择---">
											</s:select>
										</td>
										<td width="10%">
											课程卡状态：
										</td>
										<td>
											<s:select list="cardStatusMap"   listKey="key" listValue="value"  name="queryCardCourseCondition.cardCourseStatus" id="cardCourseStatus"
											headerKey="" headerValue="---请选择---">
											</s:select>
										</td>
										
										
									</tr>
									<tr>
										<td width="10%">
											商品 ID：
										</td>
										<td  align="left"  style="text-align:left">
											<input  type="text" name="queryCardCourseCondition.sellIds"
												id="sellIds" value="${queryCardCourseCondition.sellIds}" />
										</td>
										<td width="10%">
											操作人：
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input  type="text" name="queryCardCourseCondition.updateUser"
												id="updateUser" value="${queryCardCourseCondition.updateUser}" />
										</td>
									</tr>
									<tr>
										
										<td class="lists_tleft" colspan="3">
											<img src="<%=contextPath%>/back/images/add_a.gif" />
											<a href="javascript:void(0)" id="search">查询</a>&nbsp;
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
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info" >
							<tr class="lists_infobg">
								<td>编号</td>
								<td>
									课程卡名称
								</td>
								<td>
									课程卡编码
								</td>
								<td>
									商品ID
								</td>
								<td>
									生成时间
								</td>
								<td>
									课程卡金额
								</td>
								<td>
									代理商金额
								</td>
								<td>
									创建人
								</td>
								<td>
									激活人
								</td>
								<td>
									有效期
								</td>
								<td>
									用户账号
								</td>
								<td>
									课程卡状态
								</td>
								<td>
									使用状态
								</td>
								<td>
									备注
								</td>
								
								<td colspan="1">
									操作
								</td>
							</tr>
							<s:if test="page.pageResult.size()!=0">
								<s:iterator value="page.pageResult" id="cardCourse"
									status="status">
									<tr>
										<td>
											<s:property
												value="(page.currentPage-1)*page.pageSize+#status.count" /> 
										</td>
										<td>
											<s:property value="#cardCourse.cardName"/>
										</td>
										<td>
											<s:property value="#cardCourse.cardCourseCode"/>
										</td>
										<td>
											<s:property value="#cardCourse.sellIds"/>
										</td>
										<td>
											<s:date name="#cardCourse.createTime" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
											<s:property value="#cardCourse.cardMoney"/>
										</td>
										<td>
											<s:property value="#cardCourse.agentMoney"/>
										</td>
										<td>
											<s:property value="#cardCourse.creator"/>
										</td>
										<td>
											<s:property value="#cardCourse.activateUser"/>
										</td>
										<td>
											<s:date name="#cardCourse.validBeginTime" format="yyyy-MM-dd"/>-<s:date name="#cardCourse.validEndTime" format="yyyy-MM-dd"/>
										</td>
										<td>
											<s:property value="#cardCourse.userAccount"/>
										</td>
										<td>
											<s:property value="#cardCourse.cardCourseStatusContent"/>
										</td>
										<td>
											<s:property value="#cardCourse.cardCourseUseStatusContent"/> 
										</td>
										<td>
											<s:property value="#cardCourse.remark"/>
										</td>
										<td>
											<s:if test="#cardCourse.cardCourseStatus!=4&&#cardCourse.cardCourseStatus!=2">
												<a href="javascript:abolishCardCourse(<s:property value="#cardCourse.cardCourseId"/>)" >作废</a>
											</s:if>
											<a href="<%=contextPath%>/card/cardMain!getCardCourseDetail.action?cardCourse.cardCourseId=<s:property value="#cardCourse.cardCourseId"/>" >查看</a>
										</td>
									</tr>
								</s:iterator>
							</s:if>
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
		</div>	
	</body>
</html>