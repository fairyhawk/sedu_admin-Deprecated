<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>内部学员导入</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery.blockUI.js"></script>
<style>
	/*弹出窗口*/
.main_container{  border:1px solid #b3b3b3; background:#f4f4f4; padding:20px 0;}
.main_container p{font-family:微软雅黑;font-size:13px;font-weight:normal;font-style:normal;color:#999;padding:4px 0;text-align:center;}
.main_container p span{text-align:left;}
</style>
<script type="text/javascript">
	/*--弹窗显示，异步提交时等待窗口--*/
	function showBlock(numRecord){
		if(numRecord >= 300){
		$("#recordNum").html(numRecord);
        jQuery.blockUI({ 
        	message: $("#mainContainer"), 
        	css: {color:'#fff',border:'3px solid #aaa',backgroundColor:'#FFFFFF',cursor:"default"},
        	overlayCSS: { opacity:'0.5',cursor:"default" }
        	});
		}
	}
	function hideBlock(){
		jQuery.unblockUI();
	}
	/*--验证提交--*/
	function checkContent(){
		var oper = $("#raBatch").val();
		if(oper == 0 || oper == 1){
			validateGift();
		}
		if(oper == 2){
			validateDel();
		}
	}
	
	/*--删除操作验证提交--*/
	function validateDel(){
		try{
			var customerContent = $("#customerContent").val();
			customerContent = $.trim(customerContent);
			if(customerContent.length <= 0){
				alert("请输入导入内容");
				return false;
			}
			var strArray = new Array();
			strArray = customerContent.split("\n");
			for(var i=0; i<strArray.length;i++){
				if(validateEmail(strArray[i]) == false){
					alert("邮箱格式输入不正确");
					return false;
				}
			}
		} catch(e){
			alert("删除用户只输入邮箱");
			return false;
		}
		var params = $("#addForm").serialize();
		$("#customerContent").val('');
		$.ajax({
			url : "/cus/cus!delCusBatch.action",
			data : params,
			type : "post",
			dataType : "json",
			cache : false,
			async : true,
			success : function(result) {
				if(result.returnMessage == "success"){
					$("#numberGift").html("");
					$("#successResult").show();
					   jQuery.each(result.entity, function(i, val) {
						   $("#numberGift").append("删除成功,删除" +i + "表" + val + "条用户记录<br/>");
					    });
				}else if(result.returnMessage == "delnone"){
					$("#numberGift").html("");
					$("#successResult").show();
					$("#numberGift").html("未删除任何记录，以上用户在系统中不存在");
				}else{
					alert("--操作错误--");
				}
			},
			error : function(error) {
				alert("错误操作");
			}
			});
	}
	/*--赠送方式验证提交--*/
	function validateGift(){
		try{
			
			var customerContent = $("#customerContent").val();
			customerContent = $.trim(customerContent);
			
			var isTemp = $("#isTemp").val();
		
			var type = $("#cusType").val();
			if(type <= -1){
				alert("请选择用户类型");
				return false;
			}
			if(customerContent.length <= 0){
				alert("请输入导入内容");
				return false;
			}
			
			if(isTemp != null &&  isTemp == "true"){
				if(type == 0){
					alert("临时账号只能为内部用户");
					return;
				}
			}
			
			var cusSubjectId = $("#subjectId").val();
			if(cusSubjectId <= 0){
				alert("请选择用户注册专业");
				return false;
			}
			var strArray = new Array();
			strArray = customerContent.split("\n");
			for(var i=0; i<strArray.length;i++){
				var recordArray = strArray[i].split("\t");
				if(validateEmail(recordArray[0]) == false){
					alert("邮箱格式不正确");
					return false;
				}
				
				if(validatePwd(recordArray[1]) == false){
					alert("密码输入不规范");
					return false;
				}
				
				if(validateNickname(recordArray[2]) == false){
					alert("用户名输入不正确");
					return false;
				}
				
				if(validateSellWay(recordArray[3]) == false){
					alert("售卖方式输入不正确");
					return false;
				}
			}
		}catch(e){
			alert("输入错误,导入用户输入邮箱、密码、用户名、赠送课程id");
			return false;
		}
			var params = $("#addForm").serialize();
			$("#customerContent").val('');
			$.ajax({
				url : "/cus/cus!importProcess.action",
				data : params,
				type : "post",
				dataType : "json",
				cache : false,
				async : true,
				success : function(result) {
					if(result.returnMessage == "success"){
						hideBlock();
						$("#successResult").show();
						if(result.entity == 0 || result.entity == '0'){
							$("#numberGift").html("操作完成，以上课程学员已存在");
						}else{
							$("#numberGift").html("赠送成功,共赠送" + result.entity + "份课程");
						}
					}else if(result.returnMessage == "invalid"){
						alert(result.entity);
						hideBlock();
					}else{
						hideBlock();
					}
				},
				error : function(error) {
					alert("error");
					hideBlock();
				}
				});
			showBlock(strArray.length);
	}
	
	/*--正则email--*/
	function validateEmail(email){
		var pattern = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
		if(pattern.test(email) != true){
			return false;
		}
		return true;
	}
	
	/*--正则密码--*/
	function validatePwd(pwd){
		var pattern = /^(\w)+$/;
		if(pattern.test(pwd) != true){
			return false;
		}
		return true;
	}
	
	/*--正则用户名--*/
	function validateNickname(nickname){
		var pattern = /^(\w)+$/;
		if(pattern.test(nickname)  != true){
			return false;
		}
		return true;
	}
	
	/*--正则售卖方式--*/
	function validateSellWay(sellWay){
		var array = new Array();
		array = sellWay.split(",");
		for(var i=0; i<array.length;i++){
			var pattern = /^(\d)+$/;
			if(pattern.test(array[i]) != true){
				return false;
			}
		}
		return true;
	}
</script>
</head>
<body>
<div>
	<form name="addForm" id="addForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
          <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
        <td class="lists_top"><font class="lists_fleft">学员批量操作</font></td>
        <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
      </tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td  class="lists_tright">
						<font color="red">*</font>批量操作:
					</td>
					<td colspan="2" class="lists_tleft">
						<select name="raBatch" id="raBatch">
							<option value="0">批量完全赠送</option>
							<option value="1">批量部分赠送</option>
							<option value="2">批量删除用户</option>
						</select>
					</td>
				</tr>
				<tr height="30">
					<td  class="lists_tright">
						<font color="red">*</font>用户类型:
					</td>
					<td colspan="2" class="lists_tleft">
						<select name="cusType" id="cusType"
							style="width: 125px" class="{required:true}">
							<option value="-1">
							---请选择---
							</option>
							<option value="0">
								注册学员
							</option>
							<option value="1">
								内部学员
							</option>
							<option value="2">
								体验账户七天
							</option>
						</select>
					</td>
				</tr>
				
				<tr height="30">
					<td  class="lists_tright">
						<font color="red">*</font>用户注册专业:
					</td>
					<td colspan="2" class="lists_tleft">
						<s:select name="subjectId" id="subjectId" list="subjectList" listKey="subjectId"
									listValue="subjectName" headerKey="-1" headerValue="--请选择专业--" theme="simple">
						 </s:select>
					</td>
				</tr>
				<tr height="30">
					<td  class="lists_tright">
						<font color="red">*</font>是否生成临时体验账号:
					</td>
					<td colspan="2" class="lists_tleft">
						<select name="isTemp" id="isTemp"
							style="width: 125px" class="{required:true}">
							<option value="false">
								不生成
							</option>
							<option value="true">
								生成
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td  class="lists_tright">
						<font color="red">*</font>操作说明:
					</td>
					<td colspan="2" class="lists_tleft">
						<font color="red">
								1、批量部分赠送是指以前学员拥有的课程还将存在，额外加上这次赠送的课程，不会重复。
							<br>
								2、批量完全赠送是指以前学员拥有的课程将被删除掉，只保留这次赠送的课程。
							<br>
								3、批量删除指删除学员及学员相关信息。
							<br>
								4、生成临时体验账户将生成用户临时体验账户，用于前台用户体验
						</font>
					</td>
				</tr>
			<tr>
					<td  class="lists_tright">
						<font color="red">*</font>输入格式：
					</td>
					<td colspan="2" class="lists_tleft">
						<font color="red">
								1、批量删除只输入学员邮箱
							<br>
								2、批量导入输入学员邮箱、密码、用户名、赠送课程
						</font>
					</td>
				</tr>
				<tr height="30">
					<td  class="lists_tright">
						<font color="red">*</font>导入内容:
					</td>
					<td width="40%">
						<textarea name="customerContent" id="customerContent" rows="50" cols="100" align="left" class="{required:true}"></textarea>
					</td>
					<td valign="top" width="40%">
						<table cellpadding="0" cellspacing="1" class="tables" id="successResult" style="display:none">
								<tr>
									<td>
										<font color="red" id="numberGift"></font>
									</td>
								</tr>
								<tr>
									<td>
										<font color="red" id="numberDel"></font>
									</td>
								</tr>
						</table>
					</td>
				</tr>
				<tr height="30">
					<td colspan="3">
						<input type="button" value="提交" onclick="checkContent();"></input>
						<input type="button" value="返回" onclick="history.go(-1)"></input>
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
	<div class="main_container" style="display:none" id="mainContainer">
	<div id=u0_container class="u0_container">
		<div id="u0_img" class="u0_original"></div>
		<div id=u1 class="u1" >
			<div id=u1_rtf>
				<p style="text-align:center;">
					<span style="font-family:微软雅黑;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">共计提交<span id="recordNum"></span>条记录</span>
				</p><p style="text-align:center;">
					<span style="font-family:微软雅黑;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">不要关闭此窗口,请等待系统返回</span>
				</p>
			</div>
		</div>
	</div>
	<div id=u2 class="u2" >
		<div id=u2_rtf>
		</div>
	</div>
	</div>
</div>
</body>
</html>