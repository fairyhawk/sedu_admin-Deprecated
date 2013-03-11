// 弹窗
$(document).ready(function() {
    $("span.pop-close").click(function() {
        $(this).parent("div.pop").hide();
    });
    $('#chooseKn').click(function(){
    	$(".pop").show();
    });
});

// 题型范围
$(document).ready(function() {
	$("ul.ques-range li").live("mouseover",function(){
		$(this).addClass("current").siblings("li").removeClass("current");
		$(this).find("span.ques-range-del").show();
	});
	
	$("ul.ques-range li").live("mouseout",function(){
		$(this).removeClass("current").find("span.ques-range-del").hide();
	});
	
	$("span.ques-range-del").live("click",function() {
        $(this).parent("li").remove();
        qstIndex = 0;
        $("span[name=qstIndex]").each(function() {
        	qstIndex++;
        	$(this).html(qstIndex+".");
        	
        });
    });
	
	$("#add-ques-range").bind("click",function() {
		//验证题类型是否被合并
		if(checkType() == false){
			alert("题的类型有重复，请合并!");
			return;
		}
		qstIndex++;
        var quesRange_li ='<li><span name="qstIndex">'+qstIndex+'.</span><label>题型名称：</label><select class="sec-br" name="qstType"><option value="1">单选题</option><option value="2">多选题</option><option value="3">判断题</option><option value="4">材料分析题</option><option value="5">图表题</option><option value="6">主观题</option></select> <label>题型数量：</label><input type="text" onkeypress="IsNum(event,this)"  maxlength="10" name="qstNum" class="inp-br2" value="10"> <label>每小题分值：</label><input type="text" onkeypress="IsNum(event,this)"  maxlength="10" name="qstBouns" class="inp-br2" value="10"><span class="ques-range-del">删除</span></li>';
		$("ul.ques-range").append(quesRange_li);
    });
	
 
	  
});

// 选项添加删除
$(document).ready(function() {
	$("ul.select-ques li").live("mouseover",function(){
		$(this).find("span.select-ques-del").show();
	});
	
	$("ul.select-ques li").live("mouseout",function(){
		$(this).find("span.select-ques-del").hide();
	});
	
	$("span.select-ques-del").live("click",function() {
        $(this).parent("li").remove();
        addDeleClass();
    });
	
});

// 试卷设置
$(document).ready(function() {
    $("#ques-set-btn").bind("click",function() {
        $("#ques-set-pop").show();
		return false;
    });
});

// 新增试题
$(document).ready(function() {
    $("#new-ques-pro-btn").bind("click",function() {
        $("#new-ques-pop").show();
		return false;
    });
});

// 知识库
$(document).ready(function() {
    $(".inp-repository").bind("click",function() {
        $("#repository-pop").show();
		return false;
    });
});

// 题型
$(document).ready(function() {
    $(".inp-quesKind").bind("click",function() {
        $("div.ques-kind").show();
    });
	
	$(".cancel-knid").bind("click",function(){
		$(this).parents("div.ques-kind").hide();
	});
});

// 选项卡
$(document).ready(function() {
		$("div.tab").find("div.tab-con:gt(0)").hide();
		$("div.tab").find("ul.tab-menu a:first").addClass("current");
		$("div.tab").find("ul.tab-menu a").click(function() {
            $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
			$("div.tab-con:eq("+$("ul.tab-menu a").index(this)+")").show().siblings("div.tab-con").hide();
			return false;
        });
});

// 题干选项卡
$(document).ready(function() {
		$("div.tg-tab").find("div.enter-box:gt(0)").hide();
		$("div.tg-tab").find("ul.tg-tab-menu a:first").addClass("current");
		$("div.tg-tab").find("ul.tg-tab-menu a").click(function() {
            $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
			$("div.enter-box:eq("+$("ul.tg-tab-menu a").index(this)+")").show().siblings("div.enter-box").hide();
			return false;
        });
});

//答案
$(document).ready(function() {
		$('#isAsr').click(function(){
			showAns();
		});
});