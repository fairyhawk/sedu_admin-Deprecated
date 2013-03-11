<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>预览模板</title>
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
				<font class="lists_fleft">预览模板</font>
				<font class="lists_fright">
						<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0">
							<tr>
								<td></td>
							</tr>
						</table>
						<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0">
							<tr>
								<td>&nbsp;</td>
								<td></td>
								</tr>
						</table>
						<table class="lists_fleft" width="90" border="0" cellspacing="0"  cellpadding="0">
							<tr>
								<td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
								<td><a href="javascript:addTemplate(0);">添加模板</a></td>
							</tr>
						</table>
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="1" bordercolor="#00ccff" style="border-collapse: collapse;font-size: 12px;">
							<tr id="regularlySent">
								<td width="10%" align="center">
									模板名称:
								</td>
								<td class="lists_tleft">
									<span>
										<s:property value='template.name' />
									</span>
								</td>
							</tr>
							<tr>
								<td align="center">
									模板内容:
								</td>
								<td class="lists_tleft">
									<span style="width: 100px;">
										<s:property value="template.content" escape="html"/>
									</span>
								</td>
							</tr>
							<tr>
								<td align="center">
									模板备注:
								</td>
								<td class="lists_tleft">
									<span>
										<s:property value="template.remark" />
									</span>
								</td>
							</tr>
							<tr>
								<td align="center">
									创建时间:
								</td>
								<td class="lists_tleft">
									<span>
										<s:date format="yyyy-MM-dd HH:mm:ss" name="template.pubdate" />
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<span style="padding-left: 350px;">
										<input type="button"  value="返  回" onclick="javascript:history.go(-1);"/>
									</span>
								</td>
							</tr>
				</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom"></td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>
<script  type="text/javascript">
	/**
	添加模板
	*/
	function addTemplate(){
		window.location.href="<%=contextPath%>/back/jsp/feed/template/template_add.jsp";
	}
</script>