<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改优惠券</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
	$().ready(function() {
			$("#updateForm").validate({
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
			$("<a href='#'><button onclick='closePicDiv()'>取消</button>").appendTo(div);
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
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/cou/coupon!updateCoupon.action" method="post" name="updateForm" id="updateForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
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
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td rowspan="4" colspan="2" width="520px">
						<img id="picImg" name="picImg" width="500px" height="200px" src=<%=contextPath%>/back/images/coupon/<s:property value="coupon.picPath==''?'default.jpg':coupon.picPath"/> /><br/>
						<input type="hidden" id="picPath" name="coupon.picPath" value="<s:property value="coupon.picPath"/>"/>
						<a href="javascript:void(0)" onclick="showPicDiv()">修改图片</a>
					</td>
					<td align="right" width="20%">
						活动名称：
					</td>
					<td class="lists_tleft">
						<input type="text" name="coupon.title" id="title" maxlength="10" value="<s:property value='coupon.title'/>"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;" width="20%"></td>
				</tr>
				<tr>
					<td align="right" width="20%">
						优惠券金额：
					</td>
					<td class="lists_tleft">
						<input type="text" name="coupon.price" id="price" maxlength="10" value="<s:property value='coupon.price'/>"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;" width="20%"></td>
				</tr>
				<tr>
					<td align="right">
						活动开始日期：
					</td>
					<td class="lists_tleft">
						<input type="text" name="coupon.createTime" readonly id="createTime" onclick="WdatePicker()" value="<s:property value='coupon.createTime'/>"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td align="right">
						活动结束日期：
					</td>
					<td class="lists_tleft">
						<input type="text" name="coupon.stopTime" readonly id="stopTime" onclick="WdatePicker()" value="<s:property value='coupon.stopTime'/>"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td align="right">
						数量：
					</td>
					<td class="lists_tleft" colspan="3">
						<input type="text" name="coupon.num" id="num" value="<s:property value='coupon.num'/>"/>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td align="right">
						积分兑换数：
					</td>
					<td class="lists_tleft" colspan="3">
						<input type="text" name="coupon.toScore" id="toScore" value="<s:property value='coupon.toScore'/>" />
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td align="right" valign="top" width="13%">
						模板说明：
					</td>
					<td class="lists_tleft" colspan="3">
						<textarea rows="12" cols="80" style="width:600px;height:120px" maxlength="500" name="coupon.cInfo" id="cInfo"><s:property value='coupon.cInfo'/></textarea>
					</td>
					<td style="color:#ff0000;padding-left:5px;"></td>
				</tr>
				<tr>
					<td colspan="5">
						<input type="hidden" name="coupon.id" value=<s:property value="coupon.id"/> />
						<input type="hidden" name="coupon.courseId" value=<s:property value="coupon.courseId"/> />
						<input type="submit" value="修改"/><input type="button" value="返回" onclick="history.go(-1)"></input>
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
