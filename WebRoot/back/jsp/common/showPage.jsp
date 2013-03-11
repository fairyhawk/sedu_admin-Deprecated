<%--
  Created by IntelliJ IDEA.
  UserVO: Administrator
  Date: 2008-12-22
  Time: 21:08:49
  To change this template use File | Settings | File Templates.
--%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/include/header.inc"%>
<s:if test="page != null">
    <table border="0" width="100%" height="100%" class="lists_td_ablock">
        <tr>
        	<td>
        	 共有 <s:property value="page.totalRecord"/> 条记录，当前第 <s:property value="page.currentPage"/>/<s:property value="page.totalPage"/> 页
        	</td>
        	<!-- 
            <td width="50">
            	<div align="center"><span class="STYLE1">每页显示</span></div>
            </td>
            <td width="25">
            	<div align="center"><span class="STYLE1">
                    <input name="textfield" id="pageSizeIpt" type="text" size="4" style="height:12px; margin-top:8px; width:20px; border:1px solid #999999;" /> 
                    </span></div>
            </td>
            <td class="td_wid_l">
            	<div align="center"><span class="STYLE1">
                    条</span></div>
            </td>
             -->
            <td width="45">
                <s:if test="!page.first">
                    <a href="#" onclick="goPage(1);">
                </s:if>
                <img src="<%=contextPath%>/back/images/first.gif" />
                <s:if test="!page.first">
                    </a>
                </s:if>
            </td>
            <td width="45">
                <s:if test="!page.first">
                    <a href="#" onclick="goPage(<s:property value="page.currentPage-1"/>);">
                </s:if>
              	<img src="<%=contextPath%>/back/images/back.gif" />
                <s:if test="!page.first">
                    </a>
                </s:if>
            </td>
            <td id="goPageByNumber" width="15">
            </td>
            <td width="45">
                <s:if test="!page.last">
                    <a href="#" onclick="goPage(<s:property value="page.currentPage+1"/>);">
                </s:if>
               	<img src="<%=contextPath%>/back/images/next.gif" /> 
                <s:if test="!page.last">
                    </a>
                </s:if>
            </td>
            <td width="45">
                <s:if test="!page.last">
                    <a href="#" onclick="goPage(<s:property value="page.totalPage"/>);">
                </s:if>
               	<img src="<%=contextPath%>/back/images/last.gif" />
                <s:if test="!page.last">
                    </a>
                </s:if>
            </td>
            <td width="50">
            	<div align="center"><span class="STYLE1">转到第</span></div>
            </td>
            <td width="25">
            	<div align="center"><span class="STYLE1">
                    <input name="textfield" id="pageNoIpt" type="text" size="4" style="height:12px; width:20px; border:1px solid #999999;" /> 
                    </span></div>
            </td>
            <td class="td_wid_l">
            	<div align="center"><span class="STYLE1">页 </span></div>
            </td>
            <td width="45">
            	<a href="javascript:goPageByInput()"><img src="<%=contextPath%>/back/images/go.gif" width="37" height="15" /></a>
            </td>
        </tr>
    </table>
</s:if>
<script type="text/javascript">
    function goPage(pageNum){
    	//var pageSize = document.getElementById("pageNoIpt").value;
    	//if(isNaN(pageSize) || pageSize < 1) {
    	//	pageSize = 10;
    	//}
        //var pageSizeReg = new RegExp("\\.pageSize=[0-9]*");
        //document.location="${pageUrlParms}".replace(pageNoReg,".currentPage=" + pageNum).replace(pageSizeReg, ".pageSize=" + pageSize);
        
        var pageNoReg = new RegExp("\\.currentPage=[0-9]*");
        document.location="${pageUrlParms}".replace(pageNoReg,".currentPage=" + pageNum)
    }
    
    function showPageNumber() {
    	var currentPage = <s:property value="page.currentPage-1"/><1?1:<s:property value="page.currentPage"/>;
    	var totalPage = <s:property value="page.totalPage"/>;
    	var td = document.getElementById("goPageByNumber");
    	var maxNum = currentPage>4?6:11-currentPage;
    	if(currentPage>5) {
    		td.innerHTML = td.innerHTML + "|&nbsp;&nbsp;&nbsp;&nbsp;";
    	}
    	for(var i=4; i>0; i--) {
    		if(currentPage>i) {
    			td.innerHTML = td.innerHTML + "<a href='#' onclick='goPage("+(currentPage-i)+")'>"+ (currentPage-i) +"</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    		}
    	}
    	td.innerHTML = td.innerHTML + "<font color=orange>"+currentPage+"</font>&nbsp;&nbsp;&nbsp;&nbsp;";
    	for(var i=1; i<maxNum; i++) {
    		if(currentPage+i<=totalPage) {
    			td.innerHTML = td.innerHTML + "<a href='#' onclick='goPage("+(currentPage+i)+")'>"+ (currentPage+i) +"</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    		} else {
    			break;
    		}
    	}
    	if(currentPage+maxNum<=totalPage) {
    		td.innerHTML = td.innerHTML + "|&nbsp;&nbsp;&nbsp;&nbsp;";
    	}
    }
    
    function goPageByInput() {
    	var pageNo = document.getElementById("pageNoIpt").value;
    	if(pageNo><s:property value="page.totalPage"/>)return;
    	if(/^\d+$/.test(pageNo)==false) {
    		alert("只能输入整数，请重新输入！");
    		document.getElementById("pageNoIpt").value='';
    		return;
    	}
    	if(pageNo < 1) {
    		pageNo = 1;
    	}
    	if(pageNo >= <s:property value="page.totalPage"/>) {
    		if(<s:property value="page.totalPage"/>>0)
    			pageNo = <s:property value="page.totalPage"/>;
    		else
    			pageNo=1;
    	}
    	goPage(pageNo);
    }
    
    //showPageNumber();
</script>
