<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>书籍列表</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
    <script language="JavaScript">
        function All(e)
        {
            var f = window.f1;
            for (i = 0; i < f.elements.length; i++)
            {
                if (f.elements[i].name == "bkId")
                {
                    f.elements[i].checked = e.checked;
                }
            }
        }

        function deleteAll(ObjectForm)
        {
            var i = 0;
            var f = document.f1;
            if (f.bkId.length != null)
            {
                for (var j = 0; j < f.bkId.length; j++)
                {

                    if (f.bkId[j].checked == true)
                    {
                        i++;
                    }
                }
            }
            else
            {
                if (f.bkId.checked == true)
                {
                    i++;
                }
            }
            if (i == 0)
            {
                alert("没有选择需要删除的选项！");
                return false;
            }
            if (confirm("是否删除？"))
            {
                ObjectForm.action = "<%=contextPath%>/res/books!deleteAllBooks.action";
                ObjectForm.submit();
            }
        }
        function search(ObjectForm)
        {
		$("#searchKey").val(encodeURIComponent($("#searchKey").val()));
            ObjectForm.submit();

        }
    </script>
</head>
<body>
<div>
    <form name="f1" method="post" action="<%=contextPath%>/res/books!getBooksList.action">
        <s:hidden name="queryBooksCondition.currentPage" value="1"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
            <tr>
                <td class="td_wid_l">
                    <img src="<%=contextPath%>/back/images/tab_03.gif"/>
                </td>
                <td class="lists_top">
                    <font class="lists_fleft">书籍列表</font>
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
                                    按书名/标题检索
                                    <input type="text" name="searchKey" id="searchKey" value="${queryBooksCondition.searchKey}"/>
                                    <s:if test="queryUserCondition.userType==0">
                                        <input type="button" value="添加用户"
                                               onclick="document.location.href='<%=contextPath%>/sys/user!userAdd.action'"/>
                                    </s:if>
                                </td>
                                <td style="width:20px"></td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="javascript:search(document.f1)">查询</a></td>
                                <td style="width:20px"></td>
                                <td><img src="<%=contextPath%>/back/images/del_a.gif"/></td>
                                <td><a href="<%=contextPath%>/res/books!toAddBooks.action">添加</a></td>
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
                                <input type="checkbox" name="c1" onclick="All(this);"/>
                            </td>
                            <td>
                                书名
                            </td>
                            <td>
                                作者
                            </td>
                            <td>
                                类型
                            </td>
                            <td>
                                标题
                            </td>
                            <td>
                                图书条码
                            </td>
                            <td>
                                上传时间
                            </td>

                            <td colspan="2">
                                操作
                            </td>
                        </tr>
                        <s:iterator value="page.pageResult" id="books">
                            <tr>
                                <td>
                                    <input type="checkbox" name="bkId" value='<s:property value="bkId"/>'/>
                                </td>
                                <td>
                                    <s:property value="bkName"/>
                                </td>
                                <td>
                                    <s:property value="bkAuthor"/>
                                </td>
                                <td>
                                    <s:property value="bkType"/>
                                </td>
                                <td>
                                    <s:property value="bkTitle"/>
                                </td>
                                <td>
                                    <s:property value="barcode"/>
                                </td>
                                <td>
                                    <s:date name="createTime" format="yyyy-MM-dd"/>

                                </td>

                                <td>
                                    <a href='books!deleteBooks.action?books.bkId=<s:property value="bkId"/>' onclick="return confirm('是否删除？')">删除</a>
                                </td>
                                <td>
                                    <a href='books!toEditBooks.action?books.bkId=<s:property value="bkId"/>'> 修改</a>
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
