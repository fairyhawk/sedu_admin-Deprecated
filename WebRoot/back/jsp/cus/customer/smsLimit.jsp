<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>短信条数限制</title>	
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript">
			function updateSmsLimit(){
			var smsLimit=$('#smsLimit').val();
			var patrn=/^[0-9]{1,20}$/; 
			if (!patrn.exec(smsLimit)) {
			alert("请输入数字");
			return false 
			} 
			  $.post("<%=contextPath%>/cus/cus!updateSmsLimit.action",{smsLimit:smsLimit},function(json){
			  	if(json.jumpType) alert('修改成功');
			  	else alert('修改失败，请联系开发人员解决');
			  },"json");
			}
		</script>
	</head>
	<body>
		<div class="main_right">
			<h1>
				短信条数限制 
			</h1>
				<table cellpadding="0" cellspacing="1" class="tables">
					<tr height="30">
						<td>
							<input type="text" maxlength="6" id="smsLimit"  value="${smsLimit }"/>
						</td>
						<td>
							<input type="button" value="提交"  onclick="updateSmsLimit()"/>	
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>