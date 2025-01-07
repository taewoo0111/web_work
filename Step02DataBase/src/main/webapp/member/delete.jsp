<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	
	MemberDao dao = new MemberDao();
	boolean isSuccess = dao.delete(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/delete.jsp</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<strong><%=num %></strong> 번 님의 정보를 삭제했습니다.
			<a href="list.jsp">목록 보기</a>
		<%} else{ %>
			회원정보 저장 실패!
			<a href="list.jsp">다시 삭제</a>
		<%} %>
	</div>
</body>
</html>