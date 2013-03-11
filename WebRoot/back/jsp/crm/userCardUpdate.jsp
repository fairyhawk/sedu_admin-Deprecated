<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>用户卡详细</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	<!--
		//关闭
		function clocs()
		{
			var aBlean=confirm("修改还未保存成功，你确认关闭该页面？");
			if(aBlean==true)
			{
				window.location="javascript:history.go(-1)";
			}else{
				return false;
			}
		}
		
	//-->
	</script>
	
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">用户档案卡</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		
		<!-- 创建信息 -->
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<table width="90%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">创建信息</font>
					</td>
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr class="lists_infobg">
								<td width="12%">
									用户ID
								</td>
								<td width="20%">
									移动电话
								</td>
								<td width="15%">
									创建时间
								</td>
								<td width="20%">
									创建来源
								</td>
								
							</tr>
							<tr>
								<td>
									<s:property value="user.id" />
								</td>
								<td>
									<s:property value="user.mobile" />
								</td>
								<td>
									<s:date name="user.regTime"
										format="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<s:if test="user.userType == 1">
															自然注册
														</s:if>
									<s:elseif test="user.userType == 2">
															乐语在线
														</s:elseif>
									<s:elseif test="user.userType == 3">
															乐语转注册
														</s:elseif>
									<s:if test="user.userType == 4">
															自然留言
														</s:if>
									<s:elseif test="user.userType == 5">
															留言转注册
														</s:elseif>
								</td>
							</tr>
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
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<!-- 基础信息 -->
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<form action="<%=contextPath%>/crm/crmChance!updateUserCard.action?userId=<s:property value='user.id'/>" method="post" name="userForm">
			<table width="90%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">基础信息</font>
					</td>
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr>
								<td width="12%" class="crm_tableFR">姓名：&nbsp;</td>
								<td width="20%" class="crm_tableFL">&nbsp;<input type="text" value="<s:property value="user.realName" />" name="queryUsersCondition.realName" id="realName"/></td>
							</tr>	
							<tr>	
								<td class="crm_tableFR">生日：&nbsp;</td>
								<td class="crm_tableFL">&nbsp;<input type="text" readonly  value="<s:property value="user.birthday" />"
									name="queryUsersCondition.birthday" id="s_birthday"
									onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" />
								</td>
							</tr>	
							<tr>
								<td class="crm_tableFR">性别：&nbsp;</td>
								<td class="crm_tableFL">&nbsp;<select name="queryUsersCondition.sex" id="sex">
										<option value="1" <s:if test="user.sex == 1">selected="selected"</s:if>>女</option>
										<option value="2" <s:if test="user.sex == 2">selected="selected"</s:if>>男</option>
									</select>														
								</td>
							</tr>	
							<tr>	
								<td class="crm_tableFR"">职位：&nbsp;</td>
								<td class="crm_tableFL">
									&nbsp;<input type="text" value="<s:property value="user.profession" />" name="queryUsersCondition.profession" id="profession"/>
								</td>
							</tr>	
							<tr>	
								<td class="crm_tableFR">地址：&nbsp;</td>
								<td class="crm_tableFL">
									&nbsp;<textarea rows="3" cols="50" name="queryUsersCondition.address" id="address"><s:property value="user.address" /></textarea>
								</td>
								
							</tr>
							<tr>	
								<td class="crm_tableFR">
									备注：&nbsp;
								</td>
								<td class="crm_tableFL">
									&nbsp;<textarea rows="5" cols="80" name="queryUsersCondition.remarks" id="remarks"><s:property value="user.remarks" /></textarea><br/><br/>
								</td>
							</tr>
		
						</table>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td align="center"><input type="submit" value="提交"/> | <input type="button" value="关闭"  onclick="clocs();"/></td>
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
</div>
	</body>
</html>