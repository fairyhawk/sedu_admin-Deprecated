<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>短信管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar.js" ></script>  
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar-zh.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar-setup.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=contextPath%>/back/script/calendar/calendar.css" />
<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function yan(){
		 var worKey=document.getElementById('wordKey').value;
		 if(worKey.replace(/[(^\s+)(\s+$)]/g,"")=='请输入关键字'){
		 	document.getElementById('wordKeyy').value=''
		 }else{
		 	document.getElementById('wordKeyy').value=worKey;
		 }
		$("#wordKeyy").val(encodeURIComponent($("#wordKeyy").val()));
		
			var shandledate=document.getElementById("s_handledate").value;
			//结束时间
			var sendledate=document.getElementById("s_endledate").value;
			if(shandledate!=""  && sendledate!="" && shandledate>sendledate){
				alert("开始时间不能大于结束时间!");
				var sendledate=document.getElementById("s_endledate");
				sendledate.value="";
				sendledate.focus();
				}
	}
	function sendSMSFClick(){
		window.location.href="<%=contextPath%>/cus/cus!toSendSMSForCus.action"
		return true;
	}
	function endVSendTime()
		{
			 //开始时间
			var shandledate=document.getElementById("s_handledate").value;
			//结束时间
			var sendledate=document.getElementById("s_endledate").value;
			if(shandledate!="" && shandledate>sendledate){
				alert("开始时间不能大于结束时间!");
				var sendledate=document.getElementById("s_endledate");
				sendledate.value="";
				sendledate.focus();
				}
		}
		function sendVSendTime()
		{
			 //开始时间
			var shandledate=document.getElementById("s_handledate").value;
			//结束时间
			var sendledate=document.getElementById("s_endledate").value;
			if(sendledate!="" && shandledate>sendledate){
				alert("开始时间不能大于结束时间!");
				var shandledate=document.getElementById("s_handledate");
				shandledate.value="";
				shandledate.focus();
				}
		}
		function gaibianWord(){
			document.getElementById("wordKey").value='';
		}
		function gaibianhui(){
			var worKey=document.getElementById("wordKey").value;
		 if(worKey.replace(/[(^\s+)(\s+$)]/g,"")=='请输入关键字'){
		 	document.getElementById("wordKey").value='请输入关键字';
		 }
		}
</script>
</head>
<body>
	<!-- 查询块开始 -->
	<div>		
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft"> 短信管理</font> <font class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					<td width="12" class="lists_bor"></td>
					<td>
					<form name="searchForm" id="searchForm" action="<%=contextPath%>/cus/cus!sendNoteContentListWhere.action?queryNoteContentCondition.currentPage=1" method="post">
					<div class="msg-yw">
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists">
							<tr >
                                  <td class="lists_tleft">
                                  &nbsp;&nbsp;
                                  <input type="text"
									name="" id="wordKey"   value="请输入关键字" onfocus="gaibianWord()" onblur="gaibianhui()"/> 
									<input  type="hidden" id="wordKeyy" name="queryNoteContentCondition.word"/>
									  &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;
									<select name="queryNoteContentCondition.searchId">
									<option value="0">-请选择</option>
									<option value="1">操作人</option>
									<option value="2">收件人</option>
									<option value="3">发送内容</option>
									</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											发送时间：<input type="text" readonly
                                               name="queryNoteContentCondition.noteAddTime" value='<s:property value="queryNoteContentCondition.noteAddTime"/>' id="s_handledate" onchange="sendVSendTime()" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" />
                                                &nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
                                              <input type="text" readonly
name="queryNoteContentCondition.endTime" id="s_endledate"  onchange="endVSendTime()" value='<s:property value="queryNoteContentCondition.endTime"/>' onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" />
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<s:submit value="查询" onclick="yan()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 <input  type="button"  value="发送短信" onclick="sendSMSFClick()"/>
									</td>
							</tr>
						</table>
						</div>
						</form>
					</td>
					<td width="16" class="lists_tright lists_bor2"></td>
				</tr>
				
			<tr >
				<td class="lists_bor"></td>
				<td class="lists_top"><font class="lists_fleft">短信管理</font> <font
					class="lists_fright">
						</font>
				</td>
			<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td width="12" class="lists_bor"></td>
				<td>
					<form name="couponForm" method="post">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info" style="word-wrap: break-word;">
							<tr class="lists_infobg">
								<td width="4%">编号</td>
								<td width="10%">发送内容</td>
								<td width="6%">发送时间</td>
								<td width="6%">操作人</td>
								<td width="11%">操作</td>
							</tr>	
					
							<s:iterator id="noteContent" value="page.pageResult">
								<tr>
									<td>
										<s:property value="#noteContent.noteId"/>
										<s:if test="noteContent.noteId.length() >0">111</s:if>
										<s:if test="noteContent.noteId.length() <10 && noteContent.noteIdlength() >0">
											<s:property value="#noteContent.noteId" />
										</s:if>
										<s:if test="noteContent.noteId.length() >=10">
											<s:property value="%{noteContent.noteId.subString(0,10)+'...'}"  escape="false"/>
										</s:if>
										
									</td>
									<td>
										
										<s:if test="#noteContent.noteContent.length() <10 && #noteContent.noteContent.length()>0">
											<s:property value="#noteContent.noteContent"/>
										</s:if>
										<s:if test="#noteContent.noteContent.length() >=10">
											<s:property value="%{#noteContent.noteContent.substring(0, 10)+'...'}" escape="false"/>
										</s:if>
									</td>
									<td>
										<s:date name="#noteContent.noteAddTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:property value="#noteContent.noteDespatcher"/>
									</td>
									<td>
										<a href='<%=contextPath%>/cus/cus!serchContentInfo.action?queryNoteContentCondition.noteId=<s:property value="#noteContent.noteId"/>'>查看</a>
									</td>
									
								</tr>			
							</s:iterator>
						</table>
					</form>
				</td>
				<td width="16" class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td><img src="<%=contextPath%>/back/images/tab_18.gif" />
				</td>
				<td class="lists_bottom"><jsp:include
						page="/back/jsp/common/showPage.jsp" /></td>
				<td><img src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
