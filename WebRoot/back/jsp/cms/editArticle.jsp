<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
<title>修改文章</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<link rel="StyleSheet" href="<%=contextPath %>/style/dtree.css" type="text/css" />
<script src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
<script src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/dtree.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
   <script type="text/javascript">
	$(document).ready(function() {
		$("#commentForm").validate();
		// 初始化 Kindeditor
    	        KE.show({
    	                    id : 'txt_topic',
    	                    resizeMode : 1,
    	                    allowPreviewEmoticons : false,
    	                    allowUpload : true,
    	                    syncType : 'auto',
    	                    urlType : 'absolute',
    	                    imageUploadJson : 'http://tp.highso.cn:8080/upload!article.action',
    	                    allowFileManager : false,
    	                    items : ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|', 'selectall', '-','title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|', 'image','flash', 'media', 'advtable', 'hr', 'emoticons', 'link', 'unlink'
]
    	                });
	});
	function changeColumnValue(id,name){
		document.getElementById("columnid").value = id;
		document.getElementById("columnName").value = name;
		document.getElementById(obj).style.display="none";
		document.getElementById("itemArticle_titleColor").style.display="block";
		document.getElementById("itemArticle_titleFont").style.display="block";
		document.getElementById("itemArticle_titleSize").style.display="block";
	}
	function showColumn(){
		obj="columnList";
		document.getElementById(obj).style.display="block";
		document.getElementById("itemArticle_titleColor").style.display="none";
		document.getElementById("itemArticle_titleFont").style.display="none";
		document.getElementById("itemArticle_titleSize").style.display="none";
	}
	function closeColumn(){
		obj="columnList";
		document.getElementById(obj).style.display="none";
		document.getElementById("itemArticle_titleColor").style.display="block";
		document.getElementById("itemArticle_titleFont").style.display="block";
		document.getElementById("itemArticle_titleSize").style.display="block";
	}
	function FCKeditor_OnComplete(editorInstance) {
			window.status = editorInstance.Description;
		}
		
		
	function tijiao(){	
				
				document.articleform.action="<%=contextPath%>/cms/article!readArticle.action";
				document.articleform.target = "_blank";
				document.articleform.submit();
				document.articleform.target = "";
				document.articleform.action="<%=contextPath%>/cms/article!editArticle.action";
				
	}
	function che(){
		var v = document.getElementsByName("h") ;
		for (var i=0; i<v.length; i++){
　　			if(v[i].value==3){
				document.getElementById("tj").checked=true;
			}
			if(v[i].value==6){
				document.getElementById("rm").checked=true;
			}
			if(v[i].value==9){
				document.getElementById("zd").checked=true;
			}
　　		} 
	}
	
</script>
<style>
<!--
	.w20_m-p6 input{
		width: 20px;
		height:15px;
	}
	#selectT select{
		float: left;margin-left:5px;display:inline;
	}
-->
</style>
</head>
<body onload="che()">
<div>
	<form action="article!editArticle.action" id="commentForm" method="post" enctype="multipart/form-data" name="articleform">
    <input name="itemArticle.articleId" type="hidden" value='<s:property value="itemArticle.articleId"/>' />
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">修改文章</font>
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
			    		<td width="10%">所属栏目：</td>
			    		<td class="lists_tleft" width="90%" colspan="3"> 
			    			<input id="columnid" type="hidden" name="itemArticle.columnId" value="<s:property value="itemColumn.columnId"/>" />
			    			<input id="columnName" type="text" name="columnName" value="<s:property value="itemColumn.columnName"/>" class="required" onclick="showColumn();" readonly="readonly" />
			    			<div id="columnList" class="columnList">
							    <script type="text/javascript">
								<!--
								addArticle = new dTree('addArticle','<%=contextPath%>/back/images/dtree');
								addArticle.add(0,-1,'栏目列表 <a href="javascript:closeColumn();">关闭</a>');
								<s:iterator value="columnList">
								<s:if test="isFinally==0">
									addArticle.add('<s:property value="columnId"/>','<s:property value="parentId"/>','<s:property value="columnName"/>','','<s:property value="explanation"/>');
								</s:if>
								<s:else>
									addArticle.add('<s:property value="columnId"/>','<s:property value="parentId"/>','<s:property value="columnName"/>','javascript:changeColumnValue(\'<s:property value="columnId"/>\',\'<s:property value="columnName"/>\')','<s:property value="explanation"/>');
								</s:else>
								</s:iterator>
								document.write(addArticle);
								//-->
								</script>
						    </div>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td width="10%">文章标题：</td>
			    		<td class="lists_tleft" width="90%" colspan="3">
			    			<input type="text" name="itemArticle.title" value="${itemArticle.title }" class="required" minlength="2" maxlength="800" />
			    		</td>
			    	</tr>
			    	<tr>
			    		<td width="10%">标题属性：</td>
			    		<td class="lists_tleft" width="90%" colspan="3">
				    		<div id="selectT" style="width:500px;">
				    			<s:select list="colorMap" name="itemArticle.titleColor" headerKey="" headerValue="请选择" ></s:select>
				    			<s:select list="fontMap" name="itemArticle.titleFont" headerKey="" headerValue="请选择"></s:select>
				    			<s:select list="fontSizeMap" name="itemArticle.titleSize" headerKey="" headerValue="请选择"></s:select>
				    		</div>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td width="10%">关键字：</td>
			    		<td class="lists_tleft" width="90%" colspan="3"> <input type="text" value="${itemArticle.meta }" name="itemArticle.meta" maxlength="800" /></td>
			    	</tr>
			    	<tr>
			    		<td width="10%">文章作者：</td>
			    		<td class="lists_tleft" width="40%" colspan="2"> <input type="text" value="${itemArticle.author}" name="itemArticle.author" class="required" minlength="1" maxlength="100" value="sunland" /></td>
			    		</tr>
			    	<tr>
			    		<td width="10%">跳转连接：</td>
			    		<td class="lists_tleft" width="90%" colspan="3"> <input type="text" value="${itemArticle.articleUrl }" name="itemArticle.articleUrl" maxlength="300" /></td>
			    	</tr>
			    	<tr>
			    		<td width="10%">文章简介：</td>
			    		<td class="lists_tleft" width="90%" colspan="3">
			    			<textarea name="itemArticle.articleDesc" class="height50" maxlength="800" >${itemArticle.articleDesc }</textarea>
						</td>
			    	</tr>
			    	
			    	<tr>
			    		<td width="10%">标题图片：</td>
			    		<td class="lists_tleft" width="50%" ><s:file name="upLoadPhoto"/></td>
			    		<td width="10%">文章属性：</td>
		    			<td width="40%" class="lists_tleft">
		    			<div style="color:#000000;">
							<span>推荐</span><span class="w20_m-p6"><input type="checkbox" name="articletype" id="tj" value="推荐"  /></span>
							<span>置顶</span><span class="w20_m-p6"><input type="checkbox" name="articletype" id="zd" value="置顶" /></span>
							<span>热门</span><span class="w20_m-p6"><input type="checkbox" name="articletype" id="rm" value="热门"  /></span>
						</div>
					</td>
			    	</tr>
			    	
			    	<tr>
			    		<td width="10%">发布时间：</td>
			    		<td class="lists_tleft" width="40%" colspan="2"> <input type="text" value="<s:date name="itemArticle.createDate" format="yyyy-MM-dd HH:mm:ss"/>" name="itemArticle.createDate" class="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"/></td>
			    		
			    	</tr>
			    	<tr>
			    		<td width="10%">点击数：</td>
			    		<td class="lists_tleft" width="40%" colspan="2"> <input type="text" value="${itemArticle.clickTimes }" name="itemArticle.clickTimes" class="required number" value="0" minlength="1" maxlength="10" /></td>
						
			    	</tr>
			    	
					<tr>
			    		<td width="10%">文章内容：</td>
			    		<td class="lists_tleft" width="90%" colspan="3">
			    		  <textarea id="txt_topic" name="itemArticle.content" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;">
			    		  <s:property value="itemArticle.content" escape="false"/>
			    		  </textarea>
						</td>
			    	</tr>
			    	<tr>
			    		<td style="text-align: center;" colspan="4"><input class="submit" type="submit" value="提交"/><input class="submit" type="button" value="文章阅览" onclick="tijiao();" /></td>
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
<c:forEach var="vr" items="${aticletype}">
	<input type="hidden" name="h" value="${vr }" />
</c:forEach>
</body>
</html>
