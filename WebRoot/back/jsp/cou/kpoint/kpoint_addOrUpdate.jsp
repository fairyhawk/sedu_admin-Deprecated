<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程节点</title>

	    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
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
	 		if(document.getElementById("kpoint.pId").value == 0){
	 			document.getElementById("kpoint.pId").value = -2;
	 		}
	 		$("#kpointupdateform").validate({   
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
	 		if("${saveSuccess}"=='yes'){
	 			alert("保存成功！");
	 		}
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
				
	
			}
			
			function checkSubmit(){
				var fid = document.getElementById('kpoint.courseId').value;
				
				if(fid == null || fid == -2 || fid == 0){
					document.getElementById('course.sortId').value=0;
					alert(请选择课程);
					return false;
				}
				
				var level = document.getElementById('kpoint.level').value;
				
				if(typeof(level) =='undefined' || level == 0){
					document.getElementById('kpoint.level').value = 1;
				}
				return true;
			}
			
			//更新父类节点
			function changeColumnValue(id,name,level){
				var iLevel = parseInt(level);
				if(level == 0){
					document.getElementById("kpoint.level").value = 1;
				}else{
					document.getElementById("kpoint.level").value = Number(level) + 1;
				}
				
				if(id > 0){
					document.getElementById("kpoint.pId").value = id;
				}
				
				document.getElementById("pName").value = name;
				document.getElementById(obj).style.display="none";
			}
			
			function showPkpoint(){
				obj="kpointList";
				document.getElementById(obj).style.display="block";
			}
			
			function closeColumn(){
				obj="kpointList";
				document.getElementById(obj).style.display="none";
			}
			//删除知识点
			function deleteKnowLedge(obj){
				//获取删除知识点的ID，并且取出该ID值的最后一位数字，用于判断应该删除哪一个知识点
				var deleteId=obj.id;
				var str=deleteId.split("-");
				var knowledgePoint="trKnowledgePoint-"+str[1];
				var timePoint="trTimePoint-" + str[1];
				$("#"+knowledgePoint+"").remove();
				$("#"+timePoint+"").remove();
				
				  
			}
			//新增知识点
			function addPoint(){
				
				var knowledgePointArray=document.getElementsByName("knowledgePoint");
				var knowledgePointLength=knowledgePointArray.length+1
				var htmlStr="<tr id=\"trKnowledgePoint-"+knowledgePointLength+"\">"
	    						+"<td align=\"right\">视频知识点"+knowledgePointLength+"：</td>"
	    						+"<td class=\"lists_tleft\">"
	    							+"<textarea name=\"knowledgePoint\" id=\"knowledgePoint-"+knowledgePointLength+"\" style=\"height:80px;width:60%;\">"
	    							+"</textarea><input type=\"button\" id=\"deletePoint-"+knowledgePointLength+"\"  value=\"删除本知识点\" onclick=\"deleteKnowLedge(this)\"/>"	
								+"</td>"
	    					+"</tr>"
	    					+"<tr id=\"trTimePoint-"+knowledgePointLength+"\"> "
    							+"<td align=\"right\">时间：</td>"
    							+"<td class=\"lists_tleft\"> "
    								+"<input type=\"text\" name=\"timePoint\" id=\"timePoint-"+knowledgePointLength+"\"  />"
    						+"</td>"
    				+"</tr>";
				$("#trTimePoint-"+knowledgePointArray.length).after(htmlStr);
			}
			
			function relatePoint(){
				 $("#kPointList").show();
				 
			 }
			function closeColumn(){
				$("#kPointList").hide();
			}
			
			function getRaleteValue(id){
				$.ajax({  
					url : "<%=contextPath%>/kno/knowledgeSystem!getKInfoJson.action",  
					data : {ksnId : id},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:function(result){
						var htmlStr="<tr name=\"trRelateKPoints\" id=\"trRelateKPoints"+id+"\">"
										+"<td colspan=\"2\"  >"
											+"<input type=\"text\" readonly=\"readonly\" name=\"relateKnowledgePoints\" id=\"relateKnowledgePoint"+id+"\" value=\""+result.entity+"\" style=\"width:80%;\"/>"
											+"<input id=\"deleteRelatekp"+id+"\" type=\"button\" value=\"删除\" onclick=\"deleteRelatekpFun("+id+")\"/>"
											+"<input type=\"hidden\" name=\"knowledgePointId\" id=\"knowledgePointId"+id+"\" value=\""+id+"\"/>"
										+"</td>"
									+"</tr>"
									+"<tr id=\"trImportantLevel"+id+"\">"
						    			+"<td align=\"right\">重要程度：</td>"
						    			+"<td class=\"lists_tleft\">"
						    				+"<input type=\"text\" name=\"importantLevel\" id=\"importantLevel"+id+"\" size=50 class=\"w6\" />"
										+"</td>"
						    		+"</tr>";
						$("#trRelateKPoint").before(htmlStr);
					} 
				});
				document.getElementById("kPointList").style.display="none";
			}
			function deleteRelatekpFun(id){
				var relateKPoints="trRelateKPoints"+id;
				var ImportantLevel="trImportantLevel"+id;
				$("#"+relateKPoints+"").remove();
				$("#"+ImportantLevel+"").remove();
				
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
					                    'script'  :'http://tp.highso.cn:8080/upload!go.action?param=vedio',
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
					                    	$("#couponpic").attr("src","http://import.highso.org.cn/upload/vedio/"+response);
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
					 obj.href="<%=contextPath%>/cou/course!toUpdateCourse.action?course.courseId=${course.courseId}&course.sortId=${course.sortId}";
			 }
			 function detailAtribute(obj){
				 if(cid==''){
					 alert("请先保存课程基本属性！");
				 }else{
					 obj.href="<%=contextPath %>/cou/kpoint!toAddOrUpdateKpoint.action?kpoint.courseId=${course.courseId}";
				 }
			 }
			 
			 function saveVedio(){
				  if(validateInfo()!=""){
					 alert(validateInfo());
					 return false;
				 } 
				 checkSubmit();
				 if($("#kpointupdateform").valid()){
					if(window.confirm(getConfirmInfo())) {
					    document.kpointupdateform.action = "<%=contextPath%>/cou/kpoint!addOrUpdateKpoint.action";
						document.kpointupdateform.submit();
					 }
				}
			 }
			 function getConfirmInfo(){
			     var planNumStr = document.getElementById("planNum").value;			   
			     var realNumStr = document.getElementById("realNum").value;
			     var planNum = parseInt(planNumStr);
			     var realNum = parseInt(realNumStr);
				 if($("#addStatus").attr("checked")){
				    if(planNum <= realNum){
				       return "该课程模块下视频已经上传完成，确定要继续上传课程？";
				    }
					 return "您正在进行新建操作，是否继续";
				 }else{
					 return "您正在进行修改操作，是否继续";
				 }
			 }
			  function validateInfo(){
				 var message="";
				 if($("#auditionYes").attr("checked")==true){
					 if($("#auditionUrl").val()==""){
						 message+="当是否可试听为是时，试听视频地址不能为空！\n";
					 }
				 }
				 if($("input[name=addOrUpdate]:checked").val() == 0 && $.trim($("#ccUrl").val()) <= 0){
						message += "新建视频时,cc地址必须输入";
					}
				 return message;
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
<%-- <div>
	<form name="courseselectform" id="courseselectform" action="<%=contextPath %>/cou/kpoint!toAddOrUpdateKpoint.action" method="post" >
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">选择课程节点所属课程</font>
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
			<div class="msg-zy">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists">
				<tr>
					<td>
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
					<td>
						选择课程：
					</td>
					<td class="lists_tleft">
						<s:select name="courseId" id="courseId" list="#{}" headerKey="-1"
							headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">
						</s:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:hidden name="kpoint.courseId" id="kpoint.courseId"></s:hidden>
						<s:submit value="查询"  />
					</td>
				</tr>
			</table>
			</div>
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
</div> --%>
<div>
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
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td width="35%">
							课程树
						</td>
						<td  width="65%">
							课程节点信息
						</td>
						<td align="right"><a href="<%=contextPath%>/cou/kpoint!fixKpoints.action?kpoint.courseId=<s:property value='kpoint.courseId'/>">修复该知识树</a></td>
					</tr>
					
					<tr>
						<td valign="top">
							<s:if test="kpointList != null">
								<script type="text/javascript">
									addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
									addkpoint.add(-2,-1,'<s:property value="course.title"/>课程树列表');
									<s:iterator value="kpointList">
										addkpoint.add(<s:property value="pointId"/> ,<s:property value="pId"/> ,'<s:property value="name"/>' + " 排序号:" + <s:property value="sort"/>,'<%=contextPath %>/cou/kpoint!toAddOrUpdateKpoint.action?kpoint.courseId=<s:property value="courseId"/>&kpoint.pointId=<s:property value="pointId"/>&subjectId=${subjectId}');
									</s:iterator>
									document.write(addkpoint);
								</script>
							</s:if>
						</td>
						<td colspan="2">
						<form action="?"  method="post" name="kpointupdateform" id="kpointupdateform">
						<style>.w6{width:60%}</style>
					    <table class="tables" align="center" border="0" cellpadding="0" cellspacing="1" style=" width:100%;">
					    	<tr>
					    		<td align="right" width="120">上级节点：</td>
					    		<td class="lists_tleft" style="color: #ff0000;"> 
					    			
					    			<input id="pName"  type="text" name="pName" onclick="showPkpoint();" value='<s:property value="pName"/>' readonly="readonly"  style="width:50%;"/>
					    			<div id="kpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
									    <script type="text/javascript">
										<!--
										addkpointlist = new dTree('addkpointlist','<%=contextPath %>/back/images/dtree');
										addkpointlist.add(-2,-1,'栏目列表 <a href="javascript:closeColumn();">关闭</a>');
										<s:iterator value="kpointList">
										addkpointlist.add('<s:property value="pointId"/>','<s:property value="pId"/>','<s:property value="name"/>','javascript:changeColumnValue(\'<s:property value="pointId"/>\',\'<s:property value="name"/>\',\'<s:property value="level"/>\')','<s:property value="explanation"/>');
										</s:iterator>
										document.write(addkpointlist);
										//-->
										</script>
								    </div>
					    		</td>
					    	</tr>
					    	<tr>
					    		<td align="right"><font color="red">*</font>视频名称：</td>
					    		<td class="lists_tleft" style="color: #ff0000;">
					    		<input type="text" id="kpoint.name"  name="kpoint.name" class="{required:true,minlength:1,maxlength:100}" value="<s:property value="kpoint.name"/>"   style="width:50%;"/>
					    		</td>
					    	</tr>
					    	<tr>
					    		<td align="right">视频地址：</td>
					    		<td class="lists_tleft" style="color: #ff0000;">
					    			<input type="text" name="vedio.voUrl"  value="<s:property value="vedio.voUrl"/>"  style="width:50%;"/>
								</td>
					    	</tr>
						    <tr>
					    		<td align="right">CC视频地址：</td>
					    		<td class="lists_tleft" style="color: #ff0000;">
					    			<input type="text" name="vedio.ccUrl"  value="<s:property value="vedio.ccUrl"/>" id="ccUrl" style="width:50%;"/>
								</td>
					    	</tr>
					    		<!-- <input type="hidden" value="0" name="vedio.play_type"></input> -->
					    	<tr>
					    		<td align="right">默认播放器：</td>
					    		<td class="lists_tleft" style="color: #ff0000;">
					    			<select id="vedio.playType" name="vedio.playType">
					    				<option value="1">cc播放器</option>
					    				<option value="0">highso播放器</option>
					    			</select>
								</td>
					    	</tr>
					    	<tr>
					    		<td align="right">讲义地址：</td>
					    		<td class="lists_tleft">
					    			<input type="text" name="book.bkUrl" class="{required:false,minlength:0,maxlength:200}" size=50 value="<s:property value="book.bkUrl"/>"  style="width:50%;" />
								</td>
					    	</tr>
					    	<tr>
					    		<td align="right">音频地址：</td>
					    		<td class="lists_tleft">
					    			<input type="text" name="book.ypUrl" class="{required:false,minlength:0,maxlength:200}" size=50 value="<s:property value="book.ypUrl"/>"   style="width:50%;"/>
								</td>
					    	</tr>
					    	<tr>
					    		<td align="right">排序：</td>
					    		<td class="lists_tleft"> 
					    		
					    		<input type="text" name="kpoint.sort" class="{required:true,digits:true,min:0,maxlength:10}" value="<s:property value="kpoint.sort"/>" class="w6"/>
					    		</td>
					    	</tr>
					   <s:if test="kpoint.pointId!=null&&kpoint.pointId!=0">
					    <s:iterator value="vedioKnowledgeList"	id="vedioKnowledge" status="status">
					    	<tr id="trKnowledgePoint-<s:property value='#status.index+1'/>">
					    		<td align="right">视频知识点<s:property value='#status.index+1'/>：</td>
					    		<td class="lists_tleft">
					    			<textarea name="knowledgePoint"  id="knowledgePoint-<s:property value='#status.index+1'/>"  style="height:80px;width:60%;" ><s:property value="#vedioKnowledge.knowledgePoint"/></textarea>
					    		<s:if test="#status.index>1">
					    			<input type="button" id="deletePoint-<s:property value='#status.index+1'/>"  value="删除本知识点" onclick="deleteKnowLedge(this)"/>
					    		</s:if>
								</td>
					    	</tr>
					    	<tr id="trTimePoint-<s:property value='#status.index+1'/>">
					    		<td align="right">时间：</td> 
					    		<td class="lists_tleft"> 
					    		<input type="text" name="timePoint" id="timePoint-<s:property value='#status.index+1'/>" value="<s:property value="#vedioKnowledge.timePoint"/>" />
					    		</td>
					    	</tr>
					    </s:iterator>
					   </s:if>
					   <s:else>
					    	<tr id="trKnowledgePoint-1">
					    		<td align="right">视频知识点1：</td>
					    		<td class="lists_tleft">
					    			<textarea name="knowledgePoint"  id="knowledgePoint-1"  style="height:80px;width:60%;" ></textarea>
								</td>
					    	</tr>
					    	<tr id="trTimePoint-1">
					    		<td align="right">时间：</td> 
					    		<td class="lists_tleft"> 
					    		<input type="text" name="timePoint" id="timePoint-1" />
					    		</td>
					    	</tr>
					    	<tr id="trKnowledgePoint-2">
					    		<td align="right">视频知识点2：</td>
					    		<td class="lists_tleft">
					    			<textarea name="knowledgePoint"  id="knowledgePoint-2" style="height:80px;width:60%;"></textarea>
								</td>
					    	</tr>
					    	<tr id="trTimePoint-2">
					    		<td align="right">时间：</td>
					    		<td class="lists_tleft"> 
					    		<input type="text" name="timePoint" id="timePoint-2"  />
					    		</td>
					    	</tr>
					    	<tr id="trKnowledgePoint-3">
					    		<td align="right">视频知识点3：</td>
					    		<td class="lists_tleft">
					    			<textarea name="knowledgePoint" id="knowledgePoint-3" style="height:80px;width:60%;"></textarea><input type="button" id="deletePoint-3"  value="删除本知识点" onclick="deleteKnowLedge(this)"/>
								</td>
					    	</tr>
					    	<tr id="trTimePoint-3"> 
					    		<td align="right">时间：</td>
					    		<td class="lists_tleft"> 
					    		<input type="text" name="timePoint" id="timePoint-3"  />
					    		</td>
					    	</tr>
					    	
					    </s:else>
					    	<tr id="trAddKnowLedgePoint">
					    		<td  align="center"><input type="button" id="addKnowLedgePoint" value="继续添加知识点" onclick="addPoint()"/></td>
					    		
					    	</tr>
					    	<tr>
								<td>
									<font color="red">*</font>老师
								</td>
								<td class="lists_tleft" style="color: #ff0000;">
									<input type="text" name="vedio.teacherName"
										id="vedio.teacherName"
										class="{required:true,minlength:1,maxlength:200}" style="width:50%;" value="<s:property value="vedio.teacherName"/>"></input>
							
								</td>
							</tr>
					    	<tr>
     							<td>视频图片</td>
     							<td class="lists_tleft">
 									<span style="border:0px;padding-top: 2px;padding-left: 2px;position: absolute;">
 										<input type="file"  id="fileupload" style="float: left" />
 										<input type="button" onclick="uploadifyUpload()"  value="上传" style="width: 100px;height: 30px;padding-top: 0px"/>
 									</span>
									<div id="fileQueue" style="margin-top: 30px;border:0px"></div>
   									<span style="float:left;">
   								<s:if test="vedio.voId!=''">
  									<s:if test="vedio.vedioPicUrl==''">
    									<img src="http://import.highso.org.cn/images/usercenter/leftnav_2.gif" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
  									</s:if>
   									<s:if test="vedio.vedioPicUrl!=''">
   										<img src="http://import.highso.org.cn/upload/vedio/${vedio.vedioPicUrl}" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
   									</s:if>
  								</s:if>
   								<s:else>
   									<img src="" alt="" width="100px" height="100px" style="display: none;padding-left: 80px" id="couponpic"/>
   								</s:else>
   									</span>   
								</td>
							</tr>
					<s:if test="kpoint.pointId!=null&&kpoint.pointId!=0">
						<s:iterator value="relateKnowledgeList" id="relateKnowledge">
							<tr id="trRelateKPoints<s:property value='#relateKnowledge.knowledgePointId'/>">
								<td colspan="2"  >
									<input type="text" readonly="readonly" name="relateKnowledgePoints" id="relateKnowledgePoint<s:property value='#relateKnowledge.knowledgePointId'/>" value="<s:property value='#relateKnowledge.relateKnowledgePoint'/>" style="width:80%;"/>
									<input id="deleteRelatekp<s:property value='#relateKnowledge.knowledgePointId'/>" type="button" value="删除" onclick="deleteRelatekpFun(<s:property value='#relateKnowledge.knowledgePointId'/>)"/>
									<input type="hidden" name="knowledgePointId" id="knowledgePointId<s:property value='#relateKnowledge.knowledgePointId'/>" value="<s:property value='#relateKnowledge.knowledgePointId'/>"/>
								</td>
							</tr>
							<tr id="trImportantLevel<s:property value='#relateKnowledge.knowledgePointId'/>">
								<td align="right">重要程度：</td>
								<td class="lists_tleft">
									<input type="text" name="importantLevel" id="importantLevel<s:property value='#relateKnowledge.knowledgePointId'/>" value="<s:property value='#relateKnowledge.importantLevel'/>" size=50 class="w6" />
								</td>
							</tr>
						</s:iterator>
					</s:if>
							<tr id="trRelateKPoint">
					    		<td  align="center"><input type="button" id="relateKPoint" value="关联知识库" onclick="relatePoint()"/>
					    			<div id="kPointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none; ">
						    			<script type="text/javascript">
						       				 d = new dTree('d','<%=contextPath%>/back/images/dtree');
											 d.add(0,-1,'知识库 <a href="javascript:closeColumn();">关闭</a>');
											<s:iterator value="sysTree">
												if(${parentId}==-1){
													d.add(<s:property value="sysNode.ksnId"/>,-1,'${nodeName}','javascript:getRaleteValue(\'${sysNode.ksnId}\',\'${nodeName}\')');
												}else{
													d.add(${ksnId},${parentId},'${nodeName}','javascript:getRaleteValue(\'${ksnId}\')');
												}
											</s:iterator>
											document.write(d);
										</script>
					    			</div>
					    		</td>
					    	</tr>
					    	<tr>
					    		<td align="right">视频内容：</td>
					    		<td class="lists_tleft"> 
					    			 <textarea id="txt_topic" name="vedio.lecture" cols="100" rows="8" style="width:60%;height:365px;visibility:hidden;">
			    					 	 <s:property value="vedio.lecture" escape="false"/>
			    		  			 </textarea>
					    		</td>
					    	</tr>
					    	
					    	<tr>
					    		<td align="right"><font color="red">*</font>是否可试听：</td>
					    	<s:if test="vedio.isAudition==1">
					    		<td class="lists_tleft"> 
					    			<input type=radio name="vedio.isAudition" id="auditionYes" value=0  />是
					    			<input type=radio name="vedio.isAudition" id="auditionNo" value=1 checked/>否
					    		</td>
					    	</s:if>
					    	<s:else>
					    		<td class="lists_tleft"> 
					    			<s:if test="addOrUpdate==0">
						    			<input type=radio name="vedio.isAudition" id="auditionYes" value=0 />是
						    			<input type=radio name="vedio.isAudition" id="auditionNo" value=1 checked/>否
					    			</s:if>
					    			<s:if test="addOrUpdate==1">
						    			<input type=radio name="vedio.isAudition" id="auditionYes" value=0 checked/>是
						    			<input type=radio name="vedio.isAudition" id="auditionNo" value=1 />否
					    			</s:if>
					    		</td>
					    	</s:else>
					    	</tr>
					    	<tr>
					    		<td align="right">试听时长：</td>
					    		<td class="lists_tleft">  
					    			<input type="text" name="vedio.auditionSize" id="vedio.auditionSize" class="{required:false,digits:true,min:0,maxlength:20}" value="<s:property value="vedio.auditionSize"/>"/>分
					    		</td>
					    	</tr>
					    	<tr>
					    		<td align="right"><font color="red">*</font>试听视频地址：</td>
					    		<td class="lists_tleft" style="color: #ff0000;"> 
					    			<input type="text" name="vedio.auditionUrl" id="auditionUrl" class="{required:false,minlength:0,maxlength:200}" value="<s:property value="vedio.auditionUrl"/>"  style="width:50%;"/>
								</td>
					    	</tr>
					    	<tr>
					    		<td align="right">免费视频：</td>
					    	<s:if test="vedio.freeVedio==1">
					    		<td class="lists_tleft"> 
					    			<input type=radio name="vedio.freeVedio" value=0  />是
					    			<input type=radio name="vedio.freeVedio" value=1 checked/>否
					    		</td>
					    	</s:if>
					    	<s:else>
					    		<td class="lists_tleft"> 
					    			<input type=radio name="vedio.freeVedio" value=0 checked />是
					    			<input type=radio name="vedio.freeVedio" value=1 />否
					    		</td>
					    	</s:else>
					    	</tr>
					    	<tr>
					    		<td align="right">视频状态：</td>
					    		<td class="lists_tleft"> 
					    			<input type=radio name="addOrUpdate" id="addStatus" value=0 <s:if test="addOrUpdate==0">checked</s:if> />新建
					    			<input type=radio name="addOrUpdate" id="updateStatus" value=1 <s:if test="addOrUpdate==1">checked</s:if>/>修改
					    		</td>
					    	</tr>
					    	<tr>
					    		<td align="right">显示推荐位：</td>
					    	<s:if test="vedio.recommend==1">
					    		<td class="lists_tleft"> 
					    			<input type=radio name="vedio.recommend" value=0  />是
					    			<input type=radio name="vedio.recommend" value=1 checked/>否
					    		</td>
					    	</s:if>
					    	<s:else>
					    		<td class="lists_tleft"> 
					    			<input type=radio name="vedio.recommend" value=0  checked/>是
					    			<input type=radio name="vedio.recommend" value=1 />否
					    		</td>
					    	</s:else>
					    	</tr>
					    	
					    
					    	<s:hidden name="kpoint.pId" id="kpoint.pId"></s:hidden>
					    	<s:hidden name="kpoint.pointId" id="kpoint.pointId"></s:hidden>
					    	<s:hidden name="kpoint.courseId" id="kpoint.courseId"></s:hidden>
					    	<s:if test="vedio!=null">
					    	<input type="hidden" name="vedio.voId" id="vedio.voId" value="<s:property value="vedio.voId"/>"/>
					    	<input type="hidden" name="vedio.vedioPicUrl" id="vedioPicUrl" value="<s:property value="vedio.vedioPicUrl"/>"/>
					    	</s:if>
					    	<input type='hidden' name="kpoint.level" id="kpoint.level" value='<s:property value="kpoint.level"/>' class="w6"/>
					    	<s:hidden name="subjectId" id="subjectId"></s:hidden>
					    	<input type="hidden" name="planNum" id="planNum" value='<s:property value="course.lessionTime"/>'/>
					    	<s:hidden ></s:hidden>
					    	<input type="hidden" name="realNum" id="realNum" value='<s:property value="course.realUpNum"/>'/>
					    	<tr>
					    		<td></td>
					    		<td align="left"><input class="submit" type="button"  value="提交" onclick="saveVedio();" style="width:100px;"/></td>
					    	</tr>
					    </table>
					  </form>
						</td>
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
</div>
	</body>
</html>
