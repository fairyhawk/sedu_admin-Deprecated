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
			document.couponForm.action = "<%=contextPath%>/cou/coupon!deleteCoupon.action?queryCouponCondition.currentPage=" + <s:property value="page.currentPage"/>;
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
		$("input[name=queryCouponCondition.cInfo]").val("");
	}
	
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
	}
	function updateState(Id,state){
		
		window.location.href="<%=contextPath%>/finance/coupon!UpdateCouponTypeByState.action?couponType.id="+Id+"&couponType.state="+state;		
	}
	function allUpdateState(state){
		var num=0;
		var check=document.getElementsByName("ids");
        for(var i=0;i<check.length;i++){
		if(check[i].checked==true)num++;
		}
        if(num==0){alert("请至少选择一条信息");return;}
		var info="";
		if(state==3)
			info="确实要冻结这些优惠券吗";
		if(state==4)
			info="确实要作废这些优惠券吗";
		if(state==1)
			info="确实要解冻这些优惠券吗";
		if(window.confirm(info)) {
			document.couponForm.action ="<%=contextPath%>/finance/coupon!UpdateCouponTypeByStateForMore.action?couponType.state="+state;
			alert('操作成功！');
			document.couponForm.submit();
			
		}
	}
	
	function yan(){
		$("#keyword").val(encodeURIComponent($("#keyword").val()));
	}
</script>
</head>
<body >
	<!-- 查询块开始 -->
	<div>		
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft"> 优惠券管理  </font> <font class="lists_fright"> </font></td>
					<td class="td_wid_l"><img
						src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					<td  class="lists_bor"></td>
					<td>
					<form name="searchForm" action="<%=contextPath%>/finance/coupon!showCouponListByWhere.action" method="post">
							<s:hidden name="queryCouponTypeCondition.currentPage" value="1"/>
							<s:hidden name="queryCouponTypeCondition.pageSize" value="20"/>
					<div class="msg-cw">	
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists">
							<tr >
                                  <td class="lists_tright" width="80">关键字 :&nbsp;&nbsp;</td>
                                  <td class="lists_tleft">
                                  &nbsp;&nbsp;
                                  <input type="text"
									name="queryCouponTypeCondition.keyword" id="keyword" value="${queryCouponTypeCondition.keyword}" /> 
									  &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;检索字段:
									<select name="queryCouponTypeCondition.searchType">
									<option value="0">-所有-</option>
									<option value="1">合作商</option>
									<!-- <option value="2">使用限额</option>
									<option value="3">生成数量</option> -->
									<option value="4">优惠券名称</option>
									<option value="5">适用范围</option>
									</select>
									  &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;状态: 
									<select name="queryCouponTypeCondition.state">
									<option value="0">-所有-</option>
									<option value="1">正常</option>
									<option value="2">过期</option>
									<option value="3">冻结</option>
									<option value="4">作废</option>
									</select>
									  &nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp; 优惠类型: 
									<select name="queryCouponTypeCondition.cType" >
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
								<input type="text" name="queryCouponTypeCondition.startTime"
									readonly id="startTime" onclick="return showCalendar('startTime','y-mm-dd')"   width="10" />-- 
									<input
									type="text" name="queryCouponTypeCondition.endTime" readonly id="endTime"
									onclick="return showCalendar('endTime', 'y-mm-dd')"
									 width="10"  />
									  &nbsp;&nbsp;  &nbsp;&nbsp;  &nbsp;&nbsp;  &nbsp;&nbsp;
									  <s:submit value="查询" onclick="yan()" />
									</td>
							</tr>							
						</table>
						</div>
						</form>
					</td>
					<td class="lists_tright lists_bor2"></td>
				</tr>
		
			<tr >
			
				<td class="lists_bor"></td>
				<td class="lists_tright">
						
						<table border="0" cellspacing="0" cellpadding="0" style="float:right">
							<tr>
								<td><img src="<%=contextPath%>/back/images/edt_tbl.gif" /></td>
								<td><a href="###" onclick="allUpdateState(4)">作废</a>  &nbsp;</td>							
								<td><img src="<%=contextPath%>/back/images/edt_tbl.gif" /></td>
								<td><a href="###" onclick="allUpdateState(3)">冻结</a>  &nbsp;</td>						
								<td><img src="<%=contextPath%>/back/images/edt_tbl.gif" /></td>
								<td><a href="###" onclick="allUpdateState(1)">解冻</a>  &nbsp;</td>						
								<td><img src="<%=contextPath%>/back/images/add_a.gif" /></td>
								<td><a href="<%=contextPath%>/finance/coupon!treadCouponNewPage.action">新建</a></td>
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
								<td width="10%">优惠券名称</td>
								<td width="5%">优惠类型</td>
								<td width="13%">合作商</td>
								<td width="5%">使用限额</td>
								<td width="10%">适用范围</td>
								<td width="8%">使用起始时间</td>
								<td width="8%">使用截止时间</td>
								<td width="10%">生成数量/使用数量/支付数量</td>
								<td width="8%">生成时间</td>
								<td width="5%">状态</td>
								<td width="5%">创建人</td>
								<td width="8%">操作</td>
							</tr>
							<%int i=0; %>
							<script>
							var i=0;
							</script>
							<s:iterator value="page.pageResult" id="Coupon">
								<tr>
									<td><input type="checkbox" name="ids" value="${id}" />${id}</td>
									<td><s:property value="title" />
									</td>

									<td><s:if test="cType==1">折扣券</s:if> <s:if test="cType==2">定额券</s:if>
									</td>
									<td>
									<s:if test="cooperativeId!=0"><s:property value="company" /></s:if>
									</td>

									<td><s:property value="leastPrice" />
									</td>
									<td><s:if test="subjectId==0">所有项目</s:if>
									<s:if test="subjectId!=0">
									<s:property value="subjectName"/>
									</s:if>
									</td>
									<td><s:date name="useTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><s:date name="stopTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><s:property value="createSum" />/<s:property
											value="useSum" />/<s:property value="paySum" />
									</td>
									<td><s:date name="generateTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><s:if test="state==1">正常</s:if> <s:if test="state==2">过期</s:if>
										<s:if test="state==3">冻结</s:if> <s:if test="state==4">作废</s:if>
									</td>
									<td>
										 <s:property value="operatingName"/>
									</td>
									<td><%-- <a href="#"
										onclick="updateCoupon(<s:property value="id" />)">修改</a> --%>
										<%i++; %>
										<a id="zf<%=i %>"   href="###"  onclick="updateState(${id},4)"><font id="zfz<%=i%>" >作废</font></a> 
									
										<a id="dj<%=i %>" href="###" onclick="updateState(${id},3)"><font id="djz<%=i%>">冻结</font></a>
									
										<a id="jd<%=i %>"  href="###" onclick="updateState(${id},1)"><font id="jdz<%=i%>">解冻</font></a>
										
										<script>
										i++;
										
										var state=<s:property value="state" />;
										if(state==1){
											$("#jd"+i).hide();
										}
										if(state==2){
											$("#zf"+i+":disabled"); $('#zf'+i).attr('onclick','');$("#zfz"+i).attr("color","#999999"); $("#dj"+i+":disabled");$('#dj'+i).attr('onclick','');$("#djz"+i).attr("color","#999999");$("#jd"+i).hide(); 
										}
										if(state==3){
											$("#zf"+i+":disabled");$('#zf'+i).attr('onclick','');$("#zfz"+i).attr("color","#999999");$("#dj"+i).hide(); 
										}
										if(state==4){
											$("#zf"+i+":disabled");$('#zf'+i).attr('onclick','');$("#zfz"+i).attr("color","#999999");$("#jd"+i).hide(); $("#dj"+i+":disabled");$('#dj'+i).attr('onclick','');$("#djz"+i).attr("color","#999999");
										}
		    							</script>
										 <a href="<%=contextPath%>/finance/coupon!selectSingleConpon.action?couponId=${id}">查看</a>
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
