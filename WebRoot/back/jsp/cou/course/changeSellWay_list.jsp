<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>更换课程列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		
		function gotoAction(sellId,cusId,crId,packId){
	 		$.ajax({
	 				url :"<%=contextPath%>/finance/backCashRecord!updateCashRecordSellWay.action",
	 				data : {
	 					"sellWay.sellId":sellId,
	 					"customer.cusId":cusId,
	 					"cashRecord.id":crId,
	 					"pastSellWayId":packId
	 					},
	 				type : "post",
	 				dataType : "json",
	 				cache : false,
	 				async : false,
	 				success : function(result) {
	 						closeFlashWin();
	 				},
	 				error : function(error) {
	 				}
	 			});
 	}
	
	function closeFlashWin(){
		alert("课程更换成功,重新登录生效！");
		window.close();
	}
		
		
//选择专业时候，老师跟着变；
</script>
	</head>
	<body>
		<!-- 查询块开始 -->
		<div>
			<table class="lists" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">更换课程</font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor"></td>
					<td>
						<form name="searchForm" id="searchForm" action="<%=contextPath%>/finance/backCashRecord!showSellWayList.action?querySellWayCondition.currentPage=1" method="post">
							<input type="hidden"  name="customer.cusId" value='<s:property value="customer.cusId"/>' />
							<input type="hidden"  name="cashRecord.id" value='<s:property value="cashRecord.id"/>' />
							<input type="hidden"  name="cashRecord.packId" value='<s:property value="cashRecord.packId"/>' />
							<table width="100%" border="0" cellspacing="1" cellpadding="0" align="left" style="text-align: left" class="lists_info">
								<tr align="left">
									<td align="left" style="text-align: left">
										<table border="0" class="lists">
											<tr>
												<td width="60px">
													所属项目:
												</td>
												<td align="left" width="180px" style="text-align: left">
													<s:select list="subjectList" listKey="subjectId"
														id="subjectList" listValue="subjectName"theme="simple"
														name="querySellWayCondition.subjectId" headerKey="-1"
														headerValue="---请选择---">
													</s:select>
												</td>
												<td>
													&nbsp;&nbsp;&nbsp;关键字:
												</td>
												<td align="left" class="lists_tleft" colspan="3"
													style="text-align: left">
													<input type="text" name="querySellWayCondition.word"
														id="keyword"/>
													
												</td>
											</tr>
											<tr>
											
												<td align="center">
													<s:submit value="查询"  />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</form>
					</td>
					<td width="16" class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					<td>
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom"></td>
					<td>
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>

		</div>
		<!-- 查询块结尾 -->
		<div>
			<table class="lists" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
					    当前兑换课程账号：<s:property value="customer.email"/>
					    <s:hidden value="customer.cusId"></s:hidden>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td  width="12" class="lists_bor"></td>
					<td>
						<form name="sellWayForm" method="post">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info" style="word-wrap: break-word;">
								<tr class="lists_infobg">
									<td width="5%">
										商品号
									</td>
									<td width="14%">
										商品名称
									</td>
									<td width="7%">
										所属项目
									</td>
									<td width="3%">
										商品价格
									</td>
									<td width="8%">
										是否在售
									</td>
									<td width="3%">
										状态
									</td>
									<td width="9%">
										操作
									</td>
								</tr>

								<s:iterator id="sellWaytemp" value="page.pageResult">
									<tr>
										<td>
											<s:property value="#sellWaytemp.sellId" />
										</td>
										<td>
											<s:property value="#sellWaytemp.sellName" />
										</td>
										<td>
											<s:property value="#sellWaytemp.subjectName" />
										</td>
										<td>
											<s:property value="#sellWaytemp.sellPrice" />
										</td>
										<td>
											<s:if test="#sellWaytemp.shopState==0">
											否/下架时间： <br />
												<s:date name="#sellWaytemp.dropTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</s:if>
											<s:if test="#sellWaytemp.shopState==1">
											是
										</s:if>
										</td>
										<td>
											<s:if test="#sellWaytemp.status==0">
											正常
										</s:if>
											<s:if test="#sellWaytemp.status==1">
											冻结
										</s:if>
										</td>
										<td>
										
											<a onclick="return confirm('更换课程将无法恢复,请确认更换课程！');" href='javascript:gotoAction(<s:property value="#sellWaytemp.sellId"/>,<s:property value="customer.cusId"/>,<s:property value="cashRecord.id"/>,<s:property value="cashRecord.packId"/>)'>更换此课程</a>
									</tr>
								</s:iterator>
							</table>
						</form>
					</td>
					<td width="16" class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					<td class="td_wid_1">
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom"><jsp:include
							page="/back/jsp/common/showPage.jsp" /></td>
					<td class="td_wid_r">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>