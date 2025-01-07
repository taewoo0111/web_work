<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDto dto = new MemberDto(1, "김태우", "수내동");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>번호: <strong><%=dto.getNum() %></strong></p>
	<p>이름: <strong><%=dto.getName() %></strong></p>
	<p>주소: <strong><%=dto.getAddr() %></strong></p>
</body>
</html>