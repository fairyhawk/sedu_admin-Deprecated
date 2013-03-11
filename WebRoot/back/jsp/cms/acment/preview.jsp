<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>资讯</title>
<link rel="stylesheet" type="text/css" href="http://import.highso.org.cn/styles/usercenter/uc_public.css" />
<link rel="stylesheet" type="text/css" href="http://import.highso.org.cn/styles/usercenter/uc_layout.css" />
<script type="text/javascript" src="http://import.highso.org.cn/js/web/public/web_jquery-jquery-1.3.2.min.js"></script>
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<style>
.wen_info_nr p{text-indent:0px;}
</style>
</head>
<script type="text/javascript">
<!--
var baselocation = '<%=contextPath%>';
var importURL = '<%=importURL%>';
function toColumn(keyWord) {
	var columnId = getColumnId(keyWord);
	if(columnId != -1) {
		window.open( baselocation + "/static/web/column/" + columnId + "/index_1.shtml");
	}
}

function getColumnId(keyWord) {
	var columnId = null;
	var v = window.location.href ;
	if(v.indexOf("http://ga.highso.org")==0){
		keyWord+="_org";
	}
	else if(v.indexOf("http://highso.org.cn")==0){
		keyWord+="_org_cn";
	}
	else if(v.indexOf("http://highso.org")==0){
		keyWord+="_org";
	}
	else if(v.indexOf("http://highso.com.cn")==0){
		keyWord+="_com_cn";
	}
	else if(v.indexOf("http://highso.net.cn")==0){
		keyWord+="_net_cn";
	}else{
		keyWord+="_cn" ;
	}
	$.ajax({
		url : baselocation + "/cus/cusweb!getColumnId.action",
		data : {
			"queryColumnsCondition.keyWord" : keyWord
		},
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		success : function(result) {
			if(result.returnMessage == "success") {
				columnId = result.entity;
			} else {
				columnId = -1;
			}
		},
		error : function(error) {
			alert('error');
		}
	});
	return columnId;
}
//-->
</script>
<body>
<!--头开始-->
<div class="header">
	<div class="container height33">
    	<a href="/"><img class="left" src="http://import.highso.org.cn/images/usercenter/logo.gif" /></a>
        <div class="right header_right">
        	<a href="/">嗨学网首页</a>
            <a href="javascript:toColumn('commonquestion');">帮助中心</a>
        </div>
    </div>
</div>
<!--头结束--> 
<div class="container con_top_bg3 con_pos">
<div class="error_h95"></div>
<div class="sderror">
<div class="wenda">
        	<div class="maintitle">
       	    <div class="switch ptop-10" >
            	<div class="wid-820 wd_detil leftlist zx_secondary">
                <div class="xx_title"><font class="left">课程公告</font></div>
                <div class="bor1blue pall-10">
                    <div class="font_red strongb fontsize-14 mtop-10"><s:property value="announcement.title" /></div>
                    <div class="wen_info ptop-10"><font class="left">
                    <span>发布时间：[<s:date name="announcement.addTime" format="yyyy-MM-dd HH:mm:ss" />] &nbsp;&nbsp;&nbsp;点击数：<s:property value="announcement.clickNum" /> </span></font></div>
                    <div class="wen_info_nr font_hui">
                         <s:property value="announcement.content" escape="false" />
                    </div>
                            </div>
             
                    <div class="clear"></div>
                </div>
                </div>
            </div>
			</div>
		</div>
</div>
<div class="cent"><img id="error_bot" src="http://import.highso.org.cn/images/usercenter/error_bottom_bg.png" /></div>
<!--尾开始-->
<div class="cent footer">
    <a href="javascript:toColumn('about_us')">关于HighSo</a> | <a href="javascript:toColumn('commonquestion')">常见问题</a> | <a href="javascript:toColumn('about_contact')">联系方式</a> | 咨询热线:010-51665411(人工9:00-19:30)<br />

    京<font class="font_arial">ICP 备10217787号 Copyright &copy; 2010-2011 highso.org.cn</font> 尚德机构远程教育
</div>
<!--尾结束-->
</div>
</body>
</html>