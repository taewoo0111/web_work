<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cooks = request.getCookies();
	String savedMsg = null;
	for(Cookie tmp:cooks){
		String key = tmp.getName();
		if(key.equals("savedMsg")){
			savedMsg = tmp.getValue();
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_cookie/cookie_read2.jsp</title>
</head>
<body>
	<p>savedMsg 라는 키값으로 저장된 문자열 : <%=savedMsg %></p>
	<p>savedMsg 라는 키값으로 저장된 문자열 : ${cookie.savedMsg.value }</p>
	<a href="cookie_form.jsp">다시 테스트</a>
	<br />
	<a href="cookie_delete.jsp">쿠키 삭제</a>
</body>
</html>