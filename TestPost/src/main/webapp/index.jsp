<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 만들어보는 프로젝트</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container mt-5">
		<div class="container mt-5">
			<h1>DB 연결 확인</h1>
				<a href="connection/test.jsp" class="btn btn-warning">DB 연결 확인</a>
		</div>
		<div class="container mt-5">
			<c:choose>
				<c:when test="${empty User.id }">
					<h1>비회원 페이지</h1>
						<a href="user/signup-form.jsp" class="btn btn-success">회원가입</a>
						<a href="user/login-form.jsp" class="btn btn-success">로그인</a>
				</c:when>
				<c:when test="${not empty User.id }">
					<h1>회원 페이지</h1>
						<a href="post/list.jsp" class="btn btn-primary">게시판 가기</a>
						<a href="user/logout.jsp" class="btn btn-secondary">로그아웃</a>
				</c:when>
			</c:choose>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>