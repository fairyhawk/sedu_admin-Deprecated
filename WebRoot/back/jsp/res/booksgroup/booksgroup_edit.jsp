<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.shangde.edu.sys.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>信息修改——书籍组基本信息设置</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/validate.js"></script>
		<script language="javascript" type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			
			$().ready(function() {
	 			$("#editBooksGroupForm").validate();
			});
		</script>
	</head>
	<body>
<div>
	<s:form action="booksgroup!editBooksGroup" method="post" name="editBooksGroupForm" id="editBooksGroupForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">信息修改——书籍组基本信息设置</font>
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
						<font color="red">*</font>组名称：
					</td>
					<td class="lists_tleft">
						<input type="text" name="booksGroup.bgName" id="booksGroup.bgName"
							value="<s:property value="booksGroup.bgName"/>" class="{required:true,minlength:4,maxlength:50}"/>
					</td>
				</tr>
				<tr>
					<td align="left">
						<font color="red">*</font>组类型：
					</td>
					<td class="lists_tleft">
						<input type="text" name="booksGroup.bgType" id="booksGroup.bgType"
							value="<s:property value="booksGroup.bgType"/>" class="{minlength:4,maxlength:50}"/>
					</td>
				</tr>
	            <tr>
	          		 <td align="left">
						<font color="red">*</font>书籍组描述：
					</td>
					<td class="lists_tleft">
					  <textarea name="booksGroup.bgInfo" rows="5" cols="40" id="booksGroup.bgInfo"
								maxsize="255" eos_displayname="描述" chname="描述" nullable="no"
								datatype="text" class="{required:true,minlength:4,maxlength:200}"><s:property value="booksGroup.bgInfo"/></textarea>
					</td>
	            
	            </tr>
			<s:hidden name="booksGroup.bgId" value="%{booksGroup.bgId}" id="booksGroup.bgId">
			   </s:hidden>
				<tr>
					<td align="center" colspan="2">
					
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
