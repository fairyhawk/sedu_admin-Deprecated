
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />

		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>

				<script type="text/javascript">
		<!--判断是否为数字-->
			
			function IsNum(Text){
				return /^\d+$/.test(Text);
			}
			
			function IsMoblie(Text)
			{
				return /^1\d{10}$/g.test(Text);
			}
			 function search(ObjectForm){
			  var startTime=document.getElementById("startTime").value;
			  var endTime=document.getElementById("endTime").value;
			  if(startTime!=null&&endTime!=null){
			  if(startTime>endTime){
				 alert("结束时间大于开始时间!");
				 return;
			  }else{
			  		document.getElementById("locationId").value=100;
					ObjectForm.action="<%=contextPath%>/cus/cus!dsCustomerDisplayInfo.action";
					ObjectForm.submit();
				}
			  }
	}
			
			function resetKey() {
				$("#op0").attr("selected","selected");
				$("input[name=queryCustomerCondition.startNumber]").val("0");
				$("input[name=queryCustomerCondition.endNumber]").val("999999");
				$("input[name=startTime]").val("");
				$("input[name=endTime]").val("");
				$("#op1").attr("selected","selected");
				$("#op3").attr("selected","selected");
				$("#subjectId").val(-1);				
			}
			
			function allCheck(cb) {
				$("input[name=ids]:checkbox").attr('checked', cb.checked);
			}


			function SpaceKc(h){			
				if(h==5)
				{
				}else
				{
					for(var i=0;i<5;i++)
					{
					//document.getElementById("spa_kc"+i).style.display="none";
					//document.getElementById("spa_kc"+h).style.display="block";
					document.getElementById("spa_kc_t"+i).style.background="#6699bb";
					document.getElementById("spa_kc_t"+i).style.color="#ffffff";
					document.getElementById("spa_kc_t"+h).style.color="#333333";
					document.getElementById("spa_kc_t"+h).style.background="#eeeeee";
					document.getElementById("spa_kc_t"+h).style.backgroundPosition="center";							
					}
				}
			}
			function SpaceR(h){
				for(var i=0;i<3;i++){
				document.getElementById("spa_gc"+i).style.display="none";
				document.getElementById("spa_gc"+h).style.display="block";
				document.getElementById("spa_gc_t"+i).style.background="#6699bb";
				document.getElementById("spa_gc_t"+i).style.color="#ffffff";
				document.getElementById("spa_gc_t"+h).style.color="#333333";
				document.getElementById("spa_gc_t"+h).style.background="#eeeeee";
				document.getElementById("spa_gc_t"+h).style.backgroundPosition="center";
				}
				SpaceKc(<s:property value="location"/>);
					
			}
	
			function changeData() {	
				if(<s:property value="dateLocation"/>>-1) {
					SpaceR(<s:property value="dateLocation"/>)
				}else{
					SpaceKc(<s:property value="location"/>);
				}	
				changeStartHH('<s:property value="startHH"/>');
				changeEndHH('<s:property value="endHH"/>');	
				$("#cusFromUrl").val('${customer.cusFromUrl}');
				
				document.getElementById("cusFromUrl").value="<s:property value="queryCustomerCondition.cusFromUrl"/>";
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
				});
			}
			//自动刷新倒计时
		/**	var startTime = new Date();
		    var endTime=startTime.getTime()+ 10*60*1000;//十分钟
		    
		    function getRemainTime(){
		                    
		        var nowTime = new Date();
		        var nMS =endTime - nowTime.getTime();
		        var nM=Math.floor(nMS/(1000*60)) % 60;
		        var nS=Math.floor(nMS/1000) % 60;
		        if(nM==0&&nS==0&&nMS<1000) //当倒计时结束
		        {
		            window.focus();
		            window.location.reload();
		        }
		        if(nS < 10) nS = "0" + nS;
		           
		        if(nMS >= 0){
		        	$('#releaseTime').text(nM + "分" + nS + "秒");
		            setTimeout("getRemainTime()",1000);
		        }
		        
		    }*/
		   $().ready(
		   	function(){
		//   		getRemainTime();		   		
				var s1=0,s2=0,s3=0,s4=0,s5=0,s6=0,s7=0,s8=0,s9=0,s10=0,s11=0,s12=0,s13=0,s14=0,s15=0,s16=0,s17=0;
				$("label").each(function(){
					if($(this).attr("class")=="sum01"){
						s1+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum02"){
						s2+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum03"){
						s3+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum04"){
						s4+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum05"){
						s5+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum06"){
						s6+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum07"){
						s7+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum08"){
						s8+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum09"){
						s9+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum010"){
						s10+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum011"){
						s11+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum012"){
						s12+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum013"){
						s13+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum014"){
						s14+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum015"){
						s15+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum016"){
						s16+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
					if($(this).attr("class")=="sum017"){
						s17+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
					}
				});
	//			alert($("#test:eq(2)"));
				$("#test").children().each(function(){
					var count=0;
					if(count>0){
						$("#test").children().eq(count).text();
					}
					count++;
				});
				$("#test").children().eq(1).text(s1);
				$("#test").children().eq(2).text(s2);
				$("#test").children().eq(3).text(s3);
				$("#test").children().eq(4).text(s4);
				$("#test").children().eq(5).text(s5);
				$("#test").children().eq(6).text(s6);
				$("#test").children().eq(7).text(s7);
				$("#test").children().eq(8).text(s8);
				$("#test").children().eq(9).text(s9);
				$("#test").children().eq(10).text(s10);
				$("#test").children().eq(11).text(s11);
				$("#test").children().eq(12).text(s12);
				$("#test").children().eq(13).text(s13);
				$("#test").children().eq(14).text(s14);
				$("#test").children().eq(15).text(s15);
		   	}
		   );
		   //自动刷新倒计时
	   var onClick = function (fzbk_id){
		var div = document.getElementById(fzbk_id);
		var visible = div.style.display;
		if(visible == "none"){
			document.getElementById(fzbk_id).style.display = "block";
		}else{
			document.getElementById(fzbk_id).style.display = "none";
		}
		};
		
		function test(){
			alert("test");
		}
		</script>
	</head>
	<body >
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">学员统计(<font color="blue">ds商品</font>)</font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<form name="searchForm" action="cus!dsCustomerDisplayInfo.action" method="post">
							<s:hidden name="location" id="locationId" />
							<div class="msg-yw">
							<table width="75%" border="0" cellspacing="1" cellpadding="0"
								class="lists" align="center">
								<tr align="left">
									<td>
										<s:hidden name="queryCustomerCondition.currentPage" value="1" />
										开始时间：
										<input type="text" name="startTime" readonly id="startTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${startTime}" />
										<select name="startHH" id="startHH">
											<option value=" 00:00:00" id="op1">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
									<td>
										结束时间：
										<input type="text" name="endTime" readonly id="endTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${endTime}" />
										<select name="endHH" id="endHH">
											<option value=" 23:59:59" id="op0">
												23:59:59
											</option>
											<option value=" 00:00:00">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
										</select>
										(时:分:秒)
									</td>
								</tr>
								<tr>
									<td>
										域名来源：
										<select name="queryCustomerCondition.cusFromUrl"
											id="cusFromUrl" style="width: 155px">
											<option value="" id="op3">
												---请选择---
											</option>
											<option value="highso.org.cn">
												highso.org.cn
											</option>
											<option value="highso.cn">
												highso.cn
											</option>
											<option value="highso.org">
												highso.org
											</option>
											<option value="highso.net.cn">
												highso.net.cn
											</option>
											<option value="highso.com.cn">
												highso.com.cn
											</option>
										</select>
									</td>
									<td>
									</td>
								</tr>
								<tr>
									<td>
										<img src="<%=contextPath%>/back/images/add_a.gif" />
										<a href="javascript:search(document.searchForm)">查询</a>
										<img src="<%=contextPath%>/back/images/del_a.gif" />
										<a href="javascript:resetKey()">清空</a>
									</td>
									<td>
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
						<div id="timeClass" class="spa_tab spa_tjleft">
							<div
								style="float: right; line-height: 28px; padding-right: 10px;">
								<!-- 自动刷新倒计时： -->
								<font id='releaseTime'></font>&nbsp;&nbsp;&nbsp; 手动刷新按钮
								<input type="button" value="刷新"
									onclick="javascript:window.location.reload();" />
							</div>
						</div>
						<!--
							修改  
						-->
						<div class="spa_tabcon dispblock spa_tabconbor">
							<div id="timeClass" class="spa_tab spa_tjleft">
								<ul>
									<li>
										<a
											href="<%=contextPath%>/cus/cus!dsCustomerDisplayInfo.action?location=1"
											id="spa_kc_t1">今日</a>
									</li>
									<li>
										<a
											href="<%=contextPath%>/cus/cus!dsCustomerDisplayInfo.action?location=2"
											id="spa_kc_t2">一周内</a>
									</li>
									<li>
										<a
											href="<%=contextPath%>/cus/cus!dsCustomerDisplayInfo.action?location=3"
											id="spa_kc_t3">一个月内</a>
									</li>
									<li>
										<a
											href="<%=contextPath%>/cus/cus!dsCustomerDisplayInfo.action?location=4"
											id="spa_kc_t4">三个月内</a>
									</li>
									<li>
										<a
											href="<%=contextPath%>/cus/cus!dsCustomerDisplayInfo.action?location=0"
											id="spa_kc_t0">全部</a>
									</li>
							</div>
							</ul>
							<table width="99%" border="0" cellspacing="1" cellpadding="0"
								bgcolor="#c6d1d5" id="shuju">
								<tr style="font-weight: bold;">
									<td height="24" align="center" bgcolor="#d4e7f2">
										专业名称
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										全部学员
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										注册学员
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										内部学员
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										全部订单
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										网银订单
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										货到付款订单
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										内部开通订单
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										取消订单
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										代理商订单
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										银行汇款订单
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										网银订单(已付)
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										货到付款订单(已付)
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										银行汇款订单(已付)
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										订单流水
									</td>
									<td height="24" align="center" bgcolor="#d4e7f2">
										退费
									</td>
								</tr>
								<s:iterator var="customerCountDTO" value="ccNewDTO">
									<tr>
										<td height="28" align="center" bgcolor="#FFF5EE" style="font-weight: bold;">
											<s:property value="#customerCountDTO.subName" />
										</td>
										<td height="28" align="center" bgcolor="#F5F5DC" >
											<label class="sum01">
												<s:if test="#customerCountDTO.allStudent!=null" >
													<s:property value="#customerCountDTO.allStudent" />
												</s:if>
												<s:else>
													0
												</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#F5F5DC">
										<label class="sum02">
										<s:if test="#customerCountDTO.regStudent!=null" >
											<s:property value="#customerCountDTO.regStudent" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#F5F5DC">
										<label class="sum03">
										<s:if test="#customerCountDTO.interStudent!=null" >
											<s:property value="#customerCountDTO.interStudent" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<!--  
										<td height="28" align="center" bgcolor="#F5F5DC">
										<label class="sum04">
										<s:if test="#customerCountDTO.tempStudent!=null" >
											<s:property value="#customerCountDTO.tempStudent" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										-->
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum04">
										<s:if test="#customerCountDTO.allOrder!=null" >
											<s:property value="#customerCountDTO.allOrder" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum05">
										<s:if test="#customerCountDTO.bankOrder!=null" >
											<s:property value="#customerCountDTO.bankOrder" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum06">
										<s:if test="#customerCountDTO.dtoOrder!=null" >
											<s:property value="#customerCountDTO.dtoOrder" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum07">
										<s:if test="#customerCountDTO.insideOrder!=null" >
											<s:property value="#customerCountDTO.insideOrder" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum08">
										<s:if test="#customerCountDTO.cancelOrder!=null" >
											<s:property value="#customerCountDTO.cancelOrder" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<!--  
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum010">
										<s:if test="#customerCountDTO.invalidOrder!=null" >
											<s:property value="#customerCountDTO.invalidOrder" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										-->
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum09">
										<s:if test="#customerCountDTO.agentsOrder!=null" >
											<s:property value="#customerCountDTO.agentsOrder" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum010">
										<s:if test="#customerCountDTO.transferOrder!=null" >
											<s:property value="#customerCountDTO.transferOrder" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#FDF5E6">
										<label class="sum011">
										<s:if test="#customerCountDTO.bankOrderPaid!=null" >
											<s:property value="#customerCountDTO.bankOrderPaid" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#F0FFF0">
										<label class="sum012">
										<s:if test="#customerCountDTO.dtoOrderPaid!=null" >
											<s:property value="#customerCountDTO.dtoOrderPaid" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#F0FFF0">
										<label class="sum013">
										<s:if test="#customerCountDTO.transferOrderPaid!=null" >
											<s:property value="#customerCountDTO.transferOrderPaid" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#F0FFF0">
										<label class="sum014">
										<s:if test="#customerCountDTO.afterAmount!=null" >
											<s:property value="#customerCountDTO.afterAmount" />
											</s:if>
											<s:else>
												0
											</s:else>
											</label>
										</td>
										<td height="28" align="center" bgcolor="#EEA2AD">
										<label class="sum015">
										<s:if test="#customerCountDTO.Refund!=null" >
											<s:property value="#customerCountDTO.Refund" />
											</s:if>
											<s:else>
												0
											</s:else>
										</label>
										</td>
										
									</tr>
								</s:iterator>
								<tr id="test">
									<td height="28" align="center" bgcolor="#FFF5EE" style="font-weight: bold;">
										总 计
									</td>
									<td height="28" align="center" bgcolor="#F5F5DC">
										
									</td>
									<td height="28" align="center" bgcolor="#F5F5DC">
										
									</td>
									<td height="28" align="center" bgcolor="#F5F5DC">
										
									</td>
									<td height="28" align="center" bgcolor="#FDF5E6">
										
									</td>
									<td height="28" align="center" bgcolor="#FDF5E6">
										
									</td>
									<td height="28" align="center" bgcolor="#FDF5E6">
										
									</td>
									<td height="28" align="center" bgcolor="#FDF5E6">
										
									</td>
									<td height="28" align="center" bgcolor="#FDF5E6">
										
									</td>
									<td height="28" align="center" bgcolor="#FDF5E6">
										
									</td>
									<td height="28" align="center" bgcolor="#FDF5E6">
									
									</td>
									<td height="28" align="center" bgcolor="#FDF5E6">
										
									</td>
									<td height="28" align="center" bgcolor="#F0FFF0">
										
									</td>
									<td height="28" align="center" bgcolor="#F0FFF0">
											
									</td>
									<td height="28" align="center" bgcolor="#F0FFF0">
										
									</td>
									<td height="28" align="center" bgcolor="#EEA2AD">
										
									</td>
								</tr>
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
					</td>
					<td>
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
