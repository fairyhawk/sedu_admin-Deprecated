<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>免费赠送课程列表</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		   function cancel(){
			   document.getElementById("userName").value="";
			   document.getElementById("s_startCreateTime").value="";
			   document.getElementById("s_endCreateTime").value=""; 
			   document.getElementById("email").value="";
		  }
		   function exportExcel()
			{
				document.getElementById("prekpointform").action="<%=contextPath%>/cou/course!exportCSV.action";
				document.getElementById("prekpointform").submit();
				document.getElementById("prekpointform").action="<%=contextPath%>/cou/course!getCouFreeGiveByPage.action?quCoFrGiCondition.currentPage=1";
			 
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
				<font class="lists_fleft"></font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td  class="lists_bor">
			</td>
			<td>
			<div class="msg-zy">													  
				<form name="prekpointform" id="prekpointform" action="<%=contextPath%>/cou/course!getCouFreeGiveByPage.action?quCoFrGiCondition.currentPage=1" method="post">
						<table width="100%" border="0"  align="center">
							<tr>
								<td width="80%">
									电子邮件：<input type="text" name="quCoFrGiCondition.email" id="email" value="${quCoFrGiCondition.email }" />
									赠送人：<input type="text" name="quCoFrGiCondition.userName" id="userName" value="${quCoFrGiCondition.userName }" />
									
									时间选择：
													<input type="text" readonly
														name="quCoFrGiCondition.startCreateTime"
														id="s_startCreateTime" value="<s:date name="quCoFrGiCondition.startCreateTime"  format="yyyy-MM-dd HH:mm:ss"/>"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"/>
													--
													<input type="text" readonly
														name="quCoFrGiCondition.endCreateTime"
														id="s_endCreateTime" value="<s:date name="quCoFrGiCondition.endCreateTime"  format="yyyy-MM-dd HH:mm:ss"/>"
														onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" onchange="startVSendTime()"/>
									
								</td>
								<td class="lists_tleft">
									<img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:cancel()">清空</a> &nbsp;&nbsp;<img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:document.prekpointform.submit()">查询</a>
									<img src="<%=contextPath%>/back/images/down16_16.gif" />
									<a href="javascript:exportExcel()">导出excel</a>
								</td>
							</tr>
						</table>
				</form>
			</div>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">免费赠送课程列表：</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		
		<tr>
			<td class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td>
							排序
						</td>
						<td>
							赠送描述
						</td>
						<td>
							电子邮箱
						</td>
						<td>
							订单号码
						</td>
						<td>
							操作人
						</td>
						<td>
							赠送时间
						</td>
					</tr>
					<s:if test="page.pageResult != null">
						<s:iterator value="page.pageResult" id="couFreeGive" status="status">
				
						<tr>
							<td>
								<s:property
														value="(page.currentPage-1)*page.pageSize+#status.count" />
							</td>
							<td>
								<s:property value="#couFreeGive.crInfo"/>
							</td>
							<td>
								<s:property value="#couFreeGive.email"/>
							</td>
							<td>
								<s:property value="#couFreeGive.ctId"/>
							</td>
							<td>
								<s:property value="#couFreeGive.userName"/>
							</td>
							<td>
								<s:date name="#couFreeGive.createTime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							
						</tr>
					</s:iterator>
					
					</s:if>
					<s:else>
							<tr>
								<td COLSPAN="5" align="center">
									没有计入
								</td>
							</tr>
					</s:else>
					
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom">
						<jsp:include page="/back/jsp/common/showPage.jsp" />
					</td>
					<td class="td_wid_r">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
        </tr>
	</table>
		<td width="16" class="lists_tright lists_bor2">
  </td>
  <td width="16" class="lists_tright lists_bor2">
  </td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">

			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
		</table>
	
</div>
	</body>
</html>
