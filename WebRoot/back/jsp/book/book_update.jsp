<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加图书</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
        <script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<%=importURL%>/uploadify/uploadify.css" />
        <script type="text/javascript" src="<%=importURL%>/uploadify/swfobject.js"></script>
        <script type="text/javascript"  src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4_headimg.js"></script>
        <script type="text/javascript"  src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
          <script type="text/javascript"  src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
		<script type="text/javascript">
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
		  function uploadifyUpload2(){
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
				$('#fileupload2').uploadifyUpload();
		
			} 
		 $(document).ready(function()
				    {
				        $("#fileupload").uploadify({
				                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
				                    'script'  :'http://tp.highso.cn:8080/upload!go.action?param=book',
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
				                     onSelect:function(event, queueID, fileObj){
				                    	// $('#fileupload').uploadifyUpload();
				                     	fileuploadIndex=1;
				                    	$("#fileQueue").html("");
				                    	if(fileObj.size>512000){
				                    		alert("文件太大最大上传512kb");
				                    		return;
				                    	}
				                    	
				                    	},
				                    onComplete: function (event, queueID, fileObj, response, data)
				                    {
				                    	$("#bookpic").attr("src","http://import.highso.org.cn/upload/book/"+response);
				                    	$("#bookImg").val(response);		
				                    	$("#bookpic").show();
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
				        
				        $("#fileupload2").uploadify({
		                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
		                    'script'  :'http://tp.highso.cn:8080/upload!go.action?param=smallbook',
		                     'queueID':'fileQueue2',
		                    'fileDataName':'fileupload',		                 
		                     'buttonImg':'<%=importURL%>/uploadify/liulan.png',
		                     'width':105,
		                    'simUploadLimit' : 3,
		                    'sizeLimit'      : 512000,
		                    'queueSizeLimit' : 2,
		                    'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
		                    'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
		                    'folder' : '/upload',
		                    'cancelImg':'<%=importURL%>/uploadify/cancel.png',
		                     onSelect:function(event, queueID, fileObj){
		                     	fileuploadIndex=1;
		                    	$("#fileupload2").html("");
		                    	if(fileObj.size>100000){
		                    		alert("文件太大最大上传100kb");
		                    		return;
		                    	}
		                    	
		                    	},
		                    onComplete: function (event, queueID, fileObj, response, data)
		                    {
		                    	$("#slpic").attr("src","http://import.highso.org.cn/upload/smallbook/"+response);
		                    	$("#bookSmallimg").val(response);		
		                    	$("#slpic").show();
		                    },
		                    onError: function(event, queueID, fileObj,errorObj)
		                    {
		                    	$("#fileQueue2").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
		                       // alert("文件:" + fileObj.name + "上传失败");
		                    	//}
		                    }/* ,
		                    onCancel: function(event, queueID, fileObj)
		                    {
		                        alert("取消了" + fileObj.name);
		                    } */
		                });

				    });


		function saveAndAdd(){
			var subjectId=$("#subjectId").val();
			if(subjectId==0 || subjectId==-1){
				alert("请选择所属项目");
				return false;
			}
			if($("#bookImg").val()==""){
				alert("请选择商品图片");
				return false;
			}
			if($("#bookSmallimg").val()==""){
				alert("请选择商品缩略图片");
				return false;
			}
			var price=$("#price").val();
			if(/^[1-9]*[0-9]{1}\./.test(price)==true){
                if(/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(price)==false)
			    {
				alert("原价只保留两位小数");
				return false;
			    }
			}
                var nowPrice=$("#nowPrice").val();
                if(/^[1-9]*[0-9]{1}\./.test(nowPrice)==true)
                {
                if(/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(nowPrice)==false)
			    {
				alert("现价只保留两位小数");
				return false;
			    }
		         }   
                var rebatePrice=$("#rebatePrice").val();
                if(/^[1-9]*[0-9]{1}\./.test(rebatePrice)==true)
                {
                if(/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(rebatePrice)==false)
			    {
				alert("现价只保留两位小数");
				return false;
			    }
		       }  
                var publishTime=$("#publishTime").val();
            	if((new Date(publishTime.replace(/-/g,"/")))>(new Date()))
    			{
    			alert("出版时间不能大于当前日期");
    			return false;
    			}
			
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
		   
		 $().ready(function() {
				$("#updateBookFomr").validate();
			});
		</script>
		<style >
		body{margin-left: 10px;}
		body { font-size:14px; color:#444444; background:#fff; font-family:"����", "Tahoma", "Helvetica Neue", Arial, Helvetica, sans-serif; }
       .dotline {float:left;BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted;width:60%;height:1px;margin-top: 15px;margin-bottom: 10px}
       div{float:left;text-align:left;width:100%;border:1px solid #b5d6e6;padding-top: 3px;padding-bottom: 3px}
       span{float:left;padding-right: 5px;width:80%}
       .error{color:red;}
       .leftspan{padding-left:}
       .border0{
       border:0px;
       }
       #fileuploadUploader{float: left;}
       
        </style>
    </head>
	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">修改图书 </font> <font class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
	</table>
<div style="border:1px solid #b5d6e6;">
	<form action="<%=contextPath%>/book/book!updateBook.action" method="post" name="updateBookFomr" id="updateBookFomr"  >
	<input type="hidden" name="book.bookImg" id="bookImg" value="${book.bookImg}"/>
	<input type="hidden" name="book.bookSmallimg" id="bookSmallimg" value="${book.bookSmallimg}"/>
	<input type="hidden" name="book.bookId" value="${book.bookId }"/>
	<input type="hidden" name="book.addTime" value="<s:date name="book.addTime" format="yyyy-MM-dd HH:mm:ss" />"/>
		<input type="hidden" name="book.upTime" value="<s:date name="book.upTime" format="yyyy-MM-dd HH:mm:ss" />"/>
	<input type="hidden" name="book.dropTime" value="<s:date name="book.dropTime" format="yyyy-MM-dd HH:mm:ss" />"/>
	<input type="hidden" name="myshopstate" value="${book.shopState}"/>
	<input type="hidden" name="book.status" value="${book.status}"/>
    <div style="width: 100%;border:0px">
    <p><b>商品基本信息(<font color="red">*</font>为必填项)</b></p>
    <div ><font color="red">*</font>商品名称 :&nbsp;<input type="text" name="book.bookName" id="bookName" maxlength="60" class="{required:true}" value="${book.bookName}"/></div>
    <div class="border0"><font color="red">*</font>商品价格： 原价&nbsp;<input type="text" name="book.price" id="price" value="${book.price}" class="{number:true,required:true,max:9999,min:0}"/>
    &nbsp;&nbsp;现价&nbsp;<input type="text" name="book.nowPrice" id="nowPrice" class="{number:true,required:true,max:9999,min:0}" value="${book.nowPrice}"/>&nbsp;&nbsp;
       折扣价&nbsp;<input type="text" class="{number:true,max:9999,min:0}" name="book.rebatePrice" id="rebatePrice" value="${book.rebatePrice}"/>
    </div>
   
    <div  ><font color="red">*</font>所属项目：
  	<s:select name="book.bookSubjectid" id="subjectId" list="subjectList" listKey="subjectId" listValue="subjectName" headerValue="所有项目" headerKey="0"></s:select>
    </div>
       <div  class="border0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>分类：
     <select name="book.bookType" id="bookType" >
      <option value="1">教材</option>
      <option value="2">教辅</option>
      <option value="3">大纲</option>
      <option value="4">套装</option>
     </select>
    </div>

     <div > &nbsp;&nbsp;<font color="red">*</font>商品图片： &nbsp;&nbsp;
 <span style="border:0px;padding-top: 2px;padding-left: 2px;position: absolute;">
 <input type="file"  id="fileupload" style="float: left"  name="Filedata"/>
 <input type="button" onclick="uploadifyUpload()"  value="上传" style="width: 100px;height: 30px;padding-top: 0px"/>
 </span>
<div id="fileQueue" style="margin-top: 30px;border:0px;"></div>
   <span style="float:left;">
   <s:if test="book.bookImg!=null && book.bookImg!=''">
   <img src="http://import.highso.org.cn/upload/book/${book.bookImg}" alt="" width="100px" height="100px"  style="padding-left: 80px" id="bookpic"/>
   </s:if>
   <s:else>
      <img src="" alt="" width="100px" height="100px"  style="display: none;padding-left: 80px" id="bookpic"/>
   </s:else>
   </span>   
    </div>
  
    <div class="border0"><font color="red">*</font>商品缩略图： &nbsp;&nbsp;
 <span style="border:0px;padding-top: 2px;padding-left: 2px;position: absolute;">
 <span style="width: 100px">
 <input type="file"  id="fileupload2" name="Filedata2"/>
 </span>
 <input type="button" onclick="uploadifyUpload2()"  value="上传" style="width: 100px;height: 30px;padding-top: 0px;float: left" />
 </span>
<div id="fileQueue2" style="margin-top: 30px;border:0px;float: left"></div>
   <span style="float:left;">
    <s:if test="book.bookSmallimg!=null && book.bookSmallimg!=''">
   <img src="http://import.highso.org.cn/upload/smallbook/${book.bookSmallimg}" alt="" width="100px" height="100px" style="padding-left: 80px" id="slpic"/>
   </s:if>
       <s:else >
   <img src="" alt="" width="100px" height="100px" style="display: none;padding-left: 80px" id="slpic"/>
   </s:else>
   </span>   
    </div>
      <div  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>库存：&nbsp;&nbsp;
     <select name="book.stockNum" id="stockNum">
      <option value="1">有货</option>
      <option value="2">无货</option>
     </select>
    </div>
    </div>
 <!--    <hr class="dotline"/> -->
    <div style="width: 100%;" >
    <p><b>商品属性信息</b></p>
    <div >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者： <input type="text" name="book.author" maxlength="60" value="${book.author}"  id="author"/></div>
   <div  class="border0">&nbsp;&nbsp;&nbsp;出版社 ：<input type="text" name="book.press" id="press" value="${book.press}" maxlength="60"/></div>
   <div  >出版时间：  <input type="text" name="book.publishTime" id="publishTime" readonly="readonly"
	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="book.publishTime" format="yyyy-MM-dd HH:mm:ss"/>"  />
   </div>
   <div  class="border0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版次 ：<input type="text" name="book.revision" value="${book.revision}" id="revision" maxlength="60" /></div>
   <div  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;印次：<input type="text" name="book.printNum" id="printNum" maxlength="60" value="${book.printNum}" class="{digits:true,number:true,max:9999,min:0}"/></div>
   <div  class="border0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页数：<input type="text" name="book.pageNum" id="pageNum" value="${book.pageNum}" class="{digits:true,number:true,max:9999,min:0}"/></div>
   <div >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;字数 ：<input type="text" name="book.wordNum" id="wordNum" value="${book.wordNum}" class="{digits:true,number:true,max:99999999,min:0}"/></div>
   <div  class="border0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;包装 ：<input type="text" name="book.bookPackage" value="${book.bookPackage}" id="bookPackage" maxlength="60"/></div>
   <div >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;纸张：<input type="text" name="book.pager" value="${book.pager}" id="pager" maxlength="60"/></div>
   <div  class="border0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重量 ：<input type="text" name="book.weight" value="<s:property value="book.weight.replaceAll('千克','').replaceAll('克','')"/>" id="weight" class="{number:true,max:9999,min:0}"/>
   <select name="qianke" id="qianke">
   <option value="千克">千克</option>
   <option value="克">克</option>
   </select>
   </div>
   <div >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ISBN：<input type="text" name="book.isbn" id="isbn" class="{number:true,maxlength:13}" value="${book.isbn}"/></div>
   <div  class="border0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开本 ：<input type="text" name="book.bookSize" id="bookSize" value="16" value="${book.bookSize}"  maxlength="20" class="{number:true,max:100,min:0}"/></div>
  <div  class="border0">图书注释 ：<input type="text" name="book.remarks" value="${book.remarks}" id="remarks" class="{maxlength:100}"/></div>
  
    <div style="border:0px">商品简介：
    <textarea rows="3" cols="60" name="book.bookInfo" id="bookInfo"  class="{maxlength:10000}" ><s:property value="book.bookInfo"/></textarea>
    </div>
     <div >图书目录：<textarea rows="3" cols="60"  name="book.directory" id="directory" class="{maxlength:4000}"><s:property value="book.directory"/></textarea></div>
    </div>
<!--     <hr class="dotline"/> -->
    <div style="width: 100%;border:0px">
    <p><b>商品应用属性信息</b></p>
    <div ><span>上架属性 </span> &nbsp;&nbsp;<span>
    <input type="radio" name="book.shopState" value="1" id="upjia" checked="checked" onclick="nodisable()"/>上架
    <input type="radio"  name="book.shopState" value="2" id="downjia" onclick="disable()"/>下架</span></div>
    <div  class="border0" ><span>显示属性</span> &nbsp;&nbsp;<span> 

    <input type="checkbox" name="disProperty" value="1" />推荐&nbsp;
    <input type="checkbox" name="disProperty" value="2"/>特价&nbsp;
    <input type="checkbox" name="disProperty" value="3"/>精品&nbsp;
    <input type="checkbox" name="disProperty" value="4"/>畅销 
    </span></div>
    
       </div>
    <!-- <hr class="dotline"/> -->
    
        
      <div style="width: 70%;padding-left: 300px;margin-top: 10px;border:0px">
<input type="submit" value="修改" onclick="return saveAndAdd()"/> <input type="button" value="返回" onclick="javascript:history.go(-1);" />
    </div>
	</form>
</div>
    <script type="">
            $("#subjectId").val('${book.bookSubjectid}');
            $("#bookType").val('${book.bookType}');
            $("#stockNum").val('${book.stockNum}');
            $("#shopState").val('${book.shopState}');   

     var shopState=${book.shopState};
     if(shopState==1){
     $("#upjia").attr("checked","checked");
     }
     if(shopState==2){
     $("#downjia").attr("checked","checked");
       disable();
     }

  //属性
     var pro="${book.disproperty}";
     var  pros=pro.split(",");
      for(var i=0;i<pros.length;i++)
     {
      $('input[name="disProperty"]').each(function(){  
			   var str=$(this).val();
                if(pros[i]==str){
                $(this).attr("checked","checked");
                }
			   });  
     }
     var weight='${book.weight}'+"";
     weight=weight.indexOf("千克");
    if(weight!=-1){
    $("#qianke").get(0).selectedIndex=0;
      }
     else{
    weight='${book.weight}'+"";
    weight=weight.indexOf("克");
    if(weight!=-1){
    $("#qianke").get(0).selectedIndex=1;
   }
    }
    </script>
	</body>
</html>
