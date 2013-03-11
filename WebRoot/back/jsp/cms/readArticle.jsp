<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HighSo嗨学网-资讯-${itemArticle.title}</title>
<link rel="stylesheet" type="text/css" href="http://import.highso.org.cn/styles/usercenter/uc_public.css" />
<link rel="stylesheet" type="text/css" href="http://import.highso.org.cn/styles/usercenter/uc_layout.css" />
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<script type="text/javascript">
    var baselocation = '<%=contextPath%>';
	var importURL = 'http://import.highso.org.cn';
</script>
<style>
.wen_info_nr p{text-indent:0px;}
</style>
</head>

<body>
<!--头开始-->
<div class="header">
	<div class="container height33">
    	<a href="javascript:toIndexPage()"><img class="left" src="http://import.highso.org.cn/images/usercenter/logo.gif" /></a>
        <div class="right header_right">
        	<a href="/">嗨学网首页</a>
            <a href="/static/web/column/<!--TagColumn parm='keyWord::commonquestion_org'>$columnId</TagColumn-->/index_1.shtml">帮助中心</a>
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
                <div class="xx_title"><font class="left"><a href="/cus/cuslimit!toInfCenter.action">资讯中心</a>  <a href="/cus/cuslimit!toInfList.action?pageNo=1" id="subject_a"></a></font><font class="right"></font></div>
                <div class="bor1blue pall-10">
                    <div class="font_red strongb fontsize-14 mtop-10">${itemArticle.title}</div>
                    <div class="wen_info ptop-10"><font class="left">
                    <span>来源：<s:property value="itemArticle.author"/> &nbsp;&nbsp;&nbsp;
                                        时间：[<s:date name="itemArticle.createDate" format="yyyy:MM:dd HH:mm:ss" />]&nbsp;&nbsp;&nbsp;点击数：${itemArticle.clickTimes} </span></font></div>
                    <div class="wen_info_nr font_hui">
                         <s:property value="itemArticle.content" escape="false" />
                    </div>
                            </div>
             
                    <div class="clear"></div>
                </div>
                
               <!-- <div class="mtop-8 img_noleft wid-820 zx_secondary"><a href="javascript:toSubjectPage()"><img id="footer_banner_img" src="http://import.highso.org.cn/images/usercenter/blank.jpg" /></a></div>-->
                </div>
            </div>
			</div>
		</div>
</div>
<div class="cent"><img id="error_bot" src="http://import.highso.org.cn/images/usercenter/error_bottom_bg.png" /></div>
<!--尾开始-->
        <div class="cent footer">
<a href="javascript:toColumn('about_us')">关于HighSo</a>
|
<a href="javascript:toColumn('commonquestion')">常见问题</a>
|
<a href="javascript:toColumn('about_contact')">联系方式</a>
| 咨询热线:010-51665411(人工9:00-19:30)
<br/>
京<font class="font_arial">ICP 备10217787号 Copyright &copy; 2010-2011 highso.org.cn</font>
尚德机构远程教育
</div>
<!--尾结束-->

</body>
</html>