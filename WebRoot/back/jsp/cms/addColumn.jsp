<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
<script src="<%=contextPath %>/back/script/jQueryValidate/lib/jquery.js" type="text/javascript"></script>
<script src="<%=contextPath %>/back/script/jQueryValidate/jquery.validate.js" type="text/javascript"></script>
<script src="<%=contextPath %>/back/script/jQueryValidate/localization/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#commentForm").validate();
		
	});
	function changeColumnValue(id,name){
		document.getElementById("parentId").value = id;
		document.getElementById("parentColumnName").value = name;
		document.getElementById(obj).style.display="none";
	}
	function showColumn(){
		obj="columnList";
		document.getElementById(obj).style.display="block";
	}
	function closeColumn(){
		obj="columnList";
		document.getElementById(obj).style.display="none";
	}
	function changeLT(i){
		if(i==1){
			document.getElementById("articleTR").style.display="block";
			document.getElementById("article").className="required";
		}else if(i==2){
			document.getElementById("articleTR").style.display="none";
			document.getElementById("article").value="";
			document.getElementById("article").className="";
		}
	}
</script>
<style>
<!--
	input{
		width:200px;
	}
	textarea{
		width:200px;
	}
-->
</style>
</head>
  <body>
<div>
	<form action="column!createNewColumn.action" id="commentForm" method="post">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">添加栏目</font>
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
		    		<td align="right">上级栏目：</td>
		    		<td class="lists_tleft" style="color: #ff0000;"> 
		    			<input id="parentId" type="hidden" name="tempColumn.parentId"  />
		    			<input id="parentColumnName" type="text" name="parentColumnName" class="required" onclick="showColumn();" readonly="readonly" />
		    			<div id="columnList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
						    <script type="text/javascript">
							//<!--
						
							addcolumn = new dTree('addcolumn','<%=contextPath %>/back/images/dtree');
							addcolumn.add(0,-1,'栏目列表 <a href="javascript:closeColumn();">关闭</a>');
							<s:iterator value="columnList">
							addcolumn.add('<s:property value="columnId"/>','<s:property value="parentId"/>','<s:property value="columnName"/>','javascript:changeColumnValue(\'<s:property value="columnId"/>\',\'<s:property value="columnName"/>\')','<s:property value="explanation"/>');
							</s:iterator>
							document.write(addcolumn);
							//-->
							</script>
					    </div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">栏目名称：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;"> 
		    			<input type="text" name="tempColumn.columnName" class="required" minlength="4" maxlength="100" />
		    			<font color="black">专业:</font>
		    			<select name="tempColumn.subjectId">
		    				<option value="0">默认选项</option>
		    				<s:iterator value="subjectInfo"> 
     						<option value="<s:property value="subjectId"/>"><s:property value="subjectName"/></option>
     						</s:iterator>
		    			</select>
		    			
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">栏目说明：</td>
		    		<td width="80%">
		    			<textarea name="tempColumn.explanation" class="required" minlength="4" maxlength="800" style="height:80px;width:99%;"></textarea>
					</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">栏目目录：</td>
		    		<td class="lists_tleft" width="80%"> <input type="text" name="tempColumn.catalog" class="required english" minlength="4" maxlength="100" /></td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">栏目keyWord(关键字)：</td>
		    		<td width="80%">
		    			<textarea name="tempColumn.keyWord" style="height:80px;width:99%;"></textarea>
					</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">栏目meta：</td>
		    		<td width="80%">
		    			<textarea name="tempColumn.meta" class="required" minlength="4" style="height:80px;width:99%;"></textarea>
					</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">是否显示：</td>
		    		<td class="lists_tleft" width="80%">
		    			<div style="color:#000000;">
		    				是<input type="radio" name="tempColumn.columnType" value="1" style="width:20px;" checked="checked"/>
		    				
		    				否<input type="radio" name="tempColumn.columnType" value="0" style="width:20px;"/>
		    			</div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">排序：</td>
		    		<td class="lists_tleft" width="80%"> <input type="text" name="tempColumn.sequence" class="required number" minlength="1" maxlength="20" value="0"/></td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">连接地址：</td>
		    		<td class="lists_tleft" width="80%"> <input type="text" name="tempColumn.linkUrl" maxlength="300"/></td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">生成类型：</td>
		    		<td class="lists_tleft" width="80%">
		    			<select name="tempColumn.staticType">
		    				<option value="0">不生成</option>
		    				<option value="1">栏目生成</option>
		    				<option value="2">数据生成</option>
		    				<option value="3">数据分页</option>
		    			</select>
					</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">是否终级：</td>
		    		<td class="lists_tleft" width="80%">
		    			<div style="color:#000000;">
		    				是<input type="radio" name="tempColumn.isFinally" value="1" style="width:20px;" onclick="changeLT(1);"/>
		    				
		    				否<input type="radio" name="tempColumn.isFinally" value="0" style="width:20px;" checked="checked" onclick="changeLT(2);"/>
		    			</div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="right" width="20%">默认模板：</td>
		    		<td class="lists_tleft" width="80%">
		    			<s:select list="indexTemplateList" name="tempColumn.indexTemplateId" listKey="tmpId" listValue="tmpName" headerKey="0" headerValue="请选择" ></s:select>
					</td>
		    	</tr>
		    	<tr id="articleTR" style="display:none;">
		    		<td align="right" width="20%">文章模板：<br /></td>
		    		<td class="lists_tleft" width="80%">&nbsp;
		    		<s:select list="articleTemplateList" name="tempColumn.articleTemplateId" listKey="tmpId" listValue="tmpName" headerKey="0" headerValue="请选择" ></s:select>
					<br /></td>
		    	</tr>
		    	<tr>
		    		<td></td>
		    		<td align="left"><input class="submit" type="submit" value="提交" style="width:100px;"/><input type="button" value="返回" onclick="history.go(-1)"/></td>
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
