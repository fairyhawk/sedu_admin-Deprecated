<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户卡详细</title>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<link  media="screen" type="text/css" rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">

.userCardmian {	border: 1px solid #BFC6CF; width:921px; margin-left:24px;}
.userCardtop {	background: #f8f8f8;height:33px;border-bottom: 1px solid #BFC6CF;font-size:14px;font-weight:bold;padding-left:20px;line-height:33px;margin-bottom:20px;}
.chuangjianxinxi {font-size: 14px;color: #069;font-weight:bold;padding:24px 24px 10px 24px;}

.xiaoxikuang {font-size: 12px;border: 1px 1 #CCCCCC;padding:24px 24px 10px 24px;}
.hr{margin:24px 24px 10px 24px;font-size:1px;border-top:#BFC6CF solid 1px}
</style>
</head>

<body>





<div class="userCardmian">
<div class="userCardtop">用户档案卡 - <s:property value="usersList[0].mobile" /> </div>
<!-- 创建信息-->
<div class="chuangjianxinxi">创建信息</div>
 
 <s:if test="usersList != null">
 <s:iterator value="usersList" id="users">
 <hr  class="hr" size="1"/>
<div class="xiaoxikuang"><table width="100%" border="0" cellspacing="10">
  <tr>
    <td width="50">&nbsp;</td>
    <td align="right">用户ID：</td>
    <td align="left"><s:property value="#users.id" /></td>
    <td align="right">移动电话：</td>
    <td align="left"><s:property value="#users.mobile" /></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="50">&nbsp;</td>
    <td align="right">创建时间：</td>
    <td align="left"><s:date name="#users.regTime" format="yyyy-MM-dd HH:mm:ss" /></td>
    <td align="right">创建来源：</td>
    <td align="left">
    	<s:if test="#users.userType == 1"> 自然注册 </s:if>
        <s:elseif test="#users.userType == 2"> 乐语在线 </s:elseif>
        <s:elseif test="#users.userType == 3"> 乐语转注册 </s:elseif>
        <s:elseif test="#users.userType == 4"> 自然留言 </s:elseif>
        <s:elseif test="#users.userType == 5"> 留言转注册 </s:elseif>
	</td>
    <td>&nbsp;</td>
  </tr>
</table>
</div>

</s:iterator>
</s:if>

<!-- 注册信息-->
<div class="chuangjianxinxi">基本信息</div>

<hr  class="hr" size="1"/>
<div class="xiaoxikuang">
<table width="100%" border="0" cellspacing="10">
  <tr>
    <td width="10%">&nbsp;</td>
    <td align="right" width="10%">姓名：</td>
    <td align="left" width="10%"><s:property value="user.realName" /></td>
    <td align="right" width="10%">生日：</td>
    <td align="left" width="10%"><s:property value="user.birthday" /></td>
    <td align="right" width="10%">性别：</td>
    <td align="left" width="10%">
    	<s:if test="user.sex == 1">女 </s:if>
        <s:elseif test="user.sex == 2">男</s:elseif>
    </td>
    <td align="right" width="10%">职位：</td>
    <td align="left"><s:property value="user.profession" /></td>
  </tr>

  <tr>
  	<td>&nbsp;</td>
    <td align="right">地址：</td>
    <td align="left" colspan="7"><s:property value="user.address" /></td>
  </tr>

  <tr>
    <td>&nbsp;</td>
    <td align="right" valign="top">备注：</td>
    <td colspan="7" align="left"><div style="width:400px; height:100px; border:#CCCCCC solid 1px; background:#FFFFFF; color: black;">
    <s:property value="user.remarks!=null?user.remarks:'无'" />
    </div></td>
   </tr>
</table>

</div>
<br />
<!-- 注册信息-->
<div class="chuangjianxinxi">注册信息</div>

<s:if test="customerList != null">
<s:iterator value="customerList" id="customer">
 <hr  class="hr" size="1"/>
<div class="xiaoxikuang"><table width="100%" border="0" cellspacing="10">
  <tr>
    <td width="50">&nbsp;</td>
    <td align="right">账号：</td>
    <td align="left"><s:property value="#customer.email"/></td>
    <td align="right">创建时间：</td>
    <td align="left"><s:date name="#customer.regTime" format="yyyy-MM-dd HH:mm:ss" /></td>
    <td align="right">域名来源：</td>
    <td align="left"><s:property value="#customer.cusFromUrl"/></td>
    <td align="right">来源路径：</td>
    <td align="left">
    	<s:if test="subjectList != null">
            <s:iterator value="subjectList" id="subject">
                <s:if test="#customer.subjectId == #subject.subjectId">
                  <s:property value="#subject.subjectName"/>
                </s:if>
             </s:iterator>
       	</s:if>
    </td>
  </tr>
</table>
</div>
</s:iterator>
</s:if>

<!-- 购买信息-->
<div class="chuangjianxinxi">购买信息</div>
 <hr  class="hr" size="1"/>
<div class="xiaoxikuang"><br />
<s:if test="contractDTOList != null">
<s:iterator value="contractDTOList" id="contract" status="strat">
	<div  style="font-weight:bold; padding-left:24px; margin-top:20px;">订单<s:property value="#strat.count"/></div>
	<div style="border:#0000CC  solid 2px; margin-left:25px; margin-top:10px; margin-right:25px; margin-bottom:10px;">
	<table width="100%" border="0" cellspacing="10">
	  <tr>
	    <td width="50">&nbsp;</td>
	    <td align="right">订单编号：</td>
	    <td align="left"><s:property value="#contract.contractId"/></td>
	    <td align="right">下单账号：</td>
	    <td align="left"><s:property value="#contract.email"/></td>
		<td align="right">下单时间：</td>
	    <td align="left"><s:date name="#contract.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	  </tr>
	  <tr>
	    <td width="50">&nbsp;</td>
	    <td align="right">订单金额：</td>
	    <td align="left"><s:property value="#contract.contractCutSumMoney"/></td>
	    <td align="right">支付方式：</td>
	    <td align="left">
	    	<s:if test="#contract.payType == 0"> 赠送 </s:if>
            <s:elseif test="#contract.payType == 1"> 支付宝 </s:elseif>
            <s:elseif test="#contract.payType == 2"> 货到付款 </s:elseif>
            <s:elseif test="#contract.payType == 3"> 网银在线 </s:elseif>
            <s:elseif test="#contract.payType == 4"> 块钱 </s:elseif>
            <s:elseif test="#contract.payType == 5"> 代理商开通 </s:elseif>
	    </td>
		<td align="right">订单状态：</td>
	    <td align="left">
	    	<s:if test="#contract.status == 0"> 未激活 </s:if>
            <s:elseif test="#contract.status == 1"> 已激活 </s:elseif>
            <s:elseif test="#contract.status == 3"> 已取消 </s:elseif>
            <s:elseif test="#contract.status == 4"> 退费 </s:elseif>
	    </td>
	  </tr>
	  
	 <tr>
	    <td width="50">&nbsp;</td>
	    <td align="right">付款时间：</td>
	    <td align="left"><s:date name="#contract.payTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	    <td align="right">订单商品：</td>
	    <td align="left" colspan="3"><s:property value="#contract.crInfo"/></td>
	  </tr>
	  
	</table>
	
	<br />
	</div>
	<br />
</s:iterator>
</s:if>
</div>
<br />

<!-- 销售机会信息-->
<div class="chuangjianxinxi">销售机会信息</div>
 <hr  class="hr" size="1"/>
<div class="xiaoxikuang"><br />
<div style="padding:10px 0;">销售机会</div>
<div>
<table width="100%" cellspacing="1" cellpadding="0" border="0"  class="lists_info lists">
  <tr>
    <td class="lists_tright" width="10%">机会状态：</td>
    <td class="lists_tleft">&nbsp;
    	<s:if test="contractNumPay > 0"> 已购买 </s:if>
       	<s:elseif test="contractNumPay == 0&&contractNum > 0"> 未付款 </s:elseif>
        <s:elseif test="contractNum == 0"> 已注册 </s:elseif>
    </td>
    <td class="lists_tright" >用户状态：</td>
    <td class="lists_tleft">&nbsp;
		<s:if test="chance.followStatus == 1"> 放弃 </s:if>
		<s:elseif test="chance.followStatus == 2"> 跟踪 </s:elseif>
		<s:elseif test="chance.followStatus == 3"> 热点 </s:elseif>
		<s:elseif test="chance.followStatus == 4"> 成交 </s:elseif>
    </td>
	<td class="lists_tright" >用户账号：</td>
    <td class="lists_tleft">&nbsp;<s:property value="user.email"/></td>
  </tr>  
</table>
		<s:if test="recordList != null">
		<s:iterator value="recordList" id="record" status="status">
  	
  		<table width="100%" cellspacing="1" cellpadding="0" border="0" onmouseout="changeback()" onmouseover="changeto()" class="lists_info" style="margin-top:-1px;">
  			<tr align="center" style="height: 30px;">
  				<td rowspan="2" width="15"><b>
  					<s:if test = "chance.consultStatus == 1">未联系 </s:if>
              		<s:elseif test="chance.consultStatus == 2">首次 </s:elseif>
              		<s:elseif test="chance.consultStatus == 3">回访 </s:elseif>
					</b>
  				</td>
  				<td width="10%"><b>序号</b></td>
  				<td width="35%"><b>关注点</b></td>
  				<td width="20%"><b>沟通状态</b></td>
  				<td width="20%"><b>提交时间</b></td>
  			</tr>
  			<tr align="center" style="height: 30px;">
  				<td width="10%"><s:property value="#status.count"/></td>
  				<td width="35%"><s:property value="#record.concerns"/></td>
  				<td width="20%">
	  				<s:if test="#record.commStatus == 1"> 空号 </s:if>
	                <s:elseif test="#record.commStatus == 2"> 通话中 </s:elseif>
	                <s:elseif test="#record.commStatus == 3"> 再联系 </s:elseif>
	                <s:elseif test="#record.commStatus == 4"> 测试 </s:elseif>
	                <s:elseif test="#record.commStatus == 5"> 正常接通 </s:elseif>
	                <s:elseif test="#record.commStatus == 6"> 未接通 </s:elseif>
	                <s:elseif test="#record.commStatus == 7"> 假号码 </s:elseif>
	                <s:elseif test="#record.commStatus == 8"> 已购买 </s:elseif>
  				</td>
  				<td width="20%">
  					<s:date name="#record.createTime" format="yyyy-MM-dd HH:mm:ss" />
  				</td>
  			</tr>
  		</table>
  		</s:iterator>
  		</s:if>


<br />
</div>
<br />
</div>
<br />

<!-- 货到付款信息-->
<div class="chuangjianxinxi">货到付款信息</div>
 <hr  class="hr" size="1"/>
<div class="xiaoxikuang"><br />

<s:if test="addressList != null">
<s:iterator value="addressList" id="address" status="strat">

<div  style="font-weight:bold; padding-left:24px; margin-top:20px;">收货人信息<s:property value="#strat.count"/></div>
<div style="border:#0000CC  solid 2px; margin-left:25px; margin-top:10px; margin-right:25px; margin-bottom:10px;">
<table width="100%" border="0" cellspacing="10">
  <tr>
    <td width="50">&nbsp;</td>
    <td align="right">收货人姓名：</td>
    <td align="left"><s:property value="#address.receiver"/></td>
    <td align="right">移动电话：</td>
    <td align="left"><s:property value="#address.mobile"/></td>
    <td align="right">邮编：</td>
    <td align="left"><s:property value="#address.postCode"/></td>
    <td align="right">收货地址：</td>
    <td align="left"><s:property value="#address.address"/></td>
  </tr>
</table>
</div>
</s:iterator>
</s:if>
<br />
</div>

</body>
</html>