<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>查看学员信息</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
	</head>
	<body>
	<body>
		<div class="main_right">
			<h1>
				查看学员
			</h1>
			<table cellpadding="0" cellspacing="1" class="tables">
				<tr height="30">
					<th>
						用户名
					</th>
					<td>
						<s:property value='customer.cusName'/>
					</td>
				</tr>
				<tr height="30">
					<th>
						电子邮箱
					</th>
					<td>
						<s:property value='customer.email'/>
					</td>
				</tr>
				<tr height="30">
					<th>
						手机号码
					</th>
					<td>
						<s:property value='customer.mobile'/>
					</td>
				</tr>
				<tr height="30">
					<th>
						真实姓名
					</th>
					<td>
						<s:property value='customer.realName'/>
					</td>
				</tr>
				<tr height="30">
					<th>
						性别
					</th>
					<td>
						<s:if test="customer.sex==1">
							男
						</s:if>
						<s:else>
							 女
						</s:else>
					</td>
				</tr>
				<tr height="30">
					<th>
						身份证号
					</th>
					<td>
						<s:property value='customer.idNum'/>
					</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="1" class="tables">
				<tr>
					<th colspan="3">
						所选专业与课程
					</th>
				</tr>
				<tr>
					<td>专业</td>
					<td>年份</td>
					<td>课程</td>
				</tr>
				<s:if test="courseDTOList==null||courseDTOList.size==0">
					<tr>
						<td colspan="3" align="center">
							该教师没有选择任何课程
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="courseDTOList">
						<tr>
							<td><s:property value="subjectName"/></td>
							<td><s:property value="gradeName"/></td>
							<td><s:property value="title"/></td>
						</tr>
					</s:iterator>
				</s:else>
				<tr>
					<td colspan="3">
						<input type="button" value="返回" onclick="history.go(-1)"></input>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>