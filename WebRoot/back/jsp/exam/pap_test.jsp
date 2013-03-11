<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<title>入学测试</title>
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
		<script>
		  var timer;
		  var userId =2;
		  var tpId = ${exam.epId};
		  var cookieTimes = "rldTimes,"+userId+","+tpId; 
		  if(getCookie(cookieTimes)==null||getCookie(cookieTimes).split(":")[1].toString()=="NaN")  
		  { 
			  var rplTime = ${exam.replyTime};
			  var   endminutes= rplTime   *   60; 
		  }  
		  else  
		  {  
		  		var   endminutes=parseInt(getCookie(cookieTimes).split(":")[0])*60+parseInt(getCookie(cookieTimes).split(":")[1]);
		  }  
		  		var   betweendays=0;  
		  
		  function   setTimeEnd()  
		  { 
			  var   hour=0;  
			  endminutes=   endminutes   -   1;  
			  hour=endminutes%3600 
			  hour=(hour-(endminutes%3600))/3600  
			  hour   =   hour.toString();  
			  if   (hour.length   <   2)  
				  hour   =   "0"   +   hour;  
				  var   temp=parseInt(endminutes   /   60)   +   ":"   +   endminutes   %   60;  
				  document.all.countdown.innerText=temp  
				  SetCookie(cookieTimes,temp);  
			  if   (endminutes==0)  
			  {  
				  alert("考试时间到！");  
				  doSubmit('2');
				  return;  
			  }  
			   	timer = setTimeout("setTimeEnd()",1000);
		  }  
		   
		  function   SetCookie(name,value)  
		  {  
			  var   argv   =   SetCookie.arguments;  
			  var   argc   =   SetCookie.arguments.length;  
			  var   expires   =   (argc   >   2)   ?   argv[2]   :   null;  
			  var   path   =   (argc   >   3)   ?   argv[3]   :   null;  
			  var   domain   =   (argc   >   4)   ?   argv[4]   :   null;  
			  var   secure   =   (argc   >   5)   ?   argv[5]   :   false;  
			  document.cookie   =   name   +   "="   +   escape   (value)   +   ((expires   ==   null)   ?   ""   :   (";   expires="   +   expires.toGMTString()))   +   ((path   ==   null)   ?   ""   :   (";   path="   +   path))   +     ((domain   ==   null)   ?   ""   :   (";   domain="   +   domain))   +         ((secure   ==   true)   ?   ";   secure"   :   "");  
		  }  
		  function   DeleteCookie(name)  
		  {  
			  var   exp   =   new   Date();  
			  exp.setTime   (exp.getTime()   -   1);  
			  var   cval   =   GetCookie   (name);  
			  document.cookie   =   name   +   "="   +   cval   +   ";expires="   +   exp.toGMTString();  
		  }  
		  function   getCookie(cookieName)  
		  {  
		      var   cookieString   =   document.cookie;  
		      var   start   =   cookieString.indexOf(cookieName   +   '=');  
		      //   加上等号的原因是避免在某些   Cookie   的值里有  
		      //   与   cookieName   一样的字符串。  
		      if   (start   ==   -1)   //   找不到  
		          return   null;  
		      start   +=   cookieName.length   +   1;  
		      var   end   =   cookieString.indexOf(';',   start);  
		      if   (end   ==   -1)   return   unescape(cookieString.substring(start));  
		      return   unescape(cookieString.substring(start,   end));  
		  }  
		  
		  function stoper() {
			clearTimeout(timer);
		  }
		    $(document).ready(function() {
		            cancelAddRole();
		    })
		function cancelAddRoleCssSet() {
            $("#overlay").hide();
            $("#addRole").hide();
            $("#mClock").hide();
        }
		   
        function cancelAddRole() {
            $("#overlay").hide();
            $("#addRole").hide();
            $("#mClock").hide();
            stoper();
        }

        function addRole() {
	 setTimeEnd();
            $("#overlay").width($(document).width());
            $("#overlay").height($(document).height());
            $("#overlay").show();
            $("#addRole").show();
            $("#mClock").show();
        }
  </script>
		<style type="text/css">	
		
#mClock {
	margin-left: auto;
	margin-right: auto;
	font-size: 3px;
	font-family: "宋体";
	padding-top: 20px;
	width: 15%;
	height: 28px;
	position: absolute;
	z-index: 120;
	display: none;
	background: #FFFFFF;
	left: 75%;
	top: 8%;
	opacity: 0.55;
	filter: Alpha(opacity = 55);
	
} 
#overlay {
	background: #303030;
	opacity: 0.50;
	filter: Alpha(opacity =   50);
	display: none;
	position: absolute;
	top: 0px;
	left: 0px;
	z-index: 100;
}

#addRole {
	margin-left: auto;
	margin-right: auto;
	border: 2px solid #FFFFFF;
	font-size: 12px;
	font-family: "宋体";
	padding-top: 20px;
	width: 90%;
	height: 600px;
	position: absolute;
	z-index: 110;
	display: none;
	background: #FFFFFF;
	left: 5%;
	top: 5%;
	opacity: 0.85;
	overflow-x: hidden;
	overflow-y: auto;
}
</style>
	</head>
	<body>
		<h2>
			<s:property value="exam.epName" />
		</h2>
		<h3>
			本次测试共:
			<s:property value="exam.qst.size" />
			题&nbsp;&nbsp; 满分:
			<s:property value="exam.epTotelScore" />
			分&nbsp;&nbsp; 时间:
			<s:property value="exam.replyTime" />
			分钟
		</h3>
		<div class="right_content">
			<h4>
				考试说明:
			</h4>
			<br />
			<p>
				请在2010年3月10日22：00前完成所有测试，3月15日开始可以在各校区领取详细的诊断分析报告。
			</p>
		</div>
		<div class="right_content">
			<h4>
				${qstPaper.exampaper.epInfo}
			</h4>
			<br />
			<p>
				<s:property value="testpaper.tpInfo" />
			</p>
		</div>
		<p align="center">
			<input name="" type="submit" class="button" value="返 回"
				onclick="location.href='<%=contextPath%>/exam/qstManager!getExamPaperList.action'" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="进入测试" class="button" onclick="addRole();" />
		</p>
		<div id="addRole" class="addRole">
			<form action="qstManager!judgmentPaper.action" method="post">
		<table width="800">
			<tr>
				<td colspan="2">试卷</td>
			</tr>
			<s:iterator id="qsts" value="exam.qst" status="count">
			<tr>
				<td colspan="2" align="left">
					<s:property value="#count.index+1"/>.&nbsp;&nbsp;&nbsp;
					<s:property value="#qsts.qstContent"/>
				</td>
			</tr>
			<s:iterator value="#qsts.options" id="option">
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
				<input type="hidden" id="qstNum" value="<s:property value="exam.qst.size"/>" />
				<input type="hidden" name="subRlt" id="subRlt" value=""/>
				<input type="button" onclick="doSubmit()" value="提交答案"/>
			</tr>
		</table>
		
		</form>
		</div>
		<div id="mClock" class="mClock">
			<p align="right">
					<b><font size="3" color="red">考试剩余时间：<span
							id="countdown" style="FONT-WEIGHT: bolder"></span>
					</font>
					</b>&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
		</div>
		<div id="overlay" class="overlay" />
	</body>
</html>
