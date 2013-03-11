<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改话题</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript">
			function editSubmitVerify(){
				var txt_topic = $.trim($("#txt_topic").val());
				if(txt_topic.length == 0){
					alert("请填写内容");
					return;
				}
				document.getElementById("editForm").submit();
			}
		</script>
	</head>
	<body>
		<div>
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
						<td class="lists_top">
								<font class="lists_fleft">修改话题</font>
								<font class="lists_fright"></font>	
						</td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
					</tr>
					<tr>		
						<td class="lists_bor"></td>
						<td>
							<s:form action="back!doEditTopic.action" method="post" name="editForm" id="editForm">
								<!--隐藏字段-->
								<!-- id -->
								<input type="hidden" id="id" name="topic.id" value="<s:property value="topic.id"/>" />
								<!-- 标题 -->
								<input type="hidden" name="topic.title" value="<s:property value="topic.title"/>"/>
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
							
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info crm_lineh">
								<tr>
									<td width="10%" class="crm_tableFR">
										创建者：
									</td>
									<td class="lists_tleft">
										&nbsp;<s:if test="topic.customer.cusName == null || topic.customer.cusName == ''">
											<s:property value="topic.customer.email"/>
										</s:if>
										<s:else>
											<s:property value="topic.customer.cusName"/>
										</s:else>
									</td>
								</tr>
								<tr>
									<td width="10%" class="crm_tableFR">
										小&nbsp;组：
									</td>
									<td class="lists_tleft">
										&nbsp;<s:property value="discussion.name"/>
									</td>
								</tr>
								<tr>
									<td width="10%" class="crm_tableFR">
										信息类型：
									</td>
									<td class="lists_tleft">
										&nbsp;<select name="topic.type">
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
									<td width="10%" class="crm_tableFR">
										状&nbsp;态：
									</td>
									<td class="lists_tleft">
										&nbsp;<select name="topic.status">
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
									<td width="10%" class="crm_tableFR">
										内&nbsp;容：
									</td>
									<td class="lists_tleft">
										&nbsp;<textarea id="txt_topic" name="content" cols="100" rows="8" style="width:760px;height:365px;">${topic.content}</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="button" onclick="editSubmitVerify();" value="提  交" />
										&nbsp;&nbsp;<a href="<%=contextPath%>/dis/back!getReplyList.action?topic.id=${topic.id }&queryTopicReplyCondition.currentPage=1">回复管理</a>
									</td>
								</tr>
							</table>
							</s:form>
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
		</div>
	</body>
</html>