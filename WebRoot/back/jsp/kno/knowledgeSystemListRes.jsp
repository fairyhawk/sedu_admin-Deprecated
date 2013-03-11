<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>知识体系树 审核管理</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/public.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/index.css" />
</head>
<body class="overh">

	<form action="<%=contextPath %>/kno/knowledgeSystem!knowledgeSystemListRes.action?status=2&querySysCondition.currentPage=1" name="knowledgeSystem" method="post">
		<!--面包屑导航，开始-->

		<div class="crumbs padl17"><em class="flag dib fl mr5"></em>HS知识体系<span class="ml5 mr5">&gt;</span>知识树管理<span class="ml5 mr5">&gt;</span>审核管理</div>

        <!--面包屑导航，结束-->

        <div class="cont_outer posr">

        

        <!--预设项查找，开始-->

        <div class="man_default mt20 f14">

        		<label class="mr5">项目分类：</label>
        			<s:select cssClass="inp_sel width200 mr15" name="subject.subjectId" id="subjectId"
							list="subjectList" listKey="subjectId" listValue="subjectName"
							headerKey="0" headerValue="请选择所属专业" theme="simple">
					</s:select> 
        		<!-- <label class="mr5">索引：</label>
        			<input class="inp_txt mr20" type="text" /> -->
        			<input class="pub_btn bigYellow" type="submit" value="查找" />
        </div>	

        <!--预设项查找，结束-->

        <!--已建立的知识树项目，开始-->

        <table class="mt14" cellpadding="0" cellspacing="0">

        <thead>

        	<tr>

            	<th width="20%">项目</th>

                <th width="20%">状态</th>

                <th width="20%">创建时间</th>

                <th width="40%">操作</th>

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
	
	    <td><a class="mr15" href="<%=contextPath %>/kno/knowledgeSystem!toKnowledgeSystemRes.action?ksId=<s:property value="#knowledgeSystemList.ksId"/>">进入审核</a></td>
	
	  </tr>
	</s:iterator>
	<tr>
		<td colspan="4" class="flip"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
	</tr>
</table>

        <!--已建立的知识树项目，结束-->
        </div>      
        <div class="add_newdif dn" id="del_new_son2">

        		<div class="add_newdif_inner">

           		  <h2 class="add_newdif_tit f14 mb20"><span class="fl">合格</span><em class="flag_close2" id="cose_xugai3"></em></h2>

                		<div class="suredel txtc">

                        		此知识体系合格，正式发布！

                        </div>

                         <div class="add_item mt20 txtc">

                        	<input class="bigYellow pub_btn mr20" type="button" value="确定" /><input id="close_7" class="bigGray pub_btn" type="button" value="关闭" />

                        </div>

                </div>

        </div>
	</form>
</body>

</html>