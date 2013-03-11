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
			$("#editBooksForm").validate({   
		        rules: {   
		            fuSortId: {
		            	required:true,
		            	min: 0
		            },
		            
		            suSortId: {
		            	required:true,
		            	min: 0
		            },
		            tuSortId: {
		            	required:true,
		            	min: 0
		            },
		            courseId: {
		            	required:true,
		            	min: 0
		            }
		        },   
		        messages: {   
		            fuSortId: {
				            	required:"请选择分类",
				            	min: "请选择分类"
				    },
				    suSortId: {
				            	required:"请选择分类",
				            	min: "请选择分类"
				    },
				    tuSortId: {
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
		
	function onchangeShow(pId){ 
				document.getElementById('course.sortId').value=pId;
						
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
				
				document.getElementById('suSortId').options.length = 0;  //清空原有的option
				document.getElementById('tuSortId').options.length = 0; 
				$("#tSortId").html("<option value=-1>请选择</option>");
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#suSortId").html(str);  
			 } 
			
			function onchangeSecond(pId){
				var fid = document.getElementById('course.sortId').value;
				if(fid==null || fid==-1){
					document.getElementById('course.sortId').value=0;
				}else{
					document.getElementById('course.sortId').value = pId;
					
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
			}
			
			function onchangeThird(pId){
				var fid = document.getElementById('course.sortId').value;
				if(fid==null || fid==-1){
					document.getElementById('course.sortId').value=0;
				}else{
					document.getElementById('course.sortId').value = pId;
				}
				
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
			
			/**function onchangecallbackcourse(result){ //处理返回的课程JSON  
				
				document.getElementById('precourseId').options.length = 0;  //清空原有的option
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#precourseId").html(str); 
	   		} */
   			function onchangecallbackcourse(result){ //处理返回的课程JSON  
				
				document.getElementById('courseId').options.length = 0;  //清空原有的option
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				
				$("#courseId").html(str); 
	   		}  
					
			function onchangecallback2(data){   
						
				document.getElementById('tuSortId').options.length = 0;  //清空原有的option 
				var str="";  
				for(var i=0;i<data.length;i++){  
					str+="<option value='"+data[i].id+"'>"+data[i].val+"</option>"  
				}  
				$("#tuSortId").html(str); 
			}
				
			function onchangecallback2(result){   
				document.getElementById('tuSortId').options.length = 0; 
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#tuSortId").html(str);  
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
			//document.write(addkpoint);
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
			//上传图片
				function toViewDownload(){
				window.location.href = "<%=contextPath%>/test!toViewDownload.action";
			}
			
			var count = 0;
			
			function addPic() {
				var tb;
				if (IsIE()==true){
			        tb = document.getElementById('tagTb');//table
			    }else{
			    	tb = document.getElementById('editTable');//tbody
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
				td.innerHTML="<input type='file' name='otherPics' id='file"+ no +"' onchange='changePic(this)'/><input type='button' value='删除' onclick='deleteRow(this)'>";
				
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
			function changeMainPic(ipt,num) {
				var img = document.getElementById("mainpicImg");
				document.getElementById('mainpic' + num).value="1";
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
			
			function changeOtherPic(ipt, num) {
				var img = document.getElementById("otherpicImged" + ipt.id.substring(8, ipt.id.length));
				document.getElementById('otherPicEd' + num).value="1";
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
		
			 function deleteRow(button){
				var xuhao = document.getElementById('xuhao').value;
			    xuhao--;
			    document.getElementById('xuhao').value = xuhao;
				
				var rownum = button.parentNode.parentNode.rowIndex;//当前行数
				var tb;
				
			    if (IsIE()==true)
			    {
			        tb = document.getElementById('tagTb');
			    }else{
			    	tb = document.getElementById('editTable');
			    }
				
				var oldotherslength = document.getElementsByName("otherPiced");
				var mynownum = rownum - (17 + oldotherslength.length);//第几题
				
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
	<s:form action="books!editBooks" method="post" name="editBooksForm" id="editBooksForm" enctype="multipart/form-data">
	<input type="hidden" name="hidTotal" id="hidTotal" value="0" /><!-- 一直递增量 -->
	<input type="hidden" name="xuhao" id="xuhao" value="0" /><!-- 序号 -->
	<s:hidden name="course.sortId" id="course.sortId" ></s:hidden>
	<s:hidden name="books.bkId" value="%{books.bkId}" id="books.bkId"></s:hidden>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">信息修改——书籍基本信息设置</font>
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="editTable" >
				<tbody id="editTable">	
					<tr>
						<td align="left">
							<font color="red">*</font>书名： 
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.bkName" id="books.bkName" value="<s:property value="books.bkName"/>"  class="{required:true,minlength:4,maxlength:50}"/>
						</td>
					</tr>
					<!-- <tr>
						<td align="left">
							<font color="red">*</font>作者：
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.bkAuthor" id="books.bkAuthor" value="<s:property value="books.bkAuthor"/>"  class="{required:true,minlength:4,maxlength:50}"/>
						</td>
					</tr> -->
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
					<!--  <tr>
						<td align="left">
							<font color="red">*</font>标题：
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.bkTitle" id="books.bkTitle" value="<s:property value="books.bkTitle"/>"  class="{required:true,minlength:4,maxlength:50}"/>
						</td>
					</tr>-->
				<!--  <tr>
						<td align="left">
							<font color="red">*</font>价格：
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.bkPrice" id="books.bkPrice" value="<s:property value="books.bkPrice"/>" class="{required:true,number:true,min:0}"/>
						</td>
					</tr>
					<tr>
						<td align="left">
							<font color="red">*</font>促销方式：
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.bkSales" id="books.bkSales" value="<s:property value="books.bkSales"/>"/>
						</td>
					</tr>
					<tr>
						<td align="left">
							<font color="red">*</font>折扣价格：
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.discPrice" id="books.discPrice" value="<s:property value="books.discPrice"/>" class="{number:true,min:0}"/>
						</td>
					</tr>
					<tr>
						<td align="left">
							<font color="red">*</font>出版社：
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.publisher" id="books.publisher" value="<s:property value="books.publisher"/>" class="{required:true,minlength:4,maxlength:50}"/>
						</td>
					</tr>
					<tr>	
						<td align="left">
							<font color="red">*</font>图书条码：
						</td>
						<td class="lists_tleft">
						<input type="text" name="books.barcode" id="books.barcode" value="<s:property value="books.barcode"/>"/>
						</td>
					</tr>
					<tr>
						<td align="left">
						 <font color="red">*</font> 图书预读：
						</td>
						<td class="lists_tleft">
						<textarea name="books.part" id="books.part">
						<s:property value="books.bkAuthor"/>
						</textarea>
						</td>
					</tr>-->	
					<tr>	
						<td align="left">
							<font color="red">*</font>图书地址：
						</td>
						<td class="lists_tleft">
						<input type="text" size='80' name="books.bkUrl" id="books.bkUrl" value="<s:property value="books.bkUrl"/>"/>
						</td>
					</tr>
					
					<tr>
							<td align="left">
								选择课程分类：
							</td>
							<td class="lists_tleft">
								<s:select name="fuSortId" id="fuSortId" list="courseSortList"
									listKey="coursesortId" listValue="coursesortName" 
									theme="simple" onchange="onchangeShow(this.value);">
								</s:select>
								<select name="suSortId" id="suSortId"
											onchange="onchangeSecond(this.value);">
									<option value="<s:property value='suSortId'/>"><s:property value="suSortIdString"/></option>
								</select>
								
								<select name="tuSortId" id="tuSortId"
										 onchange="onchangeThird(this.value);">
									<option value="<s:property value='tuSortId'/>"><s:property value="tuSortIdString"/></option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left">
								选择课程：
							</td>
							<td class="lists_tleft">
								<select name="courseId" id="courseId"
										 onchange="onSelectCourse(this.value);">
									<option value="<s:property value='cuSortId'/>"><s:property value="cuSortIdString"/></option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left">
								选择课程节点：
							</td>
							<td class="lists_tleft" style="color: #ff0000;"> 
		    				<input id="books.pointid" type="hidden" name="books.pointid"  value="<s:property value="books.pointid"/>"/>
		    				<input id="kpoint.courseId" type="hidden" name="kpoint.courseId"  />
		    				<input id="pName" type="text" name="pName" value="<s:property value="kpoint.name"/>" onclick="showPkpoint();" readonly="readonly" />
		    				<div id="kpointList" style="position:absolute;width:340px; height:200px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
							</div>
		    		</td>
						</tr>
					<!--  	
					<s:iterator value="pictureList" id="inpic">
					<s:if test="#inpic.isIndex==1">
					<input type="hidden" name="mainpicid" value="<s:property value="#inpic.picId"/>"/>
					<tr>
							<td align="left">
								<font color="red">*</font>视频首图片
							</td>
							<td class="lists_tleft" colspan="2">-->
								
								<!-- <input type="button" value="删除" onclick="deleteRow(this)"/> -->
								<!-- <input name="firstPic" type="file" id="firstPic" onchange="changeMainPic(this,<s:property value="#inpic.picId"/>)" />
								<input type=hidden name="mainpic<s:property value="#inpic.picId"/>" id="mainpic<s:property value="#inpic.picId"/>"/>
								<br/>
								<img id="mainpicImg" src='<%=contextPath %>/back<s:property value="#inpic.picUrl"/>'/>
							
								
							</td>
						</tr>
							</s:if>
					</s:iterator>
					<tr>
							<td>
								<font color="red">*</font>已添加附属图片
							</td>
							<td class="lists_tleft">
								
							</td>
					</tr>
					<s:iterator value="pictureList" id="addpic" status="status">
					<s:if test="#addpic.isIndex==0">
					<input type="hidden" name="pic" value="<s:property value="#addpic.picId"/>"/>
					<tr>
						
							<td class="lists_tleft" colspan="2">
								
								<input name="otherPicEd" type="file" id="otherPic<s:property value="#status.index"/>" onchange="changeOtherPic(this,<s:property value="#addpic.picId"/>)" />
								<a href='<%=contextPath%>/res/books!deletePic.action?books.bkId=<s:property value="books.bkId"/>&mainpicid=<s:property value="#addpic.picId"/>'>删除</a>	
								<input type=hidden name="otherPicEd<s:property value="#addpic.picId"/>" id="otherPicEd<s:property value="#addpic.picId"/>"/><!-- 是否修改的标志 -->
								<!-- <br/>
							<img id='otherpicImged<s:property value="#status.index"/>' src='<%=contextPath %>/back<s:property value="#addpic.picUrl"/>'/>
							</td>
							
						</tr>
						
						</s:if>	
					</s:iterator>
					<tr>
	
							<td colspan="2">
								<input type="button" value="添加附属图片" onclick="addPic();"/>
							</td>
							
						</tr>
				 -->	
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
		<script language="javascript">
		var bkType=document.getElementById("books.bkType");
		for (i=0;i<bkType.options.length;i++)
		{ 
		     if(bkType.options[i].value=='${books.bkType}')
		     {
		         bkType.options[i].selected=true;break;
		     } 
		}
		</script>
	</body>
</html>
