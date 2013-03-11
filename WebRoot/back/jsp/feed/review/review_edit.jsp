<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>留言修改</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script type="text/javascript">
			function actionSubmit(){
				if($("#status").val() == -1){
					//屏蔽原因不允许为零
					if($.trim($("#detail").val()).length > 0){
						if(confirm("如果屏蔽导致前端无法看到本帖内容")){
							document.getElementById("frmReviewEdit").submit();
						}
					}else{
						alert("请填写屏蔽该项的原因！");
					}
				}else{
					document.getElementById("frmReviewEdit").submit();
				}
			}
		</script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					屏蔽该项内容
				</font>
				<font class="lists_right">
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<form id="frmReviewEdit" name="frmReviewEdit" action="<%=contextPath%>/feed/back!doEditReview.action" method="post">
					<input type="hidden" name="review.id" value="<s:property value="review.id"/>" />
					<input type="hidden" name="queryReviewCondition.currentPage" value="${param.currentPage}" />
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
								<tr>
									<td width="10%">
										回复数:
									</td>
									<td class="lists_tleft">
										<input  type="text" name="" value="<s:property value="review.conts"/>" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td width="10%">
										支持数:
									</td>
									<td class="lists_tleft">
										<input  type="text" name="" value="<s:property value="review.supportNumber"/>" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td width="10%">
										反对数:
									</td>
									<td class="lists_tleft">
										<input  type="text" name="" value="<s:property value="review.antilogNumber"/>" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td>
										状  态：
									</td>
									<td>
									    <div  style="float: left;">
											<select style="width: 80px;" name="review.status" id="status">
												<s:if test="review.status == 1">
													<option value="1" selected="selected">正常</option>												
													<option value="2">选中</option>
													<option value="-1">屏蔽</option>
												</s:if>
												<s:if test="review.status == 2">
													<option value="1">正常</option>
													<option value="2" selected="selected">选中</option>
													<option value="-1">屏蔽</option>
												</s:if>
												<s:if test="review.status == -1">
													<option value="1">正常</option>
													<option value="2">选中</option>
													<option value="-1" selected="selected">屏蔽</option>
												</s:if>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										内  容：
									</td>
									<td>
									  	<div  style="float: left;">
											<textarea rows="5" cols="60" readonly="readonly"><s:property value="review.content"/></textarea>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										屏蔽原因：
									</td>
									<td>
									 	<div  style="float: left;">
											<textarea rows="5" cols="60" id="detail" name="review.detail"><s:property value="review.detail"/></textarea>
										</div>
									</td>
								</tr>
								<tr align="center">
									<td colspan="2">
										<input type="button" onclick="actionSubmit();" value="提  交" />&nbsp;&nbsp;
										<input type="button" onclick="javascript:history.go(-1);" value="返  回" />
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
			<td class="lists_bottom"></td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>