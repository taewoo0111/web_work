<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container mt-5">
		<h1>로그인 양식입니다.</h1>
		<form action="login.jsp" method="post" class="needs-validation">
			<div class="mb-3">
				<label for="username" class="form-label">닉네임</label>
				<input class="form-control" type="text" name="username" id="username" />
			</div>
			<div class="mb-3">
				<label for="id" class="form-label">아이디</label>
				<input class="form-control" type="text" name="id" id="id" />
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">비밀번호</label>
				<input class="form-control" type="password" name="password" id="password" />
			</div>
			<button class="btn btn-success" type="submit">로그인하기!</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>