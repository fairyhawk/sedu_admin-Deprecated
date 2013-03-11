<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加话题</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript">
			function getDisAreaByDisId(disId){
				var url = "<%=contextPath%>/dis/back!getDisAreaByDisId.action";  
				var params = {
					disId:disId
				}
				$.post(
					url,
					params,
					function topic(data){
						var json = eval( "("+data+")" );

						var temp_html="";
						
						$("#disAreaId").empty();
						$.each(json,function(i,n){
							paddingSelect(json[i].id,json[i].name);
						});
					},
					'json'
				);
			}
			/**
				填充板块（区域）列表
			*/
			function paddingSelect(id,name){
				$("#disAreaId").append("<option value=\"" + id + "\">" + name + "</option>");
			}
			
			function addSubmitVerify(){
				
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
			<s:form action="back!doAddTopic.action" method="post" name="editForm" id="editForm">
				<!--隐藏字段-->
				<!-- 是否投票 default=0否 -->
		        <input type="hidden" id="voteStatus" name="voteStatus" value="0" />
		        <!-- 投票id -->
		        <input type="hidden" id="voteId" name="topic.voteId" value="0" />
				<!--推荐次数-->
				<input type="hidden" id="recCount" name="topic.recCount" value="0" />
				<!--积分数-->
				<input type="hidden" id="recNum" name="topic.recNum" value="0" />
				<!--是否允许回复-->
				<input type="hidden" id="canReply" name="canReply" value="0" />
				<!--是否置顶-->
				<input type="hidden" id="topStatus" name="topStatus" value="0" />
				<input type="hidden" id="voteCount" name="topic.voteCount" value="0" />
				<!-- 点击次数，回复次数 -->
				<input type="hidden" id="clickCounts" name="topic.clickCounts" value="0" />
				<input type="hidden" id="replyCounts" name="topic.replyCounts" value="0" />
				
				<input type="hidden" name="customer.cusId" value="<s:property value="cusId"/>" />
				
				
				<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
						<td class="lists_top">
								<font class="lists_fleft">添加话题</font>
								<font class="lists_fright"></font>	
						</td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
					</tr>
					<tr>		
						<td class="lists_bor"></td>
						<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info crm_lineh">
								<tr>
									<td width="10%" class="crm_tableFR">
										创建者：
									</td>
									<td class="lists_tleft">
										&nbsp;<s:property value="#session.sys_customer.cusName"/>
									</td>
								</tr>
								<tr>
									<td width="10%" class="crm_tableFR">
										小&nbsp;组：
									</td>
									<td class="lists_tleft">
										&nbsp;<select id="disId" name="disId">
											<s:iterator value="discussionList" var="dis">
												<option value="<s:property value="#dis.id"/>"><s:property value="#dis.name"/></option>
											</s:iterator>
										</select>
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
										<input type="button" onclick="addSubmitVerify();" value="提  交" />
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