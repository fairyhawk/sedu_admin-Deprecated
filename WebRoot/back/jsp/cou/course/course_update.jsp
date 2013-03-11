<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程更新</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=importURL%>/uploadify/uploadify.css" />
        <script type="text/javascript" src="<%=importURL%>/uploadify/swfobject.js"></script>
        <script type="text/javascript"  src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4_headimg.js"></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>
		
		<script type="text/javascript">
		var teacherNum=0;
			$().ready(function() {
			// 初始化 Kindeditor
    	        KE.show({
    	                    id : 'txt_topic',
    	                    resizeMode : 1,
    	                    allowPreviewEmoticons : false,
    	                    allowUpload : true,
    	                    syncType : 'auto',
    	                    urlType : 'absolute',
    	                    imageUploadJson : '<%=keImageUploadJsonAction%>?cusid=9999',
    	                    allowFileManager : false,
    	                    items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
    	                        'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
    	                        'code', 'image', 'flash', '|']
    	                });
			$("#courseupdateform").validate({   
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
			if("${saveSuccess}"=='yes'){
	 			alert("保存成功！");
	 		}
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
					url : "<%=contextPath%>/cou/coursesort!getSortById.action",  
					data : {sortId : $("#tuSortId").val()},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:function(result){
						$("#subjectId").val(result.entity);
					}  
					});
			} 
			
		/* 	function onchangecallbackcourse(result){ //处理返回的课程JSON  
				
							var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
			//	$("#precourseId").html(str); 
	   		}  */
					
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
			
			function checkSubmit(){
				var fid = document.getElementById('course.sortId').value;
				
				if(fid == null || fid == -1 || fid == 0){
					document.getElementById('course.sortId').value=0;
					//做警告，通知其添加分类
				}
			}
			
			function changeMainPic(ipt,num) {
				var img = document.getElementById("mainpicImg");
				img.style.display = "none";
				var div = document.getElementById("mainpicDiv");
				document.getElementById('mainpic' + num).value="1";
				
				
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
			
			function changeOtherPic(ipt, num, index) {
				var img = document.getElementById("otherpicImged" + index);
				img.style.display = "none";
				
				var div = document.getElementById("otherpicDiv" + index);
				
				document.getElementById('otherPicEd' + num).value="1";
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
				
				var oldotherslength = document.getElementsByName("otherPiced");
				var mynownum = rownum - (24 + oldotherslength.length);//第几题
				
				var yunum = tb.rows.length-rownum;//余下为多少
				
				var tempindex = rownum;
				for(j = 0; j < yunum -1; j ++){
					mynownum ++;
					tempindex ++;
					tb.rows[tempindex].cells[0].innerHTML = "图片序号:" + mynownum;
				}
				
				tb.deleteRow(rownum);
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
			
			var fileuploadIndex=0;
			  function uploadifyUpload(){
				 if(fileuploadIndex==0)
				 {
					 alert("请选择图片 ");
					 return;
				 }	
				 /*		
				 var num = $('#fileupload').uploadifySettings('queueSize');
				 if (num == 0) {
				 alert("请选择图片 ");
				 return;
				 }	*/		
				$('#fileupload').uploadifyUpload();
		
			} 
			 $(document).ready(function()
					    {
					        $("#fileupload").uploadify({
					                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
					                    'script'  :'http://tp.highso.cn:8080/upload!go.action?param=course',
					                     'queueID':'fileQueue',
					                    'fileDataName':'fileupload',
					                    'auto':false,
					                    'multi':false,
					                    'hideButton':false,
					                    'buttonText':'Browse',
					                     'buttonImg':'<%=importURL%>/uploadify/liulan.png',
					                     'width':105,
					                    'simUploadLimit' : 3,
					                    'sizeLimit'      : 512000,
					                    'queueSizeLimit' : 2,
					                    'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
					                    'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
					                    'folder' : '/upload',
					                    'cancelImg':'<%=importURL%>/uploadify/cancel.png',
					                     onSelect:function(event, queueID, fileObj){$('#fileupload').uploadifyUpload();
					                     	fileuploadIndex=1;
					                    	$("#fileQueue").html("");
					                    	if(fileObj.size>512000){
					                    		alert("文件太大最大上传512kb");
					                    		return;
					                    	}
					                    	
					                    	},
					                    onComplete: function (event, queueID, fileObj, response, data)
					                    { 
					                    	$("#couponpic").attr("src","http://import.highso.org.cn/upload/course/"+response);
					                    	$("#vedioPicUrl").val(response);
					                    	$("#couponpic").show();
					                    },
					                    onError: function(event, queueID, fileObj,errorObj)
					                    {
					                    	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
					                       // alert("文件:" + fileObj.name + "上传失败");
					                    	
					                    }/* ,
					                    onCancel: function(event, queueID, fileObj)
					                    {
					                        alert("取消了" + fileObj.name);
					                    } */
					                });

					    });
			 var cid='${course.courseId}';
 			 function baseAttribute(obj){
				 
				if(cid==''){
					 obj.href='<%=contextPath%>/cou/course!toAddCourse.action';
				 }else{
					 obj.href="<%=contextPath%>/cou/course!toUpdateCourse.action?course.courseId=${course.courseId}&course.sortId=${course.sortId}";
				 }
			 }
			 function detailAtribute(obj){
				 if(cid==''){
					 alert("请先保存课程基本属性！");
				 }else{
					 obj.href="<%=contextPath %>/cou/kpoint!toAddOrUpdateKpoint.action?kpoint.courseId=${course.courseId}&subjectId=${subjectId}";
				 }
			 }
			 
			 function saveCourse(){
				 if(validateInfo()!=""){
					 alert(validateInfo());
					 return false;
				 } 
				if(cid==''){
					var realNum =  document.getElementById("course.realUpNum").value;
					if (realNum ==null || realNum==""){
						$("#course.realUpNum").val(0);
						document.getElementById("course.realUpNum").value=0;
					}
					$("#courseupdateform").attr("action","<%=contextPath %>/cou/course!addCourse.action");
				} else{
					$("#courseupdateform").attr("action","<%=contextPath %>/cou/course!updateCourse.action");
				}
				$("#courseupdateform").submit();
				 }
			 function validateInfo(){
				 var message="";
				 if($("#teacherhidden").val()==""){
					 message+="主讲教师不能为空！\n";
				 }
				 return message;
			 }
			 function closeteacher(){
					$('#piaoteacher').hide();
				}
			 var mythids="${course.teacherName}"+"";
				//添加教师
			 function addteacher(){
					if(getSubjectId()==""||getSubjectId()==0){
						alert("请选择所属项目专业");
						return false;
					}
					$("#piaoteacher").show();
					$("#teacherhtml").html("");
					$.ajax({
						url :  "<%=contextPath%>/cou/teacher!getTeacherBySubjectId.action",
						data : {
							"subjectId" : getSubjectId()
						},
						type : "post",
						dataType : "json",
						cache : false,
						async : false,
						success : function(result) {
							if(result == null || result.entity == null) {				
								return;
							}				
							var teacher = result.entity;				
							var html = '';
							for(var i=0; i<teacher.length; i++) {
								html +="<span><input type='checkbox' name='teacherIds' title='"+teacher[i].name+"'  onclick=\"checkdelteacher(this,'"+teacher[i].name+"')\" value='"+teacher[i].tcId +"'/>"+ teacher[i].name+"</span>";
							}
							$("#teacherhtml").html(html);
							 if(mythids==null||mythids==""){
								var myteacher="${course.teacherName}";
								var teachers=myteacher.split(" ");
								 $('input[name="teacherIds"]').each(function(){
									 var str=$(this).attr("title");
									 for(var i=0;i<teachers.length-1;i++){
										 if(str == teachers[i])
										 {  
											 $(this).attr("checked","checked");	
									     }
									 }
								 });
								

							}else{
							 $('input[name="teacherIds"]').each(function(){ $(this).attr("checked",""); });  
							 var  szids=$("#teacherhidden").val().split(" ");
							 $('input[name="teacherIds"]').each(function(){
								 var str=$(this).attr("title").replace(" ","");
								 for(var i=0;i<szids.length;i++){
									 if(str==szids[i])
									 {
										 $(this).attr("checked","checked");	
								     }
								 } 
							 });  
						    }
						},
						error : function(error) {
							alert(error);
						}
					});
				}
			 function addteacherDo()
				{
					    var teachers=$("#teacherhidden").val();
					    var str="";
					   $('input[name="teacherIds"]:checked').each(function(){ 
						   if(teachers.indexOf($(this).attr("title"))==-1){
						      teachers+=" "+$(this).attr("title");
						      teacherNum=teacherNum+1;
							 str+="<p style='width:100%;margin: 0 0 0em' id='teahcerid"+teacherNum+"' name='teacherName' title='"+$(this).attr("title")+"'>"+$(this).attr("title")+"&nbsp;&nbsp;<a href='javascript:void(0)' onclick=delteacher('"+teacherNum+"','"+$(this).attr("title")+"')>删除</a></p> ";
						   }
					   });  
					   
					   $("#teacherstr").append(str);
					   $("#teacherhidden").val(teachers);
					   closeteacher();
				}
			 function delteacher(teacherId,title){
					var zj=confirm("确定要删除【"+title+"】");
					if(zj){
					$("#teahcerid"+teacherId).remove();
					var teahcers=$("#teacherhidden").val();
					teahcers=teahcers.replace(" "+title,"");
					 $("#teacherhidden").val(teahcers);
					}
				}
			 function closeteacher(){
					$('#piaoteacher').hide();
				}
			 function  checkdelteacher(obj,teacherName){
					if(obj.checked==false)
						{
						var zj=confirm("您正在取消【"+teacherName+"】");
					    if(!zj){
					    	obj.checked=true;
					    }else{
					    	var teacherId=obj.title;
				           $("p[title='"+teacherName+"']").remove();
				           var teahcers=$("#teacherhidden").val();
						   teahcers=teahcers.replace(" "+teacherName,"");
							 $("#teacherhidden").val(teahcers);
					    }
						}
				}
			 function clearteacher()
				{
					$("#teacherhidden").val("");
					 $("#teacherstr").html("");
				}
			 function getSubjectId(){
					return $("#subjectId").val();
				}
			
			//判断计划课时和实际课时的大小
			function checkNum(){
			$("#msg").html("");
			var lineTime = document.getElementById("course.lessionTime").value;
			var realNum =  document.getElementById("course.realUpNum").value;
			if(realNum!=null && realNum!="" && lineTime!=null && lineTime!=""){
			var line1 = parseInt(lineTime);
			var real = parseInt(realNum);
			if(line1<real){
			//alert("计划课时不能小于实际课时");
			$("#msg").html("计划课时不能小于实际课时");
			}
			}
			}
			
		</script>
		
		<style >
		body{margin-left: 10px;}
		body{ font-size:14px; color:#444444; background:#fff; font-family:"����", "Tahoma", "Helvetica Neue", Arial, Helvetica, sans-serif; }
       .dotline {float:left;BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted;width:60%;height:1px;margin-top: 15px;margin-bottom: 10px}
       div{padding-bottom:10px}
       span{float:left;padding-right: 5px;width:80%}
       .leftspan{padding-left:}
       .border0{
       border:0px;
       }
       #fileuploadUploader{float: left;}
        </style>
	</head>
	<body>
<div>
	<form action="?" method="post" name="courseupdateform" id="courseupdateform" enctype="multipart/form-data">
	<input type="hidden" name="course.subjectId" id="subjectId" value="${subjectId}"/>
	<input type="hidden" name="course.teacherName" id="teacherhidden" value="${course.teacherName}"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft"><a href="javascript:void(0)" onclick="baseAttribute(this)">1.课程基本属性 </a></font>
				<font class="lists_fleft"><a href="javascript:void(0)" onclick="detailAtribute(this)">2.课程详细属性</a>
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
							<font color="red">*</font>所属项目专业
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
						<td width="200">
							<font color="red">*</font>课程名称
						</td>
						<td class="lists_tleft" style="color: #ff0000;">
							<input type="text" name="course.title" value="<s:property value="course.title"/>" class="{required:true,minlength:1,maxlength:100}" style="width:50%;"></input>
						</td>
					</tr>
					<tr>
							<td>
								<font color="red">*</font>课程简述
							</td>
							<td class="lists_tleft" style="color: #ff0000;">
								<textarea rows="" cols="" name="course.introduce" class="{required:true,minlength:1,maxlength:500}" style="height:80px;width:99%;"><s:property value="course.introduce"/></textarea>
							</td>
					</tr>
					<tr>
							<td>
								<font color="red">*</font>计划上传课时
							</td>
							<td class="lists_tleft" style="color: #ff0000;">
								<input type="text" id="course.lessionTime" name="course.lessionTime" value="<s:property value="course.lessionTime"/>" class="{required:true,digits:true,min:0,maxlength:20}" onblur="checkNum();"></input><div id="msg"></div>
							</td>
					</tr>
					<tr>
							<td>
								实际上传课时
							</td>
							<td class="lists_tleft">
							<input type="hidden" id="course.realUpNum" name="course.realUpNum" value='<s:property value="course.realUpNum"/>'/>
							<span id="realNum">
							<s:property value="course.realUpNum"/>
							</span>			
							</td>
					</tr>
					<tr>
							<td>
								上传进度
							</td>
							<td class="lists_tleft">
							<span id="course.upRate" name="course.upRate"/><s:property value="course.upRate"/>%</span>						
							</td>
					</tr>			
					<tr>
	      				<td>
       						<font color="red">*</font>主讲教师
       					</td>
       					<td class="lists_tleft">
       						<a href="javascript:void(0)" style="cursor: pointer;" onclick="addteacher()">添加教师</a>&nbsp;&nbsp;
       						<a href="javascript:void(0)" style="cursor: pointer;" onclick="clearteacher()">清空</a>
    						<span style="float: left;padding-left: 73px" id="teacherstr">
  							<script >
    							var teachers='${course.teacherName}'.split(" ");
							    for(var i=0;i<teachers.length;i++)
							    	{
							    	if(teachers[i].replace(" ","")!="")
							    	{
								    	teacherNum=teacherNum+1;
								    	var str="<p style='width:100%;margin: 0 0 0em' id='teahcerid"+teacherNum+"' title='"+teachers[i]+"'>"+teachers[i]+"&nbsp;&nbsp;<a href='javascript:void(0)' onclick=delteacher('"+teacherNum+"','"+teachers[i]+"')>删除</a></p> ";
								        document.write(str);
						    			}
						    		}
						    </script> 
    						</span>
       					</td>
      				</tr>
					<tr>
							<td>
								讲义打包地址
							</td>
							<td class="lists_tleft">
								<input type="text" name="course.dpptUrl"
									value="<s:property value="course.dpptUrl"/>"
									class="{required:false,minlength:0,maxlength:200}" style="width:50%;"></input>
							</td>
						</tr>
						<tr>
							<td>
								PDF打包地址
							</td>
							<td class="lists_tleft">
								<input type="text" name="course.dpdfUrl"
									value="<s:property value="course.dpdfUrl"/>"
									class="{required:false,minlength:0,maxlength:200}" style="width:50%;"></input>
							</td>
						</tr>		
						<tr>
							<td>
								音频打包地址
							</td>
							<td class="lists_tleft">
								<input type="text" name="course.dmp3Url"
									value="<s:property value="course.dmp3Url"/>"
									class="{required:false,minlength:0,maxlength:200}" style="width:50%;"></input>
							</td>
						</tr>			
					<tr>
	      				<td>课程图片</td>
	      				<td class="lists_tleft">
 							<span style="border:0px;padding-top: 2px;padding-left: 2px;position: absolute;">
 								<input type="file"  id="fileupload" style="float: left" />
 								<input type="button" onclick="uploadifyUpload()"  value="上传" style="width: 100px;height: 30px;padding-top: 0px"/></span>
								<div id="fileQueue" style="margin-top: 30px;border:0px"></div>
  							<span style="float:left;">
  							<s:if test="course.courseId!=''">
  								<s:if test="course.vedioPicUrl==''">
    								<img src="http://import.highso.org.cn/images/usercenter/leftnav_2.gif" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
  								</s:if>
   								<s:if test="course.vedioPicUrl!=''">
   									<img src="http://import.highso.org.cn/upload/course/${course.vedioPicUrl}" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
   								</s:if>
  							</s:if>
   							<s:else>
   								<img src="" alt="" width="100px" height="100px" style="display: none;padding-left: 80px" id="couponpic"/>
   							</s:else>	
   							</span> 
   						</td>
   					</tr> 
					<tr>
							<td>
								更新说明
							</td>
							<td class="lists_tleft">
							 <textarea id="txt_topic" name="course.content" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;">
			    		  <s:property value="course.content" escape="false"/>
			    		  </textarea>
							</td>
						</tr>
					<tr>
						<td>
							课程目录
						</td>
						<td class="lists_tleft">
							<textarea rows="" cols="" name="course.courseDirectory" class="{minlength:1,maxlength:500}" style="height:80px;width:99%;"><s:property value="course.courseDirectory"/></textarea>
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
				<input type="hidden" name="course.tjVedioId" id="course.tjVedioId" value="<s:property value="course.tjVedioId"/>" />
				<input type="hidden" name="course.vedioPicUrl" id="vedioPicUrl" value="<s:property value="course.vedioPicUrl"/>" />
				<input type="hidden" name="hidTotal" id="hidTotal" value="0" /><!-- 一直递增量 -->
				<input type="hidden" name="xuhao" id="xuhao" value="0" /><!-- 序号 -->
				<input type="hidden" name="searchKey" value="<s:property value="searchKey"/>"/>
		   		<s:hidden name="course.sortId" id="course.sortId" ></s:hidden>
		   		<s:hidden name="course.courseId"></s:hidden>
				<input  type="button" value="提交课程" onclick="saveCourse()"/>
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
<div id="piaoteacher" style="position: absolute;border: 0px;display: none;z-index:999;left:420px;top:340px;width:300px;height:300px;overflow: auto;">
		<!--查询-->
		<div class="border0">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">请选择要添加的老师</font> <font
						class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
			</table>
		</div>
		<div style="margin: 0px;border:0px">
			<form name="searchForm" action="" method="post" style="margin: 0px;">
				<table width="98%" border="0" cellspacing="1" cellpadding="0"
					class="lists_info" align="center">
					<tr>
					<td colspan="2" id="teacherhtml" style="text-align: left">
			
					</td>
					</tr>
					<tr>
							<td width="48%"> <a href="javascript:addteacherDo()"><font size="3">确定</font></a></td>
							<td> <a href="javascript:closeteacher()"><font size="3">关闭</font></a></td>
					</tr>
				</table>
			</form>
		</div>
		<!--查询结束-->
	</div>
<script>

      //属性
     var pro="${course.disProperty}";
     var  pros=pro.split(",");
     for(var i=0;i<pros.length;i++)
     {
     if(pros[i]=="1"){
     $("#pro1").attr("checked","checked");
     }
     if(pros[i]=="2"){
     $("#pro2").attr("checked","checked");
     }
     if(pros[i]=="3"){
     $("#pro3").attr("checked","checked");
     }
     if(pros[i]=="4"){
     $("#pro4").attr("checked","checked");
     }
     if(pros[i]=="5"){
     $("#pro5").attr("checked","checked");
     }
     }

  //地址
     var dis="${course.disPosition}";
     var  diss=dis.split(",");
     for(var i=0;i<diss.length;i++)
     {
      $('input[name="disPosition"]').each(function(){  
			   var str=$(this).val();
                if(diss[i]==str){
                $(this).attr("checked","checked");
                }
			   });  
     }
     
	 
   </script>
	</body>
</html>
