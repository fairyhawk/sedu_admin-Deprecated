<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加课程分类</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>
		
		<script type="text/javascript">
			$().ready(function() {
	 			$("#courseSortForm").validate();
			});
			
			function onchangeShow(pId){ 
				document.getElementById('sort.pId').value=pId;
				if(pId > -1){
					document.getElementById('sort.level').value = 2;
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
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#sSortId").html(str);  
			 }  
			
			function onchangeSecond(pId){
			
				if(pId > -1){
					document.getElementById('sort.level').value = 3;
				}
				
				var fid = document.getElementById('sort.pId').value;
				if(fid==null || fid==-1){
					document.getElementById('sort.pId').value=-1;
				}else{
					document.getElementById('sort.pId').value = pId;
				}
				
			}
			
			function checkSubmit(){
				var level = document.getElementById('sort.level').value;
				
				if(level <=0){
					document.getElementById('sort.level').value = 1;
				}
				var fid = document.getElementById('sort.pId').value;
				
				if(fid==null || fid == -1){
					document.getElementById('sort.pId').value=-1;
				}
			}
			
			
		function IsIE() {
		    if (window.navigator.userAgent.indexOf("MSIE")>=1) {
		        //IE浏览器
		        return true;
		    }else{
		        return false;
		    }
		}

		function changeSortPic(ipt) {
				var img = document.getElementById("mainpicImg");
				var div = document.getElementById("mainpicDiv");
				if(ipt.value != '') {
					if(IsIE()) {
						ipt.select();    
		        		var imgSrc = document.selection.createRange().text; 
						div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
						div.style.height = "100px";
						div.style.width = "100px";
					
					} else {
						img.src = ipt.files.item(0).getAsDataURL();
						img.style.display = "";
					}
				} else {
					img.style.display = "none";
				}
		}

		</script>
	</head>
	<body>
<div>
	<form name="courseSortForm" id="courseSortForm" action="<%=contextPath %>/cou/coursesort!addCourseSort.action" method="post" enctype="multipart/form-data" onsubmit="checkSubmit();">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加课程分类</font>
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
							选择父级课程分类：
						</td>
						<td class="lists_tleft">
							<s:select name="fSortId" id="fSortId" list="courseSortList"
								listKey="coursesortId" listValue="coursesortName" 
								theme="simple" onchange="onchangeShow(this.value);">
							</s:select>
							<s:select name="sSortId" id="sSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onchangeSecond(this.value);">
							</s:select>
						</td>
					</tr>
					<tr>
						<td>
							新课程分类名称：
						</td>
						<td class="lists_tleft">
							<input type="text" name="sort.coursesortName" id="sort.coursesortName" class="required" />
						</td>
					</tr>
					
					<tr>
						<td>
							所属专业
						</td>
						<td class="lists_tleft">
							<s:select name="sort.subjectId" id="sort.subjectId"
								list="subjectList" listKey="subjectId" listValue="subjectName" headerKey="-1"
								headerValue="请选择" theme="simple">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td>
							分类图片
						</td>
						<td class="lists_tleft">
							<input type="file" name="sortPic" id="sortPic"
								class="{required:true}" onchange="changeSortPic(this)"/>
							<div class="uploadPic" id="mainpicDiv"></div>
							<img id="mainpicImg" style="display:none"/>
						</td>
					</tr>
					<tr>
						<td>
							排序值：
						</td>
						<td class="lists_tleft">
							<input type="text" name="sort.sortNum" id="sort.sortNum" class="{required:true,min:0}"/>
						</td>
					</tr>
					
					<tr>
						<td>
							分类类型：
						</td>
						<td class="lists_tleft">
							<input type=radio name="sort.status" value=0 checked />网站分类
	    					<input type=radio name="sort.status" value=1 />冻结
	    					<input type=radio name="sort.status" value=3 />内部学员
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
						<s:hidden name="sort.level" id="sort.level"></s:hidden>
						<s:hidden name="sort.pId" id="sort.pId"></s:hidden>
							<s:submit value="提交" />
						<br /></td>
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
	</body>
</html>
