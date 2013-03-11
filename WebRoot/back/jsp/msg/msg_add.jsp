<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加新消息</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryAeroWindow/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryAeroWindow/jquery-ui-1.8.1.custom.min.js"></script> 
	    <script type="text/javascript" src="<%=contextPath%>/back/script/jQueryAeroWindow/jquery.easing.1.3.js"></script>         
	    <script type="text/javascript" src="<%=contextPath%>/back/script/jQueryAeroWindow/jquery-AeroWindow.js"></script>
	    
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>

		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/script/jQueryAeroWindow/AeroWindow.css" />

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
			
	  function openSearchWindow() {
          $('#Window1').AeroWindow({
            WindowTitle:          '用户搜索框',
            WindowPositionTop:    'center',
            WindowPositionLeft:   'center',
            WindowWidth:          700,
            WindowHeight:         500,
            WindowAnimation:      'easeOutCubic',
            WindowMaximize:false
          });
          
          $('#Window1').css('display','block');
      }  
      
      function searchUser(){
      	var userName = $('#userName');
      	$.ajax({  
			url : "<%=contextPath%>/msg/msg!searchUserJSON.action",  
			data : {userName : userName[0].value},  // 参数  
			type : "post",  
			cache : false,  
			dataType : "json",  //返回json数据 
			error: function(){ 
				alert('error');      
			}, 
			success:onchangecallback  
		}); 
      }
      
      function onchangecallback(result){
      	var userList = eval(result.returnMessage);
      	var content = '';
      	for(var i = 0; i < userList.length; i ++){
      		content += '<li onDblClick="doubleClick('+ userList[i].cusId +',\''+ userList[i].cusName +'\')">' + userList[i].cusName + '</li>';
      	}
      	
      	$('#userList').html(content);
      }
      
      function doubleClick(userId, userName){
      	$('#Window1').css('display','none');
      	$('#displayUserName').html("收件人姓名：" + userName);
      	$('#userId').val(userId);
      }
      
      function selectSort(sortId){
      	if(sortId == 1){
      		$('#searchUser').css('display','block');
      	}else{
      		$('#searchUser').css('display','none');
      		$('#userId').val('');
      	}
      }

		</script>
	</head>
	<body class="manage">
	<form action="msg/msg!addMsg.action" method="post"  id="msgform">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists" id="tblList">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">消息添加</font>
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
						<td class="lists_tleft">
							<input type="text" name="msg.msgTitle" id="msg.msgTitle"/>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>消息类型分类
							
						</td>
						<td class="lists_tleft">
							<s:select name="msg.msgType" id="msg.msgType" list="msgTypeMap"
								listKey="key" listValue="value" theme="simple" onchange='selectSort(this.value);'>
							</s:select>
							<div id="searchUser" style='display: none'>
								<input type="button" value="查询用户" onclick='openSearchWindow()'/><div id='displayUserName'></div>
								<input type='hidden' name='userId' id='userId'/>
							</div>
						</td>
						
					</tr>
					
					<tr>
						<td>
							<font color="red">*</font>消息分类
							
						</td>
						<td class="lists_tleft">
							<s:select name="msg.msgSort" id="msg.msgSort" list="msgSortMap"
								listKey="key" listValue="value" theme="simple">
							</s:select>
						</td>
						
					</tr>
					<tr>
						<td>
							<font color="red">*</font>消息内容
						</td>
						<td class="lists_tleft">
							<textarea rows="20" cols="50" name="msg.msgContent" id="msg.msgContent"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="提交"/>
						</td>
					</tr>
					</tbody>
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

 <div id="Window1" style="display: none">
 	<center>
   <table class="lists_info">
   	<tr>
   		<td width='100'>
   			 输入用户名
   		</td>
   		<td class='lists_tleft'>
   			<input type='text' name='userName' id='userName'/><input type='button' onClick="searchUser()" value = "搜索"/>
   		</td>
   	</tr>
   	<tr>
   		<td valign="top">
   			用户名称：
   		</td>
   		<td class='lists_tleft' style="text-align: left">
   			<ul id='userList'>
   				
   			</ul>
   		</td>
   	</tr>
   </table>
   </center>
 </div>
	</body>
</html>
