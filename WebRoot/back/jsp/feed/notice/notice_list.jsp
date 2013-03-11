<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>公告管理</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		
		<script type="text/javascript">
			/**
				修改状态
			*/
			function updateNoticeStatus(id,status){
				var url = "<%=contextPath%>/feed/notice!doUpdateStatusById.action";
				var params = {
					id:id,
					status:status
				}
				$.post(
					url,
					params,
					function topic(data){
						if(data == 1){
							document.location.reload();
						}else{
							alert("修改失败!");
						}
					},
					'json'
				);
			}
			function delNoticeAction(id,status){
			
			
				if(status != 2){
					alert("未冻结不可以删除");
					return;
				}
			
				if(!confirm("确定要删除吗!")){
					return;
				}
				
				var url = "<%=contextPath%>/feed/notice!doDelete.action";
				var params = {
					id:id
				}
				//使用$.post方式
				$.post(
					url,
					params,
					function topic(data){
						//条件成立，执行成功
						if(data == 1){
							document.location.reload();
						}else{
							alert("删除失败!");
						}
					},
					'json'
				);
			}
		</script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					查询条件
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<form action="<%=contextPath%>/feed/notice!toNoticeList.action" method="post">
				<input type="hidden" value="1" name="queryNoticeCondition.currentPage">
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr class="lists_left">
												<td colspan="2">
												时间:
													<input type="text" name="queryNoticeCondition.startTime" id="startTime" value="${queryNoticeCondition.startTime != -1 ? queryNoticeCondition.startTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													到<input type="text" name="queryNoticeCondition.endTime" id="endTime" value="${queryNoticeCondition.endTime != -1 ? queryNoticeCondition.endTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													类别：
													<select name="queryNoticeCondition.courseCategoryId">
														<option value="">全部</option>
														<s:iterator value="courseCategoryList" var="item">
															<s:if test="#item.id == param.courseCategoryId">
																<option value="<s:property value="#item.id" />" selected="selected"><s:property value="#item.categoryName" /></option>
															</s:if>
															<s:else>
																<option value="<s:property value="#item.id" />"><s:property value="#item.categoryName" /></option>
															</s:else>
														</s:iterator>
													</select>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<input type="submit" value="查询" />
												</td>
											</tr>
				</table>
			</form>
				<font class="lists_fright">
					<a href="<%=contextPath%>/feed/notice!toNoticeAdd.action">新建公告</a>
				</font>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							公告ID
						</td>
						<td>
							公告内容
						</td>
						<td>
							公告类别
						</td>
						<td>
							公开
						</td>
						<td>
							操作
						</td>
					</tr>
				<s:iterator value="page.pageResult">
					<tr>
						<td>
							<s:property value="id" />
						</td>
						<td>
							<s:property value="content" />
						</td>
						<td>
							<s:iterator value="courseCategoryList" var="item">
								<s:if test="courseCategoryId == #item.id">
									<s:property value="#item.categoryName" />
								</s:if>
							</s:iterator>
						</td>
						<td>
							<s:if test="status == 1">
								公开
							</s:if>
							<s:elseif test="status == 2">
								冻结
							</s:elseif>
						</td>
						<td>
							<a href="<%=contextPath%>/feed/notice!toUpdateById.action?id=<s:property value="id" />">查看</a>
							<a href="<%=contextPath%>/feed/notice!toUpdateById.action?id=<s:property value="id" />">修改</a>
							<s:if test="status == 2">
								<a href="javascript:updateNoticeStatus('<s:property value="id" />',1);">解冻</a>
							</s:if>
							<s:else>
								<a href="javascript:updateNoticeStatus('<s:property value="id" />',2);">冻结</a>
							</s:else>
							<a href="javascript:delNoticeAction('<s:property value="id" />','<s:property value="status" />');">删除</a>
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