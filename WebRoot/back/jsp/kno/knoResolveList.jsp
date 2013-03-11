<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/public.css" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/index.css" />
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
	<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/attach.js" /></script>
	<script type="text/javascript">
		$().ready(function() {
		$("#resolveList").validate();
			// 初始化 Kindeditor
    	        KE.show({
    	                    id : 'resContent',
    	                    width: '440px',
    	                    resizeMode : 0,
    	                    allowPreviewEmoticons : false,
    	                    allowUpload : true,
    	                    syncType : 'auto',
    	                    urlType : 'absolute',
    	                    imageUploadJson : 'http://tp.highso.cn:8080/upload!exam.action',
    	                    allowFileManager : false,
    	                    items : ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter','-', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|', 'selectall', 'title', 'fontname', 'fontsize', '|', 'textcolor','-', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|', 'image', 'media', 'advtable', 'hr', 'emoticons', 'link', 'unlink']
    	                });
    	               })
    	               
		//删除知识树节点解析
		function delRes(resId){
		
	 		var s=window.confirm("知识树节点解析删除后无法恢复，确认删除？");
	 		if(s){
	 		$.ajax({
									url : "<%=contextPath%>/kno/resolve!delResolve.action",  
									data : {"resId" : resId},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									if(bo){
									alert("删除成功");
									}
									document.forms["resolveList"].action='<%=contextPath %>/kno/resolve!resolveList.action?ksnId=<s:property value="ksnId"/>';
									document.forms["resolveList"].submit(); 
									},
									error: function(){ 
										alert('error');  
									}
				  });
			}else{
				alert("取消成功");
			}
		}
		
		function addRes(){
			$('#person').hide();
			$('#resKeyword').val('');
			KE.html('resContent', '');
			document.forms["resolveList"].action='<%=contextPath %>/kno/resolve!resolveAdd.action?ksnId=<s:property value="ksnId"/>';
		}
		
		function updateRes(resId){
			var ksnId='<s:property value="ksnId"/>';
			$.ajax({
									url : "<%=contextPath%>/kno/resolve!getResInfo.action",  
									data : {"resId" : resId,
									"ksnId":ksnId},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var resolve=result.entity;
									$('#resKeyword').val(resolve.resKeyword);
									$('#resperson').val(resolve.resPerson);
									KE.html('resContent', resolve.resContent);
									if((resolve.isFirst)==1){
										$("#isFirst").attr("checked", "checked");
									}
									$('#person').show();
									document.forms["resolveList"].action='<%=contextPath %>/kno/resolve!resolveUpdate.action?resId='+resId+'&ksnId='+ksnId;
									},
									error: function(){ 
										alert('error');  
									}
				  });
		}
	</script>
  </head>
  
  <body>
	 <table class="treeouter" width="800" border="0" cellpadding="0" cellspacing="0">
  <tr style="margin:0; padding:0">
  <td valign="top" style="border:none; text-align:left">
  <div class="cont_outer">
            	<div class="jiegou_tab mt20">
                		<div class="tab_outer f14">
                        		<a class="tablink width95"  href='<%=contextPath %>/kno/knowledgeSystem!knowledgeTreeZi.action?ksnId=<s:property value="ksnId"/>'>结构</a>
                                <a class="tablink width95 ml2" id="superlink95"  href='#'>精讲解析</a>
                                <a class="tablink width95 ml2" href='<%=contextPath %>/kno/qstRel!toQstRelList.action?ksnId=<s:property value="ksnId"/>'>关联试题</a>
                                <a class="tablink width130 ml2"  href='<%=contextPath %>/kno/videoRel!toVideoRelList.action?ksnId=<s:property value="ksnId"/>'>关联课程视频</a>
                       			<a class="tablink width95 ml2" href="#">课程讲义</a>
                        </div>
                </div>
  </div>
   <div class="mianbao"><em class="flag dib fl mr5"></em>
  			<s:iterator value="sysNodeList" id="s" >
			<a href='<%=contextPath %>/kno/resolve!resolveList.action?ksnId=<s:property value="#s.ksnId"/>'><s:property value="#s.nodeName"/><span class="ml5 mr5">&gt;</span></a>
			</s:iterator>
   </div>
   <div class="mt20 txtr">
   	<input id="txjx_btn" class="add_defitem" onclick="addRes();" type="button" value="填写解析" />
   </div>
	  </tr>
	</table>
	
		<div class="kno_tree_title mt14 f14 fw">解析内容</div>
		 <table cellpadding="0" cellspacing="0" >
		 <thead>
        	<tr align="center">
        		<th width="20%">ID</th>
            	<th width="20%">解析人</th>
                <th width="20%">解析内容</th>
                <th width="20%">位置</th>
                <th width="30%">操作</th>
            </tr>
        </thead>

        	<s:iterator value="resList" id="resolve">
        	<tr>
        	<td><s:property value="#resolve.nodeId"/>&nbsp;</td>
        	<td><s:property value="#resolve.resPerson"/></td>
        	<td><div style='font-size:12px; text-align:center; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' ><s:property value="#resolve.resContent" escape="false"/></div></td>
        	<td>
				<s:if test="#resolve.isFirst==1">
				首位
				</s:if>&nbsp;
			</td>
        	<td><a class="mr15 mode_jiexi_pop" href='#' onclick='updateRes(<s:property value="#resolve.resId"/>)' target="_self">修改</a><a href="#" class="mr15 " onclick="delRes(<s:property value="#resolve.resId"/>)">/删除</a></td>
        	</tr>
        	</s:iterator>
    </table>
        <form name="resolveList" id="resolveList" method="post" target="_self">
        <!--填写解析弹出层-->
        <div class="pop_defouter dn" id="jiexie_opp">
        		<div class="pop_definner">
                		<h2 class="pop_tit color333 f14 mb20"><span class="fl txjx">填写解析</span><span class="fl xgjx">修改解析</span><em class="flag_close2" id="cose_add"></em></h2>
                        <div class="add_item mt20">
                        	<label class="firstLabel mr5">专业项目：</label><span class="fl lin24"><s:property value="sys.subjectName"/></span>
                            <div class="clnull"></div>
                        </div>
                        <div class="add_item mt10" id="person">
                        	<label class="firstLabel mr5">解析人名：</label><input class="inp_txt width200" id="resperson" disabled="disabled" type="text" />
                        </div>
                        <div class="add_item mt10">
                        	<label class="firstLabel mr5">关键字：</label><input class="inp_txt width200" id="resKeyword" name="resolve.resKeyword"  type="text" />
                        </div>
                        <div class="add_item mt10">
                        	<label class="firstLabel mr5">解析内容：</label><textarea id="resContent" name="resolve.resContent" class="inp_texare width300 height150 o {required:true,maxlength:300}"></textarea>
                        </div>
                        <div class="add_item mt10">
                        	<label class="firstLabel mr5"></label><input class="inp_radio" type="checkbox" value="1" id="isFirst" name="resolve.isFirst" /><span>设置为首位解析</span>
                        </div>
                        <div class="add_item mt20 txtc">
                        	<input class="bigYellow pub_btn mr20" type="submit" value="确定" /><input id="close_3" class="bigGray pub_btn" type="button" value="关闭" />
                        </div>
                </div>
        </div>
        </form>
  </body>
</html>
