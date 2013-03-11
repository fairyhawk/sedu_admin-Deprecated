<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <title>My JSP 'knowledgeTree.jsp' starting page</title>
	    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/public.css" />
	    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/index.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
		<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<script type="text/javascript">
			function setKsnId(ksnId){
				//调用父窗口中的函数
				
				
				/**
					根据ksnId查询数据，并且将值放入父页面中格式：[en:title>>title>> 删除]
				*/
				
				var actionUrl = "<%=contextPath%>/feed/course!getKnowNodes.action";
				window.parent.setKnowTree(ksnId,actionUrl);
			}
		</script>
	</head>
	<body>
	
               				<script type="text/javascript">
										d = new dTree('d','<%=contextPath%>/back/images/dtree');
										var flag = true;
										<s:iterator value="sysTree">
											if(${parentId}==-1){
												d.add(<s:property value="sysNode.ksnId"/>,-1,"<a href='javascript:setKsnId(\"${sysNode.ksnId}\");'>项目:<s:property value="sys.subjectName" escape="false"/></a>");
											}else{
												d.add(${ksnId},${parentId},"<a href='javascript:setKsnId(\"${ksnId}\");'>${nodeName}</a>");
											}
										</s:iterator>
										document.write(d);
							</script>
	
			
  </body>
</html>

