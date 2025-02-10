<%@page import="java.net.URLEncoder"%>
<%@page import="test.user.dto.SessionDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userName = request.getParameter("userName");
	String password = request.getParameter("password");
	
	UserDto dto = UserDao.getInstance().getDataByuserName(userName); 
	boolean isLoginSuccess = false;
	
	if(dto != null){
		if(dto.getPassword().equals(password)){
			SessionDto sessionDto = new SessionDto();
			sessionDto.setNum(dto.getNum());
			sessionDto.setUserName(dto.getUserName());
			sessionDto.setRole(dto.getRole());
			session.setAttribute("sessionDto", sessionDto);
			isLoginSuccess = true;
		}
	}
	
	String url = request.getParameter("url");
	String encodeUrl = URLEncoder.encode(url, "UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
</head>
<body>
	<div class="container">
		<%if(isLoginSuccess){ %>
			<p>
				<strong><%=dto.getUserName() %></strong> 님 로그인 되었습니다.
				<a href="<%=url%>">확인</a>
			</p>
		<%}else{ %>
			<p>
				아이디가 없거나 비밀번호가 틀려요!
				<a href="${pageContext.request.contextPath }/user/login-form.jsp?url=<%=encodeUrl%>">다시 입력</a>
			</p>
		<%} %>
	</div>
</body>
</html>