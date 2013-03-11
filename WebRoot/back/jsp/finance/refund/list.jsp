<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>流水列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery.blockUI.js"></script>
<script language="JavaScript">
	function createDateTwo(){
		var startTime=document.getElementById("createTimeBegin").value;
	    var endTime=document.getElementById("createTimeEnd").value;
	    if(startTime!='' && startTime>endTime){
		       alert("创建结束时间要大于开始时间!");
		       document.getElementById("createTimeEnd").value='';
		      return false;
	    }
	}
	
	function createDateOne(){
		var startTime=document.getElementById("createTimeBegin").value;
	    var endTime=document.getElementById("createTimeEnd").value;
	    if(endTime!='' && startTime>endTime){
		       alert("创建结束时间要大于开始时间!");
		       document.getElementById("createTimeBegin").value='';
		      return false;
	    }
	}
	
	function createDateTwochk(){
		var startTime=document.getElementById("starttimechk").value;
	    var endTime=document.getElementById("endtimechk").value;
	    if(startTime!='' && startTime>endTime){
		       alert("创建结束时间要大于开始时间!");
		       document.getElementById("starttimechk").value='';
		      return false;
	    }
	}
	
	function createDateOnechk(){
		var startTime=document.getElementById("starttimechk").value;
	    var endTime=document.getElementById("endtimechk").value;
	    if(endTime!='' && startTime>endTime){
		       alert("创建结束时间要大于开始时间!");
		       document.getElementById("starttimechk").value='';
		      return false;
	    }
	}
	
	function fKuanTwo(){
		 var startTime=document.getElementById("payTimeBegin").value;
	    var endTime=document.getElementById("payTimeEnd").value;
	    if(startTime!='' && startTime > endTime){
		       alert("付款结束时间要大于开始时间!");
		       document.getElementById("payTimeEnd").value='';
		       return false;
	    }
	}
	
	function fKuanOne(){
		 var startTime=document.getElementById("payTimeBegin").value;
	    var endTime=document.getElementById("payTimeEnd").value;
	    if(endTime!='' && startTime > endTime){
		       alert("付款结束时间要大于开始时间!");
		       document.getElementById("payTimeBegin").value='';
		       return false;
	    }
	}

	  function search(){
	  	$("#f1").submit(); 	
	  }
  
	  function cancel(){
			$("#f1")[0].reset();
	  }
	  
	 function openWin(ctId) {
		 window.open ("/finance/backContract!getContractInfo.action?contract.id="+ctId, "newwindow", "height=600, width=800, toolbar=no, menubar=yes, scrollbars=yes, resizable=no, location=no, status=no");
	} 
	
	/**商品下拉联动**/
	function getSellWayBySubId(subjectId){
		$(this).ready(function(){
		$.ajax({
			url :  "<%=contextPath%>/finance/backCashRecord!getSellWaysBySubId.action",
			data : {
				"subjectId" : subjectId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				$("#shops").html("<option value='0'>-请选择商品-</option>");
				if(result == null || result.entity == null) {
					return;
				}
				var sellWayList = result.entity;
				var html = '';
				for(var i=0; i<sellWayList.length; i++) {
					html += "<option value='" + sellWayList[i].sellId + "'>" +sellWayList[i].sellName + "</option>";
				}
				$("#shops").append(html);
			},
			error : function(error) {
				$("#shops").html("<option value='0'>-请选择商品-</option>");
			}
		});
		})
	}

	function checkOutExcel()
	{
	     	document.f1.action="<%=contextPath%>/finance/refd!exportCSV.action";
			document.f1.submit();
	}
</script>
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
        <td class="lists_top"><font class="lists_fleft">退费列表</font></td>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
      </tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<form name="f1"  id="f1" method="post" action="finance/refd!getRPage.action">
			<input type="hidden" name="refundCondition.sellid" id="shopsname"/>
			<input type="hidden" name="contractpackId" id="shopsId"/>
	        <s:hidden name="queryCashRecordCondition.currentPage" value="1"/>
	        <div class="msg-cw">
			<table width="70%" border="0" cellspacing="1" cellpadding="0" class="lists" align="center">
	  		<tr>
			<td>订单编号：&nbsp;<input type="text" id="shopName" value="${refundCondition.contractno}" name="refundCondition.contractno"/></td>
			<td>申请时间 ：<input type="text" name="refundCondition.starttime" readonly="readonly" id="createTimeBegin" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
										onchange="createDateOne()"	 value=""/>
						 -<input type="text" name="refundCondition.endtime" readonly="readonly" id="createTimeEnd" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
										onchange="createDateTwo()"	 value=""/></td>
			</tr>
			<tr>
			<td>用户Email：<input type="text" name="refundCondition.email" id="cemail"  value="${refundCondition.email}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>退费时间 ：<input type="text" name="refundCondition.starttimechk" readonly="readonly" id="starttimechk" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
										onchange="createDateOnechk()"	 value="" />-<input type="text" name="refundCondition.endtimechk" readonly="readonly" id="endtimechk" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
										onchange="createDateTwochk()"	 value=""/>
			</td>
			</tr>
			<tr>
			<td>退费账号 ：<input type="text" name="refundCondition.bankcode" id="bankcode" value="${refundCondition.bankcode}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			
			<td>所属项目 ：<s:select name="contractsubjectId" id="subjectId" onchange="getSellWayBySubId(this.value)"
									list="subjectList" listKey="subjectId"
									listValue="subjectName" headerKey="0" headerValue="--请选择专业--" theme="simple">
						 </s:select>
			 			 <select style="width:153px" id="shops" name="refundCondition.packid"><option value="0">-请选择商品-</option></select>
			</td>
			</tr>
			<tr>
			<td>商品名称 ：<input type="text" name="refundCondition.sellname" id="sellname" value="${refundCondition.sellname}"/>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>退费状态 ：<select name="refundCondition.status" id="status">
							 <option value="-1">-请选择-</option>
							 <option value="0">未退费</option>
							 <option value="1">已退费</option>
							 <option value="2">已撤销</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
			<td colspan="2" align="center">
				<img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:search();">查询</a>&nbsp;
				<img src="<%=contextPath%>/back/images/del_a.gif"/><a href="javascript:cancel();">清空</a>
				<img src="<%=contextPath%>/back/images/down16_16.gif" /><a href="javascript:checkOutExcel()">导出excel</a>
			 </td>
			</tr>
			</table>
			</div>
			</form>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>

		<tr>
			<td width="12" class="lists_bor">
			</td>
			
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						 <td>编号</td> 
						<td>退费商品名称</td>
						<td>成交价格</td>
						<td>退费金额</td>
						<td>订单编号</td>
						<td>用户Email</td>
						<td>付款时间</td>
						<td>申请时间</td>
						<td>支付状态</td>
						<td>退款时间</td>
						<td>取消时间</td>
						<td>退费状态</td>
						<td>操作</td>
					</tr>
					<s:if test="page.pageResult.size()!=0">
						<s:iterator value="page.pageResult" id="refund" status="status">
							<tr>
								 <td>
									<s:property value="(page.currentPage-1)*page.pageSize+#status.count" />
								</td> 
								<td>
									<s:property value="#refund.sellname"/>
								</td>
								<td>
									<s:property value="#refund.dealprice" />
								</td>
								<td>
								 	<s:property value="#refund.price" />
								</td>
								<td>
									<a href="javascript:openWin(<s:property value="#refund.ctid"/>);">
									<s:property value="#refund.contractno" /></a>
								</td>
								<td>
									<s:property value="#refund.useremail" />
								</td>
								<td>
									<s:date name="#refund.paytime"  format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>							
								   <s:date name="#refund.createtime"  format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									 <s:if test="#refund.fstatus==0">未支付</s:if>
							         <s:if test="#refund.fstatus==1">已支付</s:if>
							         <s:if test="#refund.fstatus==2">取消</s:if>
							         <s:if test="#refund.fstatus==3">退费</s:if>
								</td>
								<td>
					                <s:date name="#refund.refundtime"  format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
							        <s:date name="#refund.canceltime"  format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								 <td>
					                <s:if test="#refund.status==0">未退费</s:if>
							         <s:if test="#refund.status==1">已退费</s:if>
							         <s:if test="#refund.status==2">已撤销</s:if>
								</td>
								<td><s:if test="#refund.status==0">
										<a href="<%=contextPath%>/finance/refd!view.action?refundCondition.id=<s:property value="#refund.id" />">详情</a>
										<a href="<%=contextPath%>/finance/refd!toModify.action?refundCondition.id=<s:property value="#refund.id" />"/>修改</a>
										<a href="<%=contextPath%>/finance/refd!toConfirm.action?refundCondition.id=<s:property value="#refund.id" />"/>确认</a>
										<a href="<%=contextPath%>/finance/refd!toCancel.action?refundCondition.id=<s:property value="#refund.id" />"/>取消</a>
									</s:if>
									<s:if test="#refund.status==1">
										<a href="<%=contextPath%>/finance/refd!view.action?refundCondition.id=<s:property value="#refund.id" />">详情</a>
									</s:if>
									<s:if test="#refund.status==2">
										<a href="<%=contextPath%>/finance/refd!view.action?refundCondition.id=<s:property value="#refund.id" />">详情</a>
										<a href="<%=contextPath%>/finance/refd!toModify.action?refundCondition.id=<s:property value="#refund.id" />">重新申请</a>
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
					<tr><td align="center" colspan="16">没有退费记录!</td></tr>
					</s:else>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td class="td_wid_1">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>