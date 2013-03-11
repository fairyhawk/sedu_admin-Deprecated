<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/dtree.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<link rel="StyleSheet" href="<%=contextPath%>/back/style/dtree.css"
			type="text/css" />
		<script type="text/javascript">
			$().ready(function() {
	 			$("#addProfessionalForm").validate();
			});
			
			function AddPro() {
			var version = document.getElementById("professional.pVersion").value;
			var scCount = document.getElementById("professional.pCSubject").value;
			if(version==null){
				alert("请输入年份！");
			}
			if(isNaN(version)) {//is not a number
				alert("版本时间只能为数字！");
				return false;
			}
			if(isNaN(scCount)){
				alert("科目数量只能为数字！");
				return false;
			}
		}
		</script>

	</head>
	<body>
		<div class="main_right">
			<s:form action="professional!professionalAdd" method="post"
				name="addProfessionalForm" id="addProfessionalForm" onsubmit="return AddPro()">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">当前位置:[预设项管理>>新建专业项目]</font>
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
							<table width="50%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">

								<!--  <tr>
									<td align="left">
										<font color="red">*</font>索引编号：
									</td>
									<td>
										<input type="text" name="professional.pIndex"
											id="professional.pIndex"
											value="<s:property value="professional.pIndex"/>"
											class="{required:true,minlength:3,maxlength:50}" />
									</td>
								</tr>-->
								<tr>
									<td align="left">
										<font color="red">*</font>专业名称：
									</td>
									<td>
										<input type="text" name="professional.pName"
											id="professional.pName"
											value="<s:property value="professional.pName"/>"
											class="{required:true,minlength:4,maxlength:50}" />
									</td>
								</tr>
								<tr>
									<td align="left">
										<font color="red">*</font>包含科目数：
									</td>
									<td>
										<input type="text" name="professional.pCSubject"
											id="professional.pCSubject"
											value="<s:property value="professional.pCSubject"/>"
											class="{required:true,minlength:2,maxlength:50}" />
									</td>

								</tr>
								<tr>
									<td align="left">
										<font color="red">*</font>版本时间：
									</td>
									<td>
										<input type="text" name="professional.pVersion"
											id="professional.pVersion"
											value="<s:property value="professional.pVersion"/>"
											class="{required:true,minlength:4,maxlength:50}" />
									</td>
								</tr>
								<tr>
									<td align="left">
										<font color="red">*</font>专业说明：
									</td>
									<td>
										<textarea name="professional.pNote" rows="5" cols="18" id="professional.pNote" maxsize="255" eos_displayname="描述" chname="描述" nullable="no" datatype="text"></textarea>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<s:submit value=" 提 交 " />
										
										<input type="button" name="but" value=" 返 回 " onclick="history.go(-1)"/>
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
			</s:form>
		</div>
	</body>
</html>
