<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript">
	 
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
				<font class="lists_fleft">意见列表</font>
				<font class="lists_fright">
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
				<form action="<%=contextPath%>/cou/course!listCoursesBySortId.action" method="post" name="courselist" id="courselist">
				<s:hidden name="queryCourseCondition.currentPage" value="1" />
				<input type="hidden" name="course.sortId" value="<s:property value="course.sortId"/>"></input>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<!-- <td><input type="checkbox" name="c1" onclick="All(this);" /></td> -->
						<td>售卖方式编号</td>
						<td>售卖方式名</td>
						<td>售卖方式价格</td>
						<td>操作</td>
					</tr>
					<s:iterator value="sellWayList" id="sellWay">
					<tr>
						<td><s:property value="#sellWay.sellId" /></td>
						<td><s:property value="#sellWay.sellName" /></td>
						<td><s:property value="#sellWay.sellPrice" /></td>				
						<td>
							<a href="<%=contextPath%>/cou/course!freeAdmin.action?sellWay.sellId=<s:property value="sellId"/>&cusId=<s:property value="cusId"/>">
								赠送
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
				</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
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
