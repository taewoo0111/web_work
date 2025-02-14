<%@page import="test.post.dao.CommentDao"%>
<%@page import="test.post.dto.CommentDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	long num = Long.parseLong(request.getParameter("num"));
	String content = request.getParameter("content");
	
	CommentDto dto = new CommentDto();
	dto.setNum(num);
	dto.setContent(content);
	boolean isSuccess = CommentDao.getInstance().update(dto);
%>
{"isSuccess":<%=isSuccess %>}