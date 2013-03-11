<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>视频打分统计</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script>var bashPath = "<%=contextPath%>"</script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript">
			function createDateTwo(){
				var startTime=document.getElementById("createTimeBegin").value;
			    var endTime=document.getElementById("createTimeEnd").value;
			    if(startTime!='' && startTime>endTime){
				       alert("创建结束时间要大于开始时间!");
				       document.getElementById("createTimeEnd").value='';
				      return false;
			    }
			}
			function createDateOne(){
				var startTime=document.getElementById("createTimeBegin").value;
			    var endTime=document.getElementById("createTimeEnd").value;
			    if(endTime!='' && startTime>endTime){
				       alert("创建结束时间要大于开始时间!");
				       document.getElementById("createTimeBegin").value='';
				      return false;
			    }
			}
			
			function submitForm(){
				var subjectId = $("#subjectId").val();
				var startDate = $("#createTimeBegin").val();
				var endDate = $("#createTimeEnd").val();
				var videoName = encodeURI(encodeURI($("#videoName").val()));
				var currentPage = $("#currentPage").val();
				var url = "<%=contextPath %>/vst/level!getVlevelPage.action?condition.subjectId=" + subjectId +"&condition.startDate="+startDate + "&condition.endDate=" + endDate +"&condition.videoName="+videoName +"&condition.currentPage=" + currentPage;
				location.href = url;
			}
			
			function checkOutExcel(){
				var subjectId = $("#subjectId").val();
				var startDate = $("#createTimeBegin").val();
				var endDate = $("#createTimeEnd").val();
				var videoName = encodeURI(encodeURI($("#videoName").val()));
				var currentPage = $("#currentPage").val();
				var url = "<%=contextPath %>/vst/level!chkOutExcel.action?condition.subjectId=" + subjectId +"&condition.startDate="+startDate + "&condition.endDate=" + endDate +"&condition.videoName="+videoName +"&condition.currentPage=" + currentPage;
				location.href = url;
			}
			
			function searchByTime(type){
				var subjectId = $("#subjectId").val();
				var startDate = $("#createTimeBegin").val();
				var endDate = $("#createTimeEnd").val();
				var videoName = encodeURI(encodeURI($("#videoName").val()));
				var currentPage = $("#currentPage").val();
				var url = "<%=contextPath %>/vst/level!getVlevelPage.action?condition.subjectId=" + subjectId +"&condition.startDate="+startDate + "&condition.endDate=" + endDate +"&condition.videoName="+videoName +"&condition.currentPage=" + currentPage +"&timeType="+ type;
				location.href = url;
			}
			$().ready(function(){
				$('.calculate_num').dblclick(function(){
					var value = $(this).html();
					if($("#value" + this.id).val()!=null){
						value = $("#value" + this.id).val();
					}
					$("#" + this.id).empty();
					$("#" + this.id).append("<input type='text' class='calculate_hover' onblur='return calculate("+this.id+")' value='"+value+"' id='value"+this.id+"'/>");
					$("#value" + this.id).focus();
				});

				$('.statistics').click(function(){
					var subjectId = $("#subjectId").val();
					var startDate = $("#createTimeBegin").val();
					var endDate = $("#createTimeEnd").val();
					var videoName = encodeURI(encodeURI($("#videoName").val()));
					$(this).val("数据正在统计中......");
					$(this).attr("disabled", "disabled");
					$.ajax({
						url : bashPath + "/vst/level!ajaxStatistics.action",
						data : {
							"condition.startDate":startDate,
							"condition.endDate":endDate,
							"condition.videoName":videoName,
							"condition.subjectId":subjectId
						},
						type : "post",
						dataType : "json",
						cache : false,
						success : function(result) {
							if(result=="false"){
								$('.statistics').removeAttr("disabled");
								$('.statistics').val("改善总指数为:0");
							}else{
								$('.statistics').removeAttr("disabled");
								$('.statistics').val("改善总指数为:" + result);
							}
						},
						error : function(error) {
							alert('error');
						}
					  });
				});
			});
			function calculate(id){
				var re = /^[0-9]+.?[0-9]*$/;
				var weights = $("#value" + id).val();//权数
				if (!re.test(weights) || weights==""){
					alert("权数必须为数字!或不能为空!");
					return false;
				}
				var buyCount = $("#pointSoldCount" + id).val();
				if(isNaN(buyCount)){buyCount = 0;}
				var seeVideo = $("#opendUser" + id).val() / buyCount;
				if(isNaN(seeVideo)){seeVideo = 0;}
				var goodPingLv = $("#goodevaluateCount" + id).val() / $("#openedCount" + id).val();
				if(isNaN(goodPingLv)){goodPingLv = 0;}
				var badPingLv = $("#badevaluateCount" + id).val() / $("#openedCount" + id).val();
				if(isNaN(badPingLv)){badPingLv = 0;}
				var result = (weights * buyCount * seeVideo * goodPingLv) - (weights * buyCount * seeVideo * badPingLv);
				if(!isNaN(result)){
					$("#result" + id).html(round(result,2));
				}
				$("#" + id).html($("#value" + id).val());
			}
    	</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath %>/vst/level!getVlevelPage.action" method="get" name="vstForm" id="vstForm">
	<input type="hidden" name="condition.currentPage" id="currentPage" value="<s:property value="condition.currentPage" />"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">视频打分统计</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		
		<tr>
	            <td class="lists_bor"></td>
	            <td>
	              <div class="msg-zy">
				<table class="" border="0" cellspacing="0"  cellpadding="0">
						<tr>
						<td>
							所在项目:<s:select name="condition.subjectId" id="subjectId" list="subList" listKey="subjectId"
									listValue="subjectName" headerKey="-1" headerValue="--请选择专业--" theme="simple">
						 	</s:select>
						 </td>
						<td width="20px;"></td>
						<td>选择时间 ：<input type="text" name="condition.startDate" readonly="readonly" id="createTimeBegin" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
										onchange="createDateOne()"	 value="<s:date name="condition.startDate"  format="yyyy-MM-dd HH:mm:ss"/>" />
								-<input type="text" name="condition.endDate" readonly="readonly" id="createTimeEnd" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
										onchange="createDateTwo()"	 value="<s:date name="condition.endDate"  format="yyyy-MM-dd HH:mm:ss"/>"/>
						</td>
						<td width="80px;">&nbsp;&nbsp;&nbsp;&nbsp;视频名称:</td>
						<td width="40px;">
							<input type="text" name="condition.videoName" id="videoName" value="<s:property value="#condition.videoName" />"/>
						</td>
						
						<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:submitForm()"><img src="<%=contextPath%>/back/images/add_a.gif"/>查询</a></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:checkOutExcel()"><img src="<%=contextPath%>/back/images/add_a.gif"/>导出Excel</a></td>
						</tr>
						</table>
					 </td>		
					 
            <td class="lists_tright lists_bor2"></td>
        </tr>
        <tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<div class="clear"></div>
						<!--
							修改  
						-->
						<dir class="spa_tabcon dispblock spa_tabconbor">
								<div id="timeClass" class="spa_tab spa_tjleft">
										<a><input type="button" value="改善总指数统计" class="statistics"/></a>
								</div>
						</dir>
						<div class="spa_tabcon dispblock spa_tabconbor">
							<div id="timeClass" class="spa_tab spa_tjleft">
								<ul>
									<li>
										<a
											href="javascript:searchByTime(1)"
											id="spa_kc_t1">今日</a>
									</li>
									<li>
										<a
											href="javascript:searchByTime(2)"
											id="spa_kc_t2">一周内</a>
									</li>
									<li>
										<a
											href="javascript:searchByTime(3)"
											id="spa_kc_t3">一个月内</a>
									</li>
									<li>
										<a
											href="javascript:searchByTime(4)"
											id="spa_kc_t4">三个月内</a>
									</li>
									<li>
										<a
											href="javascript:searchByTime(5)"
											id="spa_kc_t0">全部</a>
									</li>
							</div>
							</ul>
							
						</div>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
        </form>
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							项目
						</td>
						<td>
							视频ID
						</td>
						<td>
							视频名称
						</td>
						<td>
							付费人数
						</td>
						<td>
							观看率
						</td>
						<td>
							评价率
						</td>
						<td>
							好评
						</td>
						<td>
							坏评
						</td>
						<td>
							课程权数
						</td>
						<td>
							课程改善指数
						</td>
					</tr>
					<s:iterator value="page.pageResult" var="vlevel" status="var">
						<tr>
							<td>
								<s:property value="#vlevel.subjectName"/>
							</td>
							<td>
								<s:if test="#vlevel.pointId==null">0</s:if><s:else><s:property value="#vlevel.pointId" /></s:else>
							</td>
							<td>
								<s:if test="#vlevel.videoName==null">0</s:if><s:else><s:property value="#vlevel.videoName" /></s:else>
							</td>
							<td>
								<s:if test="#vlevel.pointSoldCount==null">0</s:if><s:else><s:property value="#vlevel.pointSoldCount" /></s:else>
							</td>
							<td>
								<s:if test="#vlevel.opendUser==null||#vlevel.pointSoldCount==null||#vlevel.pointSoldCount<=0">0.0%</s:if><s:else>
								   <script>
									var kao= <s:property value="#vlevel.opendUser" /> / <s:property value="#vlevel.pointSoldCount" /> ;
									if(isNaN(kao)){
										document.write("");
									}else if(kao > 1){
										document.write("");
									}else{
										document.write(((kao * 100)+"").substring(0, 4) + "%");
									}
									</script>
								</s:else>
							</td>
							 
							<td>
							<s:if test="#vlevel.cusAll==null||#vlevel.openedCount==null">0.0%</s:if><s:else>
							<script>
								var lin= <s:property value="#vlevel.cusAll" /> / <s:property value="#vlevel.openedCount" /> ;
								if(isNaN(lin)){
									document.write("0.0%");
								}else if(lin > 1){
									document.write("100%");
								}else{
									document.write(((lin * 100)+"").substring(0, 4) + "%");
								}
							</script>
							</s:else>
							</td>
							<td>
							<s:if test="#vlevel.goodevaluateCount==0">0.0%</s:if><s:else>
							<script>
								var lin= <s:property value="#vlevel.goodevaluateCount" /> / <s:property value="#vlevel.openedCount" /> ;
								if(isNaN(lin)){
									document.write("0.0%");
								}else if(lin > 1){
									document.write("100%");
								}else{
									document.write(((lin * 100)+"").substring(0, 4) + "%");
								}
							</script>
							</s:else>
							</td>
							<td>
							<s:if test="#vlevel.badevaluateCount==0">0.0%</s:if><s:else>
							<script>
								var lin= <s:property value="#vlevel.badevaluateCount" /> / <s:property value="#vlevel.openedCount" /> ;
								if(isNaN(lin)){
									document.write("0.0%");
								}else if(lin > 1){
									document.write("100%");
								}else{
									document.write(((lin * 100)+"").substring(0, 4) + "%");
								}
							</script>
							</s:else>
							</td>
							<td>
								<label class="calculate_num" id="${var.index}">1.0</label>
								<input type="hidden" value="${vlevel.pointSoldCount}" id="pointSoldCount${var.index}"/>
								<input type="hidden" value="${vlevel.opendUser}" id="opendUser${var.index}"/>
								<input type="hidden" value="${vlevel.goodevaluateCount}" id="goodevaluateCount${var.index}"/>
								<input type="hidden" value="${vlevel.badevaluateCount}" id="badevaluateCount${var.index}"/>
								<input type="hidden" value="${vlevel.openedCount}" id="openedCount${var.index}"/>
							</td>
							<td>
								<label id="result${var.index}">
								<script>
									var weights = 1.0;//权数
									var buyCount;
									<s:if test="#vlevel.pointSoldCount==null">
										buyCount = 0;
									</s:if>
									<s:else>
										buyCount = <s:property value="#vlevel.pointSoldCount" />;
									</s:else>
									var seeVideo= <s:property value="#vlevel.opendUser" /> / buyCount;//观看率
									if(isNaN(seeVideo)){
										seeVideo = 0;
									}
									var goodPingLv = <s:property value="#vlevel.goodevaluateCount" /> / <s:property value="#vlevel.openedCount" /> ;//好评率
									var badPingLv = <s:property value="#vlevel.badevaluateCount" /> / <s:property value="#vlevel.openedCount" /> ;//差评率
									if(isNaN(goodPingLv)){
										goodPingLv = 0;
									}
									if(isNaN(badPingLv)){
										badPingLv = 0;
									}
									var result = (weights * buyCount * seeVideo * goodPingLv) - (weights * buyCount * seeVideo * badPingLv);
									if(isNaN(result)){
										document.write("0");
									}else{
										document.write(round(result,2));
									}
									function round(v,e){
										var t=1;
										for(;e>0;t*=10,e--);
											for(;e<0;t/=10,e++);
										return Math.round(v*t)/t;
									}
								 </script>
								 </label>
							</td>
						</tr>
					</s:iterator>
				</table>
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
</div>
	</body>
</html>
