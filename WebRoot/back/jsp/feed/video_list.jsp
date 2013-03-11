<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>显示视频页面</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					视频地址列表
				</font>
				<font class="lists_fright">
					&nbsp;
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
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
							视频地址
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="video">
						<tr>
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
								<s:property value="#video.url" />
							</td>
							<td>
								<a href="javascript:void(0);" onclick="javascript:del(<s:property value='#video.id' />);return false;">删除</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>
<script>
	//deleteVideo
	 function del(o){
	 	if(confirm("确定要删除该视频!")){
	 		window.location.href ="<%=contextPath%>/feed/video!deleteVideo.action?id=" + o + "&courseId=${queryMicroVideoCondition.courseId}";
	 	}
	 }
	  //editorVideo
	  function editor(o){
	  	window.location.href ="<%=contextPath%>/feed/video!editorVideo.action?id="+o;
	  }
</script>