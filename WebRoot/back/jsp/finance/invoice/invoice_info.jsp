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
    	       $("#invoiceform").validate();
    	       dealCompany();
    	       promptInfo();
		})
		function promptInfo(){
			if($("#companyName").val()==""){
				setCompanyNameInfo();
			}
			if($("#invoiceAddress").val()==""){
				setAddressInfo();
			}
			if($("#invoiceSum").val()==""){
				setSum();
			}
			if($("#orderCode").val()==""){
				setOrderCode();
			}
			
		}
		var infoArray=new Array("您填写的所有内容都将被系统自动打印到发票上，所以请千万别填写和发票抬头无关的信息","请将发票地址，邮编，收货人一并写在此处，以便正确投递","输入此发票金额","输入此发票对应的订单编号");
		function setCompanyNameInfo(){
			return $("#companyName").val(infoArray[0]);
		}
		function setAddressInfo(){
			return $("#invoiceAddress").val(infoArray[1]);
		}
		function setSum(){
			return $("#invoiceSum").val(infoArray[2]);
		}
		function setOrderCode(){
			return $("#orderCode").val(infoArray[3]);
		}
		function isDeleteInfo(obj){
			for(var i=0;i<infoArray.length;i++){
				if(obj.value==infoArray[i]){
					$("#"+obj.id+"").val("");
					if(obj.id=="companyName"||obj.id=="invoiceAddress"){
						$("#"+obj.id+"").attr("style","color:black;width:50%");
					}
					if(obj.id=="invoiceSum"||obj.id=="orderCode"){
						$("#"+obj.id+"").attr("style","color:black;width:11%");
					}
					break;
				}
			}
		}
		function isAddInfo(obj){
			if(obj.value==""){
				if(obj.id=="companyName"){
					$("#"+obj.id+"").attr("style","color:#CDCDCD;width:50%");
					setCompanyNameInfo();
				}
				if(obj.id=="invoiceAddress"){
					$("#"+obj.id+"").attr("style","color:#CDCDCD;width:50%");
					setAddressInfo();
				}
				if(obj.id=="invoiceSum"){
					$("#"+obj.id+"").attr("style","color:#CDCDCD;width:11%");
					setSum();
				}
				if(obj.id=="orderCode"){
					$("#"+obj.id+"").attr("style","color:#CDCDCD;width:11%");
					setOrderCode();
				}
			}
		}
	
		function dealCompany(){
			if(getInvoiceTitle()==0){
				document.getElementById("trCompanyName").style.display="none";
			}
			if(getInvoiceTitle()==1){
				document.getElementById("trCompanyName").style.display="block";
			}
		}
		function saveInvoice(){
			if(validateMessage()!=""){
				alert(validateMessage());
				return false;
			}
			if(getInvoiceTitle()==0){
				$("#companyName").val("");
			}
			$("#invoiceform").submit();
		}
		function getInvoiceTitle(){
			return $('input[name="invoice.invoiceTitle"]:checked').val();
		}
		function validateMessage(){
			var message="";
			if(getInvoiceTitle()==1){
				if($("#companyName").val()==""||$("#companyName").val()==infoArray[0]){
					message+="当发票抬头选择单位时，单位名称为必填项！\n";
				}
			}
			if($("#invoiceAddress").val()==""||$("#invoiceAddress").val()==infoArray[1]){
				message+="发票地址为必填项！\n";
			}
			if($("#invoiceSum").val()==""||$("#invoiceSum").val()==infoArray[2]){
				message+="发票金额为必填项！\n";
			}
			if($("#orderCode").val()==""||$("#orderCode").val()==infoArray[3]){
				message+="订单编号为必填项！\n";
			}
			if($("#invoiceSum").val()<=0){
				message+="发票金额必须大于0！\n";
			}
			return message;
		}
	
		</script>
	</head>
	<body>
		<div>
			<form action="<%=contextPath%>/finance/invoice!addOrUpdateInvoice.action" method="post" name="invoiceform" id="invoiceform" enctype="multipart/form-data">
			<input type="hidden" name="invoice.invoiceId" id="invoiceId" value="<s:property value="invoice.invoiceId"/>"/>
				<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">新建发票</font>
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
											<font color="red">*</font>发票抬头：
										</td>
										<td class="lists_tleft">
											<input type=radio name="invoice.invoiceTitle" id="personal" onclick="dealCompany();" value=0 <s:if test="invoice==null||invoice.invoiceTitle==0">checked</s:if>/>个人
								   			<input type=radio name="invoice.invoiceTitle" id="unit" onclick="dealCompany()" value=1 <s:if test="invoice.invoiceTitle==1">checked</s:if>/>单位
										</td>
									</tr>
									<tr id="trCompanyName" style="display:none">
										<td class="lists_tright" width="10%">
											<font color="red">*</font>单位名称：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="invoice.companyName" id="companyName" value="<s:property value="invoice.companyName"/>" onfocus="isDeleteInfo(this);" onblur="isAddInfo(this)"  style="color:#CDCDCD;width:50%"></input>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>发票内容：
										</td>
										<td class="lists_tleft">
											<s:select name="invoice.invoiceContent" id="invoiceContent" list="invoiceContentList"
												listKey="dicContent" listValue="dicContent" 
												theme="simple" >
											</s:select>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>发票地址：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="invoice.invoiceAddress" id="invoiceAddress" value="<s:property value="invoice.invoiceAddress"/>" class="{required:true,minlength:1,maxlength:200}" onfocus="isDeleteInfo(this);" onblur="isAddInfo(this)" style="color:#CDCDCD;width:50%"></input>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>发票金额：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="invoice.invoiceSum" id="invoiceSum" value="<s:property value="invoice.invoiceSum"/>" class="{required:true,number:true,min:0,maxlength:20}" onfocus="isDeleteInfo(this);" onblur="isAddInfo(this)" style="color:#CDCDCD;width:11%"></input>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>订单编号：  
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="invoice.orderCode" id="orderCode" value="<s:property value="invoice.orderCode"/>" class="{required:true,minlength:1,maxlength:200}" onfocus="isDeleteInfo(this);" onblur="isAddInfo(this)" style="color:#CDCDCD;width:11%"></input>
										</td>
									</tr>	
									<tr>
										<td colspan="2">
											<input  type="button" onclick="saveInvoice()" value="确认开票"/>
										</td>
									</tr>
							</table>
						</td>
					</tr>	
			</form>
		</div>
	</body>
</html>
