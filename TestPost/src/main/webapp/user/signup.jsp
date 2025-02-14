<%@page import="test.dto.SessionDto"%>
<%@page import="test.dto.UserDto"%>
<%@page import="test.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String username = request.getParameter("username");
	String id = request.getParameter("id");
	String pwd = request.getParameter("password");
	
	UserDto dto = new UserDto();
	dto.setId(id);
	dto.setUsername(username);
	dto.setPassword(pwd);
	
	boolean isSuccess = UserDao.getInstance().insert(dto);
	
	if(isSuccess){
		SessionDto sessiondto = new SessionDto();
		sessiondto.setId(id);
		sessiondto.setUsername(username);
		session.setAttribute("User", sessiondto);
%>
	<script>
		alert("회원가입 성공!");
		window.location.href = "../index.jsp";
	</script>
<% } else{%>	
	<script>
		alert("회원가입 실패! 이미 존재하는 아이디에요!");
		window.location.href = "../index.jsp";
	</script>
<%}%>
