<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改小组</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>

		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript">
	    	$(document).ready(function()
	    	{
	    	        // 初始化 Kindeditor
	    	        KE.show({
	    	                    id : 'txt_topic',
	    	                    resizeMode : 1,
	    	                    allowPreviewEmoticons : false,
	    	                    allowUpload : true,
	    	                    syncType : 'auto',
	    	                    urlType : 'absolute',
	    	                    imageUploadJson : '<%=keImageUploadJsonBackAction%>',
	    	                    allowFileManager : false,
	    	                    items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
	    	                        'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
	    	                        'code', 'image', 'flash', '|']
	    	                });
	    	});
			$().ready(function() {
				$("#select_subject").change(function(){
					var checkText=$("#select_subject").find("option:selected").text(); 
				}); 
			});
		</script>
		<script type="text/javascript">
		    $(document).ready(function()
		    {
		        $("#fileupload").uploadify({
		                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
		                    'script'  :'http://tp.highso.cn:8080/upload!face.action',
		                    'queueID':'fileQueue',
		                    'fileDataName':'fileupload',
		                    'auto':false,
		                    'multi':false,
		                    'buttonText':'Browse',
		                    'simUploadLimit' : 3,
		                    'sizeLimit'      : 19871202,
		                    'queueSizeLimit' : 2,
		                    'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
		                    'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
		                    'folder' : '/upload',
		                    onComplete: function (event, queueID, fileObj, response, data)
		                    {
		                    	 $("#pic").attr("src",response);
		                    	 $("#txtPic").attr("value", response);
		
		                    },
		                    onError: function(event, queueID, fileObj)
		                    {
		                        alert("文件:" + fileObj.name + "上传失败");
		                    },
		                    onCancel: function(event, queueID, fileObj)
		                    {
		                        alert("取消了" + fileObj.name);
		                    }
		                });
		
		    });
		</script>
		<script type="text/javascript"> 
                   //必须的 
				 function uploadifyUpload(){ 
				    $('#fileupload').uploadifyUpload(); 
				 }
				 function editSubmitVerify(){
				
					var name = $.trim($("#name").val());
					
					if(name.length == 0){
						alert("请填写小组名称");
						return;
					}
					
					document.getElementById("editForm").submit();
				} 
 		</script>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
			<tr>
				<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
				<td class="lists_top">
					<font class="lists_fleft">修改小组</font>
					<font class="lists_fright"></font>
				</td>
				<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
			</tr>
			<tr>
				<td class="lists_bor"></td>
				<td>
					<s:form action="back!editDis.action" method="post" name="editForm" id="editForm" enctype="multipart/form-data">
					<input type="hidden" name="srcCreateTime" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="dis.createtime"/>" />
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info crm_lineh">

								<tr>
									<td width="10%"  class="crm_tableFR">
										小组类型：
									</td>
									<td class="lists_tleft">
								&nbsp;<select id="dis.type" name="dis.type" style="width: 100px;">
											<option value="0" selected="selected">
												固定小组
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="crm_tableFR">
										所属项目：
									</td>
									<td class="lists_tleft">
									<input type="hidden" name="dis.subjectId" value="<s:property value="dis.subjectId"/>" />
									&nbsp;<s:property value="dis.subject"/>
										<!-- 
										<select id="select_subject" name="dis.subjectId" style="width: 100px;">
												<s:iterator value="#session.subjectList">
					                    			<s:if test="dis.subjectId == subjectId">
					                    				<option value="<s:property value="subjectId"/>" selected="selected"><s:property value="subjectName"/></option>
					                    			</s:if>
					                    			<s:if test="dis.subjectId != subjectId">
					                    				<option value="<s:property value="subjectId"/>"><s:property value="subjectName"/></option>
					                    			</s:if>
					                    		</s:iterator>
										</select>
										 -->
									</td>
								</tr>
								<tr>
									<td class="crm_tableFR">
										小组名称：
									</td>
									<td class="lists_tleft">
										&nbsp;<input type="text" id="name" name="dis.name" value="<s:property value="dis.name" />" />
									</td>
								</tr>
								<tr>
									<td class="crm_tableFR">
										状&nbsp;态：
									</td>
									<td class="lists_tleft">
										&nbsp;<select id="dis.status" name="dis.status" style="width: 100px;">
											<s:iterator value="statusDisList" var="item">
												<s:if test="dis.status == #item.value">
													<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
												</s:if>
												<s:else>
													<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
												</s:else>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td class="crm_tableFR">
										创建人：
									</td>
									<td class="lists_tleft">
										&nbsp;<input type="text" name="dis.creatuser" value="<s:property value="dis.creatuser" />"/>
									</td>
								</tr>
								<tr> 
								 <td class="crm_tableFR">小组头像：</td> 
								 <td class="lists_tleft"> 
									&nbsp;<input type="file" name="fileupload" id="fileupload" /> 
									 <div id="fileQueue"></div> 
								 <p> 
								&nbsp;<a href="javascript:;" onClick="javascript:uploadifyUpload()">开始上传</a><%--&nbsp; 
								 <a href="javascript:jQuery('#fileupload').uploadifyClearQueue()">取消所有上传</a> 
								 --%></p> 
								 <input id="txtPic" name="dis.picurl" type="hidden" value=""/>
								 <ol class=files><img id="pic" src="<s:property value="dis.picurl" />"/></ol> 
								 </td> 
								 </tr>

                                <tr>
									<td class="crm_tableFR">
										小组特点：
									</td>
									<td class="lists_tleft">
            							&nbsp;<textarea name="dis.features" cols="65" rows="5"><s:property value="dis.features" /></textarea>
                                        <br />
									</td>
								</tr>
								<tr>
									<td class="crm_tableFR">
										小组介绍：
									</td>
									<td class="lists_tleft">
            							&nbsp;<textarea id="txt_topic" name="dis.introduction" cols="100" rows="8" style="width:560px;height:200px;visibility:hidden;"><s:property value="dis.introduction" /></textarea>
                                        <br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<font color="red">
											<s:if test="#request.resMsg == 1">
												文件太大					
											</s:if>
										</font>
									</td>
								</tr>
								<tr>
									<td colspan="2">
									<s:hidden name="dis.id" id="id"></s:hidden>
										<input type="button" onclick="editSubmitVerify();" value="提  交"/>
									</td>
								</tr>
							</table>
					</s:form>
				</td>
				<td class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td class="td_wid_l">
					<img src="<%=contextPath%>/back/images/tab_18.gif" />
				</td>
				<td class="lists_bottom"></td>
				<td class="td_wid_r">
					<img src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
		</table>
	</body>
</html>