<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程列表</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		//进入页面时，初始化查询条件
$(document).ready(function(){
	changeData();									
});

//初始化时间的显示
function changeData() {
  	var status = $("#hideuploadStatus").val();
  	
	changeSelectedData("#uploadStatus option",status);
}
	
//自动赋值select
function changeSelectedData(select,hourStr){
	$.each($(select),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
}
		
		
		 function freeze(courseId,obj){
		 		
		 		var ss = obj.innerHTML;
		 		var vv = null;
		 		if(ss=='冻结'){
		 			vv = '解冻';
		 		}else{
		 			vv = '冻结';
		 		}
                $.ajax({
                    url:"<%=contextPath%>/cou/course!freezeCourse.action",
                    data:"course.courseId="+courseId,
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
        	$("input[name=courseIds]:checkbox").attr('checked', e.checked);
		}
		
		function deleteAll(){
			var courseIdArray=document.getElementsByName("courseIds");
			var num=0;
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
						document.courselist.action = "<%=contextPath%>/cou/course!freezeAllCourse.action";
						document.courselist.submit();
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
		})	;
			
		function onchangeFirstSort(pId){
				
				//if(pId != -1){
					//document.getElementById('course.sortId').value = pId;
				//}
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
					 
					//if(pId != -1){
						//document.getElementById('course.sortId').value = pId;
					//}
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
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"; 
				}  
				$("#tSortId").html(str);  
				
			 } 
		function search(){
			var form= document.getElementById("prekpointform");	
			    var title = $("#searchKey").val();
			 	$("#searchKey").val(encodeURIComponent(title));
			 	form.submit();
			 	$("#searchKey").val(title);
						    
			 }
       </script>
	</head>
	<body>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">选择课程所属分类</font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td class="lists_bor">
					</td>
					<td>
						<div class="msg-zy">
							<form name="prekpointform" id="prekpointform"
								action="<%=contextPath%>/cou/course!listCoursesBySortId.action?queryCourseCondition.currentPage=1"
								method="post">
								<table width="100%" border="0" align="center">
									<tr>
										<td>
											课程分类：
											<s:select name="fSortId" id="fSortId" list="courseSortList"
												listKey="coursesortId" listValue="coursesortName"
												theme="simple" onchange="onchangeFirstSort(this.value);">
											</s:select>

											<s:select name="sSortId" id="sSortId" list="#{}"
												headerKey="-1" headerValue="请选择" theme="simple"
												onchange="onchangeSecond(this.value);">
											</s:select>

											<s:select name="tSortId" id="tSortId" list="#{}"
												headerKey="-1" headerValue="请选择" theme="simple"
												onchange="onchangeThird(this.value);">
											</s:select>
											<input type="hidden" name="course.sortId" id="course.sortId"
												value='<s:property value="course.sortId" />' />
										</td>
										<td>
											课程名称：
											<input type="text" name="searchKey" id="searchKey"
												value="${searchKey}" />
											上传状态：
											<select name="uploadStatus" id="uploadStatus">
												<option value="0">
													请选择
												</option>
												<option value="1">
													未上传
												</option>
												<option value="2">
													上传中
												</option>
												<option value="3">
													已传完
												</option>
											</select>
											<input type="hidden" name="hideuploadStatus"
												id="hideuploadStatus" value='${uploadStatus }' />
										</td>
									</tr>
									<tr>
										<td>
											添加时间：
											<input type="text" name="addStartDate" readonly="readonly"
												id="addStartDate" onFocus="WdatePicker()"
												onchange="createDateOne()" value="${addStartDate}" />
											-
											<input type="text" name="addEndDate" readonly="readonly"
												id="addEndDate" onFocus="WdatePicker()"
												onchange="createDateOne()" value="${addEndDate }" />
										</td>
										<td>
											更新时间：
											<input type="text" name="updateStartDate" readonly="readonly"
												id="updateStartDate" onFocus="WdatePicker()"
												onchange="createDateOne()" value="${updateStartDate }" />
											-
											<input type="text" name="updateEndDate" readonly="readonly"
												id="updateEndDate" onFocus="WdatePicker()"
												onchange="createDateOne()" value="${updateEndDate }" />
										</td>
									</tr>
									<tr>
										<td>
											<img src="<%=contextPath%>/back/images/add_a.gif" />
											<a href="javascript:search()">查询</a>
											<img src="<%=contextPath%>/back/images/add_a.gif" />
											<a href="<%=contextPath%>/cou/course!toAddCourse.action">添加课程</a>
											<img src="<%=contextPath%>/back/images/del_a.gif" />
											<a href="javascript:deleteAll();">批量冻结</a>
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</form>
						</div>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="lists">
							<tr>
								<td class="td_wid_l">
									<img src="<%=contextPath%>/back/images/tab_03.gif" />
								</td>
								<td class="lists_top">
									<font class="lists_fleft"> 课程列表 所属课程分类：${pjsortname} </font>
								</td>
								<td class="td_wid_l">
									<img src="<%=contextPath%>/back/images/tab_07.gif" />
								</td>
							</tr>
							<tr>
								<td class="lists_bor">
								</td>
								<td>
								<form action="<%=contextPath%>/cou/course!listCoursesBySortId.action" method="post" name="courselist" id="courselist">
									<table width="100%" border="0" cellspacing="1" cellpadding="0"
										class="lists_info" onmouseover="changeto()"
										onmouseout="changeback()">
										<tr class="lists_infobg">
											<td>
												<input type="checkbox" name="c1" onclick="All(this);" />
											</td>
											<td>
												编号
											</td>
											<td>
												课程名
											</td>
											<td>
												所属项目
											</td>
											<td>
												计划上传课时
											</td>
											<td>
												实际上传课时
											</td>
											<td>
												上传进度
											</td>
											<td>
												上传状态
											</td>
											<td>
												课程原价格
											</td>
											<td>
												课程现价格
											</td>
											<td>
												课程状态
											</td>
											<td>
												添加时间
											</td>
											<td>
												更新时间
											</td>
											<td>
												备注
											</td>

											<td>
												操作
											</td>
										</tr>
										<s:iterator value="page.pageResult" id="course">
											<tr>
												<td>
													<input type="checkbox" name="courseIds"
														value='<s:property value="#course.courseId"/>' />
												</td>
												<td>
													<s:property value="#course.courseId" />
												</td>
												<td>
													<s:property value="#course.title" />
												</td>
												<td>
													<s:property value="#course.subjectName" />
												</td>
												<td>
													<s:property value="#course.lessionTime" />
												</td>
												<td>
													<s:property value="#course.realUpNum" />
												</td>
												<td>
													<s:property value="#course.upRate" />
													%
												</td>
												<td>
													<s:if test="#course.realUpNum==0">
								未上传
								</s:if>
													<s:elseif test="#course.lessionTime==#course.realUpNum">
								已传完
								</s:elseif>
													<s:else>
								上传中
								</s:else>
												</td>
												<td>
													<s:property value="#course.oldPrice" />
												</td>
												<td>
													<s:property value="#course.price" />
												</td>
												<td>
													<s:if test="#course.status==0">
							  正常
							</s:if>
													<s:if test="#course.status==1">
							  冻结
							</s:if>
													<s:if test="#course.status==3">
							  内部课程
							</s:if>
												</td>

												<td>
													<s:date name="#course.addtime" format="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
													<s:date name="#course.updateTime"
														format="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>

													<s:if test="%{#course.introduce.length()<20}">
														<s:property value="#course.introduce" />
													</s:if>
													<s:else>
														<s:property
															value="%{#course.introduce.substring(0,15)+'...'}"
															escape="false" />
													</s:else>
												</td>
												<td>
													<script language="javascript">
									document.write('<a href="<%=contextPath%>/cou/course!toUpdateCourse.action?course.courseId=<s:property value="courseId"/>&course.sortId=<s:property value="sortId"/>&searchKey='+encodeURIComponent ("<s:property value="searchKey"/>")+'">修改</a>');
								</script>
													<s:if test="#course.status==0">
														<a href='#'
															onclick="freeze(<s:property value='#course.courseId'/>,this);">冻结</a>
													</s:if>
													<s:if test="#course.status==1">
														<a href='#'
															onclick="freeze(<s:property value="#course.courseId"/>,this);">解冻</a>
													</s:if>
													<a
														href='<%=contextPath%>/cou/course!getHistoryListByCourseId.action?course.courseId=<s:property value="#course.courseId" />'>历史价格</a>
												</td>
											</tr>
										</s:iterator>
									</table>
									</form>
								</td>
								<td width="16" class="lists_tright lists_bor2">
								</td>
							</tr>
							</form>
							<tr>
								<td class="td_wid_l">
									<img src="<%=contextPath%>/back/images/tab_18.gif" />
								</td>
								<td class="lists_bottom">
									<jsp:include page="/back/jsp/common/showPage.jsp" />
								</td>
								<td class="td_wid_r">
									<img src="<%=contextPath%>/back/images/tab_20.gif" />
								</td>
							</tr>
						</table>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom">

					</td>
					<td class="td_wid_r">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
