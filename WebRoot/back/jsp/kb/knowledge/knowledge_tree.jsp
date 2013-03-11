<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>知识点</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<link rel="StyleSheet" href="<%=contextPath%>/back/style/dtree.css"
			type="text/css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/dtree.js"></script>

		<script type="text/javascript">

		function onchangeProfessional(pId){
			$.ajax({
				url: "<%=contextPath%>/kb/studyCourse!getStudyCourseListByPid.action",
				data : {pId : pId},
				type : "post",
				cache : false,
				dataType : "json",
				error : function(){
				alert('error');
				},
				success:onchangecallback
			});
		  }
		function onchangecallback(result){
				var scList = eval(result.returnMessage);
				document.getElementById('spId').options.length = 0; //清空原有的option 
				$("#spId").html("<option value=-1>请选择</option>");
				var str="";
				for(var i=0;i<scList.length;i++){  
					str+="<option value='"+scList[i].CId+"'>"+scList[i].CName+"</option>"  
				}  
				if(str!=""){
					$("#spId").html(str);  
				}
		}
		function toDeleteKnowledge(kId){
			if(confirm('是否删除？')){
			$.ajax({
				url: "<%=contextPath%>/kb/knowledge!deleteKnowledge.action",
				data : {kId : kId},
				type : "post",
				cache : false,
				dataType : "json",
				error : function(){
				alert('error');
				},
				success:onback
			});
			}
		}
		function onback(result){
				if(result != null  &&  result.returnMessage != ''){
					alert("请删除子项！");
				}else{
					window.location.reload();
				}
		}
	  	function onSelectCourse(courseid){ 
	  			 //alert(courseid);
				document.getElementById('knowledge.scId').value = courseid;
			}
		function checkSubmit(){
				//var fid = document.getElementById('kpoint.courseId').value;
				
				//if(fid == null || fid == -2 || fid == 0){
				//	document.getElementById('course.sortId').value=0;
				//	alert(请选择课程);
				//	return false;
				//}
				
				//var level = document.getElementById('kpoint.level').value;
				
				//if(level == 0){
				//	document.getElementById('kpoint.level').value = 1;
				//}
				return true;
			}
		
		function addTree() {
			var kName = document.getElementById("knowledge.kName").value;
			if(kName.length==0){
				alert("名称不能为空！");
				return;
			}
			var kSort = document.getElementById("knowledge_kSort").value;
			if(kSort==null){
			   laert();
			}
			if(isNaN(kSort)) {//is not a number
				alert("排序只能为数字！");
				return;
			} else {
				document.addknowledgeform.submit();
			}
		}
       </script>
	</head>
	<body>
		<div>
			<form name="courseselectform" id="courseselectform"
				action="<%=contextPath%>/kb/knowledge!getKnowledgeTree.action"
				method="post" onsubmit="checkSubmit();">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">当前位置:[知识体系管理>>选择知识点所属课程]</font>
							<font class="lists_fright"> </font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info">
								<tr>
									<td>
										选择专业：
									</td>
									<td class="lists_tleft">
										<s:select name="fpId" id="fpId" list="professionalList"
											listKey="pId" listValue="pName" headerValue="请选择"
											headerKey="-1" theme="simple"
											onchange="onchangeProfessional(this.value)">
										</s:select>

									</td>
								</tr>
								<tr>
									<td>
										选择科目：
									</td>
									<td class="lists_tleft">
										<s:select name="spId" id="spId" list="#{}" listKey="cId"
											listValue="cName" headerValue="请选择" headerKey="-1"
											onchange="onSelectCourse(this.value)">
										</s:select>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<s:hidden name="knowledge.scId" id="knowledge.scId"></s:hidden>
										<s:submit value="查询" />
										<input type="button" value="返回" onclick="history.go(-1)"/>
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
			</form>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				class="lists">
				<tr >
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td></td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info">
							<tr class="lists_infobg">
								<td width="400">
									课程树
								</td>
								<td>
									课程节点信息
								</td>
							</tr>

							<tr>
								<td valign="top">
									<s:if test="knowledgeList != null">
										<script type="text/javascript">
									addkpoint = new dTree('addkpoint','<%=contextPath%>/back/images/dtree');
									addkpoint.add(-2,-1,'科目：<s:property value="studycourse.cName"/>');
									<s:iterator value="knowledgeList">
										addkpoint.add(<s:property value="kId"/> ,<s:property value="kPId"/> ,'<s:property value="kName"/>','<%=contextPath%>/kb/knowledge!getKnowledgeTree.action?knowledge.scId=<s:property value="scId"/>&index=<s:property value="kIndex"/>');
									</s:iterator>
									document.write(addkpoint);
								</script>
							</s:if>
						</td>
						<td>
						<form action="<%=contextPath%>/kb/knowledge!addKnowledgeTree.action"  method="post" name="addknowledgeform" id="addknowledgeform" onsubmit="return textval()">
					    <table border="0" cellspacing="1" cellpadding="0" class="lists_info" style="width:90%;">
					    <tr><td>新建项目：</td><td colspan="3"></td></tr>
					    <tr>
					    <td>名称：<input type="text" id="knowledge.kName" name="knowledge.kName"/>
					    <s:if test="index==null">
					    <!--<s:property value="studycourse.cIndex"/>-->
					    <input type="hidden" name="knowledge.kPId" value="-2">
				        <input type="hidden" name="knowledge.plId" value="<s:property value="studycourse.pId"/>">
				        <input type="hidden" name="knowledge.upIndex" value="<s:property value="studycourse.cIndex"/>">
				        <input type="hidden" name="knowledge.kVersion" value="<s:property value="studycourse.cVersion"/>">
				        <input type="hidden" name="knowledge.scId" value="<s:property value="studycourse.cId"/>">
					    </s:if>
					    <s:else>
					   <!-- <s:property value="index"/>-->
					    <input type="hidden" name="knowledge.kPId" value="<s:property value="knowledge.kId"/>">
					    <input type="hidden" name="knowledge.plId" value="<s:property value="knowledge.plId"/>">
				        <input type="hidden" name="knowledge.upIndex" value="<s:property value="knowledge.kIndex"/>">
				        <input type="hidden" name="knowledge.kVersion" value="<s:property value="knowledge.kVersion"/>">
				        <input type="hidden" name="knowledge.scId" value="<s:property value="knowledge.scId"/>">
					    </s:else>
					    <input type="hidden" name="index" value="<s:property value="index"/>" />
					    <!--  <input type="text" name="knowledge.kIndex"  size="3" class="{required:true,minlength:2,maxlength:50}"/>-->
					    </td>
					    <td>排序：<input type="text" id="knowledge_kSort" name="knowledge.kSort" />
					    <td>说明：<textarea rows="" cols="" name="knowledge.kNote" style="height: 80px; width: 60%;"></textarea><input type="button" onclick="addTree()" name="s1" value="确定"/></td></td>
					    </tr>
					    <tr><td>项目列表：</td><td colspan="3"></td></tr>
					    <tr><td>索引</td><td>名称</td><td>操作</td></tr>
					    <s:if test="newKList.size!=0">
					    <s:iterator value="newKList" id="knowledge">
					    <tr><td><s:property value="#knowledge.kIndex"/></td><td><s:property value="#knowledge.kName"/></td><td><a href="#" onclick="toDeleteKnowledge(<s:property value="#knowledge.kId"/>)">删除</a>　<a href="<%=contextPath %>/kb/knowledge!toEditKnowledge.action?knowledge.kId=<s:property value="#knowledge.kId"/>">修改</a>　<a href="<%=contextPath %>/kb/knowledge!toAddNextKnowledge.action?knowledge.kId=<s:property value="#knowledge.kId"/>">新建子级</a></td></tr>
					    </s:iterator>
					    </s:if>
					    <s:else>
					    <tr><td colspan="3">没有子项！</td></tr>
					    </s:else>
					    </table>
					  </form>
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
	</body>
</html>
