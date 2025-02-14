<%@page import="test.post.dao.CommentDao"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	long num = Long.parseLong(request.getParameter("num"));
	boolean isSuccess = CommentDao.getInstance().delete(num);
%>
{"isSuccess":<%=isSuccess %>}
