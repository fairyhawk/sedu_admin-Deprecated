<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.shangde.edu.sys.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>信息添加——视频基本信息设置</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
	    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
	<script type="text/javascript">
		$().ready(function() {
			$("#addVedioForm").validate({   
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
					url : "<%=contextPath%>/res/vedio!addVedioPoint.action",  
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
			document.getElementById("vedio.pointid").value = id;
			document.getElementById("pName").value = name;
			//alert(document.getElementById("vedio.pointid").value);
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
				
				var mynownum = rownum - 10;//第几题
				
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
	<body class="manage">
<div>
	<s:form action="vedio!addVedio" method="post" name="addVedioForm" id="addVedioForm" enctype="multipart/form-data">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">回复意见</font>
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info"   id="addTable" >
				<tbody id="tagTb">
					<tr>
						<td align="left">
							<font color="red">*</font>视频名称：
						</td>
						<td class="lists_tleft">
							<input type="text" name="vedio.voName" id="vedio.voName" size=100
								value="<s:property value="vedio.voName"/>" class="{required:true,minlength:4,maxlength:50}"/>
						</td>
					</tr>
					
					<tr>
						<td align="left">
							<font color="red">*</font>讲师名称：
						</td>
						<td class="lists_tleft">
							<select name="vedio.tcId"  id="vedio.tcId" style="width:155px"  class="{required:true,maxlength:50}">
								<option value="">---请选择---</option>
								<s:iterator value="teacherList" id="teacher">
								<option value="<s:property value="#teacher.tcId"/>"><s:property value="#teacher.name"/></option>
								</s:iterator>
							</select>
							
						</td>
					</tr>
					<tr>
						<td align="left">
							<font color="red">*</font>视频描述：
						</td>
						<td class="lists_tleft">
							<input type="text" name="vedio.content" id="vedio.content"
								value="<s:property value="vedio.content"/>" class="{minlength:4,maxlength:200}"/>
						</td>
					</tr>
					<tr>
						<td align="left">
							<font color="red">*</font>视频地址：
						</td>
						<td class="lists_tleft">
							<textarea rows="10" cols="100"name="vedio.voUrl" id="vedio.voUrl"></textarea>
						</td>
					</tr>
					
					<tr>
						<td align="left">
							<font color="red">*</font>首选视频：
						</td>
						<td class="lists_tleft">
							
							<input type="radio" name="vedio.voRadOne" value="1"/>是
							<input type="radio" name="vedio.voRadOne" value="0" checked/>否
						</td>
					</tr>
					<tr>
						<td align="left">
							<font color="red">*</font>是否允许视听：
						</td>
						<td class="lists_tleft">
							
							<input type="radio" name="vedio.isAudition" value="1"/>普通可试听
							<input type="radio" name="vedio.isAudition" value="0" checked/>否
							<input type="radio" name="vedio.isAudition" value="2" />完善资料可试听
						</td>
					</tr>
					<tr>
						<td align="left">
							<font color="red">*</font>是否为选修视频：
						</td>
						<td class="lists_tleft">
							
							<input type="radio" name="vedio.xuanXiu" value="1"/>是
							<input type="radio" name="vedio.xuanXiu" value="0" checked/>否
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
	    				<input id="vedio.pointid" type="hidden" name="vedio.pointid"  />
	    				<input id="kpoint.courseId" type="hidden" name="kpoint.courseId"  />
	    				<input id="pName" type="text" name="pName" onclick="showPkpoint();" readonly="readonly" />
	    				<div id="kpointList" style="position:absolute;width:340px; height:200px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
						</div>
	    		</td>
					</tr>
					<!--  <tr>
						<td>
							<font color="red">*</font>视听次数：
						</td>
						<td class="lists_tleft">
							<input type="text" name="vedio.viewNum" id="vedio.viewNum"
								value="<s:property value="vedio.viewNum"/>" />
						</td>
					</tr>
					
					<tr>
						<td>
							<font color="red">*</font>是否分享
						</td>
						<td class="lists_tleft">
							<input type="text" name="vedio.isShare" id="vedio.isShare"
								value="<s:property value="vedio.isShare"/>" />
						</td>
					</tr>
					<tr>
						<td>
							收藏数量：
						</td>
						<td class="lists_tleft">
							<input type="text" name="vedio.collectionNum" id="vedio.collectionNum"
								value="<s:property value="vedio.collectionNum"/>" />
						</td>
					</tr>
					<tr>
						<td>
							是否允许试听：
						</td>
						<td class="lists_tleft">
							<input type="text" name="vedio.isAudition" id="vedio.isAudition"
								value="<s:property value="vedio.isAudition"/>" />
						</td>
					</tr>
				-->
			<!-- 图片上传 -->
					
					<tr>
						<td align="left">
							<font color="red">*</font>视频首图片
						</td>
						<td class="lists_tleft" colspan="2">
							<input name="fileList" type="file" id="firstPic" onchange="changeMainPic(this)"/>
							<!-- <input type="button" value="删除" onclick="deleteRow(this)"/> -->
							<div class="uploadPic" id="mainpicDiv"></div>
							<img id="mainpicImg" style="display:none"/>
						</td>
					</tr>
				<tr>
						<td class="lists_tleft" colspan="2">
							<input type="button" value="添加附属图片" onclick="addPic();"/>
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
				<input type="hidden" name="hidTotal" id="hidTotal" value="0" /><!-- 一直递增量 -->
				<input type="hidden" name="xuhao" id="xuhao" value="0" /><!-- 序号 -->
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
