<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	List<String> names = new ArrayList<String>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
	request.setAttribute("list", names);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/test01.jsp</title>
</head>
<body>
	<h1>친구목록</h1>
	<%
		List<String> list = (List<String>)request.getAttribute("list");
	%>
	<ul>
		<%for(String tmp: list){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
	<h1>친구목록 EL+JSTL</h1>
	<ul>
		<c:forEach var="tmp" items="${requestScope.list }">
			<li>${tmp }</li>
		</c:forEach>
	</ul>
	<h1>친구 목록 인덱스 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스: ${status.index }</strong></li>
		</c:forEach>
	</ul>
	<h1>친구 목록 순서 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스: ${status.count }</strong></li>
		</c:forEach>
	</ul>
	<h1>친구 목록 첫번째인지 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스: ${status.first }</strong></li>
		</c:forEach>
	</ul>
	<h1>친구 목록 마지막인지 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스: ${status.last }</strong></li>
		</c:forEach>
	</ul>
</body>
</html>