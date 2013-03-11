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


	function changeData() {
		changeStartHH('<s:property value="startHH"/>');
		changeEndHH('<s:property value="endHH"/>');
		$("#cusFromUrl").val(${customer.cusFromUrl});
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
	function allCheck(cb) {
		$("input[name=cks]:checkbox").attr('checked', cb.checked);
	}
	function ckIdsUpdate(){
		
		var sta = document.getElementById("sta1").value;
		var cks = document.getElementsByName("cks");
		var a=0;
		if(sta=='-1'){
			alert("请选择评价状态！");
		}else{
			for(var i=0;i<cks.length;i++){
				
				if(cks[i].checked==true){
					document.ckFm.submit();
					
				}else{
					a++;
				}
			}
		}
		if(a==cks.length){
			alert("请选择评价！");
		}
	}
	function resetKey() {
		$("#startHH").val(0);
		$("#endHH").val(0);
		$("#verifyStartHH").val(0);
		$("#verifyEndHH").val(0);
		$("#sta").val(-1);
		$("#sta1").val(-1);
		$("#subjectId").val(0);
		document.getElementById("tle").value="";
		document.getElementById("startTime").value="";
		document.getElementById("endTime").value="";
		document.getElementById("verifyStartTime").value="";
		document.getElementById("verifyEndTime").value="";
		
	}
</script>
	</head>
	<body onload="changeData()">
		<div>
			<form name="fm"
				action="<%=contextPath%>/ass/assess!getAssessByCondition.action"
				method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">选择课程所属分类</font>
							<font class="lists_fright"> </font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr>
									<td>
										标题:
										<input type="text" name="assess.assTitle" id="tle" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 状态:
										<select id="sta" name="sta">
											<option value="-1" id="op0">
												请选择
											</option>
											<option value="0">
												未审
											</option>
											<option value="1">
												发布
											</option>
											<option value="2">
												新稿
											</option>
											<option value="3">
												删除
											</option>
										</select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业名称:
										<s:select id="subjectId" name="assess.subjectId"
											list="subList" listKey="subjectId" listValue="subjectName"
											headerValue="---请选择---" headerKey="0" theme="simple">
										</s:select>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										发布时间:
										<input type="text" name="startTime" readonly id="startTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${startTime}" width="10" />
										<select name="startHH" id="startHH">
											<option value=" 00:00:00" id="op0">
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
										--
										<input type="text" name="endTime" readonly id="endTime"
											onclick="WdatePicker()" onfocus="inputOnFocus()"
											value="${endTime}" width="10" />
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
									</td>
								</tr>
								<tr>
									<td colspan="2">
										处理时间:
										<input type="text" name="verifyStartTime" readonly
											id="verifyStartTime" onclick="WdatePicker()"
											onfocus="inputOnFocus()" value="${verifyStartTime}"
											width="10" />
										<select name="verifyStartHH" id="verifyStartHH">
											<option value=" 00:00:00" id="op0">
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
										--
										<input type="text" name="verifyEndTime" readonly
											id="verifyEndTime" onclick="WdatePicker()"
											onfocus="inputOnFocus()" value="${verifyEndTime}" width="10" />
										<select name="verifyEndHH" id="verifyEndHH">
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
									</td>

								</tr>

								<tr align="center">
									<td colspan="4">
										<s:submit value="提交" />
										<input name="reset" type="button" onclick="resetKey();" value="重置"/>
									</td>
								</tr>
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
		<form name="ckFm" action="<%=contextPath%>/ass/assess!updateAssStatus.action" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
			<tr >
				<td width="12" class="lists_bor">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
				</td>
				<td>
					<font class="lists_fright">
						<table class="lists_fleft" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td></td>
								<td>
									批量处理：
									<select id="sta1" name="sta1">
											<option value="-1" id="op0">
												请选择
											</option>
											<option value="0">
												未审
											</option>
											<option value="1">
												发布
											</option>
											<option value="2">
												新稿
											</option>
											<option value="3">
												删除
											</option>
									</select>
									<input type="button" name="aa" value="确定" onclick="ckIdsUpdate();" />
								</td>
							</tr>
						</table> </font>
				</td>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
				</td>
			</tr>
			<tr>
				<td width="12" class="lists_bor">
				</td>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="lists_info" onmouseover="changeto()"
						onmouseout="changeback()">
						<tr class="lists_infobg">
							<td>
								<input type="checkbox" name="c1" onclick="allCheck(this);" />
							</td>
							<td>
								编号
							</td>
							<td>
								ID
							</td>
							<td>
								项目
							</td>
							<td>
								评价标题
							</td>
							<td>
								评分
							</td>
							<td>
								学员账号
							</td>
							<td>
								视频课程标题
							</td>
							<!-- 
							<td>
								讲师
							</td>
							 -->
							<td>
								状态
							</td>
							<td>
								发布时间
							</td>
							<td>
								处理时间
							</td>
							<td>
								操作
							</td>
						</tr>
						<s:iterator value="page.pageResult" id="assessDto" status="status">
							<tr>
								<td>
									<input type="checkbox" name="cks"
										value='<s:property value="#assessDto.id"/>' />
								</td>
								<td>
									<s:property value="#status.count" />
								</td>
								<td>
									<s:property value="#assessDto.id" />
								</td>
								<td>
									<s:property value="#assessDto.subjectName" />
								</td>
								<td>
									<s:property value="#assessDto.assTitle" />
								</td>
								<td>
									<s:property value="#assessDto.assLeavel" />
								</td>
								<td>
									<s:property value="#assessDto.email" />
								</td>
								<td>
									<s:property value="#assessDto.kpointName" />
								</td>
								<!-- 
								<td>
									<s:property value="#assessDto.status" />
								</td>
								-->
								<td>
									<s:if test="#assessDto.status==0">
										未审
										</s:if>
									<s:if test="#assessDto.status==1">
										发布
										</s:if>
									<s:if test="#assessDto.status==2">
										新稿
										</s:if>
									<s:if test="#assessDto.status==3">
										删除
										</s:if>
								</td>
								<td>
									<s:date name="#assessDto.assTime" format="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<s:date name="#assessDto.verifyTime"
										format="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<a
										href="<%=contextPath%>/ass/assess!getAssessById.action?aType=1&&assess.id=<s:property value="#assessDto.id" />">查看</a>
									<a
										href="<%=contextPath%>/ass/assess!getAssessById.action?assess.id=<s:property value="#assessDto.id" />">修改</a>
								</td>
							</tr>
						</s:iterator>
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
					<jsp:include page="/back/jsp/common/showPage.jsp" />
				</td>
				<td>
					<img src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>
