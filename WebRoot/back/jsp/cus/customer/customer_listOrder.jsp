<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学员列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<style type="text/css">
		.totel { width:100%;}
		.if_left { width:54%; float:left; padding:0; margin:0; }
		.if_right { width:44%; float:left; overflow-x:auto;overflow-y:hidden; padding:0; margin:0;  }
		</style>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
	
	function selected(groupId){
		if(groupId!=0){
			$.ajax({
									url : "<%=contextPath%>/cus/cusOrder!getGroupUserInfo.action",  
									data : {
									"groupIds":groupId,
									"status":1},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									var content=result.returnMessage;
									$('#sysUserName').html(content);
									},
									error: function(){ 
										alert('error');  
									}
				  });
				  }
		}
	
	function dobatch(){
	
	  //先判断是否选择了指派的销售员
	  //判断是否选择学员
	  var obj=document.getElementsByName('sellRecordBox'); 
	  var s='';  
	  for(var i=0; i<obj.length; i++){  
	  		if(obj[i].checked){
	  		if(s==''){
	  			s+=obj[i].value;
	  		}else{
	  		 s+=','+obj[i].value;
	  		 	}
	 		 }
	  }  
	  var sysUserId=$('#sysUserId').val();
	  if(sysUserId==0){
	  	alert('请选择指派人');
	  	return false;
	  }
	  var scene=$('#scene').val();
	  if(scene==0){
	  	alert('请选择场景');
	  	return;
	  }
	  if(s==''){
	  	 alert("请选择学员！");
	  }else{
	  	//提交指派操作
	 		$.ajax({
									url : "<%=contextPath%>/cus/cusOrder!sellrecordBatchAdd.action",  
									data : {"sellRecordBox" : s,
									"sysUserId":sysUserId,
									"scene":scene},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									if(bo){
										alert('批量添加成功');
										var showSelf='<s:property value="queryCustomerCondition.showSelf"/>';
										if(showSelf==1){
										document.cusForm.action = "<%=contextPath%>/cus/cusOrder!showCustomerListByBuyTime.action?queryCustomerCondition.currentPage=1&queryCustomerCondition.showSelf=1";
										}else{
											document.cusForm.action = "<%=contextPath%>/cus/cusOrder!showCustomerListByBuyTime.action?queryCustomerCondition.currentPage=1" ;
											}
											document.cusForm.submit();
										
									}else{
										alert('您选择的用户中部分已有人指派');
									}
									},
									error: function(){ 
										alert('error');  
									}
				  });
	  }
	}
	
	
	function checkOutExcel()
	{
	     	document.searchForm.action="<%=contextPath%>/cus/cusOrder!exportExcelFile.action";
			document.searchForm.submit();
	}
	
	function openwin(url) { 
	window.open (url, "newwindow", "height=800, width=800, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,left=350,top=100") 
	//写成一行 
	} 


	
	<!--判断是否为数字-->
	function IsNum(Text){
		return /^\d+$/.test(Text);
	}
	
	function IsMoblie(Text)
	{
		return /^1\d{10}$/g.test(Text);

	}

	 function search(ObjectForm){
	  var startTime=document.getElementById("startTime").value;
	  var endTime=document.getElementById("endTime").value;
	  var startNumber=document.getElementById("startNumber").value;
	  var endNumber=document.getElementById("endNumber").value;
	  var mobile=document.getElementById("mobile").value;
	  if(startTime!=null&&endTime!=null){
	  if(startTime>endTime){
		 alert("结束时间要大于开始时间!");
		 return;
	  }else{
	  
		  if(mobile!="")
		  {
		  		if(mobile.length>11)
		  		{
					
		  			alert("手机号格式不正确");
		  			return ;
		  		}
		  }
		  
		if(startNumber!=""||endNumber!="")
		{	
		 	if(IsNum(startNumber)!=true)
			{
				alert("首次数不数字");
				return ;
			}
			if(IsNum(endNumber)!=true)
			{
				alert("末次数不数字");
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
			
			if(parseInt(startNumber)>parseInt(endNumber))
			{
				alert("首次数不能大于末次数");
				return ;
			}
			
		}
			var showSelf='<s:property value="queryCustomerCondition.showSelf"/>';
			ObjectForm.action="<%=contextPath%>/cus/cusOrder!showCustomerListByBuyTime.action";
			if(showSelf==1){
			ObjectForm.action="<%=contextPath%>/cus/cusOrder!showCustomerListByBuyTime.action?queryCustomerCondition.showSelf=1";
			}
			ObjectForm.submit();
		}
	  }
	}

	

	function delCustomer() {
		if(window.confirm("确认删除这些学员吗？")) {
			document.cusForm.action = "<%=contextPath%>/cus/cus!deleteCustomer.action?queryCustomerCondition.currentPage=" + <s:property value="page.currentPage"/>;
			document.cusForm.submit();
		}
	}
	function addCustomer() {
		window.location.href = "<%=contextPath%>/cus/cus!initAddCustomer.action";
	}
	
	function updateCustomer(id) {
		window.location.href = "<%=contextPath%>/cus/cus!initUpdateCustomer.action?customer.cusId=" + id;
	}
	
	function resetKey() {
		$("#op0").attr("selected","selected");
		$("input[name=queryCustomerCondition.email]").val("");
		$("input[name=queryCustomerCondition.mobile]").val("");
		$("input[name=queryCustomerCondition.startNumber]").val("0");
		$("input[name=queryCustomerCondition.endNumber]").val("999999");
		$("input[name=startTime]").val("");
		$("input[name=endTime]").val("");
		$("#op1").attr("selected","selected");
		$("#op2").attr("selected","selected");
		$("#op3").attr("selected","selected");
		$("#subjectId").val(-1);
//		var str=document.getElementById("queryCustomerCondition.subjectId").value;
//		alert(str);
//		if(str!=-1){
//		alert(11);
//			document.getElementById("queryCustomerCondition.subjectId").selected=-1;
//		}
	}
	
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
	}
	
	function updatePwd(id) {
		window.location.href = "<%=contextPath%>/cus/cus!initUpdatePwd.action?customer.cusId=" + id;
	}
	function freeCourse(id,type){
		if(confirm("免费赠送售卖方式，不会重复")){
		window.location.href="<%=contextPath%>/cus/cus!freeCourse.action?customer.cusId="+id+"&customer.cusType="+type;
		}
	}
	function toForgotPwd() {
		window.location.href = "<%=contextPath%>/cus/cus!toForgotPwd.action";
	}
	
	function orderByPaixu(paixu){
		var showSelf='<s:property value="queryCustomerCondition.showSelf"/>';
		if(showSelf==1){
		document.cusForm.action = "<%=contextPath%>/cus/cusOrder!showCustomerListByBuyTime.action?queryCustomerCondition.currentPage=1&queryCustomerCondition.paixu=1&queryCustomerCondition.showSelf=1";
		}else{
			document.cusForm.action = "<%=contextPath%>/cus/cusOrder!showCustomerListByBuyTime.action?queryCustomerCondition.currentPage=1" +"&queryCustomerCondition.paixu="+paixu;
			}
			document.cusForm.submit();
	}
	
	function viewCus(cusId) {
		if(cusId==0||cusId==""||cusId==null){
			alert("该用户不存在！");
			return false;
		}else{
			window.location.href = "<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=" + cusId;
		}
	}
	
	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
		$("#cusFromUrl").val(${customer.cusFromUrl});
	}
	function changeStartHH(hourStr) {
		$.each($("#startHH option"),function(){
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
</script>
	</head>
	<body onload="changeData()">
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />					</td>
					<td class="lists_top">
						<font class="lists_fleft">查询条件</font>
						<font class="lists_fright"> </font>					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />					</td>
				</tr>
				<tr >
					<td width="12" class="lists_bor">					</td>
					<td>
						<form name="searchForm" action="" method="post">
						<div class="msg-yw">
						  <table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="lists">
                            <tr  >
                              <td width="40%" style="text-align:left"><s:hidden name="queryCustomerCondition.currentPage" value="1" />
                                注册开始时间：
                                <input type="text" name="startTime" readonly id="startTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${startTime}" width="10"/>
                                  <select name="startHH" id="startHH">
                                    <option value=" 00:00:00" id="op1">00:00:00</option>
                                    <option value=" 01:00:00">01:00:00</option>
                                    <option value=" 02:00:00">02:00:00</option>
                                    <option value=" 03:00:00">03:00:00</option>
                                    <option value=" 04:00:00">04:00:00</option>
                                    <option value=" 05:00:00">05:00:00</option>
                                    <option value=" 06:00:00">06:00:00</option>
                                    <option value=" 07:00:00">07:00:00</option>
                                    <option value=" 08:00:00">08:00:00</option>
                                    <option value=" 09:00:00">09:00:00</option>
                                    <option value=" 10:00:00">10:00:00</option>
                                    <option value=" 11:00:00">11:00:00</option>
                                    <option value=" 12:00:00">12:00:00</option>
                                    <option value=" 13:00:00">13:00:00</option>
                                    <option value=" 14:00:00">14:00:00</option>
                                    <option value=" 15:00:00">15:00:00</option>
                                    <option value=" 16:00:00">16:00:00</option>
                                    <option value=" 17:00:00">17:00:00</option>
                                    <option value=" 18:00:00">18:00:00</option>
                                    <option value=" 19:00:00">19:00:00</option>
                                    <option value=" 20:00:00">20:00:00</option>
                                    <option value=" 21:00:00">21:00:00</option>
                                    <option value=" 22:00:00">22:00:00</option>
                                    <option value=" 23:00:00">23:00:00</option>
                                    <option value=" 23:59:59">23:59:59</option>
                                  </select>
                                (时:分:秒) </td>
                              <td style="text-align:left"> 注册结束时间：
                                <input type="text" name="endTime" readonly id="endTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${endTime}" width="10"/>
                                  <select name="endHH" id="endHH">
                                    <option value=" 23:59:59" id="op0">23:59:59</option>
                                    <option value=" 00:00:00">00:00:00</option>
                                    <option value=" 01:00:00">01:00:00</option>
                                    <option value=" 02:00:00">02:00:00</option>
                                    <option value=" 03:00:00">03:00:00</option>
                                    <option value=" 04:00:00">04:00:00</option>
                                    <option value=" 05:00:00">05:00:00</option>
                                    <option value=" 06:00:00">06:00:00</option>
                                    <option value=" 07:00:00">07:00:00</option>
                                    <option value=" 08:00:00">08:00:00</option>
                                    <option value=" 09:00:00">09:00:00</option>
                                    <option value=" 10:00:00">10:00:00</option>
                                    <option value=" 11:00:00">11:00:00</option>
                                    <option value=" 12:00:00">12:00:00</option>
                                    <option value=" 13:00:00">13:00:00</option>
                                    <option value=" 14:00:00">14:00:00</option>
                                    <option value=" 15:00:00">15:00:00</option>
                                    <option value=" 16:00:00">16:00:00</option>
                                    <option value=" 17:00:00">17:00:00</option>
                                    <option value=" 18:00:00">18:00:00</option>
                                    <option value=" 19:00:00">19:00:00</option>
                                    <option value=" 20:00:00">20:00:00</option>
                                    <option value=" 21:00:00">21:00:00</option>
                                    <option value=" 22:00:00">22:00:00</option>
                                    <option value=" 23:00:00">23:00:00</option>
                                  </select>
                                (时:分:秒) </td>
                       
                            </tr>
                            <tr>
                              <td style="text-align:left"> 注册路径：
                                <s:select name="queryCustomerCondition.subjectId"
											id="subjectId" list="subjectList"
											listKey="subjectId" listValue="subjectName" headerKey="-1"
											headerValue="---请选择---" theme="simple" style="width: 155px"></s:select>                              </td>
                              <td style="text-align:left"> 用户类型：
                                <select name="queryCustomerCondition.cusType"
											id="queryCustomerCondition.cusType" style="width: 155px">
                                    <option value="" id="op2"> ---请选择--- </option>
                                    <option value="0"> 注册学员 </option>
                                    <option value="1"> 内部学员 </option>
                                    <option value="2"> 体验用户 </option>
                                    <option value="3"> 临时学员(10天) </option>
                                    <option value="4"> 临时学员(30天) </option>
                                    <option value="5"> 临时学员(5天) </option>
                                  </select>                              </td>
                      
                            </tr>
                            <tr>
                              <td width="30%" style="text-align:left"> 邮箱地址：
                                <input  type="text"
											value='<s:property value="querycustomercondition.email"/>'
                                
                                name="queryCustomerCondition.email" /> </td>
                              <td style="text-align:left">手机号：&nbsp;&nbsp;
                                <input  type="text" id="mobile"
											value='<s:property value="querycustomercondition.mobile"/>'
                                
                                name="queryCustomerCondition.mobile" /> </td>
                              
                            </tr>
                            <tr>
                              <td width="30%" style="text-align:left"> 登陆次数：
                                <input  type="text" value="<s:property value="queryCustomerCondition.startNumber"/>" id="startNumber" name="queryCustomerCondition.startNumber"
                                style="width: 100px;" />
                                至
                                <input type="text" value="<s:property value="queryCustomerCondition.endNumber"/>" id="endNumber" name="queryCustomerCondition.endNumber"
                                style="width: 100px;" />
                                次 </td>
                              <td style="text-align:left"> 域名来源：
                                <s:if test="fromURLType==1">
                                    <select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
                                      <option value="1">highso.org.cn</option>
                                    </select>
                                    <s:hidden name="fromURLType" value="1"></s:hidden>
                                  </s:if>
                                  <s:elseif test="fromURLType==4">
                                    <select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
                                      <option value="4">highso.com.cn</option>
                                    </select>
                                    <s:hidden name="fromURLType" value="4"></s:hidden>
                                  </s:elseif>
                                  <s:elseif test="fromURLType==3">
                                    <select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
                                      <option value="3">highso.org</option>
                                    </select>
                                    <s:hidden name="fromURLType" value="3"></s:hidden>
                                  </s:elseif>
                                  <s:elseif test="fromURLType==2">
                                    <select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
                                      <option value="2">highso.cn</option>
                                      <s:hidden name="fromURLType" value="2"></s:hidden>
                                    </select>
                                  </s:elseif>
                                  <s:elseif test="fromURLType==5">
                                    <select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
                                      <option value="5">highso.net.cn</option>
                                      <s:hidden name="fromURLType" value="5"></s:hidden>
                                    </select>
                                  </s:elseif>
                                  <s:else>
                                    <select name="customer.cusFromUrl" id="cusFromUrl" style="width:155px">
                                      <option value="" id="op3">---请选择---</option>
                                      <option value="1">highso.org.cn</option>
                                      <option value="2">highso.cn</option>
                                      <option value="3">highso.org</option>
                                      <option value="4">highso.com.cn</option>
                                      <option value="5">highso.net.cn</option>
                                    </select>
                                  </s:else>                              </td>
                              
                            </tr>
                            <tr>
                            
                              
                               <td  style="text-align:left" colspan="2" >销售姓名:&nbsp;&nbsp;<input type="text" name="queryCustomerCondition.sellNameSearch" /></td>
                            </tr>
                            <tr>
                            	<td colspan="2"  style="text-align:left">排序方式:
	                            	<select name="queryCustomerCondition.paixu"  style="width:155px">
	                                      <option value="0" >---请选择---</option>
	                                      <option value="1">注册未下单机会</option>
	                                      <option value="2">下单未支付机会</option>
	                                      <option value="3">最新支付</option>
	                                      <option value="4">最新指派</option>
	                                </select>
                                </td>
                             </tr>
                            
                            <s:if test="queryCustomerCondition.showSelf!=1">
                            <tr><td  style="text-align:left" colspan="2"><img src="<%=contextPath%>/back/images/add_a.gif" /> <a href="javascript:search(document.searchForm)">查询</a> <img src="<%=contextPath%>/back/images/del_a.gif" /> <a href="javascript:resetKey()">清空</a><img src="<%=contextPath%>/back/images/down16_16.gif" /><a href="javascript:checkOutExcel()" >导出excel</a></td>
                              </s:if><s:else>
							  		<td colspan="2" style="text-align:left"><img src="<%=contextPath%>/back/images/add_a.gif" /> <a href="javascript:search(document.searchForm)">查询</a> <img src="<%=contextPath%>/back/images/del_a.gif" /> <a href="javascript:resetKey()">清空</td>
							  </s:else>          </tr>
							  <tr><td colspan="2"  style="text-align:left"><input name="button" type="button" onclick="orderByPaixu(1)" value="注册未下单机会"/>
                                  <input name="button" type="button" onclick="orderByPaixu(2)" value="下单未支付机会"/>
                                   <input name="button" type="button" onclick="orderByPaixu(3)" value="最新支付"/>
                                    <input name="button" type="button" onclick="orderByPaixu(4)" value="最新指派"/>
                                  </td></tr>
                          </table>
                          </div>
						</form>				
				
    <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
		<tr class="lists_infobg"><td>项目</td><td>销售坐席</td><td>场景</td><td>货到付款激活量</td><td>分配量</td><td>拨打量</td><td>接通量</td><td>取消量</td><td>成交量</td><td>成交总量</td><td>订单流水</td><td>转化率</td>
		</tr>
		<s:set name="sum_sellCount" value="0"/>
		<s:set name="sum_callCount" value="0"/>
		<s:set name="sum_successCount" value="0"/>
		<s:set name="sum_payAddSendCount" value="0"/>
		<s:set name="percent" id="percent" value="0"/>
		<s:set name="sum_totelPrice" value="0"/>
		<s:set name="sum_sendSuccessCount" value="0"/>
		<s:set name="sum_totelCancelCount" value="0"/>
		<s:set name="sum_cancelCount" value="0"/>
		<s:set name="sum_backCancelCount" value="0"/>
		<s:set name="sum_payCount" value="0"/>
		<s:set name="sum_sendCount" value="0"/>
		
		<s:iterator value="simpleStatList" id="customer"
										status="status">
										
		<s:set name="sum_sellCount" value="#sum_sellCount+#customer.sellCount"/>
		<s:set name="sum_callCount" value="#sum_callCount+#customer.callCount"/>
		<s:set name="sum_successCount" value="#sum_successCount+#customer.successCount"/>
		<s:set name="sum_payAddSendCount" value="#sum_payAddSendCount+#customer.sendCount+#customer.payCount"/>
		<s:set name="percent" value="(#customer.sendCount+#customer.payCount)*100.0/#customer.sellCount"/>
		<s:set name="sum_totelPrice" value="#sum_totelPrice+#customer.intBankTotelPrice+#customer.effSendTotelPrice"/>
		<s:set name="sum_sendSuccessCount" value="#sum_sendSuccessCount+#customer.sendSuccessCount"/>
		<s:set name="sum_totelCancelCount" value="#sum_totelCancelCount+#customer.cancelCount+#customer.backCancelCount"/>
		<s:set name="sum_cancelCount" value="#sum_cancelCount+#customer.cancelCount"/>
		<s:set name="sum_backCancelCount" value="#sum_backCancelCount+#customer.backCancelCount"/>
		<s:set name="sum_payCount" value="#sum_payCount+#customer.payCount"/>
		<s:set name="sum_sendCount" value="#sum_sendCount+#customer.sendCount"/>
		
		<tr>
			<td><s:property value="#customer.groupName"/></td>
			<td><s:property value="#customer.sysUserName"/></td>
			<td><s:if test="#customer.scene==0">--</s:if>
				<s:if test="#customer.scene==1">在线</s:if>
				<s:if test="#customer.scene==2">订单</s:if>
				<s:if test="#customer.scene==3">注册</s:if>
			</td>
			<td><s:property value="#customer.sendSuccessCount"/></td>
			<td><s:property value="#customer.sellCount"/></td>
			<td><s:property value="#customer.callCount"/></td>
			<td><s:property value="#customer.successCount"/></td>
			<td><font color="#0033FF"><s:property value="#customer.cancelCount+#customer.backCancelCount"/>|<s:property value="#customer.cancelCount"/>+<s:property value="#customer.backCancelCount"/></font></td>
			<td><s:property value="#customer.payCount"/>&#43;<s:property value="#customer.sendCount"/></td>
			<td><s:property value="#customer.payCount+#customer.sendCount"/></td>
			<td><script type="text/javascript">
				var totelPrice='<s:property value="#customer.intBankTotelPrice+#customer.effSendTotelPrice"/>';
				var index=totelPrice.indexOf('.');
				document.write(totelPrice.substring(0,index));
			</script></td>
			<td><script type="text/javascript">
			var percent='<s:property value="#percent"/>';
				var patrn=/^(\d*\.)?\d+$/;
					if (patrn.exec(percent)){ 
						var index=percent.indexOf('.');
						document.write(percent.substring(0,index+2));
					}else{
						document.write('0.0');
					}
			</script>&#37;</td>
			</tr>
		</s:iterator>
		<tr>
			<td>合计</td>
			<td></td><td></td>
			<td><s:property value="#sum_sendSuccessCount"/></td>
			<td><s:property value="#sum_sellCount"/></td>
			<td><s:property value="#sum_callCount"/></td>
			<td><s:property value="#sum_successCount"/></td>
			<td><font color="#0033FF"><s:property value="#sum_totelCancelCount"/>&#124;<s:property value="#sum_cancelCount"/>&#43;<s:property value="#sum_backCancelCount"/></font></td>
			<td><s:property value="#sum_payCount"/>&#43;<s:property value="#sum_sendCount"/></td>
			<td><s:property value="#sum_payAddSendCount"/></td>
			<td><script type="text/javascript">
				var totelPrice='<s:property value="#sum_totelPrice"/>';
				totelPrice=Math.round((Math.floor(totelPrice*1000)/10))/100*1.0;
				totelPrice=totelPrice.toString();
				var index=totelPrice.indexOf('.');
				if(index==-1){
					document.write(totelPrice);
				}else{
					document.write(totelPrice.substring(0,index));
				}
			</script></td>
			<td></td>
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
				<s:if test="roleId!=73">
			<div>&nbsp;&nbsp;项目:&nbsp;&nbsp;
			<s:select list="groupList" name="querySellrecordCondition.groupId" listKey="groupId" listValue="groupName" headerKey="0"
								headerValue="请选择项目" theme="simple" onchange="selected(this.value);"></s:select>
			&nbsp;&nbsp;指派人：<span id="sysUserName"><s:select name="sellrecord.sysUserId" id="sysUserId" list="userList" listKey="userId" listValue="userName" headerKey="0"
								headerValue="请选择指派人" theme="simple">							</s:select></span>
				<select name="sellrecord.scene" id="scene">
					<option value=0>请选择场景</option>
					<option value=1>在线</option>
					<option value=2>订单</option>
					<option value=3>注册</option>
				</select>
				<input type="button" value="批量指派" onclick="dobatch();" />
			</div>
			</s:if>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />					</td>
					<td class="lists_top">
						<font class="lists_fleft">学员列表</font>
						<font class="lists_fright"> </font>					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />					</td>
				</tr>
				<tr >
					<td width="12" class="lists_bor">					</td>
					<td>
			
			<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<form name="cusForm" method="post">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
									<tr class="lists_infobg">
									<s:if test="roleId!=73">
										<td width="3%" style="text-align:left">
											选择										</td>
									</s:if>
										<td width="10%" style="text-align:left">
											电子邮件										</td>
										<td width="10%" style="text-align:left">
											注册路径										</td>
										<td width="5%" style="text-align:left">
											域名来源										</td>
										<td width="5%" style="text-align:left">
											手机号码										</td>
										<td width="8%" style="text-align:left">
											指派状态										</td>
										<td width="8%" style="text-align:left">指派时间</td>
										<td width="5%" style="text-align:left">
											登录次数										</td>
										<td width="8%" style="text-align:left">注册时间</td>
										<td width="8%" style="text-align:left">下单时间</td>
										<td width="8%" style="text-align:left">支付时间</td>
										
										<td width="8%" style="text-align:left">
											支付数/订单数										</td>
									</tr>
									<s:iterator value="page.pageResult" id="customer"
										status="status">
										<tr>
											<!--  <td><input type="checkbox" name="ids" value="<s:property value="cusId"/>"/></td>-->
											<s:if test="roleId!=73">
											<td style="text-align:left">
												<input type="checkbox" name="sellRecordBox" id="sellRecordBox" value="<s:property value='cusId' />" />										</td>
											</s:if>
											<td style="text-align:left">
												<a href="#" onclick="viewCus(<s:property value="cusId" />)"><s:property
														value="email" /> </a>											</td>
											<td style="text-align:left">
												<s:property value="cusSubject.subjectName" />											</td>
											<td style="text-align:left">
												<s:property value="cusFromUrl"/>											</td>
											<td style="text-align:left">
												<s:property value="mobile" />											</td>
											<td style="text-align:left">
												<s:if test="#customer.sellrecord==null">
												<s:if test="roleId!=73">
									<a style="color:#FF0000" onclick="openwin('<%=contextPath %>/cus/cusOrder!toAddDes.action?cusId=<s:property value="cusId" />')" href="#">未指派</a>	
									</s:if><s:else><a style="color:#FF0000"  href="#">未指派</a>	</s:else>
											  </s:if>
												<s:elseif test="#customer.sellrecord.reason==''||#customer.sellrecord.reason==null" >
													<s:if test="roleId!=73">
														<s:if test="#customer.paytime!=null">
															<a onclick="openwin('<%=contextPath %>/cus/cusOrder!toUpdateDes.action?crmId=<s:property value="#customer.sellrecord.crmId"/>')" href="#" ><font color="red"><s:property value="#customer.sellrecord.sysUserName"/>|未记录
															<s:if test="#customer.sellrecord.commuStatus==1">|空号</s:if>
															<s:elseif test="#customer.sellrecord.commuStatus==2">|通话中</s:elseif>	
															<s:elseif test="#customer.sellrecord.commuStatus==3">|再联系</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==4">|测试</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==5">|正常接通</s:elseif></font></a>
														</s:if><s:else>
															<a onclick="openwin('<%=contextPath %>/cus/cusOrder!toUpdateDes.action?crmId=<s:property value="#customer.sellrecord.crmId"/>')" href="#" ><font color="red"><s:property value="#customer.sellrecord.sysUserName"/>|未记录
															<s:if test="#customer.sellrecord.commuStatus==1">|空号</s:if>
															<s:elseif test="#customer.sellrecord.commuStatus==2">|通话中</s:elseif>	
															<s:elseif test="#customer.sellrecord.commuStatus==3">|再联系</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==4">|测试</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==5">|正常接通</s:elseif></font></a>
														</s:else>
													</s:if><s:else>
														<a onclick="openwin('<%=contextPath %>/cus/cusOrder!toUpdateDes.action?crmId=<s:property value="#customer.sellrecord.crmId"/>')" href="#" ><font color="red"><s:property value="#customer.sellrecord.sysUserName"/>|未记录
														<s:if test="#customer.sellrecord.commuStatus==1">|空号</s:if>
														<s:elseif test="#customer.sellrecord.commuStatus==2">|通话中</s:elseif>	
														<s:elseif test="#customer.sellrecord.commuStatus==3">|再联系</s:elseif>
														<s:elseif test="#customer.sellrecord.commuStatus==4">|测试</s:elseif>
														<s:elseif test="#customer.sellrecord.commuStatus==5">|正常接通</s:elseif></font></a>
														</s:else>
												</s:elseif><s:else>
													<s:if test="roleId!=73">
														<s:if test="#customer.paytime!=null">
															<a onclick="openwin('<%=contextPath %>/cus/cusOrder!toUpdateDes.action?crmId=<s:property value="#customer.sellrecord.crmId"/>')"  href="#" ><font color="green"><s:property value="#customer.sellrecord.sysUserName"/>|已记录
															<s:if test="#customer.sellrecord.commuStatus==1">|空号</s:if>
															<s:elseif test="#customer.sellrecord.commuStatus==2">|通话中</s:elseif>	
															<s:elseif test="#customer.sellrecord.commuStatus==3">|再联系</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==4">|测试</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==5">|正常接通</s:elseif>
															</font></a>	
														</s:if><s:else>
															<a  onclick="openwin('<%=contextPath %>/cus/cusOrder!toUpdateDes.action?crmId=<s:property value="#customer.sellrecord.crmId"/>')" href="#" ><font color="green"><s:property value="#customer.sellrecord.sysUserName"/>|已记录
															<s:if test="#customer.sellrecord.commuStatus==1">|空号</s:if>
															<s:elseif test="#customer.sellrecord.commuStatus==2">|通话中</s:elseif>	
															<s:elseif test="#customer.sellrecord.commuStatus==3">|再联系</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==4">|测试</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==5">|正常接通</s:elseif>
															</font></a>	
														</s:else>	
													</s:if><s:else>
															<a  onclick="openwin('<%=contextPath %>/cus/cusOrder!toUpdateDes.action?crmId=<s:property value="#customer.sellrecord.crmId"/>')" href="#" ><font color="green"><s:property value="#customer.sellrecord.sysUserName"/>|已记录
															<s:if test="#customer.sellrecord.commuStatus==1">|空号</s:if>
															<s:elseif test="#customer.sellrecord.commuStatus==2">|通话中</s:elseif>	
															<s:elseif test="#customer.sellrecord.commuStatus==3">|再联系</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==4">|测试</s:elseif>
															<s:elseif test="#customer.sellrecord.commuStatus==5">|正常接通</s:elseif>
															</font></a>	
														</s:else>					
												</s:else>	
																		
											</td>
											<td style="text-align:left"><s:if test="#customer.sellrecord!=null"><s:date format="yyyy-MM-dd HH:mm:ss" name="#customer.sellrecord.sellTime" /></s:if></td>
											<td style="text-align:left">
												<s:property value="loginTimes" />											</td>
											<td style="text-align:left"><s:date format="yyyy-MM-dd HH:mm:ss" name="regTime" />	</td>
											<td style="text-align:left"><s:date format="yyyy-MM-dd HH:mm:ss" name="#customer.createtime" />	</td>
											<td style="text-align:left">
												<s:date format="yyyy-MM-dd HH:mm:ss" name="#customer.paytime" />	</td>
											
											<td style="text-align:left">
												<s:if test="contractCount>0">
													<s:if test="contractStatus>0">
														<a
															href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">
															<font color="red"><s:property
																	value="contractStatus" />/<s:property
																	value="contractCount" />|已付款
																	<s:if test="#customer.cancelStatus>0">|<img src="<%=contextPath%>/back/images/huo_2.png" style="width:12px;height:10px;"/></s:if>
																	<s:elseif test="#customer.payStatus>0">|<img src="<%=contextPath%>/back/images/huo_1.png" style="width:12px;height:10px;"/></s:elseif>
																	</font> </a>													</s:if>
													<s:else>
													   <s:if test="cusType==1">
														<a
															href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">
															<font color="red">内部/代理商开通<s:if test="#customer.cancelStatus>0">|<img src="<%=contextPath%>/back/images/huo_2.png" style="width:12px;height:10px;"/></s:if>
																	<s:elseif test="#customer.payStatus>0">|<img src="<%=contextPath%>/back/images/huo_1.png" style="width:12px;height:10px;"/></s:elseif></font> </a>													   </s:if>
													   <s:else>
														<a
															href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="cusId"/>">
															<font color="green"><s:property
																	value="contractStatus" />/<s:property
																	value="contractCount" />|未付款<s:if test="#customer.cancelStatus>0">|<img src="<%=contextPath%>/back/images/huo_2.png" style="width:12px;height:10px;"/></s:if>
																	<s:elseif test="#customer.payStatus>0">|<img src="<%=contextPath%>/back/images/huo_1.png" style="width:12px;height:10px;"/></s:elseif></font> </a>														</s:else>
													</s:else>
												</s:if>
											<s:else>
												0/0											</s:else>											</td>
										</tr>
									</s:iterator>
								</table>
							</form>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>
					<tr>
						<td>
							<img src="<%=contextPath%>/back/images/tab_18.gif" />
						</td>
						<td class="lists_bottom">
							<jsp:include page="/back/jsp/common/showPage.jsp" />
						</td>
						<td>
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
	<script language="javascript">
var userType=document.getElementById("queryCustomerCondition.cusType");
for (i=0;i<userType.options.length;i++)
{ 
     if(userType.options[i].value=='${queryCustomerCondition.cusType}')
     {
         userType.options[i].selected=true;break;
     } 
}
</script>
</html>
