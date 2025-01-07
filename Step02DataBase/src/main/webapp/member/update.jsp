<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	MemberDto dto = new MemberDao().getinfo(num);
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	dto.setName(name);
	dto.setAddr(addr);
	new MemberDao().update(dto);
	boolean isSuccess = new MemberDao().update(dto);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<strong><%=dto.getNum()%></strong> 님의 정보를 변경했습니다.
			<a href="list.jsp">목록 보기</a>
		<%} else{ %>
			회원정보 변경 실패!
			<a href="updateform.jsp?num=<%=dto.getNum() %>">다시 변경</a>
		<%} %>
	</div>
</body>
</html>