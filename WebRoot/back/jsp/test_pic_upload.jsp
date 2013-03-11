<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>测试图片上传</title>	
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/right.css" />
		<script type="text/javascript" src="<%=contextPath%>/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/script/jQueryValidate/lib/jquery.metadata.js" >
		</script>		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>
		<script type="text/javascript">
			function toViewDownload(){
				window.location.href = "<%=contextPath%>/test!toViewDownload.action";
			}
			
			var count = 0;
			
			function addPic() {
				count ++;
				var tbl = document.getElementById("picTable");
				var tr = document.createElement("tr");
				var td = document.createElement("td");
				td.colspan = 2;
				
				var ipt = document.createElement("input");
				ipt.name = 'files';
				ipt.type = 'file';
				ipt.id = 'file' + count;
				ipt.onchange = function () {
					changePic(ipt);
				}
				
				var button = document.createElement("input");
				button.type = "button";
				button.value = "删除";
				button.onclick = function () {
					deleteRow(button);
				}
				
				var br = document.createElement("br");
				var div = document.createElement("div");
				div.className = "uploadPic";
				div.id = "picDiv" + count;
				
				var img = document.createElement("img");
				img.style.display = "none";
				img.id = "picImg" + count;
				
				tr.appendChild(td);
				td.appendChild(ipt);
				td.appendChild(button);
				td.appendChild(br);
				td.appendChild(div);
				td.appendChild(img);
				if(IsIE()) {
					tbl.firstChild.appendChild(tr);
				} else {
					tbl.appendChild(tr);
				}
			}
			
			function changePic(ipt) {
				var img = document.getElementById("picImg" + ipt.id.substring(4, ipt.id.length));
				var div = document.getElementById("picDiv" + ipt.id.substring(4, ipt.id.length));
				
				if(ipt.value != '') {
					if(IsIE()) {
						ipt.select();    
		        		var imgSrc = document.selection.createRange().text; 
						div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
						div.style.height = "80px";
						div.style.width = "60px";
					} else {
						img.src = ipt.files.item(0).getAsDataURL();
						img.style.display = "";
					}
				} else {
					if(IsIE()) {
						div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = "";
						div.style.height = "0px";
						div.style.width = "0px";
					} else {
						img.style.display = "none";
					}
				}
			}
			
			function deleteRow(btn) {
				var tbl = document.getElementById("picTable");
				tbl.deleteRow(btn.parentNode.parentNode.rowIndex);
			}
			
			function IsIE() {
			    if (window.navigator.userAgent.indexOf("MSIE")>=1) {
			        //IE浏览器
			        return true;
			    }else{
			        return false;
			    }
			}
		</script>
	</head>
	<body>
		<div class="main_right">
			<h1>
				测试图片上传
			</h1>
			<font color="red"><s:property value="actionErrors[0]"/></font>
			<form action="<%=contextPath %>/test!upload.action" method="post" name="picForm" id="picForm" enctype="multipart/form-data">
				<table cellpadding="0" cellspacing="1" class="tables" id="picTable">
					<tr>
						<th>
							上传文件
							<input type="button" value="增加图片" onclick="addPic()"/>
						</th>
					</tr>
					<tr>
						<td>
							<input name="files" type="file" id="file0" onchange="changePic(this)"/>
							<input type="button" value="删除" onclick="deleteRow(this)"/><br />
							<div class="uploadPic" id="picDiv0"></div>
							<img id="picImg0" style="display:none"/>
						</td>
					</tr>
				</table>
				<input type="submit" value="提交"/><input type="button" value="查看已上传的图片" onclick="toViewDownload()"/>
			</form>
		</div>
	</body>
</html>