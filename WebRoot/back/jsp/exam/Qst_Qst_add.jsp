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
		<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<script type="text/javascript" src="<%=contextPath %>/fckeditor/fckeditor.js"></script>
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
			// 初始化 Kindeditor
				initEditor('txt_topic');
				createEditor('txt_topic');               
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
		})	
		
			function danxuan(){
				var a=document.getElementById("Aasr").value;
				var b=document.getElementById("Basr").value;
				var c=document.getElementById("Casr").value;
				var d=document.getElementById("Dasr").value; 
				if(a==""||b==""||c==""||d==""){
				alert("选项内容不能为空");
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
				}
				return true;
			}
		
			function zhuguan(){
				var a=document.getElementById("rightasr").value;
				if(a==""){
				alert("正确答案不能为空");
				return false;
				}
				return true;
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
			
			var Content =document.getElementById('txt_topic').value;
			if(Content==''){
				alert("题干不能为空");
				return false;
			}
	
			if(qstType==1){
			if(!danxuan()){
					return false;	
				}
			}else if(qstType==2){
				if(!duoXuan()){
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
						
			
			function addCheckBox(qsttype)
			{
				var duocheck=document.getElementsByName("qst.isAsr");
				var buttonAdd2="<input type='button'  value='添加' onclick='addCheckBox(2)'/><input type='button'  value='删除' onclick='delCheckBox(2)'/><br />";
				var duoxuanContent=document.getElementsByName("asr");
				var qianBan=".<input type='checkbox' name='qst.isAsr'  value='";
				var zhongban="' checked='";
				var houban="' onclick='qstName(value)'/><textarea name='asr' id='";
				var namehouban="</textarea><br /><input  type='hidden' id='duoxuanNumber' name='duoxuanNumber' value='";
				var idhouban="asr' cols='' rows='' ' style='width:450px; height:150px;'>";
				var zimu=new Array('A','B','C','D','E','F','G');
				var newAdd='';
				var checkContent='';
				if(qsttype==2){
				var k=document.getElementById("duoxuanNumber").value;
					if(k>6){
						alert('最多只能添加3个选项，不能再添加了！');
						return false;
					}else{
						 k++;
					 	for(var i=0;i<k;i++){
						 	if(i<k-1){
						 		if(duocheck[i].checked){
						 		checkContent+=duocheck[i].value;
								newAdd+=zimu[i]+qianBan+zimu[i]+zhongban+duocheck[i].checked+houban+zimu[i]+idhouban+duoxuanContent[i].value+namehouban+k+"'/>";
								}else{
								newAdd+=zimu[i]+qianBan+zimu[i]+houban+zimu[i]+idhouban+duoxuanContent[i].value+namehouban+k+"'/>";
								}
							}
							 else{ newAdd+=zimu[i]+qianBan+zimu[i]+houban+zimu[i]+idhouban+namehouban+k+"'/>";}
						 }
						 newAdd+=buttonAdd2;
				 	}
				 }
						$("#ASR").html(checkContent);
						$("#qstTypetext").html(newAdd);
						$("#troption").show();
						$("#trASR").show();
						$("#trcuowupingyu").show();

						//为多选和图表题添加文本编辑器

						for(var i=0;i<k;i++){
							initEditor(zimu[i]+'asr');
							createEditor(zimu[i]+'asr');
						}
			}
			function delCheckBox(qsttype){
				var duocheck=document.getElementsByName("qst.isAsr");
				var buttonAdd2="<input type='button'  value='添加' onclick='addCheckBox(2)'/><input type='button'  value='删除' onclick='delCheckBox(2)'/><br />";
				var duoxuanContent=document.getElementsByName("asr");
				var qianBan=".<input type='checkbox' name='qst.isAsr'  value='";
				var zhongban="' checked='";
				var houban="' onclick='qstName(value)'/><textarea name='asr' id='";
				var namehouban="</textarea><br /><input  type='hidden' id='duoxuanNumber' name='duoxuanNumber' value='";
				var idhouban="asr' cols='' rows='' ' style='width:450px; height:150px;'>";
				var zimu=new Array('A','B','C','D','E','F','G');
				var newAdd='';
				var checkContent='';
				if(qsttype==2){
				var k=document.getElementById("duoxuanNumber").value;
					if(k<5){
						alert('最少要有4个选项，不能再删了！');
						return false;
					}else{
						 k--;
					 	for(var i=0;i<k;i++){
						 		if(duocheck[i].checked){
						 		checkContent+=duocheck[i].value;
								newAdd+=zimu[i]+qianBan+zimu[i]+zhongban+duocheck[i].checked+houban+zimu[i]+idhouban+duoxuanContent[i].value+namehouban+k+"'/>";
								}else{
								newAdd+=zimu[i]+qianBan+zimu[i]+houban+zimu[i]+idhouban+duoxuanContent[i].value+namehouban+k+"'/>";
								}
						 }
						 newAdd+=buttonAdd2;
				 	}
				 }
						$("#ASR").html(checkContent);
						$("#qstTypetext").html(newAdd);
						$("#troption").show();
						$("#trASR").show();
						$("#trcuowupingyu").show();

						//为多和图表题选添加文本编辑器
						for(var i=0;i<k;i++){
							initEditor(zimu[i]+'asr');
							createEditor(zimu[i]+'asr');
						}
			}
			
			function qstType(value){
			var duoxuan="A.<input type='checkbox' name='qst.isAsr' value='A' checked='checked' onclick='qstName(value)' /><textarea id='Aasr' name='asr' cols='' rows='' style='width:450px; height:150px;'></textarea><br />"+ 
			"B.<input type='checkbox' name='qst.isAsr' value='B' onclick='qstName(value)'/><textarea id='Basr' name='asr' cols='' rows='' style='width:450px; height:150px;'></textarea><br />"+
			"C.<input type='checkbox' name='qst.isAsr' value='C' onclick='qstName(value)'/><textarea id='Casr' name='asr' cols='' rows='' style='width:450px; height:150px;'></textarea><br />"+
			"D.<input type='checkbox' name='qst.isAsr' value='D' onclick='qstName(value)'/><textarea id='Dasr' name='asr' cols='' rows='' style='width:450px; height:150px;'></textarea><br />"+
			"<input type='button'  value='添加'  onclick='addCheckBox(2)'/><input type='button'  value='删除' onclick='delPlanCheckBox(2)'/><br />"+
			"<input  type='hidden' id='duoxuanNumber' name='duoxuanNumber' value='4'/>";
			
			var danxuan=" A.<input type='radio'name='qst.isAsr' value='A' checked='checked' onclick='qstName(value)' /><textarea id='Aasr' name='asr' cols='' rows='' style='width:450px; height:150px;'></textarea><br />"+ 
			"B.<input type='radio' name='qst.isAsr' value='B' onclick='qstName(value)'/><textarea id='Basr' name='asr' cols='' rows='' style='width:450px; height:150px;'></textarea><br />"+
			" C.<input type='radio' name='qst.isAsr' value='C' onclick='qstName(value)'/><textarea id='Casr' name='asr' cols='' rows='' style='width:450px; height:150px;'></textarea><br />"+
			"D.<input type='radio' name='qst.isAsr' value='D' onclick='qstName(value)'/><textarea id='Dasr' name='asr' cols='' rows='' style='width:450px; height:150px;'></textarea><br />";
			
			var zhuguan="<textarea id='rightasr' name='zhuguanContent' cols='100' rows='8' style='width:560px;height:365px;visibility:hidden;'/>";
			
			var content=document.getElementById("txt_topic").value;
			var bb;
			if(content==""){
				bb=true;
			}else{

					bb= window.confirm("该试题还没有保存，变更题型后，\n需重新输入，确定变更？");
			}
			if(bb){
				
				
			
				if(value==1){
				$("#ASR").html("A");
				$("#qstTypetext").html(danxuan);
				$("#troption").show();
				$("#trASR").show();
				$("#trcuowupingyu").show();
				document.getElementById("troptiontext").innerHTML='<font color="#ff0000" id="troptiontext">*</font>选项内容';

				initEditor('Aasr');
				createEditor('Aasr');

				initEditor('Basr');
				createEditor('Basr');

				initEditor('Casr');
				createEditor('Casr');

				initEditor('Dasr');
				createEditor('Dasr');
				
				}
				
				
				if(value==2){
				$("#ASR").html("A");
				$("#qstTypetext").html(duoxuan);
				$("#troption").show();
				$("#trASR").show();
				$("#trcuowupingyu").show();
				document.getElementById("troptiontext").innerHTML='<font color="#ff0000" id="troptiontext">*</font>选项内容';

				initEditor('Aasr');
				createEditor('Aasr');

				initEditor('Basr');
				createEditor('Basr');

				initEditor('Casr');
				createEditor('Casr');

				initEditor('Dasr');
				createEditor('Dasr');
				
				}
				
				if(value==6){
				$("#ASR").html("");
				$("#qstTypetext").html(zhuguan);
				$("#troption").show();
				$("#trASR").hide();
				$("#trcuowupingyu").show();
				document.getElementById("troptiontext").innerHTML='<font color="#ff0000" id="troptiontext">*</font>正确答案';
				initEditor('rightasr');
				createEditor('rightasr');
				}
			}

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
			
			function deleteqst(no,qstId){
				
				var tab=document.getElementById("table2");
						var member=no.parentNode.parentNode.rowIndex ;
						var nownum=0;
			
						
						if(tab.rows.length>0){
				
							$.ajax({  
								url : "<%=contextPath %>/exam/qst!Qstdel.action",  
								data : {"qstId" : qstId},  // 参数  
								type : "post",  
								cache : false,  
								dataType : "json",  //返回json数据 
								error: function(){ 
									alert('error');      
								}, 
								success:function (result){
								var isdel=result.returnMessage;
									if(isdel=="1"){
												
												tab.deleteRow(member);
												for(j = 0; j < tab.rows.length; j ++){
									
												nownum ++;
												
												tab.rows[j].cells[0].innerHTML = nownum+"、";
												}
												alert("删除成功");
								
									} else{
									
										alert("删除失败");
									}
								}
								});
					
					}
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
	<form action="<%=contextPath %>/exam/qst!QstcailiaoAdd.action" method="post" enctype="multipart/form-data" name="exampaperaddform" id="exampaperaddform" onsubmit="return doSubmit()">
	<input type="hidden"  id="exam.type" value="1" />
	<input type="hidden" name="qst.korcouId" id="exam.kOrCouId" value="<s:property  value="qst.korcouId"/>"/>
	<input type="hidden" id="ksnId" name="ksnId"/>
	<input type="hidden" id="nodeId" name="nodeId"/>
	<input  type="hidden" id="qstzkid0" name="qstzkid0"/>
	<input  type="hidden" name="qst.qstId" value="<s:property value="qst.qstId"/>"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">试题添加</font>
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
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="tblList" >
            <tr>
              <td width="30%" align="right"><font color="#ff0000">*</font>试题类型：</td>
              <td width="70%" align="left" class="lists_tleft">
                <select name="qst.qstType" id="select" onchange="qstType(value)">
					<option value="-1" selected="selected">请选试题类型</option>
                  <option value="1">单选题</option>
                  <option value="2">多选题</option>
                  <option value="6">简答题</option>
                </select>
              </td>
            </tr>
            <tr>
              <td width="30%" align="right"><font color="#ff0000">*</font>试题难度：</td>
              <td width="70%" align="left" class="lists_tleft"> <select name="qst.level" id="select">
                  <option value="1" selected="selected">一星</option>
                  <option value="2">二星</option>
                  <option value="3">三星</option>
                  
                </select>
              级别越高难度越大</td>
            </tr>
            <tr>
              <td width="30%" align="right"><font color="#ff0000">*</font>选择课程：</td>
              <td width="70%" align="left" class="lists_tleft"><s:select name="fSortId" id="fSortId" list="courseSortList"
								listKey="coursesortId" listValue="coursesortName" 
								theme="simple" onchange="onchangeFirstSort(this.value);">
							</s:select>
							
							<s:select name="sSortId" id="sSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onchangeSecond(this.value);">
							</s:select>
							
							<s:select name="qst.sortId" id="tSortId" list="#{}" headerKey="-1"
								headerValue="请选择" theme="simple" onchange="onchangeThird(this.value);">
							</s:select>
							
							<s:select  id="courseId" list="#{}" headerKey="-1" name="qst.courseId"
								headerValue="请选择" theme="simple" onchange="onSelectCourse(this.value)">
							</s:select>
							
			  </td>
            </tr>
            <tr>
              <td width="30%" align="right"><font color="#ff0000">*</font>选择课程节点：</td>
              <td width="70%" align="left" class="lists_tleft"><input id="pName" type="text" name="pName" onclick="showPkpoint();" readonly="readonly"  style="width:200px;"/>
	    			<div id="kpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
		      </div></td>
            </tr>
            <tr>
              <td width="30%" align="right"  >知识库分类：</td>
              <td width="70%" align="left" class="lists_tleft">
              
              <s:select list="subjectList" listKey="subjectId" 
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
              <td width="70%" align="left" class="lists_tleft"><input id="zpName" type="text" name="zpName" onclick="showPkpoint1();" readonly="readonly" /><input type="button" onclick="removeKpoint()" value="清空" />
   			  <div id="zpointList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">				    </div>	</td>
            </tr>
            <tr>
              <td width="30%" align="right"><font color="#ff0000">*</font>	题干：</td>
              <td width="70%" align="left" class="lists_tleft"><textarea id="txt_topic" name="qst.qstContent" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;"></textarea></td>
            </tr>
            <tr id="troption">
              <td width="30%" align="right" id="troptiontext"><font color="#ff0000" >*</font>  选项设置：</td>
              <td width="70%" align="left" class="lists_tleft" id="qstTypetext">
				
              </td>
            </tr>
            <tr id="trASR">
              <td width="30%" align="right"><font color="#ff0000">*</font>  正确答案：</td>
              <td width="70%" align="left" class="lists_tleft" id="ASR" >&nbsp;</td>
            </tr>
            <tr id="trcuowupingyu">
              <td width="30%" align="right"><font color="#ff0000"></font>	错误评语：</td>
              <td width="70%" align="left" class="lists_tleft"><textarea name="qst.wrongJude" rows="7" cols="70"></textarea></td>
            </tr>
            <tr>
              <td width="30%" align="right">试题备注：</td>
              <td width="70%" align="left" class="lists_tleft"><textarea name="qst.note"rows="7" cols="70"></textarea></td>
            </tr>
            <tr>
              <td width="30%" align="right">&nbsp;</td>
              <td width="70%" align="left" class="lists_tleft">&nbsp;</td>
            </tr>
            <tr>
              <td width="30%" align="right"><input class="submit" type="button" value="提交" onclick="doSubmit();" /></td>
              <td width="70%" align="left"><input type="reset" name="Submit" value="重置" /></td>
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
						<td><table width="100%" border="0" id="table2">
  <tr>
    <td colspan="8"><strong>材料分析题，题目列表</strong></td>
    </tr>
  <tr>
    <td>题号</td>
    <td>试题id</td>
    <td>子题id</td>
    <td>子题内容</td>
    <td>试题类型</td>
    <td>正确答案</td>
    <td>添加时间</td>
    <td>操作</td>
  </tr>
  <s:iterator value="qst.qstziti" id="qst"  status="stuts">
  <tr>
							<td><s:property value="#stuts.index+1" /></td>
							<td><s:property value="#qst.qstindexId"/></td>
							<td><s:property value="#qst.qstId"/></td>
										  
							<td>
								<s:property value="#qst.qstContent" />
						  	</td>
							<td>
							
							<s:if test="#qst.qstType==1">单选题</s:if>	<s:if test="#qst.qstType==6">简答题</s:if><s:if test="#qst.qstType==2">多选题</s:if><s:if test="#qst.qstType==3">判断题</s:if><s:if test="#qst.qstType==4">材料分析题</s:if><s:if test="#qst.qstType==5">图标题</s:if>	</td>
						
							
						  <td>
						  <s:property value="#qst.isAsr"/>
						  </td>
							
							<td>
								<s:date name="#qst.addtime"  format="yyyy-MM-dd HH:mm:ss"/>							</td>
						  <td>
						
								<a href="<%=contextPath%>/exam/qst!toQstUpdate.action?qstId= <s:property value="#qst.qstId"/>">修改</a><a href="#" onclick="deleteqst(this,<s:property value="#qst.qstId"/>)">删除</a></td>
						</tr>
					</s:iterator>
</table></td>
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
