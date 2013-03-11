
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>体系结构</title>
		<style type="text/css">
  			#infoWrap #info #infotab,#infoWrap #info #optstab,#infoWrap #moreopts #moreoptstab {
  				font-size:12px;
  			}
  			.wrapback{  				
  				width:100%;
  				height:100%;  				
  			}
  			#optstab td,#moreoptstab td{
  				text-align: left;
  			}
  			#infoWrap td,#moreopts td{
  				border:none;
  			}
  		</style>
  		<!--[if IE 6.0]>
  		<style type="text/css">
  			.wrapback{  				
  				width:1900px;
  				height:900px;  				
  			}
  		</style>
  		<![endif]--> 
  		
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/public.css" />	
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/index.css" />

		<script language="javascript">
		function newlevel(isnew){
		//将修改时变动属性还原
			if(isnew){
				$("#title").html("新建预设项");
				$("#levelOpts").attr("disabled","");
				$("#levelOpts").val(0);
				$("#levelinfo").html("层级：");
				$("#modifyopts").val("返回修改");
				$("#modifyopts").css("display","none");
				$("#optstab").html("");				
				$("#moreoptstab").html("");
				$("#preName").val("");				
				$("#content").val("");
				$("#confirm").val("确定");
			}
			$("#wrapback").fadeIn();
			$("#infoWrap").fadeIn();
		}		
  		</script>
  		<script language="javascript">
  			$(document).ready(function(){
  				var currMax=6;
  				$("#levelOpts").change(function(){
  					var levelNum=$("#levelOpts").val();
  					$("#modifyopts").css("display","none");
  					addOpt(levelNum);
  				});
  			});
			$(document).ready(
				function(){
					var levelOpts=document.getElementById("levelOpts");
					for(var i=1;i<100;i++){
						levelOpts.options[i]=new Option(i,i);
					}
				}
			);
			//10个选项以内在当前页显示选项，每列5个；超过10个的在新页（弹出层）显示，每列33个。最多99个选项
			function addOpt(levelNum){			
				$("#optstab").html("");				
				$("#moreoptstab").html("");
				var rightNum;
				var midNum;	
				if(levelNum<6){
					for(var i=1;i<=levelNum;i++)
						$("#optstab").append("<tr><td width='45%'>"+i+"<input type='hidden' name='preNodeSort' value='"+i+"'/>&nbsp;<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/></td><td></td></tr>");
				}else if(levelNum<11){
					for(var i=1;i<=5;i++){
						rightNum=((i+5)>levelNum?'':(i+5));						
						$("#optstab").append("<tr><td width='4%'>"+i+"<input type='hidden' name='preNodeSort' value='"+i+"'/></td><td width='40%'><input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/></td>"						
						+"<td width='4%'>"+rightNum+"<input type='hidden' name='"+(rightNum==''?'':'preNodeSort')+"' value='"+rightNum+"'/></td><td>"+((i+5)>levelNum?'':"<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/>")+"</td></tr>");						
					}
				}else if(levelNum<34){
				//从此后均在弹出层显示, 1列
				showMoreOpts();
					for(var i=1;i<=levelNum;i++)
						$("#moreoptstab").append("<tr><td width='4%'>"+i+"<input type='hidden' name='preNodeSort' value='"+i+"'/></td><td width='29%'><input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/></td><td width='4%'></td><td></td></tr>");
				}else if(levelNum<67){
				//2列
				showMoreOpts();
					for(var i=1;i<=33;i++){
						rightNum=((i+33)>levelNum?'':(i+33));
						$("#moreoptstab").append("<tr><td width='4%'>"+i+"<input type='hidden' name='preNodeSort' value='"+i+"'/></td><td width='29%'><input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/></td>"+
						"<td width='4%'>"+rightNum+"<input type='hidden' name='"+(rightNum==''?'':'preNodeSort')+"' value='"+rightNum+"'/></td><td width='29%'>"+((i+33)>levelNum?'':"<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/>")+"</td><td width='4%'></td><td></td></tr>");
					}
				}else{
				//3列
				showMoreOpts();
					for(var i=1;i<=33;i++){
						midNum=((i+33)>levelNum?'':(i+33));
						rightNum=((i+66)>levelNum?'':(i+66));
						$("#moreoptstab").append("<tr><td width='4%'>"+i+"<input type='hidden' name='preNodeSort' value='"+i+"'/></td><td width='29%'><input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/></td>"+
						"<td width='4%'>"+midNum+"<input type='hidden' name='preNodeSort' value='"+midNum+"'/></td><td width='29%'>"+((i+33)>levelNum?'':"<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/>")+"</td>"
						+"<td width='4%'>"+rightNum+"<input type='hidden' name='"+(rightNum==''?'':'preNodeSort')+"' value='"+rightNum+"'/></td><td>"+((i+66)>levelNum?'':"<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161'/>")+"</td></tr>");
					}
				}
			}
			function showMoreOpts(){
				//$("#infoWrap").css("z-index",6);
				$("#moreopts").css("display","block");
			}
			function closeManager(id){
				if(id=="infoWrap"){
					$("#infoWrap").css("display","none");
					$("#wrapback").fadeOut();
				}else{
					$("#"+id).css("display","none");
					$("#modifyopts").css("display","inline");
				}
			}
			function checkInp(flag,confirmType){
				var b=true;//作为返回值的变量
				var isExist=false;//判断预设项子项是否已存在的flag。
				var content=$("#content").val();
				if(flag==1){
					if($("#preName").val()==""){
						alert("预设项名称不能为空！");
						return false;
					}else if($("#preName").val().length>20){
						alert("预设项名称不能超过20个字符！");
						return false;
					}else if(!confirmType&&$("#levelOpts").val()==0){
						//新建时判断，修改时不判断
						alert("请选择一个层级！");
						return false;
					}else if(content.length>100){
						alert("请将字数限制在100字以内");
						return false;
					}else{					   
						$("input[name='preNodeName']").each(function(i){
							if($(this).val()==null||$(this).val()==""){
								alert("需要为每个子选项命名！");
								b=false;
								return false;
							}else if($(this).val().length>20){
								alert("必须保证每个子选项命名不能超过20个字符！");
								b=false;
								return false;
							}else{
								var currVal=$(this).val();								
								if(!isExist){
									$("input[name='preNodeName']").each(function(j){
										if(i!=j&&currVal==$(this).val()){										
											alert("子选项的名称不能重复，请修改！");
											isExist=true;
											b=false;								
											return false;
										}
									});
								}
								if(isExist)return false;
							}
						});						
						return b;
					}
					return true;
				}else{//用于更多选项页面的检查
					$("input[name='preNodeName']").each(function(i){
						if($(this).val()==null||$(this).val()==""){
							alert("需要为每个子选项命名！");
							b=false;
							return false;
						}else if($(this).val().length>20){
							alert("必须保证每个子选项命名不能超过20个字符！");
							b=false;
							return false;
						}else{
							var currVal=$(this).val();								
							if(!isExist){
								$("input[name='preNodeName']").each(function(j){
									if(i!=j&&currVal==$(this).val()){										
										alert("子选项的名称不能重复，请修改！");
										isExist=true;
										b=false;								
										return false;
									}
								});
							}
							if(isExist)return false;
							
						}
					});
					if(b){
						closeManager("moreopts");
						$("#modifyopts").css("display","inline");
					}
				}
				
			}
			function modifyPreitem(preid){
				$("#moreoptstab").html("");
				$("#optstab").html("");
				$.ajax({
					url : "<%=contextPath%>/kno/preItem!showPreItem.action",
					data : {
					preId : preid					
					},
					type:"post",
					cache:false,
					dataType:"json",
					error:function(error){
						alert(error.responseText);
					},
					success:modifyBack
					
					
				});
			}
			function modifyBack(result){
				var preItem=eval('('+result.returnMessage+')');
				var preNodeList=preItem.preNodeList;
				$("#preName").val(preItem.preName);
				$("#content").val(preItem.content);
				$("#preId").val(preItem.preId);
				$("#status").val(preItem.status);
				$("#title").html("修改预设项");
				$("#levelOpts").attr("disabled","disabled");
				$("#levelinfo").html("选项：");
				$("#confirm").val("修改");
				modifyOpts(preNodeList);
				newlevel();
			}
			function modifyOpts(list){
				if(list==null)return;
				$("#modifyopts").css("display","none");
				var levelNum=list.length;
				var rightNum;
				var midNum;	
				if(levelNum<6){
					for(var i=1;i<=levelNum;i++){
						$("#optstab").append("<tr><td width='45%'>"+i+"<input type='hidden' name='preNodeSort' value='"+list[i-1]['sortId']+"'/>&nbsp;<input type='text' name='preNodeName' id='preNode'"+i+"  class='inp_txt width161' value='"+list[i-1]['preNodeName']+"'/></td>"
						+"<td></td></tr><input type='hidden' name='preNodeId' value='"+list[i-1]['preNodeId']+"'/>");
					}
				}else if(levelNum<11){
					for(var i=1;i<=5;i++){
						rightNum=((i+5)>levelNum?'':(i+5));
						var sortId;
						var preNodeName;
						if(list[rightNum-1]){
							sortId=list[rightNum-1]['sortId'];
							preNodeName=list[rightNum-1]['preNodeName'];
							preNodeId=list[rightNum-1]['preNodeId'];
						}else{
							sortId="";
						}
						$("#optstab").append("<tr><td width='4%'>"+i+"<input type='hidden' name='preNodeSort' value='"+list[i-1]['sortId']+"'/></td><td width='40%'><input type='text' name='preNodeName' id='preNode'"+i+"  class='inp_txt width161' value='"+list[i-1]['preNodeName']+"'/></td>"						
						+"<td width='4%'>"+rightNum+"<input type='hidden' name='"+(rightNum==''?'':'preNodeSort')+"' value='"+sortId+"'/></td><td>"+((i+5)>levelNum?'':"<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161' value='"+preNodeName+"'/>")+"</td></tr>"
						+"<input type='hidden' name='preNodeId' value='"+list[i-1]['preNodeId']+"'/><input type='hidden' name='"+(rightNum==''?'':'preNodeId')+"' value='"+preNodeId+"'/>");
						
					}
				}else if(levelNum<34){
				//从此后均在弹出层显示, 1列
					showModifyAllOpts();
					for(var i=1;i<=levelNum;i++)
						$("#moreoptstab").append("<tr><td width='4%'>"+i+"<input type='hidden' name='preNodeSort' value='"+list[i-1]['sortId']+"'/></td><td width='29%'><input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161' value='"+list[i-1]['preNodeName']+"'/></td>"
						+"<td width='4%'></td><td></td></tr><input type='hidden' name='preNodeId' value='"+list[i-1]['preNodeId']+"'/>");
				}else if(levelNum<67){
				//2列
					showModifyAllOpts();
					for(var i=1;i<=33;i++){
						rightNum=((i+33)>levelNum?'':(i+33));
						var sortId;
						var preNodeName;
						if(list[rightNum-1]){
							sortId=list[rightNum-1]['sortId'];
							preNodeName=list[rightNum-1]['preNodeName'];
							preNodeId=list[rightNum-1]['preNodeId'];
						}else{
							sortId="";
						}
						$("#moreoptstab").append("<tr><td width='4%'>"+i+"<input type='hidden' name='preNodeSort' value='"+list[i-1]['sortId']+"'/></td><td width='29%'><input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161' value='"+list[i-1]['preNodeName']+"'/></td>"+
						"<td width='4%'>"+rightNum+"<input type='hidden' name='"+(rightNum==''?'':'preNodeSort')+"' value='"+sortId+"'/></td><td width='29%'>"+((i+33)>levelNum?'':"<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161' value='"+preNodeName+"'/>")+"</td>"
						+"<td width='4%'></td><td></td></tr><input type='hidden' name='preNodeId' value='"+list[i-1]['preNodeId']+"'/><input type='hidden' name='"+(rightNum==''?'':'preNodeId')+"' value='"+preNodeId+"'/>");
					}
				}else{
				//3列
					showModifyAllOpts();					
					for(var i=1;i<=33;i++){
						midNum=((i+33)>levelNum?'':(i+33));
						rightNum=((i+66)>levelNum?'':(i+66));
						var sortId;
						var preNodeName;
						if(list[rightNum-1]){
							sortId=list[rightNum-1]['sortId'];
							preNodeName=list[rightNum-1]['preNodeName'];
							preNodeId=list[rightNum-1]['preNodeId'];
						}else{
							sortId="";
						}
						$("#moreoptstab").append("<tr><td width='4%'>"+i+"<input type='hidden' name='preNodeSort' value='"+list[i-1]['sortId']+"'/></td><td width='29%'><input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161' value='"+list[i-1]['preNodeName']+"'/></td>"+
						"<td width='4%'>"+midNum+"<input type='hidden' name='preNodeSort' value='"+list[midNum-1]['sortId']+"'/></td><td width='29%'>"+((i+33)>levelNum?'':"<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161' value='"+list[midNum-1]['preNodeName']+"'/>")+"</td>"
						+"<td width='4%'>"+rightNum+"<input type='hidden' name='"+(rightNum==''?'':'preNodeSort')+"' value='"+sortId+"'/></td><td>"+((i+66)>levelNum?'':"<input type='text' name='preNodeName' id='preNode'"+i+" class='inp_txt width161' value='"+preNodeName+"'/>")+"</td></tr>"
						+"<input type='hidden' name='preNodeId' value='"+list[i-1]['preNodeId']+"'/><input type='hidden' name='preNodeId' value='"+list[midNum-1]['preNodeId']+"'/><input type='hidden' name='"+(rightNum==''?'':'preNodeId')+"' value='"+preNodeId+"'/>");
					}
				}
			}
			function showModifyAllOpts(){
				$("#modifyopts").val("查看选项");
				$("#modifyopts").css("display","inline");
				
				$("#levelOpts").val(0);
			}
			function mySubmit(preName,preId){
				var type=$("#confirm").val();
				if(type=="确定"){
				//新建
					var check=checkInp(1);
					if(check){
						addOrUpdate(0,preName,"addPreItem");
					}
				}else{
				//修改
					var check=checkInp(1,2);
					if(check){
						addOrUpdate(preId,preName,"updatePreItem");						
					}
				}
			}
			function addOrUpdate(preId,preName,action){
				//添加或修改时，先判断是否已存在预设项名，不能重名
				$.ajax({
				url:"<%=contextPath%>/kno/preItem!addPreItemBack.action",
				type:"post",
				data:{preName:preName,preId:preId},
				dataType:"json",
				cache:false,
				error:function(error){
					alert(error.responseText);
				},
				success:function(result){
					//如果已存在
					if(result.jumpType){
						alert("数据库已存在该预设项，请更换名称。");
					}else{//否则，可以添加/修改
						$("#form").attr("action","<%=contextPath%>/kno/preItem!"+action+".action");
						$("#form").submit();
					}
				}
				});						
						
			}
			
			
			function delAction(preId){
				var confirm=window.confirm("会将下级层级选项同步删除，是否删除？");
				if(confirm){
					window.location='<%=contextPath%>/kno/preItem!delPreItem.action?preItem.preId='+preId;
				}
			}
			function frostAction(preId){
				var confirm=window.confirm("冻结后，此预设项将在知识树中不可见，是否冻结？");
				if(confirm){
					window.location.href='<%=contextPath%>/kno/preItem!updatePreItem.action?preItem.status=0&flag=1&preItem.preId='+preId;					
				}
			}
			function recoverAction(preId){
				var confirm=window.confirm("确定恢复吗？");
				if(confirm){
					window.location.href='<%=contextPath%>/kno/preItem!updatePreItem.action?flag=2&preItem.preId='+preId;					
				}
			}
  		</script>
  		
	</head>
	
	
	<body class="overh">
		<!--面包屑导航，开始-->
		<div class="crumbs padl17"><em class="flag dib fl mr5"></em>HS知识体系<span class="ml5 mr5">&gt;</span>新增预设项</div>
        <!--面包屑导航，结束-->
        <div class="cont_outer posr">

        <div class="mt14">
        		<input class="add_defitem" id="add_defitem" type="button" value="新增预设项" onclick="newlevel('new')" />
        </div>
        <!--已建立的知识树项目，开始-->
        <table class="mt14" cellpadding="0" cellspacing="0">
        <thead>
        	<tr>
            	<th width="20%">序号</th>
                <th width="40%">预设项名称</th>
                <th width="40%">操作</th>
            </tr>
        </thead>
  <s:iterator value="page.pageResult" id="preItem" status="status">      
	  <tr>
	    <td><s:property value="(piCondition.currentPage-1)*20+#status.index+1" /></td>
	    <td><s:property value="#preItem.preName" /></td>
	    <td>
	    	<a class="mr15"
				href='<%=contextPath%>/kno/preItem!showPreItemDetail.action?pnCondition.currentPage=1&preItem.preId=<s:property value="#preItem.preId" />'>查看</a>
			<a class="mr15"
				href="javascript:modifyPreitem(<s:property value='#preItem.preId' />);">修改</a>
			<s:if test="#preItem.relCount==0">
				<a class="mr15"
					href='javascript:void(0);'
					onclick="delAction(<s:property value='#preItem.preId' />);return false;">删除</a>
			</s:if>
			<s:else>
				<span style="color:rgb(153,153,153);" class="mr15">删除</span>												
			</s:else>
			<s:if test="#preItem.status==1">
				<a class="mr15" href="javascript:void(0);" onclick="frostAction(<s:property value='#preItem.preId' />);return false;">冻结</a>
			</s:if>
			<s:if test="#preItem.status==0">
				<a class="mr15" href="javascript:void(0);" onclick="recoverAction(<s:property value='#preItem.preId' />);return false;">恢复</a>
			</s:if>	    
	    </td>
	  </tr>
	</s:iterator>
	<tr>
		<td colspan="3" class="flip">
			
			<jsp:include page="/back/jsp/common/showPage.jsp" />		
			
		</td>
	</tr>  
</table>
        <!--已建立的知识树项目，结束-->
        </div>
        
  <!--层遮罩部分-->		
		<div style="position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5; -moz-opacity:0.5;top:0;left:0;background:#000;display:none;z-index:4;" id="wrapback" class="wrapback">		
		</div>		
<!--层遮罩部分结束-->
   <form action="" method="post" name="" id="form">
   		<input type="hidden" name="preItem.preId" id="preId" value="0"/>
   		<input type="hidden" name="preItem.status" id="status" value="1"/>
        <!--新增预设项弹出窗口，开始-->
        <div class="pop_defouter dn"  id='infoWrap'>
        		<div class="pop_definner">
                		<h2 class="pop_tit color333 f14 mb20"><span class="fl" id="title">新增预设项</span><em class="flag_close2" id="cose_add"  onclick="closeManager('infoWrap');"></em></h2>
                        <div class="add_item mt20">
                        	<label class="firstLabel mr5">名称：</label><input name="preItem.preName" id="preName"  class="inp_txt width210" type="text" />
                        </div>
                        <div class="add_item mt10">
                        	<label class="firstLabel mr5" id="levelinfo">层级：</label>
                        	<select class="inp_sel width220" id="levelOpts">
                        	<option selected="selected" value="0">…请选择…</option>
                        	</select>
                        	<input type="button" style="display:none;" class="bigGray pub_btn" id="modifyopts" value="返回修改" onclick="$('#moreopts').css('display','block');"/>
                        </div>
                        <table style="width:80%;margin-left:77px;margin-top:12px;"  border="0" id="optstab"></table>
                        <!-- 
                        <div class="add_item mt10">
                        	<label class="secondLabel mr5">01</label><input class="inp_txt width181" type="text" />
                        </div>
                        <div class="add_item mt10">
                        	<label class="secondLabel mr5">02</label><input class="inp_txt width181" type="text" />
                        </div>
                        <div class="add_item mt10">
                        	<label class="secondLabel mr5">03</label><input class="inp_txt width181" type="text" />
                        </div>
                         -->
                        <div class="add_item mt10">
                        	<label class="firstLabel mr5">说明：</label>
                        	<textarea name="preItem.content" class="inp_texare shuming" value="${preItem.content }" id="content"></textarea>
                        </div>
                        <div class="add_item mt20 txtc">
                        	<input class="bigYellow pub_btn mr20" type="button" value="确定" id="confirm" onclick="mySubmit($('#preName').val(),$('#preId').val());" />
                        	<input id="close_3" class="bigGray pub_btn" type="button" value="关闭" onclick="closeManager('infoWrap');"/>
                        </div>
                </div>
        </div>
        <!--新增预设项弹出窗口，结束-->
        
        <!--显示更多层级选项，多于10个-->
				<div id="moreopts" style="position:absolute;left:26%; top:25px; width:800px; height:640px; background-color:#FFF;z-index:100007;display:none;overflow-y: auto; padding: 45px 18px 45px;text-align: center;">
					<iframe scrolling="no" frameborder="0" style="width:80%; height:100%; top:0px; left:0px; background-color:transparent; position:absolute; z-index:-1;"> 
                         </iframe>
				
					<table id="moreoptstab" width="85%"></table><br />
					<input type="button" class="bigYellow pub_btn mr20" value="确定" onclick="checkInp(2);"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="bigGray pub_btn" value="取消" onclick="closeManager('moreopts');"/>
				</div>
		<!--显示更多层级选项结束-->
    </form>
	</body>
</html>

