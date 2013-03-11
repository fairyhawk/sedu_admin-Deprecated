<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改优惠券</title>
		<script type="text/javascript"
	src="<%=contextPath%>/js/web/public/alert.js"></script>
		
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" href="<%=contextPath%>/styles/usercenter/uc_public.css" type="text/css"></link>
				<link rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css" type="text/css"></link>
						<link rel="stylesheet" type="text/css" media="screen"
	href="<%=importURL%>/uploadify/uploadify.css" />
<script type="text/javascript"
	src="<%=importURL%>/uploadify/swfobject.js"></script>
<script type="text/javascript"
	src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4_headimg.js"></script>
	
		<SCRIPT>
		window.history.go(1);
  var fileuploadIndex=0;
		  function uploadifyUpload(){
			 if(fileuploadIndex==0)
			 {
				 alert("请选择图片 ");
				 return;
			 }			
			 var num = $('#fileupload').uploadifySettings('queueSize');
			 if (num == 0) {
			 alert("请选择图片 ");
			 return;
			 }			
			$('#fileupload').uploadifyUpload();
	
		} 

		 $(document).ready(function()
				    {
				        $("#fileupload").uploadify({
				                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
				                    'script'  :'http://tp.highso.cn:8080/upload!coupon.action;jsessionid=<%=session.getId()%>',
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
				                     onSelect:function(event, queueID, fileObj){
				                     	fileuploadIndex=1;
				                    	$("#fileQueue").html("");
				                    	if(fileObj.size>512000){

				                    		//msg("文件 太大");

				                    		//msg("文件 太大");
				                    		alert("文件太大最大上传512kb");

				                    		return;
				                    	}
				                    	onChangeImg();
				                    	},
				                    onComplete: function (event, queueID, fileObj, response, data)
				                    { 
				                    	$("#couponpic").attr("src","http://import.highso.org.cn/upload/coupon/"+response);
				                    	$("#coupontypePicPath").val(response);		
				                    	$("#couponpic").show();
				                    },
				                    onError: function(event, queueID, fileObj,errorObj)
				                    {
				                    	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+" 上传失败</font>");
				                       // alert("文件:" + fileObj.name + "上传失败");
				                    	//}
				                    }/* ,
				                    onCancel: function(event, queueID, fileObj)
				                    {
				                        alert("取消了" + fileObj.name);
				                    } */
				                });

				    });
		
		
		function onChangeImg(){
				document.getElementById('chuanZhiYouTupian').style.display='block';
				document.getElementById('meiYouTuPian').style.display='none';
		}
		//点一级分类json获取二级分类
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
				document.getElementById('fSortId').options.length = 0;  //清空原有的option 
				document.getElementById('sSortId').options.length = 0;  //清空原有的option 
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#courseId").html(str); 
	   		}   
				   		
			function onchangecallback(result){   
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
					url : "<%=contextPath %>/finance/coupon!getChildSortByIdd.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('erdddror');      
					}, 
					success:onchangecallbackcourse1  
					});  
			} 
			function onchangecallbackcourse1(result){
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].pId+"'>"+result.entity[i].coursesortName+"</option>"  
					alert(str);
				}  
				$("#tSortId").html(str);
			 } 
		function startVSendTime()
		{
			 //开始时间
			var srartDate=document.getElementById("createTime").value;
			//结束时间
			var endDate=document.getElementById("stopTime").value;
			if(endDate!="" && srartDate>endDate){
				alert("使用起始日期不能小于使用截止日期");
				var srartDate=document.getElementById("createTime");
				srartDate.value="";
				srartDate.focus();
			}
		}
	function endVSendTime()
		{
			 //开始时间
			var srartDate=document.getElementById("createTime").value;
			//结束时间
			var endDate=document.getElementById("stopTime").value;
			if(srartDate!="" && srartDate>endDate){
				alert("使用起始日期不能小于使用截止日期");
				var enddate=document.getElementById("stopTime");
				enddate.value="";
				enddate.focus();
			}
		}
	function changke(){
		document.getElementById("displayAdd").style.display="none";  //隐藏
		document.getElementById("conponIdAndName").style.display="block"; //显示
	}
	function changquxiao(){
		document.getElementById("displayAdd").style.display="block";  //隐藏
		document.getElementById("conponIdAndName").style.display="none"; //显示
	}
	function radioLiJian(){
		document.getElementById('zuizhongdepreferentialPrice').value=0;
		document.getElementById('youHuiZheKouXiuGaiId').style.display='none';
		document.getElementById('youHuiJinEXiuGaiId').style.display='block';
		document.getElementById('youHuiZheKouSelect').style.display='none';
		document.getElementById('zongTextIdZui').style.display='block';
		document.getElementById('youHuiZheKouSelect').value='';
		document.getElementById('preferentialPriceee3').value='';
		
		document.getElementById('zongTextId').style.display='none';
		document.getElementById('zongYouHuiID').style.display='none';
	}
	function radioZheKou(){
		document.getElementById('zuizhongdepreferentialPrice').value=0;
		document.getElementById('zongTextIdZui').style.display='none';
		document.getElementById('youHuiZheKouXiuGaiId').style.display='block';
		document.getElementById('youHuiJinEXiuGaiId').style.display='none';
		document.getElementById('youHuiZheKouSelect').style.display='block';
		document.getElementById('preferentialPriceee1').value=0;
		
		
		document.getElementById('zongTextId').style.display='none';
		document.getElementById('zongYouHuiID').style.display='none';
	}
	
	function onSubmit(){
			var title=document.getElementById('title').value;
				if(title==''){
				alert("优惠券名称不能为空");
				return false;
				
				}
		var cooperativeId=document.getElementById('cooperativeId').value;
				if(cooperativeId=='-1'){
				alert("请选择合作商");
				return false;
				
				}
		var cType=document.getElementById('cType');
		var cType2=document.getElementById("cType2");
		var zuizhongdepreferentialPrice=document.getElementById('zuizhongdepreferentialPrice').value;
		if(cType.checked==true){
				if(zuizhongdepreferentialPrice==0){
				alert("请选择优惠折扣");
				return false;
				}
		}
		 if(cType2.checked==true){
		 
		 	var leastPrice=document.getElementById('leastPrice').value;
				var zuizhongdepreferentialPrice=document.getElementById('zuizhongdepreferentialPrice').value;
				if(zuizhongdepreferentialPrice==''){
					alert("请输入优惠金额");
					return false;
				}else if(/^(\-?)(\d+)$/.test(zuizhongdepreferentialPrice)==false || zuizhongdepreferentialPrice>10000 || zuizhongdepreferentialPrice<=0){
					alert("优惠金额只能为1~10000之间的整数！");
					document.getElementById('preferentialPriceee3').value='';
					return false;
				}
				}
				
			var leastPrice=document.getElementById('leastPrice').value;
				if(leastPrice==''){
				alert("请输入使用限额");
				return false;
				}else 	if(leastPrice>10000 || leastPrice<=0 ||  /^(\-?)(\d+)$/.test(leastPrice)==false){
					alert("使用限额只能为1~10000之间的整数！");
					document.getElementById('leastPrice').value='';
					return false;
				}else if(cType2.checked==true){
						var zuizhongdepreferentialPrice=document.getElementById('zuizhongdepreferentialPrice').value;
						if(parseInt(zuizhongdepreferentialPrice)>=parseInt(leastPrice)){
							alert('优惠金额不能大于或等于使用限额！');
							document.getElementById('leastPrice').value='';
							return false;
						}
						
					}
		var danyikdecheng=document.getElementById('danyikdecheng');
			if(danyikdecheng.checked==true){
				var subjectId=document.getElementById('subjectIdd').value;
				if(subjectId==-1){
					alert('请选择单选项目！');
					return false;
				}
				
				}
var createTime=document.getElementById('createTime').value;
				if(createTime==''){
				alert("请输入起始日期");
				return false;
				
				}	
		var stopTime=document.getElementById('stopTime').value;
				if(stopTime==''){
				alert("请输入截止日期");
				return false;
				
				}	
				var useSum=document.getElementById('useSum').value;
				if(useSum==''){
					alert("请输入优惠券编码数量");
				return false;
				}else if(useSum>10000  || useSum<=0 || /^(\-?)(\d+)$/.test(useSum)==false){
					alert("优惠券编码数量只能为1~10000的整数！");
					document.getElementById('useSum').value='';
					return false;
				}
			if(confirm('确认提交？')==true){
				return true;
			}else{
				return false;
			}	
	}
	
	
	
	function changketijiao(pId){
		var partten =/^[A-Za-z0-9]+$/;
		var  companyCooe=document.getElementById('companyCooe').value;
		if(document.getElementById('company').value==''){
			alert('合作商名字不能为空！');
			return false;
		}else if(document.getElementById('companyCooe').value==''){
			alert('合作商代码不能为空！');
			return false;
		}else if(!partten.test(companyCooe)){
				alert('合作商代码输入格式不对，重新输入！');
				document.getElementById('companyCooe').value='';
		}else if(companyCooe.length>4  || companyCooe.length<4){
			 	alert('合作商代码只能输入4位，重新输入！');
			 	document.getElementById('companyCooe').value='';
		}else{
			var company= document.getElementById('company').value;
			var companyCooe=document.getElementById('companyCooe').value;
				$.ajax({  
					url : "<%=contextPath%>/finance/coupon!createConperation.action",  
					data : {company : company,companyCooe:companyCooe},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('添加合作商错误！');      
					}, 
					success:onchangecallbackcoursee  
					});
				  }
				
			} 
		function onchangecallbackcoursee(result){ //处理返回的课程JSON  
				$('#cooperativeId').find('option').end().append("<option value='"+result.jumpUrl+"' selected='selected'>"+result.returnMessage+"</option>");
		  		 document.getElementById("displayAdd").style.display="block";  //隐藏
				document.getElementById("conponIdAndName").style.display="none"; //显示
				document.getElementById('company').value="";
				document.getElementById('companyCooe').value="";
	   		}   
	   		  		//检验。。
	  function onFocusHeZuoshang(pId){
			var company= document.getElementById('company').value;
				$.ajax({  
					url : "<%=contextPath%>/finance/coupon!checkConponName.action",  
					data : {company : company},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('检查合作商名字错误！');      
					}, 
					success:onchangecallbackcourseee  
					});
				  }
		function onchangecallbackcourseee(result){ //处理返回的课程JSON  
				if(result.returnMessage==''){
					return true;
				}else{
					alert(result.returnMessage);
					document.getElementById('company').value='';
				}
	   		} 
	   function onFocusHeZuoshangCoding(pId){
			var companyCooe= document.getElementById('companyCooe').value;
				$.ajax({  
					url : "<%=contextPath%>/finance/coupon!checkConponCoding.action",  
					data : {companyCooe : companyCooe},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('检查合作商编码错误！');      
					}, 
					success:onchangecallbackcourseeee  
					});
				  }
		function onchangecallbackcourseeee(result){ //处理返回的课程JSON  
				if(result.returnMessage=='1'){
					return true;
				}else{
					alert(result.returnMessage);
					 document.getElementById('companyCooe').value='';
				}
	   		}
	   		
	   		function danyikecheng(){
	   				//alert($("#xianshiSubject").html());
	   			  document.getElementById("xianshiSubject").style.display="block";
	   			  document.getElementById('subjectIdd').value=-1;
	   			  document.getElementById('subjectId1').value=-1;
	   		}
	   		function suoyoukecheng(){
	   				document.getElementById("xianshiSubject").style.display="none";
	   			   document.getElementById('subjectIdd').value=0;
	   			   if(${coupontype.subjectId}>=1){
	   			   		document.getElementById("sanjiliandong").style.display="none";
	   			   }
	   		}
	   		function changeSubjectId(){
	   			document.getElementById('subjectIdd').value=document.getElementById('subjectId1').value;
	   		}
	   		function changeSubjectIdd(){
	   			document.getElementById('subjectIdd').value=document.getElementById('subjectId11').value;
	   		}
	   		
	   //~~~~~~~~~~~~~~~~~~~~~~
	   //最终的preferentialPriceee值
	   function  preferentialPriceee1d(){
	   			 document.getElementById('zuizhongdepreferentialPrice').value=document.getElementById('preferentialPriceee1').value;
	   }
	  function preferentialPriceee2d(){
	  			document.getElementById('zuizhongdepreferentialPrice').value=document.getElementById('preferentialPriceee2').value;
	   }
	    function  preferentialPriceee3d(){
	   			 document.getElementById('zuizhongdepreferentialPrice').value=document.getElementById('preferentialPriceee3').value;
	   			var preferentialPriceee3=  document.getElementById('preferentialPriceee3').value;
	   			var leastPrice=  document.getElementById('leastPrice').value;
	   		if(leastPrice!=''){
	   			if(/^(\-?)(\d+)$/.test(preferentialPriceee3)==false || preferentialPriceee3>10000 || preferentialPriceee3<=0){
					alert("优惠金额只能为1~10000之间的整数！");
					document.getElementById('preferentialPriceee3').value='';
					return false;
				}else if(parseInt(preferentialPriceee3)>=parseInt(leastPrice)){
					alert('优惠金额不能大于或等于使用限额！');
	   				document.getElementById('preferentialPriceee3').value='';
	   				document.getElementById('zuizhongdepreferentialPrice').value='';
	   			}
	   			}
	   }
	  function preferentialPriceee4d(){
	  			document.getElementById('zuizhongdepreferentialPrice').value=document.getElementById('preferentialPriceee4').value;
	  			var preferentialPriceee4=  document.getElementById('preferentialPriceee4').value;
	   			var leastPrice=  document.getElementById('leastPrice').value;
	   		if(preferentialPriceee4!=''){
	   			if(/^(\-?)(\d+)$/.test(preferentialPriceee4)==false || preferentialPriceee4>10000 || preferentialPriceee4<=0){
					alert("优惠金额只能为1~10000之间的整数！");
					document.getElementById('preferentialPriceee4').value='';
					return false;
				}else if(parseInt(preferentialPriceee4)>=parseInt(leastPrice)){
	   				alert('优惠金额不能大于或等于使用限额！');
	   				document.getElementById('preferentialPriceee4').value='';
	   				document.getElementById('zuizhongdepreferentialPrice').value='';
	   			}
	   			}
	   }
		</SCRIPT>
<style type="">
.myselect {
	float: left;width:190px
}
;
</style>
	</head>
	<body>
		<div>
		<form action="<%=contextPath%>/finance/coupon!updateCouponType.action" method="post" name="planClockForm" id="planClockForm" onsubmit="return onSubmit();">
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
			<tr >
				<td class="td_wid_l" >
				<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top">
					<font class="lists_fleft">修改优惠券</font>
					<font class="lists_fright">
					</font>
				</td>
				<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
			</tr>
			<tr>
			<td width="12"  class="lists_bor"></td>
		<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr height="30">
					<td width="20%" class="lists_tright">
						<div  ><font style="color: red">*</font>优惠券类型：&nbsp;&nbsp;</div>
					</td>
					<td class="lists_tleft" colspan="2">
						<input  type="hidden" value="<s:property value="coupontype.id"/>" name="coupontype.id"/>
						<s:if test="coupontype.cType==1">
						<input  type="radio" checked="checked" name="coupontype.cType" id="cType" onclick="radioZheKou()"  value="1" /> 折扣优惠券
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  type="radio"  onclick="radioLiJian()" name="coupontype.cType" value="2" id="cType2"/> 立减定额优惠券
						</s:if>
						<s:if test="coupontype.cType==2">
						<input  type="radio" checked="checked" name="coupontype.cType" id="cType" onclick="radioZheKou()"  value="1"/> 折扣优惠券
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  type="radio"  onclick="radioLiJian()" name="coupontype.cType" value="2" id="cType2" checked="checked"/> 立减定额优惠券
						</s:if>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						<div ><font style="color: red">*</font>优惠券名称:&nbsp;&nbsp;&nbsp;</div>
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:property value="coupontype.title"/>" name="coupontype.title" id="title" style="width:180px;" class={required:true,maxlength:100,minlength:4} maxlength="20" />
					<span class="jqVaildate"></span>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						<div >	<font style="color: red">*</font>合作商：&nbsp;&nbsp;&nbsp;</div>
					</td>
					<td class="lists_tleft" colspan="2">
				<div style="float:left">
										<s:select list="cooperationList" cssClass="myselect"
											id="cooperativeId" name="coupontype.cooperativeId"
											listKey="id" listValue="companyName" headerKey="-1"  
											headerValue="--合作商--">
										</s:select>
										</div>
							&nbsp;&nbsp;<font style="float: left"><div  id="displayAdd"><input type="button" value="添加" name=""  onclick="changke()"/><div></font>
					<span class="jqVaildate"></span>
					</td>
					
				</tr>
				<tr height="30">
					<td>
					</td>
					<td class="lists_tleft" colspan="2">
						<div id="conponIdAndName" style="display: none">
						合作商名字:&nbsp;&nbsp;<input type="text" value="" name="company" maxlength="20" id="company" style="width:180px;" onchange="onFocusHeZuoshang()"/></br>
						合作商代码：<input type="text" value="" name="companyCooe" id="companyCooe" style="width:180px;" onchange="onFocusHeZuoshangCoding()"/>&nbsp;&nbsp;请输入合作商代码，该代码用于优惠编码规则。只能输入英文、数字。４位数。</br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"   value="提交" name=""  onclick="changketijiao()" />&nbsp;&nbsp;&nbsp;<input type="button" value="取消" name=""  onclick="changquxiao()"/>
						</div>
					<span class="jqVaildate"></span>
					</td>
				</tr>
				<tr height="30">
									<td class="lists_tright">
										<div >图片：
											&nbsp;&nbsp;&nbsp;</div>
									</td>
									<td class="lists_tleft" colspan="2">
						<div id="updiv" style="float: left;">
								<div style="float: left">	
													 <input type="file"  id="fileupload" />
												</div>	
														
													<div id="fileQueue" style="margin-top: 30px"></div>
													<div style="float:left"><input type="button" class="button_7" id="uploadId"
														 value="上传"
														style="margin-top: 7px; display: none" />
														</div>
<div style="float:left;"><input type="button" onclick="uploadifyUpload()" value="上传" ></div>
<span style="float: left;margin-top: 30px;padding-left: 0px">
					<div id="chuanZhiYouTupian" style="display: none;">
						<img src="http://import.highso.org.cn/upload/coupon/<s:property value="coupontype.picPath" />" width="100" height="100"  id="couponpic"/>
					</div>
					<s:if test="coupontype.picPath==''">
						<div id="meiYouTuPian">
							没有选图片！
						</div>
					</s:if>
					<s:if test="coupontype.picPath!=''">
							<div id="meiYouTuPian">
							<img src="http://import.highso.org.cn/upload/coupon/<s:property value="coupontype.picPath"/>" alt="" width="100px" height="100px"  id="couponpic"/></span>
							</div>
					</s:if>
					</td>


												
										
					<span class="jqVaildate"></span>
					<input  type="hidden" name="coupontype.picPath" id="coupontypePicPath" value="${coupontype.picPath }" />
					</td>
								</tr>
				
				<tr height="30" id="youhuizhekou">
					<td class="lists_tright">
						<div style="display: none;"  id="youHuiZheKouXiuGaiId"><font style="color: red">*</font>优惠折扣:&nbsp;&nbsp;&nbsp;</div>
						<div style="display: none;"  id="youHuiJinEXiuGaiId"><font style="color: red">*</font>优惠金额:&nbsp;&nbsp;&nbsp;</div>
					<s:if test="coupontype.cType==1">
						<div  id="zongYouHuiID"><font style="color: red">*</font>优惠折扣:&nbsp;&nbsp;&nbsp;</div>
					</s:if>
					<s:if test="coupontype.cType==2">
						<div  id="zongYouHuiID"><font style="color: red">*</font>优惠金额:&nbsp;&nbsp;&nbsp;</div>
					</s:if>
					</td>
					<td class="lists_tleft" colspan="2">
					<!-- 最终的值 -->
					<input type="hidden" name="coupontype.preferentialPrice"  value="<s:property value="coupontype.preferentialPrice"/>"   id="zuizhongdepreferentialPrice"/>
				<input type="hidden" name="coupontype.subjectId"  value="<s:property value="coupontype.subjectId"/>"   id="subjectIdd"/>
						<div style="float: left;display: none;" id="youHuiZheKouSelect">
						<select name="" id="preferentialPriceee1"   onchange="preferentialPriceee1d()">
											<option value="0">--请选择优惠折扣--</option>
											<option value="0.5">0.5</option>
											<option value="1.0">1.0</option>
											<option value="1.5">1.5</option>
											<option value="2.0">2.0</option>
											<option value="2.5">2.5</option>
											<option value="3.0">3.0</option>
											<option value="3.5">3.5</option>
											<option value="4.0">4.0</option>
											<option value="4.5">4.5</option>
											<option value="5.0">5.0</option>
											<option value="5.5">5.5</option>
											<option value="6.0">6.0</option>
											<option value="6.5">6.5</option>
											<option value="7.0">7.0</option>
											<option value="7.5">7.5</option>
											<option value="8.0">8.0</option>
											<option value="8.5">8.5</option>
											<option value="9.0">9.0</option>
											<option value="9.5">9.5</option>
										</select>
						&nbsp;&nbsp;折 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">该优惠券折扣额，如2折优惠券，则请输入“0.2”。</font>
					</div>
					
					<s:if test="coupontype.cType==1">
						<!--<input type="text" value="<s:property value="coupontype.preferentialPrice"/>" name="coupontype.preferentialPrice" id="preferentialPrice" style="width:180px;"/>-->
						<div id="zongTextId">
							<select  id="preferentialPriceee2" onchange="preferentialPriceee2d()"  value="">
											<option value="0">--请选择优惠折扣--</option>
											<option value="0.5">0.5</option>
											<option value="1.0">1.0</option>
											<option value="1.5">1.5</option>
											<option value="2.0">2.0</option>
											<option value="2.5">2.5</option>
											<option value="3.0">3.0</option>
											<option value="3.5">3.5</option>
											<option value="4.0">4.0</option>
											<option value="4.5">4.5</option>
											<option value="5.0">5.0</option>
											<option value="5.5">5.5</option>
											<option value="6.0">6.0</option>
											<option value="6.5">6.5</option>
											<option value="7.0">7.0</option>
											<option value="7.5">7.5</option>
											<option value="8.0">8.0</option>
											<option value="8.5">8.5</option>
											<option value="9.0">9.0</option>
											<option value="9.5">9.5</option>
										</select>
										<script type="">
							 document.getElementById('preferentialPriceee2').value='${coupontype.preferentialPrice}';
						</script>
						
						&nbsp;&nbsp;折 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">该优惠券折扣额，如2折优惠券，则请输入“0.2”。</font>
							</div>
					</s:if>
					
					
					
					<div id="zongTextIdZui" style="display: none; " >
						<input type="text" onblur="preferentialPriceee3d()" id="preferentialPriceee3" name="preferentialPriceee311" style="width:180px;"/>&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">该优惠券金额，如5元优惠券，则请输入“5”。</font>
					</div>
					
					
					<s:if test="coupontype.cType==2">
					<div id="zongTextId">
						<input type="text" value="<s:property value="coupontype.preferentialPrice"/>" onblur="preferentialPriceee4d()" id="preferentialPriceee4" style="width:180px;"/>&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">该优惠券金额，如5元优惠券，则请输入“5”。</font>
					</div>
					</s:if>
					
					
					
					
					<span class="jqVaildate"></span>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						<div><font style="color: red">*</font>使用限额:&nbsp;&nbsp;&nbsp;</div>
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text"  name="coupontype.leastPrice" id="leastPrice" style="width:180px;"/>&nbsp;&nbsp;元以上&nbsp;&nbsp;<font style="color: red">只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入“300”。</font>
						<script type="">
										document.getElementById('leastPrice').value=${coupontype.leastPrice};
						</script>
					<span class="jqVaildate"></span>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						<div ><font style="color: red">*</font>适用范围:&nbsp;&nbsp;&nbsp;</div>
					</td>
					<td class="lists_tleft" colspan="2">
					<s:if test="coupontype.subjectId==0">
						<input  type="radio"  checked="checked"  name="aaaa" value="0" onclick="suoyoukecheng()"/> 所有课程
					</s:if>
					<s:if test="coupontype.subjectId>=1">
						<input  type="radio"   value="0" onclick="suoyoukecheng()" name="bbbb"/> 所有课程
					</s:if>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:if test="coupontype.subjectId==0">
						<input  type="radio"   id="danyikdecheng" name="aaaa"  onclick="danyikecheng()"  value="1"/> 单选项目
					</s:if>
					<s:if test="coupontype.subjectId>=1">
						<input  type="radio"  checked="checked" name="bbbb"  id="danyikdecheng"  onclick="danyikecheng()"  value="1"/> 单选项目
					</s:if>
					</td>
				</tr>
				<tr height="30">
					<td>
					</td>
					<td class="lists_tleft" colspan="2">
					<div style="display: none;"  id="xianshiSubject">
							    	<s:select name="shibiedanxuan" id="subjectId1"  list="subjectList"  headerKey="-1" headerValue="--请选择--"
											listKey="subjectId" listValue="subjectName"   onchange="changeSubjectId()"
											theme="simple">		
										</s:select>
							    </div>
							   
						<s:if test="coupontype.subjectId>=1">
							<div  id="sanjiliandong" style="float: left">
							<s:select name="shibiedanxuan" id="subjectId11"  list="subjectList"  
								listKey="subjectId" listValue="subjectName"  value="coupontype.subjectId"    onchange="changeSubjectIdd()"
								theme="simple">		
							</s:select>
							<div>
						</s:if>
						
					</td>
				</tr>
				<tr height="30">
									<td class="lists_tright">
										<div><font style="color: red">*</font>使用起始日期:&nbsp;&nbsp;&nbsp;</div>
									</td>
									<td class="lists_tleft" colspan="2">
										<input type="text" name="coupontype.useTime"   value="<s:property value="coupontype.stringCreateTime"/>"
											id="createTime" readonly
											onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
											onchange="startVSendTime()" />
											<!-- WdatePicker({startDate:'%y-%M-01 00:00:00' 去掉当前时分秒 -->
										&nbsp;&nbsp;
										<font style="color: red">该优惠券使用的开始时间。</font>
										<span class="jqVaildate"></span>
									</td>
								</tr>
				<tr height="30">
					<td class="lists_tright">
						<div><font style="color: red">*</font>使用截止日期:&nbsp;&nbsp;&nbsp;</div>
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" name="coupontype.stopTime" id="stopTime" readonly  value="<s:property value="coupontype.stringstopTime"/>"  
						onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="endVSendTime()" />&nbsp;&nbsp;<font style="color: red">该优惠券使用的截止时间。</font>
					<span class="jqVaildate"></span>
					</td>
				</tr>
				<tr height="30">
					<td class="lists_tright">
						<div ><font style="color: red">*</font>生成优惠编码数量:&nbsp;&nbsp;&nbsp;</div>
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:property value="coupontype.cNum"/>" name="coupontype.cNum" id="useSum" style="width:180px;"/>&nbsp;&nbsp;<font style="color: red">必须是数字，数字不得大于10000。</font>
					<span class="jqVaildate"></span>
					</td>
				</tr>
				<!-- 
				<tr height="30">
					<td>
						<div style="float: right">	兑换积分:&nbsp;&nbsp;&nbsp;</div>
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="text" value="<s:property value="coupontype.cToscore"/>" name="coupontype.cToscore" id="cToscore" style="width:180px;"/>&nbsp;&nbsp;<font style="color: red">非必填。</font>
					<span class="jqVaildate"></span>
					</td>
				</tr> -->
				<tr height="30">
					<td>
					</td>
					<td class="lists_tleft" colspan="2">
						  <input type="submit"   value="下一步"/>
					<span class="jqVaildate"></span>
					</td>
				</tr>
	</table>
	</form>
		</div>
		<div id="zyong" style="position: absolute;margin-left: -1500px;margin-top: -1500px;z-index: 10"></div>
		<div id="zyong2" style="position: absolute;margin-left: -1000px;margin-top: -1000px;z-index: 11"></div>
	</body>

</html>
