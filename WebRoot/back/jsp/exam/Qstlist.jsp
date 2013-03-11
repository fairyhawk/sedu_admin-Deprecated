<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>试题列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>

    <script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" language="javascript">

        //单选删除list
        function delrowdanxuanlist(no, eqid)
        {

            var bb = window.confirm("试题删除后无法恢复，确认删除？");
            if (bb)
            {
                var tab = document.getElementById("table2");
                var member = no.parentNode.parentNode.rowIndex;
                var nownum = 0;


                if (tab.rows.length > 0)
                {
                    $.ajax({
                        url:"<%=contextPath%>/exam/qst!Qstdel.action",
                        data:{"qstId":eqid}, // 参数
                        type:"post",
                        cache:false,
                        dataType:"json", //返回json数据
                        success:function (result)
                        {
                            var array = result.returnMessage;
                            if (array == "1")
                            {

                                tab.deleteRow(member);
                                //for(j = 0; j < tab.rows.length; j ++){

                                //nownum ++;

                                //tab.rows[j].cells[0].innerHTML = nownum+"、";
                                //}
                                alert("删除成功");
                            }
                        },
                        error:function ()
                        {
                            alert('error');
                        }
                    });


                }
                else
                {
                    alert("删除失败");
                }
            }
            else
            {
                alert("取消成功");
            }


        }


    </script>
    <script type="text/javascript" language="javascript">
        function nodel(id)
        {

            $.ajax({
                url:"<%=contextPath%>/exam/qstmid!Qstdelinfo.action",
                data:{"qstid":id},
                type:"post",
                cache:false,
                dataType:"json", //返回json数据
                success:function (result)
                {
                    var exanmame = result.returnMessage;
                    alert("该试题已经被添加到<<" + exanmame + ">>中。\n不能删除，如果想删除试题，请先删除试卷中的试题。");

                },
                error:function ()
                {
                    alert('error');
                }
            });
        }

        /**
         编码含有中文字符字段
         */
        function encodeCommon()
        {
            $("#keyWorld").val(encodeURIComponent($("#keyWorld").val()));
            $("#author").val(encodeURIComponent($("#author").val()));
            return true;
        }

    </script>
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
        <tbody>
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
            <td width="12" class="lists_bor"></td>
            <td>
                <div class="msg-app">
                    <form action="<%=contextPath %>/exam/qst!QstList.action" method="get" onsubmit="encodeCommon()">
                        <table width="100%" border="0">
                            <tr>
                                <td>关键字:</td>
                                <td><input type="text" id="keyWorld" name="queryQstCondition.searchKey"
                                           value='<s:property value="queryQstCondition.searchKey"/>'/>
                                </td>
                                <td>
                                    <select name="queryQstCondition.tiaojian">
                                        <OPTION selected value="0">-检索条件-</OPTION>
                                        <OPTION value="1">题干</OPTION>
                                        <OPTION value="2">答案</OPTION>
                                    </select>
                                </td>
                                <td>
                                    <SELECT name="queryQstCondition.qstType">
                                        <OPTION selected value="0">-试题类型-</OPTION>
                                        <option value="1">单选题</option>
                                        <option value="2">多选题</option>
                                        <option value="3">判断题</option>
                                        <option value="4">材料分析</option>
                                        <option value="5">图表题</option>
                                        <option value="6">主观题</option>
                                    </SELECT>
                                </td>
                                <td>
                                    <s:select name="queryQstCondition.subjectId"
                                              list="subjectList" listKey="subjectId"
                                              listValue="subjectName" headerKey="0"
                                              headerValue="-项目-" theme="simple">
                                    </s:select>
                                </td>
                                <td>修改人：</td>
                                <td><input type="text" name="queryQstCondition.author" id="author" value="<s:property value=" queryQstCondition.author"/>"
                                    />
                                </td>
                                <td>时间：</td>
                                <td>
                                    <input type="text" name="queryQstCondition.startTime"
                                           onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
                                           id="startTime"/></td>
                                <td>—</td>
                                <td><input type="text" name="queryQstCondition.endTime"
                                           onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
                                           id="endTime"/>
                                    <input type="hidden" name="queryQstCondition.currentPage" value="1"/>
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
                <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="table2">
                    <tr class="lists_infobg">
                        <td>试题id</td>
                        <td>题干</td>
                        <td>
                            试题类型
                        </td>

                        <td>
                            正确答案
                        </td>

                        <td>
                            发布时间
                        </td>
                        <td>修改时间</td>
                        <td>试题修改人</td>
                        <td>
                            操作
                        </td>
                    </tr>
                    <s:iterator value="page.pageResult" id="qst" status="stuts">
                        <tr>
                            <td valign="middle"><s:property value="#qst.qstId"/></td>
                            <td width="30%">
                                <div style='font-size:12px; text-align:left; height:50px; width:100%; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff'>
                                    <s:property value="#qst.qstContent" escape="false"/>
                                </div>
                            </td>
                            <td>

                                <s:if test="#qst.qstType==1">单选题</s:if> <s:if test="#qst.qstType==6">主观题</s:if><s:if test="#qst.qstType==2">多选题</s:if><s:if
                                    test="#qst.qstType==3">判断题</s:if><s:if test="#qst.qstType==4">材料分析题</s:if><s:if test="#qst.qstType==5">图表题</s:if>
                            </td>

                            <td>
                                <s:property value="#qst.isAsr"/></td>

                            <td>
                                <s:date name="#qst.addtime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><s:date name="#qst.lastEditTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><s:property value="#qst.author" escape="false"/></td>
                            <td>
                                <a href="<%=contextPath%>/exam/qst!toQstUpdate.action?qstId=<s:property value="#qst.qstId"/>">修改</a>&nbsp;&nbsp;
                                <s:if test="#qst.qstType==4">
                                    <a href="<%=contextPath%>/exam/qst!toQstcailiaoAdd.action?qstId=<s:property value="#qst.qstId"/>">添加子题</a>
                                </s:if>
                                <s:if test="#qst.qstsize==0">
                                    <a href="#" onclick='delrowdanxuanlist(this,<s:property value="#qst.qstId" />)'>删除</a>
                                </s:if>
                                <s:else>
                                    <span class="STYLE1"><a href="#" onclick='nodel(<s:property value="#qst.qstId" />)' class="STYLE1"><span
                                            class="STYLE1">删除</span></a></span>
                                </s:else>
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
        </tbody>
    </table>

</div>
</body>
</html>