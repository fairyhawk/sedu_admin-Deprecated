<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<%
	//该页面新建任务时，使用
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建小组</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
	</head>
	<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					视频地址列表
				</font>
				<font class="lists_fright">
					<a href="javascript:void(0);" onclick="addAllVideo()">全部添加</a>
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							<input type="checkbox" id='allOrNo' onclick="allCheck()"/>
						</td>
						<td>
							ID
						</td>
						<td>
							专业名称
						</td>
						<td>
							课程名称
						</td>
						<td>
							视频名称
						</td>
						<td>
							阶段数
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="video">
						<tr>
							<td>
								<input type="checkbox" name="checkAll" value="<s:property value='#video.name' />_<s:property value='#video.id' />"/>
							</td>
							<td>
								<s:property value="#video.id" />
							</td>
							<td>
								<s:property value="#video.subject.subjectName" />
							</td>
							<td>
								<s:property value="#video.course.name" />
							</td>
							<td>
								<s:property value="#video.name" />
							</td>
							<td>
								<s:property value="#video.stageNum" />
							</td>
							<td>
								<a href="javascript:void(0);" onclick="add('<s:property value='#video.name' />_<s:property value='#video.id' />&')">添加</a>
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
<script>
	 function add(o){
		 parent.addVideo(o);
	 	//window.location.href ="/feed/video!deleteVideo.action?id="+o;
	 }
	  
	  	  	     //全选，不选
    function allCheck(){
     	if($('#allOrNo').attr('checked') != true){
     		$("input[name='checkAll']").removeAttr("checked");
     	}else{
     		$("input[name='checkAll']").attr("checked",'true');//全选
     	}
     }
	 
	function addAllVideo(){

			var str=""; 
			$("[name='checkAll']").each(function(){ 
				if($(this).attr('checked') == true){
					str+=$(this).val()+"&"; 
				}
			});
			if(str == ""){
				alert('请选择要添加的视频');
				return;
			}else{
				parent.addVideo(str);
			}
		//parent.addVideo();
	}
</script>