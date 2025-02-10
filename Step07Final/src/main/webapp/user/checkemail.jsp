<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String email = request.getParameter("email");
	
	UserDto dto = UserDao.getInstance().getDataByEmail(email);
	
	boolean canUse = dto == null? true: false;
%>
{"canUse": <%=canUse %>}