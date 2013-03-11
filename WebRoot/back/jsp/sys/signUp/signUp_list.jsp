<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>最新注会报考条件查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function yan(){
		if((new Date($("#createBeginDate").val().replace(/-/g,"/")))>(new Date($("#createEndTime").val().replace(/-/g,"/"))))
		{
			alert("创建开始时间不能大于结束时间！");
			return false;
		}
	}
	function exportcsv(){
		document.getElementById("searchForm").action="<%=contextPath%>/sys/signUp!exportCSV.action";
		document.getElementById("searchForm").submit();
		document.getElementById("searchForm").action="<%=contextPath%>/sys/signUp!showSignUpList.action?querySignUpCondition.currentPage=1";
	}
</script>
</head>
<body>
	<!-- 查询块开始 -->
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
			<tr>
				<td class="td_wid_l"><img
					src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top"><font class="lists_fleft">
						最新注会报考条件查询</font> <font class="lists_fright"> </font>
				</td>
				<td class="td_wid_r"><img
					src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
			</tr>
			<tr>
				<td class="lists_bor"></td>
				<td>
					<div class="msg-zy">
						<form name="searchForm" id="searchForm"
							action="<%=contextPath%>/sys/signUp!showSignUpList.action?querySignUpCondition.currentPage=1"
							method="post">
							<table class="" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>学历:</td>
									<td >
										<select
										name="querySignUpCondition.education" id="education">
											<option value="">--请选择--</option>
											<option value="1" <s:if test="querySignUpCondition.education==1">selected</s:if>>博士</option>
											<option value="2" <s:if test="querySignUpCondition.education==2">selected</s:if>>硕士</option>
											<option value="3" <s:if test="querySignUpCondition.education==3">selected</s:if>>本科</option>
											<option value="4" <s:if test="querySignUpCondition.education==4">selected</s:if>>专科</option>
											<option value="5" <s:if test="querySignUpCondition.education==5">selected</s:if>>其它</option>
										</select>
									</td>
									<td>年龄:</td>
									<td  class="lists_tleft">
										<select
										name="querySignUpCondition.age" id="age">
											<option value="">--请选择--</option>
											<option value="1" <s:if test="querySignUpCondition.age==1">selected</s:if>>18-25岁</option>
											<option value="2" <s:if test="querySignUpCondition.age==2">selected</s:if>>25-35岁</option>
											<option value="3" <s:if test="querySignUpCondition.age==3">selected</s:if>>35-45岁</option>
											<option value="4" <s:if test="querySignUpCondition.age==4">selected</s:if>>45岁以上</option>
									</select></td>
								</tr>
								<tr>
									<td width="60px">项目专业:</td>
									<td align="left" width="180px" style="text-align:left" >
										<s:select list="subjectList" listKey="subjectId"   id="subjectList"
											listValue="subjectName"    value="querySignUpCondition.subjectId"
											theme="simple" name="querySignUpCondition.subjectId"
											headerKey="-1" headerValue="---请选择---" ></s:select>
									</td>
									<td>手机号码:</td>
									<td align="left" class="lists_tleft" style="text-align: left">
										<input type="text" name="querySignUpCondition.phoneNumber"
										id="phoneNumber" value="<s:property value="querySignUpCondition.phoneNumber"/>" />
									</td>
								</tr>
								<tr>
								<td >创建时间:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<input type="text" readonly="readonly" name="querySignUpCondition.createBeginDate" id="createBeginDate"
                                    	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="querySignUpCondition.createBeginDate" format="yyyy-MM-dd HH:mm:ss"/>" />
                                	 &nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
                                 	<input type="text" readonly="readonly" name="querySignUpCondition.createEndTime" id="createEndTime"
                                     	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="querySignUpCondition.createEndTime" format="yyyy-MM-dd HH:mm:ss"/>" />
                                     	<s:submit value="查询" onclick="return yan();"/>
								 </td>
							</tr>
							</table>
						</form>
					</div></td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="lists_bor"></td>
				<td class="lists_top">
					<font class="lists_fleft">最新注会报考列表</font>
					<font class="lists_fright">
						<img src="<%=contextPath%>/back/images/edt_tbl.gif" /><a href="###" onclick="exportcsv()">导出数据表</a>
					</font>
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="lists_bor"></td>
				<td>
					<form name="signUpForm" method="post">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info">
							<tr class="lists_infobg">
								<td width="8%">项目专业</td>
								<td width="8%">学历</td>
								<td width="4%">年龄</td>
								<td width="4%">手机号码</td>
								<td width="4%">创建时间</td>
							</tr>
							<s:iterator id="SignUptemp" value="page.pageResult">
								<tr>
									<td><s:property value="#SignUptemp.subjectName"/></td>
									<td><s:if test="#SignUptemp.education==1">
										博士
									</s:if>
									 <s:elseif test="#SignUptemp.education==2">
										硕士
									</s:elseif>
									 <s:elseif test="#SignUptemp.education==3">
										本科
									</s:elseif>
									<s:elseif test="#SignUptemp.education==4">
										专科
									</s:elseif>
									 <s:else>
										其它
									</s:else></td>
									<td>
									<s:if test="#SignUptemp.age==1">
										18-25岁
									</s:if> 
									<s:elseif test="#SignUptemp.age==2">
										25-35岁
									</s:elseif>
									<s:elseif test="#SignUptemp.age==3">
										35-45岁
									</s:elseif>
									<s:else>
										45岁以上
									</s:else>
									</td>
									<td><s:property value="#SignUptemp.phoneNumber" /></td>
									<td>
										<s:date name="#SignUptemp.createDate" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
								</tr>
							</s:iterator>
						</table>
					</form></td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="td_wid_l"><img
					src="<%=contextPath%>/back/images/tab_18.gif" />
				</td>
				<td class="lists_bottom"><jsp:include
						page="/back/jsp/common/showPage.jsp" /></td>
				<td class="td_wid_r"><img
					src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>