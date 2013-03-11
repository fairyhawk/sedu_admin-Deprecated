<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>积分记录</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script language="JavaScript">

    function search(ObjectForm){
    
    	ObjectForm.action="<%=contextPath%>/cus/repbl!getProblemList.action";
		ObjectForm.submit();
    
    }
    function cannel(){
    document.getElementById("problem.pblTitle").value="";
    document.getElementById("problem.cusId").value=0;
    document.getElementById("problem.courseId").value=0;
    
    }
	</script>
	</head>
	<body>
<div>
	<form name="f1"  method="post" action="books!getBooksList.action">
	<s:hidden name="queryTsRecordCondition.currentPage" value="1"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">积分详细记录</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
						用户名称：
							<select name="problem.cusId" id="problem.cusId" style="width:157px">
							    <option value="0">---请选择---</option>
								<s:iterator value="customerList" id="customer">
								<option value="<s:property value="#customer.cusId"/>">
								<s:property value="#customer.cusName"/>
								</option>
								</s:iterator>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;
						课程名称：
							<select name="problem.courseId" id="problem.courseId" style="width:157px">
								<option value="0">---请选择---</option>
								<s:iterator value="courseList" id="course">
									<option value="<s:property value="#course.courseId"/>"><s:property value="#course.title"/></option>
								</s:iterator>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;
						问题内容：
							<input type="text" name="problem.pblTitle" id="problem.pblTitle" value="${problem.pblTitle}"/>&nbsp;&nbsp;&nbsp;&nbsp;
					</td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:search(document.f1)">查询</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:cannel()">清空</a></td></tr></table>
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
					 	    积分ID
					 	</td>
						<td>
							用户名称
						</td>
						<td>
							积分类别
						</td>
						<td>
							获取方式
						</td>
						<td>
							获取时间
						</td>
						<td>
							消耗方式
						</td>
						<td>
							消耗时间
						</td>
						<td>
							积分数值
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="tsRecord">
						<tr>
							<td>
							    <s:property value="#tsRecord.tsId" />
							</td>
							<td>
							<s:iterator value="customerList" id="customer">
							   <s:if test="#customer.cusId==#tsRecord.cusId">
								<s:property value="#customer.cusName" />
								</s:if>
						     </s:iterator>
							</td>
							<td>
								<s:if test="#tsRecord.trType==0">
								兑换积分
								</s:if>
								<s:if test="#tsRecord.trType==1">
								行为积分
								</s:if>
							</td>
							<td>
								<s:if test="#tsRecord.accessType==0">
								注册成功
								</s:if>
								<s:if test="#tsRecord.accessType==2">
								购买课程
								</s:if>
								
							</td>
							<td>
							    <s:date name="#tsRecord.accessTime"  format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:property value="#tsRecord.useType"/>
							</td>
							<td>
								  <s:date name="#tsRecord.useTime"  format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:property value="#tsRecord.trNum"/>
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
	
</html>
