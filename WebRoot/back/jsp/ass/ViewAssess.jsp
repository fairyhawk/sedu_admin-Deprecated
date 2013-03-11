<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>修改评论</title>
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	
	<script language="JavaScript">
	function changeSubject() {
		change('<s:property value="assess.status"/>');
		subject('<s:property value="assess.subjectId"/>');
	}

	function change(status) {
		$.each($("#sta option"),function(){
			if(this.value == status) {
				this.selected = true;
			}
		})
	}
	function subject(subId) {
		$.each($("#subjectId option"),function(){
			if(this.value == subId) {
				this.selected = true;
			}
		})
	}
	
	</script>
  </head>
  
  <body onload="changeSubject()">
  <div>
	<form action="<%=contextPath %>/ass/assess!updateAssess.action" method="post" name="updateForm" id="updateForm" enctype="multipart/form-data">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">修改评论信息</font>
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
					<td>
						ID号：
					</td>
					<td class="lists_tleft">
						<input type="text" name="assess.id" id="id" value="<s:property value="assess.id" />" disabled="disabled"/>
						<s:hidden id="assess.kpointId" name="assess.kpointId"></s:hidden>
						<s:hidden id="assess.subjectId" name="assess.subjectId"></s:hidden>
						<s:hidden id="assess.courseId" name="assess.courseId"></s:hidden>
						<s:hidden id="assess.sellwayId" name="assess.sellwayId"></s:hidden>
						<s:hidden id="assess.cusId" name="assess.cusId"></s:hidden>
						<s:hidden id="assess.id" name="assess.id"></s:hidden>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>评分标题
					</td>
					<td class="lists_tleft">
						<input type="text" name="assess.assTitle" id="title" value="<s:property value="assess.assTitle"/>" readonly="readonly"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>评分
					</td>
					<td class="lists_tleft">
						<select id="lvl" name="assess.assLeavel" disabled="disabled">
						<option value="5" <s:property value="assess.assLeavel==5?'selected':''"/>>五星</option>
						<option value="4" <s:property value="assess.assLeavel==4?'selected':''"/>>四星</option>
						<option value="3" <s:property value="assess.assLeavel==3?'selected':''"/>>三星</option>
						<option value="2" <s:property value="assess.assLeavel==2?'selected':''"/>>二星</option>
						<option value="1" <s:property value="assess.assLeavel==1?'selected':''"/>>一星</option>
						</select>
					</td>
				</tr>
				<tr height="30">
					<td>
						学员账号：
					</td>
					<td class="lists_tleft">
					<input type="text" id="cusName" name="customer.email" value='<s:property value="customer.email"/>' disabled="disabled"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>视频课程名：
					</td>
					<td class="lists_tleft">
						<input type="text" id="kpoint" name="kpoint.name" value='<s:property value="kpoint.name"/>' disabled="disabled"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>讲师：
					</td>
					<td class="lists_tleft">
						<input type="text" id="kpoint" name="kpoint" disabled="disabled"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>时长：
					</td>
					<td class="lists_tleft">
						<input type="text" id="voSize" name="voSize" value='<s:property value="voSize" />' disabled="disabled"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>所属专业：
					</td>
					<td class="lists_tleft">
						<s:property value="subject.subjectName"/>
						<s:hidden name="subject.subjectId" id="subject.subjectId"></s:hidden>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>发布时间：
					</td>
					<td class="lists_tleft">
						<input type="text" id="assTime" name="assess.assTime" value='<s:date name="assess.assTime" format="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"/>
						<s:hidden id="assess.assTime" name="assess.assTime"></s:hidden>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>处理时间：
					</td>
					<td class="lists_tleft">
						
						<input type="text" id="verifyTime" name="assess.verifyTime" value='<s:date name="assess.verifyTime" format="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>状态：
					</td>
					<td class="lists_tleft">
						<s:hidden id="assess.status" name="assess.status"/>
						<select id="sta" name="sta" disabled="disabled"> <option value="-1"> 请选择 </option><option value="0"> 未审 </option> <option value="1"> 发布 </option><option value="2"> 新稿 </option><option value="3"> 删除 </option></select>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>评价内容：
					</td>
					<td class="lists_tleft">
						<textarea rows="5" cols="50" id="assess.assContext" name="assess.assContext" readonly="readonly"><s:property value="assess.assContext" /></textarea>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>回复：
					</td>
					<td class="lists_tleft">
						<textarea rows="5" cols="50" id="reassess.reassContext" name="reassess.reassContext" readonly="readonly"><s:property value="reassess.reassContext" /></textarea><br />
						<input type="text" value="嗨学网" disabled="disabled"/>
					</td>
				</tr>
				<tr height="30">
					<td>
						<font color="red">*</font>审核备注：
					</td>
					<td class="lists_tleft">
						<textarea rows="5" cols="50" id="assess.verifyContext" name="assess.verifyContext" readonly="readonly"><s:property value="assess.verifyContext"/></textarea>
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
	</form>
</div>
  </body>
</html>
