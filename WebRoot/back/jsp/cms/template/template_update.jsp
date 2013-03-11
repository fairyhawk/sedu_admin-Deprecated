
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改模板信息</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript">
	$().ready(function() {
			$("#updateForm").validate();
	});
	function tobak(){
		$("#tmpContent_bak").val($("#tmpContent").val()) ;
		alert("备份成功") ;
	}
	function tovatting(){
		$("#tmpContent").val($("#tmpContent_bak").val()) ;
		alert("数据已恢复") ;
	}
	
	function restore(id){
		if(window.confirm("确认还原吗？")) {
			//去重
			url = "<%=contextPath%>/cms/tmpHistory!getById.action";
			$.post(url, {id: id}, function(data){
				if(data.jsonResult==1){
					$("#tmpContent").attr('value', data.history.tmpContent);
					$("#tmpName").attr('value', data.history.tmpName);
					$("#tmpType :eq("+data.history.tmpType+")").attr('selected', 'selected');
					alert("还原成功，请点击保存！");				
				}else{
					alert("还原失败！");
				}
			}, 'json');
		}
	}

	function delTmpHistory(id){
		if(window.confirm("确认删除吗？")) {
			url = "<%=contextPath%>/cms/tmpHistory!delete.action";
			$.post(url, {ids: id}, function(data){
				if(data==1){
					window.location.reload();
				}else{
					alert("删除失败！");
				}
			}, 'json');
		}
	}
	
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/cms/template!updateTemplate.action" method="post" name="updateForm" id="updateForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">修改模板</font>
				<font class="lists_fright">
					<!-- 
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:addNotes()">添加</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:delNotes()">删除</a></td></tr></table>
					 -->
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<div class="msg-xt">
				<table width="100%" border="0" cellspacing="1" cellpadding="0" >
					<tr>
						<td>
							<font color="red">*</font>模板名
						</td>
						<td class="lists_tleft">
							<input type="text" name="template.tmpName" id="tmpName" value="<s:property value='template.tmpName'/>" class="{required:true,minlength:4,maxlength:50}" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>模板类型
						</td>
						<td class="lists_tleft">
							<select id="tmpType" name="template.tmpType">
								<option value="0" <s:property value="template.tmpType==0?'selected':''"/>>栏目模板</option>
								<option value="1" <s:property value="template.tmpType==1?'selected':''"/>>文章模板</option>
								<option value="2" <s:property value="template.tmpType==2?'selected':''"/>>其他模板</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>模板内容
						</td>
						<td class="lists_tleft">
							<textarea name="template.tmpContent" id="tmpContent" style="width:650px;height:300px" class="{required:true}" ><s:property value="template.tmpContent"/></textarea>
							<textarea name="template.tmpContent_bak" id="tmpContent_bak" style="width:650px;height:300px"><s:property value="template.tmpContent_bak"/></textarea>
						</td>
						
					</tr>
					<tr>
						<td>
							<font color="red">*</font>是否可用
						</td>
						<td class="lists_tleft">
							<select name="template.isUse" id="isUse">
							<option value="1" <s:property value="template.isUse==1?'selected':''"/>>可用</option>
							<option value="0" <s:property value="template.isUse==0?'selected':''"/>>不可用</option>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="hidden" name="template.tmpId" value=<s:property value="template.tmpId"/> />
							<input type="submit" value="保存"/><input type="button" value="返回" onclick="history.go(-1)"></input>
							<input type="button" onclick="tobak()" value="备份"/>
							<input type="button" onclick="tovatting()" value="复原"/>
						</td>
					</tr>	
				</table>
			</div>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	</form>
	
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">模板修改记录</font>

			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<s:if test="page.pageResult.size()>0">
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<form name="tmpForm" method="post">
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
						<tr class="lists_infobg">
							<td width="7%">序号</td>
							<td width="15%">模板编号</td>
							<td width="15%">模板名称</td>
							<td width="10%">模板类型</td>
							<td width="10%">修改人</td>
							<td width="10%">添加/修改时间</td>
							<td width="10%">操作</td>
						</tr>
						<s:iterator value="page.pageResult" id="tmpHistory" status="status">
							<tr>
								<td><s:property value="(page.currentPage-1)*(page.pageSize)+(#status.index+1)" /></td>
								<td>
									<s:property value="tmpId" />
								</td>
								<td>
									<s:property value="tmpName" />
								</td>
								<td>
									<s:if test="tmpType==0">
										栏目模板
									</s:if>
									<s:elseif test="tmpType==1">
										文章模板
									</s:elseif>
									<s:else>
										其他模板
									</s:else>
								</td>
								<td>
									<s:property value="loginName" />
								</td>
								<td>
									<s:date name="tmpTime" format="yyyy-MM-dd HH:mm" />
								</td>
								<td>
									<a href="javascript:void(0);" onclick="restore(<s:property value="id" />)" >还原</a>
									<a href="javascript:void(0);" onclick="delTmpHistory(<s:property value="id" />)">删除</a>
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
		</s:if>
		<s:else>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td align="center">暂无!!!</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
		</s:else>
	</table>
	
</div>
</body>
</html>
