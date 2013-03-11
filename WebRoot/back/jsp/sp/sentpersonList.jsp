<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>接收信息人员列表</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<script>
		　function openWin(spId) { 
			window.open ("<%=contextPath%>/sp/sentperson!getSentPersonInfo.action?sentPersonId="+spId, "newwindow", "height=900, width=800, toolbar=no, menubar=yes, scrollbars=no, resizable=no, location=no, status=no");
		　}
		
		function delSentPerson(id){
			if(window.confirm("确认删除这条计入吗？")) {
				window.location.href = "<%=contextPath%>/sp/sentperson!delSentPersonById.action?sentPersonId=" + id;
			}
		}
		
		function cancel(){
		   document.getElementById("s_personName").value="";
		   document.getElementById("s_personPhone").value="";
	   }
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
				<font class="lists_fleft">接收信息人员列表</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		
		<tr>
			<td class="lists_bor"></td>
			<td>  
			<form action="<%=contextPath%>/sp/sentperson!seachSentPersonList.action?querySentPersonCondition.currentPage=1"
							name="keySearchForm" method="post">
				<div class="msg-yw">
				<table border="0" width="100%" align="center">
					<tr>
						<td align="right">姓名：</td>
						<td align="left"><input type="text" value="${querySentPersonCondition.personName}"
								name="querySentPersonCondition.personName" id="s_personName" /></td>
						<td align="right">手机号码：</td>
						<td align="left"><input type="text" value="${querySentPersonCondition.personPhone}"
								name="querySentPersonCondition.personPhone" id="s_personPhone" /></td>
						
						<td align="left">
							<input type="submit" value=" 查询 " />
							&nbsp;&nbsp;
							<input type="button" onclick="cancel()" value=" 重置 " />
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
						<font class="lists_fleft">信息接收人员列表</font>
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
								<td width="20%">
									编号
								</td>
								<td width="20%">
									姓名
								</td>
								<td width="25%">
									手机号码
								</td>
								<td>
									操作
								</td>
							</tr>
		
							<s:if test="page.pageResult != null">
								<s:iterator value="page.pageResult" id="sentPersonList" status="status">
									<tr>
										<td>
											<s:property
													value="(page.currentPage-1)*page.pageSize+#status.count" /> 
											</td>
										</td>
										<td>
											<s:property value="#sentPersonList.personName" />
										</td>
										<td>
											<s:property value="#sentPersonList.personPhone" />
										</td>
										<td>      
											<a href="<%=contextPath%>/sp/sentperson!getSentPersonById.action?sentPersonId=<s:property value="#sentPersonList.id"/>">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="javascript:delSentPerson(<s:property value="#sentPersonList.id"/>)">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<!--
											<a href="<%=contextPath%>/sp/sentperson!delSentPersonById.action?sentPersonId=<s:property value="#sentPersonList.id"/>"  onclick="confirm('确认删除？')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="<%=contextPath%>/sp/sentperson!getSentPersonInfo.action?sentPersonId=<s:property value="#sentPersonList.id"/>">查看详情</a> 
											-->
											<a href="javascript:openWin(<s:property value="#sentPersonList.id"/>)"> <font color="green">详细</font> </a>
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
		<!--  底下分页行  -->
	</table>
</div>
	</body>
</html>