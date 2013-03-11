<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>删除课程分类</title>
		<script type="text/javascript"	src="<%=contextPath%>/script/check_dtree.js"></script>
		
		<link href="<%=contextPath%>/style/data_table.css" rel="stylesheet"	type="text/css" />
		<link rel="StyleSheet" href="<%=contextPath%>/style/dtree.css"	type="text/css" />
		<script type="text/javascript">
			function submitForm(){
			var ids = "";
			var form = document.getElementById("testForm");
			for (var i=0; i<form.elements.length; i++) {
				var element = form.elements[i];
				if (element.name == "id" && element.type=='checkbox'){
					if( element.checked == true ){
						ids = ids + element.value + ",";
					}
				}
				}		
			    document.getElementById("sortIds").value=ids; 
			}
		</script>
	</head>
	<body>
	<div id="rightframe">
	  	<div class="rf_title">
				<h2>
					删除课程分类
				</h2>
		</div>
		<div class="dtree"
				style="border: 0px solid #cccccc; background-color: #f3f3f3; padding: 5px; width: 100%;">
				<p>
					<a href="javascript: d.openAll();">打开所有</a> |
					<a href="javascript: d.closeAll();">关闭所有</a>
				</p>
				<script type="text/javascript">
				d = new dTree('d','<%=contextPath%>/images','testForm');
				d.add(0,-1,'课程分类');
				<s:iterator value="sortList">
				d.add(<s:property value="coursesortId"/>,<s:property value="pId"/>,'<s:property value="coursesortName"/>','','','');
				</s:iterator>
				document.write(d);
				</script>
				<form action="<%=contextPath %>/cou/coursesort!deleteCourseSorts.action" method="post" onsubmit="submitForm()">
				<input type="hidden" name="sortIds" id="sortIds"/>
				<input type="submit" value="提 交"/>
				</form>
			</div>
	  </div>
	</body>
</html>