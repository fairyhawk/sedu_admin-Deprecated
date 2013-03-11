<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建难度项</title>
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
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript">
			$().ready(function() {
	 			$("#addDifForm").validate();
			});
			
		function back(){
		
			window.location.href="<%=contextPath%>/kb/difficulty!showDifficultyList.action"
		}
		</script>
	</head>
	<body class="manage">
		<div class="main_right">
			<form name="addDifForm" id="addDifForm"
				action="<%=contextPath%>/kb/difficulty!AddDifficulty.action"
				method="post">

				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">当前位置:[预设项管理>>新建难度项]</font>
							<font class="lists_fright"> </font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="50%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr>
									<td>
										项目层级:
									</td>
									<td>
										<s:property value="difficulty.dLevel" />
										<s:hidden name="difficulty.dLevel" id="difficulty.dLevel"></s:hidden>
									</td>
								</tr>
								<tr>
									<td>
										项目名称:
									</td>
									<td>
										<input type="text" id="difficulty.dName"
											name="difficulty.dName"
											class="{required:true,minlength:2,maxlength:50}" />
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" value=" 提 交 " />
										<input type="button" value=" 返 回 " onclick="back()" />
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
			</form>
	</body>
</html>
