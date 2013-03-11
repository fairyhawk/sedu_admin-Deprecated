<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<title>试卷阅览</title>
		
	<style type="text/css">
	.test_kaoti{margin:20px auto; width:800px; font-size:12px;}
	.test .zsd{ border:1px solid #d3e2f5; padding:10px; height:100%}
	.test .zsd dl{ border:0}
	.test .stgs{ padding:20px 10px 20px 10px; border:0; border-bottom:1px dashed #ccc!important;}
	.test .stgs p{ margin-bottom:12px;margin-left:25px; margin-top:0;}
	.test .stgs .tigan{ font-size:14px; font-weight:600;}
	.test .stgs dd{ color:#666; padding:3px 5px 0 5px; margin-bottom:0; margin-right:20px;margin-left:20px; line-height:20px}
	.test .stgs dd input{ cursor:pointer}
	.test .stgs dd:hover{ background:#fffac6}
	.teststgs{background:#fffac6}
	.test .stgs .dajx{background:#fffac6; padding:10px;}
	.test .stgs .dajx h4{ float:left}
	.test .stgs .dajx .right a{ margin-right:10px}
	.left{float:left;}
	.right{float:right;}
	.bgblue{}
	.wrongn dt{ color:#FF0000}
	.stgs .rightn{ background:#f8cdcf}
	.test .paper .mltitle{height:60px; margin-bottom:0; padding-left:5px; border-bottom:2px solid #006699; }
	.test .paper .mltitle font{float:right}
	.test .paper .mltitle h4{font-size:22px; font-weight:bold; margin-top:15px}
	.font_12{font-size:12px;}
	</style>
	</head>
	<body>
<div class="test">
		<div class="wd_detil test_kaoti">
			<!-- 知识水平测试 试卷 开始-->
			<div class="zsd paper mtop-10">
			<div class="mltitle mbottom"><h4 class="left strongb"><s:property value="course1"/>-<s:property value="testeptype1"/>-<s:property value="testname1"/><span class="font_12">[共<s:property value="exam.qst.size()"/>题]</span></h4></div>
			
			 <s:set name="multiQstCount" value="1"/>
			<s:iterator value="exam.qst" id="qst" status="count" >
			 <s:if test="#qst.qstType==0">
			
			<dl class="stgs"><dt><font class="left"> 【 单选题】<s:property value="#multiQstCount"/><s:set name="multiQstCount" value="#multiQstCount+1"/>.</font><p class="tigan"><s:property value="#qst.qstContent" escape="false" /></p></dt>
			  <s:iterator value="#qst.options" id="options">
			  <dd><font class="left"><input name="" type="radio" value="" /><s:property value ="#options.optOrder" />.</font><p><s:property value ="#options.optContent"  escape="false"/></p></dd>
 			</s:iterator>
  			</dl>
			</s:if>
			</s:iterator>
			
			
			 <s:set name="multiQstCount" value="1"/>
			<s:iterator value="exam.qst" id="qst" status="count2" >
			 <s:if test="#qst.qstType==2">
			
			<dl class="stgs"><dt><font class="left">【 多选题】<s:property value="#multiQstCount"/><s:set name="multiQstCount" value="#multiQstCount+1"/>.</font><p class="tigan"><s:property value="#qst.qstContent" escape="false" /></p></dt>
			  <s:iterator value="#qst.options" id="options">
			  <dd><font class="left"><input name="" type="checkbox" value="" /><s:property value ="#options.optOrder" />.</font><p><s:property value ="#options.optContent"  escape="false"/></p></dd>
 			</s:iterator>
  			</dl>
			</s:if>
			</s:iterator>
			
			
			
			<s:iterator value="exam.qst" id="qst" status="count2" >
			 <s:if test="#qst.qstType==4">
			
			<dl class="stgs"><dt><font class="left">【材料分析题】<s:property value="#multiQstCount"/><s:set name="multiQstCount" value="#multiQstCount+1"/>.</font><p class="tigan"><s:property value="#qst.qstContent" escape="false" /></p></dt>
			  <s:iterator value="#qst.options" id="options">
			  <dd><font class="left"><input name="" type="checkbox" value="" /><s:property value ="#options.optOrder" />.</font><p><s:property value ="#options.optContent"  escape="false"/></p></dd>
 			</s:iterator>
  			</dl>
			</s:if>
			</s:iterator>
			
			
			<s:iterator value="exam.qst" id="qst" status="count2" >
			 <s:if test="#qst.qstType==5">
			
			<dl class="stgs"><dt><font class="left">【 图表题】<s:property value="#multiQstCount"/><s:set name="multiQstCount" value="#multiQstCount+1"/>.</font><p class="tigan"><s:property value="#qst.qstContent" escape="false" /></p></dt>
			  <s:iterator value="#qst.options" id="options">
			  <dd><font class="left"><input name="" type="checkbox" value="" /><s:property value ="#options.optOrder" />.</font><p><s:property value ="#options.optContent"  escape="false"/></p></dd>
 			</s:iterator>
  			</dl>
			</s:if>
			</s:iterator>
			
			  <s:set name="multiQstCount" value="1"/>
			<s:iterator value="exam.qst" id="qst" status="count3" >
			 <s:if test="#qst.qstType==3">
			
			<dl class="stgs"><dt><font class="left">【 判断题】<s:property value="#multiQstCount"/><s:set name="multiQstCount" value="#multiQstCount+1"/>.</font><p class="tigan"><s:property value="#qst.qstContent" escape="false" /></p></dt>
			  <s:iterator value="#qst.options" id="options">
			  <dd><font class="left"><input name="" type="radio" value="" /><s:property value ="#options.optOrder" />.</font><p><s:property value ="#options.optContent"  escape="false"/></p></dd>
 			</s:iterator>
  			</dl>
			</s:if>
			</s:iterator>
			
			
			
			 <s:set name="multiQstCount" value="1"/>
			<s:iterator value="exam.qst" id="qst" status="count4" >
			 <s:if test="#qst.qstType==1">
			<dl class="stgs"><dt><font class="left">【主观题】<s:property value="#multiQstCount"/><s:set name="multiQstCount" value="#multiQstCount+1"/>.</font><p class="tigan"><s:property value="#qst.qstContent" escape="false" /></p></dt>
			  <s:iterator value="#qst.options" id="options">
			  <dd><font class="left"><input name="" type="radio" value="" /><s:property value ="#options.optOrder" />.</font><p><s:property value ="#options.optContent"  escape="false"/></p></dd>
 			</s:iterator>
  			</dl>
			</s:if>
			</s:iterator>
			
			</div>
			<!-- 知识水平测试 试卷 结束-->
			</div>
</div>
	</body>
</html>
