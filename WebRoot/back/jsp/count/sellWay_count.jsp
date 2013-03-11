<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>商品统计</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />

		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		function search(){

			  var startTime=document.getElementById("startTime").value;
			  var endTime=document.getElementById("endTime").value;
			  if(startTime!=null&&endTime!=null){
			  if(startTime>endTime){
				 alert("结束时间大于开始时间!");
				 return;
			  }else{
			 	document.search.action="<%=contextPath%>/sellCount/sellWayCount!getSellWayBySubjectId.action";
			 	document.search.submit();
			 
			 }
			 }
		}
		
		function resetKey() {
				$("#op0").attr("selected","selected");
				$("input[name=startTime]").val("");
				$("input[name=endTime]").val(""); 
				$("input[name=querySellWayCountCondition.webFrom]").val("");
				$("input[name=querySellWayCountCondition.webAgnet]").val("");
				$("#op1").attr("selected","selected");
				$("#op3").attr("selected","selected");
				$("#subjectId").val(1);				
		}
		function setLocation(location){
			$("#location").val(location);
			$("input[name=startTime]").val("");
			$("input[name=endTime]").val(""); 
			changefkStartHH(' 00:00:00');
			changeEndHH(' 23:59:59');
			SpaceKc(location);
		}
		function SpaceKc(h){			

			for(i=-1;i<5;i++)
			{
				document.getElementById("spa_kc_t"+i).style.background="#6699bb";
				document.getElementById("spa_kc_t"+i).style.color="#ffffff";
				document.getElementById("spa_kc_t"+h).style.color="#333333";
				document.getElementById("spa_kc_t"+h).style.background="#eeeeee";
				document.getElementById("spa_kc_t"+h).style.backgroundPosition="center";							
			}
		}
		
		function chandeDate(h){
			$("input[name=querySellWayCountCondition.webFrom]").val("<s:property value='querySellWayCountCondition.webFrom'/>");
			$("input[name=querySellWayCountCondition.webAgnet]").val("<s:property value='querySellWayCountCondition.webAgnet'/>");
			changefkStartHH('<s:property value="startHH"/>');
			changeEndHH('<s:property value="endHH"/>');
			changeUrl('<s:property value="querySellWayCountCondition.contractFromUrl"/>');
			SpaceKc(h);
		
		}
		
		function changefkStartHH(hourStr) {
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
		
		function changeUrl(fromUrl){
		$.each($("#contractFromUrl option"),function(){
				if(this.value == fromUrl){
					this.selected = true;
				}
			})
		}
		</script>
	</head>
	<body onload="chandeDate('<s:property value="location"/>');">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
			<tr >
				<td width="12" class="lists_bor">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td>
					<font class="lists_fright"></font>
				</td>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
			</tr>
			<tr>
				<td width="12" class="lists_bor">
				</td>
				<td>
					<div class="spa_tabcon dispblock spa_tabconbor">
						<form name="search"
							action="/sellCount/sellWayCount!getSellWayBySubjectId.action"
							method="post" onsubmit="return search()">
							<table width="75%" border="0" cellspacing="1" cellpadding="0"
								class="lists" align="center">
								<tr align="left">
									<td>
										<s:hidden name="querySellWayCountCondition.currentPage"
											value="1" />
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
										专业项目：
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
							<s:hidden name="location" id="location"></s:hidden>
						</form>
						<div id="timeClass" class="spa_tab spa_tjleft">
							<ul>
								<li>
									<a href="javascript:setLocation(1)" id="spa_kc_t1">今日</a>
								</li>
								<li>
									<a href="javascript:setLocation(2)" id="spa_kc_t2">一周内</a>
								</li>
								<li>
									<a href="javascript:setLocation(3)" id="spa_kc_t3">一个月内</a>
								</li>
								<li>
									<a href="javascript:setLocation(4)" id="spa_kc_t4">三个月内</a>
								</li>
								<li>
									<a href="javascript:setLocation(0)" id="spa_kc_t0">全部</a>
								</li>
								<li>
									<a href="javascript:setLocation(-1)" id="spa_kc_t-1">不选</a>
								</li>
						</div>
						<table width="99%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#c6d1d5">
							<tr style="font-weight: bold;">
								<td height="24" align="center" bgcolor="#d4e7f2">
									售卖方式名称
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									所属项目
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
								<td height="24" align="center" bgcolor="#d4e7f2">
									下单转化率
								</td>
								<td height="24" align="center" bgcolor="#d4e7f2">
									支付转化率
								</td>
							</tr>
							<s:iterator id="sellWayCountDTO" value="sellWayCountList">
								<tr>
									<td height="28" align="center" bgcolor="#FFFFFF"
										style="font-weight: bold;">
										<s:property value="#sellWayCountDTO.sellName" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF"
										style="font-weight: bold;">
										<s:property value="#sellWayCountDTO.subjectName" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.contractSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.hdfkSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.yfHdfkSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.zfbSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.yfZfbSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.kqSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.yfKqSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.wyzxSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.yfWyzxSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.yfSubjectContractSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.zsSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.sellPriceSum" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										--
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.TF" />
									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">

										<s:property value="#sellWayCountDTO.convertContract" />
										%

									</td>
									<td height="28" align="center" bgcolor="#FFFFFF">
										<s:property value="#sellWayCountDTO.YFconverrContract" />
										%
									</td>
								</tr>
							</s:iterator>
							<tr>
								<td height="28" align="center" bgcolor="#FFFFFF"
									style="font-weight: bold;">
									总计
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF"
									style="font-weight: bold;">
									<s:property value="#sellWayCountDTO.subjectName" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjHdfkSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjWfHdfkSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjZfbSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjWfZfbSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjKqSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjWfKqSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjWyzxSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjWfWyzxSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjWfSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjZsSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjSellPriceSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									--
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.tfSum" />
								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">

									<s:property value="#sellWayCountDTO.zjConvertSum" />
									%

								</td>
								<td height="28" align="center" bgcolor="#FFFFFF">
									<s:property value="#sellWayCountDTO.zjYfConvertSum" />
									%
								</td>
							</tr>
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
	</body>
</html>
