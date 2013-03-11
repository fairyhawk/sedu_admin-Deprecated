<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
	<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	<script type="text/javascript">
	$().ready(function() {
			$("#updateSysNode").validate();
		});
		//选择预设项
		function onchangePreItem(preId){
		$.ajax({  
					url : "<%=contextPath%>/kno/knowledgeSystem!getPreNodeById.action",  
					data : {'preId' : preId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback  
					});
		}
		
		function onchangecallback(result){   
				document.getElementById('preNodeId').options.length = 0;  //清空原有的option 
				$("#preNodeId").html("<option value=-1>请选择</option>");
				var str="<option value=-1>请选择</option>";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].preNodeId+"'>"+result.entity[i].preNodeName+"</option>"  
				}  
				$('#preNodeId').html(str);  
			 }  
		//验证排序序号
		function test(count){
			var a=$('#sortId').val();
			var patrn=/^[0-9]{1,20}$/; 
			if(!patrn.exec(a)){
				alert('序号必须是数字');
				$('#sortId').val(count);
			}
			if(a>count){
				alert('排列序号不能超过'+count);
				$('#sortId').val('<s:property value="sysNode.sortId" />');
			}
		}
	</script>
  </head>
  
  <body>
  <table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">

		<tr class="lists_top">
			<td width="12">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td>
			  <font class="lists_fleft"></font></td>
			<td width="16">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />

			</td>
		</tr>
		
		<tr >
			<td width="12" class="lists_bor">
			</td>
			<td>
  	<form id="updateSysNode" action="<%=contextPath %>/kno/knowledgeSystem!updateSysNode.action" target="rightFrame" method="post" >
  		<table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info">
  			<tr><td>ID:</td><td colspan="2" style="text-align:left">
  			<input type="hidden" name="sysNode.ksnId" value='<s:property value="sysNode.ksnId"/>'/>
  			<input  type="text" name="sysNode.nodeId"   value='<s:property value="sysNode.nodeId" escape="false"/>' disabled="disabled"/>
  			<input  type="hidden" name="sysNode.nodeId"   value='<s:property value="sysNode.nodeId" escape="false"/>' />
  			</td></tr>
  			<tr><td><font color="#ff0000">*</font>名称:</td><td colspan="2" style="text-align:left"><input  type="text" name="sysNode.nodeName"  id="nodeName" value='<s:property value="sysNode.nodeName" />' class="{required:true,maxlength:50}"/></td></tr>
  			<tr><td><font color="#ff0000">*</font>排序:</td><td colspan="2" style="text-align:left"><input  type="text" onblur='test(<s:property value="count" />);' name="sysNode.sortId"  id="sortId" value='<s:property value="sysNode.sortId" />'  /></td></tr>
  			<tr>
  			<td>预设项:</td>
  			<td style="text-align:left"><select name="nodePreMid.preId" id="preId" onchange="onchangePreItem(this.value);" >	
							<option value="-1" > 请选择</option>
							<s:iterator value="preItemlist" id="preItemlist">
								<s:if test="#preItemlist.preId==nodePreMid.preId">
									<option value='<s:property value="#preItemlist.preId"/>' selected="selected"><s:property value="#preItemlist.preName"/></option>
								</s:if>
								<s:else>
									<option value='<s:property value="#preItemlist.preId"/>'><s:property value="#preItemlist.preName"/></option>
								</s:else>
							</s:iterator>
							</select></td>
  			<td><select name="nodePreMid.preNodeId" id="preNodeId" >	
							<option value="-1" > 请选择</option>
							<s:if test="nodePreMid!=null">
							<option value='<s:property value="nodePreMid.preNodeId"/>' selected="selected"><s:property value="nodePreMid.preNodeName"/></option>
							</s:if>
							</select></td>
  			</tr>
  			<tr><td colspan="3" style="text-align:left"><input type="submit"  value="确定"/></td></tr>
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
  </body>
</html>
