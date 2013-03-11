<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>登录日志列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript">
			function search(){
				var $searchKey = $("#searchKey").val();
				var searchKey = $.trim($searchKey) 
				if(searchKey==""){
					alert("请输入用户名/姓名！");
				}else{
					searchKey = encodeURIComponent(encodeURIComponent(searchKey));
					window.location="<%=contextPath%>/sys/loginLog!search.action?queryLoginLogCondition.currentPage=1&userName="+searchKey;
				}
			}
       </script>
	</head>
	<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">登录日志列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
							按用户名/姓名检索：
							<input type="text" name="searchKey" id="searchKey" value="${userName}"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0">
					<tr>
						<td>
						<img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:search()">查询</a>
						</td>
					</tr>
					</table>
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td>
							序号
						</td>
						<td>
							用户名
						</td>
						<td>
							真实姓名
						</td>
						<td>
							登录IP
						</td>
						<td>
							登录时间
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="loginLog" status="status">
						<tr>
							<td>
								<s:property value="#status.index+(page.currentPage-1)*page.pageSize+1" />
							</td>
							<td>
								<s:property value="#loginLog.loginName" />
							</td>
							<td>
								<s:property value="#loginLog.userName" />
							</td>
							<td>
								<s:property value="#loginLog.ip" />
							</td>
							<td>
								<s:date name="#loginLog.loginTime" format="yyyy-MM-dd HH:mm:ss"/>
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
</div>
	</body>
</html>
