<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
			function resetKey() {
				$("#gradeId").val(0);
				$("#subjectId").val(0);
			}
			
			function allCheck(cb) {
				$("input[name=ids]:checkbox").attr('checked', cb.checked);
			}
			
			function subOnChange(subjectId) {
				$.ajax({  
					url : "<%=contextPath%>/cusmgr/cusmgr!getGradeListBySubjectId.action",  
					data : {'queryCusMgrCondition.subjectId' : subjectId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success : function (result) {
						var GradeList = result.entity;
						document.getElementById('gradeId').length = 1;
						var str=$("#gradeId").html();  
						for(var i=0;i<result.entity.length;i++){  
							str+="<option value='"+result.entity[i].gradeId+"'>"+result.entity[i].gradeName+"</option>"  
						}
						$("#gradeId").html(str);
					}
				});
			}
		</script>
	</head>
	<body>
		<div class="main_right">
				<h1>
					学员列表
				</h1>
				<form name="searchForm" action="<%=contextPath%>/cusmgr/cusmgr!showCustomerList.action" method="post">
					<s:hidden name="queryCusMgrCondition.currentPage" value="1"/>
					<table cellpadding="0" cellspacing="1" class="tables">
						<tr>
							<td width="8%">专业</td>
							<td width="12%">
								<select name="queryCusMgrCondition.subjectId" id="subjectId" onchange="subOnChange(this.value)">
									<option value="0">--请选择--</option>
									<s:iterator value="subjectSet" id="subject">
										<option value="<s:property value='subjectId'/>" <s:property value="subjectId==queryCusMgrCondition.subjectId?'selected':''"/>>
											<s:property value="#subject.subjectName"/>
										</option>
									</s:iterator>
								</select>
							</td>
							<td width="10%">年份</td>
							<td width="15">
								<select name="queryCusMgrCondition.gradeId" id="gradeId">
									<option value="0">--请选择--</option>
									<s:iterator value="gradeList" id="grade">
										<option value="<s:property value='gradeId'/>" <s:property value="gradeId==queryCusMgrCondition.gradeId?'selected':''"/>>
											<s:property value="#grade.gradeName"/>
										</option>
									</s:iterator>
								</select>
							</td>
							<td>
								<input type="submit" value="查询"/>
								<input type="button" value="重置" onclick="resetKey()"/>
							</td>
						</tr>
					</table>
				</form>
				<form name="cusForm" method="post">
					<table cellpadding="0" cellspacing="1" class="tables" onmouseover="changeto()" onmouseout="changeback()">
							<tr>
								<td width="10%"><input type="checkbox" onclick="allCheck(this)"/>全选</td>
								<td width="15%"><font style="font-weight:bold">用户名</font></td>
								<td width="13%"><font style="font-weight:bold">电子邮件</font></td>
								<td width="15%"><font style="font-weight:bold">手机号码</font></td>
								<td width="12%"><font style="font-weight:bold">真实姓名</font></td>
								<td width="5%"><font style="font-weight:bold">性别</font></td>
								<td width="13%"><font style="font-weight:bold">身份证号</font></td>
								<td width="15%"><font style="font-weight:bold">操作</font></td>
							</tr>
						<s:iterator value="page.pageResult" id="customer" status="status">
							<tr>
								<td><input type="checkbox" name="ids" value=<s:property value="#customer.cusId"/> /></td>
								<td>
									<s:property value="cusName" />
								</td>
								<td>
									<s:property value="email" />
								</td>
								<td>
									<s:property value="mobile" />
								</td>
								<td>
									<s:property value="realName" />
								</td>
								<td>
									<s:if test="sex==1">
										男
									</s:if>
									<s:else>
										女
									</s:else>
								</td>
								<td>
									<s:property value="idNum" />
								</td>
								<td>
									<a href="<%=contextPath%>/cusmgr/cusmgr!viewCustomer.action?queryCusMgrCondition.cusId=<s:property value="cusId" />">查看</a>
								</td>
							</tr>
						</s:iterator>
							<tr>
								<td colspan="8">
									<jsp:include page="/back/jsp/common/showPage.jsp" />
								</td>
							</tr>
					</table>
				</form>
		</div>
	</body>
</html>