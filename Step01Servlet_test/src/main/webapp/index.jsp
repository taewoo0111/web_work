<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<div class="container">
		<h1>index.jsp 페이지 입니다.</h1>
		<p><a href="friends.jsp">친구 목록보기</a></p>
		<p><a href="member.jsp">멤버 목록보기</a></p>
		<p><a href="memberlist.jsp">멤버 정보보기</a></p>
	</div>
	<div class="form1">
		<form action="send.jsp">
			<input type="text" name="msg" id="inputMsg" placeholer="문자열 입력: " />
			<button type="submit" id="addBtn">문자열 전달하기</button>
		</form>
	</div>
	<script>
	const f1 =()=>{
        const msg = document.querySelector("#inputMsg").value;
        const newli = document.createElement("li");
        newli.innerText = msg;
        document.querySelector("#msgList").append(newli); 
        document.querySelector("#inputMsg").value = "";
    };
    document.querySelector("#addBtn").addEventListener("click",f1);
	</script>
</body>
</html>