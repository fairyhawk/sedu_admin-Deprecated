<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>书籍列表</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>

	</head>
	<body>
		<form name="f1" method="post" action="books!getBooksList.action">
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">当前位置:[预设项管理>>专业项目管理]</font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info" onmouseover="changeto()" onmouseout="changeback()" >

							<tr>
								<td>
									<input type="button" value="新建专业"
										onclick="document.location.href='<%=contextPath%>/kb/professional!toProfessionalAdd.action'" />
								</td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td>
									索引
								</td>
								<td>
									专业名称
								</td>
								<td>
									科目数量
								</td>
								<td>
									版本
								</td>
								<td>
									创建时间
								</td>
								<td colspan="4">
									操作
								</td>
							</tr>
							<s:iterator value="pflList" id="professional">
								<tr>
									<td>
										<s:property value="#professional.pIndex" />
									</td>
									<td>
										<s:property value="#professional.pName" />
									</td>
									<td>
										<s:property value="#professional.pCSubject" />
									</td>
									<td>
										<s:property value="#professional.pVersion" />
									</td>
									<td>
										<s:date name="#professional.pCreatetime"
											format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<!--  <td>
										<a
											href='professional!deleteProfessional.action?professional.pId=<s:property value="#professional.pId"/>'
											onclick="return confirm('是否删除？')">删除</a>
									</td>-->
									<td>
										<a
											href='professional!toEditProfessional.action?professional.pId=<s:property value="#professional.pId"/>'>
											修改</a>
									</td>
									<!--  <td>
							<a href='studyCourse!getStudyCourseList.action?studycourse.pId=<s:property value="#professional.pId"/>'>查看</a>
						</td>
						<td>
							<a href='studyCourse!toStudyCourseAdd.action?professional.pId=<s:property value="#professional.pId"/>'>新建学科</a>
						</td>
						-->
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

					</td>
					<td>
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
