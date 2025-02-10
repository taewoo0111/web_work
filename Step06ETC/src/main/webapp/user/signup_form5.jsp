<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form5.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container" id="app">
		<h1 class="text-success">회원가입 폼 입니다.</h1>
		<form action="signup.jsp" method="post" id="signupForm">
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<input :class="{'is-valid': isIdValid, 'is-invalid': !isIdValid && isIdDirty}" 
					@input="onIdInput" class="form-control" type="text" name="id" id="id" placeholder="아이디 입력..."/>
				<small class="form-text text-info">영문자 소문자로 시작하고 5~10 글자 이네로 입력하세요</small>
				<div class="valid-feedback alert alert-success">사용 가능한 아이디 입니다.</div>
				<div class="invalid-feedback alert alert-danger">사용할 수 없는 아이디 입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input :class="{'is-invalid': !isPwdValid && isPwdDirty}" @input="onPwdInput"
					class="form-control" type="password" name="pwd" id="pwd" placeholder="비밀번호 입력..."/>
				<small class="form-text text-info">특수문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback alert alert-danger">사용이 불가능한 비밀번호입니다!</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input :class="{'is-invalid': !isPwdSame && isPwdSameDirty, 'is-valid': isPwdSame}" @input="samePwdInput"
					class="form-control" type="password" id="pwd2" :disabled="!isPwdValid" placeholder="비밀번호 확인!"/>
				<div class="valid-feedback alert alert-success">비밀번호가 일치합니다!</div>
				<div class="invalid-feedback alert alert-danger">비밀 번호가 일치하지 않아요!</div>
			</div>
			<div class="mb-2">
				<label for="email" class="form-label">이메일</label>
				<input @input="onEmailInput" :class="{'is-invalid': !isEmailValid && isEmailDirty}" 
				type="email" name="email" id="email" class="form-control" placeholder="이메일 입력..."/>
				<div class="invalid-feedback alert alert-danger">이메일 형식에 맞게 입력하세요</div>
			</div>
			<button class="btn btn-success" type="submit" :disabled="!isIdValid || !isPwdValid || !isEmailValid || !isPwdSame">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send-plus-fill" viewBox="0 0 16 16">
  					<path d="M15.964.686a.5.5 0 0 0-.65-.65L.767 5.855H.766l-.452.18a.5.5 0 0 0-.082.887l.41.26.001.002 4.995 3.178 1.59 2.498C8 14 8 13 8 12.5a4.5 4.5 0 0 1 5.026-4.47zm-1.833 1.89L6.637 10.07l-.215-.338a.5.5 0 0 0-.154-.154l-.338-.215 7.494-7.494 1.178-.471z"/>
 					<path d="M16 12.5a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0m-3.5-2a.5.5 0 0 0-.5.5v1h-1a.5.5 0 0 0 0 1h1v1a.5.5 0 0 0 1 0v-1h1a.5.5 0 0 0 0-1h-1v-1a.5.5 0 0 0-.5-.5"/>
				</svg>
				가입
			</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		new Vue({
			el:"#app",
			data:{
				isIdValid:false,
				isIdDirty:false,
				isPwdValid:false,
				isPwdDirty:false,
				isPwdSame:false,
				isPwdSameDirty:false,
				isEmailValid:false,
				isEmailDirty:false,
				pwd:"",
				pwd2:"",
			},
			methods:{
				onIdInput(e){
					this.isIdDirty=true;
					const reg_id = /^[a-z].{4,9}$/;
					
					let inputId = e.target.value;
					if(!reg_id.test(inputId)){
						this.isIdValid = false;
						return;
					}
					fetch("${pageContext.request.contextPath }/user/checkid.jsp?id="+inputId)
					.then(res=>res.json())
					.then(data=>{
						if(data.canUse){
							this.isIdValid = true;
						}else{
							this.isIdValid = false;
						}
					});
				},
				onEmailInput(e){
					this.isEmailDirty=true;
					const reg_email=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
					let email = e.target.value;
					if(reg_email.test(email)){
						this.isEmailValid = true;	
					}else{
						this.isEmailValid = false;
					}
				},
				onPwdInput(e){
					this.isPwdDirty=true;
					const reg_pwd = /[\W]/;
					this.pwd = e.target.value;
					this.isPwdValid=reg_pwd.test(this.pwd);
				},
				samePwdInput(e){
					this.isPwdSameDirty=true;
					this.pwd2=e.target.value;
					this.isPwdSame=(this.pwd===this.pwd2);
				}
			}
		});
	</script>
</body>
</html>