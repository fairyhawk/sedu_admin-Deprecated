<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>计划使用统计</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" href="<%=contextPath%>/styles/usercenter/uc_public.css" type="text/css"></link>
				<link rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css" type="text/css"></link>
		
	</head>
	<body>
		
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">学习计划使用情况统计</font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				
				<tr>
					<td width="12" class="lists_bor"></td>
					<td align="center" style="border:1;"  frame="border">
										  
						  <br>
						  <table width="50%" height="280" border="1" cellpadding="0" cellspacing="1" class="tabletj">
						 	  <tr>
							    <td colspan="2" align="center" valign="middle"><strong>未使用人数</strong></td>
							    <td colspan="2" align="center" valign="middle"><strong>已使用人数</strong></td>
						      </tr>
							  <tr>
							    <td width="133" rowspan="2" align="center" valign="middle" style="padding-top: 40px"><strong>未使用人数</strong></td>
							    <td width="116" rowspan="2" align="center" valign="middle" style="padding-top: 40px"><s:property value="customerNoUserNum"/></td>
							    <td width="98" align="center" valign="middle" style="padding-top: 10px;padding-left: 20px"><strong>1--10次</strong></td>
							    <td width="125" style="padding-top: 10px;padding-left: 20px"><s:property value="customerUserLessTenNum"/></td>
						      </tr>
							  <tr>
							    <td align="center" valign="middle" style="padding-top: 10px;"><strong>10次以上</strong></td>
							    <td align="center" valign="middle" style="padding-top: 10px;padding-left: 10px"><s:property value="customerUserMoreTenNum"/></td>
						      </tr>
							  <tr>
							    <td colspan="2" align="center" valign="middle" style="padding-top: 10px;"><strong>未使用人数总数:</strong>&nbsp;<s:property value="customerNoUserNum"/></td>
							    <td colspan="2" align="center" valign="middle" style="padding-top: 10px"><strong>已使用人数总数:</strong>&nbsp;<s:property value="customerUserNum"/></td>
						      </tr>
                              <tr>
							    <td colspan="4" align="center" valign="middle" style="padding-top: 10px">
							    	<strong>学员总人数:</strong>&nbsp;<s:property value="allCostomerNum"/>
							    </td>
						      </tr>
							</table>
					</td>
				
					<td  height="25" width="12" class="lists_tright lists_bor2"></td>
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
		</div>
	</body>
</html>
