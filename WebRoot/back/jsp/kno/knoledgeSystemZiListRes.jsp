<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>knoledgeSystemZiList.html</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/public.css" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/index.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
	<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/attach.js" /></script>
	<script type="text/javascript">
	//新建节点的子节点
	function newSysNode(ksnId){
		document.forms["sysNodeList"].action='<%=contextPath %>/kno/knowledgeSystem!toAddSysNode.action?ksnId='+ksnId
		document.forms["sysNodeList"].submit();
	}
	//删除知识树节点
		function delSysNode(ksnId){
	 		var s=window.confirm("知识树节点删除后无法恢复，确认删除？");
	 		if(s){
	 		$.ajax({
									url : "<%=contextPath%>/kno/knowledgeSystem!delSysNode.action",  
									data : {"ksnId" : ksnId},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									if(bo){
										alert("删除成功");
										document.forms["sysNodeList"].action='knowledgeSystem!toKnowledgeTree.action?ksId=<s:property value="sysNode.ksId"/>&ksnId=<s:property value="ksnId"/>';
										document.forms["sysNodeList"].target="rightFrame"
										document.forms["sysNodeList"].submit();    
									}else{
										alert("该节点仍有子节点，不能删除");
									}
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
  <form action='<%=contextPath %>/kno/knowledgeSystem!toAddSysNode.action?ksnId=<s:property value="sysNode.ksnId"/>' name="sysNodeList" method="post" target="ziname">
  <table class="treeouter" width="800" border="0" cellpadding="0" cellspacing="0">
  <tr style="margin:0; padding:0">
  <td valign="top" style="border:none; text-align:left">
  <div class="cont_outer">
            	<div class="jiegou_tab mt20">
                		<div class="tab_outer f14">
                        		<a class="tablink width95" id="superlink95" href="#">结构</a>
                                <a class="tablink width95 ml2"  href='<%=contextPath %>/kno/resolve!resolveListRes.action?ksnId=<s:property value="ksnId"/>'>精讲解析</a>
                                <a class="tablink width95 ml2" href='#'>关联试题</a>
                                <a class="tablink width130 ml2" href='#'>关联课程视频</a>
                                <a class="tablink width95 ml2" href="#">课程讲义</a>
                        </div>
                </div>
  </div>
   <div class="mianbao"><em class="flag dib fl mr5"></em>
  			<s:iterator value="sysNodeList2" id="s" >
			<a href='<%=contextPath %>/kno/knowledgeSystem!knowledgeTreeZiRes.action?ksnId=<s:property value="#s.ksnId"/>'><s:property value="#s.nodeName"/><span class="ml5 mr5">&gt;</span></a>
			</s:iterator>
   </div>
   </table>
        <div class="kno_tree_title mt14 f14 fw">子节点列表</div>
 <table cellpadding="0" cellspacing="0" >
        <thead>
        	<tr align="center">
            	<th width="20%">ID</th>
                <th width="20%">名称</th>
                <th width="20%">预设项</th>
            </tr>
        </thead>

  
              <s:iterator value="sysNodeList" id="sysNodeList">
                <tr>
                  <td><s:property value="#sysNodeList.nodeId"/></td>
                  <td><s:property value="#sysNodeList.nodeName"/></td>
                  <td><s:property value="#sysNodeList.preNodeName"/>&nbsp;</td>
                </tr>
              </s:iterator>
       
  </table>
  
  </form>
  </body>
</html>
