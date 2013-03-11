<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>微学习>>统计管理>>课程统计</title>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_body.css" />
		
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">
					查询条件：
				</font>
			</td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
											<tr>
												<td width="5%" class="lists_tright">
												请选择时间:
												</td>
												<td class="lists_tleft">
													<input type="text" name="" id="startTime" value="选择年月日" />
													<input type="text" name="" id="startTime" value="精确到秒" />
													到
													<input type="text" name="" id="startTime" value="选择年月日" />
													<input type="text" name="" id="startTime" value="精确到秒" />
													</td>
												</tr>
													
												<tr>
													<td width="5%" class="lists_tright">
													课程类别：
													</td>
												<td class="lists_tleft">
													<select name="">
														<option value="">1</option>
														<s:iterator value="">
															
														</s:iterator>
													</select>
													<button name="" value="">查询</button>
												</td>
											</tr>
				</table>
					<font class="lists_fright">
					<button name="" value="">导出数据</button>
				</font>
			</form>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
					<tr class="lists_infobg">
						<td>
							视频ID
						</td>
						<td>
							视频名称
						</td>
						<td>
							点击数
						</td>
						<td>
							观看人数
						</td>
						<td>
							看完人数
						</td>
						<td>
							平均观看时间
						</td>
						<td>
							支持数
						</td>
						<td>
							反对数
						</td>
						<td>
							下载数
						</td>
						<td>
							收藏数
						</td>
					</tr>
				</table>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				<jsp:include page="/back/jsp/common/showPage.jsp" />
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</body>
</html>