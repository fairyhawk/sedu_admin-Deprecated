<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>更新播放类型</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript">
		$(function(){
			var saveSuccess="${saveSuccess}";
			if(saveSuccess==1){
				alert("保存成功！");
			}
			
			
			$("#savePlayType").click(function(){
				var msg=checkData();
				if(msg!=''){
					alert(msg);
					return ;
				}
				$("#updatePlayTypeForm").attr("action","<%=contextPath%>/res/vedio!updatePlayType.action");
				$("#updatePlayTypeForm").submit();
			});
			
					$("#calAll").click(function(){
           	 			$(":checkbox").removeAttr("checked");//取消全选
        			});
        			
        			
        			
        			$("#selAll").click(function(){
           	 			  $("input[name='subjectIds']").each(function(){
 						  	$(this).attr("checked",true);
 						 });  
        			});
		});
		function checkData(){
			var msg="";
			if($("#subject").val()==''){
				msg+="所属项目专业为必选项\n";
			}
			return msg;
		}
		
	
		</script>
		


		
		
	</head>
	<body>
		<div>
			<form action="?" method="post" name="updatePlayTypeForm" id="updatePlayTypeForm" enctype="multipart/form-data">
				<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">更新播放类型</font>
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
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="tblList">
								<tbody id="tagTb">
						  			<tr>
						  				<td class="lists_tright" width="10%"><font color="red">*</font> 所属项目专业 ：</td>
						  				<td id="checktd" >
						  				   <s:checkboxlist   theme="simple" list="subjctPlayTypelist" name="subjectIds"  listKey="subjectId" listValue="subjectName" value="#intarray" />
						  				<!--
						  				<s:iterator value="#attr.subjctPlayTypelist" var="play" status="index">
						  						<input type="checkbox" name="subjectIds" value="${play.subjectId}" <s:if test="intarray[index]!=">checked</s:if> >${play.subjectName}</input>
						  				</s:iterator>
						  				 -->
						  				</td>
						  			</tr>
									<tr>
										<td colspan="2">
											<input  type="button" id="selAll"  value="全选"/>
											<input  type="button" id="calAll"  value="取消全选"/>
											<input  type="button" id="savePlayType" value="切换cc"/>
										</td>
									</tr>
								
								</tbody>
							</table>
						</td>
					</tr>	
			</form>
		</div>
	</body>
</html>


		
<script type="text/javascript"> 

			function selectall() 
			{ 
					var a = document.getElementsByTagName("input"); 
					for (var i=0; i<a.length; i++) 
					if (a[i].type == "checkbox")
					 a[i].checked = true; 
			} 
			
				function cancelall() 
			{ 
					var a = document.getElementsByTagName("input"); 
					for (var i=0; i<a.length; i++) 
					if (a[i].type == "checkbox")
					 a[i].checked = false; 
			} 

			//得到checkboxlist的td,自定义td的id为checktd 
			var checktd = document.getElementById("checktd"); 
			//得到一个checkbox数组 
			var checkboxs = checktd.getElementsByTagName("input");
			for ( var i = 1; i < checkboxs.length; i++) { 
			//每个br只能用一次,所以在循环内创建 
			var br = document.createElement(""); 
			//checkbos前插入br 
			checktd.insertBefore(br, checkboxs[i]); 
			}  
			//最前面不需要br 从1开始 
			for ( var i = 7; i < checkboxs.length; i+=7) { 
			//每个br只能用一次,所以在循环内创建 
			var br = document.createElement("br"); 
			//checkbos前插入br 
			checktd.insertBefore(br, checkboxs[i]); 
			} 
</script>