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
		function newlevel(type,preNodeName,levelOpts,preNodeId){
			$("#wrapback").fadeIn();
			if(type==1){
				$("#title").html("新建${preItem.preName }项");
				$("#preNodeName").val("");
			}
			else{
				 $("#title").html("修改");
				 $("#preNodeName").val(preNodeName);
				 $("#preNodeId").val(preNodeId);				 
			}
			$("#infoWrap").fadeIn();
			$("#confirmtype").val(type);
			generateOpts();
			if(type==2)
				$("#levelOpts").val(levelOpts);
		}
		function closeManager(id){
				if(id=="infoWrap"){
					$("#infoWrap").css("display","none");
					$("#wrapback").fadeOut();
				}else{
					$("#"+id).css("display","none");
				}
		}
		function generateOpts(){
			//var maxflag=$("#maxflag").val();alert(maxflag);
			var levelOpts=document.getElementById("levelOpts");
			for(var i=1;i<=99;i++){
				levelOpts.options[i]=new Option(i,i);
			}
		}	
		function checkInp(type){
			if(type){
				var preNodeName=$("#preNodeName").val();
				var levelOpts=$("#levelOpts").val();
				if(levelOpts==0){
					alert("请选择一个序号！");
					return false;
				}
				if(!preNodeName){
					alert("请输入名称！");
					return false;
				}else if($("#preNodeName").val().length>20){
					alert("名称不能超过20个字符！");
					return false;
				}
				
			}
			$("#sortId").val($("#levelOpts").val());
			return true;
		}
		
		function mySubmit(preNodeId,preId,preNodeName){
				var type=$("#confirmtype").val();
				if(type==1){
				//新建
					var check=checkInp(1);
					if(check){
						addOrUpdate(0,preId,preNodeName,"addPreNode");
					}
				}else{
				//修改
					var check=checkInp(1);
					if(check){
						addOrUpdate(preNodeId,preId,preNodeName,"updatePreNode");
					}
				}
			}
			function addOrUpdate(preNodeId,preId,preNodeName,action){
				//添加或修改时，先判断是否已存在预设项名，不能重名
				$.ajax({
					url:"<%=contextPath%>/kno/preNode!addPreNodeBack.action",
					data:{preNodeId:preNodeId,preId:preId,preName:preNodeName},
					type:"post",
					dataType:"json",
					cache:false,
					error:function(error){
						alert(error.responseText);
					},
					success:function(result){
							//如果已存在
	  				if(result.jumpType){
	  					alert("数据库已存在该预设项子项，请更换名称。");
	  				}else{//可以删除
	  					$("#form").attr("action","<%=contextPath%>/kno/preNode!"+action+".action?pnCondition.currentPage=1");
						$("#form").submit();
	  				}
						}
					});		
						
			}
  		</script>
  		<script language="javascript">
  		
  			function delAction(preNodeId){
  				var confirm=window.confirm("是否删除？");
  				if(confirm){
  					window.location="<%=contextPath%>/kno/preNode!delPreNode.action?preNode.preNodeId="+preNodeId+"&preNode.preId=${preItem.preId }";
  				}
  			}
  			function addCheck(preId){
  				$.ajax({
  					url:"<%=contextPath%>/kno/preNode!getPreNodeCount.action",
  					data:{preId:preId},
  					type:"post",
  					cache:false,
  					dataType:"json",
  					error:function(error){
  						alert(error.responseText);
  					},
  					success:function(result){
  						if(result.jumpType){
  							newlevel(1);
  						}else{
  							alert("最多只能有99项！");
  						}
  					}
  				});
  			}			
  		</script>
  		
	</head>
		<body class="overh">
		<!--面包屑导航，开始-->
		<div class="crumbs padl17"><em class="flag dib fl mr5"></em>HS知识体系<span class="ml5 mr5">&gt;</span>
		<a href="<%=contextPath %>/kno/preItem!showPreItemList.action?piCondition.currentPage=1">预设项管理</a>
		<span class="ml5 mr5">&gt;</span>${preItem.preName }预设</div>
        <!--面包屑导航，结束-->
        <div class="cont_outer">
        
        <!--已建立的知识树项目，开始-->
        <div class="mt20 txtl">
        		<input id="new_bud" class="add_defitem" type="button" value="新增${preItem.preName }" onclick="addCheck(${preItem.preId })" />
        </div>
        <table class="mt14" cellpadding="0" cellspacing="0">
        <thead>
        	<tr>
            	<th width="15%">显示序号</th>
            	<th width="15%">排序序号</th>
                <th width="40%">名称</th>
                <th width="30%">操作</th>
            </tr>
        </thead>
        <s:iterator value="page.pageResult" id="preNode" status="status">
			<tr>
				<td>
					<s:property value="(pnCondition.currentPage-1)*20+#status.index+1" />
					<s:if test="#status.last">
						<input type="hidden" value="<s:property value="#preNode.sortId" />" id="maxflag"/>
					</s:if>									
				</td>
				<td>
					<s:property value="#preNode.sortId" />
				</td>
				<td>
					<s:property value="#preNode.preNodeName" />
				</td>
				<td>										
					<a class="mr15 modify"
						href="javascript:newlevel(2,'<s:property value='#preNode.preNodeName' />',<s:property value='#preNode.sortId' />,<s:property value='#preNode.preNodeId' />);">修改</a>
					<s:if test="#preNode.relCount==0">
						<a class="mr15"
							href="javascript:void(0);"
							onclick="delAction(<s:property value='#preNode.preNodeId' />);return false;">删除</a>
					</s:if>
					<s:else>
						<span style="color:rgb(153,153,153);" class="mr15">删除</span>
					</s:else>
					<!-- <a class="mr15" href="#">冻结</a> -->
				</td>
			</tr>
		</s:iterator>
  	<tr>
		<td colspan="4" class="flip">
			
			<jsp:include page="/back/jsp/common/showPage.jsp" />		
			
		</td>
	</tr>  
</table>
        <!--已建立的知识树项目，结束-->
        </div>
        
       <!--层遮罩部分-->		
		<div id="wrapback" class="wrapback"  style='position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5; -moz-opacity:0.5;top:0;left:0;background:#000;display:none;z-index:4;'>	
		</div>		
		<!--层遮罩部分结束-->
  
        <div class="add_newdif dn" id='infoWrap'>
        	 <form action="" method="post" name="" id="form">
        		<div class="add_newdif_inner">
                		<h2 class="add_newdif_tit f14 mb20"><span class="fl" id="title">新建${preItem.preName }</span>
                		<em class="flag_close2" id="cose_mod" onclick="closeManager('infoWrap');"></em></h2>
                        <div class="add_item mt10">
                        	<label class="firstLabel mr5">序号：</label>
                        	<select class="inp_sel width210" id="levelOpts">
                        	<option selected="" value="0">…请选择…</option>	
                        	</select>
                        	<input type="hidden" name="preNode.sortId" id="sortId"/>
                        </div>
                        <div class="add_item mt10">
                        	<label class="firstLabel mr5">名称：</label>
                        	<input type="text" class="inp_txt width200" name="preNode.preNodeName" id="preNodeName" value=""/>
                        </div>
                         <div class="add_item mt20 txtc">
                        	<input class="bigYellow pub_btn mr20" type="button" value="确定" id="confirm" onclick="mySubmit($('#preNodeId').val(),${preItem.preId },$('#preNodeName').val());" />
                        	<input id="close" class="bigGray pub_btn" type="button" value="关闭" onclick="closeManager('infoWrap');"/>
                        </div>
                </div>
				<input type="hidden" id="confirmtype"/>
				<input type="hidden" name="preNode.preNodeId"  value="" id="preNodeId"/>
				<input type="hidden" name="preNode.preId" value="<s:property value='preItem.preId'/>"/>
              </form>
        </div>
   
       </body>
</html>

