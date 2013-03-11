<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>选择课程分类</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<link href="<%=contextPath%>/back/style/data_table.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript">
		$().ready(function() {
			$("#prekpointform").validate({   
		        rules: {   
		            fSortId: {
		            	required:true,
		            	min: 0
		            }
		        },   
		        messages: {   
		            fSortId: {
				            	required:"请选择分类",
				            	min: "请选择分类"
				            }
		        }   
   	 		});  
		})	
			
		function onchangeFirstSort(pId){ 
				if(pId != -1){
					document.getElementById('course.sortId').value = pId;
				}
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
					if(pId != -1){
						document.getElementById('course.sortId').value = pId;
					}
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
					
			} 
			
			function onchangeThird(pId){
				if(pId != -1){
					document.getElementById('course.sortId').value = pId;
				}
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
			

		</script>
	</head>
	<body>
		<div id="rightframe">
			<div class="rf_title">
				<h2>
					选择课程所属分类
				</h2>
			</div>
			<form name="prekpointform" id="prekpointform" action="<%=contextPath %>/cou/course!listCoursesBySortId.action?queryCourseCondition.currentPage=1" method="post">
				<table class="com_table" border="0" cellpadding="0" cellspacing="1"
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
						<td colspan="2">
							<input type="hidden"  name="course.sortId" id="course.sortId" value='<s:property value="course.sortId" />'/>
							<s:submit value="提交"  />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>