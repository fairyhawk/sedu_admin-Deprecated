<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.shangde.edu.sys.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>信息添加——视频组列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/validate.js"></script>
		<script type="text/javascript">
		function resetForm(f){
		document.getElementById("vedioGroup.vgName").value="";
		document.getElementById("vedioGroup.vgType").value="";
		document.getElementById("vedioGroup.vgNfo").value="";
		
		}	
		</script>
	</head>
	<body>
<div>
	<s:form action="booksgroup!addBooksGroupNew" method="post" >
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">请选择书籍组——视频组基本信息</font>
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
					<td align="left">
						<font color="red">*</font>书籍组：
					</td>
					<td>
						<select name="booksGroup.bgId" id="booksGroup.bgId">
						<s:iterator value="booksgroupList" id="booksgroup">
						<option value="<s:property value="bgId" />"><s:property value="bgName" /></option>
						
						</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					
					<td colspan="2">
						<font color="red">*说明：组中已存在的书籍不在重复添加！</font>
					</td>
				</tr>
			
				<tr>
					<td colspan="2" align="center">
					<input type="button" value="创建书籍组" onclick="document.location.href='<%=contextPath%>/res/booksgroup!toAddBooksGroup.action'"/>
					    &nbsp;
						<s:submit value="提交" />
						&nbsp;
						<input type="button" value="返回"
							onclick="document.location.href='<%=contextPath%>/res/booksgroup!getBooksGroupList.action?queryBooksgroupCondition.currentPage=1'" />
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
