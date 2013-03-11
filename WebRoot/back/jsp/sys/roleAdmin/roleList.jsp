<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>角色列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
		<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<script type="text/javascript">
        $(document).ready(function() {
            cancelAddRole();
	        <s:iterator value="role.functionList" id="function" status="h">
	            if ($("#sfId${functionId}") != undefined && null != $("#sfId${functionId}")) {
	                $("#sfId${functionId}").attr("checked", true);
	            }
	        </s:iterator>
        })
        function cancelAddRole() {
            $("#overlay").hide();
            $("#addRole").hide();
        }

		function clickBox(id){
			$.each($("input[pid=" + id + "]"), function () {
				this.checked = $('#' + id).attr("checked");
				clickBox(this.id);
			});
		}

        function addRole() {
            $("#overlay").width($(document).width());
            $("#overlay").height($(document).height());
            $("#overlay").show();
            $("#addRole").show();
        }

        function delRole() {
            if (confirm("确定删除 ${role.roleName} ？")) {
                document.location.href = "<s:url value="/sys/roleAdmin!delRole.action?role.roleId=%{role.roleId}"/>";
            } else {
                return false;
            }
        }
    </script>
	<style type="text/css">
		#overlay {
			background: #303030;
			opacity: 0.50;
			filter: Alpha(opacity =   50);
			display: none;
			position: absolute;
			top: 0px;
			left: 0px;
			z-index: 100;
		}
		
		#addRole {
			margin-left: auto;
			margin-right: auto;
			border: 2px solid #FFFFFF;
			font-size: 12px;
			font-family: "宋体";
			color: #990000;
			padding-top: 20px;
			width: 400px;
			height: 200px;
			position: absolute;
			z-index: 110;
			display: none;
			background: #e7e7e7;
			left: 35%;
			top: 20%;
			opacity: 0.85;
			filter: Alpha(opacity =   85);
		}
	</style>
	</head>
	<body>
<div>
	<s:form action="roleAdmin!updateRoleFunction" method="POST" id="updateRoleFunctionForm">
	<s:hidden name="role.roleId" />
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">角色管理</font>
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
				    <td width="15%" valign="top">
								<table>
									<s:iterator value="roleList" id="role" status="i">
										<tr>
											<td bgcolor="<s:if test="roleId==role.roleId">#678197<s:else>white</s:else></s:if>">
												<a
													href="<s:url value="/sys/roleAdmin!roleList.action?role.roleId=%{roleId}"/>"><s:property
														value="roleName" />
												</a>
											</td>
										</tr>
									</s:iterator>
								</table>
					</td>
					<td  class="lists_tleft">
						<input type="hidden" value="1" name="selectedFunctionIds"/>
						<script type="text/javascript">
							d = new dTree('d','<%=contextPath%>/back/images/dtree');
							d.add(1,-1,'系统功能权限');
							var flag = true;
							<s:iterator value="firstLevelFunctionList">
								<s:if test="functionTypeId==@com.shangde.edu.sys.domain.Function@FUNCTION_TYPE_ITEM">
									d.add(${functionId},${parentFunctionId},'<input type="checkbox" name="selectedFunctionIds" pid="sfId${parentFunctionId}" id="sfId${functionId}" onclick="clickBox(\'sfId${functionId}\')" value="${functionId}"/>${functionName}');
								</s:if>
								<s:else>
									d.add(${functionId},${parentFunctionId},'<input type="checkbox" name="selectedFunctionIds" pid="sfId${parentFunctionId}" id="sfId${functionId}" onclick="clickBox(\'sfId${functionId}\')" value="${functionId}"/><font color="red">${functionName}</font>');
								</s:else>
							</s:iterator>
							document.write(d);
						</script>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="lists_tleft">
						<input type="button" value="添加角色" onclick="addRole();"/>
						<input type="button" value="删除选中角色" onclick="delRole();"/>
						<input type="reset" value="清空"/>
						<input type="submit" value="保存"/>*红字为功能权限，其他为菜单权限
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
<div id="addRole" class="addRole">
	<s:form action="roleAdmin!addRole" method="POST" id="addRoleForm">
		<table border="0" width="100%" height="100%">
			<tr>
				<td align="center">
					要添加的角色名称：
					<s:textfield name="addRole.roleName"
						id="addRoleForm_addRole_roleName" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="submit" value="确定"/>
					<input type="button" onclick="cancelAddRole();" value="取消"/>
				</td>
			</tr>
		</table>
	</s:form>
</div>
<div>
   <div id="overlay" class="overlay"/>
</div>
	</body>
</html>
