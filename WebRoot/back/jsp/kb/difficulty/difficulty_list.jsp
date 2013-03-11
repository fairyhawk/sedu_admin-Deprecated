<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>体系结构</title>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />

		<script language="javascript">
		function newlevel(){
			var dId=document.getElementById('difficulty.dId').value;
			window.location.href="<%=contextPath%>/kb/difficulty!toAddDifficulty.action?difficulty.dId="+parseInt(dId)+""
		}
  		</script>
	</head>
	<body class="manage">
		<div class="main_right">
			<form name="form"
				action="<%=contextPath%>/kb/difficulty!toAddDifficulty.action"
				method="post" onsubmit="return verification()">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">当前位置:[预设项管理>>项目难度管理]</font>
							<font class="lists_fright"></font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
								<tr>
									<td>
										<input type="button" value="新增难度项" onclick="newlevel()" />
									</td>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td>
										项目序号
									</td>
									<td>
										项目名称
									</td>
									<td>
										操作
									</td>
								</tr>
								<s:iterator value="difficultyList" id="difficulty">
									<tr>
										<td>
											<s:property value="#difficulty.dLevel" />
											<input type="hidden" id="difficulty.dId"
												name="difficulty.dId"
												value="<s:property value="#difficulty.dId" />" />
										</td>
										<td>
											<s:property value="#difficulty.dName" />
										</td>
										<td>
											<a
												href='<%=contextPath%>/kb/difficulty!updateDifficulty.action?difficulty.dId=<s:property value="#difficulty.dId" />'>修改</a>
											<a
												href='<%=contextPath%>/kb/difficulty!delDifficulty.action?difficulty.dId=<s:property value="#difficulty.dId" />'
												onclick="return confirm('是否删除？')">删除</a>
										</td>
									</tr>
								</s:iterator>
							</table>
						</td>
						<td class="lists_tright lists_bor2">
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
	</body>
</html>

