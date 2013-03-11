<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>商品订单-订单编号</title>
		
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet"
			href="<%=contextPath%>/styles/usercenter/uc_public.css"
			type="text/css">
			<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/css_body.css" />
				<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
			<link rel="stylesheet" type="text/css" media="screen"
	href="<%=importURL%>/uploadify/uploadify.css" />
<script type="text/javascript"
	src="<%=importURL%>/uploadify/swfobject.js"></script>
<script type="text/javascript"
	src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4_headimg.js"></script>
<script>
	function clickStopTImequxiao() {
		document.getElementById('addstoptime').style.display='block';
		document.getElementById('addYanqi').style.display='none';
	}
	function clickStopTIme() {
		var stopTime=document.getElementById('yanqi').value;
		if(stopTime=="") {
			alert('延期时间不能为空！');
			return false;
		}
		var daoqishijian=document.getElementById('daoqishijian').value;
		if(stopTime<daoqishijian) {
			alert('延期时间必须大于到期时间！');
			return false;
		}
		var packId=document.getElementById('dingdanbianhao').value;
		$.ajax({
			url : "<%=contextPath%>/finance/backCashRecord!createStopTime.action",  
			data : {delayTime : stopTime,cousmerId : packId },  // 参数  
			type : "post",  
			cache : false,  
			dataType : "json",  //返回json数据 
			error: function() {
				alert('error');
			},
			success:onchangecallbackcoursee
		});
	}
	function onchangecallbackcoursee(result){ //处理返回的课程JSON
		$("#addYanqi").css('display', 'none');
		$("#displayValidityTime").text(result.returnMessage);
		$("#addstoptime").css('display', 'block');
	}   
	function addstoptime(){
		document.getElementById('addstoptime').style.display='none';
		document.getElementById('addYanqi').style.display='block';
	}
</script>
	</head>
	<body>
		<div>
  <form action="<%=contextPath%>/finance/coupon!addCreateCoupon.action"
				method="post" name="planClockForm" id="planClockForm"
				onsubmit="return onSubmit();">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
      <tr>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
        <td class="lists_top"><font class="lists_fleft">商品订单-订单编号</font></td>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
      </tr>
      <tr>
        <td class="lists_bor"></td>
        <td><table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
            <tr height="30" >
              <td width="20%" class="lists_tright"> 商品售出记录编号： </td>
              <td class="lists_tleft" colspan="2" ><!-- <input  type="text" id="packId" value="${cashRecordDTO.id}"/> -->
                <s:property value="cashRecordDTO.packId"/>
                <input  type="hidden" id="dingdanbianhao" value="<s:property value="cashRecordDTO.id"/>
                "/>
                <input type="hidden" name="coupontype.picPath" id="coupontypePicPath"/></td>
            </tr>
            <tr height="30">
              <td  class="lists_tright" ><div>商品名称：</div></td>
              <td class="lists_tleft" colspan="2"><s:property value="cashRecordDTO.packName"/>
              		<s:if test="cashRecordDTO.shopType==3">运费</s:if>
              </td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright"  ><div>隶属订单编号：</div></td>
              <td class="lists_tleft" colspan="2"><s:property value="cashRecordDTO.contractId"/></td>
              <td></td>
            </tr>
            <tr height="30">
              <td  class="lists_tright" ><div>用户email：</div></td>
              <td class="lists_tleft" colspan="2"><s:property value="cashRecordDTO.email" /></td>
              <td></td>
            </tr>
            <tr height="30">
              <td  class="lists_tright" ><div>所属项目：</div></td>
              <td class="lists_tleft" colspan="2" ><s:property value="cashRecordDTO.subjectName" />
              			<s:if test="cashRecordDTO.shopType==3">运费</s:if>
              </td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright"  ><div>商品原始价格：&nbsp;&nbsp;</div></td>
              <td class="lists_tleft" colspan="2"><s:property value="cashRecordDTO.price" />
                元 </td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright" ><div>成交价格：</div></td>
              <td class="lists_tleft" colspan="2"><s:property value="cashRecordDTO.cashRecordPrice" />
                元 </td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright" ><div>减免价格：</div></td>
              <td class="lists_tleft" colspan="2" ><s:property value="cashRecordDTO.reliefPrice"  />
                元 </td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright" ><div>优惠金额：</div></td>
              <td class="lists_tleft" colspan="2" ><s:property value="cashRecordDTO.couponMoney"  />
                元 </td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright" ><div>商品状态：</div></td>
              <td class="lists_tleft" colspan="2"><s:if test="cashRecordDTO.shopStatus==0"> 未激活 </s:if>
                <s:if test="cashRecordDTO.shopStatus==1"> 已激活 </s:if>
                <s:if test="cashRecordDTO.shopStatus==2"> 已延期 </s:if>
                <s:if test="cashRecordDTO.shopStatus==3"> 已关闭 </s:if></td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright" ><div>支付状态：</div></td>
              <td class="lists_tleft" colspan="2"><s:if test="cashRecordDTO.status==0"> 未支付 </s:if>
                <s:if test="cashRecordDTO.status==1"> 已支付 </s:if>
                <s:if test="cashRecordDTO.status==2"> 已取消 </s:if>
                <s:if test="cashRecordDTO.status==3"> 退费 </s:if></td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright"><div>支付方式：</div></td>
              <td class="lists_tleft" colspan="2"><s:if test="cashRecordDTO.shopPayType==0"> 赠送 </s:if>
              <script>alert('<s:property value="cashRecordDTO.shopPayType" />')</script>
                <s:if test="cashRecordDTO.shopPayType==1"> 支付宝 </s:if>
                <s:if test="cashRecordDTO.shopPayType==2"> 货到付款 </s:if>
                <s:if test="cashRecordDTO.shopPayType==3"> 网银在线 </s:if>
                <s:if test="cashRecordDTO.shopPayType==4"> 快钱 </s:if>
                <s:if test="cashRecordDTO.shopPayType==5"> 代理商开通 </s:if>
                <s:if test="cashRecordDTO.shopPayType==6"> 真友支付 </s:if>
                <s:if test="cashRecordDTO.shopPayType==7"> 银行汇款 </s:if>
                <s:if test="cashRecordDTO.shopPayType==8"> 银联在线 </s:if>
                <s:if test="cashRecordDTO.shopPayType==9"> 课程卡 </s:if>
                <s:if test="cashRecordDTO.shopPayType==10">易联语音</s:if>
                </td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright"><div>创建（下单）时间：</div></td>
              <td class="lists_tleft" colspan="2"><s:date name="cashRecordDTO.createTime"  format="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
            <tr height="30">
              <td class="lists_tright"><div>付款时间：</div></td>
              <td class="lists_tleft" colspan="2"><s:if test="cashRecordDTO.shopPayTime==null"> 0000-00-00 00:00:00 </s:if>
                <s:date name="cashRecordDTO.shopPayTime"  format="yyyy-MM-dd HH:mm:ss"/></td>
              <td></td>
            </tr>
            <tr height="30">
              <td  class="lists_tright"><div>退款时间：</div></td>
              <td class="lists_tleft" colspan="2"><s:if test="cashRecordDTO.refundTime==null"> 0000-00-00 00:00:00 </s:if>
                <s:date name="cashRecordDTO.refundTime"  format="yyyy-MM-dd HH:mm:ss"/></td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright"><div>到期时间：</div></td>
              <td class="lists_tleft" colspan="2">
                <s:if test="cashRecordDTO.validityTime==null">
                  <div id="displayValidityTime" style="float:left;">0000-00-00 00:00:00</div>
                  <input type="hidden" id="daoqishijian" value="0000-00-00 00:00:00"/>
                </s:if>
                <s:else>
				  <div id="displayValidityTime" style="float:left;"><s:date name="cashRecordDTO.validityTime" format="yyyy-MM-dd HH:mm:ss"/></div>
                  <input type="hidden" id="daoqishijian" value="<s:date name="cashRecordDTO.validityTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
                </s:else>
                <div id="addstoptime" style="display: block;float: left; margin-left: 20px;">
                  <input type="button" value="延期" onclick="addstoptime()"/>
                </div>
                <div style="display: none;float: left; margin-left: 20px;" id="addYanqi">
                  <input type="text" name="createTimeBegin" readonly="readonly"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd 00:00:00',alwaysUseStartDate:true,autoPickDate:true})" id="yanqi"/>
                  <a href="####" onclick="clickStopTIme()">提交</a> &nbsp;&nbsp;<a href="####" onclick="clickStopTImequxiao()">取消</a>
                </div>
              </td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright"><div>停止服务时间：</div></td>
              <td class="lists_tleft" colspan="2"><s:if test="cashRecordDTO.stopTime==null"> 0000-00-00 00:00:00 </s:if>
                <s:date name="cashRecordDTO.stopTime"  format="yyyy-MM-dd HH:mm:ss"/></td>
              <td></td>
            </tr>
            <tr height="30">
              <td class="lists_tright"></td>
              <td  colspan="2" align="center"><input type="button" value="返回" onclick="history.go(-1)"/></td>
              <td></td>
            </tr>
          </table></td>
        <td class="lists_tright lists_bor2"></td>
      </tr>
      <tr>
        <td class="td_wid_1"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
        <td class="lists_bottom"></td>
        <td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
      </tr>
    </table>
  </form>
</div>
	</body>
</html>