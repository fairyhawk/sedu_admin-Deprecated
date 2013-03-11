<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>评价列表</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		$().ready(function(){
			var contractFromUrl=$("#cfu").val();
			if(contractFromUrl!=0){
				$("#contractFromUrl").val(contractFromUrl);
			}
			var wf=$("#wf").val();
			if(wf!=0){
				$("#webFrom").attr("value",wf);
			}
			var wa=$("wa").val();
			if(wa!=0){
				$("#webAgnet").val(wa);
			}
			SpaceKc(<s:property value="location"/>);
			var sumAllOrder=0,sumDtoOrder=0,sumDtoOrderNo=0,sumBankOrder=0,sumBankOrderNo=0,sumQuickOrder=0,sumPerOne=0,sumPerTwo=0,
   			countOne=0,countTwo=0,sumTruefriendsOrder=0,sumRemittanceOrder=0,sumRemittanceOrderNo=0,sumTruefriendsOrderNo=0,sumCupOrder=0,sumCupOrderNo=0,
   			sumQuickOrderNo=0,sumInternetOrder=0,sumInternetOrderNo=0,sumOrderNo=0,sumGiftOrder=0,sumAfterAmount=0,sumRefund=0;
   			$("label").each(function(){
				if($(this).attr("class")=="sumAllOrder"){
					sumAllOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumDtoOrder"){
					sumDtoOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumDtoOrderNo"){
					sumDtoOrderNo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumBankOrder"){
					sumBankOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumBankOrderNo"){
					sumBankOrderNo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumQuickOrder"){
					sumQuickOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumQuickOrderNo"){
					sumQuickOrderNo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumInternetOrder"){
					sumInternetOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumInternetOrderNo"){
					sumInternetOrderNo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				
				if($(this).attr("class")=="sumTruefriendsOrder"){
					sumTruefriendsOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumTruefriendsOrderNo"){
					sumTruefriendsOrderNo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumRemittanceOrder"){
					sumRemittanceOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumRemittanceOrderNo"){
					sumRemittanceOrderNo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				
				if($(this).attr("class")=="sumCupOrder"){
					sumCupOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumCupOrderNo"){
					sumCupOrderNo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				
				if($(this).attr("class")=="sumOrderNo"){
					sumOrderNo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumGiftOrder"){
					sumGiftOrder+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumAfterAmount"){
					sumAfterAmount+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumRefund"){
					sumRefund+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumPerOne"){
					countOne++;
					sumPerOne+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
				if($(this).attr("class")=="sumPerTwo"){
					countTwo++;
					sumPerTwo+=parseInt($(this).text().replace(/\s+$|^\s+/g,""));
				}
			});
			$("#test").children().eq(1).text(sumAllOrder);
			$("#test").children().eq(2).text(sumDtoOrder);
			$("#test").children().eq(3).text(sumDtoOrderNo);
			$("#test").children().eq(4).text(sumBankOrder);
			$("#test").children().eq(5).text(sumBankOrderNo);
			$("#test").children().eq(6).text(sumQuickOrder);
			$("#test").children().eq(7).text(sumQuickOrderNo);
			$("#test").children().eq(8).text(sumInternetOrder);
			$("#test").children().eq(9).text(sumInternetOrderNo);
			
			$("#test").children().eq(10).text(sumTruefriendsOrder);
			$("#test").children().eq(11).text(sumTruefriendsOrderNo);
			$("#test").children().eq(12).text(sumRemittanceOrder);
			$("#test").children().eq(13).text(sumRemittanceOrderNo);
			$("#test").children().eq(14).text(sumCupOrder);
			$("#test").children().eq(15).text(sumCupOrderNo);
			
			$("#test").children().eq(16).text(sumOrderNo);
			$("#test").children().eq(17).text(sumGiftOrder);
			$("#test").children().eq(18).text(sumAfterAmount);
			$("#test").children().eq(20).text(sumRefund);
			$("#test").children().eq(21).text(Math.round((sumPerOne/countOne)*100)/100+"%");
			$("#test").children().eq(22).text(Math.round((sumPerTwo/countTwo)*100)/100+"%");
		});
		function setL(num){
			$("input[name=startTime]").val("");
			$("input[name=endTime]").val(""); 
		 	$("#ser").attr("action","<%=contextPath%>/sellCount/sellWayCount!getSellWayBySubjectId.action?l="+num);
		 	$("#ser").submit();
		}
		function SpaceKc(h){
			for(var i=0;i<5;i++)
			{
				document.getElementById("spa_kc_t"+i).style.background="#6699bb";
				document.getElementById("spa_kc_t"+i).style.color="#ffffff";
				if(h<5){
					document.getElementById("spa_kc_t"+h).style.color="#333333";
					document.getElementById("spa_kc_t"+h).style.background="#eeeeee";
					document.getElementById("spa_kc_t"+h).style.backgroundPosition="center";							
				}
			}
		}
		function search(){
			  var startTime=document.getElementById("startTime").value;
			  var endTime=document.getElementById("endTime").value;
			  if(startTime!=null&&endTime!=null){
			  if(startTime>endTime){
				 alert("结束时间大于开始时间!");
				 return;
			  }else{
				  if(startTime!=""||endTime!=""){
					 document.getElementById("location").value=100;					  
				  }
			 	document.search.action="<%=contextPath%>/sellCount/sellWayCount!getSellWayBySubjectId.action";
			 	document.search.submit();
			 
			 }
			 }
		}
		function resetKey() {
			$("input[name=startTime]").val("");
			$("input[name=endTime]").val(""); 
			$("input[name=querySellWayCountCondition.webFrom]").val("");
			$("input[name=querySellWayCountCondition.webAgnet]").val("");
			$("#op3").attr("selected","selected");
			$("#subjectId").val(0);				
		}
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
			<tr class="lists_top">
				<td width="12" class="lists_bor">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td>
					<font class="lists_fright"></font>
				</td>
				<td width="16">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
			</tr>
			<tr>
				<td width="12" class="lists_bor">
				</td>
				<td>
					<div class="spa_tabcon dispblock spa_tabconbor">
						<form id="ser" name="search" method="post">
							<!-- 域名来源 -->
							<s:if test="querySellWayCountCondition.contractFromUrl!=null">
								<input type="hidden" value=<s:property value="querySellWayCountCondition.contractFromUrl"/> id="cfu"/>								
							</s:if>
							<s:else>
								<input type="hidden" value=0 id="cfu"/>	
							</s:else>
							<!-- 推广来源 -->
							<s:if test="querySellWayCountCondition.webFrom!=null">
								<input type="hidden" value=<s:property value="querySellWayCountCondition.webFrom"/> id="wf"/>								
							</s:if>
							<s:else>
								<input type="hidden" value=0 id="wf"/>	
							</s:else>
							<!-- 订单渠道 -->
							<s:if test="querySellWayCountCondition.webAgnet!=null">
								<input type="hidden" value=<s:property value="querySellWayCountCondition.webAgnet"/> id="wa"/>								
							</s:if>
							<s:else>
								<input type="hidden" value=0 id="wa"/>	
							</s:else>
							<div class="msg-yw">
							<table width="75%" border="0" cellspacing="1" cellpadding="0"
								class="lists" align="center">
								<tr align="left">
									<td>
										<s:hidden name="querySellWayCountCondition.currentPage" value="1" />
										开始时间：
										<input type="text" name="startTime" readonly="readonly" id="startTime" onclick="WdatePicker()" value="${startTime}" />
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
										<input type="text" name="endTime" readonly="readonly" id="endTime" onclick="WdatePicker()" value="${endTime}" />
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
										<select name="querySellWayCountCondition.contractFromUrl"
											id="contractFromUrl"
											style="width: 155px">
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
										商品名称：
										<s:select id="subjectId"
											name="querySellWayCountCondition.subjectId"
											list="subjectList" listKey="subjectId"
											listValue="subjectName" tyle="width: 155px">
										</s:select>
									</td>
								</tr>
								<tr>
									<td>
										推广来源：
										<input type="text" name="querySellWayCountCondition.webFrom"
											id="webFrom" />
									</td>
									<td>
										订单渠道：
										<input type="text" name="querySellWayCountCondition.webAgnet"
											id="webAgnet" />
									</td>
								</tr>
								<tr>
									<td>
										<img src="<%=contextPath%>/back/images/add_a.gif" />
										<a href="javascript:search()">查询</a>
										<img src="<%=contextPath%>/back/images/del_a.gif" />
										<a href="javascript:resetKey()">清空</a>
									</td>
									<td>
									</td>
								</tr>
							</table>
							</div>
							<s:hidden name="location" id="location"></s:hidden>
						</form>
						<div id="timeClass" class="spa_tab spa_tjleft">							  
							<ul>
								<li>
									<a href="javascript:setL(1)" id="spa_kc_t1">今日</a>
								</li>
								<li>
									<a href="javascript:setL(2)" id="spa_kc_t2">一周内</a>
								</li>
								<li>
									<a href="javascript:setL(3)" id="spa_kc_t3">一个月内</a>
								</li>
								<li>
									<a href="javascript:setL(4)" id="spa_kc_t4">三个月内</a>
								</li>
								<li>
									<a href="javascript:setL(0)" id="spa_kc_t0">全部</a>
								</li>
								</ul>
						</div>
						<table width="99%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#c6d1d5">
							<tr style="font-weight: bold;">
								<td height="24" align="center" bgcolor="#d4e7f2">
									商品名称
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									全部订单
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									货到付款
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									货到付款(未)
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									支付宝订单
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									支付宝(未)
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									快钱订单
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									快钱订单(未)
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									网银在线
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									网银在线(未)
								</td>
								
								<td height="24" align="center" bgcolor="#d4e7f2">
									真友
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									真友(未)
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									银行汇款
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									银行汇款(未)
								</td>
								<!-- 银联在线开始 -->
								<td height="24" align="center" bgcolor="#d4e7f2">
									银联在线
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									银联在线(未)
								</td>
								<!-- 银联在线结束 -->
								<td height="24" align="center" bgcolor="#d4e7f2">
									未付订单
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									赠送订单
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									订单金额(元)
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									优惠券
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									退费
								</td>
								<!--  
								<td height="24" align="center" bgcolor="#d4e7f2">
									人数
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									订单
								</td>
								-->
								<td height="24" align="center" bgcolor="#d4e7f2">
									下单转化率
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									支付转化率
								</td>
							</tr>
							<s:iterator id="contractCountDto" value="sellWayListNew">
								<tr>
									<td height="28" align="center" bgcolor="#FFFFFF"
										style="font-weight: bold;">
										<s:property value="#contractCountDto.sellName" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<label class="sumAllOrder">
										<s:if test="#contractCountDto.allOrder!=null">
											<s:property value="#contractCountDto.allOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumDtoOrder">
										<s:if test="#contractCountDto.dtoOrder!=null">
										<s:property value="#contractCountDto.dtoOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumDtoOrderNo">
										<s:if test="#contractCountDto.dtoOrderNo!=null">
										<s:property value="#contractCountDto.dtoOrderNo" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumBankOrder">
										<s:if test="#contractCountDto.bankOrder!=null">
										<s:property value="#contractCountDto.bankOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumBankOrderNo">
										<s:if test="#contractCountDto.bankOrderNo!=null">
										<s:property value="#contractCountDto.bankOrderNo" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumQuickOrder">
										<s:if test="#contractCountDto.quickOrder!=null">
										<s:property value="#contractCountDto.quickOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumQuickOrderNo">
										<s:if test="#contractCountDto.quickOrderNo!=null">
										<s:property value="#contractCountDto.quickOrderNo" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumInternetOrder">
										<s:if test="#contractCountDto.internetOrder!=null">
										<s:property value="#contractCountDto.internetOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumInternetOrderNo">
										<s:if test="#contractCountDto.internetOrderNo!=null">
										<s:property value="#contractCountDto.internetOrderNo" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumTruefriendsOrder">
										<s:if test="#contractCountDto.truefriendsOrder!=null">
										<s:property value="#contractCountDto.truefriendsOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumTruefriendsOrderNo">
										<s:if test="#contractCountDto.truefriendsOrderNo!=null">
										<s:property value="#contractCountDto.truefriendsOrderNo" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumRemittanceOrder">
										<s:if test="#contractCountDto.remittanceOrder!=null">
										<s:property value="#contractCountDto.remittanceOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumRemittanceOrderNo">
										<s:if test="#contractCountDto.remittanceOrderNo!=null">
										<s:property value="#contractCountDto.remittanceOrderNo" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<!-- 银联在线开始 -->
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumCupOrder">
										<s:if test="#contractCountDto.cupOrder!=null">
										<s:property value="#contractCountDto.cupOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumCupOrderNo">
										<s:if test="#contractCountDto.cupOrderNo!=null">
										<s:property value="#contractCountDto.cupOrderNo" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<!-- 银联在线结束 -->
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumOrderNo">
										<s:if test="#contractCountDto.orderNo!=null">
										<s:property value="#contractCountDto.orderNo" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumGiftOrder">
										<s:if test="#contractCountDto.giftOrder!=null">
										<s:property value="#contractCountDto.giftOrder" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumAfterAmount">
										<s:if test="#contractCountDto.afterAmount!=null">
										<s:property value="#contractCountDto.afterAmount" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										--
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
									<label class="sumRefund">
										<s:if test="#contractCountDto.Refund!=null">
										<s:property value="#contractCountDto.Refund" />
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<!--  
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#contractCountDto.allStudent" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#contractCountDto.allLinshi" />
									</td>
									--> 
									<td height="28" align="center" bgcolor="#FFFFFF">
										<label class="sumPerOne">
										<s:if test="#contractCountDto.allStudent!=null">
										<fmt:formatNumber value="${contractCountDto.allLinshi*100/contractCountDto.allStudent}" pattern="#.##"/>%
										</s:if>
										<s:else>0</s:else>
										</label>
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<label class="sumPerTwo">
									<s:if test="#contractCountDto.allOrder!=null">
									<fmt:formatNumber value="${(contractCountDto.allOrder-contractCountDto.orderNo)*100/contractCountDto.allOrder}" pattern="#.##"/>%
									</s:if>
									<s:else>0</s:else>
									</label>
									</td>
								</tr>
							</s:iterator>
							<tr id="test">
								<td height="28" align="center" bgcolor="#FFFFFF"
									style="font-weight: bold;">
									总计
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
								
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
								
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
								
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
								
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
								
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
								
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									--
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
								
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
								
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
	</body>
</html>
