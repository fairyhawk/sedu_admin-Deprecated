<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>短信管理</title>
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
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet"
			href="<%=contextPath%>/styles/usercenter/uc_public.css"
			type="text/css"></link>
		<link rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css"
			type="text/css"></link>
			<script type="">
				function displayAll(){
					window.location.href="<%=contextPath%>/cus/cus!serchDisplayInfoAll.action?queryNoteContentCondition.noteId=<s:property value="noteContent.noteId"/>";
					}
			</script>
	</head>
	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft"> 短信管理</font> <font class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
	</table>
		<div>
			<form action="<%=contextPath%>/finance/coupon!addCreateCouponXiuGai.action"
				method="post" name="planClockForm" id="planClockForm"
				onsubmit="return onSubmit();">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists" >
					<tr>
						<td width="12" class="lists_bor"></td>
						<td>短信管理&gt;短信记录</td>
						<td width="16" class="lists_tright lists_bor2"></td>
					</tr>
					<tr>
						<td width="12" class="lists_bor"></td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr height="30">
									<td width="20%">
										<div>
											<font size="2">短信内容：</font> &nbsp;&nbsp;&nbsp;
										</div>
										
									</td>
									<td class="lists_tleft" colspan="2">
										 <textarea rows="4" cols="70"><s:property value="noteContent.noteContent"/> </textarea>
									</td>
								</tr>
								<tr height="30">
									<td align="center">
										<div>
											发送时间:&nbsp;&nbsp;&nbsp;
										</div>
									</td>
									<td class="lists_tleft" colspan="2">
									<input  type="text"  value="<s:date format='yyyy-MM-dd HH:mm:ss' name='noteContent.noteAddTime'/>" readonly="readonly"/>
									</td>
								</tr>
								<tr height="30">
									<td>
										<div>
											发送人：&nbsp;&nbsp;&nbsp;
										</div>
									</td>

									<td class="lists_tleft" colspan="2">
										<input type="text" readonly="readonly" value="<s:property value="noteContent.noteDespatcher"/>"/>
									</td>
								</tr>
								<tr height="30" id="youhuijine">
									<td>
											接收人:&nbsp;&nbsp;&nbsp;
									</td>
									<td class="lists_tleft" colspan="2">
										<div style="border: 1px solid #ffffff; width: 585px;">
											<span>${cellPhoneSingleCout}人</span><br/>
											<span style="margin-top: 50px; padding-top: 50PX;"><b>发送成功（<s:property value="cellPhoneList.size()" />）</b></span><br/>
											<s:if test="cellPhoneList.size()<4 && cellPhoneList.size()>0">
											<span  id="singlecellPhonee">
												<s:iterator var="cellPhonee" value="cellPhoneList">
													<s:property value="#cellPhonee.cellEmail" />,
												</s:iterator>
													</span><br/>
											</s:if>
											<s:if test="cellPhoneList.size()==0">
												未发送任何人.
											</s:if>
											<s:if test="cellPhoneList.size()>=4">
												<span  id="singlecellPhonee">
												<s:iterator var="cellPhonee" value="cellPhoneList"  end="3" begin="0">
													<s:property value="#cellPhonee.cellEmail" />,
												</s:iterator>
													</span><br/>
											</s:if>
											<span id="allcellPhonee" style="display: none;">
												<s:iterator var="cellPhonee" value="cellPhoneList"  end="" begin="0">
													<s:property value="#cellPhonee.cellEmail" />,
												</s:iterator>
													<a href="#" style="color: blue;" onclick="checkSingle()">返回</a>
													</span><br/>
											
											<span style="margin-top: 50px; padding-top: 50PX;"><b>发送失败（<s:property value="cellPhoneListCrabs.size()" />）</b></span><br/>
											<s:if test="cellPhoneListCrabs.size()<4 && cellPhoneListCrabs.size()>0">
											<span  id="singlecellPhonee">
												<s:iterator var="cellPhoneeCrabs" value="cellPhoneListCrabs">
													<s:property value="#cellPhoneeCrabs.cellEmail" />,
												</s:iterator>
													</span><br/>
											</s:if>
											<s:if test="cellPhoneListCrabs.size()==0">
												没有发送是失败的人.
											</s:if>
											<s:if test="cellPhoneListCrabs.size()>=4">
												<span  id="singlecellPhoneeCrabs">
												<s:iterator var="cellPhonee" value="cellPhoneListCrabs"  end="4" begin="0">
													<s:property value="#cellPhonee.cellEmail" />,
												</s:iterator>
													</span><br/>
											</s:if>
											<span id="allcellPhoneeCrabs" style="display: none;">
												<s:iterator var="cellPhonee" value="cellPhoneListCrabs"  end="" begin="0">
													<s:property value="#cellPhonee.cellEmail" />,
												</s:iterator>
													<a href="#" style="color: blue;" onclick="crabsCheckSingle()">返回</a>111
													</span><br/>
										</div>
									</td>
								</tr>
								<tr>
									<td></td>
									<td><input type="button" value="查看全部接受人" onclick="displayAll()"></input>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" onclick="history.go(-1)"></input></td>
									<td></td>
								</tr>
				</table>
			</form>
		</div>
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
	</body>
</html>
