<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>订单列表</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
				<script>
		$(this).ready(function(){
			//取消
			$('.tt').live('click', function() {
				var id = $(this).attr("id").replace("update-",""); 
				var cudid=$(this).attr("cudId");
				if(window.confirm("您确认取消吗？")){
						$.ajax({
							   type: "POST",
							   url: "<%=contextPath%>/finance/backContract!updateOrderStatus.action",
							   data: {	
								   	 "contract.id":id,
									 status:"cancel"	   	  
							 	},
							   success: function(result){
								   if("success"==result){
									   alert("取消成功");
									   $("#update-"+id).parent().prev().prev().prev().prev().text("已取消");
									   $("#update-"+id).parent().prev().text("后台");
									   $("#update-"+id).parent().html("<a href='javaScript:void(0)' class='Rtt' id='Rupdate-"+id+"' cudId="+cudid+">恢复</a>");
								   }else{
									   alert("取消失败,请稍后重试");
								   };
							   }
						});
					};
			});
			//恢复
			$('.Rtt').live('click', function() {
 				var id = $(this).attr("id").replace("Rupdate-",""); 
 				var Rcudid=$(this).attr("cudId");
				if(window.confirm("您确认恢复吗？")){		
					$.ajax({
						   type: "POST",
						   url: "<%=contextPath%>/finance/backContract!updateOrderStatus.action",
						   data: {	
							   	 "contract.id":id,
								 status:"restoration"	   	  
						   },
						   success: function(result){
							   if("success"==result){
								   alert("恢复成功");
									var $s=$("#Rupdate-"+id).parent().prev().prev().prev();
									if("货到付款"==$.trim($s.text())){
										$s.prev().text("未激活");
										$("#Rupdate-"+id).parent().prev().text("无");
										$("#Rupdate-"+id).parent().html("<a href='#' onclick=onClick('god"+id+"') class='font_bai'>展开</a>  <a href='javascript:void(0)' class='tt' id='update-"+id+"'>取消</a>");
									}else{
										$s.prev().text("未付款");
										$("#Rupdate-"+id).parent().prev().text("无");
										$("#Rupdate-"+id).parent().html(
									    "------ ｜  <a href='<%=contextPath%>/finance/backContract!getApprove.action?contract.id="+id+"&contract.cusId="+Rcudid+"' onclick=\"return confirm('审核订单需甚重使用，会重新初始化知识点？')\" >审核订单</a> <a href='javascript:void(0)' class='tt' id='update-"+id+"'>取消</a>");
									}
							   }else{
								   alert("恢复失败，请稍后重试");
							   };
						   }
					});												
				}; 
			});
		});
	</script>
		<script language="JavaScript">	

	function checkOutExcel()
	{
		var startTime=document.getElementById("startTime").value;
     	var endTime=document.getElementById("endTime").value;
//		document.getElementById("exportType").value="excel";
		   if(startTime!=''&&endTime!='')
    	 {
	    	var diffValue = getDateTimeStamp(endTime) - getDateTimeStamp(startTime);
			if(diffValue < 0){
			 //非法操作
			 alert("结束时间要大于开始时间！");
			 return;
				}
			}
		   	document.f1.action="<%=contextPath%>/finance/backContract!exportCRMExcelFile.action";
				document.f1.submit();
	 }
		
	
	  function getDateTimeStamp(dateStr){
 			return Date.parse(dateStr.replace(/-/gi,"/"));
	    }
   function search(ObjectForm){
     var startTime=document.getElementById("startTime").value;
     var endTime=document.getElementById("endTime").value;
     
    /** var subjectSel=document.getElementById("subjects").value;
     var courseSel=document.getElementById("course").value;
     if(subjectSel!=-1)
     {
     	if(courseSel==0)
     	{
	     	alert("需要根据课程选择");
     		return;
     	}
     }
    var subjectSel=document.getElementById("subjects").value;
    var courseSel=document.getElementById("course").value;
    if(subjectSel!=-1)
    {
     	if(courseSel==0)
     	{
	   	alert("需要根据课程选择");
     	return ;
     	}
    }*/
  
     if(startTime!=''&&endTime!='')
     {
	    	var diffValue = getDateTimeStamp(endTime) - getDateTimeStamp(startTime);
			if(diffValue < 0){
			 //非法操作
			 alert("结束时间要大于开始时间！");
			 return;
			 }
			/* var dayNum=diffValue/(1000*60*60*24);
		
			 if(dayNum>=30){
			 	alert("日期间隔不能大于30天");
			 	return;
			 }*/
			}
	    ObjectForm.action="<%=contextPath%>/finance/backContract!getCRMContractList.action";
			ObjectForm.submit();
	   
     }
   
   function cancel(){
   document.getElementById("startTime").value="";
   document.getElementById("endTime").value="";
   document.getElementById("payStartTime").value="";
   document.getElementById("payEndTime").value="";
   document.getElementById("contract.contractId").value="";
   document.getElementById("payType").value="";
   document.getElementById("status").value="";
   document.getElementById("mail").value="";
   document.getElementById("webAgent").value="";
   document.getElementById("webFrom").value="";
   $("#op1").attr("selected","selected");
   $("#op0").attr("selected","selected");
   $("#op2").attr("selected","selected");
   $("#op3").attr("selected","selected");
   $("#op4").attr("selected","selected");
   $("#op5").attr("selected","selected");
   $("#op6").attr("selected","selected");
   $("#op7").attr("selected","selected");
   $("#spId").val(-1);
   $("#subjectId").val(-1);
   $('#coupon').val(-1);
   $('#sellId').val(-1);


   }
   
   	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
		changefkStartHH('<s:property value="fkStartHH"/>');
		changefkEndHH('<s:property value="fkEndHH"/>')
		$("#payType").val(${payType});
		onchangePayType1(${payType});
		$('#contractFromUrl').val(${contract.contractFromUrl});
		$('#webFrom').val('${contract.webFrom}');
		$('#webAgent').val('${contract.webAgent}');
		$('#status').val('${queryContractCondition.newStatus}');
		$('#coupon').val('<s:property value="coupon"/>');
		$('#sellType').val('<s:property value="queryContractCondition.sellType"/>');
		onchangePayType();
		$('#spId').val('${queryContractCondition.newStatus}');
	}
	function onchangePayType(){
			var str="";
			str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>已取消</option>";
			$("#spId").html(str); 
		  }
	function onchangecallback(result){
			var scList = eval(result.returnMessage);
			document.getElementById('spId').options.length = 0; //清空原有的option 
			$("#spId").html("<option value=-1>请选择</option>");
			var str="";
			for(var i=0;i<scList.length;i++){  
				str+="<option value='"+scList[i].CId+"'>"+scList[i].CName+"</option>"  
			}  
			if(str!=""){
				$("#spId").html(str);  
			}
	}
	function onchangePayType1(payType){
			var str="";
			if(payType==2){
				str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未激活</option>"+
				 	  "<option value='1'>已激活</option>"+
				 	  "<option value='3'>已退费</option>"+
				 	  "<option value='4'>已取消</option>";
			}else if(payType==1){
				  str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
			}else if(payType==0){
				str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='2'>内部开通</option>"+
				 	  "<option value='4'>内部开通</option>";
			}else if(payType==3){
				 str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
			}else if(payType==4){
				 str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
			}else if(payType==5){
				 str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='1'>代理商开通</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
			}else if(payType==6){
				 str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>未付</option>"+
				 	  "<option value='1'>已付</option>"+
				 	  "<option value='3'>退费</option>"+
				 	  "<option value='4'>取消</option>";
			}else if(payType==7){
				 str+="<option value='-1'>---请选择---</option>"+
				 	  "<option value='0'>等待开通课程</option>"+
				 	  "<option value='1'>已开通课程</option>"+
				 	  "<option value='3'>已退费</option>"+
				 	  "<option value='4'>已取消</option>";
			}
			else if(payType==8){
				 str+="<option value='-1'>---请选择---</option>"+
			 	  "<option value='0'>未付</option>"+
			 	  "<option value='1'>已付</option>"+
			 	  "<option value='3'>退费</option>"+
			 	  "<option value='4'>取消</option>";
			}
			else if(payType==9){
				 str+="<option value='-1'>---请选择---</option>"+
			 	  "<option value='0'>未付</option>"+
			 	  "<option value='1'>已付</option>"+
			 	  "<option value='3'>退费</option>"+
			 	  "<option value='4'>取消</option>";
			}
			
			$("#spId").html(str); 
		  }
   	function onSelectStatus(status){ 
		document.getElementById('status').value = status;
	}

	function changeStartHH(hourStr) {
		$.each($("#startHH option"),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
		}

	function changefkStartHH(hourStr) {
		$.each($("#fkStartHH option"),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
		}
	function changeEndHH(hourStr) {
		$.each($("#endHH option"),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
		}
	function changefkEndHH(hourStr) {
		$.each($("#fkEndHH option"),function(){
			if(this.value == hourStr) {
				this.selected = true;
			}
		})
	}
	function getCusByEmail(str){
	   		ObjectForm.action="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email='"+str+"'";
	}
	
	var onClick = function (fzbk_id){
		var div = document.getElementById(fzbk_id);
		var visible = div.style.display;
		if(visible == "none"){
			document.getElementById(fzbk_id).style.display = "block";
		}else{
			document.getElementById(fzbk_id).style.display = "none";
		}
		}
	function onSelectSubject(subId)
	{
		document.getElementById("subjectId").value=subId;
	}
	
	
	function initCourse(id, index){
		var subjectId = 1;
		if(id != null && id != 0 && !isNaN(id)) {
			subjectId = id;
		}
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
				
					html += "<option value='" + courses[i].courseId + "'>" + courses[i].courseName + "</option>";
				}
				if(index == 0) {
					$(html).appendTo("#subject");
				} else if(index == 1) {
					
					$("#course").html("");					
					$("<option value='0'>---请选择---</option>"+ html).appendTo("#course");
				} 
			},
			error : function(error) {
				alert(error);
			}
		});
	}
	
　function openWin(ctId) { 
	window.open ("<%=contextPath%>/finance/backContract!getContractView.action?contract.id="+ctId, "newwindow", "height=600, width=800, toolbar=no, menubar=yes, scrollbars=no, resizable=no, location=no, status=no");
　
　　}

	function getSellWayBySubjectId(sId){
			if(sId != -1){
				document.getElementById('subjectId').value = sId;
			}
			$.ajax({  
				url : "<%=contextPath%>/finance/backContract!getSellWayBySubjectId.action",  
				data : {subjectId : sId},  // 参数  
				type : "post",  
				cache : false,  
				dataType : "json",  //返回json数据 
				error: function(){ 
					alert('error');      
			}, 
				success:onchangecallback  
			});
				
	} 
		
	function onchangecallback(result){   
					//alert(data);
			document.getElementById('sellId').options.length = 0;  //清空原有的option 
			var str="<option value=-1>---请选择---</option>";  
			for(var i=0;i<result.entity.length;i++){
				str+="<option value='"+result.entity[i].sellId+"'>"+result.entity[i].sellName+"</option>"  
			}  
			$("#sellId").html(str);  
	 } 
	function openWin(ctId) { 
		window.open ("<%=contextPath%>/finance/backContract!getContractInfo.action?contract.id="+ctId, "newwindow", "height=600, width=800, toolbar=no, menubar=yes, scrollbars=yes, resizable=no, location=no, status=no");
    }
</script>
	</head>
	<body onload="changeData()">
		<div>
			<form name="f1" method="post" action="bopayendTimeoks!getBooksList.action" id="form1">
				<s:hidden name="queryContractCondition.currentPage" value="1" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">订单列表</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
						<div class="msg-cw">	
							<table width="70%" border="0" cellspacing="1" cellpadding="0"
								class="lists" align="center">
								<tr>
									<td>
										订 单 编 号：&nbsp;
										<input type="text" name="contract.contractId"
											id="contract.contractId" value="${contract.contractId}" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										支 付 类 型：&nbsp;
										<!--  <input type="text" name="contract.contractFrom"
											id="contract.contractFrom" value="${contract.contractFrom}" />-->
										<select name="payType" id="payType" style="width: 155px"
											onchange="onchangePayType1(this.value)">
											<option value="-1" id="op1">
												---请选择---
											</option>
											<option value="1">
												支付宝/网银
											</option>
											<option value="3">
												网银在线/网银
											</option>
											<option value="4">
												快钱
											</option>
											<option value="2">
												货到付款
											</option>
											<option value="0">
												内部开通
											</option>
											<option value="5">
												代理商
											</option>
											<option value="6">
												真友
											</option>
											<option value="7">
												银行汇款
											</option>
											<option value="8">
												银联在线
											</option>
											<option value="9">
												课程卡
											</option>
										</select>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										邮 箱 地 址：&nbsp;
										<input type="text" name="mail" id="mail" value="${mail}" />

									</td>
									<td>
										订 单 状 态：&nbsp;
										<!-- <select name="status" id="status" style="width: 155px">
											<option value="">
												---请选择---
											</option>
											<option value="0">
												未付
											</option>
											<option value="1">
												已付
											</option>
											<option value="2">
												赠送
											</option>
											<option value="3">
												退费
											</option>
											<option value="4">
												修复或赠送
											</option>
										</select> -->
										<s:select name="spId" id="spId" list="#{}" listKey="cId"
											listValue="cName" headerValue="---请选择---" headerKey="-1"
											onchange="onSelectStatus(this.value)" style="width: 155px">
										</s:select>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="hidden" name="status" id="status">
									</td>
								</tr>

								<tr>
									<td>
										下单开始时间：
										<input type="text" name="startTime" readonly id="startTime"
												onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"
											value="${yestartTime}"/>
										<select name="startHH" id="startHH">
											<option value=" 00:00:00" id="op4">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
									<td>
										下单结束时间：
										<input type="text" name="endTime" readonly id="endTime"
											onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"
											value="${yeendTime}"/>
										<select name="endHH" id="endHH">
											<option value=" 23:59:59" id="op0">
												23:59:59
											</option>
											<option value=" 00:00:00">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
								</tr>
								<tr>
									<td>
										付款开始时间：
										<input type="text" name="payStartTime" readonly
											id="payStartTime" onclick="WdatePicker()"
											onfocus="inputOnFocus()" value="${payStartTime}" />
										<select name="fkStartHH" id="fkStartHH">
											<option value=" 00:00:00" id="op2">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
									<td>
										付款结束时间：
										<input type="text" name="payEndTime" readonly id="payEndTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${payEndTime}" />
										<select name="fkEndHH" id="fkEndHH">
											<option value=" 23:59:59" id="op3">
												23:59:59
											</option>
											<option value=" 00:00:00">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
									</td>
								</tr>
								<tr>
									<td>
										域 名 来 源：&nbsp;&nbsp;
										<s:if test="fromURLType==1">
											<select name="contract.contractFromUrl" id="contractFromUrl"
												style="width: 155px">
												<option value="1">
													highso.org.cn
												</option>

											</select>
											<s:hidden name="fromURLType" value="1"></s:hidden>
										</s:if>
										<s:elseif test="fromURLType==2">
											<select name="contract.contractFromUrl" id="contractFromUrl"
												style="width: 155px">
												<option value="2">
													highso.cn
												</option>
											</select>
											<s:hidden name="fromURLType" value="2"></s:hidden>
										</s:elseif>
										<s:elseif test="fromURLType==3">
											<select name="contract.contractFromUrl" id="contractFromUrl"
												style="width: 155px">
												<option value="3">
													highso.org
												</option>
											</select>
											<s:hidden name="fromURLType" value="3"></s:hidden>
										</s:elseif>
										<s:elseif test="fromURLType==4">
											<select name="contract.contractFromUrl" id="contractFromUrl"
												style="width: 155px">
												<option value="4">
													highso.com.cn
												</option>
											</select>
											<s:hidden name="fromURLType" value="4"></s:hidden>
										</s:elseif>
										<s:elseif test="fromURLType==5">
											<select name="contract.contractFromUrl" id="contractFromUrl"
												style="width: 155px">
												<option value="5">
													highso.net.cn
												</option>
											</select>
											<s:hidden name="fromURLType" value="5"></s:hidden>
										</s:elseif>
										<s:else>
											<select name="contract.contractFromUrl" id="contractFromUrl"
												style="width: 155px">
												<option value="" id="op5">
													---请选择---
												</option>
												<option value="1">
													highso.org.cn
												</option>
												<option value="2">
													highso.cn
												</option>
												<option value="3">
													highso.org
												</option>
												<option value="4">
													highso.com.cn
												</option>
												<option value="5">
													highso.net.cn
												</option>
											</select>
										</s:else>
									</td>
									<td>
										推 广 来 源：&nbsp;&nbsp;
										<input type="text" name="contract.webFrom"
											id="webFrom" value="${contract.webFrom}" />
									</td>
								</tr>
								<tr>
									<td>
										销售 / 代理商：&nbsp;&nbsp;
										<input type="text" name="contract.webAgent"
											id="webAgent" value="${contract.webAgent}" />
									</td>
									<td>
										专 业：
										<s:select id="subjectId" name="subjectId" list="subjectList"
											listKey="subjectId" listValue="subjectName"
											headerValue="---请选择---" headerKey="-1"
											onchange="getSellWayBySubjectId(this.value)" style="width: 155px">
										</s:select>
										售卖方式：
										<s:select name="sellId" id="sellId" list="sellWayList" listKey="sellId" headerValue="---请选择---" headerKey="-1" listValue="sellName" theme="simple" onchange="onchangeThird(this.value);">
										</s:select>
									</td>
								</tr>
								<tr>
									<td>
										优惠券：
										<select name="coupon" id="coupon">
											<option value="-1">
												--请选择--
											</option>
											<option value="0">
												否
											</option>
											<option value="1">
												是
											</option>
										</select>
									</td>
									<td>
									是否DS商品：
									<select name="queryContractCondition.sellType" id="sellType">
											<option value="0">--请选择--</option>
											<option value="1">
												非DS商品
											</option>
											<option value="2">
												DS商品
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>

									</td>
									<td class="lists_tleft">
										<img src="<%=contextPath%>/back/images/add_a.gif" />
										<a href="javascript:search(document.f1)">查询</a>&nbsp;
										<img src="<%=contextPath%>/back/images/del_a.gif" />
										<a href="javascript:cancel()">清空</a>
										<s:hidden name="exportType" id="exportType"></s:hidden>
										<!--     <input type="button" value="导出订单excel(前1000条)" onclick="checkOutExcel()"/>-->
										 <img src="<%=contextPath%>/back/images/down16_16.gif" />
										<a href="javascript:checkOutExcel()">导出excel</a>
									</td>
								</tr>

							</table>
							</div>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>


					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info" onmouseover="changeto()"
								onmouseout="changeback()">

								<tr class="lists_infobg">
									<td>
										编号
									</td>
									<td>
										订单编号
									</td>
									<td>
										用户email
									</td>
									<td>
										推广来源
									</td>
									<td>
										销售/代理商
									</td>
									<td>
										域名来源
									</td>
									<td>
										下单时间
									</td>
									<td>
										付款时间
									</td>
									<td>
										实际支付(
										<b><s:property value="contractPriceSum" /> </b>)
									</td>
									<td>
										原始价格
									</td>
									<td>
										优惠金额
									</td>
									<td>
										运费
									</td>
									<td>
										状态
									</td>
									<td>
										支付类型
									</td>
									<td>
										访问次数
									</td>
									<td>
										取消来源
									</td>
									<td>创建销售 </td>
									<td>机会创建时间</td>
									<td>场景</td>
									<td>最后指派销售</td>
									<td>最后指派时间</td>
								<!-- 	<td colspan="1">
										操作
									</td> -->
								</tr>
								<s:if test="page.pageResult.size()!=0">
									<s:iterator value="page.pageResult" id="contract"
										status="status">
										<tr>
											<td>
												<s:property
													value="(page.currentPage-1)*page.pageSize+#status.count" /> 
											</td>
											<td>
												<a href="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action?queryCashRecordCondition.contractId=<s:property value="#contract.contractId"/>">
													<s:property value="#contract.contractId" /> </a>
														<a href="javascript:openWin(<s:property value="#contract.id"/>)"><font color="green">详细</font></a>
											</td>
											<td>
												<a
													href="<%=contextPath%>/cus/cus!showCustomerList.action?queryCustomerCondition.email=<s:property value="#contract.email"/>"><s:property
														value="#contract.email" /> </a> &nbsp;&nbsp;
												<s:if test="#contract.payType==2">
													｜&nbsp;&nbsp;<a
														href='<%=contextPath%>/cou/address!toContractCustomerAddress.action?address.id=<s:property value="#contract.cusIdAddress"/>'><font
														color="green">地址</font> </a>
													｜&nbsp;&nbsp;<a
														href="javascript:openWin(<s:property value="#contract.id"/>)"><font
														color="green">详细</font> </a>
													｜&nbsp;&nbsp;<a
														href='<%=contextPath%>/finance/backContract!getContractView.action?contract.id=<s:property value="#contract.id"/>'>地址excel</a>
												</s:if>
											</td>
											<td>
												<s:property value="#contract.webFrom" />
											</td>
											<td>
												<s:property value="#contract.webAgent" />
											</td>
											<td>
												<s:property value="#contract.contractFromUrl" />
											</td>
											<td>
												<s:date name="#contract.createTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:date name="#contract.payTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:if test="#contract.payType==2">
												<script>
												var lin=<s:property value="#contract.contractCutSumMoney" />+10;
												document.write(lin);
												</script>											
												</s:if>
												<s:else>
												<s:property value="#contract.contractCutSumMoney" />													
												</s:else>
											</td>
											<td>
												<s:property value="#contract.contractSumMoney" />
											</td>
											<td>
												<s:property value="#contract.couponMoney" />
											</td>
											<td>
												<s:if test="#contract.payType==2">
												10.0
												</s:if>
											</td>
											<td id="666666">
												<s:if test="#contract.payType==2">
													<s:if test="#contract.status==0">未激活</s:if>
													<s:elseif test="#contract.status==1">已激活</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:if>
												<s:elseif test="#contract.payType==1">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==0">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==3">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==4">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==5">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==6">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==7">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==8">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:elseif>
												<s:elseif test="#contract.payType==9">
													<s:if test="#contract.status==0">未付款</s:if>
													<s:elseif test="#contract.status==1">已付款</s:elseif>
													<s:elseif test="#contract.status==3">已退费</s:elseif>
													<s:elseif test="#contract.status==4">已取消</s:elseif>
												</s:elseif>
											</td>
											<td>
												<s:if test="#contract.payType==1">
												支付宝/网银
												</s:if>
												<s:elseif test="#contract.payType==3">
												网银在线/网银
												</s:elseif>
												<s:elseif test="#contract.payType==2">
												货到付款
												</s:elseif>
												<s:elseif test="#contract.payType==0">
												内部开通
												</s:elseif>
												<s:elseif test="#contract.payType==4">
												快钱
												</s:elseif>
												<s:elseif test="#contract.payType==5">
												代理商
												</s:elseif>
												<s:elseif test="#contract.payType==6">
												真友
												</s:elseif>
												<s:elseif test="#contract.payType==7">
												银行汇款
												</s:elseif>
												<s:elseif test="#contract.payType==8">
												银联在线/银联
												</s:elseif>
												<s:elseif test="#contract.payType==9">
												课程卡
												</s:elseif>
											</td>
											<td>
												<s:property value="#contract.callSum" />
											</td>
											<td>
												<s:if test="#contract.payType==0">
													无
												</s:if>
												<s:elseif test="#contract.payType==2">
													<s:if test="#contract.status==4">
														<s:if test="#contract.cancelFrom==0">
															前台
														</s:if>
														<s:if test="#contract.cancelFrom==1">
															后台
														</s:if>
													</s:if>
													<s:else>
															无
													</s:else>
												</s:elseif>
												<s:else>
													<s:if test="#contract.status==4">
														<s:if test="#contract.cancelFrom==0">
															前台
														</s:if>
														<s:if test="#contract.cancelFrom==1">
															后台
														</s:if>
													</s:if>
													<s:else>
														无
													</s:else>
												</s:else>												
											</td>
												<td><s:property value="#contract.createUserName" /></td>
												<td>
											<s:date name="#contract.chanceStime" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:if test="#contract.origin==0">--</s:if>
												<s:if test="#contract.origin==null">--</s:if>
										        <s:if test="#contract.origin==1">自然注册</s:if>
										        <s:if test="#contract.origin==2">乐语在线</s:if>
										        <s:if test="#contract.origin==4">自然留言</s:if>
										        <s:if test="#contract.origin==6">CallIn</s:if>
											</td>
											<td>
										        <s:property value="#contract.userName" />
											</td>
											<td>
											<s:date name="#contract.chanceUtime" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<%-- <td>
												<s:if test="#contract.payType==2">
													<!--<s:if test="#contract.status!=3">
														<s:set name="tempStatus" value="0" />
														<s:iterator value="#contract.codList" id="cod">
															<s:if test="#cod.codStatus>#tempStatus">
																<s:set name="tempStatus" value="#cod.codStatus" />
															</s:if>
														</s:iterator>
														<s:if test="#tempStatus==0">
															<a
																href="<%=contextPath%>/finance/backContract!getContractCOD.action?contractStep=1&contract.id=<s:property value="#contract.id"/>">审核</a>
														</s:if>
														<s:elseif test="#tempStatus==1">
															<a
																href="<%=contextPath%>/finance/backContract!getContractCOD.action?contractStep=2&contract.id=<s:property value="#contract.id"/>">发货</a>
														</s:elseif>
														<s:elseif test="#tempStatus==2">
															<a
																href="<%=contextPath%>/finance/backContract!getContractCOD.action?contractStep=3&contract.id=<s:property value="#contract.id"/>">送达</a>
														</s:elseif>
														<s:elseif test="#tempStatus==3">
															<a
																href="<%=contextPath%>/finance/backContract!getContractCOD.action?contractStep=4&contract.id=<s:property value="#contract.id"/>">结款</a>
														</s:elseif>
														<s:else>
														------
														</s:else>
													</s:if>
													<s:else>
												  ------
												  </s:else>
											    ｜<a
														href="<%=contextPath%>/finance/backContract!getContractCancel.action?contract.id=<s:property value="#contract.id"/>">取消</a>｜-->
														<s:if test="#contract.status!=4">
																											<a href="#"
															onclick="onClick('god<s:property value="#contract.id"/>')"
															class="font_bai">展开</a>
														</s:if>
														<s:else>
															<a href='javaScript:void(0)' class='Rtt' id='Rupdate-<s:property value="#contract.id"/>' cudId=<s:property value="#contract.cusId"/> >恢复</a>
														</s:else>
												</s:if>
												<s:else>
													<!--  <a href='books!deleteBooks.action?books.bkId=<s:property value="bkId"/>' onclick="return confirm('是否删除？')">删除</a>|-->
													<s:if test="#contract.status==4&&#contract.payType!=0">
														<a href='javaScript:void(0)' class='Rtt' id='Rupdate-<s:property value="#contract.id"/>' cudId=<s:property value="#contract.cusId"/> >恢复</a>
													</s:if>
													<s:else>
														<s:if test="#contract.status!=2&&#contract.status!=3">
														<s:if test="#contract.status==1">
															<!--
															 <a href='<%=contextPath%>/finance/backContract!ReFund.action?contract.id=<s:property value="#contract.id"/>'
																onclick="return confirm('确定退费？')">退费</a>
															 -->	
														</s:if>
														<s:else>
												    ------
													</s:else>｜ 
														<s:if test="#contract.payType==2">
															<s:if test="#contract.status!=4&&#contract.status!=1">
																<a href='<%=contextPath%>/finance/backContract!getApprove.action?contract.id=<s:property value="#contract.id"/>&contract.cusId=<s:property value="#contract.cusId"/>'
																	onclick="return confirm('审核订单需甚重使用，会重新初始化知识点？')">审核订单</a>
															</s:if>
															
														</s:if>
														<s:elseif test="#contract.payType==1">
															<s:if test="#contract.status!=3&&#contract.status!=1">
																<a href='<%=contextPath%>/finance/backContract!getApprove.action?contract.id=<s:property value="#contract.id"/>&contract.cusId=<s:property value="#contract.cusId"/>'
																	onclick="return confirm('审核订单需甚重使用，会重新初始化知识点？')">审核订单</a>
															</s:if>
															
														</s:elseif>
														<s:elseif test="#contract.payType==0">
															<s:if test="#contract.status!=1">
																<a href='<%=contextPath%>/finance/backContract!getApprove.action?contract.id=<s:property value="#contract.id"/>&contract.cusId=<s:property value="#contract.cusId"/>'
																	onclick="return confirm('审核订单需甚重使用，会重新初始化知识点？')">审核订单</a>
															</s:if>
														</s:elseif>
														<s:elseif test="#contract.payType==3">
															<s:if test="#contract.status!=3&&#contract.status!=1">
																<a href='<%=contextPath%>/finance/backContract!getApprove.action?contract.id=<s:property value="#contract.id"/>&contract.cusId=<s:property value="#contract.cusId"/>'
																	onclick="return confirm('审核订单需甚重使用，会重新初始化知识点？')">审核订单</a>
															</s:if>
															
														</s:elseif>
														<s:elseif test="#contract.payType==4">
															<s:if test="#contract.status!=3&&#contract.status!=1">
																<a href='<%=contextPath%>/finance/backContract!getApprove.action?contract.id=<s:property value="#contract.id"/>&contract.cusId=<s:property value="#contract.cusId"/>'
																	onclick="return confirm('审核订单需甚重使用，会重新初始化知识点？')">审核订单</a>
															</s:if>
															
														</s:elseif>
														<s:elseif test="#contract.payType==5">
															<s:if test="#contract.status!=3&&#contract.status!=1">
																<a href='<%=contextPath%>/finance/backContract!getApprove.action?contract.id=<s:property value="#contract.id"/>&contract.cusId=<s:property value="#contract.cusId"/>'
																	onclick="return confirm('审核订单需甚重使用，会重新初始化知识点？')">审核订单</a>
															</s:if>
														</s:elseif>
														<s:elseif test="#contract.payType==7||#contract.payType==6||#contract.payType==8">
															<s:if test="#contract.status!=3&&#contract.status!=1">
																<a href='<%=contextPath%>/finance/backContract!getApprove.action?contract.id=<s:property value="#contract.id"/>&contract.cusId=<s:property value="#contract.cusId"/>'
																	onclick="return confirm('审核订单需甚重使用，会重新初始化知识点？')">审核订单</a>
															</s:if>
														</s:elseif>
													</s:if>
													<s:else>
												无
												</s:else>
													</s:else>																									
												</s:else>
												<s:if test="#contract.status==0">
													｜<a href="javascript:void(0)" class="tt" id="update-<s:property value="#contract.id"/>" cudId="<s:property value="#contract.cusId"/>">取消</a>										
												</s:if>
											</td> --%>
										</tr>
										<tr id="god<s:property value="#contract.id"/>"
											style="display: none">
											<td colspan="13">
												<div class="wen_info_sr mtop-10 bor1blue font_hui">
													<div style="margin: 0 auto; width: 100%;">
														<s:if test="#contract.codList.size>0">
															<s:iterator value="#contract.codList" id="newCod">
																<div style="width: 40%; float: left;">
																	<table>
																		<tr>
																			<td>
																				<s:property value="#newCod.codContent" />
																			</td>
																			<td>
																				&nbsp;
																			</td>
																			<td>
																				&nbsp;
																			</td>
																			<td>
																				&nbsp;
																			</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td>
																				状态：
																				<s:if test="#newCod.codStatus==1">
				  										等待发货
				  										</s:if>
																				<s:elseif test="#newCod.codStatus==2">
				  										等待送达
				  										</s:elseif>
																				<s:elseif test="#newCod.codStatus==3">
				  										等待结款
				  										</s:elseif>
																				<s:elseif test="#newCod.codStatus==4">
				  										等待激活
				  										</s:elseif>
																			</td>
																			<td>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：
																				<s:date name="#newCod.codTime"
																					format="yyyy-MM-dd HH:mm:ss" />
																			</td>
																			<td>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人：
																				<s:property value="#newCod.user.loginName" />
																			</td>
																		</tr>
																	</table>

																</div>
															</s:iterator>
														</s:if>
														<s:else>
				  									　　无状态
				  									</s:else>
													</div>
												</div>
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td align="center" colspan="17">
											没有订单!
										</td>
									</tr>
								</s:else>
							</table>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>
					<tr>
						<td class="td_wid_1">
							<img src="<%=contextPath%>/back/images/tab_18.gif" />
						</td>
						<td class="lists_bottom"><jsp:include
								page="/back/jsp/common/showPage.jsp" />
						</td>
						<td class="td_wid_r">
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>