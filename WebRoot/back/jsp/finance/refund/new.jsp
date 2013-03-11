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
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript">
	/*--选取支付宝时页面更改--*/	
	function changeType(){
		var value =  $("input[name=refund.iszfb][checked=true]").val();
		 if(value == "true"){
				$("#bankcode").val("");
				$("#bankname").val("");
				$("#bankregname").val("");
				$("#cusbankname").val("");
				$("#banknametr").hide();
				$("#bankregtr").hide();
				$("#cusbanknametd").html("支付宝注册姓名:&nbsp;");
				$("#bankcodetd").html("支付宝账号:&nbsp;");
			}else{
				$("#bankcode").val("");
				$("#bankname").val("");
				$("#bankregname").val("");
				$("#cusbankname").val("");
				$("#banknametr").show();
				$("#bankregtr").show();
				$("#cusbanknametd").html("开户人姓名:&nbsp;");
				$("#bankcodetd").html("退费账号:&nbsp;");
			}
	}
	
	/*--验证表单--*/
	function validateF(){
		var bankreg = $.trim($("#bankregname").val());
		var bankname = $.trim($("#bankname").val());
		var bankcode = $.trim($("#bankcode").val());
		var cusbankname = $.trim($("#cusbankname").val());
		var remark = $.trim($("#remark").val());
		var conname = $.trim($("#conname").val());
		var mobile = $.trim($("#mobile").val());
		var price = $.trim($("#price").val());
		var cashprice = $.trim($("#cashprice").val());
		var check= $("input[name=refund.iszfb][checked=true]").val();
		var value =  $("#isfull").val();
		
		if(cashprice <= 0){
			alert("此商品为赠送商品不可退费");
			return;
		}
		
		if(price.length <= 0){
			alert("退款金额不可为空");
			return;
		}else if (isNaN(price)){
			alert("退款金额必须为数字");
			return;
		}else if(parseFloat(price) <= 0){
			alert("退款金额不正确");
			return;
		}
		
		if(value == "false"){
			if(parseFloat(cashprice) <= parseFloat(price)){
				alert("退款金额不可大于等于成交价格");
				return;
			}
		}
		
		/*
		if(check == "false"){
			if(bankname.length <= 0){
				alert("请输入银行");
				return;
			}
			if(bankreg.length <= 0){
				alert("请输入开户行名称");
				return;
			}
			if(cusbankname.length <= 0){
				alert("请输入开户人姓名");
				return;
			}
			if(bankcode.length <= 0){
				alert("请输入退费账号");
				return;
			}else if(!isNumber(bankcode)){
				alert("退费账号必须为数字");
				return;
			}
			if(conname.length <= 0){
				alert("请输入联系人姓名");
				return;
			}
			if(mobile.length <= 0){
				alert("请输入联系人电话");
				return;
			}
			if(!checkMobile(mobile)){
				alert("请输入正确电话");
				return;
			}
			if(remark.length <= 0){
				alert("请输入备注信息");
				return;
			}
			$("#f1").submit();
		}else{
			if(cusbankname.length <= 0){
				alert("请输入支付宝姓名");
				return;
			}
			if(bankcode.length <= 0){
				alert("请输入支付宝帐号");
				return;
			}
			if(conname.length <= 0){
				alert("请输入联系人姓名");
				return;
			}
			if(mobile.length <= 0){
				alert("请输入联系人电话");
				return;
			}
			if(!checkMobile(mobile)){
				alert("请输入正确电话");
				return;
			}
			if(remark.length <= 0){
				alert("请输入备注信息");
				return;
			}
			
		}
		*/
		$("#f1").submit();
	}
	
	/*--异步查询课程与授卖方式--*/
	function getSellWayBySubjectId(sId){
		if(sId != -1){
		document.getElementById('subjectId').value = sId;
		}
		$.ajax({
		url : "/finance/backContract!getSellWayBySubjectId.action",
		data : {subjectId : sId}, // 参数
		type : "post",
		cache : false,
		dataType : "json", //返回json数据
		error: function(){
			alert('error');
		},
		success:onchangecallback
		});
		}
	
	/*---退费方式选择----*/
	function refundchange(){
		var price = $("#isfull").val();
		if(price == "true"){
			$("#price").val($("#cashprice").val());
			$("#price").attr("readonly",true);
		}else{
			$("#price").attr("readonly",false);
			$("#price").val("");
		}
	}
	/*-正则电话-*/
	function checkMobile(s){ 
		var regu =/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; 
		var re = new RegExp(regu); 
		if (re.test(s)) { 
		return true; 
		}else{ 
		return false; 
		} 
	}
	/*-正则数字-*/
	function isNumber(s){ 
	var regu = "^[0-9]+$"; 
	var re = new RegExp(regu); 
	if (s.search(re) != -1) { return true;} else { return false;} 
	} 
	/*-正则邮箱-*/
	function checkEmail(strEmail) { 
		var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/; 
		if( emailReg.test(strEmail) ){ return true; }else{ return false; } 
	}
	
</script>
</head>
<body>
<div id="detailWin">
	<form action="finance/refd!addRef.action" method="post" id="f1">
	<input type="hidden" name="refund.cashid" value="${cashRecordDTO.id}"></input>
	<input type="hidden" name="refund.contractno" value="${cashRecordDTO.contractId}"></input>
	<input type="hidden" name="refund.cusid" value="${cashRecordDTO.cusId}"></input>
	<input type="hidden" name="refund.useremail" value="${cashRecordDTO.email}"></input>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
	 <tr>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
        <td class="lists_top"><font class="lists_fleft">新建退费</font></td>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
      </tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr height="30">
					<td class="lists_tright">
						商品名称:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="30" name="sell_name" value="${cashRecordDTO.packName}" readonly="true"/>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						所属订单编号:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="30" value="${cashRecordDTO.contractId}" readonly="true"/>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						商品成交价格:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="10" value="${cashRecordDTO.cashRecordPrice}" readonly="true" id="cashprice"/>元
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						退款方式:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<select name="refund.isfull" onchange="refundchange();" id="isfull">
							<option value="true">全额退款</option>
							<option value="false">部分退款</option>
						</select>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						退费金额:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="10" name="refund.price" value="${cashRecordDTO.cashRecordPrice}" readonly="true" id="price"/>元
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						退费时间:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="30" name="refund.createtime" id="createtime" readonly="true" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"/>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						记录人:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="10" name="refund.operusername"   id="operusername"/>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						退费途径:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;银行<input type="radio" name="refund.iszfb" value="false"  checked onclick="changeType();"/>
						&nbsp;支付宝<input type="radio" name="refund.iszfb" value="true"  onclick="changeType();"/>
					</td>
				</tr>
				<tr height="30" id="banknametr">
					<td class="lists_tright">
						退费银行:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" name="refund.bankname" id="bankname"/>
					</td>
				</tr>
				<tr height="30" id="bankregtr">
					<td class="lists_tright">
						开户行名称:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="30" name="refund.bankregname" value="" id="bankregname"/>
					</td>
				</tr>
				<tr height="30" id="cusbanknametr">
					<td id="cusbanknametd" class="lists_tright">
						开户人姓名:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="30" name="refund.cusbankname" value="" id="cusbankname"/>
					</td>
				</tr>
				<tr height="30" id="bankcodetr">
					<td id="bankcodetd" class="lists_tright">
						退费账号:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="30" name="refund.bankcode" value="" id="bankcode"/>
					</td>
				</tr>
				<tr height="30" id="zfbnotr">
					<td id="bankcodetd" class="lists_tright">
						联系人姓名:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="30" name="refund.conname" value="" id="conname"/>
					</td>
				</tr>
				<tr height="30" id="zfbnotr">
					<td id="bankcodetd" class="lists_tright">
						联系人电话:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<input type="text" size="30" name="refund.usermobile" value="" id="mobile"/>
					</td>
				</tr>
				<tr height="50">
					<td class="lists_tright">
						退费原因:&nbsp;
					</td>
					<td class="lists_tleft">
						&nbsp;<textarea rows="3" cols="40" name="refund.remark" id="remark" style="resize: none; font-size: 12px;"></textarea>
					</td>
				</tr>
				 <td colspan="2" align="center"><input type="button" onclick="validateF();" value="提交"/> &nbsp;&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="返回"/> </td></tr> 
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