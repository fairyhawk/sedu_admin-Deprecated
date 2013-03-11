<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!-- 新建任务 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改任务</title>
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
							<font class="lists_fleft">修改任务</font>
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
									<td>
										专业ID
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										模板ID
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										邮件名称
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										邮件内容
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
									内容存储格式
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										发送模式
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										计划模式
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										发送时间
									</td>
									<td class="lists_tleft">
										<input type="text"/>
									</td>
								</tr>
								<tr>
									<td>
										定时发送时间
									</td>
									<td class="lists_tleft">
										<input type="text"/>
								</tr>
								<tr>
									<td>
										发送对象
									</td>
									<td class="lists_tleft">
										<input type="text"/>
								</tr>
								<tr>
									<td>
										视频ID
									</td>
									<td class="lists_tleft">
										<input type="text"/>
								</tr>
								<tr>
									<td>
										状态
									</td>
									<td class="lists_tleft">
										<input type="text"/>
								</tr>
								<tr>
									<td>
										创建时间
									</td>
									<td class="lists_tleft">
										<input type="text"/>
								</tr>
								<tr>
									<td>
										修改时间
									</td>
									<td class="lists_tleft">
										<input type="text"/>
								</tr>
								<tr>
									<td colspan="2">
										<input type="button" onclick="addSubmitVerify();" value="提交" />
										<input type="reset" value="重置"/>
									</td>
								</tr>
							</table>
						</td>
						<td width="16" class="lists_tright lists_bor2">
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
		</div>
	</body>
</html>

