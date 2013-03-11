<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>话题列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<%-- 
		<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_public.css" />
		<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_layout.css" />
		<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_popup.css" />
		--%>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			var preWin;
			function showErrorWin(errorText, win) {
		 		if(win != null  && win !='') {
					$("#" + win).css("display", "none");
					preWin = win;
		 		}
		 		lockingWindow();
		 		$("#error_win").fadeIn();
		 		$("#error_win").css("top", document.documentElement.scrollTop + document.documentElement.clientHeight * 0.15);
		 		$("#error_message").html(errorText);
		 	 }
		 	//黑底效果
			function lockingWindow(){ 
				document.getElementById("web_top_black").style.width = document.documentElement.clientWidth + "px";
				document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight + "px";
				$("#web_top_black").fadeIn();
				//document.getElementById("web_top_black").style.display = "block"; 
			}
			function closeErrorWin() {
		 		if(preWin != null  && preWin !='') {
					$("#" + preWin).fadeIn();
		 		} else {
					$("#web_top_black").fadeOut();
		 		}
		 		$("#error_win").fadeOut();
		 		preWin = null;
		 	}
		 	function showWin(winId) {
				preWin = winId;
		 		lockingWindow();
		 		$("#" + winId).fadeIn();
		 		$("#" + winId).css("top", document.documentElement.scrollTop + document.documentElement.clientHeight * 0.15);
		 	}
		 	function closeWin(winId) {
				$("#web_top_black").fadeOut();
		 		$("#" + winId).fadeOut();
		 		preWin = null;
		 	}
		</script>
		<script type="text/javascript">
			
		</script>
		<script type="text/javascript">
		
			var actionUrl;//请求地址
			var actionId;//帖子id
			var topicTrItemId;
			var topicCusId;
			
			function delTopicActionFirst(url,id,topicTrItem,topicCusIdItem){
	
				actionUrl = url;
				actionId = id;
				topicTrItemId = topicTrItem;
				topicCusId = topicCusIdItem;
				
				$("#popupContactDeleteInfoText").html("确认删除!");
				showWin("popupContactDeleteInfo");
			}
			function delTopicAction(){
				var url = actionUrl;  
				var params = {
					id:actionId,
					topicCusId:topicCusId
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
			
			/**
				编码含有中文字符字段
			*/
			function encodeCommon(){
				$("#keyWorld").val(encodeURIComponent($("#world").val()));
				
				return true;
			}
			/**
				时间控件中只显示 yyyy-MM-dd 格式
			*/
			$(function(){
				$("#startTime").val($("#startTime").val().substring(0,10));
				$("#endTime").val($("#endTime").val().substring(0,10));
				$("#modifiedStart").val($("#modifiedStart").val().substring(0,10));
				$("#modifiedEnd").val($("#modifiedEnd").val().substring(0,10));
			})
		</script>
	</head>
	<body>
		<!--头开始-->
		<%@include file="include/uc_header.inc" %>
		<div>
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top">
							<font class="lists_fleft">话题列表</font>
							<font class="lists_fright"></font>	
					</td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>		
					<td class="lists_bor"></td>
					<td>
						<div class="msg-app">
						<form name="searchForm" action="<%=contextPath%>/dis/back!searchTopic.action" method="post" onsubmit="return encodeCommon();">
							<input type="hidden" name="queryTopicCondition.currentPage" value="1" />
							<input type="hidden" id="keyWorld" name="queryTopicCondition.keyWorld" value="" />
										<table width="100%" cellspacing="1" cellpadding="0" border="0">
											<tr>
												<td class="lists_fright">
													关键字：
												</td>
												<td>
														<input type="text" id="world" value="${queryTopicCondition.keyWorld}"/>
														<select name="queryTopicCondition.subjectId">
															<option value="-1">项目</option>
															<s:iterator value="#session.session_subjectList" var="item">
																<s:if test="queryTopicCondition.subjectId == #item.subjectId">
																	<option value="<s:property value="#item.subjectId"/>" selected="selected"><s:property value="#item.subjectName"/></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="#item.subjectId"/>"><s:property value="#item.subjectName"/></option>
																</s:else>
															</s:iterator>
														</select>
														<!-- 
														<select name="queryTopicCondition.disAreaId">
															<option value="-1">板块</option>
														</select>
														 -->
														<select name="queryTopicCondition.searchCriteria">
															<option value="-1">检索条件</option>
															<s:iterator value="searchCriteriaList" var="item">
																<s:if test="queryTopicCondition.searchCriteria == #item.value">
																	<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
																</s:else>
															</s:iterator>
														</select>
														<select name="queryTopicCondition.type">
															<option value="-1">信息类型</option>
															<s:iterator value="typeTopicList" var="item">
																<s:if test="queryTopicCondition.type == #item.value">
																	<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
																</s:else>
															</s:iterator>
														</select>
														<select name="queryTopicCondition.status">
															<option value="-1" selected="selected">状态</option>
															<s:iterator value="statusTopicList" var="item">
																<s:if test="queryTopicCondition.status == #item.value">
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
												<td class="lists_fright">
													发布时间：
												</td>
												<td>
													<input type="text" name="queryTopicCondition.createTimeStart" readonly id="startTime"
														value="${queryTopicCondition.createTimeStart != -1 ? queryTopicCondition.createTimeStart : ''}" width="10" onclick="WdatePicker()"/>
														<select name="startHH" id="startHH">
														<option value=" 00:00:00" id="op0">00:00:00</option>
														<option value=" 01:00:00">01:00:00</option>
														<option value=" 02:00:00">02:00:00</option>
														<option value=" 03:00:00">03:00:00</option>
														<option value=" 04:00:00">04:00:00</option>
														<option value=" 05:00:00">05:00:00</option>
														<option value=" 06:00:00">06:00:00</option>
														<option value=" 07:00:00">07:00:00</option>
														<option value=" 08:00:00">08:00:00</option>
														<option value=" 09:00:00">09:00:00</option>
														<option value=" 10:00:00">10:00:00</option>
														<option value=" 11:00:00">11:00:00</option>
														<option value=" 12:00:00">12:00:00</option>
														<option value=" 13:00:00">13:00:00</option>
														<option value=" 14:00:00">14:00:00</option>
														<option value=" 15:00:00">15:00:00</option>
														<option value=" 16:00:00">16:00:00</option>
														<option value=" 17:00:00">17:00:00</option>
														<option value=" 18:00:00">18:00:00</option>
														<option value=" 19:00:00">19:00:00</option>
														<option value=" 20:00:00">20:00:00</option>
														<option value=" 21:00:00">21:00:00</option>
														<option value=" 22:00:00">22:00:00</option>
														<option value=" 23:00:00">23:00:00</option>
														<option value=" 23:59:59">23:59:59</option>
														</select> --
														<input type="text" name="queryTopicCondition.createTimeEnd" readonly id="endTime"
														onclick="WdatePicker()" value="${queryTopicCondition.createTimeEnd != -1 ? queryTopicCondition.createTimeEnd : ''}" width="10"/>
														<select name="endHH" id="endHH">
														<option value=" 23:59:59" id="op0">23:59:59</option>
														<option value=" 00:00:00">00:00:00</option>
														<option value=" 01:00:00">01:00:00</option>
														<option value=" 02:00:00">02:00:00</option>
														<option value=" 03:00:00">03:00:00</option>
														<option value=" 04:00:00">04:00:00</option>
														<option value=" 05:00:00">05:00:00</option>
														<option value=" 06:00:00">06:00:00</option>
														<option value=" 07:00:00">07:00:00</option>
														<option value=" 08:00:00">08:00:00</option>
														<option value=" 09:00:00">09:00:00</option>
														<option value=" 10:00:00">10:00:00</option>
														<option value=" 11:00:00">11:00:00</option>
														<option value=" 12:00:00">12:00:00</option>
														<option value=" 13:00:00">13:00:00</option>
														<option value=" 14:00:00">14:00:00</option>
														<option value=" 15:00:00">15:00:00</option>
														<option value=" 16:00:00">16:00:00</option>
														<option value=" 17:00:00">17:00:00</option>
														<option value=" 18:00:00">18:00:00</option>
														<option value=" 19:00:00">19:00:00</option>
														<option value=" 20:00:00">20:00:00</option>
														<option value=" 21:00:00">21:00:00</option>
														<option value=" 22:00:00">22:00:00</option>
														<option value=" 23:00:00">23:00:00</option>
														</select>
												</td>
											</tr>
											<tr>
												<td class="lists_fright">
													处理时间：
												</td>
												<td>
													<input type="text" name="queryTopicCondition.modifiedStart" readonly id="modifiedStart"
														value="${queryTopicCondition.modifiedStart != -1 ? queryTopicCondition.modifiedStart : ''}" width="10" onclick="WdatePicker()"/>
														<select name="modifyStartHH" id="modifyStartHH">
														<option value=" 00:00:00" id="op0">00:00:00</option>
														<option value=" 01:00:00">01:00:00</option>
														<option value=" 02:00:00">02:00:00</option>
														<option value=" 03:00:00">03:00:00</option>
														<option value=" 04:00:00">04:00:00</option>
														<option value=" 05:00:00">05:00:00</option>
														<option value=" 06:00:00">06:00:00</option>
														<option value=" 07:00:00">07:00:00</option>
														<option value=" 08:00:00">08:00:00</option>
														<option value=" 09:00:00">09:00:00</option>
														<option value=" 10:00:00">10:00:00</option>
														<option value=" 11:00:00">11:00:00</option>
														<option value=" 12:00:00">12:00:00</option>
														<option value=" 13:00:00">13:00:00</option>
														<option value=" 14:00:00">14:00:00</option>
														<option value=" 15:00:00">15:00:00</option>
														<option value=" 16:00:00">16:00:00</option>
														<option value=" 17:00:00">17:00:00</option>
														<option value=" 18:00:00">18:00:00</option>
														<option value=" 19:00:00">19:00:00</option>
														<option value=" 20:00:00">20:00:00</option>
														<option value=" 21:00:00">21:00:00</option>
														<option value=" 22:00:00">22:00:00</option>
														<option value=" 23:00:00">23:00:00</option>
														<option value=" 23:59:59">23:59:59</option>
														</select> --
														<input type="text" name="queryTopicCondition.modifiedEnd" readonly id="modifiedEnd"
														onclick="WdatePicker()" 
														value="${queryTopicCondition.modifiedEnd != -1 ? queryTopicCondition.modifiedEnd : ''}" width="10"/>
														<select name="modifyEndHH" id="modifyEndHH">
														<option value=" 23:59:59" id="op0">23:59:59</option>
														<option value=" 00:00:00">00:00:00</option>
														<option value=" 01:00:00">01:00:00</option>
														<option value=" 02:00:00">02:00:00</option>
														<option value=" 03:00:00">03:00:00</option>
														<option value=" 04:00:00">04:00:00</option>
														<option value=" 05:00:00">05:00:00</option>
														<option value=" 06:00:00">06:00:00</option>
														<option value=" 07:00:00">07:00:00</option>
														<option value=" 08:00:00">08:00:00</option>
														<option value=" 09:00:00">09:00:00</option>
														<option value=" 10:00:00">10:00:00</option>
														<option value=" 11:00:00">11:00:00</option>
														<option value=" 12:00:00">12:00:00</option>
														<option value=" 13:00:00">13:00:00</option>
														<option value=" 14:00:00">14:00:00</option>
														<option value=" 15:00:00">15:00:00</option>
														<option value=" 16:00:00">16:00:00</option>
														<option value=" 17:00:00">17:00:00</option>
														<option value=" 18:00:00">18:00:00</option>
														<option value=" 19:00:00">19:00:00</option>
														<option value=" 20:00:00">20:00:00</option>
														<option value=" 21:00:00">21:00:00</option>
														<option value=" 22:00:00">22:00:00</option>
														<option value=" 23:00:00">23:00:00</option>
														</select>
												</td>
											</tr>
											<tr>
												<td class="lists_fright">&nbsp;</td>
												<td>
													<s:submit value="搜 索" />
												</td>
											</tr>
										</table>
						</form>
						</div>
					</td>
					<td class="lists_tright lists_bor2"></td>
				</tr>
				<tr>		
					<td class="lists_bor"></td>
					<td>
					<form name="cusForm" method="post">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
									<tr class="lists_infobg">
									<td width="7%"><input type="checkbox" onclick="allCheck()" value="-1"/>全选</td>
										<td>ID</td>
										<td>小组名称</td>
										<td>话题内容</td>
										<td>昵称</td>
										<td>Email</td>
										<td>信息类型</td>
										<td>回复</td>
										<td>推荐</td>
										<td>状态</td>
										<td>创建时间</td>
										<td>操作</td>
									</tr>
									<s:iterator value="page.pageResult" id="topic" status="status">
										<tr id="topicTrItem_<s:property value="#status.count"/>">
										<td><input type="checkbox" id="checkboxIds" name="checkboxIds" value=<s:property value="id"/> /></td>
											<td>
												<s:property value="id" />
											</td>
											<td>
												<s:property value="disName" />
											</td>
											<td width="450px;">
												<div style="width:450px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap" title="<s:property value="content" />">
													<a href="<%=contextPath%>/dis/back!showTopic.action?id=${id}"><s:property value="content" /></a>
												</div>
											</td>
											<td>
												<s:property value="cusName"/>
											</td>
											<td>
											    <div style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap">
													<a href="<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=<s:property value="cusId" />">
													<s:property value="email" /></a>
												</div>
											</td>
											<td>
												<s:iterator value="typeTopicList" var="item">
													<s:if test="type == #item.value">
														<s:property value="#item.text"/>
													</s:if>
												</s:iterator>
											</td>
											<td>
												<s:property value="replyCounts" />
											</td>
											<td>
												<s:property value="recCount" />
											</td>
											<td>
												<s:iterator value="statusTopicList" var="item">
													<s:if test="status == #item.value">
														<s:if test="status == 3 || status == 1">
															<font color="red"><s:property value="#item.text"/></font>
														</s:if>
														<s:else>
															<s:property value="#item.text"/>
														</s:else>
													</s:if>
												</s:iterator>
											</td>
											<td>
												<s:date name="createTime" format="yyyy:MM:dd HH:mm:ss" />
											</td>
											<td>
												<a href="back!editTopic.action?id=<s:property value="id"/>">修改</a>|
												<a href="javascript:delTopicActionFirst('<%=contextPath%>/dis/back!delTopic.action','<s:property value="id"/>','topicTrItem_<s:property value="#status.count"/>','<s:property value="cusId"/>');">删除</a>
											</td>
										</tr>
									</s:iterator>
									<tr>
									    <td colspan="13">
									    	<div style="float: left;">
									    		<form id="frmBatch" name="frmBatch" action="dis/back!batchUpdateStatus.action" method="post">
													<input type="hidden" id="ids" name="ids" value="" />
													批量操作：
													<select id="status" name="topic.status">
														<option value="-99">-状态-</option>
														<s:iterator value="statusTopicList" var="item">
															<s:if test="topic.status == #item.value">
																<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
															</s:if>
															<s:else>
																<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
															</s:else>
														</s:iterator>
													</select>
													<input type="button" value="确认" onclick="allCheckV();" />
												</form>
											</div>
									    </td>
									</tr>
								</table>
							</form>
					</td>
					<td class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom">
						<!-- 分页页码 -->
						<s:if test="page.totalPage > 1">
							<jsp:include page="/back/jsp/common/showPage.jsp" />
						</s:if>
					</td>
					<td class="td_wid_r">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>
		</div>
		<!-- 删除帖子确认提示 -->
		<div class="popup wrong" id="popupContactDeleteInfo" style="display:none;position:absolute;left:37%;top:15%;z-index:5;">
			<ul class="title">
				<li class="ml"></li>
				<li class="mc">提示</li>
				<li class="mr"></li>
			</ul>
			<div class="con">
				<div class="ml"><img src="<%=importURL%>/images/usercenter/popimg3.gif"  /></div>
				<div class="mr success"><b id="popupContactDeleteInfoText"></b><br />
					<p class="popbut" style="width: 200px;">
						<a href="javascript:void(0);" onclick="closeWin('popupContactDeleteInfo');delTopicAction();">确定</a>
						<span class="popbutmr"></span>
						<a href="JavaScript:closeWin('popupContactDeleteInfo');">取消</a>
						<span class="popbutmr"></span>
					</p>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</body>
</html>

<script language="javascript">
	function allCheck(){
		var chkbox = document.getElementsByName("checkboxIds");
	    var i=0;
	    while( chkbox.length>i ) {
	        chkbox[i].checked = !chkbox[i].checked;
	        i++;
	    }
	}
	function allCheckFalse(){
		var chkbox = document.getElementsByName("checkboxIds");
	    var i=0;
	    while( chkbox.length>i ) {
	        chkbox[i].checked = false;
	        i++;
	    }
	}
	allCheckFalse();
	function allCheckV(){
		var chkbox = document.getElementsByName("checkboxIds");
		
		var len = chkbox.length;
	    var i=0;
	    var count = 0;
	    var status;
	    var idsStr = "";
	    
	    while( len > i ) {
	        if(chkbox[i].checked){
	        	count++;
	        	idsStr += chkbox[i].value + ",";
	        }
	        i++;
	    }
	    
	    status = $("#status").val();
	    if(status == -99){
	    	alert("请选择状态!");
	    	return;
	    }
	    if(count > 0){
	    	//将最后一个“,”去掉
	    	idsStr = idsStr.substring(0,idsStr.length - 1);
	    	$("#ids").val(idsStr);
	    	if (window.confirm('确定要修改吗？') == true) {
	    		batchUpdateStatusAction("<%=contextPath%>/dis/back!batchUpdateStatus.action",status,idsStr);
			}
	    }else{
	    	alert("请至少选择一项");
	    }
	}
	function batchUpdateStatusAction(urlAction,status,ids){
				var url = urlAction;  
				var params = {
					status:status,
					ids:ids
				}
				//使用$.post方式
				$.post(
					url,
					params,
					function batchUpdate(data){
						//条件成立，执行成功
						if(data == 1){
							document.location.reload();
						}else{
							showErrorWin('修改失败!');
						}
					},
					'json'
				);
			}
</script>