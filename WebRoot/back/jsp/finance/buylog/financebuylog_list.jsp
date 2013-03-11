<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>支付流程数据统计</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery.blockUI.js"></script>
<script language="JavaScript">

//进入页面时，初始化查询条件

$(document).ready(function(){
	changeData();
});
//查询
  function search(ObjectForm){ 
	   document.f1.action="<%=contextPath%>/finance/financeBuyLogRecord!queryFinanceBuyLogList.action";
	   document.f1.submit();
  }
//取消
  function cancel(){
			document.getElementById('shopName').value='';
  }
  
//初始化时间的显示
  	function changeData() {
  	var starthh = $("#hidestarthh").val();
  	var endhh= $("#hideendhh").val();
  	changeStartHH(starthh);
  	changeEndHH(endhh);
	}
	function changeStartHH(hourStr) {
		$.each($("#startHH option"),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
		}
	function changeEndHH(hourStr) {
		$.each($("#endHH option"),function(){
			if(this.value == hourStr) {
				this.selected = true;
			}
		})
	}
	
//导出excel
	function checkOutExcel()
	{
	     	document.f1.action="<%=contextPath%>/finance/financeBuyLogRecord!exportCSV.action";
			document.f1.submit();
	}
</script>
</head>
<body>
<div>

	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
	 <tr>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
        <td class="lists_top"><font class="lists_fleft">支付流程数据统计</font></td>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
      </tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<form name="f1"  method="post">
	         <div class="msg-cw">
			<table width="70%" border="0" cellspacing="1" cellpadding="0" class="lists" align="center">
			
			<tr>
			<input type="hidden" value="${startHH}" id="hidestarthh"/>
			<input type="hidden" value="${endHH}" id="hideendhh"/>
			<td>开始时间 ：<input type="text" name="startTime" readonly="readonly" id="startTime" onFocus="WdatePicker()"
										onchange="createDateOne()"	 value="${startTime}" />
			-
			<select name="startHH" id="startHH">
											<option value=" 00:00:00">
												00:00:00
											</option>
											<option value=" 01:00:00">
												01:00:00
											</option>
											<option value=" 02:00:00">
												02:00:00
											</option>
											<option value=" 03:00:00">
												03:00:00
											</option>
											<option value=" 04:00:00">
												04:00:00
											</option>
											<option value=" 05:00:00">
												05:00:00
											</option>
											<option value=" 06:00:00">
												06:00:00
											</option>
											<option value=" 07:00:00">
												07:00:00
											</option>
											<option value=" 08:00:00">
												08:00:00
											</option>
											<option value=" 09:00:00">
												09:00:00
											</option>
											<option value=" 10:00:00">
												10:00:00
											</option>
											<option value=" 11:00:00">
												11:00:00
											</option>
											<option value=" 12:00:00">
												12:00:00
											</option>
											<option value=" 13:00:00">
												13:00:00
											</option>
											<option value=" 14:00:00">
												14:00:00
											</option>
											<option value=" 15:00:00">
												15:00:00
											</option>
											<option value=" 16:00:00">
												16:00:00
											</option>
											<option value=" 17:00:00">
												17:00:00
											</option>
											<option value=" 18:00:00">
												18:00:00
											</option>
											<option value=" 19:00:00">
												19:00:00
											</option>
											<option value=" 20:00:00">
												20:00:00
											</option>
											<option value=" 21:00:00">
												21:00:00
											</option>
											<option value=" 22:00:00">
												22:00:00
											</option>
											<option value=" 23:00:00">
												23:00:00
											</option>
											<option value=" 23:59:59">
												23:59:59
											</option>
										</select>
										(时:分:秒)
			</td>
			<td>结束时间 ：<input type="text" name="endTime" readonly="readonly" id="endTime" onFocus="WdatePicker()"
										onchange="createDateOne()"	 value="${endTime}" />
			-
			<select name="endHH" id="endHH">
											<option value="23:59:59">
												23:59:59
											</option>
											<option value="00:00:00">
												00:00:00
											</option>
											<option value="01:00:00">
												01:00:00
											</option>
											<option value="02:00:00">
												02:00:00
											</option>
											<option value="03:00:00">
												03:00:00
											</option>
											<option value="04:00:00">
												04:00:00
											</option>
											<option value="05:00:00">
												05:00:00
											</option>
											<option value="06:00:00">
												06:00:00
											</option>
											<option value="07:00:00">
												07:00:00
											</option>
											<option value="08:00:00">
												08:00:00
											</option>
											<option value="09:00:00">
												09:00:00
											</option>
											<option value="10:00:00">
												10:00:00
											</option>
											<option value="11:00:00">
												11:00:00
											</option>
											<option value="12:00:00">
												12:00:00
											</option>
											<option value="13:00:00">
												13:00:00
											</option>
											<option value="14:00:00">
												14:00:00
											</option>
											<option value="15:00:00">
												15:00:00
											</option>
											<option value="16:00:00">
												16:00:00
											</option>
											<option value="17:00:00">
												17:00:00
											</option>
											<option value="18:00:00">
												18:00:00
											</option>
											<option value="19:00:00">
												19:00:00
											</option>
											<option value="20:00:00">
												20:00:00
											</option>
											<option value="21:00:00">
												21:00:00
											</option>
											<option value="22:00:00">
												22:00:00
											</option>
											<option value="23:00:00">
												23:00:00
											</option>
										</select>
										(时:分:秒)
			</td>
			</tr>
			
			<tr>
			<td>项目 ：<s:select id="subjectId"
											name="subjectId"
											list="subjectList" listKey="subjectId"
											listValue="subjectName" tyle="width: 155px">
					  </s:select>
			</td>
			<td colspan="2" align="center">
			<img src="<%=contextPath%>/back/images/add_a.gif"/><a href="javascript:search(document.f1)">查询</a>&nbsp;
			<img src="<%=contextPath%>/back/images/del_a.gif"/><a href="javascript:cancel()">清空</a>
			<img src="<%=contextPath%>/back/images/down16_16.gif" /><a href="javascript:checkOutExcel()">导出excel</a>
			 </td>
			</tr>
			
			</table>
			</div>
			</form>
			</td>
			<td width="16" class="lists_tright lists_bor2"></td>
		</tr>

		<tr>
			<td width="12" class="lists_bor"></td>			
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>项目</td> 
						<td>点购量(项目页购买)</td>
						<td>注册量</td>
						<td>登录量</td>
						<td>未登陆或注册用户进入购物车</td>
						<td>提交订单</td>
						<td>货到付款</td>
						<td>银行汇款</td>
						<td>网上支付</td>
						<td>返回上一步</td>
					</tr>
					<s:iterator value="financeBuyLogList" id="buylog">
					<tr>								
						<td><s:property value="#buylog.subject_name" /></td>
						<td><s:property value="#buylog.buy_clicked" /></td>
						<td><s:property value="#buylog.register_clicked" /></td>
						<td><s:property value="#buylog.login_clicked" /></td>
						<td><s:property value="#buylog.next_step_clicked" /></td>
						<td><s:property value="#buylog.create_third_clicked" /></td>
						<td><s:property value="#buylog.create_recv_clicked" /></td>
						<td><s:property value="#buylog.create_offline_clicked" /></td>
						<td><s:property value="#buylog.pay_third_clicked" /></td>
						<td><s:property value="#buylog.up_step_clicked" /></td>										
					</tr>
					</s:iterator>				
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2"></td>
		</tr>
	</table>
</div>
</body>
</html>