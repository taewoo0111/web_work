<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/singup_getlist.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container" id="app">
		<h1>회원정보 목록 보기</h1>
		<button class="btn btn-primary btn-sm" @click="getinfo">정보 가져오기</button>
		<table class="table table-info table-bordered bordered-primary">
			<thead>
				<tr class="text-center">
					<th scope="col">아이디</th>
					<th scope="col">비밀번호</th>
					<th scope="col">이메일</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="tmp in infos">
					<td>{{tmp.id}}</td>
					<td>{{tmp.pwd}}</td>
					<td>{{tmp.addr}}</td>
				</tr>
			</tbody>
		</table>
	</div>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
	new Vue({
		el:"#app",
		data:{
			infos:""
		},
		methods:{
			getinfo(){
				fetch("")
				.then(res=>res.json())
				.then(data=>{
					console.log(data);
				})
				.catch(error=>{
					console.log("에러정보:" + error);
				});
				
		}
	})
</script>
</body>
</html>