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
		<script type="text/javascript" src="@projectURL@/static/js/jquery-jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="@projectURL@/static/js/jquery.lazyload.js"></script>
		<script type="text/javascript" src="@projectURL@/static/js/jQueryValidate/jquery.validate.js"></script>
		
	<script language="JavaScript">	
	</script>
	</head>
	<body>
		<div>
			<form name="f1" method="post"
				action="bopayendTimeoks!getBooksList.action">
				<s:hidden name="queryContractCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr align="center">
					<td width="12" class="lists_bor"></td>
					<td><h2>货到付款的详细列表</h2></td>
					</tr>
					<tr>
					<td width="12" class="lists_bor"></td>
					<td>订货人信息：</td>
					</tr>
					<tr>
					<td width="12" class="lists_bor"></td>
					<td>收货人地址：<s:property value="addressStr"/></td>
					</tr>
					<tr>
					<td width="12" class="lists_bor"></td>
					<td>收货人姓名：<s:property value="address.receiver"/></td>
					</tr>
					<tr>
					<td width="12" class="lists_bor"></td>
					<td>邮编：<s:property value="address.postCode"/></td>
					</tr>
					<tr>
					<td width="12" class="lists_bor"></td>
					<td>移动电话：<s:property value="address.mobile"/></td>
					</tr>
					<tr>
					<td width="12" class="lists_bor"></td>
					<td>支付方式：货到付款</td>
					</tr>
					<tr>
					<td width="12" class="lists_bor"></td>
					<td>快递方式：普通快递</td>
					</tr>
					<tr>
					<tr>
					<td width="12" class="lists_bor"></td>
					<td>商品信息：</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr><td>订单编号：<s:property value="contract.contractId"/></td><td>订购时间：
								<s:date name="contract.createTime" format="yyyy-MM-dd HH:mm:ss"/>
								</td><td>学员账号：<s:property value="customer.email"/></td><td>课程总数：<s:property value="sellWayList.size"/></td><td colspan="2">总金额：<s:property value="contract.contractCutSumMoney"/></td></tr>
								<tr class="lists_infobg">
									<td>
										售卖方式
									</td>
									<td>
										课程名称
									</td>
									<td>
										主讲老师
									</td>
									<td>
									    　数量
									</td>
									<td>
										　价格
									</td>
									<td>
										　小计
									</td>
									</tr>
								<s:if test="sellWayList.size()!=0">
									<s:iterator value="sellWayList" id="sellWay">
										<tr>
											<td>
											     <s:property value="#sellWay.sellId"/>
											</td>
											<td>
											     <s:property value="#sellWay.sellName" />
											</td>
											<td>
												<s:property value="#sellWay.teacher" />
											</td>
											<td>
												1
											</td>
											<td>
												<s:property value="#sellWay.sellPrice"/>
											</td>
											<td>
												<s:property value="#sellWay.sellPrice"/>
											</td>
										</tr>
									</s:iterator>
		
									<tr><td colspan="6" align="left"　valign="middle">
										<!--
		<h4>课程激活码：<s:property value="contract.contractCDkey"/></h4>
		-->
		</td></tr>
								</s:if>
								<s:else>
									<tr>
										<td align="center" colspan="6">
											没有课程!
										</td>
									</tr>
								</s:else>
								<tr><td colspan="6"><input type="button" name="" value="关闭" onclick="window.close();"></td></tr>
							</table>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>