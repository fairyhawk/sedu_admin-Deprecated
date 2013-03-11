<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>My JSP 'knowledgeTree.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/public.css" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/index.css" />
	<script type="text/javascript"  src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
	<script type="text/javascript"  src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/attach.js" /></script>
	<style type="text/css">
  			#infoWrap #info #infotab,#infoWrap #info #optstab,#infoWrap #moreopts #moreoptstab {
  				font-size:12px;
  			}
  	</style>
	<script type="text/javascript">
		//知识树审核通过
		function hege(status,ksId){
			if(status!=2){
				alert('知识树不是审核状态不能发布');
				return false;
			}
			var s=window.confirm("确认发布知识树？");
	 		if(s){
	 		status=3;
	 		$.ajax({
									url : "<%=contextPath%>/kno/knowledgeSystem!changeStatus.action",  
									data : {"ksId" : ksId,
									"status":status},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									if(bo)
									alert("发布成功");
									document.forms["knowledgeSystemRes"].submit();    
									},
									error: function(){ 
										alert('error');  
									}
				  });
			}else{
				alert("取消成功");
			}
		}
		
		//显示返工弹框
		function newlevel(status){
			if(status!=2){
				alert('知识树不是审核状态，无需返工');
				return;
			}
			$("#wrapback").fadeIn();
			
			$("#infoWrap").fadeIn();
			
			
		}
		//返工提交验证
		function redo(ksId){
			var reason=$("#reason").val();
			if(reason==''){
				alert('请给出返工原因！');
				return;
			}
			if(reason.length>300){
				alert('返工原因不能超出300字');
				return;
			}
			$.ajax({
				url:"<%=contextPath%>/kno/knowledgeSystem!redo.action",
				data:{"ksId":ksId,"reason":reason},
				type:"post",
				dataType:"json",
				cache:false,
				error:function(){
					alert('返工出现ERROR');
				},
				success:function(result){
					if(result.jumpType){
						alert('成功返工，等待接收者处理');
					}else{
						alert('返工失败');
					}					
					window.location="<%=contextPath %>/kno/knowledgeSystem!knowledgeSystemListRes.action?status=2";
				}
			})
		}
		//关闭弹框
		function closeManager(id){
			if(id=="infoWrap"){
				$("#infoWrap").css("display","none");
				$("#wrapback").fadeOut();
			}else{
				$("#"+id).css("display","none");
			}
		}
	</script>
  </head>
  
  <body>
  <!--面包屑导航，开始-->
<div class="crumbs padl17"><em class="flag dib fl mr5"></em>HS知识体系<span class="ml5 mr5">&gt;</span>知识体系管理<span class="ml5 mr5">&gt;</span><a href='<%=contextPath %>/kno/knowledgeSystem!knowledgeSystemList.action?querySysCondition.currentPage=1'>录入管理</a><span class="ml5 mr5">&gt;</span>审核知识树</div>
<!--面包屑导航，结束-->
<form action="<%=contextPath %>/kno/knowledgeSystem!knowledgeSystemListRes.action?status=2" method="post" name="knowledgeSystemRes">
  <table class="treeouter" width="800" border="0" cellpadding="0" cellspacing="0">
          <tr style="margin:0; padding:0">
            <td width="255" bgcolor="#e9f4fe" valign="top" style="border:none; text-align:left">
            	<div style="margin-left:140px;" class="current_project f14">
            			<div><input type="button" value="合格" onclick='hege(<s:property value="sys.status"/>,<s:property value="sys.ksId"/>);'/><input type="button" value="返工"  onclick="newlevel(<s:property value="sys.status"/>);"/></div>
                		<h2 class="f14">项目：<s:property value="sys.subjectName"/><a href='<%=contextPath %>/kno/knowledgeSystem!toUpdateKnoSys.action?ksId=<s:property value="sys.ksId"/>'>修改</a></h2>
                        <div>版本：<s:date name="sys.versionTime"  format="yyyy-MM-dd"/></div>
                        <em class="rect"></em>
                </div>
                <div class="attach_tree">
               <script type="text/javascript">
										d = new dTree('d','<%=contextPath%>/back/images/dtree');
										
										var flag = true;
										<s:iterator value="sysTree">
									
										if(${parentId}==-1){
										d.add(<s:property value="sysNode.ksnId"/>,-1,'<a href="<%=contextPath %>/kno/knowledgeSystem!knowledgeTreeZiRes.action?ksId=${sys.ksId}&ksnId=${sysNode.ksnId}" target="ziname">项目:<s:property value="sys.subjectName" escape="false"/></a>');
											}else{
												d.add(${ksnId},${parentId},'<a href="<%=contextPath %>/kno/knowledgeSystem!knowledgeTreeZiRes.action?ksId=${sys.ksId}&ksnId=${ksnId}" target="ziname">${nodeName}</a>');
											}
										</s:iterator>
										document.write(d);
									</script>
				</div>
                </td>
                <td width="70%"><iframe frameborder="0" width="100%" height="800px;" name="ziname" src="<%=contextPath %>/kno/knowledgeSystem!knowledgeTreeZiRes.action?ksnId=<s:property value="ksnId"/>&ksId=<s:property value="sys.ksId"/>"></iframe></td>
              </tr>
	</table>

			<!--层遮罩部分-->		
		<div style="position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5; -moz-opacity:0.5;top:0;left:0;width:100%;height:100%;background:#000;display:none;z-index:4;" id="wrapback">		
		</div>		
		<!--层遮罩部分结束-->
		<!--显示信息区-->		
		<div align='center' class="zs_gl"  id='infoWrap'>
			<div class="tit">
				<span class="tie" id="title">返工</span>
			</div>	
			
				<div id="info"  class="con">
					<table width="100%" id="infotab" cellpadding="7px;">
						<tr>
							<td width="25%">接手人：</td>
							<td><input type="text" name="" value="${sys.author }" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td width="25%">返工原因：</td>
							<td>
							<textarea name="" rows="10" cols="50" value="" id="reason"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="button" value="确定" id="confirm" onclick="redo(<s:property value="sys.ksId"/>);"/>							
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="取消" id="close" onclick="closeManager('infoWrap');"/>
							</td>	
												
						</tr>
						
					</table>
					
				</div>
				
				
			
		</div> 
		<!--显示信息区结束-->
 	 </form>
  </body>
</html>
