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
	.if_left { width:1500px; float:left; padding:0; margin:0; }
	.if_right { width:500px; float:left; overflow-x:auto;overflow-y:hidden; padding:0; margin:0;  }
	</style>
	<script type="text/javascript">
		function selectX(){
			
			document.forms.salesStat.action="<%=contextPath %>/crm/salesStat!toUndesignedInfo.action?queryStatCondition.currentPage=1";
			document.forms.salesStat.submit();
			
		}
		function clear_from(){
			$("#origin").attr("value",0);
			$("#consultStatus").attr("value",0);
			$("#startTime").attr("value","");
			$("#endTime").attr("value","");
			$("#subjectid").attr("value",0);
		}
	</script>
  </head>
  
  <body>
  <table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif"/>					</td>
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
		<form action='<%=contextPath %>/crm/salesStat!toAllUndesignedInfo.action' name="salesStat" method="post">
		  <div class="msg-yw">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="lists">
		  <tr>
			<td width="11%" style="text-align:right">来源路径:</td>
			<td width="22%" style="text-align:left"><s:select name="queryStatCondition.subjectId"  list="subjectList" listKey="subjectId" listValue="subjectName" headerKey="0" id="subjectid"
										headerValue="请选择项目" >							</s:select></td>
			<td width="11%" style="text-align:right">机会来源:</td>
			<td width="22%" style="text-align:left"><select name="queryStatCondition.origin" id="origin">
							<option value="0">-请选择机会来源-</option>
							<option value="1">自然注册</option>
							<option value="2">乐语在线</option>
							<option value="4">自然留言</option>
							<option value="6">CallIn</option>
			</select></td>
			<td width="11%" style="text-align:right">咨询状态:</td>
			<td width="23%" style="text-align:left">
			<select name="queryStatCondition.consultStatus"  id="consultStatus">
							<option value="0">-请选择咨询状态-</option>
							<option value="1">未联系</option>
							<option value="2">首咨</option>
							<option value="3">常规回访</option>
							<option value="4">库存回访</option>
		   </select></td>
		  </tr>
		  <tr>
			<td style="text-align:right">开始时间:</td>
			<td style="text-align:left"><input type="text" readonly id="startTime" value='<s:property value="queryStatCondition.startTime"/>' name="queryStatCondition.startTime" onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" width="10"/></td>
			<td style="text-align:right">结束时间:</td>
			<td style="text-align:left"><input type="text" name="queryStatCondition.endTime" value='<s:property value="queryStatCondition.endTime"/>' readonly id="endTime" onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" width="10"/></td>
			<td colspan="2"><input type="submit"  value="查询" />&nbsp;&nbsp;<input name="" type="button"  value="清空"  onclick="clear_from()"/>&nbsp;&nbsp;
			<!-- <input type="button" onclick="selectX()" value="简单查看" /> --></td>
			
			</tr>
		</table>
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
		
		
		<s:iterator value="salesStatList" id="customer" status="status">
										
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
			<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
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
			<div class="if_right">
		    <table width="100%" cellspacing="1" cellpadding="0" border="0" class="lists_info" id="simStat">
		    <tr class="lists_infobg">
		    <td>00:00</td><td>01:00</td><td>02:00</td><td>03:00</td><td>04:00</td><td>05:00</td><td>06:00</td><td>07:00</td><td>08:00</td><td>09:00</td>
		<td>10:00</td><td>11:00</td><td>12:00</td>
		<td>13:00</td><td>14:00</td><td>15:00</td><td>16:00</td><td>17:00</td><td>18:00</td><td>19:00</td><td>20:00</td><td>21:00</td><td>22:00</td><td>23:00</td></tr>
		    <s:iterator value="salesStats" id="simStats" status="stat">
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
				<td><s:property value="#simStats[16][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[16][1]"/></a></td>
				<td><s:property value="#simStats[17][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[17][1]"/></a></td>
				<td><s:property value="#simStats[18][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[18][1]"/></a></td>
				<td><s:property value="#simStats[19][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[19][1]"/></a></td>
				<td><s:property value="#simStats[20][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[20][1]"/></a></td>
				<td><s:property value="#simStats[21][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[21][1]"/></a></td>
				<td><s:property value="#simStats[22][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[22][1]"/></a></td>
				<td><s:property value="#simStats[23][0]"/><a style="color:#FF0000"  href="#"><s:property value="#simStats[23][1]"/></a></td>
				</tr>
			</s:iterator>
			<tr><td colspan="24"></td></tr>
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
						</td>
						<td>
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>
  </body>
</html>
