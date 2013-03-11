<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程卡基本信息列表</title>
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
		$(function(){
			$("#search").click(function(){
				var msg=validateInfo();
				if(msg!=""){
					alert(msg);
					return;
				}  
				$("#searchForm").attr("action","<%=contextPath%>/card/cardMain!getCardMainList.action?queryCardMainCondition.currentPage=1");
				$("#searchForm").submit();
			});
			$("#newCardCourse").click(function(){
				$("#searchForm").attr("action","<%=contextPath%>/card/cardMain!getCourseCardPage.action");
				$("#searchForm").submit();
			});
		
		});
		function abolishCardCourse(cardMainId){
			if(window.confirm("您将作废该基本课程卡中所有的课程卡，作废后将不能恢复，是否继续？")){
				$.ajax({
					url :  "<%=contextPath%>/card/cardMain!abolishCardCourseMain.action",
					data : {
						"cardMain.cardMainId" : cardMainId,
						"cardMain.cardUserStatus" : 3
					},
					type : "post",
					cache : false,
					async : false,
					success : function(result) {
						$("#searchForm").attr("action","<%=contextPath%>/card/cardMain!getCardMainList.action?queryCardMainCondition.currentPage=1");
						$("#searchForm").submit();
					},
					error : function(error) {
						alert(error);
					}
				});
			}
		}
		function activateCardCourse(cardMainId){
			if(window.confirm("激活后，该基本课程卡中所有的课程卡将可以使用，是否继续？")){
				var htmlStr="<div>"
					+"<table width='100%'>"
						+"<tr>"
							+"<td>"
		    					+"<textarea style=\"height:200px;width:100%;\"  id=\"activateRemark\"></textarea>"
							+"</td>"
						+"</tr>"
					+"</table>"
				+"</div>";
			easyDialog.open({
		  					container : {
		    				header : '激活备注',
						    content : htmlStr,
						    yesFn : function(){
								$.ajax({
									url :  "<%=contextPath%>/card/cardMain!activateCardMain.action",
									data : {
										"cardMain.cardMainId" : cardMainId,
										"cardMain.activateRemark" : $("#activateRemark").val(),
										"cardMain.cardUserStatus" : 1
									},
									type : "post",
									cache : false,
									async : false,
									success : function(result) {
										$("#searchForm").attr("action","<%=contextPath%>/card/cardMain!getCardMainList.action?queryCardMainCondition.currentPage=1");
										$("#searchForm").submit();
									},
									error : function(error) {
										alert(error);
									}
								});
								 return true;
							},
						    noFn : true
		 				}
				});
		
			}
		}
		
		function validateInfo(){
			var message="";
			if((new Date($("#createBeginTime").val().replace(/-/g,"/")))>(new Date($("#createEndTime").val().replace(/-/g,"/"))))
			{
				message +="开始时间不能大于结束时间！\n";
			}
			return message;
		}
		function clear(){
			$("#createBeginTime").val("");
			$("#createEndTime").val("");
			$("#cardMoney").val("");
			$("#cardUserStatus").val("");
			$("#cardName").val("");
			$("#updateUser").val("");
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
						<font class="lists_fleft">课程卡基本信息查询列表</font>
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
											<input type="text" readonly="readonly" name="queryCardMainCondition.createBeginTime" id="createBeginTime"
	                                    		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="queryCardMainCondition.createBeginTime"  format="yyyy-MM-dd"/>" />
	                                	 	&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
	                                 		<input type="text" readonly="readonly" name="queryCardMainCondition.createEndTime" id="createEndTime"
	                                     		onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="queryCardMainCondition.createEndTime"  format="yyyy-MM-dd"/>" />
									 	</td>
									 	<td width="10%">
											代理商金额：
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input  type="text" name="queryCardMainCondition.cardMoney"
												id="cardMoney" value="${queryCardMainCondition.cardMoney}" />
										</td>
									</tr>
									<tr>
										<td width="10%">
											课程卡状态：
										</td>
										<td>
											<s:select list="cardUserStatusMap"   listKey="key" listValue="value"  name="queryCardMainCondition.cardUserStatus" id="cardUserStatus"
											headerKey="" headerValue="---请选择---">
											</s:select>
										</td>
										<td width="10%">
											课程卡名称：
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input  type="text" name="queryCardMainCondition.cardName"
												id="cardName" value="${queryCardMainCondition.cardName}" />
										</td>
									</tr>
									<tr>
										<td width="10%">
											操作人：
										</td>
										<td valign="middle" align="left"  style="text-align:left">
											<input  type="text" name="queryCardMainCondition.updateUser"
												id="updateUser" value="${queryCardMainCondition.updateUser}" />
										</td>
									</tr>
									<tr>
										
										<td class="lists_tleft" colspan="2">
											<img src="<%=contextPath%>/back/images/add_a.gif" />
											<a href="javascript:void(0)" id="search">查询</a>&nbsp;
											<img src="<%=contextPath%>/back/images/del_a.gif" />
											<a href="javascript:clear()">清空</a>
											<s:hidden name="exportType" id="exportType"></s:hidden>
											<img src="<%=contextPath%>/back/images/add_a.gif" />
											<a href="javascript:void(0)" id="newCardCourse">新建课程卡</a>
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
									生成时间
								</td>
								<td>
									数量
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
									状态
								</td>
								<td>
									新建备注
								</td>
								<td>
									激活备注
								</td>
								<td colspan="1">
									操作
								</td>
							</tr>
							<s:if test="page.pageResult.size()!=0">
								<s:iterator value="page.pageResult" id="cardMain"
									status="status">
									<tr>
										<td>
											<s:property
												value="(page.currentPage-1)*page.pageSize+#status.count" /> 
										</td>
										<td>
											<s:property value="#cardMain.cardName"/>
										</td>
										
										<td>
											<s:date name="#cardMain.createTime" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
											<a href="<%=contextPath%>/card/cardMain!getCardCourseList.action?queryCardCourseCondition.currentPage=1&queryCardCourseCondition.cardMainId=<s:property value="#cardMain.cardMainId"/>"><s:property value="#cardMain.cardCount"/></a>
										</td>
										<td>
											<s:property value="#cardMain.cardMoney"/>
										</td>
										<td>
											<s:property value="#cardMain.agentMoney"/>
										</td>
										<td>
											<s:property value="#cardMain.creator"/>
										</td>
										<td>
											<s:property value="#cardMain.activateUser"/>
										</td>
										<td>
											<s:date name="#cardMain.validBeginTime" format="yyyy-MM-dd"/>-<s:date name="#cardMain.validEndTime" format="yyyy-MM-dd"/>
										</td>
										
										<td>
											<s:property value="#cardMain.cardUserStatusContent"/>
										</td>
										<td>
											<s:property value="#cardMain.remark"/>
										</td>
										<td>
											<s:property value="#cardMain.activateRemark"/>
										</td>
										<td>
											<a href="<%=contextPath%>/card/cardMain!getCardCourseMainInfo.action?cardMain.cardMainId=<s:property value="#cardMain.cardMainId"/>" >查看</a>
											<s:if test="#cardMain.cardUserStatus!=3">
												<a href="javascript:abolishCardCourse(<s:property value="#cardMain.cardMainId"/>)" >作废</a>
											</s:if>
											<s:if test="#cardMain.cardUserStatus!=1&&#cardMain.cardUserStatus!=3">
												<a href="javascript:activateCardCourse(<s:property value="#cardMain.cardMainId"/>)" >激活</a>
											</s:if>
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