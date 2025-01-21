<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = request.getParameter("msg");
	
	Cookie cook = new Cookie("savedMsg", msg);
	cook.setMaxAge(60); // 쿠키 유지시간(초단위)
	response.addCookie(cook);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_cookie/cookie_save.jsp</title>
</head>
<body>
	<p>웹 브라우저에 savedMsg 라는 키값으로 "<%=msg %>"를 저장했습니다.</p>
	<a href="cookie_read.jsp">저장된 문자열 확인 해보기</a>
	<br />
	<a href="cookie_read2.jsp">저장된 문자열 확인 해보기2</a>
</body>
</html>