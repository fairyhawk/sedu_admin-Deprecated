<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加试题</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript" src="<%=contextPath %>/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
		
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>
		
		<link rel="shortcut icon" href="../fckeditor.gif"
				type="image/x-icon" />
		<script type="text/javascript">
			
				$().ready(function() {
				$("#exampaperupdateform").validate({   
			        rules: {   
			            fSortId: {
			            	required:true,
			            	min: 0
			            },
			            sSortId: {
			            	required:true,
			            	min: 0
			            },
			            tSortId: {
			            	required:true,
			            	min: 0
			            },
			            courseId: {
			            	required:true,
			            	min: 0
			            },
			            "exam.subjectId": {
			            	required:true,
			            	min: 0
			            },
			            "exam.coeffcient": {
			            	required:true,
			            	min: 0
			            }
			        },   
			        messages: {   
			            fSortId: {
					            	required:"请选择分类",
					            	min: "请选择分类"
					    },
					    sSortId: {
					            	required:"请选择分类",
					            	min: "请选择分类"
					    },
					    tSortId: {
					            	required:"请选择分类",
					            	min: "请选择分类"
					    },
					    courseId: {
					            	required:"请选择课程",
					            	min: "请选择课程"
					    },
					    "exam.subjectId": {
					            	required:"请选择所属专业",
					            	min: "请选择所属专业"
					    },
					    "exam.coeffcient": {
					            	required:"请选难度",
					            	min: "请选难度"
					    }
			        }   
	   	 		});  
			})
				
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
				success:onchangecallback11
			});
		  }
		function onchangecallback11(result){
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
				
				
				function onSelectCourse1(zkid){ 
				document.getElementById('exam.type').value = 1;
				document.getElementById('exam.zkid').value = zkid;
				$.ajax({  
					url : "<%=contextPath %>/kb/knowledge!getKnowledgeTreeJson.action",  
					data : {"knowledge.scId" : zkid},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackpoint11 
					});
				
			}
			
			function onchangecallbackpoint11(result){
				
				var myList = eval(result.returnMessage);
				var lanmu = result.jumpUrl;
				if(myList.length<1){
					$("#zpointList").html("");
					return;
				}
				$("#zpointList").show();
				addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
				addkpoint.add(-2,-1,lanmu+'栏目列表 <a href="javascript:closeColumn1();">关闭</a>');
				for(var i=0;i<myList.length;i++){ 
					
					addkpoint.add(myList[i].KId ,myList[i].KPId ,myList[i].KName,'javascript:changeColumnValue('+myList[i].KId+',' + "'"+myList[i].KName+ "'"+')');
					
				}
				
				$("#zpointList").html(addkpoint.toString());
			}
			
			function closeColumn1(){
				obj="zpointList";
				document.getElementById(obj).style.display="none";
			}
			
			function ceshi(valueid)
			{
				if(valueid==1)
				{
				$("#ceshiwordname").html("<font color='#ff0000'>请选择年份:*</font>");
				
				$("#eptypekeyword").html("<option value='2011'>2011年</option><option value='2010'>2010年</option><option value='2009'>2009年</option><option value='2008'>2008年</option><option value='2007'>2007年</option><option value='2006'>2006年</option><option value='2005'>2005年</option><option value='2004'>2004年</option><option value='2003'>2003年</option><option value='2002'>2002年</option><option value='2001'>2001年</option><option value='2000'>2000年</option>");
				}
				if(valueid==2)
				{
					$("#ceshiwordname").html("<font color='#ff0000'>请选择难度:*</font>");
				
				$("#eptypekeyword").html("<option value='1'>简单</option><option value='2'>普通</option><option value='3'>困难</option>");
				}
				
				if(valueid==3)
				{
					var danyuan = document.getElementById('exam.kOrCouId').value;
					var danyuanname = document.getElementById('pName').value;
					if(danyuan!=""&&danyuanname!="")
					{
						$("#ceshiwordname").html("<font color='#ff0000'>请选择知识单元:*</font>");
					
						$("#eptypekeyword").html("<option value='"+danyuan+"'>"+danyuanname+"</option>");
					}
					else
					{
						$("#ceshiwordname").html("测试类型关键字");
					
						$("#eptypekeyword").html("<option selected='selected' value='-1'>请选测试类型关键字</option>");
					}
				}
				
					if(valueid==4)
					{
						var zhuanti = document.getElementById('exam.kOrCouId').value;
						var zhuantiname = document.getElementById('pName').value
						if(zhuanti!=null&&zhuantiname!=null)
						{
						$("#ceshiwordname").html("<font color='#ff0000'>请选择专题:*</font>");
					
						$("#eptypekeyword").html("<option value='"+zhuanti+"'>"+zhuantiname+"</option>");
						}
						else
						{
							$("#ceshiwordname").html("测试类型关键字");
						
							$("#eptypekeyword").html("<option selected='selected' value='-1'>请选测试类型关键字</option>");
						}
					}
				
				   if(valueid==-1)
					{
						$("#ceshiwordname").html("测试类型关键字");
					
						$("#eptypekeyword").html("<option selected='selected' value='-1'>请选测试类型关键字</option>");
					}
			
			
			}
			
			function changeColumnValue(id,name){
				document.getElementById('exam.type').value = 2;
				var f = document.getElementById('exam.type').value;
				document.getElementById('exam.zkid').value = id;
				
				obj="zpointList";
				document.getElementById("zpName").value = name;
				document.getElementById(obj).style.display="none";
			}
			
			
			function IsIE()
			{
			    if (window.navigator.userAgent.indexOf("MSIE")>=1)
			    {
			        //IE浏览器
			        return true;
			    }else{
			        return false;
			    }
			}
			function IsFF()
			{
			    if (window.navigator.userAgent.indexOf("Firefox")>=1)
			    {
			        //Firefox
			        return true;
			    }else{
			        //如果浏览器为其他
			        return false;
			    }
			}
			
			 
			function changePic(ipt,num) {
				var img = document.getElementById("pic" + num);
				img.style.display = "none";
				var div = document.getElementById("picdiv" + num);
				
				document.getElementById('shitipic' + num).value="1";
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
		
		  	function onchangeFirstSort(pId){ 
				
				$.ajax({  
					url : "<%=contextPath%>/cou/coursesort!getChildSortById.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback  
					});
				
				$.ajax({  
					url : "<%=contextPath%>/cou/course!getCourseBySortId.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackcourse  
					});
				
			} 
			
			function onchangecallbackcourse(result){ //处理返回的课程JSON  
				
				document.getElementById('courseId').options.length = 0;  //清空原有的option
				var str="";  
				
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				
				$("#courseId").html(str); 
	   		}   
				   		
			function onchangecallback(result){   	
				document.getElementById('sSortId').options.length = 0;  //清空原有的option 
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				$("#tSortId").html("<option value=-1>请选择</option>");
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#sSortId").html(str);  
			 }  
			
			function onchangeSecond(pId){
				
					$.ajax({  
					url : "<%=contextPath%>/cou/coursesort!getChildSortById.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback2  
					});  
					
					$.ajax({  
					url : "<%=contextPath%>/cou/course!getCourseBySortId.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackcourse  
					});
			} 
			
			
			function onchangeThird(pId){
				$.ajax({  
					url : "<%=contextPath%>/cou/course!getCourseBySortId.action",  
					data : {sortId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackcourse  
					});
				
			} 
					
			function onchangecallback2(result){   		
				document.getElementById('tSortId').options.length = 0;  //清空原有的option 
				var str="";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].id+"'>"+result.entity[i].name+"</option>"  
				}  
				$("#tSortId").html(str);  
			 } 
			
			function onSelectCourse(courseid){ 
				document.getElementById('exam.type').value = 1;
				document.getElementById('exam.kOrCouId').value = courseid;
				$.ajax({  
					url : "<%=contextPath%>/cou/kpoint!getAllJSONKpoints.action",  
					data : {"kpoint.courseId" : courseid},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallbackpoint  
					});
				
			}
			
			function onchangecallbackpoint(result){
				
				var myList = result.entity;
				if(myList.length<1){
					$("#kpointList").html("");
					return;
				}
				$("#kpointList").show();
				addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
				addkpoint.add(-2,-1,'栏目列表 <a href="javascript:closeColumn();">关闭</a>');
				for(var i=0;i<myList.length;i++){  
					addkpoint.add(myList[i].id ,myList[i].PId ,myList[i].name,'javascript:changeColumnValue1('+myList[i].id+',' + "'"+myList[i].name+ "'"+')');
				}
				$("#kpointList").html(addkpoint.toString());
			}
			
			function changeColumnValue1(id,name){
				document.getElementById('exam.type').value = 2;
				var f = document.getElementById('exam.type').value;
				document.getElementById('exam.kOrCouId').value = id;
				
				obj="kpointList";
				document.getElementById("pName").value = name;
				document.getElementById(obj).style.display="none";
			}
			function showPkpoint(){
				obj="kpointList";
				document.getElementById(obj).style.display="block";
			}
			function closeColumn(){
				obj="kpointList";
				document.getElementById(obj).style.display="none";
			}
			
		</script>
	</head>
	<body>

	<form action="<%=contextPath %>/exam/exampaper!updateExam.action" name="exampaperupdateform" id="exampaperupdateform" method="post" enctype="multipart/form-data" >
	<s:hidden name="exam.epId"></s:hidden>
	<s:hidden name="exam.type" id="exam.type"></s:hidden>
	<s:hidden name="exam.kOrCouId" id="exam.kOrCouId"></s:hidden>
	<s:hidden name="exam.zkId" id="exam.zkid"></s:hidden>
	<input type="hidden" name="qstIds" id="qstIds"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">试卷修改</font>
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="tblList">
				<tr>
						<td colspan="3">
							<font color="#678197"><b>填写试卷信息</b>
							</font>
						</td>
					</tr>
					<tr>
						<td>
							试卷名称
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input  type="text" name="exam.epName" id="exam.epName" value="<s:property value="exam.epName"/>" class="{required:true,minlength:4,maxlength:50}" />
						</td>
					</tr>
					<tr>
						<td>
							考试描述
							<font color="#ff0000">*</font>
							
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="" cols="" name="exam.epInfo"
								class="{required:true,minlength:4,maxlength:1000}" style="height:80px;width:99%;"><s:property value="exam.epInfo"/></textarea>
						</td>
					</tr>
					<tr  id="nj">
						<td>
							所属专业
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<s:select name="exam.subjectId" id="exam.subjectId" list="subjectList" listKey="subjectId" listValue="subjectName" headerKey="-1"
								headerValue="请选择" theme="simple">
							</s:select>
						</td>
					</tr>
					<tr>
						<td>
							试卷考试时间
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input class="{required:true,digits:true,min:0}" type="text" name="exam.replyTime" id="exam.replyTime" value="<s:property value='exam.replyTime'/>"/>分钟
						</td>
					</tr>
					<tr>
						<td>
							试卷难度系数
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<s:select name="exam.coeffcient" id="exam.coeffcient" list="examlevel" listKey="key" listValue="value" headerKey="-1"
								headerValue="请选择" theme="simple">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td>
							选择课程分类：
						</td>
						<td class="lists_tleft" colspan="2">
							<s:select name="fSortId" id="fSortId" list="courseSortList"
								listKey="coursesortId" listValue="coursesortName" 
								theme="simple" onchange="onchangeFirstSort(this.value);">
							</s:select>
							
							<s:select name="sSortId" id="sSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onchangeSecond(this.value);">
							</s:select>
							
							<s:select name="tSortId" id="tSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onchangeThird(this.value);">
							</s:select>
						</td>
					</tr>
					<tr>
						<td>
							选择课程：
						</td>
						<td class="lists_tleft" colspan="2">
							<s:select name="exam.courseId" id="courseId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td>
							选择课程节点：
						</td>
						<td class="lists_tleft" colspan="2">
					<input id="pName" type="text" name="pName" onclick="showPkpoint();" readonly="readonly" />
	    			<div id="kpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
				    </div>
						</td>
					</tr>
					
					
					<tr>
						<td>
							测试类型：						</td>
						<td class="lists_tleft" colspan="2">
						  <select name="exam.eptype" id="select" onchange="ceshi(this.value)"> 
						    <option selected="selected" value="-1">请选择测试类型</option>
						    <option value="1">真题测试</option>
						    <option value="2">仿真自测</option>
						    <option value="3">单元练习</option>
						    <option value="4">专题练习</option>
					      </select></td>
					</tr>
					
					
						<tr>
						<td>
							<div id="ceshiwordname">测试类型关键字：<font color="#ff0000">*</font></div>						</td>
						<td class="lists_tleft" colspan="2">
						
						  <select name="exam.eptypekeyword" id="eptypekeyword">
						    <option selected="selected" value="-1">请选测试类型关键字</option>
						    
					      </select> </td>
					</tr>
					
					<tr>
						<td>
							<div id="ceshiwordname"><font color="#ff0000">下面知识库是可选的：</font></div>						</td>
						<td class="lists_tleft" colspan="2">
						
						 </td>
					</tr>
					
					<tr>
						<td>
							<div id="ceshiwordname">知识库里所属专业</div>						</td>
						<td class="lists_tleft" colspan="2">
						<s:select name="exam.zzhuanyeId" id="exam.zzhuyeId" list="professionalList" listKey="pId" listValue="pName" headerKey="-1"
								headerValue="请选择所属专业" theme="simple"  onchange="onchangeProfessional(this.value)">							</s:select>	
						 </td>
					</tr>
					
					<tr>
						<td>
							<div id="ceshiwordname">知识库所属科目：</div>						</td>
						<td class="lists_tleft" colspan="2">
						<s:select name="exam.cid" id="spId"
								list="#{}" listKey="cId" listValue="cName"
								headerValue="请选择" headerKey="-1" onchange="onSelectCourse1(this.value)">	</s:select>	
						 </td>
					</tr>
					
					<tr>
						<td>
							知识库知识点：						</td>
						<td class="lists_tleft" colspan="2">
					<input id="zpName" type="text" name="zpName" onclick="showPkpoint1();" readonly="readonly" />
	    			<div id="zpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;"></div>						</td>
					</tr>
					
					
					<tr colspan="2">
						<td>
							试卷总分数
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input type="text" class="{required:true,min:0}" name="exam.epTotelScore" id="exam.epTotelScore" value="<s:property value='exam.epTotelScore'/>"/>
						</td>
					</tr>
					<tr>
						<td>
							考试评语（PCT正确率）
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">：
						</td>
					</tr>
					<s:iterator value="exam.reviewsList" id="review" status="reviewindex">
					<tr>
						<td>
							<s:if test="#review.evaType == 1">
								基础题,PCT小于50%
							</s:if>
							<s:if test="#review.evaType == 2">
								基础题,PCT小于100%
							</s:if>
							<s:if test="#review.evaType == 3">
								基础题,PCT等于100%
							</s:if>
							<s:if test="#review.evaType == 4">
								中档题,PCT小于50%
							</s:if>
							<s:if test="#review.evaType == 5">
								中档题,PCT小于100%
							</s:if>
							<s:if test="#review.evaType == 6">
								中档题,PCT等于100%
							</s:if>
							<s:if test="#review.evaType == 7">
								难题,PCT小于50%
							</s:if>
							<s:if test="#review.evaType == 8">
								难题,PCT小于100%
							</s:if>
							<s:if test="#review.evaType == 9">
								难题,PCT等于100%
							</s:if>
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea name="zongping<s:property value="#review.rvId"/>" cols="50" rows="4"><s:property value="#review.rvInfo"/></textarea>
						</td>
					</tr>
					</s:iterator>
					
					<s:iterator value="qstPaper" id="qst" status="status">
										
					<s:if test="#qst.qstType==0"><!-- 客观题 -->
					
					<tr>
						<td>
							试题序号：<input type="hidden" name="qst" value="<s:property value="#qst.qstId"/>"/><s:property value="#status.index + 1"/>
							
						</td>
						<td class="lists_tleft" colspan="2">
							试题类型：单选题
						</td>
					</tr>
					<tr>
						<td>
							试题分数
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input name='qstscore<s:property value="#qst.qstId"/>' value='<s:property value="#qst.score"/>' class="{required:true,min:0}"/>
						</td>
					</tr>
					<tr>
						<td>
							试题试题难度:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<select name='qstlevel<s:property value="#qst.qstId"/>'> 
								<s:if test="#qst.level == -1">
									<option value='-1' selected>请选择</option>
								</s:if>
								<s:else>
									<option value='-1'>请选择</option>
								</s:else>
								
								<s:if test="#qst.level == 1">
									<option value='1' selected>简单</option>
								</s:if>
								<s:else>
									<option value='1'>简单</option>
								</s:else>
								
								<s:if test="#qst.level == 2">
									<option value='2' selected>普通</option>
								</s:if>
								<s:else>
									<option value='2' >普通</option>
								</s:else>
								
								<s:if test="#qst.level == 3">
									<option value='3' selected>困难</option>
								</s:if>
								<s:else>
									<option value='3'>困难</option>
								</s:else>
								
							</select>
						</td>
					</tr>
					
					<tr>
						<td>
							试题内容:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="qstcontent<s:property value="#qst.qstId"/>" name="qstcontent<s:property value="#qst.qstId"/>"><s:property value="#qst.qstContent"/></textarea>	
						
						</td>
					</tr>
					<tr>
						<td>
							上传图片:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input type="file" name="shitiPic" onchange="changePic(this,<s:property value="#qst.qstId"/>);"/><input type=hidden id="shitipic<s:property value="#qst.qstId"/>" name="shitipic<s:property value="#qst.qstId"/>"/>
							<div class="uploadPic" id="picdiv<s:property value="#qst.qstId"/>"></div>
								<img src='<%=contextPath %><s:property value="#qst.qstPic.picPath"/>' id="pic<s:property value="#qst.qstId"/>"/>
							
						</td>
					</tr>
					<tr>
						<td>
							错误评语:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="pingyu<s:property value="#qst.qstId"/>" name="pingyu<s:property value="#qst.qstId"/>"><s:property value="#qst.wrongJude"/></textarea>	
						
						</td>
					</tr>
					<s:iterator value="#qst.options" id="option">
					<tr>
						<td>
							选项<s:property value="#option.optOrder"/>:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70"  id="optioncontent<s:property value="#option.optId"/>" name='optioncontent<s:property value="#option.optId"/>'><s:property value="#option.optContent"/></textarea>
						
						</td>
					</tr>
					
					</s:iterator>
					<tr>
						<td>
							正确选项:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<s:if test='#qst.isAsr == "A"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if test='#qst.isAsr == "B"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='B' />B
							</s:else>
							
							<s:if test='#qst.isAsr == "C"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='C' checked/>C
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='C' />C
							</s:else>
							
							<s:if test='#qst.isAsr == "D"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='D' checked/>D
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='D' />D
							</s:else>
							
							<s:if test='#qst.isAsr == "E"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='E' checked/>E
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='E' />E
							</s:else>
							
							<s:if test='#qst.isAsr == "F"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='F' checked/>F
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='F' />F
							</s:else>
							
							<s:if test='#qst.isAsr == "G"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='G' checked/>G
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='G' />G
							</s:else>
			
						</td>
					</tr>
					</s:if>
					
					
					
					<s:if test="#qst.qstType==2" ><!-- 多选题 -->
					
					<tr>
						<td>
							试题序号：<input type="hidden" name="qst" value="<s:property value="#qst.qstId"/>"/><s:property value="#status.index + 1"/>
							
						</td>
						<td class="lists_tleft" colspan="2">
							试题类型：多选题
						</td>
					</tr>
					<tr>
						<td>
							试题分数
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input name='qstscore<s:property value="#qst.qstId"/>' value='<s:property value="#qst.score"/>' class="{required:true,min:0}"/>
						</td>
					</tr>
					<tr>
						<td>
							试题试题难度:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<select name='qstlevel<s:property value="#qst.qstId"/>'> 
								<s:if test="#qst.level == -1">
									<option value='-1' selected>请选择</option>
								</s:if>
								<s:else>
									<option value='-1'>请选择</option>
								</s:else>
								
								<s:if test="#qst.level == 1">
									<option value='1' selected>简单</option>
								</s:if>
								<s:else>
									<option value='1'>简单</option>
								</s:else>
								
								<s:if test="#qst.level == 2">
									<option value='2' selected>普通</option>
								</s:if>
								<s:else>
									<option value='2' >普通</option>
								</s:else>
								
								<s:if test="#qst.level == 3">
									<option value='3' selected>困难</option>
								</s:if>
								<s:else>
									<option value='3'>困难</option>
								</s:else>
								
							</select>
						</td>
					</tr>
					
					<tr>
						<td>
							试题内容:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70"  id="qstcontent<s:property value="#qst.qstId"/>" name="qstcontent<s:property value="#qst.qstId"/>"><s:property value="#qst.qstContent"/></textarea>	
						
						</td>
					</tr>
					<tr>
						<td>
							上传图片:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input type="file" name="shitiPic" onchange="changePic(this,<s:property value="#qst.qstId"/>);"/><input type=hidden id="shitipic<s:property value="#qst.qstId"/>" name="shitipic<s:property value="#qst.qstId"/>"/>
							<div class="uploadPic" id="picdiv<s:property value="#qst.qstId"/>"></div>
								<img src='<%=contextPath %><s:property value="#qst.qstPic.picPath"/>' id="pic<s:property value="#qst.qstId"/>"/>
							
						</td>
					</tr>
					<tr>
						<td>
							错误评语:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70"  id="pingyu<s:property value="#qst.qstId"/>" name="pingyu<s:property value="#qst.qstId"/>"><s:property value="#qst.wrongJude"/></textarea>	
						
						</td>
					</tr>
					<s:iterator value="#qst.options" id="option">
					<tr>
						<td>
							选项<s:property value="#option.optOrder"/>:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="optioncontent<s:property value="#option.optId"/>" name='optioncontent<s:property value="#option.optId"/>'><s:property value="#option.optContent"/></textarea>
							
						</td>
					</tr>
					
					</s:iterator>
					<tr>
						<td>
							正确选项:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<s:if  test="-1 != #qst.isAsr.indexOf('A')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if  test="-1 != #qst.isAsr.indexOf('B')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='B' />B
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('C')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='C' checked/>C
							</s:if>
							<s:else>

								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='C' />C
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('D')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='D' checked/>D
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='D' />D
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('E')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='E' checked/>E
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='E' />E
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('F')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='F' checked/>F
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='F' />F
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('G')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='G' checked/>G
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='G' />G
							</s:else>
			
						</td>
					</tr>
					</s:if>
					
					
						<s:if test="#qst.qstType==4" ><!-- 材料分析题 -->
					
					<tr>
						<td>
							试题序号：<input type="hidden" name="qst" value="<s:property value="#qst.qstId"/>"/><s:property value="#status.index + 1"/>
							
						</td>
						<td class="lists_tleft" colspan="2">
							试题类型：材料分析题
						</td>
					</tr>
					<tr>
						<td>
							试题分数
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input name='qstscore<s:property value="#qst.qstId"/>' value='<s:property value="#qst.score"/>' class="{required:true,min:0}"/>
						</td>
					</tr>
					<tr>
						<td>
							试题试题难度:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<select name='qstlevel<s:property value="#qst.qstId"/>'> 
								<s:if test="#qst.level == -1">
									<option value='-1' selected>请选择</option>
								</s:if>
								<s:else>
									<option value='-1'>请选择</option>
								</s:else>
								
								<s:if test="#qst.level == 1">
									<option value='1' selected>简单</option>
								</s:if>
								<s:else>
									<option value='1'>简单</option>
								</s:else>
								
								<s:if test="#qst.level == 2">
									<option value='2' selected>普通</option>
								</s:if>
								<s:else>
									<option value='2' >普通</option>
								</s:else>
								
								<s:if test="#qst.level == 3">
									<option value='3' selected>困难</option>
								</s:if>
								<s:else>
									<option value='3'>困难</option>
								</s:else>
								
							</select>
						</td>
					</tr>
					
					<tr>
						<td>
							试题内容:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70"  id="qstcontent<s:property value="#qst.qstId"/>" name="qstcontent<s:property value="#qst.qstId"/>"><s:property value="#qst.qstContent"/></textarea>	
						
						</td>
					</tr>
					<tr>
						<td>
							上传图片:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input type="file" name="shitiPic" onchange="changePic(this,<s:property value="#qst.qstId"/>);"/><input type=hidden id="shitipic<s:property value="#qst.qstId"/>" name="shitipic<s:property value="#qst.qstId"/>"/>
							<div class="uploadPic" id="picdiv<s:property value="#qst.qstId"/>"></div>
								<img src='<%=contextPath %><s:property value="#qst.qstPic.picPath"/>' id="pic<s:property value="#qst.qstId"/>"/>
							
						</td>
					</tr>
					<tr>
						<td>
							错误评语:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70"  id="pingyu<s:property value="#qst.qstId"/>" name="pingyu<s:property value="#qst.qstId"/>"><s:property value="#qst.wrongJude"/></textarea>	
						
						</td>
					</tr>
					<s:iterator value="#qst.options" id="option">
					<tr>
						<td>
							选项<s:property value="#option.optOrder"/>:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="optioncontent<s:property value="#option.optId"/>" name='optioncontent<s:property value="#option.optId"/>'><s:property value="#option.optContent"/></textarea>
							
						</td>
					</tr>
					
					</s:iterator>
					<tr>
						<td>
							正确选项:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<s:if  test="-1 != #qst.isAsr.indexOf('A')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if  test="-1 != #qst.isAsr.indexOf('B')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='B' />B
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('C')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='C' checked/>C
							</s:if>
							<s:else>

								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='C' />C
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('D')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='D' checked/>D
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='D' />D
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('E')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='E' checked/>E
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='E' />E
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('F')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='F' checked/>F
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='F' />F
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('G')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='G' checked/>G
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='G' />G
							</s:else>
			
						</td>
					</tr>
					</s:if>
					
						<s:if test="#qst.qstType==5" ><!-- 图表题 -->
					
					<tr>
						<td>
							试题序号：<input type="hidden" name="qst" value="<s:property value="#qst.qstId"/>"/><s:property value="#status.index + 1"/>
							
						</td>
						<td class="lists_tleft" colspan="2">
							试题类型：图表题
						</td>
					</tr>
					<tr>
						<td>
							试题分数
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input name='qstscore<s:property value="#qst.qstId"/>' value='<s:property value="#qst.score"/>' class="{required:true,min:0}"/>
						</td>
					</tr>
					<tr>
						<td>
							试题试题难度:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<select name='qstlevel<s:property value="#qst.qstId"/>'> 
								<s:if test="#qst.level == -1">
									<option value='-1' selected>请选择</option>
								</s:if>
								<s:else>
									<option value='-1'>请选择</option>
								</s:else>
								
								<s:if test="#qst.level == 1">
									<option value='1' selected>简单</option>
								</s:if>
								<s:else>
									<option value='1'>简单</option>
								</s:else>
								
								<s:if test="#qst.level == 2">
									<option value='2' selected>普通</option>
								</s:if>
								<s:else>
									<option value='2' >普通</option>
								</s:else>
								
								<s:if test="#qst.level == 3">
									<option value='3' selected>困难</option>
								</s:if>
								<s:else>
									<option value='3'>困难</option>
								</s:else>
								
							</select>
						</td>
					</tr>
					
					<tr>
						<td>
							试题内容:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70"  id="qstcontent<s:property value="#qst.qstId"/>" name="qstcontent<s:property value="#qst.qstId"/>"><s:property value="#qst.qstContent"/></textarea>	
						
						</td>
					</tr>
					<tr>
						<td>
							上传图片:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input type="file" name="shitiPic" onchange="changePic(this,<s:property value="#qst.qstId"/>);"/><input type=hidden id="shitipic<s:property value="#qst.qstId"/>" name="shitipic<s:property value="#qst.qstId"/>"/>
							<div class="uploadPic" id="picdiv<s:property value="#qst.qstId"/>"></div>
								<img src='<%=contextPath %><s:property value="#qst.qstPic.picPath"/>' id="pic<s:property value="#qst.qstId"/>"/>
							
						</td>
					</tr>
					<tr>
						<td>
							错误评语:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70"  id="pingyu<s:property value="#qst.qstId"/>" name="pingyu<s:property value="#qst.qstId"/>"><s:property value="#qst.wrongJude"/></textarea>	
						
						</td>
					</tr>
					<s:iterator value="#qst.options" id="option">
					<tr>
						<td>
							选项<s:property value="#option.optOrder"/>:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="optioncontent<s:property value="#option.optId"/>" name='optioncontent<s:property value="#option.optId"/>'><s:property value="#option.optContent"/></textarea>
							
						</td>
					</tr>
					
					</s:iterator>
					<tr>
						<td>
							正确选项:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<s:if  test="-1 != #qst.isAsr.indexOf('A')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if  test="-1 != #qst.isAsr.indexOf('B')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='B' />B
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('C')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='C' checked/>C
							</s:if>
							<s:else>

								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='C' />C
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('D')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='D' checked/>D
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='D' />D
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('E')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='E' checked/>E
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='E' />E
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('F')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='F' checked/>F
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='F' />F
							</s:else>
							
							<s:if test="-1 != #qst.isAsr.indexOf('G')">
							<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='G' checked/>G
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="#qst.qstId"/>' value='G' />G
							</s:else>
			
						</td>
					</tr>
					</s:if>
					
					
					<s:if test="#qst.qstType==3" ><!-- 判断题 -->
					
					<tr>
						<td>
							试题序号：<input type="hidden" name="qst" value="<s:property value="#qst.qstId"/>"/><s:property value="#status.index + 1"/>
							
						</td>
						<td class="lists_tleft" colspan="2">
							试题类型：判断题
						</td>
					</tr>
					<tr>
						<td>
							试题分数
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input name='qstscore<s:property value="#qst.qstId"/>' value='<s:property value="#qst.score"/>' class="{required:true,min:0}"/>
						</td>
					</tr>
					<tr>
						<td>
							试题试题难度:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<select name='qstlevel<s:property value="#qst.qstId"/>'> 
								<s:if test="#qst.level == -1">
									<option value='-1' selected>请选择</option>
								</s:if>
								<s:else>
									<option value='-1'>请选择</option>
								</s:else>
								
								<s:if test="#qst.level == 1">
									<option value='1' selected>简单</option>
								</s:if>
								<s:else>
									<option value='1'>简单</option>
								</s:else>
								
								<s:if test="#qst.level == 2">
									<option value='2' selected>普通</option>
								</s:if>
								<s:else>
									<option value='2' >普通</option>
								</s:else>
								
								<s:if test="#qst.level == 3">
									<option value='3' selected>困难</option>
								</s:if>
								<s:else>
									<option value='3'>困难</option>
								</s:else>
								
							</select>
						</td>
					</tr>
					
					<tr>
						<td>
							试题内容:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70"  id="qstcontent<s:property value="#qst.qstId"/>" name="qstcontent<s:property value="#qst.qstId"/>"><s:property value="#qst.qstContent"/></textarea>	
							
							
						</td>
					</tr>
					<tr>
						<td>
							上传图片:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input type="file" name="shitiPic" onchange="changePic(this,<s:property value="#qst.qstId"/>);"/><input type=hidden id="shitipic<s:property value="#qst.qstId"/>" name="shitipic<s:property value="#qst.qstId"/>"/>
							<div class="uploadPic" id="picdiv<s:property value="#qst.qstId"/>"></div>
								<img src='<%=contextPath %><s:property value="#qst.qstPic.picPath"/>' id="pic<s:property value="#qst.qstId"/>"/>
							
						</td>
					</tr>
					<tr>
						<td>
							错误评语:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="pingyu<s:property value="#qst.qstId"/>" name="pingyu<s:property value="#qst.qstId"/>"><s:property value="#qst.wrongJude"/></textarea>	
						
						</td>
					</tr>
					<s:iterator value="#qst.options" id="option">
					<tr>
						<td>
							选项<s:property value="#option.optOrder"/>:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="optioncontent<s:property value="#option.optId"/>" name='optioncontent<s:property value="#option.optId"/>'><s:property value="#option.optContent"/></textarea>
							
						</td>
					</tr>
					
					</s:iterator>
					<tr>
						<td>
							正确选项:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<s:if test='#qst.isAsr == "A"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if test='#qst.isAsr == "B"'>
							<input type='radio' name='right<s:property value="#qst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="#qst.qstId"/>' value='B' />B
							</s:else>
							
			
						</td>
					</tr>
					</s:if>
					
					
					
					<s:if test="#qst.qstType==6"><!-- 主观题 -->
					<tr>
						<td>
							试题序号<input type="hidden" name="qst" value="<s:property value="#qst.qstId"/>"/><s:property value="#status.index + 1"/>
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							试题类型：主观题
						</td>
					</tr>
					<tr>
						<td>
							试题分数
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input name='qstscore<s:property value="#qst.qstId"/>' value='<s:property value="#qst.score"/>''/>
						</td>
					</tr>
					<tr>
						<td>
							试题试题难度:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<select name='qstlevel<s:property value="#qst.qstId"/>'> 
								<s:if test="#qst.level == -1">
									<option value='-1' selected>请选择</option>
								</s:if>
								<s:else>
									<option value='-1'>请选择</option>
								</s:else>
								
								<s:if test="#qst.level == 1">
									<option value='1' selected>简单</option>
								</s:if>
								<s:else>
									<option value='1'>简单</option>
								</s:else>
								
								<s:if test="#qst.level == 2">
									<option value='2' selected>普通</option>
								</s:if>
								<s:else>
									<option value='2' >普通</option>
								</s:else>
								
								<s:if test="#qst.level == 3">
									<option value='3' selected>困难</option>
								</s:if>
								<s:else>
									<option value='3'>困难</option>
								</s:else>
								
							</select>
						</td>
					</tr>
					<tr>
						<td>
							试题内容:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="qstcontent<s:property value="#qst.qstId"/>" name="qstcontent<s:property value="#qst.qstId"/>"><s:property value="#qst.qstContent"/></textarea>	
							
						</td>
					</tr>
					<tr>
						<td>
							上传图片:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<input type="file" name="shitiPic" onchange="changePic(this,<s:property value="#qst.qstId"/>);"/><input type=hidden name="shitipic<s:property value="#qst.qstId"/>"/>
							
							<div class="uploadPic" id="picdiv<s:property value="#qst.qstId"/>"></div>
							<img src='<%=contextPath %><s:property value="#qst.qstPic.picPath"/>' id="pic<s:property value="#qst.qstId"/>"/>
					
						</td>
					</tr>
					<s:iterator value="#qst.options" id="option">
					<tr>
						<td>
							正确答案:
							<font color="#ff0000">*</font>
						</td>
						<td class="lists_tleft" colspan="2">
							<textarea rows="7" cols="70" id="optioncontent<s:property value="#option.optId"/>" name='optioncontent<s:property value="#option.optId"/>'><s:property value="#option.optContent"/></textarea>
							
						</td>
					</tr>
					</s:iterator>
					</s:if>
					
					</s:iterator>
				</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
				<input class="submit" type="submit" value="提交" />
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
