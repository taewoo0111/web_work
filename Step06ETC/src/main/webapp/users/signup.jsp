<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String email = request.getParameter("email");
	
	UserDto dto = new UserDto();
	dto.setId(id);
	dto.setPwd(pwd);
	dto.setEmail(email);
	
	UserDao dao = UserDao.getInstance();
	boolean isSuccess = dao.insert(dto);
	response.sendRedirect(request.getContextPath() + "/index.jsp");
	
%>
