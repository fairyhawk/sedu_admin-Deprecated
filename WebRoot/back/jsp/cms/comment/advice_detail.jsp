<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>回复意见</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
<script type="text/javascript" src="<%=contextPath%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
			$("#lengthwarn").css("display","none");
			// 初始化 Kindeditor
	});
	
	function replyAdvice(id,info,status){
		document.doAdvice.action="<%=contextPath %>/cms/comment!addAdvice.action";
		$('#cmtId').val(id);
		if(status==1){
			if(info.replace(/(^\s*)|(\s*$)/g, "")!=''){
				info='回复@'+info+':';
			}else{
				info='回复@游客:';
			}
		}
		$('#tmpContent').val(info);
		$('#tmpContent').focus();
		$('#submits').removeAttr("disabled"); 
	}
	
	function replyReply(id){
		document.doAdvice.action="<%=contextPath %>/cms/comment!replyReply.action";
		$('#sourceId').val(id);
		$('#tmpContent').val('');
		$('#tmpContent').focus();
		$('#submits').removeAttr("disabled"); 
	}
	
	function editAdvice(id,info){
		document.doAdvice.action="<%=contextPath %>/cms/comment!updateAdvice.action";
		$('#cmtId').val(id);
		$('#tmpContent').val(info);
		$('#tmpContent').focus();
		$('#submits').removeAttr("disabled"); 
	}
	
	function delAdvice(id){
	window.location.href="<%=contextPath %>/cms/comment!delAdviceMsg.action?comment.cmtId="+id+'&id=<s:property value="comment.cmtId"/>'
	}
	
	function onsubmits(){
		var info=$('#tmpContent').val();
		if(info.indexOf("回复@")!=-1){
			info=info.substring(info.indexOf(":")+1);
		}
		if(info.replace(/(^\s*)|(\s*$)/g, "")==''){
			alert('必须写入回复内容');
			return;
		}
		if(info.length>200){
			$("#lengthwarn").css("display","block");
			return;
		}
		document.doAdvice.submit();
	}
	
	function editReplyAdvice(id){
		$.post("<%=contextPath %>/cms/comment!getReplyBySourceId.action",{id:id},function(json){
			if(json.jumpType==true){
			var entity=json.entity;
			if(json.returnMessage!=null&&json.returnMessage!='')alert(json.returnMessage);
			document.doAdvice.action="<%=contextPath %>/cms/comment!updateAdvice.action";
			$('#cmtId').val(entity[0].cmtId);
			$('#tmpContent').val(entity[0].cmtInfo);
			$('#tmpContent').focus();
			$('#submits').removeAttr("disabled"); 
			}else 
			alert('该评论没有回复,出现bug，请与技术人员联系');
		},"json");
	}
	function delReplyAdvice(id){
	window.location.href="<%=contextPath %>/cms/comment!delReplyAdvice.action?comment.cmtId="+id+'&id=<s:property value="comment.cmtId"/>';
	}
</script>
</head>
<body>
<div>

	<form action="<%=contextPath %>/cms/comment!addAdvice.action" method="post" name="doAdvice" id="addForm">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">留言基本信息</font>
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
					<td width="30%">
						留言用户
					</td>
					<td class="lists_tleft" width="70%">
						<s:property value="comment.visitorName"/>
					</td>
				</tr>
				<tr>
					<td>
						留言时间
					</td>
					<td class="lists_tleft">
						<s:date name="comment.createTime" format="yyyy-MM-dd hh:mm:ss" />
					</td>
				</tr>
				<tr>
					<td >
						留言内容
					</td>
					<td class="lists_tleft">
					<div style="width:700px"><s:property value="comment.cmtInfo"/></div>
						
					</td>
				</tr>
				<tr>
					<td colspan="2" >
					<div style="width:400px;">
					<s:if test="comment.checkState==0">
					<input type="button" onclick="replyAdvice(<s:property value="comment.cmtId"/>,'',0)" value="回复此留言"/>
					</s:if> 
					</div>
					</td>
				</tr>	
			</table>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td width="30%">
						回复内容
					</td>
					<td class="lists_tleft" width="70%">
						<input type="hidden" name="id" value="${comment.cmtId }" />
						<input type="hidden" name="comment.cmtId" id="cmtId" />
						<input type="hidden" name="comment.sourceId" id="sourceId" value="${comment.cmtId }"/>
						<textarea name="comment.cmtInfo" style="width:500px;height:150px;margin:3px;" maxlength="220" id="tmpContent" class="{required:true}" ></textarea>
							<div id="lengthwarn">
							<span style="color:red;">
							*字符不能超过200个。
							</span>
							</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div style="width:200px;">
						<s:if test="comment.checkState==0">
					<input type="button" onclick="onsubmits()" value="提交回复" id="submits"/>
					</s:if><s:else>
					<input type="button" onclick="onsubmits()" id="submits"  disabled="disabled" value="提交回复"/>
					</s:else>
						<input type="button" value="返回" onclick="history.go(-1)"></input>
					</div>
					</td>
				</tr>	
			</table>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td colspan="2">
					<div style="width:400px;">
					<s:if test="commentList.size()==0">没有回复内容</s:if>
					<s:else>以下是全部回复内容：</s:else>
					</div>
					</td>
				</tr>
				<s:iterator value="commentList" id="comment">
				<tr>
					<td colspan="2" >
						<div style="width:400px;">
							<span>回复人：<s:property value="#comment.visitorName"/></span><span>&nbsp;&nbsp;&nbsp;回复时间：<s:date name="#comment.createTime" format="yyyy-MM-dd hh:mm:ss" /></span>
						</div>
						<div  style="width:400px;">
							<span>回复内容：<s:property value="#comment.cmtInfo"/></span>
						</div>
						<div  style="width:400px;">
							<span><a href="javascript:delAdvice('<s:property value="cmtId"/>')">删除回复</a>&nbsp;&nbsp;&nbsp;<a  href="javascript:editAdvice('<s:property value="cmtId"/>','<s:property value="#comment.cmtInfo"/>')">修改回复</a>&nbsp;&nbsp;&nbsp;
								  <a  href="javascript:replyAdvice('<s:property value="comment.cmtId"/>','<s:property value="#comment.visitorName"/>',1)">回复此条留言</a>
							</span>
						</div>
					</td>
					<td>
					
					</td>
				</tr>
				</s:iterator>
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
