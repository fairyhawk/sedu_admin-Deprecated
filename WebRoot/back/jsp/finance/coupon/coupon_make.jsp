<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>优惠券查询</title>
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
			type="text/css"></link>
		<link rel="stylesheet" href="<%=contextPath%>/back/style/css_body.css"
			type="text/css"></link>

		<SCRIPT>
		function doMyThing(){ 
			return false; 
		} 
		document.oncontextmenu = doMyThing;
		window.history.go(1);
		function builCoding(){
			
		document.getElementById("xiugai").style.display="none"; 
		var useSum=document.getElementById("useSum").value;
		var codingId=document.getElementById("codingId").value;
		var cooperationid=document.getElementById("cooperationid").value;
				$.ajax({  
					url : "<%=contextPath%>/finance/coupon!createcoding.action",  
					data : {useSum:useSum,codingId:codingId,cooperationid:cooperationid},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackcoursee  
					});
				$("#sccode").attr("disabled","disabled");
			} 
		function onchangecallbackcoursee(result){ //处理返回的课程JSON  
			document.getElementById("textareaName").style.display="block";
			document.getElementById("returnAllConfo").style.display="block";
				var conponList = result.entity;
				var str=""; 
				var jishu=1; 
				for(var i=0;i<result.entity.length;i++){  
					jishu=jishu+i;
					str+=jishu+"、"+result.entity[i].title+"\n\r</br>"
					jishu=1;
				}  
				$("#codeing").html(str);
	   		}   
	   		function onSubmit(){
	   			if(confirm('确认修改？')==true){
				return true;
			}else{
				return false;
			}
	   	}var quanju='';
	   	function daochuBianMa(){
	   		var codeIndexId=document.getElementById('codeIndexId').value;
	   	if(codeIndexId>0){
	   		window.location.href="<%=contextPath%>/finance/coupon!exportCouponCode.action?couponTypeId=${coupontype.id}";
	   		return true;
	   	}else{
	   		alert('此优惠券在新建时没有生成优惠编码，不能进行导出！');
	   		return false;
	   	}
	   	}
		</SCRIPT>
		<style type="">
.myselect {
	float: left
}
;

</style>
<style>
</style>
	</head>
	<body>
		<div>
			<form
				action="<%=contextPath%>/finance/coupon!addCreateCouponXiuGai.action"
				method="post" name="planClockForm" id="planClockForm"
				onsubmit="return onSubmit();">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">优惠券查询</font>
							<font class="lists_fright"> </font>
							<input type="hidden" name="coupontype.picPath"
								id="coupontypePicPath" />
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor"></td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr height="30">
									<td width="20%" class="lists_tright">
										<div>
											<font style="color: red">*</font>优惠券类型：&nbsp;&nbsp;
										</div>
									</td>
									<td class="lists_tleft" colspan="2">
										<font style="float: left"> <s:if
												test="coupontype.cType==1">
												<input type="text" id="cType" value="折扣优惠券"
													readonly="readonly" />
											</s:if> <s:if test="coupontype.cType==2">
												<input type="text" id="cType" value="立减定额优惠券"
													readonly="readonly" />
											</s:if>
										</font>
										<input type="hidden" name="coupontype.cType"
											value="<s:property value="coupontype.cType"/>"
											readonly="readonly" />
										<input type="hidden" name="coupontypeid"
											value="<s:property value="coupontype.id"/>" />
										<div style="float: right; padding-right: 10px; display: none;"
											id="returnAllConfo">
											<a
												href="<%=contextPath%>/finance/coupon!getCouponCodeList.action?queryCouponCodeCondition.currentPage=1"><font
												   style="text-decoration: underline;padding-top: 3px;font-size: 12px;" >返回优惠券管理列表 </font>
											</a>
										</div>
									</td>
								</tr>
								<tr height="30">
									<td align="center" class="lists_tright">
										<div>
											<font style="color: red">*</font>优惠券名称:&nbsp;&nbsp;&nbsp;
										</div>
									</td>
									<td class="lists_tleft" colspan="2">
										<input type="text" name="coupontype.title" id="title"
											value="<s:property value="coupontype.title"/>"
											readonly="readonly" />
										<span class="jqVaildate"></span>
									</td>
								</tr>
								<tr height="30">
									<td class="lists_tright">
										<div>
											<font style="color: red">*</font>合作商：&nbsp;&nbsp;&nbsp;
										</div>
									</td>

									<td class="lists_tleft" colspan="2">
										<input type="text"
											value="<s:property value="cooperation.companyName"/>"
											id="companyName" style="width: 180px;" value="a.jsp"
											readonly="readonly" />
										<input type="hidden"
											value="<s:property value="cooperation.id"/>"
											name="coupontype.cooperativeId" />
										<input type="hidden" value="<s:property value="cooperation.id"/>" id="cooperationid"/> 
									</td>
								</tr>
								<tr height="30">
									<td class="lists_tright">
										<div>
											图片：&nbsp;&nbsp;&nbsp;
										</div>
									</td>
									<td class="lists_tleft" colspan="2">
										<s:if test="coupontype.picPath!=''">
											<img
												src="http://import.highso.org.cn/upload/coupon/<s:property value="coupontype.picPath"/>"
												width="100" height="100" />
										</s:if>
										<s:if test="coupontype.picPath==''">
							没有选图片！
							</s:if>
									</td>
								</tr>
								<tr height="30" id="youhuizhekou">
								<td class="lists_tright">
										<s:if test="coupontype.cType==1">
											<div>
												<font style="color: red">*</font>优惠折扣:&nbsp;&nbsp;&nbsp;
											</div>
										</s:if>
										<s:if test="coupontype.cType==2">
											<div>
												<font style="color: red">*</font>优惠金额:&nbsp;&nbsp;&nbsp;
											</div>
										</s:if>
									</td>
									<td class="lists_tleft" colspan="2">
										<input type="text"
											value="<s:property value="coupontype.preferentialPrice"/>"
											name="coupontype.preferentialPrice" id="preferentialPrice"
											style="width: 180px;" readonly="readonly" />
										<s:if test="coupontype.cType==1">
						折 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!--  <font style="color: red">该优惠券折扣额，如2折优惠券，则请输入“0.2”。</font>-->
										</s:if>
										<s:if test="coupontype.cType==2">
							元   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--  <font style="color: red">该优惠券金额，如5元优惠券，则请输入“5”。</font> -->
										</s:if>

									</td>
								</tr>
								<tr height="30" id="youhuijine">

									<td class="lists_tright">
										<script type="">
											
										</script>
										<div>
											<font style="color: red">*</font>使用限额:&nbsp;&nbsp;&nbsp;
										</div>
									</td>
									<td class="lists_tleft" colspan="2">
										<input type="text"
											
											name="coupontype.leastPrice" id="leastPrice"
											style="width: 180px;" readonly="readonly" />
										&nbsp;&nbsp;元以上&nbsp;&nbsp;
										<script type="">
										document.getElementById('leastPrice').value=${coupontype.leastPrice};
										</script>
										<div id="xeId">
											<!--	<font style="color: red">只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入“300”。</font></div>-->
									</td>
								</tr>
								<tr height="30">
									<td class="lists_tright">
										<div>
											<font style="color: red">*</font>适用范围:&nbsp;&nbsp;&nbsp;
										</div>
									</td>
									<td class="lists_tleft" colspan="2">
										<input type="text" name=id=
											""  value="<s:property value="subject.subjectName"/>"
											readonly="readonly" />
										&nbsp;&nbsp;
										<!-- <div id="syfwId">
						<font style="color: red">适用范围说明：<br />
												１、选择所有项目，则该优惠券将适用于所有课程；<br/>
												２、选择单一项目，则根据选择的分类确定，该优惠券只能使用于购买该课程。</font></div>  -->
										<input type="hidden" name="coupontype.subjectId"
											value="<s:property value="subject.subjectId"/>" />
									</td>
								</tr>
								<s:if test="chuaCouponType!=1">
									<tr height="30">
										<td class="lists_tright">
											<div>
												<font style="color: red">*</font>使用起始日期:&nbsp;&nbsp;&nbsp;
											</div>
										</td>
										<td class="lists_tleft" colspan="2">
											<input type="text" name="coupontype.useTime" id="createTime"
												value="<s:property value="coupontype.stringCreateTime"/>"
												readonly="readonly" />
											&nbsp;&nbsp;
											<!-- <div id="qsTmId"><font style="color: red">该优惠券使用的开始时间。</font></div> -->
											<span class="jqVaildate"></span>
										</td>
									</tr>
									<tr height="30">
										<td class="lists_tright">
											<div>
												<font style="color: red">*</font>使用截止日期:&nbsp;&nbsp;&nbsp;
											</div>
										</td>
										<td class="lists_tleft" colspan="2">
											<input type="text" name="coupontype.stopTime" id="stopTime"
												value="<s:property value="coupontype.stringstopTime"/>"
												readonly="readonly" />
											&nbsp;&nbsp;
											<!-- <div id="jzTmId"><font style="color: red">该优惠券使用的截止时间。</font></div> -->
										</td>
									</tr>
								</s:if>
								<s:if test="chuaCouponType==1">
									<tr height="30">
										<td class="lists_tright">
											<div>
												<font style="color: red">*</font>使用起始日期:&nbsp;&nbsp;&nbsp;
											</div>
										</td>
										<td class="lists_tleft" colspan="2">
											<input type="text" name="coupontype.useTime" id="createTime"
												value="<s:date name="coupontype.useTime" format="yyyy-MM-dd HH:mm:ss" />"
												readonly="readonly" />
											&nbsp;&nbsp;
											<!-- <div id="qsTmId"><font style="color: red">该优惠券使用的开始时间。</font></div> -->
											<span class="jqVaildate"></span>
										</td>
									</tr>
									<tr height="30">
										<td class="lists_tright">
											<div>
												<font style="color: red">*</font>使用截止日期:&nbsp;&nbsp;&nbsp;
											</div>
										</td>
										<td class="lists_tleft" colspan="2">
											<input type="text" name="coupontype.stopTime" id="stopTime"
												value="<s:date name="coupontype.stopTime" format="yyyy-MM-dd HH:mm:ss" />"
												readonly="readonly" />
											&nbsp;&nbsp;
											<!-- <div id="jzTmId"><font style="color: red">该优惠券使用的截止时间。</font></div> -->
										</td>
									</tr>
								</s:if>
								<tr height="30">
									<td class="lists_tright">
										<div>
											<font style="color: red">*</font>生成优惠编码数量:&nbsp;&nbsp;&nbsp;
										</div>
									</td>
									<td class="lists_tleft" colspan="2">
										<input type="text"
											value="<s:property value="coupontype.cNum"/>"
											name="coupontype.useSum" id="useSum" style="width: 180px;"
											readonly="readonly" />
										&nbsp;&nbsp;
										<!-- <div 
id="scslId"><font style="color: red">必须是数字，数字不得大于10000。</font></div> -->
									</td>
								</tr>
								<!-- 
								<tr height="30">
									<td>
										<div style="float: right">	兑换积分:&nbsp;&nbsp;&nbsp;</div>
									</td>
									<td class="lists_tleft" colspan="2">
										<input type="text" value="<s:property value="coupontype.cToscore"/>" name="coupontype.cToscore" id="cToscore" style="width:180px;"  readonly="readonly"/>&nbsp;&nbsp;<!-- <div 
id="jfId"><font style="color: red">非必填。</font></div> 
									</td>
								</tr> -->

								<s:if test="chuaCouponType==1">
									<tr valign="middle">
										<td class="lists_tright">
											<div
												style="float: left; padding-top: 0px; width: 100%; height: 100px;">
												生成编码:
											</div>
										</td>
										<td class="lists_tleft" colspan="2" valign="middle">
											<textarea cols="35" rows="8" name="" id="couponcode"
												style="font-size: 9">
							</textarea>
											<a onclick="daochuBianMa()"><font>导出优惠码</font> </a>
												<input type="hidden"id="codeIndexId"/>
											<script type="">
											quanju=codeIndex;
                                            var codeIndex=0;
                                             var textareaHtml="";
	                                         <s:iterator value="couponList" id="couponcode" >
                                             codeIndex=codeIndex+1;
				                             textareaHtml=textareaHtml+codeIndex+'、 ${couponcode.title}'+'\n';
						                      </s:iterator>
											document.getElementById('codeIndexId').value=codeIndex;
                                            $("#couponcode").html(textareaHtml);
                                              </script>
										</td>
									</tr>
								</s:if>

								<s:if test="chuaCouponType!=1">
									<tr height="30">
										<td >
										</td>
										<td class="lists_tleft" colspan="2">
											<input type="submit" value="修改" id="xiugai" />
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" id="sccode" onclick="builCoding()"
												value="生成编码" />
											<input type="hidden" value="${coupontype.id}" id="codingId" />
											<!-- 又会变吗ID -->
											<input type="hidden" value="${coupontype.id}" id="codingId" />
										</td>
									</tr>
								</s:if>
								<tr height="30">
									<td>
									</td>
									<td class="lists_tleft" colspan="2">
										<div id="textareaName" style="display: none; float: left;">
											生成效果:
											<textarea rows="5" cols="30" name="" id="codeing"
												style="font-size: 12px"></textarea>
											<a
												href="<%=contextPath%>/finance/coupon!exportCouponCode.action?

couponTypeId=${coupontype.id}"><font  style="text-decoration: underline;padding-top: 3px;font-size: 12px;" >导出优惠码</font>
											</a>
										</div>
									</td>
								</tr>
							</table>

							</div>
							<div id="zyong"
								style="position: absolute; margin-left: -1500px; margin-top: -1500px; z-index: 10"></div>
							<div id="zyong2"
								style="position: absolute; margin-left: -1000px; margin-top: -1000px; z-index: 11"></div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
