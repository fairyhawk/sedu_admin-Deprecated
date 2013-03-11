<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>销售追呼统计</title>
    <link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<style type="text/css">
	.totel { width:2000px;}
	.if_left { width:2000px; float:left; padding:0; margin:0; }
	
	</style>
	<script type="text/javascript">
		function flush(){
			var groupId=$('#groupid').val();
			window.location='<%=contextPath%>/crm/salesStat!toSalesStat.action?queryStatCondition.currentPage=1&groupIds='+groupId;
		}
		
		function checkOutExcel(){
		    var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			if(startTime!=''&&endTime!=''){
			var diffValue = getDateTimeStamp(endTime) - getDateTimeStamp(startTime);
			if(diffValue < 0){
			 //非法操作
			 alert("结束时间要大于开始时间！");
			 return;
			 }
			 var dayNum=diffValue/(1000*60*60*24);
		
			 if(dayNum>=30){
			 	alert("日期间隔不能大于30天");
			 	return;
			 }
			 }else{
			 	alert("时间不能为空");
			 	return;
			 }
			var currentPage='<s:property value="queryStatCondition.currentPage"/>';
			document.forms.salesStat.action="<%=contextPath%>/crm/salesStat!salesStatExportExcelFile.action?queryStatCondition.currentPage="+currentPage;
			document.forms.salesStat.submit();
		}
		function selectX(){
			
			document.forms.salesStat.action="<%=contextPath %>/crm/salesStat!toAllSalesStat.action?queryStatCondition.currentPage=1";
			document.forms.salesStat.submit();
			
		}
		function getDateTimeStamp(dateStr){
 			return Date.parse(dateStr.replace(/-/gi,"/"));
	    }
		function search(){
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			if(startTime!=''&&endTime!=''){
			var diffValue = getDateTimeStamp(endTime) - getDateTimeStamp(startTime);
			if(diffValue < 0){
			 //非法操作
			 alert("结束时间要大于开始时间！");
			 return;
			 }
			 var dayNum=diffValue/(1000*60*60*24);
		
			 if(dayNum>=30){
			 	alert("日期间隔不能大于30天");
			 	return;
			 }
			 }else{
			 	alert("时间不能为空");
			 	return;
			 }
			document.forms.salesStat.action="<%=contextPath %>/crm/salesStat!toSalesStat.action?queryStatCondition.currentPage=1";
			document.forms.salesStat.submit();
		}
		
		function selected(groupId){
		if(groupId!=0){
			$.ajax({
									url : "<%=contextPath%>/crm/salesStat!getGroupUserInfo.action",  
									data : {
									"groupIds":groupId,
									"status":0},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									var content=result.returnMessage;
									$('#sysUserName').html(content);
									},
									error: function(){ 
										alert('error');  
									}
				  });
				  }
		}
		
		function clear_from(){
			$("#groupid").attr("value",0);
			$("#userId").attr("value", 0);
			$("#origin").attr("value",0);
			$("#consultStatus").attr("value",0);
			$("#startTime").attr("value","");
			$("#endTime").attr("value","");
		}
	</script>
  </head>
  
  <body>
  <table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">销售追呼统计</font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
					<div class="msg-yw">
		<form action='<%=contextPath %>/crm/salesStat!toSalesStat.action' name="salesStat" method="post">
		<table width="75%" border="0" cellpadding="0" cellspacing="1" class="lists">
		  <tr>
			<td width="8%" class="lists_tright">销售项目组:</td>
			<td width="15%" class="lists_tleft"><s:select list="groupList" name="queryStatCondition.groupId" listKey="groupId" listValue="groupName"  id="groupid"  onchange="selected(this.value);"
			headerKey="0" headerValue="请选择项目组" ></s:select></td>
			<td width="8%"  class="lists_tright">坐席:</td>
			<td width="10%" class="lists_tleft" id="sysUserName"><s:select name="queryStatCondition.userId" list="userList" listKey="userId" listValue="userName" headerKey="0"
										headerValue="请选择坐席" id="userId" ></s:select></td>
			<td width="7%" class="lists_tright">来源路径:</td>
			<td width="14%" class="lists_tleft"><s:select name="queryStatCondition.subjectId"  list="subjectList" listKey="subjectId" listValue="subjectName" headerKey="0"
										headerValue="请选择项目" >							</s:select></td>
			<td width="13%" class="lists_tright">机会来源:</td>
			<td width="25%" class="lists_tleft"><select name="queryStatCondition.origin" id="origin"/>
							<option value="0">-请选择机会来源-</option>
							<option value="1">自然注册</option>
							<option value="2">乐语在线</option>
							<option value="4">自然留言</option>
							<option value="6">CallIn</option>
			</select></td>
		  </tr>
		  <tr>
			<td class="lists_tright">咨询状态:</td>
			<td class="lists_tleft"><select name="queryStatCondition.consultStatus"  id="consultStatus"/>
							<option value="0">-请选择咨询状态-</option>
							<option value="1">未联系</option>
							<option value="2">首咨</option>
							<option value="3">常规回访</option>
							<option value="4">库存回访</option>
							 </select></td>
			<td class="lists_tright">开始时间:</td>
			<td class="lists_tright"><input type="text" readonly id="startTime" value='<s:property value="queryStatCondition.startTime"/>' name="queryStatCondition.startTime" onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" width="10"/></td>
			<td class="lists_tright">结束时间:</td>
			<td class="lists_tleft"><input type="text" name="queryStatCondition.endTime" value='<s:property value="queryStatCondition.endTime"/>' readonly id="endTime" onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" width="10"/></td>
			<td colspan="2"><input type="button" onclick="search();" value="查询" /> &nbsp;&nbsp;<input name="" type="button"  value="清空"  onclick="clear_from()"/>&nbsp;&nbsp;
			<!-- <input type="button" onclick="selectX()" value="查看详情" /> --></td>
			</tr>
		  <tr>
			<td colspan="8" style="text-align:center"><a href="#" onclick="checkOutExcel()" style="font-size:12px; color:#FF0000;"> 导出excel表</a></td>
			</tr>
		</table>
			</form>
		</div>
		<div class="totel">
		<div class="if_left">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
		<tr class="lists_infobg"><td width="160px;">项目</td><td width="250px;">销售坐席</td><td width="60px;">来源</td><td width="60px;">咨询状态</td><td width="60px;">订单流水</td><td width="120px;">货到付款激活量</td><td width="140px;">货到付款激活金额</td><td width="50px;">分配量</td><td width="50px;">拨打量</td><td width="50px;">接通量</td><td width="50px;">成交量</td><td width="55px;">成交总量</td><td width="70px;">当日取消量</td><td width="80px;">非当日取消量</td><td width="50px;">转化率</td>
		</tr>
		<s:set name="sum_sellCount" value="0"/>
		<s:set name="sum_callCount" value="0"/>
		<s:set name="sum_successCount" value="0"/>
		<s:set name="sum_payAddSendCount" value="0"/>
		<s:set name="sum_payCount" value="0"/>
		<s:set name="sum_sendCount" value="0"/>
		<s:set name="sum_totelPrice" value="0"/>
		<s:set name="sum_sendSuccessCount" value="0"/>
		<s:set name="sum_sendTotelPrice" value="0"/>
		<s:set name="sum_totelCancelCount" value="0"/>
		<s:set name="sum_cancelCount" value="0"/>
		<s:set name="sum_backCancelCount" value="0"/>
		<s:set name="sum_difCancelCount" value="0"/>
		<s:set name="sum_difBackCancelCount" value="0"/>
		<s:set name="percent" id="percent" value="0"/>
		
		
		<s:iterator value="page.pageResult" id="customer"
										status="status">
										
		<s:set name="sum_sellCount" value="#sum_sellCount+#customer.sellCount"/>
		<s:set name="sum_callCount" value="#sum_callCount+#customer.callCount"/>
		<s:set name="sum_successCount" value="#sum_successCount+#customer.successCount"/>
		<s:set name="sum_payAddSendCount" value="#sum_payAddSendCount+#customer.sendCount+#customer.payCount"/>
		<s:set name="sum_payCount" value="#sum_payCount+#customer.payCount"/>
		<s:set name="sum_sendCount" value="#sum_sendCount+#customer.sendCount"/>
		<s:set name="percent" value="(#customer.sendCount+#customer.payCount)*100.0/#customer.sellCount"/>
		<s:set name="sum_totelPrice" value="#sum_totelPrice+#customer.intBankTotelPrice"/>
		<s:set name="sum_sendSuccessCount" value="#sum_sendSuccessCount+#customer.sendSuccessCount"/>
		<s:set name="sum_sendTotelPrice" value="#sum_sendTotelPrice+#customer.sendTotelPrice"/>
		<s:set name="sum_totelCancelCount" value="#sum_totelCancelCount+#customer.cancelCount+#customer.backCancelCount"/>
		<s:set name="sum_cancelCount" value="#sum_cancelCount+#customer.cancelCount"/>
		<s:set name="sum_backCancelCount" value="#sum_backCancelCount+#customer.backCancelCount"/>
		<s:set name="sum_difCancelCount" value="#sum_difCancelCount+#customer.difCancelCount"/>
		<s:set name="sum_difBackCancelCount" value="#sum_difBackCancelCount+#customer.difBackCancelCount"/>
		
		<tr>
			<td><s:property value="#customer.groupName"/></td>
			<td>
				<s:if test="#customer.userName==null">未指派</s:if>
				<s:else><s:property value="#customer.userName"/></s:else>
			
			</td>
			<td><s:if test="#customer.origin==0">--</s:if>
				<s:if test="#customer.origin==1">自然注册</s:if>
				<s:if test="#customer.origin==2">乐语在线</s:if>
				<s:if test="#customer.origin==4">自然留言</s:if>
				<s:if test="#customer.origin==6">CallIn</s:if>
			</td>
			<td><s:if test="#customer.consultStatus==0">--</s:if>
				<s:if test="#customer.consultStatus==1">未联系</s:if>
				<s:if test="#customer.consultStatus==2">首咨</s:if>
				<s:if test="#customer.consultStatus==3">常规回访</s:if>
				<s:if test="#customer.consultStatus==4">库存回访</s:if>
			</td>
			<td><script type="text/javascript">
				var totelPrice='<s:property value="#customer.intBankTotelPrice"/>';
				var index=totelPrice.indexOf('.');
				document.write(totelPrice.substring(0,index));
			</script></td>
			<td><s:property value="#customer.sendSuccessCount"/></td>
			<td><script type="text/javascript">
				var sendTotelPrice='<s:property value="#customer.sendTotelPrice"/>';
				var index=sendTotelPrice.indexOf('.');
				document.write(sendTotelPrice.substring(0,index));
			</script></td>
			<td><s:property value="#customer.sellCount"/></td>
			<td><s:property value="#customer.callCount"/></td>
			<td><s:property value="#customer.successCount"/></td>
			<td><s:property value="#customer.payCount"/>+<s:property value="#customer.sendCount"/></td>
			<td><s:property value="#customer.payCount+#customer.sendCount"/></td>
			<td><font color="#0033FF"><s:property value="#customer.cancelCount+#customer.backCancelCount"/>|<s:property value="#customer.cancelCount"/>+<s:property value="#customer.backCancelCount"/></font></td>
			<td><font color="#0033FF"><s:property value="#customer.difCancelCount+#customer.difBackCancelCount"/>|<s:property value="#customer.difCancelCount"/>+<s:property value="#customer.difBackCancelCount"/></font></td>
			<td><script type="text/javascript">
			var percent='<s:property value="#percent"/>';
				var patrn=/^(\d*\.)?\d+$/;
					if (patrn.exec(percent)){ 
						var index=percent.indexOf('.');
						document.write(percent.substring(0,index));
					}else{
						  document.write('0');
						 }
			</script>&#37;</td>
			</tr>
		</s:iterator>
		<tr>
			<td>合计</td>
			<td></td><td></td><td></td>
			<td><script type="text/javascript">
				var totelPrice='<s:property value="#sum_totelPrice"/>';
				totelPrice=Math.round((Math.floor(totelPrice*1000)/10))/100*1.0;
				totelPrice=totelPrice.toString();
				var index=totelPrice.indexOf('.');
				if(index==-1){
					document.write(totelPrice);
				}else{
					document.write(totelPrice.substring(0,index));
				}
			</script></td>
			<td><s:property value="#sum_sendSuccessCount"/></td>
			<td><script type="text/javascript">
				var totelPrice='<s:property value="#sum_sendTotelPrice"/>';
				totelPrice=Math.round((Math.floor(totelPrice*1000)/10))/100*1.0;
				totelPrice=totelPrice.toString();
				var index=totelPrice.indexOf('.');
				if(index==-1){
					document.write(totelPrice);
				}else{
					document.write(totelPrice.substring(0,index));
				}
			</script></td>
			<td><s:property value="#sum_sellCount"/></td>
			<td><s:property value="#sum_callCount"/></td>
			<td><s:property value="#sum_successCount"/></td>
			<td><s:property value="#sum_payCount"/>&#43;<s:property value="#sum_sendCount"/></td>
			<td><s:property value="#sum_payAddSendCount"/></td>
			<td><font color="#0033FF"><s:property value="#sum_totelCancelCount"/>&#124;<s:property value="#sum_cancelCount"/>&#43;<s:property value="#sum_backCancelCount"/></font></td>
			<td><font color="#0033FF"><s:property value="#sum_difCancelCount+#sum_difBackCancelCount"/>&#124;<s:property value="#sum_difCancelCount"/>&#43;<s:property value="#sum_difBackCancelCount"/></font></td>
			<td></td>
		</tr>
	</table>
	</div>
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
				 <script type="text/javascript">
	    var origin=<s:property value="queryStatCondition.origin"/>;
	   $("#origin").val(origin);
	   var consultStatus=<s:property value="queryStatCondition.consultStatus"/>;
	   $("#consultStatus").val(consultStatus);
	    </script>
  </body>
</html>
