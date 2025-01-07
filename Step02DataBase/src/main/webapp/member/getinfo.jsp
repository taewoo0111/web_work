<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	MemberDto dto = new MemberDao().getinfo(num);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/getinfo.jsp</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(dto.getNum() == num){ %>
			<strong><%=num %></strong> 번 님의 정보를 가져왔습니다.
			<p><%=dto.getNum()%></p>
			<p><%=dto.getName() %></p>
			<p><%=dto.getAddr() %></p>
			<a href="update.jsp">수정하러 가기</a>
		<%} else{ %>
			없는 회원번호입니다!
			<a href="list.jsp">다시 가져오기</a>
		<%} %>
	</div>
</body>
</html>