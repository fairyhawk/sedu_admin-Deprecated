<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		
		function deleteAll(ObjectForm){
			var i = 0;
			var f=document.courselist;
			if(f.courseIds != null){
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
					alert("没有选择需要冻结的选项！");
					return false;
				}
				if(confirm("是否冻结？")){
					ObjectForm.action="<%=contextPath%>/cou/course!freezeAllCourse.action";
					ObjectForm.submit();
				}
			}
		 }
		 
		 $().ready(function() {
			$("#prekpointform").validate({   
		        rules: {   
		            fSortId: {
		            	required:true,
		            	min: 0
		            },
		            sSortId: {
		            	required:true,
		            	min: 0
		            },
		            tSortId: {
		            	required:true,
		            	min: 0
		            }
		        },   
		        messages: {   
		            fSortId: {
				            	required:"请选择分类",
				            	min: "请选择分类"
				            },
				   sSortId: {
				            	required:"请选择分类",
				            	min: "请选择分类"
				            },
				   tSortId: {
				            	required:"请选择分类",
				            	min: "请选择分类"
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
					//alert(data);		
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
			
       </script>
	</head>
	<body>	
<div>
	<form name="prekpointform" id="prekpointform" action="<%=contextPath %>/cou/tjcourse!listTjcourses.action" method="post">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">选择课程所属分类</font>
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
					<td>
						选择课程分类：
					</td>
					<td class="lists_tleft">
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
				<tr align="center">
					<td colspan="2">
						<s:hidden name="tjcourse.courseId" id="tjcourse.courseId"></s:hidden>
						<s:submit value="查询"  />
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
	</form>
</div>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td>
				<font class="lists_fleft">推荐课程列表[被推荐课程名称：<s:property value="course.title"/>]</font>
				<font class="lists_fright">
					<table class="lists_fleft" width="100" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="<%=contextPath%>/cou/tjcourse!toAddTjCourse.action">添加推荐课程</a></td></tr></table>
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
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
							添加时间
						</td>

						<td>
							操作
						</td>
					</tr>
					<s:iterator value="courseList" id="course">
						<tr>
							<td>
								<s:property value="#course.title" />
							</td>
							<td>
								<s:property value="#course.price" />
							</td>

							<td>
								<s:property value="#course.clickNum" />
							</td>

							<td>
								<s:property value="#course.gmNum" />
							</td>

							<td>
								<s:property value="#course.tgNum" />
							</td>

							<td>
								<s:property value="#course.scNum" />
							</td>

							<td>
								
								<s:date name="#course.addtime"  format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<a href='<%=contextPath%>/cou/tjcourse!deleteTjCourse.action?tjcourse.courseId=<s:property value="tjcourse.courseId"/>&tjcourse.tjcourseId=<s:property value="#course.courseId"/>'>删除</a>
							</td>
						</tr>
					</s:iterator>
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
	</body>
</html>
