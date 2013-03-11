<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/lib.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/right.css" />
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
	</head>
	<body>
	<div class="main_right">
		<h1>信息添加——节基本信息设置</h1>
	<s:form action="section!sectionAdd" method="post" name="addSectionForm"  id="addSectionForm">
				 <table cellpadding="0" cellspacing="1" class="tables"  style="width:600px;height:200px;">
					<s:hidden name="section.pId" value="%{chapter.pId}"></s:hidden>
					<s:hidden name="section.cId" value="%{chapter.cId}"></s:hidden>
					<s:hidden name="section.chId" value="%{chapter.chId}"></s:hidden>
					<tr>
						<th align="left">
							<font color="red">*</font>所属专业：
						</th>
						<td>
							<s:property value="chapter.pId"/>
						</td>
					</tr>
					<tr>
						<th align="left">
							<font color="red">*</font>所属科目：
						</th>
						<td>
							<s:property value="chapter.cId"/>
						</td>
					</tr>
					<tr>
						<th align="left">
							<font color="red">*</font>所属章：
						</th>
						<td>
							<s:property value="chapter.chName"/>
						</td>
					</tr>
					<tr>
						<th align="left">
							<font color="red">*</font>索引编号：
						</th>
						<td>
						    <s:property value="chapter.chIndex"/>
							<input type="text" name="section.sIndex" id="section.sIndex"
								value="<s:property value="section.sIndex"/>" />
							<s:hidden name="chapter.chIndex" value="%{chapter.chIndex}"></s:hidden>	
						</td>
					</tr>
					<tr>
						<th align="left">
							<font color="red">*</font>节名称：
						</th>
						<td>
							<input type="text" name="section.sName" id="section.sName"
								value="<s:property value="section.sName"/>" />
						</td>
					</tr>
		            <tr>
		            	<th align="left">
							<font color="red">*</font>包含节知识单元数：
						</th>
						<td>
						  <input type="text" name="section.sKSubject" id="section.sKSubject"
								value="<s:property value="section.sKSubject"/>" />
						</td>
		            
		            </tr>
		              <tr>
		            	<th align="left">
							<font color="red">*</font>版本时间：
						</th>
						<td>
						 <s:property value="chapter.chVersion"/>
						 <s:hidden name="section.sVersion" value="%{chapter.chVersion}"></s:hidden>
						</td>
		            </tr>
		               <tr>
		            	<th align="left">
							<font color="red">*</font>学科说明：
						</th>
						<td>
						  <textarea name="section.sNote" rows="5" cols="18" id="section.sNote"
									maxsize="255" eos_displayname="描述" chname="描述" nullable="no"
									datatype="text" class="{required:true,minlength:4,maxlength:200}"></textarea>
						</td>
		            </tr>
					<tr>
						<td align="center" colspan="2">
							<s:submit value="提交" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>