<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form2.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1 class="text-success">회원가입 폼 입니다.</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div class="container">
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
				<small class="form-text">영문자 소문자로 시작하고 5~10 글자 이네로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디 입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
			</div>
			<button class="btn btn-success" type="submit" disabled="disabled">가입</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		// && isPwdValid && isEmailValid		
		let isIdValid = false;
		let isPwdValid = false;
		let isEmailValid = false;
		const checkForm = ()=>{
			if((isIdValid)){
				$("button").prop("disabled", false);
			} else{
				$("button").prop("disabled", true);
			}
		};
		
		$("#id").on("input", (event)=>{
			event.target.classList.remove("is-valid", "is-invalid");
			const reg_id = /^[a-z].{4,9}$/;
			let inputId = event.target.value;
			if(!reg_id.test(inputId)){
				event.target.classList.add("is-invalid");
				isIdValid = false;
			}else{
				event.target.classList.add("is-valid");
				isIdValid = true;
			}
			checkForm();
		});
	</script>
</body>
</html>