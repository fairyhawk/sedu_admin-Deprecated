<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>视频组列表</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>
    <script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
    <script language="JavaScript">

    </script>
</head>
<body>
<div>
    <form name="f1" method="post" action="vediogroup!getVedioGroupList.action">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
            <tr>
                <td class="td_wid_l">
                    <img src="<%=contextPath%>/back/images/tab_03.gif"/>
                </td>
                <td class="lists_top">
                    <font class="lists_fleft">视频组列表</font>
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
                                    <input type="text" name="searchKey" id="searchKey" value="${searchKey}"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="javascript:document.f1.submit()">查询</a></td>
                                <td style="width: 20px"></td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="<%=contextPath%>/res/vediogroup!toAddVedioGroup.action">创建视频组</a></td>
                                <td style="width: 20px"></td>

                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="<%=contextPath%>/res/vediogroup!toAddVedioList.action?queryVedioCondition.currentPage=1">添加视频到视频组</a>
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
                                视频组名称
                            </td>
                            <td>
                                上传时间
                            </td>
                            <td>
                                视频组类型
                            </td>
                            <td>
                                视频组描述
                            </td>

                            <td colspan="2">
                                操作
                            </td>
                        </tr>
                        <s:iterator value="page.pageResult" id="vedio">
                            <tr>

                                <td>
                                    <a href='vediogroup!veiwVedioList.action?vedioGroup.vgId=<s:property value="vgId"/>'><s:property value="vgName"/></a>
                                </td>
                                <td>

                                    <s:date name="vgTime" format="yyyy-MM-dd"/>
                                </td>
                                <td>
                                    <s:property value="vgType"/>
                                </td>
                                <td>
                                    <s:property value="vgNfo"/>
                                </td>


                                <td>
                                    <a href='vediogroup!deleteVedioGroup.action?vedioGroup.vgId=<s:property value="vgId"/>'
                                       onclick="return confirm('是否删除,如果删除连同组下面的关联一起删除？')">删除</a>
                                </td>
                                <td>
                                    <a href='vediogroup!toEditVedioGroup.action?vedioGroup.vgId=<s:property value="vgId"/>'> 修改</a>
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
