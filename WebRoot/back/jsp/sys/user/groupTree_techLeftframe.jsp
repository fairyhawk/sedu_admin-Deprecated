<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="<%=contextPath%>/back/style/leftframe.css" rel="stylesheet" type="text/css" />
		<link href="<%=contextPath%>/back/style/style.css" rel="stylesheet" type="text/css" />
		<link rel="StyleSheet" href="<%=contextPath%>/back/style/dtree.css" type="text/css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/dtree.js"></script>
	</head>
<body>
		<div id="leftframe">
			<div class="TreeMenu" id="CategoryTree">
				<script type="text/javascript">
					d = new dTree('d','<%=contextPath%>','table');
					d.add(0,-1,'教师用户组列表');
					<s:iterator value="groupList">
					d.add(<s:property value="groupId"/>,<s:property value="parentGroupId"/>,'<s:property value="groupName"/>','user!listUserByGroupId?groupId='+<s:property value="groupId"/>+'&queryUserCondition.currentPage=1&userType=1','listById','rightFrame');
					</s:iterator>
					document.write(d);
				</script>
			 <form target="rightFrame" action="group!toAddGroup.action">
					<input type="submit" value="增加组" size="20"/>
			 </form>
			 <form target="rightFrame" action="group!toDeleteGroup.action">
					<input type="submit" value="删除组" size="20"/>
			 </form>
			 <form target="rightFrame" action="user!toAddUser.action">
			 		<input type="hidden" value="1" id="userType" name="userType"/>
					<input type="submit" value="增加用户" size="20"/>
			 </form>
			</div>
		</div>
	</body>
</html>
