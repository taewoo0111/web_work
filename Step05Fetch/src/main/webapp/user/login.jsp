<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	boolean isValid = false;
	
	if(id.equals("gura") && pwd.equals("1234")){
		isValid = true;
	}
%>

{"isSuccess": <%=isValid %>}