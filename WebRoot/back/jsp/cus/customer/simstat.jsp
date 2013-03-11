<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>aa</title>
  </head>
  <body>
  <s:if test="simStat==null">--</s:if><s:else><s:property value="simStat.sellCount"/>|<s:property value="simStat.callCount"/>|<s:property value="simStat.successCount"/>|<a style="color:#FF0000"  href="#"><s:property value="simStat.payCount"/>+<s:property value="simStat.sendCount"/></s:else></a>
  </body>
</html>
