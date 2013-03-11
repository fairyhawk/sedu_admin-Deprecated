<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>查看回复信息</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/jquery.jqplot.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/back/style/examples.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		
<script type="text/javascript">
	/**提交验证**/
	function validateF(){
		if($("#status").val() > 0){
			alert("此问题已解决,不可重复解决");
			return;
		}else{
			if($("#soldept").val() <= 0){
				alert("请选择解决部门");
				return;	
			}else if($("#soltype").val() != null && $.trim($("#soltype").val()).length <= 0){
				alert("请输入解决方案");
				return;
			}
			$("#f1").submit();
		}
	}
</script>
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">查看问题</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
			<s:form action="feedback!ansFeedById" method="post" id="f1">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<input type="hidden" id="status" value="<s:property value='feedback.status'/>"></input>
				<input type="hidden" id="id" name="feedback.id" value="<s:property value='feedback.id'/>"></input>
				<tr height="30">
					<td>
						用户邮箱:
					</td>
					<td class="lists_tleft">
						<s:property value='feedback.email'/>
					</td>
				</tr>
				<tr height="30">
					<td>
						qq号码:
					</td>
					<td class="lists_tleft">
						<s:property value='feedback.qq'/>
					</td>
				</tr>
				
				<tr height="30">
					<td>
						专业名称:
					</td>
					<td class="lists_tleft">
						<s:property value='feedback.subjectname'/>
					</td>
				</tr>
				<tr height="30">
					<td>
						电话:
					</td>
					<td class="lists_tleft">
						<s:property value='feedback.mobile'/>
					</td>
				</tr>
				<tr height="30">
					<td>
						所在省:
					</td>
					<td class="lists_tleft">
						<s:property value='feedback.provincename'/>
					</td>
				</tr>
				<tr height="30">
					<td>
						所在市:
					</td>
					<td class="lists_tleft">
						<s:property value='feedback.cityname'/>
					</td>
				</tr>
				<tr height="30">
					<td>
						带宽:
					</td>
					<td class="lists_tleft">
						<s:if test="feedback.bandwidth == 1">
								不清楚
						</s:if>
						<s:if test="feedback.bandwidth == 2">
								64K
						</s:if>
						<s:if test="feedback.bandwidth == 3">
								512K
						</s:if>
						<s:if test="feedback.bandwidth == 4">
							1M
						</s:if>
						<s:if test="feedback.bandwidth == 5">
							2M-4M
						</s:if>
						<s:if test="feedback.bandwidth == 6">
							4M以上
						</s:if>
						<s:if test="feedback.bandwidth == 7">
							其它
						</s:if>		
					</td>
				</tr>
				<tr height="30">
					<td>
						是否解决:
					</td>
					<td class="lists_tleft">
						<s:if test="feedback.status == 0">
							未解决
						</s:if>
						<s:else>
							已解决
						</s:else>
					</td>
				</tr>
				<tr height="30">
					<td>
						解决部门:
					</td>
					<td class="lists_tleft">
					<s:if test='feedback.status == 0'>
						<select name="feedback.soldept" id="soldept">
							<option value="-1">-请选择-</option>
							<option value="1">客服中心</option>
							<option value="2">销售部</option>
							<option value="3">远程技术部</option>
						</select>
					</s:if>
					<s:else>
						<s:if test="feedback.soldept == 1">
							客服中心
						</s:if>
						<s:if test="feedback.soldept == 2">
							销售部
						</s:if>
						<s:if test="feedback.soldept == 3">
							远程技术部
						</s:if>
					</s:else>
					</td>
				</tr>
				<tr height="30">
					<td>
						解决方案:
					</td>
					<td class="lists_tleft">
						<s:if test="feedback.status == 0">
							<textarea name="feedback.soltype" id="soltype" style="resize: none; font-size: 12px;" rows="4" cols="35"></textarea>
						</s:if>
						<s:else>
							<s:property value="feedback.soltype"/>
						</s:else>
					</td>
				</tr>
				<tr height="30">
					<td>
						描述:
					</td>
					<td class="lists_tleft">
						<s:property value='feedback.description'/>
					</td>
				</tr>
				<tr height="30">
					<td>
						反馈时间
					</td>
					<td class="lists_tleft">
						<s:date name="feedback.createtime"  format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					</s:form>
				</tr>
				 <td colspan="2" align="center"><input type="button" onclick="validateF();" value="提交"/> &nbsp;&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="返回"/> </td></tr> 
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
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>
