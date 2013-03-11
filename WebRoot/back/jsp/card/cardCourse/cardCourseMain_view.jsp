<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建课程卡</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=importURL%>/uploadify/uploadify.css" />
        <script type="text/javascript" src="<%=importURL%>/uploadify/swfobject.js"></script>
        <script type="text/javascript"  src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4_headimg.js"></script>
        <script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
        <style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>
		<script type="text/javascript">
		</script>
		<style >
			body{margin-left: 10px;}
			body{ font-size:14px; color:#444444; background:#fff; font-family:"����", "Tahoma", "Helvetica Neue", Arial, Helvetica, sans-serif; }
	       .dotline {float:left;BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted;width:60%;height:1px;margin-top: 15px;margin-bottom: 10px}
	       div{padding-bottom:10px}
	       span{float:left;padding-right: 5px;width:80%}
	       .leftspan{padding-left:}
	       .border0{
	       border:0px;
	       }
	       #fileuploadUploader{float: left;}
        </style>
	</head>
	<body>
		<div>
				<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">课程卡详情</font>
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
											包含商品：
										</td>
										<td class="lists_tleft" >
			    							<ul style="list-style:none">
			    							<s:iterator value="sellWayList" id="sellWay" status="status">
			    								<li>
													<s:property value="#sellWay.sellName"/>&nbsp;&nbsp;&nbsp;价格 ：&nbsp; <s:property value="#sellWay.sellPrice"/>
												</li>
										    </s:iterator>
			    							</ul> 
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											课程卡名称：
										</td>
										<td class="lists_tleft" >
											<s:property value='cardMainModel.cardName'/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											代理商金额：
										</td>
										<td class="lists_tleft" >
											<s:property value='cardMainModel.cardMoney'/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											显示金额：
										</td>
										<td class="lists_tleft" >
											<s:property value='cardMainModel.disMoney'/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											课程卡数量：
										</td>
										<td class="lists_tleft" >
											<a href="<%=contextPath%>/card/cardMain!getCardCourseList.action?queryCardCourseCondition.currentPage=1&queryCardCourseCondition.cardMainId=<s:property value="cardMainModel.cardMainId"/>"><s:property value='cardMainModel.cardCount'/></a>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											课程卡状态：
										</td>
										<td class="lists_tleft" >
											<s:property value='cardMainModel.cardUserStatusContent'/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											有效期：
										</td>
										<td class="lists_tleft" >
											<s:date name='cardMainModel.validBeginTime' format='yyyy-MM-dd'/> - <s:date name='cardMainModel.validEndTime' format='yyyy-MM-dd' />
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											新建卡备注：
										</td>
										<td class="lists_tleft" >
											<s:property value="cardMainModel.remark" />
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											激活卡备注：
										</td>
										<td class="lists_tleft" >
											<s:property value="cardMainModel.activateRemark" />
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											验证图：
										</td>
						      			<td class="lists_tleft">
					   						<span style="float:left;">
				   								<img src="http://import.highso.org.cn/upload/shop/${cardMainModel.imageUrl}" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
					   						</span> 
					   					</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>	
		</div>
	</body>
</html>
