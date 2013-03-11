<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>图书详情</title>
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
	    </script>
		<style >
		body{margin-left: 10px;}
		body { font-size:14px; color:#444444; background:#fff; font-family:"����", "Tahoma", "Helvetica Neue", Arial, Helvetica, sans-serif; }
       .dotline {float:left;BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted;width:60%;height:1px;margin-top: 15px;margin-bottom: 10px}
       div{float:left;text-align:left;width:100%;border:1px solid #b5d6e6;padding-top: 3px;padding-bottom: 3px}
       span{float:left;padding-right: 5px;width:80%}
       .leftspan{padding-left:}
       .border0{
       border:0px;
       }
       #fileuploadUploader{float: left;}
       
        </style>
    </head>
	<body >
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">图书详情</font> <font class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
	</table>
<div style="border:1px solid #b5d6e6;">
	<form action="<%=contextPath%>/book/book!updateBook.action" method="post" name="updateBookFomr" id="updateBookFomr"  >
	<input type="hidden" name="book.bookImg" id="bookImg" value="${book.bookImg}" />
	<input type="hidden" name="book.bookSmallimg" id="bookSmallimg" value="${book.bookSmallimg}"/>
    <div style="width: 100%;border:0px">
    <p><b>商品基本信息</b></p>
    <div ><font color="red">*</font>商品名称 :&nbsp;<input type="text" name="book.bookName" id="bookName" maxlength="60" class="{required:true}" value="${book.bookName}" disabled="disabled" /></div>
    <div class="border0"><font color="red">*</font>商品价格： 原价&nbsp;<input type="text" name="book.price" id="price" value="${book.price}" class="{number:true,required:true,max:9999,min:0}"/>
    &nbsp;&nbsp;现价&nbsp;<input type="text" name="book.nowPrice" id="nowPrice" class="{number:true,required:true,max:9999,min:0}" value="${book.nowPrice}"/>&nbsp;&nbsp;
       折扣价&nbsp;<input type="text" class="{number:true,max:9999,min:0}" name="book.rebatePrice" id="rebatePrice" value="${book.rebatePrice}"/>
    </div>
   
    <div  ><font color="red">*</font>所属项目：
  	<s:select name="book.bookSubjectid" id="subjectId" list="subjectList" listKey="subjectId" listValue="subjectName" headerValue="所有项目" headerKey="-1" disabled="disabled"></s:select>
    </div>
       <div  class="border0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>分类：
     <select name="book.bookType" id="bookType" >
      <option value="1">教材</option>
      <option value="2">教辅</option>
      <option value="3">大纲</option>
      <option value="4">套装</option>
     </select>
    </div>

     <div >

   <span style="float:left;"> &nbsp;&nbsp;<font color="red">*</font>商品图片： &nbsp;&nbsp;
   <s:if test="book.bookImg!=null && book.bookImg!=''">
   <img src="http://import.highso.org.cn/upload/book/${book.bookImg}" alt="" width="100px" height="100px"  style="padding-left: 10px" id="bookpic"/>
   </s:if>
   <s:else>
      <img src="http://import.highso.org.cn/images/usercenter/leftnav_2.gif" alt="" width="100px" height="100px"  style="display: none;padding-left: 10px" id="bookpic"/>
   </s:else>
   </span>   
    </div>
  
    <div class="border0">
   <span style="float:left;"><font color="red">*</font>商品缩略图： &nbsp;&nbsp;
    <s:if test="book.bookSmallimg!=null && book.bookSmallimg!=''">
   <img src="http://import.highso.org.cn/upload/smallbook/${book.bookSmallimg}" alt="" width="100px" height="100px" style="padding-left: 10px" id="slpic"/>
   </s:if>
       <s:else >
   <img src="http://import.highso.org.cn/images/usercenter/leftnav_2.gif" alt="" width="100px" height="100px" style="display: none;padding-left: 10px" id="slpic"/>
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
   <select name="qianke">
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
    <div ><span>上架属性 </span> &nbsp;&nbsp;<span><input type="radio" id="upjia" name="book.shopState" value="1" checked="checked" onclick="nodisable()"/>上架<input id="downjia" name="book.shopState"  type="radio" value="2" onclick="disable()"/>下架</span></div>
    <div  class="border0" ><span>显示属性</span> &nbsp;&nbsp;<span> 

    <input type="checkbox" name="disProperty" value="1" />推荐&nbsp;
    <input type="checkbox" name="disProperty" value="2"/>特价&nbsp;
    <input type="checkbox" name="disProperty" value="3"/>精品&nbsp;
    <input type="checkbox" name="disProperty" value="4"/>畅销 
    </span></div>
    
       </div>
    <!-- <hr class="dotline"/> -->
    
        
      <div style="width: 70%;padding-left: 300px;margin-top: 10px;border:0px">
 <a href="javascript:history.go(-1);">返回</a>
    </div>
	</form>
</div>
    <script type="">
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
     $("#subjectId").val('${book.bookSubjectid}');
      $("#bookType").val('${book.bookType}');
     $("#stockNum").val('${book.stockNum}');
    $("#shopState").val('${book.shopState}');   

     var shopState=${book.shopState };
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


  $('input').each(function(){             
                $(this).attr("disabled","disabled");
			   });  
 $('select').each(function(){             
                $(this).attr("disabled","disabled");
			   });  
 $('textarea').each(function(){             
                $(this).attr("disabled","disabled");
			   });  
    
    </script>
	</body>
</html>
