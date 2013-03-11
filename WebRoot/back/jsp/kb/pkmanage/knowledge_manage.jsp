<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>书籍组列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script language="JavaScript">
	
	</script>
	</head>
	<body>
<div>
	<form name="f1" method="post" action="booksgroup!getBooksGroupList.action">
	<s:hidden name="queryBooksgroupCondition.currentPage" value="1" />
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">知识体系管理</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" >
					<tr class="lists_infobg">
						<td>
							<strong><font size="3">录入管理</font></strong><br/>
							添加和编辑科目\章节\知识单元以及更小级的课程节点<br/>
							<a href="<%=contextPath%>/kb/entering!getProfessionalList.action">直接进入</a>
						</td>
						<td>
							<strong><font size="3">审核管理</font></strong><br/>
							审核已经建立的科目知识体系<br/>
							<a href="#">直接进入</a>
						</td>
						
						
					</tr>
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
				
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
	</body>
</html>
