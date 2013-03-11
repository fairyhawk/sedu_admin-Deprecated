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
<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar.js" ></script>  
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar-zh.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar-setup.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=contextPath%>/back/script/calendar/calendar.css" />
<script type="text/javascript">
	function delCoupon() {
		if(window.confirm("确认删除这些优惠券吗？")) {
			document.couponForm.action = "<%=contextPath%>/cou/coupon!deleteCoupon.action?queryCouponCodeCondition.currentPage=" + <s:property value="page.currentPage"/>;
			document.couponForm.submit();
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
	function updateState(Id){
		window.location.href="<%=contextPath%>/finance/coupon!UpdateCouponCodeByState.action?coupon.id="+Id+"&queryCouponCodeCondition.currentPage=1";		
	}
	function allUpdateState(){
		var num=0;
		var check=document.getElementsByName("ids");
        for(var i=0;i<check.length;i++){
		if(check[i].checked==true)num++;
		}
        if(num==0){alert("请至少选择一条信息");return;}
			info="确实要解冻这些优惠券吗";
		if(window.confirm("确实要作废这些优惠券吗")) {
			document.couponForm.action ="<%=contextPath%>/finance/coupon!UpdateCouponCodeByStateMore.action?queryCouponCodeCondition.currentPage=1";
			alert('操作成功！');
			document.couponForm.submit();
			
		}
	}
	function exportcsv(){
	document.getElementById("searchForm").action="<%=contextPath%>/finance/coupon!exportCSV.action";
	document.getElementById("searchForm").submit();
	document.getElementById("searchForm").action="<%=contextPath%>/finance/coupon!showCouponCodeListByWhere.action";
	}
	function yan(){
		$("#keyword").val(encodeURIComponent($("#keyword").val()));
	}
</script>
</head>
<body>
	<!-- 查询块开始 -->
	<div>		
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft"> 优惠券编码管理</font> <font class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					<td width="12" class="lists_bor"></td>
					<td>
						<div class="msg-cw">	
					<form name="searchForm" id="searchForm" action="<%=contextPath%>/finance/coupon!showCouponCodeListByWhere.action" method="post">
							<s:hidden name="queryCouponCodeCondition.currentPage" value="1"/>
							<s:hidden name="queryCouponCodeCondition.pageSize" value="20"/>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists">
							<tr >
                                  <td class="lists_tright" width="80">关键字 ：</td>
                                  <td class="lists_tleft">
                                  &nbsp;&nbsp;
                                  <input type="text"
									name="queryCouponCodeCondition.keyword" id="keyword" value="${queryCouponCodeCondition.keyword}" /> 
									  &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;检索字段：
									<select name="queryCouponCodeCondition.searchType">
									<option value="0">-所有-</option>
									<option value="1">优惠编码</option>
										<option value="2">优惠券名称</option>
									<option value="3">合作商</option>
									<option value="4">适用范围</option>
									</select>
									  &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;优惠编码状态: 
									<select name="queryCouponCodeCondition.state">
									<option value="0">-所有-</option>
									<option value="1">未使用</option>
									<option value="2">已使用</option>
									<option value="3">过期</option>
									<option value="4">作废</option>
									</select>
									  &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp; 支付状态: 
									<select name="queryCouponCodeCondition.payState" >
									<option value="0">-所有-</option>
									<option value="1">未支付</option>
									<option value="2">已支付</option>
									</select>
									  &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp; 优惠类型: 
									<select name="queryCouponCodeCondition.cType" >
									<option value="0">-所有-</option>
									<option value="1">折扣优惠券</option>
									<option value="2">定额优惠券</option>
									</select>
									</td>
							</tr>
							<tr>
							    <td class="lists_tright">生成时间：</td>
								<td class="lists_tleft">
								  &nbsp;&nbsp;
								<input type="text" name="queryCouponCodeCondition.startTime" 
									readonly id="startTime"onclick="return showCalendar('startTime','y-mm-dd')"   width="10" />-- 
									<input
									type="text" name="queryCouponCodeCondition.endTime" readonly id="endTime"
									onclick="return showCalendar('endTime','y-mm-dd')" 
									 width="10"  />
									  &nbsp;&nbsp;  &nbsp;&nbsp;  &nbsp;&nbsp;  &nbsp;&nbsp;
									  <s:submit value="查询" onclick="yan()" />
									</td>
							</tr>							
						</table>
						</form>
						</div>
					</td>
					<td width="16" class="lists_tright lists_bor2"></td>
				</tr>
			
			
			<tr >
				<td class="lists_bor"></td>
				<td class="lists_tright">
						
						<table border="0" cellspacing="0" cellpadding="0" style="float:right">
							<tr>
								<td><img src="<%=contextPath%>/back/images/edt_tbl.gif" /></td>
								<td><a href="###" onclick="allUpdateState()">作废</a>
								
								<td><img src="<%=contextPath%>/back/images/edt_tbl.gif" /></td>
								<td><a href="###" onclick="exportcsv()">导出</a>
								</td>
							</tr>
						</table>
					
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td width="12" class="lists_bor"></td>
				<td>
					<form name="couponForm" method="post">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info" style="word-wrap: break-word;">
							<tr class="lists_infobg">
								<td width="4%"><input type="checkbox"
									onclick="allCheck(this)" />ID号</td>
								<td width="12%">优惠编码</td>
								<td width="4%">优惠券名称</td>
								<td width="5%">优惠券类型</td>
								<td width="10%">合作商</td>
								<td width="5%">使用限额</td>
								<td width="10%">适用范围</td>
								<td width="8%">使用起始时间</td>
								<td width="8%">使用截止时间</td>
							    <td width="8%">生成时间</td>
							    <td width="5%">优惠编码状态</td>
								<td width="5%">支付状态</td>
								<td width="5%">创建人</td>
								<td width="5%">操作</td>
							</tr>
					
							<s:iterator value="page.pageResult" id="CouponCode">
								<tr>
									<td><input type="checkbox" name="ids" value="${id}" />${id}</td>
									<td><s:property value="title" /></td>
                                     <td><s:property value="couponType.title" /></td>
									<td><s:if test="couponType.cType==1">折扣券</s:if> <s:if test="couponType.cType==2">定额券</s:if>
									</td>
									<td>
									<s:if test="cooperativeId!=0"><s:property value="couponType.company" /></s:if>
									</td>

									<td><s:property value="couponType.leastPrice" />
									</td>
									<td><s:if test="couponType.subjectId==0">所有项目</s:if>
									<s:if test="couponType.subjectId!=0">
									<s:property value="couponType.subjectName" />
									</s:if>
									</td>
									<td><s:date name="couponType.useTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
									<s:date name="couponType.stopTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
									<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><s:if test="state==1">未使用</s:if> <s:if test="state==2">已使用</s:if>
										<s:if test="state==3">过期</s:if> <s:if test="state==4">作废</s:if>
									</td>
									
									</td>
									<td><s:if test="paystate==1">未支付</s:if> <s:if test="paystate==2">已支付</s:if>
									</td>
									<td> <s:property value="operatingName"/>
									</td>
									<td>
										<s:if test="state==1">
										<a href="###" onclick="updateState(${id})">作废</a> 		
										</s:if>																									
										 <a href="<%=contextPath%>/finance/coupon!couponCodeXX.action?couponId=${id}">查看</a>
 								</td>
								</tr>
							</s:iterator>
						</table>
					</form>
				</td>
				<td width="16" class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td><img src="<%=contextPath%>/back/images/tab_18.gif" />
				</td>
				<td class="lists_bottom"><jsp:include
						page="/back/jsp/common/showPage.jsp" /></td>
				<td><img src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
