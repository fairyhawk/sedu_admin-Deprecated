<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>查看问题信息</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
	    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">查看问题</font>
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
			<s:form action="repbl!editProblem" method="post" name="f1">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
			
				<tr height="30">
					<td>
						问题标题
					</td>
					<td class="lists_tleft">
						<s:property value='problem.pblTitle'/>
					</td>
				</tr>
				<input type="hidden" name="problem.pblId" value="<s:property value="problem.pblId"/>">
				
				<tr height="30">
					<td>
						问题类别
					</td>
					<td class="lists_tleft">
						<select name="subjectId" id="subjectId">
							<option value="0">---请选择---</option>
							<option value="3">会计</option>
							<option value="1">人力</option>
							<option value="5">司法</option>
							<option value="7">注册会计师</option>
							<option value="8">证券</option>
						</select>
					</td>
				</tr>
				<tr><td colspan="2" align="center"><input type="submit" name="b1" value="确定"/></td></tr>
			</table>
			</s:form>
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
</div>
<script language="javascript">
var subjectId=document.getElementById("subjectId");
		for (i=0;i<subjectId.options.length;i++)
		{ 
		     if(subjectId.options[i].value=='${problem.subjectId}')
		     {
		         subjectId.options[i].selected=true;break;
		     } 
		}
</script>
</body>
</html>
