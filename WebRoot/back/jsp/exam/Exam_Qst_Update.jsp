<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>试题组卷修改</title>
		
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
			#top{vertical-align:top;}
		</style>
				<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<link rel="shortcut icon" href="../fckeditor.gif"
				type="image/x-icon" />
		
		<script  language="javascript" type="text/javascript">
		
			$().ready(function(){
				examtotel=$("#totelscore").val(); 
				epId=$("#epId").val();
			})
			
			
			//去除重复选择的项   
			function newWindow(myArrayInStock){   
				//去重复时要有一个主键，这里用产品号,productIds每一个复选框都要用这个name   
				var qstIds = document.getElementsByName("qstId");   
				var qstId = document.getElementsByName("danxuanqstId");  
				for(var i=0;i<qstIds.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstIds[i].value==id[0]){   
						
							myArrayInStock.splice(j,1);   
						}   
					}   
				}   
				for(var i=0;i<qstId.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstId[i].value==id[0]){   
						
							myArrayInStock.splice(j,1);   
						}   
					}   
				}
				//插入到表格中   
				qstAdd(myArrayInStock);   
				
			}
			
			function qstAdd(myArray) 
			{ 
			
				for(var i=0;i<myArray.length;i++){   
				var arr=myArray[i]; 
				
				var tab=document.getElementById("table2");
				var _tr=tab.insertRow(tab.rows.length); 
				
				var _td=_tr.insertCell(0); 
				_td.innerHTML=tab.rows.length+"、";
				
				var _td=_tr.insertCell(1); 
				_td.innerHTML=" <input  type='text' readonly='readonly' flag='true' size='14' value="+arr[0]+" name='qstId'/>  <input  type='hidden'    value="+arr[0]+" name='qstId2'/>   ";
				
				var _td=_tr.insertCell(2); 
				_td.innerHTML=" <input  type='text'  size='14' value='1' name='qstScore'/>";
				
				var _td=_tr.insertCell(3); 
				_td.innerHTML=arr[1];
				
				
				var _td=_tr.insertCell(4); 
				_td.innerHTML="<a href='#' onclick='delrow(this)'>删除</a>";
				}
			
			} 
			
			//多选
			function newWindowduoxuan(myArrayInStock){   
				//去重复时要有一个主键，这里用产品号,productIds每一个复选框都要用这个name
				var qstIds = document.getElementsByName("duoxuan_qstId");  
				var qstId  = document.getElementsByName("duoxuanqstId"); 
				for(var i=0;i<qstIds.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstIds[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				} 
				for(var i=0;i<qstId.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstId[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				}     
				//插入到表格中   
				qstAddduoxuan(myArrayInStock);   
				
			}
			
			function qstAddduoxuan(myArray) 
			{ 
			
				for(var i=0;i<myArray.length;i++){   
				var arr=myArray[i]; 
				
				var tab=document.getElementById("tableduoxuan");
				var _tr=tab.insertRow(tab.rows.length); 
				
				var _td=_tr.insertCell(0); 
				_td.innerHTML=tab.rows.length+"、";
				
				var _td=_tr.insertCell(1); 
				_td.innerHTML=" <input  type='text' readonly='readonly' flag='true' size='14' value="+arr[0]+" name='duoxuan_qstId'/>";
				
				var _td=_tr.insertCell(2); 
				_td.innerHTML=" <input  type='text'  size='14' value='1' name='duoxuan_qstScore'/>";
				
				var _td=_tr.insertCell(3); 
				_td.innerHTML=arr[1];
				
				
				var _td=_tr.insertCell(4); 
				_td.innerHTML="<a href='#' onclick='delrowduoxuan(this)'>删除</a>";
				}
			
			} 

				
				//判断
			function newWindowpanduan(myArrayInStock){   
				//去重复时要有一个主键，这里用产品号,productIds每一个复选框都要用这个name 
				var qstIds = document.getElementsByName("panduan_qstId");
				var qstId  = document.getElementsByName("panduanqstId");      
				for(var i=0;i<qstIds.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstIds[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				}   
				for(var i=0;i<qstId.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstId[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				}  
				//插入到表格中   
				qstAddpanduan(myArrayInStock);   
				
			}
			
			function qstAddpanduan(myArray) 
			{ 
			
				for(var i=0;i<myArray.length;i++){   
				var arr=myArray[i]; 
				
				var tab=document.getElementById("tablepanduan");
				var _tr=tab.insertRow(tab.rows.length); 
				
				var _td=_tr.insertCell(0); 
				_td.innerHTML=tab.rows.length+"、";
				
				var _td=_tr.insertCell(1); 
				_td.innerHTML=" <input  type='text' readonly='readonly' flag='true' size='14' value="+arr[0]+" name='panduan_qstId'/>";
				
				var _td=_tr.insertCell(2); 
				_td.innerHTML=" <input  type='text'  size='14' value='1' name='panduan_qstScore'/>";
				
				var _td=_tr.insertCell(3); 
				_td.innerHTML=arr[1];
				
				
				var _td=_tr.insertCell(4); 
				_td.innerHTML="<a href='#' onclick='delrowpanduan(this)'>删除</a>";
				}
			
			} 
			
			
			//材料分析
			function newWindowcailiao(myArrayInStock){   
				//去重复时要有一个主键，这里用产品号,productIds每一个复选框都要用这个name
				var qstIds = document.getElementsByName("cailiao_qstId");
				var qstId = document.getElementsByName("cailiaoqstId");
				for(var i=0;i<qstIds.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstIds[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				}   
				for(var i=0;i<qstId.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstId[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				}   
				//插入到表格中   
				qstAddcailiao(myArrayInStock);   
				
			}
			
			function qstAddcailiao(myArray) 
			{ 
				var arr0="";
				for(var i=0;i<myArray.length;i++){   
					arr0="";
					var arr=myArray[i]; 
					var tab=document.getElementById("tablecailiao");
					var _tr=tab.insertRow(tab.rows.length); 
					
					var _td=_tr.insertCell(0); 
					_td.id="top";
					_td.innerHTML=tab.rows.length+"、";
					
					var _td=_tr.insertCell(1); 
					_td.id="top";
					var str=" <input  type='text'  size='14' readonly='readonly' value='"+arr[0]+"' name='cailiao_qstId'/>";
					
					_td.innerHTML=str;
					_td.id="top";
					var _td=_tr.insertCell(2); 
					_td.innerHTML="<input  type='text'  size='14' value='0' name='cailiao_qstScore' onfocus='this.blur()'/><table id='ziti"+arr[0]+"'></table>";
					
					var _td=_tr.insertCell(3);
					_td.id="top"; 
					_td.innerHTML=arr[1];
					
					
					var _td=_tr.insertCell(4); 
					_td.id="top";
					_td.innerHTML="<a href='#' onclick='delrowcailiao(this)'>删除</a>";
					
					
					document.getElementById("top").valign="top";

					arr0=arr[0]+",";			 
					zicailiaoti(arr0);
				}
			
			} 
			
			function zicailiaoti(arr0){
			var arry=arr0.split(",");
			for(var i=0;i<arry.length-1;i++){
				var arryqstid=arry[i];
				
				$.ajax({
						url:"<%=contextPath%>/exam/qst!ziqst.action",
						data:{"qstId":arry[i]},
						dataType:"json",
						type : "post",  
						cache : true, 
						error:function(){
							alert("error");
						},
						success:function(result){
							var qstid=result.returnMessage;
							var strqst = qstid.split(",");
							var idname="";
							for(var a=0;a<strqst.length-1;a++){
									idname="ziti"+arryqstid;
									//alert(idname);
									var tab1=document.getElementById(idname);
								
									var _tr1=tab1.insertRow(tab1.rows.length); 
									var _td1=_tr1.insertCell(0); 
									_td1.innerHTML=tab1.rows.length+"、";
									var _td1=_tr1.insertCell(1); 
									
									_td1.innerHTML=" <input  type='text'  size='14' readonly='readonly' value='"+strqst[a]+"' name='cailiao_qstId"+arryqstid+"'/>";
									
									var _td1=_tr1.insertCell(2); 
									_td1.innerHTML=" <input  type='text'  size='14' value='1' name='cailiao_qstScore"+arryqstid+"'/>";
								
							}
							
						}
					});
				}
				
			
			}
			
			
				//图表
			function newWindowtubiao(myArrayInStock){   
				//去重复时要有一个主键，这里用产品号,productIds每一个复选框都要用这个name
				var qstIds = document.getElementsByName("tubiao_qstId");  
				var qstId = document.getElementsByName("tubiaoqstId");    
				for(var i=0;i<qstIds.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstIds[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				} 
				 for(var i=0;i<qstId.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstId[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				}  
				//插入到表格中   
				qstAddtubiao(myArrayInStock);   
				
			}
			
			function qstAddtubiao(myArray) 
			{ 
			
				for(var i=0;i<myArray.length;i++){   
				var arr=myArray[i]; 
				
				var tab=document.getElementById("tabletubiao");
				var _tr=tab.insertRow(tab.rows.length); 
				
				var _td=_tr.insertCell(0); 
				_td.innerHTML=tab.rows.length+"、";
				
				var _td=_tr.insertCell(1); 
				_td.innerHTML=" <input  type='text' readonly='readonly' flag='true' size='14' value="+arr[0]+" name='tubiao_qstId'/>";
				
				var _td=_tr.insertCell(2); 
				_td.innerHTML=" <input  type='text'  size='14' value='1' name='tubiao_qstScore'/>";
				
				var _td=_tr.insertCell(3); 
				_td.innerHTML=arr[1];
				
				
				var _td=_tr.insertCell(4); 
				_td.innerHTML="<a href='#' onclick='delrowtubiao(this)'>删除</a>";
				}
			
			} 
			
				//主观
			function newWindowzhuguan(myArrayInStock){   
				//去重复时要有一个主键，这里用产品号,productIds每一个复选框都要用这个name
				var qstIds = document.getElementsByName("zhuguan_qstId");  
				var qstId = document.getElementsByName("zhuguanqstId");  
				for(var i=0;i<qstIds.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstIds[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				}   
				for(var i=0;i<qstId.length;i++){   
					for(var j=0;j<myArrayInStock.length;j++){   
						var id = myArrayInStock[j];   
						if(qstId[i].value==id[0]){   
							myArrayInStock.splice(j,1);   
						}   
					}   
				} 
				//插入到表格中   
				qstAddzhuguan(myArrayInStock);   
				
			}
			
			function qstAddzhuguan(myArray) 
			{ 
			
				for(var i=0;i<myArray.length;i++){   
				var arr=myArray[i]; 
				
				var tab=document.getElementById("tablezhuguan");
				var _tr=tab.insertRow(tab.rows.length); 
				
				var _td=_tr.insertCell(0); 
				_td.innerHTML=tab.rows.length+"、";
				
				var _td=_tr.insertCell(1); 
				_td.innerHTML=" <input  type='text' readonly='readonly' flag='true' size='14' value="+arr[0]+" name='zhuguan_qstId'/>";
				
				var _td=_tr.insertCell(2); 
				_td.innerHTML=" <input  type='text'  size='14' value='5' name='zhuguan_qstScore'/>";
				
				var _td=_tr.insertCell(3); 
				_td.innerHTML=arr[1];
				
				
				var _td=_tr.insertCell(4); 
				_td.innerHTML="<a href='#' onclick='delrowzhuguan(this)'>删除</a>";
				}
			
			} 
			
			
			
			//单选删除list
		  function delrowdanxuanlist(no,eqid,score) { 
						var tab=document.getElementById("table2");
						var member=no.parentNode.parentNode.rowIndex ;
						var nownum=0;
						if(tab.rows.length>0){
							$.ajax({
								url : "<%=contextPath%>/exam/qstmid!ExamQstDel.action",  
								data : {"eqId" : eqid,
								"score" : score,
								"epId":epId,
								"totleScore":examtotel},  // 参数  
								type : "post",  
								cache : false,  
								dataType : "json",  //返回json数据  
								success:function(result) {
									var array = result.returnMessage;
									if(array=="ok"){
									examtotel=examtotel-score;
									tab.deleteRow(member);
									for(j = 0; j < tab.rows.length; j ++){
						
									nownum ++;
									
									tab.rows[j].cells[0].innerHTML = nownum+"、";
						}
									alert("删除成功");
									}
								} ,
								error: function(){ 
									alert('error');      
								}
							});
							
							
						}else {
							 alert("不能再删了");
						}
						
			
			} 
			
			
			//多选选删除list
		  function delrowduoxuanlist(no,eqid,score) { 
						var tab=document.getElementById("tableduoxuan");
						var member=no.parentNode.parentNode.rowIndex ;
						var nownum=0;
			
						
						if(tab.rows.length>0){
							$.ajax({
								url : "<%=contextPath%>/exam/qstmid!ExamQstDel.action",  
								data : {"eqId" : eqid,
								"score" : score,
								"epId":epId,
								"totleScore":examtotel},  // 参数  
								type : "post",  
								cache : false,  
								dataType : "json",  //返回json数据  
								success:function(result) {
									var array = result.returnMessage;
									if(array=="ok"){
									examtotel=examtotel-score;
									tab.deleteRow(member);
									for(j = 0; j < tab.rows.length; j ++){
						
									nownum ++;
									
									tab.rows[j].cells[0].innerHTML = nownum+"、";
						}
									alert("删除成功");
									}
								} ,
								error: function(){ 
									alert('error');      
								}
							});
							
							
						}else {
							 alert("不能再删了");
						}
						
			
			} 
			
			//判断删除list
		  function delrowpanduanlist(no,eqid,score) { 
						var tab=document.getElementById("tablepanduan");
						var member=no.parentNode.parentNode.rowIndex ;
						var nownum=0;
			
						
						if(tab.rows.length>0){
							$.ajax({
								url : "<%=contextPath%>/exam/qstmid!ExamQstDel.action",  
								data : {"eqId" : eqid,
								"score" : score,
								"epId":epId,
								"totleScore":examtotel},  // 参数  
								type : "post",  
								cache : false,  
								dataType : "json",  //返回json数据  
								success:function(result) {
									var array = result.returnMessage;
									if(array=="ok"){
									examtotel=examtotel-score;
									tab.deleteRow(member);
									for(j = 0; j < tab.rows.length; j ++){
						
									nownum ++;
									
									tab.rows[j].cells[0].innerHTML = nownum+"、";
						}
									alert("删除成功");
									}
								} ,
								error: function(){ 
									alert('error');      
								}
							});
							
							
						}else {
							 alert("不能再删了");
						}
						
			
			} 
			
				//材料删除list
		  function delrowcailiaolist(no,eqid,score) { 
						var tab=document.getElementById("tablecailiao");
						var member=no.parentNode.parentNode.rowIndex ;
						var nownum=0;
			
						
						if(tab.rows.length>0){
							$.ajax({
								url : "<%=contextPath%>/exam/qstmid!ExamQstDel.action",  
								data : {"eqId" : eqid,
								"score" : score,
								"epId":epId,
								"totleScore":examtotel},  // 参数  
								type : "post",  
								cache : false,  
								dataType : "json",  //返回json数据  
								success:function(result) {
									var array = result.returnMessage;
									if(array=="ok"){
									examtotel=examtotel-score;
									tab.deleteRow(member);
									for(j = 0; j < tab.rows.length; j ++){
						
									nownum ++;
									
									tab.rows[j].cells[0].innerHTML = nownum+"、";
						}
									alert("删除成功");
									}
								} ,
								error: function(){ 
									alert('error');      
								}
							});
							
							
						}else {
							 alert("不能再删了");
						}
						
			
			} 
			
			
			
			//图表删除list
		  function delrowtubiaolist(no,eqid,score) { 
						var tab=document.getElementById("tabletubiao");
						var member=no.parentNode.parentNode.rowIndex ;
						var nownum=0;
			
						
						if(tab.rows.length>0){
							$.ajax({
								url : "<%=contextPath%>/exam/qstmid!ExamQstDel.action",  
								data : {"eqId" : eqid,
								"score" : score,
								"epId":epId,
								"totleScore":examtotel},  // 参数  
								type : "post",  
								cache : false,  
								dataType : "json",  //返回json数据  
								success:function(result) {
									var array = result.returnMessage;
									if(array=="ok"){
									examtotel=examtotel-score;
									tab.deleteRow(member);
									for(j = 0; j < tab.rows.length; j ++){
						
									nownum ++;
									
									tab.rows[j].cells[0].innerHTML = nownum+"、";
						}
									alert("删除成功");
									}
								} ,
								error: function(){ 
									alert('error');      
								}
							});
							
							
						}else {
							 alert("不能再删了");
						}
						
			
			} 
			
			//主观删除list
		  function delrowzhuguanlist(no,eqid,score) { 
						var tab=document.getElementById("tablezhuguan");
						var member=no.parentNode.parentNode.rowIndex ;
						var nownum=0;
			
						
						if(tab.rows.length>0){
							$.ajax({
								url : "<%=contextPath%>/exam/qstmid!ExamQstDel.action",  
								data : {"eqId" : eqid,
								"score" : score,
								"epId":epId,
								"totleScore":examtotel},  // 参数  
								type : "post",  
								cache : false,  
								dataType : "json",  //返回json数据  
								success:function(result) {
									var array = result.returnMessage;
									if(array=="ok"){
									examtotel=examtotel-score;
									tab.deleteRow(member);
									for(j = 0; j < tab.rows.length; j ++){
						
									nownum ++;
									
									tab.rows[j].cells[0].innerHTML = nownum+"、";
						}
									alert("删除成功");
									}
								} ,
								error: function(){ 
									alert('error');      
								}
							});
							
							
						}else {
							 alert("不能再删了");
						}
						
			
			} 
				
			function showwin(type){
				var win1 = window.open('<%=contextPath %>/exam/qst!QstListtanchuan.action?queryQstCondition.currentPage=1&type='+type,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=300,width=800,height=400');
			}
			
			
			function show_confirm(){

				var flag;
				
				var zongfen=$("#zongfen").val();
			 	var danxuan =  document.getElementsByName("qstScore");
			 	var qstId =  document.getElementsByName("qstId");
				var patrn=/^[0-9]{1,20}$/;
				
			 if(danxuan!=null){
				 for(var i=0;i<danxuan.length;i++){
					if(danxuan[i].value!="试题分数"&&danxuan[i].value!=""&&qstId[i].value!="试题Id"&&qstId[i].value!=""){
						flag = $(qstId[i]).attr("flag");
						if(flag!="true"){
							return false;
						}							
						if(danxuan[i].value<0){
							alert("试题分数不能为负数");
							return false;
						}
						if(danxuan[i].value>40){
						 	alert("试题分数不能大于40");
						 	return false;
						}
						if(!patrn.exec(danxuan[i].value)){
						 	alert("试题分数必须是正整数");
						 	return false;
						}
						zongfen=parseInt(zongfen)+parseInt(danxuan[i].value);
					}else {
						alert("请填写试题Id或试题分数");
						return false;
					}
				 }
			 }
			 
			//多选题
			var duoxuan =  document.getElementsByName("duoxuan_qstScore");
			var duoxuan_qstId =  document.getElementsByName("duoxuan_qstId");
			if(duoxuan!=null){
				for(var i=0;i<duoxuan.length;i++){
					if(duoxuan[i].value!="试题分数"&&duoxuan[i].value!=""&&duoxuan_qstId[i].value!="试题Id"&&duoxuan_qstId[i].value!=""){
						flag = $(duoxuan_qstId[i]).attr("flag");
						if(flag!="true"){
							return false;
						}	
						var duoxuanfen=duoxuan[i].value;
						if(duoxuan[i].value<0){
						 	alert("试题分数不能为负数");
						 	return false;
						 }
						 if(duoxuan[i].value>40){
						 	alert("试题分数不能大于40");
						 	return false;
						 }
						 if(!patrn.exec(duoxuan[i].value)){
						 	alert("试题分数必须是正整数");
						 	return false;
						 }
						zongfen=parseInt(zongfen)+parseInt(duoxuanfen);
						
						}else
						{	
							alert("请填写试题Id或试题分数");
							return false;
						}
				 }
			 }
			 
			//判断题
			var panduan =  document.getElementsByName("panduan_qstScore");
			var panduan_qstId =  document.getElementsByName("panduan_qstId");
			if(panduan!=null){
				 for(var i=0;i<panduan.length;i++){
					if(panduan[i].value!="试题分数"&&panduan[i].value!=""&&panduan_qstId[i].value!="试题Id"&&panduan_qstId[i].value!=""){
						flag = $(panduan_qstId[i]).attr("flag");
						if(flag!="true"){
							return false;
						}
						if(panduan[i].value<0){
					 		alert("试题分数不能为负数");
					 		return false;
					 	}
					 	if(panduan[i].value>40){
					 		alert("试题分数不能大于40");
					 		return false;
					 	}
					 	if(!patrn.exec(panduan[i].value)){
					 		alert("试题分数必须是正整数");
					 		return false;
					 	}
						zongfen=parseInt(zongfen)+parseInt(panduan[i].value);
					}else{	
						alert("请填写试题Id或试题分数");
						return false;
					}
				 }
			
			}
			 
			 //材料分析题
			 var cailiao =  document.getElementsByName("cailiao_qstScore");
			 var cailiao_qstId =  document.getElementsByName("cailiao_qstId");
			 if(cailiao!=null){
				 for(var i=0;i<cailiao.length;i++){
					if(cailiao[i].value!="试题分数"&&cailiao[i].value!=""&&cailiao_qstId[i].value!="试题Id"&&cailiao_qstId[i].value!=""){
						if(cailiao[i].value<0){
						 	alert("试题分数不能为负数");
						 	return false;
						 }
						 if(cailiao[i].value>40){
						 	alert("试题分数不能大于40");
						 	return false;
						 }
						 if(!patrn.exec(cailiao[i].value)){
						 	alert("试题分数必须是正整数");
						 	return false;
						 }
						zongfen=parseInt(zongfen)+parseInt(cailiao[i].value);
					}else{	
						alert("请填写试题Id或试题分数");
						return false;
					}
					
					 //子试题添加分数
					var cailiaozi =  document.getElementsByName("cailiao_qstScore"+cailiao_qstId[i].value);
			   		var cailiao_qstIdzi =  document.getElementsByName("cailiao_qstId"+cailiao_qstId[i].value);
					  	if(cailiaozi!=null){
							 for(var k=0;k<cailiaozi.length;k++){
								if(cailiaozi[k].value!="试题子题分数"&&cailiaozi[k].value!=""&&cailiao_qstIdzi[k].value!="试题子题Id"&&cailiao_qstIdzi[k].value!=""){
									if(cailiaozi[k].value<0){
										alert("试题子题分数不能为负数");
									 	return false;
									 }
									 if(cailiaozi[k].value>40){
									 	alert("试题子题分数不能大于40");
									 	return false;
									 }
									 if(!patrn.exec(cailiaozi[k].value)){
									 	alert("试题子题分数必须是正整数");
									 	return false;
									 }
									zongfen=parseInt(zongfen)+parseInt(cailiaozi[k].value);
								}else{	
									alert("请填写试题Id或试题分数");
									return false;
								}
							}
						
						}
						//结束
				 }
			 }
			 
			 
			//图表题
			var tubiao =  document.getElementsByName("tubiao_qstScore");
			var tubiao_qstId =  document.getElementsByName("tubiao_qstId");
			if(tubiao!=null){
				 for(var i=0;i<tubiao.length;i++){
					if(tubiao[i].value!="试题分数"&&tubiao[i].value!=""&&tubiao_qstId[i].value!="试题Id"&&tubiao_qstId[i].value!=""){
						flag = $(tubiao_qstId[i]).attr("flag");
						if(flag!="true"){
							return false;
						}
						if(tubiao[i].value<0){
					 		alert("试题分数不能为负数");
					 		return false;
					 	}
					 	if(tubiao[i].value>40){
					 		alert("试题分数不能大于40");
					 		return false;
					 	}
					 	if(!patrn.exec(tubiao[i].value)){
					 		alert("试题分数必须是正整数");
					 		return false;
					 	}
						zongfen=parseInt(zongfen)+parseInt(tubiao[i].value);
					}else{	
						alert("请填写试题Id或试题分数");
						return false;
					}
				 }
			 }
			 
			 //主观题
			 var zhuguan =  document.getElementsByName("zhuguan_qstScore");
			 var zhuguan_qstId =  document.getElementsByName("zhuguan_qstId");
			 if(zhuguan!=null){
				 for(var i=0;i<zhuguan.length;i++){
					if(zhuguan[i].value!="试题分数"&&zhuguan[i].value!=""&zhuguan_qstId[i].value!="试题Id"&&zhuguan_qstId[i].value!=""){
						flag = $(zhuguan_qstId[i]).attr("flag");
						if(flag!="true"){
							return false;
						}
						if(zhuguan[i].value<0){
						 	alert("试题分数不能为负数");
						 	return false;
						 }
						 if(zhuguan[i].value>40){
						 	alert("试题分数不能大于40");
						 	return false;
						 }
						 if(!patrn.exec(zhuguan[i].value)){
						 	alert("试题分数必须是正整数");
						 	return false;
						 }
						zongfen=parseInt(zongfen)+parseInt(zhuguan[i].value);
					}else{	
						alert("请填写试题Id或试题分数");
						return false;
					}
				 }
			 }
		    
		    if(examtotel!=zongfen){
			    var level=document.getElementById("fabu").value;
			    document.getElementById("totelscore").value=examtotel;
				var r=confirm("当前新增试题分为"+zongfen+"试卷已有题目分数为"+examtotel+"分，是否修改试卷");
				if(r==true){
				   if(level==1){
				   		var h=confirm("是否发布试卷");
			  			if(h){
			  				document.getElementById("fabu").value=2;
			  			}
				   }
				   	alert("修改成功!");
				   	document.forms["duanxuanfrom"].submit();
				}else{
					alert("取消成功");
				}
			  }else{
			  	 if(level==1){
				   		var h=confirm("是否发布试卷");
			  			if(h){
			  				document.getElementById("fabu").value=2;
			  			}
				   }
			  	document.forms["duanxuanfrom"].submit();
			  }
			}
		</script>
	</head>
	<body>

	<form name="duanxuanfrom" action="<%=contextPath %>/exam/qstmid!EcxamQstAdd.action?status=2" method="post">
	<table width="100%" height="173" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
	<input  type="hidden" id="epId" name="epId" value="<s:property  value="exam.epId"/>"/>
	<input type="hidden"  id="totelscore" name="totelscore" value='<s:property value="exam.epTotelScore" />'/>
	<input type="hidden"  id="zongfen" value='0'/>
	<input type="hidden" id="fabu" name="fabu" value='<s:property value="exam.level" />'/>
	<s:if  test="-1 != exam.examqsttype.indexOf('1')">

	
      <tr >
        <td width="13%" align="center"> <input type="checkbox"  class="button" value="1" name="qstType" checked/>单选题</td>
		<td width="64%" align="left">
					<table width="100%" border="0" id="table2">
							<s:iterator value="danxuan" id="danxuan" status="stuts">
							<tr>  
							<td><s:property value="#stuts.index+1" />、</td>
							<td><s:property value="#danxuan.qstId" />
							<input type="hidden"  name="danxuanqstId" value='<s:property value="#danxuan.qstId" />'/>
							</td>
						
							<td><s:property value="#danxuan.qstScore" /></td>
							<td>
							<div style='font-size:12px; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' ><s:property value="#danxuan.qstContent" default="-" escape="false"  /></div>
							 					</td>
							<td><a href='#' onclick='delrowdanxuanlist(this,<s:property value="#danxuan.eqId" />,<s:property value="#danxuan.qstScore" />)'>删除</a></td>
							</tr>
							</s:iterator>
					</table>		</td>
        <td width="23%" align="left"><table width="100%" border="0">
          <tr>
            <td align="right">&nbsp;</td>
            <td align="center"><input type="button"  value="批量导入"  onclick="showwin(1)"/></td>
          </tr>
          <tr>
            <td align="right"><div id="u240_rtf">＋<a href="#" onclick="addrow()">增加试题</a></div></td>
            <td align="center"></td>
          </tr>
        </table></td>
      </tr>
	  </s:if>
	  
	
	  
	    <s:if test="-1 != exam.examqsttype.indexOf('2')">
	   <tr>
        <td width="13%" align="center"><input type="checkbox"  class="button" value="2" name="qstType" checked/>多选题</td>
       <td width="64%" align="left" bordercolor="#999999">
					<table width="100%" border="0" id="tableduoxuan">
					
					<s:iterator value="duoxuan" id="duoxuan" status="stuts">
							<tr>  
							<td><s:property value="#stuts.index+1" />、</td>
							<td><s:property value="#duoxuan.qstId" />
							<input type="hidden"  name="duoxuanqstId" value='<s:property value="#duoxuan.qstId" />'/>
							</td>
							<td><s:property value="#duoxuan.qstScore" /></td>
							<td>
							 <div style='font-size:12px; text-align:center; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' ><s:property value="#duoxuan.qstContent" default="-" escape="false"  /></div>
							 							</td>
							<td><a href='#' onclick='delrowduoxuanlist(this,<s:property value="#duoxuan.eqId" />,<s:property value="#duoxuan.qstScore" />)'>删除</a></td>
							</tr>
							</s:iterator>
					</table>		</td>
        <td width="23%" align="left" bordercolor="#999999"><table width="100%" border="0">
          <tr>
            <td align="right">&nbsp;</td>
            <td align="center"><input type="button"  value="批量导入"  onclick="showwin(2)"/></td>
          </tr>
          <tr>
            <td align="right"><div id="u240_rtf" ><a href="#" onclick="addrowduoxuan()">＋增加试题</a></div></td>
            <td align="center"></td>
          </tr>
        </table></td>
      </tr>
	  </s:if>
	  
	    <s:if test="-1 != exam.examqsttype.indexOf('3')">
	   <tr>
        <td width="13%" align="center"><input type="checkbox"  class="button" value="3" name="qstType" checked/>判断题</td>
        <td width="64%" align="left">
					<table width="100%" border="0" id="tablepanduan">
					
					<s:iterator value="panduan" id="panduan" status="stuts">
							<tr>  
							<td><s:property value="#stuts.index+1" />、</td>
							<td><s:property value="#panduan.qstId" />
							<input type="hidden"  name="panduanqstId" value='<s:property value="#panduan.qstId" />'/>
							</td>
							<td><s:property value="#panduan.qstScore" /></td>
							<td>
							 <div style='font-size:12px; text-align:center; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' ><s:property value="#panduan.qstContent" default="-" escape="false"  /></div>
							 							</td>
							<td><a href='#' onclick='delrowpanduanlist(this,<s:property value="#panduan.eqId" />,<s:property value="#panduan.qstScore" />)'>删除</a></td>
							</tr>
							</s:iterator>
					</table>		</td>
        <td width="23%" align="left"><table width="100%" border="0">
          <tr>
            <td align="right">&nbsp;</td>
            <td align="center"><input type="button"  value="批量导入" onclick="showwin(3)"/></td>
          </tr>
          <tr>
            <td align="right"><div id="u240_rtf"><a href="#" onclick="addrowpanduan()">＋增加试题</a></div></td>
            <td align="center">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
	  </s:if>
	  
	    <s:if test="-1 != exam.examqsttype.indexOf('4')">
	   <tr>
        <td width="13%" align="center"><input type="checkbox"  class="button" value="4" name="qstType" checked/>材料分析题</td>
       <td width="64%" align="left">
		<table width="100%" border="0" id="tablecailiao">
		
			<s:iterator value="cailiao" id="cailiao" status="stuts">
					
			
						<s:if test="#cailiao.qstScore==0">
							<tr>  
							<td valign="top"><s:property value="#stuts.index+1" />、</td>
							<td valign="top" ><s:property value="#cailiao.qstId" />
							<input type="hidden"  name="cailiaoqstId" value='<s:property value="#cailiao.qstId" />'/>
							</td>
							<td valign="top"><s:property value="#cailiao.qstScore" />														</td>
							<td valign="top">
							 <div style='font-size:12px; text-align:center; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' ><s:property value="#cailiao.qstContent" default="-" escape="false"  /></div>
							 							</td>
							<td valign="top"><a href='#' onclick='delrowcailiaolist(this,<s:property value="#cailiao.eqId" />,<s:property value="#cailiao.qstScore" />)'>删除</a>主题</td>
							</tr>
							</s:if>
							
							<s:if test="#cailiao.qstScore!=0">
							<tr>  
							<td valign="top"><s:property value="#stuts.index+1" />、</td>
							<td valign="top" ><s:property value="#cailiao.qstId" />
							<input type="hidden"  name="cailiaoqstId" value='<s:property value="#cailiao.qstId" />'/>
							</td>
							<td valign="top"><s:property value="#cailiao.qstScore" />														</td>
							<td valign="top">
							<div style='font-size:12px; text-align:center; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' ><s:property value="#cailiao.qstContent" default="-" escape="false"  /></div>
							 							</td>
							<td valign="top"><a href='#' onclick='delrowcailiaolist(this,<s:property value="#cailiao.eqId" />,<s:property value="#cailiao.qstScore" />)'>删除</a>子题</td>
							</tr>
							</s:if>
							</s:iterator>
		</table>		</td>
        <td width="23%" align="left"><table width="100%" border="0">
          <tr>
            <td align="right">&nbsp;</td>
            <td align="center"><input type="button"  value="批量导入" onclick="showwin(4)" /></td>
          </tr>
          <tr>
            <td align="right"><div id="u240_rtf"></div></td>
            <td align="center">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
	  </s:if>
	  
	   <s:if test="-1 != exam.examqsttype.indexOf('5')">
	   <tr>
        <td width="13%" align="center"><input type="checkbox"  class="button" value="5" name="qstType" checked/>
        图表题</td>
        <td width="64%" align="left">
		<table width="100%" border="0" id="tabletubiao">
		
			<s:iterator value="tubiao" id="tubiao" status="stuts">
							<tr>  
							<td><s:property value="#stuts.index+1" />、</td>
							<td><s:property value="#tubiao.qstId" />
							<input type="hidden"  name="tubiaoqstId" value='<s:property value="#tubiao.qstId" />'/>
							</td>
							<td><s:property value="#tubiao.qstScore" /></td>
							<td>
							 <div style='font-size:12px; text-align:center; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' ><s:property value="#tubiao.qstContent" default="-" escape="false"  /></div>
							 							</td>
							<td><a href='#' onclick='delrowtubiaolist(this,<s:property value="#tubiao.eqId" />,<s:property value="#tubiao.qstScore" />)'>删除</a></td>
							</tr>
							</s:iterator>
		</table>		</td>
        <td width="23%" align="left"><table width="100%" border="0">
          <tr>
            <td align="right">&nbsp;</td>
            <td align="center"><input type="button"  value="批量导入" onclick="showwin(5)"/></td>
          </tr>
          <tr>
            <td align="right"><div id="u240_rtf"><a href="#" onclick="addrowtubiao()">＋增加试题</a></div></td>
            <td align="center">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
	  </s:if>
	  
	   <s:if test="-1 != exam.examqsttype.indexOf('6')">
	   <tr>
        <td width="13%" align="center"><input type="checkbox"  class="button" value="6" name="qstType" checked/>添加主观题</td>
        <td width="64%" align="left">
					<table width="100%" border="0" id="tablezhuguan">
					
						<s:iterator value="zhuguan" id="zhuguan" status="stuts">
							<tr>  
							<td><s:property value="#stuts.index+1" />、</td>
							<td><s:property value="#zhuguan.qstId" />
							<input type="hidden"  name="zhuguanqstId" value='<s:property value="#zhuguan.qstId" />'/>
							</td>
							<td><s:property value="#zhuguan.qstScore" /></td>
							<td>
							 <div style='font-size:12px; text-align:center; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' ><s:property value="#zhuguan.qstContent" default="-" escape="false"  /></div>
														</td>
							<td><a href='#' onclick='delrowzhuguanlist(this,<s:property value="#zhuguan.eqId" />,<s:property value="#zhuguan.qstScore" />)'>删除</a></td>
							</tr>
							</s:iterator>
					</table></td>
        <td width="23%" align="left"><table width="100%" border="0">
          <tr>
            <td align="right">&nbsp;</td>
            <td align="center"><input type="button"  value="批量导入" onclick="showwin(6)" /></td>
          </tr>
          <tr>
            <td align="right"><div id="u240_rtf"><a href="#" onclick="addrowzhuguan()">＋增加试题</a></div></td>
            <td align="center">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
	    </s:if>
	   <tr>
	     <td align="center">&nbsp;</td>
	     <td align="left"><input name="button" type="button" onclick="show_confirm()"  value="保存"/></td>
	     <td align="left">&nbsp;</td>
      </tr>
	  
    </table>
	 </form>
	 
	</body>
</html>
<script language="JavaScript" type="text/javascript"> 
<!--单选添加-->
	function addrow() { 
			var tab=document.getElementById("table2");
			var _tr=tab.insertRow(tab.rows.length); 
			var idName="qstcontentId"+tab.rows.length+1;
			var idname="qstcontent"+tab.rows.length+1;
			var _td=_tr.insertCell(0); 
			_td.innerHTML=tab.rows.length+"、";
			
			var _td=_tr.insertCell(1); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML=" <input  type='text' id="+idName+"  size='14' value='试题Id' flag='false' name='qstId' onblur=setContent('"+idname+"',this.value,'"+idName+"',1) />"; 
			
			var _td=_tr.insertCell(2); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			
			_td.innerHTML=" <input  type='text'  size='14' value='1' name='qstScore'/>"; 
			
			
			var _td=_tr.insertCell(3); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			var t=document.createElement("input"); 
			t.id=idname;
			t.size=42; 
			t.value="试题内容"; 
			t.name="qstcontent";
			t.disabled="disabled";
			_td.appendChild(t); 
			
			
			var _td=_tr.insertCell(4); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML="<a href='#' onclick='delrow(this)'>删除</a>";
	
	} 
	
	function setContent(idname,qstId,idName,qstType){
		if(isNaN(qstId)){
			alert("试题ID只能为数字！");
			return ;
		}
		if(qstId.toString()==""){
 			alert('试题ID不能为空！');
 			return;
		}
		
		$.ajax({
					url : "<%=contextPath%>/exam/qst!getQstContent.action",  
					data : {"qstId" : qstId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据  
					success:function(result) {
						var qst = result.returnMessage;
						var qsttype=result.jumpUrl;
						if(qsttype==qstType){
							if(qst=="false"){
								$('#'+idName).val("");
								alert('试题不存在');
							}else{
								$('#'+idname).val(qst);
								$("#"+idName).attr("flag","true");
								$("#"+idName).attr("readonly","readonly");
							}
						}else{
							$('#'+idName).val("");
							alert("类型不匹配");
						}
					},
					error: function(){ 
						$('#'+idName).val("");  
						alert('试题不存在');
					}
		});
	}
	
	<!--单选删除-->
	function delrow(no) { 
				var tab=document.getElementById("table2");
				var member=no.parentNode.parentNode.rowIndex ;
				var nownum=0;
	
				
				if(tab.rows.length>0){
					
					tab.deleteRow(member);
				}else {
					 alert("不能再删了");
				}
				for(j = 0; j < tab.rows.length; j ++){
				
					nownum ++;
					
					tab.rows[j].cells[0].innerHTML = nownum+"、";
				}
	
	} 
	
	<!--多选添加-->
	function addrowduoxuan() { 
			var tab=document.getElementById("tableduoxuan");
			var _tr=tab.insertRow(tab.rows.length); 
			var idName="duoxuan_qstcontentId"+tab.rows.length+1;
			var idname="duoxuan_qstcontent"+tab.rows.length+1;
			var _td=_tr.insertCell(0); 
			_td.innerHTML=tab.rows.length+"、";
			
			var _td=_tr.insertCell(1); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML=" <input  type='text' id="+idName+" flag='false' size='14' value='试题Id' name='duoxuan_qstId' onblur=setContent('"+idname+"',this.value,'"+idName+"',2) />"; 
			
			var _td=_tr.insertCell(2); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			
			_td.innerHTML=" <input  type='text'  size='14' value='1' name='duoxuan_qstScore'/>"; 
			
			
			var _td=_tr.insertCell(3); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			var t=document.createElement("input"); 
			t.id=idname;
			t.size=42; 
			t.value="试题内容"; 
			t.name="qstcontent";
			t.disabled="disabled";
			_td.appendChild(t); 

			var _td=_tr.insertCell(4); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML="<a href='#' onclick='delrowduoxuan(this)'>删除</a>";
	
	} 
	<!--多选删除-->
	function delrowduoxuan(no) { 
				var tab=document.getElementById("tableduoxuan");
				var member=no.parentNode.parentNode.rowIndex ;
				var nownum=0;
	
				
				if(tab.rows.length>0){
					
					tab.deleteRow(member);
				}else {
					 alert("不能再删了");
				}
				for(j = 0; j < tab.rows.length; j ++){
				
					nownum ++;
					
					tab.rows[j].cells[0].innerHTML = nownum+"、";
				}
	
	}
	
	<!--判断添加-->
	function addrowpanduan() { 
			var tab=document.getElementById("tablepanduan");
			var _tr=tab.insertRow(tab.rows.length); 
			var idName="panduan_qstcontentId"+tab.rows.length+1;
			var idname="panduan_qstcontent"+tab.rows.length+1;
			var _td=_tr.insertCell(0); 
			_td.innerHTML=tab.rows.length+"、";
			
			var _td=_tr.insertCell(1); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML=" <input  type='text' id="+idName+" flag='false' size='14' value='试题Id' name='panduan_qstId' onblur=setContent('"+idname+"',this.value,'"+idName+"',3) />"; 
			
			var _td=_tr.insertCell(2); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			
			_td.innerHTML=" <input  type='text'  size='14' value='1' name='panduan_qstScore'/>"; 
			
			
			var _td=_tr.insertCell(3); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			var t=document.createElement("input"); 
			t.id=idname;
			t.size=42; 
			t.value="试题内容"; 
			t.name="qstcontent";
			t.disabled="disabled";
			_td.appendChild(t); 

			var _td=_tr.insertCell(4); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML="<a href='#' onclick='delrowpanduan(this)'>删除</a>";
			
	} 
	<!--判断删除-->
	function delrowpanduan(no) { 
				var tab=document.getElementById("tablepanduan");
				var member=no.parentNode.parentNode.rowIndex ;
				var nownum=0;
	
				
				if(tab.rows.length>0){
					
					tab.deleteRow(member);
				}else {
					 alert("不能再删了");
				}
				for(j = 0; j < tab.rows.length; j ++){
				
					nownum ++;
					
					tab.rows[j].cells[0].innerHTML = nownum+"、";
				}
	
	}
	
	<!--cailiao删除-->
	function delrowcailiao(no) { 
				var tab=document.getElementById("tablecailiao");
				var member=no.parentNode.parentNode.rowIndex ;
				var nownum=0;
	
				
				if(tab.rows.length>0){
					
					tab.deleteRow(member);
				}else {
					 alert("不能再删了");
				}
				for(j = 0; j < tab.rows.length; j ++){
				
					nownum ++;
					
					tab.rows[j].cells[0].innerHTML = nownum+"、";
				}
	
	}
	
	
	<!--tubiao添加-->
	function addrowtubiao() { 
			var tab=document.getElementById("tabletubiao");
			var _tr=tab.insertRow(tab.rows.length); 
			var idName="tubiao_qstcontentId"+tab.rows.length+1;
			var idname="tubiao_qstcontent"+tab.rows.length+1;
			var _td=_tr.insertCell(0); 
			_td.innerHTML=tab.rows.length+"、";
			
			var _td=_tr.insertCell(1); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML=" <input  type='text' id="+idName+" flag='false' size='14' value='试题Id' name='tubiao_qstId' onblur=setContent('"+idname+"',this.value,'"+idName+"',5) />"; 
			
			var _td=_tr.insertCell(2); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			
			_td.innerHTML=" <input  type='text'  size='14' value='1' name='tubiao_qstScore'/>"; 
			
			
			var _td=_tr.insertCell(3); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			var t=document.createElement("input"); 
			t.id=idname;
			t.size=42; 
			t.value="试题内容"; 
			t.name="qstcontent";
			t.disabled="disabled";
			_td.appendChild(t); 

			var _td=_tr.insertCell(4); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML="<a href='#' onclick='delrowtubiao(this)'>删除</a>";
	
	} 
	<!--tubiao删除-->
	function delrowtubiao(no) { 
				var tab=document.getElementById("tabletubiao");
				var member=no.parentNode.parentNode.rowIndex ;
				var nownum=0;
	
				
				if(tab.rows.length>0){
					
					tab.deleteRow(member);
				}else {
					 alert("不能再删了");
				}
				for(j = 0; j < tab.rows.length; j ++){
				
					nownum ++;
					
					tab.rows[j].cells[0].innerHTML = nownum+"、";
				}
	
	}
	
	
	
	<!--主观添加-->
	function addrowzhuguan() { 
			var tab=document.getElementById("tablezhuguan");
			var _tr=tab.insertRow(tab.rows.length); 
			var idName="zhuguan_qstcontentId"+tab.rows.length+1;
			var idname="zhuguan_qstcontent"+tab.rows.length+1;
			var _td=_tr.insertCell(0); 
			_td.innerHTML=tab.rows.length+"、";
			
			var _td=_tr.insertCell(1); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML=" <input  type='text' id="+idName+" flag='false' size='14' value='试题Id' name='zhuguan_qstId' onblur=setContent('"+idname+"',this.value,'"+idName+"',6) />"; 
			
			var _td=_tr.insertCell(2); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			
			_td.innerHTML=" <input  type='text'  size='14' value='1' name='zhuguan_qstScore'/>"; 
			
			
			var _td=_tr.insertCell(3); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			var t=document.createElement("input"); 
			t.id=idname;
			t.size=42; 
			t.value="试题内容"; 
			t.name="qstcontent";
			t.disabled="disabled";
			_td.appendChild(t); 

			var _td=_tr.insertCell(4); 
			//var _tn=document.createTextNode(i.toString()+j.toString()); 
			_td.innerHTML="<a href='#' onclick='delrowzhuguan(this)'>删除</a>";
	
	} 
	<!--主观删除-->
	function delrowzhuguan(no) { 
				var tab=document.getElementById("tablezhuguan");
				var member=no.parentNode.parentNode.rowIndex ;
				var nownum=0;
	
				if(tab.rows.length>0){
					
					tab.deleteRow(member);
				}else {
					 alert("不能再删了");
				}
				for(j = 0; j < tab.rows.length; j ++){
				
					nownum ++;
					
					tab.rows[j].cells[0].innerHTML = nownum+"、";
				}
	
	}
</script> 