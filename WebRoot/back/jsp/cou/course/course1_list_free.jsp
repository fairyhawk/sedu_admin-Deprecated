<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程列表</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/right.css" />

		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		function deleteAll(ObjectForm){
			var i = 0;
			var f=document.courselist;
			if(f.courseIds.length!=null){
				for(var j=0;j<f.courseIds.length;j++){
			
					if(f.courseIds[j].checked == true){
					 i++;
					 }
				}
				}else
				{
					if(f.courseIds.checked == true)
					{
						i++;
					}		
				}
				if(i==0){
					alert("没有选择需要删除的选项！");
					return false;
				}
			if(confirm("是否赠送？")){
				ObjectForm.action="<%=contextPath%>/cou/course!freeAdmin1.action";
				ObjectForm.submit();
			}
		}
		  function All(e){
		    var f=document.getElementById("courselist");
		    for(i=0;i<f.elements.length;i++){
			 if(f.elements[i].name=="courseIds"){
			     f.elements[i].checked=e.checked;}
			 }
		}	
		</script>
	</head>
	<body>
		<div class="main_right">
			<h1>
				内部课程列表
			</h1>
			<form name="courselist" id="courselist"  method="post" action="books!getBooksList.action">
				<table class="tables" onmouseover="changeto()" onmouseout="changeback()">
				<tr><td colspan="10"><input type="button" name="button1" value="多个课程赠送" onclick="deleteAll(document.courselist);"></td></tr>
					<input type="hidden" name="cusId" value="<s:property value="cusId"/>">
					<tr>
						<td>
							<input type="checkbox" name="c1" onclick="All(this);" />
						</td>
						<td>
							课程名
						</td>
						<td>
							课程价格
						</td>

						<td>
							点击量
						</td>

						<td>
							购买量
						</td>

						<td>
							退购量
						</td>
						<td>
							收藏量
						</td>

						<td>
							课程状态
						</td>

						<td>
							添加时间
						</td>

						<!-- <td>
							操作
						</td> -->
					</tr>
					<s:iterator value="course1List" id="course">
						<tr>
							<td>
								<input type="checkbox" name="courseIds" value='<s:property value="#course.courseId"/>' />
							</td>
							<td>
								<s:property value="#course.title" />
							</td>
							<td>
								---
							</td>

							<td>
								---
							</td>

							<td>
								---
							</td>

							<td>
								---
							</td>

							<td>
								---
							</td>

							<td>
							---
						
							</td>

							<td>
								<s:date name="#course.addtime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<!--  <td>
							<a href="<%=contextPath%>/cou/course!freeAdmin.action?course.courseId=<s:property value="courseId"/>&cusId=<s:property value="cusId"/>">修复并赠送</a>

							</td>-->
						</tr>
					</s:iterator>
				</table>
			</form>
		</div>
	</body>
</html>