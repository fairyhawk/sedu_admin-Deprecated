<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加试题</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script src="<%=contextPath%>/back/script/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=contextPath%>/kindeditor-4.1.2/kindeditor-min.js"></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
		<link rel="stylesheet" href="<%=contextPath %>/back/style/screen.css" type="text/css" media="screen" title="default" />
		<!--[if IE]>
		<link rel="stylesheet" media="all" type="text/css" href="css/pro_dropline_ie.css" />
		<![endif]-->
		<!--  jquery core -->
		
		<!--  checkbox styling script -->
		<script src="<%=contextPath%>/back/script/jquery/ui.core.js" type="text/javascript"></script>
		<script src="<%=contextPath%>/back/script/jquery/jquery.ui.widget.js" type="text/javascript"></script>
		<script src="<%=contextPath%>/back/script/jquery/jquery.ui.mouse.js" type="text/javascript"></script>
		<script src="<%=contextPath%>/back/script/jquery/jquery.ui.slider.js" type="text/javascript"></script>
		<script src="<%=contextPath%>/back/script/exam.js" type="text/javascript"></script>
		
		
		<script src="js/jquery/custom_jquery.js" type="text/javascript"></script>
		<!-- Tooltips -->
		<script src="js/jquery/jquery.tooltip.js" type="text/javascript"></script>
		<script src="js/jquery/jquery.dimensions.js" type="text/javascript"></script>
		<script type="text/javascript">
		//ke编辑器的text() 去html 参考js
		function _trim(str) {
			return str.replace(/(?:^[ \t\n\r]+)|(?:[ \t\n\r]+$)/g, '');
			
		}
	 	
	 	 function text(val) {
		var self = this;
		if (val === undefined) {
			return _trim(self.html().replace(/<(?!img|embed).*?>/ig, '').replace(/&nbsp;/ig, ' '));
		} else {
			return self.html(_escape(val));
		}
	}
		
		
		//  考试js开始
		//------------------KE编辑器
			var editor= new Array();
			var define= new Array();
			
			//初始化
			KindEditor.ready(function() {
				createKE('content',0);
				createKE('content',1);
				createKE('content',2);
				createKE('content',3);
				createKE('content',4);
				createKE('content',5);
				createKE('content',6);
			});
			
			//动态创建ke编辑器
			function createKE(name,i){
				editor[i] = KindEditor.create('textarea[name="'+name+i+'"]', {
					allowFileManager : true,
					resizeType : 1,
			       	allowPreviewEmoticons : true,
			       	allowImageUpload : true,
			       	syncType : 'form',
			       	urlType : 'absolute',
					uploadJson : 'http://tp.highso.cn:8080/upload!exam.action',
					allowFileManager : false,
					items:[
					        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
					        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
					        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
					        'anchor', 'link', 'unlink', '|', 'about'
					]
				});
			}
		</script>
		
		
		<script type="text/javascript">
		
		
		//------------------试题类型
			//选择试题类型时对选项的影响
			function qstOption(qstType){
				var optionStr='';
				if(qstType==1){
					optionStr='<div class="select-ques-tt">选项组</div><ul class="select-ques"><li><label>A：</label><input type="text" class="inp-br" name="asr" value="请输入选项 A..."></li><li><label>B：</label><input type="text" class="inp-br" name="asr" value="请输入选项 B..."></li><li><label>C：</label><input type="text" class="inp-br" name="asr" value="请输入选项 C..."></li><li><label>D：</label><input type="text" class="inp-br" name="asr" value="请输入选项 D..."></li></div>';
				}
				if(qstType==2||qstType==5){
					optionStr='<div class="select-ques-tt">选项组<a id="add-select-ques-btn" class="add-select-ques-btn" href="javascript:void(0)">+ 新增选项</a></div><ul class="select-ques"><li><label>A：</label><input type="text" class="inp-br" name="asr" value="请输入选项 A..."></li><li><label>B：</label><input type="text" class="inp-br" name="asr" value="请输入选项 B..."></li><li><label>C：</label><input type="text" class="inp-br" name="asr" value="请输入选项 C..."></li><li><label>D：</label><input type="text" class="inp-br" name="asr" value="请输入选项 D..."></li></div>';
				}
				if(qstType==3){
					optionStr='<div class="select-ques-tt">选项组</div><ul class="select-ques"><li><label>A：</label><input type="text" class="inp-br" name="asr" value="请输入选项 A..."></li><li><label>B：</label><input type="text" class="inp-br" name="asr" value="请输入选项 B..."></li></div>';
				}
				if(qstType==4){
					optionStr='<div class="select-ques-tt">选项组</div><ul class="select-ques"><li><label>A：</label><input type="text" class="inp-br" name="asr" value="请输入选项 A..."></li><li><label>B：</label><input type="text" class="inp-br" name="asr" value="请输入选项 B..."></li></div>';
				}
				if(qstType==6){
					optionStr='选项组  <br>A: <input type="text" name="asr"/><br>B: <input type="text" name="asr"/>';
				}
				if(qstType==7){
					optionStr='<div class="select-ques-tt">选项组<a id="add-select-ques-btn" class="add-select-ques-btn" href="javascript:void(0)">+ 新增选项</a></div><ul class="select-ques"><li><label>A：</label><input type="text" class="inp-br" name="asr" value="请输入选项 A..."></li><li><label>B：</label><input type="text" class="inp-br" name="asr" value="请输入选项 B..."></li><li><label>C：</label><input type="text" class="inp-br" name="asr" value="请输入选项 C..."></li><li><label>D：</label><input type="text" class="inp-br" name="asr" value="请输入选项 D..."><span class="select-ques-del">删除</span></li></div>';
				}
				return optionStr;
			}
			//选择试题类型
			function selectQstType(qstType,typeName){
				$('#qstTypediv').hide();
				$('#qstType').val(qstType);
				$('#typeName').val(typeName);
				var optionStr=qstOption(qstType);
				$("div.select-ques-box").html(optionStr);
				if(qstType==4){
					$('div.ques-kind').hide();
				} 
				$('div.ques-kind').hide();
				if(qstType==2|qstType==5|qstType==7)$("#add-select-ques-btn").bind("click",addOption);
				$('#isAsr').val('');
			}
			
			
			//------------------试题选项
			//新增选项
			function addOption(){
				var asr=$('ul.select-ques > li');
				var order=String.fromCharCode('A'.charCodeAt(0)+asr.length);
				var newOption='<li><label>'+order+'：</label><input type="text" name="asr" class="inp-br" value="请输入选项 '+order+'..."><span class="select-ques-del">删除</span></li>';
				$("ul.select-ques li span").remove();
				$("ul.select-ques").append(newOption);
			}
			//增加最后一个选项的删除功能
			function addDeleClass(){
				var type=$('#qstType').val();
				var length=$("ul.select-ques li").length;
				$('#isAsr').val('');
				if((type==2|type==5)&length<=4)return;
				if(type==7&length<=2)return;
        		$("ul.select-ques >li:last-child").append('<span class="select-ques-del">删除</span>');
			}
			
			//------------------试题答案
			//显示答案
			function showAns(){
				var ss=$("ul.select-ques > li > input");
				var html='';
				var selectbox='';
				var type=$('#qstType').val();
				var isAsr=$('#isAsr').val();
				if(type==1|type==3|type=='')selectbox='<input name="qst.isAsr" type="radio" value="';
				if(type==2|type==5|type==7)selectbox='<input name="qst.isAsr" type="checkbox" value="';
				for(var i=0;i<ss.length;i++){
					var order=String.fromCharCode('A'.charCodeAt(0)+i);
					html+='<li>'+selectbox+order+'"';
					if(isAsr.indexOf(order)!=-1)html+=' checked="checked"';
					html+=' /><label for=""><i>'+order+'：</i><span>'+ss[i].value+'</span></label></li>';
				}
				html+='<li><a href="javascript:void(0)" onclick="selectAns()">确定</a></li>';
				$('ul.sec-an').html(html);
				$('ul.sec-an').show();
			}
			//选择答案
			function selectAns(){
				var ans=document.getElementsByName("qst.isAsr");
				var isasr=''; 
				for(var i=0;i<ans.length;i++){
					if(ans[i].checked){
						isasr+=ans[i].value;
					}
				}
				if(isasr=='')isasr='请选择答案';
				$('#isAsr').val(isasr);
				$('ul.sec-an').hide();
			}
			
			//------------------试题选项
			//显示题干
			function qstContentDiv(id){
				var type=$('#qstType').val();
				if(type==''){
					alert('请选择试题类型');
					return;
				}
				var title=$('#typeName').val()+'-题干';
				var ss=$("ul.select-ques > li > input").length;
				var linum=$('ul.tg-tab-menu li').length;
				$('#tg-pop h3').html(title);
				if(ss+2>linum){
					for(var i=linum-2;i<ss;i++){
						$('div.tg-tab .enter-box:nth-child('+(i+1)+')').after('<div class="enter-box" style="display:none;"><textarea name="content'+(i+3)+'" style="width:600px;height:400px;visibility:hidden;"></textarea></div>');
						$('ul.tg-tab-menu li:nth-child('+(i+1)+')').after('<li><a href="#">选项'+String.fromCharCode('A'.charCodeAt(0)+i)+'</a></li>');
						createKE('content',i+3);//添加新的编辑器元素
					}
					
					$("div.tg-tab").find("ul.tg-tab-menu a").click(function() {
			            $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
						$("div.enter-box:eq("+$("ul.tg-tab-menu a").index(this)+")").show().siblings("div.enter-box").hide();
						return false;
			        });
		        }
		        if(ss+2<linum){
		        	for(var i=ss;i<linum-2;i++){
						$('div.tg-tab .enter-box:nth-child('+(ss+2)+')').remove();
						$('ul.tg-tab-menu li:nth-child('+(ss+2)+')').remove();
						editor.pop();//删除最后一个编辑器元素
					}
					
		        }
			
				show(id,'');
			}
			
			//------------------试题各种保存操作
			//保存试题
			function saveQst(){
				$('#qstContent').val(editor[1].text());
				$('#pbResolved').val(editor[2].text())
				var option='<div class="select-ques-tt">选项组</div><ul class="select-ques">';
				var optAns=''
				for(var i=3;i<editor.length;i++){
					var order=String.fromCharCode('A'.charCodeAt(0)+i-3);
					if(i==editor.length-1&i>6)
					option+='<li><label>'+order+'：</label><input type="text" class="inp-br" name="asr" value="'+editor[i].text()+'"><span class="select-ques-del">删除</span></li>';
					else
					option+='<li><label>'+order+'：</label><input type="text" class="inp-br" name="asr" value="'+editor[i].text()+'"></li>';
				}
				option+='</div>'
				$("div.select-ques-box").html(option);
				$('div.ques-kind').hide();
				if(qstType==2|qstType==5|qstType==7)$("#add-select-ques-btn").bind("click",addOption);
				$('#isAsr').val('');
				closes('tg-pop','');
			}
			//识别输入   识别规则：'题干|选项1|选项2|选项3|选项4'
			function defineInput(){
				var input=editor[0].text().split('|');
				var errormsg=judgeLength(input.length);
				//根据题型判断，单选题长度5，判断题长度3，多选题5，图表题5，
				
				if(errormsg==true){
				if(input.length>define.length){
					for(var i=define.length;i<input.length;i++){
						createKE('define',i);
					}
				}
				var s='';
				s='<p class="filter-result mb10">当前选项<em>'+(input.length-1)+'</em>个；范围（<em>'+fanwei()+'</em>）个，以识别题干<em>1</em>个；选项'+(input.length-1)+'个（<em>'+String.fromCharCode('A'.charCodeAt(0)+0)+'-'+String.fromCharCode('A'.charCodeAt(0)+input.length-2)+'</em>）</p>';
				s+='<li><label>题干：</label><textarea class="filter-restxt" >'+input[0]+'</textarea></li>'
				for(var i=1;i<input.length;i++){
					s+='<li><label>选项'+String.fromCharCode('A'.charCodeAt(0)+i-1)+'：</label><textarea class="filter-restxt">'+input[i]+'</textarea></li>'
				}
				
				$('ul.filter-list').parent('div').html(s);
				show('filter-result-pop','filter-enter-pop');
				}else{
					alert(errormsg);
				}
			}
			
			function showDefineInput(){
				var type=$('#qstType').val();
				if(type==''){
					alert('请选择试题类型');
					return ;
				}
				show('filter-enter-pop','tg-pop');
			}
			
			
			//------------------公用方法
			//共用显示方法 显示第一个，隐藏第二个
			function show(id,id2){
				$('#'+id).show();
				if(id2!='')$('#'+id2).hide();
			}
			
			//共用隐藏方法 隐藏第一个，显示第二个
			function closes(id,id2){
				$('#'+id).hide();
				if(id2!='')$('#'+id2).show();
			}
			//根据类型 判断需要识别的长度，符合规则返回true
			function judgeLength(length){
				var type=$('#qstType').val();
				if(type==''){
					errormsg='请选择试题类型';
				}
				if(type==1&length==5)return true;
				if((type==2|type==5)&length>4&length<12)return true;
				if(type==3&length==3)return true;
				if((type==6|type==4)&length==2)return true;
				if(type=7&length>2&length<12)return true;
				if(type==1)errormsg='单选题格式为：题干|选项1|选项2|选项3|选项4';
				if(type==2|type==5)errormsg='多选题格式为：题干|选项1|选项2|选项3|选项4|.. 最多10个选项';
				if(type==3)errormsg='判断题格式为：题干|选项1|选项2';
				if(type==6|type==4)errormsg='主观题或材料分析题格式为：题干|答案';
				if(type==7)errormsg='判断题格式为：题干|选项1|选项2|.. 最多10个选项';
				return errormsg;
			}
			//根据试题类型变化，单选判断应是固定的
			function fanwei(type){
				if(type==1)return '4';//单选
				if(type==2|type==5)return '4-10';//多选或图表
				if(type==3)return '2';//判断
				if(type==7)return '2-10';//不定项
			}
			
			//------------------公用方法结束
			
			
		</script>
		<!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
		<script src="<%=contextPath%>/back/script/jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function(){
		$(document).pngFix( );
		});
		</script>
		<script> 
			$(function() {
				$( "#quse-level-slider" ).slider({
					value:0,
					min: 0,
					max: 5,
					step: 1,
					slide: function( event, ui ) {
						$( "#quse-level-num" ).text(ui.value);
					}
				});
				$( "#quse-level-num" ).text( $( "#slider" ).slider( "value" ) );
			});
		</script> 
		<!-- Start: page-top-outer -->
	</head>
	<body>
	
			<div class="exam">
	      <div class="e-item"><label class="s-label">所属题型：</label><input type="text" class="inp-br inp-quesKind" id="typeName"><input type="hidden" name="qst.qstType" id="qstType"/>
	        <div class="ques-kind" style="display:none;">
	          <h3>常用题型</h3>
	          <ul class="ques-kind-list">
	            <li><a href="javascript:void(0)" onclick="selectQstType(1,'单选题')">单选题</a></li>
	            <li><a href="javascript:void(0)" onclick="selectQstType(2,'多选题')">多选题</a></li>
	            <li><a href="javascript:void(0)" onclick="selectQstType(3,'判断题')">判断题</a></li>
	            <li><a href="javascript:void(0)" onclick="selectQstType(4,'材料分析题')">材料分析题</a></li>
	            <li><a href="javascript:void(0)" onclick="selectQstType(5,'图表题')">图表题</a></li>
	            <li><a href="javascript:void(0)" onclick="selectQstType(6,'主观题')">主观题</a></li>
	            <li><a href="javascript:void(0)" onclick="selectQstType(7,'不定项选择题')">不定项选择题</a></li>
	          </ul>
	          <div class="mt10"><input type="button" value="取消" class="btn_blue blue26 fr cancel-knid"></div>
	        </div>
	      </div>
	      <div class="e-item"><label class="s-label">题干：</label><input type="text" class="inp-br" id="qstContent" name="qstContent" value="请输入题干" readOnly="true" onclick="qstContentDiv('tg-pop')"></div>
	      <div class="e-item"><label class="s-label">选项：</label>
	          <div class="select-ques-box">
	            <div class="select-ques-tt"></div>
	            <ul class="select-ques">
	              <li><label>A：</label><input type="text" class="inp-br" value="请输入选项 A..."></li>
	              <li><label>B：</label><input type="text" class="inp-br" value="请输入选项 B..."></li>
	              <li><label>C：</label><input type="text" class="inp-br" value="请输入选项 C..."></li>
	              <li><label>D：</label><input type="text" class="inp-br" value="请输入选项 D..."></li>
	            </ul>
	          </div>
	          <div class="clear"></div>
	      </div>
	      <div class="e-item"><label class="s-label">答案：</label><input type="text" id="isAsr" class="inp-br" value="请选择答案" readOnly="true">
	      	<ul class="sec-an" style=" display:none;" >
        	</ul>
	      </div>
	      <div class="e-item"><label class="s-label">解题思路：</label><input type="text" id="pbResolved" name="qst.pbResolved" class="inp-br" value="请输入解题思路..."></div>
	      <div class="e-item"><label class="s-label">解析：</label><input type="text" id="wrongJude" name="qst.wrongJude"  class="inp-br" value="请输入错误评语..."></div>
	      <div class="e-item"><label class="s-label">知识库：</label><input type="text" class="inp-br inp-repository" value="请选择知识库知识点..."></div>
	      <div class="e-item"><label class="s-label">难易度：</label>
	        <div class="quse-level">
	          <span id="quse-level-num" class="quse-level-num">0</span>
	          <div id="quse-level-slider"></div> 
	        </div>
	        <span class="quse-level-info">(值越大越难)</span>
	        <div class="clear"></div>
	      </div>
	      <div class="e-item pl100"><input type="button" value="识别输入" class="btn_blue blue26" onclick="showDefineInput()"> <input type="button" value="预览试题" class="btn_blue blue26"> <input type="button" value="提交" class="btn_blue blue26"></div>
	    </div>
	  </div>
	  
		  <!--  end content-outer -->
		<div class="clear">&nbsp;</div>
		<!--start 知识库 弹窗-->
		<div id="repository-pop" class="pop" style="display:none;">
		  <h3>知识库</h3>
		  <span class="pop-close">&times;</span>
		  <div class="pop-con">
		    <dl class="pop-sec-course">
		      <dt>专业</dt>
		      <dd>
		        <ul class="pop-sec-course-list">
		          <li><a class="current" href="#">人力资源管理师</a></li>
		          <li><a href="#">心理咨询师</a></li>
		          <li><a href="#">会计资格证</a></li>
		          <li><a href="#">司法考试</a></li>
		          <li><a href="#">注册会计师</a></li>
		          <li><a href="#">证券从业</a></li>
		          <li><a href="#">一级建造师</a></li>
		          <li><a href="#">高级会计师</a></li>
		          <li><a href="#">公务员</a></li>
		          <li><a href="#">经济师考试课程</a></li>
		          <li><a href="#">研究生统一入学考试</a></li>
		          <li><a href="#">初级会计职称</a></li>
		          <li><a href="#">中级会计职称</a></li>
		          <li><a href="#">二级建造师</a></li>
		          <li><a href="#">监理工程师</a></li>
		          <li><a href="#">造价工程师</a></li>
		          <li><a href="#">GCT</a></li>
		          <li><a href="#">自考课程</a></li>
		        </ul>
		      </dd>
		    </dl>  
		    <dl class="pop-repository">
		      <dt>知识库知识点</dt>
		      <dd></dd>
		    </dl> 
		    <div class="clear"></div>
		    <div class="e-item tr pr20"><input type="button" value="保存" class="btn_blue blue26"></div> 
		  </div>
		</div>
		<!--end 知识库 弹窗-->
		
		<!--start 题干 弹窗-->
		<div id="tg-pop" class="pop" style=" display:none">
		  <h3>单选题-题干</h3>
		  <span class="pop-close">&times;</span>
		  <div class="pop-con p5">
		    <div class="e-item"><input type="button" value="识别输入" class="btn_blue blue26" onclick="showDefineInput()"> <input type="button" value="预览试题" class="btn_blue blue26"> <input type="button" value="保存试题" onclick="saveQst()" class="btn_blue blue26"></div>
		    <div class="tg-tab">
		      <div class="enter-box"><textarea name="content1" style="width:600px;height:400px;visibility:hidden;">1</textarea></div>
		      <div class="enter-box"><textarea name="content3" style="width:600px;height:400px;visibility:hidden;">2</textarea></div>
		      <div class="enter-box"><textarea name="content4" style="width:600px;height:400px;visibility:hidden;">3</textarea></div>
		      <div class="enter-box"><textarea name="content5" style="width:600px;height:400px;visibility:hidden;">4</textarea></div>
		      <div class="enter-box"><textarea name="content6" style="width:600px;height:400px;visibility:hidden;">5</textarea></div>
		      <div class="enter-box"><textarea name="content2" style="width:600px;height:400px;visibility:hidden;">6</textarea></div>
		      <ul class="tg-tab-menu">
		        <li><a class="current" href="#">题干</a></li>
		        <li><a href="#">选项A</a></li>
		        <li><a href="#">选项B</a></li>
		        <li><a href="#">选项C</a></li>
		        <li><a href="#">选项D</a></li>
		        <li><a href="#">解题思路</a></li>
		      </ul>
		    </div>
		    <div class="clear"></div>
		  </div>
		</div>
		<!--end 题干 弹窗-->
		
		<!--start 识别输入 弹窗-->
		<div id="filter-enter-pop" class="pop" style=" display:none;">
		  <h3>识别输入</h3>
		  <span class="pop-close">&times;</span>
		  <div class="pop-con p5">
		    <p class="mb10">请输入该题需识别的内容</p>
		    <div class="filter"><textarea class="filter-txt" name="content0" style="width:600px;height:400px;visibility:hidden;"></textarea></div>
		    <div class="e-item"><input type="button" value="开始识别" class="btn_blue blue26" onclick="defineInput()"> <input type="button" value="关闭"  onclick="closes('filter-enter-pop','')" class="btn_blue blue26"></div>
		    <div class="clear"></div>
		  </div>
		</div>
		<!--end 识别输入 弹窗-->
		
		<!--start 识别输入完成 弹窗-->
		<div id="filter-result-pop" class="pop" style=" display:none;">
		  <h3>识别输入</h3>
		  <span class="pop-close">&times;</span>
		  <div class="pop-con p5">
		    <p class="filter-result mb10">当前选项<em>4</em>个；范围（<em>2-10</em>）个，以识别题干<em>1</em>个；选项4个（<em>A-D</em>）</p>
		    <ul class="filter-list">
		      <li><label>题干：</label><textarea class="filter-restxt" name="" cols="" rows=""></textarea></li>
		      <li><label>选项A：</label><textarea class="filter-restxt" name="" cols="" rows=""></textarea></li>
		      <li><label>选项B：</label><textarea class="filter-restxt" name="" cols="" rows=""></textarea></li>
		      <li><label>选项C：</label><textarea class="filter-restxt" name="" cols="" rows=""></textarea></li>
		      <li><label>选项D：</label><textarea class="filter-restxt" name="" cols="" rows=""></textarea></li>
		    </ul>
		    <div class="e-item"><input type="button" value="识别到试题中" class="btn_blue blue26"> <input type="button" value="返回" onclick="closes('filter-result-pop','filter-enter-pop')" class="btn_blue blue26"> <input type="button" value="关闭"  onclick="closes('filter-result-pop','')" class="btn_blue blue26"></div>
		    <div class="clear"></div>
		  </div>
		</div>
		<!--end 识别输入完成 弹窗-->
	</body>
</html>
