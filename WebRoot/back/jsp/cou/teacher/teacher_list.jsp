<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>教师列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
    <script type="text/javascript">
        function delTeacher()
        {
            if (window.confirm("确认删除这些教师吗？"))
            {
                document.cusForm.action = "<%=contextPath%>/cou/teacher!deleteTeacher.action?queryTeacherCondition.currentPage="+<s:property value = "page.currentPage"/>;
                document.cusForm.submit();
            }
        }
        function addTeacher()
        {
            window.location.href = "<%=contextPath%>/cou/teacher!initAddTeacher.action";
        }

        function updateTeacher(id)
        {
            window.location.href = "<%=contextPath%>/cou/teacher!initUpdateTeacher.action?Teacher.tcId=" + id;
        }

        function toSelectCourse(id)
        {
            window.location.href = "<%=contextPath%>/cou/teacher!initSelectCourse.action?queryTeacherCondition.currentPage=1&queryTeacherCondition.tcId=" + id;
        }

        function resetKey()
        {
            $("form[name=searchForm]")[0].reset();
            $("#op0").attr("selected", "selected");
            $("input[name=queryTeacherCondition.name]").val("");
        }

        function allCheck(cb)
        {
            $("input[name=ids]:checkbox").attr('checked', cb.checked);
        }
        function yan()
        {
            $("#teacherName").val(encodeURIComponent($("#teacherName").val()));
            document.searchForm.submit();
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
                <font class="lists_fleft">教师列表</font>
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
                            <form name="searchForm" action="<%=contextPath%>/cou/teacher!showTeacherList.action" method="post">
                                <td>

                                    <s:hidden name="queryTeacherCondition.currentPage" value="1"/>
                                    教师名：
                                <td>
                                <td>
                                    <input type="text" value="<s:property value=" queryTeacherCondition.name"/>" id="teacherName"
                                    name="queryTeacherCondition.name"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td>
                                    明星教师：
                                </td>
                                <td>
                                    <select name="queryTeacherCondition.isStar">
                                        <option id="op0" value="">全部</option>
                                        <option value="1"
                                        <s:property value="queryTeacherCondition.isStar==1?'selected':''"/>>明星教师</option>
                                        <option value="0"
                                        <s:property value="queryTeacherCondition.isStar==0?'selected':''"/>>普通教师</option>
                                    </select>

                                </td>
                                <td width="20"></td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="javascript:yan()">查询</a></td>
                                <td width="20"></td>
                                <td><img src="<%=contextPath%>/back/images/del_a.gif"/></td>
                                <td><a href="javascript:resetKey()">清空</a></td>
                                <td width="20"></td>
                                <td><img src="<%=contextPath%>/back/images/add_a.gif"/></td>
                                <td><a href="javascript:addTeacher()">添加</a></td>
                                <td width="20"></td>
                                <td><img src="<%=contextPath%>/back/images/del_a.gif"/></td>
                                <td><a href="javascript:delTeacher()">删除</a></td>
                            </form>
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
                <form name="cusForm" method="post">
                    <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()"
                           onmouseout="changeback()">
                        <tr class="lists_infobg">
                            <td width="7%"><input type="checkbox" onclick="allCheck(this)"/>全选</td>
                            <td width="8%">教师名</td>
                            <td width="18%">教育背景</td>
                            <td width="36%">从业简介</td>
                            <td width="9%">明星教师</td>
                            <td width="13%">操作</td>
                        </tr>
                        <s:iterator value="page.pageResult" id="teacher" status="status">
                            <tr>
                                <td><input type="checkbox" name="ids" value=<s:property value="tcId"/> /></td>
                                <td>
                                    <s:property value="name"/>
                                </td>
                                <td title="<s:property value=" education
                                " />">
                                <div class="lists_nowrap"><s:property value="education"/></div>
                                </td>
                                <td title="<s:property value=" career
                                " />">
                                <div class="lists_nowrap"><s:property value="career"/></div>
                                </td>
                                <td>

                                    <s:if test="isStar==1">
                                        明星教师
                                    </s:if>
                                    <s:else>
                                        普通教师
                                    </s:else>
                                </td>
                                <td>
                                    <a href="#" onclick="updateTeacher(<s:property value=" tcId" />)">修改</a>
                                    <a href="<%=contextPath%>/cou/teacher!viewTeacher.action?teacher.tcId=<s:property value=" tcId"/>">查看</a>
                                    <!-- 选课功能，暂时不开 -->
                                    <!--
                                             <a href="#" onclick="toSelectCourse(<s:property value="tcId" />)">选课</a>
                                              -->
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </form>
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
