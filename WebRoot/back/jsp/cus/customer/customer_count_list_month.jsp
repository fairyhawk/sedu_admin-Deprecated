
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员按月统计</title>
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
			  if(startTime!=null&&endTime!=null){
			  if(startTime>endTime){
				 alert("结束时间大于开始时间!");
				 return;
			  }else{
			  		document.getElementById("locationId").value=100;
					ObjectForm.action="<%=contextPath%>/cus/cus!customerDisplayInfo.action";
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
		
		function test(){
			alert("test");
		}
		</script>
	</head>
	<body >
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
			<tr >
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top">
					<font class="lists_fleft">按月统计</font>
				</td>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
			</tr>
			<tr>
				<td width="12" class="lists_bor">
				</td>
				<td>
					<table border="0" width="100%" cellspacing="1"
						cellpadding="0" class="lists_info" onmouseover="changeto()"
						onmouseout="changeback()">
						<tr class="lists_infobg">
							<td width="15%">
								<font style="font-weight: bold">月份</font>							</td>
							<td width="13%">
								<font style="font-weight: bold">注册量</font>							</td>
							<td width="12%">
								<font style="font-weight: bold">订单量</font>							</td>
							<td width="5%">
								<font style="font-weight: bold">已付订单量</font>							</td>
							<td width="13%">
								<font style="font-weight: bold">成交百分比</font>							</td>
						    <td width="13%"><font style="font-weight: bold">订单转化率</font></td>
						</tr>
						<!--  
						<s:iterator value="monthList" id="month">
							<tr>
								<td>
									<a
										href="<%=contextPath%>/cus/cus!getMonthDay.action?monthDay=<s:date format="yyyy-MM" name="#month.regTime" />"><s:date
											format="yyyy-MM" name="#month.regTime" /> </a>								</td>
								<td>
									<s:property value="#month.monthRigist" />
									人								</td>
								<td>
									<s:property value="#month.monthContract" />
									笔								</td>
								<td>
									<s:property value="#month.monthPayContract" />
									笔								</td>
								<td>
									<s:property value="#month.percent" />
									%								</td>
							    <td>
								
								<s:property  value="#month.monthContract*100/#month.monthRigist"/>
  								%
							



								</td>
							</tr>
						</s:iterator>
						-->
						<s:iterator value="monthDayNewList" id="month">
							<tr>
								<td>
									<a
										href="<%=contextPath%>/cus/cus!getMonthDay.action?monthDay=<s:property value="#month.dt" /> "><s:property value="#month.dt" /> </a>								</td>
								<td>
								<s:if test="#month.allStudent!=null">
									<s:property value="#month.allStudent" />
									</s:if>
									<s:else>0
									</s:else>
									人	
								</td>
								<td>
									<s:if test="#month.allOrders!=null">
										<s:property value="#month.allOrders" />
									</s:if>
									<s:else>0</s:else>
									笔								
								</td>
								<td>
									<s:if test="#month.ordersPaid!=null">
									<s:property value="#month.ordersPaid" />
									</s:if>
									<s:else>0</s:else>
									笔								
								</td>
								<td>
									<s:if test="#month.allOrders!=null">
									<fmt:formatNumber value="${month.ordersPaid*100/month.allOrders}" pattern="#.##"/>
									</s:if>
									<s:else>0</s:else>
									%							
								</td>
							    <td>
							    	<s:if test="#month.allStudent!=null">
									<fmt:formatNumber value="${month.allOrders*100/month.allStudent}" pattern="#.##"/>
									</s:if>
									<s:else>0</s:else>
									%	
								</td>
							</tr>
						</s:iterator>
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
		</td>
		<td width="16" class="lists_tright lists_bor2">
		</td>
		</tr>
		</table>
	</body>
</html>
