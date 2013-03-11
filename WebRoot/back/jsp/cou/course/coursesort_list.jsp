<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程分类列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		
		 function updatesort(coursesortId, searchKey){
			window.location.href="<%=contextPath %>/cou/coursesort!toUpdateCoursesort.action?sort.coursesortId=" + coursesortId + "&searchKey=" + encodeURIComponent(searchKey);
			
		 }
		 function freeze(coursesortId,obj){
		 		var ss = obj.innerHTML;
		 		var vv = null;
		 		if(ss=='冻结'){
		 			vv = '解冻';
		 		}else{
		 			vv = '冻结';
		 		}
                
                 $.ajax({
                    url:"<%=contextPath%>/cou/coursesort!freezeCoursesort.action",
                    data:"sort.coursesortId="+coursesortId,
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
		    var f=document.getElementById("coursesortform");
		    for(i=0;i<f.elements.length;i++){
			 if(f.elements[i].name=="sortIds"){
			     f.elements[i].checked=e.checked;}
			 }
		 }
		
		function deleteAll(ObjectForm){
			var i = 0;
			var f=document.coursesortform;
			if(f.sortIds != null){
				if(f.sortIds.length!=null){
					for(var j=0;j<f.sortIds.length;j++){
				
						if(f.sortIds[j].checked == true){
						 i++;
						 }
					}
					}else
					{
						if(f.sortIds.checked == true)
						{
							i++;
						}		
					}
				if(i==0){
					alert("没有选择需要冻结的选项！");
					return false;
				}
				if(confirm("是否冻结？")){
					ObjectForm.action="<%=contextPath%>/cou/coursesort!freezeCoursesorts.action";
					ObjectForm.submit();
				}
			}
	 }
    </script>
	</head>
	<body>
<div>
	<form action="<%=contextPath %>/cou/coursesort!listCoursesortsByKey.action" method="post" name="coursesortform" id="coursesortform">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists" onmouseover="changeto()" onmouseout="changeback()">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">课程分类列表</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		
		
		<tr>
	            <td class="lists_bor"></td>
	            <td>
	              <div class="msg-zy">
				<table class="" border="0" cellspacing="0"  cellpadding="0">
						<tr>
						<td>
							<s:hidden name="queryCourseSortCondition.currentPage" value="1"/>
							按课程名检索：
							<input type="text" name="searchKey" id="searchKey" value="${searchKey}"/>
						</td>
						<td width="20px;"></td>
								<td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
								<td><a href="javascript:document.coursesortform.submit()">查询</a></td>
								<td width="20px;"></td>
								<td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
								<td><a href="<%=contextPath%>/cou/coursesort!toAddCourseSort.action">添加课程分类</a></td>
								<td width="20px;"></td>
								<td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:deleteAll(document.coursesortform);">批量冻结</a></td>
						</tr>		
						</table>
					 </td>
            <td class="lists_tright lists_bor2"></td>
        </tr>
		
		
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							<input type="checkbox" name="c1" onclick="All(this);" />全选
						</td>
						<td>
							课程分类名
						</td>
						<td>
							课程分类排序
						</td>
						<td>
							课程分类状态
						</td>
						
						<td>
							加入时间
						</td>
						
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="coursesort">
						<tr>
							<td>
							<input type="checkbox" name="sortIds" value='<s:property value="#coursesort.coursesortId"/>' />
							</td>
							<td>
								<s:property value="#coursesort.coursesortName" />
							</td>
							<td>
								<s:property value="#coursesort.sortNum" />
							</td>
				
							<td>
								<s:if test="#coursesort.status==0">
								  正常
								</s:if>
								<s:if test="#coursesort.status==1">
								  冻结
								</s:if>
								<s:if test="#coursesort.status==3">
								  内部课程
								</s:if>
							</td>
							<td>
								<s:date name="#coursesort.addTime"  format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
						<script language="javascript">
						document.write('<a href="<%=contextPath %>/cou/coursesort!toUpdateCoursesort.action?sort.coursesortId=<s:property value="coursesortId"/>&searchKey='+encodeURIComponent ("<s:property value="searchKey"/>")+'">修改</a>');
						</script> 
							<!--
							<a href='<%=contextPath%>/cou/coursesort!toUpdateCoursesort.action?sort.coursesortId=<s:property value="coursesortId"/>&searchKey=<s:property value="searchKey"/>'>修改</a>	
							  -->
								<!--<a href='javascript:updatesort("${coursesort.coursesortId}",<s:property value="searchKey"/>)'>修改</a>  -->
								<s:if test="#coursesort.status==0">
									<a href='#' onclick="freeze(${coursesort.coursesortId},this);">冻结</a>	
								</s:if>
								<s:if test="#coursesort.status==1">
									<a href='#' onclick="freeze(${coursesort.coursesortId},this);">解冻</a>	
								</s:if>
								
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
				<jsp:include page="/back/jsp/common/showPage.jsp" />
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
