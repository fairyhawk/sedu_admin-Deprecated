<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建发票</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript">
		 function backPurseRecordList(){
			 $("#purseRecordform").attr("action","<%=contextPath%>/purse/purseRecord!getIPurseRecordList.action?queryRecordCondition.currentPage=1");
			 $("#purseRecordform").submit();
		 }
		</script>
	</head>
	<body>
		<div>
			<form action="" method="post" name="purseRecordform" id="purseRecordform" enctype="multipart/form-data">
			<input type="hidden" name="waybill.waybillId" id="waybillId" value="<s:property value="waybill.waybillId"/>"/>
				<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">交易记录 &gt; &gt;交易详情-<s:property value="record.recordId"/> </font>
							</font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12"  class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="tblList">
								<tbody id="tagTb">
									<tr>
										<td class="lists_tright" width="10%">
											交易编号：
										</td>
										<td class="lists_tleft" >
											&nbsp;<s:property value="record.recordId"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											用户账号：
										</td>
										<td class="lists_tleft">
											&nbsp;<s:property value="record.userAccount"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											收支方向：
										</td>
										<td class="lists_tleft">
											&nbsp;<s:property value="record.szStatusName"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											金额（元）：
										</td>
										<td class="lists_tleft">
											&nbsp;<s:property value="record.money"/>（元）
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											交易方式：
										</td>
										<td class="lists_tleft">
											&nbsp;<s:property value="record.payTypeName"/>
											<s:if test="record.bank!=''">
												&nbsp;<s:property value="record.bank"/>
											</s:if>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											交易类型：
										</td>
										<td class="lists_tleft">
											&nbsp;<s:property value="record.recordTypeName"/>
											<s:if test="record.orderCode!=''">
												<br/>
												&nbsp;订单编号：<a href="<%=contextPath%>/finance/backCashRecord!getCashRecordList.action?queryCashRecordCondition.contractId=<s:property value='record.orderCode'/>"><s:property value="record.orderCode"/></a>
											</s:if>
										</td>
									</tr>	
									<tr>
										<td class="lists_tright" width="10%">
											操作人：
										</td>
										<td class="lists_tleft">
											&nbsp;<s:property value="record.creator"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											创建时间：
										</td>
										<td class="lists_tleft">
											&nbsp;<s:date name="record.createTime" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											交易备注：
										</td>
									
										<td class="lists_tleft">
											<textarea style="height:80px;width:30%;" name="record.remark"  id="remark"><s:property value="record.remark" escape="true" /></textarea>
										</td>
									</tr>	
									<tr>
										<td colspan="2">
											<input  type="button" onclick="backPurseRecordList()" value="返回列表页"/>
										</td>
									</tr>
							</table>
						</td>
					</tr>	
			</form>
		</div>
	</body>
</html>
