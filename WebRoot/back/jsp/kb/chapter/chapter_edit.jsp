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
		<h1>信息添加——章基本信息设置</h1>
	<s:form action="chapter!chapterEdit" method="post" name="editChapterForm"  id="editChapterForm">
				 <table cellpadding="0" cellspacing="1" class="tables"  style="width:600px;height:200px;">
					<s:hidden name="chapter.cId" value="%{studycourse.cId}"></s:hidden>
					<s:hidden name="chapter.pId" value="%{studycourse.pId}"></s:hidden>
					<s:hidden name="chapter.chId" value="%{chapter.chId}"></s:hidden>
					<tr>
						<th align="left">
							<font color="red">*</font>所属专业：
						</th>
						<td>
							<s:property value="studycourse.pId"/>
						</td>
					</tr>
					<tr>
						<th align="left">
							<font color="red">*</font>所属科目：
						</th>
						<td>
							<s:property value="studycourse.cName"/>
						</td>
					</tr>
					<tr>
						<th align="left">
							<font color="red">*</font>索引编号：
						</th>
						<td>
						    <s:property value="studycourse.cIndex"/>
							<input type="text" name="chapter.chIndex" id="chapter.chIndex"
								value="<s:property value="chapter.chIndex"/>" />
							<s:hidden name="studycourse.cIndex" value="%{studycourse.cIndex}"></s:hidden>	
						</td>
					</tr>
					<tr>
						<th align="left">
							<font color="red">*</font>章名称：
						</th>
						<td>
							<input type="text" name="chapter.chName" id="chapter.chName"
								value="<s:property value="chapter.chName"/>" />
						</td>
					</tr>
		            <tr>
		            	<th align="left">
							<font color="red">*</font>包含节数：
						</th>
						<td>
						  <input type="text" name="chapter.chStSubject" id="chapter.chStSubject"
								value="<s:property value="chapter.chStSubject"/>" />
						</td>
		            
		            </tr>
		              <tr>
		            	<th align="left">
							<font color="red">*</font>版本时间：
						</th>
						<td>
						 <s:property value="studycourse.cVersion"/>
						 <s:hidden name="chapter.chVersion" value="%{studycourse.cVersion}"></s:hidden>
						</td>
		            </tr>
		               <tr>
		            	<th align="left">
							<font color="red">*</font>学科说明：
						</th>
						<td>
						  <textarea name="chapter.chNote" rows="5" cols="18" id="chapter.chNote"
									maxsize="255" eos_displayname="描述" chname="描述" nullable="no"
									datatype="text" class="{required:true,minlength:4,maxlength:200}"><s:property value="chapter.chNote"/></textarea>
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