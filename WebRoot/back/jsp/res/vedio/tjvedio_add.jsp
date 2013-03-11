<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加课程节点</title>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
	<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />

    <script type="text/javascript">
    
    $().ready(function() {
			$("#tjspform").validate({   
		        rules: {   
		            id: {
		            	required:true,
		            	min: 0
		            },
		            id2: {
		            	required:true,
		            	min: 0
		            }
		        },   
		        messages: {   
		            id: {
				            	required:"请选择视频",
				            	min: "请选择视频"
				    },
				    id2: {
				            	required:"请选择推荐视频",
				            	min: "请选择推荐视频"
				    }
		        }   
   	 		});  
		})	
    
    
    
    
		function onchangevdid(vdid)
		{
		
			document.getElementById("vdid").value=vdid;
		}
		
		
		function onchangetjvdid(tjvdid)
		{
		
			document.getElementById("tjvdid").value=tjvdid;
		}
			
	</script>
  </head>
  
  <body>
<form action="<%=contextPath%>/res/tjVedio!addTvdTvd.action"  method="post" name="tjspform" id="tjspform">
<div>
	
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">选择原视频</font>
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
		    		<td align="right" width="20%">选择视频：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			
						<s:select name="id" list="tryVedioList"
							listKey="id" listValue="title"
						 headerKey="-1" headerValue="请选择" theme="simple"   onchange="onchangevdid(this.value);" >
						</s:select>
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
</div>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td>
				<font class="lists_fleft">选择推荐视频</font>
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
		    		<td align="right" width="20%">选择推荐视频：</td>
		    		<td class="lists_tleft" width="80%" style="color: #ff0000;">
		    			
						<s:select name="id2" list="tryVedioList" listKey="id" listValue="title" headerKey="-1" headerValue="请选择" theme="simple" onchange="onchangetjvdid(this.value);" >
						</s:select>
		    		</td>
		    	</tr>
		    	
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
				<s:hidden name="tvdtvd.vdid" id="vdid"></s:hidden>
	    		<s:hidden name="tvdtvd.tjvdid" id="tjvdid"></s:hidden>
	    		<input class="submit" type="submit" value="提交" style="width:100px;"/>
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
