<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	
	boolean canUse = false;
	
	if(!id.equals("kimgura")){
		canUse = true;
	}
%>
{"canUse":<%=canUse %>}