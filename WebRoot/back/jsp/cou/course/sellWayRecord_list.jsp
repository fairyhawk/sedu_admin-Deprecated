<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	var s=0;
	function search(){
		  var startTime=document.getElementById("startTime").value;
		  var endTime=document.getElementById("endTime").value;
		  if(startTime!=null&&endTime!=null){
		  if(startTime>endTime){
			 alert("结束时间大于开始时间!");
			 return;
		  }else{
		 	$("#searchForm").attr("action","<%=contextPath%>/cou/sellway!showSellWayListRecord.action");
		 	$("#searchForm").submit();	 
		 }
	   }
	}
	function resetKey() {
		$("input[name=startTime]").val("");
		$("input[name=endTime]").val(""); 			
	}
	function exportReportcsv(){
		document.getElementById("searchForm").action="<%=contextPath%>/cou/sellway!exportReportCSV.action";
		document.getElementById("searchForm").submit();
		document.getElementById("searchForm").action="<%=contextPath%>/cou/sellway!showSellWayListRecord.action?querySellWayCondition.currentPage=1";
	}
</script>
</head>
<body>
	<!-- 查询块开始 -->
	<div>		
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top"><font class="lists_fleft">销售记录财务表</font><font class="lists_fright"> </font></td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
				<td class="lists_bor"></td>   
	            <td>
	              <div class="msg-zy">
					<form name="searchForm" id="searchForm" method="post">
						<table class="lists" border="0" cellspacing="0"  cellpadding="0">
							<tr>
								<td>
										<s:hidden name="querySellWayCondition.currentPage" value="1" />
										商品创建开始时间：
										<input type="text" name="startTime" readonly="readonly" id="startTime" onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"
											value="${yestartTime}"/> 
										<select name="startHH" id="startHH">
											<option value=" 00:00:00" id="op1">
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
									<td>
										商品创建结束时间：
										<input type="text" name="endTime" readonly="readonly" id="endTime" onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"
											value="${yeendTime}"/>
										<select name="endHH" id="endHH">
											<option value=" 23:59:59" id="op0">
												23:59:59
											</option>
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
										</select>
										(时:分:秒)
									</td>
							</tr>
							<tr>
								<td>
									<img src="<%=contextPath%>/back/images/add_a.gif" />
									<a href="javascript:search()">查询</a>
									<img src="<%=contextPath%>/back/images/del_a.gif" />
									<a href="javascript:resetKey()">清空</a>
								</td>
							</tr>
						</table>
					</form>
				  </div>
				</td>
				<td class="lists_tright lists_bor2"></td>
				</tr>
				
			<tr>
					<td class="lists_bor"></td>
				<td class="lists_top">
					<font class="lists_fleft">商品列表</font>
					<font class="lists_fright">
						<!-- 导出 -->
						<img src="<%=contextPath%>/back/images/edt_tbl.gif" /><a href="###" onclick="exportReportcsv()">导出</a>
					</font>
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			
			<tr>
				<td class="lists_bor"></td>
				<td>
					<form name="sellWayForm" method="post">
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							<tr class="lists_infobg">
								<td>编号</td>
								<td>名称</td>
								<td>所属项目</td>
								<td>售卖价格</td>
								<td>实收价格</td>
								<td>用户邮箱</td>
								<td>创建时间</td>
								<td>上架时间</td>
								<td>开售时间</td>
								<td>商品确收时间</td>
								<td>本单确收时间</td>
								<td>服务有效期</td>
								<td>服务终止时间</td>
								<td>停销时间</td>
								<td>下架时间</td>
								<td>停止服务时间</td>
							</tr>					
							<s:iterator id="sellWaytemp" value="page.pageResult" status="s">
								<tr>
									<td>
										<s:property value="#sellWaytemp.sellId"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.sellName"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.subjectName"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.sellPrice"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.cashRecordPrice"/>
									</td>
									<td>
										<s:property value="#sellWaytemp.email"/>									
									</td>
									<td>
										<s:date name="#sellWaytemp.addTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
										<s:date name="#sellWaytemp.upTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
										<s:date name="#sellWaytemp.beganSellTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
										<s:date name="#sellWaytemp.confirmTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
									<s:if test="#sellWaytemp.confirmTime!=null">
										<s:if test="#sellWaytemp.confirmTime>#sellWaytemp.payTime">
									<s:date name="#sellWaytemp.confirmTime" format="yyyy-MM-dd HH:mm:ss" />
									</s:if>
									 <s:else>
								    <s:date name="#sellWaytemp.payTime" format="yyyy-MM-dd HH:mm:ss" />
								    </s:else>
									</s:if>
								    <s:else>
								    <s:date name="#sellWaytemp.payTime" format="yyyy-MM-dd HH:mm:ss" />
								    </s:else>
								
									</td>
									<td>						 
										<s:if test="#sellWaytemp.validityNum==0" >
											<s:date name="#sellWaytemp.validityTime" format="yyyy-MM-dd" />
										</s:if>
										<s:else>
											<s:property value="#sellWaytemp.validityNum"/>天
										</s:else>
									</td>
									<td>
										<s:if test="#sellWaytemp.validityNum==0" >
											<s:date name="#sellWaytemp.validityTime" format="yyyy-MM-dd" />
										</s:if>
										<s:else>	
										<div id="vatime<s:property value="#s.count"/>"></div>							
											<script type="text/javascript">
												$(document).ready(function(){
													var uom ='<s:date name="#sellWaytemp.payTime" format="yyyy-MM-dd" />';
													var num=
													uom=new Date(uom.replace(/-/g,"/"));
													uom.setDate(uom.getDate()+parseInt('<s:if test="#sellWaytemp.validityNum==0" >
																		<s:date name="#sellWaytemp.validityTime" format="yyyy-MM-dd" />
																			</s:if>
																				<s:else>
																			<s:property value="#sellWaytemp.validityNum"/>
																			</s:else>'));
													uom =uom.getFullYear() + "-" + (uom.getMonth()+1) + "-" + uom.getDate();
													//+" "+uom.getHours()+":"+uom.getMinutes()+":"+uom.getSeconds()
													$("#vatime<s:property value='#s.count'/>").html(uom);
												});
											</script>									
											
										</s:else>
									</td>
									<td>
										<s:date name="#sellWaytemp.stopSellTime" format="yyyy-MM-dd" />
									</td>
									<td>
										<s:date name="#sellWaytemp.dropTime" format="yyyy-MM-dd" />
									</td>
									<td>
										<s:date name="#sellWaytemp.stopServiceTime" format="yyyy-MM-dd" />
									</td>								
								</tr>			
							</s:iterator>
						</table>
					</form>
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif"/></td>
				<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
				<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
			</tr>
		</table>
	</div>
</body>
</html>