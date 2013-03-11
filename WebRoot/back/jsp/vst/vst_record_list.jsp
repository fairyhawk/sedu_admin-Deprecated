<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>观看行为日志</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<style>
		html, body { height: 100%; }
		#wrapback { min-height: 100%; position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5;width:100%;-moz-opacity:1;top:0;left:0;background:#000000;display:none;z-index:4;}
		* html #wrapback { height: 100%; }
		.fonts {font-size: 11pt;}
		</style>
		
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript">
		/*
			function createDateTwo(){
				var startTime=document.getElementById("watchTimeBegin").value;
			    var endTime=document.getElementById("watchTimeEnd").value;
			    if(startTime!='' && startTime>endTime){
				       alert("结束时间要大于开始时间!");
				       document.getElementById("watchTimeEnd").value='';
				      return false;
			    }
			}
			
			function createDateOne(){
				var startTime=document.getElementById("watchTimeBegin").value;
			    var endTime=document.getElementById("watchTimeEnd").value;
			    if(endTime!='' && startTime>endTime){
				       alert("结束时间要大于开始时间!");
				       document.getElementById("watchTimeBegin").value='';
				      return false;
			    }
			}
		*/
			
			//导出Excel
			function checkOutExcel(){
				alert("数据过多时，服务器需要更多时间相应，请耐心等待...");
	    	 	var url = "${pageUrlParms}";
	    	 	var param = url.substring(url.indexOf("?"), url.length-1);
	     		$.ajax({
					url : "<%=contextPath%>/vst/vido!donloadExcel.action"+param,
					data : {},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据  
					success:function(result){
						var bo = result.jumpType;
						if(bo){
							var ss='点击下载：<a href="<%=contextPath%>/excelfile/watchRecod.rar">导出excel</a>'
							$("#downinfo").html(ss);
							$("#wrapback").fadeIn();
							$("#checkOut").fadeIn();
							$('#wrapbackInfo').fadeIn();
						}else{
							window.location.reload();
						}
					},
					error: function(){ 
						alert('error');  
					}
				  });
				  
			}
			
			
		function closes(){
			$("#infoWrap").fadeOut();
			$("#wrapback").fadeOut();
			$('#wrapbackInfo').fadeOut();
			$('#checkOut').fadeOut();
		}
    	</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath %>/vst/vido!getVRPage.action?reCondtion.currentPage=1" method="post" name="vstForm" id="vstForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists" onmouseover="changeto()" onmouseout="changeback()">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">观看行为日志</font>
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
							所在项目:<s:select name="reCondtion.subjectId" id="subjectId" list="subList" listKey="subjectId"
									listValue="subjectName" headerKey="-1" headerValue="--请选择专业--" theme="simple">
						 	</s:select>
						 </td>
						<td width="20px;"></td>
					 
						<td>观看时间 ：<input type="text" name="reCondtion.start" readonly="readonly" id="watchTimeBegin" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd 00:00:00',alwaysUseStartDate:false,autoPickDate:null})"
										value="<s:date name="reCondtion.start"  format="yyyy-MM-dd HH:mm:ss"/>"/>
								    -<input type="text" name="reCondtion.end" readonly="readonly" id="watchTimeEnd" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd 23:59:59',alwaysUseStartDate:false,autoPickDate:null})"
										value="<s:date name="reCondtion.end"  format="yyyy-MM-dd HH:mm:ss"/>"/>
										<s:date name="person.birthday" format="yyyy-MM-dd" />
						</td>
						
						<td>&nbsp;&nbsp;
						<a href="javascript:document.vstForm.submit()"><img src="<%=contextPath%>/back/images/add_a.gif"/>查询</a>&nbsp;&nbsp;
						<img src="<%=contextPath%>/back/images/down16_16.gif" />
										<a href="javascript:checkOutExcel()">导出excel</a>
										
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
						<td>
							用户ID
						</td>
						<td>
							用户注册邮箱
						</td>
						<td>
							所属商品(售卖方式)
						</td>
						
						<td>
							课程
						</td>
						<td>
							视频节点ID
						</td>
						<td>
							视频名称
						</td>
						<td>
							任课教师
						</td>
						<td>
							开始观看时间
						</td>
						<td>
							结束观看时间
						</td>
						<td>
							观看时长（单位/分钟）
						</td>
						<td>
							所属项目（售卖方式所属的专业）
						</td>
					</tr>
					<s:iterator value="page.pageResult" var="vst">
						<tr>
							<td>
								<s:property value="#vst.cusId"/>
							</td>
							<td>
								<s:property value="#vst.email" />
							</td>
							<td>
								<!-- <s:property value="#vst.sellWayId" /> -->
								<s:property value="#vst.sellWayTitle" />
							</td>
				
							<td>
								 <!-- <s:property value="#vst.courseId" /> -->
								<s:property value="#vst.courseName" />
							</td>
							<td>
								<s:property value="#vst.videoPointId" />
							</td>
							<td>
								<s:property value="#vst.videoName" />
							</td>
							<td>
								<s:if test="#vst.teacher=='\"null\"'">无</s:if><s:else><s:property value="#vst.teacher" /></s:else>
							</td>
							<td>
								<s:date name="#vst.startTime"  format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:date name="#vst.endTime"  format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:property value="#vst.watchAlltime" />
							</td>
							<td>
							<!--<s:property value="#vst.subjectId" />  -->
								
								<s:property value="#vst.subjectName" />
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

		<!--层遮罩部分-->		
		<div style="position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5; -moz-opacity:0.5;top:0;left:0;width:100%;height:150%;background:#000;display:none;z-index:4;" id="wrapback">		
		<iframe id="wrapbackInfo" style="position:absolute;width:100%;height:100%;_filter:alpha(opacity=50);opacity=0.5;border-style:none;"></iframe>
		</div>
		<!--层遮罩部分结束-->
		
		<div align='center' class="excelout"  id='checkOut'>
			<div class="tit">
				<span class="tie fonts" id="title" >视屏观看计入excel打包下载</span>
			</div>	
				<div id="downinfo"  class="con">
				</div>
				<div align="center"><input type='button' value='关闭' onclick='closes();'/></div>
		</div>
		 
		
	</body>
</html>
