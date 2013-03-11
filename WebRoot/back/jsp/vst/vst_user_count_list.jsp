<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>视频观看统计(用户纬度)</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript">
		function doExcel(){
			document.vstForm.action="<%=contextPath %>/vst/vido!doWatchListExcel.action";
			document.vstForm.submit();
		}
		function search(){
			document.vstForm.action="<%=contextPath %>/vst/vido!getVideoWatchLogList.action";
			document.vstForm.submit();
		}
	</script>
	</head>
	<body>
	
<div>
	<form action="<%=contextPath %>/vst/vido!getVideoWatchLogList.action" method="post" name="vstForm" id="vstForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists" >
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">视频观看统计(用户)</font>
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
							所在项目:<s:select name="subjectcondition.subjectId" id="subjectId" list="subList" listKey="subjectId"
									listValue="subjectName" headerKey="0" headerValue="--请选择专业--" theme="simple">
						 	</s:select>
						 </td>
						<td width="20px;"></td>
						<td>查询时间 ：<input type="text" name="subjectcondition.startTime" readonly="readonly" id="createTimeBegin" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd 00:00:00',autoPickDate:true})"
											 value="<s:property value='subjectcondition.startTime' />" />
								-<input type="text" name="subjectcondition.endTime" readonly="readonly" id="createTimeEnd" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd 23:59:59',autoPickDate:true})"
											 value="<s:property value='subjectcondition.endTime' />" />
						</td>
						<td width="20px;"></td>
						<td>
							
						</td>
						<td width="20px;"></td>
						
						
						<td><a href="javascript:search()"><img src="<%=contextPath%>/back/images/add_a.gif"/>查询</a>
						<img src="<%=contextPath%>/back/images/down16_16.gif"><a href="javascript:doExcel()">导出excel</a>
						
						</td>
						</tr>
						</table>
					 </td>		
					 </td>
            <td class="lists_tright lists_bor2"></td>
        </tr>
        </form>
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>项目名称</td>
						<td>登录的有效购买用户数</td>
						<td>登录看视频的有效购买用户数</td>
						<td>看收费视频的总时长</td>
						<td>截至到目前有效购买用户的总数</td>
						<td>无购买课程登录的用户总数</td>
						<td>无购买课程登录看试听课程的用户总数</td>
						<td>登录看试听课程的总时长</td>
						<td>平均观看收费视频的时长</td>
						<td>平均观看试听课程的时长</td>
						<td>观看收费视频的比例</td>
						<td>观看试听课程的比例</td>
					</tr>
																			
					<s:iterator value="videoWatchLogslist" var="vst">
						<tr>
							<td>
								<s:property value="#vst.subjectName"/>
							</td>
							<td><s:if test="#vst.buylogincount==null">0</s:if><s:else><s:property value="#vst.buylogincount" /></s:else></td>
							<td><s:if test="#vst.watchusercount==null">0</s:if><s:else><s:property value="#vst.watchusercount" /></s:else></td>
							<td><s:if test="#vst.buywatchtime==null">0</s:if><s:else><s:property value="#vst.buywatchtime" /></s:else></td>
							<td><s:if test="#vst.buyallcount==null">0</s:if><s:else><s:property value="#vst.buyallcount" /></s:else></td>
							<td><s:if test="#vst.nobuylogincount==null">0</s:if><s:else><s:property value="#vst.nobuylogincount" /></s:else></td>
							<td><s:if test="#vst.shitingusercount==null">0</s:if><s:else><s:property value="#vst.shitingusercount" /></s:else></td>
							<td><s:if test="#vst.shitingwatchtime==null">0</s:if><s:else><s:property value="#vst.shitingwatchtime" /></s:else></td>
							<td><s:if test="#vst.ave_buywatchtime==null">0</s:if><s:else><s:property value="#vst.ave_buywatchtime" /></s:else></td>
							<td><s:if test="#vst.ave_shitingwatchtime==null">0</s:if><s:else><s:property value="#vst.ave_shitingwatchtime" /></s:else></td>
							<td><s:if test="#vst.scale_buy==null">0%</s:if><s:else><s:property value="#vst.scale_buy" />%</s:else></td>
							<td><s:if test="#vst.scale_shiting==null">0%</s:if><s:else><s:property value="#vst.scale_shiting" />%</s:else></td>
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
