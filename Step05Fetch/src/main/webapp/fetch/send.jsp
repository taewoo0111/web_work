<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = request.getParameter("msg");
	System.out.println("msg:" + msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/send.jsp</title>
</head>
<body>
	메세지 잘 받았어 클라이언트야!
</body>
</html>