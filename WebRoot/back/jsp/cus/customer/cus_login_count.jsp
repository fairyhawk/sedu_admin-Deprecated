<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员登录统计</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript">
		$(document).ready(function(){});
		function cusCount(){
			$("#cusCount").show();
			$("#cusSum").hide();
		}
		function cusSum(){
			$("#cusCount").hide();
			$("#cusSum").show();
		}
		var cusCountZong=0;
		var cusSumZong=0;
		</script>
	</head>
	<body>

	<form action="<%=contextPath %>/cus/cus!getCusLoginCount.action" method="post" name="countForm" >
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists" >
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">学员登录统计</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
	            <td class="lists_bor"></td>
	            <td>
	              <div class="msg-zy">
				<table class="" border="0" cellspacing="0"  cellpadding="0">
						<tr>
						<%-- <td>
							所在项目:<s:select name="queryCustomerCondition.subjectId" id="subjectId" list="subjectList" listKey="subjectId"
									listValue="subjectName" headerKey="-1" headerValue="--请选择专业--" theme="simple">
						 	</s:select>
						 </td> --%>
						<td width="20px;"></td>
						<td>登录日期 ：
						<input type="text" name="queryCustomerCondition.startTime" readonly="readonly" id="createTimeBegin" 
						onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"/>-
							<input type="text" name="queryCustomerCondition.endTime" readonly="readonly" id="createTimeEnd" 
						onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"/>
						</td>
						<td><a href="javascript:document.countForm.submit()"><img src="<%=contextPath%>/back/images/add_a.gif"/>查询</a></td>
						</tr>
						</table>
						</div>
					 </td>		
            <td class="lists_tright lists_bor2"></td>
        </tr>
		<tr><td></td><td colspan="3"> <b> 
		<a href="javascript:void(0)" onclick="cusCount();" style="cursor: pointer;"> 登录人次(PV)</a> &nbsp;&nbsp;
		 <a href="javascript:void(0)" onclick="cusSum();" style="cursor: pointer;">登录人数(IP)</a>
		 </b></td> 
		 </tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" id="cusCount" class="lists_info" >
					<tr class="lists_infobg">
						<td>
							登录次数<label id="tab1"></label>
						</td>
						<td>
							时间
						</td>
					</tr>
					<tr id="cusCount">
					<s:iterator value="cusLoginCountList" var="count">
						<tr >
							<td>
								<s:property value="#count.cusCount"/>
								<script type="text/javascript">
								cusCountZong+=<s:property value="#count.cusCount"/>;
								</script>
							</td>
							<td>
							<s:property value="#count.loginTime"/>
								</td>
						</tr>
					</s:iterator>
					</tr>
					</table>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" id="cusSum" class="lists_info" style="display: none">
					<tr class="lists_infobg">
						<td>
							登录人数<label id="tab2"></label>
						</td>
						<td>
							时间
						</td>
					</tr>
					<s:iterator value="cusLoginCountSum" var="loginSum" >
						<tr >
							<td>
								<s:property value="#loginSum.cusCount"/>
								<script type="text/javascript">
								cusSumZong+=<s:property value="#loginSum.cusCount"/>;
								</script>
							</td>
							<td>
							<s:property value="#loginSum.loginTime"/>
								</td>
						</tr>
					</s:iterator>
					</table>
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
	<script type="text/javascript">
	$("#tab1").html("("+cusCountZong+")");
	$("#tab2").html("("+cusSumZong+")");
	</script>
	</form>
	</body>
</html>
