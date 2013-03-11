<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<title>问题详情</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>		
        <script type="text/javascript" src="<%=contextPath%>/kindeditor/call_back_kindeditor.js"></script>
  <script type="text/javascript">
  //初始化fck编辑器
	$(document).ready(function() {
		$("#f1").validate();
    	        KE.show({
    	                    id : 'pro_context',
    	                    resizeMode : 1,
    	                    allowPreviewEmoticons : false,
    	                    allowUpload : true,
    	                    syncType : 'auto',
    	                    urlType : 'absolute',
    	                    imageUploadJson : '<%=keImageUploadJsonBackAction%>?cusid=9999',
    	                    allowFileManager : false,
    	                    items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
    	                        'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
    	                        'code', 'image', 'flash', '|']
    	                });
    	        KE.show({
    	                    id : 'pro_reply',
    	                    resizeMode : 1,
    	                    allowPreviewEmoticons : false,
    	                    allowUpload : true,
    	                    syncType : 'auto',
    	                    urlType : 'absolute',
    	                    imageUploadJson : '<%=keImageUploadJsonBackAction%>?cusid=9999',
    	                    allowFileManager : false,
    	                    items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist','|',
    	                        'textcolor', 'bgcolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons',
    	                        'code', 'image', 'flash', '|']
    	                });
    	                
	});
	
	function FCKeditor_OnComplete(editorInstance) {
			window.status = editorInstance.Description;
	}
	
	function viewCus(cusId) {
		window.location.href = "<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=" + cusId;
	}
	function rePbl(){
		var reInfo = document.getElementById("reInfo").value;
		if(trim(reInfo)== null || trim(reInfo)== ""){
			
			return false;
		}
	}
	function trim(str){ //删除左右两端的空格
　　     return str.replace(/(^\s*)|(\s*$)/g, "");
　　 }
	//管理员回复答案
	function addReProblem(){
		var pId=$("#pblId").val();
		var proContent =$("#pro_reply").val();
		var obj=document.getElementsByName('reIsResult');  //选择所有name="'test'"的对象，返回数组    
		  var check=0;    
		  for(var i=0; i<obj.length; i++){    
		    if(obj[i].checked){
		    check=1;
		    }  
		  }    
  
		if(proContent==""){
		 alert("请输入回复内容");
		 return;
		}
		$.ajax({
			url: "<%=contextPath%>/cus/repbl!addReProblemReply.action",
			data : {
			pblId : pId,
			recontent : proContent,
			reIsResult : check
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
				alert("回复失败");
			},
			success:function(result){
			alert(result.returnMessage);
			if(result.jumpType==true){
			window.location.reload();
			}
			}
			});
	}
	//保存问题标题
	function updateProblemTitle(){
		if(!window.confirm('您确定要修改问题标题吗？')){
	        return false;
	      }
	
		var pId=$("#pblId").val();
		var proTitle = document.getElementById("problem.pblTitle").value;
		if(proTitle==""){
		 alert("请输入问题标题");
		 return;
		}
		$.ajax({
			url: "<%=contextPath%>/cus/repbl!editProblem.action",
			data : {
			pblId : pId,
			pblTitle : proTitle,
			updateType:1
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
				alert('标题修改失败');
			},
			success:function(){
			alert('标题修改成功');
			window.location.reload();
			}
			});
	}
	//隐藏或者显示问题
	function hideProblem(status){
		var pId=$("#pblId").val();
		$.ajax({
			url: "<%=contextPath%>/cus/repbl!editProblem.action",
			data : {
			pblId : pId,
			pblStatus : status,
			updateType:2
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
			  if(status==1){
			  alert('设置问题隐藏失败');
			  }else if(status==0){
			  alert('取消问题隐藏失败');
			  }	
				
			},
			success:function(){		
			if(status==1){
			  alert('设置问题隐藏成功');
			  window.location.reload();
			  }else if(status==0){
			  alert('取消问题隐藏成功');
			  window.location.reload();
			  }	
			}
			});
	}
	//保存问题内容
	function updateProblemContent(){
		  if(!window.confirm('您确定要修改问题内容吗？')){
	        return false;
	      }
	
		var pId=$("#pblId").val();
		var proContent = document.getElementById("pro_context").value;
		if(proContent==""){
		 alert("请输入问题内容");
		 return;
		}
		$.ajax({
			url: "<%=contextPath%>/cus/repbl!editProblem.action",
			data : {
			pblId : pId,
			pblContent : proContent,
			updateType:3
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
				alert('问题内容修改失败');
			},
			success:function(){
			alert('问题内容修改成功');
			window.location.reload();
			}
			});
	}
	//删除答案
	function deleteReProblem(repbid){
	      var pId=$("#pblId").val();
		 if(!window.confirm('您确定要删除该条回复吗？')){
	        return false;
	      }
		$.ajax({
			url: "<%=contextPath%>/cus/repbl!editReProblem.action",
			data : {
			pblId : pId,
			repbid : repbid,
			reupdatetype:2
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
				alert('回复删除失败');
			},
			success:function(){
			alert('回复删除成功');
			window.location.reload();
			}
			});
	}
	//隐藏答案(取消隐藏答案)
	function hideReProblem(status,repid){
	    if(status==0){//用户要取消隐藏
	      if(!window.confirm('您确定要显示该条回复吗？')){
	        return false;
	      }
	    }else if(status==1){//用户要隐藏
	      if(!window.confirm('您确定要隐藏该回复吗？')){
	        return false;
	      }
	    }
	    var pId=$("#pblId").val();
		$.ajax({
			url: "<%=contextPath%>/cus/repbl!editReProblem.action",
			data : {
			pblId : pId,
			repbid : repid,
			restatus:status,
			reupdatetype:1
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
				if(status==0){//用户要取消隐藏
			     alert('该条回复设置显示失败');
			    }else if(status==1){//用户要隐藏
			      alert('该条回复设置隐藏失败');
			    }
			},
			success:function(){
			if(status==0){//用户要取消隐藏
			     alert('该条回复设置显示成功');
			}else if(status==1){//用户要隐藏
			      alert('该条回复设置隐藏成功');
			}
			window.location.reload();
			}
			});
	}
	//设置最佳答案(取消最佳答案)
	function makeBestReProblem(status,repbid){
	    var pId=$("#pblId").val();
		$.ajax({
			url: "<%=contextPath%>/cus/repbl!editReProblem.action",
			data : {
			pblId : pId,
			repbid : repbid,
			restatus:status,
			reupdatetype:3
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
				if(status==0){
			     alert('最佳答案取消失败');
			    }else if(status==1){
			      alert('设置最佳答案失败');
			    }
			},
			success:function(result){
			alert(result.returnMessage);
			if(result.jumpType==true){
			  window.location.reload();
			}		
			}
			});
	}
	
</script>

</head>
<body>
<div>
	<form name="f1" action="repbl!toProblemViewNew.action?problem.pblId=<s:property value="#problem.pblId"/>" method="post" enctype="multipart/form-data" id="f1">
	<s:hidden id="pblId" name="problem.pblId"></s:hidden>
	<s:hidden name="queryReProblemCondition.currentPage" value="1"/>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">问题详情</font>
				<font class="lists_fright"></font>
			</td >
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
			<tr>
			<td class="lists_tleft">
			<div>
			<b><font size="3">问题标题：</font></b><input type="text" name="problem.pblTitle" id="problem.pblTitle" value="${problem.pblTitle }" size="80"></input> 
			<a href="javascript:void(0);" onclick="updateProblemTitle()" id="but2"><font>【修改】</font></a>
			<s:if test="problem.pblStatus==0">
			<a href="javascript:void(0);" onclick="hideProblem(1)" id="but3"><font>【隐藏问题】</font></a>
			</s:if>  
			<s:if test="problem.pblStatus==1">
			<a href="javascript:void(0);" onclick="hideProblem(0)" id="but4"><font>【显示问题】</font></a>
			</s:if>
			</div>
			<br/>
			<div>
			<b><font size="2">回答数量：</font></b><s:property value="problem.reProblemList.size"/>
			&#12288;&#12288;
			<b><font size="2">问题类型：</font></b>
					<s:if test="problem.pblType==1">考试问题</s:if>
					<s:if test="problem.pblType==2">课程问题</s:if>
					<s:if test="problem.pblType==3">视频问题</s:if>
					<s:if test="problem.pblType==4">讲义问题</s:if>
					&#12288;&#12288;
			<b><font size="2">是否解决：</font></b>
					<s:if test="problem.pblSolv==1">已解决</s:if>
					<s:if test="problem.pblSolv==2">未解决</s:if>
			&#12288;&#12288;
			<b><font size="2">是否隐藏：</font></b>
					<s:if test="problem.pblStatus==0">未隐藏</s:if>
					<s:if test="problem.pblStatus==1">已隐藏</s:if>
			&#12288;&#12288;						
			<b><font size="2">提问者：</font></b><a href="#"
					onclick="viewCus(<s:property value="problem.Customer.cusId" />)"><s:property
						value="problem.Customer.email" /> </a>
			&#12288;&#12288;
			<b><font size="2">提问时间：</font></b><s:date name="problem.createTime" format="yyyy-MM-dd HH:mm:ss" />&#12288;&#12288;
			<b><font size="2">奖励积分：</font></b><s:property value="problem.pblTotols" />分 &#12288;&#12288;
			</div>					
			</td>
			</tr>
			<tr>
			<td class="lists_tleft">
			<table>
			<tr>
			<td><b><font size="3">问题内容：</font></b></td>
			<td><textarea id="pro_context" name="problem.pblContent" cols="100" rows="8" style="width:1000px;height:150px;visibility:hidden;">
			<s:property value='problem.pblContent' escape="flase" />
			</textarea></td>
			<td>
			<a href="javascript:void(0);" onclick="updateProblemContent()" id="but6"><font>【修改】</font></a>
			</td>
			</tr>
			</table> 
			</td>
			</tr>
			
			<s:if test="reproblem != null">
			<tr>
			<td class="lists_tleft">
			<table>
			<tr>
			<td><b><font size="3" color="red">最佳答案：</font></b></td>
			<td>
			<div align="left">
			<s:if test="reproblem.reManType==0">
			<div align="left">
					<font size="2"  color="red"><b>回复者：</b><a href="#"
					onclick="viewCus(<s:property value="reproblem.Customer.cusId" />)"><s:property
						value="reproblem.Customer.email" /></a><s:if test="reproblem.isOfficial != null">(认证)</s:if></font>&#12288;&#12288;
					<font size="2"  color="red"><b>回复时间：</b><s:date name="reproblem.reTime" format="yyyy-MM-dd HH:mm:ss" /></font><br/>
			</s:if>
			<s:if test="reproblem.reManType==1">
					<font size="2"  color="red"><b>回复者：</b>HighSo问答</font>&#12288;&#12288;			
					<font size="2"  color="red"><b>回复时间：</b><s:date name="reproblem.reTime" format="yyyy-MM-dd HH:mm:ss" /></font><br />	
					</div>				
			</s:if>
			</div>
			<textarea rows="5" cols="1000" style="width:1000px;height:100px;" disabled="disabled"><s:property value="reproblem.reInfo" escape="false" /></textarea>
			</td>																																
			<td>
			<div align="left">
			<a href="javascript:void(0);" onclick="makeBestReProblem(0,<s:property value="reproblem.reId" />)">【取消最佳答案】</a><br/>
			<a href="javascript:void(0);" onclick="deleteReProblem(<s:property value="reproblem.reId" />)">【删除回复】</a>
			</td>
			</div>
			</tr>
			</table>   
			</td>
			</tr>
			</s:if>			
						
				<s:iterator value="page.pageResult" id="repro" status="index">
					<s:if test="#repro.isResult==0">
				<tr>
				<td class="lists_tleft">
					<table>
					<tr>
					<td><b><font size="3">&#12288;&nbsp;回复<s:property value="#index.index+1+((page.currentPage-1)*page.pageSize)" />：</font></b></td>
					<td>
					<div align="left">
					<s:if test="#repro.reManType==0">
							<b><font size="2">回复者：</font></b><a href="#"
							onclick="viewCus(<s:property value="#repro.Customer.cusId" />)"><s:property
								value="#repro.Customer.email" /></a><s:if test="#repro.isOfficial != null"><font color="red">(认证)</font></s:if></font>&#12288;&#12288;
							<b><font size="2">回复时间：</font></b><s:date name="#repro.reTime" format="yyyy-MM-dd HH:mm:ss" />&#12288;&#12288;
							<b><font size="2">是否隐藏：</font></b><s:if test="#repro.reStatus==0">未隐藏</s:if>
							<s:if test="#repro.reStatus==1">已隐藏</s:if><br/>
							</s:if>
							<s:if test="#repro.reManType==1">
							<b><font size="2">回复者：</font></b>HighSo问答&#12288;&#12288;			
							<b><font size="2">回复时间：</font></b><s:date name="#repro.reTime" format="yyyy-MM-dd HH:mm:ss" />&#12288;&#12288;
							<b><font size="2">是否隐藏：</font></b><s:if test="#repro.reStatus==0">未隐藏</s:if>
							<s:if test="#repro.reStatus==1">已隐藏</s:if><br/>
							</div>				
					</s:if>
					<textarea rows="5" cols="1000" style="width:1000px;height:100px;" disabled="disabled"><s:property value="#repro.reInfo" escape="false" /></textarea>
					</td>																																
					<td>
					<div align="left">
					<a href="javascript:void(0);" onclick="makeBestReProblem(1,<s:property value="#repro.reId" />)">【设置最佳答案】</a>
					<br/>
					<a href="javascript:void(0);" onclick="deleteReProblem(<s:property value="#repro.reId" />)">【删除回复】</a>
					<br/>
					<s:if test="#repro.reStatus==0">
					<a href="javascript:void(0);" onclick="hideReProblem(1,<s:property value="#repro.reId" />)">【隐藏回复】</a>
					</s:if>
					<s:if test="#repro.reStatus==1">
					<a href="javascript:void(0);" onclick="hideReProblem(0,<s:property value="#repro.reId" />)">【显示回复】</a>
					</s:if>
					</div>
					</td>
					</tr>
					</table>   
				</td>
				</tr>
				</s:if>
				</s:iterator>				
				
				<s:if test="page.totalRecord >10">
				<tr>
				<div>
				<td>
				<table>
				<tr>
				<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp"/></td>			
				</tr>
				</table>
				</div>
				</td>			
				</tr>
				</s:if>
			  <tr>
				<td class="lists_tleft">
					<table>
					<tr>
					<td><b><font size="3">&#12288;&#12288;回复：</font></b></td>
					<td>
					<textarea id="pro_reply" name="pro_reply" cols="100" rows="8" style="width:1000px;height:150px;visibility:hidden;"></textarea>
					<div align="left">
					<input type="checkbox" id="reIsResult" name="reIsResult">设置为最佳答案</input>
					<br/><br/>
					<input type="button" onclick="addReProblem()" name="b2" value="提交回复" />
					</div>
					</td>
					</tr>
					</table>
				</td>
			  </tr>
			</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
	</table>
	</form>
</div>
  </body>
</html>