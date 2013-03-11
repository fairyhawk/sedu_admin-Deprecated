<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户列表</title>
		<script language="JavaScript" src="<%=contextPath%>/back/dwr/engine.js"	type="text/javascript"></script>
		<script language="JavaScript" src="<%=contextPath%>/back/dwr/util.js"	type="text/javascript"></script>
		<script language="JavaScript" src="<%=contextPath%>/back/dwr/interface/subjectService.js" type='text/javascript'></script>
		<link href="<%=contextPath%>/back/css/data_table.css" rel="stylesheet"	type="text/css" />
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="rightframe">
			<form action="user!listUserByKey.action" method="post">
				<table  class="com_table com_table1" >
					<s:iterator value="user.roleList" id="role">
					<tr>
					     <td>
					      	角色名称:<s:property value="roleName" />
					     </td>
						<td colspan="2">
					      	权限:
					      	<s:iterator value="#role.functionList">
								<s:property value="functionName" />
					      	</s:iterator>
					     </td>
					</tr>
					<tr>
					     <td>
					      	对应专业和所属年份设置:
					     </td>
						<td colspan="2">
					      	<s:iterator value="#role.subjectList" id="subject">
								<s:property value="subjectName" />
									<s:iterator value="#subject.gradeList" id="grade">
										<s:property value="gradeName" />
									</s:iterator>
					      	</s:iterator>
					     </td>
					</tr>
					 </s:iterator>
					<tr>
						<td>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>