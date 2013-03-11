<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>试卷更新</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<script type="text/javascript" src="<%=contextPath %>/fckeditor/fckeditor.js"></script>
		
		<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
			.eptypekeyword{
				width:200px;
			}
		</style>
		<link rel="shortcut icon" href="../fckeditor.gif"
				type="image/x-icon" />
		<script type="text/javascript">
		var importURL = '..';
			$().ready(function() {
			$("#exampaperaddform").validate({   
		        rules: {   
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
			if(pId<1) return;
			else 
			$.ajax({
				url: "<%=contextPath%>/exam/exampaper!getSysNodeListByParentId.action",
				data : {subjectId : pId},
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
			if(result.jumpType!=false){
				var sysNodeList = result.entity;
				document.getElementById('spId').options.length = 0; //清空原有的option 
				$("#spId").html("<option value=-1 >请选择</option>");
				var str="";
				str+="<option value=-1 >请选择</option>";
				for(var i=0;i<sysNodeList.length;i++){  
					str+="<option value='"+sysNodeList[i].ksnId+"'>"+sysNodeList[i].nodeName+"</option>" ; 
				}  
				if(str!=""){
					$("#spId").html(str);  
				}
			}else{
				$("#spId").html("<option value=-1 >请选择</option>");  
			}
		}
		
		
		
		//** js 调用的事件 js效果开始/
		function onchangeProfessionaljs(pId,no){
			
			$.ajax({
				url: "<%=contextPath%>/kb/studyCourse!getStudyCourseListByPid.action",
				data : {pId : pId},
				type : "post",
				cache : false,
				dataType : "json",
				success:function(result)
				{
					 onchangecallbackjs(result,no);
				
				},
				error : function(){
				alert('error');
				}
				
			});
		  }
		function onchangecallbackjs(result,no){
				
				var scList = eval(result.returnMessage);
				var ids="kecheng"+no;
				document.getElementById(ids).options.length = 0; //清空原有的option 
				$("#kecheng"+no+"").html("<option value=-1>请选择</option>");
				var str="";
				for(var i=0;i<scList.length;i++){  
					str+="<option value='"+scList[i].CId+"'>"+scList[i].CName+"</option>"  
				}  
				if(str!=""){
					$("#kecheng"+no+"").html(str);  
				}
		}
		
		function onSelectCoursejs(zkid,no){ 
				
				$.ajax({  
					url : "<%=contextPath %>/kb/knowledge!getKnowledgeTreeJson.action",  
					data : {"knowledge.scId" : zkid},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					success:function (result)
					{
						onchangecallbackpointjs(result,no);
					},
					error: function(){ 
						alert('error');      
					} 
					
					});
				
			}
			
			function onchangecallbackpointjs(result,no){
			
				var myList = eval(result.returnMessage);
				var lanmu = result.jumpUrl;
				if(myList.length<1){
					$("#qpointList"+no+"").html("");
					return;
				}
				$("#qpointList"+no+"").show();
				addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
				addkpoint.add(-2,-1,lanmu+'栏目列表 <a href="javascript:closeColumnjs('+no+');">关闭</a>');
				for(var i=0;i<myList.length;i++){ 
					
					addkpoint.add(myList[i].KId ,myList[i].KPId ,myList[i].KName,'javascript:changeColumnValuejs('+myList[i].KId+',' + "'"+myList[i].KName+ "'"+','+"'"+no+"'"+')');
					
				}
				
				$("#qpointList"+no+"").html(addkpoint.toString());
			}
		
		
			function changeColumnValuejs(id,name,no){
				var qstzkids="qstzkid"+no;
				document.getElementById(qstzkids).value += id+",";
				var zid="zhishidian"+no;
				obj="qpointList"+no;
				document.getElementById(zid).value += name;
				document.getElementById(obj).style.display="none";
			}
			
			
			function createHiddenzhishidian(colNum){//题库知识点id
				var td = null;
			    td = document.createElement('td');
				if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    td.innerHTML="<input type='hidden' id='qstzkid"+colNum+"'  name='qstzkid"+colNum+"'/>";
			    td.className = "lists_tleft";
				return td;
			}
			
			
			function showPkpointjs(no){
				obj="qpointList"+no;
				document.getElementById(obj).style.display="block";
			}
			
			function closeColumnjs(no){
				obj="qpointList"+no;
				document.getElementById(obj).style.display="none";
			}
		
		/**js效果结束**/
		
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
			
			function IsOther()
			{
			    if (window.navigator.userAgent.indexOf("MSIE")>=1)
			    {
			        //IE浏览器
			    }else{
			        if (window.navigator.userAgent.indexOf("Firefox")>=1)
			        {
			            //Firefox
			        }else{
			            //如果浏览器为其他
			            return true;
			        }
			    }
			    return false;
			}
			
			function resetRow()
			{
			    document.location.reload();
			}

		function delcourse(){
		 	document.getElementById('pName').value="";
			 } 
			
		function doSubmit(){//id进行拼接，然后发给action
		
		  		var epName=document.getElementById('exam.epName').value;
				if(epName==''){
				alert("试卷名不能为空");
				return false;
				
				}
			var subid=document.getElementById('subjectId').value;
			if(subid=='0'){
				alert("请选择所属项目");
				return false;
				
			}
			
			var eptype=document.getElementById('eptype').value;
			if(eptype<0){
				alert("请选择试卷类型");
				return false;
				
			}
			var year =$('#year').val();
			if(year<0){
				alert('请选择年份');
				return;
			}
			
			var spId=$('#spId').val();
			if(spId<0){
				alert('请选择知识库所属科目');
				return;
			}
			var jifen=document.getElementById('jifen').value;
			var jifenn= document.getElementById('jifen');
			if(jifen==''){
				alert("积分不能为空");
				return false;
				
			}else  if(0>jifen || jifen>50){
					alert('所需积分只能在0~50之间，请重新输入！');
					jifenn.value="";
					jifenn.focus();
					return false;
				}else if(/^(\-?)(\d+)$/.test(jifen)) {
				jifen=parseInt(jifen);
				if(jifen<0){
					alert("积分不能为负数");
					return false;
				}	
			
			} else{
					alert("输入的积分不是数字");
					return false;		
			}
		
			 	
			var replyTime=document.getElementById('exam.replyTime').value;
			var dataTimee= document.getElementById('exam.replyTime');
			if(replyTime==''){
				alert("答题时间不能为空");
				return false;
				
			}else if(0>replyTime || replyTime>300){
					alert('答题时间只能在0~300分之间，请重新输入！');
					dataTimee.value="";
					dataTimee.focus();
					return;
				}else if(/^(\-?)(\d+)$/.test(replyTime)) {
			replyTime=parseInt(replyTime);
				if(replyTime<=0){
					alert("答题时间要大于0");
					return false;
				}	
			
			} else
			{
				
					alert("答题时间必须是数字");
					return false;
			}
			
			
			var startTime=document.getElementById('startTime').value;
			if(startTime==''){
				alert("发布时间不能为空");
				return false;
				
			}
			
			var endTime=document.getElementById('endTime').value;
			if(endTime!=''){
				var date1=startTime;
				var date2=endTime;
				
				var year1 = date1.substring(0,date1.indexOf("-")); 
				var year2 = date2.substring(0,date2.indexOf("-")); 
				var month1 = date1.substring(date1.indexOf("-")+1,date1.lastIndexOf("-")); 
				var month2 = date2.substring(date2.indexOf("-")+1,date2.lastIndexOf("-")); 
				var day1 = date1.substring(date1.lastIndexOf("-")+1); 
				var day2 = date2.substring(date2.lastIndexOf("-")+1);
	
				if(year1 > year2){ 
				
					alert("发布时间应当小于截止时间");
	            	return false; 
				}else if(year1 == year2){ 
						if( month1 > month2 ){ 
						alert("发布时间应当小于截止时间");
						return false; 
						}else if( month1== month2 ){ 
							if( day1 > day2 ){ 
							alert("发布时间应当小于截止时间");
							return false; 
							} 
						}
				} 
				
			}
			
			var examqsttype=document.getElementsByName("exam.examqsttype");
			var c=0;
			for(var i=0;i<examqsttype.length;i++){
				if(examqsttype[i].checked==true){
				c+=1;
				}
			}
			if(c==0){
			alert("试卷题型不能为空");
			return false;
			}
			
			
				
			document.forms[0].submit();
		 
		 }
		
		  function deldata(){
		 	document.getElementById('endTime').value="";
			 }
		
		//更换图片	 
		function changePic(ipt) {
			var img = document.getElementById("picImg" + ipt.id.substring(4, ipt.id.length));
			var div = document.getElementById("picDiv" + ipt.id.substring(4, ipt.id.length));
			if(ipt.value != '') {
				if(IsIE()) {
					ipt.select();    
	        		var imgSrc = document.selection.createRange().text; 
					div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
					div.style.height = "200px";
					div.style.width = "700px";
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
					var subjectId=$("#subjectId").val();
					$.ajax({  
					url : "<%=contextPath%>/cou/coursesort!getChildSortById.action",  
					data : {sortId : pId,
							subjectId:subjectId},  // 参数  
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
			
			/**/
			
			
			/**/
			
			function onSelectCourse1(zkid){ 
		//		if(zkid<1)return;
		//		document.getElementById('exam.type').value = 1;
				document.getElementById('exam.zkid').value = zkid;
				$.ajax({  
					url : "<%=contextPath %>/exam/exampaper!getSysTreeByKsnId.action",  
					data : {"ksnId" : zkid},  // 参数  
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
				if(result.jumpType!=false){
					var myList = result.entity;
					if(myList.length<1){
						$("#zpointList").html("");
						return;
					}
					addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
					for(var i=0;i<myList.length;i++){ 
						if(myList[i].parentId==-1){
							addkpoint.add(myList[i].ksnId,-1,'项目<a href="javascript:closeColumn1();">关闭</a>');
						}else{
							addkpoint.add(myList[i].ksnId,myList[i].parentId,myList[i].nodeName,'javascript:changeColumnValue('+myList[i].ksnId+',' + "'"+myList[i].nodeName+ "')");
						}
					}
					$("#zpointList").html(addkpoint.toString());
					$("#zpointList").show();
				}
			}
			
			
			
			
			
			
			function checkSubmit(){
				
				return true;
			}
			
			function changeColumnValue(id,name){
				document.getElementById('exam.type').value = 2;
				var f = document.getElementById('exam.type').value;
				document.getElementById('exam.zkid').value = id;
				
				obj="zpointList";
				document.getElementById("zpName").value = name;
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
			
			function showPkpoint1(){
				obj="zpointList";
				document.getElementById(obj).style.display="block";
			}
			
		
			
			function closeColumn1(){
				obj="zpointList";
				document.getElementById(obj).style.display="none";
			}
			
			function removeKpoint(){
			 $('#zpName').val('');
			 $('#exam.zkid').val('');
		 }
    
		</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath %>/exam/exampaper!updateExam.action" method="post" enctype="multipart/form-data" name="exampaperaddform" id="exampaperaddform" onsubmit="return doSubmit()">
	<s:hidden name="exam.type" id="exam.type"></s:hidden>
	<input type="hidden" name="qstCount"   id="qstCount" value="<s:property value="exam.qstmiddlecount"/>"/>
	
	<input type="hidden" name="exam.zkId" id="exam.zkid" value="<s:property value="exam.zkId"/>"/>
	<input type="hidden" name="exam.epId"  value="<s:property  value="exam.epId"/>"/>
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
				<TBODY id="tagTb">
					<tr>
						<td colspan="3">
							<font color="#678197"><b>填写试卷信息</b>							</font>						</td>
					</tr>
					<tr>
						<td>
							<font color="#ff0000">*</font>试卷名称：													</td>
						<td class="lists_tleft" colspan="2">
						
							<input name="exam.epName" type="text" class={required:true,maxlength:100,minlength:4} id="exam.epName" maxlength="100" style="width:400px;" value="<s:property value="exam.epName"/>"/>		
							卷名限定100个字				</td>
					</tr>
					
						
					
					<tr  id="nj">
						<td>
							<font color="#ff0000">*</font>所属项目：</td>
						<td class="lists_tleft" colspan="2">
							<s:select name="exam.subjectId" id="subjectId" list="subjectList" listKey="subjectId" listValue="subjectName" headerKey="0"
								headerValue="请选择所属专业" theme="simple" onchange="onchangeProfessional(this.value)">							</s:select>						</td>
					</tr>
					
					
					<tr>
						<td>
							<font color="#ff0000">*</font>	试卷类型：</td>
						<td class="lists_tleft" colspan="2">
						  <select name="exam.eptype" id="eptype" onchange="ceshi(this.value)"> 
						    <option value="-1">请选择测试类型</option>
						 <s:if test="exam.eptype==1"><option value="1" selected="selected">真题测试</option></s:if> <s:else><option value="1">真题测试</option></s:else>
						 <s:if test="exam.eptype==2"><option value="2" selected="selected">仿真自测</option></s:if> <s:else><option value="2">仿真自测</option></s:else>
						<s:if test="exam.eptype==3"><option value="3" selected="selected">单元练习</option></s:if> <s:else><option value="3">单元练习</option></s:else>
						<s:if test="exam.eptype==4"><option value="4" selected="selected">专题练习</option></s:if> <s:else><option value="4">专题练习</option></s:else>
					      </select></td>
					</tr>
					
					
					<tr>
						<td>
							<div ><font color="#ff0000">*</font>年份：</div>						</td>
						<td class="lists_tleft" colspan="2">
						  <select name="exam.year" id="year">
						    <option value="-1">请选择年份</option>
						    <option value='2011' <s:property value="exam.year==2011?'selected=\"selected\"':''"/> >2011</option>
						    <option value='2012' <s:property value="exam.year==2012?'selected=\"selected\"':''"/> >2012</option>
						    <option value='2013' <s:property value="exam.year==2013?'selected=\"selected\"':''"/> >2013</option>
					      </select> </td>
					</tr>
					
					<tr>
						<td>
							<div id="ceshiwordname"><font color="#ff0000">*</font>知识库所属科目：</div>						</td>
						<td class="lists_tleft" colspan="2"> 
							<select name="exam.cid" id="spId" onchange="onSelectCourse1(this.value)">
								<option value="-1">请选择</option>
								<s:if test="sysNodeList!=null&&sysNodeList.size()>0">
									<s:iterator value="sysNodeList">
										<option value="<s:property value="ksnId"/>" <s:property value="ksnId==exam.cid?'selected=\"selected\"':''"/> ><s:property value="nodeName"/></option>
									</s:iterator>
								</s:if>
							</select>	
						</td>
					</tr>
					
					<tr>
						<td>
							知识库知识点：						
						</td>
						<td class="lists_tleft" colspan="2">
							<input id="zpName" type="text" value="${sysNode.nodeName }" name="zpName" onclick="showPkpoint1();" readonly="readonly" />
			    			<div id="zpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">		
		    					<s:if test="knoTree.size>0">
			    					<script type="text/javascript">
											d = new dTree('d','<%=contextPath%>/back/images/dtree');
											var flag = true;
											<s:iterator value="knoTree">
											if(${parentId}==-1){
													d.add('${ksnId}',-1,'项目<a href="javascript:closeColumn1();">关闭</a>');
												}else{
													d.add('${ksnId}','${parentId}','${nodeName}','javascript:changeColumnValue(${ksnId},\'${nodeName}\')');
												}
											</s:iterator>
											document.write(d);
									</script>
								</s:if>
	    					</div>		
	    					<input type="button" onclick="removeKpoint()" value="清空"/>				
	    				</td>
					</tr>
					
					<tr>
						<td><font color="#ff0000">*</font>所需积分：</td>
						<td class="lists_tleft" colspan="2" ><input  type="text" name="exam.jifen" value="<s:property value="exam.jifen"/>" id="jifen"/></td>
					</tr>
					
					<tr>
						<td><font color="#ff0000">*</font>试卷难度：</td>
						<td class="lists_tleft" colspan="2"> <select name="exam.coeffcient" >
						    <option value="1" selected="selected">-请选择试卷难度-</option>
						   <s:if test="exam.coeffcient==1" > <option value="1" selected="selected">一星</option></s:if><s:else> <option value="1" >一星</option></s:else>
						    <s:if test="exam.coeffcient==2" > <option value="2" selected="selected">二星</option></s:if><s:else> <option value="2">二星</option></s:else>
							 <s:if test="exam.coeffcient==3" ><option value="3" selected="selected">三星</option></s:if><s:else><option value="3">三星</option></s:else>
							 <s:if test="exam.coeffcient==4" ><option value="4" selected="selected">四星</option></s:if><s:else><option value="4">四星</option></s:else>
							 <s:if test="exam.coeffcient==5" ><option value="5" selected="selected">五星</option></s:if><s:else><option value="5">五星</option></s:else>
				        </select></td>
					</tr>
					
					<tr>
						<td>试题总分</td>
						<td class="lists_tleft" colspan="2">
							
							<input name="exam.epTotleScore"  type="text"  id="exam.epTotelScore" value="<s:property value='exam.epTotelScore'/>" maxlength="30" style="width:150px;" disabled="disabled"/></td>
					</tr>
					
					<tr colspan="2">
						<td>
							<font color="#ff0000">*</font>试卷状态：</td>
						<td class="lists_tleft" colspan="2">
						  <select name="exam.level" >
						    <option value="1" >-状态-</option>
						   <s:if test="exam.level==1"> <option value="1" selected="selected">新稿</option></s:if><s:else><option value="1" >新稿</option></s:else>
						    <s:if test="exam.level==2"> <option value="2" selected="selected">发布</option></s:if><s:else><option value="2" >发布</option></s:else>
				        </select></td>
					</tr>
					
						
					<tr>
						<td><font color="#ff0000">*</font>答题时间：</td>
						<td class="lists_tleft" colspan="2">
							<input type="text" name="exam.replyTime" id="exam.replyTime"   value="<s:property value="exam.replyTime"/>"/>						</td>
					</tr>
					<tr>
						<td><font color="#ff0000">*</font>发布时间：</td>
						<td class="lists_tleft" colspan="2"><input type="text" name="exam.createTime"   id="startTime"
								onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
											 width="10" value="<s:date name="exam.createTime"  format="yyyy-MM-dd HH:mm:ss"/>"/>				</td>
					</tr>
					<tr>
						<td>					截止时间：</td>
						<td class="lists_tleft" colspan="2"><input type="text" name="exam.endTime"  readonly id="endTime"
								onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})" 
											 width="10" value="<s:date name="exam.endTime"  format="yyyy-MM-dd HH:mm:ss"/>"/> <input type="button"   value="清空" onclick="deldata()"/>
											 </td>
											
						
					</tr>
					<tr>
						<td>试卷题型</td>
						<td class="lists_tleft" colspan="2">	
						   
							
							<s:if test="-1 != exam.examqsttype.indexOf('1')">
							 <input type="checkbox"  class="button" value="1" name="exam.examqsttype" checked/>单选题
							</s:if>
							<s:else>
								 <input type="checkbox"  class="button" value="1" name="exam.examqsttype"/>单选题
							</s:else>
							
							<s:if test="-1 != exam.examqsttype.indexOf('2')">
							<input type="checkbox"  class="button" value="2"  name="exam.examqsttype" checked/>多选题
							</s:if>
							<s:else>
								<input type="checkbox"  class="button" value="2"  name="exam.examqsttype"/>多选题
							</s:else>
							
							<s:if test="-1 != exam.examqsttype.indexOf('3')">
							<input type="checkbox"  class="button" value="3"  name="exam.examqsttype" checked/>判断题
							</s:if>
							<s:else>
							<input type="checkbox"  class="button" value="3"  name="exam.examqsttype"/>判断题
							</s:else>
							
							<s:if test="-1 != exam.examqsttype.indexOf('6')">
							<input type="checkbox"  class="button" value="6" name="exam.examqsttype" checked/>添加主观题
							</s:if>
							<s:else>
							<input type="checkbox"  class="button" value="6" name="exam.examqsttype"/>添加主观题
							</s:else>
							
							<s:if test="-1 != exam.examqsttype.indexOf('4')">
						<input type="checkbox"  class="button" value="4" name="exam.examqsttype" checked/>材料分析题
							</s:if>
							<s:else>
							<input type="checkbox"  class="button" value="4" name="exam.examqsttype"/>材料分析题
							</s:else>
							
							<s:if test="-1 != exam.examqsttype.indexOf('5')">
							<input type="checkbox"  class="button" value="5" name="exam.examqsttype" checked/>图表题
							</s:if>
							<s:else>
							<input type="checkbox"  class="button" value="5" name="exam.examqsttype"/>图表题
							</s:else>
							
							</td>
					</tr>
					
			  </TBODY>
			  </table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr>
						<td>
						
							
						</td>
					</tr>
					<tr>
						<td>
							<input class="submit" type="button" value="提交" onclick="doSubmit();" />
							
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
	</body>
</html>
