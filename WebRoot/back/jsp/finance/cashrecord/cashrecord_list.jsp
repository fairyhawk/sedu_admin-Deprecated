<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>流水列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script language="JavaScript">

function createDateTwo(){
	var startTime=document.getElementById("createTimeBegin").value;
    var endTime=document.getElementById("createTimeEnd").value;
    if(startTime!='' && startTime>endTime){
	       alert("创建结束时间要大于开始时间!");
	       document.getElementById("createTimeEnd").value='';
	      return false;
    }
}
function createDateOne(){
	var startTime=document.getElementById("createTimeBegin").value;
    var endTime=document.getElementById("createTimeEnd").value;
    if(endTime!='' && startTime>endTime){
	       alert("创建结束时间要大于开始时间!");
	       document.getElementById("createTimeBegin").value='';
	      return false;
    }
}
function fKuanTwo(){
	 var startTime=document.getElementById("payTimeBegin").value;
    var endTime=document.getElementById("payTimeEnd").value;
    if(startTime!='' && startTime > endTime){
	       alert("付款结束时间要大于开始时间!");
	       document.getElementById("payTimeEnd").value='';
	       return false;
    }
}
function fKuanOne(){
	 var startTime=document.getElementById("payTimeBegin").value;
    var endTime=document.getElementById("payTimeEnd").value;
    if(endTime!='' && startTime > endTime){
	       alert("付款结束时间要大于开始时间!");
	       document.getElementById("payTimeBegin").value='';
	       return false;
    }
}

  function search(ObjectForm){
 /* var vd=1;
    var startTime=document.getElementById("createTimeBegin").value;
    var endTime=document.getElementById("createTimeEnd").value;
    if(startTime!='' && endTime!='' && startTime>endTime){
	       alert("创建结束时间要大于开始时间!");
	      vd=2;
    }*/
   /* 
    var startTime=document.getElementById("payTimeBegin").value;
    var endTime=document.getElementById("payTimeEnd").value;
    if(startTime!='' && endTime!='' && startTime > endTime){
	       alert("付款结束时间要大于开始时间!");
	       vd=2;
    }*/
   $("#shopName").val(encodeURIComponent($("#shopName").val()));
   $("#contractId").val(encodeURIComponent($("#contractId").val()));
    $("#email").val(encodeURIComponent($("#email").val()));
    
   document.f1.action="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action";
	document.f1.submit();
  }
  function cancel(){
			document.getElementById('shopName').value='';
			document.getElementById('subjectId').value=0;
			document.getElementById('shops').value=0;
			document.getElementById('contractId').value='';
			document.getElementById('createTimeBegin').value='';
			document.getElementById('createTimeEnd').value='';
			document.getElementById('email').value='';
			document.getElementById('shopStatus').value=-1;
			document.getElementById('status').value=-1;
			document.getElementById('email').value='';
			document.getElementById('payTimeBegin').value='';
			document.getElementById('createTimeEnd').value='';
			document.getElementById('payTimeEnd').value='';
  }
  
  
  	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
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
	
	function getSellWayBySubId(subjectId){
		$(this).ready(function(){
		$.ajax({
			url :  "<%=contextPath%>/finance/backCashRecord!getSellWaysBySubId.action",
			data : {
				"subjectId" : subjectId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				$("#shops").html("<option value='0'>-请选择商品-</option>");
				if(result == null || result.entity == null) {
					return;
				}
				var sellWayList = result.entity;
				var html = '';
				for(var i=0; i<sellWayList.length; i++) {
					html += "<option value='" + sellWayList[i].sellId + "'>" +sellWayList[i].sellName + "</option>";
				}
				$("#shops").append(html);
			},
			error : function(error) {
				$("#shops").html("<option value='0'>-请选择商品-</option>");
				
			}
		});
		})
	}
	  function changepack(){
	  	document.getElementById('shopsname').value=$('#shops option:selected').text();
	  	document.getElementById('shopsId').value=document.getElementById('shops').value;
	  }
	function checkOutExcel()
	{
	     	document.f1.action="<%=contextPath%>/finance/backCashRecord!exportCSV.action";
			document.f1.submit();
	}
</script>
</head>
<body>
<div>

	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top">
					<font class="lists_fleft">商品销售记录表</font>
				</td>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<form name="f1"  method="post">
	        <s:hidden name="queryCashRecordCondition.currentPage" value="1"/>
	       <div class="msg-cw">
			<table width="70%" border="0" cellspacing="1" cellpadding="0" class="lists" align="center">
	  <tr>
			
			<td>商品名称：&nbsp;<input type="text" id="shopName" value="${shopName}" name="shopName"/></td>
			<td>专&nbsp;&nbsp;&nbsp;&nbsp;业：
			<s:select name="contractsubjectId" id="subjectId" onchange="getSellWayBySubId(this.value)"
												list="subjectList" listKey="subjectId"
												listValue="subjectName" headerKey="0" headerValue="--请选择专业--"
												theme="simple">
											</s:select>
			 <select style="width:153px" id="shops" name="packId" onchange="changepack()" ><option value="0">-请选择商品-</option> </select></td>
			<input type="hidden" name="contractpackName" id="shopsname"/>
			<input type="hidden" name="contractpackId" id="shopsId"/>
			</tr>
			<tr>
			<td>订单编号 ：<input type="text" name="contractId" id="contractId"  value="${contractId }"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>创建时间 ：<input type="text" name="createTimeBegin" readonly="readonly" id="createTimeBegin" 
			onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="${yestartTime}" onchange="createDateOne()" />
			-<input type="text" name="createTimeEnd" readonly="readonly" id="createTimeEnd" onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
											value="${yeendTime}" onchange="createDateTwo()"	 />
	
			
			</td>
			</tr>
			<tr>
			<td>邮箱地址 ：<input type="text" name="email" id="email" value="${email}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			
			<td>付款时间 ：<input type="text" name="payTimeBegin" value="${payTimeBegin }" readonly="readonly" id="payTimeBegin" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
											onchange="fKuanOne()"/>
		-<input type="text" name="payTimeEnd"  value="${payTimeEnd}" readonly="readonly" id="payTimeEnd" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
											onchange="fKuanTwo()" />
			
			
			</td>
			
			</tr>
			<tr>
			<td>商品状态 ：<select name="contractsshopStatus" id="shopStatus">
							 <option value="-1">-请选择-</option>
							  <option value="0">未激活</option>
							   <option value="1">已激活</option>
							    <option value="2">已延期</option>
							     <option value="3">已关闭</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			
			<td>支付状态 ：<select name="contractstatus" id="status">
							 <option value="-1">-请选择-</option>
							 <option value="0">未支付</option>
							 <option value="1">已支付</option>
							 <option value="2">已取消</option>
							 <option value="3">已退费</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>是否DS商品<select name="queryCashRecordCondition.sellType" id="sellType" value="${queryCashRecordCondition.sellType}">
					<option value="0">--请选择--</option>
											<option value="1">
												非DS商品
											</option>
											<option value="2">
												DS商品
											</option>
					</select></td>
				<td></td>
			<tr>
			</tr>
			<td colspan="2" align="center">
			<img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:search(document.f1)">查询</a>&nbsp;
			<img src="<%=contextPath%>/back/images/del_a.gif"/><a href="javascript:cancel()">清空</a>
			 	<img src="<%=contextPath%>/back/images/down16_16.gif" />
										<a href="javascript:checkOutExcel()">导出excel</a>
										<script language="JavaScript">
var contractstatus=${contractstatus};
 var contractsshopStatus=${contractsshopStatus};
	var sub=${contractsubjectId}
									if(sub=0){
										sub=-1
									}
									var subjectid=document.getElementById('subjectId');
									for(var i=0;i<subjectid.options.length;i++){
										if(subjectid.options[i].text==sub){
											subjectid.options[i].selected =true;
											isExit = true;
											break; 
										}
									}
if(contractsshopStatus==-1){
								document.getElementById('shopStatus').value=-1;
							}else{
								document.getElementById('shopStatus').value=contractsshopStatus;
							}
if(contractstatus==-1){
								document.getElementById('status').value=-1;
							}else{
								document.getElementById('status').value=contractstatus;
							}
								
var subjectIdd=$("#subjectId").val();
if(subjectIdd!=0)
{
$(this).ready(function(){
		$.ajax({
			url :  "<%=contextPath%>/finance/backCashRecord!getSellWaysBySubId.action",
			data : {
				"subjectId" : subjectIdd
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				$("#shops").html("<option value='0'>-请选择商品-</option>");
				if(result == null || result.entity == null) {
					return;
				}
				var sellWayList = result.entity;
				var html = '';
				for(var i=0; i<sellWayList.length; i++) {
					html += "<option value='" + sellWayList[i].sellId + "'>" +sellWayList[i].sellName + "</option>";
				}
				$("#shops").append(html);
				setTimeout(function() { 
             $("#shops").val("${packId}");  //进行selected选中
            });
				
				//$("#shops option[value=${packId}]").attr("selected",true);
				//document.getElementById('shops').value=${packId};
			},
			error : function(error) {
				alert(error);
				$("#shops").html("<option value='0'>-请选择商品-</option>");
				document.getElementById('shops').value=${packId};
			}
		});
		})

}
</script>
			 </td>
			</tr>
			</table>
			</div>
			</form>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>

		<tr>
			<td width="12" class="lists_bor">
			</td>
			
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						 <td>编号</td> 
						<td>商品销售记录编号</td>
						<td>商品名称</td>
						<td>商品类型</td>
						<td>订单编号</td>
						<td>所属项目</td>
						<td>原始价格</td>
						<td>成交价格</td>
						<td>支付状态</td>
						<td>商品状态</td>
						<td>用户email</td>
						<td>创建时间</td>
						<td>付款时间</td>
						<td>操作</td>
					</tr>
					<s:if test="page.pageResult.size()!=0">

						<s:iterator value="page.pageResult" id="cashrecord" status="status">
							<tr>
								 <td>
									<s:property value="(page.currentPage-1)*page.pageSize+#status.count" />
								</td> 
								<td>
									<s:property value="#cashrecord.packId"/>
								</td>
									<td>
									<s:property value="#cashrecord.packName" />
									<s:if test="#cashrecord.shopType==3">运费(<s:property value="#cashrecord.contractId" />)</s:if>
								</td>
								<td>
								 <s:if test="#cashrecord.shopType==1">视频</s:if>
							      <s:if test="#cashrecord.shopType==2">书籍</s:if>
							       <s:if test="#cashrecord.shopType==3">运费</s:if>
								</td>
								<td>
								<s:property value="#cashrecord.contractId" />
								</td>
								<td>
									<s:if test="#cashrecord.shopType==3">运费</s:if>
									<s:property value="#cashrecord.subjectName" />
								</td>
								<td>							
								   <s:property value="#cashrecord.price" />
								</td>
								<td>
					                     <s:property value="#cashrecord.cashRecordPrice" />
								</td>
								<td>
							           <s:if test="#cashrecord.status==0">未支付</s:if>
							            <s:if test="#cashrecord.status==1">已支付</s:if>
							             <s:if test="#cashrecord.status==2">取消</s:if>
							              <s:if test="#cashrecord.status==3">退费</s:if>

								</td>
								<td>
								 <s:if test="#cashrecord.shopStatus==0">未激活</s:if>
							            <s:if test="#cashrecord.shopStatus==1">已激活</s:if>
							             <s:if test="#cashrecord.shopStatus==2">已延期</s:if>
							              <s:if test="#cashrecord.shopStatus==3">已关闭</s:if>
								</td>
								<td> 
										<a href="<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=<s:property value="#cashrecord.cusId"/>">
								    	<s:property  value="#cashrecord.email"/>
								    	</a>
								</td>
							
								<td>
									<s:date name="#cashrecord.createTime"  format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<s:date name="#cashrecord.shopPayTime"  format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
								<s:if test="#cashrecord.status==1 && (#cashrecord.shopStatus==1 || #cashrecord.shopStatus==2)"><a href="<%=contextPath%>/finance/backCashRecord!closeCashbyId.action?queryCashRecordCondition.id=<s:property value="#cashrecord.id"/>&queryCashRecordCondition.cusId=<s:property value="#cashrecord.cusId"/>&queryCashRecordCondition.contractId=<s:property value="#cashrecord.contractId"/>">关闭课程</a>|</s:if><a href="<%=contextPath%>/finance/backCashRecord!getSingleCommodity.action?queryCashRecordCondition.pcId=<s:property value="#cashrecord.id"/>">详情</a>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
					<tr><td align="center" colspan="16">没有流水!</td></tr>
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
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>