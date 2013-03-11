<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加新模板</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.6.4.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<style>
dl{
float:left;
width:100px;
margin-top:100px;
}
dd {
width:100px;
}
</style>
<script type="text/javascript">
	$(function(){
		// 表单验证
		$("#modifyForm").validate();
		
		// 添加模块
		$("#addModel").click(function(){
			var flag = true;
			$("#allModel").find("option:selected").each(function(i){
				var self = $(this);
				$("#modelList > option").each(function(){
					 if(self.val() == $(this).val()){
						 flag = false;
						 return false;
					 }
				});
				if(flag)
					$("#modelList").append("<option value='"+ self.val() +"' selected>" + self.text() + "</option>");
				else
					return false;
			});
		});
		
		// 向上移动
		$("#moveUp").click(function(){
			var  currObj  = $("#modelList").find("option:selected");
			var  topObj = $("#modelList > option").first();
			if(currObj.length == 1 && currObj.val() != topObj.val()){
				var prevObj = currObj.prev("#modelList > option");
				prevObj.before(currObj);
			}
		});
		
		// 向下移动
		$("#moveDown").click(function(){
			var  currObj  = $("#modelList").find("option:selected");
			var  bottomObj = $("#modelList > option").last();
			if(currObj.length == 1 && currObj.val() != bottomObj.val()){
				var nextObj = currObj.next("#modelList > option");
				nextObj.after(currObj);
			}
		});
		
		// 删除模块
		$("#delModel").click(function(){
			$("#modelList").find("option:selected").remove();
			
		});
		
		// 全部删除
		$("#delAll").click(function(){
			$("#modelList").empty();
		});
		
		// 提交的时候选中所有添加子模块 
		$("#modifyForm").submit(function(){
			$("#modelList > option").each(function(){
				$(this).attr("selected",true);
			});
		});
	});
</script>
</head>
<body>
<div>
	<form action="<%=contextPath %>/sys/modules!doModify.action" method="post" name="modifyForm" id="modifyForm">
	<input type="hidden" name="modules.id" value="${modules.id}"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">修改模板</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td class="lists_tright">
						<font color="red">*</font>模板名：
					</td>
					<td class="lists_tleft">
						<input type="text" name="modules.name" class="{required:true,minlength:4,maxlength:50}" value="${modules.name}" />
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						<font color="red">*</font>选择模块：
					</td>
					<td class="lists_tleft">
						<select style="float:left;height:300px;width:200px;" multiple="multiple" name="mIds" id="modelList" class="{required:true}">
							<s:iterator value="modules.modelList" var="obj">
								<option value="<s:property value="id"/>" selected><s:property value="model_name"/></option>
							</s:iterator>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<dl>
							<dd><input type="button" id="moveUp" value="上移" /></dd>
							<dd><input type="button" id="moveDown" value="下移" /></dd>
							<dd><input type="button" id="addModel" value="<<添加" /></dd>
							<dd><input type="button" id="delModel" value="<<删除" /></dd>
							<dd><input type="button" id="delAll" value="<<全部删除" /></dd>
						</dl>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<select style="height:300px;width:200px;" multiple="multiple" id="allModel">
							<s:iterator value="modelList" var="obj">
								<option value='<s:property value="id"/>'><s:property value="model_name"/></option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						<font color="red">*</font>指定模板路径：
					</td>
					<td class="lists_tleft">
						<font color="red">/static/container/</font><input type="text" name="modules.url" value="${modules.url}" class="{required:true}"/>例如：<font color="red">(aaa)</font>
					</td>
				</tr>
				<tr>
					<td class="lists_tright">
						模板备注：
					</td>
					<td class="lists_tleft">
						<textarea name="modules.desc" style="width:720px;height:100px">${modules.desc}</textarea>
					</td>
				</tr>
				<tr>
					<td class="lists_tright"></td>
					<td class="lists_tleft">
						<input type="submit" value="提交修改"/><input type="button" value="返回" onclick="history.go(-1)"></input>
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
</body>
</html>
