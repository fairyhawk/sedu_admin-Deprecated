<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>根据课程添加课程节点与资源</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>

		<script type="text/javascript">
		
		$().ready(function() {
			$("#vedioform").validate({   
		        rules: {   
		            fSortId: {
		            	required:true,
		            	min: -1
		            },
		            'kpoint.courseId': {
		            	required:true,
		            	min: 0
		            },
		            prefix:{
		            	required:true,
		            	maxlength:100
		            },
		            vedioUrl:{
		            	required:true,
		            	minlength: 0,
		            	maxlength:100
		            },
		            tcId:{
		            	required:true,
		            	min:0
		            }
		            
		        },   
		        messages: {   
		            fSortId: {
		            	required:"请选择分类",
		            	min: "请选择分类"
				    },
				    'kpoint.courseId': {
		            	required:"请选择课程",
		            	min: "请选择课程"
				    },
				    prefix:{
		            	required:"请填写前缀",
		            	maxlength: "填写前缀过长"
		            },
		            vedioUrl:{
		            	required:"请填写视频服务器地址",
		            	maxlength: "填写视频服务器地址过长"
		            },
		            tcId:{
		            	required:"请选择老师",
		            	min:"请选择老师"
		            }
		        }   
   	 		});  
		})	
		
		function onchangeFirstSort(pId){ //第一个节点修改						
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
			
			var str="";  
			for(var i=0;i<result.entity.length;i++){  
				str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
			} 
			document.getElementById('kpoint.courseId').innerHTML = str;
   		}  
					
		function onchangecallback(result){   
			document.getElementById('sSortId').options.length = 0;  //清空原有的option 
			var str="";  
			for(var i=0;i<result.entity.length;i++){  
				str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
			}  
			$("#sSortId").html(str);  
		 } 
			
		function onchangeSecond(pId){//第二个分类变动
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
			
		function onchangeThird(pId){//第三个分类变动
			
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
			
		function onchangeCourse(pId){
			
		} 
					
		function onchangecallback2(result){   
			document.getElementById('tSortId').options.length = 0;  //清空原有的option 
			var str="";  
			for(var i=0;i<result.entity.length;i++){  
				str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
			}  
			$("#tSortId").html(str);  
		 } 
		 	
		function IsIE() {
		    if (window.navigator.userAgent.indexOf("MSIE")>=1) {
		        //IE浏览器
		        return true;
		    }else{
		        return false;
		    }
		}
	
		</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath%>/cou/kpoint!addVedioByCourse.action" method="post" name="vedioform" id="vedioform">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">课程添加</font>
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="tblList">
				<tbody id="tagTb">
					<tr>
						<td>
							<font color="red">*</font>课程分类
						</td>
						<td class="lists_tleft">
							<s:select name="fSortId" id="fSortId" list="courseSortList"
								listKey="coursesortId" listValue="coursesortName" theme="simple"
								onchange="onchangeFirstSort(this.value);">
							</s:select>
							<s:select name="sSortId" id="sSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple"
								onchange="onchangeSecond(this.value);">
							</s:select>

							<s:select name="tSortId" id="tSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple"
								onchange="onchangeThird(this.value);">
							</s:select>
						</td>
					</tr>										
					
					<tr>
						<td>
							课程
						</td>
						<td class="lists_tleft">
							<s:select name="kpoint.courseId" id="kpoint.courseId" list="#{}"
								headerKey="-1" headerValue="请选择" theme="simple"
								onchange="onchangeCourse(this.value)">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td align="left">
							<font color="red">*</font>讲师名称：
						</td>
						<td class="lists_tleft">
							<select name="tcId"  id="tcId" style="width:155px"  class="{required:true,maxlength:50}">
								<option value="请选择">---请选择---</option>
								<s:iterator value="teacherList" id="teacher">
								<option value="<s:property value="#teacher.tcId"/>"><s:property value="#teacher.name"/></option>
								</s:iterator>
							</select>
							
						</td>
					</tr>				
					
					<tr>
						<td>
							<font color="red">*</font>视频前缀
						</td>
						<td class="lists_tleft">
							<input type="text" name="prefix" id="prefix" size=100/>
						</td>
					</tr>
					
					<tr>
						<td>
							<font color="red">*</font>视频服务器地址
						</td>
						<td class="lists_tleft">
							<input type="text" name="vedioUrl" id="vedioUrl" size=100 value="http://"/>
						</td>
					</tr>
				</tbody>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
				<input type="submit" value="提交课程"/>
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
