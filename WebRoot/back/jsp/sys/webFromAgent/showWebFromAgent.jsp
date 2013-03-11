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
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/css_body.css" />
    <title>尚德网络教育平台——后台管理系统</title>
    
    <script type="text/javascript">
    
    
    	//添加webFromAgent
    	function addWebFromAgent(){
    		lengweb();
    		var fId = document.getElementById("webFrom").value;
			var aId = document.getElementById("webAgent").value;
			var tid = document.getElementById("typeId").value;
			if(fId.indexOf("\x20")!=-1){
				alert("不能有空字符");
				return;
			}
		    if(aId.indexOf("\x20")!=-1){
				alert("不能有空字符");
				return;
			}
			if(tid==0){
				alert("请选择部门");
				return;
			}else if(fId==""){
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
				url : "<%=contextPath%>/sys/webFromAgent!toAddFromAgentCheck.action",
				data : {
				webFrom : fId,
				webAgent : aId
				},
				type : "post",
				cache : false,
				dataType : "json",
				error : function (error) {
	   				 alert(error.responseText);
				},
				success:addfromAgent
				});
    	}
    	}
    	
    	function lengweb(){
    		var finfo = document.getElementById("webFromInfo").value;
    		var ainfo = document.getElementById("webAgentInfo").value;
    		if(finfo.length>150){
    			alert("推广来源不能超过150个字符");
    			return flase;
    		}
    		if(ainfo.length>150){
    			alert("推广名称不能超过150个字符");
    			return flase;
    		}
    	}
    	
    	
    function showAddWebfa(){
		$("#addWebfa").fadeIn();
		$("#wrapback").fadeIn();
		
	}
	
    	
    	function addfromAgent(result){
    		var a=eval(result.returnMessage);
    		if(a[0]==null){
    			document.webFromAgentForm.action="<%=contextPath%>/sys/webFromAgent!createWebFromAgent.action";
    			document.webFromAgentForm.submit();
    			closeAddManager();
    		}else{
    			alert("此推广来源已存在，请重新命名添加");
    		}
    	}
    	
    	function closeAddManager(){
    		$("#addWebfa").fadeOut();
    		$("#wrapback").fadeOut();
    	}
    	
    	//条件查询
    	function searchList(ObjectForm){
    		ObjectForm.action="<%=contextPath%>/sys/webFromAgent!getWebFromAgentByTiaoJian.action";
			ObjectForm.submit();
    	}
    	
    	// 导出Excel
    	function exportExcel(ObjectForm) {
    		ObjectForm.action="<%=contextPath%>/sys/webFromAgent!exportExcel.action";
    		ObjectForm.submit();
    	}
    	
    	function isdelete(url){
    		question = confirm("你确认要删除吗？");
   			 if (question){
    				window.location.href = url;
    			}
    	}
    	
    	
    </script>
    
    
  </head>
  <body>
  <!--显示添加开始-->	
  	<div style="position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5; -moz-opacity:0.5;top:0;left:0;width:100%;height:100%;background:#000;display:none;z-index:4;" id="wrapback">
  	<iframe style="position:absolute;width:100%;height:100%;_filter:alpha(opacity=0);opacity=0;border-style:none;"></iframe>		
		</div>		
	<div id="addWebfa" class="web_fa_add" style="display:none;position:absolute;left:35%;top:16%;z-index:5">
	    <div class="tit">&nbsp;添加推广来源</div>
		<form id="webFromAgentForm" name="webFromAgentForm" action="<%=contextPath%>/sys/webFromAgent!createWebFromAgent.action" method="post" class="con">
			  <table cellspacing="0" cellpadding="0" border="0" class="lists">
			    <tr>
			      <td>
			      <div class="msg-yw">
			      <table cellspacing="1" cellpadding="0" border="0" style="margin:auto;width:400px" class="lists">
			          <tr>
			            <td width="30%" align="center">部门：</td>
			            <td><select name="typeId" id="typeId" style="width:150px">
			                <option selected="selected" value="0">--- 请选择 ---</option>
			                <option value="1">市场部门</option>
			                <option value="2">项目部门</option>
			              </select>
			              </td>
			          </tr>
			          <tr>
			    	  	<td colspan="2"></td>
			    	  </tr>
			          <tr>
			            <td width="40%" align="center">推广来源：<br/>
			              (webFrom)</td>
			            <td><input name="webFrom" id="webFrom" type="text" style="height:30px;"/></td>
			          </tr>
			    	  <tr>
			    	  	<td colspan="2"></td>
			    	  </tr>
			          <tr>
			            <td width="40%" align="center">备注：<br/>（webFrom）</td>
			            <td><input type="text" id="webFromInfo" name="webFromInfo" style="width:200px;height:30px;"/></td>
			          </tr>
			          <tr>
			    	  	<td colspan="2"></td>
			    	  </tr>
			          <tr>
			            <td width="40%" align="center">推广名称：<br/>
			              (webAgent)</td>
			            <td><input name="webAgent" id="webAgent" type="text" style="height:30px;"/></td>
			          </tr>
			          <tr>
			    	  	<td colspan="2"></td>
			    	  </tr>
			          <tr>
			            <td width="40%" align="center">备注：<br/>（webAgent）</td>
			            <td>
							<input type="text" id="webAgentInfo" name="webAgentInfo" style="width:200px;height:30px;"/>
						</td>
			          </tr>
			          <tr>
			    	  	<td colspan="2"></td>
			    	  </tr>
			          <tr style="height: 30px;">
			            <td width="18%" align="center" colspan="2"><input type="button" value="确定" onclick="addWebFromAgent()"/>
			              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			              <input type="button" value="关闭" onclick="closeAddManager()"/></td>
			          </tr>
			        </table>
			        </div>
			        </td>
			      <td class="lists_tright lists_bor2"></td>
			    </tr>
			  </table>
	    </form>
    </div>	
  <!--显示添加结束-->
		
  <!-- 显示查询条件和查询结果信息开始 -->
<div class="main_right">
  <table width="100%" cellspacing="0" cellpadding="0" border="0" class="lists">
    <tr>
      <td style="font-size:0px;width:12px;"><img src="<%=contextPath%>/back/images/tab_03.gif"></td>
      <td class="lists_top"><font class="lists_fleft">推广来源设置&gt;推广类型设置</font> <font class="lists_fright"></font></td>
      <td style="font-size:0px;width:12px;"><img src="<%=contextPath%>/back/images/tab_07.gif"></td>
    </tr>
    <tr>
      <td width="12" class="lists_bor"></td>
      <td>
      <form action="<%=contextPath%>/sys/webFromAgent!getWebFromAgentByTiaoJian.action" id="searchListFrom" name="searchListFrom" method="post">
      <div class="msg-yw">
      <table width="100%" cellspacing="2" cellpadding="0" border="0" class="lists">
          <tr>
            <td height="30">&nbsp;推广来源<br/>(webFrom) </td>
            <td><input type="text" id="webFrom" name="webFrom" value="<s:property value="webFrom"/>" style="width:150" /></td>
            <td>&nbsp;推广名称<br/>(webAgent) </td>
            <td><input type="text" id="webFrom" name="webAgent" value="<s:property value="webAgent"/>" style="width:150" /></td>
            <td>部门 </td>
            <td><s:select name="typeId" id="typeId" cssStyle="width:150px" list="#{'0':'--- 请选择 ---', '1':'市场部门', '2':'项目部门'}" /></td>
            <td>
            	<input type="button" value="搜索" onclick="searchList(document.searchListFrom)">
            	<input type="button" value="导出" onclick="exportExcel(document.searchListFrom)">
            	<input type="hidden" name="queryWebFromAgentCondition.currentPage" value="1" />
            </td>
            <td><input type="button" id="addWebfaButton" value="新建推广来源" onclick="showAddWebfa()"/></td>
          </tr>
        </table>
        </div>
        </form>
        <table width="100%" cellspacing="1" cellpadding="0" border="0" class="lists_info">
          <tr class="lists_top lists_infobg" height="30px">
            <td width="80px"> 序列号 </td>
            <td width="150px"> 推广来源（webFrom） </td>
            <td width="300px"> 推广来源备注 </td>
            <td width="150px"> 推广名称（webAgent） </td>
            <td width="300px"> 推广名称备注 </td>
            <td width="100px"> 部门 </td>
            <td width="150px"> 创建时间 </td>
            <td width="150px"> 修改时间 </td>
            <td width="100px"> 操作者 </td>
            <td width="100px"> 操作 </td>
          </tr>
          <s:if test="page.pageResult.size()!=0">
            <s:iterator value="page.pageResult" id="webFromAgent" status="status">
              <tr id="webFromAgentList">
                <td><s:property value="(page.currentPage-1)*page.pageSize+#status.count" /><input type="hidden" id="id" name="id" value="<s:property value='webFromAgent.id'/>" /></td>
                <td><s:property value="#webFromAgent.webFrom"/></td>
                <td><s:property value="#webFromAgent.webFromInfo"/></td>
                <td><s:property value="#webFromAgent.webAgent"/></td>
                <td><s:property value="#webFromAgent.webAgentInfo"/></td>
                <td><s:if test="#webFromAgent.typeId==1">市场部门</s:if><s:elseif test="#webFromAgent.typeId==2">项目部门</s:elseif></td>
                <td><s:date name="#webFromAgent.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
																					
                <td><s:date name="#webFromAgent.updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                <td><s:property value="#webFromAgent.loginName" /></td>
                <td><a href='<%=contextPath%>/sys/webFromAgent!getWebFromAgentById.action?id=<s:property value="#webFromAgent.id"/>'>修改</a>&nbsp;&nbsp;<a href="javascript:isdelete('<%=contextPath%>/sys/webFromAgent!deleteWebFromAgentById.action?id=<s:property value="#webFromAgent.id"/>')">删除</a></td>
              </tr>
            </s:iterator>
          </s:if>
          <s:else>
            <tr>
              <td colspan="10"> 不存在！ </td>
            </tr>
          </s:else>
        </table></td>
      <td class="lists_tright lists_bor2"></td>
    </tr>
    <tr>
      <td><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
	  <td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" />
	  </td>
	  <td><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
    </tr>
  </table>
</div>	
  <!-- 显示查询条件和查询结果信息结束 -->
  </body>
</html>
