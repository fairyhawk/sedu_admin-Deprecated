<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>My JSP 'videoPreview.jsp' starting page</title>
   		<link rel="stylesheet" type="text/css"
			href="<%=importURL%>/styles/usercenter/uc_public.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=importURL%>/styles/usercenter/uc_layout.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=importURL%>/styles/usercenter/weixuexi.css" />
		<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
		<script type="text/javascript"
			src="<%=importURL%>/js/web/public/web_swfobject.js"></script>
		<script type="text/javascript"
			src="<%=importURL%>/js/web/public/web_jquery-jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=importURL%>/js/web/public/jQueryValidate/jquery.validate.js"></script>
	 
		<script type="text/javascript">
			var firstPlay = false;
			var isTryListen = false;
			var vo_url = '<s:property value="vedio.voUrl"/>';
			var shiTingUrl ="http://shipin.sunland.org.cn/web2011/kjzgz/kjjc/23_yj_cjy_2011_02_kjcyzg_kjjc_jcb_06_02_00.mp4";
			//是否播放广告
			var isFirstPlay = function (){
				return firstPlay;
			}
		
			//开始播放视频
			var getCurrentVideoUrl = function (){
				return isTryListen ? shiTingUrl : vo_url;
			}
			
			
			function onVedioClick() {
				document.getElementById("shiTingImg").style.display = "block";
				$("#videoPlayer_SF")[0].style.display = "hidden";
			}
			
			var loadVideo = function (vedioUrl){
				var oSwf = getSwfObject();;
				try{
					if(oSwf) {
						oSwf.load(vedioUrl);
						firstPlay = false;
					}
				}catch(e){ }
			}
			
			var getSwfObject = function (){
				return swfobject.getObjectById("videoPlayer");
			}
	
			$().ready(function(){
			
				var oSwf = getSwfObject();
				
					if(oSwf){
						document.getElementById("teacherImgs").style.display = "none";
						
						$("#videoPlayer")[0].style.display = "block";
						oSwf.style.display = "block";
						
					}
			});
			var userCenter=true;
			//是否显示广告界面
			var isUserCenter = function (){
				return userCenter;
		    }
		    
		    //取得视频播放列表
		function getTjCourses() {
		var title = "";
		var buyMethod = "";
		var picPath="";
		var clickTimes = 19681;
	    var	info = "";
		
		var tjCourses = {};
		tjCourses.recommendVedios = [];
		tjCourses.recommendVedios[0] = {
			"picPath" : "http://import.highso.org.cn/images/web/public/st/shiting_pt_03.gif",
			"title" : "平台介绍",
			"clickTimes" : "19735",
			"url" : "http://shipin.sunland.org.cn/web2011/xcp.mp4",
			"buyMethod" : buyMethod,
			"videoId" : getTryListenId(),
			"index" : 0,
			"info" : info
		};
		tjCourses.recommendVedios[1] = {
			"picPath" : picPath,
			"title" : title,
			"clickTimes" : clickTimes,
			"url" : shiTingUrl,
			"buyMethod" : buyMethod,
			"videoId" : getTryListenId(),
			"index" : 1,
			"info" : info
		};
		return tjCourses;
	}
	
	function cancle(ksnId){
    		window.location='<%=contextPath %>/kno/videoRel!toVideoRelList.action?ksnId='+ksnId;
    	}
	
		</script>
  </head>
  
  <body>
  <div><input type="button" value="返回" onclick="cancle(<s:property value="ksnId"/>);"/></div>
  	<li class="pbottom-5 limg" id="obj" style="clear:both ;list-style-type: none;_display:inline;" >
        	<a onclick="onImgClick()" style="cursor: hand" mce_style="cursor: hand">
			<img id="teacherImgs" src="<%=importURL%>/images/usercenter/weixuexi_videoImg.jpg" style="width:560px;height:315px;"/>
		    </a>
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				width="560" height="315" id="videoPlayer" style="display:none">
				<param name="movie"
					value="<%=importURL%>/static/flex/AdobeVideoPlayer.swf?width=560&height=315&url=<%=importURL%>&importURL=<%=importURL%>" />
				<param name="quality" value="high" />
				<param name="bgcolor" value="#ffffff" />
				<param name="allowScriptAccess" value="always" />
				<param name="allowFullScreen" value="true" />
				<param name="wmode" value="transparent" />
			<!--[if !IE]>-->'
			<object type="application/x-shockwave-flash" style="display:none" wmode="window"
					data="<%=importURL%>/static/flex/AdobeVideoPlayer.swf?width=560&height=315&url=<%=importURL%>&importURL=<%=importURL%>"
					width="560" height="315" id="videoPlayer_FF">
					<param name="quality" value="high" />
					<param name="bgcolor" value="#ffffff" />
					<param name="allowScriptAccess" value="always" />
					<param name="allowFullScreen" value="true" />
					<param name="wmode" value="transparent" />
				<!--<![endif]-->
					<!--[if gte IE 6]>-->
					<p>
						Either scripts and active content are not permitted to run or
						Adobe Flash Player version 10.1.0 or greater is not installed.
				</p>
					<!--<![endif]-->
					<a href=" http://www.adobe.com/go/getflashplayer"> <img
							src=" http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
							alt="Get Adobe Flash Player" /> </a>
					<!--[if !IE]>-->
				</object>
				<!--<![endif]-->
			</object>
        </li>
  </body>
</html>
