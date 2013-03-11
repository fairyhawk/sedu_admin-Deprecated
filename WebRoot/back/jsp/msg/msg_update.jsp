<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>更新消息</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>

		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript">
		$().ready(function() {

			$("#msgform").validate({   
		        rules: {
		            "msg.msgType": {
		            	required:true,
		            	min: 0
		            },
		            "msg.msgContent": {
		            	required:true,
		            	maxlength:1000
		            },
		             "msg.msgTitle": {
		            	required:true,
		            	minlength:4,
		            	maxlength:40
		            }
		        },   
		        messages: {   
		            "msg.msgType": {
				            	required:"请选择发送对象",
				            	min: "请选择发送对象"
				    },
				    "msg.msgContent": {
				            	required:"请填写信息内容",
				            	maxlength:"信息内容不能多于500字"
				    },
				    "msg.msgTitle": {
		            	required:"请填写信息标题",
		            	minlength:"不能低于4个字",
		            	maxlength:"不能超过20个字"
		            }
		        }   
   	 		});  
		})	
					
			function changeMainPic(ipt) {
				var img = document.getElementById("mainpicImg");
				var div = document.getElementById("mainpicDiv");
				if(ipt.value != '') {
					if(IsIE()) {
						ipt.select();    
		        		var imgSrc = document.selection.createRange().text; 
						div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
						div.style.height = "100px";
						div.style.width = "100px";
					} else {
						img.src = ipt.files.item(0).getAsDataURL();
						img.style.display = "";
					}
				} else {
					img.style.display = "none";
				}
			}
			
			function IsIE() {
			    if (window.navigator.userAgent.indexOf("MSIE")>=1) {
			        //IE浏览器
			        return true;
			    }else{
			        return false;
			    }
			}
			 
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}

		</script>
	</head>
	<body class="manage">
		<form action="msg/msg!updateMsg.action" method="post"  id="msgform">
			<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists" id="tblList">
				<tr >
					<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">消息修改</font>
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
						<tbody id="tagTb">
							<tr>
								<td>
									<font color="red">*</font>消息标题
								</td>
								<td>
									<input type="text" name="msg.msgTitle" id="msg.msgTitle" value="<s:property value='msg.msgTitle'/>"/>
								</td>
							</tr>
							
							<tr>
								<td>
									<font color="red">*</font>消息内容
								</td>
								<td>
									<textarea rows="20" cols="50" name="msg.msgContent" id="msg.msgContent"><s:property value='msg.msgContent'/></textarea>
								</td>
							</tr>
							</tbody>
						</table>
						<table class="tables" align="center" border="0" cellpadding="0" cellspacing="1" style=" width:90%;">
							<tr>
								<td align="center">
									<s:hidden name="msg.msgId"/>
									<s:submit value="提交" />
								</td>
							</tr></tbody>
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
	</body>
</html>
