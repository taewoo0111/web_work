<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pwd = request.getParameter("pwd");
	
	GuestDto dto = GuestDao.getInstance().getData(num);
	
	if(pwd.equals(dto.getPwd())) {
		GuestDao.getInstance().delete(num);
		String cPath = request.getContextPath();
		response.sendRedirect(cPath + "/guest/list.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/delete.jsp</title>
</head>
<body>
	<script>
		alert("비밀번호가 일치하지 않습니다!");
		location.href = "${pageContext.request.contextPath }/guest/list.jsp";
	</script>
</body>
</html>