$(document).ready(function(){
	$("#extract").click(function(){
		if(v()){
			$.ajax({
				url: "../cms/information!extractFile.action",
				data:{
					"url":$("#tmpUrl").val()
				},
				type : "post",
				cache : false,
				success : function(result) {
					$("#tmpContent").attr("value","");	
					if(result=="error01"){
						alert("未发现文件!");
					}
					else{
						if(result=="error02"){
							alert("写入错误!");
						}
						else{
							$("#tmpContent").attr("value",result);						
						}						
					}
				},
				error : function(error) {
					$("#tmpContent").attr("value","");
					alert("系统错误，请稍后重试！");
				}
			});			
		}else{
			$("#tmpContent").attr("value","");
			alert("提取文件路径错误，请重新输入！");
		}
	});
	
	$("#save").click(function(){
		if(v()){
			$.ajax({
				url: "../cms/information!updateInformation.action",
				data:{
					"url":$("#tmpUrl").val(),
					"common":$("#tmpContent").val()
				},
				type : "post",
				cache : false,
				success : function(result) {
					$("#tmpContent").attr("value","");
					if(result=="error01"){
						alert("未发现文件!");
					}
					if(result=="error02"){
						alert("写入错误!");
					}
					if(result=="success"){
						alert("修改成功!");						
					}
				},
				error : function(error) {
					$("#tmpContent").attr("value","");
					alert("系统错误，请稍后重试！");
				}
			});			
		}else{
			$("#tmpContent").attr("value","");
			alert("提取文件路径错误，请重新输入！");
		}
	});
	
	$("#sl").change(function(){
		se("sl");
	});
	
	$(":radio").click(function(){
		if($("#Custom").attr("checked")){
			$("#tmpUrl").attr("readonly",false);
			$("#tmpUrl").attr("disabled",false);
			$("#tmpUrl").attr("style",false);
			$("#tmpUrl").attr("value","");
			$("#sl").attr("disabled","disabled");
		}
		if($("#Consulting").attr("checked")){
			$("#tmpUrl").attr("readonly","readonly");
			$("#tmpUrl").attr("disabled","disabled");
			$("#tmpUrl").attr("style","background-color: #CCCCCC");
			$("#tmpUrl").attr("value","");
			$("#sl").attr("disabled",false);
			se("sl");
		}		
	});	
});

function se(id){
	if($("#"+id).val()=="1"){
		$("#tmpUrl").attr("value","/static/information/news_center_rl.inc");
	}
	if($("#"+id).val()=="2"){
		$("#tmpUrl").attr("value","/static/information/news_center_xl.inc");
	}
	if($("#"+id).val()=="3"){
		$("#tmpUrl").attr("value","/static/information/news_center_kjz.inc");
	}
	if($("#"+id).val()=="4"){
		$("#tmpUrl").attr("value","/static/information/news_center_cpa.inc");
	}
	if($("#"+id).val()=="5"){
		$("#tmpUrl").attr("value","/static/information/news_center_sf.inc");
	}
	if($("#"+id).val()=="6"){
		$("#tmpUrl").attr("value","/static/information/news_center_cpa.inc");
	}
	if($("#"+id).val()=="7"){
		$("#tmpUrl").attr("value","/static/information/news_center_cpa.inc");
	}
	if($("#"+id).val()=="8"){
		$("#tmpUrl").attr("value","/static/information/news_center_zq.inc");
	}
	if($("#"+id).val()=="9"){
		$("#tmpUrl").attr("value","/static/information/news_center_jz.inc");
	}
	if($("#"+id).val()=="10"){
		$("#tmpUrl").attr("value","/static/information/news_center_gk.inc");
	}
	if($("#"+id).val()=="11"){
		$("#tmpUrl").attr("value","/static/information/news_center_gwy.inc");
	}
	if($("#"+id).val()=="12"){
		$("#tmpUrl").attr("value","/static/information/news_center_jjs.inc");
	}
	if($("#"+id).val()=="13"){
		$("#tmpUrl").attr("value","/static/information/news_center_ky.inc");
	}
	if($("#"+id).val()=="14"){
		$("#tmpUrl").attr("value","/static/information/news_center_cjkjs.inc");
	}
	if($("#"+id).val()=="15"){
		$("#tmpUrl").attr("value","/static/information/news_center_kjs.inc");
	}
	if($("#"+id).val()=="16"){
		$("#tmpUrl").attr("value","/static/information/news_center_jz2.inc");
	}
	if($("#"+id).val()=="17"){
		$("#tmpUrl").attr("value","/static/information/news_center_jl.inc");
	}
	if($("#"+id).val()=="18"){
		$("#tmpUrl").attr("value","/static/information/news_center_zj.inc");
	}
	if($("#"+id).val()=="100"){
		$("#tmpUrl").attr("value","/static/information/ul_common.inc");
	}
}

function v(){
	if($("#tmpUrl").val().indexOf("/")==-1){
		return false;
	}else{
		return true;
	}
}