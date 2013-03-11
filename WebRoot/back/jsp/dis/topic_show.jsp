<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>话题展示</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
	</head>
	<body>
		<div>
			<s:form action="back!doEditTopic.action" method="post" name="editForm" id="editForm">
				<!--隐藏字段-->
				<!-- id -->
				<input type="hidden" id="id" name="topic.id" value="<s:property value="topic.id"/>" />
				<!-- 是否投票 default=0否 -->
		        <input type="hidden" id="voteStatus" name="voteStatus" value="<s:property value="topic.isVote"/>" />
		        <!-- 投票id -->
		        <input type="hidden" id="voteId" name="topic.voteId" value="<s:property value="topic.voteId"/>" />
				<!--推荐次数-->
				<input type="hidden" id="recCount" name="topic.recCount" value="<s:property value="topic.recCount" />" />
				<!--积分数-->
				<input type="hidden" id="recNum" name="topic.recNum" value="<s:property value="topic.recNum" />" />
				<!--是否允许回复-->
				<input type="hidden" id="canReply" name="canReply" value="<s:property value="topic.canReply" />" />
				<!--是否置顶-->
				<input type="hidden" id="topStatus" name="topStatus" value="<s:property value="topic.isTop" />" />
				<!-- 发布，回复时间 -->
				<input type="hidden" id="createTime" name="createTime" value="<s:property value="createTime" />" />
				<input type="hidden" id="replyTime" name="replyTime" value="<s:property value="replyTime" />" />
				<input type="hidden" id="voteCount" name="topic.voteCount" value="<s:property value="topic.voteCount" />" />
				<!-- 点击次数，回复次数 -->
				<input type="hidden" id="clickCounts" name="topic.clickCounts" value="<s:property value="topic.clickCounts" />" />
				<input type="hidden" id="replyCounts" name="topic.replyCounts" value="<s:property value="topic.replyCounts" />" />
				
				<input type="hidden" name="customer.cusId" value="<s:property value="topic.customer.cusId" />" />
				<input type="hidden" name="disId" value="<s:property value="topic.disId" />" />
				<input type="hidden" name="disAreaId" value="<s:property value="topic.disAreaId" />" />
				
				
				
				
				<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
						<td class="lists_top">
								<font class="lists_fleft">话题展示</font>
								<font class="lists_fright"></font>	
						</td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
					</tr>
					<tr>		
						<td class="lists_bor"></td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr>
									<td width="20%">
										创建者
									</td>
									<td class="lists_tleft">
										<s:if test="topic.customer.cusName == null || topic.customer.cusName == ''">
											<s:property value="topic.customer.email"/>
										</s:if>
										<s:else>
											<s:property value="topic.customer.cusName"/>
										</s:else>
									</td>
								</tr>
								<tr>
									<td width="20%">
										小组
									</td>
									<td class="lists_tleft">
										<s:property value="discussion.name"/>
									</td>
								</tr>
								
								<tr>
									<td width="20%">
										信息类型
									</td>
									<td class="lists_tleft">
										<select name="topic.type">
											<s:iterator value="typeTopicList" var="item">
												<s:if test="topic.type == #item.value">
													<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
												</s:if>
												<s:else>
													<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
												</s:else>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td width="20%">
										状态
									</td>
									<td class="lists_tleft">
										<select name="topic.status">
											<s:iterator value="statusTopicList" var="item">
												<s:if test="topic.status == #item.value">
													<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
												</s:if>
												<s:else>
													<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
												</s:else>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td width="20%">
										内容
									</td>
									<td class="lists_tleft">
										<textarea id="txt_topic" name="content" cols="100" rows="8" style="width:760px;height:365px;">${topic.content}</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<a href="<%=contextPath%>/dis/back!getReplyList.action?topic.id=${topic.id }&queryTopicReplyCondition.currentPage=1">回复管理</a>
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
			</s:form>
		</div>
	</body>
</html>