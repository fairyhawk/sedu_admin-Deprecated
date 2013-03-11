<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
	<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
	<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	<script type="text/javascript">
		$().ready(function() {
		$("#resolveUpdate").validate();
			// 初始化 Kindeditor
    	        KE.show({
    	                    id : 'resContent',
    	                    resizeMode : 1,
    	                    allowPreviewEmoticons : false,
    	                    allowUpload : true,
    	                    syncType : 'auto',
    	                    urlType : 'absolute',
    	                    imageUploadJson : 'http://tp.highso.cn:8080/upload!exam.action',
    	                    allowFileManager : false,
    	                    items : ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|', 'selectall', '-','title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|', 'image', 'media', 'advtable', 'hr', 'emoticons', 'link', 'unlink']
    	                });
    	               })
    	 //返回解析列表
    	 function cancle(ksnId){
    	 	document.forms["resolveUpdate"].action='<%=contextPath %>/kno/resolve!resolveList.action?ksnId='+ksnId
			document.forms["resolveUpdate"].submit();
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
        <form name="resolveUpdate" id="resolveUpdate" action='<%=contextPath %>/kno/resolve!resolveUpdate.action?ksnId=<s:property value="ksnId"/>&resId=<s:property value="resId"/>' method="post">
        <table cellspacing="1" cellpadding="0" border="0" width="100%" class="lists_info">
        	<tr><td>专业项目：</td><td style="text-align:left"><s:property value="sys.subjectName"/></td></tr>
        	<tr>
            <td>解析人：</td><td style="text-align:left"><input type="text" name="resolve.resKeyword" value='<s:property value="resolve.resPerson"/>'/></td>
            </tr>
            <tr>
            <td>关键字：</td><td style="text-align:left"><input type="text" name="resolve.resKeyword" value='<s:property value="resolve.resKeyword"/>'/></td>
            </tr>
            <tr>
            <td><font color="#ff0000">*</font>解析内容</td><td><textArea id="resContent" name="resolve.resContent" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;" class="{required:true,maxlength:300}"><s:property value="resolve.resContent"/></textArea></td>
            </tr>
            <tr>
            <td colspan="2" style="text-align:left">
            <s:if test="resolve.isFirst==1">
            <input type="checkbox" name="resolve.isFirst" value="1" checked="checked" />设置为首选解析
            </s:if><s:else>
            <input type="checkbox" name="resolve.isFirst" value="1"  />设置为首选解析
            </s:else>
            </td>
            </tr>
            <tr>
            <td colspan="2" style="text-align:left"><input type="submit"  value="确定"/><input type="button" value="取消" onclick="cancle(<s:property value="ksnId"/>);"/></td>
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
  </body>
</html>
