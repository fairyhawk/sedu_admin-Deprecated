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
		var importURL = '<%=importURL%>';
			$().ready(function() {
			// 初始化 Kindeditor
    	        KE.show({
    	                    id : 'txt_topic',
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

			
			function addZhuRow()
			{
			    if (IsFF()==true)
			    {
			        addZhuRowFF();
			    }
			    if (IsIE()==true)
			    {
			        addZhuRowIE();
			    }
			}
			
			function createSelect(objTag, objName,colNum)
			{
			    var td = null;
			    td  = document.createElement('td');
			    if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			   	td.innerHTML="<select name='"+ objName +"' > <option value='1'>简单</option><option value='2'>普通</option><option value='3'>困难</option></select>";
			   	td.className = "lists_tleft";
			    return td;
			}
			
			//** 知识库专业/
			
			function createSelectshuxing(colNum)
			{
			    
			    var td = null;
			    td = document.createElement('td');
				if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    var length=document.exampaperaddform.zzhuyeId.length;
			    str="<select id='zzhuanyeid"+colNum+"'  onchange='onchangeProfessionaljs(this.value,"+colNum+")'>";
			    str1="</select>"
			    for(var i=0; i<length; i++)
				{
					str+="<option value='"+document.exampaperaddform.zzhuyeId[i].value+"'>"+document.exampaperaddform.zzhuyeId[i].text+"</option>"
				}
			    
			   	td.innerHTML=str+str1;
			   	td.className = "lists_tleft";
			    return td;
			}
			
			/** 知识库的课程 */
			function createSelectkecheng(colNum)
			{
			    var td = null;
			    td = document.createElement('td');
				if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			   	td.innerHTML="<select  id='kecheng"+colNum+"'  onchange='onSelectCoursejs(this.value,"+colNum+")'> <option value='-1'>请选择课程</option></select>";
			   	td.className = "lists_tleft";
			    return td;
			}
			/** 知识点*/
			function createSelectzhishidian(colNum)
			{
			    var td = null;
			    td = document.createElement('td');
				if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			   	td.innerHTML="<input type='text' name='zkid' id='zhishidian"+colNum+"' onclick=showPkpointjs("+colNum+"); />";
			   	td.className = "lists_tleft";
			    return td;
			}
			
			
				function createdivzhishidian(colNum)
			{
			    var td = null;
			    td  = document.createElement('td');
			    if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			
			    var qplist="qpointList"+colNum;
			   	td.innerHTML="<div id='"+qplist+"' style='position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;left:260px;'> </div>";
			   	td.className = "lists_tleft";
			    return td;
			}
			
			
			
			function createTd(objTag, objType, objName, objClass, colNum, no)
			{
			    var td = null;
			    td  = document.createElement('td');
			    
			    if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			   	td.innerHTML="<input type='text' name='"+objName+"' id='"+objName+"' class={required:true,minlength:1}/>";
			   	td.className = "lists_tleft";
			    return td;
			}
			
			function createFileTd(objTag, objType, objName, objClass, colNum, no)
			{
			    var td = null;
			    td  = document.createElement('td');
			    
			    if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    //alert("<input type='file' name='"+objName+"' id='"+objName+"' />");
			   	td.innerHTML="<input type='file' name='"+objName+"' id='"+objName+"' />";
			   	td.className = "lists_tleft";
			    return td;
			}
			
			<!--单选-->
			function createRadio(objTag, objType, objName, colNum)
			{// input   类型   name  css
			    var td = null;
			    td  = document.createElement('td');
			    if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    
			    td.innerHTML="<input type='radio' name='"+objName+"' value='A' checked=true />A<input type='radio' name='"+objName+"' value='B'/>B<input type='radio' name='"+objName+"' value='C'/>C<input type='radio' name='"+objName+"' value='D'/>D<input type='radio' name='"+objName+"' value='E'/>E<input type='radio' name='"+objName+"' value='F'/>F<input type='radio' name='"+objName+"' value='G'/>G";
			    td.className = "lists_tleft"; 
			    return td;
			}
		<!--创建多选-->	
			
			function createCheckbox(objTag, objType, objName, colNum)
			{// input   类型   name  css
			    var td = null;
			    td  = document.createElement('td');
			    if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    
			    td.innerHTML="<input type='checkbox' name='"+objName+"' value='A' checked=true />A<input type='checkbox' name='"+objName+"' value='B'/>B<input type='checkbox' name='"+objName+"' value='C'/>C<input type='checkbox' name='"+objName+"' value='D'/>D<input type='checkbox' name='"+objName+"' value='E'/>E<input type='checkbox' name='"+objName+"' value='F'/>F<input type='checkbox' name='"+objName+"' value='G'/>G";
			    td.className = "lists_tleft"; 
			    return td;
			}
			
				<!--创建判断-->	
				function createPanduan(objTag, objType, objName, colNum)
			{// input   类型   name  css
			    var td = null;
			    td  = document.createElement('td');
			    if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    
			    td.innerHTML="<input type='radio' name='"+objName+"' value='A' checked=true />A<input type='radio' name='"+objName+"' value='B'/>B";
			    td.className = "lists_tleft"; 
			    return td;
			}
			
			function createTextarea(objTag, objName,rowsNum,colsNum, colNum)
			{// input   类型   name  css
			    var td = null;
			    td  = document.createElement('td');
		
			    if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    
			    td.innerHTML="<textarea  name='"+objName+"' cols='"+colsNum+"' rows='"+rowsNum+"'/>";
			    td.className = "lists_tleft";
			    return td;
			}
			
			//fck
			function createFCK(objName)
			{
				var oFCKeditor = new FCKeditor(objName) ;
				oFCKeditor.BasePath = importURL+"/fckeditor/" ;
				oFCKeditor.ReplaceTextarea() ;
			
			}
			
			
			function createHidden(name,sty,no,colNum){//删除与序号
				var td = null;
				td = document.createElement('td');
				if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    
			    td.innerHTML="<input type='hidden' name='"+name+"' value='"+no+"'/>" + "<input type='button' value='删除' onclick='deleteRow(this)'/>";
			    td.className = "lists_tleft";
				return td;
			}
			
			function createHidden2(name,sty,no,colNum){//添加试题type
				var td = null;
				td = document.createElement('td');
				if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			 
			    td.innerHTML="<input type='hidden' name='"+name+"' value='"+no+"'/>";
			    td.className = "lists_tleft";
				return td;
			}
			
			function createFTd(name,colNum){//创建文字
				var td = null;
				td = document.createElement('td');
				if (colNum!=null && colNum>1)
			    {
			        td.colSpan   = colNum;
			    }
			    td.innerHTML=name;
			    
				return td;
			}
			
			function createTR()
			{
			    var tr = document.createElement('tr');
			    var no = document.getElementById('hidTotal').value;
			    no++;
			    document.getElementById('hidTotal').value = no;
			    //alert(document.getElementById('hidTotal').value);
			    tr.appendChild(createFTd("试题内容"));
			    tr.appendChild(createTextarea("textarea", "text", "addr_start" + no, "jiaotong1"));
			  	
			    return tr;
			}
			
		function deleteRow(button){
			var xuhao = document.getElementById('xuhao').value;
		    xuhao--;
		    document.getElementById('xuhao').value = xuhao;
			//var rownum = button.parentNode.parentNode.cells[0].innerHTML;
			
			var rownum1 = button.parentNode.parentNode.rowIndex;//当前行数
			var tb ;
			if (IsFF()==true)
		    {
		        var tb = document.getElementById('tblList');
		    }
		    if (IsIE()==true)
		    {
		        var tb = document.getElementById('tagTb');
		    }
			
			var nownum = (rownum1 - 26)/17;//当前行号
			var yunum = (tb.rows.length-rownum1)/17;//余下为多少
			var tempindex = rownum1;
			for(j = 0; j < yunum -1; j ++){
				nownum ++;
				tempindex = tempindex + 17;
				tb.rows[tempindex].cells[0].innerHTML = "试题序号:" + nownum;
			}
			for(i = 0; i < 17; i ++){//删除指定行
				tb.deleteRow(rownum1);
			}
		}
			
		
		function addZhuRow(){	
				if (IsFF()==true)
			    {
			        var tb = document.getElementById('tblList');
			    }
			    if (IsIE()==true)
			    {
			        var tb = document.getElementById('tagTb');
			    }
			
			    var no = document.getElementById('hidTotal').value;
			    no++;
			    document.getElementById('hidTotal').value = no;
			    
			    var xuhao = document.getElementById('xuhao').value;
			    xuhao++;
			    document.getElementById('xuhao').value = xuhao;
			    
			    var row = document.createElement('tr');
			    row.appendChild(createFTd("试题序号：" + xuhao,1));
			    row.appendChild(createHidden("no", "jiaotong1",no,1));
			    row.appendChild(createHidden2("shititype", "jiaotong1",1,1));
			    tb.appendChild(row);
			    
			    var row10 = document.createElement('tr');
			    row10.appendChild(createFTd("试题难度"));
			    row10.appendChild(createSelect("select","level"+no,2));
			    tb.appendChild(row10);
			    
			    var row1 = document.createElement('tr');
			    row1.appendChild(createFTd("试题分数"));
			    row1.appendChild(createTd("input", "text", "score" + no, "jiaotong1",2));
			    tb.appendChild(row1);
			    
				var row5 = document.createElement('tr');
			    row5.appendChild(createFTd("知识库里所属专业"));
			    row5.appendChild(createSelectshuxing(no));
			    tb.appendChild(row5);
			    
			    var row6 = document.createElement('tr');
			    row6.appendChild(createFTd("知识库所属科目"));
			    row6.appendChild(createSelectkecheng(no));
			    tb.appendChild(row6);
			    
			    var row7 = document.createElement('tr');
			    row7.appendChild(createFTd("知识库知识点"));
			    row7.appendChild(createSelectzhishidian(no));
			    row7.appendChild(createdivzhishidian(no));
			    row7.appendChild(createHiddenzhishidian(no));
			    tb.appendChild(row7);
			    
			    
			    var row2 = document.createElement('tr');
			    row2.appendChild(createFTd("试题内容"));
			    row2.appendChild(createTextarea("textarea", "shiti" + no, 10,100,2));
			    tb.appendChild(row2);
			    createFCK("shiti" + no);
			    
			     var row4 = document.createElement('tr');	     
			    row4.appendChild(createFTd("上传试题图片"));
			    var td = document.createElement("td");
				td.colSpan = 2;
				
		        var div = document.createElement("div");
				div.className = "uploadPic";
				div.id = "picDiv" + no;
				
				td.innerHTML="<input type='file' name='shitiPic' id='file"+ no +"' onchange='changePic(this)'/>";
				
				var br = document.createElement("br");
				var img = document.createElement("img");
				img.style.display = "none";
				img.id = "picImg" + no;
				row4.appendChild(td);
				
				td.appendChild(br);
				td.appendChild(div);
				td.appendChild(img);
				tb.appendChild(row4);
			    
			    var row3 = document.createElement('tr');
			    row3.appendChild(createFTd("错误评语"));
			    row3.appendChild(createTextarea("textarea", "pingyu" + no, 10,100,2));
			    tb.appendChild(row3);
			    createFCK("pingyu" + no);
			    
			    var row9 = document.createElement('tr');
			    row9.appendChild(createFTd("正确答案"));
			    row9.appendChild(createTextarea("textarea", "right" + no, 10,100,2));
			    tb.appendChild(row9);
			    createFCK("right" + no);
			    
			    var row14 = document.createElement('tr');
			     row14.display="none";
			     row14.border=0;
			     tb.appendChild(row14);
			     
			     var row11 = document.createElement('tr');
			     row11.display="none";
			     row11.border=0;
			     tb.appendChild(row11);
			     
			     var row12 = document.createElement('tr');
			     row12.display="none";
			     row12.border=0;
			     tb.appendChild(row12);
			     
			     var row13 = document.createElement('tr');
			     row13.display="none";
			     row13.border=0;
			     tb.appendChild(row13);
			     
				 var row17 = document.createElement('tr');
			     row17.display="none";
			     row17.border=0;
			     tb.appendChild(row17);
				 
				  var row18 = document.createElement('tr');
			     row18.display="none";
			     row18.border=0;
			     tb.appendChild(row18);
				 
				 var row20 = document.createElement('tr');
			     row20.display="none";
			     row20.border=0;
			     tb.appendChild(row20);
			}
			
		function addRow()
		{	
			if (IsFF()==true)
		    {
		        var tb = document.getElementById('tblList');
		    }
		    if (IsIE()==true)
		    {
		        var tb = document.getElementById('tagTb');
		    }
			var no = document.getElementById('hidTotal').value;
		    no++;
		    document.getElementById('hidTotal').value = no;
		    
		    var xuhao = document.getElementById('xuhao').value;
		    xuhao++;
		    document.getElementById('xuhao').value = xuhao;
		    
		    var row = document.createElement('tr');
		    row.appendChild(createFTd("试题序号：" + xuhao,1));
		    row.appendChild(createHidden("no", "jiaotong1",no,1));
			row.appendChild(createHidden2("shititype", "jiaotong1",0,1));
		    tb.appendChild(row);
		    
		    var row1 = document.createElement('tr');
		    row1.appendChild(createFTd("试题分数"));
		    row1.appendChild(createTd("input", "text", "score" + no, "jiaotong1",2));
		    tb.appendChild(row1);
		    
		    var row10 = document.createElement('tr');
		    row10.appendChild(createFTd("试题难度"));
		    row10.appendChild(createSelect("select","level"+no,2));
		    tb.appendChild(row10);
		    
		    var row11 = document.createElement('tr');
		    row11.appendChild(createFTd("知识库里所属专业"));
		    row11.appendChild(createSelectshuxing(no));
		    tb.appendChild(row11);
		    
		    var row12 = document.createElement('tr');
		    row12.appendChild(createFTd("知识库所属科目"));
		    row12.appendChild(createSelectkecheng(no));
		    tb.appendChild(row12);
		    
		    var row13 = document.createElement('tr');
		    row13.appendChild(createFTd("知识库知识点"));
		    row13.appendChild(createSelectzhishidian(no));
		    row13.appendChild(createdivzhishidian(no));
		    row13.appendChild(createHiddenzhishidian(no));
		    tb.appendChild(row13);
		    
		    
		    
		    var row2 = document.createElement('tr');
		    row2.appendChild(createFTd("试题内容"));
		    row2.appendChild(createTextarea("textarea", "shiti" + no, 10,100,2));
		    tb.appendChild(row2);
		    createFCK("shiti" + no);
		    
		    var row4 = document.createElement('tr');	     
		    row4.appendChild(createFTd("上传试题图片"));
		    var td = document.createElement("td");
			td.colSpan = 2;
			
	        var div = document.createElement("div");
			div.className = "uploadPic";
			div.id = "picDiv" + no;
			
			td.innerHTML="<input type='file' name='shitiPic' id='file"+ no +"' onchange='changePic(this)'/>";
			
			var br = document.createElement("br");
			var img = document.createElement("img");
			img.style.display = "none";
			img.id = "picImg" + no;
			row4.appendChild(td);
			
			td.appendChild(br);
			td.appendChild(div);
			td.appendChild(img);
			tb.appendChild(row4);
		    
		    var row3 = document.createElement('tr');
		    row3.appendChild(createFTd("错误评语"));
		    row3.appendChild(createTextarea("textarea", "pingyu" + no, 10,100,2));
		    tb.appendChild(row3);
		    createFCK("pingyu" + no);
		    
		    var row5 = document.createElement('tr');
		    row5.appendChild(createFTd("选项A"));
		    row5.appendChild(createTextarea("textarea", "A" + no, 10,100,2));
		    tb.appendChild(row5);
		    createFCK("A" + no);
		    
		    var row6 = document.createElement('tr');
		    row6.appendChild(createFTd("选项B"));
		    row6.appendChild(createTextarea("textarea", "B" + no, 10,100,2));
		    tb.appendChild(row6);
		    createFCK("B" + no);
		    
		    var row7 = document.createElement('tr');
		    row7.appendChild(createFTd("选项C"));
		    row7.appendChild(createTextarea("textarea", "C" + no, 10,100,2));
		    tb.appendChild(row7);
		    createFCK("C" + no);
		    
		    var row8 = document.createElement('tr');
		    row8.appendChild(createFTd("选项D"));
		    row8.appendChild(createTextarea("textarea", "D" + no, 10,100,2));
		    tb.appendChild(row8);
		    createFCK("D" + no);
			
			
			 var row15 = document.createElement('tr');
		    row15.appendChild(createFTd("选项E"));
		    row15.appendChild(createTextarea("textarea", "E" + no, 10,100,2));
		    tb.appendChild(row15);
		    createFCK("E" + no);
			
			var row16 = document.createElement('tr');
		    row16.appendChild(createFTd("选项F"));
		    row16.appendChild(createTextarea("textarea", "F" + no, 10,100,2));
		    tb.appendChild(row16);
		    createFCK("F" + no);
			
			var row19 = document.createElement('tr');
		    row19.appendChild(createFTd("选项G"));
		    row19.appendChild(createTextarea("textarea", "G" + no, 10,100,2));
		    tb.appendChild(row19);
		    createFCK("G" + no);
		    
		   	var row9 = document.createElement('tr');
		    row9.appendChild(createFTd("正确答案"));
		    row9.appendChild(createRadio("input", "radio","right"+no,2));
		    tb.appendChild(row9);
		}
			
			
			<!--多选题-->
			
		function addduoRow()
		{	
			if (IsFF()==true)
		    {
		        var tb = document.getElementById('tblList');
		    }
		    if (IsIE()==true)
		    {
		        var tb = document.getElementById('tagTb');
		    }
			var no = document.getElementById('hidTotal').value;
		    no++;
		    document.getElementById('hidTotal').value = no;
		    
		    var xuhao = document.getElementById('xuhao').value;
		    xuhao++;
		    document.getElementById('xuhao').value = xuhao;
		    
		    var row37 = document.createElement('tr');
		    row37.appendChild(createFTd("试题序号：" + xuhao,1));
		    row37.appendChild(createHidden("no", "jiaotong1",no,1));
			row37.appendChild(createHidden2("shititype", "jiaotong1",2,1));
		    tb.appendChild(row37);
		    
		    var row21 = document.createElement('tr');
		    row21.appendChild(createFTd("试题分数"));
		    row21.appendChild(createTd("input", "text", "score" + no, "jiaotong1",2));
		    tb.appendChild(row21);
		    
		    var row22 = document.createElement('tr');
		    row22.appendChild(createFTd("试题难度"));
		    row22.appendChild(createSelect("select","level"+no,2));
		    tb.appendChild(row22);
		    
		    var row23 = document.createElement('tr');
		    row23.appendChild(createFTd("知识库里所属专业"));
		    row23.appendChild(createSelectshuxing(no));
		    tb.appendChild(row23);
		    
		    var row24 = document.createElement('tr');
		    row24.appendChild(createFTd("知识库所属科目"));
		    row24.appendChild(createSelectkecheng(no));
		    tb.appendChild(row24);
		    
		    var row25 = document.createElement('tr');
		    row25.appendChild(createFTd("知识库知识点"));
		    row25.appendChild(createSelectzhishidian(no));
		    row25.appendChild(createdivzhishidian(no));
		    row25.appendChild(createHiddenzhishidian(no));
		    tb.appendChild(row25);
		    
		    
		    
		    var row26 = document.createElement('tr');
		    row26.appendChild(createFTd("试题内容"));
		    row26.appendChild(createTextarea("textarea", "shiti" + no, 10,100,2));
		    tb.appendChild(row26);
		    createFCK("shiti" + no);
		    
		    var row27 = document.createElement('tr');	     
		    row27.appendChild(createFTd("上传试题图片"));
		    var td = document.createElement("td");
			td.colSpan = 2;
			
	        var div = document.createElement("div");
			div.className = "uploadPic";
			div.id = "picDiv" + no;
			
			td.innerHTML="<input type='file' name='shitiPic' id='file"+ no +"' onchange='changePic(this)'/>";
			
			var br = document.createElement("br");
			var img = document.createElement("img");
			img.style.display = "none";
			img.id = "picImg" + no;
			row27.appendChild(td);
			
			td.appendChild(br);
			td.appendChild(div);
			td.appendChild(img);
			tb.appendChild(row27);
		    
		    var row28 = document.createElement('tr');
		    row28.appendChild(createFTd("错误评语"));
		    row28.appendChild(createTextarea("textarea", "pingyu" + no, 10,100,2));
		    tb.appendChild(row28);
		    createFCK("pingyu" + no);
		    
		    var row29 = document.createElement('tr');
		    row29.appendChild(createFTd("选项A"));
		    row29.appendChild(createTextarea("textarea", "A" + no, 10,100,2));
		    tb.appendChild(row29);
		    createFCK("A" + no);
		    
		    var row30 = document.createElement('tr');
		    row30.appendChild(createFTd("选项B"));
		    row30.appendChild(createTextarea("textarea", "B" + no, 10,100,2));
		    tb.appendChild(row30);
		    createFCK("B" + no);
		    
		    var row31 = document.createElement('tr');
		    row31.appendChild(createFTd("选项C"));
		    row31.appendChild(createTextarea("textarea", "C" + no, 10,100,2));
		    tb.appendChild(row31);
		    createFCK("C" + no);
		    
		    var row32 = document.createElement('tr');
		    row32.appendChild(createFTd("选项D"));
		    row32.appendChild(createTextarea("textarea", "D" + no, 10,100,2));
		    tb.appendChild(row32);
		    createFCK("D" + no);
			
			
			 var row33 = document.createElement('tr');
		    row33.appendChild(createFTd("选项E"));
		    row33.appendChild(createTextarea("textarea", "E" + no, 10,100,2));
		    tb.appendChild(row33);
		    createFCK("E" + no);
			
			var row34 = document.createElement('tr');
		    row34.appendChild(createFTd("选项F"));
		    row34.appendChild(createTextarea("textarea", "F" + no, 10,100,2));
		    tb.appendChild(row34);
		    createFCK("F" + no);
			
			var row35 = document.createElement('tr');
		    row35.appendChild(createFTd("选项G"));
		    row35.appendChild(createTextarea("textarea", "G" + no, 10,100,2));
		    tb.appendChild(row35);
		     createFCK("G" + no);
		    
		   	var row36 = document.createElement('tr');
		    row36.appendChild(createFTd("正确答案"));
		    row36.appendChild(createCheckbox("input", "checkbox","right"+no,2));
		    tb.appendChild(row36);
		}
			<!--多选结束-->
			
			
			<!--判读题-->
			
		function addpanduanRow()
		{	
			if (IsFF()==true)
		    {
		        var tb = document.getElementById('tblList');
		    }
		    if (IsIE()==true)
		    {
		        var tb = document.getElementById('tagTb');
		    }
			var no = document.getElementById('hidTotal').value;
		    no++;
		    document.getElementById('hidTotal').value = no;
		    
		    var xuhao = document.getElementById('xuhao').value;
		    xuhao++;
		    document.getElementById('xuhao').value = xuhao;
		    
		    var row = document.createElement('tr');
		    row.appendChild(createFTd("试题序号：" + xuhao,1));
		    row.appendChild(createHidden("no", "jiaotong1",no,1));
			row.appendChild(createHidden2("shititype", "jiaotong1",3,1));
		    tb.appendChild(row);
		    
		    var row40 = document.createElement('tr');
		    row40.appendChild(createFTd("试题分数"));
		    row40.appendChild(createTd("input", "text", "score" + no, "jiaotong1",2));
		    tb.appendChild(row40);
		    
		    var row41 = document.createElement('tr');
		    row41.appendChild(createFTd("试题难度"));
		    row41.appendChild(createSelect("select","level"+no,2));
		    tb.appendChild(row41);
		    
		    var row42 = document.createElement('tr');
		    row42.appendChild(createFTd("知识库里所属专业"));
		    row42.appendChild(createSelectshuxing(no));
		    tb.appendChild(row42);
		    
		    var row43 = document.createElement('tr');
		    row43.appendChild(createFTd("知识库所属科目"));
		    row43.appendChild(createSelectkecheng(no));
		    tb.appendChild(row43);
		    
		    var row44 = document.createElement('tr');
		    row44.appendChild(createFTd("知识库知识点"));
		    row44.appendChild(createSelectzhishidian(no));
		    row44.appendChild(createdivzhishidian(no));
		    row44.appendChild(createHiddenzhishidian(no));
		    tb.appendChild(row44);
		    
		    
		    
		    var row45 = document.createElement('tr');
		    row45.appendChild(createFTd("试题内容"));
		    row45.appendChild(createTextarea("textarea", "shiti" + no, 10,100,2));
		    tb.appendChild(row45);
		    createFCK("shiti" + no);
		    
		    var row46 = document.createElement('tr');	     
		    row46.appendChild(createFTd("上传试题图片"));
		    var td = document.createElement("td");
			td.colSpan = 2;
			
	        var div = document.createElement("div");
			div.className = "uploadPic";
			div.id = "picDiv" + no;
			
			td.innerHTML="<input type='file' name='shitiPic' id='file"+ no +"' onchange='changePic(this)'/>";
			
			var br = document.createElement("br");
			var img = document.createElement("img");
			img.style.display = "none";
			img.id = "picImg" + no;
			row46.appendChild(td);
			
			td.appendChild(br);
			td.appendChild(div);
			td.appendChild(img);
			tb.appendChild(row46);
		    
		    var row47 = document.createElement('tr');
		    row47.appendChild(createFTd("错误评语"));
		    row47.appendChild(createTextarea("textarea", "pingyu" + no, 10,100,2));
		    tb.appendChild(row47);
		    createFCK("pingyu" + no);
		    
		    var row48 = document.createElement('tr');
		    row48.appendChild(createFTd("选项A"));
		    row48.appendChild(createTextarea("textarea", "A" + no, 10,100,2));
		    tb.appendChild(row48);
		    createFCK("A" + no);
		    
		    var row49 = document.createElement('tr');
		    row49.appendChild(createFTd("选项B"));
		    row49.appendChild(createTextarea("textarea", "B" + no, 10,100,2));
		    tb.appendChild(row49);
		    createFCK("B" + no);
		    
		    var row50 = document.createElement('tr');
		    row50.display="none";
		    row50.border=0;
		    tb.appendChild(row50);
			
		    var row51 = document.createElement('tr');
		    row51.display="none";
		    row51.border=0;
		    tb.appendChild(row51);
			
			
			 var row52 = document.createElement('tr');
		    row52.display="none";
		    row52.border=0;
		    tb.appendChild(row52);
			
			var row53 = document.createElement('tr');
		    row53.display="none";
		    row53.border=0;
		    tb.appendChild(row53);
			
			var row54 = document.createElement('tr');
		    row54.display="none";
		    row54.border=0;
		    tb.appendChild(row54);
		    
		   	var row55 = document.createElement('tr');
		    row55.appendChild(createFTd("正确答案"));
		    row55.appendChild(createPanduan("input", "checkbox","right"+no,2));
		    tb.appendChild(row55);
		}
			<!--判断结束-->
			
			
			
		function doSubmit(){//id进行拼接，然后发给action
		
		  	var qstNum =  document.getElementsByName("no");
		  	var qstType =  document.getElementsByName("shititype");
		  	var shitipic = document.getElementsByName("shitiPic");
		  	var  v = "";
		  	var  isexit=0;
		  	var scores = 0;//试卷总分数
		  	for(i = 0; i < qstNum.length ;i++){
		  		
				
		  	    if(v===""){
		  	    	if( shitipic[i].value==null || shitipic[i].value==""){
		  	    		isexit=0;
		  	    	}
		  	    	else
		  	    	{
			  	    	if(shitipic[i].value=="" || shitipic[i].value==null){
						
			  	    		isexit=0;
			  	    	}else{
			  	    		isexit=1;
							
			  	    	}
		  	    		
		  	    	}
		  	    	
		  	     	v= isexit +":" + qstNum[i].value + ":" + qstType[i].value;
			  	 }else{
				  	 if( shitipic[i].value==null || shitipic[i].value==""){
			  	    		isexit=0;
							
			  	    	}
			  	    	else
			  	    	{
				  	   		if( shitipic[i].value ==="" || shitipic[i].value==null){
			  	    			isexit=0;
								
				  	    	}else{
				  	    		isexit=1;
							
				  	    	}
				  	    }
			  	    	v= v+","+ isexit +":" + qstNum[i].value + ":" + qstType[i].value;
			  	 }
			  	 var scoreId = "score" + qstNum[i].value;
			  	 scores = scores +　parseFloat(document.getElementById(scoreId).value); 
		  	}
		  
		  	var totalscores = document.getElementById("exam.epTotelScore").value;
			var totalscores1 = document.getElementById("epTotelScore").value;
		  		scores=parseFloat(totalscores1)+parseFloat(scores);	
		  	if(totalscores != scores){
		  		alert("总分不对");
		  		return false;
		  	}
		  	if(v==null||v=="")
		  	{
		  		alert("请添加试题");
		  		return ;
		  	}
		  	
		  	document.getElementById("qstIds").value = v;
			document.forms[0].submit();
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
			
			
			/**/
			
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
			
			function ceshi(valueid)
			{
				if(valueid==1)
				{
				$("#ceshiwordname").html("<font color='#ff0000'>请选择年份:*</font>");
				
				$("#eptypekeyword").html("<option value='2011'>2011年</option><option value='2010'>2010年</option><option value='2000'>2000年</option><option value='2002'>2002年</option><option value='2003'>2003年</option><option value='2004'>2004年</option>");
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
			
			
			function tijiao()
			{
			var qstNum =  document.getElementsByName("no");
		  	var qstType =  document.getElementsByName("shititype");
		  	var shitipic = document.getElementsByName("shitiPic");
		  	var  v = "";
		  	var  isexit=0;
		  	
		  	var scores = 0;//试卷总分数
		  	for(i = 0; i < qstNum.length ;i++){
				var shu=i+1;
		  	
				
		  	    if(v===""){
		  	    	if(shitipic[0].value==null || shitipic[0].value==""){
		  	    		isexit=0;
		  	    	}
		  	    	else
		  	    	{
			  	    	if(shitipic[0].value=="" || shitipic[0].value==null){
			  	    		isexit=0;
			  	    	}else{
			  	    		isexit=1;
			  	    	}
		  	    		
		  	    	}
		  	    	
		  	     	v= isexit +":" + qstNum[i].value + ":" + qstType[i].value;
			  	 }else{
				  	 if(shitipic[i].value==null || shitipic[i].value==""){
			  	    		isexit=0;
			  	    	}
			  	    	else
			  	    	{
				  	   		if(shitipic[i].value ==="" || shitipic[i].value==null){
			  	    			isexit=0;
				  	    	}else{
				  	    		isexit=1;
				  	    	}
				  	    }
			  	    	v= v+","+ isexit +":" + qstNum[i].value + ":" + qstType[i].value;
			  	 }
			  	 var scoreId = "score" + qstNum[i].value;
			  	 scores = scores +　parseFloat(document.getElementById(scoreId).value); 
		  	}
		  
		  	var totalscores = document.getElementById("exam.epTotelScore").value;
		  	var totalscores1 = document.getElementById("epTotelScore").value;
		  		scores=parseFloat(totalscores1)+parseFloat(scores);	
		  	if(totalscores != scores){
		  		alert("总分不对");
		  		return ;
		  	}
		  	
		  	
		  	if(v==null||v=="")
		  	{
		  		alert("请添加试题");
		  		return ;
		  	}
		  	
		  		document.getElementById("qstIds").value = v;
		  		
		  		
				document.exampaperaddform.action="<%=contextPath%>/exam/exampaper!read.action";
				document.exampaperaddform.target = "_blank";
				document.exampaperaddform.submit();
				document.exampaperaddform.target = "";
				document.exampaperaddform.action="<%=contextPath %>/exam/exampaper!addExam.action";
			}
			
		</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath %>/exam/exampaper!QstUpdate.action" method="post" enctype="multipart/form-data" name="qstform" id="qstform" >
	
	
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
	<input name="qqst.qstId" id="qqst.qstId" type="hidden" value="<s:property value="qqst.qstId"/>" />
		<tr >
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
		  <td><table width="100%" border="0"  cellspacing="0"  cellpadding="0" class="lists">
              <tr>
                <td width="30%" align="center">试题类型</td>
                <td><s:if test="qqst.qstType==0">单选题</s:if><s:if test="qqst.qstType==2">多选题</s:if><s:if test="qqst.qstType==3">判断题</s:if><s:if test="qqst.qstType==4">材料分析题</s:if><s:if test="qqst.qstType==5">图表题</s:if><s:if test="qqst.qstType==6">主观题</s:if></td>
              </tr>
              <tr>
                <td width="30%" align="center">试题分数：<font color="#ff0000">*</font></td>
                <td><input  type="text" name="qqst.score" value="<s:property value="qqst.score" />"/></td>
              </tr>
              <tr>
                <td width="30%" align="center">试题试题难度：<font color="#ff0000">*</font></td>
                <td><label for="select"></label>
                  <select name="qqst.level" id="select">
				  <option value="<s:property  value="qqst.level" />"   selected="selected" > <s:if test="qqst.level==1">简单</s:if><s:if test="qqst.level==2">普通</s:if><s:if test="qqst.level==3">困难</s:if>
                    <option value="1">简单</option>
                    <option value="2">普通</option>
                    <option value="3">困难</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td width="30%" align="center">试题内容：<font color="#ff0000">*</font></td>
                <td> <textarea id="txt_topic" name="qqst.qstContent" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;">
			    		  <s:property value="qqst.qstContent" escape="false"/>
			    	 </textarea>
			    </td>
              </tr>
              <tr>
                <td width="30%" align="center">错误评语：<font color="#ff0000">*</font></td>
                <td><textarea rows="7" cols="70" id="pingyu<s:property value="qqst.qstId"/>" name="pingyu<s:property value="qqst.qstId"/>"><s:property value="qqst.wrongJude"/></textarea>
							<script type="text/javascript">
							var importURL = '..';
							var oFCKeditor = new FCKeditor("pingyu<s:property value="qqst.qstId"/>"); 
							oFCKeditor.BasePath = importURL+"/fckeditor/" ; 
							oFCKeditor.Height = 400; 
							
							oFCKeditor.ReplaceTextarea(); 
							</script></td>
              </tr>
			  <s:iterator value="qqst.options" id="option">
              <s:if test="qqst.qstType!=6">
			  <tr>
                <td width="30%" align="center">选项<s:property value="#option.optOrder"/>：<font color="#ff0000">*</font></td>
                <td><textarea rows="7" cols="70"  id="optioncontent<s:property value="#option.optId"/>" name='optioncontent<s:property value="#option.optId"/>'><s:property value="#option.optContent"/></textarea>	
							<script type="text/javascript">
							var importURL = '..';
							var oFCKeditor = new FCKeditor("optioncontent<s:property value="#option.optId"/>"); 
							oFCKeditor.BasePath = importURL+"/fckeditor/" ; 
							oFCKeditor.Height = 400; 
							
							oFCKeditor.ReplaceTextarea(); 
							</script></td>
              </tr>
			  </s:if>
			  </s:iterator>
              <tr>
                <td width="30%" align="center">正确选项：<font color="#ff0000">*</font></td>
                <td>
				<s:if test="qqst.qstType==0"><!-- 单选题 -->
				<s:if test='qqst.isAsr == "A"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if test='qqst.isAsr == "B"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='B' />B
							</s:else>
							
							<s:if test='qqst.isAsr == "C"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='C' checked/>C
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='C' />C
							</s:else>
							
							<s:if test='qqst.isAsr == "D"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='D' checked/>D
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='D' />D
							</s:else>
							
							<s:if test='qqst.isAsr == "E"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='E' checked/>E
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='E' />E
							</s:else>
							
							<s:if test='qqst.isAsr == "F"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='F' checked/>F
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='F' />F
							</s:else>
							
							<s:if test='qqst.isAsr == "G"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='G' checked/>G
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='G' />G
							</s:else>
				</s:if>
				
				<s:if test="qqst.qstType==2"><!-- 多选题 -->
				<s:if  test="-1 != qqst.isAsr.indexOf('A')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if  test="-1 != qqst.isAsr.indexOf('B')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='B' />B
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('C')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='C' checked/>C
							</s:if>
							<s:else>

								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='C' />C
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('D')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='D' checked/>D
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='D' />D
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('E')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='E' checked/>E
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='E' />E
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('F')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='F' checked/>F
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='F' />F
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('G')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='G' checked/>G
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='G' />G
							</s:else>
				
				</s:if>
				
				<s:if test="qqst.qstType==3"><!-- 判断题 -->
				<s:if test='qqst.isAsr == "A"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if test='qqst.isAsr == "B"'>
							<input type='radio' name='right<s:property value="qqst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='radio' name='right<s:property value="qqst.qstId"/>' value='B' />B
							</s:else>
				</s:if>
				
				<s:if test="qqst.qstType==4"><!-- 材料解析题 -->
				<s:if  test="-1 != qqst.isAsr.indexOf('A')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if  test="-1 != qqst.isAsr.indexOf('B')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='B' />B
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('C')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='C' checked/>C
							</s:if>
							<s:else>

								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='C' />C
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('D')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='D' checked/>D
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='D' />D
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('E')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='E' checked/>E
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='E' />E
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('F')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='F' checked/>F
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='F' />F
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('G')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='G' checked/>G
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='G' />G
							</s:else>
				</s:if>
				
				<s:if test="qqst.qstType==5"><!-- 图表题 -->
				<s:if  test="-1 != qqst.isAsr.indexOf('A')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='A' checked/>A
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='A' />A
							</s:else>
							
							<s:if  test="-1 != qqst.isAsr.indexOf('B')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='B' checked/>B
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='B' />B
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('C')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='C' checked/>C
							</s:if>
							<s:else>

								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='C' />C
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('D')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='D' checked/>D
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='D' />D
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('E')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='E' checked/>E
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='E' />E
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('F')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='F' checked/>F
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='F' />F
							</s:else>
							
							<s:if test="-1 != qqst.isAsr.indexOf('G')">
							<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='G' checked/>G
							</s:if>
							<s:else>
								<input type='checkbox' name='right<s:property value="qqst.qstId"/>' value='G' />G
							</s:else>
				</s:if>
				
				<s:if test="qqst.qstType==6"><!-- 主观题 -->
				  <s:iterator value="qqst.options" id="option">
				
				<textarea rows="7" cols="70"  id="optioncontent<s:property value="#option.optId"/>" name='optioncontent<s:property value="#option.optId"/>'><s:property value="#option.optContent"/></textarea>	
							<script type="text/javascript">
							var importURL = '..';
							var oFCKeditor = new FCKeditor("optioncontent<s:property value="#option.optId"/>"); 
							oFCKeditor.BasePath = importURL+"/fckeditor/" ; 
							oFCKeditor.Height = 400; 
							
							oFCKeditor.ReplaceTextarea(); 
							</script>
							</s:iterator>
				</s:if>
				
				
				</td>
              </tr>
              <tr>
                <td align="center">&nbsp;</td>
                <td><input class="submit" type="submit" value="提交" /></td>
              </tr>
            </table></td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
				
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
