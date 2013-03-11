<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程节点</title>
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
			$("#courseselectform").validate({   
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
		
		function onchangeFirstSort(pId){ 
				
				$.ajax({  
					url : "<%=contextPath%>/cou/coursesort!getChildSortById.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback  
					});
				
				$.ajax({  
					url : "<%=contextPath%>/cou/course!getCourseBySortId.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackcourse  
					});
				
			} 
			
			function onchangecallbackcourse(result){ //处理返回的课程JSON  
				
				document.getElementById('courseId').options.length = 0;  //清空原有的option
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				
				$("#courseId").html(str); 
	   		}  
				   		
			function onchangecallback(result){   
					//alert(data);		
				document.getElementById('sSortId').options.length = 0;  //清空原有的option 
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				$("#tSortId").html("<option value=-1>请选择</option>");
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#sSortId").html(str);  
			 }   
			
			function onchangeSecond(pId){
				
					$.ajax({  
					url : "<%=contextPath%>/cou/coursesort!getChildSortById.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback2  
					});  
					
					$.ajax({  
					url : "<%=contextPath%>/cou/course!getCourseBySortId.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackcourse  
					});
			} 
			
			
			function onchangeThird(pId){
				$.ajax({  
					url : "<%=contextPath%>/cou/course!getCourseBySortId.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackcourse  
					});
				
			} 
					
			function onchangecallback2(result){   
					//alert(data);		
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#tSortId").html(str);  
			 } 
			
			function onSelectCourse(courseid){ 
				document.getElementById('kpoint.courseId').value = courseid;
			}
			
			function checkSubmit(){
				var fid = document.getElementById('kPoint.courseId').value;
				
				if(fid == null || fid == -1 || fid == 0){
					document.getElementById('course.sortId').value=0;
					alert(请选择课程);
					return false;
				}
				return true;
			}
			
			function changeColumnValue(id,name,level){
				//alert(level);
				
				document.getElementById("kpoint.level").value = Number(level) + 1;
				
				alert(document.getElementById("kpoint.level").value);
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
	</head>
	<body>
	<div class="main_right">
				<h1>
					选择课程节点所属课程
				</h1>
			
			<form name="courseselectform" id="courseselectform" action="<%=contextPath %>/cou/kpoint!toUpdateKpointSort.action" method="post" onsubmit="checkSubmit();">
				<table class="tables" border="0" cellpadding="0" cellspacing="1"
					style="width: 600px;">
					<tr>
						<th>
							选择课程分类：
						</th>
						<td>
							<s:select name="fSortId" id="fSortId" list="courseSortList"
								listKey="coursesortId" listValue="coursesortName" 
								theme="simple" onchange="onchangeFirstSort(this.value);">
							</s:select>
							
							<s:select name="sSortId" id="sSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onchangeSecond(this.value);">
							</s:select>
							
							<s:select name="tSortId" id="tSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onchangeThird(this.value);">
							</s:select>
						</td>
					</tr>
					<tr>
						<th>
							选择课程：
						</th>
						<td>
							<s:select name="courseId" id="courseId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">
							</s:select>
						</td>
					</tr>
					
					
					<tr>
						<td colspan="2">
							<s:hidden name="kpoint.courseId" id="kpoint.courseId"></s:hidden>
							<s:submit value="查询"  />
						</td>
					</tr>
				</table>
			</form>
			<h2>
				课程树排序
			</h2>
	
			
			
			<table class="tables">
				<tr>
					<td>
						课程树
					</td>
					<td>
						课程节点修改
					</td>
					
				</tr>
				
				<tr>
					<td valign="top">
				<s:if test="kpointList != null">
					<script type="text/javascript">
						addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
						addkpoint.add(0,-1,'课程树列表');
						<s:iterator value="kpointList">
							addkpoint.add(<s:property value="pointId"/> ,<s:property value="pId"/> ,'<s:property value="name"/>' + " 排序号:" + <s:property value="sort"/>,'<%=contextPath %>/cou/kpoint!toUpdateKpointSort.action?kpoint.courseId=<s:property value="courseId"/>&kpoint.pointId=<s:property value="pointId"/>');
						</s:iterator>
						document.write(addkpoint);
					</script>
				</s:if>
					</td>
					<td>
		<form action="<%=contextPath%>/cou/kpoint!updateKpoint.action"  method="post" name="kpointupdateform" id="kpointupdateform">
	    <table class="tables" align="center" border="0" cellpadding="0" cellspacing="1" style=" width:90%;">
	    	<tr>
	    		<th align="right">上级课程节点：</th>
	    		<td style="color: #ff0000;"> 
	    			
	    			<input id="pName" type="text" name="pName" onclick="showPkpoint();" readonly="readonly" />
	    			<div id="kpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
					    <script type="text/javascript">
						<!--
						addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
						addkpoint.add(0,-1,'栏目列表 <a href="javascript:closeColumn();">关闭</a>');
						<s:iterator value="kpointList">
						addkpoint.add('<s:property value="pointId"/>','<s:property value="pId"/>','<s:property value="name"/>','javascript:changeColumnValue(\'<s:property value="pointId"/>\',\'<s:property value="name"/>\',\'<s:property value="level"/>\')','<s:property value="explanation"/>');
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
	    
	    	<s:hidden name="kpoint.pId" id="kpoint.pId"></s:hidden>
	    	<s:hidden name="kpoint.pointId" id="kpoint.pointId"></s:hidden>
	    	<s:hidden name="kpoint.courseId" id="kpoint.courseId"></s:hidden>
	    	<s:hidden name="kpoint.level" id="kpoint.level" value="1"></s:hidden>
	    	<tr>
	    		<td></td>
	    		<td align="left"><input class="submit" type="submit" value="提交" style="width:100px;"/></td>
	    	</tr>
	    </table>
	  </form>
					</td>
					
				</tr>
				
			</table>
		</div>
	</body>
</html>