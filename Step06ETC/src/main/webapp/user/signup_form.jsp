<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form.jsp</title>
<style>
	.invalid-feedback{
		display: none;
		color: red;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다.</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id"/>
				<small>영문자 소문자로 시작하고 5~10 글자 이네로 입력하세요</small>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
			</div>
			<button type="submit">가입</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		/*
			폼에 submit 이벤트가 발생하면 입력한 내용을 검증해서 조건을 만족하지 못하면
			폼 제출을 막는 예제
		*/
		$("#signupForm").on("submit",(event)=>{
			const reg_id = /^[a-z].{4,9}$/;
			const inputId = $("#id").val();
			if(!reg_id.test(inputId)){
				event.preventDefault();
				$(".invalid-feedback").css("display","block");
			}
		});
	</script>
</body>
</html>