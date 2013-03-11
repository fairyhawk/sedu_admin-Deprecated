<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>流水列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/util.js"></script>
<script language="JavaScript">
  function search(ObjectForm){
    var startTime=document.getElementById("startTime").value;
    var endTime=document.getElementById("endTime").value;
    if(startTime!=null&&endTime!=null){
	    if(startTime>endTime){
	       alert("结束时间要大于开始时间!");
	       return false;
	    }else{
		  	ObjectForm.action="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action";
			ObjectForm.submit();
	  	}
    }
  }
  function cancel(){
	  document.getElementById("startTime").value="";
	  document.getElementById("endTime").value="";
	  document.getElementById("cashRecord.contractId").value="";
	  document.getElementById("cashRecord.type").value="";
	  document.getElementById("mail").value="";
	  $("#op1").attr("selected","selected");
	  $("#op0").attr("selected","selected");
  }
  
  
  	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
	}
	function changeStartHH(hourStr) {
		$.each($("#startHH option"),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
		}
	function changeEndHH(hourStr) {
		$.each($("#endHH option"),function(){
			if(this.value == hourStr) {
				this.selected = true;
			}
		})
	}
</script>
</head>
<body onload="changeData()">
<div>
	<form name="f1"  method="post" action="books!getBooksList.action">
	<s:hidden name="queryCashRecordCondition.currentPage" value="1"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">流水列表</font>

			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>

		<tr>
			<td width="12" class="lists_bor">
			</td>
			
			<td>
			<div class="msg-cw">
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td>编号</td>
						<td>订单编号</td>
						<td>用户email</td>
						<td>流水类型</td>
						<td>创建时间</td>
						<td>流水说明</td>
						<td>流水金额</td>
					</tr>
					<s:if test="page.pageResult.size()!=0">
						<s:iterator value="page.pageResult" id="cashrecord" status="status">
							<tr>
								<td>
								<s:property value="(page.currentPage-1)*page.pageSize+#status.count" />
								</td>
								<td>
								<s:property value="#cashrecord.contractId" />
								</td>
								<td>							
								    	<a href="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email=<s:property value="#cashrecord.customer.email"/>">
								    	  <s:property value="#cashrecord.customer.email"/>
								    	</a>
								</td>
								<td>
									<s:if test="#cashrecord.type==1">
									前台购买
									</s:if>
									<s:if test="#cashrecord.type==2">
									后台赠送
									</s:if>
									<s:if test="#cashrecord.type==3">
									后台修复或赠送
									</s:if>
								</td>
								<td>
									<s:date name="#cashrecord.createTime"  format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<s:property value="#cashrecord.crInfo" />
								</td>
								<td>
									<s:property value="#cashrecord.cashRecordPrice" />
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
					<tr><td align="center" colspan="6">没有流水!</td></tr>
					</s:else>
			</table>
			</div>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>
