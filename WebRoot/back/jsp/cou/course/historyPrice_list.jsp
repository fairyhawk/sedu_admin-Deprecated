<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程历史价格表</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>

	</head>
	<body>
	
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
	
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<s:property value='course.title'/>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
				
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						
						<td>
							课程价格
						</td>

						<td>
							开始时间
						</td>

						<td>
							截至时间
						</td>
						<td>
							购买人数
						</td>
						
						<td>
							总价格
						</td>

					</tr>
					<s:iterator value="page.pageResult" id="historyPrice">
						<tr>
							
							<td>
								<s:property value="#historyPrice.price" />
							</td>
							<td>
								<s:date name='#historyPrice.creadeTime' format="yyyy-MM-dd HH:mm:ss"/>
							</td>

							<td>
								<s:if test="#historyPrice.stopTime == null">
									至今
								</s:if>
								<s:else>
									<s:date name='#historyPrice.stopTime' format="yyyy-MM-dd HH:mm:ss"/>
								</s:else>
								
							</td>
							
							<td>
								<s:property value="#historyPrice.buyNum" />
							</td>
							<td>
								<s:property value="#historyPrice.buyNum * #historyPrice.price" />元
							</td>

						</tr>
					</s:iterator>
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
