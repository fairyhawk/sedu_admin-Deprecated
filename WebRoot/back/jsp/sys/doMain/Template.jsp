<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
</head>
<body>
	<%
	    ServletContext sc = getServletContext();
	    RequestDispatcher rd = null;
	    rd = sc.getRequestDispatcher("###title###");
	    rd.forward(request, response);
	%>
</body>
</html>