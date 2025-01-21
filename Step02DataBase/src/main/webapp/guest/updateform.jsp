<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	GuestDto dto = GuestDao.getInstance().getData(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guest/updateform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/">Home</a>
				</li>
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/guest/list.jsp">방명록 목록</a>
				</li>
				<li class="breadcrumb-item">글 수정</li>
			</ol>
		</nav>
		<h1>방명록 글 수정 폼</h1>
		<form action="update.jsp" method="post">
			<div class="mb-2">
				<label for="num" class="form-label">번호</label>
				<input class="form-control" type="text" name="num" id="num" value="<%=dto.getNum() %>" readonly/>
			</div>
			<div class="mb-2">
				<label for="writer" class="form-label">작성자</label>
				<input class="form-control" type="text" name="writer" id="writer" value="<%=dto.getWriter() %>"/>
			</div>
			<div class="mb-2">
				<label for="content" class="form-control">내용</label>
				<textarea class="form-control"name="content" id="content" style="hegiht:200px"><%=dto.getContent() %></textarea>
			</div>
			<div class="mb-2">
				<label for="pwd" class="form-control">비밀번호</label>
				<input type="text" name="pwd" id="pwd" class="form-control" />
			</div>
			<button type="submit" class="btn btn-outline-success btn-sm">수정확인</button>
			<button type="reset" class="btn btn-outline-danger btn-sm">취소</button>
		</form>
		<button id="searchBtn" type="submit" class="btn btn-primary btn-md mt-2">비밀번호 찾기</button>
	</div>
	<script>
		document.querySelector("#searchBtn").addEventListener("click", ()=>{
			let pwd = <%=dto.getPwd()%>
			alert("비밀번호: " + pwd);
		});
	</script>
</body>
</html>