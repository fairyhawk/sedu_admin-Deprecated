<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>统计管理</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
	</head>
<body>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
					<font class="lists_fleft">统计管理</font>
					<font class="lists_fright">
						<a href="<%=contextPath%>/feed/studentstat!downData.action">导出数据</a>
					</font>
								
					
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<div id="fromDiv">
					<!-- 按照来源条件查询 -->
				   <form  action="<%=contextPath%>/feed/studentstat!searchStuStatByFromIds.action" method="post" onsubmit="return CheckFromIds();">
				   		<input name="fromIds" value="" type="hidden" id="formIds"/>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr>
								<td width="10%" class="lists_tright">
									查询条件：
								</td>
								<td class="lists_tleft">
										
								</td>
							</tr>
							<tr>
								<td width="10%" class="lists_tright">
									时间：
								</td>
								<td class="lists_tleft">
										<input type="text"/>
										<button>查询</button>
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
			    <!-- 显示所有信息 -->
				<div>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr class="lists_infobg">
						<td>
							来源
						</td>
						<td>
							用户数
						</td>
						<td>
							注册点击
						</td>
						<td>
							注册人
						</td>
						<td>
							登陆注册
						</td>
						<td>
							登陆数
						</td>
						<td>
							跳出数
						</td>
						<td>
							微学习到达次数
						</td>
						<td>
							初次
						</td>
						<td>
							重复
						</td>
						<td>
							活跃
						</td>
						<td>
							订单
						</td>
						<td>
							流水
						</td>
						<td>
							统计日期
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
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>
