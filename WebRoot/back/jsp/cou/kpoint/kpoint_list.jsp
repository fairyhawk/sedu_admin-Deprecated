<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程节点</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		 function freeze(pointId,obj){
		 		var ss = obj.innerHTML;
		 		var vv = null;
		 		if(ss=='冻结'){
		 			vv = '解冻';
		 		}else{
		 			vv = '冻结';
		 		}
                
                 $.ajax({
                    url:"<%=contextPath%>/cou/kpoint!freezeKpoint.action",
                    data:"kpoint.pointId="+pointId,
                    dataType:"text",
                    type : "post",  
					cache : false, 
                    error:function(){
                    	alert("error");
                    },
                    success:function(msg){
                        obj.innerHTML=vv;
                    }
                });
         }
         
        function All(e){
			 $("input[name=kpointIds]:checkbox").attr('checked', e.checked);
		}
		
		function deleteAll(ObjectForm){
			var num = 0;
			var courseIdArray =document.getElementsByName("kpointIds");
			for(var i=0;i<courseIdArray.length;i++){
				if(courseIdArray[i].checked){
					num++
				}
			}
				if(num=0){
					alert("请选择需要冻结的项！");
					return false;
				}else{
					 if(window.confirm("确定要执行批量冻结吗？")){
						document.kpointfreeze.action = "<%=contextPath%>/cou/kpoint!freezeKpoints.action";
						document.kpointfreeze.submit();
					} 
				}
			
			
			
	 	}
	 	
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
		});
		
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
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"; 
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
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"; 
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
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>";
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
					alert('请选择课程');
					return false;
				}
				return true;
			}
			function search(){
			$("#searchKey").val(encodeURIComponent($("#searchKey").val()));
				document.kpointform.submit();
			}
       </script>
	</head>
	<body>
<div>
	<form name="courseselectform" id="courseselectform" action="<%=contextPath %>/cou/kpoint!listKpointsByCondition.action?queryKpointCondition.currentPage=1" method="post" onsubmit="checkSubmit();">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">
					课程节点列表  所属课程分类：
					<s:iterator value="coursesortMap">
						<s:property value="value" /> --
					</s:iterator>
				</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td  class="lists_bor">
			</td>
			<td>
			<div class="msg-zy">
			<table  width="100%" border="0"  align="center">
				<tr>
					<td>
						选择课程分类：	
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
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择课程：
						<s:select name="courseId" id="courseId" list="#{}" headerKey="-1"
							headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">
						</s:select>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="2">
						<input type="hidden"  name="kpoint.courseId" id="kpoint.courseId" value='<s:property value="kpoint.courseId" />'/>
						<s:submit value="查询"  />
					</td>
				</tr>
			</table>
			</div>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
	
	</table>
	</form>

	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
		  <td class="lists_bor"></td>
			<td class="lists_top">
			<font class="lists_fleft">课程节点列表</font>
				<form action="<%=contextPath %>/cou/kpoint!listKpointsByCondition.action" method="post" name="kpointform" id="kpointform">
					<table class="lists_fright" border="0" cellspacing="0"  cellpadding="0">
					<tr>
					<td>
							<s:hidden name="queryKpointCondition.currentPage" value="1"/>
							<s:hidden name="kpoint.courseId"/>
							按课程节点名称检索：
					</td>
					<td><input type="text" name="searchKey" id="searchKey" value="${searchKey}"/></td>
					<td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:search()">查询</a></td>
					<td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:deleteAll(document.kpointform);">批量冻结</a></td>
                   </tr>
                   </table>
					</form>
			</td>
			  <td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<form action="<%=contextPath %>/cou/kpoint!listKpointsByCondition.action" method="post" name="kpointfreeze" id="kpointfreeze">
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td>
							<input type="checkbox" name="c1" onclick="All(this);" />
						</td>
						<td>
							课程节点名称
						</td>
						<td>
							课程节点简述
						</td>
						
						<td>
							是否为叶子节点
						</td>
						
						<td>
							知识点状态
						</td>
						
						<td>
							添加时间
						</td>
						
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="kpoint">
						<tr>
							<td>
									<input type="checkbox" name="kpointIds"
										value='<s:property value="#kpoint.pointId"/>' />
							</td>
							<td>
								<s:property value="#kpoint.name" />
							</td>
							<td>
								<s:property value="#kpoint.introduce" />
							</td>
						
							<td>
								<s:if test="#kpoint.leaf==0">
								  不是
								</s:if>
								<s:if test="#kpoint.leaf==1">
								  是
								</s:if>
							</td>
							
							<td>
								<s:if test="#kpoint.status==0">
								  正常
								</s:if>
								<s:if test="#kpoint.status==1">
								  冻结
								</s:if>
							</td>
						
							<td>
								<s:date name="#kpoint.addTime"  format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:if test="#kpoint.status==0">
									<a href='#' onclick="freeze(<s:property value="#kpoint.pointId"/>,this);">冻结</a>|	
								</s:if>
								<s:if test="#kpoint.status==1">
									<a href='#' onclick="freeze(<s:property value="#kpoint.pointId"/>,this);">解冻</a>|	
								</s:if>
								<a href='<%=contextPath%>/cou/kpoint!deleteKpoint.action?kpoint.pointId=<s:property value="#kpoint.pointId"/>' onclick="return confirm('是否删除？')">删除</a>
							</td>
						</tr>
					</s:iterator>
				</table>
				</form>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
	</body>
</html>
