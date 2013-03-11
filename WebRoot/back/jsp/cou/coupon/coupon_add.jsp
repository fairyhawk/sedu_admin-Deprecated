<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加优惠券</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
	$().ready(function() {
			$("#addForm").validate({
				rules : {
					"coupon.title" : {
						required : true
					},
					"coupon.price" : {
						required : true,
						number : true
					},
					"coupon.creadeTime" : {
						required : true
					},
					"coupon.stopTime" : {
						required : true
					},
					"coupon.num" : {
						required : true,
						digits : true
					},
					"coupon.toScore" : {
						required : true,
						digits : true
					},
					"coupon.cInfo" : {
						required : true,
						maxlength : 500
					}
				},
				messages : {
					"coupon.title" : {
						required : "活动名称必须填写"
					},
					"coupon.price" : {
						required : "金额必须填写",
						number : "价格只能是数字"
					},
					"coupon.creadeTime" : {
						required : "活动开始时间必须填写"
					},
					"coupon.stopTime" : {
						required : "活动结束时间必须填写"
					},
					"coupon.num" : {
						required : "请输入数量",
						digits : "请输入数字"
					},
					"coupon.toScore" : {
						required : "请输入积分兑换数",
						digits : "请输入数字"
					},
					"coupon.cInfo" : {
						required : "说明不能为空",
						maxlength : "说明不能超过500个字"
					},
			    "coupon.courseId": {
	            	required:"请选择课程",
	            	min: "请选择课程"
			    }
				},
	    	 errorPlacement: function(error, element) {
	            if ( element.is(":radio") )
	                error.appendTo( element.parent().next().next() );   
	            else if ( element.is(":checkbox") )   
	                error.appendTo ( element.next() );   
	            else  
	                error.appendTo( element.parent().next() );   
	        }, 
	        success: function(label) {
	        	
	        }
			});
		});
		
		function showPicDiv() {
		var div = $("#selec_pic_div").css("display", "block");
		if(div.attr("new")=="true") {
			$("<a href='#'><img src='<%=contextPath%>/back/images/coupon/1.jpg' ondblclick=selectPic('1.jpg') width='200' height='100'></img></a>").appendTo(div);
			div.html(div.html() + "&nbsp;&nbsp;");
			$("<a href='#'><img src='<%=contextPath%>/back/images/coupon/2.jpg' ondblclick=selectPic('2.jpg') width='200' height='100'></img></a>").appendTo(div);
			div.html(div.html() + "&nbsp;&nbsp;");
			$("<a href='#'><img src='<%=contextPath%>/back/images/coupon/default.jpg' ondblclick=selectPic('default.jpg') width='200' height='100'></img></a>").appendTo(div);
			div.html(div.html() + "&nbsp;&nbsp;");
			$("<a href='#'><input type='button' value='取消' onclick='closePicDiv()'/>").appendTo(div);
			div.attr("new", "false");
		}
	}
	
	function selectPic(pic) {
		$("input[name='coupon.picPath']").val(pic);
		$("#picImg").attr("src", '<%=contextPath%>/back/images/coupon/' + pic);
		closePicDiv();
	}
	
	function closePicDiv() {
		$("#selec_pic_div").css("display", "none");
	}
	
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
	
	function checkSubmit(){
		var fid = document.getElementById('courseId').value;
		
		if(fid == null || fid == -1 || fid == 0){
			alert(请选择课程);
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/cou/coupon!addCoupon.action" method="post" name="addForm" id="addForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加优惠券</font>
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td rowspan="4" colspan="2" width="220px">
						<img id="picImg" name="picImg" width="500px" height="200px" src="<%=contextPath%>/back/images/coupon/default.jpg"/><br/>
						<input type="hidden" id="picPath" name="coupon.picPath"/>
						<a href="javascript:void(0)" onclick="showPicDiv()">修改图片</a>
					</td>
					<td align="right" width="20%">
						活动名称：
					</td>
					<td class="lists_tleft">
						<input type="text" name="coupon.title" id="title" maxlength="20"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;" width="20%"></td>
				</tr>
				<tr>
					<td align="right" width="20%">
						优惠券金额：
					</td>
					<td class="lists_tleft">
						<input type="text" name="coupon.price" id="price" maxlength="10"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;" width="20%"></td>
				</tr>
				<tr>
					<td align="right">
						活动开始时间：
					</td>
					<td class="lists_tleft">
						<input type="text" name="coupon.createTime" readonly id="creadeTime" onclick="WdatePicker()"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td align="right">
						活动结束时间：
					</td>
					<td class="lists_tleft">
						<input type="text" name="coupon.stopTime" readonly id="stopTime" onclick="WdatePicker()"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td align="right">
						数量：
					</td>
					<td class="lists_tleft" colspan="3">
						<input type="text" name="coupon.num" id="num"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td align="right">
						积分兑换数：
					</td>
					<td class="lists_tleft" colspan="3">
						<input type="text" name="coupon.toScore" id="toScore"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td align="right" valign="top" width="13%">
						优惠券说明：
					</td>
					<td class="lists_tleft" colspan="3">
						<textarea rows="12" cols="80" maxlength="500" style="width:600px;height:120px" name="coupon.cInfo" id="cInfo"  class="{required:true,maxlength:500}" ></textarea>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td colspan="5">
						<input type="submit" value="增加"/><input type="button" value="返回" onclick="history.go(-1)"></input>
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
	</form>
</div>
</body>
</html>
