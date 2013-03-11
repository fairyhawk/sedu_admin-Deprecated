<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>视频管理>>新建视频</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_left">
				添加视频
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
								<tr>
									<td width="10%">
										专业ID
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										课程ID：
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										视频名称：
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										视频URL地址：
									</td>
									<td class="lists_tleft">
										<select name="" id="">
											<option value="0">选择阶段</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										讲义地址：
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										老师：
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										课程讲义：
									</td>
									<td class="lists_tleft">
										<input type="file" value="上传"/>
										<button>上传</button>
									</td>
								</tr>
								<tr>
									<td>
										知识点：
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										创建时间：
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										修改时间：
									</td>
									<td class="lists_tleft">
										<input type="text"/>
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
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
		
</body>
</html>