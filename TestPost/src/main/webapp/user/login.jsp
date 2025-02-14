<%@page import="test.dto.SessionDto"%>
<%@page import="test.dao.UserDao"%>
<%@page import="test.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String username = request.getParameter("username");
	String pwd = request.getParameter("password");

	UserDto dto = UserDao.getInstance().getData(id);
	if(dto != null && username.equals(dto.getUsername()) && pwd.equals(dto.getPassword())){
		SessionDto sessiondto = new SessionDto();
		sessiondto.setId(dto.getId());
		sessiondto.setUsername(dto.getUsername());
		session.setAttribute("User", sessiondto);	
%>
	<script>
		alert("로그인 성공!");
		window.location.href = "../index.jsp";
	</script>
<% } else{%>	
	<script>
		alert("로그인 실패!");
		window.location.href = "login-form.jsp";
	</script>
<%}%>