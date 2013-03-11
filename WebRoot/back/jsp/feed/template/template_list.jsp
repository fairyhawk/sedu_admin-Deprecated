<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>模板列表</title>
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
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">模板列表</font>
				<font class="lists_fright"><img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:addTemplate(0);">添加模板</a></font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<div>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr class="lists_infobg">
							    <td width="10%">
							      	编      号
							    </td>
								<td width="20%">
									模板名称
								</td>
								<td width="50%">
									模板备注
								</td>
								<td width="10%">
									创建时间
								</td>
								<td>
									操	作
								</td>
							</tr>
							<s:iterator value="page.pageResult">
								<tr>
								    <td>
							      	<s:property value="id" />
							   		</td>
									<td>
										<s:property value="name" />
									</td>
									<td>
										<s:property value="remark" />
									</td>
									<td>
										<s:date format="yyyy-MM-dd HH:mm:ss" name="pubdate" />
									</td>
									<td>
										<span>
											<a href="javascript:previewTemplate('<s:property value="id" />');">预览</a>&nbsp;|&nbsp;
											<a href="javascript:editTemplate('<s:property value="id" />');">编辑</a>&nbsp;|&nbsp;
											<a href="javascript:delTemplate('<s:property value="id" />');">删除</a>
										</span>
									</td>
								</tr>
							</s:iterator>
							</table>
					</div>
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
			/**
				添加模板
			*/
			function addTemplate(){
				window.location.href="<%=contextPath%>/back/jsp/feed/template/template_add.jsp";
			}
			
			/**
			删除模板
			*/
			function delTemplate(id){
				
				if(!confirm("确定要删除吗!")){
					return;
				}
				
				var url = "<%=contextPath%>/feed/template!delTemplate.action";
				var params = {
					id:id
				}
				//使用$.post方式
				$.post(
					url,
					params,
					function template(data){
						//条件成立，执行成功
						if(data == 1){
							document.location.reload();
						}else{
							showErrorWin('删除失败!');
						}
					},
					'json'
				);
			}

			/**
			编辑模板
			*/
			function editTemplate(id){
				window.location.href="<%=contextPath%>/feed/template!editTemplate.action?id="+id;
			}
			/**
			预览模板
			*/
			function previewTemplate(id){
				window.location.href="<%=contextPath%>/feed/template!previewTemplate.action?id="+id;
			}	
</script>