<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>项目视频观看统计</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
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
    	</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath %>/vst/vido!getVSSingle.action" method="post" name="vstForm" id="vstForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists" onmouseover="changeto()" onmouseout="changeback()">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">项目视频观看统计</font>
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
							<s:select name="subjectId" id="subjectId" list="subList" listKey="subjectId"
									listValue="subjectName" headerKey="-1" headerValue="--请选择专业--" theme="simple">
						 	</s:select>
						 </td>
								<td><a href="javascript:document.vstForm.submit()"><img src="<%=contextPath%>/back/images/add_a.gif"/>查询</a></td>
						</tr>		
						</table>
					 </td>
            <td class="lists_tright lists_bor2"></td>
        </tr>
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<!-- 
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr class="lists_infobg">
					<td>行为/月份</td>
					<s:iterator value="vstSingleList" var="vst">
							<td>
								<s:property value="#vst.month"/>月份
							</td>
					</s:iterator>
				</tr>
				
				<s:iterator value="vstSingleList" var="vst">
						<tr>
							<td>
								<s:property value="#vst.loginTimes" />
							</td>
							<td>
								<s:property value="#vst.watchedTimesAll" />
							</td>
				
							<td>
								<s:property value="#vst.watchedTotal" />
							</td>
							
							<td>
								<s:property value="#vst.watchedTimes" />
							</td>
							
							<td>
								<s:property value="#vst.learningTotalTimes" />
							</td>
							<td>
								<s:property value="#vst.learningAvgTimes" />
							</td>
							<td>
								<s:property value="#vst.watchedPercent" />
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
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	-->
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							月份
						</td>
						<td>
							登陆次数
						</td>
						<td>
							观看课程次数（即观看多少次视频)
						</td>
						
						<td>
							观看视频总数（数目）
						</td>
						<td>
							观看完的视频总数(时长>10)
						</td>
						<td>
							学习总时间（单位：分钟）
						</td>
						<td>
							学习总时间（单位：分钟）
						</td>
						<td>
							观看率
						</td>
					</tr>
					<s:iterator value="vstSingleList" var="vst">
						<tr>
							<td>
								<s:property value="#vst.month"/>月份
							</td>
							<td>
								<s:property value="#vst.loginTimes" />
							</td>
							<td>
								<s:if test="#vst.watchedTimesAll==null">无</s:if><s:else><s:property value="#vst.watchedTimesAll" /></s:else>
								
							</td>
				
							<td>
								<s:property value="#vst.watchedTotal" />
							</td>
							
							<td>
								<s:property value="#vst.watchedTimes" />
							</td>
							
							<td>
								<s:property value="#vst.learningTotalTimes" />
							</td>
							<td>
								<s:property value="#vst.learningAvgTimes" />
							</td>
							<td>
								<s:property value="#vst.watchedPercent" />
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
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	
	</form>
</div>
	</body>
</html>
