<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>销售机会库</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	<!--
		$(function(){
			var hh = "";
			for(var i=1; i<24; i++){
				if(i<10){
					hh = hh + "<option value='0"+i+":00:00'>0"+i+":00:00</option>"
				}else if(i<23){
					hh = hh + "<option value='"+i+":00:00'>"+i+":00:00</option>"
				}else{
					hh = hh + "<option value='23:59:59'>23:59:59</option>"
				}
			}
			$("#start_hh").append(hh);
			$("#end_hh").append(hh);
			$("#end_hh").attr("value","23:59:59");
			var startHH = "<s:property value="queryChanceCondition.startTime.substring(11)"/>";
			var endHH = "<s:property value="queryChanceCondition.endTime.substring(11)"/>";
			if(startHH!=''){
				$("#start_hh").attr("value",startHH);
			}
			if(endHH!=''){
				$("#end_hh").attr("value",endHH);
			}
		})
		
			//全选
		function checkAll(listform,checkSign){
		 var f = document.getElementById(listform);
			with(f){
				for(var i=0;i<f.length;i++){
					if(elements[i].type=="checkbox"){
						if(elements[i].disabled==false){
							elements[i].checked=checkSign.checked;
						}
					}
				}
			}
		}
		

	
		function search(){
		
			var url = "<%=contextPath%>/crm/crmChance!searchChanceList.action?queryChanceCondition.currentPage=1"
			var startTime = $("#startTime").val();
			if(startTime!=''){
				var start_hh = $("#start_hh").val();
				url = url + "&queryChanceCondition.startTime=" + startTime +" "+ start_hh;
			}
			
			var endTime = $("#endTime").val();
			if(endTime!=''){
				var end_hh = $("#end_hh").val();
				url = url + "&queryChanceCondition.endTime=" + endTime +" "+ end_hh;
			}
			
			var webName = $("#webName").val();
			if(webName!=''){
				url = url + "&queryChanceCondition.webName=" + webName;
			}
			
			var origin = $("#origin").val();
			if(origin!=''){
				url = url + "&queryChanceCondition.origin=" + origin;
			}

			var subjectId = $("#subjectId").val();
			if(subjectId!=''){
				url = url + "&queryChanceCondition.subjectId=" + subjectId;
			}
			
			var $mobile = $("#mobile").val();
			var mobile = $.trim($mobile);
			if(mobile!=''){
				url = url + "&queryChanceCondition.mobile=" + mobile;
			}
			
			var $userName = $("#userName").val();
			var userName = $.trim($userName);
			if(userName!=''){
				userName = encodeURIComponent(encodeURIComponent(userName));
				url = url + "&queryChanceCondition.userName=" + userName;
			}
			
			var messtatus=document.getElementById('messtatus');
			if(messtatus.checked){
				url = url + "&queryChanceCondition.mesStatus=1";
			}
			
			var salesStatus = $("#salesStatus").val();
			if(salesStatus!=''){
				url = url + "&queryChanceCondition.salesStatus=" + salesStatus;
			}
			
			var $email = $("#email").val();
			var email = $.trim($email);
			if(email!=''){
				url = url + "&queryChanceCondition.email=" + email;
			}
			
			var consultStatus = $("#consultStatus").val();
			if(consultStatus!=''){
				url = url + "&queryChanceCondition.consultStatus=" + consultStatus;
			}
			
			var endCommStatus = $("#endCommStatus").val();
			if(endCommStatus!=''){
				url = url + "&queryChanceCondition.endCommStatus=" + endCommStatus;
			}

			var $chanceNum1 = $("#chanceNum1").val();
			var chanceNum1 = $.trim($chanceNum1);
			if(chanceNum1!=""){
				if(isNaN(chanceNum1)||chanceNum1<0){
					alert("咨询次数必须是正整数！");
					return ;
				}else{
					url = url + "&queryChanceCondition.chanceNum1=" + chanceNum1;
				}
			}

			var $chanceNum2 = $("#chanceNum2").val();
			var chanceNum2 = $.trim($chanceNum2);
			if(chanceNum2!=""){
				if(isNaN(chanceNum2)||chanceNum2<0){
					alert("咨询次数必须是正整数！");
					return ;
				}else{
					url = url + "&queryChanceCondition.chanceNum2=" + chanceNum2;
				}
			}
			window.location = url;
		}
		

		//情况查询条件
		function clear_form(){
			$("#start_hh").attr("value","00:00:00");
			$("#end_hh").attr("value","23:59:59");
			$("#startTime").attr("value","");
			$("#endTime").attr("value","");
			$("#webName").attr("value","");
			$("#origin").attr("value","");
			$("#subjectId").attr("value","");	
			$("#mobile").attr("value","");
			$("#userName").attr("value","");
			$("#salesStatus").attr("value","");
			$("#email").attr("value","");
			$("#consultStatus").attr("value","");
			$("#endCommStatus").attr("value","");
			$("#chanceNum1").attr("value","");
			$("#chanceNum2").attr("value","");
			$('#messtatus').attr('checked','');
		}
		
		function checkOutExcel()
		{
			
			var currentPage='<s:property value="pageQuery.currentPage"/>';
			if(currentPage>0){
	     		window.location='<%=contextPath%>/crm/crmChance!exportExcelFileChance.action?pageQuery.currentPage=<s:property value="pageQuery.currentPage"/>';
	     	}else{
		     	var url = "${pageUrlParms}";
		     	var param = url.substring(url.indexOf("?"), url.length-1);
	     		window.location='<%=contextPath%>/crm/crmChance!exportExcelFileChance.action'+param;
	     	}
	     	$('#exportExcel').attr('disabled',true);
	     	window.setTimeout(exportEnable,5000);
	     	
	     	
		}
		function exportEnable(){
		 $('#exportExcel').attr('disabled',false);
		}

		//批量领取机会
		function getBatchChance() {
			var ids = [];
			var arrEle = document.getElementsByName("chanceId");
			var len = arrEle.length;
			for(var i=0;i<len;i++){
				if(arrEle[i].checked==true){
					ids.push(arrEle[i].value);
				}
			}
			if(ids.length==0){
				alert("请选中您要领取的销售机会！");
				return ;
			}
			var lingquButton = $("#lingqu");
			lingquButton.attr("disabled", true);
			lingquButton.attr("value","领取中...");
			url = "<%=contextPath%>/crm/crmChance!getBatchChance.action";
			$.post(url, {ids: ids}, function(data){
				if(data.jumpType==true){
					alert(data.returnMessage);
					$("input[name=chanceId]:checkbox").attr('checked', false);
					$("input[name=all]:checkbox").attr('checked', false);
					window.location.reload();
				}else{
					alert(data.returnMessage);
				}
				lingquButton.attr("disabled", false);
				lingquButton.attr("value","领取所选");
			}, 'json');
		}

		//单个销售机会领取
		function getChance(id) {
			url = "<%=contextPath%>/crm/crmChance!getBatchChance.action";
			$.post(url, {ids: id}, function(data){
				if(data.jumpType==true){
					alert(data.returnMessage);
					window.location.reload();
				}else{
					alert(data.returnMessage);
				}
			}, 'json');
		}
		
	//-->
	</script>
</head>
<body>
<div style="width:1460px;">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">销售机会库</font>
			</td>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<form action="<%=contextPath%>/dis/back!addSensWord.action" method="post" onsubmit="return check()"  >
				<input type="hidden" name="page.currentPage" value="1"/><!-- 添加后回到第一页 -->
				<div  class="msg-yw">
				<table border="0" width="75%" cellpadding="0"  cellspacing="1" class="lists">
					<tr>
						<td width="9%" class="lists_tright">创建开始时间：</td>
						<td width="24%" class="lists_tleft">
							<input type="text" value="<s:property value="queryChanceCondition.startTime.substring(0,10)"/>" id="startTime" onclick="WdatePicker()"/>
							<select name="queryChanceCondition.start_hh" id="start_hh">
								<option value="00:00:00">00:00:00</option>
					  </select> </td>
						<td width="8%" class="lists_tright">创建结束时间：</td>
						<td width="31%" class="lists_tleft">
							<input type="text" value="<s:property value="queryChanceCondition.endTime.substring(0,10)"/>" id="endTime" onclick="WdatePicker()"/> 
							<select name="queryChanceCondition.end_hh" id="end_hh">
								<option value="00:00:00">00:00:00</option>
					  </select> </td>
						<td width="9%" class="lists_tright">域 名 来 源：</td>
						<td width="19%" class="lists_tleft">
							<select name="queryChanceCondition.webName" id="webName">
							<option value="">请选择...</option>
							<option value="highso.cn" <s:property value="queryChanceCondition.webName=='highso.cn'?'selected=\"selected\"':''"/>>highso.cn</option>
							<option value="highso.org" <s:property value="queryChanceCondition.webName=='highso.org'?'selected=\"selected\"':''"/>>highso.org</option>
							<option value="highso.org.cn" <s:property value="queryChanceCondition.webName=='highso.org.cn'?'selected=\"selected\"':''"/>>highso.org.cn</option>
							<option value="highso.com.cn" <s:property value="queryChanceCondition.webName=='highso.net'?'selected=\"selected\"':''"/>>highso.com.cn</option>
							<option value="highso.net.cn" <s:property value="queryChanceCondition.webName=='highso.net.cn'?'selected=\"selected\"':''"/>>highso.net.cn</option></select>
					  </td>
					</tr>
					
					<tr>
						<td class="lists_tright">机 会 来 源：</td>
						<td class="lists_tleft">
							<select name="queryChanceCondition.origin" id="origin">
							<option value="">请选择...</option>
							<option value="1" <s:property value="queryChanceCondition.origin==1?'selected=\"selected\"':''" />>自然注册</option>
							<option value="2" <s:property value="queryChanceCondition.origin==2?'selected=\"selected\"':''" />>乐语在线</option>
							<option value="4" <s:property value="queryChanceCondition.origin==4?'selected=\"selected\"':''" />>自然留言</option>
							<option value="6" <s:property value="queryChanceCondition.origin==6?'selected=\"selected\"':''" />>Callin</option>
							</select>
						</td>
						<td class="lists_tright">咨 询 次 数：</td>
						<td class="lists_tleft">
							<input type="text" id="chanceNum1" value="<s:property value="queryChanceCondition.chanceNum1"/>" size="9" maxlength="9" style="ime-mode:disabled" />
							 至 <input type="text" id="chanceNum2" value="<s:property value="queryChanceCondition.chanceNum2"/>" size="9" maxlength="9" style="ime-mode:disabled" /> 次
						</td>
						<td class="lists_tright">所 属  项目：</td>
						<td class="lists_tleft">
							<s:select list="subjectList" headerKey=""
								headerValue="选择项目..." listKey="subjectId"
								listValue="subjectName" name="queryChanceCondition.subjectId"
								id="subjectId" listTitle="subjectName">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td class="lists_tright">手  机号 码：</td>
						<td class="lists_tleft"><input type="text" style="ime-mode:disabled" maxlength="11" value="<s:property value="queryChanceCondition.mobile"/>" id="mobile" /></td>
						<td class="lists_tright">销 售 坐 席：</td>
						<td class="lists_tleft"><input type="text" maxlength="50" value="<s:property value="queryChanceCondition.userName"/>" id="userName" /></td>
						<td class="lists_tright">销售机会状态：</td>
						<td class="lists_tleft">
							<select name="queryChanceCondition.salesStatus" id="salesStatus">
							<option value="">请选择...</option>
							<option value="1" <s:property value="queryChanceCondition.salesStatus==1?'selected=\"selected\"':''" />>未注册</option>
							<option value="2" <s:property value="queryChanceCondition.salesStatus==2?'selected=\"selected\"':''" />>已注册</option>
							<option value="3" <s:property value="queryChanceCondition.salesStatus==3?'selected=\"selected\"':''" />>已下单</option>
							<option value="4" <s:property value="queryChanceCondition.salesStatus==4?'selected=\"selected\"':''" />>已购买</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td class="lists_tright">邮 箱 地 址：</td>
						<td class="lists_tleft"><input type="text" style="ime-mode:disabled" maxlength="50" value="<s:property value="queryChanceCondition.email"/>" id="email" /></td>
						<td class="lists_tright">咨 询 状 态：</td>
						<td class="lists_tleft">
							<select name="queryChanceCondition.consultStatus" id="consultStatus">
							<option value="">请选择...</option>
							<option value="1" <s:property value="queryChanceCondition.consultStatus==1?'selected=\"selected\"':''" />>未联系</option>
							<option value="2" <s:property value="queryChanceCondition.consultStatus==2?'selected=\"selected\"':''" />>首次</option>
							<option value="3" <s:property value="queryChanceCondition.consultStatus==3?'selected=\"selected\"':''" />>回访</option>
							<option value="4" <s:property value="queryChanceCondition.consultStatus==4?'selected=\"selected\"':''" />>库存回访</option>
							</select>
						</td>						
						<td class="lists_tright">沟 通 状 态：</td>
						<td class="lists_tleft">
							<select name="queryChanceCondition.endCommStatus" id="endCommStatus" >
							<option value="">请选择...</option>
							<option value="1" <s:property value="queryChanceCondition.endCommStatus==1?'selected=\"selected\"':''" />>空号</option>
							<option value="2" <s:property value="queryChanceCondition.endCommStatus==2?'selected=\"selected\"':''" />>通话中</option>
							<option value="3" <s:property value="queryChanceCondition.endCommStatus==3?'selected=\"selected\"':''" />>再联系</option>
							<option value="4" <s:property value="queryChanceCondition.endCommStatus==4?'selected=\"selected\"':''" />>测试</option>
							<option value="5" <s:property value="queryChanceCondition.endCommStatus==5?'selected=\"selected\"':''" />>正常接通</option>
							<option value="6" <s:property value="queryChanceCondition.endCommStatus==6?'selected=\"selected\"':''" />>未接通</option>
							<option value="7" <s:property value="queryChanceCondition.endCommStatus==7?'selected=\"selected\"':''" />>假号码</option>
							<option value="8" <s:property value="queryChanceCondition.endCommStatus==8?'selected=\"selected\"':''" />>已购买</option>
							</select>
						</td>
						
					</tr>
					
					<tr align="center" style="height: 50px">
						<td class="lists_tright">报考指导短信：</td>
						<td class="lists_tleft"><input type="checkbox" id="messtatus" /></td>
						<td colspan="4"> 
							<input type="button" value="查询" onclick="search()" />
							<input type="button" value="清空" onclick="clear_form()"/>
							<input type="button" id="exportExcel" value="导出" onclick="checkOutExcel()"/>
						</td>
					</tr>
				</table>
				</div>				
			</form>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>

		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
					
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="lists_tright" width="5%">批量操作：</td>
						<td class="lists_tleft">
							<input type="button" id="lingqu" value="领取所选" onclick="getBatchChance()"/>
						</td>
					</tr>
			</table>
			
			<form action="" name="chanceForm" id="chanceForm">
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr >
					
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top">
						<font class="lists_fleft">销售机会列表</font>
					</td>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
					
				</tr>
				<tr>
					<td  class="lists_bor">
					</td><td>
					
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td width="5%" class="crm_tableFL"><input type="checkbox" name="all" onclick="checkAll('chanceForm',this);" />ID</td>
						<td width="5%">手机</td>
						<td width="7%">Email</td>
						<td width="7%">项目</td>
						<td width="6%">域名</td>
						<td width="5%">机会状态</td>
						<td width="4%">来源</td>
						<td width="4%">咨询状态</td>
						<td width="6%">销售原坐席</td>
						<td width="4%">用户状态</td>
						<td width="5%">登录次数</td>
						<td width="5%">流量来源</td>
						<td width="7%">创建时间</td>
						<td width="7%">最后指派时间</td>
						<td width="7%">最后沟通时间</td>
						<td width="5%">最后沟通状态</td>
						<td width="6%">支付/订单数</td>
						<td width="5%">操作</td>
					</tr>

				<s:iterator value="page.pageResult" var="chance" >
					<tr>
						<td class="crm_tableFL"><input type="checkbox" name="chanceId" <s:property value="#chance.fcount1>0?'disabled=\"disabled\"':''"/> value='<s:property value="#chance.id"/>'/><s:property value="#chance.id"/></td>
						<td><s:property value="#chance.mobile"/></td>
						<td><a href="<%=contextPath%>/crm/crmChance!getUserCardById.action?userId=<s:property value="#chance.userId"/>"><s:property value="#chance.email!=null?#chance.email:'无'"/></a></td>
						<td><s:property value="#chance.subjectName"/></td>
						<td><s:property value="#chance.webName!=null?#chance.webName:'无'"/></td>
						<td>
							<s:if test="#chance.email==null||#chance.email.trim()==''">
								未注册
							</s:if>
							<s:elseif test="#chance.fcount1>0">
								已购买
							</s:elseif>
							<s:elseif test="#chance.fcount2>0">
								已下单
							</s:elseif>
							<s:else>
								已注册
							</s:else>
						</td>
						<td>
						<s:if test="#chance.origin==0">--</s:if>
				        <s:if test="#chance.origin==1">自然注册</s:if>
				        <s:if test="#chance.origin==2">乐语在线</s:if>
				        <s:if test="#chance.origin==4">自然留言</s:if>
				        <s:if test="#chance.origin==6">CallIn</s:if>
						</td>
						<td>
							<s:if test="#chance.consultStatus==1">
								未联系
							</s:if>
							<s:elseif test="#chance.consultStatus==2">
								首次
							</s:elseif>
							<s:elseif test="#chance.consultStatus==3">
								回访
							</s:elseif>
							<s:elseif test="#chance.consultStatus==4">
								库存回访
							</s:elseif>
						</td>
						<s:if test="#chance.userName==null">
							<td style="color: red;">未指派</td>
						</s:if>
						<s:else>
							<s:if test="#chance.endCommTime==null">
								<td style="color: red;"><s:property value="#chance.userName"/></td>
							</s:if>
							<s:else>
								<td style="color: green;"><s:property value="#chance.userName"/></td>
							</s:else>
						</s:else>
						<td><s:property value="#chance.autoGiveup==1?'放弃':'自然回库'"/></td>
						<td><s:property value="#chance.loginTimes"/></td>
						<td><s:property value="#chance.cusWebFrom"/></td>
						<td><s:date name="#chance.chanceSTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<s:if test="#chance.chanceUTime!=null">
								<s:date name="#chance.chanceUTime" format="yyyy-MM-dd HH:mm:ss"/>
							</s:if>
							<s:else>
								无
							</s:else>
						</td>
						<td>
							<s:if test="#chance.endCommTime!=null">
								<s:date name="#chance.endCommTime" format="yyyy-MM-dd HH:mm:ss"/>
							</s:if>
							<s:else>
								无
							</s:else>
						</td>
						<td>
							<s:if test="#chance.endCommStatus==1">
								空号
							</s:if>
							<s:elseif test="#chance.endCommStatus==2">
								通话中
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==3">
								再联系
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==4">
								测试
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==5">
								正常接通
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==6">
								未接通
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==7">
								假号码
							</s:elseif>
							<s:elseif test="#chance.endCommStatus==8">
								已购买
							</s:elseif>
							<s:else>
								无
							</s:else>
						</td>
						<td>
							<s:if test="#chance.fcount2>0&&#chance.fcount1>0">
								<a href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="#chance.cusId"/>&queryContractCondition.sellType=0" style="color: red">
									<s:property value="#chance.fcount1"/>/<s:property value="#chance.fcount2"/>已付款</a>
							</s:if>
							<s:elseif test="#chance.fcount2>0">
								<a href="<%=contextPath%>/finance/backContract!getContractList.action?contract.cusId=<s:property value="#chance.cusId"/>&queryContractCondition.sellType=0" style="color: green;">
									<s:property value="#chance.fcount1"/>/<s:property value="#chance.fcount2"/>未付款</a>
							</s:elseif>
							<s:else>
								<s:property value="#chance.fcount1"/>/<s:property value="#chance.fcount2"/>
							</s:else>
							<s:if test="#chance.fcount4>0">
								<img style="width:12px;height:10px;" src="<%=contextPath%>/back/images/huo_2.png">
							</s:if>
							<s:elseif test="#chance.fcount3>0">
								<img style="width:12px;height:10px;" src="<%=contextPath%>/back/images/huo_1.png">
							</s:elseif>
						</td>
						<td>
							<s:if test="#chance.fcount1>0">
								领取机会
							</s:if>
							<s:else>
								<a href='javascript:void(0);' onclick="getChance(<s:property value="#chance.id"/>)">领取机会</a>
							</s:else>
							</br>
							<a href='<%=contextPath%>/crm/crmChance!toAddRecord.action?crmChanceId=<s:property value="#chance.id"/>&&userId=<s:property value="#chance.userId"/>&&scanType=2'>查看详情</a>
						</td>
					</tr>
				</s:iterator>
				</table>
					
					
					
					
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
					<td class="lists_bottom">
						<jsp:include page="/back/jsp/common/showPage.jsp" />
					</td>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
				</tr>
			</table>
			</form>
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
</div>
	</body>
</html>