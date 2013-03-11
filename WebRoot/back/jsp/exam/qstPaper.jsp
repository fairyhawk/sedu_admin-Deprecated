<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>显示试题</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/css.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/lib.css" />
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/style/right.css" />
		
		<script src="/script/jQueryValidate/lib/jquery.js" type="text/javascript"></script>
		<script src="/script/jQueryValidate/jquery.validate.js" type="text/javascript"></script>
		<script src="/script/jQueryValidate/localization/messages_cn.js" type="text/javascript"></script>
		
		<link rel="StyleSheet" href="<%=contextPath%>/style/dtree.css" type="text/css" />
		<link rel="shortcut icon" href="../fckeditor.gif"
				type="image/x-icon" />
	</head>
	<script type="text/javascript">
		  function doSubmit(){
		     var isAllCheck = checkSubmit();
		     if(isAllCheck==="yy"){
			  	var qstNum =  document.getElementById("qstNum").value;
			  	 var  v = "";
			  	 for(i = 1; i <=qstNum ;i++){
			  	      var asrValue = getAsr(i);			  	       
				  	    	if(i==1)
				  	    	v= asrValue;
				  	    	else
				  	    	v=v+","+asrValue;			  	   
			  	 }
			  	 document.getElementById("subRlt").value = v;
			  	 document.forms[0].submit();
			  }else{
				  return false;
			  }
			 }
		  function getAsr(msg){
		   	
		   	 var qstAsrValue = checkRadio(msg);
		  	 return qstAsrValue;
		  }
		 function checkRadio(rm)
			{
			   　var ip="";
			   
			　　var rPort = document.getElementsByName("res"+rm);
			　　for(j=0;j<rPort.length;j++)
			　　{
			    　　 if(rPort[j].checked)
			       　　ip=rPort[j].value;
			　　}
			    return ip;
			} 
		  function focusRadio(msg)
			{
			    var divId = "divId"+msg;
			    location.href="#"+divId;
			}    
		  function checkSubmit(){
		  	var qstNum =  document.getElementById("qstNum").value;
		   	for(k = 0; k <qstNum;k++){
		  	      var asrValue = getAsr(k+1);
		  	      if(asrValue===""){
		  	      	focusRadio(k);
		  	     	alert("请填写第"+(k+1)+"题答案");
		  	     	return "nn";
			  	   }else{
			  	   }
		  	 }
		  	 return "yy";
		  }
		</script>
	<center>
	<body>
		showlist
		<br/>
		<s:iterator id="qst" value="qstPaper">
			<s:property value="#qst.qstId"/>
			<s:property value="#qst.epId"/>
			<s:property value="#qst.qstContent"/>
			<s:property value="#qst.isAsr"/>
			<s:property value="#qst.qstType"/>
			<s:property value="#qst.score"/>  
			<s:property value="#qst.ctPerson"/>
			<s:property value="#qst.level"/>
			<s:property value="#qst.addtime"/>
			<br/>
		</s:iterator>
		<form action="qstManager!judgmentPaper.action" method="post">
		<table width="800">
			<tr>
				<td colspan="2">试卷</td>
			</tr>
			<s:iterator id="qst" value="qstPaper" status="count">
			<tr>
				<td colspan="2" align="left">
					<s:property value="#count.index+1"/>.&nbsp;&nbsp;&nbsp;
					<s:property value="#qst.qstContent"/>
				</td>
			</tr>
			<s:iterator value="#qst.options" id="option">
			<tr >
				<td colspan="2" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<s:property value="#option.optOrder"/>
					<s:property value="#option.optContent"/>
				</td>
			</tr>
			</s:iterator>
			<tr align="left">
				<td><input name="res<s:property value="#count.index+1"/>" type="radio" value="A"/>A</td>
				<td><input name="res<s:property value="#count.index+1"/>" type="radio" value="B"/>B</td>
			</tr>
			<tr align="left">
				<td><input name="res<s:property value="#count.index+1"/>" type="radio" value="C"/>C</td>
				<td><input name="res<s:property value="#count.index+1"/>" type="radio" value="D"/>D</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		
			</s:iterator>
			<tr>
				<td>
				<input type="hidden" id="qstNum" value="<s:property value="qstPaper.size"/>" />
				<input type="hidden" name="subRlt" id="subRlt" value=""/>
				<input type="button" onclick="doSubmit()" value="提交答案"/>
			</tr>
		</table>
		
		</form>
	</body>
	</center>
</html>
