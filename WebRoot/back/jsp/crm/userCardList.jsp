<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>用户档案库</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	<!--
			//时间比较；
		function startVSendTime()
		{
			var s_regTime=document.getElementById("s_regTime").value;
			var e_regTime=document.getElementById("e_regTime").value;

			if(s_regTime > e_regTime)
			{
				alert("创建开始时间不能大于创建结束时间,请从新选择！");
				var e_regTime=document.getElementById("e_regTime");
				e_regTime.value="";
				e_regTime.focus();
			}
		
		}
		
		//  进行校验；
		function validate()
		{
			var mobile = document.getElementById("s_mobile").value;
			var userType = document.getElementById("s_userType").value;
			
			var s_regTime = document.getElementById("s_regTime").value;
			var e_regTime = document.getElementById("e_regTime").value;
		
			if(mobile == "" && userType == -1 && s_regTime == "" && e_regTime==""){
			
				alert("请至少填写一项搜索条件");
				return false;
			}else if(s_regTime!="" && e_regTime==""){
				alert("请输入创建结束时间");
				var e_regTime=document.getElementById("e_regTime");
				e_regTime.value="";
				e_regTime.focus();
				return false;
			}else{
				return  true;
			}
		}
		
	//-->
	</script>
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td   class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">用户档案库</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		
		<tr>
			<td   class="lists_bor"></td>
			<td>  
			<form action="<%=contextPath%>/crm/crmChance!seachUserCardList.action?queryUsersCondition.currentPage=1"
							name="keySearchForm" method="post">
				<div class="msg-yw">
				<table border="0" width="100%" align="center">
					<tr>
						<td align="right">手机号码：</td>
						<td align="left"><input type="text" value=""
								name="queryUsersCondition.mobile" id="s_mobile" /></td>
						<td align="right">用户来源：</td>
						<td align="left"><select name="queryUsersCondition.userType"
								id="s_userType">
								<option selected="selected" value="-1">
									-请选择-
								</option>
								<option value="1">
									自然注册
								</option>
								<option value="2">
									乐语在线
								</option>
								<option value="3">
									乐语转注册
								</option>
								<option value="4">
									自然留言
								</option>
								<option value="5">
									留言转注册
								</option>
								<option value="6">
									CallIn
								</option>
								<option value="7">
									CallIn转注册
								</option>
							</select></td>
						<td align="left">
							创建时间：
							<input type="text" readonly
								name="queryUsersCondition.staregTime" id="s_regTime"
								onFocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" />
							--
							<input type="text" readonly
								name="queryUsersCondition.endregTime" id="e_regTime"
								onFocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="startVSendTime()"/>
							
							<input type="submit" value=" 查询 " />
							&nbsp;&nbsp;
							<input type="reset" value=" 重置 " />
						</td>
					</tr>
	
				</table>
				</div>
			</form>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr >
					<td  class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">用户列表</font>
					</td>
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td class="lists_bor">
					</td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr class="lists_infobg">
								<td width="12%">
									用户ID
								</td>
								<td width="20%">
									手机号码
								</td>
								<td width="15%">
									创建来源
								</td>
								<td width="20%">
									创建时间
								</td>
								<td>
									操作
								</td>
							</tr>
		
							<s:if test="page.pageResult != null">
								<s:iterator value="page.pageResult" id="userList">
									<tr>
										<td>
											<s:property value="#userList.id" />
										</td>
										<td>
											<s:property value="#userList.mobile" />
										</td>
										<td>
											<s:if test="#userList.userType == 1">
																	自然注册
																</s:if>
											<s:elseif test="#userList.userType == 2">
																	乐语在线
																</s:elseif>
											<s:elseif test="#userList.userType == 3">
																	乐语转注册
																</s:elseif>
										   <s:elseif test="#userList.userType == 4">
																	自然留言
																</s:elseif>
										   <s:elseif test="#userList.userType == 5">
																	留言转注册
										  </s:elseif>
										  <s:elseif test="#userList.userType == 6">
											CallIn
										</s:elseif>
										   <s:elseif test="#userList.userType == 7">
											CallIn转注册
											</s:elseif>
										</td>
										<td>
											<s:date name="#userList.regTime"
												format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>      
											<a href="<%=contextPath%>/crm/crmChance!getUserCard.action?userId=<s:property value="#userList.id"/>">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="<%=contextPath%>/crm/crmChance!getUserCardById.action?userId=<s:property value="#userList.id"/>">查看</a>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</table>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
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
					
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
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
</div>
	</body>
</html>