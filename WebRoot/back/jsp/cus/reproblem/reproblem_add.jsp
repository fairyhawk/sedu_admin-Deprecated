<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
		<script type="text/javascript">
	
		</script>
	</head>
	<body>
<div>
	<s:form action="repbl!addReProblem" method="post" name="addReProblem" id="addReProblem" >
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">信息回复</font>
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
				<tr>
					<td align="left">
						<s:property value="customer.cusName"/>　问：
					</td>
					<td>
						<s:property value="problem.pblTitle"/>
					</td>
					<td>
						<s:date name="problem.createTime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<s:iterator value="reProblemList" id="reProblem">
				 <s:if test="#reProblem.reManType==1">
				<tr>
					<td align="left">
					<s:iterator value="userList" id="user">
						<s:if test="#user.userId==#reProblem.reManId">
						<s:property value="#user.userName"/>　回复：
						</s:if>
					</s:iterator>
					</td>
					<td>
						<s:property value="#reProblem.reInfo"/>
					</td>
					<td>
						<s:date name="#reProblem.reTime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					
				</tr>
				</s:if>
				 <s:if test="#reProblem.reManType==0">
				<tr>
					<td align="left">
					<s:iterator value="customerList" id="customer">
						<s:if test="#customer.cusId==#reProblem.reManId">
						<s:property value="#customer.cusName"/>　回复：
						</s:if>
					</s:iterator>
					</td>
					<td>
						<s:property value="#reProblem.reInfo"/>
					</td>
					<td>
						<s:date name="#reProblem.reTime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				</s:if>
				
				</s:iterator>
				<tr>
					<td align="left">
						<s:property value="user.userName"/>　回复：
					</td>
					<td class="lists_tleft" colspan="2">
						<textarea name="reproblem.reInfo" id="reproblem.reInfo" rows="4" cols="40"></textarea>
					</td>
				</tr>
				
				<tr><td colspan="3">
					<input type="hidden" name="reproblem.pblId" value="<s:property value="problem.pblId"/>">
					<input type="submit" name="su" value="提交"/>
					</td></tr>
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
	</s:form>
</div>
	</body>
</html>
