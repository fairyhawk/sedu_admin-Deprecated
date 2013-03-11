<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>发送指定短信</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>

<style>
#test1 {
	position: absolute;
	width: 500px;
	padding: 0px;
	border: 1px solid black;
	z-index: 100;
	cursor: hand;
	top: 2px;
	right: 10%;
	display: none;
}
#test2 {
	position: absolute;
	width: 500px;
	padding: 0px;
	border: 1px solid black;
	z-index: 100;
	cursor: hand;
	top: 2px;
	right: 10%;
	display: none;
}
</style>

<script language="javascript" type="text/javascript">
var telnum=0;
var couponnum=0;
function ceng()
{
$("#test2").hide();	
$("#test1").show();
}
function close11()
{
	$("#test1").hide();
}

function discode(){
	$("#test1").hide();
	$("#test2").show();	
}
function closetest2(){
	$("#test2").hide();	
}
 function search(){
	 $("#sendLinks").html("");
	 telnum=0;
	  var subjectIdJson=document.getElementById("subjectIdJson").value;
	  document.getElementById("subjectId").value=subjectIdJson;
	   $.ajax({  
					url : "<%=contextPath%>/finance/coupon!returnCusMobile.action",  
					data : {"subject.subjectId": subjectIdJson},
					type : "post",  
					dataType : "json",  //返回json数据 
					error: function(error){ 
						alert('error');      
					}, 
					success:function (result)
					{
						for(var i=0;i<result.entity.length;i++){
							    telnum=telnum+1;
								$("#sendLinks").append( result.entity[i]+',\n');
						}
						 $("#telnum").html(telnum+"个");
						 document.getElementById('telnumm').value=telnum;
					 }
	  		})
	  		
	}

 function addcode(){
	 $("#couponCodes").html("");
	 couponnum=0;
	  var codeId=$("#code").val();
	  document.getElementById("couponCatorgryId").value=codeId;
	   $.ajax({  
					url : "<%=contextPath%>/finance/coupon!returnCouponForCusById.action",  
					data : {"coupon.parentId": codeId},
					type : "post",  
					dataType : "json",  //返回json数据 
					error: function(error){ 
						alert(error);      
					}, 
					success:function (result)
					{
						for(var i=0;i<result.entity.length;i++){
							    couponnum=couponnum+1;
								$("#couponCodes").append( result.entity[i].title+',\n');
						}
						$("#couponnum").html(couponnum+"个");
						 document.getElementById('couponnumm').value=couponnum;
				   }
	  		})
	  	
 }
 function yan(){
	var sendLinks=document.getElementById('sendLinks').value;
	var couponCodes=document.getElementById('couponCodes').value;
	var content=document.getElementById('content').value;
if(sendLinks==''){
	alert('手机号码不能为空！');
	return false;
}
if(couponCodes==''){
	alert('优惠编码不能为空！');
	return false;
}
if(content==''){
	alert('短信内容不能为空！');
	return false;
}
if(couponnum<telnum){
	alert("优惠券编码数量不能少于用户数量");
	return false;
}
 }
 
 function changeLinkNum(){
	 var c=$("#sendLinks").val().split(",");
	 telnum=c.length-1;
	 $("#telnum").html(telnum+"个");
 }
 function changeCodeNum(){
	 var c=$("#couponCodes").val().split(",");
	 couponnum=c.length-1;
	 $("#couponnum").html(couponnum+"个");
 }
 function onchangeValue(sub){
 	var id = sub.value;
 	var subjectName = $(sub).find("option:selected").attr("subjectName");
 	 document.getElementById('subjectNamee').value=subjectName;

 	//var obj=document.getElementByIdx_x(id);
 	//alert(obj.value);      
 //	alert(id.options[id.selectedIndex].innerText);
 }
 $(function(){
	var subject = "";
 	<s:iterator value="subjectList" var="sub">
 		subject = subject + '<option value="${subjectId}" subjectName="${subjectName}">${subjectName}</option>';
 	</s:iterator>
	$("#subjectIdJson").append(subject);
 })
</script>

</head>
<body>
	<div>
		<form action="<%=contextPath%>/finance/coupon!sendSMSForCoupon.action"
			method="post" name="addForm" id="addForm" onsubmit="return yan();" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">发送自定义短信</font> <font
						class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					<td width="12" class="lists_bor"></td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="1">
							
							
							<tr>
								<td><span>
								<img
										src="<%=contextPath%>/back/images/anniu1.gif" onclick="ceng()"
										style="width: 83px; height: 18px; cursor: hand;"
										align="middle" />
										<font id="telnum"></font>
										<input  type="hidden"  name="couponRecordInfo.mobileSum" id="telnumm" value="0"/>
										<input  type="hidden" name="couponRecordInfo.couponCatorgryId" id="couponCatorgryId" value="0"/>
								</span></td>
								<td>
								<input type="button" onclick="discode()" value="添加优惠券"/>
								<font id="couponnum"></font>
								<input  type="hidden" name="couponRecordInfo.conponSum" id="couponnumm" value="0"/>
								<input  type="hidden" name="couponRecordInfo.subjectId" id="subjectId" value="0"/>
								<input  type="hidden" name="couponRecordInfo.subjectName" id="subjectNamee"/>
								</td>

							</tr>
							<tr>
								<td width="25%"><textarea style="width: 300px;"
										name="sendLinks" id="sendLinks" rows="15"
										 align="left" class="{required:true}" onchange="changeLinkNum()"></textarea></td>
								<td width="30%"><textarea name="couponCodes" id="couponCodes" style="width: 300px;"
										rows="15"  align="left"
										class="{required:true}" onchange="changeCodeNum()"></textarea></td>
								<td align="top"></td>
							</tr>
							<tr>
							<td colspan="2">
							优惠券发送内容<br />
							<textarea style="width: 300px;" name="content" id="content" rows="3" ></textarea>
							</td>
							</tr>
							<tr height="30">
								<td colspan="3"><input type="submit" value="提交" /> 
								<input type="button" value="返回" onclick="history.go(-1)"></input></td>
							</tr>
							<tr height="30">
								<td colspan="3"><s:actionmessage escape="false" /></td>
							</tr>

						</table></td>
					<td width="16" class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					<td><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
					<td class="lists_bottom"></td>
					<td><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
				</tr>
			</table>
		</form>
		<s:if test="couponCode!=null">
		${couponCode}
		</s:if>
	</div>

	<div id="test1">
		<!--查询-->
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">学员统计</font> <font
						class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
			</table>
		</div>
		<div style="margin: 0px;">
			<form name="searchForm" action="" method="post" style="margin: 0px;">
				<table width="98%" border="0" cellspacing="1" cellpadding="0"
					class="lists_info" align="center">
					<tr>
						<td>注册路径：
							<select id="subjectIdJson" style="width: 155px" onchange="onchangeValue(this)" name="couponRecordInfo.subjectId">
								<option value="-1" subjectName="">---请选择---</option>
							</select>							
						</td>
							<td> <a href="javascript:search()">添加</a></td>
							<td> <a href="javascript:close11()">关闭</a></td>
					</tr>
				</table>
			</form>
		</div>
		<!--查询结束-->
	</div>

<div id="test2">
		<!--查询-->
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">优惠券统计</font> <font
						class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
			</table>
		</div>
		<div style="margin: 0px;">
			<form name="searchForm" action="" method="post" style="margin: 0px;">
				<table width="98%" border="0" cellspacing="1" cellpadding="0"
					class="lists_info" align="center">
					<tr>
						<td>优惠券类别id： 
						<input type="text" id="code" name="couponRecordInfo.couponCatorgryId"/>
						</td>
							<td> <a href="javascript:addcode()">添加</a></td>
							<td> <a href="javascript:closetest2()">关闭</a></td>
					</tr>
				</table>
			</form>
		</div>
		<!--查询结束-->
	</div>
</body>
</html>
