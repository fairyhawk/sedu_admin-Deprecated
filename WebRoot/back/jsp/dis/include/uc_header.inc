<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!-- 黑底效果时添加下面一行 -->
<div style="position:absolute; filter:alpha(opacity=50); opacity: 0.5; -khtml-opacity: 0.5; -moz-opacity:0.5; left:0;top:0;z-index:4; background:#000;" id="web_top_black"></div>
<!-- 弹窗：错误提示 -->
<div class="popup wrong" id="error_win" style="display:none;position:absolute;left:37%;top:15%;z-index:5;">
<ul class="title"><li class="ml"></li><li class="mc">错误提示</li><li class="mr"></li></ul>
<div class="con">
<div class="ml"><img src="<%=contextPath%>/images/web/public/popimg2.gif"  /></div>
<div class="mr"><b class="orange" id="error_message"></b><br />
<p class="popbut"><a href="javascript:closeErrorWin()">确定</a><span class="popbutmr"></span></p>
</div>
<div class="clear"></div>
</div>
</div>
<!-- 弹窗：错误提示 //-->

<!-- 弹窗：成功提示   -->
<div class="popup wrong" id="success_win" style="display:none;position:absolute;left:37%;top:15%;z-index:5;">
<ul class="title"><li class="ml"></li><li class="mc">成功提示</li><li class="mr"></li></ul>
<div class="con">
<div class="ml"><img src="<%=contextPath%>/images/usercenter/popimg3.gif"  /></div>
<div class="mr success"><b id="success_message"></b><br />
<p class="popbut"><a href="javascript:closeSuccessWin()">确定</a><span class="popbutmr"></span></p>
</div>
<div class="clear"></div>
</div>
</div>
<!-- 弹窗：成功提示 //-->
