<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript"
src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
//选择专业时候，老师跟着变；
function onchangeTeacher(pId){
					$.ajax({  
					url : "<%=contextPath%>/cou/sellway!getTeacherByName.action",  
					data : {subjectId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					} 
					});  
				
				}
			function onchangecallbackteahcer(result){//老师变动
				document.getElementById('teacherServlet').options.length = 0;  //清空原有的option 
				
				var str="<option value=''>--请选择--</option>";  
				var teacherServletval='${querySellWayCondition.teacher}';
				for(var i=0;i<result.entity.length;i++){  
					if(teacherServletval==result.entity[i].name)
					{
					str+="<option value='"+result.entity[i].tcId+"' selected='true'>"+result.entity[i].name+"</option>"; 
					}else{
						str+="<option value='"+result.entity[i].tcId+"'>"+result.entity[i].name+"</option>"; 
					}
				}  
				//$("#teacherServlet").html("<option value=-1>请选择</option>");
			//	$("teacherServlet").value="--请选择--"
				$("#teacherServlet").append(str);  
				  $("#teacherServlet option[text='周老师']").attr("selected","true");
				}
	function delCoupon(sellName,sellId) {
		var bb=sellId;
		if(window.confirm("请注意：你将要删除商品【"+sellName+"】,删除后将不能恢复，请确认删除？")) {
			document.sellWayForm.action = "<%=contextPath%>/cou/sellway!delSellWay.action?sellWay.sellId="+Number(bb);
			document.sellWayForm.submit();
			return true;
		}else
		{
			return false;
		}
	}
	function addCoupon() {
		window.location.href = "<%=contextPath%>/cou/coupon!initAddCoupon.action";
	}
	
	function updateCoupon(id) {
		window.location.href = "<%=contextPath%>/cou/coupon!initUpdateCoupon.action?coupon.id=" + id;
	}
	
	function resetKey() {
		$("input[name=queryCouponCodeCondition.cInfo]").val("");
	}
	
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
	}
	function allUpdateState(state){
		var num=0;
		var check=document.getElementsByName("ids");
		alert(check.length);
        for(var i=0;i<check.length;i++){
		if(check[i].checked==true)num++;
		}
        if(num==0){alert("请至少选择一条信息");return;}
		var info="";
		if(state==2)
			info="确实要删除这些商品吗";
		if(state==0)
			info="确实要解冻这些商品吗";
		if(state==1)
			info="确实要冻结这些商品吗";
		if(window.confirm(info)) {
			document.sellWayForm.action ="<%=contextPath%>/cou/sellway!UpdateCouponTypeByStateForMore.action?sellWay.status="+state;
			alert('操作成功！');
			document.sellWayForm.submit();
			
		}
	}
	function exportcsv(){
	document.getElementById("searchForm").action="<%=contextPath%>/cou/sellway!exportCSV.action";
	document.getElementById("searchForm").submit();
	document.getElementById("searchForm").action="<%=contextPath%>/cou/sellway!showSellWayListByWhere.action?querySellWayCondition.currentPage=1";
	
	}
	function yan(){
		if(getYanMessage()!=""){
			alert(getYanMessage());
			return false;
		}
		$("#keyword").val(encodeURIComponent($("#keyword").val()));
	}
	function getYanMessage(){
		var message="";
		if((new Date($("#upShelveBeginTime").val().replace(/-/g,"/")))>(new Date($("#upShelveEndTime").val().replace(/-/g,"/"))))
		{
			message +="上架开始时间不能大于上架结束时间！\n";
		}
		if((new Date($("#downShelveBeginTime").val().replace(/-/g,"/")))>(new Date($("#downShelveEndTime").val().replace(/-/g,"/"))))
		{
			message +="下架开始时间不能大于下架结束时间！\n";
		}
		if((new Date($("#beganSellTimeBegin").val().replace(/-/g,"/")))>(new Date($("#beganSellTimeEnd").val().replace(/-/g,"/"))))
		{
			message +="开销开始时间不能大于开销结束时间！\n";
		}
		if((new Date($("#stopSellTimeBegin").val().replace(/-/g,"/")))>(new Date($("#stopSellTimeEnd").val().replace(/-/g,"/"))))
		{
			message +="开销开始时间不能大于开销结束时间！\n";
		}
		return message;
	}
	function updateSellWayFreeze(sellId,status) {
			var bb=sellId;
			var cc=status;
			//document.sellWayForm.action = "<%=contextPath%>/cou/sellway!updateSellWayFreeze.action?sellWay.sellId=<s:property value="#sellWaytemp.sellId"/>+'"sellWaytemp.status=<s:property value="#sellWaytemp.sellId"/>"'";
			document.sellWayForm.action = "<%=contextPath%>/cou/sellway!updateSellWayFreeze.action?sellWay.sellId="+Number(bb)+"&sellWay.status="+Number(cc);
			document.sellWayForm.submit();
			return true;
	}
	function onChangStatus(){
		document.getElementById('checkStatusValue').value='';
		var status =document.getElementById('shopState').value;
		document.getElementById('checkStatusValue').value=Number(status);
		
	}
	function selteacher(){
		$("#teacherhidden").val(encodeURIComponent($("#teacherServlet").find('option:selected').text()));
	}
	
	function updateShopState(){
		var num=0;
		var check=document.getElementsByName("ids");
        for(var i=0;i<check.length;i++){
		if(check[i].checked==true)num++;
		}
        if(num==0){alert("请至少选择一条信息");return;}
		var info="确实要上架这些商品吗";
		if(window.confirm(info)) {
			document.sellWayForm.action ="<%=contextPath%>/cou/sellway!updateShopState.action";		
			document.sellWayForm.submit();
		}
	}
	

</script>
</head>
<body onload="myload()">
	<!-- 查询块开始 -->
	<div>		
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft"> 商品管理</font> <font class="lists_fright"> </font></td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					  <td class="lists_bor"></td>
	            <td>
	              <div class="msg-zy">
					<form name="searchForm" id="searchForm" action="<%=contextPath%>/cou/sellway!showSellWayListByWhere.action?querySellWayCondition.currentPage=1" method="post">
					<input type="hidden" name="querySellWayCondition.teacher" id="teacherhidden" />
						<table class="" border="0" cellspacing="0"  cellpadding="0" width="80%">
							<tr>
								<td >上架时间:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<input type="text" readonly="readonly" name="querySellWayCondition.upShelveBeginTime" id="upShelveBeginTime"
                                    	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="querySellWayCondition.upShelveBeginTime" format="yyyy-MM-dd HH:mm:ss"/>" />
                                	 &nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
                                 	<input type="text" readonly="readonly" name="querySellWayCondition.upShelveEndTime" id="upShelveEndTime"
                                     	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="querySellWayCondition.upShelveEndTime" format="yyyy-MM-dd HH:mm:ss"/>" />
								 </td>
								 <td >下架时间:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<input type="text" readonly="readonly" name="querySellWayCondition.downShelveBeginTime" id="downShelveBeginTime"
                                    	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="querySellWayCondition.downShelveBeginTime" format="yyyy-MM-dd HH:mm:ss"/>" />
                                	 &nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
                                 	<input type="text" readonly="readonly" name="querySellWayCondition.downShelveEndTime" id="downShelveEndTime"
                                     	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="querySellWayCondition.downShelveEndTime" format="yyyy-MM-dd HH:mm:ss"/>" />
								 </td>
							</tr>
							<tr>
								<td >开销时间:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<input type="text" readonly="readonly" name="querySellWayCondition.beganSellTimeBegin" id="beganSellTimeBegin"
                                    	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="querySellWayCondition.beganSellTimeBegin" format="yyyy-MM-dd HH:mm:ss"/>" />
                                	 &nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
                                 	<input type="text" readonly="readonly" name="querySellWayCondition.beganSellTimeEnd" id="beganSellTimeEnd"
                                     	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="querySellWayCondition.beganSellTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>" />
								 </td>
								 <td >停销时间:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<input type="text" readonly="readonly" name="querySellWayCondition.stopSellTimeBegin" id="stopSellTimeBegin"
                                    	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" value="<s:date name="querySellWayCondition.stopSellTimeBegin" format="yyyy-MM-dd HH:mm:ss"/>" />
                                	 &nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp;
                                 	<input type="text" readonly="readonly" name="querySellWayCondition.stopSellTimeEnd" id="stopSellTimeEnd"
                                     	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"  value="<s:date name="querySellWayCondition.stopSellTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>" />
								 </td>
							</tr>
							<tr>
								<td >是否在售:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<select name="querySellWayCondition.isSelling" id="isSelling" style="width:40%">
										<option value="">全部</option>
										<option value="1" <s:if test="querySellWayCondition.isSelling==1">selected</s:if>>在售</option>
										<option value="2" <s:if test="querySellWayCondition.isSelling==2">selected</s:if>>停售</option>
									</select>
								 </td>
								 <td >上下架:</td>
								 <td valign="middle" align="left" colspan="5" style="text-align:left">
									<select name="querySellWayCondition.shopState" id="shopState" style="width:40%">
										<option value=""  >全部</option>
										<option value="1" <s:if test="querySellWayCondition.shopState==1">selected</s:if>>上架</option>
										<option value="0" <s:if test="querySellWayCondition.shopState==0">selected</s:if>>下架</option>
									</select>
								 </td>
							</tr>
							<tr>
								<td >项目专业:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<s:select list="subjectList" listKey="subjectId"   id="subjectList"
										listValue="subjectName"    value="querySellWayCondition.subjectId"
											onchange="onchangeTeacher(this.value)" theme="simple" name="querySellWayCondition.subjectId"
												headerKey="-1" headerValue="---请选择---"  style="width:40%">
									</s:select>
								 </td>
								 <td >商品状态:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<select name="querySellWayCondition.status" id="status" style="width:40%">
										<option value="" selected="selected" >全部</option>
										<option value="0" <s:if test="querySellWayCondition.shopState==0">selected</s:if>>正常</option>
										<option value="1" <s:if test="querySellWayCondition.shopState==1">selected</s:if>>冻结</option>
									</select>
								 </td>
							</tr>
							<tr>
								<td >主讲教师:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<input type="text" name="querySellWayCondition.teacherName" id="teacherName" value="${querySellWayCondition.teacherName}" style="width:62%"/>
								 </td>
								 <td >关键字:</td>
								 <td valign="middle" align="left" colspan="5" style="text-align:left">
									<input type="text" name="querySellWayCondition.word" id="keyword" value="${querySellWayCondition.word}" style="width:62%"/>
									
								 </td>
							</tr>
							
							<tr>
								<td>是否保过:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<select name="querySellWayCondition.isProtocal" style="width:40%">
										<option value="-1" selected="selected">全部</option>
										<option value="1" <s:if test="querySellWayCondition.isProtocal==1">selected</s:if>>是</option>
										<option value="0" <s:if test="querySellWayCondition.isProtocal==0">selected</s:if>>否</option>
									</select>
								</td>
								<td>所属类别:</td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<s:select name="querySellWayCondition.sellType" list="#{'-1':'全部', '1':'项目商品', '2':'DS商品', '3':'内部商品'}" cssStyle="width:40%" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
								<td></td>
								<td valign="middle" align="left" colspan="5" style="text-align:left">
									<s:submit value="查询" onclick="return yan();" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
				
			<tr>
				<td class="lists_bor"></td>
				<td class="lists_top">
					<font class="lists_fleft">商品列表</font>
					<font class="lists_fright">
						<img src="<%=contextPath%>/back/images/add_a.gif" /><a href="<%=contextPath%>/cou/sellway!toAddSellWay.action?path=<s:property value="pageUrlParms.replace('&','^')"/>">新建</a>
						<img src="<%=contextPath%>/back/images/edt_tbl.gif" /><a href="###" onclick="updateShopState(0)">上架</a>
						<img src="<%=contextPath%>/back/images/edt_tbl.gif" /><a href="###" onclick="allUpdateState(2)">删除</a>
						<img src="<%=contextPath%>/back/images/edt_tbl.gif" /><a href="###" onclick="allUpdateState(1)">冻结</a>
						<%-- <img src="<%=contextPath%>/back/images/edt_tbl.gif" /><a href="###" onclick="allUpdateState(0)">解冻</a> --%>
						<!-- 导出 -->
						<img src="<%=contextPath%>/back/images/edt_tbl.gif" /><a href="###" onclick="exportcsv()">导出数据表</a>
					</font>
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="lists_bor"></td>
				<td>
					<form name="sellWayForm" method="post">
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr class="lists_infobg">
								<td width="2%"><input type="checkbox"
									onclick="allCheck(this)" />ID</td>
								<td  width="8%">商品名称</td>
								<td  width="6%">所属项目</td>
								<td  width="6%">主讲教师</td>
								<td  width="2%">课时</td>
								<td  width="4%">价格</td>
								<td width="4%">创建时间</td>
								<td width="4%">上架时间</td>
								<td width="4%">下架时间</td>
								<td width="4%">上下架状态</td>
								<td width="4%">开售时间</td>
								<td width="4%">收入时间</td>
								<td width="4%">停销时间</td>
								<td width="4%">销售状态</td>	
								<td width="2%">状态</td>
								<td width="2%">保过</td>
								<td width="4%">更新时间</td>
								<td width="3%">创建人</td>
								<td width="8%">操作</td>
							</tr>	
					
							<s:iterator id="sellWaytemp" value="page.pageResult">
								<tr>
									<td>
										<input type="checkbox" name="ids" value='<s:property value="#sellWaytemp.sellId"/>' />
										<s:property value="#sellWaytemp.sellId"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.sellName"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.subjectName"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.teacher"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.lessonNumber"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.sellPrice"/>
									</td>
									<td>
										<s:date name="#sellWaytemp.addTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:date name="#sellWaytemp.upShelveTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:date name="#sellWaytemp.downShelveTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:if test="#sellWaytemp.shopState==1">
											上架
										</s:if>
										<s:if test="#sellWaytemp.shopState==0">
											下架
										</s:if>
									</td>
									<td>
										<s:date name="#sellWaytemp.beganSellTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:date name="#sellWaytemp.confirmTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									
									<td>
										<s:date name="#sellWaytemp.stopSellTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.sellStatus"/>
									</td>
									<!-- 新添加结束 -->
									<td>
										<s:if test="#sellWaytemp.status==0">
											正常
										</s:if>
										<s:if test="#sellWaytemp.status==1">
											冻结
										</s:if>
									</td>
									<td>
										<s:if test="#sellWaytemp.isProtocal==true">
											是
										</s:if>
										<s:else>
											否
										</s:else>
									</td>
									<td>
										<s:date name="#sellWaytemp.updateTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.updateUser"/>
									</td>
									<td>
										<a href='<%=contextPath%>/cou/sellway!toSellWayInfo.action?sellWay.sellId=<s:property value="#sellWaytemp.sellId"/>'>查看</a>
																		| <a href='<%=contextPath%>/cou/sellway!toUpdateSellWay.action?sellWay.sellId=<s:property value="#sellWaytemp.sellId"/>&path=<s:property value="pageUrlParms.replace('&','^')"/>'>修改</a>	
											| <a href="###"  onclick="delCoupon('${sellWaytemp.sellName}','${sellWaytemp.sellId}')">删除</a>
										|
										<s:if test="#sellWaytemp.status==1">
											<a href="###" onclick="updateSellWayFreeze('${sellWaytemp.sellId}','${sellWaytemp.status}')">解冻</a>
										</s:if>
										<s:if test="#sellWaytemp.status==0">
											<a href="###" onclick="updateSellWayFreeze('${sellWaytemp.sellId}','${sellWaytemp.status}')">冻结</a>
										</s:if>
									<!-- 	| <a href='<%=contextPath%>/cou/sellway!toAddCourseForSellWay.action?sellWay.sellId=<s:property value="#sellWaytemp.sellId"/>'>添加或者删除课程</a> -->
									</td>
									
								</tr>			
							</s:iterator>
						</table>
					</form>
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif"/></td>
				<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
				<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
			</tr>
		</table>
	</div>
	<script type="">
function myload()
{
       $("#shopState").val('${querySellWayCondition.shopState}');
       $("#status").val('${querySellWayCondition.status}');
       var subjectId=$('#subjectList').val();
       onchangeTeacher(subjectId);
}
</script>
</body>
</html>