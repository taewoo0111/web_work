<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/memer.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 1명의 정보</h1>
		<p>번호: <strong>${member.getNum() }</strong></p>
		<p>이름: <strong>${member.getName() }</strong></p>
		<p>주소: <strong>${member.addr }</strong></p>
	</div>
</body>
</html>