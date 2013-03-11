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
		/*-状态保持-*/
		if(iszfb == true){
			$("#banknametr").hide();
			$("#bankregnametr").hide();
			$("#cusbanknametd").text("支付宝姓名:");
			$("#bankcodetd").text("支付宝账号:");
		}
	});
	
	/**验证表单**/
	function validateF(){
			if($("#refundid").val() <= 0){
				alert("数据不合法");
				return;
			}
			if(confirm("您确认撤销退费申请?")){
				$("#f1").submit();
			}
	}
</script>
</head>
<body>
<div id="detailWin">
	<form action="finance/refd!cancel.action" method="post" id="f1">
	<input type="hidden" value="${refundDTO.id}" name="refund.id" id="refundid"></input>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
        <td class="lists_top"><font class="lists_fleft">取消退费</font></td>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
      </tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr height="30">
					<td class="lists_tright">
						退费商品:
					</td>
					<td class="lists_tleft">
						${refundDTO.sellname}
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						所属订单编号:
					</td>
					<td class="lists_tleft">
						${refundDTO.contractno}
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						商品状态:
					</td>
					<td class="lists_tleft">
						 <s:if test="refundDTO.fstatus==0">未支付</s:if>
						 <s:if test="refundDTO.fstatus==1">已支付</s:if>
						 <s:if test="refundDTO.fstatus==2">取消</s:if>
						 <s:if test="refundDTO.fstatus==3">退费</s:if>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						退费状态:
					</td>
					<td class="lists_tleft">
						 <s:if test="refundDTO.status==0">退费中</s:if>
						 <s:if test="refundDTO.status==1">已退费</s:if>
						 <s:if test="refundDTO.status==2">已取消</s:if>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						用户Email:
					</td>
					<td class="lists_tleft">
						${refundDTO.useremail}
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						是否全额退款:
					</td>
					<td class="lists_tleft">
						  <s:if test="refundDTO.isfull==true">是</s:if>
						  <s:if test="refundDTO.isfull==false">否</s:if>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						是否支付宝账户:
					</td>
					<td class="lists_tleft">
						  <s:if test="refundDTO.iszfb==true">是</s:if>
						  <s:if test="refundDTO.iszfb==false">否</s:if>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						成交价格:
					</td>
					<td class="lists_tleft">
						${refundDTO.dealprice}元
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						退款金额:
					</td>
					<td class="lists_tleft">
						${refundDTO.price}元
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
						银行名称:
					</td>
					<td class="lists_tleft">
						${refundDTO.bankname}
					</td>
				</tr>
				<tr height="30" id="bankregnametr">
					<td class="lists_tright">
						开户行名称:
					</td>
					<td class="lists_tleft">
						${refundDTO.bankregname}
					</td>
				</tr>
				<tr height="30" >
					<td id="cusbanknametd" class="lists_tright">
						开户人姓名:
					</td>
					<td class="lists_tleft">
						${refundDTO.cusbankname}
					</td>
				</tr>
				<tr height="30">
					<td id="bankcodetd" class="lists_tright">
						退费账号:
					</td>
					<td class="lists_tleft">
						${refundDTO.bankcode}
					</td>
				</tr>
				<tr height="30">
					<td  class="lists_tright">
						付款时间:
					</td>
					<td class="lists_tleft">
						<s:date name="refundDTO.paytime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="30">
					<td  class="lists_tright">
						申请退费时间:
					</td>
					<td class="lists_tleft">
						<s:date name="refundDTO.createtime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="30">
					<td  class="lists_tright">
						实际退费时间:
					</td>
					<td class="lists_tleft">
						<s:date name="refundDTO.refundtime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="50">
					<td class="lists_tright">
						撤销退费时间:
					</td>
					<td class="lists_tleft">
						<s:date name="refundDTO.uptime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="50">
					<td class="lists_tright">
						取消原因:
					</td>
					<td class="lists_tleft">
						<textarea rows="3" cols="40" name="refund.remark" id="remark"  style="resize: none; font-size: 12px;" readonly="true">${refundDTO.remark}</textarea>
					</td>
				</tr>
				 <td colspan="2" align="center"><input type="button" onclick="validateF();" value="确认取消"/>&nbsp;&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="返回"/> </td></tr> 
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
	</form>
</div>
</body>
</html>