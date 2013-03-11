<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>knoledgeSystemZiList.html</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/public.css" />
    <link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/index.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" ></script>
	<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/attach.js" ></script>
	<script type="text/javascript">
	$().ready(function() {
			$("#sysNodeList").validate();
			$("#sysNodeUpdate").validate();
		});
	
		//选择预设项
		function onchangePreItem(preId,num){
		selectNum=num;
		$.ajax({  
					url : "<%=contextPath%>/kno/knowledgeSystem!getPreNodeById.action",  
					data : {'preId' : preId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:onchangecallback  
					});
		}
		
		function onchangecallback(result){   
				var str="<option value=-1>请选择</option>";  
				for(var i=0;i<result.entity.length;i++){  
					str+="<option value='"+result.entity[i].preNodeId+"'>"+result.entity[i].preNodeName+"</option>"  
				}  
				if(selectNum==1){
				$("#preNodeId").html(str);
				}
				if(selectNum==2){  
				$("#upPreNodeId").html(str);
				}  
			 }  
		//验证排序序号	 
		function test(count,type,sortId){
			var a=$('#sortId').val();
			var b=$('#sortId2').val();
			var patrn=/^[0-9]{1,20}$/; 
			if(type==1){
				if(!patrn.exec(a)){
					alert("序号必须是数字");
					$("#sortId").val(count);
				}
				if(a>count){
					alert('排列序号不能超过'+count);
					$("#sortId").val(count);
				}
			}
			if(type==2){
				var sortId=$('#sortIdHidden').val();
				if(!patrn.exec(b)){
					alert("序号必须是数字");
					$("#sortId2").val(sortId);
				}
				if(b>count){
					alert('排列序号不能超过'+count);
					$("#sortId2").val(sortId);
				}
			}
		}
	
	//新建节点的子节点
	function newSysNode(ksnId){
		document.forms["sysNodeList"].action='<%=contextPath %>/kno/knowledgeSystem!toAddSysNode.action?ksnId='+ksnId
		document.forms["sysNodeList"].submit();
	}
	//删除知识树节点
		function delSysNode(ksnId){
	 		var s=window.confirm("知识树节点删除后无法恢复，确认删除？");
	 		if(s){
	 		$.ajax({
									url : "<%=contextPath%>/kno/knowledgeSystem!delSysNode.action",  
									data : {"ksnId" : ksnId},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									if(bo){
										alert("删除成功");
										document.forms["sysNodeList"].action='knowledgeSystem!toKnowledgeTree.action?ksId=<s:property value="sysNode.ksId"/>&ksnId=<s:property value="ksnId"/>';
										document.forms["sysNodeList"].target="rightFrame"
										document.forms["sysNodeList"].submit();    
									}else{
										alert("该节点仍有子节点，不能删除");
									}
									},
									error: function(){ 
										alert('error');  
									}
				  });
			}else{
				alert("取消成功");
			}
			$('#new_son1').fadeOut();
		}
		
		//加载修改节点
		function toUpdate(ksnId,nodeId,nodeName,sortId){
			$.ajax({
									url : "<%=contextPath%>/kno/knowledgeSystem!getSysNodeInfo.action",  
									data : {"ksnId" : ksnId},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var nodePreMid=result.entity;
									var status=result.jumpType;
									var s='<option value="-1">请选择</option>';
									
									if(status){
									'<s:iterator value="preItemlist" id="preItemlist" >'
									if(nodePreMid.preId=='<s:property value="#preItemlist.preId"/>'){
									s+="<option value="+nodePreMid.preId+' selected="selected">'+'<s:property value="#preItemlist.preName" escape="false"/>'+'</option>';
									}else{
									s+="<option value="+'<s:property value="#preItemlist.preId"/>'+">"+'<s:property value="#preItemlist.preName" escape="false"/>'+"</option>";
									}
									'</s:iterator>'
									}else{
									'<s:iterator value="preItemlist" id="preItemlist" >'
									s+="<option value="+'<s:property value="#preItemlist.preId"/>'+">"+'<s:property value="#preItemlist.preName" escape="false"/>'+"</option>";
									'</s:iterator>'
									}
									
									$('#nodeId').html(nodeId);
									$('#nodeIdHidden').val(nodeId);
									$('#sortIdHidden').val(sortId);
									$('#sortId2').val(sortId);
									$('#ksnId').val(ksnId);
									$('#nodeName').val(nodeName);
									$('#preId').html(s);
									
									var t='<option value="-1">请选择</option>';
									if(status){
									t+="<option value="+nodePreMid.preNodeId+' selected="selected">'+nodePreMid.preNodeName+'</option>';
									}
									$('#upPreNodeId').html(t);
									$('#new_son1').fadeIn()
									$(".mask").show()
									},
									error: function(){ 
										alert('error');  
									}
				  });
		}
		
	</script>
  </head>
  
  <body>
  <form action='<%=contextPath %>/kno/knowledgeSystem!addSysNode.action' id="sysNodeList" name="sysNodeList" method="post" target="_parent">
  <table class="treeouter" width="800" border="0" cellpadding="0" cellspacing="0">
  <tr style="margin:0; padding:0">
  <td valign="top" style="border:none; text-align:left">
  <div class="cont_outer">
            	<div class="jiegou_tab mt20">
                		<div class="tab_outer f14">
                        		<a class="tablink width95" id="superlink95" href="#">结构</a>
                                <a class="tablink width95 ml2"  href='<%=contextPath %>/kno/resolve!resolveList.action?ksnId=<s:property value="ksnId"/>'>精讲解析</a>
                                <a class="tablink width95 ml2" href='<%=contextPath %>/kno/qstRel!toQstRelList.action?ksnId=<s:property value="ksnId"/>'>关联试题</a>
                                <a class="tablink width130 ml2" href='<%=contextPath %>/kno/videoRel!toVideoRelList.action?ksnId=<s:property value="ksnId"/>'>关联课程视频</a>
                        		<a class="tablink width95 ml2" href="#">课程讲义</a>
                        </div>
                </div>
  </div>
   <div class="mianbao"><em class="flag dib fl mr5"></em>
  			<s:iterator value="sysNodeList2" id="s" >
			<a href='<%=contextPath %>/kno/knowledgeSystem!knowledgeTreeZi.action?ksnId=<s:property value="#s.ksnId"/>'><s:property value="#s.nodeName"/><span class="ml5 mr5">&gt;</span></a>
			</s:iterator>
   </div>
   <div class="kno_tree_title mt10 f14 fw">新建</div>
                <div class="man_default f14">
                <input type="hidden" name="sysNode.level" value='<s:property value="sNode.level+1"/>'/>
	  			<input type="hidden" name="sysNode.ksId" value='<s:property value="sNode.ksId"/>'/>
	  			<input type="hidden" name="sysNode.parentId" value='<s:property value="sNode.ksnId"/>'/>
        		<div class="fl mb20 height25 ml15">
				<s:if test="#count<9">
	  			<input  type="text"    value='<s:property value="sysNode.nodeId" escape="false"/>00${count+1 }' disabled="disabled" />
	  			<input  type="hidden" name="sysNode.nodeId"   value='<s:property value="sysNode.nodeId" escape="false"/>00${count+1 }' />
	  			</s:if>
	  			<s:elseif test="#count<99">
	  			<input  type="text"    value='<s:property value="sysNode.nodeId" escape="false"/>0${count+1 }'  disabled="disabled"/>
	  			<input  type="hidden" name="sysNode.nodeId"   value='<s:property value="sysNode.nodeId" escape="false"/>0${count+1 }'  />
	  			</s:elseif>
	  			<s:else>
	  			<input  type="text"   value='<s:property value="sysNode.nodeId" escape="false"/>${count+1 }'  disabled="disabled"/>
	  			<input  type="hidden" name="sysNode.nodeId"   value='<s:property value="sysNode.nodeId" escape="false"/>${count+1 }'  />
	  			</s:else>
				</div>
				<div class="fl mb20 height25 ml15">
				<label class="mr5 ml15"><font color="#ff0000">*</font>名称：</label><input name="sysNode.nodeName"   class="inp_txt mr20 {required:true,maxlength:50}" type="text" />
				</div>
				<div class="fl mb20 height25 ml15">
				<label class="mr5"><font color="#ff0000">*</font>排序：</label><input class="inp_txt mr20 width95"  type="text"  name="sysNode.sortId" onblur='test(<s:property value="count+1" />,1);'  id="sortId" value='<s:property value="count+1" escape="false"/>'  />
				<label class="mr5">预设项：</label>
				<s:select cssClass="inp_sel mr20 width100" name="nodePreMid.preId"  list="preItemlist" listKey="preId" listValue="preName" headerKey="0"
				headerValue="请选择预设项" theme="simple" onchange="onchangePreItem(this.value,1);"/>
				<select class="inp_sel mr20 width100" name="nodePreMid.preNodeId" id="preNodeId" >	
							<option value="-1" selected="selected"> 请选择</option>
				</select>				
				</div>
				<div class="fl mb20 height25 ml15">
				<input class="pub_btn bigYellow" type="submit" value="确定" />
				</div>
				<div class="clnull"></div>
   </div>
    </tr>
   </table>
        <div class="kno_tree_title mt14 f14 fw">子节点列表</div>
 <table cellpadding="0" cellspacing="0" >
        <thead>
        	<tr align="center">
            	<th width="20%">ID</th>
                <th width="20%">名称</th>
                <th width="20%">预设项</th>
                <th width="30%">操作</th>
            </tr>
        </thead>

  
              <s:iterator value="sysNodeList" id="sysNodeList">
                <tr>
                  <td><s:property value="#sysNodeList.nodeId"/></td>
                  <td><s:property value="#sysNodeList.nodeName"/></td>
                  <td><s:property value="#sysNodeList.preNodeName"/>&nbsp;</td>
                  <td><a class="mr15" href='javascript:toUpdate(<s:property value="#sysNodeList.ksnId"/>,"<s:property value="#sysNodeList.nodeId"/>","<s:property value="#sysNodeList.nodeName"/>","<s:property value="#sysNodeList.sortId"/>");'>修改</a>
                  <s:if test="#sysNodeList.counts!=0">
                  <font color="#999"> /   删除</font>
                  </s:if><s:else>
                  <a href='#' class="mr15 " onclick='delSysNode(<s:property value="#sysNodeList.ksnId"/>);'>/删除</a>
                  </s:else>
                </tr>
              </s:iterator>
       
  </table>
  
  </form>
  <form action='<%=contextPath %>/kno/knowledgeSystem!updateSysNode.action' id="sysNodeUpdate"  method="post" target="_parent">
  <div class="add_newdif dn" id="new_son1">
        		<div class="add_newdif_inner">
        				<input type="hidden" name="sysNode.ksnId" id="ksnId" value='<s:property value="sysNode.ksnId"/>'/>
        				<input  type="hidden" name="sysNode.nodeId"  id="nodeIdHidden"  value='<s:property value="sysNode.nodeId" escape="false"/>' />
        				<input type="hidden"  id="sortIdHidden" value=0 />
                		<h2 class="add_newdif_tit f14 mb20"><span class="fl upd">修改</span><em class="flag_close2" id="cose_xugai2"></em></h2>
                        <div class="add_item mt10" style="clear:both">
                        	<label class="firstLabel mr5 fl">项目ID：</label><span class="fl lin24" id="nodeId"></span>
                            <div class="clnull"></div>
                        </div>
                        <div class="add_item" style="clear:both">
                        	<label class="firstLabel mr5"><font color="#ff0000">*</font>项目名称：</label><input name="sysNode.nodeName" class="inp_txt width200 {required:true,maxlength:50}" type="text" id="nodeName"/>
                        </div>
                         <div class="add_item" style="clear:both">
                        	<label class="firstLabel mr5"><font color="#ff0000">*</font>排序：</label><input class="inp_txt mr20 width95"  type="text"  name="sysNode.sortId" onblur='test(<s:property value="count+1" />,2);'  id="sortId2" value='<s:property value="count+1" escape="false"/>'  />
                        </div>
                        <div class="add_item" style="clear:both">
                        <label class="firstLabel mr5">预设项：</label><select class="inp_txt width200" name="nodePreMid.preId" id="preId" onchange="onchangePreItem(this.value,2);"></select>
                        </div>
                         <div class="add_item" style="clear:both">
                        <label class="firstLabel mr5">预设子项：</label><select class="inp_txt width200" name="nodePreMid.preNodeId" id="upPreNodeId"></select>
                        </div>
                         <div class="add_item mt20 txtc">
                        	<input class="bigYellow pub_btn mr20"  type="submit" value="确定" /><input id="close_6" class="bigGray pub_btn"  type="button" value="关闭" />
                        </div>
                </div>
        </div>
   </form>
  </body>
</html>
