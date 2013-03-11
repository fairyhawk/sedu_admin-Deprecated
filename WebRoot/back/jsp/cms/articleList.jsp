<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css" />
<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript">
<!--
	//删除
	function del(){
		var num=0;
		var check=document.getElementsByName("delIds");
        for(var i=0;i<check.length;i++){
		if(check[i].checked==true)num++;
		}
        if(num==0){alert("请至少选择一条信息"); return;}
		if(confirm("确认删除所选信息?")==true){   
			dellgaibian=0;
			document.getElementById("articleForm").action="article!delArticle.action";
	        document.getElementById("articleForm").submit();
		}
	}
	//批量生成
	function  generateArticleHTMlAll(){
	var num=0;
		var check=document.getElementsByName("delIds");
        for(var i=0;i<check.length;i++){
		if(check[i].checked==true)num++;
		}
        if(num==0){alert("请至少选择一条信息"); return;}
		if(confirm("确认生成所选的文章？?")==true){
			document.getElementById("articleForm").action="article!generateArticleHTMlAll.action";
	        document.getElementById("articleForm").submit();
		}
	}
	//取消审核
	function articleStateToZero(){
		if(confirm("确认取消审核所选信息?")==true){   
			document.getElementById("articleForm").action="articleManager!updateArticleState.action?state=0";
	        document.getElementById("articleForm").submit();
		}
	}
	//退稿
	function articleStateToOne(){
		if(confirm("确认退稿所选信息?")==true){   
			document.getElementById("articleForm").action="article!updateArticleType.action?itemArticle.aticleType=2";
	        document.getElementById("articleForm").submit();
		}
	}
	//通过审核
	function articleStateToTwo(){
		if(confirm("确认通过审核所选信息?")==true){   
			document.getElementById("articleForm").action="article!updateArticleType.action?itemArticle.aticleType=1";
	        document.getElementById("articleForm").submit();
		}
	}
	//全选
	function check(listform,checkSign){
	 var f = document.getElementById(listform);
		with(f){
			for(var i=0;i<f.length;i++){
				if(elements[i].type=="checkbox"){
					elements[i].checked=checkSign.checked;
				}
			}
		}
	}
	function changeColumnValue(id,name){
		document.getElementById("columnid").value = id;
		document.getElementById("columnName").value = name;
		document.getElementById(obj).style.display="none";
	}
	function showColumn(){
		obj="columnList";
		document.getElementById(obj).style.display="block";
		
	}
	function closeColumn(){
		obj="columnList";
		document.getElementById(obj).style.display="none";
		
	}
	function clearColumn(){
		obj="columnList";
		document.getElementById(obj).style.display="none";
		document.getElementById('columnid').value="0";
		document.getElementById('columnName').value="";
	}
//-->
</script>
 </head>
 <body>
<div id="rightframe">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">文章列表</font>
				<font class="lists_fright">

				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
        
        <tr>
            <td class="lists_bor"></td>
            <td>
                <div class="msg-zy">
                    <form action="article!showArticleList.action" method="post" id="searchForm" name="searchForm">
                					<table class="" border="0" cellspacing="0"  cellpadding="0">
                                        <tr>
                                            <td>
                						   		文章标题:</td>
                                            <td><input type="text" name="queryArticleCondition.title" value='<s:property value="queryArticleCondition.title"/>' /></td>
                								
                						   			 <td> 所属栏目:</td><td>
                						   			  	<input type="hidden" name="queryArticleCondition.currentPage" value="1" />
                										<input id="columnid" type="hidden" name="queryArticleCondition.columnId" value="0"/>
                						    			<input id="columnName" type="text" name="columnName" value="<s:property value="columnName"/>" class="required" onclick="showColumn();" readonly="readonly" /></td><td>
                						    			<div id="columnList" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
                										    <script type="text/javascript">
                											<!--
                											addArticle = new dTree('addArticle','<%=contextPath %>/back/images/dtree');
                											addArticle.add(0,-1,'栏目列表 <a href="javascript:closeColumn();">关闭</a> <a href="javascript:clearColumn();">清空</a>');
                											<s:iterator value="columnList">
                											<s:if test="isFinally==0">
                												addArticle.add('<s:property value="columnId"/>','<s:property value="parentId"/>','<s:property value="columnName"/>','','<s:property value="explanation"/>');
                											</s:if>
                											<s:else>
                												addArticle.add('<s:property value="columnId"/>','<s:property value="parentId"/>','<s:property value="columnName"/>','javascript:changeColumnValue(\'<s:property value="columnId"/>\',\'<s:property value="columnName"/>\')','<s:property value="explanation"/>');
                											</s:else>
                											</s:iterator>
                											document.write(addArticle);
                											//-->
                											</script>
                									    </div>
                					</td>
                                        <td width="20"></td>
                                            <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                                                        <td><a href="javascript:document.searchForm.submit()">查询</a></td>
                                            <td width="20"></td>
                                            <td><img src="<%=contextPath%>/back/images/edit_a.gif"/></td>
                                                                        <td><a href="javascript:articleStateToTwo();">通过审核</a></td>
                                            <td width="20"></td>
                                            <td><img src="<%=contextPath%>/back/images/del_a.gif"/></td>
                                                                        <td><a href="javascript:del()">删除</a></td>
                                            <td width="20"></td>
                                            <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                                                        <td><a href="javascript:generateArticleHTMlAll()">生成</a></td>
                                        </tr></table>
                					</form>

                </div>
            </td>
            <td class="lists_tright lists_bor2"></td>
        </tr>
        
		<tr>
			<td class="lists_bor">
			</td>
			<td>
				<form id="articleForm" method="post">
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
						<tr class="lists_infobg">
							<td><input type="checkbox" name="all" onclick="javascript:check('articleForm',this);" /></td>
			           		<td >栏目</td>
							<td >标题</td>
							<td >作者</td>
							<td >点击数</td>
							<td >属性|状态</td>
							<td >时间</td>
							<td >操作</td>
						</tr>
			           	<s:iterator value="showList" id="atce">
				  			<tr valign="middle" align="center">
				  				<td ><input type="checkbox" name="delIds" value='<s:property value="#atce[1].articleId"/>' /></td>
				  				<td ><s:property value="#atce[0]"/></td>
				  				<td ><s:property value="#atce[1].title"/></td>
				  				<td ><s:property value="#atce[1].author"/></td>
				  				<td><s:property value="#atce[1].clickTimes"/></td>
				  				<td>
				  					<s:if test="#atce[1].aticleType==0">
				  						未审核
				  					</s:if>
				  					<s:elseif test="#atce[1].aticleType==1">
				  						通过审核
				  					</s:elseif>
				  					
								</td>
				  				<td>
				  					
				  						<s:date name="#atce[1].createDate" format="yyyy-MM-dd HH:mm:ss"/>
				  					
				  				</td>
				  				<td>
				  					<a href='article!toEditArticle.action?itemArticle.articleId=<s:property value="#atce[1].articleId"/>'>修改</a>
				  					<a target="_blank" href='<%=contextPath%>/cms/article!PreArticle.action?itemArticle.articleId=<s:property value="#atce[1].articleId"/>'> 预览</a>
				  					<a target="_blank" href='<%=contextPath%>/cms/article!generateArticleHTMl.action?queryArticleCondition.articleId=<s:property value="#atce[1].articleId"/>'> 生成</a>
				  				</td>
				  			</tr>
				  		</s:iterator>
					</table>
				</form>
			</td>
			<td class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif"/></td>
			<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
		</tr>
	</table>
</div>
</body>
</html>
