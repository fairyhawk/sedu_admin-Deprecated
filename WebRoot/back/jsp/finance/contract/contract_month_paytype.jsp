<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>订单按月统计分支付方式</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
        var hk=0,zfb=0,kq=0,wy=0,zy=0,yl=0,hd=0,zong=0;
      </script>
	</head>
	<body>
		<div>
			<form name="/finance/backContract!getMontyDayForpayType"  method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
						<td class="lists_top">
							<font class="lists_fleft">订单按月统计(支付方式)</font>
							<font class="lists_fright"> </font>
						</td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
					</tr>
					<tr>
						<td class="lists_bor"></td>
						<td>
						<div class="msg-yw">
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists">
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td style="text-align: left;">
													<div id="monthTimeDiv" style="margin: 10px 0px 10px 0px">
													<select name="years" id="years">
														<option value="2010">2010</option>
														<option value="2011">2011</option>
														<option value="2012">2012</option>
														<option value="2013">2013</option>
														<option value="2014">2014</option>
														<option value="2015">2015</option>
													</select>
													<select name="months" id="months">
														<option value="1">01</option>
														<option value="2">02</option>
														<option value="3">03</option>
														<option value="4">04</option>
														<option value="5">05</option>
														<option value="6">06</option>
														<option value="7">07</option>
														<option value="8">08</option>
														<option value="9">09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
													<input type="submit" value="查询"
														/>
													</div>	
												</td>
											</tr>
											
											<%-- <tr>
												<td style="text-align: left;">
													按项目查询商品：
													<s:select id="subjectId"
														name="queryCustomerCondition.subjectId" list="subjectList"
														listKey="subjectId" listValue="subjectName"
														headerValue="---请选择---" headerKey="-1"
														style="width: 155px">
													</s:select>
												</td>

											</tr> --%>
										</table>
									</td>
								</tr>
							</table>
							</div>
						</td>
						<td class="lists_tright lists_bor2"></td>
					</tr>
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
						<td class="lists_bottom"></td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div align="center" class="jqPlot" id="chart1" style=" width: 100%;"></div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top">
						<font class="lists_fleft"></font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					<td class="lists_bor"></td>
					<td>
					 
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
							<tr class="lists_infobg">
								<td >
									<font style="font-weight: bold">日期</font>
								</td>
								<td >
									<font style="font-weight: bold">银行汇款(元)</font>
								</td>
								<td >
									<font style="font-weight: bold">支付宝(元)</font>
								</td>
								<td >
									<font style="font-weight: bold">快钱(元)</font>
								</td>
								<td >
									<font style="font-weight: bold">网银在线(元)</font>
								</td>
								<td >
									<font style="font-weight: bold">真友(元)</font>
								</td>
								<td >
									<font style="font-weight: bold">银联在线(元)</font>
								</td>
								<td >
									<font style="font-weight: bold">货到激活(<font color="blue">不含运费</font>)(元)</font>
								</td>
								<td >
									<font style="font-weight: bold">合计(元)</font>
								</td>
							</tr>
							<s:iterator value="monthDayList" id="monthDayNew">
								<tr>
								    <td>
								   			<s:property value="#monthDayNew.day" />
									</td>
								    <td>
							             	<fmt:formatNumber value="${hk}"  pattern="#.##"  />
							             	<script type="text/javascript">hk=hk+${hk};</script>
									</td>
									<td>
											<fmt:formatNumber value="${zfb}"  pattern="#.##"  />
											<script type="text/javascript">zfb=zfb+${zfb};</script>
									</td>
									<td>
											<fmt:formatNumber value="${kq}"  pattern="#.##"  />	
											<script type="text/javascript">kq=kq+${kq};</script>								
									</td>
									<td>
											<fmt:formatNumber value="${wy}"  pattern="#.##"  />
											<script type="text/javascript">wy=wy+${wy};</script>
									</td>
									<td>
											<fmt:formatNumber value="${zy}"  pattern="#.##"  />
											<script type="text/javascript">zy=zy+${zy};</script>
									</td>
									<td>
											<fmt:formatNumber value="${yl}"  pattern="#.##"  />	
											<script type="text/javascript">yl=yl+${yl};</script>			
									</td>
									<td>
											<fmt:formatNumber value="${hd}"  pattern="#.##"  />
											<script type="text/javascript">hd=hd+${hd};</script>
									</td>
									<td>
											<fmt:formatNumber value="${zong}"  pattern="#.##"  />
											<script type="text/javascript">zong=zong+${zong};</script>
									</td>
								</tr>
							</s:iterator>
								<tr>
								    <td>
								   		总计
									</td>
								    <td>
							             <script type="text/javascript">document.write(Math.round(hk*100)/100);</script>
							             	</td>
									<td>
									<script type="text/javascript">document.write(Math.round(zfb*100)/100);</script>
									</td>
									<td>
									<script type="text/javascript">document.write((Math.round(kq*100)/100));</script>					
									</td>
									<td>
									<script type="text/javascript">document.write((Math.round(wy*100)/100));</script>
									</td>
									<td>
									<script type="text/javascript">document.write((Math.round(zy*100)/100));</script>
									</td>
									<td>
									<script type="text/javascript">document.write((Math.round(yl*100)/100));</script>
									</td>
									<td>
									<script type="text/javascript">document.write((Math.round(hd*100)/100));</script>
									</td>
									<td>
									<script type="text/javascript">document.write((Math.round(zong*100)/100));</script>
									</td>
								</tr>							
						</table>
						
					</td>
					<td class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
					<td class="lists_bottom"></td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
				</tr>
			</table>
			<script type="text/javascript" >
            $("#years").attr("value",'<s:property value="years"/>');
	        $("#months").attr("value",'<s:property value="months"/>');
            </script>
	</body>
</html>