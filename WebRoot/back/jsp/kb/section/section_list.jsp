<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>书籍列表</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	</head>
	<body>
		<form name="f1"  method="post" action="books!getBooksList.action">
		<div class="main_right">
				<h1>
					节列表
				</h1>
				<table cellpadding="0" cellspacing="1" class="tables" onmouseover="changeto()" onmouseout="changeback()">
				<tr><td colspan="12"><input type="button" value="新建节" onclick="document.location.href='<%=contextPath%>/kb/section!toSectionAdd.action?chapter.chId=<s:property value="section.chId"/>'" /></td></tr>
				<tr>
					<td>
						索引
					</td>
					<td>
						节名称
					</td>
					<td>
						从属专业
					</td>
					<td>
						从属学科
					</td>
					<td>
					    从属章
					</td>
					<td>
						知识单元数
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
				<s:iterator value="sList" id="section">
					<tr>
						<td>
							<s:property value="#section.sIndex" />
						</td>
						<td>
							<s:property value="#section.sName" />
						</td>
						<td>
							<s:property value="#section.pId" />
						</td>
						<td>
							<s:property value="#section.cId" />
						</td>
						<td>
							<s:property value="#section.chId" />
						</td>
						<td>
							<s:property value="#section.sKSubject" />
						</td>
						<td>
							<s:property value="#section.sVersion" />
						</td>
						<td>
							<s:date name="#section.sCreatetime"  format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href='section!deleteSection.action?section.sId=<s:property value="#section.sId"/>&section.chId=<s:property value="#section.chId"/>' onclick="return confirm('是否删除？')">删除</a>
						</td>
						<td>
						    <a href='section!toEditSection.action?section.sId=<s:property value="#section.sId"/>&section.chId=<s:property value="#section.chId"/>'> 修改</a>
						</td>
						<td>
						查看
						</td>
						<td>
						<a href='section!toSectionAdd.action?chapter.chId=<s:property value="#chapter.chId"/>'>新建知识单元</a>
						</td>
					</tr>
				</s:iterator>
				
			</table>
			
		</div>
		</form>
	</body>
</html>