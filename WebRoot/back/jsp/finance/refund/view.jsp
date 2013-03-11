<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>查看回复信息</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/jquery.jqplot.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/examples.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript">
$(function(){
	var iszfb = ${refundDTO.iszfb};
	/*-支付宝状态判断-*/
	if(iszfb == true){
		$("#bankregnametr").hide();
		$("#banknametr").hide();
		$("#cusbanknametd").text("支付宝注册姓名:");
		$("#bankcodetd").text("支付宝注册账号");
	}
});
</script>
</head>
<body>
<div id="detailWin">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
	<tr>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
        <td class="lists_top"><font class="lists_fleft">退费信息</font></td>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
      </tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr height="30">
					<td class="lists_tright">
						退费商品:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.sellname}
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						所属订单编号:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.contractno}
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						商品状态:&nbsp;
					</td>
					<td class="lists_tleft">
						 <s:if test="refundDTO.fstatus==0">&nbsp;未支付</s:if>
						 <s:if test="refundDTO.fstatus==1">&nbsp;已支付</s:if>
						 <s:if test="refundDTO.fstatus==2">&nbsp;取消</s:if>
						 <s:if test="refundDTO.fstatus==3">&nbsp;退费</s:if>
					</td>
				</tr>
				<tr height="30">
					<td  class="lists_tright">
						退费状态:
					</td>
					<td class="lists_tleft">
						 <s:if test="refundDTO.status==0">&nbsp;退费中</s:if>
						 <s:if test="refundDTO.status==1">&nbsp;已退费</s:if>
						 <s:if test="refundDTO.status==2">&nbsp;已取消</s:if>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						用户Email:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.useremail}
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						是否全额退款:&nbsp;
					</td>
					<td class="lists_tleft">
						  <s:if test="refundDTO.isfull==true">&nbsp;是</s:if>
						  <s:if test="refundDTO.isfull==false">&nbsp;否</s:if>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						是否支付宝账户:&nbsp;
					</td>
					<td class="lists_tleft">
						  <s:if test="refundDTO.iszfb==true">&nbsp;是</s:if>
						  <s:if test="refundDTO.iszfb==false">&nbsp;否</s:if>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						成交价格:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.dealprice}元
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						退款金额:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.price}元
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						记录人:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.operusername}
					</td>
				</tr>
				<tr height="30" id="banknametr">
					<td class="lists_tright">
						银行名称:&nbsp;
					</td>
					<td class="lists_tleft" id="">
						&nbsp;${refundDTO.bankname}
					</td>
				</tr>
				<tr height="30" id="bankregnametr">
					<td class="lists_tright">
						开户行名称:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.bankregname}
					</td>
				</tr>
				<tr height="30" >
					<td id="cusbanknametd" class="lists_tright">
						开户人姓名:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.cusbankname}
					</td>
				</tr>
				<tr height="30" id="banknametr">
					<td class="lists_tright">
						联系人姓名:&nbsp;
					</td>
					<td class="lists_tleft" id="">
						&nbsp;${refundDTO.conname}
					</td>
				</tr>
				<tr height="30" id="banknametr">
					<td class="lists_tright">
						联系电话:&nbsp;
					</td>
					<td class="lists_tleft" id="">
						&nbsp;${refundDTO.usermobile}
					</td>
				</tr>
				<tr height="30" class="lists_tright">
					<td id="bankcodetd" class="lists_tright">
						退费账号:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.bankcode}
					</td>
				</tr>
				<tr height="30">
					<td id="bankcodetd" class="lists_tright">
						付款时间:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<s:date name="refundDTO.paytime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="30">
					<td id="bankcodetd" class="lists_tright">
						申请退费时间:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<s:date name="refundDTO.createtime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="30">
					<td id="bankcodetd" class="lists_tright">
						实际退费时间:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<s:date name="refundDTO.refundtime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="30">
					<td id="bankcodetd" class="lists_tright">
						撤销退费时间:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<s:date name="refundDTO.canceltime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="30">
					<td id="bankcodetd" class="lists_tright">
						提交者姓名:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.opername}
					</td>
				</tr>
				<tr height="30">
					<td id="bankcodetd" class="lists_tright">
						审核者姓名:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;${refundDTO.checkname}
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						退费原因:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<textarea rows="3" cols="40" name="refund.remark" id="remark" readonly="true" style="resize: none; font-size: 12px;">${refundDTO.remark}</textarea>
					</td>
				</tr>
				 <td colspan="2" align="center"> &nbsp;&nbsp;<a href="###" onclick="javascript:history.go(-1);">返回</a> </td></tr> 
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>