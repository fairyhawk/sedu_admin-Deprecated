<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
    <title>My JSP 'sysNodeAdd.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/public.css" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/index.css" />
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
	<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/attach.js" /></script>
	<script type="text/javascript">
		
		function goToListenCourse(courseId,subjectIdweb){
			if(courseId && subjectIdweb){
				window.open ('<%=contextPath%>/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId + "&course.subjectId=" + subjectIdweb + "&veri=1"  , "_blank", "resizable");
			}
		}
		
		
	
    	function delRel(vrId){
			var s=window.confirm("知识树的节点视频关联删除后无法恢复，确认删除？");
	 		if(s){
	 		$.ajax({
									url : "<%=contextPath%>/kno/videoRel!delVideoRel.action",  
									data : {"vrId" : vrId},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									if(bo){
									alert("删除成功");
									}
									document.forms["videoRelList"].submit(); 
									},
									error: function(){ 
										alert('error');  
									}
				  });
			}else{
				alert("取消成功");
			}
		}
    </script>
  </head>
  
  <body>
  <form name="videoRelList" action='<%=contextPath %>/kno/videoRel!toVideoRelList.action?ksnId=<s:property value="ksnId"/>' method="post" target="_self">
    <table class="treeouter" width="800" border="0" cellpadding="0" cellspacing="0">
  <tr style="margin:0; padding:0">
  <td valign="top" style="border:none; text-align:left">
  <div class="cont_outer">
            	<div class="jiegou_tab mt20">
                		<div class="tab_outer f14">
                        		<a class="tablink width95"  href='<%=contextPath %>/kno/knowledgeSystem!knowledgeTreeZi.action?ksnId=<s:property value="ksnId"/>'>结构</a>
                                <a class="tablink width95 ml2"  href='<%=contextPath %>/kno/resolve!resolveList.action?ksnId=<s:property value="ksnId"/>'>精讲解析</a>
                                <a class="tablink width95 ml2" href='<%=contextPath %>/kno/qstRel!toQstRelList.action?ksnId=<s:property value="ksnId"/>'>关联试题</a>
                                <a class="tablink width130 ml2" id="superlink95" href='#'>关联课程视频</a>
                        		<a class="tablink width95 ml2" href="#">课程讲义</a>
                        </div>
                </div>
  </div>
   <div class="mianbao"><em class="flag dib fl mr5"></em>
  			<s:iterator value="sysNodeList" id="s" >
			<a href='<%=contextPath %>/kno/videoRel!toVideoRelList.action?ksnId=<s:property value="#s.ksnId"/>'><s:property value="#s.nodeName"/><span class="ml5 mr5">&gt;</span></a>
			</s:iterator>
   </div>
	  </tr>
	</table>
	
		<div class="kno_tree_title mt14 f14 fw">关联视频列表</div>
		 <table cellpadding="0" cellspacing="0" >
		 <thead>
        	<tr align="center">
            	<th width="20%">商品名称</th>
                <th width="20%">课程名称</th>
                <th width="20%">视频名称</th>
                <th width="30%">操作</th>
            </tr>
        </thead>
        
            <s:iterator value="videoRelList" id="videoRel">
            <tr>
            <td><s:property value="#videoRel.nodeId"/></td>
            <td><s:property value="#videoRel.title"/></td>
            <td><s:property value="#videoRel.courseName"/></td>
            <td><s:property value="#videoRel.voName"/></td>
            <td><a class='mr20' href='<%=contextPath %>/kno/videoRel!videoPreview.action?voId=<s:property value="#videoRel.voId"/>&ksnId=<s:property value="ksnId"/>'>查看视频</a><a  href="#" onclick="delRel(<s:property value="#videoRel.vrId"/>);">/删除关联</a></td>
            </tr>
            </s:iterator>
		</table>
       </form>
  </body>
</html>
