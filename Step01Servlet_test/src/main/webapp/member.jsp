<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<String> names = new ArrayList<>();
	names.add("김태우");
	names.add("김구라");
	names.add("원숭이");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member.jsp</title>
</head>
<body>
	<h1>멤버 목록 보기</h1>
	<ul>
		<%for (String name: names){ %>
		<li><%=name %></li>
		<%} %>
	</ul>
</body>
</html>