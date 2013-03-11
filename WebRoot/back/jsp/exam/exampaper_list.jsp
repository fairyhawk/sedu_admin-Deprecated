<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程节点</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css"/>
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<link rel="StyleSheet" href="<%=contextPath %>/back/style/dtree.css" type="text/css"/>
 <style >
		html, body { height: 100%; }
		#wrapback { min-height: 100%; position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5;width:100%;-moz-opacity:1;top:0;left:0;background:#000000;display:none;z-index:4;}
		* html #wrapback { height: 100%; }
		.fonts {font-size: 11pt;}
		
	</style>
<script type="text/javascript">

function All(e)
{
    var f = document.getElementById("examsearchform");
    for (i = 0; i < f.elements.length; i++)
    {
        if (f.elements[i].name == "exampaperIds")
        {
            f.elements[i].checked = e.checked;
        }
    }
}

function deleteAll(ObjectForm)
{
    var i = 0;
    var f = document.examsearchform;
    if (f.exampaperIds != null)
    {
        if (f.exampaperIds.length != null)
        {
            for (var j = 0; j < f.exampaperIds.length; j++)
            {

                if (f.exampaperIds[j].checked == true)
                {
                    i++;
                }
            }
        }
        else
        {
            if (f.exampaperIds.checked == true)
            {
                i++;
            }
        }
        if (i == 0)
        {
            alert("没有选择需要冻结的选项！");
            return false;
        }
        if (confirm("是否冻结？"))
        {
            ObjectForm.action = "<%=contextPath%>/exam/exampaper!freezeExampapers.action";
            ObjectForm.submit();
        }
    }
}

$().ready(function ()
{
    $("#courseselectform").validate({
        rules:{
            fSortId:{
                required:true,
                min:0
            },
            courseId:{
                required:true,
                min:0
            }
        },
        messages:{
            fSortId:{
                required:"请选择分类",
                min:"请选择分类"
            },
            courseId:{
                required:"请选择课程",
                min:"请选择课程"
            }
        }
    });
})

function onchangeFirstSort(pId)
{

    $.ajax({
        url:"<%=contextPath%>/cou/coursesort!getChildSortById.action",
        data:{sortId:pId}, // 参数
        type:"post",
        cache:false,
        dataType:"json", //返回json数据
        error:function ()
        {
            alert('error');
        },
        success:onchangecallback
    });

    $.ajax({
        url:"<%=contextPath%>/cou/course!getCourseBySortId.action",
        data:{sortId:pId}, // 参数
        type:"post",
        cache:false,
        dataType:"json", //返回json数据
        error:function ()
        {
            alert('error');
        },
        success:onchangecallbackcourse
    });

}

function onchangecallbackcourse(result)
{ //处理返回的课程JSON

    document.getElementById('courseId').options.length = 0;  //清空原有的option
    var str = "";

    for (var i = 0; i < result.entity.length; i++)
    {
        str += "<option value='" + result.entity[i].id + "'>" + result.entity[i].name + "</option>"
    }

    $("#courseId").html(str);
}

function onchangecallback(result)
{
    document.getElementById('sSortId').options.length = 0;  //清空原有的option
    document.getElementById('tSortId').options.length = 0;  //清空原有的option
    $("#tSortId").html("<option value=-1>请选择</option>");
    var str = "";
    for (var i = 0; i < result.entity.length; i++)
    {
        str += "<option value='" + result.entity[i].id + "'>" + result.entity[i].name + "</option>"
    }
    $("#sSortId").html(str);
}

function onchangeSecond(pId)
{

    $.ajax({
        url:"<%=contextPath%>/cou/coursesort!getChildSortById.action",
        data:{sortId:pId}, // 参数
        type:"post",
        cache:false,
        dataType:"json", //返回json数据
        error:function ()
        {
            alert('error');
        },
        success:onchangecallback2
    });

    $.ajax({
        url:"<%=contextPath%>/cou/course!getCourseBySortId.action",
        data:{sortId:pId}, // 参数
        type:"post",
        cache:false,
        dataType:"json", //返回json数据
        error:function ()
        {
            alert('error');
        },
        success:onchangecallbackcourse
    });
}


function onchangeThird(pId)
{
    $.ajax({
        url:"<%=contextPath%>/cou/course!getCourseBySortId.action",
        data:{sortId:pId}, // 参数
        type:"post",
        cache:false,
        dataType:"json", //返回json数据
        error:function ()
        {
            alert('error');
        },
        success:onchangecallbackcourse
    });

}

function onchangecallback2(result)
{
    document.getElementById('tSortId').options.length = 0;  //清空原有的option
    var str = "";
    for (var i = 0; i < result.entity.length; i++)
    {
        str += "<option value='" + result.entity[i].id + "'>" + result.entity[i].name + "</option>"
    }
    $("#tSortId").html(str);
}

function onSelectCourse(courseid)
{
    document.getElementById('exam.type').value = 1;
    document.getElementById('exam.kOrCouId').value = courseid;

    $.ajax({
        url:"<%=contextPath%>/cou/kpoint!getAllJSONKpoints.action",
        data:{"kpoint.courseId":courseid}, // 参数
        type:"post",
        cache:false,
        dataType:"json", //返回json数据
        error:function ()
        {
            alert('error');
        },
        success:onchangecallbackpoint
    });

}

function onchangecallbackpoint(result)
{

    var myList = result.entity;
    if (myList.length < 1)
    {
        $("#kpointList").html("");
        return;
    }
    $("#kpointList").show();
    addkpoint = new dTree('addkpoint', '<%=contextPath %>/back/images/dtree');
    addkpoint.add(-2, -1, '栏目列表 <a href="javascript:closeColumn();">关闭</a>');
    for (var i = 0; i < myList.length; i++)
    {
        addkpoint.add(myList[i].id, myList[i].PId, myList[i].name, 'javascript:changeColumnValue(' + myList[i].id + ',' + "'" + myList[i].name + "'" + ')');
    }
    $("#kpointList").html(addkpoint.toString());
}

function checkSubmit()
{

    return true;
}

function changeColumnValue(id, name)
{
    document.getElementById('exam.type').value = 2;
    document.getElementById('exam.kOrCouId').value = id;

    obj = "kpointList";
    document.getElementById("pName").value = name;
    document.getElementById(obj).style.display = "none";
}
function showPkpoint()
{
    obj = "kpointList";
    document.getElementById(obj).style.display = "block";
}
function closeColumn()
{
    obj = "kpointList";
    document.getElementById(obj).style.display = "none";
}

/**
 编码含有中文字符字段
 */
/*
function encodeCommon()
{
    $("#keyWorld").val(encodeURIComponent($("#keyWorld").val()));
    $("#author").val(encodeURIComponent($("#author").val()));
    return true;
}*/
function onsubmits(){
	 var form= document.getElementById("examsearchform"); 
  $("#keyWorld").val(encodeURIComponent($("#keyWorld").val()));
  $("#author").val(encodeURIComponent($("#author").val())); 
	 form.submit(); 
 }
 function exportEnable(){
		 $('#exportExcel').attr('disabled',false);
		}
  	function closes(){
			$("#infoWrap").fadeOut();
			$("#wrapback").fadeOut();
			$('#wrapbackInfo').fadeOut();
			$('#checkOut').fadeOut();
		}
 function checkOutExcel(){
					
		
				var totalRecord='<s:property value="page.totalRecord"/>';
				 var url = "${pageUrlParms}";
	    	 var param = url.substring(url.indexOf("?"), url.length-1);
	    	  $('#exportExcel').attr('disabled',true);
			 window.setTimeout(exportEnable,5000);
					$.ajax({
									url : "<%=contextPath%>/exam/exampaper!exportExamPaperExcel.action"+param,  
									data : {
										},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
										var ss=result.returnMessage;
										var bo=result.jumpType;
										if(bo){
											var ss='点击下载：<a href="/excelfile_exam/'+ss+'.rar">导出excel</a>'
											$("#downinfo").html(ss);
											$("#wrapback").fadeIn();
											$("#checkOut").fadeIn();
											$('#wrapbackInfo').fadeIn();
										}else{
											window.location.reload();
										}
									},
									error: function(){ 
										alert('error');  
									}
				  });
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
                    <font class="lists_fleft">试卷列表</font>
                </td>
                <td class="td_wid_l">
                    <img src="<%=contextPath%>/back/images/tab_07.gif"/>
                </td>
            </tr>

            <tr>
                <td width="12" class="lists_bor"></td>
                <td>
                    <div class="msg-app">
                        <form action="<%=contextPath %>/exam/exampaper!listExamPaperByCondition.action" method="post" name="examsearchform" id="examsearchform">
                                <table width="100%" border="0">
                                    <tr>
                                        <td>关键字:</td>
                                        <td><input type="text" name="queryExampaperCondition.searchKey" value='<s:property value="queryExampaperCondition.searchKey"/>'
                                                   id="keyWorld"/></td>
                                        <td>
                                            <select name="queryExampaperCondition.tiaojian">
                                                <OPTION selected value="0">-检索条件-</OPTION>
                                                <OPTION value="1">试卷标题</OPTION>
                                                <OPTION value="2">使用量</OPTION>
                                                <OPTION value="3">积分</OPTION>

                                            </select></td>

                                        <td>
                                       <!-- <select name="queryExampaperCondition.level">
                                            <option selected="selected" value="0">-状态查询-</option>
                                            <option value="1">新稿</option>
                                            <option value="2">发布</option>
                                            <option value="3">过期</option> --> 
                                        </select>
                                   <select name="queryExampaperCondition.level" >
										<option value="0">-状态查询-</option>
										<option value="1" <s:property value="queryExampaperCondition.level==1?'selected=\"selected\"':''" />>新稿</option>
										<option value="2" <s:property value="queryExampaperCondition.level==2?'selected=\"selected\"':''" />>发布</option>
										<option value="3" <s:property value="queryExampaperCondition.level==3?'selected=\"selected\"':''" />>过期</option>
							      </select>
                                        </td>

                                        <td>
                                            <s:select name="queryExampaperCondition.subjectId"
                                                      list="subjectList" listKey="subjectId"
                                                      listValue="subjectName" headerKey="0"
                                                      headerValue="-项目-" theme="simple">
                                            </s:select>
                                        </td>

                                        <td>
                                      <!--   <SELECT name="queryExampaperCondition.eptype">
                                            <OPTION selected value="0">-试卷类型-</OPTION>
                                            <option value="1">真题测试</option>
                                            <option value="2">仿真自测</option>
                                            <option value="3">单元练习</option>
                                            <option value="4">专题练习</option>
                                        </SELECT> -->
                                        <SELECT name="queryExampaperCondition.eptype">
                                           <option value="0">-试卷类型-</option>
										<option value="1" <s:property value="queryExampaperCondition.eptype==1?'selected=\"selected\"':''" />>真题测试</option>
										<option value="2" <s:property value="queryExampaperCondition.eptype==2?'selected=\"selected\"':''" />>仿真自测</option>
										<option value="3" <s:property value="queryExampaperCondition.eptype==3?'selected=\"selected\"':''" />>单元练习</option>
										<option value="4" <s:property value="queryExampaperCondition.eptype==4?'selected=\"selected\"':''" />>专题练习</option>
                                        </SELECT>
                                        </td>

                                        <td>修改人：</td>
                                        <td><input type="text" name="queryExampaperCondition.author" id="author" value="<s:property value=" queryExampaperCondition.author"
                                            />" />
                                        </td>

                                        <td>时间:</td>
                                        <td>
                                            <input type="text" name="queryExampaperCondition.startTime"
                                                   onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
                                                   id="startTime"/></td>
                                        <td>——</td>
                                        <td><input type="text" name="queryExampaperCondition.endTime"
                                                   onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
                                                   id="endTime"/>
                                            <input type="hidden" name="queryExampaperCondition.currentPage" value="1"/></td>

                                        <td><input name="提交" type="button" onclick="onsubmits()" value="搜索"/></td>
                                         <td><input id="exportExcel" name="导出" type="button" onclick="checkOutExcel()" value="导出"/></td>
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
                    <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()"
                           onmouseout="changeback()">
                        <tr class="lists_infobg">
                            <td>
                                <input type="checkbox" name="c1" onclick="All(this);"/></td>
                            <td>试卷id</td>
                            <td>
                                试卷名称
                            </td>
                            <td>
                                所属专业
                            </td>

                            <td>
                                使用人数
                            </td>

                            <td>积分</td>

                            <td>
                                试卷类型
                            </td>

                            <td>状态</td>
                            <td>
                                添加时间
                            </td>
                            <td>试卷题数</td>
                            <td>修改时间</td>
                            <td>试卷修改人</td>
                            <td>
                                操作
                            </td>
                        </tr>
                        <s:iterator value="page.pageResult" id="exampaper">
                            <tr>
                                <td>
                                    <input type="checkbox" name="exampaperIds"
                                           value='<s:property value="#exampaper.epId"/>'/></td>
                                <td><s:property value="#exampaper.epId"/></td>
                                <td>
                                    <s:property value="#exampaper.epName"/></td>
                                <td>
                                    <s:property value="#exampaper.subjectName"/></td>

                                <td>
                                    <s:property value="#exampaper.joinNum"/></td>
                                <td>
                                    <s:property value="#exampaper.jifen"/></td>

                                <td>
                                    <s:if test="#exampaper.eptype==1">
                                        真题测试 </s:if>
                                    <s:if test="#exampaper.eptype==2">
                                        仿真自测 </s:if>
                                    <s:if test="#exampaper.eptype==3">
                                        单元练习 </s:if>
                                    <s:if test="#exampaper.eptype==4">
                                        专题练习 </s:if></td>


                                <td><s:if test="#exampaper.level==1">新稿</s:if><s:if test="#exampaper.level==2">发布</s:if><s:if
                                        test="#exampaper.level==3">过期</s:if></td>
                                <td>
                                    <s:date name="#exampaper.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><s:property value="#exampaper.qstmiddlecount"/></td>
                                <td><s:date name="#exampaper.lastEditTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><s:property value="#exampaper.author"/></td>
                                <td>
                                    <script language="javascript">
                                        document.write('<a href="<%=contextPath %>/exam/exampaper!toUpdateExam.action?exam.epId=<s:property value="epId"/>">修改</a>');
                                    </script>
                                    <a href='<%=contextPath %>/exam/exampaper!toaddQst.action?epid=<s:property value="#exampaper.epId"/>'>试题组卷</a>
                                    <a href='<%=contextPath %>/exam/exampaper!toPreviewExampaper.action?epid=<s:property value="#exampaper.epId"/>'>试卷预览</a>
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
<!--层遮罩部分-->		
		<div style="position:absolute; filter:alpha(opacity=50);opacity:0.5;-khtml-opacity:0.5; -moz-opacity:0.5;top:0;left:0;width:100%;height:150%;background:#000;display:none;z-index:4;" id="wrapback">		
		<iframe id="wrapbackInfo" style="position:absolute;width:100%;height:100%;_filter:alpha(opacity=50);opacity=0.5;border-style:none;"></iframe>
		</div>
		<!--层遮罩部分结束-->
		<div align='center' class="excelout"  id='checkOut'>
			<div class="tit">
				<span class="tie fonts" id="title" >试卷管理excel打包下载</span>
			</div>	
			<div id="downinfo"  class="con"></div>
			<div align="center"><input type='button' value='关闭' onclick='closes();'/></div>
		</div> 
</body>
</html>