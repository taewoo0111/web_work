<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	String pwd = request.getParameter("pwd");
	GuestDto dto = GuestDao.getInstance().getData(num);
	dto.setWriter(writer);
	dto.setContent(content);
	
	if(pwd.equals(dto.getPwd())) {
		GuestDao.getInstance().update(dto);
		String cPath = request.getContextPath();
		response.sendRedirect(cPath + "/guest/list.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/update.jsp</title>
</head>
<body>
	<script>
		alert("비밀번호가 일치하지 않습니다!");
		location.href = "${pageContext.request.contextPath }/guest/updateform.jsp?num=<%=dto.getNum()%>";
	</script>
</body>
</html>