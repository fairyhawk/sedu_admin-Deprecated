<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>任务信息列表</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
	
		
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					任务列表
				</font>
				<font class="lists_fright">
					<a href="javascript:void(0);" onclick="addAllTask()">全部添加</a>
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
					<td>
						<input type="checkbox" id='allOrNo' onclick="allCheck()"/>
					</td>
						<td>
							学员(用户)ID
						</td>
						<td>
							昵称
						</td>
						<td>
							密码
						</td>
						<td>
							邮箱地址
						</td>
						<td>
							手机号
						</td>
						<td>
							真实姓名
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="page.pageResult" id="taskLog">
						<tr>
						<td>
								<input type="checkbox" name="checkAll" value="<s:property value='email' />_<s:property value='cusId' />"/>
							</td>
							<td>
								<s:property value="cusId" />
							</td>
							<td>
								<s:property value="cusName" />
							</td>
							<td>
								<s:property value="cusPwd" />
							</td>
							<td>
								<s:property value="email" />
							</td>
							<td>
								<s:property value="mobile" />
							</td>
							<td>
								<s:property value="realName" />
							</td>
							<td>
							<a href="javascript:void(0);" onclick="add('<s:property value='email' />_<s:property value='cusId'/>&')">添加</a>
							</td>
						</tr>
					</s:iterator>
			</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
	<script type="text/javascript">
			 function allCheck(){
						     	if($('#allOrNo').attr('checked') != true){
						     		$("input[name='checkAll']").removeAttr("checked");
						     	}else{
						     		$("input[name='checkAll']").attr("checked",'true');//全选
						     	}
						     }
	//添加任务
					
	function add(o){
	   parent.addTask(o);
	 }
	 //全部添加
	 function addAllTask(){

			var str=""; 
			$("[name='checkAll']").each(function(){ 
				if($(this).attr('checked') == true){
					str+=$(this).val()+"&"; 
				}
			});
			if(str == ""){
				alert('请选择要添加的视频');
				return;
			}else{
				parent.addTask(str);
			}
		
	}
		</script>
		 
</html>