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
//是否已经选择了知识点
var isChoose = 0;
var ksnId,nodeName;
var paperType=0;


//根据专业获得知识库知识点的树状数据 
function changeSubject(subjectId,subjectName,obj){
	isChoose = 0;
	$("#chooseKn").val("请选择知识库知识点...");
	if(obj != 'null'){
		$("li a").each(function() {
		$(this).removeAttr("class");
		});
		$(obj).attr("class","current");
	}
	var url = "<%=contextPath%>/exam/qst!getSysNodeBySubjectId.action";
	$.post(url, {subjectId: subjectId}, function(data){
		if(data.jumpType==true){
			var myList = data.entity;
			if(myList.length<1){
				$("#zpointList").html("");
				return;
			}
			addkpoint = new dTree('addkpoint','<%=contextPath %>/back/images/dtree');
			//去除树的cookie
			addkpoint.config.useCookies=false;
			addkpoint.add(-2,-1,'请选择下面的知识点(必选项)：');
			for(var i=0;i<myList.length;i++){  
				if(myList[i].parentId == -1){
					
				}
				addkpoint.add(myList[i].ksnId ,myList[i].parentId ,myList[i].nodeName,'javascript:confirmKn('+myList[i].ksnId+','+"'"+myList[i].nodeName+"'"+')');	
			}
			$("#zpointList").html(addkpoint.toString());
		}else{
			alert("error");
		}
	}, 'json');
	
}

//选定知识库知识点
function confirmKn(id,name){
	isChoose = 1;
	ksnId=id;
	nodeName=name;
}

//弹出窗口保存
function saveKn(){
	if(isChoose == 0){
		alert("对不起，您还没有选择知识点，请选择！");
		return;
	}
	$("#chooseKn").val(nodeName);
	$(".pop").hide();
}

//确定哪种出卷方式
function confirmMethod(type){
	if(type == 1){
		paperType=1;
		var str = '随机出卷 | <a href="javascript:void(0);" onclick="confirmMethod(2);">手工出卷</a>';
		$("#toNext").html(str);
		return;
	}
	if(type == 2){
		paperType=2;
		var str = '<a href="javascript:void(0);" onclick="confirmMethod(1);">随机出卷</a> | 手工出卷';
		$("#toNext").html(str);
		return;
	}
}

//点击下一步
function toNext(){
	if(paperType == 0){
		alert("请选择一种出卷类型!");
		return;
	}
	if(isChoose == 0){
		alert("请选择知识库知识点!");
		return;
	}
	if(paperType == 1){
		//跳到随机出卷页面
		$("#randomform").attr("action","<%=contextPath %>/exam/exampaper!addRandomPaper.action?ksnId="+ksnId);
		$("#randomform").submit();
	}
	if(paperType == 2){
		//跳到手工出卷页面
	}
	
}



</script>
		
	</head>
	<body>
	  
<!-- start content -->
<div id="right-content">
    <div class="exam">
      <div class="mb10 f14" id="toNext"><a href="javascript:void(0);" onclick="confirmMethod(1);">随机出卷</a> | <a href="javascript:void(0);" onclick="confirmMethod(2);">手工出卷</a></div>
      <h2>第一步：请选择知识结构树</h2>
      <div class="e-item"><label class="s-label">知识库：</label><input type="text"   id="chooseKn" class="inp-br" value="请选择知识库知识点..."></div>
      <div class="e-item pl100"><input type="button" value="点击下一步" onclick="toNext()" class="btn_blue blue26"></div>
    </div>
</div>
<!--  end content-outer -->
<div class="clear">&nbsp;</div>
<!--start 弹窗-->
<div class="pop" style="display:none;">  <!-- 默认为隐藏此窗口 style="display:none;" -->
  <h3>知识库</h3>
  <span  class="pop-close">&times;</span>
  <div class="pop-con">
    <dl class="pop-sec-course">
      <dt>专业</dt>
      <dd>
        <ul class="pop-sec-course-list">
	        <s:iterator id="obj" value="subjectList" status="count">
	        	<li><a href="javascript:void(0);" onclick="changeSubject('${obj.subjectId}','${obj.subjectName }',this)"  <s:property value="#count.index==0?'class=current':''" /> >${obj.subjectName }</a>
	        		<s:if test="#count.index==0">
	        			<script language="javascript">
	        				//初始化首页的知识库知识点
	        				changeSubject('${obj.subjectId}','${obj.subjectName }','null');
	        			</script>
	        		</s:if>
	        	</li>
	        </s:iterator>
	        
          
        </ul>
      </dd>
    </dl>  
    <dl class="pop-repository">
      <dt>知识库知识点</dt>
      <dd id="zpointList"></dd>
    </dl> 
    <div class="clear"></div>
    <div class="e-item tr pr20"><input  type="button" onclick="saveKn();" value="保存" class="btn_blue blue26"></div> 
  </div>
</div>
<!--end 弹窗-->
<form action="<%=contextPath %>/exam/exampaper!addRandomPaper.action" method="post"  id="randomform"></form>
	</body>
</html>
