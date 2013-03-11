<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>评论列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript">
	function delReply(id) {
		if(window.confirm("确认删除这条回复吗？")) {
			window.location.href = "<%=contextPath%>/cms/comment!deleteReply.action?queryCommentCondition.currentPage=" + <s:property value="page.currentPage"/> +"&t=2&comment.cmtId="+id;
		}
	}
	
	function replyAdvice(id) {
		window.location.href = "<%=contextPath%>/cms/comment!initReplyAdvice.action?comment.cmtId=" + id;
	}
	
	function replyUpdate(id) {
		window.location.href = "<%=contextPath%>/cms/comment!initReplyUpdate.action?comment.cmtId=" + id;
	}
	
	function resetKey() {
		$("#reply_op_0").attr("selected","selected");
		$("input[name=queryCommentCondition.cmtInfo]").val("");
	}
	function viewCus(cusId) {
		if(cusId==0||cusId==""||cusId==null){
			alert("该用户不存在！");
			return false;
		}else{
			window.location.href = "<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=" + cusId;
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
				<font class="lists_fleft" style="height:30px;">意见人：${comment.visitorName }</font>
				<font class="lists_fleft" style="padding-left:90px;height:30px;">
				
				意见内容：
				<span title="${comment.cmtInfo }">
				<c:choose>
				<c:when test="${fn:length(comment.cmtInfo)>55}">
				${fn:substring(comment.cmtInfo,0,55) }…
				</c:when>
				<c:otherwise>
				${comment.cmtInfo }
				</c:otherwise>
				</c:choose>
				</span>
				</font>
				<input type="button" value="增加新回复" style="float:right;width:100px;margin-right:80px;" onclick="replyAdvice(${comment.cmtId })"/>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td width="65%">回复内容</td>
						<td width="20%">回复时间</td>						
						<td width="15%">操作</td>
					</tr>
					<s:iterator value="page.pageResult" id="advice">
						<tr>							
							<td title="<s:property value="cmtInfo" />" style="text-align:left;padding-left:20px;">
								<div class="lists_nowrap">
								<c:choose>
								<c:when test="${fn:length(advice.cmtInfo)>55}">
								${fn:substring(advice.cmtInfo,0,55) }…
								</c:when>
								<c:otherwise>
								${advice.cmtInfo }
								</c:otherwise>
								</c:choose>
								</div>
							</td>
							<td>
								<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
							</td>
							
							<td>
								<a href="javascript:delReply('<s:property value="cmtId"/>')">删除</a>
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
