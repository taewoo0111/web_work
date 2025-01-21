<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//String fortuneToday = (String)request.getAttribute("fortuneToday");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/fortune.jsp</title>
</head>
<body>
	<div class="container">
		<h1>운세 페이지</h1>
		<%-- <p>오늘의 운세: <strong><%=fortuneToday %></strong></p> --%>
		<%--Expression Language 를 이용하면 request 영역에 담긴 데이터를 편하게 추출할 수 있다. --%>
		<p>오늘의 운세: <strong>${requestScope.fortuneToday }</strong></p>
		<p>오늘의 운세: <strong>${fortuneToday }</strong></p>
	</div>
</body>
</html>