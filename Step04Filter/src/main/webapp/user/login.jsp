<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	boolean isValid = false;
	
	if(pwd.equals("1234")){
		// 세션에 "id" 라는 키값으로 로그인된 아이디를 저장한다.
		session.setAttribute("id", id);
		isValid = true;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
</head>
<body>
	<div class="container">
		<%if(isValid){ %>
			<p>
				<strong><%=id %></strong> 님 로그인 되었습니다.
				<a href="${pageContext.request.contextPath }/">인덱스로</a>
			</p>
		<%} else{%>
			<p>
				아이디 혹은 비밀번호가 틀려요!
				<a href="loginform.jsp">다시 로그인</a>
			</p>
		<%}%>
	</div>
</body>
</html>