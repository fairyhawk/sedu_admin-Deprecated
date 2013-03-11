<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>模板列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript">
	function delTmpHistorys() {
		if(window.confirm("确认删除这些模板吗？")) {
			document.tmpForm.action = "<%=contextPath%>/cms/template!deleteTemplate.action?queryTemplateCondition.currentPage="+<s:property value="page.currentPage"/>;
			document.tmpForm.submit();
		}
	}
	function addTemplate() {
		window.location.href = "<%=contextPath%>/cms/template!initAddTemplate.action";
	}
	function updateTemplate(id) {
		window.location.href = "<%=contextPath%>/cms/template!initUpdateTemplate.action?queryTmpHistory.currentPage=1&template.tmpId="+id;
	}
	
	function resetKey() {
		$("#op0").attr("selected","selected");
		$("input[name=queryTemplateCondition.tmpName]").val("");
		$("input[name=queryTemplateCondition.tmpContent]").val("");
		$("#temp_type_0").attr("selected","selected");
	}
	
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
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
				<font class="lists_fleft">模板列表</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
			<!-- 
			<td class="lists_top">
				<font class="lists_fleft">模板列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0" ><tr><form name="searchForm" action="<%=contextPath%>/cms/template!showTemplateList.action" method="post" border="0"><td >
							<s:hidden name="queryTemplateCondition.currentPage" value="1"/>
							模板名称：
					</td>
					<td><input type="text" value="<s:property value="queryTemplateCondition.tmpName"/>" name="queryTemplateCondition.tmpName"/></td>
					<td>模板内容：</td>
					<td><input type="text" value="<s:property value="queryTemplateCondition.tmpContent"/>" name="queryTemplateCondition.tmpContent"/></td>
					<td>模板类型：</td>
					<td><select name="queryTemplateCondition.tmpType">
								<option id="temp_type_0" value="-1" >--请选择--</option>
								<option value="0" <s:property value="queryTemplateCondition.tmpType==0?'selected':''"/>>栏目模板</option>
								<option value="1" <s:property value="queryTemplateCondition.tmpType==1?'selected':''"/>>文章模板</option>
								<option value="2" <s:property value="queryTemplateCondition.tmpType==2?'selected':''"/>>其他模板</option>
							</select></td>
					<td>是否可用：</td>
					<td><select name="queryTemplateCondition.isUse" >
								<option id="op0" value="">--请选择--</option>
								<option value="1" <s:property value="queryTemplateCondition.isUse==1?'selected':''"/>>可用</option>
								<option value="0" <s:property value="queryTemplateCondition.isUse==0?'selected':''"/>>不可用</option>
							</select></td>
					</form>
					</tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:document.searchForm.submit()">查询</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:resetKey()">清空</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:addTemplate()">添加</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:delTemplate()">删除</a></td></tr></table>
				</font>
			</td>
			 -->
		<tr>
			<td width="12"  class="lists_bor"></td>
			<td>
				<div class="msg-xt">
					<table  border="0" cellspacing="0"  cellpadding="0" >
					<tr>
					<form name="searchForm" action="<%=contextPath%>/cms/template!showTemplateList.action" method="post" border="0"><td >
							<s:hidden name="queryTemplateCondition.currentPage" value="1"/>
							模板名称：
					</td>
					<td><input type="text" value="<s:property value="queryTemplateCondition.tmpName"/>" name="queryTemplateCondition.tmpName"/></td>
					<td>模板内容：</td>
					<td><input type="text" value="<s:property value="queryTemplateCondition.tmpContent"/>" name="queryTemplateCondition.tmpContent"/></td>
					<td>模板类型：</td>
					<td><select name="queryTemplateCondition.tmpType">
								<option id="temp_type_0" value="-1" >--请选择--</option>
								<option value="0" <s:property value="queryTemplateCondition.tmpType==0?'selected':''"/>>栏目模板</option>
								<option value="1" <s:property value="queryTemplateCondition.tmpType==1?'selected':''"/>>文章模板</option>
								<option value="2" <s:property value="queryTemplateCondition.tmpType==2?'selected':''"/>>其他模板</option>
							</select></td>
					<td>是否可用：</td>
					<td>
						<select name="queryTemplateCondition.isUse" >
								<option id="op0" value="">--请选择--</option>
								<option value="1" <s:property value="queryTemplateCondition.isUse==1?'selected':''"/>>可用</option>
								<option value="0" <s:property value="queryTemplateCondition.isUse==0?'selected':''"/>>不可用</option>
						</select>
					</td>
					<td><img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:document.searchForm.submit()">查询&nbsp;&nbsp;</a></td>
					<td><img src="<%=contextPath%>/back/images/del_a.gif"/><a href="javascript:resetKey()">清空&nbsp;&nbsp;</a></td>
					<td><img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:addTemplate()">添加&nbsp;&nbsp;</a></td>
					<td><img src="<%=contextPath%>/back/images/del_a.gif"/><a href="javascript:delTmpHistorys()">删除</a></td>
					</form>
					</table>
				</div>
			</td>
			<td class="lists_tright lists_bor2" width="16"> </td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<form name="tmpForm" method="post">
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr class="lists_infobg">
							<td width="7%"><input type="checkbox" onclick="allCheck(this)"/>全选</td>
							<td width="15%">模板编号</td>
							<td width="15%">模板名称</td>
							<td width="10%">模板类型</td>
							<td width="10%">是否可用</td>
							<td width="10%">操作</td>
						</tr>
						<s:iterator value="page.pageResult" id="template">
							<tr>
								<td><input type="checkbox" name="ids" value=<s:property value="tmpId"/> /></td>
								<td>
									<s:property value="tmpId" />
								</td>
								<td>
									<s:property value="tmpName" />
								</td>
								<td>
									<s:if test="tmpType==0">
										基础模板
									</s:if>
									<s:elseif test="tmpType==1">
										文章模板
									</s:elseif>
									<s:else>
										其他模板
									</s:else>
								</td>
								<td>
									<s:if test="isUse==1">
										可用
									</s:if>
									<s:else>
										不可用
									</s:else>
								</td>
								<td>
									<a href="#" onclick="updateTemplate(<s:property value="tmpId" />)">修改</a>
								</td>
							</tr>
						</s:iterator>
					</table>
				<form name="tmpForm" method="post">
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
