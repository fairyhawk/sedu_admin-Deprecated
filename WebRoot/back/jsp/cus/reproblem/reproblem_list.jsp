<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Highso问答</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
     <style >
		html, body { height: 100%; }
		#wrapback { min-height: 100%; position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5;width:100%;-moz-opacity:1;top:0;left:0;background:#000000;display:none;z-index:4;}
		* html #wrapback { height: 100%; }
		.fonts {font-size: 11pt;}
		
	</style>
    <script language="JavaScript">

//进入页面时，初始化查询条件
$(document).ready(function(){
	changeData();									
});

//初始化时间的显示
function changeData() {
  	var starthh = $("#hidestarthh").val();
  	var endhh= $("#hideendhh").val();
  	var hasReply= $("#hidehasReply").val();
  	var pblHot= $("#hidepblHot").val();
  	var pblSolv= $("#hidepblSolv").val();
  	var pblType= $("#hidepblType").val(); 
  	var pblStatus=$("#hidepblStatus").val();
  	
	changeSelectedData("#startHH option",starthh);
	changeSelectedData("#endHH option",endhh);
	changeSelectedData("#hasReply option",hasReply);
	changeSelectedData("#pblHot option",pblHot);
	changeSelectedData("#pblSolv option",pblSolv);
	changeSelectedData("#pblType option",pblType);
	changeSelectedData("#pblStatus option",pblStatus);
}
	
//自动赋值select
function changeSelectedData(select,hourStr){
	$.each($(select),function(){
				if(this.value == hourStr) {
					this.selected = true;
				}
			})
}

function exportEnable(){
	$('#exportExcel').attr('disabled',false);
}
    	
function closes(){
	$("#infoWrap").fadeOut();
	$("#wrapback").fadeOut();
	$('#wrapbackInfo').fadeOut();
	$('#checkOut').fadeOut();
}

//导出excel压缩包
function checkOutExcel(){
				var subjectId = $("#subjectId").val();					
				var pblHot=$("#pblHot").val();
				var pblTitle=$("#pblTitle").val();
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var startHH=$("#startHH").val();
				var endHH=$("#endHH").val();
				var useremail=$("#useremail").val();
				var userothername=$("#userothername").val();
				var hasReply = $("#hasReply").val();
				var pblSolv=$("#pblSolv").val();
				var pblStatus=$("#pblStatus").val();
				var pblType=$("#pblType").val();
				var totalRecord='<s:property value="page.totalRecord"/>';
				 $('#exportExcel').attr('disabled',true);
				 window.setTimeout(exportEnable,5000);
					$.ajax({
									url : "<%=contextPath%>/cus/repbl!exportProplemExcel.action",  
									data : {
									"subjectId":subjectId,
									"pblHot":pblHot,
									"pblTitle":pblTitle,
									"startTime":startTime,
									"startHH":startHH,
									"endTime":endTime,
									"endHH":endHH,
									"useremail":useremail,
									"userothername":userothername,
									"hasReply":hasReply,
									"pblSolv":pblSolv,
									"pblStatus":pblStatus,
									"pblType":pblType
									},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
										var ss=result.returnMessage;
										var bo=result.jumpType;
										if(bo){
											var ss='点击下载：<a href="<%=contextPath%>/excelfile1/'+ss+'.rar">导出excel</a>'
											$("#downinfo").html(ss);
											$("#wrapback").fadeIn();
											$("#checkOut").fadeIn();
											$('#wrapbackInfo').fadeIn();
										}else{
											window.location.reload();
										}
									},
									error: function(){ 
										alert('error');  
									}
				  });
		}
		
//点击查询事件
function search(){
      var subjectId=$("#subjectId").val();
 	   var form= document.getElementById("f1");	
 	   var ptitle = $("#pblTitle").val();
 	   var username=$("#userothername").val();
		$("#pblTitle").val(encodeURIComponent(ptitle));
		$("#userothername").val(encodeURIComponent(username));
		form.submit();
		$("#pblTitle").val(ptitle);
		$("#userothername").val(username);
}

//清空查询数据
function cannel(){
        document.getElementById("pblTitle").value = "";
        document.getElementById("subjectId").value = 0;
        document.getElementById("pblHot").value = -1;
        document.getElementById("startTime").value = "";
        document.getElementById("endTime").value = "";
        document.getElementById("startHH").value = "00:00:00";
        document.getElementById("endHH").value = "23:59:59";
        document.getElementById("useremail").value = "";
        document.getElementById("userothername").value = "";
        document.getElementById("hasReply").value = -1;
        document.getElementById("pblSolv").value = -1;
        document.getElementById("pblStatus").value = -1;
        document.getElementById("pblType").value = -1;

}

//查看客户信息
function viewCus(cusId){
      window.location.href = "<%=contextPath%>/cus/cus!viewCus.action?customer.cusId=" + cusId;
}
//隐藏问题
function hideProblem(){
	var subjectId = $("#subjectId").val();
	window.setTimeout(exportEnable,5000);
		$.ajax({
				url : "<%=contextPath%>/cus/repbl!exportProplemExcel.action",  
				data : {
				"subjectId":subjectId								
				},  // 参数  
				type : "post",  
				cache : false,  
				dataType : "json",  //返回json数据  
				success:function(result){
				var ss=result.returnMessage;
				var bo=result.jumpType;
				if(bo){
				}else{
					//window.location.reload();
				}
				},
				error: function(){ 
										alert('error');  
				}
			});
}
</script>
   
</head>
<body>
<div>
    <form id="f1" name="f1" method="post" action="<%=contextPath%>/cus/repbl!getProblemList.action">
        <s:hidden name="queryProblemCondition.currentPage" value="1"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
            <tr>
                <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif"></td>
                <td class="lists_top">
                    <font class="lists_fleft"> Highso问答 </font>
                    <font class="lists_fright"></font>
                </td>
                <td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif"></td>
            </tr>
            <tr>
                <td class="lists_bor"></td>
                <td>
                    <div class="msg-app">
                        <table class="" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="350">
                                <input type="hidden" value="${startHH}" id="hidestarthh"/>
								<input type="hidden" value="${endHH}" id="hideendhh"/>
								<input type="hidden" value="${hasReply}" id="hidehasReply"/>
                                <input type="hidden" value="${pblHot}" id="hidepblHot"/>
                                <input type="hidden" value="${pblSolv}" id="hidepblSolv"/>
                                <input type="hidden" value="${pblStatus}" id="hidepblStatus"/>
                                <input type="hidden" value="${pblType}" id="hidepblType"/>
                                    开始时间：<input type="text" name="startTime" readonly="readonly" id="startTime" onFocus="WdatePicker()"
										onchange="createDateOne()"	 value="${startTime}" />			-
			<select name="startHH" id="startHH">
											<option value="00:00:00">
												00:00:00
											</option>
											<option value="01:00:00">
												01:00:00
											</option>
											<option value="02:00:00">
												02:00:00
											</option>
											<option value="03:00:00">
												03:00:00
											</option>
											<option value="04:00:00">
												04:00:00
											</option>
											<option value="05:00:00">
												05:00:00
											</option>
											<option value="06:00:00">
												06:00:00
											</option>
											<option value="07:00:00">
												07:00:00
											</option>
											<option value="08:00:00">
												08:00:00
											</option>
											<option value="09:00:00">
												09:00:00
											</option>
											<option value="10:00:00">
												10:00:00
											</option>
											<option value="11:00:00">
												11:00:00
											</option>
											<option value="12:00:00">
												12:00:00
											</option>
											<option value="13:00:00">
												13:00:00
											</option>
											<option value="14:00:00">
												14:00:00
											</option>
											<option value="15:00:00">
												15:00:00
											</option>
											<option value="16:00:00">
												16:00:00
											</option>
											<option value="17:00:00">
												17:00:00
											</option>
											<option value="18:00:00">
												18:00:00
											</option>
											<option value="19:00:00">
												19:00:00
											</option>
											<option value="20:00:00">
												20:00:00
											</option>
											<option value="21:00:00">
												21:00:00
											</option>
											<option value="22:00:00">
												22:00:00
											</option>
											<option value="23:00:00">
												23:00:00
											</option>
											<option value="23:59:59">
												23:59:59
											</option>
										</select></td>
                                    <td width="350">结束时间：<input type="text" name="endTime" readonly="readonly" id="endTime" onFocus="WdatePicker()"
										onchange="createDateOne()"	 value="${endTime}" />
			-
			<select name="endHH" id="endHH">
											<option value="23:59:59">
												23:59:59
											</option>
											<option value="00:00:00">
												00:00:00
											</option>
											<option value="01:00:00">
												01:00:00
											</option>
											<option value="02:00:00">
												02:00:00
											</option>
											<option value="03:00:00">
												03:00:00
											</option>
											<option value="04:00:00">
												04:00:00
											</option>
											<option value="05:00:00">
												05:00:00
											</option>
											<option value="06:00:00">
												06:00:00
											</option>
											<option value="07:00:00">
												07:00:00
											</option>
											<option value="08:00:00">
												08:00:00
											</option>
											<option value="09:00:00">
												09:00:00
											</option>
											<option value="10:00:00">
												10:00:00
											</option>
											<option value="11:00:00">
												11:00:00
											</option>
											<option value="12:00:00">
												12:00:00
											</option>
											<option value="13:00:00">
												13:00:00
											</option>
											<option value="14:00:00">
												14:00:00
											</option>
											<option value="15:00:00">
												15:00:00
											</option>
											<option value="16:00:00">
												16:00:00
											</option>
											<option value="17:00:00">
												17:00:00
											</option>
											<option value="18:00:00">
												18:00:00
											</option>
											<option value="19:00:00">
												19:00:00
											</option>
											<option value="20:00:00">
												20:00:00
											</option>
											<option value="21:00:00">
												21:00:00
											</option>
											<option value="22:00:00">
												22:00:00
											</option>
											<option value="23:00:00">
												23:00:00
											</option>
										</select>	</td>							
                                   <td> 专业名称：<s:select list="subjectList" headerKey="0"
										headerValue="--请选择--" listKey="subjectId"
										listValue="subjectName" name="subjectId"
										id="subjectId" listTitle="subjectName">
									</s:select>								
                                </td>                              
                            </tr>
                            <tr>
                                <td>
                                    &#12288;用户名：<input type="text" name="useremail" id="useremail" value="${useremail}"/></td>
                                    <td>&#12288;&#12288;昵称：<input type="text" name="userothername" id="userothername" value="${userothername}"/>       </td>                     
                                    <td>是否回复： <select name="hasReply" id="hasReply">
                                    		<option value="-1" selected="selected">--请选择--</option>
											<option value="1" >是</option>
											<option value="0" >否</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    是否热门：<select name="pblHot" id="pblHot">
                                    		<option value="-1" selected="selected">--请选择--</option>
											<option value="1" >是</option>
											<option value="0" >否</option>
                                    </select>   </td>                                                               
                                    <td>问题标题：<input type="text" name="pblTitle" id="pblTitle" value="${pblTitle}"/></td>                                                                
                                     <td>                               
                                    	&nbsp;问题类别：<select name="pblType" id="pblType">
                                    		<option value="-1" selected="selected">--请选择--</option>
											<option value="1">考试问题</option>
											<option value="2">课程问题</option>
											<option value="3">视频问题</option>
											<option value="4">讲义问题</option>
                                    </select>                                   
                                </td>                              
                            </tr>
                            <tr>
                            <td>是否解决：<select name="pblSolv" id="pblSolv">
                                    		<option value="-1" selected="selected">--请选择--</option>
											<option value="1" >已解决</option>
											<option value="2" >未解决</option>
                                    </select>
                            </td>
                            <td>是否隐藏：<select name="pblStatus" id="pblStatus">
                                    		<option value="-1" selected="selected">--请选择--</option>
											<option value="0" >未隐藏</option>
											<option value="1" >已隐藏</option>
                                    	</select>
                            </td>                                                    
                            </tr>
                            <tr>
                                <td>
                                <img src="<%=contextPath%>/back/images/add_a.gif"/>
                                <a href="javascript:search()">查询</a>
                                <img src="<%=contextPath%>/back/images/del_a.gif"/>
                                <a href="javascript:cannel()">清空</a>
                                <img src="<%=contextPath%>/back/images/down16_16.gif"/>
                                <a href="javascript:checkOutExcel()" id="exportExcel">导出excel</a>
                                </td>
                                <td colspan="2">1.开始时间和结束时间真对的是所查询的问答的提出时间字段进行的查询;<br />2.问题状态：目前的已解决是在用户选择最佳答案后改变，不提供后台进行状态调整，但是提供最佳答案的调整功能</td>                    
                            </tr>
                        </table>
                    </div>
                </td>
                <td class="lists_tright lists_bor2"></td>
            </tr>
            <tr>
                <td width="12" class="lists_bor">
                </td>
                <td>
                    <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()"
                           onmouseout="changeback()">
                        <tr class="lists_infobg">
                        <td>序号</td>
                        <td>用户名</td>
                        <td width="6%">昵称</td>
						<td width="15%">问题标题</td>
                        <td width="9%">专业名称</td>
                        <td width="5%">问题类别</td>
                        <td>是否回复</td>
                        <td>回复数量</td>
                        <td>是否解决</td>
                        <td>是否隐藏</td>
                        <td>是否热门</td>
                        <td width="6%">提问时间</td>
                        <td width="13%">操作</td>
                        </tr>
                        <s:iterator value="page.pageResult" id="problem1" status="index">
                            <tr>
                            <td><s:property value="#index.index+1+((page.currentPage-1)*page.pageSize)" /></td>
                            <td>
                                <a href="#" onclick="viewCus(<s:property value="#problem1.cusId" />)">
                                <s:property value="#problem1.cusEmail"/>
                                </a>
                            </td>
                            <td>                              
                                <s:property value="#problem1.cusname"/>
                            </td>

                                <td title="<s:property value="#problem1.pblTitle"/>">
                                <a href="repbl!toProblemView.action?queryReProblemCondition.currentPage=1&problem.pblId=<s:property value="#problem1.pblId"/>">
                                <div class="lists_nowrap"><s:property value="#problem1.pblTitle"/></div>
                                </a>
                                </td>

                                <td>
                                    <s:property value="#problem1.subjectName"/>
                                </td>
                                <td>
                                <s:if test="#problem1.pblType==1">考试问题</s:if>
                                <s:if test="#problem1.pblType==2">课程问题</s:if>
                                <s:if test="#problem1.pblType==3">视频</s:if>
                                <s:if test="#problem1.pblType==4">讲义</s:if>   
                                </td>
                                <td>
                                <s:if test="#problem1.reflayCount==0">否</s:if>
                                <s:else>
                                	是
                                </s:else>
                                </td>
                                 <td>
                                 <s:property value="#problem1.reflayCount"/>                                   
                                </td>
                                <td>
                                <s:if test="#problem1.pblSolv==1">已解决</s:if>
                                <s:if test="#problem1.pblSolv==2">未解决</s:if>                              
                                </td>
                                <td>  
                                <s:if test="#problem1.pblStatus==0">未隐藏</s:if>
                                <s:if test="#problem1.pblStatus==1">已隐藏</s:if>                               
                                </td>
                                <td>
                                    <s:property value="#problem1.pblHot==1?'是':'否'"/>
                                </td>
                                <td>
                                    <s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <s:if test="#problem1.pblHot==1">
                                    <a href='repbl!toHotProblem.action?problem.pblHot=0&problem.pblId=<s:property value="pblId"/>'
                                       onclick="return confirm('确定取消热门问题?')">取消热门</a>
                                    </s:if>
                                    <s:else>
                                    <a href='repbl!toHotProblem.action?problem.pblHot=1&problem.pblId=<s:property value="pblId"/>'
                                       onclick="return confirm('确定置为热门问题?')">置为热门</a>
                                    </s:else>
                                    <s:if test="#problem1.pblStatus==0">
                                    <a href='repbl!toHideProblem.action?problem.pblStatus=1&problem.pblId=<s:property value="pblId"/>'
                                       onclick="return confirm('确定隐藏该问题?')">隐藏问题</a>
                                    </s:if>
                                    <s:else>
                                    <a href='repbl!toHideProblem.action?problem.pblStatus=0&problem.pblId=<s:property value="pblId"/>'
                                       onclick="return confirm('确定取消隐藏该问题?')">取消隐藏</a>
                                    </s:else>                                 
                                    <a href='repbl!deleteProblem.action?problem.pblId=<s:property value="pblId"/>'
                                           onclick="return confirm('删除问题连同答案一起删除？')">删除</a>
                                           <!-- 
                                           <a href='repbl!toEditProblem.action?problem.pblId=<s:property value="pblId"/>'>修改</a>-->
                                           <a href="repbl!toProblemView.action?queryReProblemCondition.currentPage=1&problem.pblId=<s:property value="#problem1.pblId"/>">回复</a>
                                                                              
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </td>
                <td width="16" class="lists_tright lists_bor2">
                </td>
            </tr>
            <tr>
                <td>
                    <img src="<%=contextPath%>/back/images/tab_18.gif"/>
                </td>
                <td class="lists_bottom">
                    <jsp:include page="/back/jsp/common/showPage.jsp"/>
                </td>
                <td>
                    <img src="<%=contextPath%>/back/images/tab_20.gif"/>
                </td>
            </tr>
        </table>
    </form>
</div>

	<!--层遮罩部分-->		
		<div style="position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5; -moz-opacity:0.5;top:0;left:0;width:100%;height:150%;background:#000;display:none;z-index:4;" id="wrapback">		
		<iframe id="wrapbackInfo" style="position:absolute;width:100%;height:100%;_filter:alpha(opacity=50);opacity=0.5;border-style:none;"></iframe>
		</div>
		<!--层遮罩部分结束-->
		<div align='center' class="excelout"  id='checkOut'>
			<div class="tit">
				<span class="tie fonts" id="title" >highso问答excel打包下载</span>
			</div>	
			<div id="downinfo"  class="con"></div>
			<div align="center"><input type='button' value='关闭' onclick='closes();'/></div>
		</div> 
</body>
</html>