<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>课程节点</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script language="javascript" type="text/javascript">
		//存放数据的数组   
    var myArrayMoveStock=new Array();   
    //将小页面被选中的入库明细信息带到大页面   
    function selectQstList() {   
        var qstChecked =document.getElementsByName("cx");   
        // 定义是否有产品被选中   
        var notSelect = true;   
        // 把被选中的入库明细传入数组   
        for(var i=0;i<qstChecked.length;i++){   
            if(qstChecked[i].checked==true){   
                toParentsValue(qstChecked[i]);   
                notSelect = false ;   
            }   
        }   
        //没有入库明细被选择   
        if(notSelect){   
            alert("请选择产品");   
            return;   
        }     
        //调用父页面的方法  
		var type=$("#type").val(); 
		if(type==1){
        opener.newWindow(myArrayMoveStock);   
		}
		if(type==2)
		{
			opener.newWindowduoxuan(myArrayMoveStock);
		}
		if(type==3)
		{
			opener.newWindowpanduan(myArrayMoveStock);
		}
		
		if(type==4)
		{
			opener.newWindowcailiao(myArrayMoveStock);
		}
		
		if(type==5)
		{
			opener.newWindowtubiao(myArrayMoveStock);
		}
		if(type==6)
		{
			opener.newWindowzhuguan(myArrayMoveStock);
		}
        window.close();   
    }   
       
     // 把选中产品的一条记录放到数组中   
    function toParentsValue(obj){   
        var records = obj.value;   
        var instockmsg = new Array();   
        instockmsg = records.split("#");//以#分开获得数组   
        var qstId = instockmsg[0];   
        var qstscore = instockmsg[1];   
        var qstcontent = instockmsg[2];   
          
        if(obj.checked==true) {   
            myArrayMoveStock.push([qstId,qstscore,qstcontent]);   
        }    
    }   

		
		function quxiao(){
		window.close(); 
		}
		</script>
	</head>
	<body>
	<div>
	<form action="<%=contextPath %>/exam/qst!QstListtanchuan.action"  method="get">
	<input  type="text" value='<s:property value="searchKey"/>' name="searchKey"/>
	<s:select list="#{'1':'ID','2':'题干'}"   headerKey="0"   headerValue="-所有-"  theme="simple" id="tiaojian" name="queryQstCondition.tiaojian"  />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
<input  type="hidden" value='<s:property  value="type"/>' name="type" id="type"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<s:select  name="queryQstCondition.sortId" list="courseSortList"  headerKey="0"   headerValue="请选择科目" theme="simple" listKey="coursesortId" listValue="coursesortName" >
	</s:select>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
<input type="hidden" name="queryQstCondition.currentPage" value="1"/>
<input  type="submit" value="搜索"/></form>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
			  <font class="lists_fleft">试题列表</font></td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
					<tr class="lists_infobg" >
						<td>试题id</td>
						<td>题干</td>
					    <td>试题类型</td>
					</tr>
					<s:iterator value="page.pageResult" id="qst"  status="stuts">
						<tr>
							<td><input  type="checkbox" name="cx" value="<s:property value="#qst.qstId"/>#<s:property value="%{#qst.qstContent.substring(0,10)}" escape="false" />......"/><s:property value="#qst.qstId"/></td>
							<td>
								<div style='font-size:12px; height:50px; width:300px; OVERFLOW: auto; scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff' >  <s:property value="#qst.qstContent" default="-" escape="false"  /></div>
								
										  </td>
						    <td>
							
							<s:if test="#qst.qstType==1">单选题</s:if><s:if test="#qst.qstType==2">多选题</s:if><s:if test="#qst.qstType==3">判断题</s:if><s:if test="#qst.qstType==4">材料分析题</s:if><s:if test="#qst.qstType==5">图表题</s:if><s:if test="#qst.qstType==6">主观题</s:if></td>
						</tr>
					</s:iterator>
					
						<tr  >
						<td><input type="button"  onclick="selectQstList()" value="提交"/></td>
						<td><input type="button"  onclick="quxiao()" value="取消"/></td>
					    <td>&nbsp;</td>
					</tr>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	
</div>
	</body>
</html>