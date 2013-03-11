<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加新课程</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />

		<script type="text/javascript">
	/*	$().ready(function() {

			$("#courseform").validate({   
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
		            },
		            "course.teacherId" : {
		            	required : true,
		            	min : 0
		            },
		        	"course.tjMode":{
		        		required:true,
		        		min:0
		        	},
		        	"course.subjectId":{
		        		required:true,
		        		min:0
		        	},
		        	"course.gradeId":{
		        		required:true,
		        		min:0
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
				    },
				    "course.teacherId" : {
				    			required : "请选择教师",
				            	min : "请选择教师"
				    },
				    "course.tjMode":{
		        		required:"请选择推荐模式",
		        		min:"请选择推荐模式"
		        	},
		        	"course.subjectId":{
		        		required:"请选择专业",
		        		min:"请选择专业"
		        	},
		        	"course.gradeId":{
		        		required:"请选择所属年份",
		        		min:"请选择所属年份"
		        	}
		        }   
   	 		});  
		})	
		
		
		function onchangeShow(pId){ //第一个节点修改
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
			
			function onchangecallbackcourse(result){ //处理返回的课程JSON  
				
				document.getElementById('precourseId').options.length = 0;  //清空原有的option
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#precourseId").html(str); 
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
			
			function onchangeSecond(pId){//第二个分类变动
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
			
			function onchangeThird(pId){//第三个分类变动
				
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
			
			function onchangeCourse(pId){
				document.getElementById('course.precourseId').value = pId;
				
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
			 
			
			
			function changeMainPic(ipt) {
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
			
			function changeServicePic(ipt) {
				var img = document.getElementById("servicePicImg");
				var div = document.getElementById("servicePicDiv");
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
			
			function IsIE() {
			    if (window.navigator.userAgent.indexOf("MSIE")>=1) {
			        //IE浏览器
			        return true;
			    }else{
			        return false;
			    }
			}
			
			function changePic(ipt) {
				var img = document.getElementById("picImg" + ipt.id.substring(4, ipt.id.length));
				var div = document.getElementById("picDiv" + ipt.id.substring(4, ipt.id.length));
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
			 
			function addRow(){	//添加行
				
				var tb;
				if (IsIE()==true){
			        tb = document.getElementById('tagTb');//table
			    }else{
			    	tb = document.getElementById('tblList');//tbody
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
				
				var div = document.createElement("div");
				div.className = "uploadPic";
				div.id = "picDiv" + no;
			
				td.appendChild(br);
				td.appendChild(div);
				td.appendChild(img);
				
				tb.appendChild(row4);
			   
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
			    	tb = document.getElementById('tblList');
			    }
				
				var mynownum = rownum - 24;//第几题
				
				var yunum = tb.rows.length-rownum;//余下为多少
				
				var tempindex = rownum;
				for(j = 0; j < yunum -1; j ++){
					mynownum ++;
					tempindex ++;
					tb.rows[tempindex].cells[0].innerHTML = "图片序号:" + mynownum;
				}
				
				tb.deleteRow(rownum);
			}
			
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}
*/
			function addCourseArea()
			{
				var curCourseId=document.getElementById("courses").value;
				var courseArea=document.getElementById("courseArea")
				if(courseArea.innerText!="")
				{
					if(courseArea.innerText.indexOf(curCourseId)!=-1)
					{
						alert("已经添加过该课程");
					}else
					{
						courseArea.innerText=courseArea.innerText+","+curCourseId;
					}
				}else
				{
					courseArea.innerText=curCourseId
				}
			}
			function delCourse()
			{
				if(confirm("是否删除该售卖方式下的所选课程"))
				{
					document.sellwayform.action="cou/sellway!delCourseForSellWay.action";
					document.sellwayform.submit();
					return true;
				}else
				{
					return false;
				}
				
			}
			

				
		</script>
	</head>
	<body>
<div>
	<form action="cou/sellway!addCourseForSellWay.action" method="post" name="sellwayform" id="sellwayform">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">为售卖方式添加课程</font>
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
						<td width="200">
							售卖方式名
						</td>
						<td class="lists_tleft">
						<input type="text" readonly="readonly" name="sellWay.sellName" value='<s:property value="sellWay.sellName"/>'  />
						<s:hidden name="sellWay.sellId"></s:hidden>
						</td>
					</tr>
					<tr>
						<td>
							所属专业
						</td>
						<td class="lists_tleft">
							
							<input type="text" readonly="readonly" name="subjectName" value='<s:property value="subject.subjectName"/>'  />
						</td>
					</tr>
				<!--  	<tr>
						<td>
							课程列表
						</td>
						<td class="lists_tleft">
							<s:select id="courses" list="courseList" listKey="courseId" listValue="title"></s:select>
							<input type="button" value="添加课程" onclick="addCourseArea()" />
						</td>
					</tr>
				
					<tr>
						<td>
							已选课程ID列表
						</td>
						<td class="lists_tleft">
							<textarea name="courseIdList" id="courseArea" rows="2" cols="40" readonly="readonly"></textarea>
						</td>
					</tr>
				-->
					<tr>
						<td>选择课程</td>
						<td  class="lists_tleft">
								<s:checkboxlist name="courseIdLists" list="courseList" listKey="courseId" listValue="title" theme="" >								
								</s:checkboxlist>
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
				<s:submit value="添加课程" /><input type="button" value="删除课程" onclick="return delCourse()" />
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
