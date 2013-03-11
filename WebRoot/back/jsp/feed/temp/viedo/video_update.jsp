<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!-- 新建任务 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改视频</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		
	</head>
	<body>
		<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
					<tr class="lists_top">
						<td width="12">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td>
							<font class="lists_fleft">修改视频</font>
							<font class="lists_fright"> </font>
						</td>
						<td width="16">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
								<tr>
									<td width="10%">
										ID
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										专业名称
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										课程名称
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										视频名称
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										视频地址
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										讲义地址
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										老师
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										课程讲义
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										知识点
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										创建时间
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
								<tr>
									<td>
										修改时间
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
									</td>
								</tr>
							</table>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>
					<tr >
				<td colspan="3"><input type="submit" value="保存"/> | <input type="button" value="关闭"  onclick="clocs();"/></td>
			</tr>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
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
		</div>
	</body>
</html>


