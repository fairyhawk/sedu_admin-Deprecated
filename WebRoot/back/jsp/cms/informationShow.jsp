<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>   
<script type="text/javascript" src="<%=importURL%>/js/web/public/information_back.js"></script> 
<!--
<script type="text/javascript" src="<%=contextPath%>/js/web/public/information.js"></script>
-->
</head>
 <body>
 	<div id="rightframe">

	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">资讯内容管理</font>
				<font class="lists_fright"></font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr>
						<td style="width:10%">
							<font color="red">*</font>分类：
						</td>
						<td class="lists_tleft">
							<input type="radio" checked="checked" name="se" id="Consulting"/>资讯
							<!--  
							<select id="sl">								 
								 <option value="0">----请选择----</option>
								 <option value="8">证券从业资格证</option>
								 <option value="7">注册会计师</option>
								 <option value="5">司法考试</option>
								 <option value="3">会计从业资格证</option>
								 <option value="10">高级会计师</option>
								 <option value="2">心理咨询师</option>
								 <option value="12">经济师</option>
								 <option value="1">人力资源管理师</option>
								 <option value="9">一级建造师</option>
								 <option value="11">国家公务员考试</option>
								 <option value="13">研究生考试</option>
								 <option value="14">初级会计职称</option>
								 <option value="15">中级会计职称</option>
								 <option value="16">二级建造师</option>
								 <option value="17">监理工程师</option>
								 <option value="18">造价工程师</option>
								 <option value="100">项目选择</option>
		              		</select>
		              		-->
		              		<select id="sl">
								<option value="0">----请选择----</option>
								<s:iterator id="item" value="#request.lists">
									<option value="<s:property value="#item.subjectId" />">
										<s:property value="#item.subjectName"/>
									</option>
								</s:iterator>
								<option value="100">项目配置文件01</option>
								<option value="101">项目配置文件02</option>								
							</select>
		              		<input type="radio" name="se" id="Custom"/>自定义
							<div id="newfile">
								<input type="text" id="tmpNewUrl" size="40"/>
								<input id="new" type="button" value="新建"/>
							</div>
						</td>
					</tr>
					<tr id="tiqu">
						<td>
							<font color="red">*</font>文件提取地址：
						</td>
						<td class="lists_tleft">
							<input type="text" id="tmpUrl" size="40" readonly="readonly" style="background-color: #CCCCCC" disabled="disabled"/>
							<input id="extract" type="button" value="提取"/>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>模板内容
						</td>
						<td class="lists_tleft">
							<textarea id="tmpContent" style="width:99%;height:600px" ><s:property value="#request.common"/></textarea>
						</td>
						
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="保存" id="save"/>
						</td>
					</tr>	
				</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif"/></td>
			<td class="lists_bottom"></td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
		</tr>
	</table></div>
</body>
</html>
