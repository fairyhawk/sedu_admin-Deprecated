<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
<!--

	//全选
	function checkAll(listform,checkSign){
	 var f = document.getElementById(listform);
		with(f){
			for(var i=0;i<f.length;i++){
				if(elements[i].type=="checkbox"){
					elements[i].checked=checkSign.checked;
				}
			}
		}
	}

	function search(){
		var url = "<%=contextPath%>/cms/acment!search.action?queryAcmentCondition.currentPage=1"		
		var $title = $("#title").val();
		var title = $.trim($title);
		if(title!=""){
			title = encodeURIComponent(encodeURIComponent(title));
			url = url+"&queryAcmentCondition.title="+title;
		}
		var subjectId = $("#subjectId").val();
		if(subjectId!=-1){
			url = url+"&queryAcmentCondition.subjectId="+subjectId;
		}
		var startTime = $("#startTime").val();
		if(startTime!=""){
			url = url+"&queryAcmentCondition.startTime="+startTime;
		}
		var endTime = $("#endTime").val();
		if(endTime!=""){
			url = url+"&endTime="+endTime;
		}
		if((startTime!=""&&endTime!="")&&(startTime>endTime)){
			alert("开始时间不能大于结束时间！");
			return ;
		}
		window.location=url;
	}

	function editStatus(status) {
		var ids = [];
		var arrEle = document.getElementsByName("ids");
		var len = arrEle.length;
		for(var i=0;i<len;i++){
			if(arrEle[i].checked==true){
				ids.push(arrEle[i].value);
			}
		}
		if(ids.length==0){
			alert("请选中您要操作的公告列表！");
			return ;
		}
		url = "<%=contextPath%>/cms/acment!editStatus.action";
		$.post(url, {status: status, ids: ids}, function(data){
			if(data==1){
				$("input[name=ids]:checkbox").attr('checked', false);
				$("input[name=all]:checkbox").attr('checked', false);
				window.location.reload();
			}else{
				alert("设置失败！");
			}
		}, 'json');
	}

	function editStat(status, id){
		url = "<%=contextPath%>/cms/acment!editStatus.action";
		$.post(url, {status: status, ids: id}, function(data){
			if(data==1){
				$("input[name=ids]:checkbox").attr('checked', false);
				$("input[name=all]:checkbox").attr('checked', false);
				window.location.reload();
			}else{
				alert("设置失败！");
			}
		}, 'json');
	}

	function dels(){
		if(window.confirm("确认删除这些公告吗？")) {
			var ids = [];
			var arrEle = document.getElementsByName("ids");
			var len = arrEle.length;
			for(var i=0;i<len;i++){
				if(arrEle[i].checked==true){
					ids.push(arrEle[i].value);
				}
			}
			if(ids.length==0){
				alert("请选中您要删除的公告列表！");
				return ;
			}
			url = "<%=contextPath%>/cms/acment!delete.action";
			$.post(url, {ids: ids}, function(data){
				if(data==1){
					$("input[name=ids]:checkbox").attr('checked', false);
					$("input[name=all]:checkbox").attr('checked', false);
					window.location.reload();
				}else{
					alert("删除失败！");
				}
			}, 'json');
		}
	}

	function del(id){
		if(window.confirm("确认删除吗？")) {
			url = "<%=contextPath%>/cms/acment!delete.action";
			$.post(url, {ids: id}, function(data){
				if(data==1){
					$("input[name=ids]:checkbox").attr('checked', false);
					$("input[name=all]:checkbox").attr('checked', false);
					window.location.reload();
				}else{
					alert("删除失败！");
				}
			}, 'json');
		}
	}
	
//-->
</script>
 </head>
 <body>
<div id="rightframe">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td style="font-size:0px;width:12px;">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">公告列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
						  搜索: 公告标题：<input type="text" maxlength="100" name="announcement.title" id="title" value='<s:property value="queryAcmentCondition.title"/>' />
						 所属专业：
						<s:select list="sujectbList" headerKey="-1"
							headerValue="请选择专业..." listKey="subjectId"
							listValue="subjectName" name="queryAcmentCondition.subjectId"
							id="subjectId" listTitle="subjectName">
						</s:select>
						添加时间：
						<input type="text" value="<s:property value="queryAcmentCondition.startTime" />" readonly id="startTime" width="10" onclick="WdatePicker()"/>
														--
						<input type="text" value="<s:property value="endTime" />" readonly id="endTime" onclick="WdatePicker()" width="10"/>
					</td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:search()">查询</a></td></tr></table>
					<table class="lists_fleft" width="70" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/edit_a.gif"/></td><td><a href="javascript:editStatus(0);">隐藏</a></td></tr></table>
					<table class="lists_fleft" width="70" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/edit_a.gif"/></td><td><a href="javascript:editStatus(1);">显示</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:dels()">删除</a></td></tr></table>
				</font>
			</td>
			<td style="font-size:0px;width:12px;">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<form id="acmentForm" method="post">
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr class="lists_infobg">
							<td><input type="checkbox" name="all" id="all" onclick="javascript:checkAll('acmentForm',this);" /></td>
							<td width="15%">所属专业</td>
							<td width="20%">公告标题</td>
							<td width="10%">作者</td>
							<td width="10%">点击数</td>
							<td width="10%">显示状态</td>
							<td width="15%">时间</td>
							<td width="20%">操作</td>
						</tr>
			           	<s:iterator value="page.pageResult" id="acment">
				  			<tr valign="middle" align="center">
				  				<td><input type="checkbox" name="ids" id="ids" value='<s:property value="#acment.id"/>' /></td>
				  				<td><s:property value="#acment.subjectName!=null?#acment.subjectName:'全站公告'"/></td>
				  				<td><s:property value="#acment.title"/></td>
				  				<td><s:property value="#acment.author"/></td>
				  				<td><s:property value="#acment.clickNum"/></td>
								<td>
									<s:if test="#acment.status==1">
										显示
									</s:if>
									<s:else>
										隐藏
									</s:else>
								</td>
				  				<td><s:date name="#acment.addTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				  				<td>
				  					<a href='acment!toEdit.action?id=<s:property value="#acment.id"/>'>修改</a>
				  					<a href="javascript:void(0)" onclick="del(<s:property value="#acment.id"/>);"> 删除</a>
					  				<s:if test="#acment.status==1">
				  						<a href="javascript:void(0);" onclick="editStat(0, <s:property value="#acment.id"/>);">
				  							隐藏
				  						</a>
									</s:if>
									<s:else>
										<a href="javascript:void(0);" onclick="editStat(1, <s:property value="#acment.id"/>);">
				  							显示
				  						</a>
									</s:else>
				  					<a target="_blank" href='<%=contextPath%>/cms/acment!preview.action?id=<s:property value="#acment.id"/>'> 预览</a>
				  				</td>
				  			</tr>
				  		</s:iterator>
					</table>
				</form>
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
