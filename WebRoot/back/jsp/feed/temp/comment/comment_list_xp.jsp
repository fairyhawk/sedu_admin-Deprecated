<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>问题管理>>问题详情</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
	</head>
<body>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					查询条件：
				</font>
				<font class="lists_right">
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
										<table width="100%" border="0" cellspacing="1" cellpadding="0"
											class="lists_info">
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
												<!--
												<td rowspan="2"><input type=button class="bt" value="查询" onclick=""></td>
												 -->
											</tr>
											<tr>
											<td width="10%" class="lists_tright">
													课程：
													</td>
											<td class="lists_tleft">
													<select name="">
														<option value="">全部</option>
														<s:iterator value="">
															
														</s:iterator>
													</select>
													<select name="">
														<option value="">全部</option>
														<s:iterator value="">
														</s:iterator>
													</select>
													<button name="" value="">查询</button>
												</td>
											</tr>
										</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
						<tr>
							<td  width="10%" class="lists_tright">
								ID:
							</td>
							<td	class="lists_tleft">
								<input  type="text" name="" value=""/>							
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								用户ID
							</td>
							<td 	class="lists_tleft">
							<input type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								提问内容
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								回复数
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								支持数
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								反对数
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
									附件
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								附件URL
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								是否选中
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								冻结状态
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
							<tr>
							<td width="10%" class="lists_tright">
								提问时间
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
						<tr>
							<td width="10%" class="lists_tright">
								视频ID
							</td>
							<td class="lists_tleft">
								<input  type="text" name="" value=""/>
							</td>
						</tr>
				</table>
				<center>
					<input  type="button" value="关闭"/>
				</center>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>
