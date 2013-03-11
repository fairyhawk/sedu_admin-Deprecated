<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>消息列表</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />

	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
</head>
<body>
<form action="<%=contextPath%>/msg/msg!listMsgsByCondition.action" method="post" name="msglist" id="msglist">
	<s:hidden name="queryMessageCondition.currentPage" value="1" />
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr >
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
					<font class="lists_fleft">消息列表</font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
	            <td class="lists_bor"></td>
	            <td>
	              <div class="msg-zy">
				<table class="" border="0" cellspacing="0"  cellpadding="0">
						<tr>
						<td>
							按消息名检索：
							<input type="text" name="searchKey" id="searchKey" value="${searchKey}" />
						</td>
						<td width="20px;"></td>
								<td>
									<img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:document.msglist.submit()">查询</a>
								</td>
								<td width="20px;"></td>
								<td>
									<img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="<%=contextPath%>/task/task!toAddTask.action">添加消息</a>
								</td>
						</tr>		
						</table>
					 </td>
            <td class="lists_tright lists_bor2"></td>
        </tr>
        
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
							<tr class="lists_infobg">
							
								<td>
									消息标题
								</td>
								<td>
									发件人
								</td>
								<td>
									发件人类型
								</td>
								<td>
									添加时间
								</td>
								<td>
									消息类型
								</td>
								<td>
									操作
								</td>
							</tr>
							<s:iterator value="page.pageResult" id="msg">
								<tr>
									<td>
										<s:property value="#msg.msgTitle" />
									</td>
									<td>
										<s:property value="#msg.senderName" />
									</td>
									<td>
									<s:if test="#msg.senderType==1">
									  网站用户
									</s:if>
									<s:if test="#msg.senderType==2">
									  网站管理者
									</s:if>
									</td>
									
									<td>
									<s:date name="#msg.createTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
		
									<td>
										<s:if test="#msg.msgType==1">
										  单发
										</s:if>
										<s:if test="#msg.msgType==2">
										  发送到所有用户
										</s:if>
										<s:if test="#msg.msgType==3">
										  发送到内部学员
										</s:if>
										<s:if test="#msg.msgType==4">
										  发送到网站用户
										</s:if>
										<s:if test="#msg.msgType==5">
										  定时信息
										</s:if>
									</td>
									<td>
										<a href="<%=contextPath%>/msg/msg!toUpdateMsg.action?msg.msgId=<s:property value='#msg.msgId'/>">修改</a>|
										<a href="<%=contextPath%>/msg/msg!deleteMsg.action?msg.msgId=<s:property value='#msg.msgId'/>" onclick="return confirm('是否删除？')" >删除</a>
										|<a href="<%=contextPath%>/msg/msg!viewMsg.action?msg.msgId=<s:property value='#msg.msgId'/>">查看</a>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
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
	</table>
</form>
</body>
</html>
