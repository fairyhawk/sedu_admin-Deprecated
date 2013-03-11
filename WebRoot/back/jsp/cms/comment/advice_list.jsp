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
	function delAdvice(id) {
		if(window.confirm("确认删除这条意见吗？")) {
			window.location.href = "<%=contextPath%>/cms/comment!deleteAdvice.action?queryCommentCondition.currentPage=" + <s:property value="page.currentPage"/> +"&comment.cmtId="+id;
		}
	}
	function search(){
	   $("#cmtInfoval").val(encodeURIComponent($("#cmtInfoval").val()));
		document.searchForm.submit()	
	}
	function replyAdvice(id) {
		window.location.href = "<%=contextPath%>/cms/comment!initReplyAdvice.action?comment.cmtId=" + id;
	}
	
	function showReplyList(id) {
		window.location.href = "<%=contextPath%>/cms/comment!showReplyList.action?queryCommentCondition.currentPage=1&comment.cmtId=" + id;
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
				<font class="lists_fleft">意见列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><form name="searchForm" action="<%=contextPath%>/cms/comment!showAdviceList.action" method="post"><td>
						
							<s:hidden name="queryCommentCondition.currentPage" value="1"/>
							回复状态：</td><td>
							<select name="queryCommentCondition.checkState">
								<option id="reply_op_0" value="-1">--请选择--</option>
								<option value="0">未回复</option>
								<option value="1">已回复</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>
							意见内容：</td><td>
							<input type="text" id="cmtInfoval" value="<s:property value="queryCommentCondition.cmtInfo"/>" name="queryCommentCondition.cmtInfo"/></td>
						
					</td></form></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:search()">查询</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:resetKey()">清空</a></td></tr></table>
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" style="table-layout:fixed;">
					<tr class="lists_infobg">
						<td width="15%">意见人</td>
						<td width="57%">意见内容</td>
						<td width="10%">发表时间</td>
						<td width="10%">回复状态</td>
						<td width="8%">操作</td>
					</tr>
					<s:iterator value="page.pageResult" id="advice">
						<tr>
							<td>	
							<a href="#" onclick="viewCus(<s:property value="checkmanId" />)"><s:property value="visitorName" /></a>
							</td>
							<td title="<s:property value="cmtInfo" />"  style="word-wrap: break-word;">
								<div class="lists_nowrap"><s:property value="cmtInfo" /></div>
							</td>
							<td>
								<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
							</td>
							<td>
								<s:if test="checkState==0">
									<a href="javascript:replyAdvice('<s:property value="cmtId"/>')">未回复</a>
								</s:if>
								<s:else>
									<a href="javascript:showReplyList('<s:property value="cmtId"/>')">已回复</a>
								</s:else>
							</td>
							<td>
								<a href="javascript:delAdvice('<s:property value="cmtId"/>')">删除</a>
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
