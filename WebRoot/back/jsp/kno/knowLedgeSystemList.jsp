<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>知识体系树 知识体系管理</title>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/public.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/index.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
	<script type="text/javascript">
		//跳转到新增知识树
	 	function toaddkno(){
	 	document.forms["knowledgeSystem"].action="knowledgeSystem!toAddKnoSys.action"
		document.forms["knowledgeSystem"].submit();
	 	}
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
										document.forms["knowledgeSystem"].submit();
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
									if(bo)
										if(status==2){
											alert("送交审核成功");
										}
										if(status==4){
											alert("冻结成功");
										}
										if(status==3){
											alert("恢复成功");
										}
									document.forms["knowledgeSystem"].submit();
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
	<form action="<%=contextPath %>/kno/knowledgeSystem!knowledgeSystemList.action?querySysCondition.currentPage=1" name="knowledgeSystem" method="post" >
		<!--面包屑导航，开始-->

		<div class="crumbs padl17"><em class="flag dib fl mr5"></em>HS知识体系<span class="ml5 mr5">&gt;</span>知识树管理<span class="ml5 mr5">&gt;</span>录入管理</div>
        <!--面包屑导航，结束-->

        <div class="cont_outer">

        <div class="man_default mt20 f14">

        		<label class="mr5">项目分类：</label>
        			<s:select cssClass="inp_sel width200 mr15" name="subject.subjectId" id="subjectId"
							list="subjectList" listKey="subjectId" listValue="subjectName"
							headerKey="0" headerValue="请选择所属专业" theme="simple">
					</s:select> 
        			<input class="pub_btn bigYellow" type="submit" value="查找" />
        </div>

        <div class="mt14">

        		<input class="add_defitem" type="button" value="新建结构树" onclick="toaddkno()" />

        </div>

        <!--已建立的知识树项目，开始-->

        <table class="mt14" cellpadding="0" cellspacing="0">

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
					<a href='<%=contextPath %>/kno/knowledgeSystem!toKnowledgeTree.action?ksId=<s:property value="#knowledgeSystemList.ksId"/>'> 进入</a>
				</s:if>
				<s:else>
					<font color="#999"> 进入</font>
				</s:else>
				<s:if test="#knowledgeSystemList.status==1|#knowledgeSystemList.status==5">
					<a href='#' onclick='changeStatus(<s:property value="#knowledgeSystemList.ksId" />,2)'> /送交审核</a>
					<a href='<%=contextPath %>/kno/knowledgeSystem!toUpdateKnoSys.action?ksId=<s:property value="#knowledgeSystemList.ksId"/>'> /修改</a>
				</s:if>
				<s:if test="#knowledgeSystemList.status==2">
					<a href='#'> /审核中</a>
					<font color="#999"> /修改</font>
				</s:if>
				<s:if test="#knowledgeSystemList.status==3">
					<a href='#' onclick='changeStatus(<s:property value="#knowledgeSystemList.ksId" />,4)'> /冻结</a>
					<a href='<%=contextPath %>/kno/knowledgeSystem!toUpdateKnoSys.action?ksId=<s:property value="#knowledgeSystemList.ksId"/>'> /修改</a>
				</s:if>
			</s:else>
			<s:if test="#knowledgeSystemList.counts==1&&#knowledgeSystemList.status!=2">
				<a href="#" onclick='delSys(<s:property value="#knowledgeSystemList.ksId" />)'> /   删除</a>
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
	</form>
</body>

</html>