<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form5fetch.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container" id="app">
		<h1 class="text-success">회원가입 폼 입니다.</h1>
		<form action="signup.jsp" method="post" id="signupForm" @submit.prevent="onSubmit">
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<input :class="{'is-valid': isIdValid, 'is-invalid': !isIdValid && isIdDirty}" 
					@input="onIdInput" class="form-control" type="text" name="id" id="id"/>
				<small class="form-text">영문자 소문자로 시작하고 5~10 글자 이네로 입력하세요</small>
				<div class="valid-feedback alert alert-success">사용 가능한 아이디 입니다.</div>
				<div class="invalid-feedback alert alert-danger">사용할 수 없는 아이디 입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input :class="{'is-invalid': !isPwdValid && isPwdDirty}" @input="onPwdInput"
					class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text">특수문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback alert alert-danger">사용이 불가능한 비밀번호입니다!</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input :class="{'is-invalid': !isPwdSame && isPwdSameDirty, 'is-valid': isPwdSame}" @input="samePwdInput"
					class="form-control" type="password" id="pwd2" :disabled="!isPwdValid"/>
				<div class="valid-feedback alert alert-success">비밀번호가 일치합니다!</div>
				<div class="invalid-feedback alert alert-danger">비밀 번호가 일치하지 않아요!</div>
			</div>
			<div class="mb-2">
				<label for="email" class="form-label">이메일</label>
				<input @input="onEmailInput" :class="{'is-invalid': !isEmailValid && isEmailDirty}" 
				type="email" name="email" id="email" class="form-control" />
				<div class="invalid-feedback alert alert-danger">이메일 형식에 맞게 입력하세요</div>
			</div>
			<button class="btn btn-success" type="submit" :disabled="!isIdValid || !isPwdValid || !isEmailValid || !isPwdSame">가입</button>
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
				infos:""
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
				},
				onSubmit(event){
					const data = new FormData(event.target);
					const queryString = new URLSearchParams(data).toString();
					const url = event.target.action;
					fetch(url, {
						method:"POST",
						headers:{"Content-Type":"application/x-www-form-urlencoded; charset=utf-8"},
						body:queryString
					})
					.then(res=>res.json())
					.then(data=>{
						this.infos = data
						alert("회원가입 성공!");
						<%response.sendRedirect(request.getContextPath() + "/index.jsp");%>
					})
					.catch(error=>{
						console.log("에러정보:"+error);
					});
				}
			}
		});
	</script>
</body>
</html>