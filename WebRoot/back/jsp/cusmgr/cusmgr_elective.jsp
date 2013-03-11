<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员选课</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
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
		
		function All(e){
		    var f=window.courseForm;
		    for(i=0;i<f.elements.length;i++){
			 if(f.elements[i].name=="ids"  &&  f.elements[i].style.display == 'block'){
			     f.elements[i].checked=e.checked;}
			 }
		}
		
		function elective() {
			if(window.confirm("您确定要选修已勾选中的课程吗？")) {
				var cks = $("input[type=checkbox][name=ids]");
				var ids = new Array();
				var cks2 = new Array();
			 	$.each(cks,function(i,n){
                    if (n.checked  &&  n.style.display == "block"){
                    	cks2[cks2.length] = n;
                    	ids[ids.length] = n.value;
                    }
                }); 
			
				$.ajax({
					url : "<%=contextPath %>/cusmgr/cusmgr!elective.action",  
					data : {'ids' : ids},  // 参数  
					type : "post",  
					cache : false,  
					error: function(){ 
						alert('error');      
					}, 
					success : function (result) {
						for(var i=0; i<cks2.length; i++) {
	                    	cks2[i].parentNode.parentNode.cells[0].children[0].style.display = "none";
							cks2[i].parentNode.parentNode.cells[cks2[i].parentNode.parentNode.cells.length-1].children[0].disabled = false;
	                    	cks2[i].parentNode.parentNode.cells[3].innerHTML = "已选";
	                    	cks2[i].checked = false;
	                    }
	                    $("#allCheck").attr('checked', false);
					}  
				});
			}
		}
		
		function unelective(btn,id) {
			if(window.confirm("您确定要取消已选修课程吗？")) {
				$.ajax({  
					url : "<%=contextPath %>/cusmgr/cusmgr!unelective.action",  
					data : {'queryCusMgrCondition.courseId' : id},  // 参数  
					type : "post",  
					cache : false,  
					error: function(){ 
						alert('error');      
					}, 
					success : function (result) {
						btn.parentNode.parentNode.cells[0].children[0].style.display = "block";
						btn.parentNode.parentNode.cells[btn.parentNode.parentNode.cells.length-1].children[0].disabled = "false";
						btn.parentNode.parentNode.cells[3].innerHTML = "未选";
					}  
				});
			}
		}
			
		function onchangeFirstSort(pId){
			if(pId != -1){
				document.getElementById('queryCusMgrCondition.sortId').value = pId;
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
			document.getElementById('sSortId').options.length = 0;  //清空原有的option 
			document.getElementById('tSortId').options.length = 0;  //清空原有的option 
			$("#tSortId").html("<option value=-1>请选择</option>");
			var str="";  
			for(var i=0;i<result.entity.length;i++){  
				str+="<option value='"+result.entity[i].key+"'>"+result.entity[i].value+"</option>"  
			}  
			$("#sSortId").html(str);  
		}  
			
		function onchangeSecond(pId){
				if(pId != -1){
					document.getElementById('queryCusMgrCondition.sortId').value = pId;
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
					document.getElementById('queryCusMgrCondition.sortId').value = pId;
				}
			}
			
			function onchangecallback2(result){   
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].key+"'>"+result.entity[i].value+"</option>"  
				}  
				$("#tSortId").html(str);  
			 } 
       </script>
	</head>
	<body>	
		<div class="main_right">
			<h1>
				选择课程所属分类
			</h1>
			<form name="prekpointform" id="prekpointform" action="<%=contextPath %>/cusmgr/cusmgr!toElective.action?queryCusMgrCondition.currentPage=1" method="post">
				<table class="tables" border="0" cellpadding="0" cellspacing="1" style="width:600px;">
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
					
					<tr align="center">
						<td colspan="2">
							<input type="hidden" name="queryCusMgrCondition.sortId" id="queryCusMgrCondition.sortId"
								value="<s:property value="queryCusMgrCondition.sortId"/>"></input>
							<s:submit value="提交"  />
						</td>
					</tr>
				</table>
			</form>
		
				<h2>
					课程列表 所属课程分类：
					<s:iterator value="courseSortList2" status="status">
						<s:property value="coursesortName" /> 
						<s:if test="#status.count<3">
							-->
						</s:if>
					</s:iterator>
				</h2>
			
			<form action="<%=contextPath%>/cusmgr/cusmgr!toElective.action" method="post" name="courseForm" id="courseForm">
				<table class="tables">
					<tr>
						<td>

							<s:hidden name="queryCusMgrCondition.currentPage" value="1" />
							<input type="hidden" name="queryCusMgrCondition.sortId"
								value="<s:property value="queryCusMgrCondition.sortId"/>"></input>
							<table>
								<tr>
									<td>
										按课程名检索
										<input type="text" name="queryCusMgrCondition.courseName" id="queryCusMgrCondition.courseName"
											value="<s:property value="queryCusMgrCondition.courseName"/>" />
									</td>
									<td>
										<input type="submit" value="查询" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table class="tables" onmouseover="changeto()" onmouseout="changeback()">
					<tr>
						<td colspan="10">
							<input type="button" value="选课" onclick="elective()"/>
							<input type="button" value="返回" onclick="history.go(-1)"/>
						</td>
					</tr>
					<tr>
						<td width="7%">
							<input type="checkbox" name="c1" id="allCheck" onclick="All(this);" />全选
						</td>
						<td>
							课程名
						</td>
						<td>
							课程价格
						</td>
						
						<td>
							选课状态
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
					<s:iterator value="page.pageResult" id="course">
						<tr>
							<td>
									<input type="checkbox" name="ids" value='<s:property value="courseId"/>' style=display:<s:property value="status==0?'block':'none'"/>;/>
							</td>
							<td>
								<s:property value="title" />
							</td>
							<td>
								<s:property value="price" />
							</td>
							
							<td>
								<s:if test="status==1">已选</s:if>
								<s:if test="status==0">未选</s:if>
							</td>

							<td>
								<s:property value="clickNum" />
							</td>

							<td>
								<s:property value="gmNum" />
							</td>

							<td>
								<s:property value="tgNum" />
							</td>

							<td>
								<s:property value="scNum" />
							</td>

							<td>
								<s:property value="addtime" />
							</td>
							
							<td>
								<input type="button" value="取消选课" <s:property value="status==1?'':'disabled'"/> onclick="unelective(this, <s:property value="courseId"/>)"/>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="10">
							<jsp:include page="/back/jsp/common/showPage.jsp" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>