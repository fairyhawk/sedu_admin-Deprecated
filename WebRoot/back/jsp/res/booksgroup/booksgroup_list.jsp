<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>书籍组列表</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>
    <script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
    <script language="JavaScript">

    </script>
</head>
<body>
<div>
    <form name="f1" method="post" action="booksgroup!getBooksGroupList.action">
        <s:hidden name="queryBooksgroupCondition.currentPage" value="1"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
            <tr>
                <td class="td_wid_l">
                    <img src="<%=contextPath%>/back/images/tab_03.gif"/>
                </td>
                <td class="lists_top">
                    <font class="lists_fleft">书籍组列表</font>
                    <font class="lists_fright">

                    </font>
                </td>
                <td class="td_wid_r">
                    <img src="<%=contextPath%>/back/images/tab_07.gif"/>
                </td>
            </tr>

            <tr>
                <td class="lists_bor"></td>
                <td>
                    <div class="msg-app">
                        <table class="" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td>
                                    按组名检索：
                                    <input type="text" name="searchKey" id="searchKey" value="${searchKey}"/>
                                </td>
                                <td style="width: 20px"></td>

                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="javascript:document.f1.submit()">查询</a></td>
                                <td style="width: 20px"></td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="<%=contextPath%>/res/booksgroup!toAddBooksGroup.action">创建书籍组</a></td>
                                <td style="width: 20px"></td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="<%=contextPath%>/res/booksgroup!toAddBooksList.action?queryBooksCondition.currentPage=1">添加书籍到书籍组</a>
                                </td>
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
                            <td>
                                书籍组名称
                            </td>
                            <td>
                                书籍组类型
                            </td>
                            <td>
                                创建时间
                            </td>
                            <td>
                                书籍组描述
                            </td>
                            <td colspan="2">
                                操作
                            </td>
                        </tr>
                        <s:iterator value="page.pageResult" id="booksgroup">
                            <tr>

                                <td>
                                    <a
                                            href='booksgroup!viewBooksList.action?booksGroup.bgId=<s:property value="bgId"/>'><s:property
                                            value="bgName"/>
                                    </a>
                                </td>
                                <td>
                                    <s:property value="bgType"/>
                                </td>
                                <td>
                                    <s:date name="createTime" format="yyyy-MM-dd"/>

                                </td>
                                <td>
                                    <s:property value="bgInfo"/>
                                </td>
                                <td>
                                    <a
                                            href='booksgroup!deleteBooksGroup.action?booksGroup.bgId=<s:property value="bgId"/>'
                                            onclick="return confirm('是否删除,如果删除连同组下面的关联一起删除？')">删除</a>
                                </td>
                                <td>
                                    <a
                                            href='booksgroup!toEditBooksGroup.action?booksGroup.bgId=<s:property value="bgId"/>'>
                                        修改</a>
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
</div>
</body>
</html>
