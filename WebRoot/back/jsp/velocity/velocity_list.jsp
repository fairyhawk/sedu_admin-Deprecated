<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>评价列表</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script language="JavaScript">



	function allCheck(cb) {
		$("input[name=cks]:checkbox").attr('checked', cb.checked);
	}
	
	
	function getDistrict(pId){
		if(pId==-1){
			alert("请选择省份！");
			document.getElementById('sta').options.length = 0;
			$("#sta").html("<option value='-1'>--请选择--</option>");
			return;
		}
		$.ajax({
			url : "<%=contextPath%>/velocity/velocity!getAllProvinceList.action",
			data : {parenId : pId},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
			 	alert("error");
			},
			success : onchangecallback
		});
	}
	function onchangecallback(result){
		document.getElementById('sta').options.length = 0;
		var str="<option value=''>--请选择--</option>";  
			for(var i=0;i<result.entity.length;i++){  
				str+="<option value='"+result.entity[i].id+"' selected='true'>"+result.entity[i].areaName+"</option>"; 	
			}  
		$("#sta").html(str);
		$("#sta").val(-1);
	}
	
	function resetKey() {
		$("#broadBand").val(0);
		$("#network").val(0);
		$("#browser").val(0);
		$("#sta").val(-1);
		$("#area").val(-1);
		$("#subject").val(0);
		document.getElementById("mail").value="";
		document.getElementById("mobile").value="";
		
	}
</script>
	</head>
	<body onload="changeData()">
		<div>
			<form name="fm"
				action="<%=contextPath%>/velocity/velocity!getAllVelocity.action"
				method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
					<tr>
						<td class="td_wed_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
						<td class="lists_top"><font class="lists_fleft">选择课程所属分类</font><font class="lists_fright"> </font></td>
						<td class="td_wed_R"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
					</tr>
					<tr>
						<td class="lists_bor"></td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
								<tr>
									<td>
										邮箱:
										<input type="text" id="mail" name="queryVelocityCondition.mail" id="tle" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										联系电话:
										<input type="text" id="mobile" name="queryVelocityCondition.mobile" id="tle" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 所在省份:
										<s:select id="area" name="queryVelocityCondition.province" list="areaList" listKey="id" listValue="areaName" headerKey="-1" headerValue="---请选择---" onchange="getDistrict(this.value);"></s:select>
										所在地区:
										<select id="sta" name="queryVelocityCondition.district">
											<option value="-1" id="op0">
												---请选择---
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										 专业项目:
										 <s:select id="subject" name="queryVelocityCondition.subject" list="subjectList" listKey="subjectId" listValue="subjectName" headerKey="-1" headerValue="---请选择---" ></s:select>
										&nbsp;&nbsp;&nbsp;&nbsp;浏览器:
										<select name="queryVelocityCondition.browser" id="browser">
											<option value="">
											--请选择--
											</option>
											<option value="IE" id="op0">
												IE
											</option>
											<option value="FireFox" id="op0">
												FireFox
											</option>
											<option value="Goolge" id="op0">
												Goolge
											</option>
											<option value="其他" id="op0">
												其他
											</option>
										</select>
										&nbsp;&nbsp;&nbsp;&nbsp;网络运营商:
										<select name="queryVelocityCondition.network" id="network">
											<option value="">
											--请选择--
											</option>
											<option value="电信" id="op0">
												电信
											</option>
											<option value="联通(网通)" id="op0">
												联通(网通)
											</option>
											<option value="铁通" id="op0">
												铁通
											</option>
											<option value="其他" id="op0">
												其他
											</option>
										</select>
										&nbsp;&nbsp;&nbsp;&nbsp;测速结果:
										<select name="queryVelocityCondition.broadBand" id="broadBand">
											<option value="0">
											--请选择--
											</option>
											<option value="70" id="op0">
												70kb/s以下
											</option>
											<option value="71" id="op0">
												70kn/s以上
											</option>
										</select>
									</td>
								</tr>
								<tr align="center">
									<td>
										<s:submit value="查询" />
										<input name="reset" type="button" onclick="resetKey();" value="清空"/>
									</td>
								</tr>
							</table>
						</td>
						<td class="lists_tright lists_bor2"></td>
					</tr>
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
						<td class="lists_bottom"></td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
					</tr>
				</table>
			</form>
		</div>
		<form name="ckFm" action="<%=contextPath%>/ass/assess!updateAssStatus.action" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
			<tr>
				<td class="td_wed_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
				<td class="lists_top">共有<s:property value="velocityList.size()"/> 个用户参与测试:</td>
				<td class="td_wed_R"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
			</tr>
			<tr>
				<td class="lists_bor">
				</td>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
						<tr class="lists_infobg">
							<td>
								<input type="checkbox" name="c1" onclick="allCheck(this);" />
							</td>
							<td>
								编号
							</td>
							<td>
								邮箱
							</td>
							<td>
								联系电话
							</td>
							<td>
								所在省份
							</td>
							<td>
								所在地区
							</td>
							<td>
								专业项目
							</td>
							<td>
								浏览器
							</td>
							<td>
								网络运营商
							</td>

							<td>
								宽带
							</td>
							<td>
								问题描述
							</td>
							<td>
								提交时间
							</td>
							<td>
								解决部门
							</td>
							<td>
								解决方案
							</td>
							<td>
								解决时间
							</td>
						</tr>
						<s:iterator value="page.pageResult" id="velocity" status="status">
							<tr>
								<td>
								<input type="checkbox" name="cks" onclick="allCheck(this);" />
							</td>
							<td>
								<s:property value="#status.count"/>
							</td>
							<td>
								<s:property value="#velocity.mail"/>
							</td>
							<td>
								<s:property value="#velocity.mobile"/>
							</td>
							<td>
								<s:property value="#velocity.province"/>
							</td>
							<td>
								<s:property value="#velocity.district"/>
							</td>
							<td>
								<s:property value="#velocity.subject"/>
							</td>
							<td>
								<s:property value="#velocity.browser"/>
							</td>
							<td>
								<s:property value="#velocity.network"/>
							</td>

							<td>
								<s:property value="#velocity.broadBand"/>
							</td>
							<td title='<s:property value="#velocity.problem"/>'>
							<s:if test="#velocity.problem!=null && #velocity.problem!=''">
								
									<s:property value="#velocity.problem.substring(0,10)"/>...
								
							</s:if>
							</td>
							<td>
								<s:date name="#velocity.submitTime" format="yyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:property value="#velocity.solveDepartment"/>
							</td>
							<td>
								<s:property value="#velocity.solveScheme"/>
							</td>
							<td>
								<s:date name="#velocity.solveTime" format="yyy-MM-dd HH:mm:ss"/>
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
				<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
				<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
			</tr>
		</table>
		</form>
	</body>
</html>
