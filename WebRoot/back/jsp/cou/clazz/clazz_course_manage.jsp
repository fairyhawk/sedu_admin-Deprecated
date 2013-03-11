<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript">
		 $().ready(function() {
			$("#addClazzCouform").validate({   
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
				   sSortId: {
				            	required:"请选择分类",
				            	min: "请选择分类"
				            },
				   tSortId: {
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
				document.getElementById('clazzCou.courseId').value = courseid;
			}
			
			function checkSubmit(){
				var fid = document.getElementById('kpoint.courseId').value;
				
				if(fid == null || fid == -1 || fid == 0){
					document.getElementById('course.sortId').value=0;
					alert(请选择课程);
					return false;
				}
				
				var level = document.getElementById('kpoint.level').value;
				
				if(level == 0){
					document.getElementById('kpoint.level').value = 1;
				}
				return true;
			}
			
       </script>
	</head>
	<body>
<div>
	<form name="addClazzCouform" id="addClazzCouform" action="<%=contextPath%>/cou/clazz!addClazzCou.action?queryClazzCouCondition.currentPage=1" method="post">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">班级名称：<s:property value="clazz.title"/></font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td   class="lists_bor">
			</td>
			<td>
			<div class="msg-zy">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td width="15%">
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
					<td>
						选择课程：
					</td>
					<td class="lists_tleft">
						<s:select name="courseId" id="courseId" list="#{}" headerKey="-1"
							headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">
						</s:select>
					</td>
				</tr>
	
				<tr align="center">
					<td colspan="2">
						<s:hidden name="clazz.clazzId"></s:hidden>
						<s:hidden name="clazzCou.courseId" id="clazzCou.courseId"></s:hidden>
						<s:submit value="添加课程" />
					</td>
				</tr>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td  class="lists_top">
				<font class="lists_fleft"><s:property value="clazz.title"/>的课程列表</font>
				<font class="lists_fright"></font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td  class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
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
							课程状态
						</td>

						<td>
							添加时间
						</td>

						<td>
							操作
						</td>
					</tr>
					<s:if test="page.pageResult != null">
					<s:iterator value="page.pageResult" id="course">
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
								<s:if test="#course.status==0">
							  正常
							</s:if>
								<s:if test="#course.status==1">
							  冻结
							</s:if>
							</td>

							<td>
								<s:date name="#course.addtime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<a href="<%=contextPath%>/cou/clazz!deleteClazzCou.action?queryClazzCouCondition.currentPage=1&clazz.clazzId=<s:property value='clazz.clazzId'/>&clazzCou.courseId=<s:property value="#course.courseId" />">从班级中删除</a>
							</td>
						</tr>
					</s:iterator>
					</s:if>
			</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
				<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif"/></td>
				<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
				<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
			</tr>
			</table>
			</td>
		<td width="16" class="lists_tright lists_bor2"></td>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">&nbsp;
			</td>
			<td class="td_wid_1">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
		
</table>
</div>
	</body>
</html>
