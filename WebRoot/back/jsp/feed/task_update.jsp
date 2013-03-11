<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!-- 新建任务 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建任务</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/uploadify/jquery.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/calendar/WebCalendarMore.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
		<script>
			 $(function(){
			 	KE.show({
   	                    id : 'task_content_update',
   	                    resizeMode : 1,
   	                    allowPreviewEmoticons : false,
   	                    allowUpload : true,
   	                    syncType : 'auto',
   	                    urlType : 'absolute',
   	                    imageUploadJson : '<%=keImageUploadJsonBackAction%>',
   	                    allowFileManager : false,
   	                    items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
   	                        'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
   	                        'code', 'image', '|']
		    	         });
			 });
		</script>
		<script>
			function show_micro_video_item(s){
				alert(s);
			}
		</script>
	</head>
	<body>
		<div>
			<s:form namespace="feed" action="back!doAddTask.action" method="post" name="addForm" id="addForm" enctype="multipart/form-data">
				<!-- 格式: 视频id:视频名称,视频id:视频名称 -->
				<input type="hidden" id="videoStr" name="videoStr" value="<s:property value="videoStr"/> " />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
					<tr class="lists_top">
						<td width="12">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td>
							<font class="lists_fleft">新建任务</font>
							<font class="lists_fright"> </font>
						</td>
						<td width="16">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
								<tr>
									<td width="10%">
										专业选择
									</td>
									<td class="lists_tleft">
										<select name="task.subjectId" onchange="setIframAction()" id="selectSubject">
											<option value="0">全部专业</option>
											<s:iterator value="subjectList">
												<s:if test="subjectId == #task.subjectId">
													<option value="<s:property value="subjectId"/>" selected="selected"><s:property value="subjectName"/></option>
												</s:if>
												<s:else>
													<option value="<s:property value="subjectId"/>"><s:property value="subjectName"/></option>
												</s:else>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										发送模式
									</td>
									<td class="lists_tleft">
										<select id="sendMode" name="task.sendMode" onchange="selectChange();">
											<s:iterator value="sendModeList" var="item">
												<s:if test="#item.value == task.sendModel">
													<option value="<s:property value="#item.value"/>" selected="selected"><s:property value="#item.text"/></option>
												</s:if>
												<s:else>
													<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
												</s:else>
											</s:iterator>
										</select>
									</td>
								</tr>
								<!-- 这部分暂时不修改,需求未定功能详情模式 -->
								<tr id="planningMode">
									<td>
										计划模式
									</td>
									<td class="lists_tleft">
										<select name="task.planningMode.id">
											<s:iterator value="planningModeList">
												<option value="<s:property value="id"/>"><s:property value="name"/></option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr id="regularlySent">
									<td>
										定时发送
									</td>
									<td class="lists_tleft">
										<input type="text" name="regularlySentTime" id="regularlySentTime" maxlength="100" value="<s:property value="task.regularlySentTime"/>" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true" />
									</td>
								</tr>
								<tr>
									<td>
										邮件标题
									</td>
									<td class="lists_tleft">
										<input type="text" name="task.name" value="<s:property value="task.name"/>"/>
									</td>
								</tr>
								<tr>
									<td>
										邮件内容
									</td>
									<td class="lists_tleft">
										<textarea rows="5" cols="75" id="task_content_update" name="task.content"><s:property value="task.content"/></textarea>
									</td>
								</tr>
								<tr>
									<td>
										模板
									</td>
									<td class="lists_tleft">
										<select name="task.templateId">
											<s:iterator value="templateList">
												<s:if test="id == task.templateId">
													<option value="<s:property value="id"/>" selected="selected"><s:property value="name"/></option>
												</s:if>
												<s:else>
													<option value="<s:property value="id"/>"><s:property value="name"/></option>
												</s:else>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										发送对象
									</td>
									<td class="lists_tleft">
										<!-- 
										<input type="radio" name="sendObject" value="1"/>订阅用户
										
										<input type="radio" name="task.sendObject" value="2" checked="checked"/>购买用户
										<input type="radio" name="task.sendObject" value="3"/>注册用户
										<input type="radio" name="task.sendObject" value="99"/>测试用户
										 -->
										<s:iterator value="sendObjectList" var="item">
											<s:if test="task.sendObject == #item.value">
												<input type="radio" name="task.sendObject" value="<s:property value="#item.value"/>" checked="checked"/><s:property value="#item.text"/>
											</s:if>
											<s:else>
												<input type="radio" name="task.sendObject" value="<s:property value="#item.value"/>"/><s:property value="#item.text"/>
											</s:else>
										</s:iterator>
									</td>
								</tr>
								<tr>
									<td>
										视频选项列表
									</td>
									<td class="lists_tleft">
										<div id="haveVideo">
											<s:iterator value="videoList" status="status">
												<li id="<s:property value="id"/>"><s:property value="#status.count"/>,<s:property value="name"/>,<a href="#" onclick="del('<s:property value="id"/>');">删除</a></li>
											</s:iterator>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										视频选择
									</td>
									<td class="lists_tleft">
										<iframe id="videoList" src="<%=contextPath %>/feed/video!getVideoBySubjectId.action?subjectId=<s:property value="task.subjectId"/>" width="1000" height="300"></iframe>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="button" onclick="addSubmitVerify();" value="提交" />
										<input type="reset" value="重置"/>
									</td>
								</tr>
							</table>
						</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>

				</table>
			</s:form>
		</div>
	</body>
</html>


<script type="text/javascript">
	var x = 1;
	var ids = '';
	var idcount =[];
	function selectChange(){
		var sendMode = $("#sendMode").val();
		
		if(sendMode == 1){//计划发送
			$("#planningMode").show();
			$("#regularlySent").hide();
		}else if(sendMode == 2){//定时发送
			$("#planningMode").hide();
			$("#regularlySent").show();
		}
	}
	selectChange();
	
	function addSubmitVerify(){
		var sendMode = $("#sendMode").val();
		var flag = true;
		if(sendMode == 2){
			var regularlySentTime = $("#regularlySentTime").val();
			if(regularlySentTime == null || regularlySentTime.length == 0){
				flag = false;
				$("#regularlySentTime").focus();
				alert("请填写定时发送时间");
				return;
			}
		}
		
		if($.trim($("#haveVideo").html()) == ""){
			flag = false;
			alert("请选择视频");
			return;
		}
		
		if(flag == true){
			document.getElementById("addForm").submit();
		}
	}
	/**根据专业自动给iframe设置src路径
	*/
	function setIframAction(){
		var url = $('#videoList').attr('src');
		var UrlReg = new RegExp("\\?subjectId=[0-9]*");
		if($('#selectSubject').val() != '-1'){
		 	url = url.replace(UrlReg,"?subjectId=" + $('#selectSubject').val());
			$('#videoList').attr('src',url);
		}else{
			$('#videoList').attr('src','<%=contextPath %>/feed/video!getVideoBySubjectId.action?subjectId=0');
		}
	}
	
	function addVideo(obj){
	 	var str = obj.split('-');
		if($('#haveVideo li').length == 0){
			for(var i=0;i<str.length-1;i++){
				$('#haveVideo').append("<li id='"+str[i].split('_')[1]+"'>"+(x)+","+str[i].split('_')[0]+",<a href='#' onclick='del("+str[i].split('_')[1]+")'>删除</a></li>");
				ids += str[i].split('_')[1]+":"+str[i].split('_')[0]+",";
				x++;
			}
		}else{
			var isHave = false;
			$('#haveVideo li').each(function(index,obj){
				for(var i=0;i<str.length-1;i++){
					if($(obj).attr('id') == str[i].split('_')[1]){
						isHave = true;
					}
				}
			});
			if(isHave){
				alert("添加的视频地址重复了，请重新选择！");
				return;
			}else{
				for(var i=0;i<str.length-1;i++){
					$('#haveVideo').append("<li id='"+str[i].split('_')[1]+"'>"+(x)+","+str[i].split('_')[0]+",<a href='#' onclick='del("+str[i].split('_')[1]+")'>删除</a></li>");
					ids += str[i].split('_')[1]+":"+str[i].split('_')[0]+",";
					x++;
				}
			}	
		}
		 idcount = ids.split(",");
		$("#videoStr").val(ids);
	}
	
	function del(obj){
		var str = "";
		for(var i=0;i<idcount.length-1;i++){
			if(idcount[i].split(":")[0] != obj){
				str += idcount[i]+",";
			}
		}
		idses(str);
		$("#videoStr").val(str);
		$("#"+obj).remove();
	}

	function idses(str){
		idcount = str.split(",");
		ids = str;
	}
</script>

