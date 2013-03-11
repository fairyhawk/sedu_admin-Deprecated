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
		<script type="text/javascript"
			src="@projectURL@/static/js/jquery-jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="@projectURL@/static/js/jquery.lazyload.js"></script>
		<script type="text/javascript"
			src="@projectURL@/static/js/jQueryValidate/jquery.validate.js"></script>

		<script language="JavaScript">	
	</script>
	</head>
	<body>
		<div>
			<form name="f1" method="post"
				action="bopayendTimeoks!getBooksList.action">
				<s:hidden name="queryContractCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="lists lists_info">
					<tr align="center">
						<td colspan="2">
						<br />
							<h2>
								订单详情-
								<s:property value="contract.contractId" />
							</h2>
						</td>
					</tr>
					<tr class="lists_infobg">
						<td width="50%">
							订单信息
						</td>
						<td width="50%">
							收货人信息
						</td>
					</tr>
					<tr>
						<td>
							订单编号：
							<s:property value="contract.contractId" />
						</td>
						<td>
							收货人姓名：
							<s:property value="address.receiver" />
						</td>
					</tr>
					<tr>
						<td>
							学员账号：
							<s:property value="customer.email" />
						</td>
						<td>
							收货人地址：
							<s:property value="addressStr" />
						</td>
					</tr>
					<tr>
						<td>
							下单时间：
							<s:date name="contract.createTime" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							邮编：
							<s:property value="address.postCode" />
						</td>
					</tr>
					<tr>
						<td>
							支付方式：
							<s:if test="contract.payType==1">
							支付宝/网银
							</s:if>
							<s:elseif test="contract.payType==3">
							网银在线/网银
							</s:elseif>
							<s:elseif test="contract.payType==2">
							货到付款
							</s:elseif>
							<s:elseif test="contract.payType==0">
							内部/代理商开通
							</s:elseif>
							<s:elseif test="contract.payType==4">
							快钱
							</s:elseif>
							<!-- yangn 修改 加入代理商 -->
							<s:elseif test="contract.payType==5">
							代理商
							</s:elseif>
							<s:elseif test="contract.payType==6">
							真友支付
							</s:elseif>
							<s:elseif test="contract.payType==7">
							银行汇款
							</s:elseif>
							<s:elseif test="contract.payType==8">
							银联在线/银联
							</s:elseif>
							<s:elseif test="contract.payType==9">
							课程卡
							</s:elseif>
							<s:elseif test="contract.payType==10">
							易联语音
							</s:elseif>
						</td>
						<td>
							移动电话：
							<s:property value="address.mobile" />
						</td>
					</tr>
					<tr>
						<td>
							商品名称：
							<s:property value="sellNames" />
						</td>
						<td>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="lists lists_info">
					<tr align="center">
						<td colspan="2">
						<br />
							<h2>
								付款信息
							</h2>
						</td>
					</tr>
					<tr class="lists_infobg">
						<td colspan="2">
							付款信息
						</td>
					</tr>
					<tr>
						<td width="50%">
							原始金额：
							<s:property value="contract.contractSumMoney"/>
						</td>
						<td  width="50%">
							运费金额：
							<s:property value="contract.freight"/>
						</td>
					</tr>
					<tr>
						<td>
							优惠金额：
							<s:property value="contract.couponMoney" />
						</td>
						<td>
							特殊减免：
							<s:property value="contract.relief" />
						</td>
					</tr>
					<tr>
						<td>
							应付订单金额：
							<s:property value="contract.contractCutSumMoney+contract.freight"/>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							优惠编码：
						<a href="/finance/coupon!couponCodeXX.action?couponId=${coup.id}"><s:property value="coup.title"/></a>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							课程折扣：
							<s:property value="coup.couponType.preferentialPrice"/>
						</td>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="lists lists_info">
					<tr align="center">
						<td colspan="6">
						<br />
							<h2>
								课程信息
							</h2>
						</td>
					</tr>
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
									<s:property value="#sellWay.sellId" />
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
									<s:property value="#sellWay.sellPrice" />
								</td>
								<td>
									<s:property value="#sellWay.sellPrice" />
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="6" align="left" 　valign="middle">
								<h4>
									<!-- 课程激活码：
									<s:property value="contract.contractCDkey" /> -->
								</h4>
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td align="center" colspan="6">
								没有课程!
							</td>
						</tr>
					</s:else>
					<tr>
						<td colspan="6">
							<input type="button" name="" value="关闭" onclick="window.close();">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>