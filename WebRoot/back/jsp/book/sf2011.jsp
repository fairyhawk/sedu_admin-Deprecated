<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书购买-嗨学网</title>
<link rel=stylesheet type=text/css href="http://import.highso.org.cn/styles/web/public/web_shopping.css"/>
<link rel="stylesheet" type="text/css"  href="http://import.highso.org.cn/styles/web/public/payment.css" />
<link rel=stylesheet type=text/css href="http://import.highso.org.cn/styles/web/public/web_lacpage.css"/>
<script type="text/javascript" src="http://import.highso.org.cn/js/web/public/web_jquery-jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="http://import.highso.org.cn/js/web/public/web_jquery.lazyload.js"></script>
<script type="text/javascript" src="http://import.highso.org.cn/js/web/public/jQueryValidate/jquery.validate.js"></script>
<link href="http://import.highso.org.cn/styles/web/public/buy_book.css" rel="stylesheet" type="text/css" />

<script type="">
	function doMyThing(){ 
			return false; 
		} 
		document.oncontextmenu = doMyThing; 
		window.history.go(1);
function initArea(id, index){
		var parentId = 1;
		if(id != null && id != 0 && !isNaN(id)) {
			parentId = id;
		}

		$.ajax({
			url :  "/cus/areaWeb!getAreasByParentId.action",
			data : {
				"queryAreaCondition.parentId" : parentId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result == null || result.entity == null) {
					return;
				}
				var provinces = result.entity;
				var html = '';
				for(var i=0; i<provinces.length; i++) {
					html += "<option value='" + provinces[i].id + "'>" + provinces[i].areaName + "</option>";
				}
				if(index == 0) {
                   $("<option value='0'>-------</option>").appendTo("#sel_province");
					$(html).appendTo("#sel_province");
				} else if(index == 1) {
					$("#sel_city").html("");
					$("#sel_town").html("");
					$("<option value='0'>--------</option>" + html).appendTo("#sel_city");
					$("<option value='0'>--------</option>").appendTo("#sel_town");
				} else {
					$("#sel_town").html("");
					$("<option value='0'>--------</option>" + html).appendTo("#sel_town");
				}
			},
			error : function(error) {
				alert('error');
			}
		});
	}
function addOrder()
{
var buy_num=$("#buy_num").val();
if(buy_num == null ||buy_num == "")
{
alert("购买数量不能为空");
return false;
}

if(/^\d+$/.test(buy_num )==false || isNaN(buy_num))
{
alert("请输入正确定的购买数量");
return false;
}else{
$("#buySum").val(buy_num);
}
var cusName=$("#cusName").val();
if(cusName == null ||cusName == "")
{
alert("收件人姓名不能为空");
return false;
}
var province=$("#sel_province").val();
if(province==0){
alert("请选择省");
return false;
}
var city=$("#sel_city").val();
if(city==0){
alert("请选择市");
return false;
}
var town=$("#sel_town").val();
if(town==0){
alert("请选择县");
return false;
}
var addr=$("#detailAddress").val();
if(addr == null ||addr == "")
{
alert("送货地址不能为空");
return false;
}
var tel=$("#addressTel").val()+"";
var mobilephone=$("#addressMobile").val()+"";
if(tel == "" && mobilephone == "")
{
alert("请填写手机或电话");
return false;
}
if(mobilephone!="")
{
if(/^1{1}[0-9]{10}$/.test(mobilephone)==false)
{
alert("请输入正确的手机号");
return false;
}
}

if(tel!="")
{
if(/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(tel)==false)
{
alert("请输入正确的电话号码");
return false;
}
}
var postCode=$("#postCode").val();
if(postCode == null ||postCode == "")
{
alert("邮编不能为空");
return false;
}
if(/[0-9]{6}$/.test(postCode)==false)
{
alert("请输入正确的邮编");
return false;
}
addr=$("#sel_province").find('option:selected').text()+$("#sel_city").find('option:selected').text()+$("#sel_town").find('option:selected').text();
$("#buyaddr").val(addr+$("#detailAddress").val());
$("#myform").submit();
}
function closeContractWin()
{
$("#buy_step4").hide();
}

</script>
</head>

<body onload="initArea(0,0)">
	<!--header start-->
	<div class="header">
		<div class="header_box">
			<div class="logo">
				<a href="#"><img src="/images/web/public/book/logo.gif"
					alt="嗨学网Highso" title="嗨学网Highso" />
				</a>
			</div>
			<span class="help_top"><a href="#">帮助</a>
			</span>
		</div>
	</div>
	<!--header end-->
	<!--main start-->
	<div class="main">
		<form action="/book/buyInfo!addBuyInfo.action" method="post" id="myform">
			<input type="hidden" name="buyInfo.bookname" value="2011年国家司法考试辅导用书(套装全3册)" />
				 <input type="hidden" id="buySum" name="buyInfo.buySum" value="1" />
				 <input type="hidden" id="buyaddr" name="buyInfo.address" />
			<div class="book_process">
				<p>想购买此书？您只需要：</p>
				<ol>
					<li class="li1">1.填写页面下方的收货人信息</li>
					<li class="li2">2.点击"提交订单"按钮</li>
				</ol>
			</div>
			<h1>2011年国家司法考试辅导用书(套装全3册)</h1>
			<div class="book_box">
				<div class="book_img">
					<div class="book_pic">
						<img src="/images/web/public/book/sf2011.jpg" alt="司法2011" />
					</div>
					<div class="book_exp">
						注：本套图书适用于<em>2012年国家司法考试零基础保障班。</em>
					</div>
				</div>
				<div class="book_info">
					<p class="price_d">定 价: ￥300元</p>
					<p class="price_b">
						购买价:<strong> ￥300元</strong><span class="buy_num"><label>我要买：</label><input
							type="text" class="text" id="buy_num" name="buy_num" value="1" />件</span>
					</p>
					<p class="inventory">库 存: 有货，可送至全国</p>
					<div class="book_data">
						<p>出 版 社： 法律出版社</p>
						<p>编 者： 国家司法考试辅导用书编辑委员会</p>
						<p>出版时间： 2011年5月1日（第1版）</p>
						<p>I S B N ： 9787511820594</p>
					</div>
				</div>
				<div class="clear"></div>
				<div class="book_con">
					<h3>内容简介</h3>
					<p>
						《2011年国家司法考试辅导用书(套装全3册)》根据司法考试的试卷内容范围分卷成册，共分3册。即：第一卷包括社会主义法治理念、法理学、法制史、
						宪法、经济法、国际法、国际私法、国际经济法、司法制度和法律职业道德；第二卷包括刑法、刑事诉讼法、行政法与行政诉讼法；第三卷包括民法、商法、
						民事诉讼法与仲裁制度。</p>
				</div>
			</div>
			<div class="point">
				<p class="p1">
					<strong>温馨提示：</strong>我们目前仅支持<span>货到付款</span>的支付方式,所以请您务必填写好订单信息，以便我们及时送达。
				</p>
				<p class="p2">
					我们的快递目前为<span>宅急送</span>
				</p>
			</div>
			<div class="book_pament">
				<h3>收货人信息</h3>
				<div class="consignee p_item">
					<span class="star">*</span><label class="label_name">收件人姓名：</label><input
						type="text" maxlength="30" id="cusName" name="buyInfo.cusName"
						class="txt_input" />
				</div>
				<div class="region p_item">
					<span class="star">*</span> <label class="label_name"> 地区：</label><select
						id="sel_province"  class="select_w"
						onchange="initArea(this.value, 1)"></select> <select id="sel_city"
						 class="select_w"
						onchange="initArea(this.value, 2)">
						<option value="0">--------</option>
					</select> <select id="sel_town"  class="select_w">
						<option value="0">--------</option>
					</select>
					<div class="harvest_address p_item">
						<span class="star">*</span><label class="label_name">配送地址：</label><input
							type="text" maxlength="100" id="detailAddress"
							name="buyInfo.address" class="txt_input" />
					</div>
					<div class="mobile p_item">
						<span class="star">*</span><label class="label_name">手机号码：</label><input
							type="text" maxlength="15" id="addressMobile"
							name="buyInfo.mobilephone" class="txt_input" /> 或者 固定电话 <input
							type="text" maxlength="15" id="addressTel" name="buyInfo.tel"
							class="txt_input" value="" />
					</div>
					<div class="zip_code p_item">
						<span class="star">*</span> <label class="label_name">邮政编码：</label><input
							type="text" maxlength="10" id="postCode"
							name="buyInfo.postalcode" class="txt_input" />
					</div>
					<div class="remark">
						<div class="re_title">
							<h4>备注信息</h4>
							<p>( 有什么需要特别说明的请写在这里，我们会尽量满足您的要求。)</p>
						</div>
						<textarea name="" cols="" rows="" name="buyInfo.remark"></textarea>
					</div>
					<div class="bt_paymentt" >
						<p >确认无误？</p>
						<a class="btn" href="#" style="cursor: pointer;"
							onclick="return addOrder();"><strong>提交订单</strong>
						</a>
					</div>
				</div>

			</div>
		</form>
	</div>
	<!--main end-->
	<!--footer start-->
	<div id="footer">
		<div class="webinfo">
			<span><img
				src="http://import.highso.org.cn/images/web/public/footer_p1.gif" /><img
				src="http://import.highso.org.cn/images/web/public/footer_p2.gif" /><img
				src="http://import.highso.org.cn/images/web/public/footer_p3.gif" />
			</span>尚德机构远程教育<br /> 京ICP备10217787号 Copyright &copy; 2010-2011
			highso.org.cn
		</div>
	</div>
	<!--footer end-->
	    <!--货到付款订单成功提交 begin -->
	<div id="buy_step4" style="position: absolute;float: left;z-index: 11;top: 350px;margin-left: 550px;display: none">
		<div class="submitted_successfully">
		<em class="close"><a href="javascript:closeContractWin()"><img src="http://import.highso.org.cn/images/web/public/buy/bs_line_03.gif" alt="" /></a></em>
	
		
        <div class="con_cont1">
			<div class="pic"><img src="http://import.highso.org.cn/images/web/public/buy/bd_line_01.gif" /></div>
			<div class="cont_x">
				<h3><strong>恭喜您，您的订单已经成功提交，<br/>嗨学网会立即安排发货，请您耐心等待！</strong></h3>
				<p class="b1">等待期间，您可以体验嗨学网如下服务</p></div>
        </div>
		
		<div class="con_cont2">
			<p class="bbt">提示：如有疑问请进入<span><a href="http://172.16.121.123/static/web/column/174/index_1.shtml">帮助中心</a></span>或联系客服<span>4007-062-061</span>。</p></div>
		</div>
	</div>
	
	<!-- 货到付款订单成功提交 end -->
	<script type="">
  var cusId=${buyInfo.cusId}+"";
         if(cusId=="666"){//判断是否是已提交
        $("#buy_step4").show();
         }
</script>
</body>
</html>