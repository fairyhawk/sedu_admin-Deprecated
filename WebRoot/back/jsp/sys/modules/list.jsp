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
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
	}
	
	// 删除模板
	$(function(){
		$("#delModules").click(function(){
			var ids = "";
			$("input[name=ids]:checked").each(function(){
				ids += $(this).val() + ",";
			});
			if(ids){
				if(confirm("确认删除这些模板吗？")){
					ids = ids.substring(0,ids.length-1);
					$.ajax({
						type: "post",
						cache : false,
						data: "ids=" + ids,
						url: "<%=contextPath%>/sys/modules!del.action",
						success: function (data) {
							if(data == "1"){
								$("input:checked").each(function(){
									$(this).parent().parent().remove();
								});
							}
						}
					});
				}
			}
			
		});
		
	});
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
		<tr>
			<td width="12"  class="lists_bor"></td>
			<td>
				<div class="msg-xt">
					<table  border="0" cellspacing="0"  cellpadding="0" >
					<tr>
					<form name="searchForm" action="<%=contextPath%>/sys/modules!list.action" method="post" border="0"><td >
							<s:hidden name="queryModulesCondition.currentPage" value="1"/>
							模板名称：
					</td>
					<td><input type="text" value="<s:property value="queryModulesCondition.name"/>" name="queryModulesCondition.name"/></td>
					<td>模板内容：</td>
					<td><input type="text" value="<s:property value="queryModulesCondition.content"/>" name="queryModulesCondition.content"/></td>
					
					<td><img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:document.searchForm.submit()">查询&nbsp;&nbsp;</a></td>
					<td><img src="<%=contextPath%>/back/images/add_a.gif"/><a href="<%=contextPath%>/sys/modules!toAdd.action">添加&nbsp;&nbsp;</a></td>
					<td><img src="<%=contextPath%>/back/images/del_a.gif"/><a id="delModules" style="cursor:pointer;">删除</a></td>
					</form>
					</tr>
					</table>
				</div>
			</td>
			<td class="lists_tright lists_bor2" width="16"> </td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr class="lists_infobg">
							<td width="7%"><input type="checkbox" onclick="allCheck(this)" id="allC"/>全选</td>
							<td width="15%">模板编号</td>
							<td width="15%">模板名称</td>
							<td width="10%">模板路径</td>
							<td width="10%">操作</td>
						</tr>
						<s:iterator value="page.pageResult" id="obj">
							<tr>
								<td><input type="checkbox" name="ids" value=<s:property value="id"/> /></td>
								<td>
									<s:property value="id" />
								</td>
								<td>
									<s:property value="name" />
								</td>
								<td>
									/static/container/<s:property value="url" />/<s:property value="url" />.jsp
								</td>
								<td>
									<a href="<%=contextPath%>/sys/modules!toModify.action?modules.id=<s:property value="id"/>">修改</a>
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
