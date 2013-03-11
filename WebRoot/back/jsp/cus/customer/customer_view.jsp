<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>查看学员信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
</head>
<body>
<body>
<div>
	<form action="<%=contextPath %>/cms/comment!replyAdvice.action" method="post" name="addForm" id="addForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">查看学员信息</font>
				<font class="lists_fright">
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr height="30">
					<td width="20%">
						邮箱
					</td>
					<td class="lists_tleft">
					<a href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="customer.cusId" />">
						<s:property value="customer.email"/></a>
					</td>
				</tr>
				<tr height="30">
					<td>
						昵称
					</td>
					<td class="lists_tleft">
						<s:property value="customer.cusName"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						学员类型
					</td>
					<td class="lists_tleft">
						<s:property value="customer.cusType==1?'内部':'注册'"/>学员
					</td>
				</tr>
				<tr height="30">
					<td>
						头像
					</td>
					<td class="lists_tleft">
						<s:if test="customer.photo!=null&&customer.photo!=''">
							<img width="100" height="100" src="<%=importURL%>/upload/customer/photo/<s:property value="customer.photo"/>" />						
						</s:if>
						<s:else>
							暂未上传
						</s:else>
					</td>
				</tr>
				<tr height="30">
					<td>
						真实姓名
					</td>
					<td class="lists_tleft">
						<s:property value="customer.realName"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						手机号码
					</td>
					<td class="lists_tleft">
						<s:property value="customer.mobile"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						QQ
					</td>
					<td class="lists_tleft">
						<s:property value="customer.qq"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						MSN
					</td>
					<td class="lists_tleft">
						<s:property value="customer.MSN"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						联系方式
					</td>
					<td class="lists_tleft">
						<s:property value="customer.address"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						性别
					</td>
					<td class="lists_tleft">
						<s:property value="customer.sex==1?'男':'女'"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						生日
					</td>
					<td class="lists_tleft">
						<s:date name="customer.birthday" format="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						最近几次登陆地址及IP
					</td>
					<td class="lists_tleft">
						<s:iterator value="customer.loginRecordList" id="loginRecord" status="status">
							<s:property value="address"/>(<s:property value="loginIp"/>)&nbsp;&nbsp;时间：<s:date name="loginTime" format="yyyy-MM-dd HH:mm:ss"/><br>
						</s:iterator>
					</td>
				</tr>
				<tr height="30">
					<td>
						最近一次登陆时间
					</td>
					<td class="lists_tleft">
						<s:date name="customer.lastloginTime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						注册时间
					</td>
					<td class="lists_tleft">
						<s:date name="customer.regTime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						登陆次数
					</td>
					<td class="lists_tleft">
						<s:property value="customer.loginTimes"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						是否开启学习计划
					</td>
					<td class="lists_tleft">
						<s:property value="customer.isComplete==1?'已':'未'"/>开启
					</td>
				</tr>
				<tr height="30">
					<td>
						完善个人信息时间
					</td>
					<td class="lists_tleft">
						<s:date name="customer.completeTime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				
				<tr >
					<td>
						配送地址
					</td>
					<td class="lists_tleft">
					<table width="100%" style="background-color: #66CCFF;" border="0" cellpadding="0" cellspacing="1">
					<tr ><td width="5%">收货人</td><td width="60%">地址</td><td width="10%">邮编</td><td width="20%">电话</td></tr>
					<s:if test="addressList.size!=0">
					 <s:iterator value="addressList" id="address">
					 <tr>
	                             <td title="<s:property value="receiver"/>"><s:property value="receiver"/></td>
	                                <td><s:property value="provinceName"/>, <s:property value="cityName"/>, <s:property value="townName"/>, <s:property value="address"/></td>
	                                <td><s:property value="postCode"/></td>
	                                <td><s:property value="mobile"/></td>
                     </tr>
	               </s:iterator>
	               </s:if>
	              <s:if test="addressList.size==0">
	                <tr>
	                             <td colspan="4"> 暂无收货信息</td>
                   </tr>
	           </s:if>
	               </table>
					</td>
				</tr>
				<tr>
					<td>保过协议</td>
					<td class="lists_tleft">
						<s:if test="customer.cusProtocalDetailList.size > 0">
						<table width="100%" style="background-color: #66CCFF;" border="0" cellpadding="0" cellspacing="1">
							<tr>
								<td>姓名</td>
								<td>手机</td>
								<td>身份证</td>
								<td>地址</td>
								<td>邮编</td>
							</tr>
						<s:iterator value="customer.cusProtocalDetailList">
							<tr>
								<td><s:property value="cusName" /></td>
								<td><s:property value="mobile" /></td>
								<td><s:property value="identityCard" /></td>
								<td><s:property value="address" /></td>
								<td><s:property value="postcode" /></td>
							</tr>
						</s:iterator>
						</table>
						</s:if>
					</td>
				</tr>
				
				<tr height="30">
					<td colspan="2">
						<input type="button" value="返回" onclick="history.go(-2)"></input>
					</td>
				</tr height="30">
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
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>
