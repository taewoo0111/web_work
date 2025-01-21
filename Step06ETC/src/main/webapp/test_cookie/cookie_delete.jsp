<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie cook = new Cookie("savedMsg", "");
	cook.setMaxAge(0);
	response.addCookie(cook);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_cookie/cookie_delete.jsp</title>
</head>
<body>
	<p><strong>savedMsg</strong> 라는 키값으로 저장된 쿠키를 삭제 했습니다.</p>
	<a href="cookie_read2.jsp">쿠키 다시 읽어오기</a>
</body>
</html>