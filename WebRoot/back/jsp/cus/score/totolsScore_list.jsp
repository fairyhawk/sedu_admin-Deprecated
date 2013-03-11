<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>积分列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script language="JavaScript">
	function search(ObjectForm){
		ObjectForm.action="<%=contextPath%>/cus/totolsScore!getTotolsScoreList.action";
		ObjectForm.submit();
	}
   
	function cannel(){
		document.getElementById("totolsScore.tsCurrent").value="";
		document.getElementById("totolsScore.cusId").value=0;
	}
	function ch(a)
	{	
		document.getElementById("cId").value=a;
	}
</script>
</head>
<body>
<div>
	<form name="f1"  method="post" action="books!getBooksList.action">
	<s:hidden name="queryTotolsScoreCondition.currentPage" value="1"/>
	<s:hidden id="cId" name="queryTotolsScoreCondition.cusId" />
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">积分列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
						用户名称：
						<select name="totolsScore.cusId" id="totolsScore.cusId" style="width:157px" onchange="ch(this.value)">
						    <option value="0">---请选择---</option>
							<s:iterator value="customerList" id="customer">
							<option value="<s:property value="#customer.cusId"/>">
							<s:property value="#customer.cusName"/>
							</option>
							</s:iterator>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
						当前积分值：
						<input type="text" name="totolsScore.tsCurrent" id="totolsScore.tsCurrent" value="${totolsScore.tsCurrent}"/>&nbsp;&nbsp;&nbsp;&nbsp;
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
						<td>积分ID</td>
						<td>用户名称</td>
						<td>当前积分值</td>
						<td>行为积分值</td>
						<td>操作</td>
					</tr>
					<s:iterator value="page.pageResult" id="score">
						<tr>
							<td>
								<a href='tsRecord!getTsRecordList.action?tsRecord.tsId=<s:property value="#score.tsId"/>'><s:property value="#score.tsId"/></a>
							</td>
							<td>								
						     	<s:property value="#score.customer.cusName" />
							</td>
							<td>
								<s:property value="#score.tsCurrent" />
							</td>
							<td>
								<s:property value="#score.tsAction"/>
							</td>
							<td>
								<a href='totolsScore!delete.action?totolsScore.tsId=<s:property value="#score.tsId"/>' onclick="return confirm('删除积分连同积分详细记录一起删除？？')">删除</a>
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
