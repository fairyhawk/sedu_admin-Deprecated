<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改商品</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
        <script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<%=importURL%>/uploadify/uploadify.css" />
        <script type="text/javascript" src="<%=importURL%>/uploadify/swfobject.js"></script>
        <script type="text/javascript"  src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4_headimg.js"></script>
       	<link rel="stylesheet" href="<%=contextPath%>/styles/usercenter/uc_public.css" type="text/css"></link>
       	<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript">
		 var sellId='${sellWay.sellId}';
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
    	        KE.show({
                    id : 'txt_topic2',
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
                KE.show({
                    id : 'sellWay_protocalContent',
                    resizeMode : 1,
                    allowPreviewEmoticons : false,
                    allowUpload : true,
                    syncType : 'auto',
                    urlType : 'absolute',
                    imageUploadJson : '<%=keImageUploadJsonAction%>?cusid=9999',
                    allowFileManager : false,
                    items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
                        'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
                        'code', 'image', 'flash', '|'],
                    afterCreate : function(id) {
                    	KE.toolbar.disable(id, []);
						KE.readonly(id);
						KE.g[id].newTextarea.disabled = true;
					}
                }); 
    	        if(sellId!=''){
	    	        var selectSub='${sellWay.specialtyId}';
					$("#specialtyId").val(selectSub);
					$("#specialtyId").attr("disabled","disabled");
						
    	        }
    	        operateServiceType(document.getElementById("validTime"));
    	        
    	       $("#sellwayform").validate();
    	       
		})
		var teacherNum=0;
		var courseState=0;
		function doMyThing(){ 
			return false; 
		} 
		document.oncontextmenu = doMyThing; 
		window.history.go(1);
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
		  var myids="";
		  var mythids="${sellWay.teacher}"+"";
		 $(document).ready(function()
				    {
				        $("#fileupload").uploadify({
				                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
				                    'script'  :'http://tp.highso.cn:8080/upload!shop.action;jsessionid=<%=session.getId()%>',
				                     'queueID':'fileQueue',
				                    'fileDataName':'fileupload',
				                    'auto':false,
				                    'multi':false,
				                    'hideButton':false,
				                    'buttonText':'Browse',
				                     'buttonImg':'<%=contextPath%>/uploadify/liulan.png',
				                     'width':105,
				                    'simUploadLimit' : 3,
				                    'sizeLimit'      : 512000,
				                    'queueSizeLimit' : 2,
				                    'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
				                    'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
				                    'folder' : '/upload',
				                    'cancelImg':'<%=contextPath%>/uploadify/cancel.png',
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
				                    	$("#imagesUrl").val(response);		
				                    	$("#couponpic").show();
				                    },
				                    onError: function(event, queueID, fileObj,errorObj)
				                    {
				                    	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
				                       // alert("文件:" + fileObj.name + "上传失败");
				                    	//}
				                    }/* ,
				                    onCancel: function(event, queueID, fileObj)
				                    {
				                        alert("取消了" + fileObj.name);
				                    } */
				                });

				    });
		
		function addcourse()
		{
			
			
		var subjectId=$("#courseSubjectId").val();
		if(subjectId==0){
			alert("请选择课程分类");
			return false;
		}
		$("#piaocourse").show();
		
		$("#coursehtml").html("");
		$.ajax({
			url :  "<%=contextPath%>/cou/course!getCourseListBySubjectId.action",
			data : {
				"queryCourseCondition.subjectId" : subjectId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result == null || result.entity == null) {				
					return;
				}				
				var courses = result.entity;				
				var html = '';
				for(var i=0; i<courses.length; i++) {
					html +=courses[i].courseId+"<input type='checkbox' name='courseIds' id='mycourse"+courses[i].courseId+"' onclick='checkdelcourse(this,\""+courses[i].courseName+"\")' value='"+courses[i].courseId +"' title='"+courses[i].courseName+"'/>"+ courses[i].courseName+"<br />";
				}
				$("#coursehtml").append(html);
			if(myids==""&&courseState==0){
			 <s:iterator value="courseList" var="course">
			 $("#mycourse${course.id}").attr("checked","checked");				  
			  </s:iterator> 
			}else{
				 $('input[name="courseIds"]').each(function(){ $(this).attr("checked",""); });  
			 var  szids=myids.split(",");
			 for(var i=0;i<szids.length-1;i++){
				 $("#mycourse"+szids[i]).attr("checked","checked");	
			 }
			}
			},
			error : function(error) {
				alert(error);
			}
		});
		}
		function  checkdelcourse(obj,courseName){
			if(obj.checked==false)
				{
				var zj=confirm("您正在取消【"+courseName+"】");
			    if(!zj){
			    	obj.checked=true;
			    }
			    else{
			    	var courseId=obj.value;
			    	myids=myids.replace(courseId+",","");
					$("#coursespan"+courseId).remove();
					var ids= $("#coursehidden").val();
					ids=ids.replace(courseId+",","");
					$("#coursehidden").val(ids);
			    }
				}
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
		function closecourse(){
			$('#piaocourse').hide();
		}
		
		function closeteacher(){
			$('#piaoteacher').hide();
		}
		//添加教师
		function addteacher(){
			var subjectId=$("#teacherSubjectId").val();
			if(subjectId==0){
				alert("请选择教师分类");
				return false;
			}
			$("#piaoteacher").show();
			$("#teacherhtml").html("");
			$.ajax({
				url :  "<%=contextPath%>/cou/teacher!getTeacherBySubjectId.action",
				data : {
					"subjectId" : subjectId
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
						var myteacher="${sellWay.teacher}";
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
		
	
		
		function addcourseDo()
		{ 
			  myids= $("#coursehidden").val();
		    $('input[name="courseIds"]:checked').each(function(){  
		    	if(myids.indexOf($(this).val())==-1){
		    $("#coursestr").append("<p style='width:100%;margin: 0 0 0em' id='coursespan"+$(this).val()+"' >"+$(this).attr("title")+"&nbsp;&nbsp;<a href='javascript:void(0)' onclick='delcourse(\""+$(this).val()+"\",\""+$(this).attr("title")+"\")'>删除</a></p>");
		    myids+=$(this).val()+",";
		    	}
		   });  
		   $("#coursehidden").val(myids);
		   closecourse();
		}
		function delcourse(courseId,title){
			var zj=confirm("确定要删除【"+title+"】");
			if(zj){
			myids=myids.replace(courseId+",","");
			$("#coursespan"+courseId).remove();
			var ids= $("#coursehidden").val();
			ids=ids.replace(courseId+",","");
			 $("#coursehidden").val(ids);
			}
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
		function clearteacher()
		{
			$("#teacherhidden").val("");
			 $("#teacherstr").html("");
		}
		function clearcourse()
		{
			courseState=1;
			myids="";
			$("#coursehidden").val("");
			$("#coursestr").html("");
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
		function saveAndAdd(){
			$("#submitState").val("1");
		}
		 function disable()
		 {
		            $('input[name="disPosition"]').each(function(){  
		            	$(this).attr("checked","");
		    		$(this).attr("disabled","disabled");
		    		   });  
		            $('input[name="disProperty"]').each(function(){  
		            	$(this).attr("checked","");
		    		$(this).attr("disabled","disabled");
		    		   }); 
		            
		 }
		 function nodisable()
		   {
		            $('input[name="disPosition"]').each(function(){  
		            $(this).attr("checked","");
		    		$(this).attr("disabled","");
		             });  
		            $('input[name="disProperty"]').each(function(){  
		            	$(this).attr("checked","");
		    		$(this).attr("disabled","");
		    		   }); 
		   }
		 
		 function baseAttribute(){
			 document.getElementById("baseInfo").style.display="block";
			 document.getElementById("detailInfo").style.display="none";
		 }
		
		 function sellAtribute(){
			 document.getElementById("baseInfo").style.display="none";
			 document.getElementById("detailInfo").style.display="block";
		 }
		 
		 function addRebatePriceFun(){
			 	var rebatePriceArray=document.getElementsByName("rebatePrice");
				var rebatePriceLength=rebatePriceArray.length+1;
				var htmlStr="<tr id=\"trRebatePrice-"+rebatePriceLength+"\">"
      							+"<td class=\"lists_tright\" width=\"10%\">促销价格 ：</td>"
      							+"<td class=\"lists_tleft\">"
      								+"<input type=\"text\" name=\"rebatePrices\" id=\"rebatePrice-"+rebatePriceLength+"\" value=\"${sellWay.rebatePrice }\"/>"
      							+"</td>"
    						+"</tr>"
    						+"<tr id=\"trRebateTime-"+rebatePriceLength+"\">"
     							+"<td class=\"lists_tright\" width=\"10%\">促销时间 ：</td>"
    			 				+"<td class=\"lists_tleft\">"
    								+"<input type=\"text\" name=\"rebateBeginTimes\" id=\"rebateBeginTime-"+rebatePriceLength+"\" readonly=\"readonly\" value=\"<s:date name='rebateBeginTime' format='yyyy-MM-dd HH:mm:ss' />\""
									+"onfocus=\"WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})\"/>至"
									+"<input type=\"text\" name=\"rebateEndTimes\" id=\"rebateEndTime-"+rebatePriceLength+"\" readonly=\"readonly\" value=\"<s:date name='rebateEndTime' format='yyyy-MM-dd HH:mm:ss' />\"" 
									+"onfocus=\"WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})\"  />"
									+"<input type=\"button\" id=\"deleRebate-"+rebatePriceLength+"\"  value=\"删除本促销价\" onclick=\"deleRebateFun(this)\"/>"
								+"</td>"
							+"</tr>";
				$("#trRebateTime-"+rebatePriceArray.length).after(htmlStr);
		 }
		 
		 function deleRebateFun(obj){
			//获取删除知识点的ID，并且取出该ID值的最后一位数字，用于判断应该删除哪一个知识点
				var deleteId=obj.id;
				var str=deleteId.split("-");
				var rebatePrice="trRebatePrice-"+str[1];
				var rebateTime="trRebateTime-" + str[1];
				$("#"+rebatePrice+"").remove();
				$("#"+rebateTime+"").remove();
		 }
		 
		 function selectServceType(obj){
			 operateServiceType(obj);
		 }
		 function operateServiceType(obj){
			
			 var htmlStr="";
			 if(obj.value==1){
				 htmlStr="<td class=\"lists_tright\" width=\"10%\"></td>"
							+"<td class=\"lists_tleft\">"
							 	+"<input type=\"text\" name=\"sellWay.validityNum\" id=\"validityNum\" value=\"${sellWay.validityNum}\"/>天"
							+"</td>"
					 	 
			 }
			 if(obj.value==2){
				 htmlStr="<td class=\"lists_tright\" width=\"10%\"></td>"
							+"<td class=\"lists_tleft\">"
						  		+"<input type=\"text\" name=\"sellWay.validityBeginTime\" id=\"validityBeginTime\" readonly=\"readonly\" value=\"<s:date name='sellWay.validityBeginTime' format='yyyy-MM-dd HH:mm:ss' />\""
									+"onfocus=\"WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})\"  />至"
								+"<input type=\"text\" name=\"sellWay.validityEndTime\" id=\"validityEndTime\" readonly=\"readonly\" value=\"<s:date name='sellWay.validityEndTime' format='yyyy-MM-dd HH:mm:ss' />\""
									+"onfocus=\"WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})\"  />"
							+"</td>"
			 }
			 $("#trServiceTime").html(htmlStr);
		 }
		 function saveSellWay(){
			    var message=validateFun();
			    if(message!=""){
			    	alert(message);
			    	return false;
			    }
				if(sellId==''){
					$("#sellwayform").attr("action","<%=contextPath%>/cou/sellway!doAddSellWay.action");
				} else{
					$("#sellwayform").attr("action","<%=contextPath%>/cou/sellway!updateSellWay.action");
				}
				$("#sellwayform").submit();
				
			 }
		 function validateFun(){
			var message="";
			if(getBeganSellTime()!=""&&getStopServiceTime()!=""){
				if(new Date(getBeganSellTime().replace(/-/g,"/"))>new Date(getStopServiceTime().replace(/-/g,"/"))){
					message+="开始售卖时间不得大于停止商品服务时间！\n";
				}
			}
			if(getValidTime()==2){
				if(getValidityBeginTime()!=""&&getValidityEndTime()!=""){
					if(new Date(getValidityBeginTime().replace(/-/g,"/"))>new Date(getValidityEndTime().replace(/-/g,"/"))){
						message+="有效期开始时间不能大于有效期结束时间！\n";
					}
					if(getBeganSellTime()!=""){
						if(new Date(getValidityBeginTime().replace(/-/g,"/"))<new Date(getBeganSellTime().replace(/-/g,"/"))||
								new Date(getValidityEndTime().replace(/-/g,"/"))<new Date(getBeganSellTime().replace(/-/g,"/"))){
							message+="服务有效期不得早于开始售卖时间！\n";
						}
					}
					if(getStopServiceTime()!=""){
						if(new Date(getValidityEndTime().replace(/-/g,"/"))>getStopTime(new Date(getStopServiceTime().replace(/-/g,"/")))){
							message+="服务有效期不得晚于停止商品服务时间前一个月！\n";
						}
					}
					
				}
				
			}
			if(getStopSellTime()!=""&&getBeganSellTime()!=""){
				if(new Date(getStopSellTime().replace(/-/g,"/"))<new Date(getBeganSellTime().replace(/-/g,"/"))){
					message+="停止售卖时间不得小于开始售卖时间！\n";
				}
			}
			if(getStopSellTime()!=""&&getStopServiceTime()!=""){
				if(new Date(getStopSellTime().replace(/-/g,"/"))>new Date(getStopServiceTime().replace(/-/g,"/"))){
					message+="停止售卖时间不得大于停止商品服务时间！\n";
				}
			}
			if(getDownShelveTime()!=""&&getBeganSellTime()!=""){
				if(new Date(getDownShelveTime().replace(/-/g,"/"))<new Date(getBeganSellTime().replace(/-/g,"/"))){
					message+="下架时间不得早于开始售卖时间！\n";
				}
				
			}
			if(getDownShelveTime()!=""&&getStopServiceTime()!=""){
				if(new Date(getDownShelveTime().replace(/-/g,"/"))>getStopTime(new Date(getStopServiceTime().replace(/-/g,"/")))){
					message+="下架时间不得晚于商品停止服务时间前一个月！\n";
				}
			}
			
			if(rebateBeginTimeFun().length>0&&rebateEndTimeFun().length>0){
				for(var i=0;i<rebateBeginTimeFun().length;i++){
					if(new Date(rebateBeginTimeFun()[i].value.replace(/-/g,"/"))>new Date(rebateEndTimeFun()[i].value.replace(/-/g,"/"))){
						message+="第"+(i+1)+"个促销开始时间不得大于促销结束时间！\n";
					}
					if(new Date(rebateEndTimeFun()[i].value.replace(/-/g,"/"))>new Date(getStopSellTime().replace(/-/g,"/"))){
						message+="第"+(i+1)+"个促销时间不得晚于停止售卖时间！\n";
					}
					if(rebatePricesFun()[i].value!=""){
						if(/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(rebatePricesFun()[i].value)==false){
							message+="第"+(i+1)+"个促销价格只能保留小数点后两位！\n";
						}
					}
						
				}
			}
			if(getSellPrice()!=""){
				if(/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(getSellPrice())==false)
					{
						message+="商品价格只能保留小数点后两位！\n";
				    }
		 }
			if(getNowPrice()!=""){
				if(/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(getNowPrice())==false)
			    {
					message+="会员价格只能保留小数点后两位！\n";
			    }
			}
			return message;
		 }
		 function rebatePricesFun(){
			 return document.getElementsByName("rebatePrices");
		 }
		 function rebateBeginTimeFun(){
			 return document.getElementsByName("rebateBeginTimes");
		 }
		 function rebateEndTimeFun(){
			 return document.getElementsByName("rebateEndTimes");
		 }
		 function getStopTime(obj){
			 obj.setDate(obj.getDate()-30);
			 return obj;
		 }
		 function getBeganSellTime(){
			 return $("#beganSellTime").val();
		 }
		 function getStopServiceTime(){
			 return $("#stopServiceTime").val();
		 }
		 function getValidTime(){
			 return $("#validTime").val();
		 }
		 function getValidityBeginTime(){
			 return $("#validityBeginTime").val();
		 }
		 function getValidityEndTime(){
			 return $("#validityEndTime").val();
		 }
		 function getStopSellTime(){
			 return $("#stopSellTime").val();
		 }
		 function getDownShelveTime(){
			 return $("#downShelveTime").val();
		 }
		 function getSellPrice(){
			 return $("#sellPrice").val();
		 }
		 function getNowPrice(){
			 return $("#nowPrice").val();
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
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top">
						<font class="lists_fleft"><a href="javaScript:void(0)" onclick="baseAttribute()">1.商品基本属性</a> </font>
					 	<font class="lists_fleft"><a href="javaScript:void(0)" onclick="sellAtribute()">2.商品销售属性</a> </font>
					</td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>

	<tr >
				<td class="lists_bor"></td>
				<td>
	<form action="<%=contextPath%>/cou/sellway!updateSellWay.action" method="post"  name="sellwayform" id="sellwayform">
    <%-- 	<input type="hidden" name="dingwei" value="<s:property value="pageUrlParms.substring(pageUrlParms.indexOf('?')+1)"/>"/> --%>
	<input type="hidden" name="path" value="${path}"/>
	<input type="hidden" name="submitState" id="submitState" value="0" />
    <input type="hidden" name="coursehidden" id="coursehidden" value=""/>
    <input type="hidden" name="sellWay.imagesUrl" id="imagesUrl" value="${sellWay.imagesUrl}"/>
    <input type="hidden" name="sellWay.subjectId" value="${sellWay.subjectId }"/>
    <input type="hidden" name="sellWay.sellId" value="${sellWay.sellId}"/>
    <input type="hidden" name="sellWay.addTime" value="<s:date name="sellWay.addTime" format="yyyy-MM-dd HH:mm:ss" />"/>
	<input type="hidden" name="sellWay.upTime" value="<s:date name="sellWay.upTime" format="yyyy-MM-dd HH:mm:ss" />"/>
	<input type="hidden" name="sellWay.dropTime" value="<s:date name="sellWay.dropTime" format="yyyy-MM-dd HH:mm:ss" />"/>
	<input type="hidden" name="myshopstate" value="${sellWay.shopState}"/>
	<input type="hidden" name="sellWay.status" value="${sellWay.status}"/>
    <input type="hidden" name="sellWay.teacher" id="teacherhidden" value="${sellWay.teacher}"/>
    <div id="baseInfo">
    	<table width="100%" cellspacing="1" cellpadding="0" border="0" class="lists_info">
	  		<tbody>
	    		<tr>
    				<td colspan="2"><h3>商品基本属性</h3></td>
   				</tr>
   				<tr>
    				<td class="lists_tright" width="10%">所属类别 ：</td>
    				<td class="lists_tleft">
						<select name="sellWay.sellType" disabled="disabled" id="sellType">
			    			<option value="1" <s:if test="#request.sellWay.sellType==1" >selected</s:if> >项目商品</option>
			    			<option value="2" <s:if test="#request.sellWay.sellType==2" >selected</s:if> >DS商品</option>
			    			<option value="3" <s:if test="#request.sellWay.sellType==3" >selected</s:if> >内部商品</option>
						</select>
    				</td>
    			</tr>
	 			<tr>
	  				<td class="lists_tright" width="10%"><font color="red">*</font> 所属项目专业 ：</td>
	  				<td class="lists_tleft">
	    				<s:select  id="shopSubjectId" list="subjectList" listKey="subjectId" listValue="subjectName"  headerValue="所有项目" ></s:select>
	   					<script>
     						var selectSub=${sellWay.subjectId };
    				 			$("#shopSubjectId").val(selectSub);
     							$("#shopSubjectId").attr("disabled","disabled");
   						</script>
	    			</td>
	  			</tr>
     			<tr>
	      			<td class="lists_tright" width="10%"><font color="red">*</font>商品名称 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="sellWay.sellName" id="sellName" class="{required:true,minlength:1,maxlength:100}"  value="${sellWay.sellName}" style="width:50%;" /></td>
	     		</tr>
	     		<tr>
	      			<td class="lists_tright" width="10%"><font color="red">*</font>商品简述 ：</td>
	      			<td class="lists_tleft">
    					<textarea rows="7" style="width:50%;" name="sellWay.remark" class="{required:true,minlength:1,maxlength:500}" id="remark"  >${sellWay.remark} </textarea>
    				</td>
    			</tr>
    			<tr>
	      			<td class="lists_tright" width="10%"><font color="red">*</font>课时 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="sellWay.lessonNumber" id="lessonNumber" class="{required:true,digits:true,min:0,maxlength:20}" style="width:50%;" value="${sellWay.lessonNumber}" />
	      			</td>
	      		</tr>
	      		
	      		<tr>
	      			<td class="lists_tright" width="10%">商品图片 ：</td>
	      			<td class="lists_tleft">
 						<span style="border:0px;padding-top: 2px;padding-left: 2px;position: absolute;"><input type="file"  id="fileupload" style="float: left" /><input type="button" onclick="uploadifyUpload()"  value="上传" style="width: 100px;height: 30px;padding-top: 0px"/></span>
							<div id="fileQueue" style="margin-top: 30px;border:0px"></div>
   						<span style="float:left;">
   							<s:if test="sellWay.imagesUrl==''">
    							<img src="http://import.highso.org.cn/images/usercenter/leftnav_2.gif" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
   							</s:if>
   							<s:if test="sellWay.imagesUrl!=''">
   								<img src="http://import.highso.org.cn/upload/shop/${sellWay.imagesUrl }" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
   							</s:if>
   						</span> 
   					</td>
   				</tr>
   				<tr>
	      			<td class="lists_tright" width="10%">适合人群 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="sellWay.suitPerson" id="suitPerson" style="width:50%;" value="${sellWay.suitPerson}" />
	      			</td>
	      		</tr>
    			<tr>
					<td class="lists_tright" width="10%">商品详情 ：</td>
					<td class="lists_tleft">
						<textarea id="txt_topic" name="sellWay.detail" cols="100" rows="8" style="width:50%;height:365px;visibility:hidden;" >
			    					 	 <s:property value="sellWay.detail" escape="false"/>
			    		</textarea>
					</td>
				</tr> 
       			<tr>
	      			<td class="lists_tright" width="10%">商品目录 ：</td>
	      			<td class="lists_tleft">
	      				<textarea id="txt_topic2" name="sellWay.directory" cols="100" rows="8" style="width:50%;height:365px;visibility:hidden;" >
			    					 	 <s:property value="sellWay.directory" escape="false"/>
			    		</textarea>
	      			</td>
	      		</tr>
	      		<tr>
	      			<td class="lists_tright" width="10%">商品标签 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="sellWay.label" id="label" style="width:50%;" value="${sellWay.label}" />
	      			</td>
	      		</tr>
	      		<tr>
	     			<td class="lists_tright" width="10%">上架时间 ：</td>
	     			<td class="lists_tleft">
	     				<input type="text" name="sellWay.upShelveTime" id="upShelveTime" readonly="readonly" value="<s:date name='sellWay.upShelveTime' format='yyyy-MM-dd HH:mm:ss' />" 
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" />
						<input type=checkbox name="sellWay.directUpShelve"  <s:if test="sellWay.directUpShelve=='on'">checked</s:if>  />直接上架
					</td>
    			</tr>
    			<tr>
	     			<td class="lists_tright" width="10%">下架时间 ：</td>
	     			<td class="lists_tleft">
	     				<input type="text" name="sellWay.downShelveTime" id="downShelveTime" readonly="readonly" value="<s:date name='sellWay.downShelveTime' format='yyyy-MM-dd HH:mm:ss' />" 
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />
							<input type=checkbox name="sellWay.directDownShelve" <s:if test="sellWay.directDownShelve=='on'">checked</s:if> />直接下架
					</td>
    			</tr>
    			<tr>
					 <td class="lists_tright" width="10%">商品模式 ：</td>
					 <td class="lists_tleft"> 
					 	<input type=radio name="sellWay.mode" value=0 <s:if test="sellWay.mode==0">checked</s:if>  disabled="disabled"/>免费
					   	<input type=radio name="sellWay.mode" value=1 <s:if test="sellWay.mode==1">checked</s:if> disabled="disabled"/>付费
					 </td>    	
				</tr>
    		</tbody>
    	</table>
    </div>
    <div id="detailInfo" style="display:none">
   		<table width="100%" cellspacing="1" cellpadding="0" border="0" class="lists_info">
	  		<tbody>
	  			<tr>
    				<td colspan="2"><h3>商品销售信息</h3></td>
   				</tr>
	  			<tr>
    				<td class="lists_tright" width="10%">出售模式 ：</td>
    				<td class="lists_tleft">
						<select name="sellWay.sellMode" id="sellMode">
			    			<option value="1" <s:if test="#request.sellWay.sellMode==1" >selected</s:if> >整课出售</option>
			    			<option value="2" <s:if test="#request.sellWay.sellMode==2" >selected</s:if> >按节出售</option>
						</select>
    				</td>
    			</tr>
    			<tr>
	      			<td class="lists_tright" width="10%">课程分类 ：</td>
	      			<td class="lists_tleft">
  						<s:select  id="courseSubjectId" list="subjectList" listKey="subjectId" listValue="subjectName"  headerValue="所有项目" ></s:select>
  						<s:if test="sellWay!=null">
  	 						<script type="">
     							var selectSub=${sellWay.subjectId };
     							$("#courseSubjectId").val(selectSub);
   							</script>
   						</s:if>
  					</td>
   				</tr>
    			<tr>
	      			<td class="lists_tright">包含课程:&nbsp;</td>
	      			<td class="lists_tleft">&nbsp;
	     			<script >
	      				$("#coursehidden").val("");
	     			</script> 
	      			<s:iterator value="courseList" var="course">
	      				<p style='width:100%' id='coursespan${course.id }'>${course.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick="delcourse('${course.id}','${course.name}')"></a> </p>    
	      			</s:iterator>
	      			</td>
	    		</tr>
	    		<tr>
	      			<td class="lists_tright" width="10%">主讲教师 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="sellWay.teacherName" id="teacherName" style="width:50%;" class="{required:true,min:1,maxlength:200}"  value="${sellWay.teacherName}" />
	      			</td>
	      		</tr>
  			 	<tr>
	     			<td class="lists_tright" width="10%">开始售卖时间：</td>
	    			 <td class="lists_tleft">
	    				<input type="text" name="sellWay.beganSellTime" id="beganSellTime" readonly="readonly" value="<s:date name='sellWay.beganSellTime' format='yyyy-MM-dd HH:mm:ss' />" 
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />
					</td>
    			</tr>
	  			<tr>
	      			<td class="lists_tright" width="10%">商品价格 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="sellWay.sellPrice" id="sellPrice" value="${sellWay.sellPrice}"/>
	      			</td>
	      		</tr>
     			<tr> 
	      			<td class="lists_tright" width="10%"> 会员价格 ：</td>
	      			<td class="lists_tleft"><input type="text" name="sellWay.nowPrice" id="nowPrice" value="${sellWay.nowPrice}"/></td>
	     	 	</tr>
	     <s:if test="sellWay.sellId!=null">
	     	 <s:iterator value="rebatePriceList" id="rebatePriceObj" status="status">
	     	 	<tr id="trRebatePrice-<s:property value='#status.index+1'/>">
	      			<td class="lists_tright" width="10%">促销价格 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="rebatePrices" id="rebatePrice"  value="<s:property value='#rebatePriceObj.rebatePrice'/>"/>
	      			</td>
	    		</tr>
	    		<tr id="trRebateTime-<s:property value='#status.index+1'/>">
	     			<td class="lists_tright" width="10%">促销时间 ：</td>
	    			 <td class="lists_tleft">
	    				<input type="text" name="rebateBeginTimes" id="rebateBeginTime-<s:property value='#status.index+1'/>" readonly="readonly" value="<s:date name='#rebatePriceObj.rebateBeginTime' format='yyyy-MM-dd HH:mm:ss' />" 
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />至
						<input type="text" name="rebateEndTimes" id="rebateEndTime-<s:property value='#status.index+1'/>" readonly="readonly" value="<s:date name='#rebatePriceObj.rebateEndTime' format='yyyy-MM-dd HH:mm:ss' />" 
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />
					</td>
    			</tr>	
	     	 </s:iterator>
	     </s:if>
	     <s:else>
     			<tr id="trRebatePrice-1">
	      			<td class="lists_tright" width="10%">促销价格 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="rebatePrices" id="rebatePrice"/>
	      			</td>
	    		</tr>
	    		<tr id="trRebateTime-1">
	     			<td class="lists_tright" width="10%">促销时间 ：</td>
	    			 <td class="lists_tleft">
	    				<input type="text" name="rebateBeginTimes" id="rebateBeginTime-1" readonly="readonly" value="<s:date name='rebateBeginTime' format='yyyy-MM-dd HH:mm:ss' />" 
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />至
						<input type="text" name="rebateEndTimes" id="rebateEndTime-1" readonly="readonly" value="<s:date name='rebateEndTime' format='yyyy-MM-dd HH:mm:ss' />" 
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />
					</td>
    			</tr>
    	</s:else>
    			<tr id="trAddRebatePrice">
					<td colspan="2" align="center">
						<input type="button" id="addRebatePrice" value="继续添加促销价" onclick="addRebatePriceFun()"/>
					</td>
					    		
				</tr>
    			<tr>
	     			<td class="lists_tright" width="10%">确认收入时间 ：</td>
	     			<td class="lists_tleft">
	    				<input type="text" name="sellWay.confirmTime" id="confirmTime" readonly="readonly" value="<s:date name='sellWay.confirmTime' format='yyyy-MM-dd HH:mm:ss' />" 
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />
					</td>
    			</tr>
    			<tr>
					<td class="lists_tright" width="10%">服务有效期 ：</td>
 					<td class="lists_tleft">
 						<select name="sellWay.validTime" id="validTime" onchange="selectServceType(this)">
 							<option value="2" <s:if test="#request.sellWay.validTime==2" >selected</s:if> >按时间段计算</option>
			    			<option value="1" <s:if test="#request.sellWay.validTime==1" >selected</s:if> >按时间点计算</option>
						</select>	
					</td>
  				</tr>
  				<tr id="trServiceTime">
					
  				</tr>
				<tr>
	     			<td class="lists_tright" width="10%">停止售卖时间 ：</td>
	     			<td class="lists_tleft">
	    				<input type="text" name="sellWay.stopSellTime" id="stopSellTime" readonly="readonly" value="<s:date name='sellWay.stopSellTime' format='yyyy-MM-dd HH:mm:ss' />"
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />
					</td>
    			</tr>
    			<tr>
	     			<td class="lists_tright" width="10%">停止商品服务时间 ：</td>
	     			<td class="lists_tleft">
	    				<input type="text" name="sellWay.stopServiceTime" id="stopServiceTime" readonly="readonly" value="<s:date name='sellWay.stopServiceTime' format='yyyy-MM-dd HH:mm:ss' />"
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  />
					</td>
    			</tr>
    			<tr>
	      			<td class="lists_tright" width="10%">赠送积分 ：</td>
	      			<td class="lists_tleft">
	      				<input type="text" name="sellWay.integral" id="integral" value="${sellWay.integral}"/>
	      			</td>
	      		</tr>
   			 	<tr>
					<td class="lists_tright" width="10%">是否支持升级 ：</td>
					<td class="lists_tleft"> 
						<input type=radio name="sellWay.isUpgrade" value=0 <s:if test="sellWay.isUpgrade==0">checked</s:if>  />是
					    <input type=radio name="sellWay.isUpgrade" value=1 <s:if test="sellWay.isUpgrade==1">checked</s:if> />否
					</td>
				</tr>
				<tr>
					<td class="lists_tright" width="10%">是否保过：</td>
					<td class="lists_tleft" id="isProtocal_td">
						<s:if test="sellWay.isProtocal==true">
							<input type="radio" name="sellWay.isProtocal" value="true" id="sellWay_isProtocal_true" checked="checked" onchange="changeProtocalContentEditable(true);" disabled="disabled" /><label for="sellWay_isProtocal_true">是</label>
							<input type="radio" name="sellWay.isProtocal" value="false" id="sellWay_isProtocal_false" onchange="changeProtocalContentEditable(false);" disabled="disabled" /><label for="sellWay_isProtocal_false">否</label>
						</s:if>
						<s:else>
							<input type="radio" name="sellWay.isProtocal" value="true" id="sellWay_isProtocal_true" onchange="changeProtocalContentEditable(true);" disabled="disabled" /><label for="sellWay_isProtocal_true">是</label>
							<input type="radio" name="sellWay.isProtocal" value="false" id="sellWay_isProtocal_false" checked="checked" onchange="changeProtocalContentEditable(false);" disabled="disabled" /><label for="sellWay_isProtocal_false">否</label>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="lists_tright" width="10%">协议内容：</td>
					<td class="lists_tleft">
						<textarea name="sellWay.protocalContent" rows="10" cols="8" style="width: 50%; height: 365px; visibility: hidden; display: none;" id="sellWay_protocalContent"><s:property value="sellWay.protocalContent" escape="true" /></textarea>
					</td>
				</tr>
    		</tbody>
    	</table>
	</div>
    <div style="width: 70%;padding-left: 300px;margin-top: 10px;border:0px">
      	<input type="button" value="返回"  onclick="javascript:history.go(-1);"/>
    </div>
</form>

<div id="piaocourse" style="position: absolute;border: 0px;display: none;z-index:999;left:420px;top:140px;width:300px;height:300px;overflow: auto;">
		<!--查询-->
		<div class="border0">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">请选择要添加的课程</font> <font
						class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
			</table>
		</div>
		<div style="margin: 0px;border:0px;">
			<form name="searchForm" action="" method="post" style="margin: 0px;">
				<table width="98%" border="0" cellspacing="1" cellpadding="0"
					class="lists_info" align="center" >
					<tr>
					<td colspan="2" id="coursehtml" style="text-align: left">
					</td>
					</tr>
					<tr>
					
							<td width="48%"> <a href="javascript:addcourseDo()"><font size="3">确定</font></a></td>
							<td> <a href="javascript:closecourse()"><font size="3">关闭</font></a></td>
					</tr>
				</table>
			</form>
		</div>
		<!--查询结束-->
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
	</td>
				<td class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
						<td><img src="<%=contextPath%>/back/images/tab_18.gif"/></td>
						<td class="lists_bottom">&nbsp;</td>
						<td><img src="<%=contextPath%>/back/images/tab_20.gif"/></td>
				</tr>
				
	</table>
	</body>
</html>