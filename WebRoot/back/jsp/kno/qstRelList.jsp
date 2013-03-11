<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/public.css" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/index.css" />
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
	<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/attach.js" /></script>    
	<script type="text/javascript">
		//删除试题关联
    	function delRel(qrId){
    	alert(qrId);
			var s=window.confirm("知识树的节点试题关联删除后无法恢复，确认删除？");
	 		if(s){
	 		$.ajax({
									url : "<%=contextPath%>/kno/qstRel!delQstRel.action",  
									data : {"qrId" : qrId},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									if(bo){
									alert("删除成功");
									}
									document.forms["qstRelList"].submit(); 
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
  <form name="qstRelList" action="<%=contextPath %>/kno/qstRel!toQstRelList.action?ksnId=<s:property value="ksnId"/>" method="post">
	 <table class="treeouter" width="800" border="0" cellpadding="0" cellspacing="0">
  <tr style="margin:0; padding:0">
  <td valign="top" style="border:none; text-align:left">
  <div class="cont_outer">
            	<div class="jiegou_tab mt20">
                		<div class="tab_outer f14">
                        		<a class="tablink width95"  href='<%=contextPath %>/kno/knowledgeSystem!knowledgeTreeZi.action?ksnId=<s:property value="ksnId"/>'>结构</a>
                                <a class="tablink width95 ml2"  href='<%=contextPath %>/kno/resolve!resolveList.action?ksnId=<s:property value="ksnId"/>'>精讲解析</a>
                                <a class="tablink width95 ml2" id="superlink95" href='#'>关联试题</a>
                                <a class="tablink width130 ml2"  href='<%=contextPath %>/kno/videoRel!toVideoRelList.action?ksnId=<s:property value="ksnId"/>'>关联课程视频</a>
                       			<a class="tablink width95 ml2" href="#">课程讲义</a>
                        </div>
                </div>
  </div>
   <div class="mianbao"><em class="flag dib fl mr5"></em>
  			<s:iterator value="sysNodeList" id="s" >
			<a href='<%=contextPath %>/kno/qstRel!toQstRelList.action?ksnId=<s:property value="#s.ksnId"/>'><s:property value="#s.nodeName"/><span class="ml5 mr5">&gt;</span></a>
			</s:iterator>
   </div>
	  </tr>
	</table>
	
		 <table cellpadding="0" cellspacing="0" >
		 <thead>
        	<tr align="center">
            	<th width="20%">编号</th>
                <th width="20%">题型</th>
                <th width="20%">题目</th>
                <th width="30%">操作</th>
            </tr>
        </thead>  	
			<s:iterator value="qstRelList" id="qstRel">
			<tr>
			<td><s:property value="#qstRel.nodeId"/></td>
			<td>
			<s:if test="#qstRel.qstType==1">单选题</s:if>
			<s:if test="#qstRel.qstType==2">多选题</s:if>
			<s:if test="#qstRel.qstType==3">判断题</s:if>
			<s:if test="#qstRel.qstType==4">材料分析题</s:if>
			<s:if test="#qstRel.qstType==5">图表题</s:if>
			<s:if test="#qstRel.qstType==6">简答题</s:if>
			</td>
			<td><s:property value="#qstRel.qstContent"/></td>
			<td><a class='mr20' href='<%=contextPath %>/kno/qstRel!toQstPreview.action?qstId=<s:property value="#qstRel.qstId"/>&ksnId=<s:property value="ksnId"/>'>预览试题</a><a href="#" onclick="delRel(<s:property value="#qstRel.qrId"/>);">/删除关联</a></td>
			</tr>
			</s:iterator>
		</table>
	</form>
  </body>
</html>
