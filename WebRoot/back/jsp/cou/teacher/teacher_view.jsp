<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>查看教师信息</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
</head>
<body>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">查看教师</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td width="10%">
						头像
					</td>
					<td class="lists_tleft">
						<img src=<%=contextPath %>/<s:property value="teacher.picPath==''?'back/images/teacher_default.JPG':'cou/teacher!download.action?downloadFileName='+teacher.picPath"/> />
					</td>
				</tr>
				<tr height="30">
					<td>
						教师名
					</td>
					<td class="lists_tleft">
						<s:property value='teacher.name'/>
					</td>
				</tr>
				<tr height="30">
					<td>
						教育背景
					</td>
					<td class="lists_tleft">
						<s:property value='teacher.education'/>
					</td>
				</tr>
				<tr height="30">
					<td>
						明星教师
					</td>
					<td class="lists_tleft">
						<s:if test="teacher.isStar==1">
							明星教师
						</s:if>
						<s:else>
							普通教师
						</s:else>
					</td>
				</tr>
				<tr height="30">
					<td>
						从业简介
					</td>
					<td class="lists_tleft">
						<s:property value='teacher.career'/>
					</td>
				</tr>
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
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td>
				<font class="lists_fleft">所选专业与课程</font>
				<font class="lists_fright">
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
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>专业</td>
						<td>年份</td>
						<td>课程</td>
					</tr>
					<s:if test="courseDTOList==null||courseDTOList.size==0">
						<tr>
							<td colspan="3" align="center">
								该教师没有任何课程
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
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>
