<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>接收信息人员详情</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
	<script>
	
		　function openWin(spId) { 
			window.open ("<%=contextPath%>/sp/sentperson!getSentPersonInfo.action?sentPersonId="+spId, "newwindow", "height=600, width=800, toolbar=no, menubar=yes, scrollbars=no, resizable=no, location=no, status=no");
		　}

		//关闭
		function clocs(){
			var aBlean=confirm("确认关闭该页面？");
			if(aBlean==true)
			{
				window.close();
			}else{
				return false;
			}
		}	
		
		//  提交时候进行校验；
		function onSubmit()
		{
			var aBlean=confirm("确认修改接收信息？");
			if(aBlean==true)
			{
				alert("修改成功！");
				
			}else{
				return false;
			}
		}
	</script>
	
	<script>
		//接收后台拼凑好的字符窜
		var subjectIds =  "${subjectIds}";
		var arrySubId = subjectIds.split(",");
		
		$().ready(
			function setCheck(){
				for(var i=0;i<(arrySubId.length)-1;i++){
				
					$("#chk"+arrySubId[i]).attr("checked",true);//对原来已经选择的 打勾 
				}
 		});
 	</script>
</head>
<body onload="setCheck();">
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">接收信息人员详情</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		
		<tr>
			<td   class="lists_bor"></td>
			<td>  
				<div class="msg-yw">
				<table border="0" width="100%" align="center">
					<tr>
						<td align="right">姓名：</td>
						<td align="left"><s:property value='sentPerson.personName'/></td>
						<td align="right">手机号码：</td>
						<td align="left"><s:property value='sentPerson.personPhone'/></td>
					</tr>
				</table>
				</div>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr >
					<td  class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">接收的项目信息列表</font>
					</td>
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td class="lists_bor">
					</td>
					<td>
					<form action="<%=contextPath%>/sp/sentperson!updSentPersonInfo.action" name="upForm" method="post" onsubmit="return onSubmit();">
							
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
							
							<tr>
								<td scope="3">
									<div style="padding:10px">
									<input type="hidden" value="<s:property value='sentPerson.Id'/>" name="querySentPersonCondition.Id"/>
									<s:if test="subjectList != null">
										<s:iterator value="subjectList" id="subjectList" status="status">
								
										<div style="width:30%;float:left;padding:2px 4px; text-align: left;">							
											<input type="checkbox" name="subIds" value ="<s:property value="#subjectList.subjectId"/>" id="chk<s:property value="#subjectList.subjectId"/>" >
											<s:property value="#subjectList.subjectName" />											
										</div>
									
										</s:iterator>
									</s:if>
									</div>
								</td>
							</tr>
							<tr>
								<td align="left">
									<input type="submit" onclick="" value=" 确定 " />
									&nbsp;&nbsp;
									<input type="button" onclick="clocs();" value=" 关闭 " />
								</td>
							</tr>
						</table>
						</form>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom">
						
					</td>
					<td class="td_wid_r">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
				
			</table>
					
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		
	</table>
</div>
	</body>
</html>