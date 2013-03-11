<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>留言列表</title>
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
				<font class="lists_fleft">留言列表</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<form name="searchForm" action="<%=contextPath%>/feed/back!getReviewList.action" method="post">
							<input type="hidden" name="queryReviewCondition.currentPage" value="1" />
						
										<table width="100%" border="0" cellspacing="1" cellpadding="0"
											class="lists_info">
											<tr>
												<td colspan="4">
													时间:
													<input type="text" name="queryReviewCondition.startTime" id="startTime" value="${queryReviewCondition.startTime != -1 ? queryReviewCondition.startTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
													到<input type="text" name="queryReviewCondition.endTime" id="endTime" value="${queryReviewCondition.endTime != -1 ? queryReviewCondition.endTime : ''}" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/>
												</td>
											</tr>
											<tr>
												<td>
													课程:
													<select name="queryReviewCondition.courseId" id="courseId" onclick="courseSelectVideo(this.value);">
														<option value="-1">全部课程</option>
														<s:iterator value="courseList" var="item">
															<s:if test="#item.id == queryReviewCondition.courseId">
																<option value="<s:property value="#item.id"/>" selected="selected"><s:property value="#item.name"/></option>
															</s:if>
															<s:else>
																<option value="<s:property value="#item.id"/>"><s:property value="#item.name"/></option>
															</s:else>
														</s:iterator>
													</select>
													视频:
													<select name="queryReviewCondition.videoId" id="videoId" secondclassifyid="<s:property value="queryReviewCondition.videoId"/>">
													</select>
												</td>
											</tr>
											<tr align="center">
												<td colspan="4">
													<s:submit value="搜  索" />
												</td>
											</tr>
										</table>
									
						</form>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
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
								内容
							</td>
							<td>
								用户名
							</td>
							<td>
								主持数/反对数
							</td>
							<td>
								回复数
							</td>
							<td>
								发送时间
							</td>
							<td>
								状态
							</td>
							<td>
								操作
							</td>
						</tr>
						<s:iterator value="page.pageResult" id="review">
							<tr>
								<td>
									<s:property value="id" />
								</td>
								<td>
									<s:property value="content" />
								</td>
								<td>
									<s:property value="customer.email" />
								</td>
								<td>
									<s:property value="supportNumber" />/<s:property value="antilogNumber" />
								</td>
								<td>
									<s:property value="counts" />
								</td>
								<td>
									<s:date format="yyyy-MM-dd HH:mm:ss" name="pubDate" />
								</td>
								<td>
									<s:if test="status == 1">
										正常
									</s:if>
									<s:if test="status == 2">
										选中
									</s:if>
									<s:if test="status == 3">
										冻结
									</s:if>
									<s:if test="status == -1">
										<span title="屏蔽原因：<s:property value="detail" />" style="color: red;">屏蔽</span>
									</s:if>
								</td>
								<td>
									<a href="<%=contextPath%>/feed/back!toEditReview.action?id=<s:property value="id" />&currentPage=<s:property value="page.currentPage"/>">查看/屏蔽</a>
									<s:if test="status == 2">
										<a href="javascript:updateReviewStatus('<s:property value="id" />',1);">取消</a>
									</s:if>
									<s:else>
										<a href="javascript:updateReviewStatus('<s:property value="id" />',2);">选中</a>
									</s:else>
									
									<s:if test="status != 3">
										<a href="javascript:updateReviewStatus('<s:property value="id" />',3);">冻结</a>
									</s:if>
									<s:else>
										<a href="javascript:updateReviewStatus('<s:property value="id" />',1);">解冻</a>
									</s:else>
									<a href="javascript:void(0);" onclick="delReviewAction('<s:property value="id" />','<s:property value="targetReviewId" />');">删除</a>
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
		
			/**
				修改任务状态
			*/
			function delReviewAction(reviewId,targetReviewId){
			
				if(!confirm("确定要删除吗!")){
					return;
				}
				
				var url = "<%=contextPath%>/feed/back!doDelReviewById.action";
				var params = {
					id:reviewId,
					targetReviewId:targetReviewId
				}
				//使用$.post方式
				$.post(
					url,
					params,
					function topic(data){
						//条件成立，执行成功
						if(data == 1){
							alert("删除成功");
						}else if(data == 2){
							alert("删除失败");
						}else if(data == 3){
							alert("修改失败");
						}else{
							showErrorWin('删除失败!');
						}
					},
					'json'
				);
			}
			/**
				修改状态
			*/
			function updateReviewStatus(id,status){
				var url = "<%=contextPath%>/feed/back!updateReviewStatus.action";
				var params = {
					id:id,
					status:status
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
							showErrorWin('删除失败!');
						}
					},
					'json'
				);
			}
</script>
<script type="text/javascript">

	function courseSelectVideo(courseId){
				var url = "<%=contextPath%>/feed/back!getVideoListByCourseId.action";
				var params = {
					courseId : courseId
				}
				$.post(
					url,
					params,
					function topic(data){
						var videoList = data.split("|");
						
						var secondwapsort = document.getElementById("videoId");
						while(secondwapsort.hasChildNodes()){//如果有子元素、则删
							secondwapsort.removeChild(secondwapsort.childNodes[0]);
						}
						var enlargeAttributeValue = document.getElementById("videoId").getAttribute("secondclassifyid");
						/**在没有数据的情况下，采用默认值*/
						option = document.createElement("option");
					
						option.setAttribute("value", -1);
						option.appendChild(document.createTextNode("全部视频"));
						secondwapsort.appendChild(option);
						
						var videoSize = videoList.length;
						for(var i = 0;i<= videoSize; i++){
							try{
								
								var item = videoList[i].split(",");
								var id = item[0];
								var name = item[1];
								if(id != ""){
									option = document.createElement("option");
									option.setAttribute("value", id);
									/**如果等于当前项则默认选中*/
									if(id == enlargeAttributeValue){
										option.setAttribute("selected", "selected");
									}
									option.appendChild(document.createTextNode(name));
									secondwapsort.appendChild(option);
								}
							}catch(e){}
						}
					},
					'json'
				);
	}
	courseSelectVideo($("#courseId").val());
</script>