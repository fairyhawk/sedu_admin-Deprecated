<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>笔记列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript">
	function delNotes() {
		if(window.confirm("确认删除这些笔记吗？")) {
			document.notesForm.action = "<%=contextPath%>/res/notes!deleteNotes.action?queryNotesCondition.currentPage=" + <s:property value="page.currentPage"/>;
			document.notesForm.submit();
		}
	}
	function addNotes() {
		window.location.href = "<%=contextPath%>/res/notes!initAddNotes.action?notes.cusId=3&notes.pointId=3";
	}
	
	function updateNotes(id) {
		window.location.href = "<%=contextPath%>/res/notes!initUpdateNotes.action?notes.noteId=" + id;
	}
	
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
	}
</script>
</head>
<body>
<div>
	<form name="notesForm" method="post">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">笔记列表</font>
				<font class="lists_fright">
					<!-- 
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:addNotes()">添加</a></td></tr></table>
					 -->
					<span><img src="<%=contextPath%>/back/images/del_a.gif"/><a href="javascript:delNotes()">删除</a></span>
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			<img src="<%=contextPath%>/back/images/tab_17.gif" />
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td width="7%"><input type="checkbox" onclick="allCheck(this)"/>全选</td>
						<td width="53%">笔记内容</td>
						<td width="15%">记录用户</td>
						<td width="15%">修改/创建时间</td>
						<td width="10%">操作</td>
					</tr>
				<s:iterator value="page.pageResult" id="notes" status="status">
					<tr>
						<td><input type="checkbox" name="ids" value=<s:property value="noteId"/> /></td>
						<td>
							<div class="lists_nowrap"><s:property value="noteInfo" /></div>
						</td>
						<td>
							<s:property value="customer.cusName" />
						</td>
						<td>
							<s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href="#" onclick="updateNotes(<s:property value="noteId" />)"><img src="<%=contextPath%>/back/images/edt_tbl.gif"/>修改</a>
						</td>
					</tr>
				</s:iterator>
			</table>
			</td>
			<td class="lists_tright lists_bor2" width="16">
			
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
