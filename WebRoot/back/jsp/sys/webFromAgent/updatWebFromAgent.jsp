<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<meta http-equiv="imagetoolbar" content="no">
	<SCRIPT src="<%=contextPath%>/back/script/jquery-1.3.2.js"></SCRIPT>
	<SCRIPT src="<%=contextPath%>/back/script/jquery.datepick.js"></SCRIPT>
	<SCRIPT src="<%=contextPath%>/back/script/jquery.js"></SCRIPT>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
    <title>尚德网络教育平台——后台管理系统</title>
	<script type="text/javascript">
		function updatefromAgent(){
    		var fId = document.getElementById("webFrom").value;
			var aId = document.getElementById("webAgent").value;
			var Id = document.getElementById("id").value;
			if(fId.indexOf("\x20")!=-1){
				alert("不能有空字符");
				return;
			}
		    if(aId.indexOf("\x20")!=-1){
				alert("不能有空字符");
				return;
			}
			if(fId==""){
				alert("请填写推广来源");
				return;
			}else if(fId.length>30){
				alert("推广来源不能超过30个字符");
				return;
			}else if(aId==""){
				alert("请填写推广名称");
				return;
			}else if(aId.length>30){
				alert("推广名称不能超过30个字符");
				return;
			}else{
			    $.ajax({
					url : "<%=contextPath%>/sys/webFromAgent!toUpdateFromAgentCheck.action",
					data : {
					webFrom : fId,
					webAgent : aId,
					id : Id
					},
					type : "post",
					cache : false,
					dataType : "json",
					error : function (error) {
		   				 alert(error.responseText);
					},
					success:update
					});
	    	}
 		}
    	
    	
		function update(result){
    		var updateweb=eval(result.returnMessage);
    		if(updateweb[0]==null){
    			document.webFromAgentFormUpdate.action="<%=contextPath%>/sys/webFromAgent!updateWebFromAgent.action";
    			document.webFromAgentFormUpdate.submit();
    		}else{
    			alert("此推广来源已存在，请重新命名！");
    		}
    	}
    	function goback(){
    		window.history.back(-1);
    	}
    	
	</script>
  </head>
  <body>
    <!--显示修改开始-->	
			<div align='center' id="updateWebfa" class="web_fa_box">
			<div class="web_fa_info">
				<span class="web_fa_tit" id="title">修改推广设置</span>
			</div>	
				 <form id="webFromAgentFormUpdate" name="webFromAgentFormUpdate" action="<%=contextPath%>/sys/webFromAgent!updateWebFromAgent.action" method="post">
				<div id="webfromagent"  class="web_fa_in">
					<input type="hidden" id="id" name="id" value="<s:property value='webFromAgent.id'/>" />
					<table width="100%" id="webfromagenttblUpdate">
						<tr height="70px">
						<td width="40%" align="center">部门：</td>
						<td>
						<select name="typeId" id="typeId" style="width:150px">
							<option value="1" <s:if test="webFromAgent.typeId==1"> selected</s:if> >市场部门</option>
							<option value="2" <s:if test="webFromAgent.typeId==2"> selected</s:if> >项目部门</option>							
						</select>
						</td>
						</tr>
						<tr height="10px">
						<td colspan="2" height="10px" ></td>
						</tr>	
						<tr height="70px">
						<td width="40%" align="center">推广来源：<br/> (webFrom)</td>
						<td><input name="webFrom" id="webFrom" style="width:200px;height:30px" type="text" value="<s:property value='webFromAgent.webFrom'/>" /></td>
						</tr>
						<tr height="10px">
						<td colspan="2" height="5px" ></td>
						</tr>
						<tr height="70px">
						<td width="40%" align="center">备注：<br/>（webFrom）</td>
						<td>
						<input type="text" id="webFromInfo" name="webFromInfo" style="width:200px;height:30px" value="<s:property value='webFromAgent.webFromInfo'/>"/>
						</td>
						</tr>
						<tr height="10px">
						<td colspan="2" height="10px" ></td>
						</tr>
						<tr height="70px">
						<td width="40%" align="center">推广名称：<br/> (webAgent)</td>
						<td><input name="webAgent" id="webAgent" style="width:200px;height:30px" type="text" value="<s:property value='webFromAgent.webAgent'/>"/></td>
						</tr>
						<tr height="10px">
						<td colspan="2" height="10px" ></td>
						</tr>
						<tr height="70px">
						<td width="40%" align="center">备注：<br/>（webAgent）</td>
						<td>
						<input type="text" id="webAgentInfo" name="webAgentInfo" style="width:200px;height:30px" value="<s:property value='webFromAgent.webAgentInfo'/>"/>
						</td>
						</tr>
						<tr style="height: 50px;">
						<td width="18%" height="50px" align="center" colspan="2">
							<input type="button" value="确定" onclick="updatefromAgent()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="返回" onclick="goback()"/>
						</td>						
						</tr>
						
					</table>
				</div>
			</form>
		</div> 
		<!--显示修改结束-->
  </body>
</html>
