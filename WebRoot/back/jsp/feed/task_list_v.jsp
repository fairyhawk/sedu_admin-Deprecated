<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>任务详情统计列表</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/js-charts/jscharts.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/js-charts/jscharts.plug.mb.js"></script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">任务详情统计列表</font>
				<font class="lists_fright">
					<a href="<%=contextPath%>/feed/back!exportStatisticsPageData.action">导出数据</a>
					<a href="<%=contextPath%>/feed/back!downStatisticsData.action">下载</a>
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr class="lists_infobg">
							<td>
								任务ID
							</td>
							<td>
								任务名称
							</td>
							<td>
								计划总次数
							</td>
							<td>
								1_实际发出数
							</td>
							<td>
								1_激活数
							</td>
							<td>
								2_实际发出数
							</td>
							<td>
								2_激活数
							</td>
							<td>
								3_实际发出数
							</td>
							<td>
								3_激活数
							</td>
							<td>
								3天发出邮件数
							</td>
							<td>
								3天累计激活数
							</td>
							<td>
								3天累计激活数率
							</td>
							<td>
								总激活数
							</td>
							<td>
								总激活率
							</td>
							<td>
								操作
							</td>
						</tr>
						<s:iterator value="page.pageResult">
							<tr>
								<td>
									<s:property value="id" />
								</td>
								<td>
									<s:property value="name" />
								</td>
								<td>
									<s:property value="planTotal" />
								</td>
								<td>
									<s:property value="day24Count" />
								</td>
								<td>
									<s:property value="uac24Count" />
								</td>
								<td>
									<s:property value="day48Count" />
								</td>
								<td>
									<s:property value="uac48Count" />
								</td>
								<td>
									<s:property value="day72Count" />
								</td>
								<td>
									<s:property value="uac72Count" />
								</td>
								<td>
									<s:property value="day72Count" />
								</td>
								<td>
									<s:property value="uac72Count" />
								</td>
								<td>
									<s:if test="day72Count > 0">
										<s:property value="@com.shangde.edu.feed.utils.Utils@percentage(uac72Count,day72Count,1)"/>%
									</s:if>
									<s:else>
										0.0%
									</s:else>
								</td>
								<td>
									<s:property value="totals" />
								</td>
								<td>
									<s:property value="@com.shangde.edu.feed.utils.Utils@percentage(totals,planTotal,1)"/>%
								</td>
								<td>
									<a href="javascript:void(0);" onclick="changePic(<s:property value="uac24Count" />,<s:property value="uac48Count" />,<s:property value="uac72Count" />,'<s:property value="name" />');">查看图</a>
								</td>
							</tr>
						</s:iterator>
				</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	<center>
		<!-- 趋势图区域 -->
		<div id="container" style="display: none;">
			<div id="title"></div>
			<div id="graph_1">Loading graph...</div>
		</div>
	</center>
</body>
</html>
<script type="text/javascript">
			/*
				注释：数组第一个元素属于占位符（起到后移一列的作用），从第二个元素开始将是有效数据对
			*/
			function changePic(s1,s2,s3,taskName){
				
				var dataArr = new Array();
				dataArr[0] = new Array(0,0);
				dataArr[1] = new Array(1,s1);
				dataArr[2] = new Array(2,s2);
				dataArr[3] = new Array(3,s3);
				
				$("#container").show();
				$("#title").html(taskName);
				drawPic_(dataArr);
			}
			
			
			function drawPic_(dataArr){
				var myData = new Array([0, 0], [1, 0], [2, 0], [3, 0]);
				
				if(dataArr != undefined){
					myData = dataArr;
				}
				var myChart = new JSChart('graph_1', 'line');
				myChart.patchMbString(true);
				myChart.setFontFamily("微软雅黑");
				myChart.setTitle("单条邮件三天激活趋势图");
				//myChart.setDataArray([[0, 0],[1, 500],[2, 800],[3, 1000]], 'blue');
				myChart.setDataArray(myData);
				myChart.setAxisPaddingBottom(40);
				myChart.setTextPaddingBottom(10);
				myChart.setAxisValuesNumberY(1);
				myChart.setAxisValuesFontSize(11);
				myChart.setIntervalStartY(0);
				//myChart.setIntervalEndY(200);
				
				myChart.setLabelX([0,'0']);
				myChart.setLabelX([1,'1']);
				myChart.setLabelX([2,'2']);
				myChart.setLabelX([3,'3']);
				
				myChart.setAxisValuesNumberX(1);
				myChart.setShowXValues(false);
				myChart.setTitleColor('#454545');
				myChart.setAxisValuesColor('#454545');
				myChart.setLineColor('#A4D314', 'green');
				myChart.setLineColor('#BBBBBB', 'gray');
				myChart.setTooltip([1, "test", "blue"]);
			
				myChart.setTooltip(myData[1]);
				myChart.setTooltip(myData[2]);
				myChart.setTooltip(myData[3]);
				
				myChart.setFlagColor('#9D16FC');
				myChart.setFlagRadius(4);
				myChart.setBackgroundImage('<%=contextPath%>/back/images/feed/chart_bg.jpg');
				myChart.setSize(616, 321);
				myChart.draw();
			}
		</script>