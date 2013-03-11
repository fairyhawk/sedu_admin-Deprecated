<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>视频列表</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
    <script language="JavaScript">
        function All(e)
        {
            var f = window.f1;
            for (i = 0; i < f.elements.length; i++)
            {
                if (f.elements[i].name == "voId")
                {
                    f.elements[i].checked = e.checked;
                }
            }
        }

        function deleteAll(ObjectForm)
        {
            var i = 0;
            var f = document.f1;
            if (f.voId.length != null)
            {
                for (var j = 0; j < f.voId.length; j++)
                {

                    if (f.voId[j].checked == true)
                    {
                        i++;
                    }
                }
            }
            else
            {
                if (f.voId.checked == true)
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
                ObjectForm.action = "<%=contextPath%>/res/vedio!deleteAllVedio.action";
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
    <form name="f1" method="post" action="<%=contextPath%>/res/vedio!getVedioList.action">
      <s:hidden name="queryVedioCondition.currentPage" value="1"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
            <tr>
                <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif"></td>
                <td class="lists_top">
                    <font class="lists_fleft"> 视频列表 </font>
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
                                <td>
                                    按视频名/讲师名检索：
                                      <input type="text" name="searchKey" id="searchKey" value="${queryVedioCondition.searchKey}"/>
                                </td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="javascript:search(document.f1)">查询</a></td>
                                <td style="width:20px"></td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="<%=contextPath%>/res/vedio!toAddVedio.action">添加</a></td>

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
                                视频名称
                            </td>

                            <td>
                                课程节点名称
                            </td>

                            <td>
                                上传时间
                            </td>
                            <td>
                                首选视频
                            </td>

                            <td>
                                试听
                            </td>
                            <td>
                                视频视频路径
                            </td>
                            <td>
                                操作
                            </td>
                        </tr>
                        <s:iterator value="page.pageResult" id="vedio">
                            <tr>
                                <td>
                                    <input type="checkbox" name="voId" value='<s:property value="voId"/>'/>
                                </td>

                                <td>
                                    <s:property value="voName"/>
                                </td>


                                <td>
                                    <s:property value="kpointName"/>
                                </td>

                                <td>
                                    <s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>
                                </td>

                                <td>
                                    <s:if test="voRadOne == 0">
                                        否
                                    </s:if>
                                    <s:else>
                                        是
                                    </s:else>
                                </td>

                                <td>
                                    <s:if test="isAudition == 0">
                                        不可以
                                    </s:if>
                                    <s:else>
                                        可以
                                    </s:else>
                                </td>

                                <td>
                                    <s:property value="voUrl"/>
                                </td>
                                <td>
                                    <a href='vedio!deleteVedio.action?vedio.voId=<s:property value="voId"/>' onclick="return confirm('是否删除？')">删除</a>|
                                    <a href='vedio!toEditVedio.action?vedio.voId=<s:property value="voId"/>'> 修改</a>
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
</body>
</html>
