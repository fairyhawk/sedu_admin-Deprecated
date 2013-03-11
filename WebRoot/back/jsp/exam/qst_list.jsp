<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>课程节点</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>

    <style type="text/css">
        <!--
        .STYLE1 {
            color: #999999
        }

        -->
    </style>
</head>
<body>
<div>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
        <tr>
            <td class="td_wid_l">
                <img src="<%=contextPath%>/back/images/tab_03.gif"/>
            </td>
            <td class="lists_top">
                <font class="lists_fleft">试题列表</font></td>
            <td class="td_wid_l">
                <img src="<%=contextPath%>/back/images/tab_07.gif"/>
            </td>
        </tr>
        <tr>
            <td width="12" class="lists_bor">
            </td>
            <td>
                <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
                    <tr class="lists_infobg">
                        <td>题号
                        </td>
                        <td>试题id</td>
                        <td>试题内容</td>
                        <td>
                            试题类型
                        </td>

                        <td>
                            试题难度
                        </td>

                        <td>
                            正确答案
                        </td>

                        <td>
                            试题分数
                        </td>
                        <td>
                            添加时间
                        </td>
                        <td>
                            操作
                        </td>
                    </tr>
                    <s:iterator value="qstlisttb" id="qst" status="stuts">
                        <tr>
                            <td>
                                <s:property value="#stuts.index+1"/></td>
                            <td><s:property value="#qst.qstId"/></td>
                            <td>


                                <s:if test="%{null!=#qst.qstContent&&#qst.qstContent.length()>30}">
                                    <s:property value="%{#qst.qstContent.substring(0,30)}" escape="false"/>......
                                </s:if>
                                <s:else>
                                    <s:property value="#qst.qstContent" default="-" escape="false"/>
                                </s:else>

                            </td>
                            <td>

                                <s:if test="#qst.qstType==0">单选题</s:if> <s:if test="#qst.qstType==6">主观题</s:if><s:if test="#qst.qstType==2">多选题</s:if><s:if
                                    test="#qst.qstType==3">判断题</s:if><s:if test="#qst.qstType==4">材料分析题</s:if><s:if test="#qst.qstType==5">图标题</s:if>
                            </td>

                            <td>
                                <s:if test="#qst.level==1">
                                    简单 </s:if>
                                <s:if test="#qst.level==2">
                                    普通 </s:if>
                                <s:if test="#qst.level==3">
                                    困难 </s:if></td>
                            <td>
                                <s:property value="#qst.isAsr"/>
                            </td>

                            <td>

                                <s:property value="#qst.score"/>
                            </td>

                            <td>
                                <s:date name="#qst.addtime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>

                                <a href="<%=contextPath%>/exam/exampaper!toUpdateQst.action?qstId= <s:property value="#qst.qstId"/>">修改</a><s:if
                                    test="#qst.qstsize>0"><a>删除</a></s:if><s:else><span class="STYLE1">删除</span></s:else><s:property
                                    value="#qst.qstsize"/></td>
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

            </td>
            <td>
                <img src="<%=contextPath%>/back/images/tab_20.gif"/>
            </td>
        </tr>
    </table>

</div>
</body>
</html>
