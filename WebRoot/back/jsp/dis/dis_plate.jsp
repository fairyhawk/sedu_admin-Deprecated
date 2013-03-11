<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>板块列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
			function resetKey() {
				$("#op0").attr("selected", "selected");
				$("#audit_op_0").attr("selected", "selected");
				$("input[name=queryCommentCondition.cmtInfo]").val("");
			}
			function allCheck(cb) {
				$("input[name=ids]:checkbox").attr('checked', cb.checked);
			}
			function delTopicMore() {
				var s = '';
				$('input[name=ids]:checked').each(function() {
					s += $(this).val() + ',';
				});
				location.href = "dis/back!delTopPicMore.action?ids=" + s;
			}
		</script>
	</head>
	<body>
		<div><!--
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="60px" style="font-size: 12px;">
				<tr>
					<td width="35%"></td>
					<td>
						<form name="searchForm" action="dis/back!topicListByWhere.action"
							method="post">
							<input type="hidden" name="topicWhere.currentPage" value="1" />
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr width="100%">
									<td>
										模块名称: &nbsp;
										<input type="text" value="" name="topicWhere.searchWord" />
										&nbsp;
										<select id="searchType" name="topicWhere.searchType">
											<option value="0">
												-所有-
											</option>
										</select>
										&nbsp;
										<input type="submit" " value="添加" />
									</td>
								</tr>
							</table>
						</form>
					</td>
					<td width="35%"></td>
				</tr>
				<tr>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
			</table>
			--><div>
			
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">

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
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<form name="cusForm" method="post">					
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="lists_info" onmouseover="changeto()"
									onmouseout="changeback()">
									<tr class="lists_infobg">
										<td>
											<!--<input type="checkbox" onclick="allCheck(this)" />-->
											序号
										
										</td>
										<td>
											项目名称
										</td>
										<td>
											模块名称
										</td>
										<td>
											话题数量
										</td>
										<td>
											创建人
										</td>
										<td>
											创建时间
										</td>
										<!--<td>
											操作
										</td>
									-->
									</tr>
									<s:iterator value="page.pageResult" id="topic">
										<tr>
											<td>
												<!--<input type="checkbox" name="ids"
													value=<s:property value="id"/> />
												-->
												<s:property value="id" />
											</td>
											<td>
												<s:property value="subjectName" />
											</td>
											<td>
												<s:property value="areaName" />
											</td>
											<td>
												<s:property value="topicCount" />
											</td>
											<td>
												<s:property value="loginName" />
											</td>
											<td>
												<s:property value="createTime.substring(0,10)" />
											</td>
											<!--
											<td>
												<a href="#">修改</a>|
												<a
													href="<%=contextPath%>/dis/back!delTopPic.action?toPicDTO.id=">删除</a>
											</td>
										    -->
										</tr>
									</s:iterator>
								</table>
							</form>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>