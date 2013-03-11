<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>行为追踪列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script language="JavaScript">
	
    function search(ObjectForm){
   	  var startTime=document.getElementById("startTime").value;
      var endTime=document.getElementById("endTime").value;
      if(startTime!=null&&endTime!=null){
      if(startTime>endTime){
         alert("结束时间要大于开始时间!");
         return false;
      }else{
    	ObjectForm.action="<%=contextPath%>/sys/visit!getVisitList.action";
		ObjectForm.submit();
		}
    	}
    }
    function cancel(){
        document.getElementById("visitHistory.visitType").value="";
    	document.getElementById("visitHistory.visitIp").value="";
    	document.getElementById("visitHistory.nameSpace").value="";
    	document.getElementById("visitHistory.actionName").value="";
    	document.getElementById("visitHistory.userType").value="";
    	document.getElementById("startTime").value="";
    	document.getElementById("endTime").value="";
    	
    }
	</script>
	</head>
	<body>
<div>
	<form name="f1"  method="post" action="visit!getVisitList.action">
	<s:hidden name="queryVisitHistoryCondition.currentPage" value="1"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">行为追踪列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
						开始时间：<input type="text" name="startTime" readonly id="startTime" onclick="WdatePicker()" onfocus="inputOnFocus()" value="${startTime}" />&nbsp;&nbsp;&nbsp;&nbsp;
						结束时间：<input type="text" name="endTime" readonly id="endTime" onclick="WdatePicker()" onfocus="inputOnFocus()" value="${endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;
					    用户名称：<input type="text" name="userName" id="userName" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
						访问IP：<input type="text" name="visitHistory.visitIp" id="visitHistory.visitIp" value="${visitHistory.visitIp}"/>&nbsp;&nbsp;&nbsp;&nbsp;</br>
						namespace：<input type="text" name="visitHistory.nameSpace" id="visitHistory.nameSpace" value="${visitHistory.nameSpace}"/>&nbsp;&nbsp;&nbsp;&nbsp;
						actionname：<input type="text" name="visitHistory.actionName" id="visitHistory.actionName" value="${visitHistory.actionName}"/>&nbsp;&nbsp;&nbsp;&nbsp;
						访问类型：
							<select  name="visitHistory.visitType" id="visitHistory.visitType" style="width:150px">
								<option value="">---请选择---</option>
								<option value=".action">.action</option>
								<option value=".jsp">.jsp</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;
						用户类型：
						<select name="visitHistory.userType" id="visitHistory.userType" style="width:150px">
							<option value="0">---请选择---</option>
							<option value="1">前台游客</option>
							<option value="2">前台用户</option>
							<option value="3">后台用户</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:search(document.f1)">查询</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:cancel()">清空</a></td></tr></table>
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td>
							用户类型
						</td>
						<td>
							用户名称
						</td>
						<td>
							访问IP
						</td>
						<td>
							namespace
						</td>
						<td>
							actionname
						</td>
						<td>
							访问类型
						</td>
						<td>
							访问时间
						</td>
						
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="visit">
						<tr>
							<td><s:if test="#visit.userType==1">
								前台游客
								</s:if>
								<s:if test="#visit.userType==2">
								前台用户
								</s:if>
								<s:if test="#visit.userType==3">
								后台用户
								</s:if>
							</td>
							<td>
							  	<s:if test="#visit.userType==1">
								前台游客
								</s:if>
								<s:if test="#visit.userType==2">
							 		<s:iterator value="customerList" id="customer">
							 			<s:if test="#customer.cusId==#visit.userId">
							 			<s:property value="#customer.cusName" />
							 			</s:if>
							 		</s:iterator>
								</s:if>
								<s:if test="#visit.userType==3">
								<s:iterator value="userList" id="user">
							 			<s:if test="#user.userId==#visit.userId">
							 			<s:property value="#user.userName" />
							 			</s:if>
						 		</s:iterator>
								</s:if>
							</td>
							<td>
								<s:property value="#visit.visitIp" />
							</td>
							<td>
								<s:property value="#visit.nameSpace" />
							</td>
							<td>
								<s:property value="#visit.actionName" />
							</td>
							<td>
								<s:property value="#visit.visitType" />
							</td>
							<td>
								<s:date name="#visit.visitTime"  format="yyyy-MM-dd"/>
							</td>
							
							
							<td>
								<a href='visit!deleteVisit.action?visitHistory.id=<s:property value="#visit.id"/>' onclick="return confirm('是否删除？')">删除</a>
							</td>
						</tr>
					</s:iterator>
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
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	</form>
</div>
	</body>
	<script language="javascript">
var userType=document.getElementById("visitHistory.userType");
for (i=0;i<userType.options.length;i++)
{ 
     if(userType.options[i].value=='${visitHistory.userType}')
     {
         userType.options[i].selected=true;break;
     } 
}
var visitType=document.getElementById("visitHistory.visitType");
for (i=0;i<visitType.options.length;i++)
{ 
     if(visitType.options[i].value=='${visitHistory.visitType}')
     {
         visitType.options[i].selected=true;break;
     } 
}


</script>
</html>
