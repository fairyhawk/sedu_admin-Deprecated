<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.shangde.edu.sys.domain.*" %>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加用户——用户基本信息设置</title>
		<script type="text/javascript" src="<%=contextPath%>/back/js/validate.js"></script>
		<script language="JavaScript" src="<%=contextPath%>/back/dwr/engine.js"
			type="text/javascript"></script>
		<script language="JavaScript" src="<%=contextPath%>/back/dwr/util.js"
			type="text/javascript"></script>
		<script language="JavaScript"
			src="<%=contextPath%>/back/dwr/interface/groupService.js"
			type='text/javascript'></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=contextPath%>/back/dwr/interface/userService.js"></script>
		<link href="<%=contextPath%>/back/css/data_table.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript">
			function getFChildGroup(){
					var fGroupId = DWRUtil.getValue("fGroupId");
					groupService.getChildGroupById(callbackGetFChildGroup,fGroupId);
			}
			
			function callbackGetFChildGroup(msg){
				DWRUtil.removeAllOptions("sGroupId");
				DWRUtil.addOptions("sGroupId",msg,"groupId","groupName");
			}
			
			
			function getSChildGroup(){
					var sGroupId = DWRUtil.getValue("sGroupId");
					groupService.getChildGroupById(callbackGetSChildGroup,sGroupId);
			}
			
			function callbackGetSChildGroup(msg){
				DWRUtil.removeAllOptions("tGroupId");
				DWRUtil.addOptions("tGroupId",msg,"groupId","groupName");
			}
			function checkUserName() {
					var userName = DWRUtil.getValue("user.loginName");
					userService.getUserCountByLoginName(callbackGetUserCount,userName);
			}
			function callbackGetUserCount(msg) {
				if(msg != "") {
					DWRUtil.setValue("userReturn",msg);
					document.getElementById("user.loginName").focus();
				}else {
					DWRUtil.setValue("userReturn",msg);
				}
			}
			function checkSubmit(){
				var fGroupId = DWRUtil.getValue("fGroupId");
				var sGroupId = DWRUtil.getValue("sGroupId");
				var tGroupId = DWRUtil.getValue("tGroupId");
				var loginName = DWRUtil.getValue("user.loginName");
				var classList = document.getElementsByName("classId");
				var password = DWRUtil.getValue("user.loginPwd");
				var cPassword = DWRUtil.getValue("cloginPwd");
				var subject = document.getElementsByName("subjectId");
				var grade = document.getElementsByName("gradeId");
				var userName = DWRUtil.getValue("user.userName");
				var classListChecked = false;
				var subjectChecked = false;
				var gradeChecked =false;
				if(isEmpty(loginName)) {
					alert("用户名不能为空!");
					return false;
				}
				if(isEmpty(userName)) {
					alert("真实姓名不能为空!");
					return false;
				}
				if(isEmpty(password)) {
					alert("请输入密码");
					return false;
				}
				if(isEmpty(cPassword)) {
					alert("密码重复不能为空!");
					return false;
				}
				if(trim(password) != trim(cPassword)) {
					alert("两次输入的密码必须一致!");
					return false;
				}
				for(i=0; i< classList.length; i++) {
					if(classList[i].checked){
						classListChecked = true;
						break;
					}
				}
				if(!classListChecked) {
					alert("请至少选择一个班级!");
					return false;
				}
				for(i=0; i<grade.length; i++) {
					if(grade[i].checked) {
						gradeChecked = true;
						break;
					}
				}
				if(!gradeChecked) {
					alert("请选择年级");
					return false;
				}
				for(i=0; i<subject.length; i++) {
					if(subject[i].checked) {
						subjectChecked = true;
						break;
					}
				}
				if(!subjectChecked) {
					alert("请至少选择一门学科!");
					return false;
				}
				if(tGroupId!='-1'){
					DWRUtil.setValue("user.groupId",tGroupId);
				}else if(sGroupId!='-1'&&tGroupId=='-1'){
					DWRUtil.setValue("user.groupId",sGroupId);
				}else{
					alert("用户必须归属二级组或三级组下!");
					return false;
				}
				return true;
			}
			function removeStr() {
				setTimeout("document.getElementById('s1').innerHTML = ''",2000);
			}
		</script>
	</head>
	<body onload="removeStr()">
		<div id="rightframe">
			<s:form action="user!addUserSubmit" method="post"
				onsubmit="return checkSubmit();">
				<table class="com_table com_table1" border="0" cellpadding="0"
					cellspacing="1" style="width: 600px; height: 200px;">
					<tr>
						<td colspan="2">
							添加用户——用户基本信息设置&nbsp;&nbsp;
							<span id="s1" style="color:red"><%=request.getAttribute("addSucInfo")==null?"":request.getAttribute("addSucInfo") %></span>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>用户名
						</td>
						<td>
							<input type="text" name="user.loginName" id="user.loginName"
								onblur="checkUserName();" />
							<span id="userReturn" style="color: red"></span>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>用户密码
						</td>
						<td>
							<input type="password" name="user.loginPwd" id="user.loginPwd" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>密码重复
						</td>
						<td>
							<input type="password" name="cloginPwd" id="cloginPwd" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>真实姓名
						</td>
						<td>
							<input type="text" name="user.userName" id="user.userName" />
						</td>
					</tr>
					<tr>
						<td width="15%">
							<font color="red">*</font>所属用户组
						</td>
						<td>
							<s:select name="fGroupId" id="fGroupId" list="groupList"
								listKey="groupId" listValue="groupName" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="getFChildGroup();">
							</s:select>
							<s:select name="sGroupId" id="sGroupId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="getSChildGroup();">
							</s:select>
							<s:select name="tGroupId" id="tGroupId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple">
							</s:select>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>所属年级
						</td>
						<td>
							<!--<s:checkboxlist list="gradeList" id="gradeId" name="gradeId"
								listKey="gradeId" listValue="gradeName"></s:checkboxlist>-->
							<%
								List gradeList = (List)request.getAttribute("gradeList");	
								for(Iterator iter = gradeList.iterator();iter.hasNext();) {
									Grade grade = (Grade)iter.next();
									if("初一年级".equals(grade.getGradeName()) && grade != gradeList.get(0)) {%>
										<br/>
									<%}else if("高一年级".equals(grade.getGradeName()) && grade != gradeList.get(0)) {%>
										<br/>
									<%} %>
							<input type="checkbox" id="gradeId" name="gradeId" value="<%=grade.getGradeId() %>"/><%=grade.getGradeName() %>
							<%} %>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>所属学科
						</td>
						<td>
							<s:checkboxlist list="subjectList" id="subjectId"
								name="subjectId" listKey="subjectId" listValue="subjectName"></s:checkboxlist>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>所属班级
						</td>
						<td>
							<!--<s:checkboxlist list="classTList" id="classId" name="classId"
								listKey="classId" listValue="className"></s:checkboxlist>-->
							<% List classList = (List)request.getAttribute("classTList"); 
								int i = 0;
								for(Iterator iter = classList.iterator(); iter.hasNext();) {
									ClassT classT = (ClassT)iter.next();{
										 if(i != 0) {
											if(i != classT.getGradeId()) {%>
												<br/>
											<%}}}%>
									
							
							<input type="checkbox" id="classId" name="classId" value="<%=classT.getClassId() %>"/><%=classT.getClassName() %>
								<% i=classT.getGradeId();}%>
						</td>
					</tr>
					<tr>
						<td>
							联系电话
						</td>
						<td>
							<input type="text" name="user.tel" id="user.tel" />
						</td>
					</tr>
					<tr>
						<td>
							e_mail
						</td>
						<td>
							<input type="text" name="user.email" id="user.email" />
						</td>
					</tr>
					<tr>
						<td>
							性别
						</td>
						<td>
							<input type="radio" name="user.userTypeId" value="1">
								男 
							<input type="radio" name="user.userTypeId" value="0">
								女 
						</td>
					</tr>
					<tr>
						<td>
							家长姓名
						</td>
						<td>
							<input type="text" name="user.pname" id="user.pname" />
						</td>
					</tr>
					<tr>
						<td>
							地址
						</td>
						<td>
							<input type="text" name="user.address" id="user.address" />
						</td>
					</tr>
					<tr>
						<td>
							邮编
						</td>
						<td>
							<input type="text" name="user.zip" id="user.zip" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<s:hidden name="user.groupId" id="user.groupId"></s:hidden>
							<s:hidden name="user.userType" id="user.userType"
								value="%{userType}"></s:hidden>
							<s:submit value="提交" />
							&nbsp;
							<input type="button" value="返回"
								onclick="document.location.href='<%=contextPath%>/sys/user!listAllUserForStu?queryUserCondition.currentPage=1&queryUserCondition.userType=0'" />
						</td>
					</tr>
				</table>
			</s:form> 
		</div>
	</body>
</html>