<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!-- 添加视频 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加视频</title>
		<script language="javascript" type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<base target="_self" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/feed/feed.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script type="text/javascript">
		    $(document).ready(function()
		    {
		        $("#fileupload").uploadify({
		                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
		                    //'script'  :'http://tp.highso.cn:8086/upload!go.action?param=feed',//测试机
		                    'script'  :'http://tp.highso.cn:8080/upload!go.action?param=feed',//线上
		                    'queueID':'fileQueue',
		                    'fileDataName':'fileupload',
		                    'auto':false,
		                    'multi':false,
		                    'buttonText':'Browse',
		                    'simUploadLimit' : 3,
		                    'sizeLimit'      : 19871202,
		                    'queueSizeLimit' : 2,
		                    'fileDesc'       : '支持格式:doc,docx',
		                    'fileExt'        : '*.doc;*.docx',
		                    'folder' : '/upload',
		                    onComplete: function (event, queueID, fileObj, response, data)
		                    {
		                    	alert("上传成功");
		                    	$("#lectureUrl").val(response);
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
		    function uploadifyUpload(){ 
				$('#fileupload').uploadifyUpload(); 
			}
			
			$(function(){
				$("#time1").blur(function(){
					var time1 = $(this).val();
					$("#time1Hidden").val(getMin(time1));
				});
				
				$("#time2").blur(function(){
					var time2 = $(this).val();
					$("#time2Hidden").val(getMin(time2));
				});
				
				$("#time3").blur(function(){
					var time3 = $(this).val();
					$("#time3Hidden").val(getMin(time3));
				});
			});
			/**
			*把时分秒转换成秒
			*/
			function getMin(timeValue){
				 var second = 0;
				 var timeStr = new Array();
			     timeStr = timeValue.split(/[-\s:]/);
			     if(timeStr[0] !=0){
			     	second = timeStr[0] * 60 *60;
			     }
			     if(timeStr[1] !=0){
			     	second += timeStr[1] * 60;
			     }
			     second += timeStr[2] * 1;
			     
			     return second;
			}
			
			function setHiddenValue(){
				$("#recommendHidden").val($("input[name='recommend']:checked").val());
			}
		</script>
		<script type="text/javascript">
		    $(document).ready(function()
		    	    {
		    	        // 初始化 Kindeditor
		    	        KE.show({
		    	                    id : 'txt_video_content',
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
			function submitAction(){
				if($("#videoName").val()==''){
					alert("请填写视频名称");
					return;
				}
				if($("#videoUrl").val()==''){
					alert("请填写视频地址");
					return;
				}
				if($("#teacher").val() == ''){
					alert("请填写老师姓名");
					return;
				}
				if($("#lectureUrl").val() == ''){
					alert("请上传讲义地址");
					return;
				}
				if($("#effective").val()==''){
					alert("请选择视频有效期");
					return;
				}
				var maxChar = 500;
				if($("#txt_video_content").val().length > maxChar){
					alert("视频讲义内容超限，请控制在" + maxChar + "以内");
					return;
				}
				
				//调用framset left主题页面函数
				window.parent.leftMain.acvReload();
				document.getElementById("addForm").submit();
			}
		</script>
		<script type="text/javascript">
		
			var actionUrl = "<%=contextPath%>/feed/course!toKnowledgeTree.action?ksId=-1";
			/**初始化一个default选项*/
			$(document).ready(function() {
				setIframAction(actionUrl);
			});
			
			
		</script>
	</head>
	<body>
		
		<div>
					<form action="<%=contextPath%>/feed/video!createVideo.action" method="post" name="addForm" id="addForm">
							<table width="100%" border="1" bordercolor="#00ccff" style="border-collapse: collapse;font-size: 12px;">
							
								<tr>
									<td width="20%">
										上节节点:
									</td>
									<td>
										<s:property value="course.name"/>
										<input type="hidden" value="<s:property value="course.id"/>" name="microVideo.courseId"/>
										<input type="hidden" value="<s:property value="course.subjectId"/>" name="microVideo.subjectId"/>
									</td>
								</tr>
								<tr>
									<td>
										视频名称：
									</td>
									<td class="lists_tleft">
										<input type="text" value="${microVideo.name}" name="microVideo.name" id="videoName" style="width: 800px;"/>
									</td>
								</tr>
								<tr>
									<td>
										视频地址：
									</td>
									<td class="lists_tleft">
										<input type="text" value="${microVideo.url}" name="microVideo.url" id="videoUrl" style="width: 800px;"/>
									</td>
								</tr>
								<tr>
									<td>
										排序：
									</td>
									<td class="lists_tleft">
										<input type="text" value="${microVideo.orderList}" name="orderList"  style="width: 50px"/>
									</td>
								</tr>
								<tr>
									<td>
										视频知识点1：
									</td>
									<td class="lists_tleft">
										<textarea rows="3" cols="60" name="microVideo.knowledge1Context"></textarea>
									</td>
								</tr>
								<tr>
									<td>
										时间：
									</td>
									<td class="lists_tleft">
										<input type="text" id="time1" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm:ss'})" class="Wdate"/>
										<input id="time1Hidden" type="hidden"  name="microVideo.knowledge1Time" />
									</td>
								</tr>
								<tr>
									<td>
										视频知识点2：
									</td>
									<td class="lists_tleft">
										<textarea rows="3" cols="60" name="microVideo.knowledge2Context"></textarea>
									</td>
								</tr>
								<tr>
									<td>
										时间：
									</td>
									<td class="lists_tleft">
										<input type="text" id="time2" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm:ss'})" class="Wdate"/>
										<input id="time2Hidden" type="hidden"  name="microVideo.knowledge2Time" />
									</td>
								</tr>
								<tr>
									<td>
										视频知识点3：
									</td>
									<td class="lists_tleft">
										<textarea rows="3" cols="60" name="microVideo.knowledge3Context"></textarea>
									</td>
								</tr>
								<tr>
									<td>
										时间：
									</td>
									<td class="lists_tleft">
										<input type="text" id="time3" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm:ss'})" class="Wdate"/>
										<input id="time3Hidden" type="hidden"  name="microVideo.knowledge3Time" />
									</td>
								</tr>
								<tr>
									<td>
										老师：
									</td>
									<td class="lists_tleft">
										<input type="text" id="teacher" value="${microVideo.teachers}" name="microVideo.teachers"  style="width: 200px"/>
									</td>
								</tr>
								<tr>
									<td>
										讲义附件：
									</td>
									<td class="lists_tleft">
										<input type="text" readonly="readonly" id="lectureUrl" name="microVideo.lectureUrl" value="" style="width: 800px;"/>
										<br />
										<input type="file" name="fileupload" id="fileupload" /> 
										<div id="fileQueue"></div> 
										<a href="javascript:;" onClick="javascript:uploadifyUpload()">开始上传</a>
									</td>
								</tr>
								<tr>
									<td width="20%;">视频讲义：</td>
									<td class="lists_tleft">
										<textarea id="txt_video_content" name="microVideo.content" rows="3" cols="60">${microVideo.content}</textarea>
									</td>
								</tr>
								<tr>
									<td>
										重要程度：
									</td>
									<td class="lists_tleft">
										<select style="width: 200px;" name="microVideo.importance">
											<s:iterator value="importanceList" var="item">
												<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										知识树专业列表：
									</td>
									<td class="lists_tleft">
										<select style="width: 200px;" id="ksId" onchange="setIframAction('<%=contextPath%>/feed/course!toKnowledgeTree.action?ksId=' + this.value);">
											<option value="-1">===请选择知识树===</option>
											<s:iterator value="sysList" var="sys">
												<option value="<s:property value="#sys.ksId"/>"><s:property value="#sys.subjectName"/></option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										关联知识树：
									</td>
									<td class="lists_tleft">
										<input id="ksnId" name="microVideo.ksnId" value="" type="hidden" />
										<div id="haveKnowTreeList"></div>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<td class="lists_tleft" colspan="2">
										<iframe id="frmKnowTree" name="frmKnowTree" src="" style="width:800px;height:300px;"></iframe>
									</td>
								</tr>
								<tr>
									<td>视频显示有效期：</td>
									<td><input id="effective" type="text" name="microVideo.effective" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true"/></td>
								</tr>
								<tr>
									<td>免费视频：</td>
									<td>
										<input name="free" type="radio" value="1" checked="checked"/>是
										<input name="free" type="radio" value="2" disabled="disabled"/>否
										<input type="hidden" name="microVideo.isFree" value="1"/>
									</td>
								</tr>
								<tr>
									<td>显示在推荐位：</td>
									<td>
										<input name="recommend" type="radio" value="1" checked="checked" onclick="setHiddenValue()"/>是
										<input name="recommend" type="radio" value="2" onclick="setHiddenValue()"/>否
										<input id="recommendHidden" type="hidden" name="microVideo.isRecommend" value="1"/>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="button" onclick="history.go(-1)" value="返回" />
										<input type="button" onclick="submitAction();" value="完成" />
									</td>
								</tr>
							</table>
					</form>		
		</div>
	</body>
</html>


