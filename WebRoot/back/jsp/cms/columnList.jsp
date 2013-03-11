<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<link rel="StyleSheet" href="<%=contextPath %>/style/dtree.css" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript">
<!--
    var href='columnManager!queryColumnList.action';
	var	target="";
	function del(){
		if(confirm("确认删除所选信息?")==true){   
	        document.getElementById("delForm").submit();
		}
	}
	function check(listform,checkSign){
	 var f = document.getElementById(listform);
		with(f){
			for(var i=0;i<f.length;i++){
				if(elements[i].type=="checkbox"){
					elements[i].checked=checkSign.checked;
				}
			}
		}
	}
	function changeTR(str,type){
		if(type==1){
			document.getElementById(str+"trE").style.display="block";
			document.getElementById(str+"trM").style.display="block";
			document.getElementById(str+"a1").style.display="none";
			document.getElementById(str+"a0").style.display="block";
		}else if(type==0){
			document.getElementById(str+"trE").style.display="none";
			document.getElementById(str+"trM").style.display="none";
			document.getElementById(str+"a1").style.display="block";
			document.getElementById(str+"a0").style.display="none";
		}
	}
	
	function genericColumn(columnId) {
		window.location.href = "<%=contextPath%>/cms/column!genericColumn.action?queryColumnsCondition.columnId=" + columnId;
	}
	
	function genericArticle(columnId) {
		window.location.href = "<%=contextPath%>/cms/column!genericArticle.action?queryColumnsCondition.columnId=" + columnId;
	}
	
	function genericCourseAndTeacher(columnId) {
		window.location.href = "<%=contextPath%>/cms/column!genericJsonData.action?queryColumnsCondition.columnId=" + columnId;
	}

	function preArticle(columnId){
		$.ajax({  
			url : "<%=contextPath%>/cms/column!PreGenericColumn.action?queryColumnsCondition.columnId="+columnId,  
			type : "post",  
			success: function(msg){		
			var path="<%=contextPath%>"+(msg.replace(/\\/g,"/"));		
			window.open("<%=contextPath%>"+path);
			}
			});
	}
//-->
</script>
<style>
<!--
	textarea{
		width:98%;
		height:100px;
	}
-->
</style>
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">栏目列表&nbsp;&nbsp;&nbsp;&nbsp;[当前栏目位置:
					<s:iterator value="pathList"  id="cpath">
			       		<span>>></span><a href='column!showColumnList.action?Npid=${cpath.columnId }&Nindex=${pageResult.currentPage }'>${cpath.columnName }</a>
			       	</s:iterator>
			       	]
		       	</font>
				<font class="lists_fright">
					<table class="lists_fleft" width="90" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/edit_a.gif"/></td><td><a href="javascript:genericCourseAndTeacher(1)">json生成</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:del()">删除</a></td></tr></table>
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
				<form id="delForm" action="column!delColumn.action" method="post">
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
						<tr class="lists_infobg">
			           		<td><input type="checkbox" name="all" onclick="javascript:check('delForm',this);" /></td>
			           		<td>栏目ID</td>
							<td>栏目名称</td>
							<td>栏目目录</td>
							<td>是否显示</td>
							<td>排序</td>
							<td>生成类型</td>
							<td>默认模板</td>
							<td>文章模板</td>
							<td>操作</td>
						</tr>
			           	<s:iterator value="pageResult.pageResult" id="column">
				  			<tr>
				  				<td><input type="checkbox" name="delIds" value="${column.columnId}" /></td>
				  				<td>${column.columnId}</td>
				  				<td>${column.columnName}</td>
				  				<td>${column.catalog}</td>
				  				<td>${column.columnType}</td>
				  				<td>${column.sequence}</td>
				  				<td>
				  					<s:property value="staticType==0?'不生成':''"/>
				  					<s:property value="staticType==1?'栏目生成':''"/>
				  					<s:property value="staticType==2?'数据生成':''"/>
				  					<s:property value="staticType==3?'数据分页':''"/>	
				  				</td>
				  				<td>${column.indexTemplateId}</td>
				  				<td>${column.articleTemplateId}</td>
				  				<td>
				  					<div id='${column.columnId}a1'><a href='javascript:changeTR("${column.columnId}",1)'>展开</a></div>
				  					<div id='${column.columnId}a0' style="display: none;"><a href='javascript:changeTR("${column.columnId}",0);'>折叠</a></div>
				  					
				  					<a href='column!toEditColumn.action?id=${column.columnId}'>修改</a>
				  					
				  					|
				  					<s:if test="#column.isFinally!=1">
				  					<a href='column!showColumnList.action?Npid=${column.columnId}&lIndex=1'>查看下级栏目</a>
				  					</s:if>
				  					<a href="javascript:void(0)" onclick="genericColumn('${column.columnId}')">栏目生成</a>

|<a href="javascript:void(0)" onclick="preArticle('${column.columnId}')">预览</a>
				  					<!-- 
				  					<a href="javascript:void(0)" onclick="genericArticle('${column.columnId}')">文章生成</a>
				  					<button onclick="genericCourseAndTeacher('${column.columnId}')">课程教师生成</button>
				  					 -->
				  				</td>
				  			</tr>
				  			<tr id='${column.columnId}trE' style="display: none;">
				  				<td></td>
				  				<td>栏目说明</td>
				  				<td colspan="8">
				  					<textarea readonly="readonly">${column.explanation}</textarea>
				  				</td>
				  			</tr>
				  			<tr id='${column.columnId}trM' style="display: none;">
				  				<td></td>
				  				<td>栏目meta</td>
				  				<td colspan="8">
				  					<textarea readonly="readonly">${column.meta}</textarea>
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
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>
