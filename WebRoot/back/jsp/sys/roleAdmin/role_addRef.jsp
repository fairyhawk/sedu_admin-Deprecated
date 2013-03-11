<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加角色</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/script/jquery-1.3.2.js"></script>
		<script type="text/javascript">
		     function submitResult(){
		     	if(document.getElementById("roleId").value<1) {
		     		alert("请选择角色！");
		     		return false;
		     	}
				var grades = getGradeId("grades");
				document.getElementById("gradeIds").value = grades; 
			}
			function getGradeId(objId){
				var c=document.getElementsByName(objId); 
				var v="";
	  			for(i=0;i<c.length;i++){   
	        		if(c[i].checked==true){
	        			if(v===""){
	        				v=c[i].value;
	        			}else{
	                 		 v =v+","+c[i].value;
	                  	}
	       		 	}   
	  			} 
	  			return v;  
			}
		</script>
	</head>
	<body>
<div>
	<s:form action="roleAdmin!addRoleRef.action" method="post" onsubmit="return submitResult()">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加角色</font>
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
					<td class="lists_tleft" colspan="2">
					        角色名称:
						<s:if test="usersId!=433">
					    <s:select  name="roleId" id="roleId" list="roleList"  listKey="roleId" listValue="roleName" headerKey="-1" headerValue="请选择" theme="simple">
						</s:select>
						</s:if>
						<s:else>
						<select  name="roleId" id="roleId">
						<option value="-1" selected="selected">请选择</option>
						<option value="73">销售人员</option>
						</select>
						</s:else>
				 	</td>
				</tr>
				<!--<tr>
					    <td width="15%">
					      	对应专业和所属年份设置:
					    </td>
						<td class="lists_tleft">
					      	<s:iterator value="subjectList" id="subject">
								<s:property value="subjectName" />
								<s:iterator value="#subject.gradeList" id="grade">
									<input type="checkbox" name="grades"
									 value="<s:property value="subjectId"/>:<s:property value="gradeId"/>" 
									 id="gg<s:property value="subjectId"/>:<s:property value="gradeId"/>"/>
									<s:property value="gradeName" />
								</s:iterator>
								<br/>
					      	</s:iterator>
					     </td>
					</tr>
				--><tr>
					<td class="lists_tleft" colspan="2">
					    <s:hidden name="userId"  id="userId"></s:hidden>
					    <s:hidden name="gradeIds"  id="gradeIds"></s:hidden>
						<s:submit value="提交" />
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
	</s:form>
</div>
	</body>
</html>
