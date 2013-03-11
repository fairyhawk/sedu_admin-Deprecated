<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建知识单元</title>
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
	 			$("#updateEntering").validate();
			});
			function Version(pid){
				var kid = document.getElementById("knowledge.kId").value;
				window.location.href="<%=contextPath%>/kb/entering!toUpdateKnowledge.action?pId="+pid+"&knowledge.kId="+kid+"";
			}
		</script>

	</head>
	<body class="manage">
		<form name="updateEntering" id="updateEntering"
			action="<%=contextPath%>/kb/entering!updateKnowledge.action"
			method="post">
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">当前位置:[知识体系管理>>录入修改]</font>
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
									专业:
								</td>
								<td>
									<s:select name="pId" id="pId" list="pList" listKey="pId"
										listValue="pName" headerValue="请选择" headerKey="-1"
										theme="simple" onchange="Version(this.value)">
									</s:select>
								</td>
							</tr>
							<tr>
								<td>
									知识名称:
								</td>
								<td>
									<input type="text" name="knowledge.kName" id="knowledge.kName"
										value="<s:property value="knowledge.kName"/>"
										class="{required:true,minlength:2,maxlength:50}" />
								</td>
							</tr>
							<tr>
								<td>
									时间版本
								</td>
								<td>
									<s:property value="knowledge.kVersion" />
								</td>
							</tr>
							<tr>
								<td>
									状态:
								</td>
								<td>
									<s:if test="knowledge.kStuts==0">
										<s:label value="未发布"></s:label>
									</s:if>
									<s:else>
										<s:label value="已发布"></s:label>
									</s:else>
								</td>
							</tr>
							<tr>
								<td>
									创建时间:
								</td>
								<td>
									<s:property value="knowledge.kCreateTime" />
								</td>
							</tr>
							<tr>
								<td>
									说明:
								</td>
								<td>
									<textarea rows="" cols="" name="knowledge.kNote" class="{required:true,minlength:4,maxlength:100}" style="height: 80px; width: 60%;""><s:property value="knowledge.kNote" /></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:hidden id="knowledge.kId" name="knowledge.kId"></s:hidden>
									<s:hidden id="knowledge.kVersion" name="knowledge.kVersion"></s:hidden>
									<s:hidden id="knowledge.upIndex" name="knowledge.upIndex"></s:hidden>
									<s:hidden id="knowledge.kIndex" name="knowledge.kIndex"></s:hidden>
									<s:hidden id="knowledge.kPId" name="knowledge.kPId"></s:hidden>
									<s:hidden id="knowledge.scId" name="knowledge.scId"></s:hidden>
									<s:submit value=" 提 交 "></s:submit>
									<input type="button" value=" 返 回 " onclick="window.location.href='<%=contextPath%>/kb/entering!getProfessionalList.action'"/>
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
