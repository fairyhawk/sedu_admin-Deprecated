<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>公告管理</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />

	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
			<tr>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top">
					<font class="lists_fleft"> 查询条件 </font>

				</td>
				<td class="td_wid_r">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
			</tr>
			<tr>
				<td class="lists_bor"></td>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr>
											<td  width="10%" class="lists_tright">
												时间:
											</td>
											<td  class="lists_tleft">
													<input type="text" name="" id="startTime" value="选择年月日" />
													<input type="text" name="" id="startTime" value="精确到秒" />
													到
													<input type="text" name="" id="startTime" value="选择年月日" />
													<input type="text" name="" id="startTime" value="精确到秒" />
													
													</td>
											</tr>
												<tr>
													<td width="10%" class="lists_tright">
													类别：
													</td>
													<td class="lists_tleft">
													<select name="">
														<option value="">全部</option>
														<s:iterator value="">
														</s:iterator>
													</select>
													<button name="" value="">查询</button>
												</td>
											</tr>
					</table>
					<font class="lists_fright"> <a href="">新建公告</a> </font>
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="lists_bor"></td>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="lists_info">
						<tr class="lists_infobg">
							<td>
								公告ID
							</td>
							<td>
								公告标题
							</td>
							<td>
								公告类别
							</td>
							<td>
								公开
							</td>
							<td>
								操作
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