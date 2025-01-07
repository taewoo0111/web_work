<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDao dao = new MemberDao();
	List<MemberDto> list = dao.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/list.jsp</title>
</head>
<body>
	<div class="container">
		<a href="insertform.jsp">회원추가</a>
		<br>
		<a href="/Step02DataBase/member/insertform.jsp">회원추가2</a>
		<br>
		<a href="${page.Context.request.contextPath }insertform.jsp">회원추가3</a>
		<h1>회원 목록입니다.</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
					<th>삭제</th>
					<th>수정</th>
				</tr>
			</thead>
			<tbody>
			<% for(MemberDto dto: list){%>
				<tr>
					<td><%=dto.getNum() %></td>
					<td><%=dto.getName() %></td>
					<td><%=dto.getAddr() %></td>
					<td><a href="delete.jsp?num=<%=dto.getNum()%>">삭제</a></td>
					<td><a href="updateform.jsp?num=<%=dto.getNum()%>">수정</a></td>
				</tr>
			<%} %>			
			</tbody>
		</table>
	</div>
</body>
</html>