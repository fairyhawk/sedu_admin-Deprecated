<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加课程节点</title>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
	<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />

    <script type="text/javascript">
    
		$().ready(function() {
			$("#kpointform").validate({   
		        rules: {   
		            fSortId: {
		            	required:true,
		            	min: 0
		            },
		            courseId: {
		            	required:true,
		            	min: 0
		            },
		            courseId2: {
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
				    },
				    courseId2: {
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
					success:onchangeFirstcallback  
					});
				
			} 
			
			
				   		
			function onchangeFirstcallback(result){   
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
			
			function onchangecallbackcourse(result){ //处理返回的课程JSON  
				
				document.getElementById('courseId').options.length = 0;  //清空原有的option
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				
				$("#courseId").html(str); 
	   		}   
					
			function onchangecallback2(result){   
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#tSortId").html(str);  
			 } 
			
			function onSelectCourse(courseid){ 
				document.getElementById('tjcourse.courseId').value = courseid;
			}
			
			function onchangeFirstSort2(pId){ 
				
				$.ajax({  
					url : "<%=contextPath%>/cou/coursesort!getChildSortById.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangeFirstcallback2  
					});
				
			} 
			
			function onchangeFirstcallback2(result){   
				document.getElementById('sSortId2').options.length = 0;  //清空原有的option 
				document.getElementById('tSortId2').options.length = 0;  //清空原有的option 
				$("#tSortId2").html("<option value=-1>请选择</option>");
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#sSortId2").html(str);  
			 }  
			
			function onchangeSecond2(pId){
				
					$.ajax({  
					url : "<%=contextPath%>/cou/coursesort!getChildSortById.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback22  
					});  
			} 
			
			function onchangeThird2(pId){
				$.ajax({  
					url : "<%=contextPath%>/cou/course!getCourseBySortId.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackcourse2  
					});
			} 
			
			function onchangecallbackcourse2(result){ //处理返回的课程JSON  
				
				document.getElementById('courseId2').options.length = 0;  //清空原有的option
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				
				$("#courseId2").html(str); 
	   		}   
					
			function onchangecallback22(result){   
				document.getElementById('tSortId2').options.length = 0;  //清空原有的option 
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#tSortId2").html(str);  
			 } 
			
			function onSelectCourse2(courseid){ 
				document.getElementById('tjcourse.tjcourseId').value = courseid;
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
			
	</script>
  </head>
  
  <body>
<form action="<%=contextPath%>/cou/tjcourse!addTjCourse.action"  method="post" name="kpointform" id="kpointform">
<div>
	
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">选择原课程</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
		    		<td align="right" width="20%">选择课程分类：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
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
		    		<td align="right" width="20%">选择课程：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			<s:select name="courseId" id="courseId" list="#{}" headerKey="-1"
									headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">
						</s:select>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">购买方法：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			
						<input type="text" name="tjcourse.buyMethod" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">图片路径：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			
						<input type="text" name="tjcourse.picPath" />
		    		</td>
		    	</tr>
		    	
		     	<tr>
		    		<td align="right" width="20%">TITLE：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			
						<input type="text" name="tjcourse.title" />
		    		</td>
		    	</tr>
		    
		    	<tr>
		    		<td align="right" width="20%">INFO：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			
						<input type="text" name="tjcourse.info"/>
		    		</td>
		    	</tr>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td>
				<font class="lists_fleft">选择要推荐的课程</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
		    		<td align="right" width="20%">选择课程分类：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			<s:select name="fSortId2" id="fSortId2" list="courseSortList"
									listKey="coursesortId" listValue="coursesortName" 
									theme="simple" onchange="onchangeFirstSort2(this.value);">
						</s:select>
								
						<s:select name="sSortId2" id="sSortId2" list="#{}" headerKey="-1"
									headerValue="请选择" theme="simple" onchange="onchangeSecond2(this.value);">
						</s:select>
								
						<s:select name="tSortId2" id="tSortId2" list="#{}" headerKey="-1"
									headerValue="请选择" theme="simple" onchange="onchangeThird2(this.value);">
						</s:select>
		    		</td>
		    	</tr>
		    	
		    	<tr>
		    		<td align="right" width="20%">选择课程：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			<s:select name="courseId2" id="courseId2" list="#{}" headerKey="-1"
									headerValue="请选择" theme="simple" onchange="onSelectCourse2(this.value)">
						</s:select>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">图片路径：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			<input type="text" name="tjcourse.tjPicPath" /> 
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">购买方法：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			<input type="text" name="tjcourse.tjBuyMethod"  /> 
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">点击率：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			<input type="text" name="#tjcourse.tjClickTimes"  /> 
		    		</td>
		    	</tr>
		    	
		    	<tr>
		    		<td align="right" width="20%">URL：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			<input type="text" name="tjcourse.tjURL"  /> 
		    		</td>
		    	</tr>	
		    	<tr>
		    		<td align="right" width="20%">TITLE：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			<input type="text" name="tjcourse.tjTitle"  /> 
		    		</td>
		    	</tr>	
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
				<s:hidden name="tjcourse.courseId" id="tjcourse.courseId"></s:hidden>
	    		<s:hidden name="tjcourse.tjcourseId" id="tjcourse.tjcourseId"></s:hidden>
	    		<input class="submit" type="submit" value="提交" style="width:100px;"/>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	</form>
</div>
  </body>
</html>
