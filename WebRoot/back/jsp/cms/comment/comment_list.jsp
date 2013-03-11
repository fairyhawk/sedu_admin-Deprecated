<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
	function delComment(id) {
		if(window.confirm("确认删除这条评论吗？")) {
			window.location.href = "<%=contextPath%>/cms/comment!deleteComment.action?queryCommentCondition.currentPage=" + <s:property value="page.currentPage"/> +"&comment.cmtId=" + id;
		}
	}
	
	function auditComment(id) {
		if(window.confirm("确认这条评论审核通过吗？")) {
			window.location.href = "<%=contextPath%>/cms/comment!auditComment.action?comment.cmtId=" + id;
		}
	}
	function search(){
	   $("#plcmtInfo").val(encodeURIComponent($("#plcmtInfo").val()));
		document.searchForm.submit()	
	}
	function resetKey() {
		$("#op0").attr("selected","selected");
		$("#audit_op_0").attr("selected","selected");
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
				<font class="lists_fleft">评论列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><form name="searchForm" action="<%=contextPath%>/cms/comment!getCommentListBySearch.action" method="post"><td>
						
						<s:hidden name="queryCommentCondition.currentPage" value="1"/>
							评论来源：</td><td>
							<select id="selectCfId" name="queryCommentCondition.cfId">
								<option id="op0" value="0">--请选择--</option>
								<s:iterator value="commentfromList" id="commentFrom">
									<s:if test="queryCommentCondition.cfId==cfId">
										<option value="<s:property value="cfId"/>" selected="selected"><s:property value="cfName"/></option>
									</s:if>
									<s:else>
										<option value="<s:property value="cfId"/>"><s:property value="cfName"/></option>
									</s:else>
								</s:iterator>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>
							审核状态：</td><td>
							<select name="queryCommentCondition.checkState">
								<option id="audit_op_0" value="-1">--请选择--</option>
								<option value="0">未审核</option>
								<option value="1">已审核</option>
							</select></td><td>
							评论内容：&nbsp;&nbsp;&nbsp;&nbsp;</td><td>
							<input type="text" id="plcmtInfo" value="<s:property value="queryCommentCondition.cmtInfo"/>" name="queryCommentCondition.cmtInfo"/>
						
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
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td width="15%">评论人</td>
						<td width="45%">评论内容</td>
						<td width="15%">评论时间</td>
						<td width="15%">审核状态</td>
						<td width="10%">操作</td>
					</tr>
					<s:iterator value="page.pageResult" id="comment">
						<tr>
							<td>
								<s:if test="checkmanType==0">
									游客
								</s:if>
								<s:else>
									<a href="#" onclick="viewCus(<s:property value="checkmanId" />)"><s:property value="visitorName" /></a>
								</s:else>
							</td>
							<td title="<s:property value="cmtInfo" />">
								<div class="lists_nowrap"><s:property value="cmtInfo" /></div>
							</td>
							<td>
								<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
							</td>
							<td>
								<s:if test="checkState==0">
									<a href="javascript:auditComment('<s:property value="cmtId"/>')">未审核</a>
								</s:if>
								<s:else>
									已审核
								</s:else>
							</td>
							<td>
								<a href="javascript:delComment('<s:property value="cmtId"/>')">删除此评论</a>
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
