<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		
			 function search(ObjectForm){
			  var startTime=document.getElementById("startTime").value;
			  var endTime=document.getElementById("endTime").value;
			  if(startTime!=null&&endTime!=null){
			  if(startTime>endTime){
				 alert("结束时间要大于开始时间!");
				 return false;
			  }else{
				ObjectForm.action="<%=contextPath%>/cus/cus!showCustomerList.action";
				ObjectForm.submit();
				}
			  }
			}
		
			
		
			function delCustomer() {
				if(window.confirm("确认删除这些学员吗？")) {
					document.cusForm.action = "<%=contextPath%>/cus/cus!deleteCustomer.action?queryCustomerCondition.currentPage=" + <s:property value="page.currentPage"/>;
					document.cusForm.submit();
				}
			}
			function addCustomer() {
				window.location.href = "<%=contextPath%>/cus/cus!initAddCustomer.action";
			}
			
			function updateCustomer(id) {
				window.location.href = "<%=contextPath%>/cus/cus!initUpdateCustomer.action?customer.cusId=" + id;
			}
			
			function resetKey() {
				$("#op0").attr("selected","selected");
				$("input[name=queryCustomerCondition.email]").val("");
				$("input[name=queryCustomerCondition.mobile]").val("");
				$("input[name=startTime]").val("");
				$("input[name=endTime]").val("");
				$("#op1").attr("selected","selected");
			}
			
			function allCheck(cb) {
				$("input[name=ids]:checkbox").attr('checked', cb.checked);
			}
			
			function updatePwd(id) {
				window.location.href = "<%=contextPath%>/cus/cus!initUpdatePwd.action?customer.cusId=" + id;
			}
			function freeCourse(id,type){
				if(confirm("免费赠送需甚重使用，会重新初始化知识点？")){
				window.location.href="<%=contextPath%>/cus/cus!freeCourse.action?customer.cusId="+id+"&customer.cusType="+type;
				}
			}
			function toForgotPwd() {
				window.location.href = "<%=contextPath%>/cus/cus!toForgotPwd.action";
			}
			
			function viewCus(cusId) {
				window.location.href = "<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=" + cusId;
			}
		</script>
	</head>
	<body>
		<div class="main_right">
				<h1>
					学员列表
				</h1>
		
			<form name="searchForm" action="" method="post">
					<s:hidden name="queryCustomerCondition.currentPage" value="1"/>
				<table cellpadding="0" cellspacing="1" class="tables">
					<tr>
						<td>
							<table>
								<tr>
									<td>
										开始时间
										<input type="text" name="startTime" readonly id="startTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${startTime}" />
									</td>
									<td>
										结束时间
										<input type="text" name="endTime" readonly id="endTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${endTime}" />
									</td>
								</tr>
								<tr>
									<td>
										邮箱地址
										<input type="text"
											value="<s:property value="queryCustomerCondition.email"/>"
											name="queryCustomerCondition.email" />
									</td>
									<td>
										用户类型
										<select name="queryCustomerCondition.cusType" id="queryCustomerCondition.cusType" style="width:155px">
											<option value="" id="op1">---请选择---</option>
											<option value="0">注册学员</option>
											<option value="1">内部学员</option>
										</select>
									</td>
									<td>
										<input type="button" value="查询"
											onclick="search(document.searchForm)"></input>
										<input type="button" value="清空" onclick="resetKey()"></input>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
				<form name="cusForm" method="post">
					<table cellpadding="0" cellspacing="1" class="tables" onmouseover="changeto()" onmouseout="changeback()">
							<!--  <tr>
								<td colspan="8">
									<input type="button" value="删除学员" onclick="delCustomer()"/>
									
									<input type="button" value="添加学员" onclick="addCustomer()"/>
									
									<input type="button" value="找回密码" onclick="toForgotPwd()"/>
									
								</td>
								
							</tr>-->
							<tr>
								<!--  <td width="10%"><input type="checkbox" onclick="allCheck(this)"/>全选</td>-->
								<td width="10%"><font style="font-weight:bold">昵称</font></td>
								<td width="15%"><font style="font-weight:bold">电子邮件</font></td>
								<td width="8%"><font style="font-weight:bold">真实姓名</font></td>
								<td width="5%"><font style="font-weight:bold">性别</font></td>
								<td width="9%"><font style="font-weight:bold">上次登录地</font></td>
								<td width="8%"><font style="font-weight:bold">用户类型</font></td>
								<td width="5%"><font style="font-weight:bold">登录次数</font></td>
								<td width="8%"><font style="font-weight:bold">注册时间</font></td>
								<td width="8%"><font style="font-weight:bold">订单</font></td>
								<td width="*"><font style="font-weight:bold">操作</font></td>
							</tr>
						<s:iterator value="page.pageResult" id="customer" status="status">
							<tr>
								<!--  <td><input type="checkbox" name="ids" value="<s:property value="cusId"/>"/></td>-->
								<td>
									<a href="#" onclick="viewCus(<s:property value="cusId" />)"><s:property value="cusName" /></a>
								</td>
								<td>
									<s:property value="email" />
								</td>
								<td>
									<s:property value="realName" />
								</td>
								<td>
									<s:if test="sex==1">
										男
									</s:if>
									<s:else>
										女
									</s:else>
								</td>
								<td>
									<s:if test="loginRecordList.size>0">
										<s:property value="loginRecordList[0].address"/>
									</s:if>
								</td>
								<td>
									<s:if test="cusType==0">
									注册学员
									</s:if>
									<s:if test="cusType==1">
									内部学员
									</s:if>
								</td>
								<td>
									<s:property value="loginTimes"/>
								</td>
								<td>
									<s:date format="yyyy-MM-dd" name="regTime" />
								</td>
								<td>
									<s:if test="contractCount>0">
										<a href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">查看订单</a>
									</s:if>
									<s:else>
										无
									</s:else>
								</td>
								<td>
									<a href="#" onclick="updateCustomer(<s:property value="cusId" />)">修改</a>
									<a href="#" onclick="updatePwd(<s:property value="cusId" />)">修改密码</a>
									<a href="#" onclick="freeCourse(<s:property value="cusId" />,<s:property value="cusType" />)">免费赠送课程</a>
								</td>
							</tr>
						</s:iterator>
							<tr>
								<td colspan="10">
									<jsp:include page="/back/jsp/common/showPage.jsp" />
								</td>
							</tr>
					</table>
				</form>
		</div>
	</body>
	<script language="javascript">
var userType=document.getElementById("queryCustomerCondition.cusType");
for (i=0;i<userType.options.length;i++)
{ 
     if(userType.options[i].value=='${queryCustomerCondition.cusType}')
     {
         userType.options[i].selected=true;break;
     } 
}
</script>
</html>