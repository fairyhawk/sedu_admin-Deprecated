<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>试题更新</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/uploadify/uploadify.css" />	
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
	
		<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>

		<script type="text/javascript">
		var importURL = '..';

		// 初始化 Kindeditor
		function initEditor(id){
			KE.init({
			   id : id,
		       resizeMode : 1,
		       allowPreviewEmoticons : false,
		       allowUpload : true,
		       syncType : 'auto',
		       urlType : 'absolute',
		       imageUploadJson : 'http://tp.highso.cn:8080/upload!exam.action',
		       allowFileManager : false,
		       items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
		       'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
		       'code', 'image', 'flash', '|']
		    });
		}
		//创建 Kindeditor
		function createEditor(id) {
	        KE.create(id);
		}

		$().ready(function() {
			var type=<s:property value="qst.qstType" />;
			qsttype(type);
			// 初始化 Kindeditor
    	    initEditor('qstContent');
    	    createEditor('qstContent');
			$("#exampaperaddform").validate({   
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
				    }
		        }   
   	 		});  
			
				var str=""; 
				var bstr="";
				var kecheng="<s:property  value="qst.coursesortName"/>";
				var banxing="<s:property  value="qst.title"/>";
					
				str+="<option value='"+<s:property  value="qst.sortId" />+"'>"+kecheng+"</option>";
				bstr+="<option value='"+<s:property  value="qst.courseId" />+"'>"+banxing+"</option>";
				
				$("#tSortId").html(str);  
				$("#courseId").html(bstr);
				
				var sysName="<s:property  value="sysdto.subjectName"/>";
				if(sysName!=""){
					$("#ksId").append("<option value='<s:property  value="sysdto.ksId" />' selected=\"selected\" >"+sysName+"</option>");
				}
		})	

		
		function qsttype(qstType){
			if(qstType==4){
				$("#troption").hide();
				$("#trASR").hide();
				
			}
			if(qstType==6){
			
				$("#ASR").html("");
				$("#troption").show();
				$("#trASR").hide();
				$("#trcuowupingyu").show();
				document.getElementById("troptiontext").innerHTML='<font color="#ff0000" id="troptiontext">*</font>正确答案';
					
			}
		}		
			
		function doSubmit(){//id进行拼接，然后发给action
		
		  var qstType=document.getElementById('select').value;
			if(qstType=='-1'){
				alert("请选择试题类型");
				return false;
				
			}
			
			
			var fSortId=document.getElementById('fSortId').value;
			if(fSortId=='-2'){
				alert("请选择课程选项1");
				return false;
				
			}
			
			var sSortId=document.getElementById('sSortId').value;
			if(sSortId=='-2'){
				alert("请选择课程选项2");
				return false;
				
			}
			var tSortId=document.getElementById('tSortId').value;
			if(tSortId=='-2'){
				alert("请选择课程选项3");
				return false;
				
			}
			
			var courseId=document.getElementById('courseId').value;
			if(courseId=='-1'){
				alert("请选择课程选项4");
				return false;
				
			}
			
			var Content =document.getElementById('qstContent').value;
			if(Content==''){
				alert("题干不能为空");
				return false;
			}
			if(Content.length>60000){
				alert("题干长度过长，请限制字数在6万以内");
				return false;
			}
			
				var duoCheck=document.getElementsByName("qst.isAsr");
				var k=0;
				for(i=0;i<duoCheck.length;i++){
					if(duoCheck[i].checked){
						k++;
					}
				}
			
				if(k==0&&qstType!=6)
				{
					alert("请至少选择一个选项");
					return false;
				}
			if(qstType==1){
				if(!danxuan()){
					return false;	
				}
			}else if(qstType==2||qstType==5){
				if(!duoXuan()){
				return false;
				}
			}else if(qstType==3){
				if(!panduan()){
				return false;
				}
			}else if(qstType==6){
				if(!zhuguan()){
				return false;
				}
			}
			
			document.forms[0].submit();
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
			
			/**/
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
			
			function changeColumnValue(ksnId, nodeId, name){
				$("#ksnId").attr("value", ksnId);
				$("#nodeId").attr("value", nodeId);
				
				obj="zpointList";
				document.getElementById("zpName").value = name;
				document.getElementById(obj).style.display="none";
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
			
			function showPkpoint1(){
				obj="zpointList";
				document.getElementById(obj).style.display="block";
			}
			
		
			
			function closeColumn1(){
				obj="zpointList";
				document.getElementById(obj).style.display="none";
			}

			
				function danxuan(){
				var a=document.getElementById("Aasr").value;
				var b=document.getElementById("Basr").value;
				var c=document.getElementById("Casr").value;
				var d=document.getElementById("Dasr").value; 
				if(a==""||b==""||c==""||d==""){
				alert("选项内容不能为空");
				return false
				}
				if(a.length>60000||b.length>60000||c.length>60000||d.length>60000){
				alert("选项答案长度过长，请限制字数在6万以内");
				return false;
				}
				return true;
			}
			
			function duoXuan(){
				
				var duoCheck=document.getElementsByName("qst.isAsr");
				var k=0;
				for(i=0;i<duoCheck.length;i++){
					if(duoCheck[i].checked){
						k++;
					}
				}
				if(k==0)
				{
					alert("请至少选择一个选项");
					return false;
				}
				k=0;
				var duoContent=document.getElementsByName("asr");
				var m=0;
				for(m=0;m<duoContent.length;m++){
					if(duoContent[m].value==""){
					alert("选项内容不能为空");
					return false
					}
					if(duoContent[m].length>60000){
					alert("选项答案长度过长，请限制字数在6万以内");
					return false;
					}
				}
				return true;
			}
			
			function panduan(){
				
				var a=document.getElementById("Aasr").value;
				var b=document.getElementById("Basr").value;
				if(a==""||b==""){
				alert("选项内容不能为空");
				return false
				}
				if(a.length>60000||b.length>60000){
				alert("选项答案长度过长，请限制字数在6万以内");
				return false;
				}
				return true;
			}
			
			
			
			function zhuguan(){
				var a=document.getElementById("Aasr").value;
				if(a==""){
				alert("选项设置不能为空");
				return false;
				}
				if(a.length>60000){
				alert("正确答案长度过长，请限制字数在6万以内");
				return false;
				}
				return true;
			}	
			
			
			//当试题类型改变时，选择框也跟着更改
			function qstName(value){
				var isAsr=document.getElementsByName("qst.isAsr");//但选中后获取答案；
				var Asr="";//正确答案
				for(j=0;j<isAsr.length;j++) {
					if(isAsr[j].type=="radio"){
					　　		 if(isAsr[j].checked)
					   　　	 Asr=isAsr[j].value;
					   }
					   if(isAsr[j].type=="checkbox"){
							if(isAsr[j].checked){
					   　　	 Asr+=isAsr[j].value;
							}
					   }
					   
				 }  
				 
				$("#ASR").html(Asr);
			}


			//根据专业ID查看知识树列表
			function onSelectSys(subjectId){
				if(subjectId==-1){
					return ;
				}
				var url = "<%=contextPath%>/exam/qst!getSysBySubjectId.action";
				$.post(url, {subjectId: subjectId}, function(data){
					if(data.jumpType==true){
						$("#ksId").text("");
						$("#ksId").append(data.returnMessage);
					}else{
						alert("error");
					}
				}, 'json');
			}

			//显示知识树
			function sysTree(ksId){
				if(ksId==-1){
					return ;
				}
				var url = "<%=contextPath%>/exam/qst!getSysTreeById.action";
				$.post(url, {ksId: ksId}, function(data){
					if(data.jumpType==true){
						var myList = data.entity;
						if(myList.length<1){
							$("#zpointList").html("");
							return;
						}
						$("#zpointList").show();
						addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
						addkpoint.add(-2,-1,'栏目列表 <a href="javascript:closeColumn1();">关闭</a>');
						for(var i=0;i<myList.length;i++){  
							addkpoint.add(myList[i].ksnId ,myList[i].parentId ,myList[i].nodeName,'javascript:changeColumnValue('+myList[i].ksnId+','+"'"+ myList[i].nodeId + "'," +"'"+myList[i].nodeName+"'"+')');	
						}
						$("#zpointList").html(addkpoint.toString());
					}else{
						alert("error");
					}
				}, 'json');
				
			}
			
			function removeKpoint(){
				$("#ksnId").val('');
				$("#nodeId").val('');
				$("#zpName").val('');
				
			}

			
		</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath %>/exam/qst!QstUpdate.action" method="post" enctype="multipart/form-data" name="exampaperaddform" id="exampaperaddform" onsubmit="return doSubmit()">
	<s:hidden name="qst.qsttype" id="exam.type"></s:hidden>
	<input type="hidden" name="qst.korcouId" id="exam.kOrCouId" value="<s:property  value="qst.korcouId"/>"/>
	<input type="hidden" id="ksnId" name="ksnId" value="<s:property  value="sysdto.ksnId"/>" />
	<input type="hidden" id="nodeId" name="nodeId" value="<s:property  value="sysdto.nodeId"/>" />
	<input type="hidden" name="exam.epId"  value="<s:property  value="exam.epId"/>"/>
	
	<input  type="hidden"  name="qst.qstId" value="<s:property value="qst.qstId"/>"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">试题修改</font>
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
              <td width="30%" align="right"><font color="#ff0000">*</font>试题类型：</td>
              <td width="70%" align="left" class="lists_tleft">
                <select name="qst.qstType" id="select">
				<option value="<s:property  value="qst.qstType"/>" selected="selected" ><s:if test="qst.qstType==1">单选题</s:if><s:if test="qst.qstType==2">多选题</s:if><s:if test="qst.qstType==3">判断题</s:if><s:if test="qst.qstType==4">材料分析题</s:if><s:if test="qst.qstType==5">图表题</s:if><s:if test="qst.qstType==6">主观题</s:if></option>
                </select>              </td>
            </tr>
            <tr>
              <td width="30%" align="right"><font color="#ff0000">*</font>试题难度：</td>
              <td width="70%" align="left" class="lists_tleft"><select name="qst.level" id="select2">
                <option value="<s:property  value="qst.level"/>              
                " selected="selected" >
                <s:if test="qst.level==1">1级</s:if>
                <s:if test="qst.level==2">2级</s:if>
                <s:if test="qst.level==3">3级</s:if>
                <option value="1">1级</option>
                <option value="2">2级</option>
                <option value="3">3级</option>
              </select>              
              级别越高难度越大</td>
            </tr>
            <tr>
              <td width="30%" align="right"><font color="#ff0000">*</font>选择课程：</td>
              <td width="70%" align="left" class="lists_tleft"><s:select name="fSortId" id="fSortId" list="courseSortList"
								listKey="coursesortId" listValue="coursesortName"   headerKey="3" headerValue="第一分类"
								theme="simple" onchange="onchangeFirstSort(this.value);">
								
							</s:select>
							
							<s:select name="sSortId" id="sSortId" list="#{}" headerKey="4"
								headerValue="第二级分类" theme="simple" onchange="onchangeSecond(this.value);">							</s:select>
							
							<s:select name="qst.sortId" id="tSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onchangeThird(this.value);">							</s:select>
							
							<s:select  id="courseId" list="#{}" headerKey="-1" name="qst.courseId" headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">							</s:select>			  </td>
            </tr>
            <tr>
              <td width="30%" align="right">选择课程节点：</td>
              <td width="70%" align="left" class="lists_tleft"><input id="pName" type="text" name="pName" onclick="showPkpoint();" readonly="readonly" />
   			  <div id="kpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">		     </div></td>
            </tr>
            <tr>
              <td width="30%" align="right">知识库分类：</td>
              <td width="70%" align="left" class="lists_tleft">

				<s:select name="sysdto.subjectId" 
					list="subjectList" listKey="subjectId" 
					listValue="subjectName" headerKey="0"
					headerValue="请选择所属专业" theme="simple" onchange="onSelectSys(this.value)">
			  	</s:select>
	
				<select name="ksId" id="ksId" onchange="sysTree(this.value)">
					<option value="-1">请选择知识树</option>
				</select>
						
								
			</td>
            </tr>
            <tr>
              <td width="30%" align="right">知识库知识点：</td>
              <td width="70%" align="left" class="lists_tleft">
              <input id="zpName" type="text" name="zpName" onclick="showPkpoint1();" readonly="readonly" value="<s:property  value="sysdto.nodeName"/>" /><input type="button" onclick="removeKpoint()" value="清空" />
   			  <div id="zpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">				    </div>	</td>
            </tr>
            <tr>
              <td width="30%" align="right"><font color="#ff0000">*</font>	题干：</td>
              <td width="70%" align="left" class="lists_tleft"><textarea id="qstContent" name="qst.qstContent" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;">
              <s:property value="qst.qstContent"  escape="false"/>
              </textarea>
            </tr>
            <tr  id="troption">
              <td width="30%" align="right" id="troptiontext"><font color="#ff0000">*</font>  选项设置：</td>
              <td width="70%" align="left" class="lists_tleft">
			
			  <s:set name="zhuguan" value="qst.isAsr"/>
			 <s:if test="qst.qstType==1">
			    <s:iterator value="qst.options" id="option" status="opt">  
			  <s:property  value="#option.optOrder"/>.
			  			<s:iterator value="chkIdList" id="chkId" status="chk">
			  			<s:if test='#chk.index==#opt.index'>
			  			
			  			<s:if test='#zhuguan == #option.optOrder'> <input type="radio"  name="qst.isAsr" value="<s:property  value="#option.optOrder"/>" checked="checked" onclick='qstName(value)'/></s:if>
							<s:else> <input type="radio" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>"  onclick='qstName(value)'/></s:else>
			 			<textarea id="<s:property value='#chkId'/>" name="asr" cols="" rows="" style="width:450px; height:150px;"><s:property  value="#option.optContent"/></textarea><br />
			 			</s:if>
			 		 </s:iterator>
			 	 </s:iterator>
			  </s:if>
			   
			    <s:if test="qst.qstType==2">
				 
				 <s:iterator value="qst.options" id="option" status="opt">  
			 		 <s:property  value="#option.optOrder"/>.
			  		<s:iterator value="chkIdList" id="chkId" status="chk">
			  			<s:if test='#chk.index==#opt.index'>
			 				 <s:if  test="-1 != #zhuguan.indexOf(#option.optOrder)">  <input type="checkbox" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>" checked="checked" onclick='qstName(value)'/></s:if>
			 				 <s:else>  <input type="checkbox" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>" onclick='qstName(value)' /></s:else>
			 				 <textarea id="<s:property value='#chkId'/>" name="asr" cols="" rows="" style="width:450px; height:150px;"><s:property  value="#option.optContent"/></textarea><br />
			  			</s:if>
			 		 </s:iterator>
			  	 </s:iterator>
			   </s:if>
			  
			  <s:if test="qst.qstType==3">
			   <s:iterator value="qst.options" id="option" status="opt">  
			 		 <s:property  value="#option.optOrder"/>.
			  			<s:iterator value="chkIdList" id="chkId" status="chk">
			  				<s:if test='#chk.index==#opt.index'>
			  					<s:if test='#zhuguan== #option.optOrder'> <input type="radio" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>" checked="checked" onclick='qstName(value)'/></s:if>
			 					 <s:else> <input type="radio" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>"  onclick='qstName(value)'/></s:else>
			 					 <textarea id="<s:property value='#chkId'/>" name="asr" cols="" rows="" style="width:450px; height:150px;"><s:property  value="#option.optContent"/></textarea><br />
			    			</s:if>
			 		    </s:iterator>
			   		 </s:iterator>
			  </s:if>
			  
			  <s:if test="qst.qstType==4">
			   <s:iterator value="qst.options" id="option">  
			  <s:if  test="-1 != #zhuguan.indexOf(#option.optOrder)">
			 	 <input type="checkbox" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>" checked="checked" onclick='qstName(value)' id="qstAsr"/></s:if><s:else>  <input type="checkbox" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>" onclick='qstName(value)' /></s:else><textarea name="asr" cols="" rows="" style="width:450px; height:150px;"><s:property  value="#option.optContent"/></textarea><br />
			    </s:iterator>
			  </s:if>
			  
			  <s:if test="qst.qstType==5">
			    <s:iterator value="qst.options" id="option" status="opt">  
			 		 <s:property  value="#option.optOrder"/>.
			  			<s:iterator value="chkIdList" id="chkId" status="chk">
			  				<s:if test='#chk.index==#opt.index'>
			  					<s:if  test="-1 != #zhuguan.indexOf(#option.optOrder)">
			 					 <input type="checkbox" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>" checked="checked" onclick='qstName(value)'/></s:if><s:else> <input type="checkbox" name="qst.isAsr" value="<s:property  value="#option.optOrder"/>"  onclick='qstName(value)'/></s:else>
			 					 <textarea id="<s:property value='#chkId'/>" name="asr" cols="" rows="" style="width:450px; height:150px;"><s:property  value="#option.optContent"/></textarea><br />
			   					</s:if>
			 		 	</s:iterator>
			   		 </s:iterator>
			    </s:if>
			  
			  <s:if test="qst.qstType==6">
			  <s:iterator value="qst.options" id="option" status="opt">  
			  		<s:iterator value="chkIdList" id="chkId" status="chk">
			  			<s:if test='#chk.index==#opt.index'>
			  				 <input  type="hidden" name="qst.isAsr" value="A" checked="checked"/>
							 <textarea id="<s:property value='#chkId'/>" name="asr" cols="" rows="" style="width:560px; height:365px;"><s:property  value="#option.optContent"/></textarea><br />
			   			</s:if>
			 		 </s:iterator>
			   </s:iterator>
			    
			  </s:if>              </td>
            </tr>
            <tr  id="trASR">
              <td width="30%" align="right"><font color="#ff0000">*</font>  正确答案：</td>
              <td width="70%" align="left" class="lists_tleft" id="ASR"><s:property value="qst.isAsr" /></td>
            </tr>
            <tr>
              <td width="30%" align="right"><font color="#ff0000"></font>	错误评语：</td>
              <td width="70%" align="left" class="lists_tleft"><textarea name="qst.wrongJude" rows="7" cols="70" id="wrongJude"><s:property value="qst.wrongJude" /></textarea></td>
            </tr>
            <tr>
              <td width="30%" align="right">试题备注：</td>
              <td width="70%" align="left" class="lists_tleft"><textarea name="qst.note"rows="7" cols="70" id="note"><s:property value="qst.note"/></textarea></td>
            </tr>
            <tr>
              <td width="30%" align="right">隶属试卷：</td>
              <td width="70%" align="left" class="lists_tleft"><textarea rows="7" cols="70" disabled="disabled"><s:iterator value="qstmiddleList" id="mid">
			  <s:property value="#mid.exam.epName"/>
			  
			  </s:iterator></textarea></td>
            </tr>
            <tr>
              <td width="30%" align="right">&nbsp;</td>
              <td width="70%" align="left" class="lists_tleft">&nbsp;</td>
            </tr>
            <tr>
              <td width="30%" align="right"><input class="submit" type="button" value="提交" onclick="doSubmit();" /></td>
              <td width="70%" align="left">&nbsp;</td>
            </tr>
            <tr>
              <td width="30%" align="right">&nbsp;</td>
              <td width="70%" align="left">&nbsp;</td>
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
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
					  <td>
						
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


<s:if test="qst.qstType!=4">
<script type="text/javascript">
<s:iterator value="qst.options" id="option" status="opt"> 
<s:iterator value="chkIdList" id="chkId" status="chk">
	<s:if test='#chk.index==#opt.index'>
		initEditor('<s:property value='#chkId'/>');
		createEditor('<s:property value='#chkId'/>');
	</s:if>	
</s:iterator>
</s:iterator>
</script>
</s:if>
