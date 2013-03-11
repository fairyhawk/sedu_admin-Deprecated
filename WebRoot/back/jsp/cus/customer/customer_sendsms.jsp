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
		<script type="text/javascript">
		$(document).ready(function() {
			$("#addForm").validate();
		});
</script>


<style>
#test1{
position:absolute;
width:600px;
padding:0px;
border:1px solid black;
z-index:1000;
cursor:hand;
top:2px;
right:10%;
display:none;
}
</style>

<script language="javascript" type="text/javascript">
	var	smslimit='${smsLimit}';
	sendSmsCount='${sendSmsCount}';
	var roleId='${roleId}';
	var page=1;
	var zongshu=1;
	
	//判断是否为数字
	function IsNum(Text){
		return /^\d+$/.test(Text);
	}
	
	function IsMoblie(Text)
	{
		return /^1\d{10}$/.test(Text);
	}

function ceng()
{

this.document.getElementById("test1").style.display="block";

}
function close11()
{

this.document.getElementById("test1").style.display="none";

}

function resetKey() {
		$("#op0").attr("selected","selected");
		$("input[name=queryCustomerCondition.email]").val("");
		$("input[name=queryCustomerCondition.mobile]").val("");
		$("input[name=queryCustomerCondition.startNumber]").val("");
		$("input[name=queryCustomerCondition.endNumber]").val("");
		 document.getElementById('sellIdJson').value=-1;
		 document.getElementById('subjectIdJson').value=-1;
		$("input[name=startTime]").val("");
		$("input[name=endTime]").val("");
		$("#op1").attr("selected","selected");
		document.getElementById('test1').value=''
		if(document.getElementById("equl").innerHTML !=''){
 		$("equl").empty();
 		}
	}


function add()
{
	var boxes = document.getElementsByName("checkbox");
	var check = document.getElementById("sendLinks").value;
	document.getElementById('checkIdSum').value='';
//	document.getElementById('sendLinkss').value='';
	var newCheck="";   
	var checked = false;
	var checkSum="";
	var mb = 0;
	for (var i = 0; i < boxes.length; i++)   
    {   
		if (boxes[i].checked == true)   
		{   
			checked = true;   
			var bb= boxes[i].value;
			  var cc=bb.split('&');
				check += cc[0]+',\n';
				checkSum+=cc[1]+',\n';
			mb++;
		}   
 	}
	var valL = $("#sendLinks").val().split(",").length - 1;
    if (parseInt(valL) + parseInt(mb) > smslimit) {
    	alert("每次发送的手机号不能超过"+smslimit+"个，当前已添加"+valL+"个");
        return ;
    }
    if (parseInt(valL) + parseInt(mb)+parseInt(sendSmsCount) >60&&roleId==73) {
    	alert("您今天发送短信条数不能超过60个，当前已发送"+sendSmsCount+"条");
        return ;
    }
    
	   
if(!checked){   
    alert("至少得选一个");   
    return false;   
}  
document.getElementById('checkIdSum').value='';
document.getElementById('sendLinks').value='';
document.getElementById('sendLinkss').value='';
document.getElementById("sendLinks").value  = check;
document.getElementById("sendLinkss").value  = check;
document.getElementById("checkIdSum").value  = checkSum;
document.getElementById("allAndsingleStatus").value  = 6;
}


function CheckAll(form){
for (var i=0;i<form.elements.length;i++){
var e = form.elements[i];
if (e.Name != 'chkAll'&&e.disabled==false)
e.checked = form.chkAll.checked;
}
}

 function search(){
 	if(document.getElementById("equl").innerHTML !=''){
 		$("equl").empty();
 	}
 page=1;
 	  document.getElementById('checkIdSum').value='';
	  document.getElementById('sendLinks').value='';
	  document.getElementById('sendLinkss').value='';
	  var startTimeJson=document.getElementById("startTimeJson").value;
	  var endTimeJson=document.getElementById("endTimeJson").value;
	  var subjectIdJson=document.getElementById("subjectIdJson").value;
	  var cusTypeJson = document.getElementById("cusTypeJson").value;
	  var emailJson = document.getElementById("emailJson").value;
	  var startNumber=Number(document.getElementById("startNumber").value);
	  var endNumber=Number(document.getElementById("endNumber").value);
	  var mobile=document.getElementById("mobile").value;
	  var sellIdJson=document.getElementById("sellIdJson").value;
	 
	  
	  if(mobile!="")
		  {
		  
		  		if(!IsMoblie(mobile))
		  		{
		  			alert("手机号格式不正确");
		  			return ;
		  		}
		  }
		
		if(startNumber!=""||endNumber!="")
		{	
			if(IsNum(startNumber)!=true)
			{
				alert("首次数不是数字！");
				return ;
			}
			if(IsNum(endNumber)!=true)
			{
				alert("末次数不是数字");
				return ;
			}
			if(startNumber!=""&&endNumber=="")
			{
				alert("末次数为空");
				return ;
			}	  
			if(startNumber==""&&endNumber!="")
			{
				alert("首次数为空");
				return ;
			}
			if(startNumber>endNumber)
			{
				alert("首次数不能大于末次数");
				return ;
			}
	
		}
	  if(startTimeJson!=null&&endTimeJson!=null){
	  if(startTimeJson>endTimeJson){
		 alert("结束时间要大于开始时间!");
		 return ;
	  }else{
	  		$.ajax({  
	  		
					url : "<%=contextPath%>/cus/cus!showCustomerCellphoneListJson.action",  
					data : {
							startTime : startTimeJson,
							endTime : endTimeJson,
							"queryCustomerCondition.subjectId": subjectIdJson,
							"queryCustomerCondition.cusType" : cusTypeJson,
							"queryCustomerCondition.email" : emailJson,
							"queryCustomerCondition.currentPage":page,
							"queryCustomerCondition.startNumber":startNumber,
							"queryCustomerCondition.endNumber":endNumber,
							"queryCustomerCondition.mobile":mobile,
							"queryCustomerCondition.sellId":sellIdJson
							 },
				    // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(error){ 
						alert('未查询到数据请从新选择！');      
					}, 
					success:function (result) 
					{
						
						var str="";  
						for(var i=0;i<result.entity.length;i++){  
							if(result.entity[i].mobile==null||result.entity[i].mobile=='')
							{
								result.entity[i].mobile="0";
							}
						str+="<tr class='lists_infobg'><td width='16%'><input type='checkbox' name='checkbox' value='"+result.entity[i].mobile+"&"+result.entity[i].cusId+"' /></td><td width='26%'>"+result.entity[i].email+"</td><td width='25%'>"+result.entity[i].mobile+"</td></tr>";
					      }  
						var tiaoshu=result.returnMessage;
						var zongyeshu=result.jumpUrl;
						zongshu=zongyeshu;
							var html="<a href='javascript:goPage(1)'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
							var html2="";
						
						if(page == 1) 
							{
								html += "<a>上一页</a>";
						 	} else {
						 		if(zongyeshu==0  || zongyeshu==1)
								{
									html2 = "<a href='####'>上一页</a>";
								}else{
									html += "<a href='javascript:goPage(" + (page - 1) + ")'>上一页</a>";
								}
							}
						if(page == zongyeshu) {
								html2 = "<a>下一页</a>";
							} else {
								if(zongyeshu==0  || zongyeshu==1)
								{
									html2 = "<a href='####'>下一页</a>";
								}else{
									html2 = "<a href='javascript:goPage(" + (page + 1) + ")'>下一页</a>";
								}
							}
							
							if(zongyeshu==0  || zongyeshu==1)
								{
									html2 += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='####'>尾页</a>";
								}else{
									html2 += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:goPage(" + zongyeshu + ")'>尾页</a>";
								}
							
						
						$("#info").html(str);
						$("#tiaoshu").html(tiaoshu);
						$("#yeshu").html(zongyeshu);
						$("#dangqianyeshu").html(page);
						$("#fenye1").html(html);
						$("#fenye2").html(html2);
						
					
					}
					
					});
		}
	  }
	}


//分页
function goPage(page)
{
	
	var startTimeJson=document.getElementById("startTimeJson").value;
	  var endTimeJson=document.getElementById("endTimeJson").value;
	  var subjectIdJson=document.getElementById("subjectIdJson").value;
	  var cusTypeJson = document.getElementById("cusTypeJson").value;
	  var emailJson = document.getElementById("emailJson").value;
	  var startNumber=Number(document.getElementById("startNumber").value);
	  var endNumber=Number(document.getElementById("endNumber").value);
	  var mobile=document.getElementById("mobile").value;
	  var sellIdJson=document.getElementById("sellIdJson").value;
	
	  
	  if(startTimeJson!=null&&endTimeJson!=null){
	  if(startTimeJson>endTimeJson){
		 alert("结束时间要大于开始时间!");
		 return false;
	  }else{
	  
	  	if(mobile!="")
		  {
		  		if(IsMoblie(mobile)!=true)
		  		{
		  			alert("手机号格式不正确");
		  			return ;
		  		}
		  }
		
		if(startNumber!=""||endNumber!="")
		{	
			if(IsNum(startNumber)!=true)
			{
				alert("首次数不是数字！");
				return ;
			}
			if(IsNum(endNumber)!=true)
			{
				alert("末次数不是数字");
				return ;
			}
			if(startNumber!=""&&endNumber=="")
			{
				alert("末次数为空");
				return ;
			}	  
			if(startNumber==""&&endNumber!="")
			{
				alert("首次数为空");
				return ;
			}
			if(startNumber>endNumber)
			{
				alert("首次数不能大于末次数");
				return ;
			}
			
		}
	  		$.ajax({  
					url : "<%=contextPath%>/cus/cus!showCustomerCellphoneListJson.action",  
					data : {
							startTime : startTimeJson,
							endTime : endTimeJson,
							"queryCustomerCondition.subjectId": subjectIdJson,
							"queryCustomerCondition.cusType" : cusTypeJson,
							"queryCustomerCondition.email" : emailJson,
							"queryCustomerCondition.currentPage":page,
							"queryCustomerCondition.startNumber":startNumber,
							"queryCustomerCondition.endNumber":endNumber,
							"queryCustomerCondition.mobile":mobile,
							"queryCustomerCondition.sellId":sellIdJson
							 },  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(error){ 
						alert('数据不对！');      
					}, 
					success:function (result) 
					{
						
						var str="";  
						for(var i=0;i<result.entity.length;i++){  
							
							if(result.entity[i].mobile==null||result.entity[i].mobile=='')
							{
								result.entity[i].mobile="0";
							}
						str+="<tr class='lists_infobg'><td width='16%'><input type='checkbox' name='checkbox' value='"+result.entity[i].mobile+"&"+result.entity[i].cusId+"' /></td><td width='26%'>"+result.entity[i].email+"</td><td width='25%'>"+result.entity[i].mobile+"</td></tr>";
					      }  
						var tiaoshu=result.returnMessage;
						var zongyeshu=result.jumpUrl;
						var html="<a href='javascript:goPage(1)'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
						var html2="";
						if(page == 1) 
							{
								html += "<a>上一页</a>";
						 	} else {
						 		if(zongyeshu==0  || zongyeshu==1)
								{
									html2 = "<a href='####'>上一页</a>";
								}else{
									html += "<a href='javascript:goPage(" + (page - 1) + ")'>上一页</a>";
								}
							}
						if(page == zongyeshu) {
								html2 = "<a>下一页</a>";
							} else {
								if(zongyeshu==0  || zongyeshu==1)
								{
									html2 = "<a href='####'>下一页</a>";
								}else{
									html2 = "<a href='javascript:goPage(" + (page + 1) + ")'>下一页</a>";
								}
							}
						if(zongyeshu==0  || zongyeshu==1)
								{
									html2 += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='####'>尾页</a>";
								}else{
									html2 += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:goPage(" + zongyeshu + ")'>尾页</a>";
								}
					document.getElementById('tiaozhandaoye').value='';
						$("#info").html(str);
						$("#tiaoshu").html(tiaoshu);
						$("#yeshu").html(zongyeshu);
						$("#dangqianyeshu").html(page);
						$("#fenye1").html(html);
						$("#fenye2").html(html2);
					
					}
					
				});
		}
	  }

}

//-分页
function goPageInport(pagee)
{
var tiaozhandaoye=Number(document.getElementById('tiaozhandaoye').value);
		  if(tiaozhandaoye ==0 || /^\d+$/.test(tiaozhandaoye)==false ){
				    		alert("只能输入整数，请重新输入！");
				    		document.getElementById("tiaozhandaoye").value='';
				    		return;
			}else if(zongshu==1){
							document.getElementById("tiaozhandaoye").value='';
							return;
			}else if(zongshu<tiaozhandaoye){
						//	goPageInport(zongshu)
							goPage(zongshu);
							return;
			}else if(zongshu > tiaozhandaoye){
						//	goPageInport(zongshu)
							goPage(tiaozhandaoye);
							return;
			}
}

//提交时验证手机号码个数不能超过500
function checkMb(){
	var valL = $("#sendLinks").val().split(",").length - 1;
    if (valL > smslimit) {
    	alert("每次发送的手机号不能超过"+smslimit+"个，当前已添加"+valL+"个");
        return false;
    }
     if (parseInt(valL) +parseInt(sendSmsCount) >60&&roleId==73) {
    	alert("您今天发送短信条数不能超过60个，当前已发送"+sendSmsCount+"条");
        return ;
    }
}
//根据项目ID获得售卖方式；
function changGetSell(pId){
					  var subjectId=document.getElementById("subjectIdJson").value;
					$.ajax({  
					url : "<%=contextPath%>/cus/cus!getSinggleSellListJson.action",  
					data : {subjectId : subjectId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('该项目没有售卖方式！');      
					}, 
					success:onchangecallback2  
					});  
				
				}
			function onchangecallback2(result){//第三个分类变动
				document.getElementById('sellIdJson').options.length = 0;  //清空原有的option 
				
				var str="<option value='-1'>--请选择售卖方式--</option>";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].sellId+"'>"+result.entity[i].sellName+"</option>"  
				}  
				//$("#teacherServlet").html("<option value=-1>请选择</option>");
			//	$("teacherServlet").value="--请选择--"
				$("#sellIdJson").append(str);  
				}
				function onBlurChang(){
					var sendLinks=document.getElementById('sendLinks').value;
					document.getElementById('checkIdSum').value=sendLinks;
				}
function addAll(){
	if(document.getElementById("equl").innerHTML !=''){
 		$("equl").empty();
 	}
 	  document.getElementById('checkIdSum').value='';
	  document.getElementById('sendLinks').value='';
	  document.getElementById('sendLinkss').value='';
	  var startTimeJson=document.getElementById("startTimeJson").value;
	  var endTimeJson=document.getElementById("endTimeJson").value;
	  var subjectIdJson=document.getElementById("subjectIdJson").value;
	  var cusTypeJson = document.getElementById("cusTypeJson").value;
	  var emailJson = document.getElementById("emailJson").value;
	  var startNumber=Number(document.getElementById("startNumber").value);
	  var endNumber=Number(document.getElementById("endNumber").value);
	  var mobile=document.getElementById("mobile").value;
	  var sellIdJson=document.getElementById("sellIdJson").value;
	 
	  
	  if(mobile!="")
		  {
		  		if(!IsMoblie(mobile))
		  		{
		  			alert("手机号格式不正确");
		  			return ;
		  		}
		  }
		
		if(startNumber!=""||endNumber!="")
		{	
			if(IsNum(startNumber)!=true)
			{
				alert("首次数不是数字！");
				return ;
			}
			if(IsNum(endNumber)!=true)
			{
				alert("末次数不是数字");
				return ;
			}
			if(startNumber!=""&&endNumber=="")
			{
				alert("末次数为空");
				return ;
			}	  
			if(startNumber==""&&endNumber!="")
			{
				alert("首次数为空");
				return ;
			}
			if(startNumber>endNumber)
			{
				alert("首次数不能大于末次数");
				return ;
			}
			
		}
	  if(startTimeJson!=null&&endTimeJson!=null){
	  if(startTimeJson>endTimeJson){
		 alert("结束时间要大于开始时间!");
		 return ;
	  }else{
	  		$.ajax({  
	  		
					url : "<%=contextPath%>/cus/cus!showCustomerListQianJson.action",  
					data : {
							startTime : startTimeJson,
							endTime : endTimeJson,
							"queryCustomerCondition.subjectId": subjectIdJson,
							"queryCustomerCondition.cusType" : cusTypeJson,
							"queryCustomerCondition.email" : emailJson,
							"queryCustomerCondition.currentPage":page,
							"queryCustomerCondition.startNumber":startNumber,
							"queryCustomerCondition.endNumber":endNumber,
							"queryCustomerCondition.mobile":mobile,
							"queryCustomerCondition.sellId":sellIdJson
							 },
				    // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(error){ 
						alert('未查询到数据请从新选择！');      
					}, 
					success:function (result) 
					{
						//alert(result.entity.length);
	var newCheck="";   
	var checked = false;
	var checkSum="";
	var mb = 0;
	for(var i=0;i<result.entity.length;i++){  
		if(result.entity[i].mobile==null||result.entity[i].mobile=='')
		{
					result.entity[i].mobile="0";
		}
			newCheck+=result.entity[i].mobile+',\n';
			checkSum+=result.entity[i].cusId+',';
			mb++;
		}  
	var valL = $("#sendLinks").val().split(",").length - 1;
    if (parseInt(valL) + parseInt(mb) > smslimit) {
    	alert("每次发送的手机号不能超过"+smslimit+"个，当前已添加"+valL+"个");
        return ;
    }else{
    	var sumQian=result.entity.length;
    }
     if (parseInt(valL) + parseInt(mb)+parseInt(sendSmsCount) >60&&roleId==73) {
    	alert("您今天发送短信条数不能超过60个，当前已发送"+sendSmsCount+"条");
        return ;
    }
    
    	document.getElementById("allAndsingleStatus").value='5';
		document.getElementById("sendLinks").value  = newCheck;
		document.getElementById("sendLinkss").value  = newCheck;
		document.getElementById("checkIdSum").value  = checkSum;
	}
	});
		}
	  }
	}

	//第二次发送；
	function addAllAggin(discern){
	
	page=page+1;
	  var startTimeJson=document.getElementById("startTimeJson").value;
	  var endTimeJson=document.getElementById("endTimeJson").value;
	  var subjectIdJson=document.getElementById("subjectIdJson").value;
	  var cusTypeJson = document.getElementById("cusTypeJson").value;
	  var emailJson = document.getElementById("emailJson").value;
	  var startNumber=Number(document.getElementById("startNumber").value);
	  var endNumber=Number(document.getElementById("endNumber").value);
	  var mobile=document.getElementById("mobile").value;
	  var sellIdJson=document.getElementById("sellIdJson").value;
	  var discern=discern 
	 
	  
	  if(mobile!="")
		  {
		  		if(!IsMoblie(mobile))
		  		{
		  			alert("手机号格式不正确");
		  			return ;
		  		}
		  }
		
		if(startNumber!=""||endNumber!="")
		{	
			if(IsNum(startNumber)!=true)
			{
				alert("首次数不是数字！");
				return ;
			}
			if(IsNum(endNumber)!=true)
			{
				alert("末次数不是数字");
				return ;
			}
			if(startNumber!=""&&endNumber=="")
			{
				alert("末次数为空");
				return ;
			}	  
			if(startNumber==""&&endNumber!="")
			{
				alert("首次数为空");
				return ;
			}
			if(startNumber>endNumber)
			{
				alert("首次数不能大于末次数");
				return ;
			}
			
		}
	  if(startTimeJson!=null&&endTimeJson!=null){
	  if(startTimeJson>endTimeJson){
		 alert("结束时间要大于开始时间!");
		 return ;
	  }else{
	  		$.ajax({  
	  		
					url : "<%=contextPath%>/cus/cus!showCustomerListQianJson.action",  
					data : {
							allAndsingleStatus: page,
							startTime : startTimeJson,
							endTime : endTimeJson,
							"queryCustomerCondition.subjectId": subjectIdJson,
							"queryCustomerCondition.cusType" : cusTypeJson,
							"queryCustomerCondition.email" : emailJson,
							"queryCustomerCondition.currentPage":page,
							"queryCustomerCondition.startNumber":startNumber,
							"queryCustomerCondition.endNumber":endNumber,
							"queryCustomerCondition.mobile":mobile,
							"queryCustomerCondition.sellId":sellIdJson
							 },
				    // 参数  
					type : "post",  
					 async : false,
					dataType : "json",  //返回json数据 
					error: function(error){ 
						alert('未查询到数据请从新选择！');      
					}, 
						success:function (result) 
						{
							var newCheck="";   
							var checked = false;
							var checkSum="";
							var mb = 0;
							for(var i=0;i<result.entity.length;i++){  
								if(result.entity[i].mobile==null||result.entity[i].mobile=='')
								{
											result.entity[i].mobile='0';
											document.getElementById('allAndsingleStatus').value=5;
								}
									newCheck+=result.entity[i].mobile+',\n';
									checkSum+=result.entity[i].cusId+',';
									document.getElementById('allAndsingleStatus').value=5;
									mb++;
								}  
							var valL = $("#sendLinks").val().split(",").length - 1;
						    if (parseInt(valL) + parseInt(mb) > smslimit) {
						    	alert("每次发送的手机号不能超过"+smslimit+"个，当前已添加"+valL+"个");
						        return ;
						    }else{
						    	var sumQian=result.entity.length;
						    }
						     if (parseInt(valL) + parseInt(mb)+parseInt(sendSmsCount) >60&&roleId==73) {
							    	alert("您今天发送短信条数不能超过60个，当前已发送"+sendSmsCount+"条");
							        return ;
							    }
								document.getElementById("sendLinks").value  = newCheck;
								document.getElementById("sendLinkss").value  = newCheck;
								document.getElementById("checkIdSum").value  = checkSum;
								var checkIdSumm=document.getElementById("checkIdSum").value;
								document.getElementById("bianbie").value=discern;
							if(result.entity.length==0){
								alert("你选择添加全部方式发送，\n 现在符合条件要求的已全部发完.");
								document.getElementById('checkIdSum').value='';
								document.getElementById('sendLinks').value='';
								document.getElementById('sendLinkss').value='';
								document.getElementById('bianbie').value='-1';
								return;
							}else{
								clickSMFF()
							}
							}
							});
		}
	  }
	}
var timer;
function dingshiqingqiu(idd){
	timer=setTimeout("dingshiqingqiu(1)",3000);//每10秒钟刷新一次
 	$.ajax({  
					url : "<%=contextPath%>/cus/cus!sendSMSDingshiForCus.action",  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(error){ 
						alert('数据不对！');      
					}, 
					success:function (result) 
					{	
							
					}
				});
}	
function stop(){
	 clearTimeout(timer);
}
	var jilu=0;
function clickSMF(){
var valL = $("#sendLinks").val().split(",").length - 1;
    if (valL> smslimit) {
    	alert("每次发送的手机号不能超过"+smslimit+"个，当前已添加"+valL+"个");
    	document.getElementById("sendLinks").value  ='';
        return ;
    }
    if (parseInt(valL) +parseInt(sendSmsCount) >60&&roleId==73) {
    	alert("您今天发送短信条数不能超过60个，当前已发送"+sendSmsCount+"条");
        return ;
    }
 document.getElementById('equl').value='';
 var sendLinkss=document.getElementById('sendLinkss').value;
  var sendLinks=document.getElementById('sendLinks').value;
  var allAndsingleStatus=document.getElementById('allAndsingleStatus').value;
  var bianbie=document.getElementById('bianbie').value;
  if(sendLinks==''){
 	 alert('短信接收人不能为空');
 	 return false;
 }
 if(sendLinkss==''){
 	 alert('短信接收人不能为空');
 	 return false;
 }
 var sendInfo=document.getElementById('sendInfo').value;
 if(sendInfo==''){
 	 alert('短信内容不能为空！');
 	 return false;
 }
 if(sendInfo.length>140){
 	alert("短信内容过长，请按要求输入！");
 	return false;
 }
 if(confirm('确定发送?')==false){
 	return false;
 }
	var checkIdSum=document.getElementById('checkIdSum').value;
	var sendInfo=document.getElementById('sendInfo').value;
	var sendLinks=document.getElementById('sendLinks').value;
	dingshiqingqiu();
	$.ajax({  
					url : "<%=contextPath%>/cus/cus!sendSMSForCusAll.action",  
					data : {
							discern:bianbie,
							allAndsingleStatus: allAndsingleStatus,
							sendLinks : sendLinks,
							checkIdSum : checkIdSum,
							sendInfo : sendInfo
							 },  // 参数  
					type : "post",  
					 async : false,
					dataType : "json",  //返回json数据 
					error: function(error){ 
						alert('数据不对！');      
					}, 
					success:function (result) 
					{
						stop();
							if(result.entity.fanhui!="你编辑的短信已经发完!"){
								alert(result.entity.fanhui);
							}else{
								alert(result.entity.fanhui+"已经发送"+($("#sendLinks").val().split(",").length - 1)+"条");
							}
							sendSmsCount=result.entity.zhuangtai==13?parseInt(sendSmsCount)+$("#sendLinks").val().split(",").length - 1:result.entity.zhuangtai==12?parseInt(sendSmsCount)+result.entity.slll:result.entity.zhuangtai==11?sendSmsCount:0;
							document.getElementById('checkIdSum').value='';
							document.getElementById('sendLinks').value='';
							document.getElementById('sendLinkss').value='';
							if(result.entity.fanhui!="所发的手机号码不符合要求不能进行发送!" && result.entity.fanhui!="你编辑的短信已经发完!"){
							   if(result.entity.allAndsingleStatus==5){
							   		if(confirm("您选择的是全部发送，一次"+smslimit+"条，你确认继续再发送？\n")){
							   			addAllAggin(result.entity.discern);
							   		}else{
							   			document.getElementById('checkIdSum').value='';
										document.getElementById('sendLinks').value='';
										document.getElementById('sendLinkss').value='';
										document.getElementById('bianbie').value='-1';
							   		}
								  }
								  jilu=jilu+result.entity.slll;
								  var html5='你已经发送了'+jilu+'条了！'
								  $("#equl").html(html5);
							}
							if(result.entity.zhuangtai==13){
								document.getElementById('sendInfo').value='';
							}
							document.getElementById('bianbie').value='-1';
					}
					
				});
}
function clickSMFF(){
	var valL = $("#sendLinks").val().split(",").length - 1;
	    if (valL> smslimit) {
	    	alert("每次发送的手机号不能超过"+smslimit+"个，当前已添加"+valL+"个");
	    	document.getElementById("sendLinks").value  ='';
	        return ;
	    }
	      if (parseInt(valL) +parseInt(sendSmsCount) >60&&roleId==73) {
	    	alert("您今天发送短信条数不能超过60条，当前已发送"+sendSmsCount+"条");
	        return ;
   		 }
	 document.getElementById('equl').value='';
	 var sendLinkss=document.getElementById('sendLinkss').value;
	  var sendLinks=document.getElementById('sendLinks').value;
	  var allAndsingleStatus=document.getElementById('allAndsingleStatus').value;
	  var bianbie=document.getElementById('bianbie').value;
		dingshiqingqiu();
		var checkIdSum=document.getElementById('checkIdSum').value;
		var sendInfo=document.getElementById('sendInfo').value;
		var sendLinks=document.getElementById('sendLinks').value;
		$.ajax({  
						url : "<%=contextPath%>/cus/cus!sendSMSForCusAll.action",  
						data : {
								discern:bianbie,
								allAndsingleStatus: allAndsingleStatus,
								sendLinks : sendLinks,
								checkIdSum : checkIdSum,
								sendInfo : sendInfo
								 },  // 参数  
						type : "post",  
                         async : false,
						dataType : "json",  //返回json数据 
						error: function(error){ 
							alert('数据不对！');      
						}, 
						success:function (result) 
						{
							stop();
							
							if(result.entity.fanhui!="你编辑的短信已经发完!"){
								alert(result.entity.fanhui);
							}else{
								alert(result.entity.fanhui+"已经发送"+$("#sendLinks").val().split(",").length - 1+"条");
							}
							sendSmsCount=result.entity.zhuangtai==13?parseInt(sendSmsCount)+$("#sendLinks").val().split(",").length - 1:result.entity.zhuangtai==12?parseInt(sendSmsCount)+result.entity.slll:result.entity.zhuangtai==11?sendSmsCount:0;
							document.getElementById('checkIdSum').value='';
							document.getElementById('sendLinks').value='';
							document.getElementById('sendLinkss').value='';
							if(result.entity.fanhui!="所发的手机号码不符合要求不能进行发送!" && result.entity.fanhui!="你编辑的短信已经发完!"){
							   if(result.entity.allAndsingleStatus==5){
							   		if(confirm("您选择的是全部发送，一次"+smslimit+"条，你确认继续再发送？\n")){
							   			addAllAggin(result.entity.discern);
							   		}else{
							   			document.getElementById('checkIdSum').value='';
										document.getElementById('sendLinks').value='';
										document.getElementById('sendLinkss').value='';
										document.getElementById('bianbie').value='-1';
							   		}
								  }
								  jilu=jilu+result.entity.slll;
								  var html5='你已经发送了'+jilu+'条了！'
								  $("#equl").html(html5);
							}
							if(result.entity.zhuangtai==13){
								document.getElementById('sendInfo').value='';
							}
							document.getElementById('bianbie').value='-1';
						}
						
					});
}
function gaibian(){
	var sendLinkss= document.getElementById('sendLinkss').value;
	document.getElementById('sendLinks').value=sendLinkss;
	
	
}
</script>

	</head>
	<body>
		<div>
			<form action=""
				method="post" name="addForm" id="Form" onsubmit="return checkMb();">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
						<font class="lists_fright"> </font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="1">
								<tr>
									<td width="800" colspan="" align="left">
										<b  >1、添加接收人：(<font style="color:red;font-weight:bold">提醒：每人每天短信限发60条</font>)</b>
									</td>
									<td align="left"><span><img src="<%=contextPath%>/back/images/anniu1.gif" onclick="ceng()" style="width:83px; height:18px; cursor:hand;" align="middle" /></span>	</td>
									<td></td>
								</tr>
								<tr>
									<td width="25%">
											<textarea style="width:400px;"  id="sendLinkss" rows="16"  name=""
											onfocus="if(value=='') {value=''}" 
											align="left" class="{required:true}"  onblur="gaibian()"/></textarea>
											<input  type="hidden" name="" id="checkIdSum" value="0"/>
											<input  type="hidden" name="" id="sendLinks" value="0"/>
					   			          <input  type="hidden" name="allAndsingleStatus" id="allAndsingleStatus" value="0"/>
											<input  type="hidden" id="bianbie" value="-1"/>
									</td>
									<td>
										 <font color="red">
										 		 规则<br>
											1、手机号格式:132********  <br>
											   或者132********,138********,159********<br>
											2、短信内容,不能超过70汉字或者140个英文字母 <br>
											3、发送流程：添加手机号-&gt;设置短信内容&gt;提交发送<br/>
											4、添加手机号时，查询后可以选择添加所选学员及添加所有学员，请慎重选择。<br/>
											5.群发短信最多不能超过<s:property value="smsLimit"/>
											</font>
									</td>
									<td></td>
								</tr>
								<tr>
									<td width="00" colspan="" align="left">
											<b  >2、设置短信内容：</b>
									</td>
								</tr>	
								<tr>
									<td width="80" colspan="" align="left">
											<select disabled="disabled">
												<option>--请选择模板--</option>
											</select>
									</td>
								</tr>	
								<tr>
									<td width="%">
										<textarea name="" id="sendInfo" rows="16" style="width:400px;"
											onfocus="if(value=='') {value=''}"
											align="left" class="{required:true}"></textarea>
									</td>
								</tr>
								<tr height="30">
									<td colspan="3">
										<input type="button" value="提交"  onclick="clickSMF()"/>
										<input type="button" value="返回" onclick="history.go(-1)"></input>
										<span id="equl"></span>
									</td>
								</tr>
									<tr height="30">
									<td colspan="3">
											<s:actionmessage escape="false"/>
										
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
		
		<div  id="test1">
	
	
	
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td>
						<font class="lists_fleft">添加短信收件人</font>
						<font class="lists_fright"> </font>
			<div style="margin-right:10px; float:right"><img src="<%=contextPath%>/back/images/anniu3.gif" onclick="close11()" style="height:18px; width:43px; cursor:hand;" /></div>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
			</table>
		</div>
		<div style="margin:0px;">
			<form name="searchForm" action="" method="post" style="margin:0px;">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="lists_info" align="center">
					<tr align="left"  >
						<td  align="left" width="55%" height="10px">
							<span style="float: left">
							&nbsp;&nbsp;&nbsp;&nbsp;注册时间：
							<input type="text" name="startTime" readonly="readonly" id="startTimeJson"
								onclick="WdatePicker()"
								value=""  style="" /></span>
						</td>
						<td align="left" valign="bottom"  height="26px">&nbsp;&nbsp;
									<span style="width: 100%;float: left;padding-bottom:15px">&nbsp;&nbsp;--<input type="text" name="endTime" readonly="readonly" id="endTimeJson" style=""
											onclick="WdatePicker()" value="" /></span></td>					</tr>
					<tr>
						<td colspan="" align="left" >
							<span style="float: left">
										&nbsp;&nbsp;&nbsp;&nbsp;用户类型：
										<select name="cusType"
											id="cusTypeJson" style="width: 155px">
											<option value="-1" id="op1" >
												---请选择---
											</option>
											<option value="0">
												已购课用户
											</option>
											<option value="1">
												注册未购课用户
											</option>
											<option value="2">
											  仅注册用户
											</option>
											<option value="3">
												内部学员
											</option>
										</select>
										</span>
										</td>
									<td> 手机号：<input type="text"  id="mobile" value="" name="queryCustomerCondition.mobile"/>&nbsp;&nbsp;&nbsp;&nbsp;	</td>
					</tr>
					<tr>
						<td align="left" >
						<span style="float: left">
										&nbsp;&nbsp;&nbsp;&nbsp;注册路径：
										<s:select name="subjectId"
											id="subjectIdJson" list="subjectList"
											listKey="subjectId" listValue="subjectName" headerKey="-1" 
											headerValue="---请选择专业---" theme="simple" onchange="changGetSell()"
											style="width: 155px">
										</s:select>
										</span>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<select id="sellIdJson" style="width: 150px">
											<option value="-1">---请先选择专业---</option>
										</select>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
									<td align="left" >
									<span style="float: left">
									&nbsp;&nbsp;&nbsp;&nbsp;登录次数：&nbsp;<input  type="text" value = "" id="startNumber" name="queryCustomerCondition.startNumber" style="width:150px;"/>
									</span>
									</td>
									<td>
									&nbsp;&nbsp;至 &nbsp;<input  type="text" value = "" id="endNumber" name="queryCustomerCondition.endNumber" style="width:150px;"/>次
									</td>
				  </tr>
				  
				  <tr >
								  <td align="left">
								  <span style="float: left">
								   &nbsp;&nbsp;&nbsp;&nbsp;邮箱地址：
										<input type="text"
											value=""
											name="queryCustomerCondition.email" id="emailJson" />
											</span>
											 </td>
								  <td align="left"></td>
				  				</tr>
				  
								<tr>
									<td>
													<img src="<%=contextPath%>/back/images/add_a.gif" />
													<a href="javascript:search()">查询</a>
													<img src="<%=contextPath%>/back/images/del_a.gif" />
													<a href="javascript:resetKey()">清空</a>
									</td>
									<td>
									<a href="####" onclick="addAll()" style="cursor:hand; height:18px; width:43px;  text-decoration: underline;size: 3"   >添加全部</a>
									<!-- <img src="<%=contextPath%>/back/images/anniu2.gif" onclick="add()" style="cursor:hand; height:18px; width:43px;" /> -->
									<a href="####" onclick="add()" style="text-decoration: underline">添加所选</a>
									</td>
								</tr>
			  </table>
		  </form>		
		</div>
<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<form name="cusForm" method="post">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr class="lists_infobg">
									<td width="16%">
										<input name='chkAll' type='checkbox' id='chkAll' onclick='CheckAll(this.form)' value='checkbox' />全选</td>
									<td width="26%">
										电子邮件									</td>
									<td width="25%">
										手机号码									</td>
								</tr>
							</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info" id="info">
								
							</table>
							
						</form>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				<tr >
					<td>
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom" align="center">
						共有<span id="tiaoshu" ></span>
						条记录,当前第
						<span id="dangqianyeshu"></span>/<span id="yeshu"></span>页&nbsp;
						<span id="fenye1"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="fenye2">&nbsp;&nbsp;</span>
			<span  >转到第
			<input type="text" id="tiaozhandaoye" style="height:12px; margin-top:8px; width:20px; border:1px solid #999999;" >页
			<a href="javascript:goPageInport('')">
            <img  height="15"  src="<%=contextPath%>/back/images/go.gif"></a></span>
					</td>
					
					<td>
					</td>
				</tr>
			</table>
			
		</div>
	 
  
	
 
</div>
		
	</body>
</html>
