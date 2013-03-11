<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>订单实收统计</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=contextPath%>/back/style/jquery.jqplot.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextPath%>/back/style/examples.css" />
		<!--[if IE]><script language="javascript" type="text/javascript" src="<%=contextPath%>/back/script/jQueryPlot/excanvas.min.js"></script><![endif]-->
		<!-- BEGIN: load jquery -->
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jquery-1.4.2.min.js"></script>
		<!-- END: load jquery -->
		<!-- BEGIN: load jqplot -->
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jquery.jqplot.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jqplot.cursor.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jqplot.dateAxisRenderer.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jqplot.canvasTextRenderer.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jqplot.categoryAxisRenderer.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jqplot.pieRenderer.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jqplot.barRenderer.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryPlot/jqplot.canvasAxisTickRenderer.js"></script>
		<style type="text/css">
.jqplot-cursor-legend {
	width: 160px;
	font-family: "Courier New";
	font-size: 0.85em;
}

td.jqplot-cursor-legend-swatch {
	width: 1.3em;
}

div.jqplot-cursor-legend-swatch { /*      width: 15px;*/
	
}
</style>
		<script type="text/javascript" language="javascript">
		$(document).ready(function(){
	      	var s='<s:property value="radios"/>';
	      	if(s==""||s=="m"){
				$("#monthTimeDiv").css("display","");
				$("#customTimeDiv").css("display","none");
				$("#monthTime").attr("checked","checked");
	      	}
	      	if(s=="c"){
				$("#monthTimeDiv").css("display","none");
				$("#customTimeDiv").css("display","");
				$("#customTime").attr("checked","checked");
	      	}
	      	var webtype='<s:property value="webType"/>';
	      	if(webtype!=0){      		
	      		$("#webType").attr("value",webtype);
	      	}
	      	var contractFromUrl='<s:property value="contractFromUrl"/>';
	        if(contractFromUrl!=null||contractFromUrl!=""){
	        	$("#contractFromUrl").attr("value",contractFromUrl);
	        }   
		  	$(":radio").click(function(){
				if($("#monthTime").attr("checked")){
					$("#monthTimeDiv").css("display","");
					$("#customTimeDiv").css("display","none");
				}
				if($("#customTime").attr("checked")){
					$("#monthTimeDiv").css("display","none");
					$("#customTimeDiv").css("display","");
				}
		  	});
			
			
	      	$.jqplot.config.enablePlugins = true;
	      	var yaxismin=0;//全部订单起始x坐标
	      	var y2axismin=0;//支付订单起始x坐标
	      	var numberTicksmax=8;//x坐标行数
						 //全部订单 
			 var InPr =<s:property value="contractPr"/>;

			//支付成功的订单
			var OutPr =<s:property value="contractPayPr"/>;
 
	      var ticks =<s:property value="datePr"/>;
	      plot1 = $.jqplot('chart1', [InPr, OutPr], {
	      	title: '订单统计一览图', //标题
	      	
	        //seriesDefaults:{neighborThreshold:0, showMarker: false},// fill:true 阴影显示 注释掉后会显示点
	        series:[
	          {label:'实收金额', yaxis:'y2axis'},
	          {label:'支付成功'}
	        ],
	        cursor:{
	          showVerticalLine:true,//是否显示竖线
	          showTooltip: true,//鼠标移动时是否显示订单量
	          followMouse: true,//订单量是否跟随鼠标移动，false显示在右下角，true随鼠标移动
	          showTooltipDataPosition:true,
	          zoom:false,//是否允许局部分析
	          intersectionThreshold:6,
	          tooltipFormatString: '%s 时间:%s, 数量:%s'
	        },
	        legend:{
	        	show: true,//显示 全部订单 支付成功
	            location:'nw'
	        },
	        axesDefaults:{},
	        axes: { 
               yaxis: {min: yaxismin, numberTicks:numberTicksmax},//全部订单起始x坐标
	           y2axis:{min: y2axismin, numberTicks:numberTicksmax},//支付订单起始x坐标
	           xaxis: {
	              renderer:$.jqplot.DateAxisRenderer, 
	              rendererOptions: {
           			tickRenderer: $.jqplot.CanvasAxisTickRenderer//日期要斜着显示，需要配置此项目
         		  },
	              ticks: ticks,
	              tickOptions:{
	              	formatString:'%m/%d',
                    fontFamily:'Tahoma',
                    angle:-60 // 日期旋转角度
	              }  ,
	              tickInterval: "1 days"
	          }
	      } 
      }); 
    });
    
		function formatDate(time){
		 	  var time1 = time.split("-");
		 	 var date =new Date;
		 	  if(time1.length==3){
		      	 date =new Date(time1[0],(time1[1]-1),time1[2],0,0);
		 	  }else if(time1.length==2){
		 	     date =new Date(time1[0],(time1[1]-1),0,0,0);
		 	  }else{
		 	    date =new Date(time1[0],0,0,0,0);
		 	  }
		     return date;
		}

    
    	function resetKey() {
				$("#op1").attr("selected","selected");
				$("input[name=startTime]").val("");
				$("input[name=endTime]").val("");
				$("#op5").attr("selected","selected");
				$("#subjectId").val(-1);				
		}
		
		
		function search(ObjectForm){
			if($("#monthTime").attr("checked")){
				ObjectForm.action="<%=contextPath%>/cus/cus!getMonthDay.action";
				ObjectForm.submit();
			}
			if($("#customTime").attr("checked")){
			  var startTime=document.getElementById("startTime").value;
			  var endTime=document.getElementById("endTime").value;
			  if(startTime!=""){
				  if(startTime!=null&&endTime!=null){
				  if(startTime>endTime){
					 alert("结束时间大于开始时间!");
					 return;
				  }else{
					  	var diff=(Date.parse(formatDate(endTime))-Date.parse(formatDate(startTime)))/(24*60*60*1000);
						ObjectForm.action="<%=contextPath%>/cus/cus!getMonthDay.action?diffDay="+diff;
						ObjectForm.submit();
	//			  		var monthDay=document.getElementById("month").value;
	//					ObjectForm.action="<%=contextPath%>/cus/cus!getMonthDayByCondition.action?month = " + monthDay;
	//					ObjectForm.submit();
					}
				}								  
			  }else{
				  alert("请选择开始时间");
				  return ;
			  }
			}
		}
		function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
		changeWebType('<s:property value="webType"/>');
		changeContractFromUrl('<s:property value="queryContractCondition.contractFromUrl"/>');
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
		
		function changeWebType(webType){
			$.each($("#webType option"),function(){
				if(this.value == webType) {
					this.selected = true;
				}
			})
		
		}
		function changeContractFromUrl(contractFromUrl){
			$.each($("#contractFromUrl option"),function(){
				if(this.value == contractFromUrl) {
					this.selected = true;
				}
			})
		
		}
</script>
	</head>
	<!--  
	<body onload="changeData()">
	-->
	<body>
		<div>
			<form name="searchForm" method="post">
				<s:hidden name="queryCustomerCondition.currentPage" value="1" />
				<s:iterator value="monthList" id="month">

				</s:iterator>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
						<td class="lists_top">
							<font class="lists_fleft">订单实收统计</font>
							<font class="lists_fright"> </font>
						</td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
					</tr>
					<tr>
						<td class="lists_bor"></td>
						<td>
						<div class="msg-yw">
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists">
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td style="text-align: left;">
													分类查询：<input type="radio" checked="checked" name="radios" id="monthTime" value="m"/>月份
															<input type="radio" name="radios" id="customTime" value="c"/>自定义时间段
												</td>
											</tr>
											<tr>
												<td style="text-align: left;">
													<div id="customTimeDiv" style="margin: 10px 0px 10px 0px">
													开始时间
													<input type="text" name="startTime" readonly id="startTime"
														onclick="WdatePicker()" onfocus="inputOnFocus()"
														value="${startTime}" />
													- 结束时间
													<input type="text" name="endTime" readonly id="endTime"
														onclick="WdatePicker()" onfocus="inputOnFocus()"
														value="${endTime}" />
													</div>
													<div id="monthTimeDiv" style="margin: 10px 0px 10px 0px">
													<select name="years" id="years">
														<option value="2010">2010</option>
														<option value="2011">2011</option>
														<option value="2012">2012</option>
														<option value="2013">2013</option>
														<option value="2014">2014</option>
														<option value="2015">2015</option>
													</select>
													<select name="months" id="months">
														<option value="01">01</option>
														<option value="02">02</option>
														<option value="03">03</option>
														<option value="04">04</option>
														<option value="05">05</option>
														<option value="06">06</option>
														<option value="07">07</option>
														<option value="08">08</option>
														<option value="09">09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
													</div>	
												</td>
											</tr>
											<tr>
												<td style="text-align: left;">
													域名：
													<select name="queryCustomerCondition.contractFromUrl"
														id="contractFromUrl" style="width: 155px">
														<option value="" id="op5">
															---请选择---
														</option>
														<option id="op0" value="highso.org.cn">
															highso.org.cn
														</option>
														<option value="highso.cn">
															highso.cn
														</option>
														<option value="highso.org">
															highso.org
														</option>
														<option value="highso.com.cn">
															highso.com.cn
														</option>
														<option value="highso.net.cn">
															highso.net.cn
														</option>
													</select>

													部门：
													<select id="webType" name="queryCustomerCondition.webType">
														<option id="op1" value="0">
															---请选择---
														</option>
														<option value="1">
															市场部
														</option>
														<option value="2">
															项目部
														</option>
													</select>
												</td>
											</tr>
											<tr>
												<td style="text-align: left;">
													按项目查询商品：
													<s:select id="subjectId"
														name="queryCustomerCondition.subjectId" list="subjectList"
														listKey="subjectId" listValue="subjectName"
														headerValue="---请选择---" headerKey="-1"
														style="width: 155px">
													</s:select>
												</td>

											</tr>
											<tr>
												<td>
													<input type="button" value="查询"
														onclick="search(document.searchForm)" />
													<input type="button" value="清空" onclick="resetKey()"></input>
												</td>

											</tr>
										</table>
									</td>
								</tr>
							</table>
							</div>
						</td>
						<td class="lists_tright lists_bor2"></td>
					</tr>
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
						<td class="lists_bottom"></td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div align="center" class="jqPlot" id="chart1" style="height: 320px; width: 100%;"></div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top">
						<font class="lists_fleft"></font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					<td class="lists_bor"></td>
					<td>
					 
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
							<tr class="lists_infobg">
								<td width="15%">
									<font style="font-weight: bold">日期</font>
								</td>
								<td width="13%">
									<font style="font-weight: bold">总金额(元)</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">已付订单量</font>
								</td>
								
								<td width="12%">
									<font style="font-weight: bold">订单量</font>
								</td>
								
								<td width="13%">
									<font style="font-weight: bold">成交百分比</font>
								</td>
								<td width="13%">
									<font style="font-weight: bold">注册量</font>
								</td>
							</tr>
							<!-- 
							<s:iterator value="monthDayList" id="monthDay" status="status">
								<tr>
									<td>
										<a
											href="<%=contextPath%>/cus/cus!showCustomerList.action?dateDay=<s:date format="yyyy-MM-dd" name="#monthDay.regTime"/> &&queryCustomerCondition.currentPage=1"><s:date
												format="yyyy-MM-dd" name="#monthDay.regTime" /> </a>
										<s:if test="#status.count==1">
											<input type="hidden" id="month" name="month"
												value="<s:date format="yyyy-MM" name="#monthDay.regTime"/>"></input>
										</s:if>
									</td>
									<td>
										<s:property value="#monthDay.monthPaySumMoney" />
									</td>
									<td>
										<s:property value="#monthDay.monthPayContract" />
										笔
									</td>
									<td>
										<s:property value="#monthDay.monthContract" />
										笔
									</td>
									<td>
										<s:property value="#monthDay.percent" />
										%
									</td>
									<td>
										<s:property value="#monthDay.monthRigist" />
										人
									</td>
								</tr>
							</s:iterator>
							 -->
							<s:iterator value="monthDayNewList" id="monthDayNew">
								<tr>
									<td>
										<s:if test="#monthDayNew.allOrders==null&&#monthDayNew.allStudent==null">	
											<s:property value="#monthDayNew.dt" />
										</s:if>
										<s:else>
											<a href="<%=contextPath%>/cus/cus!showCustomerList.action?dateDay=<s:property value="#monthDayNew.dt" /> &&queryCustomerCondition.currentPage=1">
											<s:property value="#monthDayNew.dt" /></a>
										</s:else>
									</td>
									<td>
										<s:if test="#monthDayNew.totalAmount!=null">		
											<s:property value="#monthDayNew.totalAmount" />									
										</s:if>
										<s:else>0</s:else>
									</td>
									<td>
										<s:if test="#monthDayNew.ordersPaid!=null">
											<s:property value="#monthDayNew.ordersPaid" />
										</s:if>
										<s:else>0</s:else>
										笔
									</td>
									<td>
										<s:if test="#monthDayNew.allOrders!=null">
											<s:property value="#monthDayNew.allOrders" />
										</s:if>
										<s:else>0</s:else>
										笔
									</td>
									<td>
										<s:if test="#monthDayNew.allOrders!=null">
											<fmt:formatNumber value="${monthDayNew.ordersPaid*100/monthDayNew.allOrders}" pattern="#.##"/>%
										</s:if>
										<s:else>0%</s:else>							
									</td>
									<td>
										<s:if test="#monthDayNew.allStudent!=null">
											<s:property value="#monthDayNew.allStudent" />
										</s:if>
										<s:else>0</s:else>
										人
									</td>
								</tr>
							</s:iterator>							
						</table>
						
					</td>
					<td class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
					<td class="lists_bottom"></td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
				</tr>
			</table>
			<script type="text/javascript" language="javascript">
            $("#years").attr("value",'<s:property value="years"/>');
	        $("#months").attr("value",'<s:property value="months"/>');
            </script>
	</body>
</html>