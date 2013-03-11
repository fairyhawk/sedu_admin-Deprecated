<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!-- 新建任务 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建任务</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_left">
					新建任务
				</td>
			</tr>
		</table>
							<table width="100%" border="1" bordercolor="#00ccff" style="border-collapse: collapse;font-size: 12px;">
								<tr>
									<td width="8%">
										专业ID
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										模板ID
									</td>
									<td class="lists_tleft">
									<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										邮件名称
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										邮件内容
									</td>
									<td class="lists_tleft">
									<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										内容存储格式
									</td>
									<td class="lists_tleft">
										<input type="text" />
									</td>
								</tr>
								<tr>
									<td>
										发送模式
									</td>
									<td class="lists_tleft">
											<input  type="text"/>								
									</td>
								</tr>
								<tr>
									<td>
										计划模式
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										发送时间
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										定时发送时间
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										发送对象
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										视频ID
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										状态
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										创建时间
									</td>
									<td class="lists_tleft">
										<input  type="text"/>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="button" onclick="addSubmitVerify();" value="提交" />
										<input type="reset" value="重置"/>
									</td>
								</tr>
							</table>
	</body>
</html>


