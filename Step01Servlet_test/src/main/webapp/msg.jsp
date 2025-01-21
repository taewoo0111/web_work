<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/msg.jsp</title>
<style>
	.container{
		transform: scale(1.5);
		transform-origin: top left;
	}
</style>
</head>
<body>
	<div class="container">
		<input type="text" id="inputMsg" placeholder="문자열 입력..."/>
		<button id="addBtn">추가하기</button>
		<button id="deleteBtn">삭제하기</button>
	</div>
	<div class="list">
		<h1>내가 추가한 문자열</h1>
		<ul id="msglist">
			
		</ul>
	</div>
<script>
	document.querySelector("#addBtn").addEventListener("click", ()=>{
		const msg = document.querySelector("#inputMsg").value;
		const newli = document.createElement("li");
		newli.innerText = msg;
		document.querySelector("#msglist").append(newli);
		document.querySelector("#inputMsg").value = "";
	});
</script>
</body>
</html>