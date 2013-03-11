<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券短信管理</title>
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
<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<!-- 查询块结尾 -->
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
			<tr >
				<td class="td_wid_l"><img
					src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td class="lists_top"><font class="lists_fleft" >优惠券短信管理</font> <font
					class="lists_fright">
					<a href="<%=contextPath%>/finance/coupon!sendMobileForCoupon.action" style="text-decoration:underline">发送短信</a>
						</font>
				</td>
				<td class="td_wid_l"><img
					src="<%=contextPath%>/back/images/tab_07.gif" />

				</td>
			</tr>
			<tr>
				<td width="12" class="lists_bor"></td>
				<td>
					<form name="couponForm" method="post">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info" style="word-wrap: break-word;">
							<tr class="lists_infobg">
								<td width="4%">编号</td>
								<td width="10%">短信内容</td>
								<td width="6%">发送手机号条数</td>
								<td width="6%">优惠编码条数</td>
								<td width="11%">项目名字</td>
								<td width="11%">优惠券ID</td>
								<td width="11%">发送人</td>
								<td width="11%">发送时间</td>
							</tr>	
					
							<s:iterator id="couponRecordInfo" value="page.pageResult">
								<tr>
									<td>
										<s:property value="#couponRecordInfo.id"/>
										
									</td>
									<td>
										<s:property value="#couponRecordInfo.title"/>
									</td>
									<td>
										<s:property value="#couponRecordInfo.mobileSum"/>
									</td>
									<td>
										<s:property value="#couponRecordInfo.conponSum"/>
									</td>
									<td>
										<s:property value="#couponRecordInfo.subjectName"/>
									</td>
									<td>
										<s:property value="#couponRecordInfo.couponCatorgryId"/>
									</td>
									<td>
										<s:property value="#couponRecordInfo.addName"/>
									</td>
									<td>
										 <s:date name="#couponRecordInfo.addTime" format="yyyy-MM-dd HH:mm:ss"   />
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
