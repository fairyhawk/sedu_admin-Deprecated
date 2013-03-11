<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>反馈信息列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/jquery.jqplot.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/examples.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		
<script>
		$().ready(function(){
			initArea(1, 0);
			initArea($("#sel_province").find("option:eq(0)").val(), 1); 
		}); 

	/**城市字典初始化**/
	function initArea(id, index){
		var parentId = 1;
		if(id != null && id != 0 && !isNaN(id)) {
			parentId = id;
		}
		$.ajax({
			url : "<%=contextPath%>" + "/cus/areaWeb!getAreasByParentId.action",
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
		$(html).appendTo("#sel_province");
		} else if(index == 1) {
		$("#sel_city").html("");
		$("<option value='0'>-请选择-</option>" + html).appendTo("#sel_city");
		} else {
		}
		},
		error : function(error) {
			alert('error'+error.responseText);
		}
		});
		} 
		/**验证规则可以不输入但要合法**/
		function validate(){
			var email=$("#email").val();
			var qq = $("#qq").val();
			var mobile = $("#mobile").val();
			var description = $("#description").val();
			if(email != null && $.trim(email).length > 0){
				if(email.length > 30){
					alert("邮箱字符不能大于30个！");
		  	  		return;
				}
			}
			if(qq != null && $.trim(qq).length > 0){
				if($.trim(qq).length > 12){
					alert("qq号码不可大于12位");
					return;
				}
				if(isNaN($.trim(qq))){
					alert("qq号码必须为数字");
					return;
				}
			}
			
			if(mobile != null && $.trim(qq).length > 0){
				if($.trim(mobile).length > 20){
					alert("电话号码不可大于20位");
					return;
				}
			}
			
			if(description != null && $.trim(description).length > 0){
				if($.trim(mobile).length > 50){
					alert("问题描述不可大于50位");
					return;
				}
			}
			$("#f1").submit();
		}
	
	/**跳转至反馈信息详细**/
	function viewFeed(id){
		if(id==0||id==""||id==null){
			alert("该用户不存在！");
			return;
		}else{
			window.location.href = "<%=contextPath%>/fbc/feedback!getFeedById.action?feedback.id=" + id;
		}
	}
	
	/**删除单个反馈信息**/
	function delFeed(id){
		if(id==0||id==""||id==null){
			alert("该用户不存在！");
			return;
		}else{
			if(confirm("确定要删除此条记录?")){
				window.location.href = "<%=contextPath%>/fbc/feedback!delFeedById.action?feedback.id=" + id;
			}
		}
	}
	
	/**清空表单**/
	function clearF(){
		$("#f1")[0].reset();
	}
</script>
	</head>
	<body>
		<div>
			<form name="searchForm" method="post" id="f1">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
						<td class="lists_top">
							<font class="lists_fleft">反馈信息</font>
							<font class="lists_fright"> </font>
						</td>
						<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
					</tr>
					<tr>
						<td class="lists_bor"></td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="lists_tright">
												所在省:
												</td>
												<td class="lists_tleft">
													<select name="condition.province"  onchange="initArea(this.value, 1)" id="sel_province">
													<option value="-1">-请选择-</option>
												</select>&nbsp;&nbsp;&nbsp;&nbsp;
												所在市:
													<select name="condition.city"   id="sel_city">
													<option value="-1">-请选择-</option>
												</select>&nbsp;&nbsp;&nbsp;&nbsp;
												专业名称:
													<select name="condition.subject_id" id="subjectId" style="width:80px" id="subject">
														<option value="-1">-请选择-</option>
							            				<option value="3">会计证</option>
														<option value="1">人力</option>
														<option value="7">注册会计师</option>
														<option value="5">司法</option>
														<option value="8">证券</option>
														<option value="9">一级建造师</option>
														<option value="10">高级会计师</option>
														<option value="11">公务员考试</option>
														<option value="2">心理咨询师</option>
														<option value="12">经济师考试</option>
														<option value="13">全国研究生统一入学考试</option>
														<option value="14">初级会计职称</option>
														<option value="15">中级会计职称</option>
														<option value="16">二级建造师</option>
													</select>&nbsp;&nbsp;&nbsp;&nbsp;
													运营商:
													<select name="condition.sp" id="sp" style="width:80px" id="sp">
														<option value="-1">-请选择-</option>
						            					<option value="1">中国电信</option>
						            					<option value="2">联通网通</option>
						            					<option value="3">移动铁通</option>
						            					<option value="4">其它</option>
													</select>&nbsp;&nbsp;&nbsp;&nbsp;
													带宽:
													<select name="condition.bandwidth" id="bandwidth" style="width:80px" id="bandwidth">
														<option value="-1">-请选择-</option>
									            		<option value="1">不清楚</option>
									            		<option value="2">64K</option>
									            		<option value="3">512K</option>
									            		<option value="4">1M</option>
									            		<option value="5">2M-4M</option>
									            		<option value="6">4M以上</option>
									            		<option value="7">其它</option>
													</select>&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
											</tr>
											<tr>
												<td class="lists_tright">
												邮箱:
												</td>
												<td class="lists_tleft">
													<input type="text" name="condition.email" maxlength="30" size="10" id="email"/>&nbsp;&nbsp;&nbsp;&nbsp;
													qq:
													<input type="text" name="condition.qq" maxlength="10" size="10" id="qq"/>&nbsp;&nbsp;&nbsp;&nbsp;
													联系电话:
													<input type="text" name="condition.mobile" maxlength="20" size="12" id="mobile"/>&nbsp;&nbsp;&nbsp;&nbsp;
													描述信息:
													<input type="text" size="10" maxLength="50" name="condition.description" id="description"/>&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="button" value="查询" onclick="validate();" />&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="button" value="清空" onclick="clearF();"></input>
												</td>
											</tr>	
										</table>
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
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top">
						<font class="lists_fleft"></font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>
					<td class="lists_bor"></td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
							<tr class="lists_infobg">
								<td width="3%">
									<font style="font-weight: bold">序号</font>
								</td>
								<td width="3%">
									<font style="font-weight: bold">ID</font>
								</td>
								<td width="10%">
									<font style="font-weight: bold">用户邮箱</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">用户qq</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">专业名称</font>
								</td>
								<td width="8%">
									<font style="font-weight: bold">电话</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">所在省</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">所在市</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">运营商</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">带宽</font>
								</td>
								<td width="10%">
									<font style="font-weight: bold">问题描述</font>
								</td>
								<td width="10%">
									<font style="font-weight: bold">提交时间</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">解决部门</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">解决方案</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">解决时间</font>
								</td>
								<td width="5%">
									<font style="font-weight: bold">操作</font>
								</td>
							</tr>
							<s:iterator value="page.pageResult" id="feedback" status="i">
								<tr>
									<td>
										<s:property value="#i.count" />
									</td>
									<td>
										<s:property value="#feedback.id" />
									</td>
									<td>
										<s:property value="#feedback.email" />
									</td>
									<td>
										<s:property value="#feedback.qq" />
									</td>
									<td>
										<s:property value="#feedback.subjectname" />
									</td>
									<td>
										<s:property value="#feedback.mobile" />
									</td>
									<td>
										<s:property value="#feedback.provincename" />
									</td>
									<td>
										<s:property value="#feedback.cityname" />
									</td>
									<td>
										<s:if test="#feedback.sp == 1">
											中国电信
										</s:if>
										<s:if test="#feedback.sp == 2">
											联通网通
										</s:if>
										<s:if test="#feedback.sp == 3">
											移动铁通
										</s:if>
										<s:if test="#feedback.sp == 4">
											其它
										</s:if>	
									</td>
									<td>
										<s:if test="#feedback.bandwidth == 1">
											不清楚
										</s:if>
										<s:if test="#feedback.bandwidth == 2">
											64K
										</s:if>
										<s:if test="#feedback.bandwidth == 3">
											512K
										</s:if>
										<s:if test="#feedback.bandwidth == 4">
											1M
										</s:if>
										<s:if test="#feedback.bandwidth == 5">
											2M-4M
										</s:if>
										<s:if test="#feedback.bandwidth == 6">
											4M以上
										</s:if>
										<s:if test="#feedback.bandwidth == 7">
											其它
										</s:if>				
									</td>
									<td>
										<s:property value="#feedback.description" />
									</td>
									<td>
										<s:date name="#feedback.createtime"  format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:if test='#feedback.soldept == null '>
										
										</s:if>
										<s:else>
											<s:if test='#feedback.soldept == "1"'>
												客服中心
											</s:if>
											<s:if test='#feedback.soldept == "2"'>
												销售部
											</s:if>
											<s:if test='#feedback.soldept == "3"'>
												远程技术部
											</s:if>
										</s:else>
									</td>
									<td>
										<s:if test='#feedback.soltype == null'>
											
										</s:if>
										<s:else>
											<s:property value="#feedback.soltype"/>
										</s:else>
									</td>
									<td>
										<s:if test='#feedback.soltime == null'>
											
										</s:if>
										<s:else>
											<s:date name="#feedback.soltime"  format="yyyy-MM-dd HH:mm:ss"/>
										</s:else>
									</td>
									<td>
										<s:if test="#feedback.status==0">
											<a href="javascript:void(0);" onclick="viewFeed(<s:property value="#feedback.id" />);return false;">回复</a>&nbsp;&nbsp;
										</s:if>
										<a href="javascript:void(0);" onclick="delFeed(<s:property value="#feedback.id" />);return false;">删除</a>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
					<td class="lists_tright lists_bor2">
					</td>
				</tr>
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
					<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
				</tr>
			</table>
	</body>
</html>