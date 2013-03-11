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

</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">流水列表</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			
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
						<td>售卖方式编号</td>
						<td>订单编号</td>
						<td>流水类型</td>
						<td>创建时间</td>
						<td>流水说明</td>
						<td>售卖方式名称</td>
						<td>流水金额</td>
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
								<s:property value="#cashrecord.contractId" />
								</td>
								<td>
									<s:if test="#cashrecord.type==1">
									前台购买
									</s:if>
									<s:if test="#cashrecord.type==2">
									后台赠送
									</s:if>
									<s:if test="#cashrecord.type==3">
									后台修复或赠送
									</s:if>
								</td>
								<td>
									<s:date name="#cashrecord.createTime"  format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<s:property value="#cashrecord.crInfo" />
								</td>
								<td>
									<s:property value="#cashrecord.packName" />
								</td>
								<td>
									<s:property value="#cashrecord.price" />
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
					<tr><td align="center" colspan="9">没有流水!</td></tr>
					</s:else>
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
