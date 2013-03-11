<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>模板列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function delTmpHistorys() {
		if(window.confirm("确认删除这些模板记录吗？")) {
			var ids = [];
			var arrEle = document.getElementsByName("ids");
			var len = arrEle.length;
			for(var i=0;i<len;i++){
				if(arrEle[i].checked==true){
					ids.push(arrEle[i].value);
				}
			}
			if(ids.length==0){
				alert("请选中您要删除的模板记录！");
				return ;
			}
			url = "<%=contextPath%>/cms/tmpHistory!delete.action";
			$.post(url, {ids: ids}, function(data){
				if(data==1){
					$("input[name=ids]:checkbox").attr('checked', false);
					window.location.reload();
				}else{
					alert("删除失败！");
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
	
	function resetKey() {
		$("#temp_type_0").attr("selected","selected");
		$("#tmpId").val("");
		$("#tmpName").val("");
		$("#startTime").val("");
		$("#endTime").val("");
	}
	
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
	}

	function search(){
		var $tmpId = $("#tmpId").val();
		var tmpId = $.trim($tmpId);

		if(tmpId!=""&&isNaN(tmpId)){
			alert("模板编号必须为数字！");
			return ;
		}
		var url = "<%=contextPath%>/cms/tmpHistory!search.action?queryTmpHistory.currentPage=1";
		if(tmpId != ""){
			url = url + "&queryTmpHistory.tmpId=" + tmpId;
		}
		var $tmpName = $("#tmpName").val();
		var tmpName = $.trim($tmpName);
		tmpName = encodeURIComponent(encodeURIComponent(tmpName));
		if(tmpName!=""){
			url = url + "&queryTmpHistory.tmpName=" + tmpName;
		}
		
		var tmpType = $("#tmpType").val();
		if(tmpType!=''&&tmpType!=-1){
			url = url + "&queryTmpHistory.tmpType=" + tmpType;
		}

		var startTime = $("#startTime").val();
		if(startTime!=""){
			url = url + "&queryTmpHistory.startTime=" + startTime;
		}
		
		var endTime = $("#endTime").val();
		if(endTime!=""){
			url = url + "&endTime=" + endTime;
		}

		if(startTime!=""&&endTime!=""&&(startTime>endTime)){
			alert("开始时间不能大于结束时间！");
			return;
		}
		
		window.location=url;
	}

	function restores(){
		if(window.confirm("确认还原这些模板记录吗？")) {
			var tmpId = [];
			var ids = [];
			var arrEle = document.getElementsByName("ids");
			var len = arrEle.length;
			for(var i=0;i<len;i++){
				if(arrEle[i].checked==true){
					tmpId.push($(arrEle[i]).attr("tmpId"));
					ids.push(arrEle[i].value);
				}
			}
			if(tmpId.length==0){
				alert("请选中您要还原的模板记录！");
				return ;
			}
			//去重
			tmpId = undulpicate(tmpId);
			url = "<%=contextPath%>/cms/tmpHistory!restore.action";
			$.post(url, {tmpId: tmpId, ids: ids}, function(data){
				if((data.jsonResult)==1&&(data.tmpIds)==""){
					alert('还原成功！');
				}else if(data.tmpIds!=""){
					alert("还原失败，编号为："+(data.tmpIds)+"的模板已被删除。");
				}else{
					alert("系统错误！");
				}
			}, 'json');
		}
	}
	function restore(tmpId, id){
		if(window.confirm("确认还原吗？")) {
			//去重
			url = "<%=contextPath%>/cms/tmpHistory!restore.action";
			$.post(url, {tmpId: tmpId, ids: id}, function(data){
				if((data.jsonResult)==1&&(data.tmpIds)==""){
					alert('还原成功！');
				}else if(data.tmpIds!=""){
					alert("还原失败，编号为："+(data.tmpIds)+"的模板已被删除。");
				}else{
					alert("系统错误！");
				}
			}, 'json');
		}
	}

	//数组去重
	function undulpicate(array){
		for(var i=0;i<array.length;i++) {
			for(var j=i+1;j<array.length;j++) {
				//注意 ===
				if(array[i]===array[j]) {
					array.splice(j,1);
					j--;
				}
			}
		}
		return array;
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
		<tr>
			<td width="12" class="lists_bor">
			<td>
				<div class="msg-xt">
					<table  border="0" cellspacing="0"  cellpadding="0">
					<tr>
					<td>
							模板编号：
							<input type="text" maxlength="9" value="<s:property value="queryTmpHistory.tmpId==0?'':queryTmpHistory.tmpId"/>" id="tmpId" />
							模板名称：
							<input type="text" maxlength="50" value="<s:property value="queryTmpHistory.tmpName"/>" id="tmpName" />
							模板类型：
							<select id="tmpType" >
								<option id="temp_type_0" value="-1" >--请选择--</option>
								<option value="0" <s:property value="queryTmpHistory.tmpType==0?'selected':''"/>>栏目模板</option>
								<option value="1" <s:property value="queryTmpHistory.tmpType==1?'selected':''"/>>文章模板</option>
								<option value="2" <s:property value="queryTmpHistory.tmpType==2?'selected':''"/>>其他模板</option>
							</select>
							添加/修改时间：
							<input type="text" value="<s:property value="queryTmpHistory.startTime" />" readonly id="startTime" width="10" onclick="WdatePicker()"/>
														--
							<input type="text" value="<s:property value="endTime" />" readonly id="endTime" onclick="WdatePicker()" width="10"/>
					</td>
						<td><img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:search()">查询&nbsp;&nbsp;</a></td>
						<td><img src="<%=contextPath%>/back/images/del_a.gif"/><a href="javascript:resetKey()">清空&nbsp;&nbsp;</a></td>
						<td><img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:restores()">还原&nbsp;&nbsp;</a></td>
						<td><img src="<%=contextPath%>/back/images/del_a.gif"/><a href="javascript:delTmpHistorys()">删除</a></td>
					</tr>
					</table>
					
				</div>
			</td>
		</tr>
		<tr>
			<!-- 
					<div class="msg-app">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
							模板编号：
							<input type="text" maxlength="9" value="<s:property value="queryTmpHistory.tmpId==0?'':queryTmpHistory.tmpId"/>" id="tmpId" />
							模板名称：
							<input type="text" maxlength="50" value="<s:property value="queryTmpHistory.tmpName"/>" id="tmpName" />
							
							模板类型：
							<select id="tmpType" >
								<option id="temp_type_0" value="-1" >--请选择--</option>
								<option value="0" <s:property value="queryTmpHistory.tmpType==0?'selected':''"/>>栏目模板</option>
								<option value="1" <s:property value="queryTmpHistory.tmpType==1?'selected':''"/>>文章模板</option>
								<option value="2" <s:property value="queryTmpHistory.tmpType==2?'selected':''"/>>其他模板</option>
							</select>
							添加/修改时间：
							<input type="text" value="<s:property value="queryTmpHistory.startTime" />" readonly id="startTime" width="10" onclick="WdatePicker()"/>
														--
							<input type="text" value="<s:property value="endTime" />" readonly id="endTime" onclick="WdatePicker()" width="10"/>
					</td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:search()">查询</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:resetKey()">清空</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:restores()">还原</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:delTmpHistorys()">删除</a></td></tr></table>
				</div>
				-->
			<td width="12" class="lists_bor">
			</td>
			<td>
				<form name="tmpForm" method="post">
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
						<tr class="lists_infobg">
							<td width="7%"><input type="checkbox" onclick="allCheck(this)"/>全选</td>
							<td width="15%">模板编号</td>
							<td width="15%">模板名称</td>
							<td width="10%">模板类型</td>
							<td width="10%">修改人</td>
							<td width="10%">添加/修改时间</td>
							<td width="10%">操作</td>
						</tr>
						<s:iterator value="page.pageResult" id="tmpHistory">
							<tr>
								<td><input type="checkbox" name="ids" id="ids" tmpId="<s:property value="tmpId"/>" value=<s:property value="id"/> /></td>
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
									<a href="javascript:void(0);" onclick="restore(<s:property value="tmpId" />, <s:property value="id" />)" >还原</a>
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
	</table>
</div>
</body>
</html>
