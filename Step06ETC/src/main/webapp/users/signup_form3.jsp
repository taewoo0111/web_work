<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form3.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1 class="text-success">회원가입 폼 입니다.</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
				<small class="form-text">영문자 소문자로 시작하고 5~10 글자 이네로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디 입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password"  id="pwd2"/>
			</div>
			<div class="mb-2">
				<label for="email" class="form-label">이메일</label>
				<input type="email" name="email" id="email" class="form-control" />
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요</div>
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
			if((isIdValid && isPwdValid && isEmailValid)){
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
		
		const checkPwd = ()=>{
			const reg_pwd = /[\W]/;
			let pwd = $("#pwd").val();
			let pwd2 = $("#pwd2").val();
			$("#pwd").removeClass("is-valid is-invalid");
			if(!reg_pwd.test(pwd) || !reg_pwd.test(pwd2)){
				$("#pwd").addClass("is-invalid");
				isPwdValid = false;
				checkForm();
				return;
			}
			if(pwd == pwd2){
				$("#pwd").addClass("is-valid");
				isPwdValid = true;
			} else{
				$("#pwd").addClass("is-invalid");
				isPwdValid = false;
			}
			checkForm();
		};
		
		$("#pwd").on("input", checkPwd);		
		$("#pwd2").on("input", checkPwd);
		
		
		$("#email").on("input",()=>{
			const reg_email=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			const email = $("#email").val();
			$("#email").removeClass("is-valid is-invalid");
			if(reg_email.test(email)){
				isEmailValid = true;
				$("#email").addClass("is-valid");	
			}else{
				isEmailValid = false;
				$("#email").addClass("is-invalid");
			}
			checkForm();
		});
	</script>
</body>
</html>