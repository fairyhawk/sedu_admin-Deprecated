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
		<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
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
				
				//document.getElementById('precourseId').options.length = 0;  //清空原有的option
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				//$("#precourseId").html(str); 
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
					                    'script'  :'http://tp.highso.cn:8080/upload!go.action?param=shop',
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
					                    	$("#couponpic").attr("src","http://import.highso.org.cn/upload/shop/"+response);
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
	<form action="cou/course!addCourse.action" method="post" name="courseform" id="courseform" enctype="multipart/form-data">
	<input type="hidden" name="course.vedioPicUrl" id="vedioPicUrl"/>
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
							<s:select name="fSortId" id="fSortId" list="courseSortList"
								listKey="coursesortId" listValue="coursesortName" theme="simple"
								onchange="onchangeShow(this.value);">
							</s:select>
							<s:select name="sSortId" id="sSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple"
								onchange="onchangeSecond(this.value);">
							</s:select>
	
							<s:select name="tSortId" id="tSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple"
								onchange="onchangeThird(this.value);">
							</s:select>
						</td>
					</tr>
					<tr>
						<td width="200">
							<font color="red">*</font>课程名称
						</td>
						<td class="lists_tleft">
							<input type="text" name="course.title" id="course.title"
								class="{required:true,minlength:1,maxlength:100}"  style="width:50%;"/>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>课程简述
						</td>
						<td class="lists_tleft">
							<textarea rows="" cols="" name="course.introduce" class="{required:true,minlength:1,maxlength:500}" style="height:80px;width:50%;"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>课时
						</td>
						<td class="lists_tleft">
							<input type="text" name="course.lessionTime"
								id="course.lessionTime"
								class="{required:true,digits:true,min:0,maxlength:20}"></input>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>主讲教师
						</td>
						<td class="lists_tleft">
							<input type="text" name="course.teacherName"
								id="course.teacherName"
								class="{required:true,minlength:1,maxlength:100}" style="width:50%;"></input>
							
						</td>
					</tr>
					<tr>
     					<td><font color="red">*</font>课程图片</td>
     					<td class="lists_tleft">
 							<span style="border:0px;padding-top: 2px;padding-left: 2px;position: absolute;">
 								<input type="file"  id="fileupload" style="float: left" />
 								<input type="button" onclick="uploadifyUpload()"  value="上传" style="width: 100px;height: 30px;padding-top: 0px"/>
 							</span>
							<div id="fileQueue" style="margin-top: 30px;border:0px"></div>
   							<span style="float:left;">
   								<img src="" alt="" width="100px" height="100px" style="display: none;padding-left: 80px" id="couponpic"/>
   							</span>   
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>更新说明
						</td>
						<td class="lists_tleft">
						<textarea id="txt_topic" name="course.content" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>课程目录
						</td>
						<td class="lists_tleft">
							<textarea rows="" cols="" name="course.courseDirectory" class="{required:false,minlength:0,maxlength:500}" style="height:80px;width:50%;"></textarea>
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
				<input type="hidden" name="hidTotal" id="hidTotal" value="0" /><!-- 一直递增量 -->
				<input type="hidden" name="xuhao" id="xuhao" value="0" /><!-- 序号 -->
				<s:hidden name="course.precourseId" id="course.precourseId"></s:hidden>
				<s:hidden name="course.sortId" id="course.sortId"></s:hidden>
				<s:submit value="提交课程" />
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
