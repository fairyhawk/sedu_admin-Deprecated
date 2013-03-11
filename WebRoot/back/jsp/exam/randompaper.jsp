<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>添加试卷</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/screen.css" />
	<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/back/script/exam.js"></script>		
	

  
<script language="javascript">
//题的序号
var qstIndex=1;
function toPre(){
	$("#randomform").attr("action","<%=contextPath %>/exam/exampaper!toAddExam.action?parm="+new Date());
	$("#randomform").submit();
}
function submitRandom(){
	//验证题类型是否被合并
	if(checkType() == false){
		alert("题的类型有重复，请合并!");
		return;
	}
	var data = new Array();
	var index = 0 ;
	$("select[name=qstType]").each(function() {
		data.push($(this).val());
	});
	$("input[name=qstNum]").each(function() {
		data[index]=data[index]+"*"+$(this).val();
		index++;
	});
	index = 0
	$("input[name=qstBouns]").each(function() {
		data[index]=data[index]+"*"+$(this).val();
		index++;
	});
	//alert(data);
	$("#randomform").attr("action","<%=contextPath %>/exam/exampaper!createRandomQuestion.action?parm="+data+"&ksnId="+"${ksnId}");
	$("#randomform").submit();
}

//检查随机题型是否有重复
function checkType(){
	var data = new Array();
	var flag = 1;
	$("select[name=qstType]").each(function() {
		if(isContain(data,$(this).val()) == true){
			data.push($(this).val());
		}else{
			flag = 0;
			return false;
		}
	});
	if(flag == 1){
		return true;
	}else{
		return false;
	}
}

function isContain(arr,va){
	for(i=0;i<arr.length;i++){
		if(arr[i] == va){
			return false;
		}
	}
	return true;
}

function IsNum(e,obj)
{
	
      var k = window.event ? e.keyCode:e.which;
     if (((k >= 48) && (k <= 57)) || k==8 || k==0 || k==46)
     {
     	//去除两个小数点开始
     	if(k==46){
     		var v = $(obj).val();
     		if(v.indexOf(".") > 0){
     			if(window.event)
          {
               window.event.returnValue = false;
        }
        else
         {
               e.preventDefault();//for firefox
       }
     		}
     	}
     	//去除两个小数点结束
     }else
   {
         if(window.event)
          {
               window.event.returnValue = false;
        }
        else
         {
               e.preventDefault();//for firefox
       }
   }

} 

</script>
		
	</head>
	<body>
	  
 <!-- start content -->
  <div id="right-content">
    <div class="exam">
      <div class="mb10 f14">随机出卷 | <a href="javascript:void(0);">手工出卷</a></div>
      <h2>第二步：请选择题型范围</h2>
      <div><input id="add-ques-range" type="button" value="添加题型范围" class="btn_blue blue26"></div>
      <ul class="ques-range">
        <li><span name="qstIndex">1.</span><label>题型名称：</label><select class="sec-br" name="qstType"><option value="1">单选题</option><option value="2">多选题</option><option value="3">判断题</option><option value="4">材料分析题</option><option value="5">图表题</option><option value="6">主观题</option></select> <label>题型数量：</label><input type="text" onkeypress="IsNum(event,this)"  maxlength="10" name="qstNum"  class="inp-br2" value="10"> <label>每小题分值：</label><input type="text" onkeypress="IsNum(event,this)" maxlength="10" name="qstBouns" class="inp-br2" value="10"><span class="ques-range-del">删除</span></li>
      </ul>
      <div class="clear"></div>
      <div class="mt10"><input type="button" value="返回上一步" onclick="toPre();" class="btn_blue blue26"> <input type="button" onclick="submitRandom();" value="随机生成试卷" class="btn_blue blue26"></div>
    </div>
  </div>
  <form action="<%=contextPath %>/exam/exampaper!toAddExam.action" method="post"  id="randomform"></form>
	</body>
</html>
