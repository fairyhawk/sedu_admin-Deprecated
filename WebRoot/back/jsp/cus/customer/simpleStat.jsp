<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>My JSP 'simpleStat.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
	<script src="<%=contextPath%>/back/script/WebCalendar.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<style type="text/css">
	.totel { width:1400px;}
	.if_left { width:1000px; float:left; padding:0; margin:0; }
	.if_right { width:350px; float:left; overflow-x:auto;overflow-y:hidden; padding:0; margin:0;  }
	</style>
	<script type="text/javascript">
		function flush(){
		var groupId=$('#groupid').val();
			window.location='<%=contextPath%>/cus/cusOrder!simpleStat.action?querySellrecordCondition.currentPage=1&groupIds='+groupId;
		}
		
		function checkOutExcel(){
			var groupId=$('#groupid').val();
			document.simpleStat.action="<%=contextPath%>/cus/cusOrder!simStatExportExcelFile.action?groupIds="+groupId;
			document.simpleStat.submit();
		}
		
		function selected(groupId){
		if(groupId!=0){
			$.ajax({
									url : "<%=contextPath%>/cus/cusOrder!getGroupUserInfo.action",  
									data : {
									"groupIds":groupId,
									"status":0},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									var content='姓名:'+result.returnMessage;
									$('#sysUserName').html(content);
									},
									error: function(){ 
										alert('error');  
									}
				  });
				  }
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
		<form action='<%=contextPath %>/cus/cusOrder!simpleStat.action' name="simpleStat" method="post">
		<div class="msg-yw">
		<table border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
		<td>项目:<s:select list="groupList" name="querySellrecordCondition.groupId" listKey="groupId" listValue="groupName" headerKey="0"
								headerValue="请选择项目" id="groupid"  onchange="selected(this.value);"></s:select>&nbsp;&nbsp;</td>
		<td id="sysUserName">姓名:<s:select name="querySellrecordCondition.sysUserId"  list="userList" listKey="userId" listValue="userName" headerKey="0"
								headerValue="请选择姓名" >							</s:select>&nbsp;&nbsp;</td>
		<td>场景:<select name="querySellrecordCondition.scene">
					<option value=0>请选择场景</option>
					<option value=1>在线</option>
					<option value=2>订单</option>
					<option value=3>注册</option>
				</select>&nbsp;&nbsp;
		</td>
		<td>
		开始时间:
		<input type="text" readonly id="startTime" value='<s:property value="querySellrecordCondition.startTime"/>' name="querySellrecordCondition.startTime" onclick="SelectDate(this,'yyyy-MM-dd hh时')" width="10"/>&nbsp;&nbsp;
		</td>
		<td>结束时间:
		<input type="text" name="querySellrecordCondition.endTime" value='<s:property value="querySellrecordCondition.endTime"/>' readonly id="endTime" onclick="SelectDate(this,'yyyy-MM-dd hh时')" width="10"/>&nbsp;&nbsp;
		</td>
		<td  style="text-align:left"><input type="submit" value="查询" />&nbsp;&nbsp;</td></tr><tr><td>按项目查询当天数据:</td><td>&nbsp;&nbsp;<input type="button" onclick="flush();" value="刷新"/></td><td><input type="button" value="导出excel表" onclick="checkOutExcel()"/></td></tr>
		</table>
		</div>
		<div class="totel">
		<div class="if_left">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
		<tr class="lists_infobg"><td width="60px;">项目</td><td width="250px;">销售坐席</td><td width="30px;">场景</td><td width="60px;">订单流水</td><td width="90px;">货到付款激活量</td><td width="100px;">货到付款激活金额</td><td width="50px;">分配量</td><td width="50px;">拨打量</td><td width="50px;">接通量</td><td width="50px;">成交量</td><td width="55px;">成交总量</td><td width="50px;">取消量</td><td width="50px;">转化率</td>
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
		<s:set name="sum_totelPrice" value="#sum_totelPrice+#customer.intBankTotelPrice+#customer.effSendTotelPrice"/>
		<s:set name="sum_sendSuccessCount" value="#sum_sendSuccessCount+#customer.sendSuccessCount"/>
		<s:set name="sum_sendTotelPrice" value="#sum_sendTotelPrice+#customer.sendTotelPrice"/>
		<s:set name="sum_totelCancelCount" value="#sum_totelCancelCount+#customer.cancelCount+#customer.backCancelCount"/>
		<s:set name="sum_cancelCount" value="#sum_cancelCount+#customer.cancelCount"/>
		<s:set name="sum_backCancelCount" value="#sum_backCancelCount+#customer.backCancelCount"/>
		
		<tr>
			<td><s:property value="#customer.groupName"/></td>
			<td><s:property value="#customer.sysUserName"/></td>
			<td><s:if test="#customer.scene==0">--</s:if>
				<s:if test="#customer.scene==1">在线</s:if>
				<s:if test="#customer.scene==2">订单</s:if>
				<s:if test="#customer.scene==3">注册</s:if>
			</td>
			<td><script type="text/javascript">
				var totelPrice='<s:property value="#customer.intBankTotelPrice+#customer.effSendTotelPrice"/>';
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
			<td><script type="text/javascript">
			var percent='<s:property value="#percent"/>';
				var patrn=/^(\d*\.)?\d+$/;
					if (patrn.exec(percent)){ 
						var index=percent.indexOf('.');
						document.write(percent.substring(0,index));
					}else{
						  document.write('0.0');
						 }
			</script>&#37;</td>
			</tr>
		</s:iterator>
		<tr>
			<td>合计</td>
			<td></td><td></td>
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
			<td></td>
		</tr>
	</table>
	</div>
			<div class="if_right">
		    <table width="100%" cellspacing="1" cellpadding="0" border="0" class="lists_info" id="simStat">
		    <tr class="lists_infobg">
		    <td>08:00</td><td>09:00</td>
		<td>10:00</td><td>11:00</td><td>12:00</td>
		<td>13:00</td><td>14:00</td><td>15:00</td><td>16:00</td><td>17:00</td><td>18:00</td><td>19:00</td><td>20:00</td><td>21:00</td><td>22:00</td><td>23:00</td></tr>
		    <s:iterator value="simStats" id="simStats" status="stat">
				<tr>
				<td><s:property value="#simStats[0][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[0][1]"/></a></td>
				<td><s:property value="#simStats[1][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[1][1]"/></a></td>
				<td><s:property value="#simStats[2][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[2][1]"/></a></td>
				<td><s:property value="#simStats[3][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[3][1]"/></a></td>
				<td><s:property value="#simStats[4][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[4][1]"/></a></td>
				<td><s:property value="#simStats[5][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[5][1]"/></a></td>
				<td><s:property value="#simStats[6][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[6][1]"/></a></td>
				<td><s:property value="#simStats[7][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[7][1]"/></a></td>
				<td><s:property value="#simStats[8][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[8][1]"/></a></td>
				<td><s:property value="#simStats[9][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[9][1]"/></a></td>
				<td><s:property value="#simStats[10][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[10][1]"/></a></td>
				<td><s:property value="#simStats[11][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[11][1]"/></a></td>
				<td><s:property value="#simStats[12][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[12][1]"/></a></td>
				<td><s:property value="#simStats[13][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[13][1]"/></a></td>
				<td><s:property value="#simStats[14][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[14][1]"/></a></td>
				<td><s:property value="#simStats[15][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[15][1]"/></a></td>
				</tr>
			</s:iterator>
		    
		   
			<tr><td colspan="16"></td></tr>
			</table>
			</div>
			</div>
	</form>
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
  </body>
</html>
