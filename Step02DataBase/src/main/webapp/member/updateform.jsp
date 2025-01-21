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
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath }/">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath }/member/list.jsp">회원목록</a>
				</li>
				<li class="breadcrumb-item">회원추가</li>
			</ol>
		</nav>
		<h1>선택한 정보를 보여드릴게요</h1>
		<p>번호: <%=dto.getNum() %></p>
		<p>이름: <%=dto.getName() %></p>
		<p>주소: <%=dto.getAddr() %></p>
	</div>
	<div class="container">
		<h1>수정하기</h1>
		<form action="${pageContext.request.contextPath }/member/update.jsp?num=<%=dto.getNum() %>" method="post">
			<div class="mb-3">
				<label class="form-label" for="name">이름</label>
				<input class="form-control" type="text" name="name" id="name" placeholder="이름 입력..." />
			</div>
			<div class="mb-3">
				<label class="form-label" for="addr">주소</label>
				<input class="form-control" type="text" name="addr" id="addr" placeholder="주소 입력..." />
			</div>
			<button class="btn btn-success" type="submit">수정</button>
		</form>
	</div>
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