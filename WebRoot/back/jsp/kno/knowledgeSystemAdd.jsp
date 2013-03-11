<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>HighSo嗨学网_考试中心 远程网络职业教育领跑者 尚德机构旗下</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/public.css" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/index.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/WebCalendar.js" ></script>  
	<script type="text/javascript">
	$().ready(function(){
		//初始化 Kindeditor
		KE.show({
    	                    id : 'content',
    	                    resizeMode : 1,
    	                    allowPreviewEmoticons : false,
    	                    allowUpload : true,
    	                    syncType : 'auto',
    	                    urlType : 'absolute',
    	                    imageUploadJson : 'http://tp.highso.cn:8080/upload!exam.action',
    	                    allowFileManager : false,
    	                    items : ['source','bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
    	                        'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
    	                        'code', 'image', '|']
    	                });
	})
	
		
		//验证并提交
		function dosubmit(){
		var subjectid=$("#subjectId").val();
		if(subjectid==0){
			alert("请选择专业");
			return false;
		}
		var versiontime=$("#versionTime").val();
		if(versiontime==''){
			alert("请选择版本时间");
			return false;
		}
		var content=$('#content').val();
		content=content.replace(/\s/g,'');
		if(content.length>400){
			alert("输入内容过长,请不要超过400字")
			return false;
		}
		var nodeId=$('#nodeId').val();
		if(nodeId==''){
			alert('项目简称不能为空');
			return false;
		}
		document.forms[0].submit();
		}
	</script>
  </head>
  
  <body>
   <form action="<%=contextPath %>/kno/knowledgeSystem!addKnoSys.action" name="knowledgeSystemAdd" method="post" onsubmit="encodeCommon()">
  <!--面包屑导航，开始-->
<div class="crumbs padl17"><em class="flag dib fl mr5"></em>知识树系统<span class="ml5 mr5">&gt;</span>知识树管理<span class="ml5 mr5">&gt;</span><a href='<%=contextPath %>/kno/knowledgeSystem!knowledgeSystemList.action?querySysCondition.currentPage=1'>录入管理</a><span class="ml5 mr5">&gt;</span>新建知识树</div>
<!--面包屑导航，结束-->
<div class="cont_outer">
  <div class="add_attach_one mt14">
    <div class="tree_item_line">
	    <div class="fl mb20 height25 ml15">
	      <label>一级分类：</label>
	      <select class="width130 fl inp_sel">
	        <option>一级分类</option>
	      </select>
	    </div>
	    <div class="fl mb20 height25 ml15">
	      <label class=" db fl">二级分类：</label>
	      <select class="width130  fl inp_sel">
	        <option>二级分类</option>
	      </select>
	    </div>
	    <div class="fl mb20 height25 ml15">
	      <label class=" db fl">项目分类：</label>
	      <s:select cssClass="inp_sel width200 " name="subject.subjectId" id="subjectId"
								list="subjectList" listKey="subjectId" listValue="subjectName"
								headerKey="0" headerValue="请选择所属专业" theme="simple">
						</s:select> 
		</div>
      <div class="clnull"></div>
    </div>
    <div class="mt10 cl">
      <label class="nerrong_label">内容：</label>
      <textarea id="content"    name="sys.content" class="nerrong_testare"></textarea>
      <div class="tree_item_line lin24 mt14">
        <label><font color="#ff0000">*</font>时间版本：</label>
        <input class="inp_txt width315" type="text" name="sys.versionTime"  readonly id="versionTime"
						onclick="SelectDate(this,'yyyy\/MM\/dd')" width="10"/>
        <div class="clnull"></div>
      </div>
      <div class="tree_item_line lin24 mt14">
        <label>关键字：</label>
        <input class="inp_txt width315" type="text" name="sys.keyWord"  id="keyWord" maxlength="150"/>
      </div>
      <div class="tree_item_line lin24 mt14">
        <label><font color="#ff0000">*</font>项目简称(如会计从业资格证为KJ)：</label>
        <input class="inp_txt width315"  type="text" name="sysNode.nodeId"  id="nodeId" maxlength="10" />
      </div>
      <div class="mt14" style="padding-left:140px">
      		<input class="bigYellow pub_btn ml70" type="button" onclick="dosubmit()"  value="下一步" />
      </div>
    </div>
    <div class="clnull"></div>
  </div>
</div>

  
</form>
  </body>
</html>
