
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
	　function openWin(ctId) 
	{ 
		window.open ("<%=contextPath%>/finance/backContract!getContractView.action?contract.id="+ctId, "newwindow", "height=600, width=800, toolbar=no, menubar=yes, scrollbars=no, resizable=no, location=no, status=no");
　
　　	}
	
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
			  var startNumber=document.getElementById("startNumber").value;
			  var endNumber=document.getElementById("endNumber").value;
			  var mobile=document.getElementById("mobile").value;
			  if(startTime!=null&&endTime!=null){
			  if(startTime>endTime){
				 alert("结束时间要大于开始时间!");
				 return false;
			  }else{
				  if(mobile!="")
				  {
				  		if(mobile.length>11)
				  		{
				  			alert("手机号格式不正确");
				  			return ;
				  		}
				  }
				  
				if(startNumber!=""||endNumber!="")
				{	
				 	if(IsNum(startNumber)!=true)
					{
						alert("首次数不数字");
						return ;
					}
					if(IsNum(endNumber)!=true)
					{
						alert("末次数不数字");
						return ;
					}
				 
					if(startNumber!=""&&endNumber=="")
					{
						alert("末次数为空");
						return ;
					}	  
					
					if(startNumber==""&&endNumber!="")
					{
						alert("首次数为空");
						return ;
					}
					if(parseInt(startNumber)>parseInt(endNumber))
					{
						alert("首次数不能大于末次数");
						return false;
					}
				}
				if(startTime!=null&&startTime!=""){
						document.getElementById("locationId").value=5;
						//ObjectForm.action="<%=contextPath%>/cus/cus!openCusCountList.action?location=5";
					}else
					{
						document.getElementById("locationId").value="<s:property value='location' />";
					}
					ObjectForm.action="<%=contextPath%>/cus/cus!openCusCountList.action";
					ObjectForm.submit();
					
				}
			  }
	}
			
			function addCustomer() {
				window.location.href = "<%=contextPath%>/cus/cus!initAddCustomer.action";
			}
			
			function updateCustomer(id) {
				window.location.href = "<%=contextPath%>/cus/cus!initUpdateCustomer.action?customer.cusId=" + id;
			}
			
			function resetKey() {
				$("#op0").attr("selected","selected");
				$("input[name=queryCustomerCondition.email]").val("");
				$("input[name=queryCustomerCondition.mobile]").val("");
				$("input[name=queryCustomerCondition.startNumber]").val("0");
				$("input[name=queryCustomerCondition.endNumber]").val("999999");
				$("input[name=startTime]").val("");
				$("input[name=endTime]").val("");
				$("#op1").attr("selected","selected");
				$("#op2").attr("selected","selected");
				$("#op3").attr("selected","selected");
				$("#subjectId").val(-1);				
			}
			
			function allCheck(cb) {
				$("input[name=ids]:checkbox").attr('checked', cb.checked);
			}
			
			function updatePwd(id) {
				window.location.href = "<%=contextPath%>/cus/cus!initUpdatePwd.action?customer.cusId=" + id;
			}
			function freeCourse(id,type){
				if(confirm("免费赠送需甚重使用，会重新初始化知识点？")){
				window.location.href="<%=contextPath%>/cus/cus!freeCourse.action?customer.cusId="+id+"&customer.cusType="+type;
				}
			}
			function toForgotPwd() {
				window.location.href = "<%=contextPath%>/cus/cus!toForgotPwd.action";
			}
			
			function viewCus(cusId) {
				if(cusId==0||cusId==""||cusId==null){
					alert("该用户不存在！");
					return false;
				}else{
					window.location.href = "<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=" + cusId;
				}
			}
			//切换
			function SpaceKc(h){			
				if(h==5)
				{
				}else
				{
					for(i=0;i<5;i++)
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
				for(i=0;i<3;i++){
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
				$("#cusFromUrl").val(${customer.cusFromUrl});
					
			//	var searchInfo= document.getElementById("searchInfo");				
			//	if(""==searchInfo.innerHTML||searchInfo.innerHTML==null)
				//{					
				//	searchInfo.innerHTML="查询结果";
				//}else
				//{
					
				//	var timeConnSTR= document.getElementById("timeConn");
			//		timeConnSTR.innerHTML="&nbsp;-&nbsp;";
			//	}			

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
			
			//自动刷新倒计时
			var startTime = new Date();
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
		        
		    }
		    
		   $().ready(
		   	function(){
		   		getRemainTime();	
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
		}
		</script>
	</head>
	<body onload="changeData()">
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">学员统计</font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<form name="searchForm" action="" method="post">
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
											<option value=" 00:00:00" >
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
										注册路径：
										<s:select name="customer.subjectId" id="subjectId"
											list="subjectList" listKey="subjectId"
											listValue="subjectName" headerKey="-1"
											headerValue="---请选择---" theme="simple" style="width: 155px">
										</s:select>
									</td>
									<td>
										用户类型：
										<select name="queryCustomerCondition.cusType"
											id="queryCustomerCondition.cusType" style="width: 155px">
											<option value="" id="op2">
												---请选择---
											</option>
											<option value="0">
												注册学员
											</option>
											<option value="1">
												内部学员
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										邮箱地址：
										<input type="text"
											value="<s:property value="queryCustomerCondition.email"/>"
											name="queryCustomerCondition.email" />
									</td>
									<td>
										&nbsp;&nbsp;手机号：
										<input type="text" id="mobile"
											value="<s:property value="queryCustomerCondition.mobile"/>"
											name="queryCustomerCondition.mobile" />
									</td>
								</tr>
								<tr>
									<td>
										登陆次数：
										<input type="text"
											value="<s:property value="queryCustomerCondition.startNumber"/>"
											id="startNumber" name="queryCustomerCondition.startNumber"
											style="width: 100px;" />
										至
										<input type="text"
											value="<s:property value="queryCustomerCondition.endNumber"/>"
											id="endNumber" name="queryCustomerCondition.endNumber"
											style="width: 100px;" />
										次
									</td>
									<td>
									域名来源：
									<select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
									<option value="" id="op3">---请选择---</option>
									<option value="1">highso.org.cn</option>
									<option value="2">highso.cn</option>
									<option value="3">highso.org</option>
									<option value="4">highso.net.cn</option>
									<option value="5">highso.com.cn</option>
									</select>
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
						<div id="timeClass" class="spa_tab spa_tjleft" >
							<ul>
								<s:if test="location==5">
								<li>
								<font style="font-size:12px; font-weight:600;" >	
									<s:property value="startTime" />:<s:property value="startHH"/>
									至
									<s:property value="endTime" />:<s:property value="endHH" /><a href="<%=contextPath%>/cus/cus!openCusCountList.action" style="display:inline; background:#fff; font-size:12px; color:#069;">其他方式查询</a>		
								</font>								
								</li>
								</s:if>
								<s:else>
								<li>
									<a
										href="<%=contextPath%>/cus/cus!openCusCountList.action?location=1"
										id="spa_kc_t1">今日</a>
								</li>
								<li>
									<a
										href="<%=contextPath%>/cus/cus!openCusCountList.action?location=2"
										id="spa_kc_t2">一周内</a>
								</li>
								<li>
									<a
										href="<%=contextPath%>/cus/cus!openCusCountList.action?location=3"
										id="spa_kc_t3">一个月内</a>
								</li>
								<li>
									<a
										href="<%=contextPath%>/cus/cus!openCusCountList.action?location=4"
										id="spa_kc_t4">三个月内</a>
								</li>
								<li>
									<a
										href="<%=contextPath%>/cus/cus!openCusCountList.action?location=0"
										id="spa_kc_t0">全部</a>
								</li>								
								</s:else>
							</ul>
							
							<div
								style="float: right; line-height: 28px; padding-right: 10px;">
								自动刷新倒计时：
								<font id='releaseTime'></font>&nbsp;&nbsp;&nbsp; 手动刷新按钮
								<input type="button" value="刷新"
									onclick="javascript:window.location.reload();" />
							</div>
						</div>					
						<div class="clear"></div>
						<!--
							修改  
						-->
						<div class="spa_tabcon dispblock spa_tabconbor" >
							
							<table width="99%" border="0" cellspacing="1" cellpadding="0"
								bgcolor="#c6d1d5">
								<tr style="font-weight: bold;">
									<td height="24" align="center" bgcolor="#d4e7f2">课程</td>
									<td height="24" align="center" bgcolor="#d4e7f2">全部用户</td>
									<td height="24" align="center" bgcolor="#d4e7f2">注册用户</td>
									<td height="24" align="center" bgcolor="#d4e7f2">面授用户</td>
									<td height="24" align="center" bgcolor="#d4e7f2">全部订单</td>
									<td height="24" align="center" bgcolor="#d4e7f2">支付宝订单</td>
									<td height="24" align="center" bgcolor="#d4e7f2">货到付款订单</td>
									<td height="24" align="center" bgcolor="#d4e7f2">网银在线订单</td>									
									<td height="24" align="center" bgcolor="#d4e7f2">全部订单(已付)</td>
									<td height="24" align="center" bgcolor="#d4e7f2">支付宝订单(已付)</td>
									<td height="24" align="center" bgcolor="#d4e7f2">货到付款订单(已付)</td>
									<td height="24" align="center" bgcolor="#d4e7f2">网银在线订单(已付)</td>
								</tr>																	
									<s:iterator var="subjectCounts" value="subjectCountsDTOList"  >									
										<tr>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.subjectName"/>
											</td>											
											<td height="28" align="center" bgcolor="#FFFFFF">												
												<s:property value="#subjectCounts.qbRegist" />
											</td>										
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.qbRegist" />
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">		
												——										
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.contracts" />
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.zfbContracts" />
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.hdfkContracts" />
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.wyzxContracts" />
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.payedContracts" />
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.zfbPayedContracts" />
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.hdfkPayedContracts" />
											</td>
											<td height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="#subjectCounts.wyzxPayedContracts" />
											</td>
										</tr>	
									</s:iterator>
									<tr style="font-weight:800;">
											<td  height="28" align="center" bgcolor="#FFFFFF" >总&nbsp;&nbsp;&nbsp;计
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="customerDTO.qbRegist" />
											</td>											
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="customerDTO.qbRegistWb" />
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="customerDTO.qbRegistNb" />
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="customerDTO.qbContract" /> 
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="zfbCustomerDTO.qbContract" />
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="hdfkCustomerDTO.qbContract" />
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property value="customerDTO.wyzxContract" />
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property	value="customerDTO.qbPayContract" />
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF"> 
												<s:property	value="zfbCustomerDTO.qbPayContract" /> 
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property	value="hdfkCustomerDTO.qbPayContract" />
											</td>
											<td  height="28" align="center" bgcolor="#FFFFFF">
												<s:property	value="customerDTO.wyzxPayContract" />
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

		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td>
						<font class="lists_fleft">统计列表</font>
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
						<table width="100%" border="0" width="100%" cellspacing="1"
							cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
							<s:if test="monthList.size!=0">
								<tr class="lists_infobg">
									<td width="15%">
										<font style="font-weight: bold">月份</font>
									</td>
									<td width="13%">
										<font style="font-weight: bold">注册量</font>
									</td>
									<td width="12%">
										<font style="font-weight: bold">订单量</font>
									</td>
									<td width="5%">
										<font style="font-weight: bold">已付订单量</font>
									</td>
									<td width="13%">
										<font style="font-weight: bold">成交百分比</font>
									</td>
								</tr>
								<s:iterator value="monthList" id="month">
									<tr>
										<td>
											<a
												href="<%=contextPath%>/cus/cus!getMonthDay.action?monthDay=<s:date format="yyyy-MM" name="#month.regTime" />"><s:date
													format="yyyy-MM" name="#month.regTime" /> </a>
										</td>
										<td>
											<s:property value="#month.monthRigist" />
											人
										</td>
										<td>
											<s:property value="#month.monthContract" />
											笔
										</td>
										<td>
											<s:property value="#month.monthPayContract" />
											笔
										</td>
										<td>
											<s:property value="#month.percent" />
											%
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td>
										<div class="spa_tab spa_tjleft">
											<ul>
												<li style="background: #ffffff;">
													<a
														href="<%=contextPath%>/cus/cus!openCusCountList.action?dateLocation=0&startTime=${startTime}&endTime=${endTime}&customer.subjectId=${customer.subjectId}&customer.cusFromUrl=${customer.cusFromUrl}&queryCustomerCondition.currentPage=1"
														id="spa_gc_t0" style="background: #eee; color: #333;">学员列表</a>
												</li>
												<li>
													<a
														href="<%=contextPath%>/cus/cus!openCusCountList.action?dateLocation=1&startTime=${startTime}&endTime=${endTime}&customer.subjectId=${customer.subjectId}&customer.cusFromUrl=${customer.cusFromUrl}&queryContractCondition.currentPage=1"
														id="spa_gc_t1" onclick="SpaceR(1)">订单列表</a>
												</li>
												<li>
													<a
														href="<%=contextPath%>/cus/cus!openCusCountList.action?dateLocation=2&startTime=${startTime}&endTime=${endTime}&customer.subjectId=${customer.subjectId}&customer.cusFromUrl=${customer.cusFromUrl}&queryContractCondition.currentPage=1"
														id="spa_gc_t2" onclick="SpaceR(2)">已付订单列表</a>
												</li>
											</ul>
										</div>
										<div class="spa_tabcon dispblock spa_tabconbor" id="spa_gc0">
											<table width="100%" border="0" width="100%" cellspacing="1"
												cellpadding="0" class="lists_info" onmouseover="changeto()"
												onmouseout="changeback()">
												<tr class="lists_infobg">
										<td width="5%">
											昵称
										</td>
										<td width="15%">
											电子邮件
										</td>
										<td width="10%">
											注册路径
										</td>
										<td width="5%">
											域名来源
										</td>
										<td width="15%">
											手机号码
										</td>
										<td width="9%">
											上次登录地
										</td>
										<td width="8%">
											用户类型
										</td>
										<td width="5%">
											登录次数
										</td>
										<td width="8%">
											注册时间
										</td>
										<td width="8%">
											支付数/订单数
										</td>
										<td width="*">
											操作
										</td>
									</tr>
									<s:iterator value="page.pageResult" id="customer"
										status="status">
										<tr>
											<!--  <td><input type="checkbox" name="ids" value="<s:property value="cusId"/>"/></td>-->
											<td>
												<s:property value="cusName" />
											</td>
											<td>
												<a href="#" onclick="viewCus(<s:property value="cusId" />)"><s:property
														value="email" /> </a>
											</td>
											<td>
												<s:property value="cusSubject.subjectName" />
											</td>
											<td>
												<s:property value="cusFromUrl"/>
											</td>
											<td>
												<s:property value="mobile" />
											</td>
											<td>
												<s:if test="loginRecordList.size>0">
													<s:property value="loginRecordList[0].address" />
												</s:if>
											</td>
											<td>
												<s:if test="cusType==0">
									注册学员
									</s:if>
												<s:if test="cusType==1">
									内部学员
									</s:if>
											</td>
											<td>
												<s:property value="loginTimes" />
											</td>
											<td>
												<s:date format="yyyy-MM-dd HH:mm:ss" name="regTime" />
											</td>
											<td>
												<s:if test="contractCount>0">
													<s:if test="contractStatus>0">
														<a
															href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">
															<font color="red"><s:property
																	value="contractStatus" />/<s:property
																	value="contractCount" />|已付款</font> </a>
													</s:if>
													<s:else>
														<a
															href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">
															<font color="green"><s:property
																	value="contractStatus" />/<s:property
																	value="contractCount" />|未付款</font> </a>
													</s:else>
												</s:if>
												<s:else>
												0/0
											</s:else>
											</td>
											<td>
											  <a href="#"
													onclick="updateCustomer(<s:property value="cusId" />)">修改</a>
												<a href="#"
													onclick="updatePwd(<s:property value="cusId" />)">修改密码</a>
												<!--	<a href="#"
													onclick="freeCourse(<s:property value="cusId" />,<s:property value="cusType" />)">赠送课程</a>
													-->
											</td>
										</tr>
									</s:iterator>
											</table>
										</div>
										<div class="spa_tabcon dispblock spa_tabconbor" id="spa_gc1"
											style="display: none;">
											<table width="100%" border="0" width="100%" cellspacing="1"
												cellpadding="0" class="lists_info" onmouseover="changeto()"
												onmouseout="changeback()">
												<tr class="lists_infobg">
									<td>
										订单编号
									</td>
									<td>
										用户email
									</td>
									<td>
										推广来源
									</td>
									<td>
										销售/代理商
									</td>
									<td>
										域名来源
									</td>
									<td>
										下单时间
									</td>
									<td>
										付款时间
									</td>
									<td>
										金额
									</td>
									<td>
										状态
									</td>
									<td>
										支付类型
									</td>
									<td>
										访问次数
									</td>
									<td colspan="1">
										操作
									</td>
								</tr>
								<s:if test="page.pageResult.size()!=0">
									<s:iterator value="page.pageResult" id="contract">
										<tr>
											<td>
												<a
													href="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action?cashRecord.ctId=<s:property value="#contract.id"/>&cashRecord.cusId=<s:property value="#contract.cusId"/>"><s:property
														value="#contract.contractId" /> </a>
											</td>
											<td>
													<a href="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email=<s:property value="#contract.customer.email"/>"><s:property	value="#contract.customer.email" /> </a>
													&nbsp;&nbsp;
													<s:if test="#contract.payType==2">
													｜&nbsp;&nbsp;<a href='<%=contextPath%>/cou/address!toContractCustomerAddress.action?address.id=<s:property value="#contract.cusIdAddress"/>'><font color="green">地址</font></a>
													｜&nbsp;&nbsp;<a href="javascript:openWin(<s:property value="#contract.id"/>)"><font color="green">详细</font></a>
													</s:if>
											</td>
											<td>
												<s:property value="#contract.webFrom" />
											</td>
											<td>
												<s:property value="#contract.webAgent" />
											</td>
											<td>
												<s:property value="#contract.contractFromUrl" />
											</td>
											<td>
												<s:date name="#contract.createTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:date name="#contract.payTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:property value="#contract.contractSumMoney" />
											</td>
											<td>
												<s:if test="#contract.payType==2">
													<s:if test="#contract.status==0">未激活</s:if>
													<s:elseif test="#contract.status==1">已激活</s:elseif>
													<s:elseif test="#contract.status==3">已取消</s:elseif>
												</s:if>
												<s:elseif test="#contract.payType==1">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==0">
													<s:if test="#contract.status==2">赠送</s:if>
													<s:elseif test="#contract.status==4">赠送/修复</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==3">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
												</s:elseif>
											</td>
											<td>
												<s:if test="#contract.payType==1">
												支付宝/网银
												</s:if>
												<s:elseif test="#contract.payType==3">
												网银在线/网银
												</s:elseif>
												<s:elseif test="#contract.payType==2">
												货到付款
												</s:elseif>
												<s:elseif test="#contract.payType==0">
												赠送/修复
												</s:elseif>
											</td>
											<td>
												<s:property value="#contract.callSum" />
											</td>
											<td>
												
											</td>
										</tr>
										<tr id="god<s:property value="#contract.id"/>"
											style="display: none">
											<td colspan="10">
												<div class="wen_info_sr mtop-10 bor1blue font_hui">
													<div style="margin: 0 auto; width: 100%;">
														<s:if test="#contract.codList.size>0">
															<s:iterator value="#contract.codList" id="newCod">
																<div style="width: 40%; float: left;">
																	<table>
																		<tr>
																			<td>
																				<s:property value="#newCod.codContent" />
																			</td>
																			<td>
																				&nbsp;
																			</td>
																			<td>
																				&nbsp;
																			</td>
																			<td>
																				&nbsp;
																			</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td>
																				状态：
																				<s:if test="#newCod.codStatus==1">
				  										等待发货
				  										</s:if>
																				<s:elseif test="#newCod.codStatus==2">
				  										等待送达
				  										</s:elseif>
																				<s:elseif test="#newCod.codStatus==3">
				  										等待结款
				  										</s:elseif>
																				<s:elseif test="#newCod.codStatus==4">
				  										等待激活
				  										</s:elseif>
																			</td>
																			<td>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：
																				<s:date name="#newCod.codTime"
																					format="yyyy-MM-dd HH:mm:ss" />
																			</td>
																			<td>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人：
																				<s:property value="#newCod.user.loginName" />
																			</td>
																		</tr>
																	</table>

																</div>
															</s:iterator>
														</s:if>
														<s:else>
				  									　　无状态
				  									</s:else>
													</div>
												</div>
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td align="center" colspan="11">
											没有订单!
										</td>
									</tr>
								</s:else>
											</table>
										</div>
										<div class="spa_tabcon dispblock spa_tabconbor" id="spa_gc2"
											style="display: none;">
											<table width="100%" border="0" width="100%" cellspacing="1"
												cellpadding="0" class="lists_info" onmouseover="changeto()"
												onmouseout="changeback()">
												<tr class="lists_infobg">
									<td>
										订单编号
									</td>
									<td>
										用户email
									</td>
									<td>
										推广来源
									</td>
									<td>
										销售/代理商
									</td>
									<td>
										域名来源
									</td>
									<td>
										下单时间
									</td>
									<td>
										付款时间
									</td>
									<td>
										金额
									</td>
									<td>
										状态
									</td>
									<td>
										支付类型
									</td>
									<td>
										访问次数
									</td>
									<td colspan="1">
										操作
									</td>
								</tr>
								<s:if test="page.pageResult.size()!=0">
									<s:iterator value="page.pageResult" id="contract">
										<tr>
											<td>
												<a
													href="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action?cashRecord.ctId=<s:property value="#contract.id"/>&cashRecord.cusId=<s:property value="#contract.cusId"/>"><s:property
														value="#contract.contractId" /> </a>
											</td>
											<td>
													<a href="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email=<s:property value="#contract.customer.email"/>"><s:property	value="#contract.customer.email" /> </a>
													&nbsp;&nbsp;
													<s:if test="#contract.payType==2">
													｜&nbsp;&nbsp;<a href='<%=contextPath%>/cou/address!toContractCustomerAddress.action?address.id=<s:property value="#contract.cusIdAddress"/>'><font color="green">地址</font></a>
													｜&nbsp;&nbsp;<a href="javascript:openWin(<s:property value="#contract.id"/>)"><font color="green">详细</font></a>
													</s:if>
											</td>
											<td>
												<s:property value="#contract.webFrom" />
											</td>
											<td>
												<s:property value="#contract.webAgent" />
											</td>
											<td>
												<s:property value="#contract.contractFromUrl" />
											</td>
											<td>
												<s:date name="#contract.createTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:date name="#contract.payTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:property value="#contract.contractSumMoney" />
											</td>
											<td>
												<s:if test="#contract.payType==2">
													<s:if test="#contract.status==0">未激活</s:if>
													<s:elseif test="#contract.status==1">已激活</s:elseif>
													<s:elseif test="#contract.status==3">已取消</s:elseif>
												</s:if>
												<s:elseif test="#contract.payType==1">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==0">
													<s:if test="#contract.status==2">赠送</s:if>
													<s:elseif test="#contract.status==4">赠送/修复</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==3">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
												</s:elseif>
											</td>
											<td>
												<s:if test="#contract.payType==1">
												支付宝/网银
												</s:if>
												<s:elseif test="#contract.payType==3">
												网银在线/网银
												</s:elseif>
												<s:elseif test="#contract.payType==2">
												货到付款
												</s:elseif>
												<s:elseif test="#contract.payType==0">
												赠送/修复
												</s:elseif>
											</td>
											<td>
												<s:property value="#contract.callSum" />
											</td>
											<td>
											
											</td>
										</tr>
										<tr id="god<s:property value="#contract.id"/>"
											style="display: none">
											<td colspan="10">
												<div class="wen_info_sr mtop-10 bor1blue font_hui">
													<div style="margin: 0 auto; width: 100%;">
														<s:if test="#contract.codList.size>0">
															<s:iterator value="#contract.codList" id="newCod">
																<div style="width: 40%; float: left;">
																	<table>
																		<tr>
																			<td>
																				<s:property value="#newCod.codContent" />
																			</td>
																			<td>
																				&nbsp;
																			</td>
																			<td>
																				&nbsp;
																			</td>
																			<td>
																				&nbsp;
																			</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td>
																				状态：
																				<s:if test="#newCod.codStatus==1">
				  										等待发货
				  										</s:if>
																				<s:elseif test="#newCod.codStatus==2">
				  										等待送达
				  										</s:elseif>
																				<s:elseif test="#newCod.codStatus==3">
				  										等待结款
				  										</s:elseif>
																				<s:elseif test="#newCod.codStatus==4">
				  										等待激活
				  										</s:elseif>
																			</td>
																			<td>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：
																				<s:date name="#newCod.codTime"
																					format="yyyy-MM-dd HH:mm:ss" />
																			</td>
																			<td>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人：
																				<s:property value="#newCod.user.loginName" />
																			</td>
																		</tr>
																	</table>

																</div>
															</s:iterator>
														</s:if>
														<s:else>
				  									　　无状态
				  									</s:else>
													</div>
												</div>
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td align="center" colspan="11">
											没有订单!
										</td>
									</tr>
								</s:else>
											</table>
										</div>
									</td>
								</tr>
							</s:else>

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
						<s:if test="monthList.size==0">
							<jsp:include page="/back/jsp/common/showPage.jsp" />
						</s:if>
					</td>
					<td>
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>
		</div>
	</body>
	<script language="javascript">
	var userType=document.getElementById("queryCustomerCondition.cusType");
	for (i=0;i<userType.options.length;i++)
	{ 
	     if(userType.options[i].value=='${queryCustomerCondition.cusType}')
	     {
	         userType.options[i].selected=true;break;
	     } 
	}
	
	</script>
</html>
