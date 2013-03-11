<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建发票</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript">
		$().ready(function() {
    	       $("#waybillform").validate();
		})
		
		function saveWaybill(){
			var msg=validateInfo();
			if(msg!=""){
				alert(msg);
				return;
			}
			$("#waybillform").submit();
		}
		function validateInfo(){
			var message="";
			message+=validateRepeat();
			if(/^1{1}[0-9]{10}$/.test($("#consigneePhone").val())==false)
			{
				message +="收货人手机号码不正确，请重新填写！ \n";
			}
			return message;
		}
		function validateRepeat(){
			var message="";
			$.ajax({
				url :  "<%=contextPath%>/finance/waybill!validateRepeat.action",
				data : {
					"waybill.waybillCode" : $("#waybillCode").val()
					
				},
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
					 if(result.entity[0].waybillCodeCount>0){
						message+="运单号在系统中已经存在，请确认以前是否已经录入过该运单号！\n"
					}
					
				},
				error : function(error) {
					alert(error);
				}
			});
			return message;
		}
		</script>
	</head>
	<body>
		<div>
			<form action="<%=contextPath%>/finance/waybill!addOrUpdateWaybill.action" method="post" name="waybillform" id="waybillform" enctype="multipart/form-data">
			<input type="hidden" name="waybill.waybillId" id="waybillId" value="<s:property value="waybill.waybillId"/>"/>
				<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">新建运单号</font>
							</font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12"  class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="tblList">
								<tbody id="tagTb">
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>输入运单号码：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="waybill.waybillCode" id="waybillCode" value="<s:property value="waybill.waybillCode"/>" class="{required:true,minlength:1,maxlength:32}"  style="width:11%"></input>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>选择快递公司：
										</td>
										<td class="lists_tleft">
											<s:select name="waybill.expressCompany" id="expressCompany" list="expressCompanyList"
												listKey="dicContent" listValue="dicContent" 
												theme="simple" >
											</s:select>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>选择物品类别：
										</td>
										<td class="lists_tleft">
											<s:select name="waybill.goodsCategory" id="goodsCategory" list="goodsCategoryList"
												listKey="dicContent" listValue="dicContent" 
												theme="simple" >
											</s:select>
										</td>
									</tr>
										<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>收货人姓名：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="waybill.consigneeName" id="consigneeName" value="<s:property value="waybill.consigneeName"/>" class="{required:true,minlength:1,maxlength:32}"   style="width:11%"></input>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>收货人邮箱：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="waybill.consigneeEmail" id="consigneeEmail" value="<s:property value="waybill.consigneeEmail"/>" class="{required:true,email:true}"   style="width:11%"></input>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>收货人手机：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="waybill.consigneePhone" id="consigneePhone" value="<s:property value="waybill.consigneePhone"/>" class="{required:true,minlength:1,maxlength:16}"  style="width:11%"></input>
										</td>
									</tr>	
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>订单号码：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="waybill.orderCode" id="orderCode" value="<s:property value="waybill.orderCode"/>" class="{required:true,minlength:1,maxlength:32}"  style="width:11%"></input>
										</td>
									</tr>	
									<tr>
										<td colspan="2">
											<input  type="button" onclick="saveWaybill()" value="确认发货"/>
										</td>
									</tr>
							</table>
						</td>
					</tr>	
			</form>
		</div>
	</body>
</html>
