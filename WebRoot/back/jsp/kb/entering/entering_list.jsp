<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>录入管理</title>

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
	</head>

	<body>
		<form name="form"
			action="<%=contextPath%>/kb/entering!getKnowledgeListByPidOrIndex.action">
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">当前位置:[知识体系管理>>录入管理]</font>
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
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info">
							<tr>
								<td>
									专业：
									<s:select id="pId" name="pId" list="pList" listKey="pId"
										listValue="pName" headerValue="请选择" headerKey="0"
										theme="simple"></s:select>
									索引：
									<input type="text" name="index" id="index" />
									<s:submit value=" 查 询 " />
									<input type="button" value="新建结构树"
										onclick="window.location.href='<%=contextPath%>/kb/studyCourse!getStudyCourseList.action'" />
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
		<from>
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			class="lists">
			<tr >
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td>
					<font class="lists_fleft"></font>
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
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
						<tr>
							<td>
								专业名称
							</td>
							<td>
								科目名称
							</td>
							<td>
								包含章
							</td>
							<td>
								包含节
							</td>
							<td>
								知识单元
							</td>
							<td>
								1级知识单元
							</td>
							<td>
								2级知识单元
							</td>
							<td>
								3级知识单元
							</td>
							<td>
								状态
							</td>
							<td>
								创建时间
							</td>
						</tr>
						<s:iterator value="knowledgeList" id="knowledge">
							<tr>
								<td>
									<s:iterator value="pList" id="professional">
										<s:if test="#knowledge.plId==#professional.pId">
											<s:property value="#professional.pName" />
											<s:hidden id="knowledge.plId" name="knowledge.plId"></s:hidden>
										</s:if>

									</s:iterator>
								</td>
								<td>
									<s:property value="#knowledge.kName" />
								</td>
								<td>
									<s:property value="#knowledge.zSum" />
								</td>
								<td>
									<s:property value="#knowledge.jSum" />
								</td>
								<td>
									<s:property value="#knowledge.zsSum" />
								</td>
								<td>
									<s:property value="#knowledge.zsSum1" />
								</td>
								<td>
									<s:property value="#knowledge.zsSum2" />
								</td>
								<td>
									<s:property value="#knowledge.zsSum3" />
								</td>
								<td>
									<s:if test="#knowledge.kStuts==0">
										<s:label value="未发布"></s:label>
									</s:if>
									<s:else>
										<s:label value="已发布"></s:label>
									</s:else>
								</td>
								<td>
									<s:date name="#knowledge.kCreateTime"
										format="yyyy-MM-dd HH:mm:ss" />
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

				</td>
				<td>
					<img src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
		</table>
		</from>
	</body>
</html>
