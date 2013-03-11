<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>课程节点</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>
    <link type="text/css" rel="stylesheet" href="<%=contextPath%>/back/script/calendar/calendar.css"/>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>

    <script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar-zh.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/calendar/calendar-setup.js"></script>
    <script type="text/javascript" language="javascript">

        /**
         编码含有中文字符字段
         */
        function encodeCommon()
        {
            $("#keyWorld").val(encodeURIComponent($("#keyWorld").val()));

            return true;
        }


    </script>
</head>
<body>
<div>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
        <tr>
            <td class="td_wid_l">
                <img src="<%=contextPath%>/back/images/tab_03.gif"/>
            </td>
            <td class="lists_top">
                <font class="lists_fleft">成绩查询列表</font></td>
            <td class="td_wid_l">
                <img src="<%=contextPath%>/back/images/tab_07.gif"/>
            </td>
        </tr>
        <tr>
            <td width="12" class="lists_bor">
            </td>
            <td>
                <div class="msg-app">
                    <form action="<%=contextPath %>/exam/examrecord!ExamRecordAll.action" method="get" onsubmit="encodeCommon()">
                        <table width="100%" border="0">
                            <tr>
                                <td>关键字:</td>
                                <td><input type="text" name="queryExampaperRecordCondition.searchKey"
                                           value='<s:property value="queryExampaperRecordCondition.searchKey"/>' id="keyWorld"/></td>
                                <td>
                                    <select name="queryExampaperRecordCondition.tiaojian">
                                        <OPTION selected value="0">-检索条件-</OPTION>
                                        <OPTION value="1">试卷标题</OPTION>
                                        <OPTION value="2">学员昵称/邮箱</OPTION>
                                        <OPTION value="3">得分</OPTION>
                                        <OPTION value="4">用时</OPTION>
                                        <OPTION value="5">得分率</OPTION>
                                        <OPTION value="6">使用人数</OPTION>
                                    </select>
                                </td>
                                <td>
                                    <s:select name="queryExampaperRecordCondition.subject_id" list="subjectList" listKey="subjectId" listValue="subjectName"
                                              headerKey="0"
                                              headerValue="-项目-" theme="simple"> </s:select>

                                </td>
                                <td><SELECT name="queryExampaperRecordCondition.eptype">
                                    <OPTION selected value="0">-试卷类型-</OPTION>
                                    <option value="1">真题测试</option>
                                    <option value="2">仿真自测</option>
                                    <option value="3">单元练习</option>
                                    <option value="4">专题练习</option>
                                </SELECT></td>
                                <td>时间</td>
                                <td>
                                    <input type="text" name="queryExampaperRecordCondition.startTime" onclick="return showCalendar('startTime', 'y-mm-dd')"
                                           id="startTime"/></td>
                                <td>至</td>
                                <td><input type="text" name="queryExampaperRecordCondition.endTime" onclick="return showCalendar('endTime', 'y-mm-dd')" id="endTime"/>
                                    <input type="hidden" name="queryExampaperRecordCondition.currentPage" value="1"/>
                                </td>

                                <td><input name="提交" type="submit" value="搜索"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </td>
            <td class="lists_tright lists_bor2"></td>
        </tr>
        <tr>
            <td class="lists_bor"></td>
            <td>
                <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
                    <tr class="lists_infobg">
                        <td><strong>题号 </strong></td>
                        <td><strong>学员帐号</strong></td>
                        <td><strong>项目</strong></td>
                        <td><strong>
                            试卷名称 </strong></td>

                        <td><strong>
                            试卷类型</strong></td>

                        <td><strong>
                            使用人数 </strong></td>

                        <td><strong>
                            试卷满分 </strong></td>
                        <td><strong>
                            得分 </strong></td>
                        <td><strong>
                            用时（分） </strong></td>
                        <td><strong>得分率</strong></td>
                        <td><strong>提交时间</strong></td>
                    </tr>
                    <s:iterator value="page.pageResult" id="examrecord" status="stuts">
                        <tr>
                            <td>
                                <s:property value="#stuts.index+1"/></td>
                            <td><s:property value="#examrecord.email"/></td>
                            <td>


                                <s:property value="#examrecord.subjectName"/></td>
                            <td>

                                <s:property value="#examrecord.epName"/>
                            </td>

                            <td>
                                <s:if test="#examrecord.eptype==1">
                                    真题测试 </s:if>
                                <s:if test="#examrecord.eptype==2">
                                    仿真自测 </s:if>
                                <s:if test="#examrecord.eptype==3">
                                    单元练习 </s:if>
                                <s:if test="#examrecord.eptype==4">
                                    专题练习 </s:if></td>
                            <td>
                                <s:property value="#examrecord.joinNum"/></td>

                            <td>

                                <s:property value="#examrecord.epTotelScore"/></td>

                            <td>
                                <s:property value="#examrecord.userScore"/></td>
                            <td>

                                <s:property value="#examrecord.testTime"/></td>
                            <td><s:property value="#examrecord.accuracy"/> %</td>
                            <td><s:date name="#examrecord.addtime" format="yyyy-MM-dd HH:mm:ss"/></td>
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