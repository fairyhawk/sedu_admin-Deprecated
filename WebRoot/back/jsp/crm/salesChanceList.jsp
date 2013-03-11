<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>销售机会管理(个人)</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	<!--
		$(function(){
			var hh = "";
			for(var i=1; i<24; i++){
				if(i<10){
					hh = hh + "<option value='0"+i+":00:00'>0"+i+":00:00</option>"
				}else if(i<23){
					hh = hh + "<option value='"+i+":00:00'>"+i+":00:00</option>"
				}else{
					hh = hh + "<option value='23:59:59'>23:59:59</option>"
				}
			}
			$("#start_hh").append(hh);
			$("#end_hh").append(hh);
			$("#end_hh").attr("value","23:59:59");
			var startHH = "<s:property value="queryChanceCondition.startTime.substring(11)"/>";
			var endHH = "<s:property value="queryChanceCondition.endTime.substring(11)"/>";
			if(startHH!=''){
				$("#start_hh").attr("value",startHH);
			}
			if(endHH!=''){
				$("#end_hh").attr("value",endHH);
			}
		})
		
		function closes(){
			$('#infoWrap').fadeOut();
			$('#wrapback').fadeOut();
		}
		
		//单个销售机会领取
		function getChance(id) {
			url = "<%=contextPath%>/crm/crmChance!getOneChance.action";
			$.post(url, {ids: id}, function(data){
				if(data.jumpType==true){
					alert(data.returnMessage);
					newChance();
				}else{
					alert(data.returnMessage);
				}
			}, 'json');
		}
		
		function newChance(){
			var mobile=$('#phone').val();
			var patrn=/^[0-9]{1,20}$/; 
			if(mobile==''){
				alert("手机号必填")
				return;
			}else if(!patrn.exec(mobile)){
				alert("手机号必须是数字");
				$("#sortId").val(count);
				return;
			}
			if(mobile.length!=11){
				alert('手机号填写格式不正确');
				return;
			}
			
			$.ajax({
									url : "<%=contextPath%>/crm/crmChance!testMobile.action",  
									data : {
									"mobile":mobile
									},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
										var bo=result.jumpType;
										var ss=result.returnMessage;
										if(bo){
											alert(ss);
											window.location='<%=contextPath%>/crm/crmChance!toChanceAdd.action?mobile='+mobile;
										}else{
											$('#info').html(ss);
											$('#infoWrap').fadeIn();
											$('#wrapback').fadeIn();
										}
									},
									error: function(){ 
										alert('error');  
									}
				  });
		}
		
		function sorts(type){
			window.location="<%=contextPath%>/crm/crmChance!searchSalesChance.action?queryChanceCondition.currentPage=1&queryChanceCondition.orderType="+type;
		}
		function getDateTimeStamp(dateStr){
 			return Date.parse(dateStr.replace(/-/gi,"/"));
	    }
		function search(){
		
			var url = "<%=contextPath%>/crm/crmChance!searchSalesChance.action?queryChanceCondition.currentPage=1"
			var startTime = $("#startTime").val();
			if(startTime!=''){
				var start_hh = $("#start_hh").val();
				url = url + "&queryChanceCondition.startTime=" + startTime +" "+ start_hh;
			}
			
			var endTime = $("#endTime").val();
			if(endTime!=''){
				var end_hh = $("#end_hh").val();
				url = url + "&queryChanceCondition.endTime=" + endTime +" "+ end_hh;
			}
			
			var messtatus=document.getElementById('messtatus');
			if(messtatus.checked){
				url = url + "&queryChanceCondition.mesStatus=1";
			}
			
			var webName = $("#webName").val();
			if(webName!=''){
				url = url + "&queryChanceCondition.webName=" + webName;
			}
			
			var origin = $("#origin").val();
			if(origin!=''){
				url = url + "&queryChanceCondition.origin=" + origin;
			}
			
			var followStatus = $("#followStatus").val();
			if(followStatus!=''){
				url = url + "&queryChanceCondition.followStatus=" + followStatus;
			}

			var subjectId = $("#subjectId").val();
			if(subjectId!=''){
				url = url + "&queryChanceCondition.subjectId=" + subjectId;
			}
			
			var $mobile = $("#mobile").val();
			var mobile = $.trim($mobile);
			if(mobile!=''){
				url = url + "&queryChanceCondition.mobile=" + mobile;
			}
			
			var salesStatus = $("#salesStatus").val();
			if(salesStatus!=''){
				url = url + "&queryChanceCondition.salesStatus=" + salesStatus;
			}
			
			var $email = $("#email").val();
			var email = $.trim($email);
			if(email!=''){
				url = url + "&queryChanceCondition.email=" + email;
			}
			
			var consultStatus = $("#consultStatus").val();
			if(consultStatus!=''){
				url = url + "&queryChanceCondition.consultStatus=" + consultStatus;
			}
			
			var endCommStatus = $("#endCommStatus").val();
			if(endCommStatus!=''){
				url = url + "&queryChanceCondition.endCommStatus=" + endCommStatus;
			}

			var $chanceNum1 = $("#chanceNum1").val();
			var chanceNum1 = $.trim($chanceNum1);
			if(chanceNum1!=""){
				if(isNaN(chanceNum1)||chanceNum1<0){
					alert("咨询次数必须是正整数！");
					return ;
				}else{
					url = url + "&queryChanceCondition.chanceNum1=" + chanceNum1;
				}
			}

			var $chanceNum2 = $("#chanceNum2").val();
			var chanceNum2 = $.trim($chanceNum2);
			if(chanceNum2!=""){
				if(isNaN(chanceNum2)||chanceNum2<0){
					alert("咨询次数必须是正整数！");
					return ;
				}else{
					url = url + "&queryChanceCondition.chanceNum2=" + chanceNum2;
				}
			}
			
			window.location = url;
		}


		function clear_form(){
			$("#start_hh").attr("value","00:00:00");
			$("#end_hh").attr("value","23:59:59");
			$("#startTime").attr("value","");
			$("#endTime").attr("value","");
			$("#webName").attr("value","");
			$("#origin").attr("value","");
			$("#followStatus").attr("value","");
			$("#subjectId").attr("value","");	
			$("#mobile").attr("value","");
			$("#salesStatus").attr("value","");
			$("#email").attr("value","");
			$("#consultStatus").attr("value","");
			$("#endCommStatus").attr("value","");
			$("#chanceNum1").attr("value","");
			$("#chanceNum2").attr("value","");
			$('#messtatus').attr('checked','');
		}
		
		function checkOutExcel()
		{
		
			var currentPage='<s:property value="pageQuery.currentPage"/>';
			if(currentPage>0){
	     		window.location='<%=contextPath%>/crm/crmChance!exportExcelFile.action?pageQuery.currentPage=<s:property value="pageQuery.currentPage"/>';
	     	}else{
		     	var url = "${pageUrlParms}";
		     	var param = url.substring(url.indexOf("?"), url.length-1);
	     		window.location='<%=contextPath%>/crm/crmChance!exportExcelFile.action'+param;
	     	}
			$('#exportExcel').attr('disabled',true);
	     	window.setTimeout(exportEnable,5000);
	     	
	     	
		}
		function exportEnable(){
		 $('#exportExcel').attr('disabled',false);
		}
		
		function selected(groupId){
		if(groupId!=0){
			$.ajax({
									url : "<%=contextPath%>/crm/crmChance!getGroupUserInfo.action",  
									data : {
									"groupIds":groupId
									},  // 参数  
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
	//-->
	</script>
	
	
	<style >
		html, body { height: 100%; }
		#wrapback { min-height: 100%; position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5;width:100%;-moz-opacity:1;top:0;left:0;background:#000000;display:none;z-index:4;}
		* html #wrapback { height: 100%; }
		.fonts {font-size: 11pt;}
	</style>
</head>
<body>
<div style="width:1460px;">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">销售机会管理</font>
			</td>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
	<tr>
			<td class="lists_bor"></td>
			<td>
			<div class="msg-yw">
			<form action="<%=contextPath%>/dis/back!addSensWord.action" method="post" onsubmit="return check()"  >
				<input type="hidden" name="page.currentPage" value="1"/><!-- 添加后回到第一页 -->
				
				<table border="0" width="75%" cellpadding="0" cellspacing="1" class="lists">
					<tr>
						<td width="8%" style="text-align:right">创建开始时间：</td>
						<td width="24%" style="text-align:left">
							<input type="text" value="<s:property value="queryChanceCondition.startTime.substring(0,10)"/>" id="startTime" onclick="WdatePicker()"/>
							<select name="queryChanceCondition.start_hh" id="start_hh">
								<option value="00:00:00">00:00:00</option>
					  </select> </td>
						<td width="8%" style="text-align:right">创建结束时间：</td>
						<td width="24%" style="text-align:left">
							<input type="text" value="<s:property value="queryChanceCondition.endTime.substring(0,10)"/>" id="endTime" onclick="WdatePicker()"/> 
							<select name="queryChanceCondition.end_hh" id="end_hh">
								<option value="00:00:00">00:00:00</option>
					  </select> </td>
						<td width="7%" style="text-align:right">域名来源：</td>
						<td width="32%" style="text-align:left">
							<select name="queryChanceCondition.webName" id="webName">
							<option value="">请选择...</option>
							<option value="highso.cn" <s:property value="queryChanceCondition.webName=='highso.cn'?'selected=\"selected\"':''"/>>highso.cn</option>
							<option value="highso.org" <s:property value="queryChanceCondition.webName=='highso.org'?'selected=\"selected\"':''"/>>highso.org</option>
							<option value="highso.org.cn" <s:property value="queryChanceCondition.webName=='highso.org.cn'?'selected=\"selected\"':''"/>>highso.org.cn</option>
							<option value="highso.com.cn" <s:property value="queryChanceCondition.webName=='highso.net'?'selected=\"selected\"':''"/>>highso.com.cn</option>
							<option value="highso.net.cn" <s:property value="queryChanceCondition.webName=='highso.net.cn'?'selected=\"selected\"':''"/>>highso.net.cn</option></select>
					  </td>
					</tr>
					
					<tr>
						<td style="text-align:right">机会来源：</td>
						<td style="text-align:left">
							<select name="queryChanceCondition.origin" id="origin">
							<option value="">请选择...</option>
							<option value="1" <s:property value="queryChanceCondition.origin==1?'selected=\"selected\"':''" />>自然注册</option>
							<option value="2" <s:property value="queryChanceCondition.origin==2?'selected=\"selected\"':''" />>乐语在线</option>
							<option value="4" <s:property value="queryChanceCondition.origin==4?'selected=\"selected\"':''" />>自然留言</option>
							<option value="6" <s:property value="queryChanceCondition.origin==6?'selected=\"selected\"':''" />>CallIn</option>
							</select>
						</td>
						<td style="text-align:right">用户状态：</td>
						<td style="text-align:left">
							<select name="queryChanceCondition.followStatus" id="followStatus">
							<option value="">请选择...</option>
							<option value="2" <s:property value="queryChanceCondition.followStatus==2?'selected=\"selected\"':''" />>跟踪</option>
							<option value="3" <s:property value="queryChanceCondition.followStatus==3?'selected=\"selected\"':''" />>重点</option>
							<option value="4" <s:property value="queryChanceCondition.followStatus==4?'selected=\"selected\"':''" />>成交</option>
							<option value="5" <s:property value="queryChanceCondition.followStatus==5?'selected=\"selected\"':''" />>预约</option>
							</select>
						</td>
						<td style="text-align:right">所属项目：</td>
						<td style="text-align:left">
							<s:select list="subjectList" headerKey=""
								headerValue="选择项目..." listKey="subjectId"
								listValue="subjectName" name="queryChanceCondition.subjectId"
								id="subjectId" listTitle="subjectName">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right">手机号码：</td>
						<td style="text-align:left"><input type="text" style="ime-mode:disabled" maxlength="11" value="<s:property value="queryChanceCondition.mobile"/>" id="mobile" /></td>
						<td style="text-align:right">沟通状态：</td>
						<td style="text-align:left">
							<select name="queryChanceCondition.endCommStatus" id="endCommStatus" >
							<option value="">请选择...</option>
							<option value="1" <s:property value="queryChanceCondition.endCommStatus==1?'selected=\"selected\"':''" />>空号</option>
							<option value="2" <s:property value="queryChanceCondition.endCommStatus==2?'selected=\"selected\"':''" />>通话中</option>
							<option value="3" <s:property value="queryChanceCondition.endCommStatus==3?'selected=\"selected\"':''" />>再联系</option>
							<option value="4" <s:property value="queryChanceCondition.endCommStatus==4?'selected=\"selected\"':''" />>测试</option>
							<option value="5" <s:property value="queryChanceCondition.endCommStatus==5?'selected=\"selected\"':''" />>正常接通</option>
							<option value="6" <s:property value="queryChanceCondition.endCommStatus==6?'selected=\"selected\"':''" />>未接通</option>
							<option value="7" <s:property value="queryChanceCondition.endCommStatus==7?'selected=\"selected\"':''" />>假号码</option>
							<option value="8" <s:property value="queryChanceCondition.endCommStatus==8?'selected=\"selected\"':''" />>已购买</option>
							</select>
						</td>
						<td style="text-align:right">咨询状态：</td>
						<td style="text-align:left">
							<select name="queryChanceCondition.consultStatus" id="consultStatus">
							<option value="">请选择...</option>
							<option value="1" <s:property value="queryChanceCondition.consultStatus==1?'selected=\"selected\"':''" />>未联系</option>
							<option value="2" <s:property value="queryChanceCondition.consultStatus==2?'selected=\"selected\"':''" />>首次</option>
							<option value="3" <s:property value="queryChanceCondition.consultStatus==3?'selected=\"selected\"':''" />>回访</option>
							<option value="4" <s:property value="queryChanceCondition.consultStatus==4?'selected=\"selected\"':''" />>库存回访</option>							
							</select>
						</td>
					</tr>
					
					<tr>
						<td style="text-align:right">邮箱地址：</td>
						<td style="text-align:left"><input type="text" style="ime-mode:disabled" maxlength="50" value="<s:property value="queryChanceCondition.email"/>" id="email" /></td>
						<td style="text-align:right">销售机会状态：</td>
						<td style="text-align:left">
							<select name="queryChanceCondition.salesStatus" id="salesStatus">
							<option value="">请选择...</option>
							<option value="1" <s:property value="queryChanceCondition.salesStatus==1?'selected=\"selected\"':''" />>未注册</option>
							<option value="2" <s:property value="queryChanceCondition.salesStatus==2?'selected=\"selected\"':''" />>已注册</option>
							<option value="3" <s:property value="queryChanceCondition.salesStatus==3?'selected=\"selected\"':''" />>已下单</option>
							<option value="4" <s:property value="queryChanceCondition.salesStatus==4?'selected=\"selected\"':''" />>已购买</option>
							</select>
						</td>
						<td style="text-align:right">咨询次数：</td>
						<td style="text-align:left">
							<input type="text" id="chanceNum1" value="<s:property value="queryChanceCondition.chanceNum1"/>" size="9" maxlength="9" style="ime-mode:disabled" />
							 至 <input type="text" id="chanceNum2" value="<s:property value="queryChanceCondition.chanceNum2"/>" size="9" maxlength="9" style="ime-mode:disabled" /> 次
						</td>
					</tr>
					
					
					<tr align="center" style="height: 50px">
						<td style="text-align:right">报考指导短信：</td>
						<td style="text-align:left" ><input type="checkbox" id="messtatus" /></td>
						<td colspan="4"> 
							<input type="button" value="查询" onclick="search()" />
							<input type="button" value="清空" onclick="clear_form()"/>
							<input type="button" value="导出" id="exportExcel" onclick="checkOutExcel()"/>
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
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top" >
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
						<tr><td width="60%"><font class="lists_fleft">个人销售统计</font>&nbsp;</td>
						<td width="40%">
						</td>
						</tr>
						</table>
					</td>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
		<tr class="lists_infobg"><td width="160px;">项目</td><td width="250px;">销售坐席</td><td width="60px;">来源</td><td width="60px;">咨询状态</td><td width="60px;">订单流水</td><td width="90px;">货到付款激活量</td><td width="100px;">货到付款激活金额</td><td width="50px;">分配量</td><td width="50px;">拨打量</td><td width="50px;">接通量</td><td width="50px;">成交量</td><td width="55px;">成交总量</td><td width="70px;">当日取消量</td><td width="80px;">非当日取消量</td><td width="50px;">转化率</td>
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
		
		
		<s:iterator value="salesStatList" id="customer"
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
			<td>
				        <s:if test="#customer.origin==0">--</s:if>
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
	</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
					<td class="lists_bottom">
					</td>
					<td class="td_wid_r">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>	
			
			<table border="0" width="99%" cellpadding="0" cellspacing="0" >
				<tr><td colspan="3"></td></tr>
				
				<tr>
						<td style="text-align:right">快捷筛选：</td>
						<td style="text-align:left" ><input type="button" value="注册未下单机会" onclick="sorts(1)" />
							<input type="button" value="下单未支付机会" onclick="sorts(2)" />
							<input type="button" value="最新支付机会" onclick="sorts(3)" />
							<input type="button" value="最新指派" onclick="sorts(4)" />
						</td>
				
					<td style="text-align:right">
						<label>请输入要验证的手机号：</label>
						<input type="text" id="phone" maxlength="11"/>
						<input type="button" onclick="newChance()" value="验证手机销售机会"/>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top" >
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
						<tr><td width="60%"><font class="lists_fleft">销售机会列表</font>&nbsp;</td>
						<td width="40%">
						</td>
						</tr>
						</table>
					</td>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
					
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td width="5%">ID</td>
						<td width="5%">手机</td>
						<td width="6%">Email</td>
						<td width="5%">项目</td>
						<td width="5%">域名</td>
						<td width="7%">机会状态</td>
						<td width="5%">来源</td>
						<td width="5%">咨询状态</td>
						<td width="5%">用户状态</td>
						<td width="3%">登录次数</td>
						<td width="5%">流量来源</td>
						<td width="7%">创建时间</td>
						<td width="7%">最后指派时间</td>
						<td width="5%">最后沟通时间</td>
						<td width="5%">最后沟通状态</td>
						<td width="8%">最后沟通备注</td>
						<td width="7%">支付/订单数</td>
						<td width="5%">操作</td>
					</tr>

				<s:iterator value="page.pageResult" var="chance" >
					<tr>
						<td><input type="checkbox" name="chanceId" value='<s:property value="#chance.id"/>'/><s:property value="#chance.id"/></td>
						<td><s:property value="#chance.mobile"/></td>
						<td><a href="<%=contextPath%>/crm/crmChance!getUserCardById.action?userId=<s:property value="#chance.userId"/>"><s:property value="#chance.email!=null?#chance.email:'无'"/></a></td>
						<td><s:property value="#chance.subjectName"/></td>
						<td><s:property value="#chance.webName!=null?#chance.webName:'无'"/></td>
						<td>
							<s:if test="#chance.email==null||#chance.email.trim()==''">
								未注册
							</s:if>
							<s:elseif test="#chance.fcount1>0">
								已购买
							</s:elseif>
							<s:elseif test="#chance.fcount2>0">
								已下单
							</s:elseif>
							<s:else>
								已注册
							</s:else>
						</td>
						<td>
						<s:if test="#chance.origin==0">--</s:if>
				        <s:if test="#chance.origin==1">自然注册</s:if>
				        <s:if test="#chance.origin==2">乐语在线</s:if>
				        <s:if test="#chance.origin==4">自然留言</s:if>
				        <s:if test="#chance.origin==6">CallIn</s:if>
					
					</td>
						<td>
							<s:if test="#chance.consultStatus==1">
								未联系
							</s:if>
							<s:elseif test="#chance.consultStatus==2">
								首次
							</s:elseif>
							<s:elseif test="#chance.consultStatus==3">
								回访
							</s:elseif>
							<s:elseif test="#chance.consultStatus==4">
								库存回访
							</s:elseif>
						</td>
						<td><s:property value="#chance.followStatus==1?'放弃':(#chance.followStatus==2?'跟踪':(#chance.followStatus==3?'重点':(#chance.followStatus==4?'成交':(#chance.followStatus==5?'预约':'无'))))"/></td>
						<td><s:property value="#chance.loginTimes"/></td>
						<td><s:property value="#chance.cusWebFrom"/></td>
						<td><s:date name="#chance.chanceSTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<s:if test="#chance.chanceUTime!=null">
								<s:date name="#chance.chanceUTime" format="yyyy-MM-dd HH:mm:ss"/>
							</s:if>
							<s:else>
								无
							</s:else>
						</td>
						<td>
							<s:if test="#chance.endCommTime!=null">
								<s:date name="#chance.endCommTime" format="yyyy-MM-dd HH:mm:ss"/>
							</s:if>
							<s:else>
								无
							</s:else>
						</td>
						<td>
							<s:if test="#chance.endCommStatus==1">
								空号
							</s:if>
							<s:elseif test="#chance.endCommStatus==2">
								通话中
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==3">
								再联系
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==4">
								测试
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==5">
								正常接通
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==6">
								未接通
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==7">
								假号码
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==8">
								已购买
							</s:elseif>
							<s:else>
								无
							</s:else>
						</td>
						<td><s:property value="#chance.endRecordRemarks"/></td>
						<td>
							<s:if test="#chance.fcount2>0&&#chance.fcount1>0">
								<a href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="#chance.cusId"/>" style="color: red">
									<s:property value="#chance.fcount1"/>/<s:property value="#chance.fcount2"/>已付款</a>
							</s:if>
							<s:elseif test="#chance.fcount2>0">
								<a href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="#chance.cusId"/>" style="color: green;">
									<s:property value="#chance.fcount1"/>/<s:property value="#chance.fcount2"/>未付款</a>
							</s:elseif>
							<s:else>
								<s:property value="#chance.fcount1"/>/<s:property value="#chance.fcount2"/>
							</s:else>
							<s:if test="#chance.fcount4>0">
								<img style="width:12px;height:10px;" src="<%=contextPath%>/back/images/huo_2.png">
							</s:if>
							<s:elseif test="#chance.fcount3>0">
								<img style="width:12px;height:10px;" src="<%=contextPath%>/back/images/huo_1.png">
							</s:elseif>
						</td>
						<td>
								<a href='<%=contextPath%>/crm/crmChance!toAddRecord.action?crmChanceId=<s:property value="#chance.id"/>&&userId=<s:property value="#chance.userId"/>'>添加记录</a>
						</td>
					</tr>
				</s:iterator>
				</table>
					
					
					
					
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
					<td class="lists_bottom">
						<jsp:include page="/back/jsp/common/showPage.jsp" />
					</td>
					<td class="td_wid_r">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>	
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
			<td class="lists_bottom">

			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
		</tr>
	</table>
</div>

	<!--层遮罩部分-->		
		<div style="position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5; -moz-opacity:0.5;top:0;left:0;width:100%;height:150%;background:#000;display:none;z-index:4;" id="wrapback">		
		<iframe id="wrapbackInfo" style="position:absolute;width:100%;height:100%;_filter:alpha(opacity=50);opacity=0.5;border-style:none;"></iframe>
		</div>
		<!--层遮罩部分结束-->
		<!--显示信息区-->		
		<div align='center' class="zs_gl"  id='infoWrap'>
			<div class="tit">
				<span class="tie fonts" id="title" >手机号检测</span>
			</div>	
			
				<div id="info"  class="con">
				</div>
				<div align="center"><input type='button' value='关闭' onclick='closes();'/></div>
		</div> 
		<!--显示信息区结束-->
	</body>
</html>