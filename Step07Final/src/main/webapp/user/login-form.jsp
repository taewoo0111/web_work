<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String url = request.getParameter("url");
	if(url==null){
		String cPath = request.getContextPath();
		url = cPath + "/index.jsp";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login-form.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>로그인 폼</h1>
		<form action="login.jsp" method="post">
			<input type="hidden" name="url" value="<%=url %>" />
			<div>
				<label for="userName">아이디</label> <input type="text" name="userName"
					id="userName" />
			</div>
			<div>
				<label for="password">비밀번호</label> <input type="password"
					name="password" id="password" />
			</div>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>