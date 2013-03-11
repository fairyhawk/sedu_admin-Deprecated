<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
		<script type="text/javascript">
		$().ready(function() {
			$("#addBooksForm").validate({   
		        rules: {   
		            fSortId: {
		            	required:true,
		            	min: -1
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
				   		
			function onchangecallback(result){   
					//alert(data);		
				document.getElementById('sSortId').options.length = 0;  //清空原有的option 
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				$("#tSortId").html("<option value=-2>请选择</option>");
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
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#tSortId").html(str);  
			 } 
			
			function onSelectCourse(courseid){ 
			 document.getElementById('kpoint.courseId').value = courseid;
			 showPkpoint();
	
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
 		function showPkpoint(){
			var courseId=document.getElementById('courseId').value;
			$.ajax({  
					url : "<%=contextPath%>/res/books!addBooksPoint.action",  
					data : {courseId : courseId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackpoint  
					});
				
					
		}
		
		function onchangecallbackpoint(result){
			var myList = result.entity;
			if(myList.length<1){return;}
			$("#kpointList").show();
			addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
			addkpoint.add(-2,-1,'栏目列表 <a href="javascript:closeColumn();">关闭</a>');
			for(var i=0;i<myList.length;i++){  
					addkpoint.add(myList[i].id ,myList[i].PId ,myList[i].name,'javascript:changeColumnValue('+myList[i].id+',' + "'"+myList[i].name+ "'"+')');
				}
			$("#kpointList").html(addkpoint.toString());
		}
		function changeColumnValue(id,name){
			obj="kpointList";
			document.getElementById("books.pointid").value = id;
			document.getElementById("pName").value = name;
			document.getElementById(obj).style.display="none";
		}
		function closeColumn(){
			obj="kpointList";
			document.getElementById(obj).style.display="none";
		}
		
		function toViewDownload(){
				window.location.href = "<%=contextPath%>/test!toViewDownload.action";
		}
			
			var count = 0;
			
			function addPic() {
				var tb;
				if (IsIE()==true){
			        tb = document.getElementById('tagTb');//table
			    }else{
			    	tb = document.getElementById('addTable');//tbody
			    }
			   
			    var no = document.getElementById('hidTotal').value;
			    no++;
			    document.getElementById('hidTotal').value = no;
			  	
			    var xuhao = document.getElementById('xuhao').value;
			    xuhao++;
			    document.getElementById('xuhao').value = xuhao;
			    
			    var row4 = document.createElement('tr');	     
			 	var td1 = document.createElement("td");
			 	td1.innerHTML ="图片序号：" + xuhao;
			    
			    var td = document.createElement("td");
			    td.className = "lists_tleft";
				td.colSpan = 1;
				td.innerHTML="<input type='file' name='fileList' id='file"+ no +"' onchange='changePic(this)'/><input type='button' value='删除' onclick='deleteRow(this)'>";
				
				var br = document.createElement("br");
				var img = document.createElement("img");
				img.style.display = "none";
				img.id = "picImg" + no;
				row4.appendChild(td1);
				row4.appendChild(td);
				td.appendChild(br);
				td.appendChild(img);
				tb.appendChild(row4);
			   
			}
			function changeMainPic(ipt) {
				var img = document.getElementById("mainpicImg");
				if(ipt.value != '') {
					if(IsIE()) {
						img.src = ipt.value
						img.style.display = "block";
					} else {
						img.src = ipt.files.item(0).getAsDataURL();
						img.style.display = "";
					}
				} else {
					img.style.display = "none";
				}
			}
			
			function changePic(ipt) {
				var img = document.getElementById("picImg" + ipt.id.substring(4, ipt.id.length));
				if(ipt.value != '') {
					if(IsIE()) {
						img.src = ipt.value
						img.style.display = "block";
					} else {
						img.src = ipt.files.item(0).getAsDataURL();
						img.style.display = "";
					}
				} else {
					img.style.display = "none";
				}
			}
			
			function deleteRow(button) {
				/**var tbl = document.getElementById("addTable");
				tbl.deleteRow(btn.parentNode.parentNode.rowIndex);**/
				var xuhao = document.getElementById('xuhao').value;
				xuhao--;
			   	document.getElementById('xuhao').value = xuhao;
				var rownum = button.parentNode.parentNode.rowIndex;//当前行数
				var tb;
				if (IsIE()==true)
			    {
			        tb = document.getElementById('tagTb');
			    }else{
			    	tb = document.getElementById('addTable');
			    }
				
				var mynownum = rownum - 15;//第几题
				
				var yunum = tb.rows.length-rownum;//余下为多少
				
				var tempindex = rownum;
				for(j = 0; j < yunum -1; j ++){
					mynownum ++;
					tempindex ++;
					tb.rows[tempindex].cells[0].innerHTML = "图片序号:" + mynownum;
				}
				
				tb.deleteRow(rownum);
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
	<s:form action="books!addBooks" method="post" name="addBooksForm" id="addBooksForm" enctype="multipart/form-data">
	<s:hidden name="queryBooksCondition.currentPage" value="1"/>
	<input type="hidden" name="hidTotal" id="hidTotal" value="0" /><!-- 一直递增量 -->
	<input type="hidden" name="xuhao" id="xuhao" value="0" /><!-- 序号 -->
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">信息添加——书籍基本信息设置</font>
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="addTable" >
				<tbody id="tagTb">
					<tr>
						<td align="left">
							<font color="red">*</font>书名：
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.bkName" id="books.bkName" class="{required:true,minlength:4,maxlength:50}"/>
						</td>
					</tr>
					<!-- 
					<tr>
						<td align="left">
							<font color="red">*</font>作者：
						</td>
						<td>
						<input type="text" name="books.bkAuthor" id="books.bkAuthor" class="{required:true,minlength:4,maxlength:50}"/>
						</td>
					</tr>
					 -->
					<tr>
						<td align="left">
							<font color="red">*</font>类型：
						</td>
						<td class="lists_tleft">
							<select name="books.bkType"  id="books.bkType" style="width:155px">
									
									<option value="ppt">ppt</option>
									<option value="pdf">pdf</option>
									<option value="doc">doc</option>
									<option value="rar">rar</option>
								
							</select>
						</td>
					</tr>
				<!-- 	<tr>
						<td align="left">
							<font color="red">*</font>标题：
						</td>
						<td>
						<input type="text" name="books.bkTitle" id="books.bkTitle" class="{required:true,minlength:4,maxlength:50}"/>
						</td>
					</tr> -->
					
					<tr>	
						<td align="left">
							<font color="red">*</font>图书地址：
						</td>
						<td class="lists_tleft">
						<input type="text" size='80' name="books.bkUrl" id="books.bkUrl" value=""/>
						</td>
					</tr>
						<tr>
							<td align="left">
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
							<td align="left">
								选择课程：
							</td>
							<td class="lists_tleft">
								<s:select name="courseId" id="courseId" list="#{}" headerKey="-1"
									headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">
								</s:select>
							</td>
						</tr>
						<tr>
							<td align="left">
								选择课程节点：
							</td>
							<td class="lists_tleft" style="color: #ff0000;"> 
		    				<input id="books.pointid" type="hidden" name="books.pointid"  />
		    				<input id="kpoint.courseId" type="hidden" name="kpoint.courseId"  />
		    				<input id="pName" type="text" name="pName" onclick="showPkpoint();" readonly="readonly" />
		    				<div id="kpointList" style="position:absolute;width:340px; height:200px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
							</div>
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
			<td align="center">
				<input type="submit" name="su" value="提交"/>
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
	</s:form>
</div>
	</body>
</html>
