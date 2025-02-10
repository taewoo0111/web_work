<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userName = request.getParameter("userName");
	
	UserDto dto = UserDao.getInstance().getDataByuserName(userName);
	
	boolean canUse = dto == null ? true : false;
%>
{"canUse":<%=canUse %>}