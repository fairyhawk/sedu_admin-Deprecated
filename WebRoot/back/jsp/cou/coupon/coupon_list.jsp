<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
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
</script>
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">优惠券列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
						<form name="searchForm" action="<%=contextPath%>/cou/coupon!showCouponList.action" method="post">
							<s:hidden name="queryCouponCondition.currentPage" value="1"/>
							优惠券说明：
							<input type="text" value="<s:property value="queryCouponCondition.cInfo"/>" name="queryCouponCondition.cInfo"/>
						</form>
					</td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:document.searchForm.submit()">查询</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:resetKey()">清空</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:addCoupon()">添加</a></td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/del_a.gif"/></td><td><a href="javascript:delCoupon()">删除</a></td></tr></table>
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<form name="couponForm" method="post">
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td width="7%"><input type="checkbox" onclick="allCheck(this)"/>全选</td>
						<td width="8%">优惠金额</td>
						<td width="20%">优惠说明</td>
						<td width="10%">数量</td>
						<td width="10%">积分兑换数</td>
						<td width="10%">活动开始时间</td>
						<td width="10%">活动结束时间</td>
						<td width="10%">操作</td>
					</tr>
					<s:iterator value="page.pageResult" id="Coupon" status="status">
						<tr>
							<td><input type="checkbox" name="ids" value=<s:property value="id"/> /></td>
							<td>
								<s:property value="price" />
							</td>
							<td>
								<s:property value="cInfo" />
							</td>
							<td>
								<s:property value="num" />
							</td>
							<td>
								<s:property value="toScore" />
							</td>
							<td>
								<s:property value="createTime" />
							</td>
							<td>
								<s:property value="stopTime" />
							</td>
							<td>
								<a href="#" onclick="updateCoupon(<s:property value="id" />)">修改</a>
							</td>
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
</body>
</html>
