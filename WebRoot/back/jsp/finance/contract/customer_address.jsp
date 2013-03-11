<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>查看学员信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />

</head>
<body>
<body>
<div>
	<form action="<%=contextPath %>/cms/comment!replyAdvice.action" method="post" name="addForm" id="addForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">查看学员信息</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				
				<tr height="30">
					<td width="20%">
						收货人
					</td>
					<td class="lists_tleft">
						<s:property value="address.receiver"/>
					</td>
				</tr>			
				<tr height="30">
					<td>
						联系电话
					</td>
					<td class="lists_tleft">
						<s:property value="customer.mobile"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						地址
					</td>
					<td class="lists_tleft">
						<s:property value="addressArea"/>
					</td>
				</tr>			
				<tr height="30">
					<td>
						邮编
					</td>
					<td class="lists_tleft">
						<s:property value="address.postCode"/>
					</td>
				</tr>
				<tr height="30">
					<td width="20%">
						邮箱
					</td>
					<td class="lists_tleft">
						<s:property value="customer.email"/>
					</td>
				</tr>					
				<tr height="30">
					<td>送达时间</td>
					<td class="lists_tleft">				
							<s:if test="address.sendTime==1">
								时间不限
							</s:if>
							<s:elseif test="address.sendTime==2">
								周一至周五
							</s:elseif>
							<s:elseif test="address.sendTime==3">
								周六日及公共假期
							</s:elseif>
					</td>
				</tr>
				
			
				<tr height="30">
					<td colspan="2">
						<input type="button" value="返回" onclick="history.go(-1)"></input>
					</td>
				</tr height="30">
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
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>
