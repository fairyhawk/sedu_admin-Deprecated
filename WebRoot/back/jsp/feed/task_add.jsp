<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
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
   	                    id : 'task_content_add',
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
		
	</head>
	<body>
		<table width="100%" border="0" bordercolor="#00ccff" style="font-size: 12px;">
			<tr>
				<td colspan="2">
					新建任务
				</td>
			</tr>
		</table>
			<s:form namespace="feed" action="back!doAddTask.action" method="post" name="addForm" id="addForm" enctype="multipart/form-data">
				<!-- 格式: 视频id:视频名称,视频id:视频名称 -->
				<input type="hidden" id="videoStr" name="videoStr" value="" />
				<input type="hidden" id="emailStr" name="emailStr" value="" />
							<table width="100%" border="1" bordercolor="#00ccff" style="border-collapse: collapse;font-size: 12px;">
								<tr>
									<td width="8%">
										专业选择
									</td>
									<td class="lists_tleft">
										<select name="task.subjectId" onchange="setIframAction()" id="selectSubject">
											<option value="0">全部专业</option>
											<s:iterator value="subjectList">
												<option value="<s:property value="subjectId"/>"><s:property value="subjectName"/></option>
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
												<option value="<s:property value="#item.value"/>"><s:property value="#item.text"/></option>
											</s:iterator>
										</select>
									</td>
								</tr>
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
										<input type="text" name="regularlySentTime" id="regularlySentTime" maxlength="100" value="" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly="true" />
									</td>
								</tr>
								<tr>
									<td>
										邮件标题
									</td>
									<td class="lists_tleft">
										<input type="text" name="task.name"/>
									</td>
								</tr>
								<tr>
									<td>
										邮件内容
									</td>
									<td class="lists_tleft">
										<textarea id="task_content_add" name="task.content" cols="100" rows="8"></textarea>
									</td>
								</tr>
								<tr>
									<td>
										模板
									</td>
									<td class="lists_tleft">
										<select name="task.templateId">
											<s:iterator value="templateList">
												<option value="<s:property value="id"/>"><s:property value="name"/></option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										发送对象
									</td>
									<td class="lists_tleft">
										<s:iterator value="sendObjectList" var="item">
											<input type="radio" name="task.sendObject" value="<s:property value="#item.value"/>"/><s:property value="#item.text"/>
										</s:iterator>
									</td>
								</tr>
								<tr>
									<td>
										视频选项列表
									</td>
									<td class="lists_tleft">
										<div id="haveVideo">
										</div>
									</td>
								</tr>
								<tr>
									<td>
										视频选择
									</td>
									<td class="lists_tleft">
										<iframe id="videoList" src="<%=contextPath %>/feed/video!getVideoBySubjectId.action?queryMicroVideoCondition.currentPage=1&subjectId=0" width="100%" height="300"></iframe>
									</td>
								</tr>
								<tr>
									<td  class="lists_tleft">
										邮箱名查询：
									</td>
									<td class="lists_tleft">
									<!--<form name="customerListFrame" action="<%=contextPath %>/feed/cuscustomer!getByemail.action" method="post">
									</form>
									<form name="customerListFrame" action="<%=contextPath %>/feed/cuscustomer!queryemail.action" method="post">
									</form>
									-->
										<input id="select" type="text" name="queryCusCustomerCondition.email">
										<a href="#" onclick="selectEmai()"/>查询
									</td>
								</tr>
								<tr>
									<td>
										邮件选项列表
									</td>
									
									<td class="lists_tleft">
										<div id="haveTask">
										</div>
									</td>
								</tr>
									<script>
									function selectEmai(){
										var value=document.getElementById("select").value;
										if(value == ''){
											$('#customerListFrame').attr('src',"<%=contextPath %>/feed/cuscustomer!getByemail.action?queryCusCustomerCondition.currentPage=1'");
										}else{
											$('#customerListFrame').attr('src',"<%=contextPath %>/feed/cuscustomer!queryemail.action?queryCusCustomerCondition.currentPage=1&queryCusCustomerCondition.email="+value);
										}
									}
									</script>
									</tr>
								<tr>
									<td>
										手选用户									
									</td>
									<td>
										<iframe id="customerListFrame" name="select" src="<%=contextPath %>/feed/cuscustomer!getByemail.action?queryCusCustomerCondition.currentPage=1" width="100%" height="300"></iframe>
									</td>									
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="button" onclick="addSubmitVerify();" value="提交" />
										<input type="reset" value="重置"/>
									</td>
								</tr>
							</table>
			</s:form>
	</body>
</html>


<script type="text/javascript"><!--
	var x = 1;
	var ids = '';
	var idcount =[];
	var temp = '';
	var tempcount=[];
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
		var UrlReg = new RegExp("\\&subjectId=[0-9]*");
		if($('#selectSubject').val() != '-1'){
		 	url = url.replace(UrlReg,"&subjectId=" + $('#selectSubject').val());
			$('#videoList').attr('src',url);
		}else{
			$('#videoList').attr('src','<%=contextPath %>/feed/video!getVideoBySubjectId.action?queryMicroVideoCondition.currentPage=1&subjectId=0');
		}
	}
	
	function addVideo(obj){
	 	var str = obj.split('&');
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
	
	
	//添加任务
	function addTask(object){
			var str=object.split('&');
			if($('#haveTask li').length == 0){
				for(var i=0;i<str.length-1;i++){
					$('#haveTask').append("<li id='"+str[i].split('^')[1]+"'>"+(x)+","+str[i].split('^')[0]+",<a href='#' onclick='del("+str[i].split('^')[1]+")'>删除</a></li>");
					temp += str[i].split('^')[1]+":"+str[i].split('^')[0]+",";
					x++;
				}
			}else{
				 var flag = false;
				$('#haveTask li').each(function(index,object){
					for(var i=0;i<str.length-1;i++){
						if($(object).attr('id') == str[i].split('_')[1]){
							flag = true;
						}
					}
				});
				if(flag){
					alert("添加的邮箱地址重复了，请重新选择！");
					return;
				}else{
					for(var i=0;i<str.length-1;i++){
						$('#haveTask').append("<li id='"+str[i].split('^')[1]+"'>"+(x)+","+str[i].split('^')[0]+",<a href='#' onclick='del("+str[i].split('^')[1]+")'>删除</a></li>");
						temp += str[i].split('^')[1]+":"+str[i].split('^')[0]+",";
						x++;
					}
				}
			}
			tempcount = temp.split(",");
			
			$("#emailStr").val(temp);
	}
	//删除任务
	
	
	
	function delete(object){
		var str="";
		for(var i=0;i<tempcount.length-1;i++){
				if(tempcount[i].split(":")[0] !=object){
					str += tempcount[i]+",";
				}
		}
		comm(str);
		$("#emailStr").val(str);
		$("#"+object).remove();
	
	}
	function comm(str){
		tempcount = str.split(",");
		temp = str;
	}
</script>

