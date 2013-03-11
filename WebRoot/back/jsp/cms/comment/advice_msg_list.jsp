<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>留言查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
			var hh = "";
			for(var i=1; i<24; i++){
				if(i<10){
					hh = hh + "<option value='0"+i+":00:00'>0"+i+":00:00</option>"
				}else if(i<23){
					hh = hh + "<option value='"+i+":00:00'>"+i+":00:00</option>"
				}else{
					hh = hh + "<option value='23:59:59'>23:59:59</option>"
				}
			}
			$("#start_hh").append(hh);
			$("#end_hh").append(hh);
			$("#end_hh").attr("value","23:59:59");
			var startHH = "<s:property value="queryCommentCondition.startTime"/>";
			var endHH = "<s:property value="queryCommentCondition.endTime"/>";
			if(startHH!='') startHH=startHH.substring(11)
			if(endHH!='') endHH=endHH.substring(11)
			if(startHH!=''){
				$("#start_hh").attr("value",startHH);
			}
			if(endHH!=''){
				$("#end_hh").attr("value",endHH);
			}
		})

	function delAdvice(id) {
		if(window.confirm("确认删除这条意见吗？")) {
			document.searchForm.action = "<%=contextPath%>/cms/comment!delAdvice.action?id="+id;
			document.searchForm.submit()	;
		}
	}
	function scanAdvice(id) { 
			window.location.href = "<%=contextPath%>/cms/comment!adviceDetail.action?comment.cmtId=" + id;
	}
	function topAdvice(id) {
			$.post("<%=contextPath%>/cms/comment!topAdvice.action",{id:id},function(json){
				if(json.jumpType==true){
					alert('置顶成功');
				}else{
					alert('置顶失败');
				}
			},"json")
	}
	function freezeAdvice(id,isFreeze) {
	var confirmInfo='';
	if(isFreeze==1)confirmInfo="确认隐藏这条意见吗？"
	if(isFreeze==0)confirmInfo="确认显示这条意见吗？"
		if(window.confirm(confirmInfo)) {
			document.searchForm.action = "<%=contextPath%>/cms/comment!freezeAdvice.action?id="+id+"&isFreeze="+isFreeze;
			document.searchForm.submit()	;
		}
	}
	function search(){
		var startTime=document.searchForm.elements[3].value;
		var endTime=document.searchForm.elements[5].value;
		if(startTime!=''&&endTime!=''&&startTime>endTime){
		alert('结束时间必须大于开始时间');
		return
		} 
	   $("#cmtInfoval").val(encodeURIComponent($("#cmtInfoval").val()));
	   if(document.searchForm.elements[3].value!='')
	   $('#startTime').val(document.searchForm.elements[3].value+' '+$("#start_hh").val());
	   if(document.searchForm.elements[5].value!='')
	   $('#endTime').val(document.searchForm.elements[5].value+' '+$("#end_hh").val());
	   document.searchForm.action ="<%=contextPath%>/cms/comment!showAdviceMsgList.action"
	   document.searchForm.submit()	;
	}
	function replyAdvice(id) {
		window.location.href = "<%=contextPath%>/cms/comment!initReplyAdvice.action?comment.cmtId=" + id;
	}
	
	function week(){
		window.location.href="<%=contextPath%>/cms/comment!showAdviceMsgList.action?queryCommentCondition.currentPage=1&queryCommentCondition.searchStatus=1";
	}
	
	function month(){
		window.location.href="<%=contextPath%>/cms/comment!showAdviceMsgList.action?queryCommentCondition.currentPage=1&queryCommentCondition.searchStatus=2";
	}
	
	function threeMonth(){
		window.location.href="<%=contextPath%>/cms/comment!showAdviceMsgList.action?queryCommentCondition.currentPage=1&queryCommentCondition.searchStatus=3";
	}
	
	function exportComment(){
		if('${queryCommentCondition.searchStatus>0}'=='true'){
	   	window.location.href="<%=contextPath%>/cms/comment!exportComment.action?queryCommentCondition.searchStatus=${queryCommentCondition.searchStatus}";
	   }else{
			var startTime=document.searchForm.elements[3].value;
			var endTime=document.searchForm.elements[5].value;
			if(startTime!=''&&endTime!=''&&startTime>endTime){
			alert('结束时间必须大于开始时间');
			return
			} 
		   $("#cmtInfoval").val(encodeURIComponent($("#cmtInfoval").val()));
		   if(document.searchForm.elements[3].value!='')
		   $('#startTime').val(document.searchForm.elements[3].value+' '+$("#start_hh").val());
		   if(document.searchForm.elements[5].value!='')
		   $('#endTime').val(document.searchForm.elements[5].value+' '+$("#end_hh").val());
		   document.searchForm.action ="<%=contextPath%>/cms/comment!exportComment.action"
		   document.searchForm.submit();
	   }
	}
	 
</script>
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">留言查询</font>
				
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
					
					<form name="searchForm" action="<%=contextPath%>/cms/comment!showAdviceMsgList.action" method="post">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0">
					<tr>
					<td>留言关键字：<s:hidden name="queryCommentCondition.currentPage" value="1"/></td>
					<td><input type="text" name="queryCommentCondition.cmtInfo" value='<s:property value="queryCommentCondition.cmtInfo"/>'/></td>
					<td>电子邮件：</td>
					<td><input type="text" name="queryCommentCondition.email" value='<s:property value="queryCommentCondition.email"/>'/></td>
					</tr>
					<tr>
					<td>时间段查询：</td>
					<td>
					<s:if test="queryCommentCondition.startTime==null||queryCommentCondition.startTime==''">
					<input type="text"  onclick="WdatePicker()"/>
					</s:if><s:else>
					<input type="text" onclick="WdatePicker()" value='<s:property value="queryCommentCondition.startTime.substring(0,10)"/>'/>
					</s:else>
						<select  id="start_hh">
						<option value="00:00:00">00:00:00</option>
					    </select>
					</td>
					<td colspan="2">-
					<s:if test="queryCommentCondition.endTime==null||queryCommentCondition.endTime==''">
					<input type="text" onclick="WdatePicker()"/>
					</s:if><s:else>
					<input type="text" onclick="WdatePicker()" value='<s:property value="queryCommentCondition.endTime.substring(0,10)"/>'/>
					</s:else>
						<select id="end_hh">
						<option value="00:00:00">00:00:00</option>
					  </select>
					</td>
					</tr>
					<tr>
					<td>用户名：</td>
					<td><input  type="text" name="queryCommentCondition.visitorName"  maxlength="20" value="<s:property value="queryCommentCondition.visitorName"/>"/>
					</td>
					<td>专业：</td>
					<td><s:select name="queryCommentCondition.subjectId"
                                                      list="subjectList" listKey="subjectId"
                                                      listValue="subjectName" headerKey="0"
                                                      headerValue="-项目-" theme="simple">
                                            </s:select>
					</td>
					</tr>
					<tr>
					<td>是否置顶：</td>
					<td><select name="queryCommentCondition.isTop">
					<option value=-1 <s:property value="queryCommentCondition.isTop==-1?'selected=\"selected\"':''" />>-请选择-</option>
					<option value=1 <s:property value="queryCommentCondition.isTop==1?'selected=\"selected\"':''" />>是</option>
					<option value=0 <s:property value="queryCommentCondition.isTop==0?'selected=\"selected\"':''" />>否</option>
						</select>
					</td>
					<td>是否回复：</td>
					<td><select name="queryCommentCondition.checkState">
					<option value=-1 <s:property value="queryCommentCondition.checkState==-1?'selected=\"selected\"':''" />>-请选择-</option>
					<option value=1 <s:property value="queryCommentCondition.checkState==1?'selected=\"selected\"':''" />>是</option>
					<option value=0 <s:property value="queryCommentCondition.checkState==0?'selected=\"selected\"':''" />>否</option>
						</select>
					</td>
					</tr>
					<tr>
					<td><input type="hidden" name="queryCommentCondition.endTime" id="endTime"/><input type="hidden" name="queryCommentCondition.startTime" id="startTime"/></td>
					<td colspan="2"><input value="本周" type="button" onclick="week()"/><input value="本月" type="button" onclick="month()"/><input value="三个月" type="button" onclick="threeMonth()"/><input value="立即查询" type="button" onclick="search()"/></td>
					<td><input value="导出" type="button" onclick="exportComment()"/></td>
					</tr>
					 </table>
					 </form>  
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" style="table-layout:fixed;">
					<tr class="lists_infobg">
						<td width="8%">编号</td>
						<td width="28%">问题内容</td>
					<!--	<td width="8%">问题类型</td>-->
						<td width="8%">用户</td>
						<td width="7%">专业</td>
						<td width="7%">回复状态</td>
						<td width="7%">置顶状态</td>
						<td width="10%">提问时间</td>
						<td width="10%">置顶时间</td>
						<td width="5%">操作人</td>
						<td width="10%">操作</td>
					</tr>
					<s:iterator value="page.pageResult" id="advice">
						<tr>
							<td>
							<s:property value="cmtId" />
							</td>
							<td title="<s:property value="cmtInfo" />"  style="word-wrap: break-word;">
								<div class="lists_nowrap"><s:property value="cmtInfo" /></div>
							</td> 
						 
							<td>	
							 <s:property value="visitorName" /> 
							</td>
							<td>	
							 <s:property value="subjectName" /> 
							</td>
							<td>
								<s:if test="checkState==0">
									未回复
								</s:if>
								<s:else>
									已回复
								</s:else>
							</td>
							<td>
								<s:if test="isTop==1">
									已置顶
								</s:if>
								<s:else>
									未置顶
								</s:else>
							</td>
							<td>
								<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<s:date name="topTime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<s:property value="author"/>
							</td>
							
							<td>
								<a href="javascript:scanAdvice('<s:property value="cmtId"/>')">查看</a>
								<a href="javascript:topAdvice('<s:property value="cmtId"/>')">置顶</a>
								<a href="javascript:delAdvice('<s:property value="cmtId"/>')">删除</a>
								<s:if test="isFreeze==0"><a href="javascript:freezeAdvice('<s:property value="cmtId"/>','1')">隐藏</a></s:if>
								<s:if test="isFreeze==1"><a href="javascript:freezeAdvice('<s:property value="cmtId"/>','0')">显示</a></s:if>
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
</div>
</body>
</html>
