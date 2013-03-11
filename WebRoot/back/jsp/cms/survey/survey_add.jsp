<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加调查问卷</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" />
<script type="text/javascript">
	var qsNo = 1;
	$().ready(function() {
		$("#surveyForm").validate({});
	})
	
	function addSQ() {
		var tbl = $("#surveyTbl");
		//问题序号行
		var notr = $("<tr></tr>").appendTo(tbl);
		var notd1 = $("<td><b>问题"+qsNo+++"</b></td>").appendTo(notr);
		var notd2 = $("<td class=\"lists_tleft\"><button onclick=delSQ(this)>删除此问题</button></td>").appendTo(notr);
		//问题内容行
		var contenttr = $("<tr></tr>").appendTo(tbl);
		var contenttd1 = $("<td>问题内容：</td>").appendTo(contenttr);
		var contenttd2 = $("<td class=\"lists_tleft\"><textarea cols=40 rows=3 name=sqContents ></textarea></td>").appendTo(contenttr);
		//问题类型行
		var typetr = $("<tr></tr>").appendTo(tbl);
		var typetd1 = $("<td>问题类型：</td>").appendTo(typetr);
		var typetd2 = $("<td class=\"lists_tleft\"><select name=sqTypes onchange=selChange(this)><option value=0>单选</option><option value=1>多选</option><option value=2>文字</option></select></td>").appendTo(typetr);
		//问题选项行
		var optionstr = $("<tr></tr>").appendTo(tbl);
		var optionstd1 = $("<td>问题选项：</td>").appendTo(optionstr);
		var optionstd2 = $("<td class=\"lists_tleft\"><textarea rows=6 cols=40 name=sqOptions ></textarea>&nbsp;&nbsp;请每个选项以英文输入法下的“#opt:”开始</td>").appendTo(optionstr);
		
	}
	
	function selChange(sel) {
		var tbl = $("#surveyTbl");
		var index = $(sel).parent().parent().attr("rowIndex");
		var tr = $(tbl.find("tr")[index+1]);
		if(sel.value == 2) {
			if(tr.is(":show")) {
				tr.find("td:eq(1)>textarea").text("-");
				tr.hide();
			}
		} else {
			if(tr.is(":hidden")) {
				tr.find("td:eq(1)>textarea").text("");
				tr.show();
			}
		}
	}
	
	function onSubmit() {
		if(qsNo == 1) {
			alert("请您添加问题。");
			return;
		}
		if( $("#surveyForm").valid() && checkSQ()) {
			document.surveyForm.submit();
		}
	}
	
	function checkSQ() {
		var flag = true;
		$.each($("textarea[name=sqContents], textarea[name=sqOptions]"),function () {
			if($.trim(this.value) == "") {
				alert("问题内容和问题选项不能为空。");
				flag = false;
				return false;
			}
		});
		return flag;
	}
	
	function delSQ (btn) {
		if(window.confirm("您确定要删除这个问题吗?")) {
			var index = $(btn).parent().parent().attr("rowIndex");
			
			for(var i=3; i>0; i--) {
				$("#surveyTbl tr:eq(" + (index + i) + ")").remove();
			}
			$(btn).parent().parent().remove();
			
			var length = $("#surveyTbl tr").length
			if(length>2) {
				var no = 1;
				for(var i=2; i<length-1; i+=4) {
					$("#surveyTbl tr:eq(" + i + ")").find("td:first > b").html("问题" + no++);
				}
			}
			qsNo--;
		}
	}
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/cms/sq!addSurvey.action" method="post" enctype="multipart/form-data" name="surveyForm" id="surveyForm" onsubmit="return false;">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加问卷调查(*请不要在一个小时内添加或修改两套问卷)</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="surveyTbl">
				<tr>
					<td colspan="2">
						<font color="#678197"><b>填写问卷信息(*请不要在一个小时内添加或修改两套问卷)</b>
						</font>
					</td>
				</tr>
				<tr>
					<td width="200">
						问卷名称
						<font color="#ff0000">*</font>
					</td>
					<td class="lists_tleft">
						<input type="text" name="surveyQst.surveyName" id="surveyName" maxlength="30" class={required:true}/>
					</td>
				</tr>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td width="200">
						添加问题
					</td>
					<td class="lists_tleft" colspan="2">
						<input type="button" onclick="addSQ();" class="button" value="添加问题"/>
					</td>
				</tr>
				<tr><td align="center" colspan="3"><input type="submit" onclick="onSubmit()" value="提交"/><input type="button" onclick="history.go(-1)" value="返回"/></td></tr>
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
</body>
</html>
