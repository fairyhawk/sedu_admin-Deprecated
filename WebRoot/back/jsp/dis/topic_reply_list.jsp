<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>话题回复管理</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_public.css" />
		<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_popup.css" />
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
		
			var actionUrl;//请求地址
			var actionId;//帖子id
			var topicTrItemId;
			var tagId;
			
			function delTopicReplyActionFirst(url,tag_Id){
	
				actionUrl = url;
				tagId = tag_Id;
				
				
				$("#popupContactDeleteInfoText").html("确认删除!");
				showWin("popupContactDeleteInfo");
			}
			function delTopicReplyAction(){
				var url = actionUrl;  
				var params = {
				}
				//使用$.post方式
				$.post(
					url,
					params,
					function topic(data){
						//条件成立，执行成功
						if(data == 1){
							$("#" + tagId).remove();
						}else{
							showErrorWin('删除失败!');
						}
					},
					'json'
				);
			}
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
									<font class="lists_fleft">回复管理-${topic.title}</font>
									<font class="lists_fright"></font>	
							</td>
							<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
						</tr>
						<tr>		
							<td class="lists_bor"></td>
							<td>
								<table width=900px border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr>
									<td width="5%">
										<b>话题</b>
									</td>
									<td width="95%" height="100%">
										<table height="100%" width="100%" border="0">
											<tr>
												<td colspan="2" align="center" style="text-align:center;padding-top: 10px">
													<b><font size="3">${topic.title}</font> </b>
												</td>
											</tr>
											<tr>
												<td  style="text-align:left;width: 25%">
													发贴人:
													<a href="<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=<s:property value='topic.customer.cusId'/>">
														<s:property value="topic.customer.email"/>
													</a>
												</td>
												<td style="text-align:left;width: 75%">
													时间:
													<s:date name="topic.replyTime" format="yyyy-MM-dd HH:mm:ss" />
												</td>
											</tr>
											<tr>
												<td style="text-align:left;" >
													<img src="<%=importURL%><s:property value="topic.customer.photo==null || topic.customer.photo==''?'/images/usercenter/leftnav_2.gif':'/upload/customer/photo/'+topic.customer.photo"/>" height="60" width="60" />
												</td>
												<td style="text-align:left;">
													${topic.content }
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<s:iterator value="page.pageResult" id="result" status="status">
									<tr id="topicReply_<s:property value="#status.count"/>">
										<td>
											<b>回复 <s:property
													value="(queryTopicReplyCondition.currentPage-1)*20+#status.count" />
											</b>
										</td>
										<td>
											<table height="100%" width="100%" border="0">
												<tr>
													<td width="25%" align="left" style="text-align:left;">
														发贴人: 
																<a href="<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=${cusId}">
																	${email}
																</a>
													</td>
													<td align="left" style="text-align:left;width: 75%">
														时间:
														<s:if test="replyTime!=null">
															<s:if test="replyTime.length()>19">
																<s:property value="replyTime.substring(0,19)" />
															</s:if>
														</s:if>
													</td>
												</tr>
												<tr>
													<td style="text-align:left;">
														<img src="<%=importURL%><s:property value="photo==null || photo==''?'/images/usercenter/leftnav_2.gif':'/upload/customer/photo/'+photo"/>" height="60" width="60" />
													</td>
													<td align="left" style="text-align:left">
														<s:if test="targetCustomer != null">
										           			回复<a href="<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=${targetCustomer.cusId}">@
										           					<s:property value="targetCustomer.email == null || targetCustomer.email == '' ? targetCustomer.cusName : targetCustomer.email" />
										           			</a>:
										           		</s:if>
										           		<s:property value="replyContent"/>
													</td>
												</tr>
												<div style="float: left; margin-top: 3px; position: absolute; margin-left: 800px;">
													<a href="javascript:delTopicReplyActionFirst('<%=contextPath%>/dis/back!delTopicReplyById.action?replyId=${replyId}&topic.id=${topic.id}&replyCusId=${cusId}&queryTopicReplyCondition.currentPage=1','topicReply_<s:property value="#status.count"/>');">删除</a>
												</div>
											</table>
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
						<td class="lists_bottom"></td>
						<td class="td_wid_r">
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
			</table>
		</div>
	</body>
</html>

<!-- 删除帖子回复确认提示 -->
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
				<a href="javascript:void(0);" onclick="closeWin('popupContactDeleteInfo');delTopicReplyAction();">确定</a>
				<span class="popbutmr"></span>
				<a href="JavaScript:closeWin('popupContactDeleteInfo');">取消</a>
				<span class="popbutmr"></span>
			</p>
		</div>
		<div class="clear"></div>
	</div>
</div>

