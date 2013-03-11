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
					章列表
				</h1>
				<table cellpadding="0" cellspacing="1" class="tables" onmouseover="changeto()" onmouseout="changeback()">
				<tr><td colspan="11"><input type="button" value="新建章" onclick="document.location.href='<%=contextPath%>/kb/chapter!toChapterAdd.action?studycourse.cId=<s:property value="chapter.cId"/>'" /></td></tr>
				<tr>
					<td>
						索引
					</td>
					<td>
						章名称
					</td>
					<td>
						从属专业
					</td>
					<td>
						从属学科
					</td>
					<td>
						包括节数
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
				<s:iterator value="chList" id="chapter">
					<tr>
						<td>
							<s:property value="#chapter.chIndex" />
						</td>
						<td>
							<s:property value="#chapter.chName" />
						</td>
						<td>
							<s:property value="#chapter.pId" />
						</td>
						<td>
							<s:property value="#chapter.cId" />
						</td>
						<td>
							<s:property value="#chapter.chStSubject" />
						</td>
						<td>
							<s:property value="#chapter.chVersion" />
						</td>
						<td>
							<s:date name="#chapter.chCreatetime"  format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href='chapter!deleteChapter.action?chapter.chId=<s:property value="#chapter.chId"/>&chapter.cId=<s:property value="#chapter.cId"/>' onclick="return confirm('是否删除？')">删除</a>
						</td>
						<td>
						    <a href='chapter!toEditChapter.action?chapter.chId=<s:property value="#chapter.chId"/>&chapter.cId=<s:property value="#chapter.cId"/>'> 修改</a>
						</td>
						<td>
						    <a href='section!getSectionList.action?section.chId=<s:property value="#chapter.chId"/>'>查看</a>
						</td>
						<td>
						<a href='section!toSectionAdd.action?chapter.chId=<s:property value="#chapter.chId"/>'>新建节</a>
						</td>
					</tr>
				</s:iterator>
				
			</table>
			
		</div>
		</form>
	</body>
</html>