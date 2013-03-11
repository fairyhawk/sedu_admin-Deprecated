<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>短信显示</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>

<style>
#test1 {
	position: absolute;
	width: 500px;
	padding: 0px;
	border: 1px solid black;
	z-index: 100;
	cursor: hand;
	top: 2px;
	right: 10%;
	display: none;
}
#test2 {
	position: absolute;
	width: 500px;
	padding: 0px;
	border: 1px solid black;
	z-index: 100;
	cursor: hand;
	top: 2px;
	right: 10%;
	display: none;
}
</style>

<script language="javascript" type="text/javascript">
</script>

</head>
<body>
		<form action="<%=contextPath%>/finance/coupon!sendSMSForCoupon.action"
			method="post" name="addForm" id="addForm" onsubmit="return yan();" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">短信显示</font> <font
						class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					<td width="12" class="lists_bor"></td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="2"  >
							
							
							<tr>
								<td width="20%" >
								   <b> 发送成功（<s:property value="cellPhoneList.size()" />）</b>
								</td>
								<td width="25%">
									   <b> 发送失败（<s:property value="cellPhoneListCrabs.size()" />）</b>
								</td>
							</tr>
								<tr>
								  		<td>邮箱  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		电话</td>
								  			<td>邮箱  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								  		&nbsp;&nbsp;
								  		电话</td>
								<tr>
								<td width="30%">
								<div style="overflow-y:scroll;width:70%;height:1500%">
								
								  <table border="1"  width="" >
								  	<tr>
								  	</tr>
								  <s:iterator var="cellPhonee" value="cellPhoneList">
								  	<tr>
								  		<td><s:property value="#cellPhonee.cellEmail" /></td>
								  		<td><s:property value="#cellPhonee.sellPhone" /></td>
								  	</tr>
								  	</s:iterator>
								  </table>
								  </div>
								  
								</td>
								<td width="2%">
								<div style="overflow-y:scroll;width:70%;height:1500%">
								  <table border="1" width="200">
								  		<tr>
								  	</tr>
								  <s:iterator var="cellPhonee" value="cellPhoneListCrabs">
								  	<tr>	
								  		<td><s:property value="#cellPhonee.cellEmail" /></td>
								  		<td><s:property value="#cellPhonee.sellPhone" /></td>
								  	</tr>
								  	</s:iterator>
								  </table>
								 </div>
								</td>
								<td align="top"></td>
							</tr>
							<tr>
						<tr>
							<td align="right"><input  type="button" onclick="history.go(-1)" value="返回"/> </td>
						</tr>
						</table></td>
						
						
					<td width="16" class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					<td><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
					<td class="lists_bottom"></td>
					<td><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
				</tr>
			</table>
				
		</form>
</body>
</html>
