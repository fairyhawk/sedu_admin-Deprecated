<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>

</head>
<body>
   <%--  <div style="float: right;padding-right: 10px" id="returnAllConfo" >
							<a href="<%=contextPath%>/finance/coupon!getCouponCodeList.action?queryCouponCodeCondition.currentPage=1"><font  class="lists">返回优惠券管理列表 </font></a>
	</div> --%>
	<div class="msg-cw">
	<table  border="0" cellspacing="0" cellpadding="0"  width="100%" style="margin-left: 100px" >
		<tr >
		
			<td colspan="2" align="center"><font ><b> 优惠券详情</b></font>
			<font color="red">&nbsp;【该优惠统计编码
			<s:if test="coupon.state==1">未使用</s:if> <s:if test="coupon.state==2">已使用</s:if>
										<s:if test="coupon.state==3">过期</s:if> <s:if test="coupon.state==4">作废</s:if>
										】
			</font>
			</td>
			
		</tr>
		<tr>
			<td width="140" >优惠券编码</td>
			<td>${coupon.title }</td>
		</tr>
		<tr>
			<td width="140" >优惠券类型</td>
			<td><s:if test="coupon.couponType.cType==1">折扣券</s:if> <s:if test="coupon.couponType.cType==2">定额券</s:if></td>
		</tr>
		<tr>
			<td width="140" >优惠券名称</td>
			<td><s:property value="coupon.couponType.title" /></td>
		</tr>
		<tr>
			<td width="140" >合作商</td>
			<td><s:property value="coupon.couponType.company" /></td>
		</tr>
		<tr>
			<td width="140" >优惠折扣/金额 </td>
			<td><s:property value="coupon.couponType.preferentialPrice"/></td>
		</tr>
		<tr>
			<td width="140" >使用限额</td>
			<td><s:property value="coupon.couponType.leastPrice" /></td>
		</tr>
		<tr>
			<td width="140" >使用范围</td>
			<td>
			<s:if test="coupon.couponType.subjectId==0">所有项目</s:if>
									<s:if test="coupon.couponType.subjectId!=0">
									<s:property value="coupon.couponType.subjectName" />
									</s:if>
			</td>
		</tr>
        <tr>
			<td width="140" >使用起始日期</td>
			<td>
			<s:property value="coupon.couponType.useTime" />
			</td>
		</tr>
		<tr>
			<td width="140" >使用截止日期</td>
			<td><s:property value="coupon.couponType.stopTime" /></td>
		</tr>
		<tr>
			<td width="140" >兑换积分</td>
			<td><s:property value="coupon.couponType.cToscore" /></td>
		</tr>
	</table>
	</div>
	<%-- <s:if test="contract!=null">
	<p style="margin-left: 100px"><b>相关订单信息</b></p>
	<table  border="0" cellspacing="0" cellpadding="0"	width="350px" style="margin-left: 100px;border: 1px">
		
		<tr>
			<td  width="140">关联订单</td>
			<td><!-- <a href="finance/backContract!getContractView.action?contract.id=${contract.id}">${contract.contractId}</a> -->${contract.contractId}</a></td>
		</tr>
		<tr>
			<td width="140" >学员邮箱</td>
			<td>${contract.customer.email }</td>
		</tr>
		<tr>
			<td width="140" >原始金额</td>
			<td>${contract.contractSumMoney }</td>
			
		</tr>
		<tr>
			<td width="140" >支付金额</td>
			<td>${contract.contractCutSumMoney}</td>
		</tr>
				<tr>
			<td width="140" >优惠金额</td>
			<td><s:property value="contract.couponMoney"/></td>
		</tr>
		<tr>
			<td width="140" >支付状态</td>
			<td>
			<s:if test="contract.payType==2">
			<s:if test="contract.status==0">未激活</s:if> 
			<s:if test="contract.status==1">已激活</s:if>
			<s:if test="contract.status==3">已取消</s:if>
			<s:if test="contract.status==4">退费</s:if>
			</s:if>
			
			<s:if test="contract.payType==1||contract.payType==3||contract.payType==4">
			<s:if test="coupon.paystate==1">未支付</s:if> 
			<s:if test="coupon.paystate==2">已支付</s:if>
			<s:if test="coupon.paystate==3">退费</s:if>
			</s:if>
			
			<s:if test="contract.payType==0">
			<s:if test="coupon.paystate==2">赠送</s:if> 

			</s:if>
			</td>
			
		</tr>
		<tr>
			<td width="140" >已购课程</td>
			<td style="border:solid 1px #aaaaaa;">
			<s:iterator id="my" value="sellWayNames">
			<s:property  value="my"/><br/>
			</s:iterator>
			</td>
		</tr>
		
	</table>
</s:if>
<s:if test="contract==null">
	<p style="margin-left: 100px"><b>无相关订单信息</b></p>
	</s:if> --%>
</body>
</html>