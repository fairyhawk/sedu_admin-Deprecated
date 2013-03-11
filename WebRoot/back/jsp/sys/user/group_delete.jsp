<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>删除用户组</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="StyleSheet" href="<%=contextPath%>/back/style/dtree.css"	type="text/css" />
		<script type="text/javascript"	src="<%=contextPath%>/back/script/check_dtree.js"></script>
		<script type="text/javascript">
			function submitForm(){
				if(window.confirm("删除用户组将会同时删除用户组下的用户。您确定要删除吗？")) {
					var ids = "";
					var form = document.getElementById("grouptForm");
					for (var i=0; i<form.elements.length; i++) {
						var element = form.elements[i];
						if (element.name == "id" && element.type=='checkbox'){
							if( element.checked == true ){
								ids = ids + element.value + ",";
							}
						}
					}		
				    document.getElementById("groupIds").value=ids;
				    form.appendChild(document.getElementById("groupIds"));
				    form.action = "group!deleteGroup.action";
				    form.submit();
			    } 
			    return false;
			}
		</script>
	</head>
	<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">删除用户组</font>
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
					<td class="lists_tleft">	
						<a href="javascript: d.openAll();">open all</a> |
						<a href="javascript: d.closeAll();">close all</a>
					</td>
				</tr>
				<tr>
					<td class="lists_tleft">
						<script type="text/javascript">
							d = new dTree('d','<%=contextPath%>','grouptForm');
							d.add(-2,-1,'用户组');
							<s:iterator value="groupList">
								d.add(<s:property value="groupId"/>,<s:property value="parentGroupId"/>,'<s:property value="groupName"/>','','','');
							</s:iterator>
							document.write(d);
						</script>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="groupIds" id="groupIds"/>
						<input type="button" value="提 交" onclick="submitForm()"/>
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
	</body>
</html>
