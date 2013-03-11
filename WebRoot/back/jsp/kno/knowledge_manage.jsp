<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>知识体系树</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/public.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/index.css" />
<script type="text/javascript"  src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
<script type="text/javascript"  src="<%=contextPath%>/back/script/attach.js" /></script>

	<script type="text/javascript">
	
		$(function(){
			$("#close_message").click(function(){
			$("#tiops").slideUp()	
			})
		})
		
		//删除知识树
	 	function delSys(ksId){
	 		var s=window.confirm("知识树删除后无法恢复，确认删除？");
	 		if(s){
	 		$.ajax({
						url : "<%=contextPath%>/kno/knowledgeSystem!delSys.action",  
						data : {"ksId" : ksId},  // 参数  
						type : "post",  
						cache : false,  
						dataType : "json",  //返回json数据  
						success:function(result){
							var bo=result.jumpType;
							if(bo){
									alert("删除成功");
									window.location.reload();
								}else{
									alert("该知识树还有子节点，不能删除");
								}    
						},
						error: function(){ 
							alert('error');
						}
				  });
			}else{
				alert("取消成功");
			}
	 	}
	 	//更改知识树状态
	 	function changeStatus(ksId,status){
	 	var k='';
	 	   if(status==2){
	 	 		k='确定送交审核知识树？'
	 		}
	 		 if(status==4){
	 		 k='确定冻结知识树？'
	 		}
	 		 if(status==3){
	 		 k='确定恢复知识树？'
	 		}
	 		var s=window.confirm(k);
	 		if(s){
	 		$.ajax({
						url : "<%=contextPath%>/kno/knowledgeSystem!changeStatus.action",  
						data : {"ksId" : ksId,
						"status":status},  // 参数  
						type : "post",  
						cache : false,  
						dataType : "json",  //返回json数据  
						success:function(result){
							var bo=result.jumpType;
							if(bo){
								if(status==2){
									alert("送交审核成功");
								}
								if(status==4){
									alert("冻结成功");
								}
								if(status==3){
										alert("恢复成功");
								}
								window.location='<%=contextPath%>/kno/knowledgeSystem!knowLedgeMain.action?querySysCondition.currentPage=1'
							}else{
								if(status==2){
									alert("送交审核失败");
								}
								if(status==4){
									alert("冻结失败");
								}
								if(status==3){
										alert("恢复失败");
								}
							}
						},
						error: function(){ 
								alert('error');  
						}
				  });
			}else{
				alert("取消成功");
			}
	 	}
	</script>

</head>
<body>

		<!--面包屑导航，开始-->

		<div class="crumbs padl17"><em class="flag dib fl mr5"></em>HS知识体系<span class="ml5 mr5">&gt;</span>首页</div>

        <!--面包屑导航，结束-->

        <div class="cont_outer">

        <!--消息得示，开始-->

        <div class="tips mt20 posr" id="tiops">
        		<em id="close_message" class="flag_close"></em>
        		<ul class="tips_ul">
					<s:iterator value="sysinfolist" id="sysinfo">
						<s:if test="#sysinfo.status==2">
                		<li><span style="display: inline-block; width: 850px;">消息通知：<strong class="colorf60 fn">[<s:property value="#sysinfo.subjectName" />]</strong>知识体系成功录入，请及时审核并发布。</span>
                			<a class="colorf60 ml20" href="<%=contextPath%>/kno/knowledgeSystem!toKnowledgeSystemRes.action?ksId=<s:property value="#sysinfo.ksId"/>">查看</a>
                			<a class="colorf60 ml20" href="<%=contextPath%>/kno/knowledgeSystem!toKnowledgeTreeRes.action?ksId=${sysinfo.ksId }">审核</a></li>
                		</s:if>
						<s:if test="#sysinfo.status==5">
                		<li><span style="display: inline-block; width: 850px;">消息通知：您发布的<strong class="colorf60 fn">[<s:property value="#sysinfo.subjectName" />]</strong>
                		因为<strong class="colorf60 fn">[<c:if test="${sysinfo.reason!=null&&sysinfo.reason!=''}">
					    <c:choose>
					    <c:when test="${fn:length(sysinfo.reason)>20}">
					    ${fn:substring(sysinfo.reason,0,20) }……
					    </c:when>
					    <c:otherwise>
					    <s:property value="#sysinfo.reason" />
					    </c:otherwise>
					    </c:choose>
					    </c:if>]</strong>未能发布，请及时修改</span>
                			<a class="colorf60 ml20" href="<%=contextPath%>/kno/knowledgeSystem!toKnowledgeTree.action?ksId=<s:property value="#sysinfo.ksId"/>">查看</a>
                			<a class="colorf60 ml20" href="<%=contextPath%>/kno/knowledgeSystem!toUpdateKnoSys.action?ksId=<s:property value="#sysinfo.ksId"/>">修改</a></li>
                		</s:if>
 					</s:iterator>
                </ul>
        </div>

        <!--消息得示，结束-->

        <!--录入，预置，开始-->
   		<div class="entry mt14">
        		<div class="entry_block fl">
                		<div class="entry_inner">
                        		<div class="entry_ico fl  mt14 ml17 dib bacp1"></div>
                                <div class="entry_word fl mt14 ml20">
                                		<h2 class="f14 color697">录入管理</h2>
                                        <p class="mt7">添加和编辑科目\章节\知识单元以及更小级的知识点<a class="ml5 colorf60" href="<%=contextPath%>/kno/knowledgeSystem!knowledgeSystemList.action">点击进入&gt;&gt;</a></p>
                                </div>
                        </div>
                </div>
                <div class="entry_block fl ml25">
                		<div class="entry_inner">
                        		<div class="entry_ico fl  mt14 ml17 dib bacp2"></div>
                                <div class="entry_word fl mt14 ml20">
                                		<h2 class="f14 color697">审核管理</h2>
                                        <p class="mt7">审核已经建立的科目知识体系<br /><a class="colorf60" href="<%=contextPath%>/kno/knowledgeSystem!knowledgeSystemListRes.action?status=2">点击进入&gt;&gt;</a></p>
                                </div>
                        </div>
                </div>
                <div class="clnull"></div>
        </div>     

        <!--录入，预置，结束-->

        <!--已建立的知识树项目，开始-->

        <div class="kno_tree_title mt14 f14 fw">己建立的知识树项目</div>
        <table cellpadding="0" cellspacing="0">
        <thead>
        	<tr>
            	<th width="20%">项目</th>

                <th width="20%">状态</th>

                <th width="30%">创建时间</th>

                <th width="30%">操作</th>
            </tr>
        </thead>
	<s:iterator value="page.pageResult" id="knowledgeSystemList">
	  <tr>
	    <td><s:property value="#knowledgeSystemList.subjectName"/></td>
	    <td>
			<s:if test="#knowledgeSystemList.status==1">未发布</s:if>
			<s:if test="#knowledgeSystemList.status==2">待审核</s:if>
			<s:if test="#knowledgeSystemList.status==3">已发布</s:if>
			<s:if test="#knowledgeSystemList.status==4">已冻结</s:if>
			<s:if test="#knowledgeSystemList.status==5">已返工</s:if>
		</td>
	    <td><s:date name="#knowledgeSystemList.versionTime"  format="yyyy-MM-dd"/></td>
	    <td>
    	<s:if test="#knowledgeSystemList.status==4">
			<a href='#' onclick='changeStatus(<s:property value="#knowledgeSystemList.ksId" />,3)'> /恢复</a>
		</s:if>
		<s:else>
			<s:if test="#knowledgeSystemList.status!=2">
				<a href='<%=contextPath%>/kno/knowledgeSystem!toKnowledgeTree.action?ksId=<s:property value="#knowledgeSystemList.ksId"/>'> 进入</a>
			</s:if>
			<s:else>
				<font color="#999"> 进入</font>
			</s:else>
			<s:if test="#knowledgeSystemList.status==1|#knowledgeSystemList.status==5">
				<a href='#' onclick='changeStatus(<s:property value="#knowledgeSystemList.ksId" />,2)'> /送交审核</a>
				<a href='<%=contextPath%>/kno/knowledgeSystem!toUpdateKnoSys.action?ksId=<s:property value="#knowledgeSystemList.ksId"/>'> /修改</a>
			</s:if>
			<s:if test="#knowledgeSystemList.status==2">
				<a href='#'> /审核中</a>
				<font color="#999"> /修改</font>
			</s:if>
			<s:if test="#knowledgeSystemList.status==3">
				<a href='#' onclick='changeStatus(<s:property value="#knowledgeSystemList.ksId" />,4)'> /冻结</a>
				<a href='<%=contextPath%>/kno/knowledgeSystem!toUpdateKnoSys.action?ksId=<s:property value="#knowledgeSystemList.ksId"/>'> /修改</a>
			</s:if>
		</s:else>
		<s:if test="#knowledgeSystemList.counts==1&&#knowledgeSystemList.status!=2">
			<a href="#" onclick='delSys(<s:property value="#knowledgeSystemList.ksId" />)'> /删除</a>
		</s:if>
		<s:else>
			<font color="#999"> /删除</font>
		</s:else>
		</td>
	  </tr>
	</s:iterator>
	<tr>
		<td colspan="4" class="flip"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
	</tr>
</table>
        <!--已建立的知识树项目，结束-->
        </div>
</body>
</html>