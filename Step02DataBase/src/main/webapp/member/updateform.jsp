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
<title>/member/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>선택한 정보를 보여드릴게요</h1>
		<p>이름: <%=dto.getName() %></p>
		<p>주소: <%=dto.getAddr() %></p>
	</div>
	<div class="container">
		<h1>수정하기</h1>
		<form action="${page.Context.request.contextPath }update.jsp?num=<%=dto.getNum() %>" method="post">
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" placeholder="이름 입력..." />
			</div>
			<div>
				<label for="addr">주소</label>
				<input type="text" name="addr" id="addr" placeholder="주소 입력..." />
			</div>
			<button type="submit">수정</button>
		</form>
<%-- 	</div>
	 <div class="container">
		<h1>주소 수정하기</h1>
		<form action="${page.Context.request.contextPath }update.jsp" method="post">
			<div>
				<label for="addr">주소</label>
				<input type="text" name="addr" id="addr" placeholder="주소 입력..." />
			</div>
			<button type="submit">수정</button>
		</form>
	</div>  --%>
</body>
</html>