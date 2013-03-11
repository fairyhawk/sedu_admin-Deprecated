<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加课程节点</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
	
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	
	<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
    <script type="text/javascript">
		$().ready(function() {
			$("#kpointupdateform").validate({   
		        rules: {   
		            fSortId: {
		            	required:true,
		            	min: 0
		            },
		            courseId: {
		            	required:true,
		            	min: 0
		            }
		        },   
		        messages: {   
		            fSortId: {
				            	required:"请选择分类",
				            	min: "请选择分类"
				    },
				    courseId: {
				            	required:"请选择课程",
				            	min: "请选择课程"
				    }
		        }   
   	 		});  
		})	
		
		function changeColumnValue(id,name){
			document.getElementById("kpoint.pId").value = id;
			document.getElementById("pName").value = name;
			document.getElementById(obj).style.display="none";
		}
		function showPkpoint(){
			obj="kpointList";
			document.getElementById(obj).style.display="block";
		}
		function closeColumn(){
			obj="kpointList";
			document.getElementById(obj).style.display="none";
		}
		
	</script>
	<style>
	<!--
		input{
			width:200px;
		}
		textarea{
			width:200px;
		}
	-->
	</style>
  </head>
  
  <body class="manage">
  
  <div class="main_right">
  	
    	<h1>修改课程节点</h1>
	
		
    <form action="<%=contextPath%>/cou/kpoint!updateKpoint.action"  method="post" name="kpointupdateform" id="kpointupdateform">
	    <table class="tables" align="center" border="0" cellpadding="0" cellspacing="1" style=" width:90%;">
	    	<tr>
	    		<th align="right">上级课程节点：</th>
	    		<td style="color: #ff0000;"> 
	    			<input id="kpoint.pId" type="hidden" name="kpoint.pId"  />
	    			<input id="pName" type="text" name="pName" onclick="showPkpoint();" readonly="readonly" />
	    			<div id="kpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
					    <script type="text/javascript">
						<!--
						addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
						addkpoint.add(0,-1,'栏目列表 <a href="javascript:closeColumn();">关闭</a>');
						<s:iterator value="kpointList">
						addkpoint.add('<s:property value="pointId"/>','<s:property value="pId"/>','<s:property value="name"/>','javascript:changeColumnValue(\'<s:property value="pointId"/>\',\'<s:property value="name"/>\')','<s:property value="explanation"/>');
						</s:iterator>
						document.write(addkpoint);
						//-->
						</script>
				    </div>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th align="right" width="20%">课程节点名称：</th>
	    		<td width="80%" style="color: #ff0000;">
	    		<input type="text" name="kpoint.name" class="{required:true,minlength:4,maxlength:200}" value="<s:property value="kpoint.name"/>" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th align="right" width="20%">课程节点简介：</th>
	    		<td width="80%">
	    			<textarea name="kpoint.introduce" class="{required:true,minlength:4,maxlength:1000}" style="height:80px;width:99%;"><s:property value="kpoint.introduce"/></textarea>
				</td>
	    	</tr>
	    	
	    	<s:if test="isLeaf==1">
	    	<tr>
	    		<th align="right" width="20%">是否叶子节点：</th>
	    		<td width="80%">
	    			<div style="color:#000000;">
	    				<s:if test="kpoint.leaf == 1">
	    				<input type="radio" name="kpoint.leaf" value="1" style="width:20px;" checked/>是
	    				
	    				</s:if>
	    				<s:else>
							<input type="radio" name="kpoint.leaf" value="1" style="width:20px;"/>是
						</s:else>
	    				<s:if test="kpoint.leaf == 0">
	    					<input type="radio" name="kpoint.leaf" value="0" style="width:20px;" checked />否
	    				</s:if>
	    				<s:else>
							<input type="radio" name="kpoint.leaf" value="0" style="width:20px;"/>否
						</s:else>
	    			</div>
	    		</td>
	    	</tr>
	    	</s:if>
	    	<tr>
	    		<th align="right" width="20%">排序：</th>
	    		<td width="80%"> 
	    		
	    		<input type="text" name="kpoint.sort" class="{required:true,digits:true,min:0,maxlength:10}" value="<s:property value="kpoint.sort"/>"/>
	    		</td>
	    	</tr>
	    	<s:hidden name="searchKey" id="searchKey"></s:hidden>
	    	<s:hidden name="kpoint.pointId" id="kpoint.pointId"></s:hidden>
	    	<s:hidden name="kpoint.courseId" id="kpoint.courseId"></s:hidden>
	    	<tr>
	    		<td></td>
	    		<td align="left"><input class="submit" type="submit" value="提交" style="width:100px;"/></td>
	    	</tr>
	    </table>
	  </form>
  </div>
  </body>
</html>
