<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>课程信息</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script type="text/javascript">
				 $(document).ready(function() {
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
		
				$(function(){
					if('${course.id}' != ''){
						$('#addForm').attr('action','<%=contextPath%>/feed/course!updateCourse.action');
						$("#selectSubject option[value='${course.subjectId}']").attr("selected","selected");
						$("#selectedStageNum option[value='${course.stageNum}']").attr("selected","selected");	
					}
					
					
					
					if('${course.subjectId}'!= ''){
						var subjectIdArr = '${course.subjectId}';
						if(subjectIdArr == '0'){
							$("input[name='one'][value=${course.subjectId}]").attr("checked","checked");
							$("input[name='checkAll']").attr("checked","checked");
						}else{
							if(subjectIdArr.indexOf(";") > -1){
								var subjectId = subjectIdArr.split(";");
								for(var i=0;i<subjectId.length;i++){
									$("input[name='checkAll'][value="+subjectId[i]+"]").attr("checked","checked");
								} 
							}else{
								$("input[name='checkAll'][value=${course.subjectId}]").attr("checked","checked");
							}
						}
					}
					
					if('${course.courseCategoryId}'!= ''){
						$("#category option[value='${course.courseCategoryId}']").attr("selected","selected");
					}
				});
		
				function addSubmitVerify(updateSign,srcStageNum){
					if($('#subjectHidden').val() == ""){
						alert('请选择专业');
						return;
					}
					if($("#category").val() == -1){
						alert("请选择课程类别");
						return;
					}
					
					if($('#courseName').val() == ''){
						alert('请输入课程名称');
						return ;
					}
					
					if($('#effective').val() == ''){
						alert("请选择课程有效期");
						return;
					}
					
					/**
						校验阶段数是否修改，如果修改是否小于原设定数
					*/
					if(updateSign != null && updateSign == 1){
						var stageNum = $('#selectedStageNum').val();
						if(stageNum != srcStageNum && stageNum < srcStageNum){
							alert("阶段不能小于原设定数");
							return;
						}
						
						try{
							//调用framset left主题页面函数
							window.parent.leftMain.acvReload();
						}catch(e){}
					}
					
					document.getElementById("addForm").submit();
				}
				
				 function allCheck(){
			     	if(!$('#allOrNo').attr('checked')){
			     		$("input[name='checkAll']").removeAttr("checked");
			     		$('#subjectHidden').val(" ");
			     	}else{
			     		$("input[name='checkAll']").attr("checked","checked");//全选
			     		$('#subjectHidden').val("0");
			     	}
			     }
			     
			     function checkOne(value){
			     	if($('#allOrNo').attr('checked')){
			     		$('#allOrNo').removeAttr('checked');
			     		$('#subjectHidden').val(" ");
			     	}else{
			     		var str=""; 
			     		var chekAllLength = $("[name='checkAll']").length;
						$("[name='checkAll']").each(function(){ 
							if($(this).attr('checked')){
								str += $(this).val()+";"; 
							}
						});
						str = str.substring(0,str.length-1);
						var strLength = str.split(";").length;
						if(strLength == chekAllLength){
							$('#allOrNo').attr('checked',"checked");
							 $('#subjectHidden').val("0");
						}else{
							 $('#subjectHidden').val(str);
						}
			     	}
			     }
			     
		</script>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					<s:if test="course.id == null || course.id == ''">
						新建课程
					</s:if>
					<s:else>
						修改课程
					</s:else>
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<s:form namespace="feed" action="course!addCourse.action" method="post" name="addForm" id="addForm">
				<input type="hidden" value="${course.id}" name="course.id"/>
				<input type="hidden" value="${course.status}" name="course.status"/>
				<input type="hidden" name="course.pubDate" value="<s:date name='course.pubDate' format='yyyy-MM-dd HH:mm:ss'/>"/>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
								<tr>
									<td width="10%">
										所属项目专业：
									</td>
									<td class="lists_tleft">
										<input id="allOrNo" name="one" type="checkbox" value="0" onclick="allCheck()"/>全部
										<s:iterator value="subjectList">
											<input name="checkAll" type="checkbox" value="<s:property value="subjectId" />" onclick="checkOne(this.value)"/><s:property value="subjectName"/>
										</s:iterator>
										<input type="hidden" id="subjectHidden" value="${course.subjectId}" name="course.subjectId"/>
									</td>
								</tr>
								<tr>
									<td>所属类别：</td>
									<td class="lists_tleft">
										<select id="category" name="course.courseCategoryId">
											<option value="-1">选择类别</option>
											<s:iterator value="courseCategoryList" var="items">
												<option value ="<s:property value="#items.id"/>"><s:property value="#items.categoryName"/></option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										课程名称：
									</td>
									<td class="lists_tleft">
										<input id="courseName" type="text" name="course.name" value="${course.name}"/>
									</td>
								</tr>
								<tr>
									<td>
										课程简介：
									</td>
									<td class="lists_tleft">
										<input type="text" name="course.introduction" value="${course.introduction}"/>
									</td>
								</tr>
								<tr>
									<td>
										课时：
									</td>
									<td class="lists_tleft">
										<input type="text" name="course.hours" value="${course.hours}"/>
									</td>
								</tr>
								<!--<tr>
									<td>
										课程图片：
									</td>
									<td class="lists_tleft"> 
									 &nbsp;<input type="file" name="fileupload" id="fileupload" /> 
									 <div id="fileQueue"></div> 
									 <p> 
									 &nbsp;<a href="javascript:;" onClick="javascript:uploadifyUpload()">开始上传</a>
									 <%--&nbsp; 
									 <a href="javascript:jQuery('#fileupload').uploadifyClearQueue()">取消所有上传</a> 
									 --%>
									 </p> 
									 <input id="txtPic" name="dis.picurl" type="hidden" value=""/>
									 <ol class=files>
									 	 
									 	<img id="pic" src="<s:property value="dis.picurl" />"/>
									 	 
									 </ol> 
								 </td> 
								</tr>
								--><tr>
									<td>
										课程有效期：
									</td>
									<td class="lists_tleft">
										<input id="effective" name="course.effective" value="<s:date name="course.effective" format="yyyy-MM-dd HH:mm:ss"/>" type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:if test="course.id == null || course.id == ''">
											<input type="button" onclick="addSubmitVerify();" value="下一步" />	
										</s:if>
										<s:else>
											<input type="button" onclick="addSubmitVerify(1,'${course.stageNum}');" value="修改" />
										</s:else>
										
										<input type="button" value="返回" onclick="history.go(-1)"/>
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
			<td class="lists_bottom">
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>